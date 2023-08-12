package ru.kviak.gson.controller;

import ru.kviak.gson.model.Label;
import ru.kviak.gson.repository.GsonLabelRepository;
import ru.kviak.gson.util.Status;

import java.util.Scanner;

public class LabelController {
    private final GsonLabelRepository gsonLabelRepository;
    private final Scanner scanner;
    public LabelController(){
        gsonLabelRepository = new GsonLabelRepository();
        scanner = new Scanner(System.in);
    }

    public void newLabel(){

            System.out.println("Введите id(число) новой метки: ");
            int id = scanner.nextInt();  // Rework on exception
            scanner.nextLine(); // Without this line, scanner take empty string.
            System.out.println("Введите название новой метки");
            String name = scanner.nextLine();

            System.out.println(gsonLabelRepository.save(new Label(id, name, Status.ACTIVE)));
    }

    public void getAllLabel(){
        var l = gsonLabelRepository.getAll();
        for (Label label:l) {
            System.out.print(label.getId() + ", " + label.getName() + "\n");
        }
    }

    public void getById(){
        System.out.println("Введите id(число): ");
        int id = scanner.nextInt();  // Rework on exception
        var label = gsonLabelRepository.getById(id);
        if (label != null) System.out.println(label.getId() + ", " + label.getName());
            else System.out.println("Не найдено объекта, с таким id");
    }

    public void deleteById(){
        System.out.println("Введите id(число): ");
        int id = scanner.nextInt();  // Rework on exception
        gsonLabelRepository.deleteById(id);
    }

    public void updateById(){
        System.out.println("Введите id(число) новой метки: ");
        int id = scanner.nextInt();  // Rework on exception
        scanner.nextLine(); // Without this line, scanner take empty string.
        System.out.println("Введите название новой метки");
        String name = scanner.nextLine();
        gsonLabelRepository.update(new Label(id, name, Status.ACTIVE));
    }


}
