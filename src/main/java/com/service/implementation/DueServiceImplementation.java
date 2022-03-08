package com.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Due;
import com.bean.Expense;
import com.dao.DueDao;
import com.dao.ExpenseDao;
import com.service.DueService;

@Service
public class DueServiceImplementation implements DueService{
	@Autowired
	private DueDao dueDao;
	@Autowired
	private ExpenseDao expenseDao;
	@Override
	// method used to add Due values to the table, check is there to ensure that the
	// amount due is not more than the expense that has happened.
	public Due addDue(Due due) {
		try {
		Optional<Expense> expense = this.expenseDao.findById(due.getExpense().getExpId());
		long amount  = due.getOweAmount();
		System.out.println("Amount :::"+amount);
		Set<Due> d = this.dueDao.findByExpense(expense);
		long totalAmountPaid = 0;
		long amountTobePaid = expense.get().getAmount();
		System.out.println("amountTobePaid :::"+amountTobePaid);
		for(Due tempDue: d) {
			totalAmountPaid += tempDue.getOweAmount();
		}
		System.out.println("totalAmountPaid :::"+totalAmountPaid);
		if((totalAmountPaid+amount ) <= amountTobePaid) {
			return this.dueDao.save(due);
//		return d.stream().findFirst().get();
		}
		return null;}
		catch(Exception e) {
			throw e;
//			return null;
		}
	}
	// method by which we will be getting the number of due that is there for the expense
	// that has happened.
	@Override
	public Set<Due> getDueOfExpenses(Expense expense){
		return this.dueDao.findByExpense(expense);
	}
	// method to get all the due data present in the table.
	@Override
	public Set<Due> getDue(){
		return new HashSet<Due>(this.dueDao.findAll());
	}
	@Override
	// method is there to ensure that the due for an expense has been cleared or not,
	//due will be settled based on the value of the amount,the amount which has the 
	// highest value will be cleared first then the second highest value will be cleared
	//,it will go till all the due amounts are clear, status 1 is for
	// the due that is clear and status 0 is for pending due amount.
	public Due clearDueByExpenseId(Long expId) {
		try {
			Expense e = new Expense();
			e.setExp_id(expId);
			Set<Due> due=this.getDueOfExpenses(e);
			int dueCount = due.size();
			int count = 0;
		
			Due toBeClearDue = due.stream().findFirst().get();
			int high = 0;

			for(Due d:due) {
				if(d.getStatus() == 0) {
					count++;
				}
				if(d.getOweAmount() > high && d.getStatus() == 0) {
				   high = d.getOweAmount();
				   toBeClearDue = d;
				   count++;
				   
				}
			}

			toBeClearDue.setStatus(1);
			Due d = this.dueDao.saveAndFlush(toBeClearDue);
			if(d.getStatus() == 1 && count != 0) {
				return d;
			}else {
				d.setStatus(2);
				return d;
			}
		}catch(Exception e) {
			return null;
		}
		
				
		 
	}
}

