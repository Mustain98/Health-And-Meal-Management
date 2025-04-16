-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: meal_planner
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
-- Table structure for table `meal_items`
--

DROP TABLE IF EXISTS `meal_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal_items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `meal_id` int NOT NULL,
  `food_name` varchar(100) NOT NULL,
  `amount` decimal(5,1) NOT NULL,
  `calories` decimal(5,1) DEFAULT NULL,
  `protein` decimal(5,1) DEFAULT NULL,
  `carbs` decimal(5,1) DEFAULT NULL,
  `fat` decimal(5,1) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `meal_id` (`meal_id`),
  CONSTRAINT `meal_items_ibfk_1` FOREIGN KEY (`meal_id`) REFERENCES `meals` (`meal_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_items`
--

LOCK TABLES `meal_items` WRITE;
/*!40000 ALTER TABLE `meal_items` DISABLE KEYS */;
INSERT INTO `meal_items` VALUES (6,11,'Chicken Breast',200.0,330.0,62.0,0.0,7.2),(7,12,'Peanuts',200.0,1134.0,50.0,32.2,98.4),(8,12,'Banana',200.0,192.0,2.6,46.2,0.6),(12,15,'Eggs',50.0,77.5,6.5,0.6,5.5),(13,15,'Chicken Breast',200.0,330.0,62.0,0.0,7.2),(14,16,'Eggs',150.0,232.5,19.5,1.7,16.5),(15,16,'Parboiled Rice',200.0,246.0,5.8,52.0,0.6),(16,17,'Chicken Breast',200.0,330.0,62.0,0.0,7.2),(17,17,'Parboiled Rice',100.0,123.0,2.9,26.0,0.3),(20,19,'Parboiled Rice',200.0,246.0,5.8,52.0,0.6),(21,19,'Chicken Breast',150.0,247.5,46.5,0.0,5.4),(22,19,'Peanuts',50.0,283.5,12.5,8.1,24.6),(23,20,'Dates',50.0,141.0,1.3,37.5,0.2),(24,20,'Eggs',50.0,77.5,6.5,0.6,5.5),(25,20,'Chicken Breast',100.0,165.0,31.0,0.0,3.6),(26,21,'Parboiled Rice',200.0,246.0,5.8,52.0,0.6),(27,21,'Beef (Lean)',200.0,500.0,52.0,0.0,30.0);
/*!40000 ALTER TABLE `meal_items` ENABLE KEYS */;
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
