package kz.aruana.coursesSite.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import kz.aruana.coursesSite.dto.request.UserDtoAuthorizationRequest;
import kz.aruana.coursesSite.dto.request.UserDtoRegistrationRequest;
import kz.aruana.coursesSite.dto.request.UserDtoResponse;
import kz.aruana.coursesSite.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UsersController {
    private final UsersService usersService;
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@Valid @RequestBody UserDtoRegistrationRequest userDtoRegistrationRequest){
        usersService.registration(userDtoRegistrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/authorization")
    public ResponseEntity<UserDtoResponse> authorization(@Valid @RequestBody UserDtoAuthorizationRequest userDtoAuthorizationRequest, HttpServletRequest request){
        return usersService.authorization(userDtoAuthorizationRequest, request);
    }

}
