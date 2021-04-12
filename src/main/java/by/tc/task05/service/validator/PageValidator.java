package by.tc.task05.service.validator;

import by.tc.task05.entity.PageInformation;
import by.tc.task05.service.exception.ServiceException;

public interface PageValidator {
    public void validatePage(PageInformation pageInfo) throws ServiceException;
}
