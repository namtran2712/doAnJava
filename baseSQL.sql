USE doanbantrangsucjava

INSERT INTO authorizes (AUTHORIZE_NAME)
VALUES ("STAFF");

INSERT INTO authorizes (AUTHORIZE_NAME)
VALUES ("MANAGER");

INSERT INTO staff (FULLNAME, PHONE_NUMBER, SALARY, DATE_START)
VALUES ("ĐỖ NGỌC NHƯ LOAN", "0123456789",90000000,"2024-03-21");

INSERT INTO accounts (ID_AUTHORIZE, ID_STAFF, USERNAME, PASS_WORD, CREATE_AT)
VALUES (2, 1, "admin", "admin", "2024-03-21");

INSERT INTO material (MATERIAL_NAME)
VALUES ("vàng"),
("bạc"),
("bạch kim");

INSERT INTO category (CATEGORY_NAME)
VALUES ("dây chuyền"),
("vòng tay"),
("bông tai"),
("nhẫn"),
("kiềng");