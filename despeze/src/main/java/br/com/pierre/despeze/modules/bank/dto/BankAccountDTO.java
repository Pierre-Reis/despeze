package br.com.pierre.despeze.modules.bank.dto;

import java.math.BigDecimal;

public record BankAccountDTO(String name, BigDecimal creditLimit, Integer dueDay) {
}