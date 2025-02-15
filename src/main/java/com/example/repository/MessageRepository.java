package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    boolean existsByPostedBy(int postedBy);

    List<Message> findAllByPostedBy(int postedBy);
}
