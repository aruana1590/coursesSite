package kz.aruana.coursesSite.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;


@Getter


public class UserDtoRegistrationRequest {
    @NotBlank(message="Login should be specified")
    private String userName;
    @NotBlank(message="Password should be specified")
    private String password;
    @NotBlank(message="Name should be specified")
    private String name;
}
