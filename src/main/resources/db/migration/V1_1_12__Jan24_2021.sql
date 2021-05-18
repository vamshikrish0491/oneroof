DROP TABLE IF EXISTS `popular_cities`;
CREATE TABLE `popular_cities` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  city_name TEXT NOT NULL,
  latitude VARCHAR(20) DEFAULT NULL,
  longitude VARCHAR(20) DEFAULT NULL,
  status INT NOT NULL,
  city_image TEXT DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;

INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (1,'Mumbai','19.0760','72.8777',1,'/resources/img/cities/mumbai-icon.png');
INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (2,'Chandigarh','30.7333','76.7794',1,'/resources/img/cities/chandigarh-icon.png');
INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (3,'Chennai','13.0827','80.2707',1,'/resources/img/cities/chennai-icon.png');
INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (4,'Goa','15.2993','74.1240',1,'/resources/img/cities/goa-icon.png');
INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (5,'Vizag','17.6868','83.2185',1,'/resources/img/cities/vizag-icon.png');
INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (6,'Vijayawada','16.5062','80.6480',1,'/resources/img/cities/vijayawada-icon.png');
INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (7,'Bangalore','12.9716','77.5946',1,'/resources/img/cities/bangalore-icon.png');
INSERT INTO `popular_cities` (`id`,`city_name`,`latitude`,`longitude`,`status`,`city_image`) VALUES (8,'Hyderabad','17.3850','78.4867',1,'/resources/img/cities/hyderabad-icon.png');
