package com.acculytixs.wayuparty.dao.vendor;

import java.util.Date;
import java.util.List;

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

public interface VendorDao {

	void saveVendor(Vendors vendors) throws Exception;
	
	List<VendorDTO> getRegisteredVendors() throws Exception;
	
	List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit, String deals) throws Exception;
	
	List<VendorDTO> getRegisteredRadiusRestaurantsList(Integer offset, Integer limit, Double latitude, Double longitude, String deals) throws Exception;
	
	VendorDTO getVendorDetails(String vendorUUID) throws Exception;
	
	Vendors getVendorByUUID(String vendorUUID) throws Exception;
	
	VendorBankDetailsDTO getVendorBankDetails(Long vendorId) throws Exception;
	
	VendorBankAccount getVendorBankDetailsByVendorUUID(String vendorUUID) throws Exception;
	
	void saveVendorBankAccountDetails(VendorBankAccount vendorBankAccount) throws Exception;
	
	List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception;
	
	VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception;
	
	VendorCustomDetailsDTO getVendorCustomDetailsDTO(String vendorUUID) throws Exception;
	
	Long getVendorIdByUUID(String vendorUUID) throws Exception;
	
	boolean isVendorExistedByEmailId(String emailId) throws Exception;
	
	boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception;
	
	boolean isVendorExistedByVendorCode(String vendorCode) throws Exception;
	
	List<ServicesDTO> getVendorServices();
	
	Long getServiceIdByUUID(String serviceUUID) throws Exception;
	
	Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception;
	
	String getCategoryNameByCategoryId(Long categoryId) throws Exception;
	
	List<String> getAllcategoriesListByType(String categoryType) throws Exception;
	
	VendorServiceSettings getVendorServiceSettings(String vendorUUID, String serviceUUID) throws Exception;
	
	void saveVendorServiceSettings(VendorServiceSettings vendorServiceSettings) throws Exception;
	
	VendorSettingsDTO getVendorSettingServiceDetails(String vendorUUID, String serviceUUID) throws Exception;
	
	String getVendorTimezoneByVendorUUID(String vendorUUID) throws Exception;
	
	List<VendorPlaceOrdersDTO> getVendorsServicesDashboardCount(String vendorUUID, Date startDate, Date endDate) throws Exception;
	
	void saveVendorRatings(VendorRatings vendorRatings) throws Exception;
	
	List<VendorRatingDetailsDTO> getVendorRatingsList(String vendorUUID,Integer offset, Integer limit) throws Exception;
	
	void saveSpecialPackage(SpecialPackage specialPackage) throws Exception;

	List<SpecialPackageDetailsDTO> getSpecialPackageList() throws Exception;

	List<CouponDTO> getCouponList() throws Exception;

	void saveCoupon(Coupon coupon) throws Exception;

}
