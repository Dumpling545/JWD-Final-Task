package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.Room;
import by.tc.task05.entity.RoomFeature;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class GoToChangeRoomFeaturePage extends AuthorizedUserCommand {
    private static final String CHANGE_FEATURE_JSP_LOCATION =
            "/WEB-INF/jsp/addChangeFeature.jsp";
    private static final String ID_KEY = "id";
    private static final String FEATURE_ATTRIBUTE_KEY = "feature";
    private static final String PASS_CONFIRM_KEY = "passwordConfirmation";
    private static final boolean PASS_CONFIRM_VALUE = true;
    private static final String FEATURE_NOT_FOUND = "featureNotFound";

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        RoomService roomService = provider.getRoomService();
        Optional<RoomFeature> feature = roomService.getRoomFeatureById(
                Integer.parseInt(request.getParameter(ID_KEY)));
        if(feature.isPresent()){
            request.setAttribute(FEATURE_ATTRIBUTE_KEY, feature.get());
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher(CHANGE_FEATURE_JSP_LOCATION);
            request.setAttribute(PASS_CONFIRM_KEY, PASS_CONFIRM_VALUE);
            requestDispatcher.forward(request, response);
        } else {
            UrlHelper.sendRedirectToLastUrlWithMessage(request, response,
                    FEATURE_NOT_FOUND);
        }
    }
}
