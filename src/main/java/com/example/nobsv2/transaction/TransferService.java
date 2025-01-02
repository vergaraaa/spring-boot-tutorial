package com.example.nobsv2.transaction;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Command;

import jakarta.transaction.Transactional;

@Service
public class TransferService implements Command<TransferDto, String> {

    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<String> execute(TransferDto transfer) {
        Optional<BankAccount> fromAccountOptional = bankAccountRepository.findById(transfer.getFromUser());
        Optional<BankAccount> toAccountOptional = bankAccountRepository.findById(transfer.getToUser());

        if(fromAccountOptional.isEmpty() || toAccountOptional.isEmpty()){
            throw new RuntimeException("User not found"); // this can be a Custom Exception
        }

        BankAccount fromAccount = fromAccountOptional.get();
        BankAccount toAccount = toAccountOptional.get();

        add(toAccount, transfer.getAmount());
        deduct(fromAccount, transfer.getAmount());

        return ResponseEntity.ok("Transfer successful");
    }

    private void deduct(BankAccount bankAccount, double amount){
        if(bankAccount.getBalance() < amount){
            throw new RuntimeException("Not enough money"); // this must be a runtime exception or extend it
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }
    
    private void add(BankAccount bankAccount, double amount){
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
    
}