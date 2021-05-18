DROP TABLE IF EXISTS `wayuparty_serivce_sub_category`;
DROP TABLE IF EXISTS `wayuparty_serivce_category`;
DROP TABLE IF EXISTS `wayuparty_services`;

CREATE TABLE `wayuparty_services` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_id BIGINT DEFAULT NULL,
  service_name TEXT NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  service_image TEXT DEFAULT NULL,
  created_by BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;


CREATE TABLE `wayuparty_serivce_category` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  service_id BIGINT NOT NULL,
  vendor_id BIGINT DEFAULT NULL,
  category_name TEXT NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  category_image TEXT DEFAULT NULL,
  created_by BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(service_id) REFERENCES `wayuparty_services`(id),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;



CREATE TABLE `wayuparty_serivce_sub_category` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  category_id BIGINT NOT NULL,
  vendor_id BIGINT DEFAULT NULL,
  sub_category_name TEXT NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  created_by BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(category_id) REFERENCES `wayuparty_serivce_category`(id),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;


INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('1', 'Venue', 'J6rUaf5n', '1', '/resources/img/venue.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('2', 'Cuisine', 'Jhb5S3nB', '1', '/resources/img/cuisine.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('3', 'Events', 'Jhb5S3nC', '1', '/resources/img/events.jpg');

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (1,1,NULL,'500','G6jHgfWs',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (2,1,NULL,'750','Hbg6wFgp',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (3,1,NULL,'1000','7HgtrdEo',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (4,1,NULL,'1500','Uhjt5Rfd',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (5,1,NULL,'20000','Io9Ygf4E',NULL,1,NULL);

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (6,2,NULL,'Indian','L9uF4s3d',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (7,2,NULL,'Italian','B6rFd3x2',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (8,2,NULL,'Mexican','Yt6ldfC3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (9,2,NULL,'Chinese','G6dX4td3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (10,2,NULL,'Oriental','Vhy5D5f3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (11,2,NULL,'Chef Special','L9uF4s3e',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (12,2,NULL,'American','B6rFd3x3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (13,2,NULL,'Multi Cuisine','Yt6ldfC4',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (14,2,NULL,'Mediterranean','G6dX4td4',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (15,2,NULL,'Asian','Vhy5D5f4',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (16,2,NULL,'European','G6dX4te4',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (17,2,NULL,'Parsi','Vhy5D5g3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (18,2,NULL,'Iranian','L9uF4s4e',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (19,2,NULL,'Greek','B6rFd3y3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (20,2,NULL,'Spanish','Yt6ldfD4',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (21,2,NULL,'Labanese','G6dX4te3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (22,2,NULL,'Finger Food','Vhy5D5g4',NULL,1,NULL);


