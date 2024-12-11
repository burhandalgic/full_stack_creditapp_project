package com.burhan.creditmanagement.evaluation.dao.entities;


import com.burhan.creditmanagement.evaluation.webapi.response.GetCustomerLimitResponce;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="CustomerLimit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name  = "id")
    private Long id ;

    @Column(name  = "customerNo", unique = true)
    private Long customerNo;

    @Column(name  = "limitt")
    private BigDecimal limitt;


    public GetCustomerLimitResponce toGetCustomerLimitResponce(){
        return new GetCustomerLimitResponce(this.id,this.customerNo,this.limitt);
    }




}
