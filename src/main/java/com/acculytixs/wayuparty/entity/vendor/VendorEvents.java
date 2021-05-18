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
@Table(name = "vendor_events")
public class VendorEvents implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private Long id;
		
		@Column(name = "vendor_uuid")
		private String vendorUUID;
		
		@Column(name = "event_name",columnDefinition = "TEXT default NULL")
		private String eventName;
		
		@Column(name = "event_type",columnDefinition = "TEXT default NULL")
		private String eventType;
		
		@Column(name = "event_location",columnDefinition = "TEXT default NULL")
		private String eventLocation;
		
		@Column(name = "event_host",columnDefinition = "TEXT default NULL")
		private String eventHost;
		
		@Column(name = "music_type",columnDefinition = "TEXT default NULL")
		private String musicType;
		
		@Column(name = "event_date")
		private Date eventDate;
		
		@Column(name = "event_time_slots",columnDefinition = "TEXT default NULL")
		private String eventTimeSlots;
		
		@Column(name = "event_language",columnDefinition = "TEXT default NULL")
		private String eventLanguage;
		
		@Column(name = "event_address",columnDefinition = "TEXT default NULL")
		private String eventAddress;
		
		@Column(name = "event_display_image",columnDefinition = "TEXT default NULL")
		private String eventDisplayImage;
		
		@Column(name = "event_banner_image",columnDefinition = "TEXT default NULL")
		private String eventBannerImage;
		
		@Column(name = "event_tickets",columnDefinition = "TEXT default NULL")
		private String eventTickets;
		
		@Column(name = "created_date")
		private Date createdDate;;
		
		@Column(name = "uuid")
		private String uuid;
		
		@Column(name = "status")
		private Integer status;
		
		

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

		public String getEventName() {
			return eventName;
		}

		public void setEventName(String eventName) {
			this.eventName = eventName;
		}
		
		public String getEventType() {
			return eventType;
		}

		public void setEventType(String eventType) {
			this.eventType = eventType;
		}

		public String getEventLocation() {
			return eventLocation;
		}

		public void setEventLocation(String eventLocation) {
			this.eventLocation = eventLocation;
		}

		public String getEventHost() {
			return eventHost;
		}

		public void setEventHost(String eventHost) {
			this.eventHost = eventHost;
		}

		public Date getEventDate() {
			return eventDate;
		}

		public void setEventDate(Date eventDate) {
			this.eventDate = eventDate;
		}

		public String getEventTimeSlots() {
			return eventTimeSlots;
		}

		public void setEventTimeSlots(String eventTimeSlots) {
			this.eventTimeSlots = eventTimeSlots;
		}

		public String getEventLanguage() {
			return eventLanguage;
		}

		public void setEventLanguage(String eventLanguage) {
			this.eventLanguage = eventLanguage;
		}

		public String getEventAddress() {
			return eventAddress;
		}
		
		public String getMusicType() {
			return musicType;
		}

		public void setMusicType(String musicType) {
			this.musicType = musicType;
		}

		public void setEventAddress(String eventAddress) {
			this.eventAddress = eventAddress;
		}

		public String getEventDisplayImage() {
			return eventDisplayImage;
		}

		public void setEventDisplayImage(String eventDisplayImage) {
			this.eventDisplayImage = eventDisplayImage;
		}

		public String getEventBannerImage() {
			return eventBannerImage;
		}

		public void setEventBannerImage(String eventBannerImage) {
			this.eventBannerImage = eventBannerImage;
		}
		
		public String getEventTickets() {
			return eventTickets;
		}

		public void setEventTickets(String eventTickets) {
			this.eventTickets = eventTickets;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
		
		
}
