package BUS;

import java.util.ArrayList;

import DTO.materialDTO;
import DAO.materialDAO;

public class materialBUS {
    ArrayList<materialDTO> listMaterial;
    materialDAO mdao;

    public materialBUS() throws Exception {
        listMaterial =new ArrayList<>();
        mdao =new materialDAO();
        loadMaterial ();
    }

    private void loadMaterial ()
    {
        listMaterial =mdao.selectAll();
    }

    public String getMaterial (int id)
    {
        return listMaterial.get(id).getMaterial();
    }

    public ArrayList <materialDTO> getListMaterial ()
    {
        return listMaterial;
    }

}
