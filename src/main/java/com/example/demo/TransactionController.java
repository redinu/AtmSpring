package com.example.demo;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@RequestMapping(path="/")
	public String access(Model model){
		
		model.addAttribute(new Transaction());
		return "home";
	}
	
	@RequestMapping(path="/withdrawal")
	public String withdrawal(Model model){
		
		model.addAttribute(new Transaction());
		return "withdrawal";
	}
	@RequestMapping(path="/withdraw", method=RequestMethod.POST)
	public String withdraw(@Valid Transaction t ,  BindingResult bindingResult, Long acctNo, Double amount, Model model){
		
		if(bindingResult.hasErrors()){
			return "withdrawal";
		}
			Transaction tnew = new Transaction();
			tnew.setAcctNo(acctNo);
			tnew.setWithdrawal(true);
			tnew.setAction("withdrawal");
			tnew.setAmount(0-amount);
			tnew.setReason("Emergency");
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			tnew.setDate(date);
			model.addAttribute("transaction", tnew);
			transactionRepository.save(tnew);
			
		    return "balance";
	}
	
	@RequestMapping(path="/deposit")
	public String deposit(Model model){
		
		model.addAttribute(new Transaction());
		return "deposit";
	}
	@RequestMapping(path="/deposit", method=RequestMethod.POST)
	public String deposit(@Valid Transaction t ,  BindingResult bindingResult,Long acctNo, Double amount, Model model){
	
			if(bindingResult.hasErrors()){
				return "deposit";
			}
			Transaction tnew = new Transaction();
			tnew.setAcctNo(acctNo);
			tnew.setDeposit(true);
			tnew.setAction("deposit");
			tnew.setAmount(amount);
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			tnew.setDate(date);
			model.addAttribute("transaction", tnew);
			transactionRepository.save(tnew);
			
			return "balance";
		
	}
	
	@RequestMapping(path="/accountForm")
	public String accountForm(Model model){
		
		model.addAttribute(new Transaction());
		return "accountForm";
	}
	
	@RequestMapping(path="/balance", method=RequestMethod.POST)
	public String balance(Model model,Long acctNo){
		
		List<Transaction> t = getTransWithAccNo(acctNo);
		if(t == null || t.size() == 0){
			Transaction trans = new Transaction();
			trans.setAcctNo(acctNo);
			t.add(trans);
			model.addAttribute("transactions", t);
			return "transHistory";
			
		}else{
			
			model.addAttribute("transactions", t);
			return "transHistory";
		}	
		
	}
	
	public List<Transaction> getTransWithAccNo(long acctNo){
		
		List<Transaction> t = transactionRepository.findByAcctNo(acctNo);
		
		return t;
		
	}
	
}
