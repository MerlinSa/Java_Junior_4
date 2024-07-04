package ru.saikalb.homework;

import jakarta.persistence.*;
import ru.saikalb.lesson4.User;

@Entity
@Table(name = "Student")
public class Student {
    public Student() {
    }

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "fName")
    private String fName;
    @Column(name = "lName")
    private String lName;

    @Column(name = "group_id")
    private long group_id;


    public Student(long id, String fName, String lName, long group_id) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.group_id = group_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", group_id=" + group_id +
                '}';
    }
}
