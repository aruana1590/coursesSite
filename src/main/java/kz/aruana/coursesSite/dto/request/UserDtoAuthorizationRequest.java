package kz.aruana.coursesSite.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter

public class UserDtoAuthorizationRequest {
    @NotBlank(message="Login should be specified")
    private String username;
    @NotBlank(message="Password should be specified")
    private String password;
}
