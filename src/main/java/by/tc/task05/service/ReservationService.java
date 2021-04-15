package by.tc.task05.service;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.entity.ExtendedReservation;
import by.tc.task05.entity.PageInformation;
import by.tc.task05.entity.Reservation;
import by.tc.task05.service.exception.ServiceException;
import by.tc.task05.utils.ListPart;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    public void add(Reservation reservation) throws ServiceException;

    public void acceptReservation(int userId, int reservationId, String password) throws ServiceException;

    public void archiveReservation(int userId, int reservationId, int archivationReason, String password)
            throws ServiceException;

    //public Optional<Reservation> get(int reservationId) throws ServiceException;

    public ListPart<ExtendedReservation> getByUser(int userId, PageInformation page, boolean archived)
            throws ServiceException;

    public ListPart<ExtendedReservation> getByHotel(int userId, int hotelId, PageInformation page, boolean archived)
            throws ServiceException;

    public ListPart<ExtendedReservation> getByRoom(int userId, int roomId, PageInformation page, boolean archived)
            throws ServiceException;

    public boolean areDatesOccupied(int roomId, LocalDate checkIn,
                                    LocalDate checkOut) throws ServiceException;
}
