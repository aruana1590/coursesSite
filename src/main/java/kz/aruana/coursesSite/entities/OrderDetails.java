package kz.aruana.coursesSite.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="order_detail")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name="video_id", nullable = false)
    private Videos video;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
   private LocalDateTime paymentDate;

    @PrePersist
    private void onCreate(){
        this.paymentDate=LocalDateTime.now();
    }



}
