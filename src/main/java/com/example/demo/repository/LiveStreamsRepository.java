package com.example.demo.repository;

import com.example.demo.exception.LiveStreamNotFoundException;
import com.example.demo.model.LiveStream;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class LiveStreamsRepository {

    List<LiveStream> streams = new ArrayList<>();

    public LiveStreamsRepository() {
        streams.add(new LiveStream(
                UUID.randomUUID().toString(),
                "Build REST APIs",
                "Build Rest APIs with Spring Boot",
                "https://www.twtich.tv",
                LocalDateTime.of(2022, 8, 17, 13, 45),
                LocalDateTime.of(2022, 8, 17, 15, 45)
        ));
    }

    public List<LiveStream> findAll() {
        return streams;
    }

    public LiveStream findById(String id) throws LiveStreamNotFoundException {
        return streams.stream().filter(stream -> stream.id().equals(id)).findFirst().orElseThrow(LiveStreamNotFoundException::new);
    }

    public LiveStream create(LiveStream stream) {
        streams.add(stream);
        return stream;
    }

    public void update(LiveStream stream, String id) {
        LiveStream existing = streams.stream().filter(s -> s.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stream not found"));
        int i = streams.indexOf(existing);
        streams.set(i, stream);
    }

    public void delete(String id) {
        streams.removeIf(stream -> stream.id().equals(id));
    }
}
