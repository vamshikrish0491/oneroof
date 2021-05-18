package com.acculytixs.wayuparty.services.impl.vendor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.user.UserDao;
import com.acculytixs.wayuparty.dao.vendor.VendorDao;
import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.user.VendorPlaceOrdersDTO;
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
import com.acculytixs.wayuparty.entity.WayupartyRoles;
import com.acculytixs.wayuparty.entity.email.EmailMessages;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.Coupon;
import com.acculytixs.wayuparty.entity.vendor.SpecialPackage;
import com.acculytixs.wayuparty.entity.vendor.VendorBankAccount;
import com.acculytixs.wayuparty.entity.vendor.VendorRatings;
import com.acculytixs.wayuparty.entity.vendor.VendorServiceSettings;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.security.WayupartyPasswordEncoder;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.EmailUtil;
import com.acculytixs.wayuparty.util.FileInfo;
import com.acculytixs.wayuparty.util.FileUploadUtil;
import com.acculytixs.wayuparty.util.RandomCodeHelper;

@Service
public class VendorServiceImpl implements VendorService{
	
	@Autowired
	VendorDao vendorDao;
	
	@Value("${static.path}")
	private String staticPath;

	@Value("${fromEmailAddress}")
	private String fromEmailAddress;
	
	@Value("${fromEmailPassword}")
	private String fromEmailPassword;
	
	@Value("${smtphost}")
	private String smtphost;
	
	@Value("${port}")
	private Integer port;
	
	@Value("${appUrl}")
	private String appUrl;
	
	@Autowired
	EmailUtil emailUtil;
	
	
	@Autowired
	WayupartyPasswordEncoder wayupartyPasswordEncoder;
	
	@Autowired
	UserDao userDao;

	@Override
	public void saveVendor(AddVendorDTO addVendorDTO) throws Exception {
		
		try {
		Vendors vendors = new Vendors();
		vendors.setVendorName(addVendorDTO.getVendorName());
		vendors.setVendorCode(addVendorDTO.getVendorCode());
		vendors.setVendorMobile(addVendorDTO.getVendorMobile());
		vendors.setVendorEmail(addVendorDTO.getVendorEmail());
		vendors.setVendorCapacity(Integer.valueOf(addVendorDTO.getVendorCapacity()));
		vendors.setEstabilshedYear(Integer.valueOf(addVendorDTO.getEstablishedYear()));
		vendors.setCostForTwoPeople(Integer.valueOf(addVendorDTO.getCostForTwoPeople()));
		vendors.setCurrency(addVendorDTO.getCurrency());
		vendors.setVendorDescription(addVendorDTO.getVendorDescription());
		vendors.setBestSellingItems(addVendorDTO.getBestSellingItems());
		
		vendors.setLocation(addVendorDTO.getVendorLocation());
		vendors.setCountry(addVendorDTO.getCountry());
		vendors.setState(addVendorDTO.getState());
		vendors.setCity(addVendorDTO.getCity());
		vendors.setPincode(addVendorDTO.getPincode());
		vendors.setLatitude(addVendorDTO.getLatitude());
		vendors.setLongitude(addVendorDTO.getLongitude());
		vendors.setPhoneNumber(addVendorDTO.getPhoneNumber());
		vendors.setAddress(addVendorDTO.getVendorAddress());
		vendors.setTimezone(addVendorDTO.getTimezone());
		vendors.setOrderApproval(Constants.NO);
		
		if (addVendorDTO.getFileInfo() != null && !addVendorDTO.getFileInfo().isEmpty()) {
			
			FileInfo fileInfo = addVendorDTO.getFileInfo().get(0);
			vendors.setProfileImage(fileInfo.getFileURL().replaceAll(" ", "_"));
			try {
				FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_profile_images");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		vendors.setUuid(RandomCodeHelper.generateRandomUUID());
		vendors.setStatus(1);
		vendors.setCreatedDate(new Date());
		
		
		HashSet<User> adminUserList = new HashSet<User>();
		User user = new User();
		user.setVendorId(vendors);
		user.setFirstName(addVendorDTO.getVendorAdministratorName());
		user.setMobileNumber(addVendorDTO.getVendorMobile());
		user.setEmail(addVendorDTO.getVendorEmail());
		user.setUserName(addVendorDTO.getVendorEmail());
		String password = RandomCodeHelper.generateRandomPassword();
		user.setPassword(wayupartyPasswordEncoder.encode(password));
		WayupartyRoles roles = new WayupartyRoles();
		roles.setId(Constants.ROLE_ADMIN);
		user.setRoleId(roles);
		user.setUuid(RandomCodeHelper.generateRandomUUID());
		user.setStatus(1);
		user.setIsEmailVerified(Constants.STRING_N);
		user.setUserStatus(Constants.USER_NOT_LOGGED_IN);
		user.setCreatedDate(new Date());
		adminUserList.add(user);
		vendors.setUser(adminUserList);
		
		HashSet<VendorBankAccount> bankAccountList = new HashSet<VendorBankAccount>();
		VendorBankAccount bankAccount = new VendorBankAccount();
		bankAccount.setVendorId(vendors);
		bankAccount.setBeneficiaryName(addVendorDTO.getBeneficiaryName());
		bankAccount.setBankName(addVendorDTO.getBankName());
		bankAccount.setBankBranch(addVendorDTO.getBankBranch());
		bankAccount.setAccountNumber(addVendorDTO.getAccountNumber());
		bankAccount.setBankCode(addVendorDTO.getBankCode());
		bankAccount.setAccountType(addVendorDTO.getAccountType());
		bankAccount.setUuid(RandomCodeHelper.generateRandomUUID());
		bankAccount.setStatus(1);
		bankAccount.setCreatedDate(new Date());
		bankAccountList.add(bankAccount);
		vendors.setVendorBankAccount(bankAccountList);
		
		vendorDao.saveVendor(vendors);
		
		sendActivationEmail(addVendorDTO, password, user);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void saveVendorSpecialPackage(String vendorUUID, AddSpecialPackageDTO addSpecialPackageDTO) throws Exception {

		try {
			
		SpecialPackage specialPackage = new SpecialPackage();
		specialPackage.setVendorUUID(vendorUUID);
		specialPackage.setServiceType(addSpecialPackageDTO.getServiceType());
		
		Date serviceStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(addSpecialPackageDTO.getStartDate());  
		specialPackage.setStartDate(serviceStartDate);
		Date serviceEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(addSpecialPackageDTO.getEndDate());  
		specialPackage.setEndDate(serviceEndDate);
		specialPackage.setCreatedDate(new Date());
		
		if (addSpecialPackageDTO.getFileInfo() != null && !addSpecialPackageDTO.getFileInfo().isEmpty()) {
			
			FileInfo displayImageFileInfo = addSpecialPackageDTO.getFileInfo().get(0);
			if(displayImageFileInfo.getFileURL() != null) {
				specialPackage.setEventDisplayImage(displayImageFileInfo.getFileURL().replaceAll(" ", "_"));
				try {
					FileUploadUtil.moveFile(staticPath, displayImageFileInfo, "vendor_banner_images");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(addSpecialPackageDTO.getFileInfo().size() > 1) {
				FileInfo bannerImageFileInfo = addSpecialPackageDTO.getFileInfo().get(1);
				if(bannerImageFileInfo.getFileURL() != null) {
					specialPackage.setEventBannerImage(bannerImageFileInfo.getFileURL().replaceAll(" ", "_"));
					try {
						FileUploadUtil.moveFile(staticPath, bannerImageFileInfo, "vendor_banner_images");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		vendorDao.saveSpecialPackage(specialPackage);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendActivationEmail(AddVendorDTO addVendorDTO, String password, User user) {
		 EmailMessages emailMessages = new EmailMessages();
		 emailMessages.setLoginUserName(addVendorDTO.getVendorAdministratorName());
		 emailMessages.setToEmail(addVendorDTO.getVendorEmail());
		 emailMessages.setUserName(addVendorDTO.getVendorEmail());
		 emailMessages.setPassword(password);
		 emailMessages.setFromEmail(fromEmailAddress);
		 emailMessages.setFromPassword(fromEmailPassword);
		 emailMessages.setSmtphost(smtphost);
		 emailMessages.setPort(port);
		 
		 emailMessages.setFromName(Constants.FROM_EMAIL_NAME);
		 emailMessages.setSubject("Verification Link :: ONEROOF");
		 
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("registerName", addVendorDTO.getVendorName());
		 StringBuilder verifylink = new StringBuilder();
		 verifylink.append("<a href='").append(appUrl).append("/ws/activateEmail?&tocken=").append(user.getUuid()).append("'>").append(appUrl).append("/ws/activateEmail?&tocken=").append(user.getUuid()).append("</a>").toString();
	     
		 emailMessages.setVerificationLink(verifylink.toString());
		 emailUtil.sendTemplateEmail(emailMessages);
	}

	@Override
	public List<VendorDTO> getRegisteredVendors() throws Exception {
		return vendorDao.getRegisteredVendors();
	}
	
	@Override
	public List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit, String deals) throws Exception {
		return vendorDao.getRegisteredRestaurantsList(offset, limit, deals);
	}

	@Override
	public VendorDTO getVendorDetails(String vendorUUID) throws Exception {
		return vendorDao.getVendorDetails(vendorUUID);
	}

	@Override
	public void saveVendorBasicDetails(VendorDetailsDTO vendorDetailsDTO) throws Exception {
		
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorDetailsDTO.getVendorUUID());
		if(vendors != null) {
			
			vendors.setVendorName(vendorDetailsDTO.getVendorName());
			vendors.setVendorCode(vendorDetailsDTO.getVendorCode());
			vendors.setVendorMobile(vendorDetailsDTO.getVendorMobile());
			vendors.setVendorEmail(vendorDetailsDTO.getVendorEmail());
			vendors.setVendorCapacity(Integer.valueOf(vendorDetailsDTO.getVendorCapacity()));
			vendors.setEstabilshedYear(Integer.valueOf(vendorDetailsDTO.getEstablishedYear()));
			vendors.setCostForTwoPeople(Integer.valueOf(vendorDetailsDTO.getCostForTwoPeople()));
			vendors.setCurrency(vendorDetailsDTO.getCurrency());
			vendors.setVendorDescription(vendorDetailsDTO.getVendorDescription());
			vendors.setBestSellingItems(vendorDetailsDTO.getBestSellingItems());
			vendors.setOrderApproval(vendorDetailsDTO.getOrderApproval());
			
			if (vendorDetailsDTO.getFileInfo() != null && !vendorDetailsDTO.getFileInfo().isEmpty()) {
				
				FileInfo fileInfo = vendorDetailsDTO.getFileInfo().get(0);
				vendors.setProfileImage(fileInfo.getFileURL().replaceAll(" ", "_"));
				try {
					FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_profile_images");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			vendorDao.saveVendor(vendors);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		
	}

	@Override
	public VendorBankDetailsDTO getVendorBankDetails(String vendorUUID) throws Exception {
		VendorDTO vendorDTO = vendorDao.getVendorDetails(vendorUUID);
		return vendorDao.getVendorBankDetails(vendorDTO.getVendorId().longValue());
	}

	@Override
	public String saveVendorBankAccountDetails(VendorBankDetailsDTO vendorBankDetailsDTO) throws Exception {
		
		String queryExecutionStatus = null;
		try {
			VendorBankAccount bankAccount = vendorDao.getVendorBankDetailsByVendorUUID(vendorBankDetailsDTO.getVendorUUID());
			if(bankAccount != null) {
				bankAccount.setBeneficiaryName(vendorBankDetailsDTO.getBeneficiaryName());
				bankAccount.setBankName(vendorBankDetailsDTO.getBankName());
				bankAccount.setBankBranch(vendorBankDetailsDTO.getBankBranch());
				bankAccount.setBankCode(vendorBankDetailsDTO.getBankCode());
				bankAccount.setAccountNumber(vendorBankDetailsDTO.getAccountNumber());
				bankAccount.setAccountType(vendorBankDetailsDTO.getAccountType());
				vendorDao.saveVendorBankAccountDetails(bankAccount);
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			}
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		
		return queryExecutionStatus;
	}

	@Override
	public String saveVendorAddressDetails(VendorAddressDTO vendorAddressDTO) throws Exception {
		String queryExecutionStatus = null;
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorAddressDTO.getVendorUUID());
		if(vendors != null) {
			
			vendors.setLocation(vendorAddressDTO.getVendorLocation());
			vendors.setCountry(vendorAddressDTO.getCountry());
			vendors.setState(vendorAddressDTO.getState());
			vendors.setCity(vendorAddressDTO.getCity());
			vendors.setPincode(vendorAddressDTO.getPincode());
			vendors.setLatitude(vendorAddressDTO.getLatitude());
			vendors.setLongitude(vendorAddressDTO.getLongitude());
			vendors.setPhoneNumber(vendorAddressDTO.getPhoneNumber());
			vendors.setAddress(vendorAddressDTO.getVendorAddress());
			vendors.setTimezone(vendorAddressDTO.getTimezone());
			vendorDao.saveVendor(vendors);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	}

	@Override
	public List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception {
		return vendorDao.getAllcategoriesByType(categoryType);
	}

	@Override
	public String saveVendorProfileCategoriesDetails(VendorProfileCategoriesDTO vendorProfileCategoriesDTO)
			throws Exception {

		String queryExecutionStatus = null;
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorProfileCategoriesDTO.getVendorUUID());
		if(vendors != null) {
			
			if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_CATEGORIES_TYPE)) {
				vendors.setVendorCategories(vendorProfileCategoriesDTO.getCategoriesIds());
			}else if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_FACILITIES_TYPE)) {
				vendors.setVendorFacilities(vendorProfileCategoriesDTO.getFacilitiesIds());
			}else if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE)) {
				vendors.setVendorMusicGenre(vendorProfileCategoriesDTO.getMusicIds());
			}else if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_CUISINE_TYPE)) {
				vendors.setVendorCuisine(vendorProfileCategoriesDTO.getCuisineIds());
			}
			vendorDao.saveVendor(vendors);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	
	}

	@Override
	public Vendors getVendorByUUID(String vendorUUID) throws Exception {
		return vendorDao.getVendorByUUID(vendorUUID);
	}

	@Override
	public VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception {
		return vendorDao.getVendorProflieCategories(vendorUUID);
	}

	@Override
	public String saveVendorWorkingHoursDetails(VendorWorkingHoursDTO vendorsWorkingHoursDTO, String vendorUUID) throws Exception {
		
	String queryExecutionStatus = null;
	if (vendorsWorkingHoursDTO.getTimeSchedulerInfo() != null && !vendorsWorkingHoursDTO.getTimeSchedulerInfo().isEmpty()) {
		try {
		   JSONArray array = new JSONArray();
			for(TimeSchedulerInfo timeSchedulerInfo : vendorsWorkingHoursDTO.getTimeSchedulerInfo()) {
				JSONObject documentItem = new JSONObject();
				documentItem.put("workingDay", timeSchedulerInfo.getWorkingDay());
				documentItem.put("startTime", timeSchedulerInfo.getStartTime());
				documentItem.put("endTime", timeSchedulerInfo.getEndTime());
				array.put(documentItem);
			}
			String timeSchedulerJsonArry = array.toString();
			
				Vendors vendors = vendorDao.getVendorByUUID(vendorUUID);
				if(vendors != null) {
					
					vendors.setWorkingHours(timeSchedulerJsonArry);
					vendorDao.saveVendor(vendors);
					queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
				}
			}catch (Exception e) {
				e.printStackTrace();
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
			}
		}
		
		return queryExecutionStatus;
	}

	@Override
	public List<TimeSchedulerInfo> getVendorWorkingHoursDetails(String vendorUUID) throws Exception {
		
		VendorCustomDetailsDTO vendorCustomDetailsDTO = vendorDao.getVendorCustomDetailsDTO(vendorUUID);
		
		List<TimeSchedulerInfo> timeSchedulerInfoList = null;
		if(StringUtils.isNotBlank(vendorCustomDetailsDTO.getWorkingHours())) {
			timeSchedulerInfoList = new ArrayList<TimeSchedulerInfo>();
			JSONParser parser = new JSONParser();
			try {
				org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorCustomDetailsDTO.getWorkingHours());
				  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
					    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
					   
					    String startTime = ((String) jsonObject.get("startTime")).trim();
					    String endTime = ((String) jsonObject.get("endTime")).trim();
					    String workingDay = ((String) jsonObject.get("workingDay")).trim();
					    TimeSchedulerInfo timeSchedulerInfo = new TimeSchedulerInfo();
					    timeSchedulerInfo.setStartTime(startTime);
					    timeSchedulerInfo.setEndTime(endTime);
					    timeSchedulerInfo.setWorkingDay(workingDay);
					    timeSchedulerInfoList.add(timeSchedulerInfo);
				 }
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return timeSchedulerInfoList;
	}

	@Override
	public VendorCustomDetailsDTO getVendorTermsAndCondtionsDetails(String vendorUUID) throws Exception {
		VendorCustomDetailsDTO vendorCustomDetailsDTO = vendorDao.getVendorCustomDetailsDTO(vendorUUID);
		return vendorCustomDetailsDTO;
	}

	@Override
	public String saveVendorTermsAndConditions(VendorCustomDetailsDTO vendorCustomDetailsDTO, String vendorUUID)
			throws Exception {

		String queryExecutionStatus = null;
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorUUID);
		if(vendors != null) {
			vendors.setTermsAndConditions(vendorCustomDetailsDTO.getTermsAndConditions());
			vendorDao.saveVendor(vendors);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	
	}

	@Override
	public Long getVendorIdByUUID(String vendorUUID) throws Exception {
		return vendorDao.getVendorIdByUUID(vendorUUID);
	}

	@Override
	public String saveVendorImages(VendorImagesDTO vendorImagesDTO) throws Exception {

		String queryExecutionStatus = null;
		try {
				Vendors vendors = vendorDao.getVendorByUUID(vendorImagesDTO.getVendorUUID());
				if(vendors != null) {
					StringBuilder fileUrl = new StringBuilder();
					String imagesUrl = null;
					if (vendorImagesDTO.getFileInfo() != null && !vendorImagesDTO.getFileInfo().isEmpty()) {
						for(FileInfo fileInfo : vendorImagesDTO.getFileInfo()) {
							if(fileInfo != null && StringUtils.isNotBlank(fileInfo.getFileURL())){
								fileUrl.append(fileInfo.getFileURL()).append(",");
								if(StringUtils.isBlank(fileInfo.getIsSavedImg())) {
									 FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_images");
								}
							}
						}
						imagesUrl = fileUrl.substring(0, fileUrl.length() - 1).toString();
					}
					
					if(vendorImagesDTO.getImageType().equalsIgnoreCase("Slider")) {
						vendors.setSliderImages(imagesUrl);
					}else if(vendorImagesDTO.getImageType().equalsIgnoreCase("Gallery")) {
						vendors.setGalleryImages(imagesUrl);
					}else if(vendorImagesDTO.getImageType().equalsIgnoreCase("Menu")) {
						vendors.setMenuImages(imagesUrl);
					}else if(vendorImagesDTO.getImageType().equalsIgnoreCase("Videos")) {
						vendors.setVendorVideos(imagesUrl);
					}
					
					vendorDao.saveVendor(vendors);
					queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
				}
			}catch (Exception e) {
				e.printStackTrace();
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
			}
		return queryExecutionStatus;	
	
	}

	@Override
	public boolean isVendorExistedByEmailId(String emailId) throws Exception{
		return vendorDao.isVendorExistedByEmailId(emailId);
	}

	@Override
	public boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception{
		return vendorDao.isVendorExistedByPhoneNumber(mobileNumber);
	}

	@Override
	public boolean isVendorExistedByVendorCode(String vendorCode) throws Exception {
		return vendorDao.isVendorExistedByVendorCode(vendorCode);
	}

	@Override
	public List<ServicesDTO> getVendorServices() {
		return vendorDao.getVendorServices();
	}

	@Override
	public Long getServiceIdByUUID(String serviceUUID) throws Exception {
		return vendorDao.getServiceIdByUUID(serviceUUID);
	}

	@Override
	public Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception {
		return vendorDao.getServiceCategoryIdByUUID(categoryUUID);
	}

	@Override
	public void resendVendorInvation(AddVendorDTO addVendorDTO) throws Exception {
		
		User user = userDao.getUserByVendorUUID(addVendorDTO.getVendorUUID());
		StringBuilder vendorAdministratorName = new StringBuilder();
		if(StringUtils.isNotBlank(user.getLastName())) {
			vendorAdministratorName.append(user.getFirstName()).append(" ").append(user.getLastName());
		}else {
			vendorAdministratorName.append(user.getFirstName());
		}
		addVendorDTO.setVendorAdministratorName(vendorAdministratorName.toString());
		addVendorDTO.setVendorEmail(user.getEmail());
		String password = RandomCodeHelper.generateRandomPassword();
		user.setPassword(wayupartyPasswordEncoder.encode(password));
		sendActivationEmail(addVendorDTO, password, user);
		userDao.saveUser(user);
		
		
	}

	@Override
	public List<VendorDTO> getRegisteredRadiusRestaurantsList(Integer offset, Integer limit, Double latitude,
			Double longitude, String deals) throws Exception {
		return vendorDao.getRegisteredRadiusRestaurantsList(offset, limit, latitude, longitude, deals);
	}

	@Override
	public List<String> getAllcategoriesListByType(String categoryType) throws Exception {
		return vendorDao.getAllcategoriesListByType(categoryType);
	}

	@Override
	public String saveVendorSettings(VendorSettingsDTO vendorSettingsDTO) throws Exception {

		String queryExecutionStatus = null;
		try {
				VendorServiceSettings vendorServiceSettings = vendorDao.getVendorServiceSettings(vendorSettingsDTO.getVendorUUID(), vendorSettingsDTO.getServiceUUID());
				if(vendorServiceSettings == null) {
					vendorServiceSettings = new VendorServiceSettings();
				}
				vendorServiceSettings.setVendorUUID(vendorSettingsDTO.getVendorUUID());
				vendorServiceSettings.setServiceUUID(vendorSettingsDTO.getServiceUUID());
				vendorServiceSettings.setIsEntryRatioEnabled(vendorSettingsDTO.getIsEntryRatioEnabled());
				vendorServiceSettings.setMenEntryRatio(vendorSettingsDTO.getMenRatioValue());
				vendorServiceSettings.setWomenEntryRatio(vendorSettingsDTO.getWomenRatioValue());
				vendorServiceSettings.setIsCancelOrderEnabled(vendorSettingsDTO.getIsCancelOrderEnabled());
				vendorServiceSettings.setCancelOrderType(vendorSettingsDTO.getCancelOrder());
				vendorServiceSettings.setCancelOrderValue(vendorSettingsDTO.getCancelOderValue());
				vendorServiceSettings.setIsRescheduleOrderEnabled(vendorSettingsDTO.getIsRescheduleOrderEnabled());
				vendorServiceSettings.setRescheduleOrderType(vendorSettingsDTO.getRescheduleOrder());
				vendorServiceSettings.setRescheduleOrderValue(vendorSettingsDTO.getRescheduleOrderValue());
				vendorServiceSettings.setIsOrderPriorBookingEnabled(vendorSettingsDTO.getIspPriorOrderEnabled());
				vendorServiceSettings.setOrderPriorBookingType(vendorSettingsDTO.getPriorOrder());
				vendorServiceSettings.setOrderPriorBookingValue(vendorSettingsDTO.getPriorOrderValue());
				vendorServiceSettings.setOrderApproval(vendorSettingsDTO.getOrderApproval());
				vendorDao.saveVendorServiceSettings(vendorServiceSettings);
				
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			}catch (Exception e) {
				e.printStackTrace();
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
			}
		return queryExecutionStatus;	
	
	}

	@Override
	public VendorServiceSettings getVendorServiceSettings(String vendorUUID, String serviceUUID) throws Exception {
		return vendorDao.getVendorServiceSettings(vendorUUID, serviceUUID);
	}

	@Override
	public VendorSettingsDTO getVendorSettingServiceDetails(String vendorUUID, String serviceUUID) throws Exception {
		return vendorDao.getVendorSettingServiceDetails(vendorUUID, serviceUUID);
	}

	@Override
	public String getVendorTimezoneByVendorUUID(String vendorUUID) throws Exception {
		return vendorDao.getVendorTimezoneByVendorUUID(vendorUUID);
	}

	@Override
	public VendorsDashboardCountsDTO getVendorsServicesDashboardCount(String vendorUUID, Date startDate,
			Date endDate) throws Exception {
		List<VendorPlaceOrdersDTO> vendorPlaceOrdersDTOList =  vendorDao.getVendorsServicesDashboardCount(vendorUUID, startDate, endDate);
		VendorsDashboardCountsDTO vendorsDashboardCountsDTO = new VendorsDashboardCountsDTO();
		if(vendorPlaceOrdersDTOList != null && !vendorPlaceOrdersDTOList.isEmpty()) {
			List<ServicesDTO> servicesList = vendorDao.getVendorServices();
			
			for(ServicesDTO servicesDTO : servicesList) {
				if(servicesDTO.getServiceDisplayName().equalsIgnoreCase("Book a bottle")) {
					long count = vendorPlaceOrdersDTOList.stream().filter((s) -> s.getServiceName().equalsIgnoreCase(servicesDTO.getServiceDisplayName())).count();
					vendorsDashboardCountsDTO.setBottleServiceCount(count);
				}
				
				if(servicesDTO.getServiceDisplayName().equalsIgnoreCase("Venue")) {
					long count = vendorPlaceOrdersDTOList.stream().filter((s) -> s.getServiceName().equalsIgnoreCase(servicesDTO.getServiceDisplayName())).count();
					vendorsDashboardCountsDTO.setTableServiceCount(count);
				}
				
				if(servicesDTO.getServiceDisplayName().equalsIgnoreCase("Entry")) {
					long count = vendorPlaceOrdersDTOList.stream().filter((s) -> s.getServiceName().equalsIgnoreCase(servicesDTO.getServiceDisplayName())).count();
					vendorsDashboardCountsDTO.setEntryServiceCount(count);
				}
				
				if(servicesDTO.getServiceDisplayName().equalsIgnoreCase("Surprise")) {
					long count = vendorPlaceOrdersDTOList.stream().filter((s) -> s.getServiceName().equalsIgnoreCase(servicesDTO.getServiceDisplayName())).count();
					vendorsDashboardCountsDTO.setSurpriseServiceCount(count);
				}
				
				if(servicesDTO.getServiceDisplayName().equalsIgnoreCase("Deals and Offers")) {
					long count = vendorPlaceOrdersDTOList.stream().filter((s) -> s.getServiceName().equalsIgnoreCase(servicesDTO.getServiceDisplayName())).count();
					vendorsDashboardCountsDTO.setOffersServiceCount(count);
				}
				
				if(servicesDTO.getServiceDisplayName().equalsIgnoreCase("Packages")) {
					long count = vendorPlaceOrdersDTOList.stream().filter((s) -> s.getServiceName().equalsIgnoreCase(servicesDTO.getServiceDisplayName())).count();
					vendorsDashboardCountsDTO.setPackagesServiceCount(count);
				}
				 
			}
		}else {
			vendorsDashboardCountsDTO.setBottleServiceCount(0l);
			vendorsDashboardCountsDTO.setTableServiceCount(0l);
			vendorsDashboardCountsDTO.setEntryServiceCount(0l);
			vendorsDashboardCountsDTO.setSurpriseServiceCount(0l);
			vendorsDashboardCountsDTO.setOffersServiceCount(0l);
			vendorsDashboardCountsDTO.setPackagesServiceCount(0l);
		}
		
		return vendorsDashboardCountsDTO;
	}

	@Override
	public void activateVendorStatus(AddVendorDTO addVendorDTO) throws Exception {
		Vendors vendors = vendorDao.getVendorByUUID(addVendorDTO.getVendorUUID());
		List<User> usersList = userDao.getUsersListByVendorId(vendors.getId());
		if(StringUtils.isNotBlank(addVendorDTO.getVendorActive()) && addVendorDTO.getVendorActive().equalsIgnoreCase(Constants.STRING_Y)) {
			vendors.setStatus(1);
		}else {
			vendors.setStatus(0);
		}
		
		vendorDao.saveVendor(vendors);
		
		if(usersList != null) {
			for(User user : usersList) {
				if(StringUtils.isNotBlank(addVendorDTO.getVendorActive()) && addVendorDTO.getVendorActive().equalsIgnoreCase(Constants.STRING_Y)) {
					user.setStatus(1);
				}else {
					user.setStatus(0);
				}
				userDao.saveUser(user);
			}
		}
	}

	@Override
	public String saveVendorRatings(VendorRatingsDTO vendorRatingsDTO) throws Exception {
		
		String queryExecutionStatus = null;
		try {
			
			VendorRatings vendorRatings = new VendorRatings();
			vendorRatings.setVendorUUID(vendorRatingsDTO.getVendorUUID());
			vendorRatings.setUserUUID(vendorRatingsDTO.getUserUUID());
			vendorRatings.setRating(Integer.valueOf(vendorRatingsDTO.getRating()));
			vendorRatings.setRatingDescription(vendorRatingsDTO.getRatingDescription());
			vendorRatings.setStatus(1);
			String timezone = vendorDao.getVendorTimezoneByVendorUUID(vendorRatingsDTO.getVendorUUID());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timezone)));
			String createdDate = dateFormat.format(new Date());
			Date ratingDate = dateFormat.parse(createdDate);
			vendorRatings.setCreatedDate(ratingDate);
			
			LocalTime offsetTime = LocalTime.now(ZoneId.of(timezone));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
			String fromatedTime = offsetTime.format(formatter);
			vendorRatings.setCreatedTime(fromatedTime);
			
			vendorRatings.setUuid(RandomCodeHelper.generateRandomUUID());
			vendorDao.saveVendorRatings(vendorRatings);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	}

	@Override
	public List<VendorRatingDetailsDTO> getVendorRatingsList(String vendorUUID, Integer offset, Integer limit)
			throws Exception {
		return vendorDao.getVendorRatingsList(vendorUUID, offset, limit);
	}

	@Override
	public List<SpecialPackageDetailsDTO> getSpecialPackageList() throws Exception {
		return vendorDao.getSpecialPackageList();
	}
	
	@Override
	public String saveCoupon(CouponDTO couponDTO) throws Exception {
		
		String queryExecutionStatus = null;
		try {
			
			Coupon coupon = new Coupon();
			
			coupon.setCouponCode(couponDTO.getCouponCode());
			coupon.setCouponName(couponDTO.getCouponName());
			coupon.setCouponValue(couponDTO.getCouponValue());
			coupon.setDiscountType(couponDTO.getDiscountType());
			coupon.setDisplayOffer(couponDTO.getDisplayOffer());
			coupon.setExpireDate(couponDTO.getExpireDate());
			coupon.setMinimumOrder(couponDTO.getMinimumOrder());
			coupon.setCouponApplicable(couponDTO.getCouponApplicable());
			coupon.setUserUUID(couponDTO.getUserUUID());
			coupon.setUsageLimit(couponDTO.getUsageLimit());
			
			vendorDao.saveCoupon(coupon);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	}

	@Override
	public List<CouponDTO> getCouponList() throws Exception {
		return vendorDao.getCouponList();
	}

}
