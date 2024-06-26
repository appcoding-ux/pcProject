-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: computer
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order_food`
--

DROP TABLE IF EXISTS `order_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_food` (
  `order_food_num` bigint NOT NULL AUTO_INCREMENT,
  `food_amount` int NOT NULL,
  `food_food_num` bigint DEFAULT NULL,
  `orders_order_num` bigint DEFAULT NULL,
  PRIMARY KEY (`order_food_num`),
  KEY `FKqse1j51vnc4iib3ei0wdn09xq` (`food_food_num`),
  KEY `FKiig7kiyhdcl2u1r4awapu212x` (`orders_order_num`),
  CONSTRAINT `FKiig7kiyhdcl2u1r4awapu212x` FOREIGN KEY (`orders_order_num`) REFERENCES `orders` (`order_num`),
  CONSTRAINT `FKqse1j51vnc4iib3ei0wdn09xq` FOREIGN KEY (`food_food_num`) REFERENCES `food` (`food_num`)
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_food`
--

LOCK TABLES `order_food` WRITE;
/*!40000 ALTER TABLE `order_food` DISABLE KEYS */;
INSERT INTO `order_food` VALUES (1,1,1027,1),(2,1,1028,1),(3,1,1038,1),(4,1,1044,1),(5,1,1047,1),(6,1,1023,1),(7,1,1026,1),(8,1,1003,1),(9,1,1005,1),(10,1,1002,2),(11,1,1011,2),(12,1,1021,2),(13,1,1008,2),(14,1,1014,2),(15,1,1015,2),(16,2,1009,2),(17,1,1005,2),(18,1,1007,3),(19,1,1005,3),(20,1,1015,3),(21,1,1006,3),(22,1,1010,3),(23,1,1009,4),(24,1,1015,4),(25,1,1016,4),(26,2,1010,4),(27,2,1014,4),(28,1,1011,4),(29,1,1014,5),(30,1,1008,5),(31,1,1013,5),(32,1,1009,5),(33,1,1011,6),(34,1,1016,6),(35,1,1010,6),(36,1,1009,6),(37,1,1014,6),(38,1,1015,6),(39,1,1014,7),(40,1,1038,7),(41,1,1041,7),(42,1,1009,7),(43,1,1016,7),(44,1,1020,7),(45,1,1021,7),(46,1,1008,8),(47,1,1025,8),(48,1,1023,8),(49,1,1003,8),(50,1,1005,8),(51,1,1027,8),(52,1,1024,8),(53,1,1011,9),(54,1,1010,9),(55,1,1006,9),(56,1,1005,9),(57,1,1009,9),(58,1,1008,10),(59,1,1007,10),(60,1,1013,10),(61,1,1014,10),(62,1,1041,10),(63,1,1003,10),(64,1,1010,10),(65,1,1008,11),(66,1,1047,11),(67,1,1030,11),(68,1,1003,11),(69,1,1004,11),(70,1,1038,11),(71,1,1009,12),(72,1,1017,12),(73,1,1037,12),(74,1,1006,12),(75,1,1003,12),(76,1,1033,12),(77,1,1023,13),(78,1,1030,13),(79,1,1024,13),(80,1,1028,13),(81,1,1029,13),(82,1,1010,13),(83,1,1037,14),(84,1,1026,14),(85,1,1033,14),(86,1,1018,14),(87,1,1032,15),(88,1,1019,15),(89,1,1038,15),(90,1,1005,15),(91,1,1004,16),(92,1,1030,16),(93,1,1003,16),(94,1,1040,16),(95,1,1027,17),(96,1,1035,17),(97,1,1036,17),(98,1,1002,17),(99,1,1023,17),(100,1,1011,18),(101,1,1021,18),(102,1,1025,18),(103,1,1010,18),(104,1,1007,19),(105,1,1033,19),(106,1,1032,19),(107,1,1014,19),(108,1,1024,19),(109,1,1038,20),(110,1,1036,20),(111,1,1017,20),(112,1,1028,20),(113,1,1019,21),(114,1,1043,21),(115,1,1012,21),(116,1,1047,22),(117,1,1033,22),(118,1,1021,22),(119,1,1026,22),(120,1,1020,23),(121,1,1037,23),(122,1,1033,23),(123,1,1030,23),(124,1,1036,23),(125,1,1004,24),(126,1,1002,24),(127,1,1007,24),(128,1,1035,24),(129,1,1020,25),(130,1,1040,25),(131,1,1033,25),(132,1,1024,25),(133,1,1019,26),(134,2,1006,26),(135,1,1023,26),(136,1,1008,26),(137,1,1047,27),(138,1,1012,27),(139,1,1030,27),(140,1,1015,28),(141,1,1017,28),(142,1,1043,28),(143,1,1032,28),(144,1,1050,29),(145,1,1048,29),(146,1,1010,29),(147,1,1042,30),(148,1,1026,30),(149,1,1019,30),(150,1,1024,30),(151,1,1048,31),(152,1,1036,31),(153,1,1009,31),(154,1,1033,31),(155,1,1017,32),(156,1,1004,32),(157,1,1028,32),(158,1,1023,32),(159,1,1014,32),(160,1,1005,33),(161,1,1008,33),(162,1,1035,33),(163,1,1021,34),(164,1,1044,34),(165,1,1033,34),(166,1,1024,34),(167,1,1034,35),(168,1,1033,35),(169,1,1005,35),(170,1,1009,35),(171,1,1006,35),(172,1,1004,35),(173,1,1026,35),(174,1,1024,36),(175,1,1018,36),(176,1,1016,36),(177,1,1049,36),(178,1,1041,36),(179,1,1028,37),(180,2,1002,37),(181,1,1050,37),(182,1,1012,37),(183,1,1043,38),(184,1,1034,38),(185,1,1020,38),(186,1,1033,38),(187,1,1032,38),(188,1,1007,39),(189,2,1047,39),(190,1,1019,39),(191,1,1010,39),(192,1,1017,39),(193,1,1015,40),(194,1,1048,40),(195,1,1029,40),(196,1,1024,40),(197,1,1025,40),(198,1,1034,41),(199,2,1035,41),(200,1,1009,41),(201,1,1039,41),(202,1,1011,42),(203,1,1012,42),(204,1,1037,42),(205,1,1027,42),(206,2,1033,43),(207,1,1047,43),(208,1,1024,43),(209,1,1020,43),(210,1,1007,44),(211,1,1050,44),(212,1,1004,44),(213,1,1024,44),(214,1,1019,45),(215,1,1029,45),(216,2,1002,45),(217,1,1017,45),(218,1,1002,46),(219,1,1010,46),(220,1,1019,46),(221,1,1011,46),(222,1,1015,46),(223,1,1007,47),(224,1,1008,47),(225,1,1012,47),(226,1,1002,47),(227,1,1036,48),(228,1,1002,48),(229,1,1011,48),(230,1,1010,48),(231,1,1024,49),(232,1,1011,49),(233,1,1025,49),(234,1,1002,49),(235,1,1017,50),(236,1,1007,50),(237,1,1021,50),(238,1,1048,50),(239,1,1019,51),(240,1,1036,51),(241,1,1014,51),(242,1,1032,51),(243,1,1015,52),(244,1,1034,52),(245,1,1016,52),(246,1,1038,52),(247,1,1050,53),(248,1,1017,53),(249,1,1028,53),(250,1,1023,53),(251,1,1024,53),(252,1,1040,53),(253,1,1002,54),(254,1,1030,54),(255,1,1008,54),(256,1,1033,54),(257,1,1019,55),(258,1,1014,55),(259,1,1007,55),(260,1,1015,55),(261,1,1050,56),(262,1,1024,56),(263,1,1029,56),(264,1,1026,56),(265,1,1048,56),(266,1,1002,57),(267,1,1023,57),(268,1,1035,57),(269,1,1009,57),(270,1,1043,58),(271,1,1030,58),(272,1,1033,58),(273,1,1009,58),(274,1,1004,58),(275,1,1040,59),(276,1,1010,59),(277,1,1050,59),(278,1,1049,60),(279,1,1017,60),(280,2,1006,60),(281,1,1027,60),(282,1,1015,61),(283,2,1037,61),(284,1,1011,61),(285,1,1010,61),(286,1,1012,62),(287,1,1036,62),(288,1,1024,62),(289,1,1044,62);
/*!40000 ALTER TABLE `order_food` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-24 16:22:16
