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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(255) NOT NULL,
  `mod_date` datetime(6) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `del` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `social` bit(1) NOT NULL,
  `time` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('admin','2024-05-04 17:44:25.787597','2024-05-04 17:44:25.787597',_binary '\0','admin@admin.com','관리자','$2a$10$emPStfvflCbKlNe4Vh.4W.Kmbn2ZTJu9AQy3VAdFuCyCDYAaUMzdy','0101010101',_binary '\0',NULL),('dodo607','2024-05-24 16:20:04.984932','2024-04-20 17:07:11.467139',_binary '\0','iy398884@gmail.com','이영수','$2a$10$LvAIUREvE9JtFU4LrCf8Lua3nsUXt6B15RmlSGa6sxvL24Jx.lMMu','01012345678',_binary '\0','21:01:00.000000'),('dodo6071','2024-05-09 15:11:47.531180','2024-04-27 19:46:22.558158',_binary '\0','asdfna70@gmail.com','이름','$2a$10$hMfr9SXriW/l4LHpXj2Eh.6EyJHKhXpFlvo.w7Z58m5tEC2tPIP4O','01086345669',_binary '\0','22:57:00.000000'),('myguor11','2024-04-28 14:27:42.446524','2024-04-28 14:21:22.117486',_binary '\0','oppayoj@naver.com','이름입니당','$2a$10$2fRmiXH0hOEBwoZPk/KZ.ur9qKo1SSWdrd9NIcgo1P8AupCwn80TC','01012345678',_binary '\0','23:58:00.000000'),('user2','2024-04-14 21:29:22.195091','2024-04-14 21:29:22.195091',_binary '\0','email2@email.com','이름','$2a$10$hLvVZxgmUq7h2BXD1Gt0L.D2cM6L8jRRkn9R1Az2rAcv9GeSTN0iy','123-123-123',_binary '\0','00:00:00.000000');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
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
