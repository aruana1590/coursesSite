package kz.aruana.coursesSite.service;

import kz.aruana.coursesSite.dto.request.UserDtoAuthorizationRequest;
import kz.aruana.coursesSite.dto.request.UserDtoRegistrationRequest;
import kz.aruana.coursesSite.dto.request.UserDtoResponse;
import kz.aruana.coursesSite.entities.Users;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UsersService {

    Optional<Users> getByUsername(String username);

    Users getByUsernameThrowException(String username);

    void registration(UserDtoRegistrationRequest userDtoRegistrationRequest);
    ResponseEntity<UserDtoResponse> authorization(UserDtoAuthorizationRequest userDtoAuthorizationRequest, HttpServletRequest request);
}
