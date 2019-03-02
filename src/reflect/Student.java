package reflect;

/**
 * @ClassName Student
 * @Description
 * @Author chendapeng
 * @Date 2019/3/3
 **/
public class Student {
    private String name;


    public void sayHi(){
        System.out.println("I am s student.....");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
