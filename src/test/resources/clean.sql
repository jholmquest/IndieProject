delete from user_role;
delete from role;
delete from specimen;
delete from user;

insert into user values (1, 'testuser', 'password');
insert into user values (2, 'anotheruser', '123pass');
insert into user values (3, 'holmquest', 'aaaaaaaa');

insert into specimen values (1, 'beetle', 'madison', '1997-01-01', 'eating some grass', 3);
insert into specimen values (2, 'wasp', 'milwaukee', '2004-04-17', 'predator of buprestid', 3);
insert into specimen values (3, 'fly', 'shawano', '2013-07-18', 'buzzing about', 2);

insert into role values (1, 'admin', 0);
insert into role values (2, 'moderator', 1);
insert into role values (3, 'user', 2);

insert into user_role values (3, 1);
insert into user_role values (3, 3);
insert into user_role values (1, 3);