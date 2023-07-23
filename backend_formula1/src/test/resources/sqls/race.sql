INSERT INTO campeonato(codigo_campeonato, descricao, ano) VALUES(1, 'Campeonato Grand Prix', 2023);
INSERT INTO campeonato(codigo_campeonato, descricao, ano) VALUES(2, 'Campeonato Grand Prix Italia', 2023);

INSERT INTO pais(id, name) VALUES(1, 'Brasil');
INSERT INTO pais(id, name) VALUES(2, 'Italia');

INSERT INTO pista(id_pista, nome_pista, tamanho_pista, country_id) VALUES(1, 'Interlagos', 1500, 1);
INSERT INTO pista(id_pista, nome_pista, tamanho_pista, country_id) VALUES(2, 'Monza', 1000, 2);

INSERT INTO corrida(id_corrida, data_corrida, speedway_id_pista, championship_codigo_campeonato) VALUES(1, now(), 1, 1);
INSERT INTO corrida(id_corrida, data_corrida, speedway_id_pista, championship_codigo_campeonato) VALUES(2, now(), 2, 2);