package BUS;

import java.util.ArrayList;

import DAO.categoryDAO;
import DTO.categoryDTO;
@SuppressWarnings("unused")

public class categoryBUS {
    ArrayList<categoryDTO> listCategory;
    categoryDAO cateDao;

    public categoryBUS() throws Exception {
        listCategory = new ArrayList<>();
        cateDao = new categoryDAO();
        loadCategory();
    }

    private void loadCategory() {
        listCategory = cateDao.selectAll();
    }

    public String getCategory(int id){
        return listCategory.get(id).getCategoryName();
    }

    public ArrayList <categoryDTO> getListCategory ()
    {
        return listCategory;
    }

}
