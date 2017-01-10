package spike.emde.annotation;

import spike.emde.card.model.Exportable;

import java.util.List;
import java.util.Map;

public class AnnotationTest implements Exportable {
    @ToExportField
    String id;
    @ToExportField(exportName = "名称")
    String name;
    @ToExportField(exportName = "性别")
    String sex;

    public AnnotationTest() {
    }

    public AnnotationTest(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public static void main(String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();
        annotationTest.getSchemaList().stream().forEach(System.out::println);

        annotationTest.getSchemaNameMap().forEach((k, v) -> {
                    System.out.println(k + " : " + v);
                }
        );

        AnnotationTest annotationTest1 = new AnnotationTest("1", "LH", "Male");
        Map<String, Object> schemaValueMap = annotationTest1.getSchemaValueMap();
        schemaValueMap.forEach((k, v) -> {
            System.out.println("key:" + k + "   value:" + v);
        });
//        ToExportField[] annotationsByType = AnnotationTest.class.getAnnotationsByType(ToExportField.class);
//         interface spike.emde.annotation.ToExportField
//         System.out.println(ToExportField.class);

/*        Field[] declaredFields = AnnotationTest.class.getDeclaredFields();
        Arrays.asList(declaredFields)
                .forEach(field -> {
            Class type = field.getType();
            String name = field.getName();
            Annotation[] annotations = field.getDeclaredAnnotations();
                    // TODO: 09/01/2017 This is really impressed.
                    // class com.sun.proxy.$Proxy3
            Arrays.stream(annotations).forEach(annotation -> System.out.println(annotation.getClass()));
        });*/

//        Field[] declaredFields = AnnotationTest.class.getDeclaredFields();
        // TODO: Peek needs collect. Or the 'peek' seems not worked.
/*        Arrays.stream(declaredFields)
                .peek(field -> {
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                    System.out.println(declaredAnnotations.length);
            Arrays.stream(declaredAnnotations)
                    .filter(annotation -> annotation.getClass().equals(ToExportField.class))
                    .peek(System.out::println).collect(Collectors.toList());
        }).collect(Collectors.toList());*/
    }

    @Override
    public List<String> toList() {
        return null;
    }
}
