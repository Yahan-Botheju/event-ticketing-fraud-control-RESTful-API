package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.controllers;

import jakarta.validation.Valid;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.BuyTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.GetMyTicketsUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.ScanTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.TransferTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.webMappers.TicketWebMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.DTOs.ApiResponse;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.annotation.CurrentUserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse<BuyTicketResponseDTO>> buyTicket(
            @PathVariable("eventId") Long eventId,
            @CurrentUserId Long userId
    ){
        BuyTicketCommand toCommand = ticketWebMapper.toBuyTicketCommand(eventId, userId);
        BuyTicketResult toResult = buyTicketUseCase.execute(toCommand);
        BuyTicketResponseDTO responseDTO = ticketWebMapper.toBuyTicketResponseDTO(toResult);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(responseDTO));
    }

    //scan ticket at event counter
    @PostMapping("/scan/{ticketCode}")
    public ResponseEntity<ApiResponse<ScanTicketResponseDTO>> scanTicket(
            @PathVariable("ticketCode") String ticketCode
    ){
        ScanTicketCommand toCommand = ticketWebMapper.toScanTicketCommand(ticketCode);
        ScanTicketResult toResult = scanTicketUseCase.execute(toCommand);
        ScanTicketResponseDTO responseDTO = ticketWebMapper.toScanTicketResponseDTO(toResult);

        return ResponseEntity.ok(ApiResponse.success(responseDTO));
    }

    //transfer ticket
    @PostMapping("/transfer-ticket")
    public ResponseEntity<ApiResponse<TransferTicketResponseDTO>> transferTicket(
            @Valid @RequestBody TransferTicketRequestDTO transferTicketRequestDTO,
            @CurrentUserId Long userId
    ){
        TransferTicketCommand toCommand = ticketWebMapper.toTransferTicketCommand(userId, transferTicketRequestDTO);
        TransferTicketResult toResult = transferTicketUseCase.transferTicket(toCommand);
        TransferTicketResponseDTO responseDTO = ticketWebMapper.toTransferTicketResponseDTO(toResult);

        return ResponseEntity.ok(ApiResponse.success(responseDTO));
    }

    //get my all tickets
    @GetMapping("/my-tickets")
    public ResponseEntity<ApiResponse<List<GetMyTicketsResponseDTO>>> getMyTickets(
            @CurrentUserId Long userId
    ){
        GetMyTicketsCommand toCommand = ticketWebMapper.toGetMyTicketCommand(userId);
        List<GetMyTicketsResult> results = getMyTicketsUseCase.findMyTickets(toCommand);
        List<GetMyTicketsResponseDTO> responseDTOS = results.stream()
                .map(ticketWebMapper::toGetMyTicketResponseDTO).toList();

        return ResponseEntity.ok(ApiResponse.success(responseDTOS));
    }
}
