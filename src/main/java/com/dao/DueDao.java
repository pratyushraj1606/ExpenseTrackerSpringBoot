package com.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bean.Due;
import com.bean.Expense;


	public interface DueDao extends JpaRepository<Due,Long>{
		Set<Due> findByExpense(Expense expense);

		Set<Due> findByExpense(Optional<Expense> expense);

	}

