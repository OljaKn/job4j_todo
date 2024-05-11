ALTER TABLE tasks
ADD user_id int not null default 1 references todo_user(id);