package com.acculytixs.wayuparty.rest.events;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acculytixs.wayuparty.annotations.Validator;
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
import com.acculytixs.wayuparty.enums.Result;
import com.acculytixs.wayuparty.services.vendor.EventsService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.Response;
import com.acculytixs.wayuparty.util.ResponseList;

@RestController
public class EventsRestController {
	
	@Autowired
	EventsService eventService;

	
	@RequestMapping(value = { "/validateTicket", "/rest/validateTicket" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateMenuName(
			@RequestParam(value = "ticketName", required = true) String ticketName,
			@RequestParam(value = "ticketUUID", required = false) String ticketUUID,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) throws Exception {
		Response<String> response = new Response<>();
		try {
			boolean flag;
			if (StringUtils.isNotBlank(ticketUUID)) {
				EventTicketsDTO eventTicketsDTO = eventService.getEventTicketDetails(ticketUUID);
				if (eventTicketsDTO.getTicketName().toLowerCase().trim()
						.equalsIgnoreCase(ticketName.toLowerCase().trim())) {
					response.setResponse(Result.VALID_DATA.name());
				} else {
					flag = eventService.isTicketNameExists(ticketName, vendorUUID);
					if (flag == true) {
						response.setResponse(Result.INVALID_DATA.name());
					} else {
						response.setResponse(Result.VALID_DATA.name());
					}
				}
			} else {
				flag = eventService.isTicketNameExists(ticketName, vendorUUID);
				if (flag == true) {
					response.setResponse(Result.INVALID_DATA.name());
				} else {
					response.setResponse(Result.VALID_DATA.name());
				}
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
	public Response<String> saveTicket(EventTicketsDTO eventTicketsDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(eventTicketsDTO)) {
				eventService.saveEventTicket(eventTicketsDTO);
				response.setResponse(Result.SUCCESS.name());
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = "/getTicketDetailsByTicketUUID", method = RequestMethod.GET)
	public Response<EventTicketsDTO> getTicketDetailsByTicketUUID(@RequestParam(value = "ticketUUID", required = true) String ticketUUID,
			HttpServletRequest request) throws Exception {

		Response<EventTicketsDTO> response = new Response<EventTicketsDTO>();
		try {

			EventTicketsDTO eventTicketsDTO = eventService.getEventTicketDetails(ticketUUID);
			response.setObject(eventTicketsDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/getVendorEventTicketsList", "/rest/getVendorEventTicketsList" }, method = RequestMethod.GET)
	public ResponseList<EventTicketsDTO> getVendorEventTicketsList(@RequestParam(value="vendorUUID", required=true) String vendorUUID,
			 HttpServletRequest request) throws Exception {
		
		ResponseList<EventTicketsDTO> eventTicketsDTOList = new ResponseList<EventTicketsDTO>();
		try {
			List<EventTicketsDTO> eventTicketsList = eventService.getEventsTicketsList(vendorUUID);
			eventTicketsDTOList.setData(eventTicketsList);
			eventTicketsDTOList.setResponse(Result.SUCCESS.name());
		}catch (Exception e) {
			eventTicketsDTOList.setResponse(Result.AWKWARD.name());
			eventTicketsDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return eventTicketsDTOList;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/saveVendorEvents", method = RequestMethod.POST)
	public Response<String> saveVendorEvents(EventsDTO eventsDTO,
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(eventsDTO)) {
				String result = eventService.saveVendorEvent(eventsDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
				response.setResponse(Result.SUCCESS.name());
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/getVendorEvents", "/ws/getVendorEvents" }, method = RequestMethod.GET)
	public ResponseList<EventListDTO> getVendorEvents(@RequestParam(value="vendorUUID", required=true) String vendorUUID,
			 HttpServletRequest request) throws Exception {
		
		ResponseList<EventListDTO> eventsDTOList = new ResponseList<EventListDTO>();
		try {
			List<EventListDTO> eventsList = eventService.getEventList(vendorUUID);
			eventsDTOList.setData(eventsList);
			eventsDTOList.setResponse(Result.SUCCESS.name());
			eventsDTOList.setResponseMessage(Result.SUCCESS.getValue());
		}catch (Exception e) {
			eventsDTOList.setResponse(Result.AWKWARD.name());
			eventsDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return eventsDTOList;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getVendorEventsList", method = RequestMethod.GET)
	public ResponseList<EventListDTO> getVendorEventsList(@RequestParam(value="vendorUUID", required=true) String vendorUUID,
			 HttpServletRequest request) throws Exception {
		
		ResponseList<EventListDTO> eventsDTOList = new ResponseList<EventListDTO>();
		try {
			List<EventListDTO> eventsList = eventService.getVendorEventsList(vendorUUID);
			eventsDTOList.setData(eventsList);
			eventsDTOList.setResponse(Result.SUCCESS.name());
			eventsDTOList.setResponseMessage(Result.SUCCESS.getValue());
		}catch (Exception e) {
			eventsDTOList.setResponse(Result.AWKWARD.name());
			eventsDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return eventsDTOList;
	}
	
	
	@RequestMapping(value = { "/getEventDetails", "/ws/getEventDetails" }, method = RequestMethod.GET)
	public Response<EventDetailsDTO> getEventDetails(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			HttpServletRequest request) throws Exception {

		Response<EventDetailsDTO> response = new Response<EventDetailsDTO>();
		try {

			EventDetailsDTO eventDetailsDTO = eventService.getEventDetails(eventUUID);
			response.setObject(eventDetailsDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/getEventTimeSlots", "/ws/getEventTimeSlots" }, method = RequestMethod.GET)
	public Response<EventTimeSlotsDTO> getEventTimeSlots(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			HttpServletRequest request) throws Exception {

		Response<EventTimeSlotsDTO> response = new Response<EventTimeSlotsDTO>();
		try {

			EventTimeSlotsDTO eventTimeSlotsDTO = eventService.getEventTimeSlots(eventUUID);
			response.setObject(eventTimeSlotsDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = { "/getEventCategories", "/ws/getEventCategories" }, method = RequestMethod.GET)
	public ResponseList<EventCategoriesDTO> getEventCategories(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			HttpServletRequest request) throws Exception {

		ResponseList<EventCategoriesDTO> response = new ResponseList<EventCategoriesDTO>();
		try {

			List<EventCategoriesDTO> eventCategoryList = eventService.getEventCategoriesList(eventUUID);
			response.setData(eventCategoryList);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = { "/getEventTickets", "/ws/getEventTickets" }, method = RequestMethod.GET)
	public ResponseList<EventTicketCategoriesDTO> getEventTickets(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			@RequestParam(value = "categoryType", required = true) String categoryType,
			HttpServletRequest request) throws Exception {

		ResponseList<EventTicketCategoriesDTO> response = new ResponseList<EventTicketCategoriesDTO>();
		try {

			List<EventTicketCategoriesDTO> eventTicketsList = eventService.getEventTicketCategoriesList(eventUUID, categoryType);
			response.setData(eventTicketsList);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = "/saveEventOrder", method = RequestMethod.POST)
	public Response<String> saveEventOrder(PlaceEventsDTO placeEventsDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {
			if (Validator.validate(placeEventsDTO)) {
				String result = eventService.placeEventOrder(placeEventsDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}
		
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	

	@RequestMapping(value = "/rest/placeEventOrder", method = RequestMethod.POST)
	public Response<String> placeEventOrder(@RequestBody PlaceEventsDTO placeEventsDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {
			if (Validator.validate(placeEventsDTO)) {
				String result = eventService.placeEventOrder(placeEventsDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}
		
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/getEventQRDetails", "/ws/getEventQRDetails" }, method = RequestMethod.GET)
	public Response<EventQRDetailsDTO> getEventQRDetails(@RequestParam(value = "orderUUID", required = true) String orderUUID,
			HttpServletRequest request) throws Exception {

		Response<EventQRDetailsDTO> response = new Response<EventQRDetailsDTO>();
		try {

			EventQRDetailsDTO eventQRDetailsDTO = eventService.getEventEntryQRDetails(orderUUID);
			response.setObject(eventQRDetailsDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getUserBookedEventsList", method = RequestMethod.GET)
	public ResponseList<UserBookedEventsDTO> getUserBookedEventsList(UserBookedEventsDTO userBookedEventsDTO, HttpServletRequest request) {
		ResponseList<UserBookedEventsDTO> eventsOrdersResponseList = new ResponseList<UserBookedEventsDTO>();

		try {
			
			String[] tableColumns = { "userName", "ticketType", "ticketCategory",  "totalAmount" , "orderCode", "orderActions" };
			int sortColumnIndex = 1; // by default sorting using service order date
			String sortColumnOrder = "desc"; // by default assending order
			if (userBookedEventsDTO.getOrder() != null && !userBookedEventsDTO.getOrder().isEmpty()) {
				sortColumnIndex = Integer.parseInt(userBookedEventsDTO.getOrder().get(0).get("column"));
				sortColumnOrder = userBookedEventsDTO.getOrder().get(0).get("dir");
			}
			userBookedEventsDTO.setSortColumn(tableColumns[sortColumnIndex]);
			userBookedEventsDTO.setSortOrder(sortColumnOrder);
			eventsOrdersResponseList = eventService.getUserBookedEvents(userBookedEventsDTO);
			eventsOrdersResponseList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			eventsOrdersResponseList.setResponse(Result.AWKWARD.name());
			eventsOrdersResponseList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return eventsOrdersResponseList;
	}
	
	
	@RequestMapping(value = { "/getVendorEventTickets", "/ws/getVendorEventTickets" }, method = RequestMethod.GET)
	public ResponseList<EventsTicketInfoDTO> getVendorEventTickets(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			HttpServletRequest request) throws Exception {

		ResponseList<EventsTicketInfoDTO> response = new ResponseList<EventsTicketInfoDTO>();
		try {

			List<EventsTicketInfoDTO> eventCategoryList = eventService.getVendorEventTickets(eventUUID);
			response.setData(eventCategoryList);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getSavedEventDetails", method = RequestMethod.GET)
	public Response<EventsDTO> getSavedEventDetails(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			HttpServletRequest request) throws Exception {

		Response<EventsDTO> response = new Response<EventsDTO>();
		try {

			EventsDTO eventsDTO = eventService.getEventDetailsByUUID(eventUUID);
			response.setObject(eventsDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
}
