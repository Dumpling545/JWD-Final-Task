package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.ExtendedReservation;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.ReservationService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToUserReservationsPage extends GoToReservationsPage {
	@Override
	public CommandName getExceptionRedirectCommand() {
		return CommandName.ACCOUNT;
	}

	@Override
	protected ListPart<ExtendedReservation> getReservations(int userId,
	                                                        PageInformation pageInfo,
	                                                        boolean archived,
	                                                        HttpServletRequest request)
			throws ServiceException
	{
		ServiceProvider provider = ServiceProvider.getInstance();
		ReservationService reservationService =
				provider.getReservationService();
		return reservationService.getByUser(userId, pageInfo, archived);
	}

}
