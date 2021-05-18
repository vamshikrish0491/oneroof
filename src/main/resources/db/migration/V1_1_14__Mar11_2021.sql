
DROP TABLE IF EXISTS `special_package`;
CREATE TABLE  `special_package` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) NOT NULL,
  service_type TEXT DEFAULT NULL,
  start_date DATE DEFAULT NULL,
  end_date DATE DEFAULT NULL,
  event_banner_image TEXT DEFAULT NULL,
  event_mobile_banner_image TEXT DEFAULT NULL,
  created_date DATETIME,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;