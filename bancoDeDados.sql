-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           9.0.1 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para comanda_digital
CREATE DATABASE IF NOT EXISTS `comanda_digital` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `comanda_digital`;

-- Copiando dados para a tabela comanda_digital.comandas: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela comanda_digital.dishes
CREATE TABLE IF NOT EXISTS `dishes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Copiando dados para a tabela comanda_digital.itens_comanda: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela comanda_digital.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_date` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela comanda_digital.orders: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela comanda_digital.order_dishes
CREATE TABLE IF NOT EXISTS `order_dishes` (
  `order_id` bigint NOT NULL,
  `dish_id` bigint NOT NULL,
  KEY `FKey1hoe44aqgsnukr2x3x6nxg9` (`dish_id`),
  KEY `FK5p8h5knoot59tkjy4x9eh41dt` (`order_id`),
  CONSTRAINT `FK5p8h5knoot59tkjy4x9eh41dt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKey1hoe44aqgsnukr2x3x6nxg9` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela comanda_digital.order_dishes: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela comanda_digital.produto

-- Copiando dados para a tabela comanda_digital.produto: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela comanda_digital.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users VALUES (null,'admin@admin.com','administrador','admin','(11) 11223-4554','gerente');

-- Copiando dados para a tabela comanda_digital.users: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela comanda_digital.usuarios

-- Copiando dados para a tabela comanda_digital.usuarios: ~0 rows (aproximadamente)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
