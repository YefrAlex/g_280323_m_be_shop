package de.telran.g_280323_m_be_shop.controllers;


import de.telran.g_280323_m_be_shop.domain.entity.common.CommonProduct;
import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Product;
import de.telran.g_280323_m_be_shop.domain.entity.jpa.JpaProduct;
import de.telran.g_280323_m_be_shop.exeptions_handler.Response;
import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.EntityValidationException;
import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.FirstTestExceptions;
import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.SecondTestException;
import de.telran.g_280323_m_be_shop.exeptions_handler.exceptions.ThirdTestException;
import de.telran.g_280323_m_be_shop.service.common.CommonProductService;
import de.telran.g_280323_m_be_shop.service.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * Сервис продуктов.
     * Содержит бизнес-логику, относящуюся к продуктам.
     */
    @Autowired
    private ProductService service;

    /**
     * Получение всех продуктов.
     *
     * @return список всех продуктов, хранящихся в БД.
     */
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    /**
     * Получение продукта по идентификатору.
     *
     * @param id    идентификатор продукта.
     * @return      продукт, имеющий переданный идентификатор.
     */
    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {

        return service.getById(id);
    }

    /**
     * Добавление продукта.
     *
     * @param product   объект продукта, содержащийся в теле POST-запроса.
     * @return          объект сохраняемого продукта в случае успешного сохранения.
     */
    @PostMapping
    public Product add(@RequestBody @Valid JpaProduct product) {
        if ("Test".equals(product.getName())){
            throw new FirstTestExceptions("Inncorrect product name!!!");
        }
        if ("Test1".equals(product.getName())){
            throw new SecondTestException("Another incorrect name!!!");
        }
        if ("Test2".equals(product.getName())){
            throw new ThirdTestException("Message from third exception");
        }
        try{
            service.add(product);
        } catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }
        return product;
    }

    /**
     * Удаление продукта.
     *
     * @param id идентификатор удаляемого из БД продукта.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        if (!getAll().contains(id)){
            throw new ThirdTestException("There is no product with this id");
        }
        service.deleteById(id);
    }

    /**
     * Удаление продукта.
     *
     * @param name наименование удаляемого из БД продукта.
     */
    @DeleteMapping("/name/{name}")
    public void delete(@PathVariable String name) {
        if (!getAll().contains(name)){
            throw new ThirdTestException("There is no product with this name");
        }
        service.deleteByName(name);
    }

    /**
     * Получение количества продуктов.
     *
     * @return количество продуктов, содержащихся в БД.
     */
    @GetMapping("/count")
    public int getCount() {
        return service.getCount();
    }

    /**
     * Получение стоимости всех продуктов.
     *
     * @return суммарная стоимость всех продуктов, содержащихся в БД.
     */
    @GetMapping("/total")
    public double getTotalPrice() {
        return service.getTotalPrice();
    }

    /**
     * Получение средней стоимости.
     *
     * @return средняя стоимость продукта из всех продуктов, содержащихся в БД.
     */
    @GetMapping("/average")
    public double getAverage() {
        return service.getAveragePrice();
    }

    // 1й способ обработки ошибок
    // такой метод нужно прописывать в каждом классе
    // Либо мы можем унаследовать все контроллеры от общего родителя
    // но это не всегда возможно или удобно
    @ExceptionHandler(FirstTestExceptions.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(FirstTestExceptions e){
        return new Response(e.getMessage());
    }
}
