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
-- Table structure for table `carbs`
--

DROP TABLE IF EXISTS `carbs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carbs` (
  `carb_id` int NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`carb_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carbs`
--

LOCK TABLES `carbs` WRITE;
/*!40000 ALTER TABLE `carbs` DISABLE KEYS */;
INSERT INTO `carbs` VALUES (1,'Basmati Rice',130,2.7,0.3,28,0.1,0.4,68,1,35,0.2,0.4,0,0),(2,'Whole Wheat Bread',247,13,3.5,41,5,7,38,414,115,2.1,1.6,0,0),(3,'Quinoa',120,4.1,1.9,21.3,0.9,2.8,72,7,172,1.5,1.3,1.3,1),(4,'Oats',389,16.9,6.9,66.3,0.9,10.6,8,2,329,4.3,3.1,0,0),(5,'Sweet Potato',86,1.6,0.1,20.1,4.2,3,77,55,337,0.6,0.3,2.4,192),(6,'Whole Wheat Flour',364,13.7,2.5,76.1,0.4,12.2,11,2,363,3.9,2.9,0,0),(7,'Basmati Rice',130,2.7,0.3,28,0.1,0.4,68,1,35,0.2,0.4,0,0),(8,'Red Rice (Chakhao)',111,2.6,0.9,23,0.3,1.8,70,5,150,0.4,0.8,0,0),(9,'Wheat Flour (Atta)',364,13.7,2.5,76.1,0.4,12.2,11,2,363,3.9,2.9,0,0),(10,'Luchi Flour',364,13.7,2.5,76.1,0.4,12.2,11,2,363,3.9,2.9,0,0),(11,'Parboiled Rice',123,2.9,0.3,26,0.1,1,68,5,56,0.4,0.6,0,0),(12,'Suji (Semolina)',360,12.7,1.1,72.8,0.3,3.9,11,1,186,4.4,1.1,0,0),(13,'Muri (Puffed Rice)',402,8,3.8,80,0.2,2.8,7,5,150,3,2,0,0),(14,'Chira (Flattened Rice)',111,2.6,0.9,23,0.3,1.8,70,5,150,0.4,0.8,0,0);
/*!40000 ALTER TABLE `carbs` ENABLE KEYS */;
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
