package com.example.proyecto.service;

import com.example.proyecto.model.Task;
import com.example.proyecto.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task(1L, "Task 1", "Desc 1", false);
        Task task2 = new Task(2L, "Task 2", "Desc 2", true);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertThat(tasks).hasSize(2);
        assertThat(tasks.get(0).getTitle()).isEqualTo("Task 1");
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task(1L, "Task 1", "Desc 1", false);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskById(1L);

        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getTitle()).isEqualTo("Task 1");
    }
}
