package DTO;

public class authorize {
    private int idAuthorize;
    private String name;

    public authorize(int idAuthorize, String name) {
        this.idAuthorize = idAuthorize;
        this.name = name;
    }

    public int getIdAuthorize() {
        return idAuthorize;
    }

    public void setIdAuthorize(int idAuthorize) {
        this.idAuthorize = idAuthorize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
