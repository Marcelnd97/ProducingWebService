package com.exam.examInLine.helper;

public class UserFoundException extends  Exception{


    public UserFoundException(){
        super("Cet utilisateur est deja dans la base de donnée !! Vous pouvez essayer unautre !!");
    }

    public  UserFoundException(String msg){ super (msg); }
}
