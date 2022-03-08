package com.service;

import java.util.Set;

import com.bean.Due;
import com.bean.Expense;
// all the methods to be used in the implementation class.
public interface DueService {
	public Due addDue(Due due);
	public Set<Due> getDueOfExpenses(Expense expense);
	public Set<Due> getDue();
	public Due clearDueByExpenseId(Long expId);

}
