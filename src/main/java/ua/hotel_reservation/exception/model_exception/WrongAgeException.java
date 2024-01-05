package ua.hotel_reservation.exception.model_exception;

public class WrongAgeException extends RuntimeException{
    public String getMessage(){
        return "Wrong age value";
    }
}
