-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: food
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `nuts`
--

DROP TABLE IF EXISTS `nuts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nuts` (
  `nut_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `calories` int DEFAULT NULL,
  `protein` float DEFAULT NULL,
  `fat` float DEFAULT NULL,
  `carbs` float DEFAULT NULL,
  `sugar` float DEFAULT NULL,
  `fiber` float DEFAULT NULL,
  `water_content` float DEFAULT NULL,
  `sodium` float DEFAULT NULL,
  `potassium` float DEFAULT NULL,
  `iron` float DEFAULT NULL,
  `zinc` float DEFAULT NULL,
  `vitamin_c` float DEFAULT NULL,
  `vitamin_a` float DEFAULT NULL,
  PRIMARY KEY (`nut_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nuts`
--

LOCK TABLES `nuts` WRITE;
/*!40000 ALTER TABLE `nuts` DISABLE KEYS */;
INSERT INTO `nuts` VALUES (1,'Almonds',579,21,49,21.6,4.8,12.5,4,1,705,3.7,3.2,0,0),(2,'Walnuts',654,15.2,65.2,13.7,2.6,6.7,4,1,441,2.9,3,1.3,20),(3,'Cashews',553,18.2,43.9,30.2,5,3.3,5,12,565,6.2,5.1,0,0),(4,'Pistachios',562,20,45.3,27.2,7.7,10.6,6,1,1025,3.9,2.2,0,0),(5,'Hazelnuts',628,14.1,60.8,16.7,4,9.7,5,1,680,4.7,2.5,0,0),(6,'Betel Nut (Supari)',330,4,6,64,0,15,10,10,300,0.8,1.2,0,0),(7,'Sesame Seeds (Til)',573,17.7,49.7,23.4,0.3,11.8,5,11,468,14.6,7.8,0,9),(8,'Niger Seeds (Kalojeera)',573,17.7,49.7,23.4,0.3,11.8,5,11,468,14.6,7.8,0,9),(9,'Poppy Seeds (Posto)',525,18,42,28,3,19.5,6,26,719,9.8,7.9,1,0),(10,'Coconut (Narkel)',354,3.3,33.5,15.2,6.2,9,8,2,559,2.1,1.5,0,0);
/*!40000 ALTER TABLE `nuts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-14 11:13:06
