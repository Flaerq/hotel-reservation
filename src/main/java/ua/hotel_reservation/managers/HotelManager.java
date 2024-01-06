package ua.hotel_reservation.managers;

import ua.hotel_reservation.dto.ReservationStatusDAO;
import ua.hotel_reservation.dto.RoomDAO;
import ua.hotel_reservation.entity.ReservationStatus;
import ua.hotel_reservation.entity.Room;
import ua.hotel_reservation.entity.User;
import ua.hotel_reservation.exception.enitiy_exceptions.NotEnoughSpaceInRoomException;
import ua.hotel_reservation.exception.enitiy_exceptions.RoomIsAlreadyOccupiedException;
import ua.hotel_reservation.exception.hotel_manager_exceptions.RoomNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class HotelManager {

    public static void reserve(Room room, User... users){
        ReservationStatusDAO rsDao = new ReservationStatusDAO();
        RoomDAO roomDao = new RoomDAO();

        if (room == null || roomDao.readAll().stream()
                .filter(r -> r.getId() == room.getId())
                .findFirst()
                .get() == null){
            throw new RoomNotFoundException();
        }

        List<ReservationStatus> rs = rsDao.readAll();
        List<User> userInRoom = rs.stream()
                .filter(res -> res.getRoom().getId()==room.getId())
                .map(res -> res.getUser())
                .collect(Collectors.toList());
        if (!userInRoom.isEmpty()){
            throw new RoomIsAlreadyOccupiedException("Room №"+room.getRoomNumber() + " is already occupied by " + userInRoom.stream()
                    .map(user -> user.getFirstName() +" "+  user.getLastName())
                    .collect(Collectors.joining(", ")));
        }

        if (room.getRoomCapacity()  < users.length){
            throw new NotEnoughSpaceInRoomException("Room is expected for " + room.getRoomCapacity() + " person, but there is " + users.length);
        }



        for (User user : users){
            ReservationStatus reservation = new ReservationStatus(room,user);
            rsDao.save(reservation);
        }



    }
}

