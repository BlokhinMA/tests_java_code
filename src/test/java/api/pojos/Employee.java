package api.pojos;

import java.util.Date;

public class Employee {

    private String name;
    private String job;
    private Date updatedAt;

    public Employee() {

    }

    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
