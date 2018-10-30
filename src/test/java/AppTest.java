import DomainLayer.ProjectManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * JUnit test class.
 *
 * It now checks removing and adding projects.
 */
public class AppTest {

    private ProjectManager projectManager = ProjectManager.getInstance();

    @Test
    public void checkAddProject() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        projectManager.addProject("Test-1", "Test-Dec",
                new Date());
        projectManager.printProjectList();

        Assert.assertTrue(outContent.toString().contains("Test-1"));
    }

    @Test
    public void checkRemoveProject() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        projectManager.removeProject("Test-1");
        projectManager.printProjectList();

        Assert.assertTrue(!outContent.toString().contains("Test-Dec"));
    }
}
