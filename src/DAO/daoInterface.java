package DAO;

import java.util.ArrayList;

public interface daoInterface<T> {
	public boolean insert (T t);
	
	public boolean delete (T t);
	
	public boolean update (T t);
	
	public ArrayList<T> selectAll ();
	
	public ArrayList<T> selectByCondition (String condition);
}
