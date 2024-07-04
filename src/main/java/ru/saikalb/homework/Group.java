package ru.saikalb.homework;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "groups")
public class Group {
    public Group(long l, String a1) {
    }
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "gName")
    private String gName;
    @Column(name = "description")
    private String description;

    public Group(long id, String gName, String description) {
        this.id = id;
        this.gName = gName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return gName;
    }

    public void setName(String name) {
        this.gName = name;
    }


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", gName='" + gName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
