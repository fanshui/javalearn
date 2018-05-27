package objectlistener;

import java.util.Observable;

/**
 * 使用观察者模式
 * 继承 Observable类
 * 触发通知要先调用 setChanged()
 * 观察者支持推和拉模型
 */

/**
 * Person : 被观察的目标具体实现类
 */
public class Person extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //通知观察者
        //通知之前必须调用setChanged();
        this.setChanged();
        //主动通知
        //推 只传需要的属性
        this.notifyObservers(name);
        //拉 把自己对象本身传过去
//        this.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }
}
