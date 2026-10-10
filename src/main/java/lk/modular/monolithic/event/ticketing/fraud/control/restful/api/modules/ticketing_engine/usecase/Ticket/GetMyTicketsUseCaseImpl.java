package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.shared_domain.SharedRepositories.UserValidationClientRepository;

import java.util.List;

public class GetMyTicketsUseCaseImpl implements GetMyTicketsUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;
    private final UserValidationClientRepository userValidationClientRepository;

    public GetMyTicketsUseCaseImpl(
            TicketRepository ticketRepository,
            UserValidationClientRepository userValidationClientRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.userValidationClientRepository = userValidationClientRepository;
    }

    //find all tickets of a user
    @Override
    public List<GetMyTicketResult> findMyTickets(GetMyTicketCommand getMyTicketCommand) {

        if(getMyTicketCommand.userId() == null || getMyTicketCommand.userId() == 0L){
            throw new IllegalArgumentException("Ticket id cannot be null or empty");
        }

        if(!userValidationClientRepository.userValidateById(getMyTicketCommand.userId())){
            throw new ResourceNotFoundException("User not found");
        }

        return ticketRepository.findMyTickets(getMyTicketCommand.userId()).stream()
                .map(ticket -> new GetMyTicketResult(
                        ticket.getTicketId().toString(),
                        ticket.getTicketCode(),
                        ticket.getEventId().toString(),
                        ticket.getOwnerId().toString(),
                        ticket.getTicketPrice().toString(),
                        ticket.getTicketStatus().toString(),
                        ticket.getPurchasedAt().toString(),
                        ticket.getScannedAt().toString()
                )).toList();
    }
}
