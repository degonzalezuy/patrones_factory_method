package factorymethod;

import factorymethod.dao.ProductoDAO;
import factorymethod.entity.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        //Creamos los nuevos productos a registrar
        Product productA = new Product(1L, "Producto A", 100);
        Product productB = new Product(2L, "Producto B", 100);

        //Creamos una instancia del DAO
        ProductoDAO productoDAO = new ProductoDAO();

        //Persistimos los productos
        productoDAO.saveProduct(productA);
        productoDAO.saveProduct(productB);

        //Consultamos nuevamente los productos
        List<Product> products = productoDAO.findAllProducts();
        System.out.println("Product size ==> " + products.size());
        for (Product product : products){
            System.out.println(product);
        }

    }
}