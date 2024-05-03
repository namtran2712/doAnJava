package test;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import DAO.productDao;
import database.databaseUtil;

public class readdata {
    private static int idCategory;
    private static int idProduct;
    private static int idMaterial;
    private static String productName;
    private static int quantitySold;
    private static String image;
    private static int quantityRemain;
    private static float price;

    public static void main(String[] args) throws FileNotFoundException {
        FileReader fr = new FileReader("Database\\vongtay.txt");
        BufferedReader br = new BufferedReader(fr);
        @SuppressWarnings("unused")
        int i = 0;
        try {
            String line = "";
            String line2 = "";

            while ((line = br.readLine()) != null) {

                line2 = br.readLine();
                i++;
                String[] slipArray = line.split(",");
                idProduct = Integer.parseInt(slipArray[0]);
                idCategory = Integer.parseInt(slipArray[1]);
                idMaterial = Integer.parseInt(slipArray[2]);
                productName = slipArray[3];
                quantitySold = new Random().nextInt(1000);
                image = slipArray[5];
                quantityRemain = new Random().nextInt(1000);
                line2 = line2.replace(",", "");
                price = Float.parseFloat(line2);

                Map<Integer, Float> priceOfsize = new HashMap<Integer, Float>();

                Connection conc = databaseUtil.getConnection();
                try {
                    Statement stmt = conc.createStatement();
                    String sql = "insert into products (ID_PRODUCT,ID_CATEGORY,ID_MATERIAL,PRODUCT_NAME,QUANTITY_SOLD,LINK_IMAGE)"
                            + " values ("
                            + idProduct + ","
                            + idCategory + ","
                            + idMaterial + ",'"
                            + productName + "',"
                            + quantitySold + ",'"
                            + image + "'"
                            + ");";
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                productDao pdao = new productDao();
                for (int k = 40; k <= 44; k++) {
                    price = (float) (price + (price * 0.05));
                    pdao.insertParticularProduct(idProduct, k, price, quantityRemain);
                    priceOfsize.put(k, price);
                }

            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
