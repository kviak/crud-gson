package ru.kviak.gson.view;

import ru.kviak.gson.controller.WriterController;
import ru.kviak.gson.util.Launcher;

import java.util.Scanner;

public class WriterView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final WriterController writeController = new WriterController();

    public static void writerResponse(){
        System.out.println("Доступный функционал:\n" +
                "Создание новой пост - new\n" +
                "Просмотр всех существующих постов - all\n" +
                "Просмотр одного поста по уникальному идентификатору(id) - id\n" +
                "Удаление поста, по уникальному идентификатору(id) - delete\n" +
                "Обновление поста, по уникальному идентификатору(id) - update\n" +
                "Выйти на начальную страницу - exit");

        switch (scanner.nextLine()) {
            case "new" -> writeController.newWriter();
            case "all" -> writeController.getAllWriter();
            case "id" -> writeController.getById();
            case "delete" -> writeController.deleteById();
            case "update" -> writeController.updateById();
            case "exit" -> new Launcher();
            default -> writerResponse();
        }
    }
}
