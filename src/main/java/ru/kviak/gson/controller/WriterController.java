package ru.kviak.gson.controller;


import ru.kviak.gson.model.Post;
import ru.kviak.gson.model.Writer;
import ru.kviak.gson.repository.GsonPostRepositoryImpl;
import ru.kviak.gson.repository.GsonWriterRepositoryImpl;
import ru.kviak.gson.util.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterController{
    private final Scanner scanner;
    private final GsonPostRepositoryImpl gsonPostRepository;
    private final GsonWriterRepositoryImpl gsonWriterRepository;

    public WriterController(){
        scanner = new Scanner(System.in);
        gsonPostRepository = new GsonPostRepositoryImpl();
        gsonWriterRepository = new GsonWriterRepositoryImpl();
    }

    public void newWriter(){
        System.out.println("Введите id(число) нового писателя: ");
        int id = scanner.nextInt();  // Rework on exception
        scanner.nextLine(); // Without this line, scanner take empty string.
        System.out.println("Введите имя нового писателя: ");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию нового писателя: ");
        String lastName = scanner.nextLine();
        System.out.println("Введите id поста: ");
        int idLabel = scanner.nextInt();
        List<Post> l = new ArrayList<>();
        l.add(gsonPostRepository.getById(idLabel));
        System.out.println(gsonWriterRepository.save(new Writer(id, firstName, lastName, l, Status.ACTIVE)));
    }

    public void getAllWriter(){
        var l = gsonWriterRepository.getAll();
        for (Writer writer:l) {
            System.out.print(writer.getId() + ", " + writer.getFirstName() + ", "+ writer.getLastName()+ ", " + writer.getPosts() + "\n");
        }
    }
    public void getById(){
        System.out.println("Введите id(число): ");
        int id = scanner.nextInt();  // Rework on exception
        var writer = gsonWriterRepository.getById(id);
        if (writer != null) System.out.println(writer.getId() + ", " + writer.getFirstName() + ", " + writer.getLastName() + ", " + writer.getPosts());
        else System.out.println("Не найдено объекта, с таким id");
    }

    public void deleteById(){
        System.out.println("Введите id(число): ");
        int id = scanner.nextInt();  // Rework on exception
        gsonWriterRepository.deleteById(id);
    }

    public void updateById(){
        System.out.println("Введите id(число)  обновляемого писателя: ");
        int id = scanner.nextInt();  // Rework on exception
        scanner.nextLine(); // Without this line, scanner take empty string.
        System.out.println("Введите имя обновляемого писателя: ");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию обновляемого писателя: ");
        String lastName = scanner.nextLine();
        System.out.println("Введите id поста: ");
        int idLabel = scanner.nextInt();
        List<Post> l = new ArrayList<>();
        l.add(gsonPostRepository.getById(idLabel));
        System.out.println(gsonWriterRepository.update(new Writer(id, firstName, lastName, l, Status.ACTIVE)));
    }

}
