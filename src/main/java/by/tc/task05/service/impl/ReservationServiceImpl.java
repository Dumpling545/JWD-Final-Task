package by.tc.task05.service.impl;

import by.tc.task05.dao.DAOProvider;
import by.tc.task05.dao.HotelDAO;
import by.tc.task05.dao.ReservationDAO;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.dao.exception.OccupiedDateRangeDAOException;
import by.tc.task05.entity.*;
import by.tc.task05.service.*;
import by.tc.task05.service.exception.*;
import by.tc.task05.service.helper.HelperProvider;
import by.tc.task05.service.helper.PaymentHelper;
import by.tc.task05.service.validator.DateRangeValidator;
import by.tc.task05.service.validator.PageValidator;
import by.tc.task05.service.validator.ValidatorProvider;
import by.tc.task05.utils.ListPart;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {
    @Override
    public void add(Reservation reservation) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RoomService roomService = serviceProvider.getRoomService();
        HotelService hotelService = serviceProvider.getHotelService();
        Room room = roomService.getById(reservation.getRoomId());
        DateRangeValidator validator =
                ValidatorProvider.getInstance().getDateRangeValidator();
        validator.validateRange(reservation.getCheckInDate(),
                reservation.getCheckOutDate(), true);
        long days = ChronoUnit.DAYS.between(reservation.getCheckInDate(),
                reservation.getCheckOutDate());
        reservation.setPaymentAmount(room.getCost() * days);
        Hotel hotel = hotelService.getById(room.getHotelId());
        PaymentHelper paymentHelper =
                HelperProvider.getInstance().getPaymentHelper();
        reservation.setPaymentToken(paymentHelper
                .getTokenForPayment(hotel.getBankAccount(),
                        reservation.getPaymentAmount()));
        ReservationDAO reservationDAO = daoProvider.getReservationDAO();
        try {
            reservationDAO.add(reservation);
        } catch (OccupiedDateRangeDAOException e) {
            throw new OccupiedDateRangeException(e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void acceptReservation(int userId, int reservationId,
                                  String password) throws ServiceException {
        ReservationDAO reservationDAO =
                DAOProvider.getInstance().getReservationDAO();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RoomService roomService = serviceProvider.getRoomService();
        UserService userService = serviceProvider.getUserService();
        try {
            Optional<Reservation> reservation =
                    reservationDAO.get(reservationId);
            if (reservation.isPresent()) {
                if (roomService.isRoomAdministrator(userId,
                        reservation.get().getRoomId()) &&
                        userService.isPasswordMatched(userId, password)) {
                    reservationDAO.acceptReservation(reservationId);
                } else {
                    throw new UnauthorizedActionException();
                }
            } else {
                throw new NoSuchReservationException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void archiveReservation(int userId, int reservationId,
                                   int archivationReason, String password)
            throws ServiceException {
        ReservationDAO reservationDAO =
                DAOProvider.getInstance().getReservationDAO();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RoomService roomService = serviceProvider.getRoomService();
        UserService userService = serviceProvider.getUserService();
        try {
            if (!userService.isPasswordMatched(userId, password)) {
                throw new UnauthorizedActionException();
            }
            Optional<Reservation> reservation =
                    reservationDAO.get(reservationId);
            if (reservation.isEmpty()) {
                throw new NoSuchReservationException();
            }
            switch (archivationReason) {
                case ExtendedReservation.CANCELLED:
                    if (reservation.get().getUserId() != userId) {
                        throw new UnauthorizedActionException();
                    }
                    reservationDAO.archiveReservation(reservationId,
                            archivationReason);
                    break;
                case ExtendedReservation.REJECTED:
                case ExtendedReservation.ENDED_SUCCESSFULLY:
                    if (!roomService.isRoomAdministrator(userId,
                            reservation.get().getRoomId())) {
                        throw new UnauthorizedActionException();
                    }
                    reservationDAO.archiveReservation(reservationId,
                            archivationReason);
                    break;
                default:
                    throw new InvalidArchivationReasonException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ListPart<ExtendedReservation> getByUser(int userId,
                                                   PageInformation page,
                                                   boolean archived)
            throws ServiceException {
        PageValidator pageValidator =
                ValidatorProvider.getInstance().getPageValidator();
        pageValidator.validatePage(page);
        DAOProvider provider = DAOProvider.getInstance();
        ReservationDAO reservationDAO = provider.getReservationDAO();
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            int skip = (page.getPage() - 1) * page.getPageSize();
            int take = page.getPageSize();
            if (archived) {
                reservations.addAll(reservationDAO
                        .getArchivedByUser(userId, skip, take + 1));
            } else {
                reservations.addAll(reservationDAO
                        .getByUser(userId, skip, take + 1));
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        boolean last = (reservations.size() <= page.getPageSize());
        if (!last) {
            reservations.remove(reservations.size() - 1);
        }
        return new ListPart<ExtendedReservation>(reservations, last);
    }

    @Override
    public ListPart<ExtendedReservation> getByHotel(int userId, int hotelId,
                                                    PageInformation page,
                                                    boolean archived)
            throws ServiceException {
        PageValidator pageValidator =
                ValidatorProvider.getInstance().getPageValidator();
        pageValidator.validatePage(page);
        DAOProvider provider = DAOProvider.getInstance();
        ReservationDAO reservationDAO = provider.getReservationDAO();
        HotelDAO hotelDAO = provider.getHotelDAO();
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            if (hotelDAO.isHotelAdministrator(userId, hotelId)) {
                int skip = (page.getPage() - 1) * page.getPageSize();
                int take = page.getPageSize();
                if (archived) {
                    reservations.addAll(reservationDAO
                            .getArchivedByHotel(hotelId, skip, take + 1));
                } else {
                    reservations.addAll(reservationDAO
                            .getByHotel(hotelId, skip, take + 1));
                }
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        boolean last = (reservations.size() <= page.getPageSize());
        if (!last) {
            reservations.remove(reservations.size() - 1);
        }
        return new ListPart<ExtendedReservation>(reservations, last);
    }

    @Override
    public ListPart<ExtendedReservation> getByRoom(int userId, int roomId,
                                                   PageInformation page,
                                                   boolean archived)
            throws ServiceException {
        PageValidator pageValidator =
                ValidatorProvider.getInstance().getPageValidator();
        pageValidator.validatePage(page);
        DAOProvider provider = DAOProvider.getInstance();
        ReservationDAO reservationDAO = provider.getReservationDAO();
        RoomDAO roomDAO = provider.getRoomDAO();
        List<ExtendedReservation> reservations = new ArrayList<>();
        try {
            if (roomDAO.isRoomAdministrator(userId, roomId)) {
                int skip = (page.getPage() - 1) * page.getPageSize();
                int take = page.getPageSize();
                if (archived) {
                    reservations.addAll(reservationDAO
                            .getArchivedByRoom(roomId, skip, take + 1));
                } else {
                    reservations.addAll(reservationDAO
                            .getByRoom(roomId, skip, take + 1));
                }
                reservations.addAll(reservationDAO
                        .getByRoom(roomId, skip, take + 1));
            } else {
                throw new UnauthorizedActionException();
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        boolean last = (reservations.size() <= page.getPageSize());
        if (!last) {
            reservations.remove(reservations.size() - 1);
        }
        return new ListPart<ExtendedReservation>(reservations, last);
    }


    @Override
    public boolean areDatesOccupied(int roomId, LocalDate checkIn,
                                    LocalDate checkOut)
            throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        ReservationDAO reservationDAO = provider.getReservationDAO();
        boolean result = false;
        try {
            result = reservationDAO.areDatesOccupied(roomId, checkIn, checkOut);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
