package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.TransferTicketCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.TransferTicketResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.MethodArgumentNotValidException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.UnauthorizedException;

public class TransferTicketUseCaseImpl implements TransferTicketUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;

    public TransferTicketUseCaseImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //transfer a ticket
    @Override
    public TransferTicketResult transferTicket(TransferTicketCommand transferTicketCommand) {

        if (transferTicketCommand.ticketId() == null || transferTicketCommand.currentOwnerId() == null || transferTicketCommand.newOwnerId() == null) {
            throw new MethodArgumentNotValidException("Required fields cannot be empty");
        }

        //check ticket availability
        Ticket findTicket = ticketRepository.findById(transferTicketCommand.ticketId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        //check ticket is owned by actual owner
        if (!findTicket.getOwnerId().equals(transferTicketCommand.currentOwnerId())) {
            throw new UnauthorizedException("You are not allowed to transfer this ticket");
        }
        //set new ownership
        findTicket.transferOwnerShip(transferTicketCommand.newOwnerId());

        Ticket ticket = ticketRepository.save(findTicket);

        return new TransferTicketResult(
                ticket.getTicketId().toString(),
                transferTicketCommand.currentOwnerId().toString(),
                transferTicketCommand.newOwnerId().toString(),
                ticket.getTicketCode(),
                ticket.getEventId().toString(),
                ticket.getTicketPrice().toString(),
                ticket.getTicketStatus().toString(),
                ticket.getPurchasedAt().toString(),
                ticket.getScannedAt().toString()
        );
    }
}
