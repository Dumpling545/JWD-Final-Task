package by.tc.task05.service.exception;

public class NoSuchRoomException extends ServiceException {
    private static final long serialVersionUID = -6540864368660113696L;
    public NoSuchRoomException(){
        super("Room does not exist");
    }
}
