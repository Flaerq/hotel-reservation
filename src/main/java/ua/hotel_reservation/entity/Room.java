package ua.hotel_reservation.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "room_capacity")
    private int roomCapacity;

    @ManyToOne
    @JoinColumn(columnDefinition = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ReservationStatus> reservationStatuses = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<ReservationStatus> getReservationStatuses() {
        return reservationStatuses;
    }

    public void setReservationStatuses(List<ReservationStatus> reservationStatuses) {
        this.reservationStatuses = reservationStatuses;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public Room(int roomNumber, int roomCapacity) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
    }

    public Room(){

    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", roomCapacity=" + roomCapacity +
                '}';
    }

    public Hotel getOwningHotel() {
        return hotel;
    }

    public void setOwningHotel(Hotel owningHotel) {
        this.hotel = owningHotel;
    }
}
