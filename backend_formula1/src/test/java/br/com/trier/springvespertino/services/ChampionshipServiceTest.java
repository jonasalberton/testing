package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.BaseTest;
import br.com.trier.springvespertino.models.Championship;
import br.com.trier.springvespertino.models.Country;
import br.com.trier.springvespertino.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class ChampionshipServiceTest extends BaseTest {


    @Autowired
    ChampionshipService championshipService;



    @Test
    @DisplayName("Should find by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/championship.sql"})
    void findById() {
        Championship championship = championshipService.findById(1);

        assertEquals("Campeonato Grand Prix", championship.getDescription());
        assertEquals(1, championship.getId());
        assertEquals(2023, championship.getYear());
    }

    @Test
    @DisplayName("Should not find by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/championship.sql"})
    void findByNull() {
        assertNull( championshipService.findById(4));
    }

    @Test
    @DisplayName("Should list all pilots")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/championship.sql"})
    void listAll() {
        List<Championship> list = championshipService.listAll();
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Should be empty list")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void listAllEmpty() {
        assertEquals(0, championshipService.listAll().size());
    }

    @Test
    @DisplayName("Should save a new country")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void save() {
        Championship championship =  new Championship(1, "Testing", 2024);

        Championship saved = championshipService.insert(championship);
        assertEquals("Testing", saved.getDescription() );
        assertEquals(2024, saved.getYear() );
    }

    @Test
    @DisplayName("Should find by year")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void filterByYear() {
        Championship championship1 =  new Championship(1, "Testing 1", 2022);
        Championship championship2 =  new Championship(2, "Testing 2", 2023);
        Championship championship3 =  new Championship(3, "Testing 3", 2024);

        championshipService.insert(championship1);
        championshipService.insert(championship2);
        championshipService.insert(championship3);

        List<Championship> filtered =  championshipService.findByYear(2023);
        assertEquals(1, filtered.size());
    }



    @Test
    @DisplayName("Should filter by description ignoring case")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void findByDescriptionContainsIgnoreCase() {
        Championship championship1 =  new Championship(1, "Testing 1", 2022);
        Championship championship2 =  new Championship(2, "Grand Prix", 2023);
        Championship championship3 =  new Championship(3, "Retomada", 2024);

        championshipService.insert(championship1);
        championshipService.insert(championship2);
        championshipService.insert(championship3);

        List<Championship> filtered =  championshipService.findByDescriptionContainsIgnoreCase("GRAnD PRiX");

        assertEquals(1, filtered.size());
        assertEquals("Grand Prix", filtered.get(0).getDescription());
        assertEquals(2023, filtered.get(0).getYear());
    }

}
