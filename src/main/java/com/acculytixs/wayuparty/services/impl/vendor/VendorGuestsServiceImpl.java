package com.acculytixs.wayuparty.services.impl.vendor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.user.UserDao;
import com.acculytixs.wayuparty.dao.vendor.VendorDao;
import com.acculytixs.wayuparty.dao.vendor.VendorGuestsDao;
import com.acculytixs.wayuparty.dto.user.GuestUserDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDTO;
import com.acculytixs.wayuparty.dto.user.UserGuestClubsDetailsDTO;
import com.acculytixs.wayuparty.dto.user.VendorGuestUsersDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.entity.WayupartyRoles;
import com.acculytixs.wayuparty.entity.email.EmailMessages;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.VendorGuests;
import com.acculytixs.wayuparty.security.WayupartyPasswordEncoder;
import com.acculytixs.wayuparty.services.vendor.VendorGuestsService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.EmailUtil;
import com.acculytixs.wayuparty.util.RandomCodeHelper;
import com.acculytixs.wayuparty.util.ResponseList;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class VendorGuestsServiceImpl implements VendorGuestsService{

	@Autowired
	VendorGuestsDao vendorGuestsDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	VendorDao vendorDao;
	
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
	
	@Value("${static.path}")
	private String staticPath;
	
	@Autowired
	WayupartyPasswordEncoder wayupartyPasswordEncoder;
	
	@Override
	public boolean isUserExistedAsGuest(String userUUID, String vendorUUID) throws Exception {
		return vendorGuestsDao.isUserExistedAsGuest(userUUID, vendorUUID);
	}

	@Override
	public String saveGuestUser(GuestUserDTO guestUserDTO) throws Exception {
		
		
		
		String queryExecutionStatus = null;
		try {
			
				if(StringUtils.isBlank(guestUserDTO.getUserUUID())) {
					User user = new User();
					user.setFirstName(guestUserDTO.getFirstName());
					user.setLastName(guestUserDTO.getLastName());
					user.setEmail(guestUserDTO.getEmail());
					user.setUserName(guestUserDTO.getEmail());
					String password = RandomCodeHelper.generateRandomPassword();
					user.setPassword(wayupartyPasswordEncoder.encode(password));
					Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(guestUserDTO.getDob());  
					user.setDob(dob);
					user.setGender(guestUserDTO.getGender());
					
					WayupartyRoles roles = new WayupartyRoles();
					roles.setId(Constants.ROLE_USER);
					user.setRoleId(roles);
					user.setUserStatus(Constants.USER_NOT_LOGGED_IN);
					user.setIsEmailVerified(Constants.STRING_N);
					user.setUuid(RandomCodeHelper.generateRandomUUID());
					user.setStatus(1);
					user.setCreatedDate(new Date());
					userDao.saveUser(user);
					
					VendorGuests vendorGuest = new VendorGuests();
					vendorGuest.setUserUUID(user.getUuid());
					vendorGuest.setVendorUUID(guestUserDTO.getVendorUUID());
					
					if(StringUtils.isNotBlank(guestUserDTO.getStartDate())) {
						Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(guestUserDTO.getStartDate());  
						vendorGuest.setStartDate(startDate);
					}
					
					if(StringUtils.isNotBlank(guestUserDTO.getEndDate())) {
						Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(guestUserDTO.getEndDate());  
						vendorGuest.setEndDate(endDate);
					}
					
					String guestCode = RandomCodeHelper.generateRandomGuestCode();
					vendorGuest.setGuestCode(guestCode);
					vendorGuest.setUuid(RandomCodeHelper.generateRandomUUID());
					vendorGuest.setStatus(1);
					vendorGuest.setCreatedDate(new Date());
					String qrCode = writeQRCode(guestUserDTO.getVendorUUID(), RandomCodeHelper.generateQRUUID(), guestCode);
					vendorGuest.setQrCode(qrCode);
					vendorGuestsDao.saveGuestUser(vendorGuest);
					
					sendActivationEmail(guestUserDTO, user, password);
				}else {
					
					
					User user = userDao.getUserByUUID(guestUserDTO.getUserUUID());
					Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(guestUserDTO.getDob());  
					user.setDob(dob);
					user.setGender(guestUserDTO.getGender());
					user.setFirstName(guestUserDTO.getFirstName());
					user.setLastName(guestUserDTO.getLastName());
					userDao.saveUser(user);
					
					VendorGuests vendorGuest = new VendorGuests();
					vendorGuest.setUserUUID(guestUserDTO.getUserUUID());
					vendorGuest.setVendorUUID(guestUserDTO.getVendorUUID());
					
					if(StringUtils.isNotBlank(guestUserDTO.getStartDate())) {
						Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(guestUserDTO.getStartDate());  
						vendorGuest.setStartDate(startDate);
					}
					
					if(StringUtils.isNotBlank(guestUserDTO.getEndDate())) {
						Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(guestUserDTO.getEndDate());  
						vendorGuest.setEndDate(endDate);
					}
					
					String guestUUID = RandomCodeHelper.generateRandomUUID();
					vendorGuest.setGuestCode(RandomCodeHelper.generateRandomGuestCode());
					vendorGuest.setUuid(guestUUID);
					vendorGuest.setStatus(1);
					vendorGuest.setCreatedDate(new Date());
					String qrCode = writeQRCode(guestUserDTO.getVendorUUID(), RandomCodeHelper.generateQRUUID(), guestUUID);
					vendorGuest.setQrCode(qrCode);
					vendorGuestsDao.saveGuestUser(vendorGuest);
				}
				
				
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		
		return queryExecutionStatus;
		
	}
	
	private String writeQRCode(String vendorUUID,String QRCodeUUID, String guestUUID ) throws WriterException, IOException {
		VendorDTO vendorDTO;
		String qrCodePath = null;
		try {
			vendorDTO = vendorDao.getVendorDetails(vendorUUID);
			File folderFile = new File(staticPath, "/" + vendorDTO.getVendorId() + "/Guests-QR-Codes");
			if (!folderFile.exists()) {
				folderFile.mkdirs();
			}
			
			String qcodePath = staticPath+"/"+vendorDTO.getVendorId()+"/Guests-QR-Codes/" + QRCodeUUID + ".png";
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(appUrl+"/ws/guestUser/"+guestUUID, BarcodeFormat.QR_CODE, 350, 350);
			Path path = FileSystems.getDefault().getPath(qcodePath);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			qrCodePath = "/wayuparty-static/"+vendorDTO.getVendorId()+"/Guests-QR-Codes/" + QRCodeUUID + ".png";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return qrCodePath;
	}
	
	public void sendActivationEmail(GuestUserDTO guestUserDTO, User user, String password) {
		 EmailMessages emailMessages = new EmailMessages();
		 StringBuilder guestUserName = new StringBuilder();
		 if(StringUtils.isNotBlank(guestUserDTO.getLastName())) {
			 guestUserName.append(guestUserDTO.getFirstName()).append(" ").append(guestUserDTO.getLastName());
		 }else {
			 guestUserName.append(guestUserDTO.getFirstName());
		 }
		 emailMessages.setLoginUserName(guestUserName.toString());
		 emailMessages.setToEmail(guestUserDTO.getEmail());
		 emailMessages.setUserName(guestUserDTO.getEmail());
		 emailMessages.setPassword(password);
		 emailMessages.setFromEmail(fromEmailAddress);
		 emailMessages.setFromPassword(fromEmailPassword);
		 emailMessages.setSmtphost(smtphost);
		 emailMessages.setPort(port);
		 
		 emailMessages.setFromName(Constants.FROM_EMAIL_NAME);
		 emailMessages.setSubject("Verification Link :: ONEROOF");
		 
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("registerName", guestUserName.toString());
		 StringBuilder verifylink = new StringBuilder();
		 verifylink.append("<a href='").append(appUrl).append("/ws/activateEmail?&tocken=").append(user.getUuid()).append("'>").append(appUrl).append("/ws/activateEmail?&tocken=").append(user.getUuid()).append("</a>").toString();
	     
		 emailMessages.setVerificationLink(verifylink.toString());
		 emailUtil.sendTemplateEmail(emailMessages);
	}

	@Override
	public ResponseList<VendorGuestUsersDTO> getGuestUsersList(VendorGuestUsersDTO guestUserDTO) throws Exception {
		return vendorGuestsDao.getGuestUsersList(guestUserDTO);
	}

	@Override
	public VendorGuestUsersDTO getGuestUseDetails(String guestUUID) throws Exception {
		return vendorGuestsDao.getGuestUseDetails(guestUUID);
	}

	@Override
	public ResponseList<VendorGuestUsersDTO> getUserGuestsList(VendorGuestUsersDTO guestUserDTO) throws Exception {
		return vendorGuestsDao.getUserGuestsList(guestUserDTO);
	}

	@Override
	public List<UserGuestClubsDTO> getGuestClubsList(String userUUID) throws Exception {
		return vendorGuestsDao.getGuestClubsList(userUUID);
	}

	@Override
	public UserGuestClubsDetailsDTO getGuestClubDetails(String guestUUID) throws Exception {
		return vendorGuestsDao.getGuestClubDetails(guestUUID);
	}



}
