package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception;

public class InvalidTicketException extends RuntimeException{
    public InvalidTicketException(String message)
    {
        super(message);
    }
}
