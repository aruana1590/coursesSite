package kz.aruana.coursesSite.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RecordNotCreatedException  extends RuntimeException{
    public RecordNotCreatedException(String message) {
        super(message);
    }
}
