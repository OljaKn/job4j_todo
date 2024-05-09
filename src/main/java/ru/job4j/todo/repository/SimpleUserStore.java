package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;
@Repository
@AllArgsConstructor
public class SimpleUserStore implements UserStore {
    private final SessionFactory sf;

    @Override
    public Optional<User> save(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Optional<User> query = session.createQuery(
                            "FROM User WHERE login = :login AND password = :pass", User.class)
                    .setParameter("login", login)
                    .setParameter("pass", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return query;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
