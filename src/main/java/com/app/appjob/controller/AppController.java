package com.app.appjob.controller;

//Repositories where data is stored and we can ask for it
import com.app.appjob.repo.UserRepo;

import io.swagger.v3.oas.annotations.Operation;

import com.app.appjob.repo.AccountRepo;

import java.util.*;
//Models: user and account, which have the attributes and methods of each class
import com.app.appjob.model.Account;
import com.app.appjob.model.User;

//Tags useful of Lombok and HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Declaration of the service with REST conttroller

@RestController
public class AppController {

    //autowired is used to managed the relation between dependencies. 
    //Important: We need to specify for each repository an @Autowired
    //repositories to use which are User and Account, to have access to their information
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;

    //HTTP methods

    //-----------------USER METHODS-------------------------

    // -------Add Users--------
    @Operation(summary = "Add users with name and accountsList")
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userObj = userRepo.save(user);

        return new ResponseEntity<>(userObj,HttpStatus.OK);

    }

    //-------- UserList(UserId, Name, AccountsList)---------------
    // Get all users with their ID, name and account list
    @Operation(summary = "Get the User List")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList(){
        try{
            List<User> userList = new ArrayList<>();
            //query all the User objects
            userRepo.findAll().forEach(userList::add);

            //if there are not registered users
            if (userList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            //if there are registered users
            return new ResponseEntity<>(userList,HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    

    //-------- Read item details (by item number)-----------
    // Get User by id

    //the parameter is the User ID which is read on the URL in this path with {userId}
    @Operation(summary = "Read item details by userId")
    @GetMapping("/getUserById/{userId}")
    //Returns the user with its details
    //the parameter is the User ID which is read on the URL specifying with @PathVariable to read data from URL
    public ResponseEntity<User> getUserById (@PathVariable Long userId){
        Optional<User> userData = userRepo.findById(userId);

        //If exists the user
        if (userData.isPresent()){
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
        //if the user does not exist
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




    //-----------------ACCOUNT METHODS-------------------------

   

 //-------- Account List (AccountId, AccountName, AccauntCurrency )-----------
    // Get all accounts with their ID, name and account list

    @Operation(summary = "Gell the Accounts List")
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccountList(){
        try{
            List<Account> accountList = new ArrayList<>();
            // with a for-each sequence adds each list that exist in the accountRepo to the accountList
            accountRepo.findAll().forEach(accountList::add);

            //if there are not registered accounts
            if (accountList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            //if there are registered accounts
            
            return new ResponseEntity<>(accountList,HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    //-------- Read item details (by item number)-----------
    // Get Account by id

    //the parameter is the Account ID which is read on the URL in this path with {accountId}
    @Operation(summary = "Read item (account) by accountId")
    @GetMapping("/getAccountById/{accountId}")
    //Returns the account with its details
    //the parameter is the Account ID which is read on the URL specifying with @PathVariable to read data from URL
    public ResponseEntity<Account> getAccountById (@PathVariable Long accountId){
        Optional<Account> accountData = accountRepo.findById(accountId);

        //If exists the account
        if (accountData.isPresent()){
            //return the specific account that has founded with its ID
            return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
        }
        //if the Account does not exist
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    
    //-------- Add Account to User-----------
    // We use the HTTP method: POST

    @Operation(summary = "Add account to User specifying the userId")
    @PostMapping("/addAccount/{userId}")
    // @RequestBody is to read the object to add, in this case Account object
    // And request the usereId to know which user owns the account
    public ResponseEntity<User> addAccount(@RequestBody Account account,@PathVariable Long userId){

        //First, find the user with the userId
        Optional<User> userObj = userRepo.findById(userId);

        // if the user exists
        if(userObj.isPresent()){
            //get the object User with the User object in the repository
            User user = userObj.get();
            // add the account to the user
            user.addAccount(account);
            //Account accountObj = accountRepo.save(account);
            //Save the userr with the new account in the repository
            User newUserObj = userRepo.save(user);

            return new ResponseEntity<>(newUserObj,HttpStatus.OK); 
        }

        //if the user does not exist
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);     


    }

    

    //-------- Delete an Account from UserAccountsList -----------
    // We use the HTTP method: DELETE
    @Operation(summary = "Delete and account from UserAccountsList")
    @DeleteMapping ("/deleteAccount/{accountId}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long accountId){
        accountRepo.deleteById(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




   

}
