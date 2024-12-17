CREATE TABLE IF NOT EXISTS media (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    cover_image VARCHAR(255),
    media_type INT,
    synopsis VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS media_entity_genre (
    id SERIAL PRIMARY KEY,
    media_id BIGINT NOT NULL,
    genre VARCHAR(255) NOT NULL,
    CONSTRAINT fk_media FOREIGN KEY (media_id) REFERENCES media(id) ON DELETE CASCADE,
    CONSTRAINT media_entity_genre_unique UNIQUE (media_id, genre)
);

CREATE TABLE IF NOT EXISTS media_entity_where_to_watch (
    id SERIAL PRIMARY KEY,
    media_id BIGINT NOT NULL,
    where_to_watch VARCHAR(255) NOT NULL,
    CONSTRAINT fk_media_where_to_watch FOREIGN KEY (media_id) REFERENCES media(id) ON DELETE CASCADE,
    CONSTRAINT media_entity_where_to_watch_unique UNIQUE (media_id, where_to_watch)
);

CREATE INDEX fk_media_id_idx ON media_entity_genre (media_id);
CREATE INDEX fk_media_where_to_watch_id_idx ON media_entity_where_to_watch (media_id);

INSERT INTO media (title, cover_image, synopsis, media_type) VALUES
('Inception', 'https://picsum.photos/seed/inception/200/300', 'Um ladrão que rouba segredos corporativos através da tecnologia de compartilhamento de sonhos recebe a chance de apagar seu histórico criminal.', 0),
('The Matrix', 'https://picsum.photos/seed/matrix/200/300', 'Um hacker de computador descobre, por meio de misteriosos rebeldes, a verdadeira natureza de sua realidade e seu papel na guerra contra seus controladores.', 0),
('The Dark Knight', 'https://picsum.photos/seed/darkknight/200/300', 'Quando a ameaça conhecida como O Coringa emerge de seu misterioso passado, ele provoca caos na cidade de Gotham.', 0),
('The Godfather', 'https://picsum.photos/seed/godfather/200/300', 'O patriarca de uma dinastia criminosa transferem o controle de seu império clandestino para seu filho relutante.', 0),
('Pulp Fiction', 'https://picsum.photos/seed/pulpfiction/200/300', 'As vidas de dois matadores de aluguel, um boxeador, a esposa de um gangster e dois bandidos de restaurante se cruzam em quatro histórias de violência e redenção.', 0),
('Forrest Gump', 'https://picsum.photos/seed/forrestgump/200/300', 'As presidências de Kennedy e Johnson, os eventos do Vietnã, o escândalo de Watergate e outros eventos históricos se desenrolam sob a perspectiva de um homem do Alabama com uma história extraordinária.', 0),
('Fight Club', 'https://picsum.photos/seed/fightclub/200/300', 'Um trabalhador de escritório insone, em busca de uma forma de mudar sua vida, cruza caminhos com um fabricante de sabão irreverente.', 0),
('The Shawshank Redemption', 'https://picsum.photos/seed/shawshank/200/300', 'Dois homens presos se conectam ao longo dos anos, encontrando consolo e eventual redenção por meio de atos de bondade comum.', 0),
('The Lion King', 'https://picsum.photos/seed/lionking/200/300', 'O príncipe leão Simba e seu pai são alvos de seu tio maligno, que deseja tomar o trono para si.', 2),
('The Avengers', 'https://picsum.photos/seed/avengers/200/300', 'Os heróis mais poderosos da Terra devem se unir e aprender a lutar como uma equipe para impedir que Loki e seu exército alienígena subjuguem a Terra.', 0),
('The Terminator', 'https://picsum.photos/seed/terminator/200/300', 'Um ciborgue é enviado do futuro para assassinar uma garçonete, cuja criança ainda não nascida liderará a humanidade em uma guerra contra as máquinas.', 0),
('Gladiator', 'https://picsum.photos/seed/gladiator/200/300', 'Um ex-general romano busca vingança contra o imperador corrupto que matou sua família e o enviou para a escravidão.', 0),
('The Prestige', 'https://picsum.photos/seed/prestige/200/300', 'Dois mágicos de palco se envolvem em uma rivalidade amarga, cada um tentando superar o outro com truques cada vez mais espetaculares e perigosos.', 0),
('Interstellar', 'https://picsum.photos/seed/interstellar/200/300', 'Uma equipe de exploradores viaja por um buraco de minhoca no espaço para tentar garantir a sobrevivência da humanidade.', 0),
('The Social Network', 'https://picsum.photos/seed/socialnetwork/200/300', 'A história dos fundadores do site de rede social, Facebook.', 0),
('The Dark Knight Rises', 'https://picsum.photos/seed/darkknightrises/200/300', 'Oito anos após o reinado de anarquia do Coringa, o Cavaleiro das Trevas é forçado a sair de seu exílio para enfrentar um novo líder terrorista, Bane.', 0),
('Joker', 'https://picsum.photos/seed/joker/200/300', 'Em Gotham, o comediante mentalmente perturbado Arthur Fleck é desprezado e maltratado pela sociedade. Ele começa uma lenta queda na insanidade e se transforma no mestre criminoso conhecido como o Coringa.', 0),
('Inglourious Basterds', 'https://picsum.photos/seed/inglouriousbasterds/200/300', 'Na França ocupada pelos nazistas durante a Segunda Guerra Mundial, um grupo de soldados judeus-americanos conhecidos como "Os Bastardos" é escolhido para espalhar o medo por meio de massacres e assassinatos de nazistas.', 0),
('Avatar', 'https://picsum.photos/seed/avatar/200/300', 'Um fuzileiro paraplégico é enviado para a lua Pandora com uma missão única e se vê dividido entre seguir suas ordens ou proteger o mundo que agora sente ser seu lar.', 0),
('Se7en', 'https://picsum.photos/seed/seven/200/300', 'Dois detetives, um novato e um veterano, caçam um serial killer que usa os sete pecados capitais como seu modus operandi.', 0),
('Naruto', 'https://picsum.photos/seed/naruto/200/300', 'Naruto Uzumaki, um jovem ninja que busca reconhecimento e sonha em se tornar o líder da sua vila, enfrenta desafios enquanto luta contra inimigos e busca a verdade sobre seu passado.', 2),
('One Piece', 'https://picsum.photos/seed/onepiece/200/300', 'Monkey D. Luffy e sua tripulação de piratas viajam pelos mares em busca do maior tesouro de todos os tempos, o One Piece.', 2),
('The Matrix Revolutions', 'https://picsum.photos/seed/matrixrevolutions/200/300', 'Neo e seus aliados continuam a lutar contra as forças das máquinas enquanto tentam salvar a humanidade.', 0),
('Avengers: Endgame', 'https://picsum.photos/seed/avengersendgame/200/300', 'Após a devastadora batalha contra Thanos, os Vingadores se reúnem para tentar reverter os danos e salvar o universo.', 0),
('The Lord of the Rings: The Fellowship of the Ring', 'https://picsum.photos/seed/fellowshipofthering/200/300', 'Um hobbit e seus amigos se embarcam em uma jornada épica para destruir um anel que pode trazer destruição ao mundo.', 2),
('Star Wars: A New Hope', 'https://picsum.photos/seed/starwarsanewhope/200/300', 'Um jovem agricultor se junta a uma aliança rebelde para lutar contra o Império Galáctico e salvar a princesa Leia.', 0),
('Jurassic Park', 'https://picsum.photos/seed/jurassicpark/200/300', 'Em uma ilha remota, um cientista revive dinossauros para um parque temático, mas a situação sai de controle.', 0),
('Guardians of the Galaxy', 'https://picsum.photos/seed/guardiansofthegalaxy/200/300', 'Um grupo de desajustados se une para proteger um artefato universal de ser destruído por um vilão intergaláctico.', 0),
('The Hobbit: An Unexpected Journey', 'https://picsum.photos/seed/thehobbit/200/300', 'Bilbo Bolseiro se une a um grupo de anões em uma aventura para recuperar seu reino perdido de um dragão.', 2),
('Pirates of the Caribbean: The Curse of the Black Pearl', 'https://picsum.photos/seed/piratesofthecaribbean/200/300', 'O capitão Jack Sparrow deve lutar contra piratas amaldiçoados e resgatar seu navio perdido.', 0),
('The Hunger Games', 'https://picsum.photos/seed/hungergames/200/300', 'Em um futuro distópico, Katniss Everdeen é forçada a lutar pela sobrevivência em um evento mortal transmitido ao vivo.', 0),
('Frozen', 'https://picsum.photos/seed/frozen/200/300', 'Duas irmãs com poderes mágicos devem unir forças para salvar seu reino de um inverno eterno.', 2),
('Deadpool', 'https://picsum.photos/seed/deadpool/200/300', 'Um ex-soldado das forças especiais se transforma em um anti-herói irreverente após passar por experimentos genéticos.', 0),
('Incredibles 2', 'https://picsum.photos/seed/incredibles2/200/300', 'A família Parr luta para equilibrar a vida de heróis com a rotina de família enquanto enfrentam um novo vilão.', 2),
('Spider-Man: Homecoming', 'https://picsum.photos/seed/spidermanhomecoming/200/300', 'Peter Parker luta para equilibrar a vida de um adolescente e super-herói enquanto enfrenta o vilão Abutre.', 0),
('Thor: Ragnarok', 'https://picsum.photos/seed/thorragnarok/200/300', 'Thor é capturado e deve lutar em uma arena contra o Hulk para impedir que seu mundo seja destruído.', 0),
('Shrek', 'https://picsum.photos/seed/shrek/200/300', 'Um ogro chamado Shrek deve resgatar uma princesa para recuperar a tranquilidade de sua casa.', 2),
('Transformers', 'https://picsum.photos/seed/transformers/200/300', 'Os Autobots, robôs alienígenas, devem lutar contra os Decepticons para proteger a Terra de sua destruição.', 0),
('Avatar: The Way of Water', 'https://picsum.photos/seed/avatarthewayofwater/200/300', 'Jake Sully e Neytiri enfrentam novos desafios ao proteger sua família e sua casa em Pandora.', 0),
('Mad Max: Fury Road', 'https://picsum.photos/seed/madmaxfuryroad/200/300', 'Em um futuro pós-apocalíptico, Max se alia a uma fugitiva para escapar de um tirano e encontrar liberdade.', 0),
('Blade Runner 2049', 'https://picsum.photos/seed/bladerunner2049/200/300', 'Em um futuro distópico, um novo Blade Runner deve investigar um segredo que pode mudar o rumo da humanidade.', 0);

INSERT INTO media_entity_where_to_watch (media_id, where_to_watch) VALUES
((SELECT id FROM media WHERE title = 'Inception'), 'Netflix'),

((SELECT id FROM media WHERE title = 'Inception'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'The Matrix'), 'Netflix'),

((SELECT id FROM media WHERE title = 'The Dark Knight'), 'HBO Max'),

((SELECT id FROM media WHERE title = 'The Godfather'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Pulp Fiction'), 'Hulu'),

((SELECT id FROM media WHERE title = 'Forrest Gump'), 'Netflix'),

((SELECT id FROM media WHERE title = 'Fight Club'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'The Shawshank Redemption'), 'Netflix'),

((SELECT id FROM media WHERE title = 'The Lion King'), 'Animação'),

((SELECT id FROM media WHERE title = 'The Lion King'), 'Disney+'),

((SELECT id FROM media WHERE title = 'The Avengers'), 'Disney+'),


((SELECT id FROM media WHERE title = 'The Terminator'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Gladiator'), 'Netflix'),

((SELECT id FROM media WHERE title = 'The Prestige'), 'HBO Max'),

((SELECT id FROM media WHERE title = 'Interstellar'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'The Social Network'), 'Netflix'),

((SELECT id FROM media WHERE title = 'The Dark Knight Rises'), 'HBO Max'),

((SELECT id FROM media WHERE title = 'Joker'), 'HBO Max'),

((SELECT id FROM media WHERE title = 'Inglourious Basterds'), 'Netflix'),

((SELECT id FROM media WHERE title = 'Avatar'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Se7en'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Naruto'), 'Crunchyroll'),

((SELECT id FROM media WHERE title = 'One Piece'), 'Netflix'),

((SELECT id FROM media WHERE title = 'The Matrix Revolutions'), 'Netflix'),

((SELECT id FROM media WHERE title = 'Avengers: Endgame'), 'Disney+'),

((SELECT id FROM media WHERE title = 'The Lord of the Rings: The Fellowship of the Ring'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Star Wars: A New Hope'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Jurassic Park'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Guardians of the Galaxy'), 'Disney+'),

((SELECT id FROM media WHERE title = 'The Hobbit: An Unexpected Journey'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Pirates of the Caribbean: The Curse of the Black Pearl'), 'Disney+'),

((SELECT id FROM media WHERE title = 'The Hunger Games'), 'Netflix'),

((SELECT id FROM media WHERE title = 'Frozen'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Deadpool'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Incredibles 2'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Spider-Man: Homecoming'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Thor: Ragnarok'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Shrek'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Transformers'), 'Amazon Prime'),

((SELECT id FROM media WHERE title = 'Avatar: The Way of Water'), 'Disney+'),

((SELECT id FROM media WHERE title = 'Mad Max: Fury Road'), 'HBO Max'),

((SELECT id FROM media WHERE title = 'Blade Runner 2049'), 'Amazon Prime');

INSERT INTO media_entity_genre (media_id, genre) VALUES
((SELECT id FROM media WHERE title = 'Inception'), 'Ação'),
((SELECT id FROM media WHERE title = 'Inception'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Inception'), 'Suspense'),
((SELECT id FROM media WHERE title = 'The Matrix'), 'Ação'),
((SELECT id FROM media WHERE title = 'The Matrix'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'The Matrix'), 'Suspense'),
((SELECT id FROM media WHERE title = 'The Dark Knight'), 'Ação'),
((SELECT id FROM media WHERE title = 'The Dark Knight'), 'Suspense'),
((SELECT id FROM media WHERE title = 'The Godfather'), 'Crime'),
((SELECT id FROM media WHERE title = 'The Godfather'), 'Drama'),
((SELECT id FROM media WHERE title = 'Pulp Fiction'), 'Crime'),
((SELECT id FROM media WHERE title = 'Pulp Fiction'), 'Drama'),
((SELECT id FROM media WHERE title = 'Forrest Gump'), 'Drama'),
((SELECT id FROM media WHERE title = 'Forrest Gump'), 'Romance'),
((SELECT id FROM media WHERE title = 'Fight Club'), 'Drama'),
((SELECT id FROM media WHERE title = 'The Shawshank Redemption'), 'Drama'),
((SELECT id FROM media WHERE title = 'The Lion King'), 'Animação'),
((SELECT id FROM media WHERE title = 'The Lion King'), 'Aventura'),
((SELECT id FROM media WHERE title = 'The Lion King'), 'Drama'),
((SELECT id FROM media WHERE title = 'The Avengers'), 'Ação'),
((SELECT id FROM media WHERE title = 'The Avengers'), 'Aventura'),
((SELECT id FROM media WHERE title = 'The Avengers'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'The Terminator'), 'Ação'),
((SELECT id FROM media WHERE title = 'The Terminator'), 'Suspense'),
((SELECT id FROM media WHERE title = 'Gladiator'), 'Ação'),
((SELECT id FROM media WHERE title = 'Gladiator'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Gladiator'), 'Drama'),
((SELECT id FROM media WHERE title = 'The Prestige'), 'Drama'),
((SELECT id FROM media WHERE title = 'The Prestige'), 'Mistério'),
((SELECT id FROM media WHERE title = 'The Prestige'), 'Suspense'),
((SELECT id FROM media WHERE title = 'Interstellar'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Interstellar'), 'Drama'),
((SELECT id FROM media WHERE title = 'Interstellar'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'The Social Network'), 'Biografia'),
((SELECT id FROM media WHERE title = 'The Social Network'), 'Drama'),
((SELECT id FROM media WHERE title = 'The Dark Knight Rises'), 'Ação'),
((SELECT id FROM media WHERE title = 'The Dark Knight Rises'), 'Suspense'),
((SELECT id FROM media WHERE title = 'Joker'), 'Crime'),
((SELECT id FROM media WHERE title = 'Joker'), 'Drama'),
((SELECT id FROM media WHERE title = 'Joker'), 'Suspense'),
((SELECT id FROM media WHERE title = 'Inglourious Basterds'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Inglourious Basterds'), 'Drama'),
((SELECT id FROM media WHERE title = 'Inglourious Basterds'), 'Guerra'),
((SELECT id FROM media WHERE title = 'Avatar'), 'Ação'),
((SELECT id FROM media WHERE title = 'Avatar'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Avatar'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'Se7en'), 'Crime'),
((SELECT id FROM media WHERE title = 'Se7en'), 'Drama'),
((SELECT id FROM media WHERE title = 'Se7en'), 'Mistério'),
((SELECT id FROM media WHERE title = 'Naruto'), 'Animação'),
((SELECT id FROM media WHERE title = 'Naruto'), 'Ação'),
((SELECT id FROM media WHERE title = 'Naruto'), 'Aventura'),
((SELECT id FROM media WHERE title = 'One Piece'), 'Ação'),
((SELECT id FROM media WHERE title = 'One Piece'), 'Aventura'),
((SELECT id FROM media WHERE title = 'One Piece'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'The Matrix Revolutions'), 'Ação'),
((SELECT id FROM media WHERE title = 'The Matrix Revolutions'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Avengers: Endgame'), 'Ação'),
((SELECT id FROM media WHERE title = 'Avengers: Endgame'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Avengers: Endgame'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'The Lord of the Rings: The Fellowship of the Ring'), 'Aventura'),
((SELECT id FROM media WHERE title = 'The Lord of the Rings: The Fellowship of the Ring'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'The Lord of the Rings: The Fellowship of the Ring'), 'Drama'),
((SELECT id FROM media WHERE title = 'Star Wars: A New Hope'), 'Ação'),
((SELECT id FROM media WHERE title = 'Star Wars: A New Hope'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Star Wars: A New Hope'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Jurassic Park'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Jurassic Park'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Jurassic Park'), 'Suspense'),
((SELECT id FROM media WHERE title = 'Guardians of the Galaxy'), 'Ação'),
((SELECT id FROM media WHERE title = 'Guardians of the Galaxy'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Guardians of the Galaxy'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'The Hobbit: An Unexpected Journey'), 'Aventura'),
((SELECT id FROM media WHERE title = 'The Hobbit: An Unexpected Journey'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'The Hobbit: An Unexpected Journey'), 'Ação'),
((SELECT id FROM media WHERE title = 'Pirates of the Caribbean: The Curse of the Black Pearl'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Pirates of the Caribbean: The Curse of the Black Pearl'), 'Ação'),
((SELECT id FROM media WHERE title = 'Pirates of the Caribbean: The Curse of the Black Pearl'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'The Hunger Games'), 'Ação'),
((SELECT id FROM media WHERE title = 'The Hunger Games'), 'Aventura'),
((SELECT id FROM media WHERE title = 'The Hunger Games'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Frozen'), 'Animação'),
((SELECT id FROM media WHERE title = 'Frozen'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Frozen'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'Deadpool'), 'Ação'),
((SELECT id FROM media WHERE title = 'Deadpool'), 'Comédia'),
((SELECT id FROM media WHERE title = 'Deadpool'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Incredibles 2'), 'Animação'),
((SELECT id FROM media WHERE title = 'Incredibles 2'), 'Ação'),
((SELECT id FROM media WHERE title = 'Spider-Man: Homecoming'), 'Ação'),
((SELECT id FROM media WHERE title = 'Spider-Man: Homecoming'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Spider-Man: Homecoming'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'Thor: Ragnarok'), 'Ação'),
((SELECT id FROM media WHERE title = 'Thor: Ragnarok'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Thor: Ragnarok'), 'Fantasia'),
((SELECT id FROM media WHERE title = 'Shrek'), 'Animação'),
((SELECT id FROM media WHERE title = 'Shrek'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Shrek'), 'Comédia'),
((SELECT id FROM media WHERE title = 'Transformers'), 'Ação'),
((SELECT id FROM media WHERE title = 'Transformers'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Avatar: The Way of Water'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Avatar: The Way of Water'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Mad Max: Fury Road'), 'Ação'),
((SELECT id FROM media WHERE title = 'Mad Max: Fury Road'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Mad Max: Fury Road'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Blade Runner 2049'), 'Ficção Científica'),
((SELECT id FROM media WHERE title = 'Blade Runner 2049'), 'Aventura'),
((SELECT id FROM media WHERE title = 'Blade Runner 2049'), 'Mistério');