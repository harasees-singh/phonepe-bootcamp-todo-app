package todo_app;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;
import todo_app.db.TodoDao;
import todo_app.filter.CORSFilter;
import todo_app.resources.TodoResource;
import todo_app.services.TodoService;

public class todo_appApplication extends Application<todo_appConfiguration> {

    public static void main(final String[] args) throws Exception {
        new todo_appApplication().run(args);
    }

    @Override
    public String getName() {
        return "todo_app";
    }

    @Override
    public void initialize(final Bootstrap<todo_appConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final todo_appConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

        final TodoDao todoDao = jdbi.onDemand(TodoDao.class);

        TodoService todoService = new TodoService(todoDao);

        environment.jersey().register(new TodoResource(todoService));
        environment.servlets().addFilter("CORSFilter", new CORSFilter()).addMappingForUrlPatterns(null, true, "/*");
    }

}
