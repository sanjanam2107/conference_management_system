-- Password for all users is 'password'
INSERT INTO users (username, password, email) VALUES
('conference_user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'conf@example.com'),
('pcchair', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'chair@example.com'),
('author', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'author@example.com'),
('reviewer', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', 'reviewer@example.com');

INSERT INTO user_roles (user_id, roles) VALUES
(1, 'ROLE_CONFERENCE_USER'),
(2, 'ROLE_PC_CHAIR'),
(3, 'ROLE_AUTHOR'),
(4, 'ROLE_REVIEWER');