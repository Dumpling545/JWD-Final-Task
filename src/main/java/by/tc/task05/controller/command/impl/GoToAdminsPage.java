package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.command.CommandName;
import by.tc.task05.entity.Hotel;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.User;
import by.tc.task05.service.HotelService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAdminsPage extends AuthorizedUserCommand {
    private static final String ADMINS_ATTRIBUTE_KEY = "admins";
    private static final String LAST_ATTRIBUTE_KEY = "last";
    private static final String ADMIN_MANAGEMENT_JSP_LOCATION =
            "/WEB-INF/jsp/adminManagement.jsp";
    private static final String PAGE_PARAMETER_KEY = "page";
    private static final String RESULTS_PER_PAGE_PARAMETER_KEY =
            "resultsperpage";
    private static final String ID_KEY = "id";


    @Override
    public CommandName getExceptionRedirectPolicy() {
        return CommandName.GOTOHOTELMANAGEMENTPAGE;
    }

    @Override
    public void executeAuthorized(int userId, HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServiceException, ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        HotelService hotelService = provider.getHotelService();
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
        int hotelId = Integer.parseInt(request.getParameter(ID_KEY));
        ListPart<User> admins =
                hotelService.getAdministratorsByHotel(userId, hotelId, pageInfo);
        request.setAttribute(ADMINS_ATTRIBUTE_KEY, admins.getSubList());
        request.setAttribute(LAST_ATTRIBUTE_KEY, admins.isLast());
        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(ADMIN_MANAGEMENT_JSP_LOCATION);
        requestDispatcher.forward(request, response);

    }
}
