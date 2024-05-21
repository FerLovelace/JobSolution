package com.app.appjob.repo;

import com.app.appjob.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    
}
