-- Person Database and values
DROP TABLE IF EXISTS Person;
 
CREATE TABLE Person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  person_id INTEGER(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  subscription_type VARCHAR(250) NOT NULL,
  age VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO Person (person_id,first_name, last_name,subscription_type,age) VALUES
	(1,'Aliko', 'Dangote','Annual Subscriber', 25),
	(2,'Bill', 'Gates', 'Guest',45),
	(3,'kill', 'Ramos','Monthly Subscriber', 33),
	(4,'Folrunsho', 'Alakija','Guest', 30),
	(5,'Sara', 'Tenc','Guest', 35);

-- Movie Database and values
DROP TABLE IF EXISTS Movie;
 
CREATE TABLE Movie (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  imdb_id INTEGER(250) NOT NULL,
  title VARCHAR(250) NOT NULL,
  story_line VARCHAR(2500) NOT NULL,
  release_date DATE NOT NULL,
  rated VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO Movie (imdb_id,title, story_line,release_date,rated) VALUES
  (1,'Aladdin', 'Aladdin is a lovable street urchin who meets Princess Jasmine, the beautiful daughter of the sultan of Agrabah. While visiting her exotic palace, Aladdin stumbles upon a magic oil lamp that unleashes a powerful, wisecracking, larger-than-life genie. As Aladdin and the genie start to become friends, they must soon embark on a dangerous mission to stop the evil sorcerer, Jafar, from overthrowing young Jasmine kingdom.',
  '2019-05-24', 'PG'),
  (2,'Mulan', 'When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.',
  '2020-09-04', 'PG-13'),
  (3,'Train to Busan ', 'Martial law is declared when a mysterious viral outbreak pushes Korea into a state of emergency. Those on an express train to Busan, a city that has successfully fended off the viral outbreak, must fight for their own survival…',
  '2016-07-20', '15+'),
  (4,'Black Panther', 'King TChalla returns home from America to the reclusive, technologically advanced African nation of Wakanda to serve as his countrys new leader. However, TChalla soon finds that he is challenged for the throne by factions within his own country as well as without. Using powers reserved to Wakandan kings, TChalla assumes the Black Panther mantel to join with girlfriend Nakia, the queen-mother, his princess-kid sister, members of the Dora Milaje (the Wakandan special forces) and an American secret agent, to prevent Wakanda from being dragged into a world war.',
  '2018-02-16', 'PG-13');
  
-- PersonMovie Database and values
DROP TABLE IF EXISTS person_movie;
 
CREATE TABLE person_movie (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  person_id INTEGER(250) NOT NULL,
  imdb_id INTEGER(250) NOT NULL
);

INSERT INTO person_movie (person_id,imdb_id) VALUES
(1,1),
(1,2),
(2,1),
(2,3),
(2,4),
(3,3),
(3,1),
(3,4),
(4,1),
(4,3);