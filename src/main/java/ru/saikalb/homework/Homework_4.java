package ru.saikalb.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.saikalb.lesson4.Pet;
import ru.saikalb.lesson4.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Перенести структуру дз третьего урока на JPA:
 * 1. Описать сущности Student и Group
 * 2. Написать запросы: Find, Persist, Remove
 * 3. ... поупражняться с разными запросами ...
 */

public class Homework_4 {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "root", "root")) {
            // prepareTables(connection);
            run(connection);
        }
    }

    private static void prepareTables(Connection connection) throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute("""
                    create table groups(
                      id bigint,
                      gName varchar(256),
                      description varchar(256)
                    )
                    """);
        }

        try (Statement st = connection.createStatement()) {
            st.execute("""
                    insert into groups(id, gName, description) values
                      (1, 'group#1', 'summer'),
                      (2, 'group#2', 'winter'),
                      (3, 'group#3', 'spring')
                    """);
        }

    }

    private static void run(Connection connection) throws SQLException {

        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                createStudent(session);
                createGroup(session);
                updateStudent(session, 1L);
                find(session);
                remove(session, 1L);
                selectAll(session);
            }
        }
    }


    private static void createGroup(Session session) {
        Group group = new Group(123L, "group#1","summerCamp");
        Group group2 = new Group(124L, "group#2","winterCamp");

        session.beginTransaction();
        session.persist(group);
        session.persist(group2);
        session.getTransaction().commit();
        System.out.println("Добавление группы: " + group + "\n Зимняя группа " + group2);
    }

    private static void createStudent(Session session) {
        Student student = new Student(1L, "Sally", "Jasper", 123L);
        Student student2 = new Student(2L, "Selena", "Casper", 124L);
        session.beginTransaction();
        session.persist(student);
        session.persist(student2);
        session.getTransaction().commit();
        System.out.println("Добавление студента: " + student + "\n" + student2);
    }

    private static void updateStudent(Session session, Long id) {
        Student updatedStudent = selectStudentByID(session, id);
        updatedStudent.setfName("Janna");
        updatedStudent.setlName("Johns");
        Transaction tx = session.beginTransaction();
        session.merge(updatedStudent);
        tx.commit();
        System.out.println("Обновление студента: " + selectStudentByID(session, id));
    }

    private static Student selectStudentByID(Session session, Long id) {
        return session.find(Student.class, id);
    }

    static void find(Session session) {
        Student student = session.find(Student.class, 1L);
        System.out.println("Найти студента: " + student); // Find
    }

    static void remove(Session session, Long id) {
        Student student = selectStudentByID(session, id);
        Transaction tx = session.beginTransaction();
        session.remove(student); // Remove
        tx.commit();
        System.out.println("Удаление студента:" + student);
    }

    /**
     * 3. ... поупражняться с разными запросами ...
     */
    private static void selectAll(Session session) {
        System.out.println("список студентов и групп: ");
        List<Group> groups = session.createQuery("FROM Group", Group.class).getResultList();
        groups.forEach(System.out::println);

        List<Student> students = session.createQuery("FROM Student", Student.class).getResultList();
        students.forEach(System.out::println);
    }
}



