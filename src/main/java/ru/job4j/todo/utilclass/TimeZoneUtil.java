package ru.job4j.todo.utilclass;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class TimeZoneUtil {
    public static List<Task> timeZoneList(User user, List<Task> tasks) {
        for (Task task : tasks) {
            LocalDateTime createdDateTime = task.getCreated().atZone(ZoneId.of("UTC"))
                    .withZoneSameInstant(ZoneId.of(user.getTimezone())).toLocalDateTime();
            task.setCreated(createdDateTime);
        }
        return tasks;
    }
    public static List<TimeZone> getTimeZones() {
        List<TimeZone> timeZones = new ArrayList<>();
        String[] availableIds = TimeZone.getAvailableIDs();
        for (String id : availableIds) {
            timeZones.add(TimeZone.getTimeZone(id));
        }
        return timeZones;
    }
}
