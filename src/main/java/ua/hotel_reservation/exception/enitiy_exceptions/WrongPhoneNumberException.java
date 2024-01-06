package ua.hotel_reservation.exception.enitiy_exceptions;

public class WrongPhoneNumberException extends  RuntimeException {

    public String getMessage(){
        return "Wrong phone number value that didn't match pattern";
    }
}
