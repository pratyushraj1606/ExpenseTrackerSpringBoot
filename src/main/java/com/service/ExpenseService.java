package com.service;

import java.util.List;

import com.bean.Expense;
// all the methods to be used in the implementation class.
public interface ExpenseService {
	public Expense addExpense(Expense expense);
	public List<Expense> getExpenses(String name);
	public void deleteExpense(Long expId);
	public Expense getExpense(Long expId);
	

}
