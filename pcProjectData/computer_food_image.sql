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
-- Table structure for table `food_image`
--

DROP TABLE IF EXISTS `food_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_image` (
  `uuid` varchar(255) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `food_food_num` bigint DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UK_or7heuase24mg31b8sfpcysqq` (`food_food_num`),
  CONSTRAINT `FK7hnfrkxoug2wijrvbeqs1m8kg` FOREIGN KEY (`food_food_num`) REFERENCES `food` (`food_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_image`
--

LOCK TABLES `food_image` WRITE;
/*!40000 ALTER TABLE `food_image` DISABLE KEYS */;
INSERT INTO `food_image` VALUES ('02d703cb-9b3c-4c36-bfe9-5f0b83537a2d','img13.jpg',1005),('12a9f8e5-dd50-4991-8caf-c9b9b71dc02d','img12.jpg',1002),('13d115db-a556-454f-8f6e-a99819fdf308','img36.jpg',1028),('15b0b5fa-23b7-4084-a76c-235dedb1e922','img15.jpg',1009),('178dbc3c-6eaf-42fd-b6ce-27c68d770f8a','img30.jpg',1022),('1dca021f-4eaa-4b2b-80c6-1dcaab94758c','img23.jpg',1015),('25f0bb16-f0da-47f6-b06d-5f3aeab9e27b','img21.jpg',1013),('2fc5f4e3-fdb4-48d7-a32f-1e6631bb3bd0','img45.jpg',1035),('301acdfe-a9a9-4771-8d90-466ca7d8f052','img19.png',1012),('318558f3-05e6-49bf-801c-8b60e2616024','coffee1.jpg',1037),('41321c3f-b256-498d-ac9a-f7c509e63f20','coffee5.jpg',1041),('44f47f67-5819-4fee-965e-98bf344b9e7a','img39.jpg',1030),('463ec36d-4d6d-42e2-b6ae-7af4e3f4b43b','coffee6.jpg',1042),('475e5199-25a9-4ad1-a3e4-eb0ffafd3959','img50.jpg',1048),('49b448ed-443f-4eaf-beae-e045cfa7a3b0','img40.jpg',1031),('49eba2ec-4c53-4647-929d-633aa94eec7d','img37.jpg',1029),('4e35997a-f983-47a4-b35c-7d73143c6740','img24.jpg',1016),('4e9d0ca0-a2e7-44e4-9de8-423c0c4f51bc','coffee8.jpg',1044),('549c666c-700d-4fa5-bd63-b5ff3c5286b9','img29.jpg',1021),('63028039-b35b-4f25-88bc-9f5287dc73a0','img33.jpg',1025),('64cff223-1877-4d22-9720-a995817570ff','coffee4.jpg',1040),('6b5c2111-4105-4676-8b52-0201b2e5f1ec','img43.jpg',1033),('70686577-3680-4cfc-81ad-900995f2e4f5','img27.jpg',1019),('75a77c21-6894-472c-b898-5d409d9cbe2a','img14.jpg',1047),('7949dfb9-190a-4a38-9f40-8fb0f81754bc','img11.jpg',1003),('7eac6119-9bb0-447d-9daa-3e95b6625bb6','img51.jpg',1049),('854a0426-550e-4b55-9b93-62035ba3effc','img3.jpg',1008),('8c89489c-827a-4fab-bcc1-00ee779a0c41','img31.jpg',1023),('92123f7e-c2fc-4ba4-a277-36012f805701','img9.png',1006),('92b95fa4-58f5-4fa2-83ff-751907970d3a','img6.jpg',1007),('93f5db90-59e7-4bd6-9e42-a278516db1ae','img26.jpg',1018),('a39de7a4-6d88-4f6b-b84b-d2170dd2360a','img35.jpg',1027),('a7f7a55b-e242-45c4-b566-ee4168486930','coffee2.jpg',1038),('b1696449-afe8-4096-a9f2-1bbefbb19d6a','coffee7.jpg',1043),('b5e9de8e-9668-4df9-a065-924961550dd0','img25.jpg',1017),('bd8e92bd-ffc6-44f8-b20d-388e993f328e','img5.jpg',1004),('c404ebd9-37f7-409b-97e4-b745ef12ddd1','img34.jpg',1026),('c8494776-7f64-4b94-bd7f-a60681dee7eb','img52.jpg',1050),('d092f480-ad1f-4398-8eb6-8c63f24f2798','img17.jpg',1010),('d3ffb057-4687-4f84-8600-30f185365b24','img18.png',1011),('d9b6ecd2-7d7b-4dcf-8461-17b88f4a8807','coffee3.jpg',1039),('e2ae5d81-e88c-4077-a3f5-23a0311fd91e','img28.jpg',1020),('e2c328c9-233f-427b-84fd-f132231fcc1e','img22.jpg',1014),('e768ecd2-6637-4e23-b323-35cdefaf1fa4','img46.jpg',1036),('ebf68669-47d3-4e39-a296-64e7b9043e97','img32.jpg',1024),('edf5dd0f-4982-4709-9dce-50d71bff06aa','img44.jpg',1034),('fd422991-8f33-4806-b310-56945c6d90b2','img41.jpg',1032);
/*!40000 ALTER TABLE `food_image` ENABLE KEYS */;
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
