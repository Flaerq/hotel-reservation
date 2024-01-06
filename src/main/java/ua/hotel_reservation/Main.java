package ua.hotel_reservation;

import ua.hotel_reservation.dto.UserDTO;
import ua.hotel_reservation.entity.User;

public class Main {

    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();

        User user = new User("Danil","Keks",20,"800-555-1234");

        System.out.println(userDTO.readAll());
    }
}
