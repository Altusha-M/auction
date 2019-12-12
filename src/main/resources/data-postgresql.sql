INSERT INTO city (id, name)
VALUES (nextval('city_id_seq'), 'city1'),
       (nextval('city_id_seq'), 'city2'),
       (nextval('city_id_seq'), 'city3'),
       (nextval('city_id_seq'), 'city4');

INSERT INTO roles (id, name)
VALUES (nextval('role_id_seq'), 'admin'),
       (nextval('role_id_seq'), 'user');

INSERT INTO condition (id, name)
VALUES (nextval('condition_id_seq'), 'идеальное'),
       (nextval('condition_id_seq'), 'хорошее'),
       (nextval('condition_id_seq'), 'среднее'),
       (nextval('condition_id_seq'), 'плохое');

INSERT INTO category (id, name)
VALUES (nextval('category_id_seq'), 'Одежда'),
       (nextval('category_id_seq'), 'Электроника'),
       (nextval('category_id_seq'), 'Спорттовары'),
       (nextval('category_id_seq'), 'Услуги');

INSERT INTO users (id, username, first_name, last_name, password,
                   phone_number, email, city_id, role_id)
VALUES (nextval('users_id_seq'), 'username1', 'first name 1', 'last name 1',
        'password1', '1234560', 'email1',
        (SELECT id FROM city WHERE id = 1), 1),
       (nextval('users_id_seq'), 'username2', 'first name 2', 'last name 2',
        'password2', '1234560', 'email2',
        (SELECT id FROM city WHERE id = 2), 2),
       (nextval('users_id_seq'), 'username3', 'first name 3', 'last name 3',
        'password3', '1234560', 'email3',
        (SELECT id FROM city WHERE id = 3), 2),
       (nextval('users_id_seq'), 'username4', 'first name 4', 'last name 4',
        'password4', '1234560', 'email4',
        (SELECT id FROM city WHERE id = 4), 2),
       (nextval('users_id_seq'), 'username5', 'first name 5', 'last name 5',
        'password5', '1234560', 'email5',
        (SELECT id FROM city WHERE id = 1), 2);


INSERT INTO message (id, body, sender_id, receiver_id)
VALUES (nextval('message_id_seq'), 'Hello. I want to get my purchase.',
        (SELECT id FROM users WHERE username = 'username3'), (SELECT id FROM users WHERE username = 'username2')),
       (nextval('message_id_seq'), 'Ok, call me',
        (SELECT id FROM users WHERE username = 'username3'), (SELECT id FROM users WHERE username = 'username2')),
       (nextval('message_id_seq'), 'I can''t, call me back pls',
        (SELECT id FROM users WHERE username = 'username2'), (SELECT id FROM users WHERE username = 'username3')),
       (nextval('message_id_seq'), 'Ok, I will call you tomorrow',
        (SELECT id FROM users WHERE username = 'username2'), (SELECT id FROM users WHERE username = 'username3'));

INSERT INTO lot (id, category_id, condition_id, creation_time, last_mod_time, description, title,
                  user_id, current_price, min_price, max_price, step_price, city_id)
VALUES (nextval('lots_id_seq'), 1, 2, now(), now(), 'Simple car', 'Simple car', (SELECT id FROM users WHERE username = 'username1'),
        1000, 500, 1500, 75, 1),
       (nextval('lots_id_seq'), 3, 3, now(), now(), 'Simple car', 'Simple car', (SELECT id FROM users WHERE username = 'username4'),
        1000, 500, 1500, 75, 2),
       (nextval('lots_id_seq'), 2, 1, now(), now(), 'Simple phone', 'Simple phone', (SELECT id FROM users WHERE username = 'username2'),
        1000, 500, 1500, 75, 3),
       (nextval('lots_id_seq'), 4, 3, now(), now(), 'Simple phone', 'Simple phone',(SELECT id FROM users WHERE username = 'username5'),
        1000, 500, 1500, 75, 4),
       (nextval('lots_id_seq'), 4, 4, now(), now(), 'Simple car', 'Simple car', (SELECT id FROM users WHERE username = 'username3'),
        1000, 500, 1500, 75, 1);

INSERT INTO photo (id, lot_id, url)
VALUES (nextval('photo_id_seq'), 1, 'https://images.unsplash.com/photo-1529778873920-4da4926a72c2?fit=crop&w=376&q=80'),
       (nextval('photo_id_seq'), 2, 'https://images.unsplash.com/photo-1533425398081-0583e2d57c1c?fit=crop&w=750&q=80'),
       (nextval('photo_id_seq'), 3, 'https://images.unsplash.com/photo-1515172543028-5d19d9e34fba?fit=crop&w=750&q=80');