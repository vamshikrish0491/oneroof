package com.acculytixs.wayuparty.dao.impl.vendor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.vendor.VendorDao;
import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.user.VendorPlaceOrdersDTO;
import com.acculytixs.wayuparty.dto.vendor.CouponDTO;
import com.acculytixs.wayuparty.dto.vendor.SpecialPackageDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorBankDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorRatingDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorSettingsDTO;
import com.acculytixs.wayuparty.entity.vendor.Coupon;
import com.acculytixs.wayuparty.entity.vendor.SpecialPackage;
import com.acculytixs.wayuparty.entity.vendor.VendorBankAccount;
import com.acculytixs.wayuparty.entity.vendor.VendorRatings;
import com.acculytixs.wayuparty.entity.vendor.VendorServiceSettings;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.util.Constants;

@Repository
@Transactional
public class VendorDaoImpl implements VendorDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveVendor(Vendors vendors) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendors);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<VendorDTO> getRegisteredVendors() throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorDTO> vendorDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.vendor_name AS vendorName, "
				+" vendors.vendor_code AS vendorCode, "
				+" vendors.vendor_email AS vendorEmail, "
				+" vendors.vendor_mobile AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'/resources/img/vendor_default_img.jpg') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.best_selling_items,'') AS bestSellingItems, "
				+" IFNULL((SELECT u.is_email_verified FROM user u WHERE u.vendor_id = vendors.id), 'N') AS isVerified, "
				+" IF(vendors.status = 1, 'Y', 'N') AS isActiveVendor, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors ORDER BY DATE_FORMAT(vendors.created_date, '%d') DESC, DATE_FORMAT(vendors.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorDTO.class));
		vendorDTOList = queryObj.list();
		
		return vendorDTOList;
	}

	@Override
	public VendorDTO getVendorDetails(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorDTO> vendorDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.id AS vendorId, "
				+" vendors.vendor_name AS vendorName, "
				+" vendors.vendor_code AS vendorCode, "
				+" vendors.vendor_email AS vendorEmail, "
				+" vendors.vendor_mobile AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'/resources/img/vendor_default_img.jpg') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" vendors.currency AS currency, "
				+" vendors.vendor_description AS vendorDescription, "
				+" vendors.best_selling_items AS bestSellingItems, "
				+" vendors.location AS location, "
				+" vendors.country AS country, "
				+" vendors.state AS state, "
				+" vendors.city AS city, "
				+" vendors.pincode AS pincode, "
				+" vendors.latitude AS latitude, "
				+" vendors.longitude AS longitude, "
				+" vendors.phone_number AS phoneNumber, "
				+" vendors.address AS vendorAddress, "
				+" vendors.time_zone AS timezone, "
				+" vendors.order_approval AS orderApproval, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorDTO.class));
		vendorDTOList = queryObj.list();
		

		if(vendorDTOList != null && !vendorDTOList.isEmpty()) {
			return vendorDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Vendors getVendorByUUID(String vendorUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from Vendors vendors where vendors.uuid = :vendorUUID ";
		List<Vendors> vendorsList = currentSession.createQuery(query).setString("vendorUUID", vendorUUID).list();
		Vendors vendors = null;
		if (vendorsList != null && vendorsList.size() > 0) {
			vendors = (Vendors) vendorsList.iterator().next();
		}
		return vendors;
	}

	@Override
	public VendorBankDetailsDTO getVendorBankDetails(Long vendorId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorBankDetailsDTO> vendorBankDetailsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" bank.beneficiary_name AS beneficiaryName, "
				+" bank.account_number AS accountNumber, "
				+" bank.bank_name AS bankName, "
				+" bank.bank_branch AS bankBranch, "
				+" bank.bank_code AS bankCode, "
				+" bank.account_type AS accountType "
				
			+" FROM "
			     + "vendor_bank_account bank where bank.vendor_id = :vendorId ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("vendorId", vendorId);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorBankDetailsDTO.class));
		vendorBankDetailsDTOList = queryObj.list();
		

		if(vendorBankDetailsDTOList != null && !vendorBankDetailsDTOList.isEmpty()) {
			return vendorBankDetailsDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public VendorBankAccount getVendorBankDetailsByVendorUUID(String vendorUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from VendorBankAccount bank left join fetch bank.vendorId vendors where vendors.uuid = :vendorUUID ";
		List<VendorBankAccount> vendorsAccountDetailsList = currentSession.createQuery(query).setString("vendorUUID", vendorUUID).list();
		VendorBankAccount vendorBankAccount = null;
		if (vendorsAccountDetailsList != null && vendorsAccountDetailsList.size() > 0) {
			vendorBankAccount = (VendorBankAccount) vendorsAccountDetailsList.iterator().next();
		}
		return vendorBankAccount;
	}

	@Override
	public void saveVendorBankAccountDetails(VendorBankAccount vendorBankAccount) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorBankAccount);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorProfileCategoriesDTO> profileCategoriesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" categories.id AS categoryId, "
				+" categories.category_name AS categoryName "
				
			+" FROM "
			     + "wayuparty_categories categories where categories.status = '1'  and categories.category_type = :categoryType ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("categoryType", categoryType);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorProfileCategoriesDTO.class));
		profileCategoriesDTOList = queryObj.list();
		
		return profileCategoriesDTOList;
	}

	@Override
	public VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorProfileCategoriesDTO> vendorProfileCategoriesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.vendor_categories AS categoriesIds, "
				+" vendors.vendor_facilities AS facilitiesIds, "
				+" vendors.vendor_music_genre AS musicIds, "
				+" vendors.vendor_cuisine AS cuisineIds "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorProfileCategoriesDTO.class));
		vendorProfileCategoriesDTOList = queryObj.list();
		

		if(vendorProfileCategoriesDTOList != null && !vendorProfileCategoriesDTOList.isEmpty()) {
			return vendorProfileCategoriesDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public VendorCustomDetailsDTO getVendorCustomDetailsDTO(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorCustomDetailsDTO> VendorCustomDetailsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.working_hours AS workingHours, "
				+" vendors.terms_conditions AS termsAndConditions, "
				+" vendors.slider_images AS sliderImages, "
				+" vendors.gallery_images AS galleryImages, "
				+" vendors.menu_images AS menuImages, "
				+" vendors.vendor_videos AS vendorVideos "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorCustomDetailsDTO.class));
		VendorCustomDetailsDTOList = queryObj.list();
		

		if(VendorCustomDetailsDTOList != null && !VendorCustomDetailsDTOList.isEmpty()) {
			return VendorCustomDetailsDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long getVendorIdByUUID(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" vendors.id "           
				+" FROM vendors vendors "
				+" WHERE vendors.uuid = :vendorUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("vendorUUID", vendorUUID);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		if(vendorId != null) {
			return vendorId.longValue();
		}else {
			return null;
		}
		
	}

	@Override
	public boolean isVendorExistedByEmailId(String emailId) throws Exception{
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		try {
			  String query = "select id from Vendors vendors where vendors.vendorEmail = :emailId and vendors.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("emailId", emailId);
			  Long vendorId = (Long) queryObj.uniqueResult();
			  if(vendorId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception{
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		try {
			  String query = "select id from Vendors vendors where vendors.vendorMobile = :mobileNumber and vendors.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("mobileNumber", mobileNumber);
			  Long vendorId = (Long) queryObj.uniqueResult();
			  if(vendorId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean isVendorExistedByVendorCode(String vendorCode) throws Exception {
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		try {
			  String query = "select id from Vendors vendors where vendors.vendorCode = :vendorCode and vendors.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("vendorCode", vendorCode);
			  Long vendorId = (Long) queryObj.uniqueResult();
			  if(vendorId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit, String deals) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorDTO> vendorDTOList = null;
		String query = null;
		Query queryObj = null;
		
		String dealsQuery = "";
		if(StringUtils.isNotBlank(deals) && (deals.equalsIgnoreCase(Constants.YES.toLowerCase()) || 
				deals.equalsIgnoreCase(Constants.STRING_Y.toLowerCase()))) {
			dealsQuery = " and vendors.id IN(select master.vendor_id from vendor_master_service master where master.service_id IN "
		             +"(select services.id from wayuparty_services services where services.service_display_name = 'Deals and Offers' ) "
		             + "and DATEDIFF(master.service_end_date,CURDATE()) >= 0) ";
		}
		
		query = "SELECT " 
				+" vendors.vendor_name AS vendorName, "
				+" vendors.vendor_code AS vendorCode, "
				+" vendors.vendor_email AS vendorEmail, "
				+" vendors.vendor_mobile AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'/resources/img/vendor_default_img.jpg') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" vendors.currency AS currency, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.best_selling_items,'') AS bestSellingItems, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors where vendors.status = '1' "+dealsQuery+" ORDER BY DATE_FORMAT(vendors.created_date, '%d') DESC, DATE_FORMAT(vendors.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorDTO.class));
		if(offset != null & limit != null) {
			queryObj.setFirstResult(offset);
			queryObj.setMaxResults(limit);
		}
		vendorDTOList = queryObj.list();
		
		return vendorDTOList;
	}

	@Override
	public List<ServicesDTO> getVendorServices() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<ServicesDTO> servicesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" services.id AS serviceId, "
				+" services.service_name AS serviceName, "
				+" services.service_display_name AS serviceDisplayName, "
				+" services.service_image AS serviceImage, "
				+" services.uuid AS serviceUUID "
				
			+" FROM "
			     + "wayuparty_services services where services.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(ServicesDTO.class));
		servicesDTOList = queryObj.list();
		
		return servicesDTOList;
	}

	@Override
	public Long getServiceIdByUUID(String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" services.id "           
				+" FROM wayuparty_services services "
				+" WHERE services.uuid = :serviceUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("serviceUUID", serviceUUID);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		return vendorId.longValue();
	}

	@Override
	public Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" category.id "           
				+" FROM wayuparty_serivce_category category "
				+" WHERE category.uuid = :categoryUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("categoryUUID", categoryUUID);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		return vendorId.longValue();
	}

	@Override
	public String getCategoryNameByCategoryId(Long categoryId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" category.category_name "           
				+" FROM wayuparty_serivce_category category "
				+" WHERE category.id = :categoryId ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setLong("categoryId", categoryId);
		String categoryName = (String) queryObj.uniqueResult();
		return categoryName;
	}

	@Override
	public List<VendorDTO> getRegisteredRadiusRestaurantsList(Integer offset, Integer limit, Double latitude,
			Double longitude, String deals) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorDTO> vendorDTOList = null;
		String query = null;
		Query queryObj = null;
		String dealsQuery = "";
		
		if(StringUtils.isNotBlank(deals) && (deals.equalsIgnoreCase(Constants.YES.toLowerCase()) || 
				deals.equalsIgnoreCase(Constants.STRING_Y.toLowerCase()))) {
			dealsQuery = " and vendors.id IN(select master.vendor_id from vendor_master_service master where master.service_id IN "
		             +"(select services.id from wayuparty_services services where services.service_display_name = 'Deals and Offers' ) "
		             + "and DATEDIFF(master.service_end_date,CURDATE()) >= 0) ";
		}
		
		query = "SELECT " 
				+" vendors.vendor_name AS vendorName, "
				+" vendors.vendor_code AS vendorCode, "
				+" vendors.vendor_email AS vendorEmail, "
				+" vendors.vendor_mobile AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'/resources/img/vendor_default_img.jpg') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" vendors.currency AS currency, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.best_selling_items,'') AS bestSellingItems, "
				+" vendors.uuid AS vendorUUID, "
				+" ROUND(p.distanceUnit "
				+" * DEGREES(ACOS(COS(RADIANS(p.latpoint)) "
		             +" * COS(RADIANS(vendors.latitude)) "
		             +" * COS(RADIANS(p.longpoint) - RADIANS(vendors.longitude)) "
		             +" + SIN(RADIANS(p.latpoint)) "
		             +" * SIN(RADIANS(vendors.latitude)))),3) AS kilometers "
				
			+" FROM "
			     +" vendors vendors JOIN ( SELECT "+latitude+" AS latpoint, "+longitude+" AS longpoint, 50.0 AS radius, 111.045 AS distanceUnit ) AS p ON 1=1 "
			     +" WHERE vendors.status = '1' and p.distanceUnit "
					+" * DEGREES(ACOS(COS(RADIANS(p.latpoint)) "
			             +" * COS(RADIANS(vendors.latitude)) "
			             +" * COS(RADIANS(p.longpoint) - RADIANS(vendors.longitude)) "
			             +" + SIN(RADIANS(p.latpoint)) "
			             +" * SIN(RADIANS(vendors.latitude)))) <= 100 "+dealsQuery+" ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorDTO.class));
		if(offset != null & limit != null) {
			queryObj.setFirstResult(offset);
			queryObj.setMaxResults(limit);
		}
		vendorDTOList = queryObj.list();
		
		return vendorDTOList;
	}

	@Override
	public List<String> getAllcategoriesListByType(String categoryType) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<String> profileCategoriesList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" categories.category_name AS categoryName "
				
			+" FROM "
			     + "wayuparty_categories categories where categories.status = '1'  and categories.category_type = :categoryType ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("categoryType", categoryType);
		profileCategoriesList = queryObj.list();
		
		return profileCategoriesList;
	}

	@Override
	public VendorServiceSettings getVendorServiceSettings(String vendorUUID, String serviceUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from VendorServiceSettings settings  where settings.vendorUUID = :vendorUUID and settings.serviceUUID = :serviceUUID ";
		List<VendorServiceSettings> vendorsServiceSettingsList = currentSession.createQuery(query).setString("vendorUUID", vendorUUID).setString("serviceUUID", serviceUUID).list();
		VendorServiceSettings vendorServiceSettings = null;
		if (vendorsServiceSettingsList != null && vendorsServiceSettingsList.size() > 0) {
			vendorServiceSettings = (VendorServiceSettings) vendorsServiceSettingsList.iterator().next();
		}
		return vendorServiceSettings;
	}

	@Override
	public void saveVendorServiceSettings(VendorServiceSettings vendorServiceSettings) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorServiceSettings);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public VendorSettingsDTO getVendorSettingServiceDetails(String vendorUUID, String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorSettingsDTO> vendorSettingsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" IFNULL(settings.is_entry_ratio_enabled,'') AS isEntryRatioEnabled, "
				+" settings.men_entry_ratio AS menRatioValue, "
				+" settings.women_entry_ratio AS womenRatioValue, "
				
				+" IFNULL(settings.is_cancel_order_enabled,'') AS isCancelOrderEnabled, "
				+" settings.cancel_order_value AS cancelOderValue, "
				+" IFNULL(settings.cancel_order_type,'') AS cancelOrder, "
				
				+" IFNULL(settings.is_reschedule_order_enabled,'') AS isRescheduleOrderEnabled, "
				+" settings.reschedule_order_value AS rescheduleOrderValue, "
				+" IFNULL(settings.reschedule_order_type,'') AS rescheduleOrder, "
				
				+" IFNULL(settings.is_order_prior_booking_enabled,'') AS ispPriorOrderEnabled, "
				+" settings.order_prior_booking_value AS priorOrderValue, "
				+" IFNULL(settings.order_prior_booking_type,'') AS priorOrder, "
				+" IFNULL(settings.order_approval,'') AS orderApproval "
				
			+" FROM "
			     + "vendor_service_settings settings where settings.vendor_uuid = :vendorUUID and settings.service_uuid = :serviceUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setString("serviceUUID", serviceUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorSettingsDTO.class));
		vendorSettingsDTOList = queryObj.list();
		

		if(vendorSettingsDTOList != null && !vendorSettingsDTOList.isEmpty()) {
			return vendorSettingsDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public String getVendorTimezoneByVendorUUID(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String timezoneQuery =  "SELECT "
				+" vendors.time_zone "           
				+" FROM vendors vendors "
				+" WHERE vendors.uuid = :vendorUUID ";
		
		Query queryObj = currentSession.createSQLQuery(timezoneQuery);
		queryObj.setParameter("vendorUUID", vendorUUID);
		String timeZone = (String) queryObj.uniqueResult();
		return timeZone;
	}

	@Override
	public List<VendorPlaceOrdersDTO> getVendorsServicesDashboardCount(String vendorUUID, Date startDate,
			Date endDate) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorPlaceOrdersDTO> placeOrdersDTOList = null;
		String query = null;
		Query queryObj = null;
		
		String orderDateStr = "";
		
		if(startDate != null && endDate != null) {
			java.sql.Date orderStartDate = new java.sql.Date(startDate.getTime());
			java.sql.Date orderEndDate = new java.sql.Date(endDate.getTime());
			orderDateStr = "AND (DATE_FORMAT(placeOrder.service_ordered_date, '%Y-%m-%d') BETWEEN '"+orderStartDate+"' AND '"+orderEndDate+"') ";
		}
		
		query = "SELECT " 
				+" (SELECT services.service_display_name FROM wayuparty_services services WHERE services.uuid = placeOrder.service_uuid) AS serviceName, "
				+" placeOrder.service_uuid AS serviceUUID, "
				+" placeOrder.uuid AS placeOrderUUID "
				
			+" FROM "
			     + "place_order placeOrder where placeOrder.order_status = 'Approved' "+orderDateStr+" ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorPlaceOrdersDTO.class));
		placeOrdersDTOList = queryObj.list();
		
		return placeOrdersDTOList;
	}

	@Override
	public void saveVendorRatings(VendorRatings vendorRatings) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorRatings);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<VendorRatingDetailsDTO> getVendorRatingsList(String vendorUUID, Integer offset, Integer limit)
			throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorRatingDetailsDTO> vendorRatingsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" (SELECT CONCAT (U.first_name,' ',IFNULL(U.last_name,'')) FROM user U WHERE U.uuid = ratings.user_uuid) AS userName, "
				+" ratings.rating AS rating, "
				+" ratings.rating_description AS ratingDescription, "
				+" DATE_FORMAT(ratings.created_date, '%b %e %Y') AS createdDate, "
				+" ratings.created_time AS createdTime "
				
			+" FROM "
			     + "vendor_ratings ratings where ratings.vendor_uuid = :vendorUUID "
			     + "ORDER BY DATE_FORMAT(ratings.created_date, '%d') DESC, DATE_FORMAT(ratings.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorRatingDetailsDTO.class));
		if(offset != null & limit != null) {
			queryObj.setFirstResult(offset);
			queryObj.setMaxResults(limit);
		}
		vendorRatingsDTOList = queryObj.list();
		
		return vendorRatingsDTOList;
	}

	@Override
	public void saveSpecialPackage(SpecialPackage specialPackage) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(specialPackage);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<SpecialPackageDetailsDTO> getSpecialPackageList() throws Exception {
		
//		Session currentSession = entityManager.unwrap(Session.class);
//		String query = "from SpecialPackage WHERE start_date > '2021-03-17' \n"
//				+ "AND end_date <= '2021-04-26'";

		Session currentSession = entityManager.unwrap(Session.class);
		List<SpecialPackageDetailsDTO> specialPackageDetailsDTO = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" package.service_type AS serviceType, "
				+" package.event_banner_image AS eventBannerImage, "
				+" package.event_mobile_banner_image AS eventMobileBannerImage, "
				+" package.vendor_uuid AS vendorUUID "
				+" FROM "
			    + "special_package package WHERE start_date BETWEEN CURRENT_DATE() AND (CURRENT_DATE() + INTERVAL 7 DAY)";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(SpecialPackageDetailsDTO.class));
		specialPackageDetailsDTO = queryObj.list();
		
		
		return specialPackageDetailsDTO;
		
	}

	@Override
	public List<CouponDTO> getCouponList() throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<CouponDTO> couponsDTO = null;
		String query = null;
		Query queryObj = null;

		query = "SELECT " 
				+" item.coupon_name AS couponName, "
				+" item.coupon_code AS couponCode, "
				+" item.coupon_value AS couponValue, "
				+" item.display_offer AS displayOffer, "
				+" item.minimum_order AS minimumOrder, "
				+" item.discount_type AS discountType, "
				+" item.expire_date AS expireDate, "
				+" item.usage_limit AS usageLimit "
				+" FROM "
			    + "coupon item";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(CouponDTO.class));
		couponsDTO = queryObj.list();
		
		return couponsDTO;
		
	}
	
	@Override
	public void saveCoupon(Coupon coupon) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(coupon);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
