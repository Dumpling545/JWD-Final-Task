package by.tc.task05.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import by.tc.task05.controller.command.Command;
import by.tc.task05.entity.RoomShortView;
import by.tc.task05.entity.filter.RoomSearchServiceFilter;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToSearchPage implements Command {
	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		RoomService roomService = provider.getRoomService();
		RoomSearchServiceFilter filter = extractFilter(request);
		try {
			List<RoomShortView> rooms = roomService.getRoomListPage(filter);
			request.setAttribute("rooms", rooms);
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(
                    "Controller?command=gotostarterpage&message=serverError");
		}

	}
	private RoomSearchServiceFilter extractFilter(HttpServletRequest request){
		RoomSearchServiceFilter filter = new RoomSearchServiceFilter();
		filter.setCheckInDate(LocalDate.parse((String)request.getAttribute("checkin")));
		filter.setCheckOutDate(LocalDate.parse((String)request.getAttribute("checkout")));
		filter.setCostLowBound(Double.parseDouble((String) request.getAttribute("fromprice")));
		filter.setCostHighBound(Double.parseDouble((String) request.getAttribute("toprice")));
		filter.setLocation((String) request.getAttribute("location"));
		filter.setNumberOfBeds(Integer.parseInt((String) request.getAttribute("numberofbeds")));
		filter.setRatingLowBound(Double.parseDouble((String) request.getAttribute("fromrating")));
		filter.setRatingHighBound(Double.parseDouble((String) request.getAttribute("torating")));
		filter.setPageSize(Integer.parseInt((String) request.getAttribute("roomsperpage")));
		filter.setPage(Integer.parseInt((String) request.getAttribute("page")));
		return filter;
	}
}
