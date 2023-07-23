package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.BaseTest;
import br.com.trier.springvespertino.models.Country;
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
public class CountryServiceTest extends BaseTest {

    @Autowired
    CountryService countryService;

    @Test
    @DisplayName("Should find by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/country.sql"})
    void findById() {
        Country country = countryService.findById(1);

        assertEquals("Brasil", country.getName());
        assertEquals(1, country.getId());
    }

    @Test
    @DisplayName("Should not find by id")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/country.sql"})
    void findByNull() {
        var exception = assertThrows(ObjectNotFound.class, () -> countryService.findById(4));
        assertEquals("País não existe", exception.getMessage());
    }

    @Test
    @DisplayName("Should list all pilots")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/country.sql"})
    void listAll() {
        List<Country> list = countryService.listAll();
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Should be empty list")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void listAllEmpty() {
        assertEquals(0, countryService.listAll().size());
    }

    @Test
    @DisplayName("Should save a new country")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void save() {
        Country country = new Country(1, "Chile");
        country = countryService.salvar(country);
        assertEquals("Chile", country.getName());
    }

    @Test
    @DisplayName("Should update country")
    @Sql({"/sqls/limpa_tabelas.sql"})
    void update() {
        Country country = new Country(1, "Chile");

        country = countryService.salvar(country);
        assertEquals(1, country.getId());
        assertEquals("Chile", country.getName());

        country.setName("EUA");
        Country updated = countryService.update(country);
        assertEquals(1, country.getId());
        assertEquals("EUA", country.getName());
    }



    @Test
    @DisplayName("Should search for country ignoring case")
    @Sql({"/sqls/limpa_tabelas.sql"})
    @Sql({"/sqls/country.sql"})
    void findByNomeEqualsIgnoreCase() {
        List<Country> list = countryService.findByNomeEqualsIgnoreCase("bRaSiL");
        assertEquals(1, list.size());
        assertEquals("Brasil", list.get(0).getName());
        assertEquals(1, list.get(0).getId());
    }
}
