package by.tc.task05.service.validator;

import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface ImageValidator {
    void validateImage(Part imageFile) throws ServiceException;
}
