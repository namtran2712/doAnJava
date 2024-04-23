package BUS;

import java.util.ArrayList;

import DTO.materialDTO;
import DAO.materialDAO;

public class materialBUS {
    ArrayList<materialDTO> listMaterial;
    materialDAO mdao;

    public materialBUS() {
        listMaterial =new ArrayList<>();
        mdao =new materialDAO();
        loadMaterial ();
    }

    private void loadMaterial ()
    {
        listMaterial =mdao.selectAll();
    }

    public String getMaterial (int index)
    {
        return listMaterial.get(index-1).getMaterial();
    }

    public ArrayList <materialDTO> getListMaterial ()
    {
        return listMaterial;
    }

}
