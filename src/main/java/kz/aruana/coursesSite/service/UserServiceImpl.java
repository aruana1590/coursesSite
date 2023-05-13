package kz.aruana.coursesSite.service;

import kz.aruana.coursesSite.dto.request.UserDtoAuthorizationRequest;
import kz.aruana.coursesSite.dto.request.UserDtoRegistrationRequest;
import kz.aruana.coursesSite.dto.request.UserDtoResponse;
import kz.aruana.coursesSite.entities.Users;
import kz.aruana.coursesSite.exceptions.*;
import kz.aruana.coursesSite.mapper.UserMapper;
import kz.aruana.coursesSite.repositories.UsersRepository;
import kz.aruana.coursesSite.security.JWTTokenProvider;
import kz.aruana.coursesSite.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor

public class UserServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder encoder;
    @Lazy
    //webConfig и userServiceImpl вызывают друг друга бесконечно (рекурсия), чтобы AuthenticationManager вызывался только при необходимости (когда объект вызывается) используем эту аннотацию
    private final AuthenticationManager authenticationManager;//даем доступ к классу AuthenticationManager, его методам
    private final JWTTokenProvider jwtTokenProvider;

    //loadUserByUsername - готовый метод класса UserDetail


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.getByUsernameThrowException(username);
        return new UserPrincipal(user);//т.к. метод loadUserByUsername возвращает тип UserDetails то нужно перевести тип
        //Users в тип UserDetails. Для этого создаем класс UserPrincipal, в котором конвертируем Users в UserDetails
    }
    @Override
    public Optional<Users> getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Users getByUsernameThrowException(String username) {
        return this.getByUsername(username).orElseThrow(()-> new NotFoundException("There is no such username"));
    }
    private Users save(Users user){
        return usersRepository.save(user);
    }
    @Override
    public void registration(UserDtoRegistrationRequest userDtoRegistrationRequest) {
    String username = userDtoRegistrationRequest.getUserName().toLowerCase().trim();
    String password = userDtoRegistrationRequest.getPassword().trim();

    Optional <Users> user=this.getByUsername(username);
        if(user.isPresent()) throw new AlreadyExists("Username already exists");
    try{
        Users createdUser = new Users();
        createdUser.setUsername(username);
        createdUser.setPassword(encoder.encode(password));
        createdUser.setName(userDtoRegistrationRequest.getName());
        this.save(createdUser);
    } catch (Exception e) {
        log.error(e.getMessage());

        }
    }
//мы оборачиваем метод authorization в оболочку ResponseEntity т.к. мы хотим, чтобы ответ содержал в себе не только body (как обычно),
    //но и header и status.
    @Override
    public ResponseEntity <UserDtoResponse> authorization(UserDtoAuthorizationRequest userDtoAuthorizationRequest, HttpServletRequest request) {
        String username = userDtoAuthorizationRequest.getUsername().toLowerCase().trim();
        String password = userDtoAuthorizationRequest.getPassword().trim();
        try{
        this.authenticate(username,password);}//если ошибку не выдает, то далее формируется jwt token и возвращается клиенту
        catch (Exception e){
        log.error(e.getMessage());
        throw new InvalidAuthException("Invalid login or password");
        }
        try{
        Users user = this.getByUsernameThrowException(username);//нашли юзера по username
        UserPrincipal userPrincipal= new UserPrincipal(user);//перевели его в UserPrincipal
        String IP = jwtTokenProvider.getIpFromClient(request);//вывели его ip
        HttpHeaders httpHeaders=this.JWTHeader(userPrincipal,IP);//сформировали jwt token
        return new ResponseEntity<>(UserMapper.userToDto(user)/*body - содержит передаваемую инфу*/, httpHeaders/*содержит ip, jwt token*/, HttpStatus.OK);// вложить в ResponseEntity просто user мы не можем, тк метод
        //authorization имеет тип UserDtoResponse, а user - тип Users. Поэтому был создан дополнительно класс UserMapper, в котором произошло конвертирование
        //user из типа Users в тип UserDtoResponse
            }
            catch(Exception e){
                log.error(e.getMessage());
            throw new UnexpectedException("Возникла непредвиденная ошибка");
            }
    }
    private HttpHeaders JWTHeader(UserPrincipal userPrincipal, String IP) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String JWT = jwtTokenProvider.generateToken(userPrincipal, IP);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, JWT);
        return  httpHeaders;
    }

    private void authenticate(String username, String password){ //метод для проверка подлинности пользователя путём
        // сравнения введённого им пароля (для указанного логина) с паролем, сохранённым в базе данных
        // пользовательских логинов;
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));//класс компонент спринг
        // секьюрити, который вызывает метод authenticate и передает пароль и логин, введеный пользователем, но оборачивает их в
        // обвертку, чтобы не передавать голый пароль. Если логин и пароль не совпадают с сохраненными в базе, то метод выдает ошибку
        //метод пока не понимает с чем сравнивать полученные данные, чтобы он понимал, нужно имплеметировать метод loadUserByUsername класса UserDetailService, который
        //будет выполнять поиск пользователя по username. Для того чтобы authenticate использовал loadUserByUsername в WebConfig
        //нужно использовать метод  configure() WebSecurityConfigurerAdapter
    }


}
