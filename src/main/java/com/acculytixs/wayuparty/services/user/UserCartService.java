package com.acculytixs.wayuparty.services.user;

import java.util.List;

import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.OrdersDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderTransactionsDTO;
import com.acculytixs.wayuparty.dto.user.RescheduleOrderDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.dto.user.UserOrdersDTO;
import com.acculytixs.wayuparty.entity.services.PlaceOrder;
import com.acculytixs.wayuparty.entity.user.UserCart;
import com.acculytixs.wayuparty.util.ResponseList;

public interface UserCartService {

	void addToCart(UserCartDTO userCartDTO) throws Exception;
	
	boolean getCartServiceExists(UserCartDTO userCartDTO) throws Exception;
	
	List<CartDTO> getUserCartList(Long userId) throws Exception;
	
	Long getUserCartCount(Long userId) throws Exception;
	
	Long getVendorIdForExistingCartByUserId(Long userId) throws Exception;
	
	UserCart getUserCartbyUUID(String cartUUID) throws Exception;
	
	void deleteCartItem(UserCart userCart) throws Exception;
	
	UserCart getUserCartOrderDetailsByUUID(String cartUUID) throws Exception;
	
	String placeOrder(PlaceOrderDTO placeOrderDTO) throws Exception;
	
	ResponseList<OrdersDTO> getVendorOrdersList(OrdersDTO ordersDTO, String serviceUUID, String vendorUUID) throws Exception;
	
	OrdersDTO getOrderDetails(String orderUUID) throws Exception;
	
	PlaceOrder getPlaceOrderByOrderUUID(String orderUUID) throws Exception;
	
	void savePlaceOrder(PlaceOrder placeOrder) throws Exception;
	
	List<UserOrdersDTO> getUserOrdersList(String userUUID) throws Exception;
	
	List<PlaceOrderDTO> getQRPlaceOrdersList(String uuid) throws Exception;
	
	List<UserCart> getCartListByVendorId(Long vendorId,Long userId) throws Exception;
	
	String rescheduleOrder(RescheduleOrderDTO rescheduleOrderDTO) throws Exception;
	
	PlaceOrderTransactionsDTO getTransactionDetailsByCartOrderCode(String cartOrderCode) throws Exception;
	
	void saveCancelOrderDetails(PlaceOrder placeOrder) throws Exception;
	
	void sendOrderConfirmationEmail(PlaceOrder placeOrder) throws Exception;
	
	void sendSMS(PlaceOrder placeOrder) throws Exception;
	
}
