package single;
//双检锁/双重校验锁（DCL，即 double-checked locking）安全且在多线程情况下能保持高性能。
public class Singleton2 {

    private volatile static Singleton2 singleton;

    private Singleton2() { }

    public static Singleton2 getInstance() {
        if (singleton == null) {
            synchronized (Singleton2.class) {
                if (singleton == null) {
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }

}
