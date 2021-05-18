package com.acculytixs.wayuparty.dao.user;

import java.util.Date;
import java.util.List;

import com.acculytixs.wayuparty.dto.services.SurpriseDetailsDTO;
import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.OrdersDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderTransactionsDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.dto.user.UserPackageItemsDTO;
import com.acculytixs.wayuparty.entity.services.PlaceOrder;
import com.acculytixs.wayuparty.entity.services.PlaceOrderTransactions;
import com.acculytixs.wayuparty.entity.user.UserCanceledOrders;
import com.acculytixs.wayuparty.entity.user.UserCart;
import com.acculytixs.wayuparty.util.ResponseList;

public interface UserCartDao {

	void addToCart(UserCart userCart) throws Exception;
	
	boolean getCartServiceExists(UserCartDTO userCartDTO) throws Exception;
	
	List<CartDTO> getUserCartList(Long userId) throws Exception;
	
	Long getUserCartCount(Long userId) throws Exception;
	
	Long getVendorIdForExistingCartByUserId(Long userId) throws Exception;
	
	UserCart getUserCartbyUUID(String cartUUID) throws Exception;
	
	void deleteCartItem(UserCart userCart) throws Exception;
	
	UserCart getUserCartOrderDetailsByUUID(String cartUUID) throws Exception;
	
	void placeOrder(PlaceOrder placeOrder) throws Exception;
	
	ResponseList<OrdersDTO> getVendorOrdersList(OrdersDTO ordersDTO, String serviceUUID, String vendorUUID) throws Exception;
	
	OrdersDTO getOrderDetails(String orderUUID) throws Exception;
	
	PlaceOrder getPlaceOrderByOrderUUID(String orderUUID) throws Exception;
	
	List<PlaceOrderDTO> getUserPlaceOrders(String userUUID) throws Exception;
	
	List<PlaceOrderDTO> getUserOrdersList(String cartOrderCode, Date serviceOrderDate, String vendorUUID) throws Exception;
	
	List<UserPackageItemsDTO> getUserOrderedPackageItmes(String packageMenuItems) throws Exception;
	
	List<SurpriseDetailsDTO> getUserOrderSurpriseDetails(String supriseDetails) throws Exception;
	
	void savePlaceOrderTransactions(PlaceOrderTransactions placeOrderTransactions) throws Exception;
	
	List<UserCart> getCartListByVendorId(Long VendorId,Long userId) throws Exception;
	
	PlaceOrderTransactionsDTO getTransactionDetailsByCartOrderCode(String cartOrderCode) throws Exception;
	
	void saveCancelOrderDetails(UserCanceledOrders userCancelOrders) throws Exception;
	
}
