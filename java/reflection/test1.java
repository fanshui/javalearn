package reflection;

/**
 *
 * Created by fang on 2017/11/26.
 */
public class test1 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Address addr = new Address("beijing","beijing");
        University univer = new University(10086,"BUPT",addr);
        Students s = new Students("superman",19,Sex.boy,addr,"250",true,univer);
        System.out.println("default toString : " + s);
        System.out.println("toString() : " + new ObjectAnalyzer().toString(s));
        try {
            Object a = Class.forName("reflection.Address").newInstance(); // 手工加载类
            System.out.println(a);
            System.out.println(a.getClass().getName()); //Class.getName() 类型名 int float 也适用
            System.out.println(a.getClass());
            System.out.println(a.getClass().getPackage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(s.getClass());
        System.out.println(addr);

        System.out.println();
        System.out.println();
        System.out.println();

    }
}
