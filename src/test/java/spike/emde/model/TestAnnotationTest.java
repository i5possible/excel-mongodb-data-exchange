package spike.emde.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestAnnotationTest {
    @Test
    public void shouldReturnSchemaOfTheClass () {
        List<String> schemaNameListInOrder = Exportable.getSchemaNameListInOrder(AnnotationTest.class);
        List<String> expected = Arrays.asList("序号","名称","性别");

        assertEquals(expected,schemaNameListInOrder);
    }

    @Test
    public void shouldReturnValueListOfInstance () {
        AnnotationTest annotationTest1 = new AnnotationTest("1", "LH", "Male");
        Map<String, Object> schemaValueMap = Exportable.getSchemaValueMap(annotationTest1);
        Map<String, String> expected = new HashMap();
        expected.put("id", "1");
        expected.put("name", "LH");
        expected.put("sex", "Male");

        assertEquals(schemaValueMap,expected);
    }
}