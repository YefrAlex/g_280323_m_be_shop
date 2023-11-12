package de.telran.g_280323_m_be_shop.controllers;

import de.telran.g_280323_m_be_shop.domain.entity.common.CommonCustomer;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Customer;
import de.telran.g_280323_m_be_shop.domain.entity.jpa.JpaCustomer;
import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.EntityValidationException;
import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.ThirdTestException;
import de.telran.g_280323_m_be_shop.service.interfaces.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    /**
     * Сервис покупателей.
     * Содержит бизнес-логику, относящуюся к покупателям.
     */
    @Autowired
    private CustomerService service;

    /**
     * Получение всех покупателей.
     *
     * @return список всех покупателей, хранящихся в БД.
     */
    @GetMapping("/all")
    public List<Customer> getAll() {
        return service.getAll();
    }

    /**
     * Получение покупателя по идентификатору.
     *
     * @param id    идентификатор покупателя.
     * @return      покупатель, имеющий переданный идентификатор.
     */
    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        if (!getAll().contains(id)){
            throw new ThirdTestException("There is no customer with this id");
        }
        return service.getById(id);
    }

    /**
     * Добавление покупателя.
     *
     * @param customer  объект покупателя, содержащийся в теле POST-запроса.
     * @return          объект сохраняемого покупателя в случае успешного сохранения.
     */
    @PostMapping ("/add")
    public Customer add(@RequestBody @Valid CommonCustomer customer) {
        try {
            service.add(customer);
        } catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }

        return customer;
    }

    /**
     * Удаление покупателя.
     *
     * @param id идентификатор удаляемого из БД покупателя.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (!getAll().contains(id)){
            throw new ThirdTestException("There is no customer with this id");
        }
        service.deleteById(id);
    }

    /**
     * Удаление покупателя.
     *
     * @param name имя удаляемого из БД покупателя.
     */
    @DeleteMapping("/name/{name}")
    public void delete(@PathVariable String name) {
        if (!getAll().contains(name)){
            throw new ThirdTestException("There is no customer with this name");
        }
        service.deleteByName(name);
    }

    /**
     * Получение количества покупателей.
     *
     * @return количество покупателей, содержащихся в БД.
     */
    @GetMapping("/count")
    public int getCount() {
        return service.getCount();
    }

    /**
     * Получение стоимости продуктов конкретного покупателя.
     *
     * @param id    идентификатор покупателя.
     * @return      стоимость всех продуктов в корзине покупателя с переданным идентификатором.
     */
    @GetMapping("/total/{id}")
    public double getTotalPrice(@PathVariable int id) {
        if (!getAll().contains(id)){
            throw new ThirdTestException("There is no customer with this id");
        }
        return service.getTotalPriceById(id);
    }

    /**
     * Получение средней стоимости продукта конкретного покупателя.
     *
     * @param id    идентификатор покупателя.
     * @return      средняя стоимость продукта в корзине покупателя с переданным идентификатором.
     */
    @GetMapping("/average/{id}")
    public double getAverage(@PathVariable int id) {
        if (!getAll().contains(id)){
            throw new ThirdTestException("There is no customer with this id");
        }
        return service.getAveragePriceById(id);
    }

    /**
     * Добавление продукта в корзину.
     *
     * @param customerId    идентификатор покупателя, которому добавляется продукт.
     * @param productId     идентификатор добавляемого продукта.
     */
    @GetMapping("/{customerId}/{productId}")
    public void addToCart(@PathVariable int customerId, @PathVariable int productId) {
        if (!getAll().contains(customerId)){
            throw new ThirdTestException("There is no customer with this id");
        }
        if (!getAll().contains(productId)){
            throw new ThirdTestException("There is no product with this id");
        }
        service.addToCartById(customerId, productId);
    }

    /**
     * Удаление продукта из корзины.
     *
     * @param customerId    идентификатор покупателя, из корзины которого удаляется продукт.
     * @param productId     идентификатор удаляемого продукта.
     */
    @DeleteMapping("/{customerId}/{productId}")
    public void deleteFromCart(@PathVariable int customerId, @PathVariable int productId) {
        if (!getAll().contains(customerId)){
            throw new ThirdTestException("There is no customer with this id");
        }
        if (!getAll().contains(productId)){
            throw new ThirdTestException("There is no product with this id");
        }
        service.deleteFromCartById(customerId, productId);
    }

    /**
     * Очистка корзины.
     *
     * @param customerId идентификатор покупателя, у которого очищается корзина.
     */
    @DeleteMapping("/clear/{customerId}")
    public void clearCart(@PathVariable int customerId) {
        if (!getAll().contains(customerId)){
            throw new ThirdTestException("There is no customer with this id");
        }
        service.clearCartById(customerId);
    }

}
