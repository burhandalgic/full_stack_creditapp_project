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
@Table( name = "mail_log" )
public class MailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id")
    private Long id;

    @Column(name  = "email_address")
    private String email;

    @Column(name  = "email_text")
    private String emailText;

    @Column(name  = "log_status")
    private String logStatus;

    @Column(name  = "sent_time")
    private Date sentTime;

}