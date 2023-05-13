package kz.aruana.coursesSite.dto.request;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class VideoDtoRequest {
    @NotBlank(message="Title should be specified")
    private String courseName;
    @NotBlank(message="Section should be specified")
    private String section;
    @NotBlank(message="Author should be specified")
    private String author;
    @NotBlank(message="Information should be specified")
    private String courseInfo;
    @NotNull(message = "Price may not be empty")
    private Float price;
    @NotNull(message = "Rating may not be empty")
    private Float rating;
    @NotNull(message = "Quantity of sold may not be empty")
    private Float sold;
    private String reviews;
}
