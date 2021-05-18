DROP TABLE IF EXISTS `vendor_ratings`;
CREATE TABLE `vendor_ratings` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) NOT NULL,
  user_uuid VARCHAR(20) NOT NULL,
  rating INT NOT NULL,
  rating_description TEXT NOT NULL,
  uuid VARCHAR(20) NOT NULL,
  status INT NOT NULL,
  created_date DATE DEFAULT NULL,
  created_time VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `place_order_ratings`;
CREATE TABLE `place_order_ratings` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_uuid VARCHAR(20) NOT NULL,
  place_order_code VARCHAR(20) NOT NULL,
  rating INT NOT NULL,
  rating_tag TEXT DEFAULT NULL,
  rating_description TEXT DEFAULT NULL,
  uuid VARCHAR(20) NOT NULL,
  status INT NOT NULL,
  created_date DATE DEFAULT NULL,
  created_time VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;