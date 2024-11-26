package com.tcs.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.tcs.loans.constants.LoansConstants;
import com.tcs.loans.dto.LoansDto;
import com.tcs.loans.entity.Loans;
import com.tcs.loans.exception.LoanAlreadyExistException;
import com.tcs.loans.exception.ResourceNotFoundException;
import com.tcs.loans.mapper.LoansMapper;
import com.tcs.loans.repository.LoansRepository;
import com.tcs.loans.service.ILoansService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService{
	
	private LoansRepository loansRepository;

	@Override
	public void createLoan(String mobileNumber) {
		// TODO Auto-generated method stub
		
		Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
		
		if(loan.isPresent()) {	
			throw new LoanAlreadyExistException("Loan with this mobile already exist");
		}
		
		 loansRepository.save(createNewLoans(mobileNumber));
	}
	
	
	public Loans createNewLoans(String mobileNumber) {
		
		Loans loans = new Loans();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		loans.setLoanNumber(Long.toString(randomCardNumber));
		loans.setMobileNumber(mobileNumber);
		loans.setLoanType(LoansConstants.HOME_LOAN);
		loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
		loans.setAmountPaid(0);
		loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
		
		return loans;
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		// TODO Auto-generated method stub
		
		Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
		
		return LoansMapper.mapToLoansDto(loans, new LoansDto());
		
	}

	@Override
	public boolean updateLoan(LoansDto loansDto) {
		// TODO Auto-generated method stub
		Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
				()-> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
		
		loansRepository.save(LoansMapper.mapToLoans(loansDto, loans));
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		// TODO Auto-generated method stub
		Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
		
		loansRepository.deleteById(loans.getLoanId());
		
		return true;
	}

}
