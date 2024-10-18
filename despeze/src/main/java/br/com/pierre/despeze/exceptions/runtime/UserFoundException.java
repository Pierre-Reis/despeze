package br.com.pierre.despeze.exceptions.runtime;

public class UserFoundException extends RuntimeException{

    public UserFoundException(){
        super("O usuário já está cadastrado");
    }
}