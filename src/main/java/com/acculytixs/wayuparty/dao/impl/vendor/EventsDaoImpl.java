package com.acculytixs.wayuparty.dao.impl.vendor;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.vendor.EventsDao;
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

@Repository
@Transactional
public class EventsDaoImpl implements EventsDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public boolean isTicketNameExists(String ticketName, String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		boolean flag = true;
		
		String query = "SELECT " 
				+" tickets.id "
				+" FROM "
			    +" vendor_events_tickets tickets where tickets.ticket_name = :ticketName "
			    +" and tickets.vendor_uuid = :vendorUUID and tickets.status = '1' ";
		
		Query queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("ticketName", ticketName);
		queryObj.setParameter("vendorUUID", vendorUUID);
		BigInteger ticketId = (BigInteger) queryObj.uniqueResult();
		if(ticketId == null) {
			flag = false;
		}
		return flag;
	}

	@Override
	public EventTicketsDTO getEventTicketDetails(String ticketUUID) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<EventTicketsDTO> ticketDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" ticket.ticket_name AS ticketName, "
				+" ticket.ticket_rating AS ticketRating, "
				+" ticket.uuid AS ticketUUID "
				
			+" FROM "
			     + "vendor_events_tickets ticket where ticket.uuid = :ticketUUID and ticket.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("ticketUUID", ticketUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventTicketsDTO.class));
		ticketDTOList = queryObj.list();
		if(ticketDTOList != null && !ticketDTOList.isEmpty()) {
			return ticketDTOList.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public void saveEventTicket(VendorEventsTickets vendorEventsTickets) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorEventsTickets);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Long getTicketIdByTicketUUID(String ticketUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String query = "SELECT " 
				+" tickets.id "
				+" FROM "
			    +" vendor_events_tickets tickets "
			    +" where tickets.uuid = :ticketUUID and tickets.status = '1' ";
		
		Query queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("ticketUUID", ticketUUID);
		BigInteger eventTicketId = (BigInteger) queryObj.uniqueResult();
		if(eventTicketId != null) {
			return eventTicketId.longValue();
		}else {
			return null;
		}
		
	}

	@Override
	public List<EventTicketsDTO> getEventsTicketsList(String vendorUUID) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<EventTicketsDTO> ticketsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" ticket.ticket_name AS ticketName, "
				+" ticket.ticket_rating AS ticketRating, "
				+" ticket.uuid AS ticketUUID "
				+" FROM "
				+ "vendor_events_tickets ticket where ticket.vendor_uuid = :vendorUUID and ticket.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventTicketsDTO.class));
		ticketsDTOList = queryObj.list();
		
		return ticketsDTOList;
	}

	@Override
	public VendorEventsTickets getVendorEventTicketByUUID(String ticketUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from VendorEventsTickets ticket where ticket.uuid = :ticketUUID ";
		List<VendorEventsTickets> vendorEventsTicketsList = currentSession.createQuery(query).setString("ticketUUID", ticketUUID).list();
		VendorEventsTickets vendorEventsTickets = null;
		if (vendorEventsTicketsList != null && vendorEventsTicketsList.size() > 0) {
			vendorEventsTickets = (VendorEventsTickets) vendorEventsTicketsList.iterator().next();
		}
		return vendorEventsTickets;
	}

	@Override
	public VendorEvents getVendorEventByEventUUID(String eventUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from VendorEvents events where events.uuid = :eventUUID ";
		List<VendorEvents> vendorEventsList = currentSession.createQuery(query).setString("eventUUID", eventUUID).list();
		VendorEvents vendorEvents = null;
		if (vendorEventsList != null && vendorEventsList.size() > 0) {
			vendorEvents = (VendorEvents) vendorEventsList.iterator().next();
		}
		return vendorEvents;
	}

	@Override
	public void saveVendorEvent(VendorEvents vendorEvents) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorEvents);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EventListDTO> getEventList(String vendorUUID) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<EventListDTO> eventsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" events.event_name AS eventName, "
				+" (SELECT count(id) FROM events_order orders WHERE events.uuid = orders.event_uuid) AS bookingsOpen, "
				+" events.event_location AS eventLocation, "
				+" DAY(events.event_date) AS date, "
				+" UPPER(LEFT(DAYNAME(events.event_date),3)) AS day, "
				+" UPPER(MONTHNAME(events.event_date)) AS month, "
				+" events.event_display_image AS eventImage, "
				+" DATE_FORMAT(events.event_date, '%a %b %e %Y') AS eventDate, "
				+" events.event_host AS eventHost, "
				+" events.event_time_slots AS eventTimeSlots, "
				+" events.uuid AS eventUUID "
				+" FROM "
				+ "vendor_events events where events.vendor_uuid = :vendorUUID and events.status = '1' "
				+ "and DATEDIFF(events.event_date,CURDATE()) >= 0 ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventListDTO.class));
		eventsDTOList = queryObj.list();
		
		return eventsDTOList;
	}
	
	
	@Override
	public List<EventListDTO> getVendorEventsList(String vendorUUID) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<EventListDTO> eventsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" events.event_name AS eventName, "
				+" (SELECT count(id) FROM events_order orders WHERE events.uuid = orders.event_uuid) AS bookingsOpen, "
				+" events.event_location AS eventLocation, "
				+" DAY(events.event_date) AS date, "
				+" UPPER(LEFT(DAYNAME(events.event_date),3)) AS day, "
				+" UPPER(MONTHNAME(events.event_date)) AS month, "
				+" events.event_display_image AS eventImage, "
				+" DATE_FORMAT(events.event_date, '%a %b %e %Y') AS eventDate, "
				+" events.event_host AS eventHost, "
				+" events.event_time_slots AS eventTimeSlots, "
				+" CONVERT(CURRENT_DATE() >= events.event_date,CHAR) AS dayDiff, "
				+" events.uuid AS eventUUID "
				+" FROM "
				+ "vendor_events events where events.vendor_uuid = :vendorUUID and events.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventListDTO.class));
		eventsDTOList = queryObj.list();
		
		return eventsDTOList;
	}

	@Override
	public EventDetailsDTO getEventDetails(String eventUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<EventDetailsDTO> eventsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" events.event_name AS eventName, "
				+" events.event_type AS eventType, "
				+" events.event_banner_image AS eventImage, "
				+" events.event_address AS address, "
				+" events.music_type AS musicType, "
				+" events.event_language AS language, "
				+" DATE_FORMAT(events.event_date, '%a %b %e %Y') AS eventDate, "
				+" events.event_host AS eventHost, "
				+" events.event_time_slots AS eventTimeSlots, "
				+" events.event_tickets AS eventTickets, "
				+" (SELECT vendors.currency FROM vendors vendors WHERE vendors.uuid = events.vendor_uuid) AS currency, "
				+" events.uuid AS eventUUID "
				+" FROM "
				+ "vendor_events events where events.uuid = :eventUUID and events.status = '1' ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("eventUUID", eventUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventDetailsDTO.class));
		eventsDTOList = queryObj.list();
		if(eventsDTOList != null && !eventsDTOList.isEmpty()) {
			return eventsDTOList.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public String getTimeSlotsByEventUUID(String eventUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String query = "SELECT " 
				+" tickets.event_time_slots "
				+" FROM "
			    +" vendor_events events "
			    +" where events.uuid = :eventUUID and events.status = '1' ";
		
		Query queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("eventUUID", eventUUID);
		String timeSlot = (String) queryObj.uniqueResult();
		if(timeSlot != null) {
			return timeSlot;
		}else {
			return null;
		}
		
	}

	@Override
	public EventsInfoDTO getEventsInfo(String eventUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<EventsInfoDTO> eventsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" DATE_FORMAT(events.event_date, '%a %e %M') AS eventDate, "
				+" events.event_time_slots AS timeSlot, "
				+" events.event_tickets AS eventTickets, "
				+" (SELECT vendors.currency FROM vendors vendors WHERE vendors.uuid = events.vendor_uuid) AS currency, "
				+" events.uuid AS eventUUID "
				+" FROM "
				+ "vendor_events events where events.uuid = :eventUUID and events.status = '1' ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("eventUUID", eventUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventsInfoDTO.class));
		eventsDTOList = queryObj.list();
		if(eventsDTOList != null && !eventsDTOList.isEmpty()) {
			return eventsDTOList.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public void saveEventsOrder(EventsOrder eventsOrder) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(eventsOrder);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UserConformedEventsDTO> getUserEventList(String userUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<UserConformedEventsDTO> eventsOrderDTOList = new CopyOnWriteArrayList<UserConformedEventsDTO>();
		Query queryObj = null;
		
		query = "SELECT " 
				+" (SELECT vendor.vendor_name FROM vendors vendor WHERE vendor.uuid = orders.vendor_uuid) AS clubName, "
				+" (SELECT vendor.city FROM vendors vendor WHERE vendor.uuid = orders.vendor_uuid) AS clubLocation, "
				+" (SELECT events.event_name FROM vendor_events events WHERE events.uuid = orders.event_uuid) AS eventName, "
				+" DATE_FORMAT(orders.event_date, '%b %e %Y') AS orderDate, "
				+" DATE_FORMAT(orders.event_date, '%Y-%m-%d') AS eventOrderDate, "
				+" orders.time_slot AS timeSlot, "
				+" orders.ticket_type AS ticketType, "
				+" orders.quantity AS quantity, "
				+" orders.total_amount AS totalAmount, "
				+" orders.event_amount AS eventAmount, "
				+" orders.currency AS currency, "
				+" orders.order_code AS orderCode, "
				+" orders.uuid AS eventUUID, "
				+" (SELECT orderRatings.rating FROM place_order_ratings orderRatings WHERE orderRatings.place_order_code = orders.order_code) AS rating, "
				+" IFNULL(orders.is_user_arrived,'N') AS isUserArrived, "
				+" IFNULL(orders.qr_code,'') AS qrCode "
				
			+" FROM "
			     + "events_order orders where  orders.user_uuid = :userUUID";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("userUUID", userUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(UserConformedEventsDTO.class));
		eventsOrderDTOList = queryObj.list();
		
		return eventsOrderDTOList;
		
	
	}

	@Override
	public EventQRDetailsDTO getEventEntryQRDetails(String orderUUID) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<EventQRDetailsDTO> eventsOrderDTOList = new CopyOnWriteArrayList<EventQRDetailsDTO>();
		Query queryObj = null;
		
		query = "SELECT " 
				+" (SELECT vendor.vendor_name FROM vendors vendor WHERE vendor.uuid = orders.vendor_uuid) AS clubName, "
				+" (SELECT vendor.city FROM vendors vendor WHERE vendor.uuid = orders.vendor_uuid) AS clubLocation, "
				+" (SELECT events.event_name FROM vendor_events events WHERE events.uuid = orders.event_uuid) AS eventName, "
				+" DATE_FORMAT(orders.event_date, '%b %e %Y') AS eventDate, "
				+" orders.time_slot AS timeslot, "
				+" orders.ticket_type AS ticketType, "
				+" orders.category AS ticketCategory, "
				+" orders.order_code AS orderCode, "
				+" orders.quantity AS quantity, "
				+" orders.total_amount AS totalAmount, "
				+" orders.currency AS currency "
				
			+" FROM "
			     + "events_order orders where  orders.uuid = :orderUUID";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("orderUUID", orderUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventQRDetailsDTO.class));
		eventsOrderDTOList = queryObj.list();
		if(eventsOrderDTOList != null && !eventsOrderDTOList.isEmpty()) {
			return eventsOrderDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public ResponseList<UserBookedEventsDTO> getUserBookedEvents(UserBookedEventsDTO userBookedEventsDTO) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<BigInteger> countlist = null;
		Integer filterCount = 0;
		Query queryObj = null;
		List<UserBookedEventsDTO> usersList = null;
		String querySeachStr =  "";
		ResponseList<UserBookedEventsDTO> response = new ResponseList<>();
		
		StringBuilder query1 = new StringBuilder("");
		if(userBookedEventsDTO.getSearchValue() != null && !"".equalsIgnoreCase(userBookedEventsDTO.getSearchValue())) {
			 query1.append(" Q1.userName like '%" + userBookedEventsDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.timeSlot like '%" + userBookedEventsDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.ticketType like '%" + userBookedEventsDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.ticketCategory like '%" + userBookedEventsDTO.getSearchValue() + "%' ")
				.append(" OR Q1.orderCode like '%" + userBookedEventsDTO.getSearchValue() + "%' ");
			 
		}
		
		if(query1.toString() != null && !query1.toString().isEmpty()){
			querySeachStr = " where "+"("+query1.toString()+")";
		}
		
		
		try {
			 query = "SELECT Q1.* " + 
			 		 "FROM (" + 
					 "SELECT " + 
					"(SELECT CONCAT (u.first_name,' ',IFNULL(u.last_name,'')) FROM user u WHERE u.uuid = orders.user_uuid) AS userName, "+
					"DATE_FORMAT(orders.event_date, '%b %e %Y') AS eventDate, " +
					"orders.time_slot AS timeSlot,"+
					"orders.ticket_type AS ticketType, "+
					"orders.category AS ticketCategory, " +
					"orders.order_code AS orderCode, " +
					"orders.quantity AS quantity, " +
					"orders.total_amount AS totalAmount, " +
					"orders.currency AS currency, " +
					"orders.uuid AS orderUUID " +
					"FROM " + 
					"events_order orders " + 
					"WHERE orders.vendor_uuid = :vendorUUID AND orders.event_uuid = :eventUUID " + 
					"ORDER BY DATE_FORMAT(orders.created_date ,'%d') ASC)Q1 "+querySeachStr;
			 
		   
			queryObj = currentSession.createSQLQuery(query);
			queryObj.setParameter("vendorUUID", userBookedEventsDTO.getVendorUUID());
			queryObj.setParameter("eventUUID", userBookedEventsDTO.getEventUUID());
			queryObj.setResultTransformer(Transformers.aliasToBean(UserBookedEventsDTO.class));
			queryObj.setFirstResult(userBookedEventsDTO.getStart());
			queryObj.setMaxResults(userBookedEventsDTO.getLength());
			usersList = queryObj.list();
			
			String  countQuery = "SELECT COUNT(*) AS TOTAL" + 
					" FROM" + 
					"(" + query +
					")Q2;" ;
			
			countlist = currentSession.createSQLQuery(countQuery).setParameter("vendorUUID", userBookedEventsDTO.getVendorUUID()).setParameter("eventUUID", userBookedEventsDTO.getEventUUID()).list();
			filterCount = countlist.iterator().next().intValue();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			int totalCount = 0;

			
			if (userBookedEventsDTO != null) {
				if(countlist != null && !countlist.isEmpty()) {
					totalCount = countlist.iterator().next().intValue();
				}
			} 
			response.setRecordsTotal(totalCount);
			
			if(query1.toString() != null && !query1.toString().isEmpty()){
				response.setRecordsFiltered(filterCount);
			}else {
				response.setRecordsFiltered(totalCount);
			}
		
			response.setData(usersList);
			return response;
	}

	@Override
	public EventsDTO getEventDetailsByUUID(String eventUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<EventsDTO> eventsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" events.event_name AS eventName, "
				+" events.event_type AS eventType, "
				+" events.event_location AS eventLocation, "
				+" events.event_host AS eventHost, "
				+" events.music_type AS musicType, "
				+" IFNULL(DATE_FORMAT(events.event_date, '%d/%m/%Y'),'') AS eventDate, "
				+" events.event_time_slots AS eventTimeSlots, "
				+" events.event_language AS eventLanguage, "
				+" events.event_banner_image AS bannerImage, "
				+" events.event_display_image AS displayImage, "
				+" events.event_address AS eventAddress, "
				+" events.event_tickets AS eventTickets " 
				+" FROM "
				+ "vendor_events events where events.uuid = :eventUUID and events.status = '1' ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("eventUUID", eventUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(EventsDTO.class));
		eventsDTOList = queryObj.list();
		if(eventsDTOList != null && !eventsDTOList.isEmpty()) {
			return eventsDTOList.get(0);
		}else {
			return null;
		}
		
	}

}
