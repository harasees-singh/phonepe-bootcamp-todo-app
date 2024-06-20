package todo_app.resources;

import todo_app.api.Todo;
import todo_app.db.TodoDao;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final TodoDao todoDao;

    public TodoResource(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @GET
    public List<Todo> getAllTodos() {
        return todoDao.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getTodoById(@PathParam("id") String id) {
        Optional<Todo> todo = todoDao.findById(id);
        if (todo.isPresent()) {
            return Response.ok(todo.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createTodo(Todo todo) {
        todoDao.insert(todo);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTodo(@PathParam("id") String id, Todo todo) {
        Optional<Todo> existingTodo = todoDao.findById(id);
        if (existingTodo.isPresent()) {
            todo.setId(id);
            todoDao.update(todo);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodoById(@PathParam("id") String id) {
        todoDao.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
