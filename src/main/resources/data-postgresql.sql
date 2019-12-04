INSERT INTO users (id, username, first_name, last_name, password, email)
VALUES (nextval('users_id_seq'), 'username1', 'first name 1', 'last name 1', 'password1', 'email1'),
       (nextval('users_id_seq'), 'username2', 'first name 2', 'last name 2', 'password2', 'email2'),
       (nextval('users_id_seq'), 'username3', 'first name 3', 'last name 3', 'password3', 'email3'),
       (nextval('users_id_seq'), 'username4', 'first name 4', 'last name 4', 'password4', 'email4'),
       (nextval('users_id_seq'), 'username5', 'first name 5', 'last name 5', 'password5', 'email5');

INSERT INTO lots (id, category_name, condition, created_at, description, user_id)
VALUES (nextval('lots_id_seq'), 'car', 'IDEAL', now(), 'Simple car',
        (SELECT id FROM users WHERE username = 'username3')),
       (nextval('lots_id_seq'), 'car', 'GOOD', now(), 'Simple car',
        (SELECT id FROM users WHERE username = 'username3')),
       (nextval('lots_id_seq'), 'phone', 'NORM', now(), 'Simple phone',
        (SELECT id FROM users WHERE username = 'username2')),
       (nextval('lots_id_seq'), 'phone', 'IDEAL', now(), 'Simple phone',
        (SELECT id FROM users WHERE username = 'username2')),
       (nextval('lots_id_seq'), 'car', 'IDEAL', now(), 'Simple car',
        (SELECT id FROM users WHERE username = 'username4'));

INSERT INTO roles(id, name)
VALUES (nextval('roles_id_seq'), 'USER'),
       (nextval('roles_id_seq'), 'ADMIN'),
       (nextval('roles_id_seq'), 'SUPER');

INSERT INTO user_role(user_id, role_id)
VALUES (1000, 100),
       (1001, 100),
       (1002, 100),
       (1003, 101),
       (1004, 102);

INSERT INTO prices (current_price, max_price, min_price, step_size_value, lot_id)
VALUES (500, 1000, 200, 50, 1000),
       (500, 1000, 200, 50, 1001),
       (500, 1000, 200, 50, 1002),
       (500, 1000, 200, 50, 1003),
       (500, 1000, 200, 50, 1004);