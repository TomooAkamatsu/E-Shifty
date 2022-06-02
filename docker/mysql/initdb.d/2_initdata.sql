-- working_form
INSERT INTO
    working_form(working_form_id, working_form_name)
VALUES
    (1, "正社員");

INSERT INTO
    working_form(working_form_id, working_form_name)
VALUES
    (2, "正社員(時短)");

INSERT INTO
    working_form(working_form_id, working_form_name)
VALUES
    (3, "パート");


-- employee
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (1,"岸田","文雄","Kishida","Fumio","1957/07/29",64,"man","090-1111-1111","kishida@hoge.com","2020/01/01",1);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (2,"菅","義偉","Suga","Yoshihide","1948/12/06",73,"man","090-2222-2222","suga@hoge.com","2020/02/01",2);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (3,"安倍","晋三","Abe","Shinzo","1954/09/21",67,"man","090-3333-3333","abe@hoge.com","2020/03/01",1);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (4,"野田","佳彦","Noda","Yoshihiko","1957/05/20",65,"man","090-4444-4444","noda@hoge.com","2020/04/01",1);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (5,"菅","直人","Kan","Naoto","1946/10/10",75,"man","090-5555-5555","kan@hoge.com","2020/05/01",3);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (6,"岸田","文雄","Kishida","Fumio","1957/07/29",64,"man","090-1111-1111","kishida@hoge.com","2020/01/01",1);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (7,"麻生","太郎","Aso","Taro","1940/09/20",81,"man","090-7777-7777","aso@hoge.com","2020/07/01",2);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (8,"福田","康夫","Fukuda","Yasuo","1936/07/16",85,"man","090-8888-8888","hukuda@hoge.com","2020/08/01",1);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (9,"小泉","純一郎","Koizumi","Junichiro","1942/01/08",80,"man","090-9999-9999","koizumi@hoge.com","2020/09/01",3);
INSERT INTO employee(employee_id,last_name,first_name,roman_last_name,roman_first_name,birthday,age,gender,phone_number,email,employment_date,working_form_id) VALUES (10,"森","善朗","Mori","Yoshiro","1937/07/14",84,"man","090-0000-0000","mori@hoge.com","2020/10/01",1);