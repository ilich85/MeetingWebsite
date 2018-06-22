CREATE DATABASE `meeting_website`
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `users` (
  `id`       INT(11)     NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(25) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `photos` (
  `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
  `photo`   MEDIUMBLOB NOT NULL,
  `user_id` INT(11)    NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_photo_user_id` (`user_id`),
  CONSTRAINT `fk_photo_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users_info` (
  `id`            INT(11)     NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(45) NOT NULL,
  `age`           INT(2)      NOT NULL,
  `gender`        VARCHAR(5)  NOT NULL,
  `city`          VARCHAR(25) NOT NULL,
  `main_photo_id` BIGINT(20)           DEFAULT NULL,
  `user_id`       INT(11)     NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_info_user_idx` (`user_id`),
  KEY `fk_user_info_photo_idx` (`main_photo_id`),
  CONSTRAINT `fk_user_info_photo` FOREIGN KEY (`main_photo_id`) REFERENCES `photos` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_info_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `characteristics` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `about_me` VARCHAR(255)     DEFAULT NULL,
  `growth`   INT(11)          DEFAULT NULL,
  `weight`   INT(11)          DEFAULT NULL,
  `user_id`  INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_characteristic_user_id_idx` (`user_id`),
  CONSTRAINT `fk_characteristic_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `dialogs` (
  `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_user_id`  INT(11)    NOT NULL,
  `second_user_id` INT(11)    NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dialog_first_user_id_idx` (`first_user_id`),
  KEY `fk_dialog_first_user_id_idx1` (`second_user_id`),
  CONSTRAINT `fk_dialog_first_user_id` FOREIGN KEY (`first_user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dialog_second_user_id` FOREIGN KEY (`second_user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `messages` (
  `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `date`        VARCHAR(255) NOT NULL,
  `text`        VARCHAR(200) NOT NULL,
  `author_id`   INT(11)      NOT NULL,
  `author_name` VARCHAR(45)  NOT NULL,
  `dialog_id`   BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_message_dialog_id_idx` (`dialog_id`),
  KEY `fk_message_author_id_idx` (`author_id`),
  CONSTRAINT `fk_message_author_id` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_dialog_id` FOREIGN KEY (`dialog_id`) REFERENCES `dialogs` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `comments` (
  `id`       BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `date`     VARCHAR(25) NOT NULL,
  `text`     LONGTEXT    NOT NULL,
  `user_id`  INT(11)     NOT NULL,
  `photo_id` BIGINT(20)  NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_photo_id_idx` (`photo_id`),
  KEY `fk_comment_user_id_idx` (`user_id`),
  CONSTRAINT `fk_comment_photo_id` FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;