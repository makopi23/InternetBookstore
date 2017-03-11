CREATE TABLE Books (
  id INTEGER NOT NULL IDENTITY PRIMARY KEY,
  title VARCHAR(255),
  synopsis VARCHAR(255)
);
CREATE INDEX books_title ON Books(title);


CREATE TABLE Customers (
  id INTEGER NOT NULL IDENTITY PRIMARY KEY,
  firstName VARCHAR(50),
  lastName VARCHAR(50),
  email VARCHAR(50),
  password VARCHAR(50)
);
CREATE INDEX customer_email ON Customers(email);
CREATE INDEX customer_lastname ON Customers(lastName);


CREATE TABLE CustomerAddresses (
  id INTEGER NOT NULL IDENTITY PRIMARY KEY,
  customerID INTEGER NOT NULL,
  address1 VARCHAR(50),
  address2 VARCHAR(50),
  town VARCHAR(50),
  zipCode VARCHAR(50),
  country VARCHAR(50),
  telephone VARCHAR(50)
);
alter table CustomerAddresses add constraint fk_addresses_customers foreign key (customerID) references Customers(id);


CREATE TABLE CustomerReviews (
  id INTEGER NOT NULL IDENTITY PRIMARY KEY,
  bookID INTEGER NOT NULL,
  customerID INTEGER NOT NULL,
  title VARCHAR(255),
  review VARCHAR(255),
  rating INTEGER
);
alter table CustomerReviews add constraint fk_reviews_books foreign key (bookID) references Books(id);
alter table CustomerReviews add constraint fk_reviews_customers foreign key (customerID) references Customers(id);



// Insert bookstore data:

INSERT INTO Books (id, title, synopsis)
  VALUES
(101, 'Harry Potter and the Philosophers Stone',
  'Harry discovers that he is a wizard.'
);

INSERT INTO Books (id, title, synopsis)
  VALUES
(102, 'Harry Potter and the Chamber of Secrets',
  'Harry continues his adventures and ...'
);

INSERT INTO Books (id, title, synopsis)
  VALUES
(103, 'Harry Potter and the Prisoner of Azkhaban',
  'Harry continues his adventures and meets Sirius Black.'
);

INSERT INTO Books (id, title, synopsis)
  VALUES
(104, 'Harry Potter and the Goblet of Fire',
  'Harry continues his adventures and ...'
);

INSERT INTO Books (id, title, synopsis)
  VALUES
(105, 'Harry Potter and the Order of the Phoenix',
  'Harry continues his adventures and ...'
);

INSERT INTO Books (id, title, synopsis)
  VALUES
(106, 'Harry Potter and the Half-Blood Prince',
  'Harry continues his adventures and ...'
);

INSERT INTO Books (id, title, synopsis)
  VALUES
(107, 'Harry Potter and the As-yet Unreleased Novel',
  'Harry continues his adventures and ...'
);