package ru.kviak.gson.view;

import ru.kviak.gson.controller.LabelController;
import ru.kviak.gson.util.Launcher;
import java.util.Scanner;

public class LabelView {
   private static final Scanner scanner = new Scanner(System.in);
   private static final LabelController labelController = new LabelController();

   public static void labelResponse(){
      System.out.println("Доступный функционал:\n" +
              "Создание новой метки(label) - new\n" +
              "Просмотр всех существующих меток(label) - all\n" +
              "Просмотр одной метки(label) по уникальному идентификатору(id) - id\n" +
              "Удаление метки(label), по уникальному идентификатору(id) - delete\n" +
              "Обновление метки(label), по уникальному идентификатору(id) - update\n" +
              "Выйти на начальную страницу - exit");


      switch (scanner.nextLine()) {
         case "new" -> labelController.newLabel();
         case "all" -> labelController.getAllLabel();
         case "id" -> labelController.getById();
         case "delete" -> labelController.deleteById();
         case "update" -> labelController.updateById();
         case "exit" -> new Launcher();
         default -> labelResponse();
      }
   }



}
