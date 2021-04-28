package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.HotelForm;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddHotel extends AuthorizedUserCommand {
    private static final String HOTEL_NAME_ATTRIBUTE_KEY = "hotelName";
    private static final String BANK_ACCOUNT_ATTRIBUTE_KEY = "bankAccount";
    private static final String CACHED_ADDRESS_ATTRIBUTE_KEY = "cachedAddress";
    private static final String LATITUDE_ADDRESS_ATTRIBUTE_KEY = "latitude";
    private static final String LONGITUDE_ADDRESS_ATTRIBUTE_KEY = "longitude";

    @Override
    public CommandName getExceptionRedirectCommand() {
        return CommandName.GOTOADDHOTELPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        HotelService hotelService = provider.getHotelService();
        HotelForm form = new HotelForm();
        form.setName(request.getParameter(HOTEL_NAME_ATTRIBUTE_KEY));
        form.setBankAccount(request.getParameter(BANK_ACCOUNT_ATTRIBUTE_KEY));
        form.setCachedAddress(
                request.getParameter(CACHED_ADDRESS_ATTRIBUTE_KEY));
        double lat = Double.parseDouble(
                request.getParameter(LATITUDE_ADDRESS_ATTRIBUTE_KEY));
        form.setLatitudeAddress(lat);
        double lon = Double.parseDouble(
                request.getParameter(LONGITUDE_ADDRESS_ATTRIBUTE_KEY));
        form.setLongitudeAddress(lon);
        hotelService.addWithAdministrator(userId, form);
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.GOTOHOTELMANAGEMENTPAGE));
    }
}
