-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPRbaza27
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `Cars`
--

DROP TABLE IF EXISTS `Cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cars` (
  `Cid` int NOT NULL AUTO_INCREMENT,
  `Make` varchar(45) DEFAULT NULL,
  `Model` varchar(45) DEFAULT NULL,
  `CarYear` int DEFAULT NULL,
  `Price` int DEFAULT NULL,
  `Available` bit(1) DEFAULT NULL,
  PRIMARY KEY (`Cid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cars`
--

LOCK TABLES `Cars` WRITE;
/*!40000 ALTER TABLE `Cars` DISABLE KEYS */;
INSERT INTO `Cars` VALUES (1,'Audi','A4',2007,150,_binary '\0'),(7,'Audi','A4',2008,150,_binary ''),(9,'AUDI','A5',2005,140,_binary ''),(12,'BMW','X5',2022,225000,_binary '');
/*!40000 ALTER TABLE `Cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customers`
--

DROP TABLE IF EXISTS `Customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customers` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(45) DEFAULT NULL,
  `DrivLicenceNumber` varchar(25) DEFAULT NULL,
  `Adress` varchar(45) DEFAULT NULL,
  `Mail` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `CarID` int unsigned NOT NULL DEFAULT '0',
  `Admin` tinyint DEFAULT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`CustomerID`),
  KEY `fk_Customers_CarID_idx` (`CarID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customers`
--

LOCK TABLES `Customers` WRITE;
/*!40000 ALTER TABLE `Customers` DISABLE KEYS */;
INSERT INTO `Customers` VALUES (1,'Bakir','65316','Patriot','ahbja','jasbsj',1,1,'bakir'),(27,'kerim','kerim','kerim','kerim','kerim',0,0,'kerim'),(28,'bakir','bakir','bakir','bakir','bakir',0,0,'bakir'),(29,'bakir','bakir','bakir','bakir','bakir',0,0,'bakir'),(30,'a','a','a','a','a',0,0,'a'),(31,'haha','haha','haha','haha','haha',0,0,'haha'),(32,'AH','A','A','NA','A',0,0,'A'),(33,'hah','hah','ahha','hah','haha',0,0,'hah'),(34,'haha','haha','haah','hhaha','haha',0,0,'haha'),(35,'aaaaaaaaaaaaaa','aaaaaaaaaaaaaa','aaaaaaaaaaaaaa','aaaaaaaaaaaaaa','aaaaaaaaaaaaaa',0,0,'aaaaaaaaaaaaaa'),(36,'kakaka','kakaka','kakaka','kakaka','kakaka',0,0,'kakaka'),(37,'proba','proba','proba','proba','proba',0,0,'proba'),(38,'hahah','hah','haha','hahah','hahh',0,0,'hahaha'),(39,'hahaaahha','a','a','a','a',0,0,'a'),(40,'aman','aman','aman','aman','aman',0,0,'aman'),(41,'ahahah','saias','iubsa','ahbabshxbs','habi',0,0,'asbiskn');
/*!40000 ALTER TABLE `Customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `SaleDate` date DEFAULT NULL,
  `TotalPrice` int DEFAULT NULL,
  `CustomerID` int DEFAULT NULL,
  `CarID` int DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `fk_Orders_CarID_idx` (`CarID`),
  KEY `fk_Orders_CustomerID_idx` (`CustomerID`),
  CONSTRAINT `fk_Orders_CarID` FOREIGN KEY (`CarID`) REFERENCES `Cars` (`Cid`),
  CONSTRAINT `fk_Orders_CustomerID` FOREIGN KEY (`CustomerID`) REFERENCES `Customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (1,'2022-08-20',25,1,1),(4,'2023-02-07',150,27,7),(5,'2023-02-07',140,30,9),(6,'2023-02-15',140,30,9),(7,'2023-02-22',140,30,9),(8,'2023-02-15',140,30,9),(9,'2023-01-31',140,30,9),(10,'2023-02-07',140,30,9),(11,'2023-02-18',150,37,7),(12,'2023-02-20',140,37,9);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-12 22:45:44
