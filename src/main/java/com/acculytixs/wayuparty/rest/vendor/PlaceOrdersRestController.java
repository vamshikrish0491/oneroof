package com.acculytixs.wayuparty.rest.vendor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acculytixs.wayuparty.annotations.Validator;
import com.acculytixs.wayuparty.dto.user.OrdersDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderDTO;
import com.acculytixs.wayuparty.dto.user.RescheduleOrderDTO;
import com.acculytixs.wayuparty.dto.user.UserOrdersDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.entity.services.PlaceOrder;
import com.acculytixs.wayuparty.enums.Result;
import com.acculytixs.wayuparty.services.user.UserCartService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.RandomCodeHelper;
import com.acculytixs.wayuparty.util.Response;
import com.acculytixs.wayuparty.util.ResponseList;
import com.acculytixs.wayuparty.util.SessionManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@RestController
public class PlaceOrdersRestController {

	@Autowired
	UserCartService userCartService;
	
	@Value("${static.path}")
	private String staticPath;
	
	@Value("${appUrl}")
	private String appUrl;
	
	@Autowired
	VendorService vendorService;
	
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public Response<String> saveToCart(PlaceOrderDTO placeOrderDTO,
			HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
			if (Validator.validate(placeOrderDTO)) {
				String result = userCartService.placeOrder(placeOrderDTO);
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
	
	@RequestMapping(value = "/rest/placeOrder", method = RequestMethod.POST)
	public Response<String> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO,
			HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
			if (Validator.validate(placeOrderDTO)) {
				String result = userCartService.placeOrder(placeOrderDTO);
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
	
	
	@RequestMapping(value = "/rescheduleOrder", method = RequestMethod.POST)
	public Response<String> rescheduleServiceOrder(RescheduleOrderDTO rescheduleOrderDTO,
			HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
			if (Validator.validate(rescheduleOrderDTO)) {
				String result = userCartService.rescheduleOrder(rescheduleOrderDTO);
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
	
	@RequestMapping(value = "/rest/rescheduleOrder", method = RequestMethod.POST)
	public Response<String> rescheduleOrder(@RequestBody RescheduleOrderDTO rescheduleOrderDTO,
			HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
		  if(StringUtils.isNotBlank(rescheduleOrderDTO.getServiceOrderDate()) && 
				  StringUtils.isNotBlank(rescheduleOrderDTO.getServiceTimeSlot())) {
				if (Validator.validate(rescheduleOrderDTO)) {
					String result = userCartService.rescheduleOrder(rescheduleOrderDTO);
					if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
						response.setResponse(Result.SUCCESS.name());
					}else {
						response.setResponse(Result.AWKWARD.name());
					}
					
				} else {
					response.setResponse(Result.INVALID_DATA.name());
					response.setResponseMessage(Result.INVALID_DATA.getValue());
				}
		  }else {
			  response.setResponse(Result.INVALID_ORDER_DATE_TIME_SLOT.name());
			  response.setResponseMessage(Result.INVALID_ORDER_DATE_TIME_SLOT.getValue());
		  }
		
		
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getOrdersList", method = RequestMethod.GET)
	public ResponseList<OrdersDTO> getOrdersList(OrdersDTO ordersDTO,
			@RequestParam(value = "serviceUUID", required = true) String serviceUUID, 
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		ResponseList<OrdersDTO> ordersResponseList = new ResponseList<OrdersDTO>();

		try {
			
			String[] tableColumns = { "userName", "orderCode", "serviceOrderDate", "timeSlot", "orderAmount", "vendorOrderStatus","orderActions" };
			int sortColumnIndex = 1; // by default sorting using service order date
			String sortColumnOrder = "desc"; // by default assending order
			if (ordersDTO.getOrder() != null && !ordersDTO.getOrder().isEmpty()) {
				sortColumnIndex = Integer.parseInt(ordersDTO.getOrder().get(0).get("column"));
				sortColumnOrder = ordersDTO.getOrder().get(0).get("dir");
			}
			ordersDTO.setSortColumn(tableColumns[sortColumnIndex]);
			ordersDTO.setSortOrder(sortColumnOrder);
			ordersResponseList = userCartService.getVendorOrdersList(ordersDTO, serviceUUID, vendorUUID);
			ordersResponseList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			ordersResponseList.setResponse(Result.AWKWARD.name());
			ordersResponseList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return ordersResponseList;
	}
	
	@RequestMapping(value = "/getOrderDetails", method = RequestMethod.GET)
	public Response<OrdersDTO> placeOrder(@RequestParam(value = "orderUUID", required = true) String orderUUID,
			HttpServletRequest request) throws Exception {
		
		Response<OrdersDTO> response = new Response<OrdersDTO>();
		try {
			OrdersDTO ordersDTO = userCartService.getOrderDetails(orderUUID);
			response.setObject(ordersDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());
				
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	@RequestMapping(value = "/acceptOrder", method = RequestMethod.POST)
	public Response<String> acceptOrder(@RequestParam(value = "orderUUID", required = true) String orderUUID, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {
			String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			PlaceOrder placeOrder = userCartService.getPlaceOrderByOrderUUID(orderUUID);
			placeOrder.setOrderStatusModifiedDate(new Date());
			placeOrder.setOrderStatus(Constants.ORDER_APPROVED);
			String qrCode = writeQRCode(vendorUUID, RandomCodeHelper.generateQRUUID(), orderUUID);
			placeOrder.setQrCode(qrCode);
			userCartService.savePlaceOrder(placeOrder);
			userCartService.sendOrderConfirmationEmail(placeOrder);
			userCartService.sendSMS(placeOrder);
			response.setResponse(Result.SUCCESS.name());
			
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	private String writeQRCode(String vendorUUID,String QRCodeUUID, String orderUUID ) throws WriterException, IOException {
		VendorDTO vendorDTO;
		String qrCodePath = null;
		try {
			vendorDTO = vendorService.getVendorDetails(vendorUUID);
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
	
	
	
	@RequestMapping(value = "/saveUserArrivedStatus", method = RequestMethod.POST)
	public Response<String> saveUserArrivedStatus(@RequestParam(value = "orderUUID", required = true) String orderUUID, 
			@RequestParam(value = "arrivedStatus", required = true) String arrivedStatus, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {
			PlaceOrder placeOrder = userCartService.getPlaceOrderByOrderUUID(orderUUID);
			placeOrder.setIsUserArrived(arrivedStatus);
			userCartService.savePlaceOrder(placeOrder);
			response.setResponse(Result.SUCCESS.name());
			
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/getUserOrdersList", "/rest/getUserOrdersList" }, method = RequestMethod.POST)
	public ResponseList<UserOrdersDTO> getUserOrdersList(@RequestParam(value = "userUUID", required = true) String userUUID,
			HttpServletRequest request) throws Exception {

		ResponseList<UserOrdersDTO> ordersList = new ResponseList<UserOrdersDTO>();
		try {
			List<UserOrdersDTO> userOrdersDTOList = userCartService.getUserOrdersList(userUUID);
			ordersList.setData(userOrdersDTOList);
			ordersList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			ordersList.setResponse(Result.AWKWARD.name());
			ordersList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return ordersList;
	}
	
	
	@RequestMapping(value = "/ws/getQROrdersList", method = RequestMethod.POST)
	public ResponseList<PlaceOrderDTO> getQROrdersList(@RequestParam(value = "orderUUID", required = true) String orderUUID,
			HttpServletRequest request) throws Exception {

		ResponseList<PlaceOrderDTO> ordersList = new ResponseList<PlaceOrderDTO>();
		try {
			List<PlaceOrderDTO> ordersDTOList = userCartService.getQRPlaceOrdersList(orderUUID);
			ordersList.setData(ordersDTOList);
			ordersList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			ordersList.setResponse(Result.AWKWARD.name());
			ordersList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return ordersList;
	}
	
	
	
	@RequestMapping(value = "/cancelUserOrder", method = RequestMethod.POST)
	public Response<String> cancelUserOrder(@RequestParam(value = "orderUUID", required = true) String orderUUID, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {
			PlaceOrder placeOrder = userCartService.getPlaceOrderByOrderUUID(orderUUID);
			placeOrder.setOrderStatusModifiedDate(new Date());
			placeOrder.setOrderStatus(Constants.ORDER_CANCELED);
			placeOrder.setIsVendorCanceled(Constants.STRING_Y);
			placeOrder.setOrderCanceledDate(new Date());
			userCartService.savePlaceOrder(placeOrder);
			userCartService.saveCancelOrderDetails(placeOrder);
			response.setResponse(Result.SUCCESS.name());
			
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/cancelOrder", "/rest/cancelOrder" }, method = RequestMethod.POST)
	public Response<String> cancelOrder(@RequestParam(value = "orderUUID", required = true) String orderUUID, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {
			PlaceOrder placeOrder = userCartService.getPlaceOrderByOrderUUID(orderUUID);
			
				placeOrder.setOrderStatusModifiedDate(new Date());
				placeOrder.setOrderStatus(Constants.ORDER_CANCELED);
				placeOrder.setIsUserCanceled(Constants.STRING_Y);
				placeOrder.setOrderCanceledDate(new Date());
				userCartService.savePlaceOrder(placeOrder);
				userCartService.saveCancelOrderDetails(placeOrder);
				response.setResponse(Result.ORDER_CANCELED.name());
				response.setResponseMessage(Result.ORDER_CANCELED.getValue());
			
			
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
}
