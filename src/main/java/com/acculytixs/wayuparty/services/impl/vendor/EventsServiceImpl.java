package com.acculytixs.wayuparty.services.impl.vendor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.user.UserCartDao;
import com.acculytixs.wayuparty.dao.user.UserDao;
import com.acculytixs.wayuparty.dao.vendor.EventsDao;
import com.acculytixs.wayuparty.dao.vendor.VendorDao;
import com.acculytixs.wayuparty.dto.events.EventCategoriesDTO;
import com.acculytixs.wayuparty.dto.events.EventDetailsDTO;
import com.acculytixs.wayuparty.dto.events.EventListDTO;
import com.acculytixs.wayuparty.dto.events.EventQRDetailsDTO;
import com.acculytixs.wayuparty.dto.events.EventTicketCategoriesDTO;
import com.acculytixs.wayuparty.dto.events.EventTicketsDTO;
import com.acculytixs.wayuparty.dto.events.EventTimeSlotsDTO;
import com.acculytixs.wayuparty.dto.events.EventsDTO;
import com.acculytixs.wayuparty.dto.events.EventsInfoDTO;
import com.acculytixs.wayuparty.dto.events.EventsTicketInfoDTO;
import com.acculytixs.wayuparty.dto.events.PlaceEventsDTO;
import com.acculytixs.wayuparty.dto.events.UserBookedEventsDTO;
import com.acculytixs.wayuparty.dto.events.UserConformedEventsDTO;
import com.acculytixs.wayuparty.dto.services.TimeSlotInfo;
import com.acculytixs.wayuparty.dto.services.TimeSlotSchedulerInfo;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.entity.email.EmailMessages;
import com.acculytixs.wayuparty.entity.services.PlaceOrderTransactions;
import com.acculytixs.wayuparty.entity.vendor.EventsOrder;
import com.acculytixs.wayuparty.entity.vendor.VendorEvents;
import com.acculytixs.wayuparty.entity.vendor.VendorEventsTickets;
import com.acculytixs.wayuparty.services.vendor.EventsService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.EmailUtil;
import com.acculytixs.wayuparty.util.FileInfo;
import com.acculytixs.wayuparty.util.FileUploadUtil;
import com.acculytixs.wayuparty.util.RandomCodeHelper;
import com.acculytixs.wayuparty.util.ResponseList;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class EventsServiceImpl implements EventsService{

	@Autowired
	EventsDao eventsDao;
	
	@Autowired
	VendorDao vendorDao;
	
	@Value("${static.path}")
	private String staticPath;
	
	@Value("${appUrl}")
	private String appUrl;
	
	@Autowired
	UserCartDao userCartDao;
	
	@Autowired
	UserDao userDao;
	
	@Value("${fromEmailAddress}")
	private String fromEmailAddress;

	@Value("${fromEmailPassword}")
	private String fromEmailPassword;

	@Value("${smtphost}")
	private String smtphost;

	@Value("${port}")
	private Integer port;

	@Autowired
	EmailUtil emailUtil;
	
	@Value("${textLocalAPIKey}")
	private String textLocalAPIKey;
	
	@Override
	public boolean isTicketNameExists(String ticketName, String vendorUUID) throws Exception {
		return eventsDao.isTicketNameExists(ticketName, vendorUUID);
	}

	@Override
	public EventTicketsDTO getEventTicketDetails(String ticketUUID) throws Exception {
		return eventsDao.getEventTicketDetails(ticketUUID);
	}

	@Override
	public void saveEventTicket(EventTicketsDTO eventTicketsDTO) throws Exception {
		VendorEventsTickets vendorEventsTickets = null;
		if(StringUtils.isNotBlank(eventTicketsDTO.getTicketUUID())) {
			vendorEventsTickets = eventsDao.getVendorEventTicketByUUID(eventTicketsDTO.getTicketUUID());
		}else {
			vendorEventsTickets = new VendorEventsTickets();
			vendorEventsTickets.setUuid(RandomCodeHelper.generateRandomUUID());
			vendorEventsTickets.setStatus(1);
			vendorEventsTickets.setCreatedDate(new Date());
			vendorEventsTickets.setVendorUUID(eventTicketsDTO.getVendorUUID());
		}
		
		vendorEventsTickets.setTicketName(eventTicketsDTO.getTicketName());
		vendorEventsTickets.setTicketRating(eventTicketsDTO.getTicketRating());
		eventsDao.saveEventTicket(vendorEventsTickets);		
	}

	@Override
	public List<EventTicketsDTO> getEventsTicketsList(String vendorUUID) throws Exception {
		return eventsDao.getEventsTicketsList(vendorUUID);
	}

	@Override
	public String saveVendorEvent(EventsDTO eventsDTO) throws Exception {
		
		String queryExecutionStatus = null;
		try {
			
			VendorEvents vendorEvents = new VendorEvents();
			
			if(StringUtils.isNotBlank(eventsDTO.getEventUUID())) {
				vendorEvents = eventsDao.getVendorEventByEventUUID(eventsDTO.getEventUUID());
			}else {
				vendorEvents.setCreatedDate(new Date());
				vendorEvents.setUuid(RandomCodeHelper.generateRandomUUID());
			}
			
			vendorEvents.setVendorUUID(eventsDTO.getVendorUUID());
			vendorEvents.setEventName(eventsDTO.getEventName());
			vendorEvents.setEventType(eventsDTO.getEventType());
			vendorEvents.setMusicType(eventsDTO.getMusicType());
			vendorEvents.setEventLocation(eventsDTO.getEventLocation());
			vendorEvents.setEventHost(eventsDTO.getEventHost());
			
			Date eventDate = new SimpleDateFormat("dd/MM/yyyy").parse(eventsDTO.getEventDate());  
			vendorEvents.setEventDate(eventDate);
		
			if (eventsDTO.getTimeSchedulerInfo() != null && !eventsDTO.getTimeSchedulerInfo().isEmpty()) {
				JSONArray array = new JSONArray();
				for(TimeSlotSchedulerInfo timeSchedulerInfo : eventsDTO.getTimeSchedulerInfo()) {
					if(StringUtils.isNotBlank(timeSchedulerInfo.getStartTime()) && StringUtils.isNotBlank(timeSchedulerInfo.getEndTime())) {
						JSONObject documentItem = new JSONObject();
						documentItem.put("startTime", timeSchedulerInfo.getStartTime());
						documentItem.put("endTime", timeSchedulerInfo.getEndTime());
						array.put(documentItem);
					}
				}
				String timeSlotsSchedulerJsonArry = array.toString();
				vendorEvents.setEventTimeSlots(timeSlotsSchedulerJsonArry);
			}
			
			vendorEvents.setEventLanguage(eventsDTO.getEventLanguage());
			vendorEvents.setEventAddress(eventsDTO.getEventAddress());
			
			
			if (eventsDTO.getFileInfo() != null && !eventsDTO.getFileInfo().isEmpty()) {
				
				FileInfo displayImageFileInfo = eventsDTO.getFileInfo().get(0);
				if(displayImageFileInfo.getFileURL() != null) {
					vendorEvents.setEventDisplayImage(displayImageFileInfo.getFileURL().replaceAll(" ", "_"));
					try {
						FileUploadUtil.moveFile(staticPath, displayImageFileInfo, "vendor_events_images");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(eventsDTO.getFileInfo().size() > 1) {
					FileInfo bannerImageFileInfo = eventsDTO.getFileInfo().get(1);
					if(bannerImageFileInfo.getFileURL() != null) {
						vendorEvents.setEventBannerImage(bannerImageFileInfo.getFileURL().replaceAll(" ", "_"));
						try {
							FileUploadUtil.moveFile(staticPath, bannerImageFileInfo, "vendor_events_images");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			
			if (eventsDTO.getTicketItemsList() != null && !eventsDTO.getTicketItemsList().isEmpty()) {
				JSONArray ticketsArray = new JSONArray();
				for(EventsTicketInfoDTO eventsTicketInfoDTO : eventsDTO.getTicketItemsList()) {
					
					if(eventsTicketInfoDTO != null && StringUtils.isNotBlank(eventsTicketInfoDTO.getTicketName())) {
						JSONObject stagItem = new JSONObject();
						stagItem.put("ticketName", eventsTicketInfoDTO.getTicketName());
						stagItem.put("entryType", "Stag");
						stagItem.put("ticketAmount", eventsTicketInfoDTO.getStagTicketCost());
						stagItem.put("bookingQuantity", eventsTicketInfoDTO.getStagMaxBooking());
						stagItem.put("categoryType", "stag");
						
						JSONObject coupleItem = new JSONObject();
						coupleItem.put("ticketName", eventsTicketInfoDTO.getTicketName());
						coupleItem.put("entryType", "Couple");
						coupleItem.put("ticketAmount", eventsTicketInfoDTO.getCoupleTicketCost());
						coupleItem.put("bookingQuantity", eventsTicketInfoDTO.getCoupleMaxBooking());
						coupleItem.put("categoryType", "couple");
						
						JSONObject singleLadyItem = new JSONObject();
						singleLadyItem.put("ticketName", eventsTicketInfoDTO.getTicketName());
						singleLadyItem.put("entryType", "Single Lady");
						singleLadyItem.put("ticketAmount", eventsTicketInfoDTO.getSingleLadyTicketCost());
						singleLadyItem.put("bookingQuantity", eventsTicketInfoDTO.getSingleLadyMaxBooking());
						singleLadyItem.put("categoryType", "singleLady");
						
						JSONObject totalTicketsItem = new JSONObject();
						totalTicketsItem.put("ticketName", eventsTicketInfoDTO.getTicketName());
						totalTicketsItem.put("totalTickets", eventsTicketInfoDTO.getTotalTickets());
						
						ticketsArray.put(stagItem);
						ticketsArray.put(coupleItem);
						ticketsArray.put(singleLadyItem);
						ticketsArray.put(totalTicketsItem);
					}
						
				}
				String ticketsSchedulerJsonArry = ticketsArray.toString();
				vendorEvents.setEventTickets(ticketsSchedulerJsonArry);
			}
			
			vendorEvents.setStatus(1);
			eventsDao.saveVendorEvent(vendorEvents);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	}

	@Override
	public List<EventListDTO> getEventList(String vendorUUID) throws Exception {
		List<EventListDTO> eventsDTOList = new CopyOnWriteArrayList<EventListDTO>();
		eventsDTOList =  eventsDao.getEventList(vendorUUID);
		if(eventsDTOList != null & !eventsDTOList.isEmpty()) {
			for(EventListDTO eventListDTO : eventsDTOList) {
				if(StringUtils.isNotBlank(eventListDTO.getEventTimeSlots())) {
					JSONParser parser = new JSONParser();
					String startTime = null;
					String endTime = null;
						org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(eventListDTO.getEventTimeSlots());
						  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
							    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
							   if(startTime == null) {
								   startTime = ((String) jsonObject.get("startTime")).trim();
							   }
							 endTime = ((String) jsonObject.get("endTime")).trim();
						 }
					
						  eventListDTO.setTime(startTime+" to "+endTime);	  
				}
			}
		}
		return eventsDTOList;
	}

	
	@Override
	public List<EventListDTO> getVendorEventsList(String vendorUUID) throws Exception {
		List<EventListDTO> eventsDTOList = new CopyOnWriteArrayList<EventListDTO>();
		eventsDTOList =  eventsDao.getVendorEventsList(vendorUUID);
		if(eventsDTOList != null & !eventsDTOList.isEmpty()) {
			for(EventListDTO eventListDTO : eventsDTOList) {
				if(StringUtils.isNotBlank(eventListDTO.getEventTimeSlots())) {
					JSONParser parser = new JSONParser();
					String startTime = null;
					String endTime = null;
						org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(eventListDTO.getEventTimeSlots());
						  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
							    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
							   if(startTime == null) {
								   startTime = ((String) jsonObject.get("startTime")).trim();
							   }
							 endTime = ((String) jsonObject.get("endTime")).trim();
						 }
					
						  eventListDTO.setTime(startTime+" to "+endTime);	  
				}
			}
		}
		return eventsDTOList;
	}

	@Override
	public EventDetailsDTO getEventDetails(String eventUUID) throws Exception {
		EventDetailsDTO eventDetialsDTO =  eventsDao.getEventDetails(eventUUID);
		
		if(StringUtils.isNotBlank(eventDetialsDTO.getEventTimeSlots())) {
			JSONParser parser = new JSONParser();
			String startTime = null;
			String endTime = null;
				org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(eventDetialsDTO.getEventTimeSlots());
				  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
					    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
					   if(startTime == null) {
						   startTime = ((String) jsonObject.get("startTime")).trim();
					   }
					 endTime = ((String) jsonObject.get("endTime")).trim();
				 }
				  
					  SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
					  SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
				      Date startDate = parseFormat.parse(startTime);
					  Date endDate = parseFormat.parse(endTime); 
					  String eventStartTime = displayFormat.format(startDate); 
					  String eventEndTime = displayFormat.format(endDate); 
					  LocalTime t1 = LocalTime.parse(eventStartTime);
					  LocalTime t2 = LocalTime.parse(eventEndTime); 
					  Duration diff = Duration.between(t1,t2); 
					  eventDetialsDTO.setDuration(String.valueOf(diff.toHours()+" hours"));
					  
					  List<Integer> costList = new ArrayList<Integer>();
					  org.json.simple.JSONArray amountJsonArray = (org.json.simple.JSONArray)parser.parse(eventDetialsDTO.getEventTickets());
					  for (Iterator it = amountJsonArray.iterator(); it.hasNext();) {
						    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
						    if(jsonObject.containsKey("ticketAmount")) {
						    	String ticketAmount = ((String) jsonObject.get("ticketAmount")).trim();
								 costList.add(Integer.valueOf(ticketAmount));
						    }
					 }
					 
					 if(costList != null && !costList.isEmpty()) {
						 Integer minimunAmount = Collections.min(costList); 
						 StringBuilder minumumAmountBuilder = new StringBuilder();
						 minumumAmountBuilder.append(eventDetialsDTO.getCurrency()).append(" ").append(String.valueOf(minimunAmount)).append(" Onwards");
						 eventDetialsDTO.setMinimumStartingAmount(minumumAmountBuilder.toString());
					 }
					 
			
		}
		
		return eventDetialsDTO;
	}

	@Override
	public EventTimeSlotsDTO getEventTimeSlots(String eventUUID) throws Exception {
		EventTimeSlotsDTO eventTimeSlotsDTO = new EventTimeSlotsDTO();
		EventsInfoDTO eventsInfoDTO = eventsDao.getEventsInfo(eventUUID);
		if(eventsInfoDTO != null) {
			eventTimeSlotsDTO.setEventDate(eventsInfoDTO.getEventDate());
			eventTimeSlotsDTO.setEventUUID(eventsInfoDTO.getEventUUID());
			List<TimeSlotInfo> timeslotsList = Collections.<TimeSlotInfo>emptyList();
			if(StringUtils.isNotBlank(eventsInfoDTO.getTimeSlot())) {
				timeslotsList = new CopyOnWriteArrayList<TimeSlotInfo>();
				JSONParser parser = new JSONParser();
					org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(eventsInfoDTO.getTimeSlot());
					  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
						    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
						   
						    String startTime = ((String) jsonObject.get("startTime")).trim();
						    TimeSlotInfo timeSlotSchedulerInfo = new TimeSlotInfo();
						    timeSlotSchedulerInfo.setTimeSlot(startTime);
						    timeslotsList.add(timeSlotSchedulerInfo);
					 }
					  eventTimeSlotsDTO.setTimeSlots(timeslotsList);
			}
		}
		return eventTimeSlotsDTO;
	}

	@Override
	public List<EventCategoriesDTO> getEventCategoriesList(String eventUUID) throws Exception {
		EventsInfoDTO eventsInfoDTO = eventsDao.getEventsInfo(eventUUID);
		 List<Integer> stagCostList = new ArrayList<Integer>();
		 List<Integer> coupleCostEList = new ArrayList<Integer>();
		 List<Integer> singleCostLadyList = new ArrayList<Integer>();
		 JSONParser parser = new JSONParser();
		  org.json.simple.JSONArray amountJsonArray = (org.json.simple.JSONArray)parser.parse(eventsInfoDTO.getEventTickets());
		  for (Iterator it = amountJsonArray.iterator(); it.hasNext();) {
			    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
			    if(jsonObject.containsKey("ticketAmount") && jsonObject.containsKey("entryType")) {
			    	  String ticketAmount = ((String) jsonObject.get("ticketAmount")).trim();
						 String entryType = ((String) jsonObject.get("entryType")).trim();
						 if(entryType.equalsIgnoreCase("Stag")) {
							 stagCostList.add(Integer.valueOf(ticketAmount));
						 }else if(entryType.equalsIgnoreCase("Couple")) {
							 coupleCostEList.add(Integer.valueOf(ticketAmount));
						 }else if(entryType.equalsIgnoreCase("Single Lady")) {
							 singleCostLadyList.add(Integer.valueOf(ticketAmount));
						 }
			    }
		 }
		  
		  List<EventCategoriesDTO> eventCategoriesDTOList = new CopyOnWriteArrayList<EventCategoriesDTO>();
		  EventCategoriesDTO stagEntryDTO = new EventCategoriesDTO();
		  stagEntryDTO.setCategoryName("Stag");
		  stagEntryDTO.setMinimumCost(Collections.min(stagCostList));
		  stagEntryDTO.setCategoryType("stag");
		  stagEntryDTO.setCurrency(eventsInfoDTO.getCurrency());
		  stagEntryDTO.setEventUUID(eventsInfoDTO.getEventUUID());
		  eventCategoriesDTOList.add(stagEntryDTO);
		  
		  
		  EventCategoriesDTO coupleEntryDTO = new EventCategoriesDTO();
		  coupleEntryDTO.setCategoryName("Couple");
		  coupleEntryDTO.setMinimumCost(Collections.min(coupleCostEList));
		  coupleEntryDTO.setCategoryType("couple");
		  coupleEntryDTO.setCurrency(eventsInfoDTO.getCurrency());
		  coupleEntryDTO.setEventUUID(eventsInfoDTO.getEventUUID());
		  eventCategoriesDTOList.add(coupleEntryDTO);
		  
		  EventCategoriesDTO singleLadyEntryDTO = new EventCategoriesDTO();
		  singleLadyEntryDTO.setCategoryName("Single Lady");
		  singleLadyEntryDTO.setMinimumCost(Collections.min(singleCostLadyList));
		  singleLadyEntryDTO.setCategoryType("singleLady");
		  singleLadyEntryDTO.setCurrency(eventsInfoDTO.getCurrency());
		  singleLadyEntryDTO.setEventUUID(eventsInfoDTO.getEventUUID());
		  eventCategoriesDTOList.add(singleLadyEntryDTO);
		  
		return eventCategoriesDTOList;
	}

	@Override
	public List<EventTicketCategoriesDTO> getEventTicketCategoriesList(String eventUUID, String categoryType)
			throws Exception {
		
		EventsInfoDTO eventsInfoDTO = eventsDao.getEventsInfo(eventUUID);
		List<EventTicketCategoriesDTO> eventTicketCategoriesList = new CopyOnWriteArrayList<EventTicketCategoriesDTO>();
		JSONParser parser = new JSONParser();
		  org.json.simple.JSONArray amountJsonArray = (org.json.simple.JSONArray)parser.parse(eventsInfoDTO.getEventTickets());
		  for (Iterator it = amountJsonArray.iterator(); it.hasNext();) {
			    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
			    
			  if(jsonObject.containsKey("categoryType")) {
				 String ticketCategoryType = ((String) jsonObject.get("categoryType")).trim();
				 if(categoryType.equalsIgnoreCase(ticketCategoryType)) {
					 EventTicketCategoriesDTO eventTicketCategoriesDTO = new EventTicketCategoriesDTO();
						 String ticketAmount = ((String) jsonObject.get("ticketAmount")).trim();
						 String ticketType = ((String) jsonObject.get("ticketName")).trim();
						 String bookingQuantity = ((String) jsonObject.get("bookingQuantity")).trim();
						 eventTicketCategoriesDTO.setTicketType(ticketType);
						 eventTicketCategoriesDTO.setTicketAmount(Integer.valueOf(ticketAmount));
						 eventTicketCategoriesDTO.setCurrency(eventsInfoDTO.getCurrency());
						 eventTicketCategoriesDTO.setMaxBookingAllowed(Integer.valueOf(bookingQuantity));
						 eventTicketCategoriesDTO.setEventUUID(eventsInfoDTO.getEventUUID());
						 eventTicketCategoriesList.add(eventTicketCategoriesDTO);
					 
				 }
			    }
				 
		 }
		  
		return eventTicketCategoriesList;
	}

	@Override
	public String placeEventOrder(PlaceEventsDTO placeEventDTO) throws Exception {
		
		String queryExecutionStatus = null;
		
		try {
		EventsOrder eventsOrder = new EventsOrder();
		eventsOrder.setVendorUUID(placeEventDTO.getVendorUUID());
		eventsOrder.setEventUUID(placeEventDTO.getEventUUID());
		eventsOrder.setUserUUID(placeEventDTO.getUserUUID());
		String orderCode = RandomCodeHelper.generateRandomOrderCode();
		eventsOrder.setOrderCode(orderCode);
		eventsOrder.setTicketType(placeEventDTO.getTicketType());
		if(placeEventDTO.getCategoryType().equalsIgnoreCase("stag") || placeEventDTO.getCategoryType().equalsIgnoreCase("Stag")) {
			eventsOrder.setCategory("Stag");
		}
		
		if(placeEventDTO.getCategoryType().equalsIgnoreCase("couple") || placeEventDTO.getCategoryType().equalsIgnoreCase("Couple")) {
			eventsOrder.setCategory("Couple");
		}
		
		if(placeEventDTO.getCategoryType().equalsIgnoreCase("singleLady") || placeEventDTO.getCategoryType().equalsIgnoreCase("Single Lady")) {
			eventsOrder.setCategory("Single Lady");
		}
		eventsOrder.setTimeSlot(placeEventDTO.getTimeslot());
		eventsOrder.setQuantity(Integer.valueOf(placeEventDTO.getQuantity()));
		eventsOrder.setEventAmount(Double.valueOf(placeEventDTO.getTicketAmount()));
		Double orderAmount = Double.valueOf(placeEventDTO.getTicketAmount()) * Integer.valueOf(placeEventDTO.getQuantity());
		eventsOrder.setTotalAmount(orderAmount);
		eventsOrder.setCurrency(placeEventDTO.getCurrency());
		VendorEvents vendorEvents = eventsDao.getVendorEventByEventUUID(placeEventDTO.getEventUUID());
		eventsOrder.setEventDate(vendorEvents.getEventDate());
		eventsOrder.setTimeSlot(placeEventDTO.getTimeslot());
		eventsOrder.setIsUserArrived(Constants.STRING_N);
		eventsOrder.setCreatedDate(new Date());
		String eventOrderUUID = RandomCodeHelper.generateRandomUUID();
		eventsOrder.setUuid(eventOrderUUID);
		eventsOrder.setStatus(1);
		String qrCode = writeQRCode(placeEventDTO.getVendorUUID(), RandomCodeHelper.generateQRUUID(), eventOrderUUID);
		eventsOrder.setQrCode(qrCode);
		eventsDao.saveEventsOrder(eventsOrder);
		
		PlaceOrderTransactions transactions = new PlaceOrderTransactions();
		transactions.setVendorUUID(placeEventDTO.getVendorUUID());
		transactions.setUserUUID(placeEventDTO.getUserUUID());
		transactions.setEventOrderCode(orderCode);
		transactions.setAmount(orderAmount);
		transactions.setCurrency(placeEventDTO.getCurrency());
		
		if(StringUtils.isNotBlank(placeEventDTO.getPaymentId())) {
			transactions.setPaymentId(placeEventDTO.getPaymentId());
		}
		
		if(StringUtils.isNotBlank(placeEventDTO.getOrderId())) {
			transactions.setOrderId(placeEventDTO.getOrderId());
		}
		
		if(StringUtils.isNotBlank(placeEventDTO.getSignature())) {
			transactions.setSignature(placeEventDTO.getSignature());
		}
		transactions.setPaymentDate(new Date());
		transactions.setUuid(RandomCodeHelper.generateRandomUUID());
		transactions.setStatus(1);
		userCartDao.savePlaceOrderTransactions(transactions);
		sendOrderConfirmationEmail(eventsOrder);
		sendSMS(eventsOrder);
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		
		}catch (Exception e) {
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
		
	}
	
	
	private String writeQRCode(String vendorUUID,String QRCodeUUID, String orderUUID ) throws WriterException, IOException {
		VendorDTO vendorDTO;
		String qrCodePath = null;
		try {
			vendorDTO = vendorDao.getVendorDetails(vendorUUID);
			File folderFile = new File(staticPath, "/" + vendorDTO.getVendorId() + "/Event-Orders-QR-Codes");
			if (!folderFile.exists()) {
				folderFile.mkdirs();
			}
			
			String qcodePath = staticPath+"/"+vendorDTO.getVendorId()+"/Event-Orders-QR-Codes/" + QRCodeUUID + ".png";
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(appUrl+"/ws/eventOrders/"+orderUUID, BarcodeFormat.QR_CODE, 350, 350);
			Path path = FileSystems.getDefault().getPath(qcodePath);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			qrCodePath = "/wayuparty-static/"+vendorDTO.getVendorId()+"/Event-Orders-QR-Codes/" + QRCodeUUID + ".png";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return qrCodePath;
	}
	
	public void sendOrderConfirmationEmail(EventsOrder eventsOrder) throws Exception {

		UserDTO userDTO = userDao.getUserDetailsByUUID(eventsOrder.getUserUUID());
		VendorDTO vendorDTO = vendorDao.getVendorDetails(eventsOrder.getVendorUUID());
		EmailMessages emailMessages = new  EmailMessages();
		StringBuilder userName = new StringBuilder();
		if (StringUtils.isNotBlank(userDTO.getLastName())) {
			userName.append(userDTO.getFirstName()).append(" ").append(userDTO.getLastName());
		} else {
			userName.append(userDTO.getFirstName());
		}
		emailMessages.setLoginUserName(userName.toString());
		emailMessages.setToEmail(userDTO.getUserEmail());
		emailMessages.setFromEmail(fromEmailAddress);
		emailMessages.setFromPassword(fromEmailPassword);
		emailMessages.setSmtphost(smtphost);
		emailMessages.setPort(port);

		emailMessages.setFromName(Constants.FROM_EMAIL_NAME);
		emailMessages.setSubject("Event Conformation :: ONEROOF");

		Map<String, String> globalVarsMap = new HashMap<String, String>();
		globalVarsMap.put("registerName", userName.toString());
		StringBuilder qrCodeLink = new StringBuilder();
		qrCodeLink = qrCodeLink.append(appUrl).append(eventsOrder.getQrCode());
		emailMessages.setQrCodeLink(qrCodeLink.toString());
		StringBuilder messageBody = new StringBuilder();
		SimpleDateFormat  formatter = new SimpleDateFormat("dd MMM yyyy"); 
		String serviceDate = formatter.format(eventsOrder.getEventDate());
		messageBody = messageBody.append("Your event has conformed with ").append(vendorDTO.getVendorName()).append(" On ").append(serviceDate);
		emailMessages.setMessageBody(messageBody.toString());
		emailMessages.setClubAddress(vendorDTO.getVendorAddress());
		emailUtil.sendOrderConformationEmail(emailMessages);
				
	}
	
	public void sendSMS(EventsOrder eventsOrder) throws Exception {

		try {
			UserDTO userDTO = userDao.getUserDetailsByUUID(eventsOrder.getUserUUID());
			VendorDTO vendorDTO = vendorDao.getVendorDetails(eventsOrder.getVendorUUID());

			StringBuilder userName = new StringBuilder();
			if (StringUtils.isNotBlank(userDTO.getLastName())) {
				userName.append(userDTO.getFirstName()).append(" ").append(userDTO.getLastName());
			} else {
				userName.append(userDTO.getFirstName());
			}
			
			SimpleDateFormat  formatter = new SimpleDateFormat("dd MMM yyyy"); 
			String serviceDate = formatter.format(eventsOrder.getEventDate());

			
			// Construct data
			String apiKey = "apikey=" + textLocalAPIKey;
			String message = "&message=" + "Dear " + userName
					+ "\nYour Wayuparty order has confirmed with "+ vendorDTO.getVendorName() + "on " + serviceDate
					+ "\nAddress: "+ vendorDTO.getVendorAddress();
			String sender = "&sender=" + "WAYUPT";
			String numbers = "&numbers=" + "91"+userDTO.getUserMobile();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			System.out.println("DATA -- "+data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			e.printStackTrace();

		}
				
	}

	@Override
	public List<UserConformedEventsDTO> getUserEventList(String userUUID) throws Exception {
		return eventsDao.getUserEventList(userUUID);
	}

	@Override
	public EventQRDetailsDTO getEventEntryQRDetails(String orderUUID) throws Exception {
		return eventsDao.getEventEntryQRDetails(orderUUID);
	}

	@Override
	public ResponseList<UserBookedEventsDTO> getUserBookedEvents(UserBookedEventsDTO userBookedEventsDTO) throws Exception {
		return eventsDao.getUserBookedEvents(userBookedEventsDTO);
	}

	@Override
	public List<EventsTicketInfoDTO> getVendorEventTickets(String eventUUID) throws Exception {
		EventsInfoDTO eventsInfoDTO = eventsDao.getEventsInfo(eventUUID);
		List<EventsTicketInfoDTO> eventsTicketsList = new CopyOnWriteArrayList<EventsTicketInfoDTO>();
		List<String> ticketNamesList = new ArrayList<String>();
		
		 JSONParser parser = new JSONParser();
		  org.json.simple.JSONArray ticketJsonArray = (org.json.simple.JSONArray)parser.parse(eventsInfoDTO.getEventTickets());
		  for (Iterator it = ticketJsonArray.iterator(); it.hasNext();) {
			    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
			    List<String> evemtTicketNamesList = new ArrayList<String>();
			    if(jsonObject.containsKey("ticketName")) {
			    	     String ticketName = ((String) jsonObject.get("ticketName")).trim();
			    	     evemtTicketNamesList.add(ticketName.trim());
			    	     for(String eventTicketName : evemtTicketNamesList) {
			    	    	 if(!ticketNamesList.contains(eventTicketName)) {
			    	    		 ticketNamesList.add(eventTicketName);
			    	    	 }
			    	     }
			    }
		 }
		  
	 	     for(String eventTicketName : ticketNamesList) {
	 	    	EventsTicketInfoDTO eventTicketsInfo = new EventsTicketInfoDTO();
	 	  	  org.json.simple.JSONArray eventTicketsJsonArray = (org.json.simple.JSONArray)parser.parse(eventsInfoDTO.getEventTickets());
			  for (Iterator it = eventTicketsJsonArray.iterator(); it.hasNext();) {
				    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
				    if(jsonObject.containsKey("totalTickets")) {
				    	String ticketName = ((String) jsonObject.get("ticketName")).trim();
				    	 if(eventTicketName.equalsIgnoreCase(ticketName)) {
				    		 String totalTickets = ((String) jsonObject.get("totalTickets")).trim();
			    	    	 eventTicketsInfo.setTotalTickets(totalTickets);
				    	 }
		    	    	
		    	     }
				    if(jsonObject.containsKey("entryType")) {
				    	     String entryType = ((String) jsonObject.get("entryType")).trim();
				    	     String ticketName = ((String) jsonObject.get("ticketName")).trim();
			    	    	 String bookingQuantity = ((String) jsonObject.get("bookingQuantity")).trim();
				    	     String ticketAmount = ((String) jsonObject.get("ticketAmount")).trim();
			    	    	 if(eventTicketName.equalsIgnoreCase(ticketName)) {
			    	    		 if(entryType.equalsIgnoreCase("Stag")) {
			    	    			 eventTicketsInfo.setTicketName(ticketName);
			    	    			 eventTicketsInfo.setStagMaxBooking(bookingQuantity);
			    	    			 eventTicketsInfo.setStagTicketCost(ticketAmount);
					    	     }
					    	     
					    	     if(entryType.equalsIgnoreCase("Couple")) {
					    	    	 eventTicketsInfo.setTicketName(ticketName);
					    	    	 eventTicketsInfo.setCoupleMaxBooking(bookingQuantity);
					    	    	 eventTicketsInfo.setCoupleTicketCost(ticketAmount);
					    	     }
					    	     
					    	     if(entryType.equalsIgnoreCase("Single Lady")) {
					    	    	 eventTicketsInfo.setTicketName(ticketName);
					    	    	 eventTicketsInfo.setSingleLadyMaxBooking(bookingQuantity);
					    	    	 eventTicketsInfo.setSingleLadyTicketCost(ticketAmount);
					    	     }
			    	    	 }
				    	    
				    }
			 }
			  eventsTicketsList.add(eventTicketsInfo);
    	     }
		  
		
		  
		return eventsTicketsList;
	}

	@Override
	public EventsDTO getEventDetailsByUUID(String eventUUID) throws Exception {
		EventsDTO eventsDTO =  eventsDao.getEventDetailsByUUID(eventUUID);
		List<TimeSlotSchedulerInfo> timeslotsList = Collections.<TimeSlotSchedulerInfo>emptyList();
		if(StringUtils.isNotBlank(eventsDTO.getEventTimeSlots())) {
			timeslotsList = new CopyOnWriteArrayList<TimeSlotSchedulerInfo>();
			JSONParser parser = new JSONParser();
				org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(eventsDTO.getEventTimeSlots());
				  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
					    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
					   
					    String startTime = ((String) jsonObject.get("startTime")).trim();
					    String endTime = ((String) jsonObject.get("endTime")).trim();
					    TimeSlotSchedulerInfo timeSlotSchedulerInfo = new TimeSlotSchedulerInfo();
					    timeSlotSchedulerInfo.setStartTime(startTime);
					    timeSlotSchedulerInfo.setEndTime(endTime);
					    timeslotsList.add(timeSlotSchedulerInfo);
				 }
				  eventsDTO.setTimeSchedulerInfo(timeslotsList);
		}
		
		List<EventsTicketInfoDTO> eventTicketItemsList = getVendorEventTickets(eventUUID);
		eventsDTO.setTicketItemsList(eventTicketItemsList);
		
		return eventsDTO;
	}

}
