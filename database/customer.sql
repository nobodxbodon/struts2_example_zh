DROP TABLE IF EXISTS `mkyong`.`customer`;
CREATE TABLE  `mkyong`.`customer` (
  `CUSTOMER_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `姓名` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;