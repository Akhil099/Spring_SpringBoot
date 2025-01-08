--insert into user_details(id, birth_date, name) values(10001, current_date, 'akhil' )
--
--insert into user_details(id, birth_date, name) values(10002, current_date, 'nag' )
--
--insert into user_details(id, birth_date, name) values(10003, current_date, 'anudeep' )

--insert into post(id, description, user_id) values (20001, 'I want to learn AWS', 10001)
--insert into post(id, description, user_id) values (20002, 'I want to learn DevOps', 10002)
--insert into post(id, description, user_id) values (20003, 'I want to learn Google Cloud', 10003)
--insert into post(id, description, user_id) values (20004, 'I want to learn Google Certified', 10001)

CREATE TABLE todo(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    description VARCHAR(255),
    target_date DATE,
    done BOOLEAN
);

INSERT INTO todo (username, description, target_date, done) VALUES ('in28minutes', 'Get AWS Certified', '2033-07-19', 0)
INSERT INTO todo (username, description, target_date, done) VALUES ('in28minutes', 'Learn DevOps', '2034-07-19', 0)
INSERT INTO todo (username, description, target_date, done) VALUES ('in28minutes', 'Learn Full Stack Development', '2035-07-19', 0)
