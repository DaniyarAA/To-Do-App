package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskReader {

    private final Gson gson;

    public TaskReader() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    public List<Task> loadTasks() {
        try (Reader reader = new FileReader("src/data/Tasks.json")) {
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Файл с задачами не найден. Создайте задачи.");
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try (Writer writer = new FileWriter("src/data/Tasks.json")) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении задач: " + e.getMessage());
        }
    }
}
