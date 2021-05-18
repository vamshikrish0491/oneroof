package com.acculytixs.wayuparty.services.impl.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.user.UserCartDao;
import com.acculytixs.wayuparty.dao.user.UserDao;
import com.acculytixs.wayuparty.dao.vendor.VendorDao;
import com.acculytixs.wayuparty.dto.events.UserConformedEventsDTO;
import com.acculytixs.wayuparty.dto.services.SurpriseDetailsDTO;
import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.OrdersDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderTransactionsDTO;
import com.acculytixs.wayuparty.dto.user.RescheduleOrderDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserOrdersDTO;
import com.acculytixs.wayuparty.dto.user.UserPackageItemsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorSettingsDTO;
import com.acculytixs.wayuparty.entity.email.EmailMessages;
import com.acculytixs.wayuparty.entity.services.PlaceOrder;
import com.acculytixs.wayuparty.entity.services.PlaceOrderTransactions;
import com.acculytixs.wayuparty.entity.services.VendorMasterService;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.user.UserCanceledOrders;
import com.acculytixs.wayuparty.entity.user.UserCart;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.services.user.UserCartService;
import com.acculytixs.wayuparty.services.vendor.EventsService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.EmailUtil;
import com.acculytixs.wayuparty.util.RandomCodeHelper;
import com.acculytixs.wayuparty.util.ResponseList;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class UserCartServiceImpl implements UserCartService{
	
	@Autowired
	UserCartDao userCartDao;
	
	@Autowired
	VendorDao vendorDao;
	
	@Value("${static.path}")
	private String staticPath;
	
	@Value("${appUrl}")
	private String appUrl;
	
	@Autowired
	EventsService eventsService;
	
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
	public void addToCart(UserCartDTO userCartDTO) throws Exception {
		
		UserCart userCart = new UserCart();
		
		User user = new User();
		user.setId(userCartDTO.getUserId());
		userCart.setUserId(user);
		
		Vendors vendors = new Vendors();
		vendors.setId(userCartDTO.getVendorId());
		userCart.setVendorId(vendors);
		
		VendorMasterService masterService = new VendorMasterService();
		masterService.setId(userCartDTO.getServiceId());
		userCart.setVendorMasterServiceId(masterService);
		
		userCart.setQuantity(userCartDTO.getQuantity());
		userCart.setOrderAmount(userCartDTO.getOrderAmount());
		userCart.setTotalAmount(userCartDTO.getTotalAmount());
		userCart.setCurrency(userCartDTO.getCurrency());
		userCart.setServiceTimeSlot(userCartDTO.getTimeslot());
		
		if(StringUtils.isNotBlank(userCartDTO.getSurpriseDetails())) {
			userCart.setSurpriseDetails(userCartDTO.getSurpriseDetails());
		}
		
		if(StringUtils.isNotBlank(userCartDTO.getSurpriseInstructions())) {
			userCart.setSurpriseInstructions(userCartDTO.getSurpriseInstructions());
		}
		
		if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())) {
			userCart.setPackageMenuItems(userCartDTO.getPackageMenuItems());
		}
		
		Date serviceOrderDate = new SimpleDateFormat("dd/MM/yyyy").parse(userCartDTO.getServiceOrderDate());  
		userCart.setServiceOrderedDate(serviceOrderDate);
		userCart.setCreatedDate(new Date());
		userCart.setUuid(RandomCodeHelper.generateRandomUUID());
		userCart.setStatus(1);
		userCartDao.addToCart(userCart);
		
	}

	@Override
	public boolean getCartServiceExists(UserCartDTO userCartDTO) throws Exception {
		return userCartDao.getCartServiceExists(userCartDTO);
	}

	@Override
	public List<CartDTO> getUserCartList(Long userId) throws Exception {
		return userCartDao.getUserCartList(userId);
	}

	@Override
	public Long getUserCartCount(Long userId) throws Exception {
		return userCartDao.getUserCartCount(userId);
	}

	@Override
	public Long getVendorIdForExistingCartByUserId(Long userId) throws Exception {
		return userCartDao.getVendorIdForExistingCartByUserId(userId);
	}

	@Override
	public UserCart getUserCartbyUUID(String cartUUID) throws Exception {
		return userCartDao.getUserCartbyUUID(cartUUID);
	}

	@Override
	public void deleteCartItem(UserCart userCart) throws Exception {
		userCartDao.deleteCartItem(userCart);		
	}
	
	@Override
	public UserCart getUserCartOrderDetailsByUUID(String cartUUID) throws Exception {
		return userCartDao.getUserCartOrderDetailsByUUID(cartUUID);
	}

	@Override
	public String placeOrder(PlaceOrderDTO placeOrderDTO) throws Exception {
		
		String queryExecutionStatus = null;
		
		try {
		String[] cartItems = placeOrderDTO.getCartItems().split(",");
		String cartOrderCode = RandomCodeHelper.generateRandomOrderCode();
		String vendorUUID = null;
		String currency = null;
		Double totalAmount = 0d;
		for(int i=0; i<cartItems.length; i++) {
			
			UserCart userCart = userCartDao.getUserCartOrderDetailsByUUID(cartItems[i]);
			if(userCart != null) {
				
				vendorUUID = userCart.getVendorMasterServiceId().getVendorId().getUuid();
				currency = userCart.getCurrency();
				
				PlaceOrder placeOrder = new PlaceOrder();
				placeOrder.setUserUUID(placeOrderDTO.getUserUUID());
				placeOrder.setOrderCode(RandomCodeHelper.generateRandomOrderCode());
				placeOrder.setCartOrderCode(cartOrderCode);
				placeOrder.setVendorUUID(userCart.getVendorMasterServiceId().getVendorId().getUuid());
				placeOrder.setMasterServiceUUID(userCart.getVendorMasterServiceId().getUuid());
				placeOrder.setServiceUUID(userCart.getVendorMasterServiceId().getServiceId().getUuid());
				placeOrder.setQuantity(userCart.getQuantity());
				placeOrder.setOrderAmount(userCart.getOrderAmount());
				placeOrder.setTotalAmount(userCart.getTotalAmount());
				totalAmount = totalAmount+userCart.getTotalAmount();
				placeOrder.setCurrency(userCart.getCurrency());
				placeOrder.setServiceTimeSlot(userCart.getServiceTimeSlot());
				
				if(StringUtils.isNotBlank(userCart.getSurpriseDetails())) {
					placeOrder.setSurpriseDetails(userCart.getSurpriseDetails());
				}
				
				if(StringUtils.isNotBlank(userCart.getSurpriseInstructions())) {
					placeOrder.setSurpriseInstructions(userCart.getSurpriseInstructions());
				}
				
				if(StringUtils.isNotBlank(userCart.getPackageMenuItems())) {
					placeOrder.setPackageMenuItems(userCart.getPackageMenuItems());
				}
				
				placeOrder.setServiceOrderedDate(userCart.getServiceOrderedDate());
				placeOrder.setCreatedDate(new Date());
				String orderUUID = RandomCodeHelper.generateRandomUUID();
				placeOrder.setUuid(orderUUID);
				
				
				VendorSettingsDTO vendorSettingsDTO = vendorDao.getVendorSettingServiceDetails(vendorUUID, userCart.getVendorMasterServiceId().getServiceId().getUuid());
				
				if(vendorSettingsDTO != null && StringUtils.isNotBlank(vendorSettingsDTO.getOrderApproval()) &&
						vendorSettingsDTO.getOrderApproval().equalsIgnoreCase(Constants.YES)) {
					placeOrder.setOrderStatus(Constants.ORDER_PENDING_FOR_APPROVAL);
				}else {
					placeOrder.setOrderStatus(Constants.ORDER_APPROVED);
					String qrCode = writeQRCode(vendorUUID, RandomCodeHelper.generateQRUUID(), orderUUID);
					placeOrder.setQrCode(qrCode);
				}
				
				placeOrder.setStatus(1);
				userCartDao.placeOrder(placeOrder);
				userCartDao.deleteCartItem(userCart);
				
				if(placeOrder.getOrderStatus().equalsIgnoreCase(Constants.ORDER_APPROVED)) {
					sendOrderConfirmationEmail(placeOrder);
					sendSMS(placeOrder);
				}
					
			}
		}
		
		PlaceOrderTransactions transactions = new PlaceOrderTransactions();
		transactions.setVendorUUID(vendorUUID);
		transactions.setUserUUID(placeOrderDTO.getUserUUID());
		transactions.setCartOrderCode(cartOrderCode);
		transactions.setAmount(totalAmount);
		transactions.setCurrency(currency);
		
		if(StringUtils.isNotBlank(placeOrderDTO.getPaymentId())) {
			transactions.setPaymentId(placeOrderDTO.getPaymentId());
		}
		
		if(StringUtils.isNotBlank(placeOrderDTO.getOrderId())) {
			transactions.setOrderId(placeOrderDTO.getOrderId());
		}
		
		if(StringUtils.isNotBlank(placeOrderDTO.getSignature())) {
			transactions.setSignature(placeOrderDTO.getSignature());
		}
		transactions.setPaymentDate(new Date());
		transactions.setUuid(RandomCodeHelper.generateRandomUUID());
		transactions.setStatus(1);
		userCartDao.savePlaceOrderTransactions(transactions);
		
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
			File folderFile = new File(staticPath, "/" + vendorDTO.getVendorId() + "/Orders-QR-Codes");
			if (!folderFile.exists()) {
				folderFile.mkdirs();
			}
			
			String qcodePath = staticPath+"/"+vendorDTO.getVendorId()+"/Orders-QR-Codes/" + QRCodeUUID + ".png";
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(appUrl+"/ws/orders/"+orderUUID, BarcodeFormat.QR_CODE, 350, 350);
			Path path = FileSystems.getDefault().getPath(qcodePath);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			qrCodePath = "/wayuparty-static/"+vendorDTO.getVendorId()+"/Orders-QR-Codes/" + QRCodeUUID + ".png";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return qrCodePath;
	}
	
	@Override
	public ResponseList<OrdersDTO> getVendorOrdersList(OrdersDTO ordersDTO, String serviceUUID, String vendorUUID) throws Exception {
		return userCartDao.getVendorOrdersList(ordersDTO, serviceUUID, vendorUUID);
	}

	@Override
	public OrdersDTO getOrderDetails(String orderUUID) throws Exception {
		OrdersDTO ordersDTO = userCartDao.getOrderDetails(orderUUID);
		if(StringUtils.isNotBlank(ordersDTO.getPackageMenuItems())) {
			List<UserPackageItemsDTO> packageItemsList = userCartDao.getUserOrderedPackageItmes(ordersDTO.getPackageMenuItems());
			ordersDTO.setPackageItems(packageItemsList);
		}else {
			List<UserPackageItemsDTO> emptyList = Collections.<UserPackageItemsDTO>emptyList();  
			ordersDTO.setPackageItems(emptyList);
		}
		
		if(StringUtils.isNotBlank(ordersDTO.getSurpriseDetails())) {
			List<SurpriseDetailsDTO> surpriseList = userCartDao.getUserOrderSurpriseDetails(ordersDTO.getSurpriseDetails());
			ordersDTO.setSurpriseList(surpriseList);
		}else {
			List<SurpriseDetailsDTO> emptyList = Collections.<SurpriseDetailsDTO>emptyList();  
			ordersDTO.setSurpriseList(emptyList);
		}
		return ordersDTO;
	}

	@Override
	public PlaceOrder getPlaceOrderByOrderUUID(String orderUUID) throws Exception {
		return userCartDao.getPlaceOrderByOrderUUID(orderUUID);
	}

	@Override
	public void savePlaceOrder(PlaceOrder placeOrder) throws Exception {
		userCartDao.placeOrder(placeOrder);		
	}

	@Override
	public List<UserOrdersDTO> getUserOrdersList(String userUUID) throws Exception {
		
		List<UserOrdersDTO> userOrdersDTOList = new CopyOnWriteArrayList<UserOrdersDTO>();
		List<PlaceOrderDTO> placeOrdersList = userCartDao.getUserPlaceOrders(userUUID);
		if(placeOrdersList != null) {
			for(PlaceOrderDTO placeOrderDTO : placeOrdersList) {
				DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
				Date orderDate = df.parse(placeOrderDTO.getServiceOrderDate());
				List<PlaceOrderDTO> ordersList = userCartDao.getUserOrdersList(placeOrderDTO.getCartOrderCode(), orderDate, placeOrderDTO.getVendorUUID());
				
				if(ordersList != null && !ordersList.isEmpty()) {
					StringBuilder orderItemsBuilder = new StringBuilder();
					StringBuilder orderRatesBuilder = new StringBuilder();
					StringBuilder orderUUIDsBuilder = new StringBuilder();
					StringBuilder orderStatusBuilder = new StringBuilder();
					StringBuilder cancelOrderBuilder = new StringBuilder();
					StringBuilder rescheduleOrderBuilder = new StringBuilder();
					StringBuilder userArrivedBuilder = new StringBuilder();
					
					Double totalAmount = 0d;
					Integer canceledOrdersCount = 0;
					String qrCode = "";
					UserOrdersDTO userOrdersDTO = new UserOrdersDTO();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC+05:30")));
					String currentDateStr = dateFormat.format(new Date());
					Date currentDate = dateFormat.parse(currentDateStr);
					
					    
					if(DateUtils.isSameDay(orderDate, currentDate) == true || orderDate.after(currentDate)) {
						userOrdersDTO.setOrderDateStatus(Constants.ORDER_DATE_NOT_EXPIRED_STATUS);
					}else {
						userOrdersDTO.setOrderDateStatus(Constants.ORDER_DATE_EXPIRED_STATUS);
					}
					
					for(PlaceOrderDTO orderDTO : ordersList) {
						orderItemsBuilder = orderItemsBuilder.append(orderDTO.getQuantity()).append(" x ").append(orderDTO.getServiceName()).append(", ");
						orderRatesBuilder = orderRatesBuilder.append(orderDTO.getRates()).append(", ");
						totalAmount = totalAmount + orderDTO.getRates();
						
						orderUUIDsBuilder = orderUUIDsBuilder.append(orderDTO.getPlaceOrderUUID()).append(",");
						orderStatusBuilder = orderStatusBuilder.append(orderDTO.getOrderStatus()).append(",");
						userArrivedBuilder = userArrivedBuilder.append(orderDTO.getIsUserArrived()).append(",");
						
						userOrdersDTO.setClubName(orderDTO.getVendorName());
						userOrdersDTO.setClubLocation(orderDTO.getVendorLocation());
						userOrdersDTO.setOrderDate(orderDTO.getServiceOrderDate());
						userOrdersDTO.setCurrency(orderDTO.getCurrency());
						
						if(StringUtils.isNotBlank(orderDTO.getOrderStatus()) &&
								orderDTO.getOrderStatus().equalsIgnoreCase(Constants.ORDER_CANCELED)) {
							canceledOrdersCount = canceledOrdersCount+1;
						}
						
						if(StringUtils.isNotBlank(orderDTO.getQrCode())) {
							qrCode = orderDTO.getQrCode();
						}
						
						VendorSettingsDTO vendorSettingsDTO = vendorDao.getVendorSettingServiceDetails(orderDTO.getVendorUUID(), orderDTO.getServiceUUID());
						if(vendorSettingsDTO != null && StringUtils.isNotBlank(vendorSettingsDTO.getIsCancelOrderEnabled()) && 
								vendorSettingsDTO.getIsCancelOrderEnabled().equalsIgnoreCase(Constants.STRING_Y) 
								&& userOrdersDTO.getOrderDateStatus().equalsIgnoreCase(Constants.ORDER_DATE_NOT_EXPIRED_STATUS)) {
							String timezone = vendorDao.getVendorTimezoneByVendorUUID(orderDTO.getVendorUUID());
							LocalTime offsetTime = LocalTime.now(ZoneId.of(timezone));
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
							String fromatedTime = offsetTime.format(formatter);
							
							SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
						    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
						    Date date = parseFormat.parse(fromatedTime);
						    String currentTime = displayFormat.format(date);
						    
						    String orderBookedTime[] = orderDTO.getServiceTimeSlot().split("to");
						    String bookedTime = orderBookedTime[0].trim();
						    date = parseFormat.parse(bookedTime);
						    String orderedTime = displayFormat.format(date);
						    LocalTime t1 = LocalTime.parse(orderedTime);
						    LocalTime t2 = LocalTime.parse(currentTime);
						    
						    Duration diff = Duration.between(t1, t2);
						    
						    if(vendorSettingsDTO.getCancelOrder().equalsIgnoreCase("Hours") || 
						    		vendorSettingsDTO.getCancelOrder().equalsIgnoreCase("Minutes")) {
						    	
						    	Integer minutes = 0;
						    	if(vendorSettingsDTO.getCancelOrder().equalsIgnoreCase("Hours")) {
						    		minutes = vendorSettingsDTO.getCancelOderValue() * 60;
						    	}
						    	if(DateUtils.isSameDay(orderDate, currentDate) == true) {
						    		if(diff.toMinutes() <= minutes) {
						    			cancelOrderBuilder = cancelOrderBuilder.append(Constants.STRING_Y).append(",");
							    	}else {
							    		cancelOrderBuilder = cancelOrderBuilder.append(Constants.STRING_N).append(",");
							    	}
						    	}else {
						    		cancelOrderBuilder = cancelOrderBuilder.append(Constants.STRING_Y).append(",");
						    	}
						    	
						    }
						    
						    if(vendorSettingsDTO.getCancelOrder().equalsIgnoreCase("Days")) {
						    	
						    	LocalDate userOrderedDate = LocalDate.parse(placeOrderDTO.getServiceOrderDate());
						    	LocalDate orderCurrentDate = LocalDate.parse(currentDateStr);
						    	long noOfDaysBetween = ChronoUnit.DAYS.between(orderCurrentDate, userOrderedDate);
						    	if(noOfDaysBetween >= vendorSettingsDTO.getCancelOderValue()){
						    		cancelOrderBuilder = cancelOrderBuilder.append(Constants.STRING_Y).append(",");
						    	}else {
						    		cancelOrderBuilder = cancelOrderBuilder.append(Constants.STRING_N).append(",");
						    	}
						    }
						    
						}else {
							cancelOrderBuilder = cancelOrderBuilder.append(Constants.STRING_N).append(",");
						}
						
						
						
						
						if(vendorSettingsDTO != null && StringUtils.isNotBlank(vendorSettingsDTO.getIsRescheduleOrderEnabled()) && 
								vendorSettingsDTO.getIsRescheduleOrderEnabled().equalsIgnoreCase(Constants.STRING_Y) 
								&& userOrdersDTO.getOrderDateStatus().equalsIgnoreCase(Constants.ORDER_DATE_NOT_EXPIRED_STATUS)) {
							String timezone = vendorDao.getVendorTimezoneByVendorUUID(orderDTO.getVendorUUID());
							LocalTime offsetTime = LocalTime.now(ZoneId.of(timezone));
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
							String fromatedTime = offsetTime.format(formatter);
							
							SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
						    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
						    Date date = parseFormat.parse(fromatedTime);
						    String currentTime = displayFormat.format(date);
						    
						    String orderBookedTime[] = orderDTO.getServiceTimeSlot().split("to");
						    String bookedTime = orderBookedTime[0].trim();
						    date = parseFormat.parse(bookedTime);
						    String orderedTime = displayFormat.format(date);
						    LocalTime t1 = LocalTime.parse(orderedTime);
						    LocalTime t2 = LocalTime.parse(currentTime);
						    
						    Duration diff = Duration.between(t1, t2);
						    
						    if(vendorSettingsDTO.getRescheduleOrder().equalsIgnoreCase("Hours") || 
						    		vendorSettingsDTO.getRescheduleOrder().equalsIgnoreCase("Minutes")) {
						    	
						    	Integer minutes = 0;
						    	if(vendorSettingsDTO.getRescheduleOrder().equalsIgnoreCase("Hours")) {
						    		minutes = vendorSettingsDTO.getRescheduleOrderValue() * 60;
						    	}
						    	if(DateUtils.isSameDay(orderDate, currentDate) == true) {
						    		if(diff.toMinutes() <= minutes) {
						    			rescheduleOrderBuilder = rescheduleOrderBuilder.append(Constants.STRING_Y).append(",");
							    	}else {
							    		rescheduleOrderBuilder = rescheduleOrderBuilder.append(Constants.STRING_N).append(",");
							    	}
						    	}else {
						    		rescheduleOrderBuilder = rescheduleOrderBuilder.append(Constants.STRING_Y).append(",");
						    	}
						    	
						    }
						    
						    if(vendorSettingsDTO.getRescheduleOrder().equalsIgnoreCase("Days")) {
						    	
						    	LocalDate userOrderedDate = LocalDate.parse(placeOrderDTO.getServiceOrderDate());
						    	LocalDate orderCurrentDate = LocalDate.parse(currentDateStr);
						    	long noOfDaysBetween = ChronoUnit.DAYS.between(orderCurrentDate, userOrderedDate);
						    	if(noOfDaysBetween >= vendorSettingsDTO.getRescheduleOrderValue()){
						    		rescheduleOrderBuilder = rescheduleOrderBuilder.append(Constants.STRING_Y).append(",");
						    	}else {
						    		rescheduleOrderBuilder = rescheduleOrderBuilder.append(Constants.STRING_N).append(",");
						    	}
						    }
						    
						}else {
							rescheduleOrderBuilder = rescheduleOrderBuilder.append(Constants.STRING_N).append(",");
						}
					}
					
					String orderItems = orderItemsBuilder.toString();
					orderItems = orderItems.substring(0, orderItems.length() - 2);
					
					String orderRates = orderRatesBuilder.toString();
					orderRates = orderRates.substring(0, orderRates.length() - 2);
					
					String orderUUIDs = orderUUIDsBuilder.toString();
					orderUUIDs = orderUUIDs.substring(0, orderUUIDs.length() - 1);
					
					String orderStatus = orderStatusBuilder.toString();
					orderStatus = orderStatus.substring(0, orderStatus.length() - 1);
					
					String orderCancelStatus = cancelOrderBuilder.toString();
					orderCancelStatus = orderCancelStatus.substring(0, orderCancelStatus.length() - 1);
					
					String orderRescheduleStatus = rescheduleOrderBuilder.toString();
					orderRescheduleStatus = orderRescheduleStatus.substring(0, orderRescheduleStatus.length() - 1);
					
					String userArrivedStatus = userArrivedBuilder.toString();
					userArrivedStatus = userArrivedStatus.substring(0, userArrivedStatus.length() - 1);
					
					userOrdersDTO.setRating(placeOrderDTO.getRating());
					if(placeOrderDTO.getRating() > 0) {
						userOrdersDTO.setIsUserRated(Constants.STRING_Y);
					}else {
						userOrdersDTO.setIsUserRated(Constants.STRING_N);
					}
					userOrdersDTO.setOrderItems(orderItems);
					userOrdersDTO.setOrderRates(orderRates);
					userOrdersDTO.setTotalAmount(totalAmount);
					userOrdersDTO.setOrderUUIDs(orderUUIDs);
					userOrdersDTO.setOrderStatus(orderStatus);
					userOrdersDTO.setCanceledOrdersCount(canceledOrdersCount);
					userOrdersDTO.setQrCode(qrCode);
					userOrdersDTO.setOrderItemsCanceled(orderCancelStatus);
					userOrdersDTO.setOrderItemsReschedule(orderRescheduleStatus);
					userOrdersDTO.setUserArrivedStatus(userArrivedStatus);
					userOrdersDTO.setPlaceOrderCode(placeOrderDTO.getCartOrderCode());
					userOrdersDTOList.add(userOrdersDTO);
					
				}
			}
		}
		
		List<UserConformedEventsDTO> userEventsList = eventsService.getUserEventList(userUUID);
		if(userEventsList != null && !userEventsList.isEmpty()) {
			for(UserConformedEventsDTO userConformedEventsDTO : userEventsList) {
				UserOrdersDTO userOrdersDTO = new UserOrdersDTO();
				DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
				Date orderDate = df.parse(userConformedEventsDTO.getEventOrderDate());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC+05:30")));
				String currentDateStr = dateFormat.format(new Date());
				Date currentDate = dateFormat.parse(currentDateStr);
				
				    
				if(DateUtils.isSameDay(orderDate, currentDate) == true || orderDate.after(currentDate)) {
					userOrdersDTO.setOrderDateStatus(Constants.ORDER_DATE_NOT_EXPIRED_STATUS);
				}else {
					userOrdersDTO.setOrderDateStatus(Constants.ORDER_DATE_EXPIRED_STATUS);
				}
				
				userOrdersDTO.setClubName(userConformedEventsDTO.getClubName());
				userOrdersDTO.setClubLocation(userConformedEventsDTO.getClubLocation());
				userOrdersDTO.setOrderDate(userConformedEventsDTO.getOrderDate());
				userOrdersDTO.setCurrency(userConformedEventsDTO.getCurrency());
				userOrdersDTO.setOrderItems(userConformedEventsDTO.getQuantity()+" x "+userConformedEventsDTO.getTicketType()+" - "+userConformedEventsDTO.getEventName());
				userOrdersDTO.setOrderRates(userConformedEventsDTO.getEventAmount().toString());
				userOrdersDTO.setTotalAmount(userConformedEventsDTO.getTotalAmount());
				userOrdersDTO.setOrderUUIDs(userConformedEventsDTO.getEventUUID());
				userOrdersDTO.setPlaceOrderCode(userConformedEventsDTO.getOrderCode());
				userOrdersDTO.setOrderStatus("Approved");
				userOrdersDTO.setCanceledOrdersCount(0);
				userOrdersDTO.setQrCode(userConformedEventsDTO.getQrCode());
				userOrdersDTO.setOrderItemsCanceled(Constants.STRING_N);
				userOrdersDTO.setOrderItemsReschedule(Constants.STRING_N);
				userOrdersDTO.setUserArrivedStatus(userConformedEventsDTO.getIsUserArrived());
				if(userConformedEventsDTO.getRating() != null) {
					userOrdersDTO.setIsUserRated(Constants.STRING_Y);
					userOrdersDTO.setRating(userConformedEventsDTO.getRating());
				}else {
					userOrdersDTO.setIsUserRated(Constants.STRING_N);
					userOrdersDTO.setRating(0);
				}
				userOrdersDTOList.add(userOrdersDTO);
				
			}
		}
		
		return userOrdersDTOList;
	}

	@Override
	public List<PlaceOrderDTO> getQRPlaceOrdersList(String uuid) throws Exception {
		
		PlaceOrder placeOrder = userCartDao.getPlaceOrderByOrderUUID(uuid);
		List<PlaceOrderDTO> ordersList = new CopyOnWriteArrayList<PlaceOrderDTO>();
		ordersList = userCartDao.getUserOrdersList(placeOrder.getCartOrderCode(), placeOrder.getServiceOrderedDate(), placeOrder.getVendorUUID());
		
		if(ordersList != null && !ordersList.isEmpty()) {
			for(PlaceOrderDTO placeOrderDTO : ordersList) {
				if(StringUtils.isNotBlank(placeOrderDTO.getPackageMenuItems())) {
					List<UserPackageItemsDTO> packageItemsList = userCartDao.getUserOrderedPackageItmes(placeOrderDTO.getPackageMenuItems());
					placeOrderDTO.setPackageItems(packageItemsList);
				}else {
					List<UserPackageItemsDTO> emptyList = Collections.<UserPackageItemsDTO>emptyList();  
					placeOrderDTO.setPackageItems(emptyList);
				}
				
				if(StringUtils.isNotBlank(placeOrderDTO.getSurpriseDetails())) {
					List<SurpriseDetailsDTO> surpriseList = userCartDao.getUserOrderSurpriseDetails(placeOrderDTO.getSurpriseDetails());
					placeOrderDTO.setSurpriseList(surpriseList);
				}else {
					List<SurpriseDetailsDTO> emptyList = Collections.<SurpriseDetailsDTO>emptyList();  
					placeOrderDTO.setSurpriseList(emptyList);
				}
			}
		}

		return ordersList;
	}

	@Override
	public List<UserCart> getCartListByVendorId(Long vendorId, Long userId) throws Exception {
		return userCartDao.getCartListByVendorId(vendorId, userId);
	}

	@Override
	public String rescheduleOrder(RescheduleOrderDTO rescheduleOrderDTO) throws Exception {
		
		String queryExecutionStatus = null;
		try {
		PlaceOrder placeOrder = userCartDao.getPlaceOrderByOrderUUID(rescheduleOrderDTO.getOrderUUID());
		Date serviceOrderDate = new SimpleDateFormat("dd/MM/yyyy").parse(rescheduleOrderDTO.getServiceOrderDate());  
		placeOrder.setServiceOrderedDate(serviceOrderDate);
		placeOrder.setServiceTimeSlot(rescheduleOrderDTO.getServiceTimeSlot());
		userCartDao.placeOrder(placeOrder);
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		
		}catch (Exception e) {
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	}

	@Override
	public PlaceOrderTransactionsDTO getTransactionDetailsByCartOrderCode(String cartOrderCode) throws Exception {
		return userCartDao.getTransactionDetailsByCartOrderCode(cartOrderCode);
	}

	@Override
	public void saveCancelOrderDetails(PlaceOrder placeOrder) throws Exception {
		
		PlaceOrderTransactionsDTO transactionsDTO = userCartDao.getTransactionDetailsByCartOrderCode(placeOrder.getCartOrderCode());
		UserCanceledOrders canceledOrders = new UserCanceledOrders();
		canceledOrders.setVendorUUID(transactionsDTO.getVendorUUID());
		canceledOrders.setUserUUID(transactionsDTO.getUserUUID());
		canceledOrders.setOrderCode(placeOrder.getOrderCode());
		canceledOrders.setCartOrderCode(transactionsDTO.getCartOrderCode());
		canceledOrders.setServiceUUID(placeOrder.getServiceUUID());	
		canceledOrders.setMasterServiceUUID(placeOrder.getMasterServiceUUID());
		canceledOrders.setCanceledOrderAmount(placeOrder.getOrderAmount());
		canceledOrders.setTotalTransactionAmount(transactionsDTO.getAmount());
		canceledOrders.setCurrency(transactionsDTO.getCurrency());
		canceledOrders.setServiceOrderedDate(placeOrder.getServiceOrderedDate());
		canceledOrders.setServiceTimeSlot(placeOrder.getServiceTimeSlot());
		canceledOrders.setPaymentId(transactionsDTO.getPaymentId());
		canceledOrders.setOrderId(transactionsDTO.getOrderId());
		canceledOrders.setSignature(transactionsDTO.getSignature());
		canceledOrders.setPaymentDate(placeOrder.getCreatedDate());
		canceledOrders.setCanceledDate(placeOrder.getOrderCanceledDate());
		if(StringUtils.isNotBlank(placeOrder.getIsUserCanceled()) && 
				placeOrder.getIsUserCanceled().equalsIgnoreCase(Constants.STRING_Y)) {
			canceledOrders.setCanceledBy("user");
		}
		
		if(StringUtils.isNotBlank(placeOrder.getIsVendorCanceled()) && 
				placeOrder.getIsVendorCanceled().equalsIgnoreCase(Constants.STRING_Y)) {
			canceledOrders.setCanceledBy("vendor");
		}
		
		canceledOrders.setUuid(RandomCodeHelper.generateRandomUUID());
		canceledOrders.setStatus(1);
		userCartDao.saveCancelOrderDetails(canceledOrders);
		
	}

	@Override
	public void sendOrderConfirmationEmail(PlaceOrder placeOrder) throws Exception {

		UserDTO userDTO = userDao.getUserDetailsByUUID(placeOrder.getUserUUID());
		VendorDTO vendorDTO = vendorDao.getVendorDetails(placeOrder.getVendorUUID());
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
		emailMessages.setSubject("Order Conformation :: ONEROOF");

		Map<String, String> globalVarsMap = new HashMap<String, String>();
		globalVarsMap.put("registerName", userName.toString());
		StringBuilder qrCodeLink = new StringBuilder();
		qrCodeLink = qrCodeLink.append(appUrl).append(placeOrder.getQrCode());
		emailMessages.setQrCodeLink(qrCodeLink.toString());
		StringBuilder messageBody = new StringBuilder();
		SimpleDateFormat  formatter = new SimpleDateFormat("dd MMM yyyy"); 
		String serviceDate = formatter.format(placeOrder.getServiceOrderedDate());
		messageBody = messageBody.append("Your order has conformed with ").append(vendorDTO.getVendorName()).append(" On ").append(serviceDate);
		emailMessages.setMessageBody(messageBody.toString());
		emailMessages.setClubAddress(vendorDTO.getVendorAddress());
		emailUtil.sendOrderConformationEmail(emailMessages);
				
	}
	
	@Override
	public void sendSMS(PlaceOrder placeOrder) throws Exception {

		try {
			UserDTO userDTO = userDao.getUserDetailsByUUID(placeOrder.getUserUUID());
			VendorDTO vendorDTO = vendorDao.getVendorDetails(placeOrder.getVendorUUID());

			StringBuilder userName = new StringBuilder();
			if (StringUtils.isNotBlank(userDTO.getLastName())) {
				userName.append(userDTO.getFirstName()).append(" ").append(userDTO.getLastName());
			} else {
				userName.append(userDTO.getFirstName());
			}
			
			SimpleDateFormat  formatter = new SimpleDateFormat("dd MMM yyyy"); 
			String serviceDate = formatter.format(placeOrder.getServiceOrderedDate());

			
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

	

}
