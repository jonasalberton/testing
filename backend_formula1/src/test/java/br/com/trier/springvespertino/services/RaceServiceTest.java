package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.BaseTest;
import br.com.trier.springvespertino.models.Race;
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
public class RaceServiceTest extends BaseTest {

    @Autowired
    RaceService raceService;

    @Autowired
    SpeedwayService speedwayService;

    @Test
    @DisplayName("Should not find race")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/race.sql"})
    void findByNull() {
        var exception = assertThrows(ObjectNotFound.class, () -> raceService.findById(3));
        assertEquals("Corrida 3 não existe", exception.getMessage());
    }

    @Test
    @DisplayName("Should find race by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/race.sql"})
    void findById() {
        Race race = raceService.findById(2);
        assertEquals(2, race.getId());
        assertEquals("Campeonato Grand Prix Italia", race.getChampionship().getDescription());
    }

    @Test
    @DisplayName("Should list all races")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/race.sql"})
    void listAll() {
        List<Race> list = raceService.listAll();
       assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Should be empty list")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void listAllEmpty() {
        var exception = assertThrows(ObjectNotFound.class, () -> raceService.listAll());
        assertEquals("Não existem corridas cadastradas", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete race by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/race.sql"})
    void deleteById() {
        List<Race> list = raceService.listAll();
        assertEquals(2, list.size());

        raceService.delete(1);

        list = raceService.listAll();
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Should update race by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/race.sql"})
    void updateRace() {
        Race race = raceService.findById(1);
        assertEquals("Interlagos", race.getSpeedway().getName());
        assertEquals(1500, race.getSpeedway().getSize());
        assertEquals(1, race.getSpeedway().getId());

        Speedway speedway = speedwayService.findById(2);

        Race updatedRace = new Race(race.toDTO(), race.getChampionship(), speedway);

        updatedRace = raceService.update(updatedRace);
        assertEquals("Monza", race.getSpeedway().getName());
        assertEquals(1000, race.getSpeedway().getSize());
        assertEquals(2, race.getSpeedway().getId());


    }
}
