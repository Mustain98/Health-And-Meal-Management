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
-- Table structure for table `proteins`
--

DROP TABLE IF EXISTS `proteins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proteins` (
  `protein_id` int NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`protein_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proteins`
--

LOCK TABLES `proteins` WRITE;
/*!40000 ALTER TABLE `proteins` DISABLE KEYS */;
INSERT INTO `proteins` VALUES (1,'Chicken Breast',165,31,3.6,0,0,0,65,74,256,0.9,1,0,13),(2,'Chicken Thigh',209,26,10.9,0,0,0,65,86,259,1.3,2.4,0,20),(3,'Turkey Breast',135,29,1.1,0,0,0,66,62,292,1.2,2,0,0),(4,'Duck',337,27,28.4,0,0,0,50,63,297,2.5,3.1,0,80),(5,'Beef (Lean)',250,26,15,0,0,0,55,65,300,2.5,3.1,0,0),(6,'Lamb Chop',294,24.5,21,0,0,0,54,65,310,1.9,4.4,0,0),(7,'Goat Meat',143,27.1,2.6,0,0,0,63,82,326,3.6,3.9,0,10),(8,'Salmon',208,20,13,0,0,0,64,50,420,0.8,0.9,0,50),(9,'Tuna',144,25,4.9,0,0,0,70,50,350,1.3,1.5,0,15),(10,'Hilsa Fish',273,21.8,19.4,0,0,0,65,50,300,1.2,0.8,0,150),(11,'Shrimp',99,20,1.5,0.2,0,0,78,150,259,1,1.4,0,100),(12,'Eggs',155,13,11,1.1,1,0,74,142,132,1.2,1.3,0,520),(13,'Cottage Cheese',98,11,4.3,3.4,2.7,0,81,364,104,0.5,0.6,0,180),(14,'Tofu',144,15,8,3.9,0.7,0.3,72,10,150,2.2,2,0,100),(15,'Lentils',116,9,0.4,20,1.8,7.9,75,2,369,3.3,3.6,1.5,5),(16,'Chickpeas',164,8.9,2.6,27.4,4.8,7.6,69,24,291,2.9,2.5,1.3,3),(17,'Soya Chunks',345,52,0.5,33,0,13,7,20,1500,9,4,0,0),(18,'Hilsa Fish',273,21.8,19.4,0,0,0,65,50,300,1.2,0.8,0,150),(19,'Rui Fish',97,16.5,2.7,0,0,0,80,49,275,0.5,0.4,0,30),(20,'Katla Fish',111,17,4.5,0,0,0,78,45,290,0.6,0.5,0,40),(21,'Pangas Fish',92,15,3,0,0,0,82,40,260,0.4,0.3,0,25),(22,'Beef (Desi)',250,26,15,0,0,0,55,65,300,2.5,3.1,0,0),(23,'Goat Meat',143,27.1,2.6,0,0,0,63,82,326,3.6,3.9,0,10),(24,'Chicken (Desi)',165,31,3.6,0,0,0,65,74,256,0.9,1,0,13),(25,'Duck (Desi)',337,27,28.4,0,0,0,50,63,297,2.5,3.1,0,80),(26,'Eggs (Desi)',155,13,11,1.1,1,0,74,142,132,1.2,1.3,0,520),(27,'Prawns',99,20,1.5,0.2,0,0,78,150,259,1,1.4,0,100);
/*!40000 ALTER TABLE `proteins` ENABLE KEYS */;
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
