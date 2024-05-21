package com.app.appjob.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import jakarta.persistence.*;


@Entity
//Name of the table on the H2 DB
@Table(name="Accounts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Account {

   
    //Generates automatically the userId adding the user to the database with POST
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="acc_id")
    private Long accountId;
    private String accountName;
    //USD, MXN, EUR, GBP
    private String accountCurrency;

    
}
