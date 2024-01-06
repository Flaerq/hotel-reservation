package ua.hotel_reservation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.hotel_reservation.entity.Hotel;
import ua.hotel_reservation.entity.ReservationStatus;
import ua.hotel_reservation.entity.Room;
import ua.hotel_reservation.entity.User;
import ua.hotel_reservation.managers.HotelManager;

public class Main {

    public static void main(String[] args) {
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Hotel.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(ReservationStatus.class)
                .buildSessionFactory();
        Session session = sf.openSession();

        try {
            session.getTransaction().begin();

            HotelManager.reserve(session.get(Room.class,1),new User("Vitali","Verzun",20,"0965868346"));

            session.getTransaction().commit();
        } catch (Exception e){
            sf.close();
            e.printStackTrace();
        }

    }
}
