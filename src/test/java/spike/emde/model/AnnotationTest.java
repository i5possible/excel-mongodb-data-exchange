package spike.emde.model;

@Title(name = "TEST")
public class AnnotationTest extends AbstractExportable {
    @Column(name = "序号", index = 1 )
    String id;
    @Column(name = "名称", index = 2)
    String name;
    @Column(name = "性别", index = 3)
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
//          TODO: 09/01/2017 This is really impressed:
//          class com.sun.proxy.$Proxy3
//          TODO: Peek needs collect. Or the 'peek' seems not worked.
    }
}
