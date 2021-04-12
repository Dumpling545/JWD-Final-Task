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

public class ChangeHotel extends AuthorizedUserCommand {
    private static final String ID_KEY = "id";
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";
    private static final String HOTEL_NAME_ATTRIBUTE_KEY = "hotelName";
    private static final String BANK_ACCOUNT_ATTRIBUTE_KEY = "bankAccount";
    private static final String CACHED_ADDRESS_ATTRIBUTE_KEY = "cachedAddress";
    private static final String LATITUDE_ADDRESS_ATTRIBUTE_KEY = "latitude";
    private static final String LONGTITUDE_ADDRESS_ATTRIBUTE_KEY = "longtitude";

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
        int hotelId = Integer.parseInt(request.getParameter(ID_KEY));
        HotelForm form = new HotelForm();
        form.setId(hotelId);
        form.setName(request.getParameter(HOTEL_NAME_ATTRIBUTE_KEY));
        form.setBankAccount(request.getParameter(BANK_ACCOUNT_ATTRIBUTE_KEY));
        form.setCachedAddress(
                request.getParameter(CACHED_ADDRESS_ATTRIBUTE_KEY));
        double lat = Double.parseDouble(
                request.getParameter(LATITUDE_ADDRESS_ATTRIBUTE_KEY));
        form.setLatitudeAddress(lat);
        double lon = Double.parseDouble(
                request.getParameter(LONGTITUDE_ADDRESS_ATTRIBUTE_KEY));
        form.setLongtitudeAddress(lon);
        hotelService.change(userId, form,
                request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.GOTOHOTELMANAGEMENTPAGE));
    }
}
