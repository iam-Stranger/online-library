-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: online_library
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL COMMENT 'Имя или псевдоним автора.',
  `deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='Таблица со списков авторов книг.\n\n\nТаблица имеет связь один ко многим с таблицей книги (book) каждый автор (author) может соответствовать многим книгам (book)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Федор Достоевский',0),(2,'Лев Толстой',0),(3,'Михаил Булгаков',0),(4,'Александр Пушкин',0),(5,'Николай Гоголь',0),(6,'Антон Чехов',0),(7,'Эрих Мария Ремарк',0),(8,'Эрнест Хеменгуэй',0),(9,'Уильям Шекспир',0),(10,'Илья Ильф',0),(11,'Евгений Петров',0),(12,'Рэй Бредберри',0),(14,'Джордж Оруэлл',0),(15,'Оскар Уайлд',0),(16,'Антуан де Сент Экзюпери',0),(17,'Иван Крылов',0),(18,'Сергей Михалков',0),(19,'Агата Кристи',0),(20,'Артур Конан Дойл',0),(21,'Филип К. Дик',0),(22,'Герберт Уэллс ',0),(23,'Сергей Есенин',0);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT 'Название произведения',
  `publish_year` smallint(4) NOT NULL COMMENT 'Год выхода (издания) книги',
  `total_amount` int(11) NOT NULL COMMENT 'кол-во доступных книг В НАЛИЧИИ',
  `real_amount` int(11) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'атрибут boolean книга удалена или нет (по умолчанию - false)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='Таблица книг, в ней хранятся доступные для заказа книги при условии их не нулевого кол-ва (total_amount) и того что они не удалены (deleted)\n\nТаблица имеет 2 связи многие к одному: с таблицей author (автор) и с таблицей genre (жанр).\n\nТаблица имеет связь один ко многим с таблицей book_order т.е. одна книга (book) может быть в нескольких заказах(order)\n\nТаким образом в таблице хранится полная информация о некой книге, которая может быть в определенном количестве';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Двенадцать стульев',1928,3,0,0),(2,'Марсианские хроники',1950,2,1,0),(3,'Война и мир',2015,1,0,0),(4,'Идиот',2016,4,4,0),(5,'100 лет одиночества',1967,2,1,0),(6,'Сказка о царе Салтане',2017,5,4,0),(7,'Золотой телёнок',1931,2,0,0),(16,'Необыкновенные истории из жизни города Колоколамска',1928,1,1,0),(17,'1984',2016,10,10,0),(18,'Мастер и Маргарита',2011,5,5,1),(19,'Три товарища',2015,4,4,0),(20,'Портрет Дориана Грея',2017,3,3,0),(21,'Вино из одуванчиков',2001,4,4,0),(22,'Маленький принц',1998,10,10,0),(23,'Анна Каренина',2002,6,6,0),(24,'Преступление и наказание',1978,3,2,0),(25,'Сборник басен русских писателей',1997,3,2,0),(26,'Басни Крылова',2001,1,1,0),(27,'Трагедия в Марсдон-Мэнор',1962,1,1,0),(28,'Собака Баскервилей',2005,2,2,0),(29,'Мечтают ли андроиды об электроовцах?',1968,1,1,0),(30,'451 градус по фаренгейту',2007,1,1,0),(31,'Машина времени',2006,3,3,0),(32,'Сборник лучших стихов',1988,2,2,0),(33,'Евегний Онегин',2008,5,5,0),(34,'Руслан и Людмила',2000,2,2,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_authors`
--

DROP TABLE IF EXISTS `book_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_authors` (
  `book_id` int(10) unsigned NOT NULL,
  `author_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `fk_book_authors_author1_idx` (`author_id`),
  KEY `fk_book_authors_book1_idx` (`book_id`),
  CONSTRAINT `fk_book_authors_author1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_authors_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_authors`
--

LOCK TABLES `book_authors` WRITE;
/*!40000 ALTER TABLE `book_authors` DISABLE KEYS */;
INSERT INTO `book_authors` VALUES (4,1),(24,1),(3,2),(23,2),(25,2),(18,3),(6,4),(33,4),(34,4),(5,7),(19,7),(1,10),(7,10),(16,10),(1,11),(7,11),(16,11),(2,12),(21,12),(30,12),(17,14),(20,15),(22,16),(25,17),(26,17),(25,18),(27,19),(28,20),(29,21),(31,22),(32,23);
/*!40000 ALTER TABLE `book_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_genres`
--

DROP TABLE IF EXISTS `book_genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_genres` (
  `book_id` int(10) unsigned NOT NULL,
  `genre_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`book_id`,`genre_id`),
  KEY `fk_book_genres_genre1_idx` (`genre_id`),
  KEY `fk_book_genres_book1_idx` (`book_id`),
  CONSTRAINT `fk_book_genres_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_genres_genre1` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_genres`
--

LOCK TABLES `book_genres` WRITE;
/*!40000 ALTER TABLE `book_genres` DISABLE KEYS */;
INSERT INTO `book_genres` VALUES (25,1),(26,1),(24,3),(27,3),(28,3),(1,4),(7,4),(6,6),(32,6),(33,6),(34,6),(2,8),(29,8),(30,8),(31,8),(16,9),(31,9),(1,10),(21,10),(22,10),(28,10),(30,10),(33,11),(34,11),(3,13),(4,13),(5,13),(7,13),(17,13),(18,13),(19,13),(20,13),(23,13),(24,13),(27,13),(33,13),(20,14),(6,22),(22,22);
/*!40000 ALTER TABLE `book_genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_orders`
--

DROP TABLE IF EXISTS `book_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT 'Внешний ключ на таблицу user (пользователь)\n',
  `book_id` int(10) unsigned NOT NULL COMMENT 'Внешнйи ключ на таблицу book (книга)',
  `date_from` date NOT NULL COMMENT 'Дата с какого числа выдана книга.',
  `date_to` date DEFAULT NULL COMMENT 'Дата по какое число выдана книга.',
  `date_return` date DEFAULT NULL COMMENT 'Фактическая дата возврата',
  `order_type_id` tinyint(1) NOT NULL COMMENT '0 - читальный зал\n1 - абонемент',
  `status_id` int(10) unsigned NOT NULL COMMENT 'Внешний ключ на таблицу status (статус книги: заказана, отменено, выдано, возвращено)',
  PRIMARY KEY (`id`),
  KEY `fk_book_order_user1_idx` (`user_id`),
  KEY `fk_book_order_book1_idx` (`book_id`),
  KEY `fk_book_order_status1_idx` (`status_id`),
  CONSTRAINT `fk_book_order_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_order_status1` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='Таблица закаов.\nСпиок заказанных книг пользоваталями.\nЕсть дата выдачи и требуемая дата возвращения книги.\nСтатус показывает текущее состояние заказа.\n\nТаблица имеет связь многие к одному ( многим заказам (book_order) может соответствовать один статус (status)\nТак же табица имеет 2 связи многие к одному с таблицами user и book\n\nТаким образом реализована связь многие ко многим между таблицами user и book - это позволяет сохранять информацию какие книги были взяты какими пользователями ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_orders`
--

LOCK TABLES `book_orders` WRITE;
/*!40000 ALTER TABLE `book_orders` DISABLE KEYS */;
INSERT INTO `book_orders` VALUES (1,5,6,'2017-06-05','2017-07-05',NULL,1,2),(2,6,2,'2017-06-15','2017-06-15','2017-06-15',0,4),(3,7,6,'2017-06-21','2017-07-21','2017-07-07',1,4),(4,6,5,'2017-07-19','2017-08-19','2017-08-21',1,4),(5,10,4,'2017-08-19','2017-09-05','2017-09-05',1,4),(6,7,3,'2017-09-09','2017-09-09','2017-09-10',0,4),(7,6,1,'2017-10-01','2018-11-01',NULL,1,2),(8,7,1,'2017-10-15','2018-12-15',NULL,1,2),(9,5,3,'2017-10-16',NULL,NULL,0,3),(10,10,3,'2017-10-30',NULL,NULL,1,1),(11,1,25,'2018-02-08',NULL,NULL,0,1),(14,1,5,'2018-02-09',NULL,NULL,1,3),(15,1,16,'2018-02-09',NULL,NULL,0,3),(16,2,5,'2018-02-09','2018-02-24',NULL,1,2),(17,2,16,'2018-02-09',NULL,NULL,1,3),(18,2,20,'2018-02-09','2018-02-09','2018-02-09',0,4),(19,16,1,'2018-02-09','2018-02-10',NULL,1,2),(20,16,18,'2018-02-09',NULL,NULL,1,3),(21,1,7,'2018-02-10','2018-02-25',NULL,1,2),(22,3,7,'2018-02-11','2018-02-26',NULL,1,2),(23,15,24,'2018-02-12','2018-02-27',NULL,1,2),(24,15,2,'2018-02-12',NULL,NULL,1,1),(25,15,6,'2018-02-12',NULL,NULL,1,3),(26,2,16,'2018-02-12',NULL,NULL,1,3),(27,2,27,'2018-02-12',NULL,NULL,0,3);
/*!40000 ALTER TABLE `book_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(48) NOT NULL COMMENT 'Жанр произведения.',
  `deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='Таблица со списком жанров книг.\n\nТаблица имеет связь один ко многим с таблицей книги (book) каждый жанр (genre) может соответствовать многим книгам (book)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Басня',0),(3,'Детектив',0),(4,'Комедия',0),(5,'Мелодрамма',0),(6,'Поэзия',0),(8,'Научная фантастика',0),(9,'Новелла',0),(10,'Повесть',0),(11,'Поэма',0),(12,'Рассказ',0),(13,'Роман',0),(14,'Трагедия',0),(15,'Триллер',0),(16,'Фентези',1),(17,'Фольклор',1),(18,'Приключения',0),(19,'Фантастика',0),(22,'Сказка',0);
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL COMMENT 'Статус заказа книги. Статус описывает весь цикл "движения" книги, варианты:\n- orederd - заказано\n- takeaway - одобрено, книга на вынос\n- reading_room - одобрено, чтение в зале\n- returned - возвращено (заказ в архиве)\nВозможно, стоит еще подумать над названиями статусов.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Таблица со списком возможных статусов заказа, заказ проходит жизненный цикл от "заказано" до "возвращено"\n\nТаблица имеет связь один ко многим с таблицей book_order (каждый статус может соответствовать многим заказам (order)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'ordered'),(2,'issued'),(3,'сanceled'),(4,'returned');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL COMMENT 'Тип роли, может принимать значения :\n- user\n- librarian\n- administrator\nПри необходимости список может быть легко расширен.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Таблица ролей пользователей. Поьзователь может быть юзером библиотекарем или администратором. При необходимости списко расширяется.\n\nТаблица имеет связь один ко многим стаблицей пользователи (user) таким образом одна роль (role) может быть у нескольких пользователей (user)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'reader'),(2,'librarian'),(3,'administrator');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL COMMENT 'login в связке с password идентифицирует пользователя, поле Unique',
  `password` varchar(32) NOT NULL COMMENT 'password в связке с login  идентифицирует пользователя',
  `email` varchar(50) NOT NULL COMMENT 'email поле -  для отправки информации, например, о задолженности или для восстановления пароля',
  `firstname` varchar(45) NOT NULL COMMENT 'Имя пользователя',
  `lastname` varchar(45) NOT NULL COMMENT 'Фамилия полльзователя',
  `role_id` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'внешний ключ на таблицу с РОЛЯМИ (role)  - user, librarian, admin',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Булевое значение активен пользователь или нет, дефолтовое значение TRUE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='Таблица пользователей.\nТут будут хранится пользователи всех типов.\nЛогин, пароль, емэйл, имя фамилия, роль.\n\nТаблица имеет свзять один ко многим с таблицей ook_order одному пользователю (user) могут соответствовать много заказов (order)\n\nТаким образом таблица представляет собранные данные о конкретном пользователе, при чем пользователь должен быть уникальным.\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','be68f5702b9b666a69697e78f6dbef23','neverminsk@gmail.com','Иван','Иванов',3,0),(2,'root','f35c169721e062f1eecd5e26499eae12','arkady_dobkin@epam.com','Аркадий','Добкин',3,0),(3,'fedor','9f6e7d84e3aa6ece1395a2a5030b400b','fedor@mail.ru','Фёдор','Емельяненко',2,0),(4,'kate','3f850082518b3524052593c5af39b48e','kate.teplouhova@gmail.com','Екатерина','Теплоухова',1,1),(5,'mordas','56d5c2aa2999bf3c17f5ce9080452c2e','sergei.mordas@gmail.com','Сергей','Мордас',1,0),(6,'zhukel','b2a02df9abaa57c8cd4f41cca9e975b4','gleb.zhukel@gmail.com','Глеб','Жукель',1,0),(7,'byhovsky','fd0448ae6739f475000b51f9e83cbd81','denisbyh7@gmail.com','Денис','Быховский',1,0),(8,'tyson','b3a5c3d9d26339b75eed641d674d3c01','mike@gmail.com','Майк','Тайсон',2,1),(9,'putin','60921422600af4275b8262f78c61e1a1','vvp@mail.ru','Владимир','Путин',2,1),(10,'k.igor','a41c3aa8ff6f36c6899d3a7151fb919a','kvigor83@rambler.ru','Игорь','Костюченко',1,0),(15,'alex','821647f81e0c352d6ef602e3fb8079e9','neverminsk@mail.ru','Alex','Tailor',1,0),(16,'albert','2ebbe5cd8832d2894fe3cc9738354560','admin@fiz.com','Альберт','Энштейн',2,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-13 15:11:03
