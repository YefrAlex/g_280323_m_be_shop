package de.telran.g_280323_m_be_shop.controllers;


import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Customer;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Product;
import de.telran.g_280323_m_be_shop.service.common.CommonCustomerService;
import de.telran.g_280323_m_be_shop.service.common.CommonProductService;
import de.telran.g_280323_m_be_shop.service.interfaces.CustomerService;
import de.telran.g_280323_m_be_shop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService ;

    // GET -> http://localhost:8080/customers/all
    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }
}
