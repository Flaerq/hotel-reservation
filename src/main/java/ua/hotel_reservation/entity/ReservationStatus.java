package ua.hotel_reservation.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "reservation_status")
public class ReservationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(columnDefinition = "room_id", referencedColumnName = "id")
    private Room room;

    @OneToOne
    @JoinColumn(columnDefinition = "user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    public ReservationStatus(Room room, User user) {
        this.room = room;
        this.user = user;
    }

    public ReservationStatus(){

    }



    @Override
    public String toString() {
        return "ReservationStatus{" +
                "id=" + id +
                ", room=" + room +
                ", user=" + user +
                '}';
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
