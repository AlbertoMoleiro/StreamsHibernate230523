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
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        System.out.println("SELECT * FROM products;");
        productService.getAll().forEach(System.out::println);

//        Obtenerr una consulta con Productid, productname, supplierid, categoryId, UnistsinStock, UnitPrice
        System.out.println("SELECT product_id, product_name, supplier_id, category_id, units_in_stock, unit_price FROM products;");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getSupplierId() + " " + product.getCategoryId() + " " + product.getUnitsInStock() + " " + product.getUnitPrice()).forEach(System.out::println);

        //Crear una consulta para obtener el IdOrden, IdCustomer, Fecha de la orden de la tabla de ordenes.
        System.out.println("SELECT order_id, customer_id, order_date FROM orders;");
        orderService.getAll().stream().map(order -> order.getOrderId() + " " + order.getCustomerId() + " " + order.getOrderDate()).forEach(System.out::println);

//        Crear una consulta para obtener el OrderId, EmployeeId, Fecha de la orden.
        System.out.println("SELECT order_id, employee_id, order_date FROM orders;");
        orderService.getAll().stream().map(order -> order.getOrderId() + " " + order.getEmployeeId() + " " + order.getOrderDate()).forEach(System.out::println);

//        Obtener una consulta con Productid, productname y valor del inventario, valor inventrio (UnitsinStock * UnitPrice)
        System.out.println("SELECT product_id, product_name, units_in_stock * unit_price AS valor_inventario FROM products;");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitsInStock() * product.getUnitPrice()).forEach(System.out::println);

//        Cuanto vale el punto de reorden
        System.out.println("SELECT product_id, product_name, reorder_level * unit_price AS punto_reorden FROM products;t");
        productService.getAll().stream()
                .map(product -> Optional.ofNullable(product.getReorderLevel()).orElse((short) 0) *
                        Optional.ofNullable(product.getUnitPrice()).orElse(0.0F))
                .forEach(System.out::println);

//        Mostrar una consulta con Productid, productname y precio, el nombre del producto debe estar en mayuscula
        System.out.println("SELECT product_id, UPPER(product_name) AS product_name, unit_price FROM products;");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().toUpperCase() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener unicamente 10 caracteres
        System.out.println("SELECT product_id, LEFT(product_name, 10) AS product_name, unit_price FROM products;");
        Function<String,String> substring = s -> s.length()>10? s.substring(0,10):s;
        productService.getAll().stream().map(product -> product.getProductId() + " " + substring.apply(product.getProductName()) + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtenre una consulta que muestre la longitud del nombre del producto
        System.out.println("SELECT product_id, LOWER(product_name) AS product_name, unit_price FROM products;");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().length() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener una consulta de la tabla de productos que muestre el nombre en minúscula
        System.out.println("SELECT product_id, LOWER(product_name) AS product_name, unit_price FROM products;");
        productService.getAll().stream().map(product -> product.getProductId() + " " + product.getProductName().toLowerCase() + " " + product.getUnitPrice()).forEach(System.out::println);

//  Mostrar una consulta con Productid, productname y precio, el nombre del producto debe contener máximo 10 caracteres y se deben mostrar en mayúscula
        System.out.println("SELECT product_id, UPPER(LEFT(product_name, 10)) AS product_name, unit_price FROM products;");
        productService.getAll().stream().map(product -> product.getProductId() + " " + substring.apply(product.getProductName()).toUpperCase() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais Obtener los clientes cuyo pais sea Spain
        System.out.println("SELECT customer_id, company_name, country FROM customers WHERE country = 'Spain';");
        customerService.getAll().stream().filter(customer -> customer.getCountry().equals("Spain")).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCountry()).forEach(System.out::println);

//        Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais, Obtener los clientes cuyo pais comience con la letra U
        System.out.println("SELECT customer_id, company_name, country FROM customers WHERE country LIKE 'U%';");
        customerService.getAll().stream().filter(customer -> customer.getCountry().startsWith("U")).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCountry()).forEach(System.out::println);

//        Obtener de la tabla de Customers las columnas CustomerId, CompanyName, Pais, Obtener los clientes cuyo pais comience con la letra U,S,A
        System.out.println("SELECT customer_id, company_name, country FROM customers WHERE LEFT(country,1) IN ('U','A','S');");
        customerService.getAll().stream().filter(customer -> customer.getCountry().startsWith("U") || customer.getCountry().startsWith("S") || customer.getCountry().startsWith("A")).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCountry()).forEach(System.out::println);

//        Obtener de la tabla de Productos las columnas productid, ProductName, UnitPrice cuyos precios esten entre 50 y 150
        System.out.println("SELECT product_id, product_name, unit_price FROM products WHERE unit_price BETWEEN 50 AND 150;");
        productService.getAll().stream().filter(product -> product.getUnitPrice() > 50 && product.getUnitPrice() < 150).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener de la tabla de Productos las columnas productid, ProductName, UnitPrice, UnitsInStock cuyas existencias esten entre 50 y 100
        System.out.println("SELECT product_id, product_name, unit_price, units_in_stock FROM products WHERE units_in_stock BETWEEN 50 AND 100;");
        productService.getAll().stream().filter(product -> product.getUnitsInStock() > 50 && product.getUnitsInStock() < 100).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice() + " " + product.getUnitsInStock()).forEach(System.out::println);

//        Obtener las columnas OrderId, CustomerId, employeeid de la tabla de ordenes cuyos empleados sean 1, 4, 9
        System.out.println("SELECT order_id, customer_id, employee_id FROM orders WHERE employee_id IN (1, 4, 9);");
        orderService.getAll().stream().filter(order -> order.getEmployeeId() == 1 || order.getEmployeeId() == 4 || order.getEmployeeId() == 9).map(order -> order.getOrderId() + " " + order.getCustomerId() + " " + order.getEmployeeId()).forEach(System.out::println);

//    ORDENAR EL RESULTADO DE LA QUERY POR ALGUNA COLUMNA Obtener la información de la tabla de Products, Ordenarlos por Nombre del Producto de forma ascendente
        System.out.println("SELECT * FROM products ORDER BY product_name ASC;");
        productService.getAll().stream().sorted(Comparator.comparing(Product::getProductName)).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener la información de la tabla de Products, Ordenarlos por Categoria de forma ascendente y por precio unitario de forma descendente
        System.out.println("SELECT * FROM products ORDER BY category_id ASC, unit_price DESC;");
        productService.getAll().stream().sorted(Comparator.comparing(Product::getCategoryId).thenComparing(Product::getUnitPrice).reversed()).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice()).forEach(System.out::println);

//        Obtener la información de la tabla de Clientes, Customerid, CompanyName, city, country ordenar por pais, ciudad de forma ascendente
        System.out.println("SELECT customer_id, company_name, city, country FROM customers ORDER BY country ASC, city ASC;");
        customerService.getAll().stream().sorted(Comparator.comparing(Customer::getCountry).thenComparing(Customer::getCity)).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCity() + " " + customer.getCountry()).forEach(System.out::println);

//Obtener los productos productid, productname, categoryid, supplierid ordenar por categoryid y supplier únicamente mostrar aquellos cuyo precio esté entre 25 y 200
        System.out.println("SELECT product_id, product_name, category_id, supplier_id \\n\" +\n" +
                "                \"FROM products \\n\" +\n" +
                "                \"WHERE unit_price BETWEEN 25 AND 200 \\n\" +\n" +
                "                \"ORDER BY category_id ASC, supplier_id ASC;");
        productService.getAll().stream().filter(product -> product.getUnitPrice() > 25 && product.getUnitPrice() < 200).sorted(Comparator.comparing(Product::getCategoryId).thenComparing(Product::getSupplierId)).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getCategoryId() + " " + product.getSupplierId()).forEach(System.out::println);

//        Cuantos productos hay en la tabla de productos
        System.out.println("SELECT COUNT(*) FROM products;");
        System.out.println(productService.getAll().stream().count());

//        de la tabla de productos Sumar las cantidades en existencia
        System.out.println("SELECT SUM(units_in_stock) FROM products;");

        System.out.println(productService.getAll().stream().mapToInt(Product::getUnitsInStock).sum());

//        Promedio de los precios de la tabla de productos
        System.out.println("SELECT AVG(unit_price) FROM products;");
        System.out.println(productService.getAll().stream().mapToDouble(Product::getUnitPrice).average());

//        Obtener los datos de productos ordenados descendentemente por precio unitario de la categoría 1
        System.out.println("SELECT * FROM products WHERE category_id = 1 ORDER BY unit_price DESC;");
        productService.getAll().stream().filter(product -> product.getCategoryId() == 1).sorted(Comparator.comparing(Product::getUnitPrice).reversed()).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice() + " " + product.getCategoryId()).forEach(System.out::println);

//        Obtener los datos de los clientes(Customers) ordenados descendentemente por nombre(CompanyName) que se encuentren en la ciudad(city) de barcelona, Lisboa
        System.out.println("SELECT * FROM customers WHERE city IN ('Barcelona', 'Lisboa') ORDER BY company_name DESC;");
        customerService.getAll().stream().filter(customer -> customer.getCity().equals("Barcelona") || customer.getCity().equals("Lisboa")).sorted(Comparator.comparing(Customer::getCompanyName).reversed()).map(customer -> customer.getCustomerId() + " " + customer.getCompanyName() + " " + customer.getCity()).forEach(System.out::println);

//        Obtener los datos de las ordenes, ordenados descendentemente por la fecha de la orden cuyo cliente(CustomerId) sea ALFKI
        System.out.println("SELECT * FROM orders WHERE customer_id = 'ALFKI' ORDER BY order_date DESC;");
        orderService.getAll().stream().filter(order -> order.getCustomerId().equals("ALFKI")).sorted(Comparator.comparing(Order::getOrderDate).reversed()).map(order -> order.getOrderId() + " " + order.getCustomerId() + " " + order.getOrderDate()).forEach(System.out::println);

//        --Obtener los datos de las ordenes ordenados ascendentemente por la fecha de la orden cuyo empleado sea 2 o 4
        System.out.println("SELECT * FROM order_details WHERE product_id IN (1, 5, 20) ORDER BY unit_price ASC;");
        orderService.getAll().stream().filter(order -> order.getEmployeeId() == 2 || order.getEmployeeId() == 4).sorted(Comparator.comparing(Order::getOrderDate)).map(order -> order.getOrderId() + " " + order.getCustomerId() + " " + order.getOrderDate() + " " + order.getEmployeeId()).forEach(System.out::println);

//        Obtener los productos cuyo precio están entre 30 y 60 ordenado por nombre
        System.out.println("SELECT * FROM products WHERE unit_price BETWEEN 30 AND 60 ORDER BY product_name ASC;");
        productService.getAll().stream().filter(product -> product.getUnitPrice() > 30 && product.getUnitPrice() < 60).sorted(Comparator.comparing(Product::getProductName)).map(product -> product.getProductId() + " " + product.getProductName() + " " + product.getUnitPrice()).forEach(System.out::println);

//        OBTENER EL MAXIMO, MINIMO Y PROMEDIO DE PRECIO UNITARIO DE LA TABLA DE PRODUCTOS UTILIZANDO ALIAS
        System.out.println("SELECT MAX(unit_price) AS precio_maximo, MIN(unit_price) AS precio_minimo, AVG(unit_price) AS precio_promedio FROM products;");
        System.out.println(productService.getAll().stream().mapToDouble(Product::getUnitPrice).max());
        System.out.println(productService.getAll().stream().mapToDouble(Product::getUnitPrice).min());
        System.out.println(productService.getAll().stream().mapToDouble(Product::getUnitPrice).average());

//        Numero de productos por categoria
        System.out.println("SELECT category_id, COUNT(*) FROM products GROUP BY category_id;");
        productService.getAll().stream().collect(Collectors.groupingBy(Product::getCategoryId, Collectors.counting())).forEach((categoryId, count) -> System.out.println(categoryId + " " + count));

//        Obtener el precio promedio por proveedor de la tabla de productos
        System.out.println("SELECT supplier_id, AVG(unit_price) FROM products GROUP BY supplier_id;");
        productService.getAll().stream().collect(Collectors.groupingBy(Product::getSupplierId, Collectors.averagingDouble(Product::getUnitPrice))).forEach((supplierId, average) -> System.out.println(supplierId + " " + average));

//        Obtener la suma de inventario (UnitsInStock) por SupplierID De la tabla de productos
        System.out.println("SELECT supplier_id, SUM(units_in_stock) FROM products GROUP BY supplier_id;");
        productService.getAll().stream().collect(Collectors.groupingBy(Product::getSupplierId, Collectors.summingInt(Product::getUnitsInStock))).forEach((supplierId, sum) -> System.out.println(supplierId + " " + sum));

//        Contar las ordenes por cliente de la tabla de orders
        System.out.println("SELECT customer_id, COUNT(*) FROM orders GROUP BY customer_id;");
        orderService.getAll().stream().collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting())).forEach((customerId, count) -> System.out.println(customerId + " " + count));

//        Contar las ordenes por empleado de la tabla de ordenes unicamente del empleado 1,3,5,6
        System.out.println("SELECT employee_id, COUNT(*) FROM orders WHERE employee_id IN (1, 3, 5, 6) GROUP BY employee_id;");
        orderService.getAll().stream().filter(order -> order.getEmployeeId() == 1 || order.getEmployeeId() == 3 || order.getEmployeeId() == 5 || order.getEmployeeId() == 6).collect(Collectors.groupingBy(Order::getEmployeeId, Collectors.counting())).forEach((employeeId, count) -> System.out.println(employeeId + " " + count));

//        Obtener la suma del envío (freight) por cliente
        System.out.println("SELECT customer_id, SUM(freight) FROM orders GROUP BY customer_id;");
        orderService.getAll().stream().collect(Collectors.groupingBy(Order::getCustomerId, Collectors.summingDouble(order -> order.getFreight() == null ?0:order.getFreight()))).forEach((customerId, sum) -> System.out.println(customerId + " " + sum));

//        De la tabla de ordenes únicamente de los registros cuya ShipCity sea Madrid, Sevilla, Barcelona, Lisboa, LondonOrdenado por el campo de suma del envío
        System.out.println("SELECT ship_city, SUM(freight) AS suma_envio FROM orders WHERE ship_city IN ('Madrid', 'Sevilla', 'Barcelona', 'Lisboa', 'London') GROUP BY ship_city ORDER BY suma_envio;  ");
        orderService.getAll().stream().filter(order -> Optional.ofNullable(
                order.getShipCity()).orElse("").equals("Madrid")
                || Optional.ofNullable(order.getShipCity()).orElse("").equals("Sevilla")
                || Optional.ofNullable(order.getShipCity()).orElse("").equals("Barcelona")
                || Optional.ofNullable(order.getShipCity()).orElse("").equals("Lisboa")
                || Optional.ofNullable(order.getShipCity()).orElse("").equals("London"))
                .collect(Collectors.groupingBy(Order::getShipCity, Collectors.summingDouble(order -> order.getFreight() == null ?0:order.getFreight()))).entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .forEach(System.out::println);

//        obtener el precio promedio de los productos por categoria sin contar con los productos descontinuados (Discontinued)
        System.out.println("SELECT category_id, AVG(unit_price) FROM products WHERE discontinued = 0 GROUP BY category_id;");
        productService.getAll().stream().filter(product -> product.getDiscontinued() == 0).collect(Collectors.groupingBy(Product::getCategoryId, Collectors.averagingDouble(Product::getUnitPrice))).forEach((categoryId, average) -> System.out.println(categoryId + " " + average));

//        Obtener la cantidad de productos por categoria,  aquellos cuyo precio se encuentre entre 10 y 60 que tengan más de 12 productos
        System.out.println("SELECT category_id, COUNT(*) FROM products WHERE unit_price BETWEEN 10 AND 60 GROUP BY category_id HAVING COUNT(*) > 12;");
        productService.getAll().stream().filter(product -> product.getUnitPrice() > 10 && product.getUnitPrice() < 60)
                .collect(Collectors.groupingBy(Product::getCategoryId, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 12)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
//        --OBTENER LA SUMA DE LAS UNIDADES EN EXISTENCIA (UnitsInStock) POR CATEGORIA, Y TOMANDO EN CUENTA UNICAMENTE LOS PRODUCTOS CUYO PROVEEDOR (SupplierID) SEA IGUAL A 17, 19, 16.
//                --cuya categoria tenga menos de 100 unidades ordenado por unidades
        System.out.println("SELECT category_id, units_exist \n" +
                "FROM (\n" +
                "    SELECT category_id, SUM(units_in_stock) AS units_exist \n" +
                "    FROM products \n" +
                "    WHERE supplier_id IN (17, 19, 16) \n" +
                "    GROUP BY category_id) AS tabla\n" +
                "WHERE units_exist > 100 \n" +
                "ORDER BY units_exist;");
        productService.getAll().stream().filter(product -> product.getSupplierId() == 17 || product.getSupplierId() == 19 || product.getSupplierId() == 16)
                .collect(Collectors.groupingBy(Product::getCategoryId, Collectors.summingInt(Product::getUnitsInStock)))
                .entrySet().stream()
                .filter(entry -> entry.getValue() < 200)
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

    }


}
