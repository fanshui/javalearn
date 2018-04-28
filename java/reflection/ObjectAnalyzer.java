package reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 *
 * 类分析器
 * Created by fang on 2017/11/29.
 */
public class ObjectAnalyzer {
    public String toString(Object obj) {
        Class cl = obj.getClass();
        String r = cl.getName();
        do {
            r += "[";
            Field[] fields = cl.getFields();
            AccessibleObject.setAccessible(fields,true);
            for (Field f : fields)
            {
                if(!Modifier.isStatic(f.getModifiers())){
                    if(!r.endsWith("[")) r += ",";
                    r += f.getName() + "=";
                    try {
                        Object val =  f.get(obj);
                        r += toString(val);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
//                Arrays;
            }
            r += "]";
            cl = cl.getSuperclass();
        }while(cl != null);
        return r;
    }
}
