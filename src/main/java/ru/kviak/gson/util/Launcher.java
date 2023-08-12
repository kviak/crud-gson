package ru.kviak.gson.util;

import ru.kviak.gson.view.LabelView;
import ru.kviak.gson.view.PostView;

import java.util.Scanner;

public class Launcher {
    private final Scanner scanner = new Scanner(System.in);

    public Launcher(){
        start();
    }

    public void start() {
        System.out.println("Выберите с каким элементом вы хотите работать: Labels, Posts, Writers");
        while (true){
        switch (scanner.nextLine()) {
            case "Labels" -> LabelView.labelResponse();
            case "Posts" -> PostView.postResponse();
            case "Writers" -> System.out.println("Writers interface");
        }
        }
    }
}
