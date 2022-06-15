INSERT INTO web_user
    (username, password, first_name, last_name, address, postal_code, city, email, terms_agreement)
VALUES
    ('admin', '{noop}Password!', 'Tomasz', 'Musiał', 'Wrocławska 52a', '55-100', 'Trzebnica', 'admin@strona.pl', true),
    ('slawek', '{noop}Sl@wekToczek', 'Sławomir', 'Toczek', 'Obornika 11', '50-008', 'Wrocław', 'slawek@mail.com', true),
    ('mati', '{noop}Mat!123Nowy', 'Mateusz', 'Nowak', 'Srebrna 19/5', '55-100', 'Prusice', 'mati123@mail.pl', true),
    ('jaras', '{noop}J@rok!ng12', 'Jan', 'Jarkiewicz', 'Kochanowskiego 31', '00-001', 'Warszawa', 'jaras@mailowo.pl', true);

INSERT INTO user_role
    (role)
VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

INSERT INTO user_and_user_role
    (user_id, user_role_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 2),
    (4, 1),
    (4, 2);