package by.tc.task05.service;

import by.tc.task05.service.impl.HotelServiceImpl;
import by.tc.task05.service.impl.RoomServiceImpl;
import by.tc.task05.service.impl.UserServiceImpl;

public final class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private final UserService userService = new UserServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    private final HotelService hotelService = new HotelServiceImpl();

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }
}
