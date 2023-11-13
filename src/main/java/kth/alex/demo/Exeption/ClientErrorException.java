package kth.alex.demo.Exeption;

public class ClientErrorException extends Exception{
    public ClientErrorException(String msg){
        super(msg);
    }
}
