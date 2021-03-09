package by.tc.task05.controller.command.impl;

import java.io.IOException;
import java.util.List;
import by.tc.task05.controller.command.Command;
import by.tc.task05.entity.Room;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceException;
import by.tc.task05.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToIndexPage implements Command {
	private static final String PAGE = "p";
	private static final String SIZE = "s";
	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		RoomService roomService = provider.getRoomService();
		int page = (int) request.getAttribute(PAGE);
		int size = (int) request.getAttribute(SIZE);
		try {
			List<Room> rooms = roomService.getRoomListPage(page, size);
			request.setAttribute("rooms", rooms);
			RequestDispatcher requestDispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/main_index.jsp");
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {
			e.printStackTrace();
		}


	}

}
