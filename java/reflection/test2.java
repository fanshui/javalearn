package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 利用反射分析类的能力
 * 检查类的结构
 *
 * Created by fang on 2017/11/29.
 */
public class test2 {
    public static void main(String[] args) {
        Address addr = new Address("beijing","beijing");
        University univer = new University(10086,"BUPT",addr);
        Students s = new Students("superman",19,Sex.boy,addr,"250",true,univer);
        //返回对象所属的类   或使用Class.forName("classname")加载类
        Class cla = s.getClass();
        //SuperClass
        Class supercla = cla.getSuperclass();



        //域 Filed
        Field[] fileds = cla.getDeclaredFields();
        for (Field f : fileds){
            Class type = f.getType();
            String name = f.getName();
            //修饰符 public private ...   类型 int String .. 域名称：Object1
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0){
                System.out.print(modifiers + " ");
            }
            System.out.print

                    (type.getName() + " " + name + ";");
        }



        //方法 methods
        Method[] methods = cla.getDeclaredMethods();
        for (Method m : methods) {
            //修饰符 返回类型 方法名 参数
            Class retType = m.getReturnType();
            String name = m.getName();
            String modifier = Modifier.toString(m.getModifiers());
            if (modifier.length() > 0){
                System.out.print(modifier + " ");
            }
            System.out.print(retType.getName() + " " + name + "(");
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(",");
                System.out.print
                        (paramTypes[i].getName());
            }
            System.out.print(");\n");

        }



        //构造函数
        Constructor[] constructors = cla.getConstructors();
        for (Constructor c : constructors){
            String name = c.getName();
            String modifier = Modifier.toString(c.getModifiers());
            if (modifier.length() > 0) System.out.print(modifier + " ");
            System.out.print(name + "(");

            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(",");
                System.out.print(paramTypes[i].getName());
            }
            System.out.print(");\n");

        }


    }
}
