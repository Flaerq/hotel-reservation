package ua.hotel_reservation;

import ua.hotel_reservation.dto.HotelDTO;
import ua.hotel_reservation.dto.RoomDTO;
import ua.hotel_reservation.dto.UserDTO;
import ua.hotel_reservation.entity.Hotel;
import ua.hotel_reservation.entity.Room;
import ua.hotel_reservation.entity.User;

public class Main {

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Ukraine, Kyiv, Lob 2");

        Room room = new Room(123,3);
        Room room2 = new Room(124,2);
        Room room3 = new Room(124,2 );
        hotel.addRooms(room,room2,room3);

        HotelDTO dto = new HotelDTO();

        dto.save(hotel);
    }
}
