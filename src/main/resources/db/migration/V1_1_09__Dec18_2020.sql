CALL AlterTableAddColumn ('vendors','order_approval','VARCHAR(50) DEFAULT NULL AFTER `vendor_videos`');
CALL AlterTableAddColumn ('vendor_service_settings','order_approval','VARCHAR(50) DEFAULT NULL');


DROP TABLE IF EXISTS `user_canceled_orders`;
CREATE TABLE `user_canceled_orders` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_uuid VARCHAR(20) NOT NULL,
  user_uuid VARCHAR(20) NOT NULL,
  master_service_uuid VARCHAR(20) NOT NULL,
  service_uuid VARCHAR(20) NOT NULL,
  order_code VARCHAR(20) NOT NULL,
  cart_order_code VARCHAR(20) NOT NULL,
  service_ordered_date DATE NOT NULL,
  service_time_slot TEXT DEFAULT NULL,
  canceled_order_amount DOUBLE NOT NULL,
  total_transaction_amount DOUBLE NOT NULL,
  currency VARCHAR(10) NOT NULL,
  payment_id TEXT NOT NULL,
  order_id TEXT DEFAULT NULL,
  signature TEXT DEFAULT NULL,
  payment_date DATETIME NOT NULL,
  canceled_date DATETIME NOT NULL,
  canceled_by VARCHAR(50) NOT NULL,
  uuid VARCHAR(20) NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;
