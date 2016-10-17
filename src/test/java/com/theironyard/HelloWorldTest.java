package com.theironyard;

import net.doughughes.testifier.annotation.Testifier;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.watcher.NotifyingWatcher;
import net.doughughes.testifier.watcher.OutputWatcher;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Testifier(sourcePath = "./src/main/java/com/theironyard/HelloWorld.java", clazz = HelloWorld.class)
public class HelloWorldTest {

    @Rule
    public NotifyingWatcher notifyingWatcher = new NotifyingWatcher("https://tiy-testifier-webapp.herokuapp.com/notify");

    @Rule
    public OutputWatcher outputWatcher = new OutputWatcher();

    @Test
    @Testifier(method = "main", args = {String[].class})
    public void testMain() {
        /* arrange */

        /* act */
        // run the main method
        HelloWorld.main(new String[]{});

        /* assert */
        OutputStreamInterceptor out = (OutputStreamInterceptor) System.out;
        assertThat("No output was printed.",
                out.getPrinted().size(), greaterThan(0));
        assertThat("\"Hello World\" was not printed",
                out.getPrinted().get(0), is("Hello World"));

    }
}