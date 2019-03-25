DROP TABLE IF EXISTS `演示`.`顾客`;
CREATE TABLE  `演示`.`顾客` (
  `顾客_ID` bigint(20) unsigned COLLATE utf8_unicode_ci NOT NULL AUTO_INCREMENT,
  `姓名` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`顾客_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;