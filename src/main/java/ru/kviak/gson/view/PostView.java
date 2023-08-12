package ru.kviak.gson.view;

import ru.kviak.gson.controller.PostController;
import ru.kviak.gson.util.Launcher;

import java.util.Scanner;

public class PostView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PostController postController = new PostController();

    public static void postResponse(){
        System.out.println("Доступный функционал:\n" +
                "Создание новой пост - new\n" +
                "Просмотр всех существующих постов - all\n" +
                "Просмотр одного поста по уникальному идентификатору(id) - id\n" +
                "Удаление поста, по уникальному идентификатору(id) - delete\n" +
                "Обновление поста, по уникальному идентификатору(id) - update\n" +
                "Выйти на начальную страницу - exit");

        switch (scanner.nextLine()) {
            case "new" -> postController.newPost();
            case "all" -> postController.getAllPost();
            case "id" -> postController.getById();
            case "delete" -> postController.deleteById();
            case "update" -> postController.updateById();
            case "exit" -> new Launcher();
            default -> postResponse();
        }
    }
}
