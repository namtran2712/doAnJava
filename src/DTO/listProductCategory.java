package DTO;

import java.util.ArrayList;

public class listProductCategory {
    categoryDTO category = new categoryDTO();
    ArrayList<categoryDTO> categories = new ArrayList<>();

    public void addCategories(categoryDTO category) {
        categories.add(category);
    }

    public String getCategory(int id) {
        return categories.get(id-1).getCategoryName();
    }

    public void setCategory(int id, String name) {
        categories.get(id - 1).setCategoryName(name);
    }

    public void setCategories(ArrayList<categoryDTO> categories) {
        this.categories = categories;
    }
}
