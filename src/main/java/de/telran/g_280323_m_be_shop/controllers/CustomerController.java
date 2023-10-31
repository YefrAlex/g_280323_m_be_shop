package de.telran.g_280323_m_be_shop.controllers;


import de.telran.g_280323_m_be_shop.domain.entity.common.CommonCustomer;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Customer;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Product;
import de.telran.g_280323_m_be_shop.service.common.CommonCustomerService;
import de.telran.g_280323_m_be_shop.service.common.CommonProductService;
import de.telran.g_280323_m_be_shop.service.interfaces.CustomerService;
import de.telran.g_280323_m_be_shop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
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

    // GET -> http://localhost:8080/customers/2
    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        return customerService.getById(id);
    }
    // POST -> http://localhost:8080/customers
    //todo
    @PostMapping
    public Customer addCustomer(@RequestBody CommonCustomer customer) {
        customerService.add(customer);
        return customer;
    }

    // DELETE -> http://localhost:8080/customers/delete/2
    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable int id) {
       customerService.deleteById(id);
    }
    // GET -> http://localhost:8080/customers/count
    @GetMapping("/count")
    public String getCount(){
       return "Общее количество зарегестрированных пользователей = " + customerService.getCount();
    }
    // GET -> http://localhost:8080/customers/2/totalprice
    @GetMapping("/{id}/totalprice")
    public String getTotalPrice(@PathVariable int id){
        return "Общая сумма товаров в корзине " + customerService.getTotalPriceById(id);
    }
    // GET -> http://localhost:8080/customers/2/averageprice
    @GetMapping("/{id}/averageprice")
    public String getAveragePrice(@PathVariable int id){
        return "Средняя сумма товаров в корзине " + customerService.getAveragePriceById(id);
    }

    // DELETE -> http://localhost:8080/customers/deletebyname/Vanya
    //todo   не удаляет
    @DeleteMapping("/deletebyname/{name}")
    public void deleteCustomerByName(@PathVariable String name) {
        customerService.deleteByName(name);
    }
    // POST -> http://localhost:8080/customers/2/add/2
    // todo  пишет что не может скастить продукт к кастомеру
    @PostMapping ("/{id}/add/{productid}")
    void addToCartById (@PathVariable int id,@PathVariable int productid) {
        customerService.addToCartById(id,productid);
    }

}
