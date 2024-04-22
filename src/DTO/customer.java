package DTO;

import java.sql.Date;

public class customer extends user{
    

    public customer()
    {
        super();
    }
    public customer(int id, String name, String phoneNumber, Date birthday) {
		super(id,name,phoneNumber,birthday);
	}



    
}
