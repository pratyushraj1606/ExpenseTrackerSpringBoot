package com.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.bean.Due;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="expense")
public class Expense {
	
	// expense id is the primary key and the values for that will be auto generated.
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long expId;
	private String name;
	private String reason;
	private String category;
	private long dateOfExpense;
	private String paymentMethod;
	private long amount;
	
	
	//orphan removal is used here to clean the dependent object.
	@OneToMany(mappedBy="expense",fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonIgnore
	private Set<Due> due=new HashSet<>();
	public Set<Due> getDue() {
		return due;
	}
	public void setDue(Set<Due> due) {
		this.due = due;
	}
	public long getExpId() {
		return expId;
	}
	public void setExp_id(long expId) {
		this.expId = expId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Expense(long expId, String name, String reason, String category, long dateOfExpense, String paymentMethod,
			long amount) {
		super();
		this.expId = expId;
		this.name = name;
		this.reason = reason;
		this.category = category;
		this.dateOfExpense = dateOfExpense;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Expense [expId=" + expId + ", name=" + name + ", reason=" + reason + ", category=" + category
				+ ", dateOfExpense=" + dateOfExpense + ", paymentMethod=" + paymentMethod + ", amount=" + amount + "]";
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getDateOfExpense() {
		return dateOfExpense;
	}
	public void setDateOfExpense(long dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	

}
