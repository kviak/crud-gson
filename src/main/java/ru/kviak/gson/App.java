package ru.kviak.gson;

import ru.kviak.gson.model.Label;
import ru.kviak.gson.model.Post;
import ru.kviak.gson.model.Writer;
import ru.kviak.gson.repository.GsonWriterRepositoryImpl;
import ru.kviak.gson.util.Status;

import java.util.ArrayList;
import java.util.List;


public class App
{
    public static void main( String[] args ){
        Label label = new Label();
        List<Label> list = new ArrayList<>();
        list.add(label);
        Post post = new Post(1, "1", list, Status.ACTIVE);
        List<Post> listPost = new ArrayList<>();
        listPost.add(post);
        GsonWriterRepositoryImpl gsonWriterRepository = new GsonWriterRepositoryImpl();
        Writer writer = new Writer(3, "Ivan", "Ivanov", listPost, Status.ACTIVE);

        //gsonWriterRepository.newFile(writer);

        //gsonWriterRepository.save(writer);
        System.out.println(gsonWriterRepository.getById(4));
        //System.out.println(gsonWriterRepository.getAll());


    }
}
