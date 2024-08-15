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
-- Table structure for table `recipe_step`
--

DROP TABLE IF EXISTS `recipe_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_step` (
  `step_id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int DEFAULT NULL,
  `step_number` int DEFAULT NULL,
  `step_description` text,
  PRIMARY KEY (`step_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `recipe_step_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_step`
--

LOCK TABLES `recipe_step` WRITE;
/*!40000 ALTER TABLE `recipe_step` DISABLE KEYS */;
INSERT INTO `recipe_step` VALUES (1,1,1,'Boil pasta in salted water until al dente.'),(2,1,2,'In a pan, heat olive oil and sauté minced garlic.'),(3,1,3,'Add chopped tomatoes and cook for 10 minutes.'),(4,1,4,'Drain pasta and add to the sauce.'),(5,1,5,'Toss with torn basil leaves and serve.'),(6,2,1,'Cut chicken into small cubes and marinate with soy sauce.'),(7,2,2,'Heat oil in a wok and stir-fry dried chilies until fragrant.'),(8,2,3,'Add chicken and stir-fry until nearly cooked.'),(9,2,4,'Add peanuts and continue to stir-fry.'),(10,2,5,'Add sauce and stir until chicken is fully cooked and sauce thickens.'),(11,3,1,'Wash lentils and cook with turmeric until soft.'),(12,3,2,'In a separate pan, heat ghee and add cumin seeds.'),(13,3,3,'Add chopped onions and garlic, sauté until golden.'),(14,3,4,'Add this tempering to the cooked lentils.'),(15,3,5,'Simmer for 5 minutes and serve hot.'),(16,4,1,'Cut avocados in half, remove pit, and scoop out the flesh.'),(17,4,2,'Mash avocados in a bowl.'),(18,4,3,'Finely chop onion and tomato, add to the mashed avocados.'),(19,4,4,'Squeeze lime juice over the mixture.'),(20,4,5,'Mix well, add salt to taste, and serve.'),(21,5,1,'Cook sushi rice and let it cool to room temperature.'),(22,5,2,'Slice salmon into thin rectangular pieces.'),(23,5,3,'Shape rice into small oval balls.'),(24,5,4,'Place a slice of salmon on top of each rice ball.'),(25,5,5,'Cut nori into thin strips and wrap around the middle of each nigiri.'),(26,7,1,'1)Marinate 200g pork shoulder with 1 tsp light soy sauce, 1 tsp rice wine, 1 tsp corn flour,\r 2)Mix and let it marinate for 25 mins.\r 3)\r Cut Â½ of both red and green bell peppers into cubes.\r 4)Cut 2 pcs of pineapple rings into small pieces.\r 5)Chop 1 clove of garlic.\r 6)Sift ,½ cup plain flour with ,¼ cup corn flour.\r 7)Add 1 tsp baking soda, pinch of salt, 1 tsp cooking oil, crack an egg and then Â½ cup water into the flour Mix batter until smooth.\r 8)Coat pork with batter. Deep-fry until golden brown.\r 9)\r For the sauce: Add 2 tbsp ketchup, 1 tsp plum sauce, Â½ tsp rice vinegar, Â½ tsp worcestershire, 1 tsp oyster sauce, 1 tsp corn flour, 1 tsp sugar, 2 tbsp water, and mix.\r 10)Heat the wok and stir fry garlic, then the bell peppers and pineapples.\r 11)When you can smell the peppers add in the fried pork pieces and then the sweet and sour sauce.\r 12)Toss and cook until the sauce thickens, it\'s done.'),(27,6,1,'Preheat the oven to 200C. Heat a large, oven-friendly skillet over medium heat.'),(28,6,2,'Add the olive oil, minced garlic, thyme and saute for 2 minutes.'),(29,6,3,'Add tomatoes and continue to cook for about 10 minutes, stirring occasionally. Add salt and pepper, to taste.'),(30,6,4,'Place the sliced veggies, in the skillet over the sauce, in a spiral pattern around the skillet until the entire pan is covered.'),(31,6,5,'Brush the veggies with the olive oil and sprinkle them with salt, pepper, and oregano.'),(32,6,6,'Bake in the oven about 50 minutes or until the veggies are soft and tender.'),(33,6,7,'For the sauce: 1 Tbsp olive oil 2 cloves garlic minced 8 tomatoes peeled and crushed 2 sprigs thyme salt and pepper');
/*!40000 ALTER TABLE `recipe_step` ENABLE KEYS */;
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
