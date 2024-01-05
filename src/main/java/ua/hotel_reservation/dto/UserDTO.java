package ua.hotel_reservation.dto;

import jakarta.transaction.UserTransaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.hotel_reservation.logs.NewLogFileGenerator;
import ua.hotel_reservation.model.User;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private static SessionFactory sf = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
    private static Session session = sf.openSession();



    public static void sessionRunner (Runnable runnable){
        try {
            session.getTransaction().begin();

            runnable.run();

            session.getTransaction().commit();
        } catch (Exception e ){
            if (session.isOpen()){
                session.getTransaction().rollback();
            }
            sf.close();

            String fileName = NewLogFileGenerator.createNewFile( "user_dto", Paths.get("D:\\Maven-plugins\\Hotel-Reservation-System\\src\\main\\java\\ua\\hotel_reservation\\logs\\dto_classes_logs"));
            try (PrintStream ps = new PrintStream(fileName)){
                e.printStackTrace(ps);
            } catch (FileNotFoundException ignored){    }
        }
    }

    public void save(User user){
        sessionRunner(() -> {
            session.persist(user);
        });
    }

    public User readById(int id){
        User[] user = new User[1];
        sessionRunner(() -> {
            user[0] = session.get(User.class,id);
        });

        return user[0];
    }

    public List<User> readAll(){
        List<User> userList = new ArrayList<>();

        sessionRunner(() -> {
            userList.addAll(session.createQuery("From User").getResultList());
        });

        return userList;
    }

    public void delete(int id){
        sessionRunner(() -> {
            session.remove(session.get(User.class,id));
        });
    }

    public void update(int id, User user){
        sessionRunner(() -> {
            User dbUser = session.get(User.class,id);
            dbUser.setFirstName(user.getFirstName());
            dbUser.setLastName(user.getLastName());
            dbUser.setAge(user.getAge());
            dbUser.setPhoneNumber(user.getPhoneNumber());
        });
    }

}
