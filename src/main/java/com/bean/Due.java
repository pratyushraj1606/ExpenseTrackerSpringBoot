package com.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bean.Expense;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="due")
public class Due {
	//due id will be auto generated and it's the primary key.
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long dueId;
	private String name;
	private int oweAmount;
	private long repaymentDate;
	private int status;
	@ManyToOne(fetch= FetchType.EAGER)
	private Expense expense;
	// one to many relationship has been established as the expense id is the foreign key
	//for the due table.
	@OneToMany(mappedBy="due",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonIgnore
	
	public long getDueId() {
		return dueId;
	}
	public void setDueId(long dueId) {
		this.dueId = dueId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOweAmount() {
		return oweAmount;
	}
	public void setOweAmount(int oweAmount) {
		this.oweAmount = oweAmount;
	}
	public long getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(long repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	
	public Due(long dueId, String name, int oweAmount, long repaymentDate, int status, Expense expense) {
		super();
		this.dueId = dueId;
		this.name = name;
		this.oweAmount = oweAmount;
		this.repaymentDate = repaymentDate;
		this.status = status;
		this.expense = expense;
	}
	public Due() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Due [dueId=" + dueId + ", name=" + name + ", oweAmount=" + oweAmount + ", repaymentDate="
				+ repaymentDate + ", status=" + status + ", expense=" + expense + "]";
	}
	
	
	
	

}
