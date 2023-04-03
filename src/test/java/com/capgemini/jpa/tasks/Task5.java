package com.capgemini.jpa.tasks;

import com.capgemini.jpa.configuration.AuditingConfiguration;
import com.capgemini.jpa.entities.Server;
import com.capgemini.jpa.repositories.ServerRepository;
import com.capgemini.jpa.services.ServerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@Import(AuditingConfiguration.class)
class Task5 {

    @Autowired
    private ServerService serverService;

    @MockBean
    private ServerRepository serverRepositoryMock;

    @Test
    void shouldReturnMockServer() throws Exception {
        // given
        String serverName = "dummyName";
        String mockServerName = "Alex";
        String mockServerIp = "noIp";
        Server dummyServer = new Server(mockServerName, mockServerIp);
        whenSerachingForNameReturn(serverName, dummyServer);

        // when
        Optional<Server> result = serverService.findByName(serverName);

        // then
        assertThat(result.isPresent(), Matchers.is(true));
        assertThat(result.get().getName(), Matchers.is(mockServerName));
        assertThat(result.get().getIp(), Matchers.is(mockServerIp));
    }

    private void whenSerachingForNameReturn(String serverName, Server dummyServer) {
        when(serverRepositoryMock.findByName(serverName)).thenReturn(Optional.ofNullable(dummyServer));
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ServerService serverService(ServerRepository serverRepository) {
            return new ServerService(serverRepository);
        }
    }

}
