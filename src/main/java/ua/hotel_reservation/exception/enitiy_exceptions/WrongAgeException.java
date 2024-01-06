package ua.hotel_reservation.exception.enitiy_exceptions;

public class WrongAgeException extends RuntimeException{
    public String getMessage(){
        return "Wrong age value";
    }
}
