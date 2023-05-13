package kz.aruana.coursesSite.config;
//в папке config будут находиться классы, которые будут настраивать наши компоненты, например Spring Security

import kz.aruana.coursesSite.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceImpl userService;

    private final BCryptPasswordEncoder encoder;
    //в менеджере аутентификации мы указывает, что в классе UserServiceImpl находится метод, который осуществляет поиск по
    //username (это метод loadUserByUsername, тк он единственный имплементируется от UserDetailsService. Далее просим расшифровать
    // полученный пороль и только потом сравнивать с поролем в БД)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception { //делает все наши методы доступными, т.к. spring security ограничил доступ к нашим методам
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{//создание объекта authenticationManager, чтобы он
        //присваивался полю authenticationManager в UserServiceImpl, который без этого объекта будет иметь значение null
        return super.authenticationManagerBean();
    }


}
