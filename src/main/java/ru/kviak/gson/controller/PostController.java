package ru.kviak.gson.controller;

import ru.kviak.gson.model.Label;
import ru.kviak.gson.model.Post;
import ru.kviak.gson.repository.GsonLabelRepository;
import ru.kviak.gson.repository.GsonPostRepositoryImpl;
import ru.kviak.gson.util.Status;

import java.util.ArrayList;
import java.util.Scanner;

public class PostController {

    private final GsonPostRepositoryImpl gsonPostRepository;
    private final GsonLabelRepository gsonLabelRepository;
    private final Scanner scanner;

    public PostController(){
        gsonPostRepository = new GsonPostRepositoryImpl();
        scanner = new Scanner(System.in);
        gsonLabelRepository = new GsonLabelRepository();
    }

    public void newPost(){

        System.out.println("Введите id(число) нового поста: ");
        int id = scanner.nextInt();  // Rework on exception
        scanner.nextLine(); // Without this line, scanner take empty string.
        System.out.println("Введите название нового поста");
        String content = scanner.nextLine();
        System.out.println("Введите id(число) списка меток(label): ");
        int labelId = scanner.nextInt();  // Rework on exception

        System.out.println(gsonPostRepository.save(new Post(id, content, gsonLabelRepository.getAll(),Status.ACTIVE)));
    }

    public void getAllPost(){
        var l = gsonPostRepository.getAll();
        for (Post post:l) {
            System.out.print(post.getId() + ", " + post.getContent() + ", "+ post.getCreated()+ ", " + post.getLabels() + "\n");
        }
    }

    public void getById(){
        System.out.println("Введите id(число): ");
        int id = scanner.nextInt();  // Rework on exception
        var post = gsonPostRepository.getById(id);
        if (post != null) System.out.println(post.getId() + ", " + post.getContent() + ", " + post.getCreated());
        else System.out.println("Не найдено объекта, с таким id");
    }

    public void deleteById(){
        System.out.println("Введите id(число): ");
        int id = scanner.nextInt();  // Rework on exception
        gsonPostRepository.deleteById(id);
    }

    public void updateById(){
        System.out.println("Введите id(число) обновляемого поста: ");
        int id = scanner.nextInt();  // Rework on exception
        scanner.nextLine(); // Without this line, scanner take empty string.
        System.out.println("Введите контент нового поста");
        String content = scanner.nextLine();
        gsonPostRepository.update(new Post(id, content, gsonLabelRepository.getAll(), Status.ACTIVE));
    }
}
