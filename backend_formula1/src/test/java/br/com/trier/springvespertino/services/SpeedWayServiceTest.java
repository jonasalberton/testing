package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.BaseTest;
import br.com.trier.springvespertino.models.Speedway;
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
public class SpeedWayServiceTest  extends BaseTest {

    @Autowired
    SpeedwayService speedwayService;

    @Test
    @DisplayName("Should not find speedway")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/speedway.sql"})
    void findByNull() {
        var exception = assertThrows(ObjectNotFound.class, () -> speedwayService.findById(3));
        assertEquals("Pista 3 não existe", exception.getMessage());
    }

    @Test
    @DisplayName("Should find by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/speedway.sql"})
    void findById() {
        Speedway speedway = speedwayService.findById(2);
        assertEquals("Monza", speedway.getName());
    }

    @Test
    @DisplayName("Should list all speedways")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/speedway.sql"})
    void listAll() {
        List<Speedway> list = speedwayService.listAll();
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Should return exception empty list")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void listEmptyList() {
        var exception = assertThrows(ObjectNotFound.class, () -> speedwayService.listAll());
        assertEquals("Nenhuma pista cadastrada", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/speedway.sql"})
    void deleteById() {
        List<Speedway> list = speedwayService.listAll();
        assertEquals(2, list.size());

        speedwayService.delete(2);
        list =  speedwayService.listAll();
        assertEquals(1, list.size());
    }


    @Test
    @DisplayName("Should find track by size Between")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/speedway.sql"})
    void findBySizeBetween() {
        List<Speedway> list = speedwayService.findBySizeBetween(1200, 1501);
        assertEquals(1, list.size());
        assertEquals("Interlagos", list.get(0).getName());

        var exception = assertThrows(ObjectNotFound.class, () -> speedwayService.findBySizeBetween(1501, 1504));
        assertEquals("Nenhuma pista cadastrada com essas medidas", exception.getMessage());
    }
}
