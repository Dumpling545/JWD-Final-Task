package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.ExtendedReservation;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.service.ReservationService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRoomReservationsPage extends GoToReservationsPage {
	private static final String ROOM_ID_KEY = "roomid";

	@Override
	public CommandName getExceptionRedirectCommand() {
		return CommandName.GOTOHOTELMANAGEMENTPAGE;
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
		int roomId =
				Integer.parseInt(request.getParameter(ROOM_ID_KEY));
		return reservationService.getByRoom(userId, roomId, pageInfo, archived);
	}
}
