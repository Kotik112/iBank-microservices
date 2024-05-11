package com.ibank.loans.service.impl;

import com.ibank.loans.dto.LoansDto;
import com.ibank.loans.entity.Loans;
import com.ibank.loans.exception.LoanAlreadyExistsException;
import com.ibank.loans.exception.ResourceNotFoundException;
import com.ibank.loans.repository.LoansRepository;
import com.ibank.loans.service.ILoansService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static com.ibank.loans.constants.LoansConstants.HOME_LOAN;
import static com.ibank.loans.constants.LoansConstants.NEW_LOAN_LIMIT;
import static com.ibank.loans.mapper.LoansMapper.mapToLoans;
import static com.ibank.loans.mapper.LoansMapper.mapToLoansDto;


@Service
public class LoansServiceImpl implements ILoansService {

    private final LoansRepository loansRepository;

    public LoansServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    /**
     * Create a new loan based on the mobile number
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists for the given mobile number: " + mobileNumber);
        }
        Loans newLoan = createNewLoan(mobileNumber);
        loansRepository.save(newLoan);
    }

    /**
     * Create a new loan based on the mobile number
     *
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(HOME_LOAN);
        newLoan.setTotalLoan(NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * Fetch the loan details based on the mobile number
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));

        return mapToLoansDto(loan, new LoansDto());
    }

    /**
     * Update the loan details based on LoanDto object
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber())
        );
        mapToLoans(loansDto, loan);
        loansRepository.save(loan);
        return true;
    }

    /**
     * Delete the loan details based on the mobile number
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );
        loansRepository.delete(loan);
        return true;
    }
}
