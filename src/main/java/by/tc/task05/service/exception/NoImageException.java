package by.tc.task05.service.exception;

public class NoImageException extends ServiceException {
    private static final long serialVersionUID = 302187880981187542L;
    public  NoImageException(){
        super("No image in request");
    }
}
