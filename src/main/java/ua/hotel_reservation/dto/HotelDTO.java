package ua.hotel_reservation.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.hotel_reservation.entity.Hotel;
import ua.hotel_reservation.entity.Room;
import ua.hotel_reservation.logs.NewLogFileGenerator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelDTO {

    private static SessionFactory sf = new Configuration().addAnnotatedClass(Room.class)
            .addAnnotatedClass(Hotel.class).buildSessionFactory();
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

            String fileName = NewLogFileGenerator.createNewFile( "hotel_dto", Paths.get("D:\\Maven-plugins\\Hotel-Reservation-System\\src\\main\\java\\ua\\hotel_reservation\\logs\\dto_classes_logs"));
            try (PrintStream ps = new PrintStream(fileName)){
                e.printStackTrace(ps);
            } catch (FileNotFoundException ignored){    }
        }
    }

    public void save(Hotel hotel){
        sessionRunner(() -> {
            session.persist(hotel);
        });
    }

    public Optional<Hotel> read(int id){
        Hotel[] hotel = new Hotel[1];
        sessionRunner(() ->{
            hotel[0] = session.get(Hotel.class,id);
        });

        return Optional.ofNullable(hotel[0]);
    }

    public List<Hotel> readAll(){
        List<Hotel> hotels = new ArrayList<>();
        sessionRunner(() -> {
            hotels.addAll(session.createQuery("From Hotel").getResultList());
        });

        return hotels;
    }

    public void update(int id, Hotel hotel){
        sessionRunner(() -> {
            Hotel dbHotel = session.get(Hotel.class,id);
            dbHotel.setAddress(hotel.getAddress());
        });
    }

    public void delete(int id){
        sessionRunner(() -> {
            session.remove(session.get(Hotel.class,id));
        });
    }

}
