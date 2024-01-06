package ua.hotel_reservation.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.hotel_reservation.entity.Hotel;
import ua.hotel_reservation.entity.ReservationStatus;
import ua.hotel_reservation.entity.Room;
import ua.hotel_reservation.entity.User;
import ua.hotel_reservation.logs.NewLogFileGenerator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAO {

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

            String fileName = NewLogFileGenerator.createNewFile( "room_dto", Paths.get("D:\\Maven-plugins\\Hotel-Reservation-System\\src\\main\\java\\ua\\hotel_reservation\\logs\\dto_classes_logs"));
            try (PrintStream ps = new PrintStream(fileName)){
                e.printStackTrace(ps);
            } catch (FileNotFoundException ignored){    }
        }

    }
    public void save(Room room){
        sessionRunner(() -> {
            session.persist(room);
        });
    }

    public Optional<Room> read(int id){
        Room[] room = new Room[1];
        sessionRunner(() ->{
            room[0] = session.get(Room.class,id);
        });

        return Optional.ofNullable(room[0]);
    }

    public List<Room> readAll(){
        List<Room> rooms = new ArrayList<>();
        sessionRunner(() -> {
            rooms.addAll(session.createQuery("From Room").getResultList());
        });

        return rooms;
    }

    public void update(int id, Room room){
        sessionRunner(() -> {
            Room dbRoom = session.get(Room.class,id);
            dbRoom.setRoomNumber(room.getRoomNumber());
            dbRoom.setRoomCapacity(room.getRoomCapacity());
        });
    }

    public void delete(int id){
        sessionRunner(() -> {
            session.remove(session.get(Room.class,id));
        });
    }


}
