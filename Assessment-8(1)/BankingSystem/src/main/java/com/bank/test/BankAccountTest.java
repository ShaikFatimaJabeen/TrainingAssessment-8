package com.bank.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bank.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    
    @BeforeEach
    void setUp() {
        account = new BankAccount(1000.00);  
    }

   
    @Test
    void testDeposit() {
        account.deposit(500.00);
        assertEquals(1500.00, account.getBalance(), "Balance should be 1500 after deposit");
    }

    
    @Test
    void testWithdraw() {
        account.withdraw(200.00);
        assertEquals(800.00, account.getBalance(), "Balance should be 800 after withdrawal");
    }

   
    @Test
    void testWithdrawInsufficientFunds() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(1200.00);  
        });
        assertEquals("Insufficient funds", thrown.getMessage());
    }

   
    @Test
    void testDepositNegativeAmount() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100.00);  
        });
        assertEquals("Deposit amount must be positive", thrown.getMessage());
    }

    
    @Test
    void testInitialBalance() {
        assertEquals(1000.00, account.getBalance(), "Initial balance should be 1000");
    }
}


