package com.myzonesoft.microservice.todo;

import com.myzonesoft.microservice.todo.model.Todo;
import com.myzonesoft.microservice.todo.repository.TodoRepository;
import com.myzonesoft.microservice.todo.service.TodoServiceImpl;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceLayerTests {

    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoRepository todoRepository;

    private List<Todo> todoList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Todo todo1 = new Todo(1L,"Create a Todo for test 1", "Create a Todo for test 1",
                LocalDate.now(), "NOT_STARTED");
        Todo todo2 = new Todo(2L,"Create a Todo for test 2", "Create a Todo for test 2",
                LocalDate.now(), "NOT_STARTED");
        Todo todo3 = new Todo(3L,"Create a Todo for test 3", "Create a Todo for test 3",
                LocalDate.now(), "NOT_STARTED");

        todoList.add(todo1);
        todoList.add(todo2);
        todoList.add(todo3);
        Mockito.when(todoRepository.findAll()).thenReturn(todoList);
    }

    @Test
    public void findAllTodoTasksTest() {
        List<Todo> todoTaskList = todoService.findAll();
        Assert.assertEquals(3, todoTaskList.size());
    }
}
