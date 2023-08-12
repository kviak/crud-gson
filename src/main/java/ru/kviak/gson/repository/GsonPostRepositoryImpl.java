package ru.kviak.gson.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.kviak.gson.model.Post;
import ru.kviak.gson.util.Status;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {

    public void newFile(Post post) {
        try {
            List<Post> l = new ArrayList<>();
            l.add(post);
            java.io.Writer jsonWriter = new FileWriter("posts.json");
            new Gson().toJson(l, jsonWriter);
            jsonWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Post> getAll() {
        Type targetClassType = new TypeToken<ArrayList<Post>>() { }.getType();
        List<Post> listPost;
        try {
            listPost = new Gson().fromJson(new FileReader("posts.json"), targetClassType);

            List<Post> withoutDel = new ArrayList<>();
            for (Post p: listPost) {

                if (p.getStatus() != Status.DELETED)
                    withoutDel.add(p);
            }
            return withoutDel;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void reSave(List<Post> list) {
        try {
            java.io.Writer jsonWriter = new FileWriter("posts.json");
            new Gson().toJson(list, jsonWriter);
            jsonWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Post getById(Integer id){

        for (Post p: getAll()) {
            if (p.getId() == id && p.getStatus() != Status.DELETED)
                return p;
        }
        return null;
    }

    public Post save(Post post){
        var l = getAll();
        l.add(post);
        reSave(l);
        return post;
    }

    public Post update(Post post){
        var l = getAll();
        for (Post post1: l) {
            if (post1.getId() == post.getId()){
                post1.setContent(post.getContent());
                post1.setUpdated(post.getUpdated());
                post1.setStatus(post.getStatus());
                post1.setLabels(post.getLabels());

                reSave(l);
                return post;
            }
        }
        return null;
    }

    public void deleteById(Integer id){
        var l = getAll();
        for (Post post: l) {
            if (post.getId() == id) post.setStatus(Status.DELETED);
        }
        reSave(l);
    }
}
