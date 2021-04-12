package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class SetHotelIcon extends AuthorizedUserCommand {
    private static final String HOTEL_ICON_KEY = "icon";
    private static final String PASSWORD_KEY = "password";
    private static final String ID_KEY = "id";

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
        Part part = request.getPart(HOTEL_ICON_KEY);
        int hotelId = Integer.parseInt(request.getParameter(ID_KEY));
        String password = request.getParameter(PASSWORD_KEY);
        hotelService.setIcon(userId, hotelId, part, password);
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.GOTOHOTELMANAGEMENTPAGE));
    }
}
