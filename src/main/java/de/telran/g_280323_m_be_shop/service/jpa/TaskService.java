package de.telran.g_280323_m_be_shop.service.jpa;

import de.telran.g_280323_m_be_shop.domain.entity.jpa.Task;
import de.telran.g_280323_m_be_shop.repository.jpa.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repository;
    public TaskService(TaskRepository repository){
        this.repository=repository;
    }
    public Task createTask(String description){
        return repository.save(new Task(description));
    }
    public List<Task> findFiveLatestTasks() {
        return new ArrayList<>(repository.findFiveLatestTask());
    }
    public Task getLastProductAddTask (){
        return repository.getLastProductAddTask();
    }
}
