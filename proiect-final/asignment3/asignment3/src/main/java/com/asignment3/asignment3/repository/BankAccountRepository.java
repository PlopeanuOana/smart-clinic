package com.asignment3.asignment3.repository;

import com.asignment3.asignment3.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    BankAccount findByUserId(Long user_id);

}
