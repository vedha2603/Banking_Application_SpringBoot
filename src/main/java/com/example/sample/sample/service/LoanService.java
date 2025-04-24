// src/main/java/com/example/sample/service/LoanService.java
package com.example.sample.sample.service;

import com.example.sample.sample.dto.LoanForm;
import com.example.sample.sample.model.Loan;

public interface LoanService {
    Loan processApplication(LoanForm form);
}