package kz.aruana.coursesSite.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class VideoDtoResponse {

    private String courseName;

    private String section;

    private String author;

    private String courseInfo;

    private Float price;

    private Float rating;

    private Float sold;
    private String reviews;
}
