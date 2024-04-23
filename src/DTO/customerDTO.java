package DTO;

import java.sql.Date;

public class customerDTO extends user {

    public customerDTO() {
        super();
    }

    public customerDTO(int id, String name, String phoneNumber, Date birthday) {
        super(id, name, phoneNumber, birthday);
    }

}
