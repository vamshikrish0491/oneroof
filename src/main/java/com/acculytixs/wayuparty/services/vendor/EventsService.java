package com.acculytixs.wayuparty.services.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.events.EventCategoriesDTO;
import com.acculytixs.wayuparty.dto.events.EventDetailsDTO;
import com.acculytixs.wayuparty.dto.events.EventListDTO;
import com.acculytixs.wayuparty.dto.events.EventQRDetailsDTO;
import com.acculytixs.wayuparty.dto.events.EventTicketCategoriesDTO;
import com.acculytixs.wayuparty.dto.events.EventTicketsDTO;
import com.acculytixs.wayuparty.dto.events.EventTimeSlotsDTO;
import com.acculytixs.wayuparty.dto.events.EventsDTO;
import com.acculytixs.wayuparty.dto.events.EventsTicketInfoDTO;
import com.acculytixs.wayuparty.dto.events.PlaceEventsDTO;
import com.acculytixs.wayuparty.dto.events.UserBookedEventsDTO;
import com.acculytixs.wayuparty.dto.events.UserConformedEventsDTO;
import com.acculytixs.wayuparty.util.ResponseList;

public interface EventsService {

	boolean isTicketNameExists(String ticketName,String vendorUUID) throws Exception;
	
	EventTicketsDTO getEventTicketDetails(String ticketUUID) throws Exception;
	
	void saveEventTicket(EventTicketsDTO eventTicketsDTO) throws Exception;
	
	List<EventTicketsDTO> getEventsTicketsList(String vendorUUID) throws Exception;
	
	String saveVendorEvent(EventsDTO eventsDTO) throws Exception;
	
	List<EventListDTO> getEventList(String vendorUUID) throws Exception;
	
	List<EventListDTO> getVendorEventsList(String vendorUUID) throws Exception;
	
	EventDetailsDTO getEventDetails(String eventUUID) throws Exception;
	
	EventTimeSlotsDTO getEventTimeSlots(String eventUUID) throws Exception;
	
	List<EventCategoriesDTO> getEventCategoriesList(String eventUUID) throws Exception;
	
	List<EventTicketCategoriesDTO> getEventTicketCategoriesList(String eventUUID, String categoryType) throws Exception;
	
	String placeEventOrder(PlaceEventsDTO placeEventDTO) throws Exception; 
	
	List<UserConformedEventsDTO> getUserEventList(String userUUID) throws Exception;
	
	EventQRDetailsDTO getEventEntryQRDetails(String orderUUID) throws Exception;
	
	ResponseList<UserBookedEventsDTO> getUserBookedEvents(UserBookedEventsDTO userBookedEventsDTO) throws Exception;
	
	List<EventsTicketInfoDTO> getVendorEventTickets(String eventUUID) throws Exception;
	
	EventsDTO getEventDetailsByUUID(String eventUUID) throws Exception;
}
