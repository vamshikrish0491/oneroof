package com.acculytixs.wayuparty.dao.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.events.EventDetailsDTO;
import com.acculytixs.wayuparty.dto.events.EventListDTO;
import com.acculytixs.wayuparty.dto.events.EventQRDetailsDTO;
import com.acculytixs.wayuparty.dto.events.EventTicketsDTO;
import com.acculytixs.wayuparty.dto.events.EventsDTO;
import com.acculytixs.wayuparty.dto.events.EventsInfoDTO;
import com.acculytixs.wayuparty.dto.events.UserBookedEventsDTO;
import com.acculytixs.wayuparty.dto.events.UserConformedEventsDTO;
import com.acculytixs.wayuparty.entity.vendor.EventsOrder;
import com.acculytixs.wayuparty.entity.vendor.VendorEvents;
import com.acculytixs.wayuparty.entity.vendor.VendorEventsTickets;
import com.acculytixs.wayuparty.util.ResponseList;

public interface EventsDao {

	boolean isTicketNameExists(String ticketName,String vendorUUID) throws Exception;
	
	EventTicketsDTO getEventTicketDetails(String ticketUUID) throws Exception;
	
	void saveEventTicket(VendorEventsTickets vendorEventsTickets) throws Exception;
	
	Long getTicketIdByTicketUUID(String ticketUUID) throws Exception;
	
	List<EventTicketsDTO> getEventsTicketsList(String vendorUUID) throws Exception;
	
	VendorEventsTickets getVendorEventTicketByUUID(String ticketUUID) throws Exception;
	
	VendorEvents getVendorEventByEventUUID(String eventUUID) throws Exception;
	
	void saveVendorEvent(VendorEvents vendorEvents) throws Exception;
	
	List<EventListDTO> getEventList(String vendorUUID) throws Exception;
	
	List<EventListDTO> getVendorEventsList(String vendorUUID) throws Exception;
	
	EventDetailsDTO getEventDetails(String eventUUID) throws Exception;
	
	String getTimeSlotsByEventUUID(String eventUUID) throws Exception;
	
	EventsInfoDTO getEventsInfo(String eventUUID) throws Exception;
	
	void saveEventsOrder(EventsOrder eventsOrder) throws Exception;
	
	List<UserConformedEventsDTO> getUserEventList(String userUUID) throws Exception;
	
	EventQRDetailsDTO getEventEntryQRDetails(String orderUUID) throws Exception;
	
	ResponseList<UserBookedEventsDTO> getUserBookedEvents(UserBookedEventsDTO userBookedEventsDTO) throws Exception;
	
	EventsDTO getEventDetailsByUUID(String eventUUID) throws Exception;
	
}
