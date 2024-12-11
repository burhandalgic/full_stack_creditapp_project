package com.burhan.creditmanagement.aplication.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "mailLogStatistics" )
public class MailLogStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id")
    private Long id;

    @Column(name  = "ok_count")
    private long okCount;

    @Column(name  = "red_count")
    private long redCount;

    @Column(name  = "record_time")
    private Date recordTime;


}
