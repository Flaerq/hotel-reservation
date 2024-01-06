package ua.hotel_reservation.exception.enitiy_exceptions;

public class NotEnoughSpaceInRoomException extends RuntimeException{

    private String message;

    public NotEnoughSpaceInRoomException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }


}
