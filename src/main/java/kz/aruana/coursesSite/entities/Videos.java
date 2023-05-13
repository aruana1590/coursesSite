package kz.aruana.coursesSite.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="videos")
public class Videos {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    @Column(name="course_name", nullable=false)
    private String courseName;
    @Column(nullable = false)
    private String section;
    @Column(nullable = false)
    private String author;
    @Column(name="course_info", nullable = false)
    private String courseInfo;
    @Column(nullable = false)
    private Float price;
    @Column(nullable = false)
    private Float rating;
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private Float sold;
    @Column
    private String reviews;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }


}
