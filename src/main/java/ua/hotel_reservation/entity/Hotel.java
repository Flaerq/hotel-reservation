package ua.hotel_reservation.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "hotel")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }

    public Hotel(){

    }

    public Hotel(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRooms(Room... rooms){
        for (Room room : rooms){
            room.setOwningHotel(this);
            this.rooms.add(room);
        }
    }
}
