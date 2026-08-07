package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.transfer;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.UnauthorizedException;

public class TransferTicketUseCaseImpl implements  TransferTicketUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;

    public TransferTicketUseCaseImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //transfer a ticket
    @Override
    public Ticket transferTicket(
            Long ticketId,
            Long currentOwnerId,
            Long newOwnerId
    ){
        //check ticket availability
        Ticket findTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        //check ticket is owned by actual owner
        if(findTicket.getOwnerId().equals(currentOwnerId)){
            throw new UnauthorizedException("You are not allowed to transfer this ticket");
        }
        //set new ownership
        findTicket.transferOwnerShip(newOwnerId);

        return ticketRepository.save(findTicket);
    }
}
