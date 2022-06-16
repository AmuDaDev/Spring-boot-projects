CREATE DATABASE  IF NOT EXISTS `hygi3n3_employee_directory`;
USE `hygi3n3_employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
	(1,'Amu','Nkombyani','amu@gmail.com'),
	(2,'James','Jameson','james@gmail.com'),
	(3,'Dan','Danilson','dan@gmail.com'),
	(4,'Peter','Peterson','peter@luv2code.com'),
	(5,'John','Johnson','john@luv2code.com');

