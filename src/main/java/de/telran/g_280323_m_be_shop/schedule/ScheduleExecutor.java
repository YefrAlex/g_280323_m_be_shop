package de.telran.g_280323_m_be_shop.schedule;


import de.telran.g_280323_m_be_shop.domain.entity.jpa.Task;
import de.telran.g_280323_m_be_shop.service.jpa.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleExecutor {

    private static final Logger LOGGER=LoggerFactory.getLogger(ScheduleExecutor.class);

    private TaskService service;

    public ScheduleExecutor(TaskService service) {
        this.service=service;
    }

           // HW

    @Scheduled(fixedRate = 30000)
    @Async
    public void printLatestTasks() {
        List<Task> latestTasks = service.findFiveLatestTasks();
        System.out.println("Latest tasks:");
        latestTasks.forEach(task -> System.out.println(task));
    }
    @Scheduled(cron = "15,45 * * * * *")
    public void printLastAddProductTask (){
        Task lastAddedProduct = service.getLastProductAddTask();
        System.out.println("последняя задача с добавлением продуктов");
        System.out.println(lastAddedProduct.toString());
    }


//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        Task task = service.createTask("Fixed delay task");
//        LOGGER.info(task.getDescription());
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayLongTimeTask() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("Fixed delay long time task 3000");
//        LOGGER.info(task.getDescription());
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayLongTimeTask() {
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("Fixed delay long time task 7000");
//        LOGGER.info(task.getDescription());
//    }
//    @Scheduled(fixedRate = 5000)
//    public void fixedRateLongTimeTask() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("Fixed rate long time task 3000");
//        LOGGER.info(task.getDescription());
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateLongTimeTask() {
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("Fixed rate long time task 7000");
//        LOGGER.info(task.getDescription());
//    }
//    @Scheduled(fixedRate = 5000)
//    @Async
//    public void fixedRateLongTimeAsyncTask() {
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("Fixed rate long time asynk task 7000");
//        LOGGER.info(task.getDescription());
//    }

//    @Scheduled(fixedDelay = 5000, initialDelay=20000)
//    public void fixedDelay_InitialTask() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("Fixed initial delay task");
//        LOGGER.info(task.getDescription());
//    }
     //задержка 2 часа - 7 200 000 -> PT02H
//    @Scheduled(fixedDelayString = "PT03S")
//    public void anotherDelatFormatTask() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("another delay format task");
//        LOGGER.info(task.getDescription());
//    }

//    @Scheduled(fixedDelayString = "${interval}")
//    public void delayInPropertyTask() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask(" delay in property task");
//        LOGGER.info(task.getDescription());
//    }

//    @Scheduled(cron = "${cron-interval}")
//    public void cronExpressionTask() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Task task = service.createTask("cron expression task");
//        LOGGER.info(task.getDescription());
//    }

//    public static void executScheduleTask (Task task){
//        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//        scheduler.schedule(()-> LOGGER.info(task.getDescription()),
//                new CronTrigger("0,10,20,30,40,50 * * * * * "));
//    }

//    public static void executeTaskOnce(Task task){
//        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//        Instant executionTime = Instant.now().plusSeconds(20);
//        scheduler.schedule(()-> LOGGER.info(task.getDescription()), executionTime);
//    }


}
