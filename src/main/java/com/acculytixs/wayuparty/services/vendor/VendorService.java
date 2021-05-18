package com.acculytixs.wayuparty.services.vendor;

import java.util.Date;
import java.util.List;

import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.user.VendorsDashboardCountsDTO;
import com.acculytixs.wayuparty.dto.vendor.AddSpecialPackageDTO;
import com.acculytixs.wayuparty.dto.vendor.AddVendorDTO;
import com.acculytixs.wayuparty.dto.vendor.CouponDTO;
import com.acculytixs.wayuparty.dto.vendor.SpecialPackageDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.TimeSchedulerInfo;
import com.acculytixs.wayuparty.dto.vendor.VendorAddressDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorBankDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorImagesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorRatingDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorRatingsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorSettingsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorWorkingHoursDTO;
import com.acculytixs.wayuparty.entity.vendor.VendorServiceSettings;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

public interface VendorService {

	void saveVendor(AddVendorDTO addVendorDTO) throws Exception;
	
	List<VendorDTO> getRegisteredVendors() throws Exception;
	
	List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit, String deals) throws Exception;
	
	List<VendorDTO> getRegisteredRadiusRestaurantsList(Integer offset, Integer limit, Double latitude, Double longitude, String deals) throws Exception;
	
	VendorDTO getVendorDetails(String vendorUUID) throws Exception;
	
	void saveVendorBasicDetails(VendorDetailsDTO vendorDetailsDTO) throws Exception;
	
	VendorBankDetailsDTO getVendorBankDetails(String vendorUUID) throws Exception;
	
	String saveVendorBankAccountDetails(VendorBankDetailsDTO vendorBankDetailsDTO) throws Exception;
	
	String saveVendorAddressDetails(VendorAddressDTO vendorAddressDTO) throws Exception;
	
	List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception;
	
	String saveVendorProfileCategoriesDetails(VendorProfileCategoriesDTO vendorProfileCategoriesDTO) throws Exception;
	
	Vendors getVendorByUUID(String vendorUUID) throws Exception;
	
	VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception;
	
	String saveVendorWorkingHoursDetails(VendorWorkingHoursDTO vendorsWorkingHoursDTO, String vendorUUID) throws Exception;
	
	List<TimeSchedulerInfo> getVendorWorkingHoursDetails(String vendorUUID) throws Exception;
	
	VendorCustomDetailsDTO getVendorTermsAndCondtionsDetails(String vendorUUID) throws Exception;
	
	String saveVendorTermsAndConditions(VendorCustomDetailsDTO vendorCustomDetailsDTO, String vendorUUID) throws Exception;
	
	Long getVendorIdByUUID(String vendorUUID) throws Exception;
	
	String saveVendorImages(VendorImagesDTO vendorImagesDTO) throws Exception;
	
	boolean isVendorExistedByEmailId(String emailId) throws Exception;
	
	boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception;
	
	boolean isVendorExistedByVendorCode(String vendorCode) throws Exception;
	
	List<ServicesDTO> getVendorServices();
	
	Long getServiceIdByUUID(String serviceUUID) throws Exception;
	
	Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception;
	
	void resendVendorInvation(AddVendorDTO addVendorDTO) throws Exception;
	
	List<String> getAllcategoriesListByType(String categoryType) throws Exception;
	
	String saveVendorSettings(VendorSettingsDTO vendorSettingsDTO) throws Exception;
	
	VendorServiceSettings getVendorServiceSettings(String vendorUUID, String serviceUUID) throws Exception;
	
	VendorSettingsDTO getVendorSettingServiceDetails(String vendorUUID, String serviceUUID) throws Exception;
	
	String getVendorTimezoneByVendorUUID(String vendorUUID) throws Exception;
	
	VendorsDashboardCountsDTO getVendorsServicesDashboardCount(String vendorUUID, Date startDate, Date endDate) throws Exception;
	
	void activateVendorStatus(AddVendorDTO addVendorDTO) throws Exception;
	
	String saveVendorRatings(VendorRatingsDTO vendorRatingsDTO) throws Exception;
	
	List<VendorRatingDetailsDTO> getVendorRatingsList(String vendorUUID,Integer offset, Integer limit) throws Exception;
	
	void saveVendorSpecialPackage(String vendorUUID, AddSpecialPackageDTO addSpecialPackageDTO) throws Exception;
	
	List<SpecialPackageDetailsDTO> getSpecialPackageList() throws Exception;

	List<CouponDTO> getCouponList() throws Exception;
	
	String saveCoupon(CouponDTO couponDTO) throws Exception;

	
}
