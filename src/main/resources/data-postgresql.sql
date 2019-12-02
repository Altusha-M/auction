INSERT INTO users (id, username, first_name, last_name, password, email)
VALUES (nextval('users_id_seq'), 'username1', 'first name 1', 'last name 1', 'password1', 'email1'),
       (nextval('users_id_seq'), 'username2', 'first name 2', 'last name 2', 'password2', 'email2'),
       (nextval('users_id_seq'), 'username3', 'first name 3', 'last name 3', 'password3', 'email3'),
       (nextval('users_id_seq'), 'username4', 'first name 4', 'last name 4', 'password4', 'email4'),
       (nextval('users_id_seq'), 'username5', 'first name 5', 'last name 5', 'password5', 'email5');