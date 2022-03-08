package com.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Due;
import com.bean.Expense;
import com.dao.DueDao;
import com.dao.ExpenseDao;
import com.service.ExpenseService;

@Service
public class ExpenseServiceImplementation implements ExpenseService{
	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private  DueDao dueDao;
	@Override
	// method to add all the expense values in the table and check is there to ensure
	// that the expense amount is not 0.
	public Expense addExpense(Expense expense) {
		try {
			if(expense.getAmount() > 0) {
				Expense e = this.expenseDao.save(expense);
				return e;
			}
		}catch(Exception e) {
			return null;
		}
		return null;
		 
	
	}
	// method to get all the expense details of someone from their name.
	@Override
	public List<Expense> getExpenses(String name) {
		List <Expense> e  = this.expenseDao.findByName(name);
		if(e.size()>0) {
			return e;
		}
		return null; 
		 
	}
	// method is there to delete all the expense and due table data for the given
	// expense id.
	@Override
	public void deleteExpense(Long expId) {
		Expense expense = new Expense();
		Set<Due> due = expense.getDue();
		System.out.println(expense.getDue());
		for (Due d : due) {
			dueDao.delete(d);
		}
		expense.setExp_id(expId);
		this.expenseDao.delete(expense);
	}
	// method is there to get all the expense data for a particular expense id.
	@Override
	public Expense getExpense(Long expId) {
		try {
		return this.expenseDao.findById(expId).get();
		}catch(Exception e) {
			return null;
		}
	}
	
	
	
	
	


}

