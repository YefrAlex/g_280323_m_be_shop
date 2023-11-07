package de.telran.g_280323_m_be_shop.service.jpa;

import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Cart;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Customer;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Product;
import de.telran.g_280323_m_be_shop.domain.entity.jpa.JpaCustomer;
import de.telran.g_280323_m_be_shop.repository.jpa.JpaCartRepository;
import de.telran.g_280323_m_be_shop.repository.jpa.JpaCustomerRepository;
import de.telran.g_280323_m_be_shop.repository.jpa.JpaProductRepository;
import de.telran.g_280323_m_be_shop.service.interfaces.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaCustomerService implements CustomerService {

    private JpaCustomerRepository customerRepository;

    private JpaCartRepository cartRepository;

    private JpaProductRepository productRepository;

    public JpaCustomerService(JpaCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customerRepository.findAll());
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(new JpaCustomer(customer.getId(), customer.getName()));

    }

    @Override
    public void deleteById(int id) {
          customerRepository.deleteById(id);
    }

    @Override
    public int getCount() {
        return (int)customerRepository.count();
    }

    @Override
    public double getTotalPriceById(int id) {
        return getById(id).getCart().getTotalPrice();
    }

    @Override
    public double getAveragePriceById(int id) {
        return getById(id).getCart().getAveragePrice();
    }

    @Override
    public void deleteByName(String name) {
        customerRepository.deleteByName(name);
    }
    @Transactional
    @Override
    public void addToCartById(int customerId, int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        getById(customerId).getCart().addProduct(product);
    }
    @Transactional
    @Override
    public void deleteFromCartById(int customerId, int productId) {
        getById(customerId).getCart().deleteProduct(productId);
    }
    @Transactional
    @Override
    public void clearCartById(int id) {
        getById(id).getCart().clear();
    }
}
