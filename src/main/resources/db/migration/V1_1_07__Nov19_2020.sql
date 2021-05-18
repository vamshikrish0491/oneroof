DROP TABLE IF EXISTS `password_verification`;
CREATE TABLE `password_verification` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_uuid VARCHAR(20) NOT NULL,
  email TEXT NOT NULL,
  verification_code VARCHAR(10) NOT NULL,
  is_verified VARCHAR(10) NOT NULL,
  uuid VARCHAR(20) NOT NULL,
  created_date DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;



DROP TABLE IF EXISTS `vendor_service_settings`;
CREATE TABLE `vendor_service_settings` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) NOT NULL,
  service_uuid VARCHAR(20) NOT NULL,
  is_entry_ratio_enabled VARCHAR(10) DEFAULT NULL,
  men_entry_ratio INT DEFAULT NULL,
  women_entry_ratio INT DEFAULT NULL,
  is_cancel_order_enabled VARCHAR(10) DEFAULT NULL,
  cancel_order_value INT DEFAULT NULL,
  cancel_order_type VARCHAR(20) DEFAULT NULL,
  is_reschedule_order_enabled VARCHAR(10) DEFAULT NULL,
  reschedule_order_value INT DEFAULT NULL,
  reschedule_order_type VARCHAR(20) DEFAULT NULL,
  is_order_prior_booking_enabled VARCHAR(10) DEFAULT NULL,
  order_prior_booking_value INT DEFAULT NULL,
  order_prior_booking_type VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


