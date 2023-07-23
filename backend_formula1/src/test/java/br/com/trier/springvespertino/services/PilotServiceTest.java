package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.BaseTest;
import br.com.trier.springvespertino.models.Pilot;
import br.com.trier.springvespertino.models.Race;
import br.com.trier.springvespertino.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
public class PilotServiceTest extends BaseTest {

    @Autowired
    PilotService pilotService;


    @Test
    @DisplayName("Should find by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/pilot.sql"})
    void findById() {
        Pilot pilot = pilotService.findById(1);

        assertEquals("Max Verstappen", pilot.getName());
        assertEquals(1, pilot.getId());
    }

    @Test
    @DisplayName("Should not find by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/pilot.sql"})
    void findByNull() {
        var exception = assertThrows(ObjectNotFound.class, () -> pilotService.findById(3));
        assertEquals("Pilot 3 não existe", exception.getMessage());
    }


    @Test
    @DisplayName("Should list all pilots")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/pilot.sql"})
    void listAll() {
        List<Pilot> list = pilotService.listAll();
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Should be empty list")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void listAllEmpty() {
        var exception = assertThrows(ObjectNotFound.class, () -> pilotService.listAll());
        assertEquals("Nenhum piloto cadastrado", exception.getMessage());
    }


    @Test
    @DisplayName("Should delete pilot by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/pilot.sql"})
    void deleteById() {
        List<Pilot> list = pilotService.listAll();
        assertEquals(2, list.size());

        pilotService.delete(1);

        list = pilotService.listAll();
        assertEquals(1, list.size());
    }

}
