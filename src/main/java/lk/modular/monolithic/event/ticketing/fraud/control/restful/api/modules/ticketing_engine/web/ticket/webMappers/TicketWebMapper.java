package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.GetMyTicketResponseDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.TicketResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketWebMapper{

    //requestDTO to domain model
    TicketResponseDTO toResponseDTO(Ticket ticket);

    /* __GET_ALL_MY_TICKETS__ */

    //to command
    GetMyTicketCommand toGetMyTicketCommand(Long userId);

    //to response
    GetMyTicketResponseDTO toGetMyTicketResponseDTO(GetMyTicketResult getMyTicketResult);

}
