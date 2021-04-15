package by.tc.task05.service.exception;

public class OccupiedDateRangeException extends ServiceException {
    private static final long serialVersionUID = 7911079530639392216L;
    public OccupiedDateRangeException(Exception e){
        super("Date range is already occupied", e);
    }
}
