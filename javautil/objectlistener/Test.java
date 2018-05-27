package objectlistener;

public class Test {
    public static void main(String[] args) {
        //创建被观察目标
        Person person = new Person();

        //创建观察者
        PersonObserver o1 = new PersonObserver();
        o1.setObserverName("o1");
        PersonObserver o2 = new PersonObserver();
        o2.setObserverName("o2");

        //注册观察者
        person.addObserver(o1);
        person.addObserver(o2);

        //目标更新
        person.setName("改名 ： 李白");

    }
}
