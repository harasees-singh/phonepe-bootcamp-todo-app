package todo_app.db;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import todo_app.api.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoDao {

    @SqlUpdate("INSERT INTO todos (id, status, description, startDate, targetDate) VALUES (:id, :status, :description, :startDate, :targetDate)")
    void insert(@BindBean Todo todo);

    @SqlQuery("SELECT * FROM todos WHERE id = :id")
    @RegisterBeanMapper(Todo.class)
    Optional<Todo> findById(@Bind("id") String id);

    @SqlQuery("SELECT * FROM todos")
    @RegisterBeanMapper(Todo.class)
    List<Todo> findAll();

    @SqlUpdate("UPDATE todos SET status = :status, description = :description, startDate = :startDate, targetDate = :targetDate WHERE id = :id")
    void update(@BindBean Todo todo);

    @SqlUpdate("DELETE FROM todos WHERE id = :id")
    void deleteById(@Bind("id") String id);
}
