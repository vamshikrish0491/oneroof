ALTER TABLE `place_order_transactions` CHANGE COLUMN `cart_order_code` `cart_order_code` VARCHAR(20) NULL ;


DROP TABLE IF EXISTS `events_order`;
CREATE TABLE `events_order` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_uuid VARCHAR(20)  NOT NULL,
  vendor_uuid VARCHAR(20) NOT NULL,
  event_uuid VARCHAR(20) NOT NULL,
  order_code VARCHAR(10) NOT NULL,
  ticket_type TEXT NOT NULL,
  category VARCHAR(100),
  quantity INT NOT NULL,
  event_amount DOUBLE NOT NULL,
  total_amount DOUBLE NOT NULL,
  currency VARCHAR(10) NOT NULL,
  event_date DATE NOT NULL,
  time_slot TEXT DEFAULT NULL,
  is_user_arrived VARCHAR(10) DEFAULT NULL,
  qr_code TEXT DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


CALL AlterTableAddColumn ('place_order_transactions','event_order_code','VARCHAR(20) NULL AFTER `cart_order_code`');