package objectlistener;

import java.util.Observable;
import java.util.Observer;

public class PersonObserver implements Observer {

    //观察的名称
    private String observerName;

    /**
     *
     * @param o 拉方式 传的被观察对象的引用
     * @param arg 推方式 传的被观察对象的属性
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(observerName + " 收到： " + arg);
//        System.out.println(observerName + "拿到目标对象的引用 ： " + (Person)o);
    }

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }
}
