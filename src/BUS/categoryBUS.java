package BUS;

import java.util.ArrayList;

import DAO.categoryDAO;
import DTO.categoryDTO;
@SuppressWarnings("unused")

public class categoryBUS {
    ArrayList<categoryDTO> listCategory;
    categoryDAO cateDao;

    public categoryBUS() {
        listCategory = new ArrayList<>();
        cateDao = new categoryDAO();
        loadCategory();
    }

    private void loadCategory() {
        listCategory = cateDao.selectAll();
    }

    public String getCategory(int index){
        return listCategory.get(index-1).getCategoryName();
    }

    public ArrayList <categoryDTO> getListCategory ()
    {
        return listCategory;
    }

}
