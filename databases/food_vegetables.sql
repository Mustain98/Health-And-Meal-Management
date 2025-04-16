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
-- Table structure for table `vegetables`
--

DROP TABLE IF EXISTS `vegetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vegetables` (
  `veg_id` int NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`veg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vegetables`
--

LOCK TABLES `vegetables` WRITE;
/*!40000 ALTER TABLE `vegetables` DISABLE KEYS */;
INSERT INTO `vegetables` VALUES (1,'Broccoli',55,3.7,0.6,11.2,1.7,5.1,89,33,316,0.7,0.5,89.2,567),(2,'Spinach',23,2.9,0.4,3.6,0.4,2.2,91,79,558,2.7,0.9,28.1,2813),(3,'Carrot',41,0.9,0.2,9.6,4.7,2.8,88,69,320,0.6,0.2,5.9,835),(4,'Eggplant',25,0.8,0.2,5.9,3.5,3,92,2,188,0.3,0.2,2.2,23),(5,'Okra',33,1.9,0.2,7.5,1.5,3.2,90,7,299,0.6,0.6,23,716),(6,'Brinjal (Begun)',25,0.8,0.2,5.9,3.5,3,92,2,188,0.3,0.2,2.2,23),(7,'Bitter Gourd (Korola)',17,1,0.2,3.7,0,2.8,94,5,296,0.4,0.8,84,471),(8,'Pumpkin (Misti Kumra)',26,1,0.1,6.5,2.8,0.5,91,1,340,0.8,0.3,9,8513),(9,'Ridge Gourd (Jhinga)',20,0.5,0.1,4.4,0,1.7,93,3,150,0.3,0.2,12,410),(10,'Pointed Gourd (Potol)',20,0.8,0.1,4.4,0,1.5,94,5,150,0.6,0.3,11,240),(11,'Amaranth Leaves (Data Shak)',23,2.5,0.3,4,0,2,91,20,611,2.3,0.9,43,2917),(12,'Drumstick (Sajna)',37,2.1,0.2,8.5,0,3.2,88,42,461,0.4,0.5,141,74),(13,'Taro Root (Kochu)',112,1.5,0.2,26.5,0.4,4.1,70,15,484,0.6,0.3,4.5,76);
/*!40000 ALTER TABLE `vegetables` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-14 11:13:05
