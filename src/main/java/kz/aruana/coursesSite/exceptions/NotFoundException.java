package kz.aruana.coursesSite.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}
