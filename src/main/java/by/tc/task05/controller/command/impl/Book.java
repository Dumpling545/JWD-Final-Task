package by.tc.task05.controller.command.impl;

import by.tc.task05.entity.Reservation;
import by.tc.task05.service.ReservationService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Book extends AuthorizedUserCommand {

    private static final String ROOM_ID_PARAMETER_KEY = "roomId";
    private static final String CHECK_IN_PARAMETER_KEY = "checkin";
    private static final String CHECK_OUT_PARAMETER_KEY = "checkout";

    @Override
    public void redirectOnException(HttpServletRequest request,
                                    HttpServletResponse response,
                                    ServiceException e) throws IOException {

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
        /* temporary code */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.append("Booked sussessfully!");
        out.close();
    }
}
