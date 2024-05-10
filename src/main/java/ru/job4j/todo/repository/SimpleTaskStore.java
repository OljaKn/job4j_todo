package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleTaskStore implements TaskStore {
    private final CrudRepository crudRepository;

    @Override
    public Task save(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    @Override
    public boolean update(Task task) {
       /* boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery("UPDATE Task SET title = :title, description = :description, done = :done WHERE id = :id")
                    .setParameter("title", task.getTitle())
                    .setParameter("description", task.getDescription())
                    .setParameter("done", task.isDone())
                    .setParameter("id", task.getId())
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }*/
        return crudRepository.tx(session -> session.createQuery(
                        "UPDATE Task SET title = :title, description = :description, done = :done WHERE id = :id")
                .setParameter("title", task.getTitle())
                .setParameter("description", task.getDescription())
                .setParameter("done", task.isDone())
                .setParameter("id", task.getId())
                .executeUpdate() > 0);
    }

    @Override
    public boolean updateStatus(int id) {
        /*boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
           rsl = session.createQuery(
                            "UPDATE Task SET done = true WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;*/
        return crudRepository.tx(session -> session.createQuery(
                        "UPDATE Task SET done = true WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate() > 0);
    }

    @Override
    public boolean delete(int id) {
        /*boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                            "DELETE Task WHERE id = :Id")
                    .setParameter("Id", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;*/
        return crudRepository.tx(session -> session.createQuery(
                        "DELETE Task WHERE id = :Id")
                .setParameter("Id", id)
                .executeUpdate() > 0);
    }

    @Override
    public Optional<Task> findById(int id) {
        /*Session session = sf.openSession();
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
        }*/
        return crudRepository.optional("FROM Task WHERE id = :id", Task.class,
                Map.of("id", id));
    }

    @Override
    public List<Task> getAll() {
        /*Session session = sf.openSession();
        try {
            session.beginTransaction();
            List<Task> tasks = session.createQuery("FROM Task ORDER BY id", Task.class).list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }*/
        return crudRepository.query("FROM Task ORDER BY id", Task.class);
    }

    @Override
    public List<Task> completedTasks(boolean b) {
        /*Session session = sf.openSession();
        try {
            session.beginTransaction();
            List<Task> tasks = session.createQuery("FROM Task WHERE done = :d", Task.class)
                    .setParameter("d", b).list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }*/
        return crudRepository.query("FROM Task WHERE done = :d", Task.class,
                Map.of("d", b));
    }

}
