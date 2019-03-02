package reflect;

import java.lang.reflect.Field;

/**
 * @ClassName PropertyUtil
 * @Description
 * @Author chendapeng
 * @Date 2019/3/3
 **/
public class PropertyUtil {

    //person.setXxx(value);
    public static void setProperty(Object obj, String propertyName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        //获取所有属性
        Field field = clazz.getDeclaredField(propertyName);
        //给某个对象赋值
        field.setAccessible(true);
        field.set(obj,value);
    }
}
