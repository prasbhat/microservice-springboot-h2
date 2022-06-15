package com.myzonesoft.todo.microservice;

import com.myzonesoft.todo.microservice.model.Tasks;
import com.myzonesoft.todo.microservice.model.TodoTaskComments;
import com.myzonesoft.todo.microservice.repository.TasksRepository;
import com.myzonesoft.todo.microservice.service.TodoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceLayerTests {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TasksRepository tasksRepository;

    private List<Tasks> tasksList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        TodoTaskComments todoTaskComments = new TodoTaskComments(1L, "Test Comment", LocalDate.now());
        Set<TodoTaskComments> todoTaskCommentsSet = new HashSet<>();
        todoTaskCommentsSet.add(todoTaskComments);
        Tasks task1 = new Tasks(1L,"Create a Todo for test 1", "Create a Todo for test 1", LocalDate.now(),
                LocalDate.now(), "NOT_STARTED", todoTaskCommentsSet);
        Tasks task2 = new Tasks(2L,"Create a Todo for test 2", "Create a Todo for test 2", LocalDate.now(),
                LocalDate.now(), "NOT_STARTED", todoTaskCommentsSet);
        Tasks task3 = new Tasks(3L,"Create a Todo for test 3", "Create a Todo for test 3", LocalDate.now(),
                LocalDate.now(), "NOT_STARTED", todoTaskCommentsSet);

        tasksList.add(task1);
        tasksList.add(task2);
        tasksList.add(task3);
        Mockito.when(tasksRepository.findAll()).thenReturn(tasksList);
    }

    @Test
    public void findAllTodoTasksTest() {
        List<Tasks> todoTaskList = todoService.findAll();
        Assert.assertEquals(3, todoTaskList.size());
    }
}
