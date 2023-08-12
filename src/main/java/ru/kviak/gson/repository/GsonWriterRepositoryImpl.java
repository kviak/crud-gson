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


    public Writer getById(Integer integer) {
        var l = getAll();

        for (Writer writer: l) {
            if (writer.getId() == integer && writer.getStatus() != Status.DELETED) return writer;
        }

        return null;
    }

    public List<Writer> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Writer>>() { }.getType();
        List<Writer> listWriter;
        try {
            listWriter = new Gson().fromJson(new FileReader("writer.json"), targetClassType);

            List<Writer> withoutDel = new ArrayList<>();
            for (Writer w: listWriter) {

                if (w.getStatus() != Status.DELETED)
                    withoutDel.add(w);
            }

            return withoutDel;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Writer save(Writer writer) {
        try {

            var l = this.getAll();
            l.add(writer);
            java.io.Writer jsonWriter = new FileWriter("writer.json"); // CREATE WRITER PREV WRITE
            new Gson().toJson(l, jsonWriter);
            jsonWriter.close();

            return writer;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void reSave(List<Writer> list) {
        try {
            java.io.Writer jsonWriter = new FileWriter("writer.json");
            new Gson().toJson(list, jsonWriter);
            jsonWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Writer update(Writer writer) {
        var l = getAll();
        for (Writer wr: l) {
            if (writer.getId() == wr.getId()){
                wr.setLastName(writer.getLastName());
                wr.setFirstName(writer.getFirstName());
                wr.setPosts(writer.getPosts());

                reSave(l);
                return wr;
            }
        }
        return null;
    }

    public void deleteById(Integer id) {
        var l = getAll();
        for (Writer writer: l) {
            if (writer.getId() == id) writer.setStatus(Status.DELETED);
        }
        reSave(l);
    }

    public void newFile(Writer writer) {
        try {
            List<Writer> l = new ArrayList<>();
            l.add(writer);
            java.io.Writer jsonWriter = new FileWriter("writer.json");
            new Gson().toJson(l, jsonWriter);
            jsonWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
