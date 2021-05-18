package com.acculytixs.wayuparty.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.vendor.VendorGuestsDao;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDetailsDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.entity.vendor.VendorGuests;
import com.acculytixs.wayuparty.util.ResponseList;

@Repository
@Transactional
public class VendorGuestsDaoImpl implements VendorGuestsDao{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public boolean isUserExistedAsGuest(String userUUID, String vendorUUID) throws Exception {
		boolean flag = false;
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			  String query = "select id from VendorGuests guest where guest.vendorUUID = :vendorUUID and guest.userUUID = :userUUID ";
			  Query queryObj =  currentSession.createQuery(query).setParameter("userUUID", userUUID).setParameter("vendorUUID", vendorUUID);
			  Long guestId = (Long) queryObj.uniqueResult();
			  if(guestId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return flag;
	
	}

	@Override
	public void saveGuestUser(VendorGuests vendorGuests) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorGuests);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ResponseList<VendorGuestUsersDTO> getGuestUsersList(VendorGuestUsersDTO guestUserDTO) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<BigInteger> countlist = null;
		Integer filterCount = 0;
		Query queryObj = null;
		List<VendorGuestUsersDTO> guestList = null;
		String querySeachStr =  "";
		ResponseList<VendorGuestUsersDTO> response = new ResponseList<>();
		
		StringBuilder query1 = new StringBuilder("");
		if(guestUserDTO.getSearchValue() != null && !"".equalsIgnoreCase(guestUserDTO.getSearchValue())) {
			 query1.append(" Q1.guestUserName like '%" + guestUserDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.email like '%" + guestUserDTO.getSearchValue() + "%' ")
				.append(" OR Q1.guestCode like '%" + guestUserDTO.getSearchValue() + "%' ");
			 
		}
		
		if(query1.toString() != null && !query1.toString().isEmpty()){
			querySeachStr = " where "+"("+query1.toString()+")";
		}
		
		
		try {
			 query = "SELECT Q1.* " + 
			 		 "FROM (" + 
					 "SELECT " + 
					"(SELECT CONCAT (u.first_name,' ',IFNULL(u.last_name,'')) FROM user u WHERE u.uuid = guests.user_uuid) AS guestUserName, "+
					"(SELECT u.email FROM user u WHERE u.uuid = guests.user_uuid) AS email, "+
					"(SELECT u.is_email_verified FROM user u WHERE u.uuid = guests.user_uuid) AS isUserVerified, "+
					"guests.user_uuid AS userUUID, " +
					"guests.uuid AS guestUUID, " +
					"IFNULL(DATE_FORMAT(guests.start_date, '%b %e %Y'),'') AS startDate, "+
					"IFNULL(DATE_FORMAT(guests.end_date, '%b %e %Y'),'') AS endDate, "+
					"guests.guest_code AS guestCode " +
					"FROM " + 
					"vendor_guests guests " + 
					"WHERE guests.vendor_uuid = :vendorUUID AND guests.status = '1' " + 
					"ORDER BY DATE_FORMAT(guests.created_date ,'%d') ASC)Q1 "+querySeachStr;
		   
			queryObj = currentSession.createSQLQuery(query);
			queryObj.setParameter("vendorUUID", guestUserDTO.getVendorUUID());
			queryObj.setResultTransformer(Transformers.aliasToBean(VendorGuestUsersDTO.class));
			queryObj.setFirstResult(guestUserDTO.getStart());
			queryObj.setMaxResults(guestUserDTO.getLength());
			guestList = queryObj.list();
			
			String  countQuery = "SELECT COUNT(*) AS TOTAL" + 
					" FROM" + 
					"(" + query +
					")Q2;" ;
			
			countlist = currentSession.createSQLQuery(countQuery).setParameter("vendorUUID", guestUserDTO.getVendorUUID()).list();
			filterCount = countlist.iterator().next().intValue();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			int totalCount = 0;

			
			if (guestUserDTO != null) {
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
		
			response.setData(guestList);
			return response;
	}

	@Override
	public VendorGuestUsersDTO getGuestUseDetails(String guestUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorGuestUsersDTO> guestUserDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " +
				"(SELECT CONCAT (u.first_name,' ',IFNULL(u.last_name,'')) FROM user u WHERE u.uuid = guests.user_uuid) AS guestUserName, "+
				"(SELECT u.email FROM user u WHERE u.uuid = guests.user_uuid) AS email, "+
				"(SELECT u.gender FROM user u WHERE u.uuid = guests.user_uuid) AS gender, "+
				"(SELECT IFNULL(DATE_FORMAT(u.dob, '%b %e %Y'),'') FROM user u WHERE u.uuid = guests.user_uuid) AS dob, "+
				"(SELECT IFNULL(u.user_image,'/resources/img/userprofile-img.png') FROM user u WHERE u.uuid = guests.user_uuid) AS userImg, "+
				"(SELECT v.vendor_name FROM vendors v WHERE v.uuid = guests.vendor_uuid) AS club, "+
				"(SELECT v.location FROM vendors v WHERE v.uuid = guests.vendor_uuid) AS clubLocation, "+
				"IFNULL(DATE_FORMAT(guests.start_date, '%b %e %Y'),'') AS startDate, "+
				"IFNULL(DATE_FORMAT(guests.end_date, '%b %e %Y'),'') AS endDate, "+
				"guests.qr_code AS qrCode, " +
				"guests.guest_code AS guestCode " +
				"FROM " + 
				"vendor_guests guests " + 
				"WHERE guests.uuid = :guestUUID";
	
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("guestUUID", guestUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorGuestUsersDTO.class));
		guestUserDTOList = queryObj.list();
		if(guestUserDTOList != null && !guestUserDTOList.isEmpty()) {
			return guestUserDTOList.get(0);
		}else {
			return null;
		}
		


	}

	@Override
	public ResponseList<VendorGuestUsersDTO> getUserGuestsList(VendorGuestUsersDTO guestUserDTO) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<BigInteger> countlist = null;
		Integer filterCount = 0;
		Query queryObj = null;
		List<VendorGuestUsersDTO> guestList = null;
		String querySeachStr =  "";
		ResponseList<VendorGuestUsersDTO> response = new ResponseList<>();
		
		StringBuilder query1 = new StringBuilder("");
		if(guestUserDTO.getSearchValue() != null && !"".equalsIgnoreCase(guestUserDTO.getSearchValue())) {
			 query1.append(" Q1.club like '%" + guestUserDTO.getSearchValue() + "%' ")
			    .append(" OR Q1.clubLocation like '%" + guestUserDTO.getSearchValue() + "%' ")
				.append(" OR Q1.guestCode like '%" + guestUserDTO.getSearchValue() + "%' ");
			 
		}
		
		if(query1.toString() != null && !query1.toString().isEmpty()){
			querySeachStr = " where "+"("+query1.toString()+")";
		}
		
		
		try {
			 query = "SELECT Q1.* " + 
			 		 "FROM (" + 
					 "SELECT " + 
					"(SELECT vendors.vendor_name FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS club, "+
					"(SELECT vendors.location FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS clubLocation, "+
					"guests.uuid AS guestUUID, " +
					"IFNULL(DATE_FORMAT(guests.start_date, '%b %e %Y'),'') AS startDate, "+
					"IFNULL(DATE_FORMAT(guests.end_date, '%b %e %Y'),'') AS endDate, "+
					"guests.guest_code AS guestCode " +
					"FROM " + 
					"vendor_guests guests " + 
					"WHERE guests.user_uuid = :userUUID AND guests.status = '1' " + 
					"ORDER BY DATE_FORMAT(guests.created_date ,'%d') ASC)Q1 "+querySeachStr;
		   
			queryObj = currentSession.createSQLQuery(query);
			queryObj.setParameter("userUUID", guestUserDTO.getUserUUID());
			queryObj.setResultTransformer(Transformers.aliasToBean(VendorGuestUsersDTO.class));
			queryObj.setFirstResult(guestUserDTO.getStart());
			queryObj.setMaxResults(guestUserDTO.getLength());
			guestList = queryObj.list();
			
			String  countQuery = "SELECT COUNT(*) AS TOTAL" + 
					" FROM" + 
					"(" + query +
					")Q2;" ;
			
			countlist = currentSession.createSQLQuery(countQuery).setParameter("userUUID", guestUserDTO.getUserUUID()).list();
			filterCount = countlist.iterator().next().intValue();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			int totalCount = 0;

			
			if (guestUserDTO != null) {
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
		
			response.setData(guestList);
			return response;
	}

	@Override
	public List<UserGuestClubsDTO> getGuestClubsList(String userUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<UserGuestClubsDTO> guestClubsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+"(SELECT vendors.vendor_name FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS club, "
				+"(SELECT CONCAT (vendors.city,', ',vendors.state) FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS clubLocation, "
				+"(SELECT  IFNULL(vendors.profile_image,'/resources/img/vendor_default_img.jpg') FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS clubImage, "
				+"guests.uuid AS guestUUID "
				+" FROM "
				+ "vendor_guests guests WHERE guests.user_uuid = :userUUID AND guests.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("userUUID", userUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(UserGuestClubsDTO.class));
		guestClubsDTOList = queryObj.list();
		
		return guestClubsDTOList;
	}

	@Override
	public UserGuestClubsDetailsDTO getGuestClubDetails(String guestUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<UserGuestClubsDetailsDTO> guestClubDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " +
				"(SELECT vendors.vendor_name FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS club, "+
				"(SELECT vendors.location FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS clubLocation, "+
				"(SELECT  IFNULL(vendors.profile_image,'/resources/img/vendor_default_img.jpg') FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS clubImage, "+
				"(SELECT vendors.vendor_description FROM vendors vendors WHERE vendors.uuid = guests.vendor_uuid) AS description, "+
				"CONCAT(DATE_FORMAT(guests.start_date, '%b %e %Y'), ' to ',DATE_FORMAT(guests.end_date, '%b %e %Y')) AS guestDate, "+
				"guests.qr_code AS qrCode, " +
				"guests.guest_code AS guestCode " +
				"FROM " + 
				"vendor_guests guests " + 
				"WHERE guests.uuid = :guestUUID";
	
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameter("guestUUID", guestUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(UserGuestClubsDetailsDTO.class));
		guestClubDTOList = queryObj.list();
		if(guestClubDTOList != null && !guestClubDTOList.isEmpty()) {
			return guestClubDTOList.get(0);
		}else {
			return null;
		}
		


	}

}
