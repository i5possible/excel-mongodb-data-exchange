package spike.emde.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestAnnotationTest {
    AnnotationTest annotationTest;

    @Before
    public void setUp() throws Exception {
        annotationTest = new AnnotationTest();
    }

    @Test
    public void shouldReturnSchemaOfTheClass() {
        List<String> schemaNameListInOrder = annotationTest.fetchSchema();
        List<String> expected = Arrays.asList("id", "name", "sex");

        assertEquals(expected, schemaNameListInOrder);
    }

    @Test
    public void shouldReturnTitleOftheClass() {
        String title = annotationTest.fetchTitle();
        String expected = "TEST";

        assertEquals(expected, title);
    }

    @Test
    public void shouldReturnValueList() {
        AnnotationTest annotationTest1 = new AnnotationTest("1", "LH", "Male");
        List<String> valueListInOrder = annotationTest1.toList();
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("LH");
        expected.add("Male");
        assertEquals(expected, valueListInOrder);
    }

    @Test
    public void shouldReturnSchemaWithinGivingList() {
        List<String> givenList = Arrays.asList("id", "sex");
        List<String> expected = Arrays.asList("id", "sex");
        assertEquals(expected, givenList);
    }
}