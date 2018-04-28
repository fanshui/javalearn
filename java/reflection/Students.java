package reflection;

/**
 * Created by fang on 2017/11/29.
 */
public class Students {
    private String name;
    private int age;
    private Sex sex;
    private Address address;
    private String tel_num;
    private Boolean alive;
    private University university;

    public Students(String name, int age, Sex sex, Address address, String tel_num, Boolean alive,University university) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.tel_num = tel_num;
        this.alive = alive;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTel_num() {
        return tel_num;
    }


    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void setTel_num(String tel_num) {
        this.tel_num = tel_num;
    }

    public void jump(){
        this.alive = false;
    }
    public String exam(){
        this.alive = false;
        return "Please Kill me ！OMG! ";
    }
}
