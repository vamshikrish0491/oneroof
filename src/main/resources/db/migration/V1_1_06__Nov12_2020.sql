DROP TABLE IF EXISTS `place_order_transactions`;
CREATE TABLE `place_order_transactions` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) NOT NULL,
  user_uuid VARCHAR(20) NOT NULL,
  cart_order_code VARCHAR(20) NOT NULL,
  amount DOUBLE NOT NULL,
  currency VARCHAR(10) NOt NULL,
  payment_id TEXT NOT NULL,
  order_id TEXT DEFAULT NULL,
  signature TEXT DEFAULT NULL,
  payment_date DATETIME NOT NULL,
  uuid VARCHAR(20) NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;

CALL AlterTableAddColumn ('place_order','is_vendor_canceled','VARCHAR(10) DEFAULT NULL AFTER `is_user_arrived`');
CALL AlterTableAddColumn ('place_order','is_user_canceled','VARCHAR(10) DEFAULT NULL AFTER `is_vendor_canceled`');
CALL AlterTableAddColumn ('place_order','order_canceled_date','DATETIME DEFAULT NULL AFTER `is_user_canceled`');


CALL AlterTableAddColumn ('user','preferred_drinks','TEXT DEFAULT NULL AFTER `user_image`');
CALL AlterTableAddColumn ('user','preferred_music','TEXT DEFAULT NULL AFTER `preferred_drinks`');