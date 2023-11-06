package de.telran.g_280323_m_be_shop.repository.mysql;

import de.telran.g_280323_m_be_shop.domain.entity.common.CommonCart;
import de.telran.g_280323_m_be_shop.domain.entity.common.CommonCustomer;
import de.telran.g_280323_m_be_shop.domain.entity.common.CommonProduct;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Cart;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Customer;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Product;
import de.telran.g_280323_m_be_shop.repository.interfaces.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

import static de.telran.g_280323_m_be_shop.domain.database.MySqlConnector.getConnection;

public class MySqlCustomerRepository implements CustomerRepository {

            // MySqlProductRepository productRepository;
     private final String C_ID="customer_id";
     private final String C_NAME="name";

    @Override
    public List<Customer> getAll() {
        try (Connection connection=getConnection()) {
            String query=" select * from customer as c left join customer_product as cp on c.customer_id = cp.customer_id left join product as p on cp.product_id = p.product_id;";
            ResultSet resultSet=connection.createStatement().executeQuery(query);
            List<Customer> customer=new ArrayList<>();
            Map<Integer, Customer> customerMap=new HashMap<>();
            while (resultSet.next()) {
                int id=resultSet.getInt(C_ID);
                if (!customerMap.containsKey(id)) {
                    String name=resultSet.getString(C_NAME);
                    Cart cart=new CommonCart();
                    if (resultSet.getInt("p.product_id") > 0) {
                        cart.addProduct(new CommonProduct(resultSet.getInt("p.product_id"),
                                resultSet.getString("p.name"), resultSet.getDouble("p.price")));
                    }
                    customer.add(new CommonCustomer(id, name, cart));
                    customerMap.put(id, new CommonCustomer(id, name, cart));
                } else {
                    customerMap.get(id).getCart().addProduct(new CommonProduct(resultSet.getInt("p.product_id"),
                            resultSet.getString("p.name"), resultSet.getDouble("p.price")));
                }
            }
            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getById(int id) {
        try (Connection connection=getConnection()) {
            String query=String.format("select * from customer as c left join customer_product as cp on c.customer_id = cp.customer_id " +
                    "left join product as p on cp.product_id = p.product_id where c.customer_id = %d;", id);
            ResultSet resultSet=connection.createStatement().executeQuery(query);
            Customer customer=null;
            while (resultSet.next()) {
                if (customer == null) {
                    String name=resultSet.getString(C_NAME);
                    Cart cart=new CommonCart();
                    if (resultSet.getInt("p.product_id") > 0) {
                        cart.addProduct(new CommonProduct(resultSet.getInt("p.product_id"),
                                resultSet.getString("p.name"), resultSet.getDouble("p.price")));
                    }
                    customer=new CommonCustomer(id, name, cart);
                } else {
                    customer.getCart().addProduct(new CommonProduct(resultSet.getInt("p.product_id"),
                            resultSet.getString("p.name"), resultSet.getDouble("p.price")));
                }

            }
            return customer;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    //INSERT INTO `customer` (`name`) VALUES ('%s');
    public void add(String name) {
        try (Connection connection=getConnection()) {
            String query=String.format(Locale.US, "INSERT INTO `customer` (`name`) VALUES ('%s');", name);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    //DELETE FROM  `customer` WHERE (`customer_id` = %d);
    public void delete(int id) {
        try (Connection connection=getConnection()) {
            String query=String.format("DELETE FROM `customer` WHERE (`customer_id` = %d);", id);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    //INSERT INTO `customer_product` (`customer_id`, `product_id`) VALUES ('2', '2');
    public void addToCartById(int customerId, int productId) {
        try (Connection connection=getConnection()) {
            String query=String.format(Locale.US, "INSERT INTO `customer_product` (`customer_id`, `product_id`) " +
                    "VALUES ('%d', '%d');", customerId, productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    //DELETE FROM  `customer_product` WHERE customer_id = 1 AND product_id = 1;
    public void deleteFromCartById(int customerId, int productId) {
        try (Connection connection=getConnection()) {
            String query = String.format("DELETE FROM  `customer_product` WHERE customer_id = %d AND product_id = %d;", customerId,productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    //DELETE FROM  `customer_product` WHERE customer_id = 1;
    public void clearCart(int customerId) {
        try (Connection connection=getConnection()) {
            String query = String.format("DELETE FROM  `customer_product` WHERE customer_id = %d;", customerId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
//  select * from customer as c left join customer_product as cp on c.customer_id = cp.customer_id left join product as p on cp.product_id = p.product_id;

//cart.add(productRepository.getById(resultSet.getInt(ID)));