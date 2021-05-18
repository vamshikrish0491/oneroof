package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;
import java.util.List;

import com.acculytixs.wayuparty.annotations.Required;
import com.acculytixs.wayuparty.util.FileInfo;

public class AddVendorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Required(desc="vendorName")
	private String vendorName;
	
	@Required(desc="vendorCode")
	private String vendorCode;
	
	@Required(desc="vendorAdministratorName")
	private String vendorAdministratorName;
	
	@Required(desc="vendorCapacity")
	private String vendorCapacity;
	
	@Required(desc="establishedYear")
	private String establishedYear;
	
	@Required(desc="vendorMobile")
	private String vendorMobile;
	
	@Required(desc="vendorEmail")
	private String vendorEmail;
	
	@Required(desc="costForTwoPeople")
	private String costForTwoPeople;
	
	@Required(desc="currency")
	private String currency;
	
	@Required(desc="vendorDescription")
	private String vendorDescription;
	
	@Required(desc="bestSellingItems")
	private String bestSellingItems;
	
	
	private String fileURL;
	 
    private List<FileInfo> fileInfo;
	 
	private String staticPath;
	 
	private String docFile;
	
	@Required(desc="beneficiaryName")
	private String beneficiaryName;
	
	@Required(desc="bankName")
	private String bankName;
	
	@Required(desc="bankBranch")
	private String bankBranch;
	
	@Required(desc="accountNumber")
	private String accountNumber;
	
	@Required(desc="bankCode")
	private String bankCode;
	
	@Required(desc="accountType")
	private String accountType;
	
	@Required(desc="vendorLocation")
	private String vendorLocation;
	
	@Required(desc="country")
	private String country;
	
	@Required(desc="state")
	private String state;
	
	@Required(desc="city")
	private String city;

	@Required(desc="pincode")
	private String pincode;
	
	@Required(desc="latitude")
	private String latitude;
	
	@Required(desc="longitude")
	private String longitude;
	
	@Required(desc="phoneNumber")
	private String phoneNumber;
	
	@Required(desc="vendorAddress")
	private String vendorAddress;
	
	@Required(desc="timezone")
	private String timezone;
	
	private String vendorUUID;
	
	private String vendorActive;
	
	

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorAdministratorName() {
		return vendorAdministratorName;
	}

	public void setVendorAdministratorName(String vendorAdministratorName) {
		this.vendorAdministratorName = vendorAdministratorName;
	}

	public String getVendorCapacity() {
		return vendorCapacity;
	}

	public void setVendorCapacity(String vendorCapacity) {
		this.vendorCapacity = vendorCapacity;
	}

	public String getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(String establishedYear) {
		this.establishedYear = establishedYear;
	}

	public String getVendorMobile() {
		return vendorMobile;
	}

	public void setVendorMobile(String vendorMobile) {
		this.vendorMobile = vendorMobile;
	}
	
	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getCostForTwoPeople() {
		return costForTwoPeople;
	}

	public void setCostForTwoPeople(String costForTwoPeople) {
		this.costForTwoPeople = costForTwoPeople;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getVendorDescription() {
		return vendorDescription;
	}

	public void setVendorDescription(String vendorDescription) {
		this.vendorDescription = vendorDescription;
	}

	public String getBestSellingItems() {
		return bestSellingItems;
	}

	public void setBestSellingItems(String bestSellingItems) {
		this.bestSellingItems = bestSellingItems;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public List<FileInfo> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getStaticPath() {
		return staticPath;
	}

	public void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}

	public String getDocFile() {
		return docFile;
	}

	public void setDocFile(String docFile) {
		this.docFile = docFile;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getVendorLocation() {
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}

	public String getVendorActive() {
		return vendorActive;
	}

	public void setVendorActive(String vendorActive) {
		this.vendorActive = vendorActive;
	}
	

}
