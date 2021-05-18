ALTER TABLE `user` CHANGE COLUMN `mobile_number` `mobile_number` VARCHAR(50) NULL ;


DROP TABLE IF EXISTS `vendor_guests`;
CREATE TABLE `vendor_guests` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) NOT NULL,
  user_uuid VARCHAR(20) NOT NULL,
  start_date DATE DEFAULT NULL,
  end_date DATE DEFAULT NULL,
  guest_code VARCHAR(20) NOT NULL,
  qr_code TEXT DEFAULT NULL,
  uuid VARCHAR(20) NOT NULL,
  created_date DATETIME NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `vendor_events_tickets`;
CREATE TABLE `vendor_events_tickets` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) DEFAULT NULL,
  ticket_name VARCHAR(100) DEFAULT NULL,
  ticket_rating INT DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;



DROP TABLE IF EXISTS `vendor_events`;
CREATE TABLE `vendor_events` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) DEFAULT NULL,
  event_name TEXT DEFAULT NULL,
  event_type TEXT DEFAULT NULL,
  event_location TEXT DEFAULT NULL,
  event_host TEXT DEFAULT NULL,
  event_date DATE DEFAULT NULL,
  event_time_slots TEXT DEFAULT NULL,
  event_language TEXT DEFAULT NULL,
  music_type TEXT DEFAULT NULL,
  event_address TEXT DEFAULT NULL,
  event_display_image TEXT DEFAULT NULL,
  event_banner_image TEXT DEFAULT NULL,
  event_tickets TEXT DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


