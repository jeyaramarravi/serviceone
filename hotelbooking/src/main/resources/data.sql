DROP TABLE IF EXISTS hotelbooking;

CREATE TABLE room (
  id VARCHAR(250) PRIMARY KEY,
  type VARCHAR(250) NOT NULL,
  rent NUMBER(10, 2) NOT NULL,
  numberofbeds NUMBER(5) NOT NULL,
  vacant BIT
);
  
CREATE TABLE booking_history (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age NUMBER(5) NOT NULL,
  address VARCHAR(250) NOT NULL,
  aadharnumber VARCHAR(250) NOT NULL,
  roomnumber VARCHAR(250) NOT NULL,
  amount NUMBER(10, 2) NOT NULL,
  checkintime TIMESTAMP,
  checkouttime TIMESTAMP
);

INSERT INTO room (id, type, rent, numberofbeds, vacant) VALUES
  ('S101', 'DELUX', 1000, 2, 1),
  ('S102', 'Super DELUX', 2000, 3, 1),
  ('S103', 'DELUX', 1000, 2, 1),
  ('S104', 'DELUX', 1000, 2, 1),
  ('S105', 'DELUX', 1000, 2, 1),
  ('S106', 'Altra DELUX', 6000, 4, 1);
