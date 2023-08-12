package ru.kviak.gson.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.kviak.gson.model.Label;
import ru.kviak.gson.util.Status;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonLabelRepository implements LabelRepository {

    public void newFile(Label label){
        try {
            List<Label> l = new ArrayList<>();
            l.add(label);
            java.io.Writer jsonWriter = new FileWriter("labels.json");
            new Gson().toJson(l, jsonWriter);
            jsonWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reSave(List<Label> list) {
        try {
            java.io.Writer jsonWriter = new FileWriter("labels.json");
            new Gson().toJson(list, jsonWriter);
            jsonWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Label getById(Integer id) {
        var l = getAll();
        for(Label label: l){
            if (label.getId() == id && label.getStatus() != Status.DELETED) return label;
        }
        return null;
    }

    @Override
    public List<Label> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Label>>() { }.getType();
        List<Label> listLabels;
        try {
            listLabels = new Gson().fromJson(new FileReader("labels.json"), targetClassType);

            List<Label> withoutDel = new ArrayList<>();
            for (Label w: listLabels) {

                if (w.getStatus() != Status.DELETED)
                    withoutDel.add(w);
            }

            return withoutDel;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Label save(Label label) {
        var l = getAll();
        l.add(label);
        reSave(l);
        return label;
    }

    @Override
    public Label update(Label label) {
        var l = getAll();
        for (Label label1: l) {
            if (label1.getId() == label.getId()){
                label1.setName(label.getName());
                reSave(l);
                return label1;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        var l = getAll();
        for (Label label: l) {
            if (label.getId() == id) label.setStatus(Status.DELETED);
        }
        reSave(l);
    }
}
