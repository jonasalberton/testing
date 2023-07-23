package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.BaseTest;
import br.com.trier.springvespertino.models.Team;
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
public class TeamServiceTest extends BaseTest {

    @Autowired
    TeamService teamService;

    @Test
    @DisplayName("Should select by team id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/team.sql"})
    void findByIdTest() {
        Team team = teamService.findById(2);
        assertEquals(2, team.getId());
        assertEquals("Redbull Sports", team.getName());
    }

    @Test
    @DisplayName("Should try search for undefined team")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/team.sql"})
    void findNull() {
        var exception = assertThrows(ObjectNotFound.class, () -> teamService.findById(3));
        assertEquals("Equipe 3 não encontrada", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete team by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/team.sql"})
    void deleteById() {
        teamService.delete(2);
        List<Team> list = teamService.listAll();
        assertEquals(1, list.size());
    }


    @Test
    @DisplayName("Should list all teams")

    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/team.sql"})
    void findAll() {
        List<Team> list = teamService.listAll();
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Should find team by name ignoring case")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/team.sql"})
    void findTeamByName() {
        List<Team> list = teamService.findByNameIgnoreCase("rEdbuLl sPorts");
        assertEquals(1, list.size());
        assertEquals("Redbull Sports", list.get(0).getName());
    }

    @Test
    @DisplayName("Should find team name that contains Sports")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/team.sql"})
    void findByNameContains() {
        List<Team> list = teamService.findByNameContains("Sports");
        assertEquals(1, list.size());
        assertEquals("Redbull Sports", list.get(0).getName());
    }
}
