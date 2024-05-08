package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;
import javax.servlet.http.HttpServletRequest;


@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "tasks/list";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model) {
        try {
            taskService.save(task);
            return "redirect:/tasks";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model) {
        model.addAttribute("tasks", taskService.completedTasks(true));
        return "tasks/completed";
    }

    @GetMapping("/new")
    public String getNewTasks(Model model) {
        model.addAttribute("tasks", taskService.completedTasks(false));
        return "tasks/new";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id, HttpServletRequest request) {
        var task = taskService.findById(id);
        model.addAttribute("task", task.get());
        var session = request.getSession();
        session.setAttribute("task", task.get());
        return "tasks/one";
    }

   @GetMapping("isDone/{id}")
   public String doneTask(@PathVariable int id) {
        var task = taskService.findById(id).get();
        task.setDone(true);
        taskService.update(task);
        return "redirect:/tasks";
   }

    @GetMapping("/delete/{id}")
    public String deletedTask(Model model, @PathVariable int id) {
        var isDeleted = taskService.delete(id);
        if (!isDeleted) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/update/{id}")
    public String getUpdatedPage(Model model, @PathVariable int id) {
        var task = taskService.findById(id).get();
        model.addAttribute("task", task);
        return "tasks/updated";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/tasks";
    }
}