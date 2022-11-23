package exceptions;

public class SqlException extends RuntimeException{
    public SqlException(String msg){
        super(msg);
    }

}
