package com.acculytixs.wayuparty.dao.impl.user;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.user.UserCartDao;
import com.acculytixs.wayuparty.dto.services.SurpriseDetailsDTO;
import com.acculytixs.wayuparty.dto.services.VendorServicesDTO;
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

@Repository
@Transactional
public class UserCartDaoImpl implements UserCartDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void addToCart(UserCart userCart) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(userCart);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean getCartServiceExists(UserCartDTO userCartDTO) throws Exception {
		
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(userCartDTO.getServiceOrderDate());  
		String serviceOrderDate = formatter.format(orderDate);
		
		query = "SELECT COUNT(*) " 
				+" FROM "
			    +" user_cart cart where cart.service_ordered_date = STR_TO_DATE('"+serviceOrderDate+"', '%Y-%m-%d') "  
			    +" and cart.service_time_slot = '"+userCartDTO.getTimeslot()+"' and  cart.user_id = :userId and "
			    +" cart.vendor_id = :vendorId and cart.vendor_master_service_id = :serviceId and cart.status = '1'";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userCartDTO.getUserId());
		queryObj.setLong("vendorId", userCartDTO.getVendorId());
		queryObj.setLong("serviceId", userCartDTO.getServiceId());
		BigInteger cartCount = (BigInteger) queryObj.uniqueResult();
		if(cartCount.intValue() > 0) {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public List<CartDTO> getUserCartList(Long userId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<CartDTO> cartDTOList = new ArrayList<CartDTO>();
		List<CartDTO> cartList = new CopyOnWriteArrayList<CartDTO>();
		Query queryObj = null;
  
		query = "SELECT " 
				+" cart.vendor_master_service_id AS masterServiceId, "
				+" cart.order_amount AS orderAmount, "
				+" cart.quantity AS quantity, "
				+" cart.total_amount AS totalAmount, "
				+" cart.service_time_slot AS timeSlot, "
				+" cart.currency AS currency, "
				+" IFNULL(DATE_FORMAT(cart.service_ordered_date, '%b %e %Y'),'') AS serviceOrderDate," 
				+" cart.uuid AS cartUUID "
				
			+" FROM "
			     + "user_cart cart where cart.user_id = :userId and cart.status = '1'";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userId);
		queryObj.setResultTransformer(Transformers.aliasToBean(CartDTO.class));
		cartDTOList = queryObj.list();
		
		if(cartDTOList != null && !cartDTOList.isEmpty()) {
			for(CartDTO cartDTO : cartDTOList) {
				
				query = "SELECT " 
						+" IFNULL(vendorService.service_image,'/resources/img/glass.jpg') AS serviceImage, "
						+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id), vendorService.service_name) AS subCategory "
						
					+" FROM "
					     + "vendor_master_service vendorService where  vendorService.id = :serviceId ";
			
				
				queryObj = currentSession.createSQLQuery(query);
				queryObj.setLong("serviceId", cartDTO.getMasterServiceId().longValue());
				queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
				List<VendorServicesDTO> vendorServicesList = queryObj.list();
				VendorServicesDTO vendorServicesDTO = vendorServicesList.get(0);
				cartDTO.setServiceName(vendorServicesDTO.getSubCategory());
				cartDTO.setServiceImage(vendorServicesDTO.getServiceImage());
				cartList.add(cartDTO);
			}
		}
		
		return cartList;
	}

	@Override
	public Long getUserCartCount(Long userId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;

		query = "SELECT COUNT(*) " 
			+" FROM "
			     + "user_cart cart where cart.status = '1' and "
			     + "cart.user_id = :userId and DATEDIFF(cart.service_ordered_date,CURDATE()) >= 0 ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userId);
		BigInteger cartCount = (BigInteger) queryObj.uniqueResult();
		
		return cartCount.longValue();
	}

	@Override
	public Long getVendorIdForExistingCartByUserId(Long userId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;

		query = "SELECT distinct(cart.vendor_id) " 
			+" FROM "
			     + "user_cart cart where cart.status = '1' and "
			     + "cart.user_id = :userId ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userId);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		if(vendorId != null) {
			return vendorId.longValue();
		}else {
			return null;
		}
		
	}

	@Override
	public UserCart getUserCartbyUUID(String cartUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from UserCart cart  where cart.uuid = :cartUUID ";
		List<UserCart> cartList = currentSession.createQuery(query).setString("cartUUID", cartUUID).list();
		UserCart userCart = null;
		if (cartList != null && cartList.size() > 0) {
			userCart = (UserCart) cartList.iterator().next();
		}
		return userCart;
	}

	@Override
	public void deleteCartItem(UserCart userCart) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.delete(userCart);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public UserCart getUserCartOrderDetailsByUUID(String cartUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from UserCart cart left join fetch cart.vendorId left join fetch cart.vendorMasterServiceId vendorMaster "
				+ " left join fetch vendorMaster.serviceId  where cart.uuid = :cartUUID ";
		List<UserCart> cartList = currentSession.createQuery(query).setString("cartUUID", cartUUID).list();
		UserCart userCart = null;
		if (cartList != null && cartList.size() > 0) {
			userCart = (UserCart) cartList.iterator().next();
		}
		return userCart;
	}

	@Override
	public void placeOrder(PlaceOrder placeOrder) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(placeOrder);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ResponseList<OrdersDTO> getVendorOrdersList(OrdersDTO ordersDTO, String serviceUUID, String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<BigInteger> countlist = null;
		Integer filterCount = 0;
		Query queryObj = null;
		List<OrdersDTO> ordersList = null;
		String querySeachStr =  "";
		ResponseList<OrdersDTO> response = new ResponseList<>();
		
		StringBuilder query1 = new StringBuilder("");
		if(ordersDTO.getSearchValue() != null && !"".equalsIgnoreCase(ordersDTO.getSearchValue())) {
			 query1.append(" Q1.userName like '%" + ordersDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.orderCode like '%" + ordersDTO.getSearchValue() + "%' ")
				.append(" OR Q1.serviceOrderDate like '%" + ordersDTO.getSearchValue() + "%' ")
				.append(" OR Q1.orderAmount like '%" + ordersDTO.getSearchValue() + "%' ")
				.append(" OR Q1.timeSlot like '%" + ordersDTO.getSearchValue() + "%' ")
				.append(" OR Q1.vendorOrderStatus like '%" + ordersDTO.getSearchValue() + "%' ");
			 
		}
		
		if(query1.toString() != null && !query1.toString().isEmpty()){
			querySeachStr = " where "+"("+query1.toString()+")";
		}
		
		
		try {
			 query = "SELECT Q1.* " + 
			 		 "FROM (" + 
					 "SELECT " + 
					"(SELECT CONCAT (u.first_name,' ',IFNULL(u.last_name,'')) FROM user u WHERE u.uuid = placeOrder.user_uuid) AS userName, "+
					"DATE_FORMAT(placeOrder.service_ordered_date, '%d-%m-%Y') AS serviceOrderDate, " +
					"DATE_FORMAT(placeOrder.created_date, '%d-%m-%Y') AS placeOrderDate, " +
					"placeOrder.order_code AS orderCode, " +
					"placeOrder.total_amount AS orderAmount, " +
					"placeOrder.currency AS currency, " +
					"placeOrder.order_status AS vendorOrderStatus, " +
					"placeOrder.service_time_slot AS timeSlot, " +
					"IFNULL(placeOrder.is_user_arrived,'') AS isUserArrived, " +
					"placeOrder.uuid AS orderUUID " +
					"FROM " + 
					"place_order placeOrder " + 
					"WHERE placeOrder.vendor_uuid = :vendorUUID AND " + 
					"placeOrder.service_uuid = :serviceUUID " + 
					"ORDER BY DATE_FORMAT(placeOrder.service_ordered_date ,'%d') ASC)Q1 "+querySeachStr;
		   
			queryObj = currentSession.createSQLQuery(query);
			queryObj.setParameter("vendorUUID", vendorUUID);
			queryObj.setParameter("serviceUUID", serviceUUID);
			queryObj.setResultTransformer(Transformers.aliasToBean(OrdersDTO.class));
			queryObj.setFirstResult(ordersDTO.getStart());
			queryObj.setMaxResults(ordersDTO.getLength());
			ordersList = queryObj.list();
			
			String  countQuery = "SELECT COUNT(*) AS TOTAL" + 
					" FROM" + 
					"(" + query +
					")Q2;" ;
			
			countlist = currentSession.createSQLQuery(countQuery).setParameter("vendorUUID", vendorUUID).setParameter("serviceUUID", serviceUUID).list();
			filterCount = countlist.iterator().next().intValue();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			int totalCount = 0;

			
			if (ordersDTO != null) {
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
		
			response.setData(ordersList);
			return response;
	}

	@Override
	public OrdersDTO getOrderDetails(String orderUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<OrdersDTO> orderDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " +
				"(SELECT CONCAT (u.first_name,' ',IFNULL(u.last_name,'')) FROM user u WHERE u.uuid = placeOrder.user_uuid) AS userName, "+
				"DATE_FORMAT(placeOrder.service_ordered_date, '%d-%m-%Y') AS serviceOrderDate, " +
				"DATE_FORMAT(placeOrder.created_date, '%d-%m-%Y') AS placeOrderDate, " +
				"placeOrder.order_code AS orderCode, " +
				"placeOrder.quantity AS quantity, " +
				"placeOrder.order_amount AS orderAmount, " +
				"placeOrder.total_amount AS totalOrderAmount, " +
				"placeOrder.master_service_uuid AS masterServiceUUID, " +
				"IFNULL(placeOrder.package_menu_items,'') AS packageMenuItems, " +
				"IFNULL(placeOrder.surprise_details,'') AS surpriseDetails, " +
				"IFNULL(placeOrder.surprise_instructions,'') AS surpriseInstructions, " +
				"placeOrder.currency AS currency, " +
				"placeOrder.order_status AS vendorOrderStatus, " +
				"placeOrder.service_time_slot AS timeSlot, " +
				"placeOrder.uuid AS orderUUID " +
				"FROM " + 
				"place_order placeOrder " + 
				"WHERE placeOrder.uuid = :orderUUID";
	
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("orderUUID", orderUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(OrdersDTO.class));
		orderDTOList = queryObj.list();
		

		if(orderDTOList != null && !orderDTOList.isEmpty()) {
			OrdersDTO orderDTO = orderDTOList.get(0);
			
			query = "SELECT " 
					+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id),vendorService.service_name) AS subCategory, "
					+" IFNULL(vendorService.service_image,'/resources/img/glass.jpg') AS serviceImage, "
					+" vendorService.allowed AS allowed "
					+" FROM "
				    + "vendor_master_service vendorService where vendorService.status = '1' and "
				    + "vendorService.uuid = :serviceUUID  ";
		
			
			queryObj = currentSession.createSQLQuery(query);
			queryObj.setParameter("serviceUUID", orderDTO.getMasterServiceUUID());
			queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
			List<VendorServicesDTO> vendorServicesDTOList = queryObj.list();
			if(vendorServicesDTOList != null && !vendorServicesDTOList.isEmpty()) {
				VendorServicesDTO vendorServicesDTO = vendorServicesDTOList.get(0);
				orderDTO.setAllowed(vendorServicesDTO.getAllowed());
				orderDTO.setServiceName(vendorServicesDTO.getSubCategory());
				orderDTO.setServiceImage(vendorServicesDTO.getServiceImage());
			}
			
			return orderDTO;
			
			
		}else {
			return null;
		}
	}

	@Override
	public PlaceOrder getPlaceOrderByOrderUUID(String orderUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from PlaceOrder placeOrder where placeOrder.uuid = :orderUUID ";
		List<PlaceOrder> placeOrderList = currentSession.createQuery(query).setString("orderUUID", orderUUID).list();
		PlaceOrder placeOrder = null;
		if (placeOrderList != null && placeOrderList.size() > 0) {
			placeOrder = (PlaceOrder) placeOrderList.iterator().next();
		}
		return placeOrder;
	}
	
	
	
	@Override
	public List<PlaceOrderDTO> getUserPlaceOrders(String userUUID) throws Exception {
		
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<PlaceOrderDTO> placeOrderDTOList = new CopyOnWriteArrayList<PlaceOrderDTO>();
		Query queryObj = null;
		
		query = "SELECT " 
				+" placeOrder.cart_order_code AS cartOrderCode, "
				+" DATE_FORMAT(placeOrder.service_ordered_date, '%Y-%m-%d') AS serviceOrderDate, "
				+" placeOrder.vendor_uuid AS vendorUUID "
				
			+" FROM "
			     + "place_order placeOrder where placeOrder.user_uuid = :userUUID  ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("userUUID", userUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(PlaceOrderDTO.class));
		List<PlaceOrderDTO> placeOrdersList  = queryObj.list();
		
		if(placeOrdersList != null && !placeOrdersList.isEmpty()) {
			for(PlaceOrderDTO placeOrderDTO : placeOrdersList) {
				
				query = "SELECT orderRatings.rating FROM place_order_ratings orderRatings WHERE orderRatings.place_order_code = :orderCode ";
						
				queryObj = currentSession.createSQLQuery(query);
				queryObj.setParameter("orderCode", placeOrderDTO.getCartOrderCode());
				Integer rating = (Integer) queryObj.uniqueResult();
				if(rating != null) {
					placeOrderDTO.setRating(rating);
				}else {
					placeOrderDTO.setRating(0);
				}
				placeOrderDTOList.add(placeOrderDTO);
			}
		}
		
		return placeOrderDTOList;
	}

	@Override
	public List<PlaceOrderDTO> getUserOrdersList(String cartOrderCode, Date serviceOrderDate, String vendorUUID)
			throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<PlaceOrderDTO> placeOrderDTOList = new CopyOnWriteArrayList<PlaceOrderDTO>();
		Query queryObj = null;
		java.sql.Date orderedDate = new java.sql.Date(serviceOrderDate.getTime());
		
		query = "SELECT " 
				+" (SELECT vendor.vendor_name FROM vendors vendor WHERE vendor.uuid = placeOrder.vendor_uuid) AS vendorName, "
				+" (SELECT vendor.city FROM vendors vendor WHERE vendor.uuid = placeOrder.vendor_uuid) AS vendorLocation, "
				+" placeOrder.cart_order_code AS cartOrderCode, "
				+" placeOrder.order_code AS orderCode, "
				+" DATE_FORMAT(placeOrder.service_ordered_date, '%b %e %Y') AS serviceOrderDate, "
				+" placeOrder.master_service_uuid AS masterServiceUUID, "
				+" placeOrder.service_uuid AS serviceUUID, "
				+" placeOrder.vendor_uuid AS vendorUUID, "
				+" placeOrder.service_time_slot AS serviceTimeSlot, "
				+" placeOrder.quantity AS quantity, "
				+" placeOrder.total_amount AS rates, "
				+" placeOrder.order_amount AS orderAmount, "
				+" placeOrder.currency AS currency, "
				+" placeOrder.uuid AS placeOrderUUID, "
				+" IFNULL(placeOrder.package_menu_items,'') AS packageMenuItems, "
				+" IFNULL(placeOrder.surprise_details,'') AS surpriseDetails, "
				+" IFNULL(placeOrder.surprise_instructions,'') AS surpriseInstructions, "
				+" placeOrder.order_status AS orderStatus, "
				+" IFNULL(placeOrder.is_user_arrived,'N') AS isUserArrived, "
				+" IFNULL(placeOrder.qr_code,'') AS qrCode "
				
			+" FROM "
			     + "place_order placeOrder where placeOrder.cart_order_code = :cartOrderCode  "
			     + "and placeOrder.vendor_uuid = :vendorUUID  "
			     + "and (DATE_FORMAT(placeOrder.service_ordered_date, '%Y-%m-%d') BETWEEN '"+orderedDate+"' AND '"+orderedDate+"')";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("cartOrderCode", cartOrderCode);
		queryObj.setParameter("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(PlaceOrderDTO.class));
		List<PlaceOrderDTO> placeOrdersList = queryObj.list();
		
		if(placeOrdersList != null && !placeOrdersList.isEmpty()) {
			for(PlaceOrderDTO placeOrderDTO : placeOrdersList) {
				
				query = "SELECT " 
						+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id), vendorService.service_name) AS subCategory "
						
					+" FROM "
					     + "vendor_master_service vendorService where  vendorService.uuid = :serviceUUID ";
			
				
				queryObj = currentSession.createSQLQuery(query);
				queryObj.setParameter("serviceUUID", placeOrderDTO.getMasterServiceUUID());
				queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
				List<VendorServicesDTO> vendorServicesList = queryObj.list();
				VendorServicesDTO vendorServicesDTO = vendorServicesList.get(0);
				placeOrderDTO.setServiceName(vendorServicesDTO.getSubCategory());
				placeOrderDTOList.add(placeOrderDTO);
			}
		}
		
		return placeOrderDTOList;
		
	}

	@Override
	public List<UserPackageItemsDTO> getUserOrderedPackageItmes(String packageMenuItems) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<UserPackageItemsDTO> placeItemsDTOList = new CopyOnWriteArrayList<UserPackageItemsDTO>();
		Query queryObj = null;
		
		String offeringItems [] = packageMenuItems.split(",");
		String itemsUUIDs = "'" + StringUtils.join(offeringItems,"','") + "'";
		
		query = "SELECT " 
				+" (SELECT menu.offering_item FROM package_menu_offering menu WHERE menu.id = items.menu_offering_id) AS menuOfferingItem, "
				+" GROUP_CONCAT(items.menu_offering_item) AS menuItems "
				
			+" FROM "
			     + "package_menu_offering_items items where items.uuid IN ("+itemsUUIDs+") group by items.menu_offering_id ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(UserPackageItemsDTO.class));
		placeItemsDTOList = queryObj.list();
		
		return placeItemsDTOList;
	}

	@Override
	public List<SurpriseDetailsDTO> getUserOrderSurpriseDetails(String supriseDetails) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<SurpriseDetailsDTO> surpriseDTOList = new CopyOnWriteArrayList<SurpriseDetailsDTO>();
		Query queryObj = null;
		
		String surprise [] = supriseDetails.split(",");
		String surpriseUUIDs = "'" + StringUtils.join(surprise,"','") + "'";
		
		query = "SELECT " 
				+" surprise.surprise_name AS surpriseName, "
				+" surprise.surprise_type AS surpriseType"
				
			+" FROM "
			     + "surprise_details surprise where surprise.uuid IN ("+surpriseUUIDs+")";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(SurpriseDetailsDTO.class));
		surpriseDTOList = queryObj.list();
		
		return surpriseDTOList;
	}

	@Override
	public void savePlaceOrderTransactions(PlaceOrderTransactions placeOrderTransactions) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(placeOrderTransactions);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UserCart> getCartListByVendorId(Long vendorId, Long userId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from UserCart cart where cart.vendorId.id = :vendorId and  cart.userId.id = :userId  ";
		List<UserCart> userCartList = currentSession.createQuery(query).setLong("vendorId", vendorId).setLong("userId", userId).list();
		
		return userCartList;
	}

	@Override
	public PlaceOrderTransactionsDTO getTransactionDetailsByCartOrderCode(String cartOrderCode) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		List<PlaceOrderTransactionsDTO> placeOrderTransactionsDTOList = null;
		PlaceOrderTransactionsDTO placeOrderTransactionsDTO = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " +
				"transactions.vendor_uuid AS vendorUUID, " +
				"transactions.user_uuid AS userUUID, " +
				"transactions.cart_order_code AS cartOrderCode, " +
				"transactions.amount AS amount, " +
				"transactions.currency AS currency, " +
				"transactions.payment_id AS paymentId, " +
				"transactions.order_id AS orderId, " +
				"transactions.signature AS signature, " +
				"DATE_FORMAT(transactions.payment_date, '%b %e %Y') AS paymentDate, "+
				"transactions.uuid AS transactionUUID " +
				"FROM " + 
				"place_order_transactions transactions " + 
				"WHERE transactions.cart_order_code = :cartOrderCode";
	
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("cartOrderCode", cartOrderCode);
		queryObj.setResultTransformer(Transformers.aliasToBean(PlaceOrderTransactionsDTO.class));
		placeOrderTransactionsDTOList = queryObj.list();
		if(placeOrderTransactionsDTOList != null && !placeOrderTransactionsDTOList.isEmpty()) {
			placeOrderTransactionsDTO = placeOrderTransactionsDTOList.get(0);
		}
		
		return placeOrderTransactionsDTO;
		
	}

	@Override
	public void saveCancelOrderDetails(UserCanceledOrders userCancelOrders) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(userCancelOrders);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	

}
