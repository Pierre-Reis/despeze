package br.com.pierre.despeze.modules.bank.controllers;

import br.com.pierre.despeze.modules.bank.dto.BankAccountDTO;
import br.com.pierre.despeze.modules.bank.entities.BankAccount;
import br.com.pierre.despeze.modules.bank.services.BankAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody BankAccountDTO bankAccountDTO, HttpServletRequest request){
        try {

            var idFromRequest = request.getAttribute("user_id").toString();

            var userId = UUID.fromString(idFromRequest);

            var bankAccount =
                BankAccount.builder()
                    .userId(userId)
                    .name(bankAccountDTO.name())
                    .creditLimit(bankAccountDTO.creditLimit())
                    .dueDay(bankAccountDTO.dueDay()).build();

            var create = this.bankAccountService.create(bankAccount);

            return ResponseEntity.ok().body(create);

        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}