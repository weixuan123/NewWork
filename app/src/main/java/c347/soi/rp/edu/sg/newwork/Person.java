package c347.soi.rp.edu.sg.newwork;

import io.realm.RealmObject;

/**
 * Created by 14056864 on 13/3/2017.
 */
public class Person extends RealmObject {
    private String name;
    private int age;

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
}
