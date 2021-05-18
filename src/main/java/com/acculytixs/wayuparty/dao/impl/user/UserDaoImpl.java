package com.acculytixs.wayuparty.dao.impl.user;


import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.user.UserDao;
import com.acculytixs.wayuparty.dto.search.PopularCitiesDTO;
import com.acculytixs.wayuparty.dto.user.RegisteredUsersDTO;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserMobileLoginDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.dto.user.VerificationDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.user.PasswordVerification;
import com.acculytixs.wayuparty.entity.user.PlaceOrderRatings;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.util.ResponseList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public boolean isUserExistedByEmailId(String emailId) throws Exception {
		boolean flag = false;
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			  String query = "select id from User user where user.email = :emailId and user.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("emailId", emailId);
			  Long userId = (Long) queryObj.uniqueResult();
			  if(userId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isUserExistedByMobileNumber(String mobileNumber) throws Exception {
		boolean flag = false;
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			  String query = "select id from User user where user.mobileNumber = :mobileNumber and user.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("mobileNumber", mobileNumber);
			  Long userId = (Long) queryObj.uniqueResult();
			  if(userId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return flag;
	}

	@Override
	public UserDTO getUserDetailsByUUID(String userUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query userPasswordQuery = null;
		 String passwordQuery =  "SELECT "
				 +" U.id AS userId, "
				 +" U.email AS userEmail, "
				 +" U.mobile_number AS userMobile, "
				 +" U.password AS password, "
				 +" U.first_name AS firstName, "
				 +" IFNULL(U.last_name,'') AS lastName, "
				 +" IFNULL(DATE_FORMAT(dob, '%d/%m/%Y'),'') AS dob, "
				 +" IFNULL(U.gender,'') AS gender, "
				 +" IFNULL(U.preferred_drinks,'') AS preferredDrinks, "
				 +" IFNULL(U.preferred_music,'') AS preferredMusic, "
				 +" IFNULL(U.user_image,'') AS userImage "
				 +" FROM user U "
				 +" WHERE U.uuid = :userUUID";

				 userPasswordQuery = currentSession.createSQLQuery(passwordQuery).setParameter("userUUID", userUUID); 
				 userPasswordQuery.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
				 if(userPasswordQuery.list() != null && !userPasswordQuery.list().isEmpty()) {
					 userDTO = (UserDTO) userPasswordQuery.list().get(0);
				 }
				 
				 
		return userDTO;
	}

	@Override
	public void saveUser(User user) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public VendorInfoDTO getVendorInfoDetails(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorInfoDTO> vendorInfoDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" IFNULL(vendors.vendor_name,'') AS vendorName, "
				+" IFNULL(vendors.vendor_code,'') AS vendorCode, "
				+" IFNULL(vendors.vendor_email,'') AS vendorEmail, "
				+" IFNULL(vendors.vendor_mobile,'') AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" IFNULL(vendors.currency,'') AS currency, "
				+" IFNULL(vendors.vendor_description,'') AS vendorDescription, "
				+" IFNULL(vendors.best_selling_items,'') AS bestSellingItems, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.country,'') AS country, "
				+" IFNULL(vendors.state,'') AS state, "
				+" IFNULL(vendors.city,'') AS city, "
				+" IFNULL(vendors.pincode,'') AS pincode, "
				+" IFNULL(vendors.latitude,'') AS latitude, "
				+" IFNULL(vendors.longitude,'') AS longitude, "
				+" IFNULL(vendors.phone_number,'') AS phoneNumber, "
				+" IFNULL(vendors.address,'') AS vendorAddress, "
				+" IFNULL(vendors.working_hours,'') AS workingHours, "
				+" IFNULL(vendors.terms_conditions,'') AS termsAndConditions, "
				+" IFNULL(vendors.slider_images,'') AS sliders, "
				+" IFNULL(vendors.gallery_images,'') AS gallery, "
				+" IFNULL(vendors.menu_images,'') AS menus, "
				+" IFNULL(vendors.vendor_videos,'') AS videos, "
				+" IFNULL(vendors.vendor_categories,'') AS categories, "
				+" IFNULL(vendors.vendor_facilities,'') AS facilities, "
				+" IFNULL(vendors.vendor_music_genre,'') AS music, "
				+" IFNULL(vendors.vendor_cuisine,'') AS cuisine, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorInfoDTO.class));
		vendorInfoDTOList = queryObj.list();
		

		if(vendorInfoDTOList != null && !vendorInfoDTOList.isEmpty()) {
			VendorInfoDTO vendorInfoDTO = vendorInfoDTOList.get(0);
			return vendorInfoDTO;
		}else {
			return null;
		}
	}

	@Override
	public List<String> getVendorCustomCategories(List<Long> categoriesIds) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<String> vendorCategoriesList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" categories.category_name AS categoryName "
				
			+" FROM "
			     + "wayuparty_categories categories where categories.status = '1'  and categories.id IN (:categoriesIds) ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameterList("categoriesIds", categoriesIds);
		vendorCategoriesList = queryObj.list();
		
		return vendorCategoriesList;
	}

	@Override
	public User getUserByUUID(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from User user left join fetch user.vendorId left join fetch user.roleId where user.uuid = :uuid ";
		List<User> userList = currentSession.createQuery(query).setString("uuid", uuid).list();
		User user = null;
		if (userList != null && userList.size() > 0) {
			user = (User) userList.iterator().next();
		}
		return user;
	}

	@Override
	public UserLoginDTO getLoginUserDetails(Long userId) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query queryObj = null;
		 String query =  "SELECT "
				 +" U.id AS userId, "
				 +" U.email AS userEmail, "
				 +" U.mobile_number AS userMobile, "
				 + "CONCAT (U.first_name,' ',IFNULL(U.last_name,'')) AS loginUserName,"
				 +" IFNULL(U.user_image,'') AS userImage, "
				 +" U.uuid AS userUUID "
				 +" FROM user U "
				 +" WHERE U.id = :userId";

		    queryObj = currentSession.createSQLQuery(query);
			queryObj.setLong("userId", userId);
			queryObj.setResultTransformer(Transformers.aliasToBean(UserLoginDTO.class));
			List<UserLoginDTO> userList = queryObj.list();
			
			if(userList != null && !userList.isEmpty()) {
				UserLoginDTO userLoginDTO = userList.get(0);
				return userLoginDTO;
			}else {
				return null;
			}
	}

	@Override
	public User getUserByVendorUUID(String vendorUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from User user where user.vendorId.id IN (select vendors.id from Vendors vendors where vendors.uuid = :vendorUUID) ";
		List<User> userList = currentSession.createQuery(query).setParameter("vendorUUID", vendorUUID).list();
		User user = null;
		if (userList != null && userList.size() > 0) {
			user = (User) userList.iterator().next();
		}
		return user;
	}

	@Override
	public UserMobileLoginDTO getLoginMobileUserDetails(String userUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query queryObj = null;
		 String query =  "SELECT "
				 +" U.email AS userEmail, "
				 +" U.first_name AS firstName, "
				 +" IFNULL(U.last_name,'') AS lastName, "
				 +" U.mobile_number AS userMobile, "
				 + "CONCAT (U.first_name,' ',IFNULL(U.last_name,'')) AS loginUserName,"
				 +" IFNULL(U.user_image,'') AS userImage, "
				 +" IFNULL(U.gender,'') AS gender, "
				 +" IFNULL(DATE_FORMAT(dob, '%d/%m/%Y'),'') AS dob, "
				 +" IFNULL(U.preferred_drinks,'') AS preferredDrinks, "
				 +" IFNULL(U.preferred_music,'') AS preferredMusic, "
				 +" U.uuid AS userUUID "
				 +" FROM user U "
				 +" WHERE U.uuid = :userUUID";

		    queryObj = currentSession.createSQLQuery(query);
			queryObj.setString("userUUID", userUUID);
			queryObj.setResultTransformer(Transformers.aliasToBean(UserMobileLoginDTO.class));
			List<UserMobileLoginDTO> userList = queryObj.list();
			
			if(userList != null && !userList.isEmpty()) {
				UserMobileLoginDTO userLoginDTO = userList.get(0);
				return userLoginDTO;
			}else {
				return null;
			}
	}

	@Override
	public UserLoginDTO getLoginUserDetailsByEmail(String email) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query queryObj = null;
		 String query =  "SELECT "
				 +" U.id AS userId, "
				 +" U.email AS userEmail, "
				 +" U.mobile_number AS userMobile, "
				 + "CONCAT (U.first_name,' ',IFNULL(U.last_name,'')) AS loginUserName,"
				 +" IFNULL(U.user_image,'') AS userImage, "
				 +" U.uuid AS userUUID "
				 +" FROM user U "
				 +" WHERE U.email = :email";

		    queryObj = currentSession.createSQLQuery(query);
			queryObj.setString("email", email);
			queryObj.setResultTransformer(Transformers.aliasToBean(UserLoginDTO.class));
			List<UserLoginDTO> userList = queryObj.list();
			
			if(userList != null && !userList.isEmpty()) {
				UserLoginDTO userLoginDTO = userList.get(0);
				return userLoginDTO;
			}else {
				return null;
			}
	}

	@Override
	public void savePasswordVerification(PasswordVerification passwordVerification) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(passwordVerification);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public VerificationDTO getVerificationDetails(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		Query queryObj = null;
		 String query =  "SELECT "
				 +" pv.verification_code AS verificationCode, "
				 +" pv.user_uuid AS userUUID "
				 +" FROM password_verification pv "
				 +" WHERE pv.uuid = :uuid and pv.is_verified = 'N' ";

		    queryObj = currentSession.createSQLQuery(query);
			queryObj.setString("uuid", uuid);
			queryObj.setResultTransformer(Transformers.aliasToBean(VerificationDTO.class));
			List<VerificationDTO> verificationDTOList = queryObj.list();
			
			if(verificationDTOList != null && !verificationDTOList.isEmpty()) {
				VerificationDTO verificationDTO = verificationDTOList.get(0);
				return verificationDTO;
			}else {
				return null;
			}
	}

	@Override
	public PasswordVerification getPasswordVerificationByUUID(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from PasswordVerification pv where pv.uuid = :uuid ";
		List<PasswordVerification> passwordVerificationList = currentSession.createQuery(query).setString("uuid", uuid).list();
		PasswordVerification passwordVerification = null;
		if (passwordVerificationList != null && passwordVerificationList.size() > 0) {
			passwordVerification = (PasswordVerification) passwordVerificationList.iterator().next();
		}
		return passwordVerification;
	}

	@Override
	public String getUserUUIDByEmail(String email) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String query =  "SELECT "
				+" user.uuid "           
				+" FROM user user "
				+" WHERE user.email = :email ";
		
		Query queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("email", email);
		String userUUID = (String) queryObj.uniqueResult();
		if(userUUID != null) {
			return userUUID;
		}else {
			return null;
		}
		
	}

	@Override
	public String getUserUUIDByMobile(String mobileNumber) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String query =  "SELECT "
				+" user.uuid "           
				+" FROM user user "
				+" WHERE user.mobile_number = :mobileNumber ";
		
		Query queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("mobileNumber", mobileNumber);
		String userUUID = (String) queryObj.uniqueResult();
		if(userUUID != null) {
			return userUUID;
		}else {
			return null;
		}
		
	}

	@Override
	public VendorGuestUsersDTO getGuestUserDetailsByEmail(String email) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		Query queryObj = null;
		 String query =  "SELECT "
				 +" U.email AS email, "
				 +" U.first_name AS firstName, "
				 +" IFNULL(U.last_name,'') AS lastName, "
				 +" U.mobile_number AS mobileNumber, "
				 +" IFNULL(U.gender,'') AS gender, "
				 +" IFNULL(DATE_FORMAT(dob, '%d/%m/%Y'),'') AS dob "
				 +" FROM user U "
				 +" WHERE U.email = :email";

		    queryObj = currentSession.createSQLQuery(query);
			queryObj.setString("email", email);
			queryObj.setResultTransformer(Transformers.aliasToBean(VendorGuestUsersDTO.class));
			List<VendorGuestUsersDTO> userList = queryObj.list();
			
			if(userList != null && !userList.isEmpty()) {
				VendorGuestUsersDTO guestUserDetailsDTO = userList.get(0);
				return guestUserDetailsDTO;
			}else {
				return null;
			}
	}

	@Override
	public VendorGuestUsersDTO getGuestUserDetailsByMobileNumber(String mobileNumber) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		Query queryObj = null;
		 String query =  "SELECT "
				 +" U.email AS email, "
				 +" U.first_name AS firstName, "
				 +" IFNULL(U.last_name,'') AS lastName, "
				 +" U.mobile_number AS mobileNumber, "
				 +" IFNULL(U.gender,'') AS gender, "
				 +" IFNULL(DATE_FORMAT(dob, '%d/%m/%Y'),'') AS dob "
				 +" FROM user U "
				 +" WHERE U.mobile_number = :mobileNumber";

		    queryObj = currentSession.createSQLQuery(query);
			queryObj.setString("mobileNumber", mobileNumber);
			queryObj.setResultTransformer(Transformers.aliasToBean(VendorGuestUsersDTO.class));
			List<VendorGuestUsersDTO> userList = queryObj.list();
			
			if(userList != null && !userList.isEmpty()) {
				VendorGuestUsersDTO guestUserDetailsDTO = userList.get(0);
				return guestUserDetailsDTO;
			}else {
				return null;
			}
	}

	@Override
	public List<User> getUsersListByVendorId(Long vendorId) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from User user where user.vendorId.id = :vendorId  ";
		List<User> usersList = currentSession.createQuery(query.toString()).setLong("vendorId", vendorId).list();
		return usersList;
	}

	@Override
	public List<PopularCitiesDTO> getPopularCities() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<PopularCitiesDTO> citiesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" cities.city_name AS cityName, "
				+" cities.city_image AS cityImage, "
				+" cities.latitude AS latitude, "
				+" cities.longitude AS longitude "
				+" FROM "
				+ "popular_cities cities WHERE cities.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(PopularCitiesDTO.class));
		citiesDTOList = queryObj.list();
		
		return citiesDTOList;
	}

	@Override
	public ResponseList<RegisteredUsersDTO> getRegisteredUsersList(RegisteredUsersDTO registeredUsersDTO) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<BigInteger> countlist = null;
		Integer filterCount = 0;
		Query queryObj = null;
		List<RegisteredUsersDTO> usersList = null;
		String querySeachStr =  "";
		ResponseList<RegisteredUsersDTO> response = new ResponseList<>();
		
		StringBuilder query1 = new StringBuilder("");
		if(registeredUsersDTO.getSearchValue() != null && !"".equalsIgnoreCase(registeredUsersDTO.getSearchValue())) {
			 query1.append(" Q1.userName like '%" + registeredUsersDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.email like '%" + registeredUsersDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.registeredDate like '%" + registeredUsersDTO.getSearchValue() + "%' ")
				.append(" OR Q1.mobileNumber like '%" + registeredUsersDTO.getSearchValue() + "%' ");
			 
		}
		
		if(query1.toString() != null && !query1.toString().isEmpty()){
			querySeachStr = " where "+"("+query1.toString()+")";
		}
		
		
		try {
			 query = "SELECT Q1.* " + 
			 		 "FROM (" + 
					 "SELECT " + 
					"CONCAT (user.first_name,' ',IFNULL(user.last_name,'')) AS userName," +
					"user.email AS email, " +
					"user.mobile_number AS mobileNumber, " +
					"user.uuid AS userUUID, " +
					"IFNULL(DATE_FORMAT(user.created_date, '%b %e %Y'),'') AS registeredDate, "+
					"IF(user.status = 1, 'Y', 'N') AS isActiveUser " +
					"FROM " + 
					"user user " + 
					"WHERE user.vendor_id IS NULL AND user.role_id <> '1' " + 
					"ORDER BY DATE_FORMAT(user.created_date ,'%d') ASC)Q1 "+querySeachStr;
		   
			queryObj = currentSession.createSQLQuery(query);
			queryObj.setResultTransformer(Transformers.aliasToBean(RegisteredUsersDTO.class));
			queryObj.setFirstResult(registeredUsersDTO.getStart());
			queryObj.setMaxResults(registeredUsersDTO.getLength());
			usersList = queryObj.list();
			
			String  countQuery = "SELECT COUNT(*) AS TOTAL" + 
					" FROM" + 
					"(" + query +
					")Q2;" ;
			
			countlist = currentSession.createSQLQuery(countQuery).list();
			filterCount = countlist.iterator().next().intValue();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			int totalCount = 0;

			
			if (registeredUsersDTO != null) {
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
		
			response.setData(usersList);
			return response;
	}

	@Override
	public void savePlaceOrderRatings(PlaceOrderRatings placeOrderRatings) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(placeOrderRatings);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sendSMS(UserDTO userDTO, String uuid) throws Exception {
		
		try {
			Session currentSession = entityManager.unwrap(Session.class);

			// Construct data
			String apiKey = "apikey=" + "OGFkNjI0ODcxOTg0MjBhYjIzZTBmZDYyMzgwZTFiMDE=";
			String message = "&message=" + "Dear " + userDTO.getFirstName() + " " + userDTO.getLastName()
					+ "\nYour Wayuparty order has confirmed with Iron Hill On 22/04/2021\n"
					+ "Address: Vijayawada";
			String sender = "&sender=" + "WAYUPT";
			String numbers = "&numbers=" + "91"+userDTO.getUserMobile();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			System.out.println("DATA -- "+data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			e.printStackTrace();

		}
		
	}

}
