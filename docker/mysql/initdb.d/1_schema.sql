CREATE TABLE working_form(
    working_form_id INT NOT NULL,
    working_form_name VARCHAR(10) NOT NULL,
    PRIMARY KEY(working_form_id)
);

CREATE TABLE employee(
    employee_id INT NOT NULL AUTO_INCREMENT,
    last_name VARCHAR(10) NOT NULL,
    first_name VARCHAR(10) NOT NULL,
    roman_last_name VARCHAR(15) NOT NULL,
    roman_first_name VARCHAR(15) NOT NULL,
    birthday DATE NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    employment_date DATE NOT NULL,
    retirement_date DATE,
    working_form_id INT NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY(working_form_id) REFERENCES working_form(working_form_id) ON DELETE CASCADE
);

CREATE TABLE security(
    employee_id INT NOT NULL,
    password VARCHAR(100) NOT NULL,
    authority VARCHAR(10) NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);

CREATE TABLE vacation_request(
    request_id INT NOT NULL,
    employee_id INT NOT NULL,
    request_date DATE NOT NULL,
    PRIMARY KEY(request_id),
    FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE
);

CREATE TABLE shift_pattern(
    shift_pattern_id INT NOT NULL,
    shift_pattern_name VARCHAR(5) NOT NULL,
    start_time VARCHAR(10),
    end_time VARCHAR(10),
    PRIMARY KEY(shift_pattern_id)
);

CREATE TABLE shift(
    employee_id INT NOT NULL,
    date DATE NOT NULL,
    shift_pattern_id INT,
    confirmation VARCHAR(5) NOT NULL,
    PRIMARY KEY(employee_id, date),
    FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY(shift_pattern_id) REFERENCES shift_pattern(shift_pattern_id) ON DELETE CASCADE
);
