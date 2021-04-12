package by.tc.task05.service.validator.impl;

import by.tc.task05.entity.PageInformation;
import by.tc.task05.service.exception.InvalidPageException;
import by.tc.task05.service.exception.InvalidPageSizeException;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.service.validator.PageValidator;

public class PageValidatorImpl implements PageValidator {
    private final static int DEFAULT_PAGE = 1;
    private final static int DEFAULT_PAGE_SIZE = 10;
    @Override
    public void validatePage(PageInformation pageInfo) throws ServiceException {
        if (!pageInfo.isPageInitialized()) {
            pageInfo.setPage(DEFAULT_PAGE);
        } else if (pageInfo.getPage() < 1) {
            throw new InvalidPageException();
        }
        if (!pageInfo.isPageSizeInitialized()) {
            pageInfo.setPageSize(DEFAULT_PAGE_SIZE);
        } else if (pageInfo.getPageSize() < 1) {
            throw new InvalidPageSizeException();
        }
    }
}
