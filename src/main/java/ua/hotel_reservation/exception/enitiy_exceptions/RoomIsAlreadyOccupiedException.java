package ua.hotel_reservation.exception.enitiy_exceptions;

public class RoomIsAlreadyOccupiedException extends RuntimeException{

    private String message;

    public RoomIsAlreadyOccupiedException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
