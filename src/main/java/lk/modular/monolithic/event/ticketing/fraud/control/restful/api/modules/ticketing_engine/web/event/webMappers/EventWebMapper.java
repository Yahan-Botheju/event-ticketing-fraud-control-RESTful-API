package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventWebMapper {

    //requestDTO to domain model
    CreateEventCommand toCommand(CreateEventRequestDTO createEventRequestDTO);

    //domain model to responseDTO
    EventResponseDTO toResponseDTO(Event event);

    /* __GET_ALL_EVENTS__ */

    //domain model to response
    GetAllEventsResponseDTO toGetAllEventsResponse(GetAllEventsResult getAllEventsResult);

    /* __CREATE_EVENT__ */

    //request to command
    CreateEventCommand toCreateEventCommand(Long organizerId, CreateEventRequestDTO createEventRequestDTO);

    //domain model to response
    CreateEventResponseDTO toCreateEventResponseDTO(CreateEventResult createEventResult);

    /* __GET_EVENT_BY_ID__ */

    //to usecase command
    GetEventByIdCommand toGetEventByIdCommand(Long eventId);

    //domain model to response
    GetEventByIdResponseDTO getEventByIdResponse(GetEventByIdResult getEventByIdResult);
}
