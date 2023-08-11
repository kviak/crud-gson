package ru.kviak.gson.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.kviak.gson.model.Writer;
import ru.kviak.gson.util.Status;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class GsonWriterRepositoryImpl implements WriterRepository{
    private List<Writer> list = new ArrayList<>();
    private final Gson gson = new Gson();
    public void setList(List<Writer> list) {
        this.list = list;
    }

    public Writer getById(Integer integer) {
        var listWriter = getAll();

        for (Writer writer: listWriter) {
            if (writer.getId() == integer && writer.getStatus() != Status.DELETED) return writer;
        }
        return null;
    }

    public List<Writer> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Writer>>() { }.getType();
        List<Writer> listWriter = null;
        try {
            listWriter = new Gson().fromJson(new FileReader("list.json"), targetClassType);
            return listWriter;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Writer save(Writer writer) {
        try {
            // create a list of users
            var l = getAll();
            l.add(writer);

            // create writer
            java.io.Writer jsonWriter = new FileWriter("list.json");

            new Gson().toJson(l, jsonWriter);

            // close writer
            jsonWriter.close();
            return writer;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void newFile(Writer writer) {
        try {
            // create a list of users
            List<Writer> list1 = new ArrayList<>();
            list1.add(writer);

            // create writer
            java.io.Writer jsonWriter = new FileWriter("list.json");

            new Gson().toJson(list1, jsonWriter);

            // close writer
            jsonWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public Writer update(Writer writer) {
        for (Writer wr: list) {
            if (writer.getId() == wr.getId()){
                wr = writer;
                return wr;}
        }
        return null;
    }

    public void deleteById(Integer integer) {
        for (Writer writer: list) {
            if (writer.getId() == integer) writer.setStatus(Status.DELETED);
        }
    }
}
