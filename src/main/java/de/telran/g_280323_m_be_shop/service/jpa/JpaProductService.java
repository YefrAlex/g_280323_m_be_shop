package de.telran.g_280323_m_be_shop.service.jpa;

import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Product;
import de.telran.g_280323_m_be_shop.domain.entity.jpa.JpaProduct;
import de.telran.g_280323_m_be_shop.domain.entity.jpa.Task;
import de.telran.g_280323_m_be_shop.repository.jpa.JpaProductRepository;
import de.telran.g_280323_m_be_shop.schedule.ScheduleExecutor;
import de.telran.g_280323_m_be_shop.service.interfaces.ProductService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaProductService implements ProductService {

    private final Logger LOGGER =LogManager.getLogger(JpaProductService.class);

    private JpaProductRepository repository;
    private TaskService taskService;

    JpaProduct lastProduct ;


    public JpaProductService(JpaProductRepository repository, TaskService taskService) {
        this.repository=repository;
        this.taskService=taskService;
    }

    @Override
    public List<Product> getAll() {
        Task task = taskService.createTask("запрошен список всех продуктов");
       // ScheduleExecutor.executScheduleTask(task);
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Product getById(int id) {
        LOGGER.log(Level.INFO, "Запрошен продукт с идентификатором {}.",id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(Product product) {
        repository.save(new JpaProduct(product.getId(), product.getName(), product.getPrice()));
        Task task = taskService.createTask("Последний добавленный в БД продукт " + product.getName());
    }

    @Override
    public void deleteById(int id) {
//        Task task = taskService.createTask("Попытка удаления продукта с ИД " + id);
//        LOGGER.info("Вызван метод удалить по ид");
//        ScheduleExecutor.executeTaskOnce(task);
          repository.deleteById(id);
    }

    @Override
    public int getCount() {
        return (int)repository.count();
    }

    @Override
    public double getTotalPrice() {
        return repository.getTotalPrice();
    }

    @Override
    public double getAveragePrice() {
        return repository.getAvgPrice();
    }

    @Override
    public void deleteByName(String name) {
         repository.deleteByName(name);
    }

}
