// src/main/java/com/example/sample/repository/LoanRepository.java
package com.example.sample.sample.repository;

import com.example.sample.sample.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
/**
 * Persistence interface for Loan entities.
 * Spring Boot generates the implementation at runtime.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {


       List<Loan> findByApprovalStatus(String status);
       boolean existsByLoanId(String loanId);
    
   

}
