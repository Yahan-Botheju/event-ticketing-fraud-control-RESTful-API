package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.shared_domain.SharedRepositories.UserValidationClientRepository;

import java.util.List;

public class GetAllTicketsUseCaseImpl implements  GetAllTicketsUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;
    private final UserValidationClientRepository userValidationClientRepository;

    public GetAllTicketsUseCaseImpl(
            TicketRepository ticketRepository,
            UserValidationClientRepository userValidationClientRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.userValidationClientRepository = userValidationClientRepository;
    }

    //find all tickets of a user
    @Override
    public List<Ticket> findMyTickets(Long userId) {
        userValidationClientRepository.userValidateById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found" + userId));

        return ticketRepository.findMyTickets(userId);
    }
}
