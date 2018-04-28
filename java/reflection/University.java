package reflection;

/**
 * Created by fang on 2017/11/29.
 */
public class University {
    private int university_id;
    private String fullname;
    private Address address;

    public University(int university_id, String fullname, Address address) {
        this.university_id = university_id;
        this.fullname = fullname;
        this.address = address;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
