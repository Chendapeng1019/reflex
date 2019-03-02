package reflect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @ClassName ReflectDemo02
 * @Description
 * @Author chendapeng
 * @Date 2019/3/3
 **/
public class ReflectDemo02 {
    //获取对象的实例，并操作对象
    public static void demo01() throws IllegalAccessException, InstantiationException {
        //Class入口
        Class perClazz = null;
        try {
            perClazz = Class.forName("reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Person person = (Person) perClazz.newInstance();
        person.setName("张三");
        person.setAge(23);
        System.out.println(person.getName() + "," + person.getAge());
    }

    //操作属性
    public static void demo02() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //Class入口
        Class perClazz = null;
        try {
            perClazz = Class.forName("reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Person person = (Person) perClazz.newInstance();
        Field idfield = null;
        try {
            idfield = perClazz.getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //访问的是private修饰的id,但是private是私有的
        //修改属性的访问权限 使用反射时，如果因为访问修饰符限制造成异常 ，可以通过Field /Method/Constructor.setAccessible(true)
        idfield.setAccessible(true);
        idfield.set(person, 1);  //person.setId(1)

        System.out.println(person.getId());

        System.out.println("========================================================");
        Method method = perClazz.getDeclaredMethod("privateMethod", null);
        method.setAccessible(true);
        method.invoke(person, null);   //方法的调用：invoke()  =person.methodName(); 方法名上面以已指定privateMethod

        System.out.println("========================================================");
        Method method2 = perClazz.getDeclaredMethod("privateMethod2", String.class);
        method2.setAccessible(true);
        method2.invoke(person, "王五");   //方法的调用：invoke()  =person.methodName(); 方法名上面以已指定privateMethod

    }

    //操作构造方法
    public static void demo03() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //Class入口
        Class perClazz = null;
        try {
            perClazz = Class.forName("reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Constructor<?>[] constructors = perClazz.getConstructors(); //公共
//        for (Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }

//        Constructor<?>[] constructors = perClazz.getDeclaredConstructors(); //本类全部构造方法
//        for (Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }

        //获取指定的公有构造方法
        //在反射时，根据类型获取方法时：基本类型（int、char...）和包装类(Integer,Character)
        //有参数
        Constructor<?> constructor = perClazz.getConstructor(int.class);
        System.out.println(constructor);
        Person instance = (Person) constructor.newInstance(12); // 因为constructor是有参构造方法，因为需要传入参数
        System.out.println(instance);
        //无参数
        Constructor<?> constructor2 = perClazz.getConstructor();
        System.out.println(constructor2);
        Person instance2 = (Person) constructor2.newInstance();  //  因为constructor2是无参构造方法，因为不需要传入参数
        System.out.println(instance2);

        //获取指定的私有的构造方法
        Constructor<?> constructor3 = perClazz.getDeclaredConstructor(String.class);
        System.out.println(constructor3);
        constructor3.setAccessible(true);
        Person person = (Person) constructor3.newInstance("zs");
        System.out.println("person" + person);

    }

    //动态加载类名 和 方法
    //操作构造方法
    public static void demo04() throws IllegalAccessException, InstantiationException, IOException, NoSuchMethodException, InvocationTargetException {

        Properties prop = new Properties();
        prop.load(new FileReader("G:\\Java\\newReflex\\src\\class.txt"));

        String classname = prop.getProperty("classname");
        String methodname = prop.getProperty("methodname");
        Class perClazz = null;
        try {
            perClazz = Class.forName(classname);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method method = perClazz.getMethod(methodname);
        method.invoke(perClazz.newInstance());
    }

    //反射可以越过泛型检查
    public static void demo05() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(3);
//        list.add("zs");

        Class<?> listClazz = list.getClass();
        Method method = listClazz.getMethod("add", Object.class);
        method.invoke(list, "zs....");
        System.out.println(list);
    }

    public static void demo06() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        PropertyUtil.setProperty(person,"name","zs");
        PropertyUtil.setProperty(person,"age",23);
        Student stu = new Student();
        PropertyUtil.setProperty(stu,"name","jack");

        System.out.println(person.getName()+","+person.getAge());
        System.out.println(stu.getName());
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IOException, InvocationTargetException, NoSuchFieldException {
//        demo01();
//        demo02();
//        demo03();
        demo04();
//        demo05();
        demo06();
    }
}
