package ua.hotel_reservation.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.hotel_reservation.entity.Hotel;
import ua.hotel_reservation.entity.ReservationStatus;
import ua.hotel_reservation.entity.Room;
import ua.hotel_reservation.entity.User;
import ua.hotel_reservation.logs.NewLogFileGenerator;

import java.awt.desktop.PreferencesEvent;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationStatusDAO {


    private static SessionFactory sf = new Configuration()
            .addAnnotatedClass(Room.class)
            .addAnnotatedClass(Hotel.class)
            .addAnnotatedClass(ReservationStatus.class)
            .addAnnotatedClass(User.class)
            .buildSessionFactory();
    private static Session session = sf.openSession();

    public static void sessionRunner(Runnable runnable){

        try {
            session.getTransaction().begin();

            runnable.run();

            session.getTransaction().commit();
        } catch (Exception e){
            if (session.isOpen()){
                session.getTransaction().rollback();
            }
            sf.close();

            String fileName = NewLogFileGenerator.createNewFile( "reservation-status_dto", Paths.get("D:\\Maven-plugins\\Hotel-Reservation-System\\src\\main\\java\\ua\\hotel_reservation\\logs\\dto_classes_logs"));
            try (PrintStream ps = new PrintStream(fileName)){
                e.printStackTrace(ps);
            } catch (FileNotFoundException ignored){    }
        }
    }

    public void save(ReservationStatus reservationStatus){
        sessionRunner(() -> {
            session.persist(reservationStatus);
        });
    }

    public List<ReservationStatus> read(int id){
        List<ReservationStatus> rsList = new ArrayList<>();
        sessionRunner(() ->{
            rsList.addAll(session.createQuery("from ReservationStatus WHERE room_id = "+id).getResultList());
        });

        return rsList;
    }

    public List<ReservationStatus> readAll(){
        List<ReservationStatus> reservationStatuses = new ArrayList<>();
        sessionRunner(() -> {
            reservationStatuses.addAll(session.createQuery("From ReservationStatus ").getResultList());
        });

        return reservationStatuses;
    }

    public void update(int id, ReservationStatus reservationStatus){
        sessionRunner(() -> {
            ReservationStatus rs = session.get(ReservationStatus.class,id);
            rs.setRoom(reservationStatus.getRoom());
            rs.setUser(reservationStatus.getUser());
        });
    }

    public void delete(int id){
        sessionRunner(() -> {
            session.remove(session.get(ReservationStatus.class,id));
        });
    }
}
