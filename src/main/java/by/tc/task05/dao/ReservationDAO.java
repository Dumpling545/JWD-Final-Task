package by.tc.task05.dao;

import by.tc.task05.dao.exception.DAOException;
import by.tc.task05.entity.ExtendedReservation;
import by.tc.task05.entity.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationDAO {
    public void add(Reservation reservation) throws DAOException;

    public void acceptReservation(int reservationId) throws DAOException;

    public void archiveReservation(int reservationId, int archivationReason)
            throws DAOException;

    public Optional<Reservation> get(int reservationId) throws DAOException;

    public List<ExtendedReservation> getByUser(int userId, int skip, int take)
            throws DAOException;

    public List<ExtendedReservation> getByHotel(int hotelId, int skip, int take)
            throws DAOException;

    public List<ExtendedReservation> getByRoom(int roomId, int skip, int take)
            throws DAOException;

    public List<ExtendedReservation> getArchivedByUser(int userId, int skip,
                                                       int take)
            throws DAOException;

    public List<ExtendedReservation> getArchivedByHotel(int hotelId, int skip,
                                                        int take)
            throws DAOException;

    public List<ExtendedReservation> getArchivedByRoom(int roomId, int skip,
                                                       int take)
            throws DAOException;

    public boolean areDatesOccupied(int roomId, LocalDate checkIn,
                                   LocalDate checkOut) throws DAOException;
}
