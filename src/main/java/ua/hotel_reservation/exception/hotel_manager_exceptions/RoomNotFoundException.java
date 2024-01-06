package ua.hotel_reservation.exception.hotel_manager_exceptions;

public class RoomNotFoundException extends RuntimeException{

    public String getMessage(){
        return "There is no such room in database";
    }
}
