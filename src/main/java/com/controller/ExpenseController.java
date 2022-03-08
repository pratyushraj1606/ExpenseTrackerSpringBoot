package com.controller;

import java.util.List;
import com.responseHandler.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Expense;
import com.service.ExpenseService;

@RestController
@RequestMapping("/expense")
@CrossOrigin("*")
public class ExpenseController {
	@Autowired
	private  ExpenseService expenseService;
	@PostMapping("/")
	public ResponseEntity<?> addExpense(@RequestBody Expense expense){
		try {
			Expense expnse = this.expenseService.addExpense(expense);
			if(expnse != null) {
				return ResponseHandler.generateResponse("Expense Created", HttpStatus.OK, expnse);
			}else {
				return ResponseHandler.generateResponse("Please enter valid amount", HttpStatus.MULTI_STATUS, null);

			}

		}catch(Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
	
		}	
	}
	@GetMapping("/{name}")
	public ResponseEntity<?> getExpenses(@PathVariable("name")String name) {
		try {
			List<Expense> e = expenseService.getExpenses(name);
			if(e != null) {
				return ResponseHandler.generateResponse("Success", HttpStatus.OK, e);
			}else {
				return ResponseHandler.generateResponse("user not found!", HttpStatus.MULTI_STATUS, null);
			}
		}catch(Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
		}
		
		
		
	}
	@DeleteMapping("/{expId}")
	public ResponseEntity<?> deleteExpense(@PathVariable("expId")Long expId) {
		try {
			 this.expenseService.deleteExpense(expId);
			 return ResponseHandler.generateResponse("Success", HttpStatus.OK, null);
		}catch(Exception e) {
			return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
		}	
	}
	
	
	

}

