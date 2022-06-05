SELECT
    employee_id,
    last_name,
    first_name,
    roman_last_name,
    roman_first_name,
    birthday,
    age,
    gender,
    phone_number,
    email,
    employment_date,
    working_form_name
FROM
    employee
    JOIN working_form ON employee.working_form_id = working_form.working_form_id
WHERE
    employee.retirement_date is null
ORDER BY
    employee.employee_id ASC;