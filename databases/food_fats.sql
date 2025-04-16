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
-- Table structure for table `fats`
--

DROP TABLE IF EXISTS `fats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fats` (
  `fat_id` int NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`fat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fats`
--

LOCK TABLES `fats` WRITE;
/*!40000 ALTER TABLE `fats` DISABLE KEYS */;
INSERT INTO `fats` VALUES (1,'Olive Oil',884,0,100,0,0,0,0,2,1,0,0,0,0),(2,'Avocado',160,2,15,9,0.7,7,73,7,485,0.6,0.6,10,146),(3,'Almond Butter',614,21,55,20,3.9,12,4,1,705,3.7,3.2,0,0),(4,'Ghee',900,0,100,0,0,0,0,0,0,0,0,0,0),(5,'Tahini',595,17,53,21,0.5,9,3,115,414,8.9,4.6,0,67),(6,'Mustard Oil',884,0,100,0,0,0,0,0,0,0,0,0,0),(7,'Ghee (Desi)',900,0,100,0,0,0,0,0,0,0,0,0,0),(8,'Coconut Oil',862,0,100,0,0,0,0,3,0,0,0,0,0),(9,'Sesame Oil (Til)',884,0,100,0,0,0,0,0,0,0,0,0,0),(10,'Niger Seed Oil (Kalojeera)',884,0,100,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `fats` ENABLE KEYS */;
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
