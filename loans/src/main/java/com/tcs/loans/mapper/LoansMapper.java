package com.tcs.loans.mapper;

import com.tcs.loans.dto.LoansDto;
import com.tcs.loans.entity.Loans;

public class LoansMapper {
	
	public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
		
		loansDto.setLoanType(loans.getLoanType());
		loansDto.setMobileNumber(loans.getMobileNumber());
		loansDto.setLoanNumber(loans.getLoanNumber());
		loansDto.setTotalLoan(loans.getTotalLoan());
		loansDto.setAmountPaid(loans.getAmountPaid());
		loansDto.setOutstandingAmount(loans.getOutstandingAmount());
		
		return loansDto;
	}
	
	public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
		
		loans.setLoanType(loansDto.getLoanType());
		loans.setMobileNumber(loansDto.getMobileNumber());
		loans.setLoanNumber(loansDto.getLoanNumber());
		loans.setTotalLoan(loansDto.getTotalLoan());
		loans.setAmountPaid(loansDto.getAmountPaid());
		loans.setOutstandingAmount(loansDto.getOutstandingAmount());
		
		return loans;
	}

}
