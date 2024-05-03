USE doanbantrangsucjava
INSERT INTO authorizes (AUTHORIZE_NAME)
VALUES ("STAFF");
INSERT INTO authorizes (AUTHORIZE_NAME)
VALUES ("MANAGER");
INSERT INTO staff (FULLNAME, PHONE_NUMBER, SALARY, BIRTHDAY)
VALUES (
        "ĐỖ NGỌC NHƯ LOAN",
        "0123456789",
        90000000,
        "2000-03-21"
    );
INSERT INTO accounts (ID_AUTHORIZE, ID_STAFF, USERNAME, PASS_WORD)
VALUES (2, 1, "admin", "admin");
INSERT INTO material (MATERIAL_NAME)
VALUES("bạc"),
    ("vàng"),
    ("bạch kim");
INSERT INTO category (CATEGORY_NAME)
VALUES ("dây chuyền"),
    ("vòng tay"),
    ("bông tai"),
    ("nhẫn"),
    ("kiềng");