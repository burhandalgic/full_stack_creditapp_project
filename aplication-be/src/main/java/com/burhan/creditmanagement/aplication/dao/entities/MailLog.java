package com.burhan.creditmanagement.aplication.dao.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "MailLog" )
public class MailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id")
    private Long id;

    @Column(name  = "emailAddress")
    private String email;

    @Column(name  = "emailText")
    private String emailText;

    @Column(name  = "logStatus")
    private String logStatus;

    @Column(name  = "sentTime")
    private Date sentTime;




}