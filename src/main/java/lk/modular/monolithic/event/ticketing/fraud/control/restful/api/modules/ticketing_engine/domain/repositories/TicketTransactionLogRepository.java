package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.ticket_log_records.TicketTransactionLog;

public interface TicketTransactionLogRepository {
    //save ticket transaction log
    void save(TicketTransactionLog ticketTransactionLog);
}
