package com.tcs.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.loans.entity.Loans;
import java.util.List;
import java.util.Optional;


@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
	
	Optional<Loans> findByMobileNumber(String mobileNumber);
	
	Optional<Loans> findByLoanNumber(String loanNumber);

}
