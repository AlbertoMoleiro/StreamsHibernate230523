package com.softtek.streamshibernate230523;

import com.softtek.streamshibernate230523.service.CustomerService;
import com.softtek.streamshibernate230523.service.OrderService;
import com.softtek.streamshibernate230523.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.softtek.streamshibernate230523.model.Product;
import com.softtek.streamshibernate230523.model.Customer;
import com.softtek.streamshibernate230523.model.Order;
import java.util.Comparator;
import java.util.Optional;

@SpringBootApplication
public class StreamsHibernate230523Application implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {SpringApplication.run(StreamsHibernate230523Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//       Seleccionar todos los campos de la tabla Producto
        System.out.println("Select * from Product");
        productService.getAll().forEach(System.out::println);

//        Obtenerr una consulta con Productid, productname, supplierid, categoryId, UnistsinStock, UnitPrice
        System.out.println("Select ProductId, ProductName, SupplierId, CategoryId, UnitsInStock, UnitPrice from Product");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getSupplierId() + " " + product.getCategoryId() + " " + product.getUnitsInStock() + " " + product.getUnitPrice()).forEach(System.out::println);

        //Crear una consulta para obtener el IdOrden, IdCustomer, Fecha de la orden de la tabla de ordenes.
        System.out.println("Select OrderId, CustomerId, OrderDate from Orders");
        orderService.getAll().stream().map(order -> order.getOrderId() + " " + order.getCustomerId() + " " + order.getOrderDate()).forEach(System.out::println);

//        Crear una consulta para obtener el OrderId, EmployeeId, Fecha de la orden.
        System.out.println("Select OrderId, EmployeeId, OrderDate from Orders");
        orderService.getAll().stream().map(order -> order.getOrderId() + " " + order.getEmployeeId() + " " + order.getOrderDate()).forEach(System.out::println);

//        Obtener una consulta con Productid, productname y valor del inventario, valor inventrio (UnitsinStock * UnitPrice)
        System.out.println("Select ProductId, ProductName, UnitsInStock * UnitPrice from Product");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitsInStock() * product.getUnitPrice()).forEach(System.out::println);

//        Cuanto vale el punto de reorden
        System.out.println("Select ReorderLevel * UnitPrice from Product");
        productService.getAll().stream()
                .map(product -> Optional.ofNullable(product.getReorderLevel()).orElse((short) 0) *
                        Optional.ofNullable(product.getUnitPrice()).orElse(0.0F))
                .forEach(System.out::println);

//        Mostrar una consulta con Productid, productname y precio, el nombre del producto debe estar en mayuscula
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().toUpperCase() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener unicamente 10 caracteres
//        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
//        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().substring(0,10) + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtenre una consulta que muestre la longitud del nombre del producto
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().length() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener una consulta de la tabla de productos que muestre el nombre en minúscula
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().toLowerCase() + " " + product.getUnitPrice()).forEach(System.out::println);

//  Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener máximo 10 caracteres y se deben mostrar en mayúscula
//        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
//        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().substring(0,10).toUpperCase() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais Obtener los clientes cuyo pais sea Spain
        System.out.println("Select CustomerId, CompanyName, Country from Customers");
        customerService.getAll().stream().filter(customer -> customer.getCountry().equals("Spain")).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCountry()).forEach(System.out::println);

//        Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais, Obtener los clientes cuyo pais comience con la letra U
        System.out.println("Select CustomerId, CompanyName, Country from Customers");
        customerService.getAll().stream().filter(customer -> customer.getCountry().startsWith("U")).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCountry()).forEach(System.out::println);

//        Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais, Obtener los clientes cuyo pais comience con la letra U,S,A
        System.out.println("Select CustomerId, CompanyName, Country from Customers");
        customerService.getAll().stream().filter(customer -> customer.getCountry().startsWith("U") || customer.getCountry().startsWith("S") || customer.getCountry().startsWith("A")).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCountry()).forEach(System.out::println);

//        Obtener de la tabla de Productos las columnas productid, ProductName, UnitPrice cuyos precios esten entre 50 y 150
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        productService.getAll().stream().filter(product -> product.getUnitPrice() > 50 && product.getUnitPrice() < 150).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener de la tabla de Productos las columnas productid, ProductName, UnitPrice, UnitsInStock cuyas existencias esten entre 50 y 100
        System.out.println("Select ProductId, ProductName, UnitPrice, UnitsInStock from Product");
        productService.getAll().stream().filter(product -> product.getUnitsInStock() > 50 && product.getUnitsInStock() < 100).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice() + " " + product.getUnitsInStock()).forEach(System.out::println);

//        Obtener las columnas OrderId, CustomerId, employeeid de la tabla de ordenes cuyos empleados sean 1, 4, 9
        System.out.println("Select OrderId, CustomerId, EmployeeId from Orders");
        orderService.getAll().stream().filter(order -> order.getEmployeeId() == 1 || order.getEmployeeId() == 4 || order.getEmployeeId() == 9).map(order -> order.getOrderId() + " " + order.getCustomerId() + " " + order.getEmployeeId()).forEach(System.out::println);

//    ORDENAR EL RESULTADO DE LA QUERY POR ALGUNA COLUMNA Obtener la información de la tabla de Products, Ordenarlos por Nombre del Producto de forma ascendente
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        productService.getAll().stream().sorted(Comparator.comparing(Product::getProductName)).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener la información de la tabla de Products, Ordenarlos por Categoria de forma ascendente y por precio unitario de forma descendente
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        productService.getAll().stream().sorted(Comparator.comparing(Product::getCategoryId).thenComparing(Product::getUnitPrice).reversed()).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener la información de la tabla de Clientes, Customerid, CompanyName, city, country ordenar por pais, ciudad de forma ascendente
        System.out.println("Select CustomerId, CompanyName, City, Country from Customers");
        customerService.getAll().stream().sorted(Comparator.comparing(Customer::getCountry).thenComparing(Customer::getCity)).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCity() + " " + customer.getCountry()).forEach(System.out::println);

//Obtener los productos productid, productname, categoryid, supplierid ordenar por categoryid y supplier únicamente mostrar aquellos cuyo precio esté entre 25 y 200
        System.out.println("Select ProductId, ProductName, CategoryId, SupplierId from Product");
        productService.getAll().stream().filter(product -> product.getUnitPrice() > 25 && product.getUnitPrice() < 200).sorted(Comparator.comparing(Product::getCategoryId).thenComparing(Product::getSupplierId)).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getCategoryId() + " " + product.getSupplierId()).forEach(System.out::println);

//        Cuantos productos hay en la tabla de productos
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        System.out.println(productService.getAll().stream().count());

//        de la tabla de productos Sumar las cantidades en existencia
        System.out.println("Select ProductId, ProductName, UnitPrice, UnitsInStock from Product");
        System.out.println(productService.getAll().stream().mapToInt(Product::getUnitsInStock).sum());

//        Promedio de los precios de la tabla de productos
        System.out.println("Select ProductId, ProductName, UnitPrice from Product");
        System.out.println(productService.getAll().stream().mapToDouble(Product::getUnitPrice).average());

//        Obtener los datos de productos ordenados descendentemente por precio unitario de la categoría 1
        System.out.println("Select ProductId, ProductName, UnitPrice, CategoryId from Product");
        productService.getAll().stream().filter(product -> product.getCategoryId() == 1).sorted(Comparator.comparing(Product::getUnitPrice).reversed()).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice() + " " + product.getCategoryId()).forEach(System.out::println);

//        Obtener los datos de los clientes(Customers) ordenados descendentemente por nombre(CompanyName) que se encuentren en la ciudad(city) de barcelona, Lisboa
        System.out.println("Select CustomerId, CompanyName, City from Customers");
        customerService.getAll().stream().filter(customer -> customer.getCity().equals("Barcelona") || customer.getCity().equals("Lisboa")).sorted(Comparator.comparing(Customer::getCompanyName).reversed()).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCity()).forEach(System.out::println);

//        Obtener los datos de las ordenes, ordenados descendentemente por la fecha de la orden cuyo cliente(CustomerId) sea ALFKI
        System.out.println("Select OrderId, CustomerId, OrderDate from Orders");
        orderService.getAll().stream().filter(order -> order.getCustomerId().equals("ALFKI")).sorted(Comparator.comparing(Order::getOrderDate).reversed()).map(order -> order.getOrderId() + " " + order.getCustomerId() + " " + order.getOrderDate()).forEach(System.out::println);

    }


}
