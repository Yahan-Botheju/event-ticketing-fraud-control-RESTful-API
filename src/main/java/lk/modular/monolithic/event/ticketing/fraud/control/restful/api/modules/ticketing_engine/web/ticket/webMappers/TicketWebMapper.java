package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketWebMapper{

    /* __GET_ALL_MY_TICKETS__ */

    //to command
    GetMyTicketsCommand toGetMyTicketCommand(Long userId);

    //to response
    GetMyTicketsResponseDTO toGetMyTicketResponseDTO(GetMyTicketsResult getMyTicketResult);

    /* __BUY_TICKET__ */

    //to command
    BuyTicketCommand toBuyTicketCommand(Long eventId, Long userId);

    //to response
    BuyTicketResponseDTO toBuyTicketResponseDTO(BuyTicketResult  buyTicketResult);

    /* __SCAN_TICKET__ */

    //to command
    ScanTicketCommand toScanTicketCommand(String ticketCode);

    //to response
    ScanTicketResponseDTO toScanTicketResponseDTO(ScanTicketResult scanTicketResult);

    /* __TRANSFER_TICKET__ */

    //to command
    TransferTicketCommand  toTransferTicketCommand(Long userId, TransferTicketRequestDTO  transferTicketRequest);

    //to response
    TransferTicketResponseDTO toTransferTicketResponseDTO(TransferTicketResult transferTicketResult);

}
