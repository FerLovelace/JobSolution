package com.app.appjob.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import jakarta.persistence.*;


@Entity
//Name of the table on the H2 DB
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class User {

    //Generates automatically the userId adding the user to the database with POST
    // @Id is the key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_Id")
    private Long userId;
    private String userName;
  
    @OneToMany(cascade = CascadeType.ALL)
    //specify a foreign key of the table called "id"
    @JoinColumn(name="fk_user_Id",referencedColumnName = "user_Id")
    private List<Account> accountsList;


    public void addAccount(Account account){
        
        this.accountsList.add(account);   
    }
}
