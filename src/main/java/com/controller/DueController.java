package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.responseHandler.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Due;
import com.bean.Expense;
import com.service.DueService;
import com.service.ExpenseService;

@RestController
@RequestMapping("/due")
@CrossOrigin("*")
public class DueController {
	@Autowired
	private  DueService dueService;
	@Autowired
	private ExpenseService expenseService;
	@PostMapping("/")
	public ResponseEntity<?> addDue(@RequestBody Due due){
		try {
			Due due1=this.dueService.addDue(due);
			if(due1 != null) {
			return ResponseHandler.generateResponse("Success", HttpStatus.OK, due1);
			}else {
				return ResponseHandler.generateResponse("Amount is higher than the total expense", HttpStatus.MULTI_STATUS, null);
			}
		}catch(Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
		}
	}
	
	
	@GetMapping("/expense/{expId}")
	public ResponseEntity<?> getDueOfExpenses(@PathVariable("expId") Long expId){
		try {
			Expense expense=this.expenseService.getExpense(expId);
			if(expense != null) {
				Set<Due> due=expense.getDue();
				List list =new ArrayList(due);
				return ResponseHandler.generateResponse("Success", HttpStatus.OK, list);
			}else {
				return ResponseHandler.generateResponse("Expense not found!", HttpStatus.MULTI_STATUS, null);
			}
		}catch(Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
		}
	}
	
	@GetMapping("/expense/settleByAmount/{expId}")
	public ResponseEntity<?> clearDueByExpenseId(@PathVariable("expId") Long expId){
		try {
			Due isClear=this.dueService.clearDueByExpenseId(expId);
			if(isClear != null) {
				if(isClear.getStatus() == 2) {
					return ResponseHandler.generateResponse("All Dues are clear", HttpStatus.OK, null);

				}else {
					return ResponseHandler.generateResponse("Due Clear", HttpStatus.OK, isClear);

				}
			}else {
				return ResponseHandler.generateResponse("No Due found!", HttpStatus.MULTI_STATUS, null);
			}
		}catch(Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);

			
		}
		
	}
}


