package by.tc.task05.controller.command.impl;

import by.tc.task05.controller.helper.ExceptionMessageMapper;
import by.tc.task05.controller.helper.UrlHelper;
import by.tc.task05.entity.Reservation;
import by.tc.task05.entity.Review;
import by.tc.task05.entity.Room;
import by.tc.task05.service.ReservationService;
import by.tc.task05.service.ReviewService;
import by.tc.task05.service.RoomService;
import by.tc.task05.service.ServiceProvider;
import by.tc.task05.service.exception.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateReview extends AuthorizedUserCommand {
	private static final String RESERVATION_ID_PARAMETER_KEY = "reservationId";
	private static final String TITLE_PARAMETER_KEY = "title";
	private static final String TEXT_PARAMETER_KEY = "text";
	private static final String RATING_PARAMETER_KEY = "rating";
	private static final String REVIEW_CREATED_MESSAGE_KEY = "reviewCreated";


	@Override
	public void onUnauthorizedUser(HttpServletRequest request,
	                               HttpServletResponse response)
			throws IOException
	{
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}


	@Override
	public void onException(HttpServletRequest request,
	                        HttpServletResponse response,
	                        ServiceException e) throws IOException
	{
		UrlHelper.sendRedirectToLastUrlWithMessage(request, response,
				ExceptionMessageMapper.getKey(this, e));
	}

	@Override
	public void executeAuthorized(int userId, HttpServletRequest request,
	                              HttpServletResponse response)
			throws ServiceException, ServletException, IOException
	{
		Review review = new Review();
		String reservationIdText =
				request.getParameter(RESERVATION_ID_PARAMETER_KEY);
		if (reservationIdText != null && !reservationIdText.isBlank()) {
			review.setReservationId(Integer.parseInt(reservationIdText));
		}
		review.setTitle(request.getParameter(TITLE_PARAMETER_KEY));
		review.setText(request.getParameter(TEXT_PARAMETER_KEY));
		String ratingText = request.getParameter(RATING_PARAMETER_KEY);
		if (reservationIdText != null && !reservationIdText.isBlank()) {
			review.setRating(Integer.parseInt(ratingText));
		}
		ServiceProvider provider = ServiceProvider.getInstance();
		ReviewService reviewService =
				provider.getReviewService();
		reviewService.add(userId, review);
		UrlHelper.sendRedirectToLastUrlWithMessage(request, response,
				REVIEW_CREATED_MESSAGE_KEY);
	}
}
