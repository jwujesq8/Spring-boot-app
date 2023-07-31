package com.s473618.services;

import org.springframework.stereotype.Service;
import com.s473618.entities.ReadBooks;

import java.util.List;
import java.util.Optional;

@Service
public interface ReadService {
    List<ReadBooks> listAllRead();

    Optional<ReadBooks> getReadById(Integer id);

    ReadBooks saveRead(ReadBooks read);

    void deleteRead(Integer id);

    Boolean checkIfReadExist(Integer id);
}
