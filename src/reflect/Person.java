package reflect;

/**
 * @ClassName Person
 * @Description
 * @Author chendapeng
 * @Date 2019/3/2
 **/
public class Person implements MyInterface,MyInterface2 {

    private int id;
    private String name;
    private int age;

    public String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    @Override
    public void interfaceMethod() {
        System.out.println("interface  Method......");
    }

    public static void staticMethod(){
        System.out.println("static method.....");
    }

    private void privateMethod(){
        System.out.println("private method.....");
    }

    private void privateMethod2(String name){
        System.out.println("private method....."+name);
    }


    @Override
    public void intface2Method() {
        System.out.println("interface2 Method.....");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
