// src/main/java/com/example/sample/service/LoanServiceImpl.java
package com.example.sample.sample.service;

import com.example.sample.sample.dto.*;
import com.example.sample.sample.ml.PythonScorer;
import com.example.sample.sample.model.Loan;
import com.example.sample.sample.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repo;
    private final PythonScorer scorer;

    public LoanServiceImpl(LoanRepository repo, PythonScorer scorer) {
        this.repo = repo;
        this.scorer = scorer;
    }

    @Override
    @Transactional
    public Loan processApplication(LoanForm f) {

        // Build feature object
        LoanFeatures features = new LoanFeatures(
                f.getNoOfDependents(),
                f.getIncomeAnnum(),
                f.getLoanAmount(),
                f.getLoanTerm(),
                f.getCibilScore(),
                f.getResidentialAssetsValue(),
                f.getCommercialAssetsValue(),
                f.getLuxuryAssetsValue(),
                f.getBankAssetValue());

        String decision = scorer.score(features);

        // Build and save entity
        Loan loan = new Loan(
                f.getLoanId(),
                f.getNoOfDependents(),
                f.getEducation(),
                f.getSelfEmployed(),
                f.getIncomeAnnum(),
                f.getLoanAmount(),
                f.getLoanTerm(),
                f.getCibilScore(),
                f.getResidentialAssetsValue(),
                f.getCommercialAssetsValue(),
                f.getLuxuryAssetsValue(),
                f.getBankAssetValue(),
                decision);

        return repo.save(loan);
    }
}
