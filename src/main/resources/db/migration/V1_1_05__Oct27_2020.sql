DROP TABLE IF EXISTS `place_order`;
CREATE TABLE `place_order` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_uuid VARCHAR(20)  NOT NULL,
  vendor_uuid VARCHAR(20) NOT NULL,
  order_code VARCHAR(10) NOT NULL,
  cart_order_code VARCHAR(10) NOT NULL,
  service_uuid VARCHAR(20) NOT NULL,
  master_service_uuid VARCHAR(20) NOT NULL,
  quantity INT NOT NULL,
  order_amount DOUBLE NOT NULL,
  total_amount DOUBLE NOT NULL,
  currency VARCHAR(10) NOT NULL,
  service_ordered_date DATE NOT NULL,
  service_time_slot TEXT DEFAULT NULL,
  package_menu_items TEXT DEFAULT NULL,
  order_status VARCHAR(50) NOT NULL,
  order_status_modified_date DATETIME DEFAULT NULL,
  is_user_arrived VARCHAR(10) DEFAULT NULL,
  qr_code TEXT DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


CALL AlterTableAddColumn ('vendor_master_service','minimum_order','DOUBLE DEFAULT NULL AFTER `offer_price`');
CALL AlterTableAddColumn ('vendor_master_service','discount_value','DOUBLE DEFAULT NULL AFTER `minimum_order`');
CALL AlterTableAddColumn ('vendor_master_service','discount_type','VARCHAR(50) DEFAULT NULL AFTER `discount_value`');

DROP TABLE IF EXISTS `surprise_details`;
CREATE TABLE `surprise_details` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  surprise_name VARCHAR(20)  NOT NULL,
  surprise_type VARCHAR(20) NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('1', 'Wife', 'Surprise For', 'Gvb8Uj6d', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('2', 'Husband', 'Surprise For', 'Fbc6Rdfh', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('3', 'Boy Friend', 'Surprise For', 'Cd4oNv5d', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('4', 'Girl Friend', 'Surprise For', 'Hb5dc3Sd', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('5', 'Friend', 'Surprise For', 'N6tfEd4s', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('6', 'Fiance', 'Surprise For', 'Okj7Gvde', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('7', 'Fiancee', 'Surprise For', 'J6tf4Sdx', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('8', 'Birthday', 'Surprise Occation', 'Ujh6Fcds', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('9', 'Anniversary', 'Surprise Occation', 'Yh6fD4es', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('10', 'Proposal', 'Surprise Occation', 'Sx4rF6td', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('11', 'Just like that', 'Surprise Occation', 'Kn7yFd3w', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('12', 'Thanking of you', 'Surprise Occation', 'C7yFds2a', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('13', 'Apology', 'Surprise Occation', 'Kju7Dsxh', '1');
INSERT INTO `surprise_details` (`id`, `surprise_name`, `surprise_type`, `uuid`, `status`) VALUES ('14', 'Promotion', 'Surprise Occation', 'H6tfr3Ws', '1');

CALL AlterTableAddColumn ('user_cart','surprise_details','VARCHAR(50) DEFAULT NULL AFTER `package_menu_items`');
CALL AlterTableAddColumn ('user_cart','surprise_instructions','TEXT DEFAULT NULL AFTER `surprise_details`');

CALL AlterTableAddColumn ('place_order','surprise_details','VARCHAR(50) DEFAULT NULL AFTER `package_menu_items`');
CALL AlterTableAddColumn ('place_order','surprise_instructions','TEXT DEFAULT NULL AFTER `surprise_details`');

