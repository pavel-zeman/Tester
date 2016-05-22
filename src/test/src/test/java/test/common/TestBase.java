package test.common;

import java.io.IOException;
import java.util.HashSet;

import org.junit.AfterClass;
import org.junit.Rule;

/**
 * @author Pavel Zeman
 */
public abstract class TestBase {
    @Rule
    public Watcher watcher = new Watcher();

    private static HashSet<Class<?>> classInitialized = new HashSet<>();

    public TestBase() {
        if (!classInitialized.contains(this.getClass())) {
            classInitialized.add(this.getClass());
            try {
                Utils.clearResultDir(this.getClass().getName());
            } catch (IOException e) {
                throw new RuntimeException("Error when creating test", e);
            }
        }
    }

    @AfterClass
    public static void afterClass() {
        Utils.clearDriver();
    }

}
