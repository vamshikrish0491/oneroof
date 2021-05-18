package com.acculytixs.wayuparty.rest.user;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acculytixs.wayuparty.annotations.Validator;
import com.acculytixs.wayuparty.dto.search.PopularCitiesDTO;
import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.GuestUserDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderRatingsDTO;
import com.acculytixs.wayuparty.dto.user.RegisteredUsersDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDetailsDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserMobileLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserProfileDTO;
import com.acculytixs.wayuparty.dto.user.UserRegistrationDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.dto.user.VerificationDTO;
import com.acculytixs.wayuparty.dto.vendor.CouponDTO;
import com.acculytixs.wayuparty.dto.vendor.SpecialPackageDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.razorpay.RazorPayOrders;
import com.acculytixs.wayuparty.entity.user.PasswordVerification;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.user.UserCart;
import com.acculytixs.wayuparty.enums.Result;
import com.acculytixs.wayuparty.security.WayupartyPasswordEncoder;
import com.acculytixs.wayuparty.services.WayupartyLoginService;
import com.acculytixs.wayuparty.services.user.UserCartService;
import com.acculytixs.wayuparty.services.user.UserService;
import com.acculytixs.wayuparty.services.vendor.VendorGuestsService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.services.vendor.VendorServicesService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.RandomCodeHelper;
import com.acculytixs.wayuparty.util.Response;
import com.acculytixs.wayuparty.util.ResponseList;
import com.acculytixs.wayuparty.util.SessionManager;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@RestController
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	WayupartyPasswordEncoder wayupartyPasswordEncoder;
	
	@Autowired
	UserCartService userCartService;
	
	@Autowired
	private WayupartyLoginService wayupartyLoginService;
	
	@Autowired
	VendorServicesService vendorServicesService;
	
	@Autowired
	VendorGuestsService vendorGuestsService;
	
	@Value("${razorpay.keyId}")
	private String razorpayKeyId;
	
	@Value("${razorpay.secretId}")
	private String razorpayKeySecretId;
	
	@Value("${twilio.accountsid}")
	private String accountsid;
	
	@Value("${twilio.authtocken}")
	private String authtocken;
	
	
	/***
	 * This service to validate user email is existed or not from registration form
	 * @param userEmail
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = { "/ws/validateUserEmail","/validateUserEmail","/rest/validateUserEmail" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateUserEmail(
		@RequestParam(value = "userEmail", required = true) String userEmail,
		@RequestParam(value = "userUUID", required = false) String userUUID, HttpServletRequest request)
		throws Exception {
	Response<String> response = new Response<>();
	
	try {
		boolean flag;
		if (StringUtils.isNotBlank(userUUID)) {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			if (StringUtils.isNotBlank(userDTO.getUserEmail())
					&& userDTO.getUserEmail().toLowerCase().trim().equalsIgnoreCase(userEmail.toLowerCase().trim())) {
				response.setResponse(Result.VALID_EMAIL_DATA.name());
				response.setResponseMessage(Result.VALID_EMAIL_DATA.getValue());
			} else {
				flag = userService.isUserExistedByEmailId(userEmail);
				if (flag == true) {
					response.setResponse(Result.INVALID_EMAIL_DATA.name());
					response.setResponseMessage(Result.INVALID_EMAIL_DATA.getValue());
				} else {
					response.setResponse(Result.VALID_EMAIL_DATA.name());
					response.setResponseMessage(Result.VALID_EMAIL_DATA.getValue());
				}
	
			}
		} else {
			flag = userService.isUserExistedByEmailId(userEmail);
			if (flag == true) {
				response.setResponse(Result.INVALID_EMAIL_DATA.name());
				response.setResponseMessage(Result.INVALID_EMAIL_DATA.getValue());
			} else {
				response.setResponse(Result.VALID_EMAIL_DATA.name());
				response.setResponseMessage(Result.VALID_EMAIL_DATA.getValue());
			}
	
		}
	
	} catch (Exception e) {
		response.setResponse(Result.AWKWARD.name());
		response.setResponseMessage(Result.AWKWARD.getValue());
		e.printStackTrace();
	}
	
	return response;
	}
	
	
	/***
	 * This service to validate user mobile is existed or not from registration form
	 * @param userMobile
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/ws/validateUserMobile","/validateUserMobile","/rest/validateUserMobile" }, method = RequestMethod.POST)
	public @ResponseBody Response<String> validateUserMobile(
		@RequestParam(value = "userMobile", required = true) String userMobile,
		@RequestParam(value = "userUUID", required = false) String userUUID, HttpServletRequest request)
		throws Exception {
	Response<String> response = new Response<>();
	
	try {
		boolean flag;
		if (StringUtils.isNotBlank(userUUID)) {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			if (StringUtils.isNotBlank(userDTO.getUserMobile())
					&& userDTO.getUserMobile().trim().equalsIgnoreCase(userMobile.trim())) {
				response.setResponse(Result.VALID_MOBILE_DATA.name());
				response.setResponseMessage(Result.VALID_MOBILE_DATA.getValue());
			} else {
				flag = userService.isUserExistedByMobileNumber(userMobile);
				if (flag == true) {
					response.setResponse(Result.INVALID_MOBILE_DATA.name());
					response.setResponseMessage(Result.INVALID_MOBILE_DATA.getValue());
				} else {
					response.setResponse(Result.VALID_MOBILE_DATA.name());
					response.setResponseMessage(Result.VALID_MOBILE_DATA.getValue());
				}
	
			}
		} else {
			flag = userService.isUserExistedByMobileNumber(userMobile);
			if (flag == true) {
				response.setResponse(Result.INVALID_MOBILE_DATA.name());
				response.setResponseMessage(Result.INVALID_MOBILE_DATA.getValue());
			} else {
				response.setResponse(Result.VALID_MOBILE_DATA.name());
				response.setResponseMessage(Result.VALID_MOBILE_DATA.getValue());
			}
	
		}
	
	} catch (Exception e) {
		response.setResponse(Result.AWKWARD.name());
		response.setResponseMessage(Result.AWKWARD.getValue());
		e.printStackTrace();
	}
	
	return response;
	}
	
	/***
	 * This service to register new user from registration form
	 * @param userRegistrationDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/saveUserRegistration", method = RequestMethod.POST)
	public Response<UserLoginDTO> saveUserRegistration(UserRegistrationDTO userRegistrationDTO, HttpServletRequest request) throws Exception {

		Response<UserLoginDTO> response = new Response<UserLoginDTO>();
		try {

			if (Validator.validate(userRegistrationDTO)) {
				userService.saveNewUserRegistration(userRegistrationDTO);
				UserLoginDTO userLoginDTO = userService.getLoginUserDetailsByEmail(userRegistrationDTO.getEmail());
				response.setObject(userLoginDTO);
				response.setResponse(Result.SUCCESS.name());
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
	
	/***
	 * This service to get all registered vendors for user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/getAllregisteredRestaurantsList", "/rest/getAllregisteredRestaurantsList" , "/ws/getAllregisteredRestaurantsList" }, method = RequestMethod.GET)
	public ResponseList<VendorDTO> getAllregisteredRestaurantsList(
			@RequestParam(value = "latitude", required = false) String latitude,
			@RequestParam(value = "longitude", required = false) String longitude,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "deals", required = false) String deals,
			HttpServletRequest request) throws Exception {

		ResponseList<VendorDTO> vendorsList = new ResponseList<VendorDTO>();
		try {
			List<VendorDTO> vendorsDTOList = null;
		
			  if(StringUtils.isNotBlank(latitude) && StringUtils.isNotBlank(longitude)) {
				  vendorsDTOList = vendorService.getRegisteredRadiusRestaurantsList(offset, limit, Double.valueOf(latitude), Double.valueOf(longitude), deals); 
			  }else {
				  vendorsDTOList = vendorService.getRegisteredRestaurantsList(offset, limit, deals); 
			 }
			 
			vendorsList.setData(vendorsDTOList);
			vendorsList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			vendorsList.setResponse(Result.AWKWARD.name());
			vendorsList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return vendorsList;
	}
	
	/***
	 * This service to get vendor details
	 * @param vendorUUID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/getVendorInfo", "/rest/getVendorInfo", "/ws/getVendorInfo" }, method = RequestMethod.GET)
	public Response<VendorInfoDTO> getVendorDetails(
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID, HttpServletRequest request) {
		Response<VendorInfoDTO> response = new Response<VendorInfoDTO>();

		try {
			VendorInfoDTO vendorInfoDTO = userService.getVendorInfoDetails(vendorUUID);
			response.setObject(vendorInfoDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}
	
	/***
	 * This service to get user details
	 * @param userUUID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/getUserDetails", "/rest/getUserDetails"}, method = RequestMethod.GET)
	public Response<UserDTO> getUserDetails(
			@RequestParam(value = "userUUID", required = true) String userUUID, HttpServletRequest request) {
		Response<UserDTO> response = new Response<UserDTO>();

		try {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			response.setObject(userDTO);
			response.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;

	}
	
	/***
	 * This service to update user profile
	 * @param userProfileDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUserProfileDetails", method = RequestMethod.POST)
	public Response<String> saveUserProfileDetails(UserProfileDTO userProfileDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(userProfileDTO)) {
				boolean flag = true;	
				   if(StringUtils.isNotBlank(userProfileDTO.getPreferredDrinks())) {
						String[] preferredDrinks = userProfileDTO.getPreferredDrinks().trim().split(",");
						if(preferredDrinks.length > 3) {
							response.setResponse(Result.DRINK_PREFERENCES.name());
							response.setResponseMessage(Result.DRINK_PREFERENCES.getValue());
							flag = false;
						}
					}
					
					if(StringUtils.isNotBlank(userProfileDTO.getPreferredMusic())) {
						String[] preferredMusic = userProfileDTO.getPreferredMusic().trim().split(",");
						if(preferredMusic.length > 3) {
							response.setResponse(Result.MUSIC_PREFERENCES.name());
							response.setResponseMessage(Result.MUSIC_PREFERENCES.getValue());
							flag = false;
						}
					}
				
					if(flag) {
						userService.saveUserProfile(userProfileDTO);
						response.setResponse(Result.SUCCESS.name());
						response.setResponseMessage(Result.SUCCESS.getValue());
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
	
	
	
	@RequestMapping(value = "/rest/saveUserProfile", method = RequestMethod.POST)
	public Response<UserMobileLoginDTO> saveUserProfile(@RequestBody UserProfileDTO userProfileDTO, HttpServletRequest request) throws Exception {

		Response<UserMobileLoginDTO> response = new Response<UserMobileLoginDTO>();
		try {
			UserMobileLoginDTO userMobileLoginDTO = new UserMobileLoginDTO();
			if (Validator.validate(userProfileDTO)) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    Date date = formatter.parse(userProfileDTO.getDob());
			    Instant instant = date.toInstant();
			    ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
			    LocalDate dateOfBirth = zone.toLocalDate();
				Period period = Period.between(dateOfBirth, LocalDate.now());
				if(period.getYears() < 21) {
					userMobileLoginDTO = userService.getLoginMobileUserDetails(userProfileDTO.getUserUUID());
					List<String> preferredDrinksList = vendorServicesService.getServiceCategoriesListByServiceId(Constants.VENDOR_SERVICES_BOTTLE);
					List<String> preferredMusicList = vendorService.getAllcategoriesListByType(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE);
					userMobileLoginDTO.setPreferredDrinksList(preferredDrinksList);
					userMobileLoginDTO.setPreferredMusicList(preferredMusicList);
					response.setObject(userMobileLoginDTO);
					response.setResponse(Result.INVALID_AGE.name());
					response.setResponseMessage(Result.INVALID_AGE.getValue());
				}else {

					boolean emailFlag = true;
					boolean mobileFlag = true;
					boolean preferencesFlag = true;
					
					UserDTO userDTO = userService.getUserDetailsByUUID(userProfileDTO.getUserUUID());
					
					if (StringUtils.isNotBlank(userDTO.getUserEmail())
							&& userDTO.getUserEmail().toLowerCase().trim().equalsIgnoreCase(userProfileDTO.getEmail().toLowerCase().trim())) {
						response.setResponse(Result.VALID_EMAIL_DATA.name());
						response.setResponseMessage(Result.VALID_EMAIL_DATA.getValue());
					} else {
						boolean flag = userService.isUserExistedByEmailId(userProfileDTO.getEmail());
						if (flag == true) {
							response.setResponse(Result.INVALID_EMAIL_DATA.name());
							response.setResponseMessage(Result.INVALID_EMAIL_DATA.getValue());
							emailFlag = false;
						} else {
							response.setResponse(Result.VALID_EMAIL_DATA.name());
							response.setResponseMessage(Result.VALID_EMAIL_DATA.getValue());
						}
			
					}
					
					
					if (StringUtils.isNotBlank(userDTO.getUserMobile())
							&& userDTO.getUserMobile().trim().equalsIgnoreCase(userProfileDTO.getMobile().trim())) {
						response.setResponse(Result.VALID_MOBILE_DATA.name());
						response.setResponseMessage(Result.VALID_MOBILE_DATA.getValue());
					} else {
						boolean flag = userService.isUserExistedByMobileNumber(userProfileDTO.getMobile());
						if (flag == true) {
							response.setResponse(Result.INVALID_MOBILE_DATA.name());
							response.setResponseMessage(Result.INVALID_MOBILE_DATA.getValue());
							mobileFlag = false;
						} else {
							response.setResponse(Result.VALID_MOBILE_DATA.name());
							response.setResponseMessage(Result.VALID_MOBILE_DATA.getValue());
						}
			
					}
					
					if(StringUtils.isNotBlank(userProfileDTO.getPreferredDrinks())) {
						String[] preferredDrinks = userProfileDTO.getPreferredDrinks().trim().split(",");
						if(preferredDrinks.length > 3) {
							response.setResponse(Result.DRINK_PREFERENCES.getValue());
							response.setResponseMessage(Result.DRINK_PREFERENCES.getValue());
							preferencesFlag = false;
						}
					}
					
					if(StringUtils.isNotBlank(userProfileDTO.getPreferredMusic())) {
						String[] preferredMusic = userProfileDTO.getPreferredMusic().trim().split(",");
						if(preferredMusic.length > 3) {
							response.setResponse(Result.MUSIC_PREFERENCES.getValue());
							response.setResponseMessage(Result.MUSIC_PREFERENCES.getValue());
							preferencesFlag = false;
						}
					}
					
					
					if(emailFlag && mobileFlag && preferencesFlag) {
						userService.saveUserProfile(userProfileDTO);
					}
					
					userMobileLoginDTO = userService.getLoginMobileUserDetails(userProfileDTO.getUserUUID());
					List<String> preferredDrinksList = vendorServicesService.getServiceCategoriesListByServiceId(Constants.VENDOR_SERVICES_BOTTLE);
					List<String> preferredMusicList = vendorService.getAllcategoriesListByType(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE);
					userMobileLoginDTO.setPreferredDrinksList(preferredDrinksList);
					userMobileLoginDTO.setPreferredMusicList(preferredMusicList);
					response.setObject(userMobileLoginDTO);
					if(emailFlag && mobileFlag && preferencesFlag) {
						response.setResponse(Result.SUCCESS.name());
						response.setResponseMessage(Result.SUCCESS.getValue());
					}
				}
			} else {
				userMobileLoginDTO = userService.getLoginMobileUserDetails(userProfileDTO.getUserUUID());
				List<String> preferredDrinksList = vendorServicesService.getServiceCategoriesListByServiceId(Constants.VENDOR_SERVICES_BOTTLE);
				List<String> preferredMusicList = vendorService.getAllcategoriesListByType(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE);
				userMobileLoginDTO.setPreferredDrinksList(preferredDrinksList);
				userMobileLoginDTO.setPreferredMusicList(preferredMusicList);
				response.setObject(userMobileLoginDTO);
				response.setResponse(Result.FIELDS_MISSING.name());
				response.setResponseMessage(Result.FIELDS_MISSING.getValue());
			}

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	/***
	 * This service to validate current password
	 * @param currentPassword
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/validateCurrentPassword","/rest/validateCurrentPassword" }, method = RequestMethod.POST)
	public String validateCurrentPassword(
		@RequestParam(value = "currentPassword", required = true) String currentPassword,
		@RequestParam(value = "userUUID", required = true) String userUUID,
		HttpServletRequest request) throws Exception {
	
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			if (wayupartyPasswordEncoder.matches(currentPassword, userDTO.getPassword())) {
				return "valid";
			} else {
				return "invalid";
			}
	}
	
	/**
	 * This service to reset new password
	 * @param password
	 * @param userUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public Response<String> resetPassword(@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "userUUID", required = true) String userUUID, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (StringUtils.isNotBlank(password)) {
				userService.resetUserPassword(password, userUUID);
				response.setResponse(Result.SUCCESS.name());
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
	
	/***
	 * This service to add service to cart form mobile side
	 * @param userCartDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rest/addToCart", method = RequestMethod.POST)
	public Response<String> addToCart(@RequestBody UserCartDTO userCartDTO,
			HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();

		try {
			
			UserDTO userDTO = userService.getUserDetailsByUUID(userCartDTO.getUserUUID());
			VendorDTO vendorDTO = vendorService.getVendorDetails(userCartDTO.getVendorUUID());
			Long serviceId = vendorServicesService.getVendorMasterServiceIdByUUID(userCartDTO.getMasterServiceUUID());
			Long vendorId = userCartService.getVendorIdForExistingCartByUserId(userDTO.getUserId().longValue());
			String serviceName = vendorServicesService.getServiceNameByServiceId(serviceId);
			
			if(vendorId == null || vendorId == vendorDTO.getVendorId().longValue()) {
				
				if(StringUtils.isNotBlank(userCartDTO.getServiceOrderDate()) && StringUtils.isNotBlank(userCartDTO.getTimeslot())) {
					userCartDTO.setServiceId(serviceId);
					userCartDTO.setUserId(userDTO.getUserId().longValue());
					userCartDTO.setVendorId(vendorDTO.getVendorId().longValue());
					boolean flag = userCartService.getCartServiceExists(userCartDTO);
					if(flag == true) {
						response.setResponse(Result.CART_SERVICE_EXISTS.name());
						response.setResponseMessage(Result.CART_SERVICE_EXISTS.getValue());
					}else {
						if(serviceName.equalsIgnoreCase("Packages")) {
							if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())){
								String packageMenuItems = vendorServicesService.getPackageMenuDetailsByServiceId(userCartDTO.getServiceId());
								Integer itemsCount = 0;
								if(StringUtils.isNotBlank(packageMenuItems)) {
									JSONParser parser = new JSONParser();
									org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(packageMenuItems);
									 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
										    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
										    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
										    itemsCount = itemsCount+Integer.valueOf(itemsOffered);
									 }
								}
								
								String[] selectedItms = userCartDTO.getPackageMenuItems().split(",");
								if(selectedItms.length == itemsCount) {
									userCartService.addToCart(userCartDTO);
									response.setResponse(Result.SUCCESS.name());
									response.setResponseMessage(Result.SUCCESS.getValue());
								}else {
									response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
									response.setResponseMessage(Result.ADD_PACKAGE_MENU_ITEMS.getValue());
								}
							}else {
								response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
								response.setResponseMessage(Result.ADD_PACKAGE_MENU_ITEMS.getValue());
							}
						}else {
							userCartService.addToCart(userCartDTO);
							response.setResponse(Result.SUCCESS.name());
							response.setResponseMessage(Result.SUCCESS.getValue());
						}
					}
				}else {
					if(StringUtils.isBlank(userCartDTO.getServiceOrderDate())){
						response.setResponse(Result.INVALID_ORDER_DATE.name());
						response.setResponseMessage(Result.INVALID_ORDER_DATE.getValue());
					}else if(StringUtils.isBlank(userCartDTO.getTimeslot())){
						response.setResponse(Result.INVALID_TIME_SLOT.name());
						response.setResponseMessage(Result.INVALID_TIME_SLOT.getValue());
					}
				}
			
			}else {
				
				if(StringUtils.isNotBlank(userCartDTO.getServiceOrderDate()) && StringUtils.isNotBlank(userCartDTO.getTimeslot())) {
					
					List<UserCart> userCartList = userCartService.getCartListByVendorId(vendorId, userDTO.getUserId().longValue());
					if(userCartList != null && !userCartList.isEmpty()) {
						for(UserCart userCart : userCartList) {
							userCartService.deleteCartItem(userCart);
						}
					}
					
					userCartDTO.setServiceId(serviceId);
					userCartDTO.setUserId(userDTO.getUserId().longValue());
					userCartDTO.setVendorId(vendorDTO.getVendorId().longValue());
					boolean flag = userCartService.getCartServiceExists(userCartDTO);
					if(flag == true) {
						response.setResponse(Result.CART_SERVICE_EXISTS.name());
						response.setResponseMessage(Result.CART_SERVICE_EXISTS.getValue());
					}else {
						
						if(serviceName.equalsIgnoreCase("Packages")) {
							
							if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())) {
								String packageMenuItems = vendorServicesService.getPackageMenuDetailsByServiceId(userCartDTO.getServiceId());
								Integer itemsCount = 0;
								if(StringUtils.isNotBlank(packageMenuItems)) {
									JSONParser parser = new JSONParser();
									org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(packageMenuItems);
									 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
										    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
										    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
										    itemsCount = itemsCount+Integer.valueOf(itemsOffered);
									 }
								}
								
								String[] selectedItms = userCartDTO.getPackageMenuItems().split(",");
								if(selectedItms.length == itemsCount) {
									userCartService.addToCart(userCartDTO);
									response.setResponse(Result.SUCCESS.name());
									response.setResponseMessage(Result.SUCCESS.getValue());
								}else {
									response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
									response.setResponseMessage(Result.ADD_PACKAGE_MENU_ITEMS.getValue());
								}
							}else {
								response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
								response.setResponseMessage(Result.ADD_PACKAGE_MENU_ITEMS.getValue());
							}
							
						}else {
							userCartService.addToCart(userCartDTO);
							response.setResponse(Result.SUCCESS.name());
							response.setResponseMessage(Result.SUCCESS.getValue());
						}
						
					}
				}else {
					if(StringUtils.isBlank(userCartDTO.getServiceOrderDate())){
						response.setResponse(Result.INVALID_ORDER_DATE.name());
						response.setResponseMessage(Result.INVALID_ORDER_DATE.getValue());
					}else if(StringUtils.isBlank(userCartDTO.getTimeslot())){
						response.setResponse(Result.INVALID_TIME_SLOT.name());
						response.setResponseMessage(Result.INVALID_TIME_SLOT.getValue());
					}
				}
			}
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service to add service to cart from web side
	 * @param userCartDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/saveToCart", method = RequestMethod.POST)
	public Response<String> saveToCart(UserCartDTO userCartDTO, Authentication authentication,
			HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
			if(authentication == null || (authentication != null && authentication.getPrincipal() == null)) {
				response.setResponse(Result.UN_AUTHORIZED_USER.name());
			}else {
				BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
				userCartDTO.setUserId(loginUserId.longValue());
				Long vendorId = userCartService.getVendorIdForExistingCartByUserId(loginUserId.longValue());
				String serviceName = vendorServicesService.getServiceNameByServiceId(userCartDTO.getServiceId());
				if(vendorId == null || vendorId == userCartDTO.getVendorId()) {
					boolean flag = userCartService.getCartServiceExists(userCartDTO);
					if(flag == true) {
						response.setResponse(Result.CART_SERVICE_EXISTS.name());
						response.setResponseMessage(Result.CART_SERVICE_EXISTS.getValue());
					}else {
						if(serviceName.equalsIgnoreCase("Packages")) {
							if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())) {
								String packageMenuItems = vendorServicesService.getPackageMenuDetailsByServiceId(userCartDTO.getServiceId());
								Integer itemsCount = 0;
								if(StringUtils.isNotBlank(packageMenuItems)) {
									JSONParser parser = new JSONParser();
									org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(packageMenuItems);
									 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
										    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
										    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
										    itemsCount = itemsCount+Integer.valueOf(itemsOffered);
									 }
								}
								
								String[] selectedItms = userCartDTO.getPackageMenuItems().split(",");
								if(selectedItms.length == itemsCount) {
									userCartService.addToCart(userCartDTO);
									Long cartCount = userCartService.getUserCartCount(loginUserId.longValue());
									SessionManager.removeSessionAttribute(request, "cartCount");
									SessionManager.setSessionAttribute(request, "cartCount",cartCount);
									response.setResponse(Result.SUCCESS.name());
								}else {
									response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
								}
							}else {
								response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
							}
						}else {
							userCartService.addToCart(userCartDTO);
							Long cartCount = userCartService.getUserCartCount(loginUserId.longValue());
							SessionManager.removeSessionAttribute(request, "cartCount");
							SessionManager.setSessionAttribute(request, "cartCount",cartCount);
							response.setResponse(Result.SUCCESS.name());
						}
						
					}
				}else {
					
					List<UserCart> userCartList = userCartService.getCartListByVendorId(vendorId, loginUserId.longValue());
					if(userCartList != null && !userCartList.isEmpty()) {
						for(UserCart userCart : userCartList) {
							userCartService.deleteCartItem(userCart);
						}
					}
					
					if(serviceName.equalsIgnoreCase("Packages")) {
						if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())) {
							String packageMenuItems = vendorServicesService.getPackageMenuDetailsByServiceId(userCartDTO.getServiceId());
							Integer itemsCount = 0;
							if(StringUtils.isNotBlank(packageMenuItems)) {
								JSONParser parser = new JSONParser();
								org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(packageMenuItems);
								 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
									    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
									    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
									    itemsCount = itemsCount+Integer.valueOf(itemsOffered);
								 }
							}
							
							String[] selectedItms = userCartDTO.getPackageMenuItems().split(",");
							if(selectedItms.length == itemsCount) {
								userCartService.addToCart(userCartDTO);
								Long cartCount = userCartService.getUserCartCount(loginUserId.longValue());
								SessionManager.removeSessionAttribute(request, "cartCount");
								SessionManager.setSessionAttribute(request, "cartCount",cartCount);
								response.setResponse(Result.SUCCESS.name());
							}else {
								response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
							}
					}else {
						response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
					}
						
					}else {
						userCartService.addToCart(userCartDTO);
						Long cartCount = userCartService.getUserCartCount(loginUserId.longValue());
						SessionManager.removeSessionAttribute(request, "cartCount");
						SessionManager.setSessionAttribute(request, "cartCount",cartCount);
						response.setResponse(Result.SUCCESS.name());
					}
				}
		  }
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service to get user cart list
	 * @param userId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/getCartList", "/rest/getCartList" }, method = RequestMethod.GET)
	public ResponseList<CartDTO> getCartList(@RequestParam(value = "userUUID", required = false) String userUUID,
			HttpServletRequest request) throws Exception {
		
		Long userId = null;
		if(StringUtils.isNotBlank(userUUID)) {
			UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
			userId = userDTO.getUserId().longValue();
		}else {
			BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
			userId = loginUserId.longValue();
		}

		ResponseList<CartDTO> cartDTOList = new ResponseList<CartDTO>();
		try {
			List<CartDTO> cartList = userCartService.getUserCartList(userId);
			cartDTOList.setData(cartList);
			cartDTOList.setResponse(Result.SUCCESS.name());
			cartDTOList.setRazorpayKeyId(razorpayKeyId.trim());
			cartDTOList.setRazorpayKeySecretId(razorpayKeySecretId.trim());
		} catch (Exception e) {
			cartDTOList.setResponse(Result.AWKWARD.name());
			cartDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return cartDTOList;
	}
	
	/***
	 * This service for user login from mobile
	 * @param loginDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/loginRegisteredUser", method = RequestMethod.POST)
	public Response<UserMobileLoginDTO> loginRegisteredUser(@RequestBody UserLoginDTO loginDTO, HttpServletRequest request)
			throws Exception {

		Response<UserMobileLoginDTO> response = new Response<UserMobileLoginDTO>();
		try {
			UserMobileLoginDTO userMobileLoginDTO = new UserMobileLoginDTO();
			UserDTO userDTO = wayupartyLoginService.getUserPassWordByUsername(loginDTO.getUserName());
			if (userDTO != null && userDTO.getPassword() != null && userDTO.getUserRole().equalsIgnoreCase("ROLE_USER")) {
				if(StringUtils.isNotBlank(userDTO.getIsVerified()) && userDTO.getIsVerified().equalsIgnoreCase(Constants.STRING_Y)) {
					if(wayupartyPasswordEncoder.matches(loginDTO.getPassword(), userDTO.getPassword())) { 
						userMobileLoginDTO = userService.getLoginMobileUserDetails(userDTO.getUserUUID());
						
						List<String> preferredDrinksList = vendorServicesService.getServiceCategoriesListByServiceId(Constants.VENDOR_SERVICES_BOTTLE);
						List<String> preferredMusicList = vendorService.getAllcategoriesListByType(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE);
						userMobileLoginDTO.setPreferredDrinksList(preferredDrinksList);
						userMobileLoginDTO.setPreferredMusicList(preferredMusicList);
						
						response.setObject(userMobileLoginDTO);
						response.setResponse(Result.SUCCESS.name());
						response.setResponseMessage(Result.SUCCESS.getValue());
						response.setError("");
					} else {
						response.setObject(userMobileLoginDTO);
						response.setResponse(Result.INVALID_CREDENTIALS.name());
						response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
						response.setError("");
					}
				}else {
					response.setObject(userMobileLoginDTO);
					response.setResponse(Result.ACCOUNT_NOT_VERIFIED.name());
					response.setResponseMessage(Result.ACCOUNT_NOT_VERIFIED.getValue());
					response.setError("");
				}
			} else {
				response.setError("");
				response.setObject(userMobileLoginDTO);
				response.setResponse(Result.INVALID_CREDENTIALS.name());
				response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	
	
	@RequestMapping(value = "/ws/signupUser", method = RequestMethod.POST)
	public Response<UserMobileLoginDTO> signupUser(@RequestBody UserRegistrationDTO userRegistrationDTO, HttpServletRequest request) throws Exception {

		Response<UserMobileLoginDTO> response = new Response<UserMobileLoginDTO>();
		UserMobileLoginDTO userMobileLoginDTO = new UserMobileLoginDTO();
		try {

			if (Validator.validate(userRegistrationDTO)) {
				boolean flag = false;
				flag = userService.isUserExistedByEmailId(userRegistrationDTO.getEmail());
				if (flag == true) {
					response.setObject(userMobileLoginDTO);
					response.setResponse(Result.USER_EMAIL_EXISTED.name());
					response.setResponseMessage(Result.USER_EMAIL_EXISTED.getValue());
				} else {
					flag = userService.isUserExistedByMobileNumber(userRegistrationDTO.getMobile());
					if(flag == true) {
						response.setObject(userMobileLoginDTO);
						response.setResponse(Result.USER_MOBILE_EXISTED.name());
						response.setResponseMessage(Result.USER_MOBILE_EXISTED.getValue());
					}else {
						
						if(userRegistrationDTO.getConfirmPassword().trim().equalsIgnoreCase(userRegistrationDTO.getPassword().trim())) {
							if(validateEmail(userRegistrationDTO.getEmail())){
								userService.saveNewUserRegistration(userRegistrationDTO);
								UserLoginDTO userLoginDTO = userService.getLoginUserDetailsByEmail(userRegistrationDTO.getEmail());
								userMobileLoginDTO = userService.getLoginMobileUserDetails(userLoginDTO.getUserUUID());
								List<String> preferredDrinksList = vendorServicesService.getServiceCategoriesListByServiceId(Constants.VENDOR_SERVICES_BOTTLE);
								List<String> preferredMusicList = vendorService.getAllcategoriesListByType(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE);
								userMobileLoginDTO.setPreferredDrinksList(preferredDrinksList);
								userMobileLoginDTO.setPreferredMusicList(preferredMusicList);
								
								response.setObject(userMobileLoginDTO);
								response.setResponse(Result.SUCCESS.name());
								response.setResponseMessage(Result.SUCCESS.getValue());
								response.setError("");
							}else {
								response.setObject(userMobileLoginDTO);
								response.setResponse(Result.INVALID_EMAIL.name());
								response.setResponseMessage(Result.INVALID_EMAIL.getValue());
							}
							
						}else {
							response.setObject(userMobileLoginDTO);
							response.setResponse("Please fill up all details");
							response.setResponseMessage("Please fill up all details");
						}
					
					}
				}
				
			} else {
				response.setObject(userMobileLoginDTO);
				response.setResponse("Please fill up all details");
				response.setResponseMessage("Please fill up all details");
			}

		} catch (Exception e) {
			response.setObject(userMobileLoginDTO);
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	private boolean validateEmail(String email) {
		 
		  String emailId = email.trim();
		  Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		  Matcher matcher = pattern.matcher(emailId);
		  return matcher.matches();
		  
	}
	
	
	
	/***
	 * This service to remove cart item from moblie
	 * @param cartUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rest/removeCartItem", method = RequestMethod.POST)
	public Response<String> removeCartItem(@RequestParam(value = "cartUUID", required = true) String cartUUID,
			HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			UserCart userCart = userCartService.getUserCartbyUUID(cartUUID);
			userCartService.deleteCartItem(userCart);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.CART_ITEM_REMOVED.getValue());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service to remove cart item from moblie
	 * @param cartUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	public Response<String> sendSMS(@RequestParam(value = "userUUID", required = true) String userUUID,
			HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			if (StringUtils.isNotBlank(userUUID)) {
				UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
				if (StringUtils.isNotBlank(userDTO.getUserMobile())) {
					userService.sendSMS(userDTO,userUUID);
				}
			}
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	/***
	 * This service to remove cart item from web
	 * @param cartUUID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeCartItem", method = RequestMethod.POST)
	public Response<String> deleteCartItem(@RequestParam(value = "cartUUID", required = true) String cartUUID,
			HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();
		BigInteger loginUserId = (BigInteger) SessionManager.getSessionAttribute(request, "loginUserId");
		try {
			UserCart userCart = userCartService.getUserCartbyUUID(cartUUID);
			userCartService.deleteCartItem(userCart);
			Long cartCount = userCartService.getUserCartCount(loginUserId.longValue());
			SessionManager.removeSessionAttribute(request, "cartCount");
			SessionManager.setSessionAttribute(request, "cartCount",cartCount);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.CART_ITEM_REMOVED.getValue());
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	/***
	 * This service for user login from mobile
	 * @param loginDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/loginCartUser", method = RequestMethod.POST)
	public Response<UserLoginDTO> loginCartUser(UserLoginDTO loginDTO, HttpServletRequest request)
			throws Exception {

		Response<UserLoginDTO> response = new Response<UserLoginDTO>();
		try {
			UserLoginDTO userLoginDTO = new UserLoginDTO();
			UserDTO userDTO = wayupartyLoginService.getUserPassWordByUsername(loginDTO.getUserName());
			if (userDTO != null && userDTO.getPassword() != null) {
				if(wayupartyPasswordEncoder.matches(loginDTO.getPassword(), userDTO.getPassword())) {
					if(StringUtils.isNotBlank(userDTO.getIsVerified()) && userDTO.getIsVerified().equalsIgnoreCase("Y")) {
						userLoginDTO = userService.getLoginUserDetails(userDTO.getUserId().longValue());
						response.setObject(userLoginDTO);
						response.setResponse(Result.SUCCESS.name());
						response.setResponseMessage(Result.SUCCESS.getValue());
					}else {
						response.setResponse(Result.ACCOUNT_NOT_VERIFIED.name());
						response.setResponseMessage(Result.ACCOUNT_NOT_VERIFIED.getValue());
					}
					
				} else {
					response.setResponse(Result.INVALID_CREDENTIALS.name());
					response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
				}
			} else {
				response.setResponse(Result.INVALID_CREDENTIALS.name());
				response.setResponseMessage(Result.INVALID_CREDENTIALS.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	/***
	 * This service for save to cart from home login
	 * @param userCartDTO
	 * @param authentication
	 * @param request
	 * @param httpResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ws/saveToHomeLoginCart", method = RequestMethod.POST)
	public Response<String> saveToHomeLoginCart(UserCartDTO userCartDTO, HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
				Long vendorId = userCartService.getVendorIdForExistingCartByUserId(userCartDTO.getUserId());
				String serviceName = vendorServicesService.getServiceNameByServiceId(userCartDTO.getServiceId());
				if(vendorId == null || vendorId == userCartDTO.getVendorId()) {
					boolean flag = userCartService.getCartServiceExists(userCartDTO);
					if(flag == true) {
						response.setResponse(Result.CART_SERVICE_EXISTS.name());
						response.setResponseMessage(Result.CART_SERVICE_EXISTS.getValue());
					}else {
						
						if(serviceName.equalsIgnoreCase("Packages")) {
							if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())) {
								String packageMenuItems = vendorServicesService.getPackageMenuDetailsByServiceId(userCartDTO.getServiceId());
								Integer itemsCount = 0;
								if(StringUtils.isNotBlank(packageMenuItems)) {
									JSONParser parser = new JSONParser();
									org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(packageMenuItems);
									 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
										    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
										    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
										    itemsCount = itemsCount+Integer.valueOf(itemsOffered);
									 }
								}
								
								String[] selectedItms = userCartDTO.getPackageMenuItems().split(",");
								if(selectedItms.length == itemsCount) {
									userCartService.addToCart(userCartDTO);
									Long cartCount = userCartService.getUserCartCount(userCartDTO.getUserId());
									SessionManager.removeSessionAttribute(request, "cartCount");
									SessionManager.setSessionAttribute(request, "cartCount",cartCount);
									response.setResponse(Result.SUCCESS.name());
								}else {
									response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
								}
							}else {
								response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
							}
					
							
						}else {
							userCartService.addToCart(userCartDTO);
							Long cartCount = userCartService.getUserCartCount(userCartDTO.getUserId());
							SessionManager.removeSessionAttribute(request, "cartCount");
							SessionManager.setSessionAttribute(request, "cartCount",cartCount);
							response.setResponse(Result.SUCCESS.name());
						}
						
					}
				}else {
					
					List<UserCart> userCartList = userCartService.getCartListByVendorId(vendorId, userCartDTO.getUserId().longValue());
					if(userCartList != null && !userCartList.isEmpty()) {
						for(UserCart userCart : userCartList) {
							userCartService.deleteCartItem(userCart);
						}
					}
					
					if(serviceName.equalsIgnoreCase("Packages")) {
						if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())) {
							String packageMenuItems = vendorServicesService.getPackageMenuDetailsByServiceId(userCartDTO.getServiceId());
							Integer itemsCount = 0;
							if(StringUtils.isNotBlank(packageMenuItems)) {
								JSONParser parser = new JSONParser();
								org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(packageMenuItems);
								 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
									    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
									    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
									    itemsCount = itemsCount+Integer.valueOf(itemsOffered);
								 }
							}
							
							String[] selectedItms = userCartDTO.getPackageMenuItems().split(",");
							if(selectedItms.length == itemsCount) {
								userCartService.addToCart(userCartDTO);
								Long cartCount = userCartService.getUserCartCount(userCartDTO.getUserId());
								SessionManager.removeSessionAttribute(request, "cartCount");
								SessionManager.setSessionAttribute(request, "cartCount",cartCount);
								response.setResponse(Result.SUCCESS.name());
							}else {
								response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
							}
						}else {
							response.setResponse(Result.ADD_PACKAGE_MENU_ITEMS.name());
						}
						
					}else {
						userCartService.addToCart(userCartDTO);
						Long cartCount = userCartService.getUserCartCount(userCartDTO.getUserId());
						SessionManager.removeSessionAttribute(request, "cartCount");
						SessionManager.setSessionAttribute(request, "cartCount",cartCount);
						response.setResponse(Result.SUCCESS.name());
					}
					
				}
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public Response<RazorPayOrders> razorpayOrders(RazorPayOrders razorPayOrders, HttpServletRequest request)throws Exception {

		Response<RazorPayOrders> response = new Response<RazorPayOrders>();
		try {  
			
	    int paise = (int) Math.round(100*razorPayOrders.getCartAmount());
		RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId.trim(), razorpayKeySecretId.trim());
		JSONObject orderRequest = new JSONObject();  
	    orderRequest.put("amount", paise); 
		orderRequest.put("currency", razorPayOrders.getCurrency());
		orderRequest.put("receipt", RandomCodeHelper.generateRandomRazorPayOrderId());  
		Order order = razorpayClient.Orders.create(orderRequest);
		RazorPayOrders orders = new RazorPayOrders();
		orders.setOrderId(order.get("id").toString());
		orders.setAmount(order.get("amount"));
		orders.setCurrency(order.get("currency"));
		orders.setReceipt(order.get("receipt"));
		response.setObject(orders);
		response.setResponse(Result.SUCCESS.name());
		response.setResponseMessage(Result.SUCCESS.getValue());
		} catch (RazorpayException e) {  
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}		
		return response;
	 }
	
	
	@RequestMapping(value = "/rest/orders", method = RequestMethod.POST)
	public Response<RazorPayOrders> razorpayMobileOrders(@RequestBody RazorPayOrders razorPayOrders, HttpServletRequest request)throws Exception {

		Response<RazorPayOrders> response = new Response<RazorPayOrders>();
		try {  
			
	    int paise = (int) Math.round(100*razorPayOrders.getCartAmount());
		RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId.trim(), razorpayKeySecretId.trim());
		JSONObject orderRequest = new JSONObject();  
	    orderRequest.put("amount", paise); 
		orderRequest.put("currency", razorPayOrders.getCurrency());
		orderRequest.put("receipt", RandomCodeHelper.generateRandomRazorPayOrderId());  
		Order order = razorpayClient.Orders.create(orderRequest);
		RazorPayOrders orders = new RazorPayOrders();
		orders.setOrderId(order.get("id").toString());
		orders.setAmount(order.get("amount"));
		orders.setCurrency(order.get("currency"));
		orders.setReceipt(order.get("receipt"));
		response.setObject(orders);
		response.setResponse(Result.SUCCESS.name());
		response.setResponseMessage(Result.SUCCESS.getValue());
		} catch (RazorpayException e) {  
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}		
		return response;
	 }
	
	
	
	
	@RequestMapping(value = "/ws/validateEmail", method = RequestMethod.POST)
	public Response<VerificationDTO> validateEmail(@RequestParam(value = "email", required = true) String email, HttpServletRequest request) throws Exception {
		
		Response<VerificationDTO> response = new Response<VerificationDTO>();
		VerificationDTO verificationDTO = new VerificationDTO();
		try {
				boolean isUserExisted = userService.isUserExistedByEmailId(email);
				if(isUserExisted) {
					String verificationUUID = userService.getForgotPasswordVerificationCode(email);
					verificationDTO.setVerificationUUID(verificationUUID);
					response.setObject(verificationDTO);
					response.setResponse(Result.SUCCESS.name());
					response.setResponseMessage(Result.SUCCESS.getValue());
				}else {
					response.setObject(verificationDTO);
					response.setResponse(Result.NOT_REGISTERED_EMAIL.name());
					response.setResponseMessage(Result.NOT_REGISTERED_EMAIL.getValue());
				}
		} catch (Exception e) {
			response.setObject(verificationDTO);
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	@RequestMapping(value = "/ws/resetUserPassword", method = RequestMethod.POST)
	public Response<String> resetUserPassword(VerificationDTO verificationDTO, HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<String>();
		try {
				VerificationDTO verifyDTO = userService.getVerificationDetails(verificationDTO.getVerificationUUID());
				if(verifyDTO != null) {
					if(StringUtils.isNotBlank(verificationDTO.getVerificationCode()) && 
							verifyDTO.getVerificationCode().trim().equalsIgnoreCase(verificationDTO.getVerificationCode().trim())) {
						userService.resetUserPassword(verificationDTO.getPassword(), verifyDTO.getUserUUID());
						PasswordVerification passwordVerification = userService.getPasswordVerificationByUUID(verificationDTO.getVerificationUUID());
						passwordVerification.setIsVerified(Constants.STRING_Y);
						userService.savePasswordVerification(passwordVerification);
						response.setResponse(Result.SUCCESS.name());
						response.setResponseMessage(Result.SUCCESS.getValue());
					}else {
						response.setResponse(Result.INVALID_VERIFICATION_CODE.name());
						response.setResponseMessage(Result.INVALID_VERIFICATION_CODE.getValue());
					}
					
				}else {
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
	
	
	
	@RequestMapping(value = "/ws/resetLoginUserPassword", method = RequestMethod.POST)
	public Response<String> resetLoginUserPassword(@RequestBody VerificationDTO verificationDTO, HttpServletRequest request) throws Exception {
		
		Response<String> response = new Response<String>();
		try {
			
			if (Validator.validate(verificationDTO)) {
				
				VerificationDTO verifyDTO = userService.getVerificationDetails(verificationDTO.getVerificationUUID());
				if(verifyDTO != null) {
					if(StringUtils.isNotBlank(verificationDTO.getVerificationCode()) && 
							verifyDTO.getVerificationCode().trim().equalsIgnoreCase(verificationDTO.getVerificationCode().trim())) {
						
						if(verificationDTO.getConfirmPassword().trim().equalsIgnoreCase(verificationDTO.getPassword().trim())) {
							userService.resetUserPassword(verificationDTO.getPassword(), verifyDTO.getUserUUID());
							PasswordVerification passwordVerification = userService.getPasswordVerificationByUUID(verificationDTO.getVerificationUUID());
							passwordVerification.setIsVerified(Constants.STRING_Y);
							userService.savePasswordVerification(passwordVerification);
							response.setResponse(Result.SUCCESS.name());
							response.setResponseMessage(Result.SUCCESS.getValue());
						}else {
							response.setResponse(Result.PASSWORD_NOT_MATCHED.name());
							response.setResponseMessage(Result.PASSWORD_NOT_MATCHED.getValue());
						}
						
					}else {
						response.setResponse(Result.INVALID_VERIFICATION_CODE.name());
						response.setResponseMessage(Result.INVALID_VERIFICATION_CODE.getValue());
					}
					
				}else {
					response.setResponse(Result.INVALID_DATA.name());
					response.setResponseMessage(Result.INVALID_DATA.getValue());
				}
			}else {
				response.setResponse("Please fill up all details");
				response.setResponseMessage("Please fill up all details");
			}
				
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	
	@RequestMapping(value = "/getGuestUserEmail", method = RequestMethod.POST)
	public @ResponseBody Response<VendorGuestUsersDTO> getGuestUserEmail(
		@RequestParam(value = "email", required = false) String email, HttpServletRequest request)
		throws Exception {
	Response<VendorGuestUsersDTO> response = new Response<VendorGuestUsersDTO>();
	
	try {
		VendorGuestUsersDTO guestUserDetailsDTO = userService.getGuestUserDetailsByEmail(email);
		response.setObject(guestUserDetailsDTO);
		response.setResponse(Result.SUCCESS.name());
		response.setResponseMessage(Result.SUCCESS.getValue());
	
	} catch (Exception e) {
		response.setResponse(Result.AWKWARD.name());
		response.setResponseMessage(Result.AWKWARD.getValue());
		e.printStackTrace();
	}
	
	return response;
	}
	
	
	@RequestMapping(value = "/getGuestUserMobileNumber", method = RequestMethod.POST)
	public @ResponseBody Response<VendorGuestUsersDTO> getGuestUserMobileNumber(
		@RequestParam(value = "mobileNumber", required = false) String mobileNumber, HttpServletRequest request)
		throws Exception {
	Response<VendorGuestUsersDTO> response = new Response<VendorGuestUsersDTO>();
	
	try {
		VendorGuestUsersDTO guestUserDetailsDTO = userService.getGuestUserDetailsByMobileNumber(mobileNumber);
			response.setObject(guestUserDetailsDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());
	
	} catch (Exception e) {
		response.setResponse(Result.AWKWARD.name());
		response.setResponseMessage(Result.AWKWARD.getValue());
		e.printStackTrace();
	}
	
	return response;
	}
	
	
	@RequestMapping(value = "/saveGuestUser", method = RequestMethod.POST)
	public Response<String> saveGuestUser(GuestUserDTO guestUserDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(guestUserDTO)) {
				boolean flag = userService.isUserExistedByEmailId(guestUserDTO.getEmail());
				if(flag == true) {
					String userUUID = userService.getUserUUIDByEmail(guestUserDTO.getEmail());
					guestUserDTO.setUserUUID(userUUID);
					boolean guestFlag = vendorGuestsService.isUserExistedAsGuest(userUUID, guestUserDTO.getVendorUUID());
					if(guestFlag == true) {
						response.setResponse(Result.GUEST_USER_EXISTS.name());
						response.setResponseMessage(Result.GUEST_USER_EXISTS.getValue());
					}else {
						vendorGuestsService.saveGuestUser(guestUserDTO);
						response.setResponse(Result.SUCCESS.name());
						response.setResponseMessage(Result.SUCCESS.getValue());
					}
				}else {
					vendorGuestsService.saveGuestUser(guestUserDTO);
					response.setResponse(Result.SUCCESS.name());
					response.setResponseMessage(Result.SUCCESS.getValue());
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
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@RequestMapping(value = "/getGuestUserList", method = RequestMethod.GET)
	public ResponseList<VendorGuestUsersDTO> getOrdersList(VendorGuestUsersDTO guestUserDTO, HttpServletRequest request) {
		ResponseList<VendorGuestUsersDTO> guestsResponseList = new ResponseList<VendorGuestUsersDTO>();

		try {
			
			String[] tableColumns = { "guestUserName", "email", "guestCode",  "startDate" , "guestActions" };
			int sortColumnIndex = 1; // by default sorting using service order date
			String sortColumnOrder = "desc"; // by default assending order
			if (guestUserDTO.getOrder() != null && !guestUserDTO.getOrder().isEmpty()) {
				sortColumnIndex = Integer.parseInt(guestUserDTO.getOrder().get(0).get("column"));
				sortColumnOrder = guestUserDTO.getOrder().get(0).get("dir");
			}
			guestUserDTO.setSortColumn(tableColumns[sortColumnIndex]);
			guestUserDTO.setSortOrder(sortColumnOrder);
			guestsResponseList = vendorGuestsService.getGuestUsersList(guestUserDTO);
			guestsResponseList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			guestsResponseList.setResponse(Result.AWKWARD.name());
			guestsResponseList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return guestsResponseList;
	}
	
	
	@RequestMapping(value = "/userGuestsList", method = RequestMethod.GET)
	public ResponseList<VendorGuestUsersDTO> userGuestsList(VendorGuestUsersDTO guestUserDTO, HttpServletRequest request) {
		ResponseList<VendorGuestUsersDTO> guestsResponseList = new ResponseList<VendorGuestUsersDTO>();

		try {
			
			String[] tableColumns = { "clubName", "clubLocation", "guestCode",  "startDate" , "guestActions" };
			int sortColumnIndex = 1; // by default sorting using service order date
			String sortColumnOrder = "desc"; // by default assending order
			if (guestUserDTO.getOrder() != null && !guestUserDTO.getOrder().isEmpty()) {
				sortColumnIndex = Integer.parseInt(guestUserDTO.getOrder().get(0).get("column"));
				sortColumnOrder = guestUserDTO.getOrder().get(0).get("dir");
			}
			guestUserDTO.setSortColumn(tableColumns[sortColumnIndex]);
			guestUserDTO.setSortOrder(sortColumnOrder);
			guestsResponseList = vendorGuestsService.getUserGuestsList(guestUserDTO);
			guestsResponseList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			guestsResponseList.setResponse(Result.AWKWARD.name());
			guestsResponseList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return guestsResponseList;
	}
	
	@RequestMapping(value = { "/getGuestUserDetails", "/ws/getGuestUserDetails" }, method = RequestMethod.GET)
	public Response<VendorGuestUsersDTO> getGuestUserDetails(@RequestParam(value = "guestUUID", required = true) String guestUUID,
			HttpServletRequest request) throws Exception {
		
		Response<VendorGuestUsersDTO> response = new Response<VendorGuestUsersDTO>();
		try {
			VendorGuestUsersDTO vendorGuestUsersDTO = vendorGuestsService.getGuestUseDetails(guestUUID);
			response.setObject(vendorGuestUsersDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());
				
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	@RequestMapping(value = "/resendUserInvitation", method = RequestMethod.POST)
	public Response<String> resendVendorInvitation(@RequestParam(value = "userUUID", required = true) String userUUID, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			userService.resendUserInvation(userUUID);
			response.setResponse(Result.SUCCESS.name());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = { "/getSms", "/rest/getSms" }, method = RequestMethod.POST)
	public void getTwilioSms(HttpServletRequest request)throws Exception {

		final String ACCOUNT_SID = accountsid;
	    final String AUTH_TOKEN = authtocken;

	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("(+91)7569650838"),
	                new com.twilio.type.PhoneNumber("+12544556378"),
	                "Hi Big bro.. nidrapotunava!")
	            .create();

	        System.out.println(message);
	        System.out.println(message.getSid());
	}
	
	
	@RequestMapping(value = "/rest/guestClubsList", method = RequestMethod.GET)
	public ResponseList<UserGuestClubsDTO> guestClubsList(@RequestParam(value = "userUUID", required = true) String userUUID,
			HttpServletRequest request) {
		ResponseList<UserGuestClubsDTO> guestsResponseList = new ResponseList<UserGuestClubsDTO>();

		try {
			List<UserGuestClubsDTO> guestClubsList = vendorGuestsService.getGuestClubsList(userUUID);
			guestsResponseList.setData(guestClubsList);
			guestsResponseList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			guestsResponseList.setResponse(Result.AWKWARD.name());
			guestsResponseList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return guestsResponseList;
	}
	
	@RequestMapping(value = "/rest/getGuestClubDetails", method = RequestMethod.GET)
	public Response<UserGuestClubsDetailsDTO> getGuestClubDetails(@RequestParam(value = "guestUUID", required = true) String guestUUID,
			HttpServletRequest request) throws Exception {
		
		Response<UserGuestClubsDetailsDTO> response = new Response<UserGuestClubsDetailsDTO>();
		try {
			UserGuestClubsDetailsDTO userGuestClubsDetailsDTO = vendorGuestsService.getGuestClubDetails(guestUUID);
			response.setObject(userGuestClubsDetailsDTO);
			response.setResponse(Result.SUCCESS.name());
			response.setResponseMessage(Result.SUCCESS.getValue());
				
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
	@RequestMapping(value = { "/getPopularCities", "/rest/getPopularCities", "/ws/getPopularCities" }, method = RequestMethod.GET)
	public ResponseList<PopularCitiesDTO> getPopularCities(HttpServletRequest request) throws Exception {
		
		ResponseList<PopularCitiesDTO> citiesDTOList = new ResponseList<PopularCitiesDTO>();
		try {
			List<PopularCitiesDTO> citiesList = userService.getPopularCities();
			citiesDTOList.setData(citiesList);
			citiesDTOList.setResponse(Result.SUCCESS.name());
			citiesDTOList.setResponseMessage(Result.SUCCESS.getValue());
		} catch (Exception e) {
			citiesDTOList.setResponse(Result.AWKWARD.name());
			citiesDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return citiesDTOList;
	}
	
	
	@RequestMapping(value = "/getRegisteredUsersList", method = RequestMethod.GET)
	public ResponseList<RegisteredUsersDTO> getRegisteredUsersList(RegisteredUsersDTO registeredUsersDTO, HttpServletRequest request) {
		ResponseList<RegisteredUsersDTO> usersResponseList = new ResponseList<RegisteredUsersDTO>();

		try {
			
			String[] tableColumns = { "userName", "email", "mobileNumber",  "registeredDate" , "userActions" };
			int sortColumnIndex = 1; // by default sorting using service order date
			String sortColumnOrder = "desc"; // by default assending order
			if (registeredUsersDTO.getOrder() != null && !registeredUsersDTO.getOrder().isEmpty()) {
				sortColumnIndex = Integer.parseInt(registeredUsersDTO.getOrder().get(0).get("column"));
				sortColumnOrder = registeredUsersDTO.getOrder().get(0).get("dir");
			}
			registeredUsersDTO.setSortColumn(tableColumns[sortColumnIndex]);
			registeredUsersDTO.setSortOrder(sortColumnOrder);
			usersResponseList = userService.getRegisteredUsersList(registeredUsersDTO);
			usersResponseList.setResponse(Result.SUCCESS.name());
		} catch (Exception e) {
			usersResponseList.setResponse(Result.AWKWARD.name());
			usersResponseList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return usersResponseList;
	}
	
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@RequestMapping(value = "/userActiveStatus", method = RequestMethod.POST)
	public Response<String> userActiveStatus(RegisteredUsersDTO registeredUsersDTO, HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			User user = userService.getUserByUUID(registeredUsersDTO.getUserUUID());
			if(user != null) {
				if(StringUtils.isNotBlank(registeredUsersDTO.getUserActive()) && registeredUsersDTO.getUserActive().equalsIgnoreCase(Constants.STRING_Y)) {
					user.setStatus(1);
				}else {
					user.setStatus(0);
				}
				userService.saveUser(user);
			}
			response.setResponse(Result.SUCCESS.name());

		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		return response;
	}
	
	
	@RequestMapping(value = "/saveOrderRatings", method = RequestMethod.POST)
	public Response<String> saveOrderRatings(PlaceOrderRatingsDTO placeOrderRatingsDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(placeOrderRatingsDTO)) {
				String result = userService.saveUserPlaceOrderRatings(placeOrderRatingsDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
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
	
	@RequestMapping(value = "/rest/savePlaceOrderRatings", method = RequestMethod.POST)
	public Response<String> savePlaceOrderRatings(@RequestBody PlaceOrderRatingsDTO placeOrderRatingsDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(placeOrderRatingsDTO)) {
				String result = userService.saveUserPlaceOrderRatings(placeOrderRatingsDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
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

	
	/***
	 * This service to get Special Package Banners list
	 * @param userId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/ws/getSpecialPackageBannersList" }, method = RequestMethod.GET)
	public ResponseList<SpecialPackageDetailsDTO> getSpecialPackageBannersList(HttpServletRequest request) throws Exception {
		
		ResponseList<SpecialPackageDetailsDTO> specialPackageDTOList = new ResponseList<SpecialPackageDetailsDTO>();
		try {
			List<SpecialPackageDetailsDTO> specialPackageList = vendorService.getSpecialPackageList();
			specialPackageDTOList.setData(specialPackageList);
			specialPackageDTOList.setResponse(Result.SUCCESS.name());
			specialPackageDTOList.setRazorpayKeyId(razorpayKeyId.trim());
			specialPackageDTOList.setRazorpayKeySecretId(razorpayKeySecretId.trim());
		} catch (Exception e) {
			specialPackageDTOList.setResponse(Result.AWKWARD.name());
			specialPackageDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		
		return specialPackageDTOList;
	}
	
	/***
	 * This service to get coupons list
	 * @param userId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/ws/getCouponList" }, method = RequestMethod.GET)
	public ResponseList<CouponDTO> getCouponList(HttpServletRequest request) throws Exception {
		
		ResponseList<CouponDTO> couponsDTOList = new ResponseList<CouponDTO>();
		try {
			List<CouponDTO> couponsList = vendorService.getCouponList();
			couponsDTOList.setData(couponsList);
			couponsDTOList.setResponse(Result.SUCCESS.name());
			couponsDTOList.setRazorpayKeyId(razorpayKeyId.trim());
			couponsDTOList.setRazorpayKeySecretId(razorpayKeySecretId.trim());
		} catch (Exception e) {
			couponsDTOList.setResponse(Result.AWKWARD.name());
			couponsDTOList.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}
		
		return couponsDTOList;
	}
	
	@RequestMapping(value = "/saveCoupon", method = RequestMethod.POST)
	public Response<String> saveCoupon(CouponDTO couponDTO, 
			HttpServletRequest request) throws Exception {

		Response<String> response = new Response<>();
		try {

			if (Validator.validate(couponDTO)) {
				String result = vendorService.saveCoupon(couponDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				response.setResponse(Result.SUCCESS.name());
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

}
