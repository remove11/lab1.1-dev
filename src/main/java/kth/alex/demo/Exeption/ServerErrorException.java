package kth.alex.demo.Exeption;

public class ServerErrorException extends Exception{
    public ServerErrorException(String msg){
        super(msg);
    }
}
