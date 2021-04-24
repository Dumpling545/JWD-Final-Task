package by.tc.task05.controller.command.impl;

import by.tc.task05.entity.Reservation;
import by.tc.task05.service.ReservationService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.OccupiedDateRangeException;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Book extends AuthorizedUserCommand {

    private static final String ROOM_ID_PARAMETER_KEY = "roomId";
    private static final String CHECK_IN_PARAMETER_KEY = "checkin";
    private static final String CHECK_OUT_PARAMETER_KEY = "checkout";
    private static final String CHECK_IN_JSON_KEY = "checkin";
    private static final String CHECK_OUT_JSON_KEY = "checkout";
    private static final String PAYMENT_JSON_KEY = "payment";
    private static final String TOKEN_JSON_KEY = "token";
    private static final String RESPONSE_CONTENT_TYPE = "application/json";
    private static final String RESPONSE_ENCODING = "UTF-8";

    @Override
    public void onUnauthorizedUser(HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void onException(HttpServletRequest request,
                            HttpServletResponse response,
                            ServiceException e) throws IOException {
        if(e instanceof OccupiedDateRangeException){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter(ROOM_ID_PARAMETER_KEY));

        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        String checkIn = request.getParameter(CHECK_IN_PARAMETER_KEY);
        if (checkIn != null && !checkIn.isBlank()) {
            reservation.setCheckInDate(LocalDate.parse(checkIn));
        }
        String checkOut = request.getParameter(CHECK_OUT_PARAMETER_KEY);
        if (checkOut != null && !checkOut.isBlank()) {
            reservation.setCheckOutDate(LocalDate.parse(checkOut));
        }
        ServiceProvider provider = ServiceProvider.getInstance();
        ReservationService reservationService =
                provider.getReservationService();
        reservationService.add(reservation);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        PrintWriter out = response.getWriter();
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setCharacterEncoding(RESPONSE_ENCODING);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CHECK_IN_JSON_KEY, reservation.getCheckInDate().toString());
        jsonObject.put(CHECK_OUT_JSON_KEY, reservation.getCheckOutDate().toString());
        jsonObject.put(PAYMENT_JSON_KEY, reservation.getPaymentAmount());
        jsonObject.put(TOKEN_JSON_KEY, reservation.getPaymentToken());
        out.print(jsonObject.toString());
        out.flush();
    }
}
