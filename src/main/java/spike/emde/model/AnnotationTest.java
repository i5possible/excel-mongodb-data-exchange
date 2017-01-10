package spike.emde.model;

import java.util.List;

@ToExportClass(exportName = "TEST")
public class AnnotationTest implements Exportable {
    @ToExportField(exportName = "序号", exportIndex = 1 )
    String id;
    @ToExportField(exportName = "名称", exportIndex = 2)
    String name;
    @ToExportField(exportName = "性别", exportIndex = 3)
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
//        Exportable.getSchemaInfoList(AnnotationTest.class).stream().forEach(System.out::println);
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
