CREATE TABLE enhancement (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             description TEXT,
                             extra_damage INT,
                             droppable BOOLEAN,
                             level_requirement INT,
                             weapon_id INT,
                             FOREIGN KEY (weapon_id) REFERENCES weapons(id)
);


CREATE TABLE explorer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nickname VARCHAR(255) NOT NULL,
                          level INT NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          experience INT NOT NULL
);

CREATE TABLE explorer_mission (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  explorer_id INT,
                                  mission_id INT,
                                  assigned_date DATETIME,
                                  finished_date DATETIME,
                                  FOREIGN KEY (explorer_id) REFERENCES explorer(id),
                                  FOREIGN KEY (mission_id) REFERENCES missions(id)
);

CREATE TABLE missions (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          status VARCHAR(255),
                          type VARCHAR(255),
                          initial_date DATE,
                          finish_date DATE
);

CREATE TABLE weapons (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         base_damage INT NOT NULL,
                         description TEXT,
                         forged_date DATETIME,
                         droppable BOOLEAN,
                         explorer_id INT,
                         FOREIGN KEY (explorer_id) REFERENCES explorer(id)
);
