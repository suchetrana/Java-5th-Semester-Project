CREATE DATABASE Employee_Database;

Use Employee_Database;

CREATE TABLE EMPLOYEE_TABLE
(
    id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(10) NOT NULL UNIQUE,
    email_id VARCHAR(255) NOT NULL UNIQUE,
    employee_id INT NOT NULL,
    employee_date_of_birth DATE NOT NULL
);

INSERT INTO EMPLOYEE_TABLE
(id, first_name, last_name, gender, city, state, mobile_number, email_id, employee_id, employee_date_of_birth)
VALUES
    (1, 'Suchet', 'Sharma', 'Male', 'Pune', 'Maharashtra', '9876543210', 'suchet@example.com', 1001, '1998-07-15');

SELECT * FROM employee_table WHERE id = ?;
UPDATE employee_table SET employee_name = ? WHERE id = ?;
DELETE FROM employee_table WHERE id = ?;
SELECT * FROM employee_table WHERE email_id = ? AND employee_date_of_birth = ?;
