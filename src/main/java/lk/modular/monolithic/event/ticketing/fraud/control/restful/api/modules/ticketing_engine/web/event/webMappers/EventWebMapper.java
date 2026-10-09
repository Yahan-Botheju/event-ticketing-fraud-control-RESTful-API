package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.CreateEventRequestCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.GetAllEventsResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.CreateEventRequestDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.EventResponseDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.GetAllEventsResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventWebMapper {

    //requestDTO to domain model
    CreateEventRequestCommand toCommand(CreateEventRequestDTO createEventRequestDTO);

    //domain model to responseDTO
    EventResponseDTO toResponseDTO(Event event);

    /* __GET_ALL_EVENTS__ */

    //domain model to response
    GetAllEventsResponseDTO toGetAllEventsResponse(GetAllEventsResult getAllEventsResult);
}
