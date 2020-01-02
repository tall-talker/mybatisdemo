package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.entity.Notify;
import com.example.mybatisdemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyMapper {
    List<Notify> listNotifies();

    List<Notify> findNotifyByTitle(String title);

    List<Notify> findNotifyByAuthor(String author);

    //title, content, author, publishDate
    void insertNotify(String title, String content, String author, String publishDate);

    void deleteNotifyForTitle(String title);

    void updateNotifyForTitle(String title, String content);
}
