DROP TABLE IF EXISTS working_form, employee, security, vacation_request, shift_pattern, shift;

CREATE TABLE working_form(
    working_form_id INT,
    working_form_name VARCHAR(10)
);

CREATE TABLE employee(
    employee_id INT,
    last_name VARCHAR(10),
    first_name VARCHAR(10),
    roman_last_name VARCHAR(15),
    roman_first_name VARCHAR(15),
    birthday DATE,
    age INT,
    gender VARCHAR(10),
    phone_number VARCHAR(20),
    email VARCHAR(30),
    employment_date DATE,
    retirement_date DATE,
    working_form_id INT
);

CREATE TABLE security(
    employee_id INT,
    password VARCHAR(100),
    authority VARCHAR(10)
);

CREATE TABLE vacation_request(
    request_id INT,
    employee_id INT,
    request_date DATE
);

CREATE TABLE shift_pattern(
    shift_pattern_id INT,
    shift_pattern_name VARCHAR(5),
    start_time VARCHAR(10),
    end_time VARCHAR(10)
);

CREATE TABLE shift(
    employee_id INT,
    date DATE,
    shift_pattern_id INT,
    confirmation VARCHAR(5)
);
