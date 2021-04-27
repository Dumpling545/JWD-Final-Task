-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hotel_system_database
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel_system_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_hotel_system_database` DEFAULT CHARACTER SET utf8mb4 ;
USE `test_hotel_system_database` ;

-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`users` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`users` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `password_hash` VARCHAR(100) NOT NULL,
  `avatar` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`archived_reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`archived_reservations` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`archived_reservations` (
  `id` INT(10) UNSIGNED NOT NULL,
  `user_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `email` VARCHAR(100) NOT NULL,
  `room_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `room_name` VARCHAR(100) NOT NULL,
  `hotel_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `hotel_name` VARCHAR(100) NOT NULL,
  `check_in_date` DATE NOT NULL,
  `check_out_date` DATE NOT NULL,
  `payment_token` VARCHAR(100) NOT NULL,
  `archivation_reason` INT(1) UNSIGNED NOT NULL,
  `payment_amount` DECIMAL(10,3) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_archived_reservations_users1` (`user_id` ASC),
  CONSTRAINT `fk_archived_reservations_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_hotel_system_database`.`users` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`hotels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`hotels` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`hotels` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `cached_address` VARCHAR(250) NULL DEFAULT NULL,
  `longtitude_address` DECIMAL(9,6) NOT NULL,
  `latitude_address` DECIMAL(8,6) NOT NULL,
  `bank_account` VARCHAR(80) NOT NULL,
  `icon` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 99
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`m2m_administrator_hotel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`m2m_administrator_hotel` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`m2m_administrator_hotel` (
  `user_id` INT(10) UNSIGNED NOT NULL,
  `hotel_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `hotel_id`),
  INDEX `fk_m2m_administrator_hotel_users_idx` (`user_id` ASC),
  INDEX `fk_m2m_administrator_hotel_hotels1_idx` (`hotel_id` ASC),
  CONSTRAINT `fk_m2m_administrator_hotel_hotels1`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `test_hotel_system_database`.`hotels` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_m2m_administrator_hotel_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_hotel_system_database`.`users` (`id`)
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`rooms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`rooms` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`rooms` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `hotel_id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `number_of_beds` TINYINT(2) UNSIGNED NOT NULL,
  `short_description` VARCHAR(600) NOT NULL,
  `cost` DECIMAL(10,3) UNSIGNED NOT NULL,
  `icon` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rooms_hotels1_idx` (`hotel_id` ASC),
  CONSTRAINT `fk_rooms_hotels1`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `test_hotel_system_database`.`hotels` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`reservations` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`reservations` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `room_id` INT(10) UNSIGNED NOT NULL,
  `check_in_date` DATE NOT NULL,
  `check_out_date` DATE NOT NULL,
  `payment_token` VARCHAR(100) NOT NULL,
  `status` INT(1) UNSIGNED NOT NULL,
  `payment_amount` DECIMAL(10,3) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservations_users1_idx` (`user_id` ASC),
  INDEX `fk_reservations_rooms1_idx` (`room_id` ASC),
  CONSTRAINT `fk_reservations_rooms1`
    FOREIGN KEY (`room_id`)
    REFERENCES `test_hotel_system_database`.`rooms` (`id`)
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservations_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_hotel_system_database`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`reviews` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`reviews` (
  `reservation_id` INT(10) UNSIGNED NOT NULL,
  `title` VARCHAR(210) NOT NULL,
  `text` VARCHAR(6000) NULL DEFAULT NULL,
  `rating` TINYINT(2) UNSIGNED NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`reservation_id`),
  CONSTRAINT `fk_reviews_reservations1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `test_hotel_system_database`.`archived_reservations` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_hotel_system_database`.`room_features`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_hotel_system_database`.`room_features` ;

CREATE TABLE IF NOT EXISTS `test_hotel_system_database`.`room_features` (
  `room_id` INT(10) UNSIGNED NOT NULL,
  `has_airconditioning` TINYINT(1) NOT NULL,
  `has_heating` TINYINT(1) NOT NULL,
  `has_balcony` TINYINT(1) NOT NULL,
  `has_tv` TINYINT(1) NOT NULL,
  `has_refrigerator` TINYINT(1) NOT NULL,
  `has_kitchen` TINYINT(1) NOT NULL,
  `has_hair_dryer` TINYINT(1) NOT NULL,
  `has_toilet` TINYINT(1) NOT NULL,
  `has_shower` TINYINT(1) NOT NULL,
  `has_washing_machine` TINYINT(1) NOT NULL,
  `description` VARCHAR(6000) NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  CONSTRAINT `fk_room_features_rooms1`
    FOREIGN KEY (`room_id`)
    REFERENCES `test_hotel_system_database`.`rooms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
USE `test_hotel_system_database`;

DELIMITER $$

USE `test_hotel_system_database`$$
DROP TRIGGER IF EXISTS `test_hotel_system_database`.`date_range_validator` $$
USE `test_hotel_system_database`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `test_hotel_system_database`.`date_range_validator`
BEFORE INSERT ON `test_hotel_system_database`.`reservations`
FOR EACH ROW
BEGIN
  IF EXISTS(SELECT id FROM reservations WHERE NEW.room_id = reservations.room_id AND ((NEW.check_out_date > reservations.check_in_date AND NEW.check_out_date <= reservations.check_out_date) OR (reservations.check_out_date > NEW.check_in_date AND reservations.check_out_date <= NEW.check_out_date))) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Date range has been already occupied by previous reservations';
  END IF;
END$$


DELIMITER ;

DELIMITER //

CREATE EVENT `archive_reservations_event` 
ON SCHEDULE EVERY 1 DAY 
DO BEGIN 
START TRANSACTION; 
	INSERT INTO archived_reservations SELECT reservations.id, reservations.user_id, users.email, reservations.room_id, rooms.name, hotels.id, hotels.name, reservations.check_in_date, reservations.check_out_date, reservations.payment_token, reservations.status, reservations.payment_amount FROM reservations INNER JOIN users ON reservations.user_id=users.id INNER JOIN rooms ON reservations.room_id=rooms.id INNER JOIN hotels ON rooms.hotel_id=hotels.id WHERE reservations.check_out_date <= NOW(); 
	DELETE FROM reservations WHERE reservations.check_out_date <= NOW(); 
COMMIT;
END;

//

DELIMITER ;

CREATE USER IF NOT EXISTS `hotel_system_user`@`localhost` IDENTIFIED BY 'very_strong_password_I_promise';
GRANT ALL PRIVILEGES ON `test_hotel_system_database`.* TO `hotel_system_user`@`localhost` IDENTIFIED BY 'very_strong_password_I_promise';


