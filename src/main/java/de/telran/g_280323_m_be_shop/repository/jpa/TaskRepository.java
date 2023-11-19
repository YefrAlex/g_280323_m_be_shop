package de.telran.g_280323_m_be_shop.repository.jpa;

import de.telran.g_280323_m_be_shop.domain.entity.jpa.JpaProduct;
import de.telran.g_280323_m_be_shop.domain.entity.jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository <Task, Integer> {

    @Query(value="SELECT * FROM task order by  executed_at DESC LIMIT 5;", nativeQuery=true)
    List<Task> findFiveLatestTask();


    @Query(value="SELECT * FROM task\n" +
            "WHERE `description`\n" +
            "LIKE 'Последний добавленный в БД продукт %'\n" +
            "order by executed_at desc\n" +
            "limit 1;", nativeQuery=true)
    Task getLastProductAddTask();
}
