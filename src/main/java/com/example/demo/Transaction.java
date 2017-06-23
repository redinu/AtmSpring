package com.example.demo;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@NotNull
    private Long acctNo;
	private String reason;
    private Double amount;
    @Transient
    private boolean withdrawal;
    @Transient
    private boolean deposit;
    @Transient
    private boolean checkBalance;
    private String action;
    private Timestamp date;
    
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public boolean isWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(boolean withdrawal) {
		this.withdrawal = withdrawal;
	}
	
	public boolean isDeposit() {
		return deposit;
	}
	public void setDeposit(boolean deposit) {
		this.deposit = deposit;
	}
	public boolean isCheckBalance() {
		return checkBalance;
	}
	public void setCheckBalance(boolean checkBalance) {
		this.checkBalance = checkBalance;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date2) {
		this.date = date2;
	}
	
    

}