package by.tc.task05.service.exception;

public class LocationAPIException extends ServiceException {
    private static final long serialVersionUID = 5959587846366677705L;
    public LocationAPIException(Exception e){
        super("Problems encountered while accessing Location API", e);
    }
    
}
