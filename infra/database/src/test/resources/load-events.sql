insert into partner (id, name, cnpj) values ('8c4fc406-3e25-4ccd-af77-29ad47ae5e47', 'Test partner', '00000000000001');

insert into events (id, name, description, date, partner_id)
values ('abee89f7-ee9e-48c1-8796-76c084532d8e', 'Test event', 'Test event description', '2021-01-01', '8c4fc406-3e25-4ccd-af77-29ad47ae5e47');

insert into sections (id, name, event_id)
values ('d21f333c-a267-424b-954a-d49e005209c9', 'section name', 'abee89f7-ee9e-48c1-8796-76c084532d8e');

insert into spots (id, location, section_id)
values ('83bac229-1554-45e2-a256-dfa75019180a', 'location', 'd21f333c-a267-424b-954a-d49e005209c9'),
       ('83bac229-1554-45e2-a256-dfa75019180b', 'location 2', 'd21f333c-a267-424b-954a-d49e005209c9'),
       ('83bac229-1554-45e2-a256-dfa75019180c', 'location 3', 'd21f333c-a267-424b-954a-d49e005209c9');