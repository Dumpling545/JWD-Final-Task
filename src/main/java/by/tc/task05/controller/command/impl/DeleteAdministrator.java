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

import java.io.IOException;

public class DeleteAdministrator extends AuthorizedUserCommand {
    private static final String ADMIN_ID_KEY = "id";
    private static final String HOTEL_ID_KEY = "id2";
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";

    @Override
    public void onException(HttpServletRequest request,
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
        int adminId = Integer.parseInt(request.getParameter(ADMIN_ID_KEY));
        int hotelId = Integer.parseInt(request.getParameter(HOTEL_ID_KEY));
        hotelService.removeAdministrator(userId, adminId, hotelId,
                request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        if(userId == adminId){
            response.sendRedirect(UrlHelper.buildUrl(CommandName.GOTOHOTELMANAGEMENTPAGE));
        } else {
            UrlHelper.sendRedirectToReturnUrl(request, response,
                    UrlHelper.buildUrl(CommandName.GOTOHOTELMANAGEMENTPAGE));
        }

    }
}
