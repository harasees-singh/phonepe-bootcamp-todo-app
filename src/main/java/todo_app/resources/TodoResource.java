package todo_app.resources;

import todo_app.error.Error;
import todo_app.api.Todo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import todo_app.services.TodoService;

import java.util.List;
import java.util.Optional;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getTodoById(@PathParam("id") String id) {
        Optional<Todo> todo = todoService.findById(id);
        if (todo.isPresent()) {
            return Response.ok(todo.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("Todo with id " + id + " does not exist")).build();
        }
    }

    @POST
    public Response createTodo(Todo todo) {
        try {
            String id = todoService.insertTodo(todo);
            return Response.status(Response.Status.CREATED).entity(id).build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateTodo(@PathParam("id") String id, Todo todo) {
        todo.setId(id);
        try {
            todoService.updateTodo(todo);
            return Response.ok().build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodoById(@PathParam("id") String id) {
        todoService.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
