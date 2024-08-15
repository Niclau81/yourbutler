CREATE DATABASE  IF NOT EXISTS `recipe_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `recipe_db`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: recipe_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `recipe_id` int NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(100) NOT NULL,
  `cuisine_id` int DEFAULT NULL,
  `instructions` text,
  `cooking_time` varchar(50) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`),
  KEY `cuisine_id` (`cuisine_id`),
  CONSTRAINT `recipe_ibfk_1` FOREIGN KEY (`cuisine_id`) REFERENCES `cuisine` (`cuisine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Spaghetti Pomodoro',1,'Cook pasta. Sauté garlic in olive oil. Add tomatoes and basil. Toss with pasta.','30 Min','/images/recipes/spaghetti_pomodoro.jpg'),(2,'Kung Pao Chicken',2,'Stir-fry chicken with peanuts, vegetables, and Kung Pao sauce. Serve with rice.','25 Min','/images/recipes/kung_pao_chicken.jpg'),(3,'Dal Tadka',3,'Cook lentils with spices. Prepare tadka with ghee, cumin, and garlic. Mix and serve.','40 Min','/images/recipes/dal_tadka.jpg'),(4,'Guacamole',4,'Mash avocados. Mix with diced onion, tomato, cilantro, lime juice, and salt.','15 Min','/images/recipes/guacamole.jpg'),(5,'Salmon Nigiri',5,'Prepare sushi rice. Slice salmon. Shape rice and top with salmon. Serve with soy sauce and wasabi.','20 Min','/images/recipes/Salmon_Nigiri.jpg'),(6,'Ratatouille',6,'Chop vegetables. Sauté each vegetable separately. Layer in a casserole dish. Bake for 45 minutes.','1 hour 30 minutes','/images/recipes/ratatouille.jpg'),(7,'Sweet Sour Pork',2,'Marinate 200g pork shoulder with 1 tsp light soy sauce, 1 tsp rice wine, 1 tsp corn flour.','30','/images/recipes/sweetsourpork.jpg');
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-15 15:00:56
