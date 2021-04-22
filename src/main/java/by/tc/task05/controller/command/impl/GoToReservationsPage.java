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
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GoToReservationsPage extends AuthorizedUserCommand {
	private static final String RESERVATIONS_ATTRIBUTE_KEY = "reservations";
	private static final String LAST_ATTRIBUTE_KEY = "last";
	private static final String RESERVATIONS_JSP_LOCATION =
			"/WEB-INF/jsp/reservations.jsp";
	private static final String PAGE_PARAMETER_KEY = "page";
	private static final String RESULTS_PER_PAGE_PARAMETER_KEY =
			"resultsperpage";
	private static final String ARCHIVED_PARAMETER_KEY = "archived";

	@Override
	public CommandName getExceptionRedirectCommand() {
		return CommandName.ACCOUNT;
	}

	@Override
	public void executeAuthorized(int userId, HttpServletRequest request,
	                              HttpServletResponse response)
			throws ServiceException, ServletException, IOException
	{
		PageInformation pageInfo = new PageInformation();
		String resultsPerPage =
				request.getParameter(RESULTS_PER_PAGE_PARAMETER_KEY);
		if (resultsPerPage != null && !resultsPerPage.isBlank()) {
			pageInfo.setPageSize(Integer.parseInt(resultsPerPage));
		}
		String page = request.getParameter(PAGE_PARAMETER_KEY);
		if (page != null && !page.isBlank()) {
			pageInfo.setPage(Integer.parseInt(page));
		}
		boolean archived =
				request.getParameter(ARCHIVED_PARAMETER_KEY) != null &&
						request.getParameter(ARCHIVED_PARAMETER_KEY)
								.equals("true");
		ListPart<ExtendedReservation> reservations =
				getReservations(userId, pageInfo, archived, request);
		request.setAttribute(RESERVATIONS_ATTRIBUTE_KEY,
				reservations.getSubList());
		request.setAttribute(LAST_ATTRIBUTE_KEY, reservations.isLast());
		RequestDispatcher requestDispatcher =
				request.getRequestDispatcher(RESERVATIONS_JSP_LOCATION);
		requestDispatcher.forward(request, response);
	}

	protected abstract ListPart<ExtendedReservation> getReservations(int userId,
	                                                                 PageInformation pageInfo,
	                                                                 boolean archived,
	                                                                 HttpServletRequest request)
			throws ServiceException;
}
