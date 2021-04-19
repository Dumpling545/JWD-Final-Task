package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.RoomFeature;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ChangeRoomFeature extends AuthorizedUserCommand {
    private static final String ID_KEY = "id";
    private static final String AIRCONDITIONING_ATTRIBUTE_KEY =
            "airconditioning";
    private static final String HEATING_ATTRIBUTE_KEY = "heating";
    private static final String BALCONY_ATTRIBUTE_KEY = "balcony";
    private static final String TV_ATTRIBUTE_KEY = "tv";
    private static final String REFRIGERATOR_ATTRIBUTE_KEY = "refrigerator";
    private static final String KITCHEN_ATTRIBUTE_KEY = "kitchen";
    private static final String HAIRDRYER_ATTRIBUTE_KEY = "hairDryer";
    private static final String TOILET_ATTRIBUTE_KEY = "toilet";
    private static final String SHOWER_ATTRIBUTE_KEY = "shower";
    private static final String WASHING_MACHINE_ATTRIBUTE_KEY =
            "washingMachine";
    private static final String DESCRIPTION_ATTRIBUTE_KEY = "description";
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
        RoomService roomService = provider.getRoomService();
        RoomFeature feature = new RoomFeature();
        feature.setRoomId(Integer.parseInt(request.getParameter(ID_KEY)));
        feature.setHasAirconditioning(
                request.getParameter(AIRCONDITIONING_ATTRIBUTE_KEY) != null);
        feature.setHasHeating(
                request.getParameter(HEATING_ATTRIBUTE_KEY) != null);
        feature.setHasBalcony(
                request.getParameter(BALCONY_ATTRIBUTE_KEY) != null);
        feature.setHasTV(request.getParameter(TV_ATTRIBUTE_KEY) != null);
        feature.setHasRefrigerator(
                request.getParameter(REFRIGERATOR_ATTRIBUTE_KEY) != null);
        feature.setHasKitchen(
                request.getParameter(KITCHEN_ATTRIBUTE_KEY) != null);
        feature.setHasHairDryer(
                request.getParameter(HAIRDRYER_ATTRIBUTE_KEY) != null);
        feature.setHasToilet(
                request.getParameter(TOILET_ATTRIBUTE_KEY) != null);
        feature.setHasShower(
                request.getParameter(SHOWER_ATTRIBUTE_KEY) != null);
        feature.setHasWashingMachine(
                request.getParameter(WASHING_MACHINE_ATTRIBUTE_KEY) != null);
        feature.setDescription(request.getParameter(DESCRIPTION_ATTRIBUTE_KEY));
        roomService.changeFeature(userId, feature,
                request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.GOTOHOTELMANAGEMENTPAGE));
    }
}
