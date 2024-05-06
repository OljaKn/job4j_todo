package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleTaskStore implements TaskStore {
    private final SessionFactory sf;

    @Override
    public Task save(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Task WHERE id = :Id")
                    .setParameter("Id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Optional<Task> query = session.createQuery(
                            "FROM Task WHERE id = :id", Task.class)
                    .setParameter("id", id)
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

    @Override
    public List<Task> getAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List<Task> tasks = session.createQuery("FROM Task ORDER BY id", Task.class).list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Task> completedTasks() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List<Task> tasks = session.createQuery("FROM Task WHERE done = true", Task.class).list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Task> newTasks() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            List<Task> tasks = session.createQuery("FROM Task WHERE created > NOW() - INTERVAL '12 hour'", Task.class).list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }
}
