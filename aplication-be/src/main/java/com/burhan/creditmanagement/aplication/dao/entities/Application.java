package com.burhan.creditmanagement.aplication.dao.entities;
import com.burhan.creditmanagement.aplication.business.dto.GetApplicationResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="Applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name  = "applicationNo")
    private Long applicationNo ;

    @Column(name  = "customerNo")
    private Long customerNo;

    @Column(name  = "status")
    private Integer status;

    @Column(name  = "creditType")
    private Integer creditType;

    @Column(name  = "creditAmount")
    private BigDecimal creditAmount;

    public GetApplicationResponse toGetApplicationResponce () {
        return new GetApplicationResponse(this.applicationNo,this.customerNo,this.status,this.creditType,this.creditAmount);
    }


}
