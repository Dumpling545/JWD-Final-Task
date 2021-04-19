package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.Reservation;
import by.tc.task05.service.ReservationService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ArchiveReservation extends AuthorizedUserCommand {
    private static final String ID_KEY = "id";
    private static final String ARCHIVATION_REASON_KEY = "id2";
    private static final String PASSWORD_ATTRIBUTE_KEY = "password";

    @Override
    public void onException(HttpServletRequest request,
                            HttpServletResponse response, ServiceException e)
            throws IOException {
        UrlHelper.sendRedirectToLastUrlWithMessage(request, response,
                ExceptionMessageMapper.getKey(this, e));
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        ReservationService reservationService =
                provider.getReservationService();
        int reservationId = Integer.parseInt(request.getParameter(ID_KEY));
        int archivationReason =
                Integer.parseInt(request.getParameter(ARCHIVATION_REASON_KEY));
        reservationService
                .archiveReservation(userId, reservationId, archivationReason,
                        request.getParameter(PASSWORD_ATTRIBUTE_KEY));
        UrlHelper.sendRedirectToReturnUrl(request, response,
                UrlHelper.buildUrl(CommandName.GOTOUSERRESERVATIONSPAGE));
    }
}
