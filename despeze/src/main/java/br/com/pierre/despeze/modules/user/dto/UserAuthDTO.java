package br.com.pierre.despeze.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthDTO {

    private String email;
    private String password;
}