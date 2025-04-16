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
-- Table structure for table `fruits`
--

DROP TABLE IF EXISTS `fruits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fruits` (
  `fruit_id` int NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`fruit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fruits`
--

LOCK TABLES `fruits` WRITE;
/*!40000 ALTER TABLE `fruits` DISABLE KEYS */;
INSERT INTO `fruits` VALUES (1,'Dates',282,2.5,0.4,75,63.4,8,21,2,656,1,0.4,0.4,149),(2,'Banana',96,1.3,0.3,23.1,12.2,2.6,74,1,358,0.3,0.2,8.7,64),(3,'Mango',60,0.8,0.4,15,13.7,1.6,83,1,168,0.2,0.1,36.4,112),(4,'Pomegranate',83,1.7,1.2,19.6,14.9,4,78,3,236,0.3,0.2,10,0),(5,'Fig',74,0.8,0.3,19.2,16.3,2.9,79,1,232,0.4,0.2,2,142),(6,'Jackfruit (Kathal)',95,1.7,0.6,23.2,19.1,1.5,74,3,448,0.6,0.4,13.7,110),(7,'Mango (Aam)',60,0.8,0.4,15,13.7,1.6,83,1,168,0.2,0.1,36.4,112),(8,'Litchi (Lichu)',66,0.8,0.4,16.5,15.2,1.3,82,1,171,0.3,0.1,71.5,0),(9,'Guava (Peyara)',68,2.6,1,14.3,8.9,5.4,81,2,417,0.3,0.2,228.3,624),(10,'Wood Apple (Kathbel)',137,2,0.6,31.8,0,5,65,13,600,0.6,0.2,8,55),(11,'Banana (Kola)',96,1.3,0.3,23.1,12.2,2.6,74,1,358,0.3,0.2,8.7,64),(12,'Papaya (Pepe)',43,0.5,0.3,10.8,7.8,1.7,88,8,182,0.1,0.1,60.9,950),(13,'Star Fruit (Kamranga)',31,1,0.3,6.7,3.9,2.8,91,2,133,0.1,0.1,34.4,61);
/*!40000 ALTER TABLE `fruits` ENABLE KEYS */;
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
