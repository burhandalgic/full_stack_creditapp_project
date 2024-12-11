package com.burhan.customermanagement.customer.dao.entities;

import com.burhan.customermanagement.customer.webapi.response.GetCustomerResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
    @SequenceGenerator(name = "my_sequence", sequenceName = "my_custom_sequence", initialValue = 10000000, allocationSize = 1)
    @Column(name = "customer_no")
    private Long customerNo;


    @Column(name = "name")
    private String name;


    @Column(name = "surname")
    private String surname;


    @Column(name = "tc_no")
    private String tcNo;

    @Column(name = "mail")
    private String mail;


    @OneToMany(mappedBy = "customer" , cascade = CascadeType.REMOVE)
    private List<Address> addresses;

     public GetCustomerResponse toGetCustomerResponce () {
         return new GetCustomerResponse(this.customerNo,this.name,this.surname,this.tcNo,this.mail);
     }



}
