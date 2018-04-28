package reflection;

/**
 * Created by fang on 2017/11/29.
 */
public class Address {
    private String province;
    private String city;
    public Address(){
        province = "no";
        city = "no";
    }
    public Address(String province,String city){
        this.city = city;
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    @Override
    public String toString() {
        return "Province : " + this.province + "     City : " + city;
    }
}
