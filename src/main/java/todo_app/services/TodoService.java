package todo_app.services;

import todo_app.api.Todo;
import todo_app.db.TodoDao;
import todo_app.enums.Status;

import java.util.List;
import java.util.Optional;

public class TodoService {
    private final TodoDao todoDao;
    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }
    public String insertTodo(Todo todo) {
        if (todo.getStatus() == null) {
            todo.setStatus(Status.TODO);
        }
        if (todo.getDescription() == null || todo.getDescription().isBlank()) {
            throw new IllegalArgumentException("Todo must have a valid non-empty description");
        }
        if (todo.getStartDate() == null) {
            throw new IllegalArgumentException("Todo must have a valid start date");
        }
        if (todo.getTargetDate() == null) {
            throw new IllegalArgumentException("Todo must have a valid target date");
        }
        if (todo.getStartDate().isAfter(todo.getTargetDate())) {
            throw new IllegalArgumentException("start date cannot be set past the target date");
        }
        return todoDao.insert(todo);
    }
    public Optional<Todo> findById(String id) {
        return todoDao.findById(id);
    }
    public List<Todo> findAll() {
        return todoDao.findAll();
    }
    public void updateTodo(Todo todo) {
        String id = todo.getId();
        Optional<Todo> prev_version = todoDao.findById(id);
        if (prev_version.isEmpty()) {
            throw new IllegalArgumentException("Todo with id " + id + " not found.");
        }
        if (todo.getStartDate() != null && todo.getTargetDate() != null && (todo.getStartDate().isAfter(todo.getTargetDate()))){
            throw new IllegalArgumentException("start date cannot be set past the target date");
        }
        else {
            Todo prev_version_todo = prev_version.get();
            if (todo.getDescription() == null)
                todo.setDescription(prev_version_todo.getDescription());
            if (todo.getStartDate() == null)
                todo.setStartDate(prev_version_todo.getStartDate());
            if (todo.getTargetDate() == null)
                todo.setTargetDate(prev_version_todo.getTargetDate());
            if (todo.getStatus() == null)
                todo.setStatus(prev_version_todo.getStatus());

            todoDao.update(todo);
        }
    }
    public void deleteById(String id) {
        todoDao.deleteById(id);
    }
}
