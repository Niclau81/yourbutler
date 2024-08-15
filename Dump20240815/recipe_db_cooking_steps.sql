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
-- Table structure for table `cooking_steps`
--

DROP TABLE IF EXISTS `cooking_steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooking_steps` (
  `step_id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int DEFAULT NULL,
  `step_number` int DEFAULT NULL,
  `step_description` text,
  PRIMARY KEY (`step_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `cooking_steps_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooking_steps`
--

LOCK TABLES `cooking_steps` WRITE;
/*!40000 ALTER TABLE `cooking_steps` DISABLE KEYS */;
INSERT INTO `cooking_steps` VALUES (1,1,1,'Boil water and cook spaghetti according to package instructions.'),(2,1,2,'In a pan, heat olive oil and sauté garlic until fragrant.'),(3,1,3,'Add chopped tomatoes and cook for 10 minutes.'),(4,1,4,'Add cooked pasta to the sauce, toss well, and garnish with basil.'),(5,2,1,'Cut chicken into bite-sized pieces and marinate with soy sauce and cornstarch.'),(6,2,2,'Heat oil in a wok and stir-fry chicken until golden.'),(7,2,3,'Add peanuts, vegetables, and Kung Pao sauce. Stir-fry for 2-3 minutes.'),(8,2,4,'Serve hot with steamed rice.'),(9,3,1,'Rinse lentils and cook them with water, turmeric, and salt until soft.'),(10,3,2,'In a separate pan, heat ghee and add cumin seeds, garlic, and onions.'),(11,3,3,'Add tomatoes and cook until soft. Add spices and cook for 2 minutes.'),(12,3,4,'Add the tadka to the cooked lentils and simmer for 5 minutes.'),(13,4,1,'Cut avocados in half, remove the pit, and scoop out the flesh.'),(14,4,2,'Mash the avocado flesh in a bowl.'),(15,4,3,'Add diced onion, tomato, cilantro, lime juice, and salt. Mix well.'),(16,4,4,'Taste and adjust seasoning as needed.'),(17,5,1,'Cook sushi rice and let it cool to room temperature.'),(18,5,2,'Slice salmon into thin strips.'),(19,5,3,'Shape rice into small oval balls and place a slice of salmon on top.'),(20,5,4,'Serve with soy sauce, wasabi, and pickled ginger on the side.'),(21,6,1,'Preheat the oven to 375°F (190°C).'),(22,6,2,'Slice all vegetables thinly.'),(23,6,3,'Sauté each vegetable separately in olive oil until lightly browned.'),(24,6,4,'Layer the vegetables in a casserole dish.'),(25,6,5,'Bake for 45 minutes to 1 hour until vegetables are tender.');
/*!40000 ALTER TABLE `cooking_steps` ENABLE KEYS */;
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
