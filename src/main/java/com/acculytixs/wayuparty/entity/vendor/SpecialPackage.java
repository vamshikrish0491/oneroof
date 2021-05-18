package com.acculytixs.wayuparty.entity.vendor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "special_package")
public class SpecialPackage implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private Long id;
		
		@Column(name = "vendor_uuid")
		private String vendorUUID;
		
		@Column(name = "service_type",columnDefinition = "TEXT default NULL")
		private String serviceType;
		
		@Column(name = "start_date")
		private Date startDate;
		
		@Column(name = "end_date")
		private Date endDate;
		
		@Column(name = "event_banner_image",columnDefinition = "TEXT default NULL")
		private String eventBannerImage;
		
//		@Column(name = "event_mobile_banner_image",columnDefinition = "TEXT default NULL")
//		private String eventMobileBannerImage;
		
		@Column(name = "event_mobile_banner_image",columnDefinition = "TEXT default NULL")
		private String eventDisplayImage;
//		
//		@Column(name = "event_banner_image",columnDefinition = "TEXT default NULL")
//		private String eventBannerImage;
//		
		@Column(name = "created_date")
		private Date createdDate;

		public String getEventDisplayImage() {
			return eventDisplayImage;
		}

		public void setEventDisplayImage(String eventDisplayImage) {
			this.eventDisplayImage = eventDisplayImage;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getVendorUUID() {
			return vendorUUID;
		}

		public void setVendorUUID(String vendorUUID) {
			this.vendorUUID = vendorUUID;
		}

		public String getServiceType() {
			return serviceType;
		}

		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public String getEventBannerImage() {
			return eventBannerImage;
		}

		public void setEventBannerImage(String eventBannerImage) {
			this.eventBannerImage = eventBannerImage;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "SpecialPackage [id=" + id + ", vendorUUID=" + vendorUUID + ", serviceType=" + serviceType
					+ ", startDate=" + startDate + ", endDate=" + endDate + ", eventBannerImage=" + eventBannerImage
					+ ", eventDisplayImage=" + eventDisplayImage + ", createdDate=" + createdDate + "]";
		}

}
