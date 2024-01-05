package ua.hotel_reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.hotel_reservation.dto.UserDTO;
import ua.hotel_reservation.exception.model_exception.WrongAgeException;
import ua.hotel_reservation.logs.NewLogFileGenerator;
import ua.hotel_reservation.model.User;

import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();

        User user = new User("Danil","Keks",20,"800-555-1234");

        System.out.println(userDTO.readAll());
    }
}
