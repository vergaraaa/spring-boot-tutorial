package com.example.nobsv2.transaction;

import lombok.Data;

@Data
public class TransferDto {
    private String fromUser;
    private String toUser;
    private double amount;
}
