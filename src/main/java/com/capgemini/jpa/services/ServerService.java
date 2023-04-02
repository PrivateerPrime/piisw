package com.capgemini.jpa.services;

import com.capgemini.jpa.entities.Server;
import com.capgemini.jpa.repositories.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ServerService {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public Optional<Server> findByName(String name) {
        return serverRepository.findByName(name);
    }

}
