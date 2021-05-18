package com.acculytixs.wayuparty.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acculytixs.wayuparty.dto.events.EventTicketsDTO;
import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.services.WayupartyLoginService;
import com.acculytixs.wayuparty.services.user.UserService;
import com.acculytixs.wayuparty.services.vendor.EventsService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.services.vendor.VendorServicesService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.SessionMaintainanceData;
import com.acculytixs.wayuparty.util.SessionManager;

@Controller
public class WayupartyHomeController {

	@Value("${appUrl}")
	private String appUrl;
	
	@Value("${googleMapsLocationApiKey}")
	private String googleMapsLocationApiKey;
	
	
	@Value("${static.path}")
	private String staticPath;
	
	@Autowired
	WayupartyLoginService wayupartyLoginService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	VendorServicesService vendorServicesService;
	
	@Autowired
	EventsService eventService;
	
	
	@GetMapping("favicon.ico")
	@ResponseBody
	public void returnNoFavicon() {
	}
	
	
	@RequestMapping(value = {"/", "/home"})
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.addObject("googleMapsLocationApiKey", googleMapsLocationApiKey);
		model.setViewName("website/index"); 
		return model; 
	}
	
	@RequestMapping(value = "/clubs")
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.addObject("googleMapsLocationApiKey", googleMapsLocationApiKey);

//			try {
//				List<SpecialPackageDetailsDTO> specialPackageList = vendorService.getSpecialPackageList();
//				model.addObject("specialPackageList", specialPackageList);
//				model.addObject("specialPackageListSize", specialPackageList.size());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
		model.setViewName("home"); 
		return model; 
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("login"); 
		return model; 
	}
	
	@RequestMapping(value = "/deals")
	public ModelAndView deals(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.addObject("googleMapsLocationApiKey", googleMapsLocationApiKey);
		model.setViewName("deals"); 
		return model; 
	}
	@RequestMapping(value = "/services")
	public ModelAndView services(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("services"); 
		return model; 
	}
	@RequestMapping(value = "/privacyPolicy")
	public ModelAndView privacyPolicy(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("privacyPolicy"); 
		return model; 
	}
	@RequestMapping(value = "/termsAndConditions")
	public ModelAndView termsAndConditions(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("termsAndConditions"); 
		return model; 
	}
	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public ModelAndView displayErrorPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errorPage");
		return model;

	}
	@RequestMapping(value = "/createCoupon")
	public ModelAndView createCoupon(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.addObject("googleMapsLocationApiKey", googleMapsLocationApiKey);
		model.setViewName("createCoupon"); 
		return model; 
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		model.setViewName("accessDenied");
		return model;

	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView permissionDenied() {

		ModelAndView model = new ModelAndView();
		model.setViewName("accessDenied");
		return model;

	}
	
	@RequestMapping(value = "/forgotPassword")
	public ModelAndView forgotPassword(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("forgotPassword"); 
		return model; 
	}
	
	
	@RequestMapping(value = "/ws/resetPassword/{verificationUUID}", method = RequestMethod.GET)
	public ModelAndView resetPassword(@PathVariable String verificationUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("verificationUUID", verificationUUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("appUrl", appUrl);
		model.setViewName("resetPassword"); 
		return model; 
	}
	
	@RequestMapping(value = "/registerUser")
	public ModelAndView registerUser(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("appUrl", appUrl);
		model.setViewName("user/registerUser"); 
		return model; 
	}
	
	@RequestMapping(value = "/ws/activateEmail")
	public ModelAndView privacyPloicy(@RequestParam(value = "tocken", required = true) String tocken,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			User user = userService.getUserByUUID(tocken);
			if(user != null) {
				user.setIsEmailVerified(Constants.STRING_Y);
				userService.saveUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("appUrl", appUrl);
		model.setViewName("verifyAccount"); 
		return model; 
	}
	
	/***
	 * This service for dashbord (landing page)
	 * @param request
	 * @param authentication
	 * @return
	 */
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(HttpServletRequest request,Authentication authentication) {
		ModelAndView model = new ModelAndView();
		if(authentication == null || (authentication != null && authentication.getPrincipal() == null)) {
			model.setViewName("login");
		}else {
			String userName =  (String) authentication.getPrincipal();
			UserDTO userDTO = wayupartyLoginService.getLoggedInUserDetailsByUserName(userName);
			SessionManager.setSessionAttribute(request, "userId",userDTO.getUserId());
			SessionManager.setSessionAttribute(request, "appUrl",appUrl);
			
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			if(userDTO.getUserRole().equalsIgnoreCase("ROLE_ADMIN")) {
				model.setViewName("dashboard/vendorDashboard");
			}else if(userDTO.getUserRole().equalsIgnoreCase("ROLE_USER")) {
			
//				try {
//					List<SpecialPackageDetailsDTO> specialPackageList = vendorService.getSpecialPackageList();
//					model.addObject("specialPackageList", specialPackageList);
//					model.addObject("specialPackageListSize", specialPackageList.size());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			
				model.setViewName("dashboard/userDashboard");
			}else {
				model.setViewName("dashboard/dashboard");
			}
			
		}
		
		return model;
	}
	
	/***
	 * This service to get new vendor adding page. Form this form page we can add new vendor.
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@RequestMapping("/vendor")
	public ModelAndView vendor(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.setViewName("vendor/vendor");
		
		    return model;
	}
	
	/***
	 * This service to get new special Package adding page. Form this form page we can add new special Package.
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@RequestMapping("/specialPackage")
	public ModelAndView specialPackage(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendor/specialPackage");
		
		    return model;
	}
	
	/***
	 * This service to get all vendor profile details
	 * @param request
	 * @return
	 */
	@RequestMapping("/vendorProfile")
	public ModelAndView vendorProfileDetails(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			String loginUserRole = (String) SessionManager.getSessionAttribute(request, "loginUserRole");
			
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			if(loginUserRole.equalsIgnoreCase("ROLE_ADMIN")) {
				sessionData.setNav(Constants.EXPLORE);
			}else {
				sessionData.setNav(Constants.DASHBOARD);
			}
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
				
				List<ServicesDTO> servicesList = vendorService.getVendorServices();
				model.addObject("servicesList", servicesList);
				model.addObject("bottleServiceUUID", servicesList.get(0).getServiceUUID());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendor/vendorProfileDetails");
		
		    return model;
	}
	
	
	
	@RequestMapping("/vendorInfo")
	public ModelAndView vendorInfo(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendorInfo/vendorInfo");
		
		    return model;
	}
	
	
	@RequestMapping("/ws/vendorInfo")
	public ModelAndView vendorInfoDetails(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.DASHBOARD);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendorInfo/vendorInfoDetails");
		
		    return model;
	}
	
	
	@RequestMapping(value = "/ws/vendorTermsAndCondtions")
	public ModelAndView vendorTermsAndCondtions(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		VendorCustomDetailsDTO detailsDTO;
		try {
			detailsDTO = vendorService.getVendorTermsAndCondtionsDetails(vendorUUID);
			model.addObject("termsAndCondtions", detailsDTO.getTermsAndConditions());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    model.setViewName("vendorInfo/termsAndConditions");
		return model;
	}
	
	@RequestMapping("/profile")
	public ModelAndView profile(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
		    String userUUID = (String) SessionManager.getSessionAttribute(request, "loginUserUUId");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			
			try {
				List<ServicesCategoryDTO> preferredDrinksList = vendorServicesService.getServiceCategoriesByServiceId(Constants.VENDOR_SERVICES_BOTTLE);
				List<VendorProfileCategoriesDTO> preferredMusicList = vendorService.getAllcategoriesByType(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE);
				model.addObject("preferredDrinksList", preferredDrinksList);
				model.addObject("preferredMusicList", preferredMusicList);
				
				UserDTO userDTO = userService.getUserDetailsByUUID(userUUID);
				if(StringUtils.isNotBlank(userDTO.getPreferredDrinks())) {
					List<String> drinksList = new ArrayList<>();
					  String[] drinks = userDTO.getPreferredDrinks().split(",");
						for (int i = 0; i < drinks.length; i++) {
							drinksList.add(drinks[i]);
						}
					  model.addObject("drinksList", drinksList);
				}
				
				if(StringUtils.isNotBlank(userDTO.getPreferredMusic())) {
					List<String> musicList = new ArrayList<>();
					  String[] music = userDTO.getPreferredMusic().split(",");
						for (int i = 0; i < music.length; i++) {
							musicList.add(music[i]);
						}
					  model.addObject("musicList", musicList);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			sessionData.setNav(Constants.PROFILE);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("userUUID", userUUID);
			model.setViewName("user/profile");
		
		    return model;
	}
	
	
	@RequestMapping("/orders")
	public ModelAndView orders(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
		    String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.ORDERS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
				
				List<ServicesDTO> servicesList = vendorService.getVendorServices();
				model.addObject("servicesList", servicesList);
				model.addObject("bottleServiceUUID", servicesList.get(0).getServiceUUID());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("vendor/orders");
		
		    return model;
	}
	
	
	@RequestMapping(value = "/ws/orders/{orderUUID}", method = RequestMethod.GET)
	public ModelAndView scanOrdersQR(@PathVariable String orderUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject(orderUUID, orderUUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("appUrl", appUrl);
		model.setViewName("user/ordersQR"); 
		return model; 
	}
	
	
	@RequestMapping("/myOrders")
	public ModelAndView myOrders(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
		    String loginUserUUId = (String) SessionManager.getSessionAttribute(request, "loginUserUUId");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.ORDERS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			model.addObject("appUrl", appUrl);
			model.addObject("loginUserUUId", loginUserUUId);
			model.setViewName("user/myOrders");
		
		    return model;
	}
	
	
	@RequestMapping("/guests")
	public ModelAndView guests(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
	    	String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.GUESTS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("guest/guests");
		
		    return model;
	}
	
	@RequestMapping("/addGuest")
	public ModelAndView addGuest(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
		    String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.GUESTS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("guest/addGuest");
		
		    return model;
	}
	
	
	@RequestMapping("/guestClubs")
	public ModelAndView guestClubs(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
	    	String userUUID = (String) SessionManager.getSessionAttribute(request, "loginUserUUId");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.GUESTS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			model.addObject("appUrl", appUrl);
			model.addObject("userUUID", userUUID);
			model.setViewName("user/guestClubs");
		
		    return model;
	}
	
	@RequestMapping(value = "/ws/guestUser/{guestUUID}", method = RequestMethod.GET)
	public ModelAndView scanGuestQR(@PathVariable String guestUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("guestUUID", guestUUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("appUrl", appUrl);
		model.setViewName("user/guestQR"); 
		return model; 
	}
	
	
	@RequestMapping("/events")
	public ModelAndView events(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
	    	String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.EVENTS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("events/events");
		
		    return model;
	}
	
	@RequestMapping("/addEvent")
	public ModelAndView addEvent(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
	    	String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.EVENTS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("events/addEvent");
		
		    return model;
	}
	
	
	
	@RequestMapping("/editEvent")
	public ModelAndView editEvent(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
	    	String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.EVENTS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			try {
				Long vendorId = vendorService.getVendorIdByUUID(vendorUUID);
				model.addObject("vendorId", vendorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.addObject("eventUUID", eventUUID);
			model.setViewName("events/addEvent");
		
		    return model;
	}
	
	@RequestMapping("/eventSettings")
	public ModelAndView eventSettings(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
	    	String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.EVENTS);
			request.getSession().setAttribute("sessionData",sessionData);
			
			try {
				List<EventTicketsDTO> ticketsList = eventService.getEventsTicketsList(vendorUUID);
				model.addObject("ticketsList", ticketsList);
				model.addObject("ticketsListSize", ticketsList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("events/eventSettings");
		
		    return model;
	}
	
	@RequestMapping("/clubEvents")
	public ModelAndView clubEvents(@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.EVENTS);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("events/clubEvents");
		
		    return model;
	}
	
	
	@RequestMapping("/users")
	public ModelAndView users(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.USERS);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.setViewName("user/users");
		
		    return model;
	}
	
	@RequestMapping(value = { "/eventDetails", "/ws/eventDetails" }, method = RequestMethod.GET)
	public ModelAndView eventDetails(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			@RequestParam(value = "vendorUUID", required = true) String vendorUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
	
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.EVENTS);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("eventUUID", eventUUID);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("events/eventDetails");
		
		    return model;
	}
	
	
	@RequestMapping(value = { "/eventBookings", "/ws/eventBookings" }, method = RequestMethod.GET)
	public ModelAndView eventBookings(@RequestParam(value = "eventUUID", required = true) String eventUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String vendorUUID = (String) SessionManager.getSessionAttribute(request, "vendorUUID");
			SessionMaintainanceData sessionData = (SessionMaintainanceData)request.getSession().getAttribute("sessionData");
			if(null == sessionData)
			{
				sessionData = new SessionMaintainanceData();
			}
			sessionData.setNav(Constants.EVENTS);
			request.getSession().setAttribute("sessionData",sessionData);
			model.addObject("appUrl", appUrl);
			model.addObject("eventUUID", eventUUID);
			model.addObject("vendorUUID", vendorUUID);
			model.setViewName("events/eventBookings");
		
		    return model;
	}
	
	
	@RequestMapping(value = "/ws/eventOrders/{orderUUID}", method = RequestMethod.GET)
	public ModelAndView scanEventOrdersQR(@PathVariable String orderUUID,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("orderUUID", orderUUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addObject("appUrl", appUrl);
		model.setViewName("events/eventsQR"); 
		return model; 
	}
	
	
}
