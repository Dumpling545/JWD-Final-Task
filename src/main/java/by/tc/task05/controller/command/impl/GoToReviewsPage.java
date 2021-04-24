package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.Command;
import by.tc.task05.controller.command.CommandName;
import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.ExtendedReview;
import by.tc.task05.entity.ExtendedRoom;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.ReviewService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToReviewsPage implements Command {
	private static final String REVIEWS_ATTRIBUTE_KEY = "reviews";
	private static final String REVIEWS_JSP_LOCATION =
			"/WEB-INF/jsp/reviews.jsp";
	private static final String ROOM_ID_KEY = "roomid";
	private static final String ROOMS_PER_PAGE_PARAMETER_KEY = "roomsperpage";
	private static final String PAGE_PARAMETER_KEY = "page";
	private static final String ERROR_MESSAGE_ATTRIBUTE_KEY = "errorMessage";

	@Override
	public void execute(HttpServletRequest request,
	                    HttpServletResponse response)
			throws ServletException, IOException
	{
		ServiceProvider provider = ServiceProvider.getInstance();
		ReviewService reviewService = provider.getReviewService();
		RoomSearchServiceFilter filter = new RoomSearchServiceFilter();
		PageInformation pageInformation = new PageInformation();
		String roomsPerPage =
				request.getParameter(ROOMS_PER_PAGE_PARAMETER_KEY);
		if (roomsPerPage != null && !roomsPerPage.isBlank()) {
			pageInformation.setPageSize(Integer.parseInt(roomsPerPage));
		}
		String page = request.getParameter(PAGE_PARAMETER_KEY);
		if (page != null && !page.isBlank()) {
			pageInformation.setPage(Integer.parseInt(page));
		}
		String room = request.getParameter(ROOM_ID_KEY);
		int roomId = -1;
		if (room != null && !room.isBlank()) {
			roomId = Integer.parseInt(room);
		}
		try {
			ListPart<ExtendedReview> reviews =
					reviewService.getByRoom(roomId, pageInformation);
			request.setAttribute(REVIEWS_ATTRIBUTE_KEY, reviews);
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher(REVIEWS_JSP_LOCATION);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(UrlHelper
					.buildUrl(CommandName.GOTOSTARTERPAGE,
							ExceptionMessageMapper.getKey(this, e)));
		}
	}
}
