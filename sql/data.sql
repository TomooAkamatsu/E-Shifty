DROP TABLE IF EXISTS vacation_request;

CREATE TABLE vacation_request(
    request_id INT NOT NULL,
    employee_id INT NOT NULL,
    request_date DATE NOT NULL,
    PRIMARY KEY(request_id)
);
