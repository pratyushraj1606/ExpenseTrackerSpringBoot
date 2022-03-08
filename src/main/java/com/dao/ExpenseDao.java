package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bean.Expense;

public interface ExpenseDao extends JpaRepository <Expense,Long> {
	// to get all the expense data from the user name.
	public List<Expense> findByName(String name);
	}
