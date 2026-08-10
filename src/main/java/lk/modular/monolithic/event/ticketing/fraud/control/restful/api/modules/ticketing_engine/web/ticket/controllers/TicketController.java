package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.controllers;

import jakarta.validation.Valid;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.BuyTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.GetMyTicketsUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.ScanTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.TransferTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.TicketResponseDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.TransferTicketRequestDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.webMappers.TicketWebMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.DTOs.ApiResponse;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.annotation.CurrentUserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    //inject required dependencies
    private final BuyTicketUseCase  buyTicketUseCase;
    private final ScanTicketUseCase scanTicketUseCase;
    private final TransferTicketUseCase  transferTicketUseCase;
    private final GetMyTicketsUseCase getMyTicketsUseCase;
    private final TicketWebMapper ticketWebMapper;


    public TicketController(
            BuyTicketUseCase buyTicketUseCase,
            ScanTicketUseCase scanTicketUseCase,
            TransferTicketUseCase transferTicketUseCase,
            GetMyTicketsUseCase getMyTicketsUseCase,
            TicketWebMapper ticketWebMapper
    ) {
        this.buyTicketUseCase = buyTicketUseCase;
        this.scanTicketUseCase = scanTicketUseCase;
        this.transferTicketUseCase = transferTicketUseCase;
        this.getMyTicketsUseCase = getMyTicketsUseCase;
        this.ticketWebMapper = ticketWebMapper;
    }

    //buy ticket
    @PostMapping("/buy/{eventId}")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> buyTicket(
            @PathVariable("eventId") Long eventId,
            @CurrentUserId Long userId
    ){
        Ticket buyTicket = buyTicketUseCase.execute(eventId, userId);
        TicketResponseDTO responseDTO = ticketWebMapper.toResponseDTO(buyTicket);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(responseDTO));
    }

    //scan ticket at event counter
    @PostMapping("/scan/{ticketCode}")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> scanTicket(
            @PathVariable("ticketCode") String ticketCode
    ){
        Ticket scanTicket = scanTicketUseCase.execute(ticketCode);
        TicketResponseDTO responseDTO = ticketWebMapper.toResponseDTO(scanTicket);

        return ResponseEntity.ok(ApiResponse.success(responseDTO));
    }

    //transfer ticket
    @PostMapping("/transfer-ticket")
    public ResponseEntity<ApiResponse<TicketResponseDTO>> transferTicket(
            @Valid @RequestBody TransferTicketRequestDTO transferTicketRequestDTO,
            @CurrentUserId Long userId
    ){
        //get newUserId and ticketId from request
        Long newUserId = transferTicketRequestDTO.getNewOwnerId();
        Long ticketId = transferTicketRequestDTO.getTicketId();

        Ticket transferTicket = transferTicketUseCase.transferTicket(ticketId, userId, newUserId);
        TicketResponseDTO responseDTO = ticketWebMapper.toResponseDTO(transferTicket);

        return ResponseEntity.ok(ApiResponse.success(responseDTO));
    }


}
