package by.tc.task05.dao.impl;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import by.tc.task05.dao.DAOException;
import by.tc.task05.dao.RoomDAO;
import by.tc.task05.dao.connectionpool.ConnectionPool;
import by.tc.task05.dao.connectionpool.ConnectionPoolException;
import by.tc.task05.entity.*;
import by.tc.task05.entity.filter.RoomSearchDatabaseFilter;
import by.tc.task05.utils.ListPart;
import jakarta.servlet.http.Part;

public class SQLRoomDAO implements RoomDAO {
    private final static String SQL_BUNDLE = "by.tc.task05.bundle.sql";

    private final static String GET_MANY = "rooms.getMany";
    private final static String SQL_GET_MANY;

    private final static String GET_BY_ID = "rooms.getById";
    private final static String SQL_GET_BY_ID;

    private final static String GET_BY_HOTEL = "rooms.getByHotel";
    private final static String SQL_GET_BY_HOTEL;

    private final static String ADD = "rooms.add";
    private final static String SQL_ADD;

    private final static String CHANGE = "rooms.change";
    private final static String SQL_CHANGE;

    private final static String REMOVE = "rooms.remove";
    private final static String SQL_REMOVE;

    private final static String SET_ICON = "rooms.setIcon";
    private final static String SQL_SET_ICON;

    private final static String GET_FEATURE_BY_ID = "rooms.getRoomFeaturesById";
    private final static String SQL_GET_FEATURE_BY_ID;

    private final static String ADD_FEATURE = "rooms.addRoomFeatures";
    private final static String SQL_ADD_FEATURE;

    private final static String CHANGE_FEATURE = "rooms.changeRoomFeatures";
    private final static String SQL_CHANGE_FEATURE;

    private final static String REMOVE_FEATURE = "rooms.removeRoomFeatures";
    private final static String SQL_REMOVE_FEATURE;

    private final static String GET_PHOTOS_BY_ROOM = "rooms.getPhotosByRoom";
    private final static String SQL_GET_PHOTOS_BY_ROOM;

    private final static String ADD_PHOTO = "rooms.addPhoto";
    private final static String SQL_ADD_PHOTO;

    private final static String REMOVE_PHOTO = "rooms.removePhoto";
    private final static String SQL_REMOVE_PHOTO;

    private final static String IS_ADMIN = "rooms.isAdmin";
    private final static String SQL_IS_ADMIN;

    private final static String RELATIVE_ROOM_ICON_PATH = "rooms/icons/";


    //columns
    private final static String C_ID = "rooms.id";
    private static final String C_HOTEL_ID = "rooms.hotel_id";
    private static final String C_NAME = "rooms.name";
    private static final String C_NUMBER_OF_BEDS = "rooms.number_of_beds";
    private static final String C_SHORT_DESCRIPTION = "rooms.short_description";
    private static final String C_COST = "rooms.cost";
    private static final String C_ICON = "rooms.icon";

    private final static String C_FTR_HAS_AIRCON =
            "room_features.has_airconditioning";
    private static final String C_FTR_HAS_HEATING = "room_features.has_heating";
    private static final String C_FTR_HAS_BALCONY = "room_features.has_balcony";
    private static final String C_FTR_HAS_TV = "room_features.has_tv";
    private static final String C_FTR_HAS_REFRIGERATOR =
            "room_features.has_refrigerator";
    private static final String C_FTR_HAS_KITCHEN = "room_features.has_kitchen";
    private static final String C_FTR_HAS_HAIR_DRYER =
            "room_features.has_hair_dryer";
    private static final String C_FTR_HAS_TOILET = "room_features.has_toilet";
    private static final String C_FTR_HAS_SHOWER = "room_features.has_shower";
    private static final String C_FTR_HAS_WASHING_MACHINE =
            "room_features.has_washing_machine";
    private static final String C_FTR_DESCRIPTION = "room_features.description";
    private static final String C_PH_ID = "room_photos.id";
    private static final String C_PH_ROOM_ID = "room_photos.room_id";
    private static final String C_PH_EXT = "room_photos.extension";
    private static final String C_ADDRESS = "hotels.cached_address";
    private static final String C_RATING = "`rooms.rating`";


    static {
        ResourceBundle sb = ResourceBundle.getBundle(SQL_BUNDLE);
        SQL_GET_MANY = sb.getString(GET_MANY);
        SQL_ADD = sb.getString(ADD);
        SQL_CHANGE = sb.getString(CHANGE);
        SQL_REMOVE = sb.getString(REMOVE);
        SQL_GET_BY_ID = sb.getString(GET_BY_ID);
        SQL_SET_ICON = sb.getString(SET_ICON);
        SQL_ADD_FEATURE = sb.getString(ADD_FEATURE);
        SQL_CHANGE_FEATURE = sb.getString(CHANGE_FEATURE);
        SQL_REMOVE_FEATURE = sb.getString(REMOVE_FEATURE);
        SQL_GET_FEATURE_BY_ID = sb.getString(GET_FEATURE_BY_ID);
        SQL_GET_PHOTOS_BY_ROOM = sb.getString(GET_PHOTOS_BY_ROOM);
        SQL_ADD_PHOTO = sb.getString(ADD_PHOTO);
        SQL_REMOVE_PHOTO = sb.getString(REMOVE_PHOTO);
        SQL_GET_BY_HOTEL = sb.getString(GET_BY_HOTEL);
        SQL_IS_ADMIN = sb.getString(IS_ADMIN);
    }

    private void setParametersForGetManyStatement(
            PreparedStatement preparedStatement,
            RoomSearchDatabaseFilter filter) throws SQLException {
        Date outDate = Date.valueOf(filter.getCheckOutDate());
        preparedStatement.setDate(1, outDate);
        preparedStatement.setDate(2, outDate);
        preparedStatement.setDate(3, Date.valueOf(filter.getCheckInDate()));
        preparedStatement.setDate(4, outDate);
        preparedStatement
                .setObject(5, filter.getLatitudeLowBound(), Types.DECIMAL, 6);
        preparedStatement
                .setObject(6, filter.getLatitudeHighBound(), Types.DECIMAL, 6);
        preparedStatement
                .setObject(7, filter.getLongtitudeLowBound(), Types.DECIMAL, 6);
        preparedStatement
                .setObject(8, filter.getLongtitudeHighBound(), Types.DECIMAL,
                        6);
        preparedStatement.setObject(9, filter.getCostLowBound(), Types.DECIMAL);
        preparedStatement
                .setObject(10, filter.getCostHighBound(), Types.DECIMAL);
        preparedStatement
                .setObject(11, filter.getNumberOfBeds(), Types.TINYINT);
        preparedStatement
                .setObject(12, filter.getRatingLowBound(), Types.DECIMAL);
        preparedStatement
                .setObject(13, filter.getRatingHighBound(), Types.DECIMAL);
        preparedStatement.setInt(14, filter.getSkipAmount());
        preparedStatement.setInt(15, filter.getTakeAmount());
    }

    @Override
    public List<RoomShortView> getMany(RoomSearchDatabaseFilter filter)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<RoomShortView> rooms = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_MANY);
            setParametersForGetManyStatement(preparedStatement, filter);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoomShortView room = new RoomShortView();
                room.setId(resultSet.getInt(C_ID));
                room.setName(resultSet.getString(C_NAME));
                room.setAddress(resultSet.getString(C_ADDRESS));
                room.setRating(resultSet.getDouble(C_RATING));
                room.setIcon(resultSet.getString(C_ICON));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return rooms;
    }

    @Override
    public boolean isRoomAdministrator(int userId, int roomId)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_IS_ADMIN);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, roomId);
            resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return result;
    }

    @Override
    public Optional<Room> getById(int roomId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<Room> room = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);
            preparedStatement.setInt(1, roomId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Room r = new Room();
                r.setId(roomId);
                r.setName(resultSet.getString(C_NAME));
                r.setHotelId(resultSet.getInt(C_HOTEL_ID));
                r.setCost(resultSet.getDouble(C_COST));
                r.setNumberOfBeds(resultSet.getInt(C_NUMBER_OF_BEDS));
                r.setShortDescription(resultSet.getString(C_SHORT_DESCRIPTION));
                r.setIcon(resultSet.getString(C_ICON));
                room = Optional.of(r);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return room;
    }

    @Override
    public List<Room> getByHotel(int hotelId, int skip, int take)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_HOTEL);
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room r = new Room();
                r.setId(resultSet.getInt(C_ID));
                r.setName(resultSet.getString(C_NAME));
                r.setHotelId(hotelId);
                r.setCost(resultSet.getDouble(C_COST));
                r.setNumberOfBeds(resultSet.getInt(C_NUMBER_OF_BEDS));
                r.setShortDescription(resultSet.getString(C_SHORT_DESCRIPTION));
                r.setIcon(resultSet.getString(C_ICON));
                rooms.add(r);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return rooms;
    }

    @Override
    public void add(Room room) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getName());
            preparedStatement.setInt(3, room.getNumberOfBeds());
            preparedStatement.setString(4, room.getShortDescription());
            preparedStatement.setDouble(5, room.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void change(Room room) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE);
            preparedStatement.setString(1, room.getName());
            preparedStatement.setInt(2, room.getNumberOfBeds());
            preparedStatement.setString(3, room.getShortDescription());
            preparedStatement.setDouble(4, room.getCost());
            preparedStatement.setInt(5, room.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void setIcon(int roomId, Part icon) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_SET_ICON);
            int index = icon.getSubmittedFileName().lastIndexOf('.');
            String ext = icon.getSubmittedFileName().substring(index);
            StringBuilder pathBuilder =
                    new StringBuilder(RELATIVE_ROOM_ICON_PATH);
            pathBuilder.append(roomId).append(ext);
            String path = pathBuilder.toString();
            icon.write(path);
            preparedStatement.setString(1, path);
            preparedStatement.setInt(2, roomId);
            preparedStatement.executeUpdate();
        } catch (IOException | SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void remove(int roomId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_REMOVE);
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public Optional<RoomFeature> getRoomFeatureById(int roomId)
            throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<RoomFeature> feature = Optional.empty();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement =
                    connection.prepareStatement(SQL_GET_FEATURE_BY_ID);
            preparedStatement.setInt(1, roomId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                RoomFeature f = new RoomFeature();
                f.setRoomId(roomId);
                f.setDescription(resultSet.getString(C_FTR_DESCRIPTION));
                f.setHasAirconditioning(resultSet.getBoolean(C_FTR_HAS_AIRCON));
                f.setHasBalcony(resultSet.getBoolean(C_FTR_HAS_BALCONY));
                f.setHasHairDryer(resultSet.getBoolean(C_FTR_HAS_HAIR_DRYER));
                f.setHasHeating(resultSet.getBoolean(C_FTR_HAS_HEATING));
                f.setHasKitchen(resultSet.getBoolean(C_FTR_HAS_KITCHEN));
                f.setHasShower(resultSet.getBoolean(C_FTR_HAS_SHOWER));
                f.setHasRefrigerator(
                        resultSet.getBoolean(C_FTR_HAS_REFRIGERATOR));
                f.setHasToilet(resultSet.getBoolean(C_FTR_HAS_TOILET));
                f.setHasTV(resultSet.getBoolean(C_FTR_HAS_TV));
                f.setHasWashingMachine(
                        resultSet.getBoolean(C_FTR_HAS_WASHING_MACHINE));
                feature = Optional.of(f);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return feature;
    }

    @Override
    public void addFeature(RoomFeature feature) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_ADD_FEATURE);
            preparedStatement.setInt(1, feature.getRoomId());
            preparedStatement.setBoolean(2, feature.isHasAirconditioning());
            preparedStatement.setBoolean(3, feature.isHasHeating());
            preparedStatement.setBoolean(4, feature.isHasBalcony());
            preparedStatement.setBoolean(5, feature.isHasTV());
            preparedStatement.setBoolean(6, feature.isHasRefrigerator());
            preparedStatement.setBoolean(7, feature.isHasKitchen());
            preparedStatement.setBoolean(8, feature.isHasHairDryer());
            preparedStatement.setBoolean(9, feature.isHasToilet());
            preparedStatement.setBoolean(10, feature.isHasShower());
            preparedStatement.setBoolean(11, feature.isHasWashingMachine());
            preparedStatement.setString(12, feature.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);

            }
        }
    }

    @Override
    public void changeFeature(RoomFeature feature) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_FEATURE);
            preparedStatement.setBoolean(1, feature.isHasAirconditioning());
            preparedStatement.setBoolean(2, feature.isHasHeating());
            preparedStatement.setBoolean(3, feature.isHasBalcony());
            preparedStatement.setBoolean(4, feature.isHasTV());
            preparedStatement.setBoolean(5, feature.isHasRefrigerator());
            preparedStatement.setBoolean(6, feature.isHasKitchen());
            preparedStatement.setBoolean(7, feature.isHasHairDryer());
            preparedStatement.setBoolean(8, feature.isHasToilet());
            preparedStatement.setBoolean(9, feature.isHasShower());
            preparedStatement.setBoolean(10, feature.isHasWashingMachine());
            preparedStatement.setString(11, feature.getDescription());
            preparedStatement.setInt(12, feature.getRoomId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void removeFeature(int roomId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(SQL_REMOVE_FEATURE);
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public List<RoomPhoto> getPhotosByRoom(int roomId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<RoomPhoto> photos = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement =
                    connection.prepareStatement(SQL_GET_PHOTOS_BY_ROOM);
            preparedStatement.setInt(1, roomId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoomPhoto photo = new RoomPhoto();
                photo.setId(resultSet.getInt(C_PH_ID));
                photo.setRoomId(roomId);
                photo.setExtension(resultSet.getString(C_PH_EXT));
                photos.add(photo);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException | SQLException e) {
                throw new DAOException(e);
            }
        }
        return photos;
    }

    @Override
    public void addPhotos(int roomId, List<Part> photos) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_ADD_PHOTO,
                    Statement.RETURN_GENERATED_KEYS);
            StringBuilder pathBuilder =
                    new StringBuilder(RoomPhoto.RELATIVE_ROOM_PHOTO_PATH);
            int startVarIndex = pathBuilder.length();
            for (Part photo : photos) {
                preparedStatement.setInt(1, roomId);
                int index = photo.getSubmittedFileName().lastIndexOf('.');
                String ext = photo.getSubmittedFileName().substring(index);
                preparedStatement.setString(2, ext);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    pathBuilder.append(id).append(ext);
                    photo.write(pathBuilder.toString());
                } else {
                    try {
                        connection.rollback();
                    } catch (SQLException innerE) {
                        throw new DAOException(innerE);
                    }
                    throw new DAOException();
                }
                pathBuilder.delete(startVarIndex,  pathBuilder.length());
            }
            connection.commit();
        } catch (IOException | SQLException | ConnectionPoolException e) {
            try {
                connection.rollback();
            } catch (SQLException innerE) {
                throw new DAOException(innerE);
            }
            throw new DAOException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                if (preparedStatement != null) preparedStatement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void removePhotos(int roomId, List<Integer> photoIds)
            throws DAOException {
        //TODO May Be Later, May Be Never...
    }
}
