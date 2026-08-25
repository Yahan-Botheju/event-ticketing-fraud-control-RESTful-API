package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.CreateEventRequestCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.CreateEventRequestDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.EventResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventWebMapper {

    //requestDTO to domain model
    CreateEventRequestCommand toCommand(CreateEventRequestDTO createEventRequestDTO);

    //domain model to responseDTO
    EventResponseDTO toResponseDTO(Event event);
}
