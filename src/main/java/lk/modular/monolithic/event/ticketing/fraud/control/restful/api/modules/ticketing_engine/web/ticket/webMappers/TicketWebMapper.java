package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.TicketResponseDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.changed__;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketWebMapper{

    //requestDTO to domain model
    TicketResponseDTO toResponseDTO(Ticket ticket);
}
