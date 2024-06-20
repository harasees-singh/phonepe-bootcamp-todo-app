package todo_app;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

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
        // TODO: implement application
    }

}
