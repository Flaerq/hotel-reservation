package ua.hotel_reservation.exception.model_exception;

public class WrongPhoneNumberException extends  RuntimeException {

    public String getMessage(){
        return "Wrong phone number value that didn't match pattern";
    }
}
