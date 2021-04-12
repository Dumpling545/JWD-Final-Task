package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.HotelForm;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddAdmin extends AuthorizedUserCommand {
    private static final String ID_KEY = "id";
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";
    private static final String EMAIL_ATTRIBUTE_KEY = "email";

    @Override
    public void redirectOnException(HttpServletRequest request,
                                    HttpServletResponse response,
                                    ServiceException e) throws IOException {
        UrlHelper.sendRedirectToLastUrlWithMessage(request, response,
                ExceptionMessageMapper.getKey(this, e));
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        HotelService hotelService = provider.getHotelService();
        String email = request.getParameter(EMAIL_ATTRIBUTE_KEY);
        int hotelId = Integer.parseInt(request.getParameter(ID_KEY));
        hotelService.addAdministratorByEmail(userId, email, hotelId,
                request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.GOTOADMINSPAGE));
    }
}
