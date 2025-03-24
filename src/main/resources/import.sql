INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_CANDIDATO');
INSERT INTO tb_role (authority) VALUES ('ROLE_RH');
INSERT INTO tb_role (authority) VALUES ('ROLE_GERENTE');

INSERT INTO tb_user (nome, email, password, profissao, telefone, endereco) VALUES ('Jardel Rodrigues', 'rodriguesjardel96@gmail.com', '$2a$10$Qk8KkAC6KsqI2OWRmPica.hNl5ZDWZDzdTa1sHyd25BtVsmHcW/5y', 'Desenvolvedor de software', '(61) 994229903', 'Rua 110 Quandra 320 Lote 10');
INSERT INTO tb_user (nome, email, password, profissao, telefone, endereco) VALUES ('Maria silva', 'maria@gmail.com', '$2a$10$Qk8KkAC6KsqI2OWRmPica.hNl5ZDWZDzdTa1sHyd25BtVsmHcW/5y','Eletricista', '(61) 996542587', 'Rua 10 Quandra 30 Lote 11');
INSERT INTO tb_user (nome, email, password, profissao, telefone, endereco) VALUES ('Alex Alves', 'alex@gmail.com', '$2a$10$Qk8KkAC6KsqI2OWRmPica.hNl5ZDWZDzdTa1sHyd25BtVsmHcW/5y', 'Segurança', '(61) 984562547', 'Rua 15 Quandra 50 Lote 113');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);

INSERT INTO tb_notificacoes (user_id, texto, instante, lida) VALUES (1, 'Candidatura enviada com sucesso', TIMESTAMP WITH TIME ZONE '2024-8-31T13:00:00Z', true);
INSERT INTO tb_notificacoes (user_id, texto, instante, lida) VALUES (2, 'Candidatura aprovada', TIMESTAMP WITH TIME ZONE '2024-08-31T13:00:00Z', false);

INSERT INTO tb_vagas (titulo, descricao, requisitos, data_criacao_vaga, local, valor_diaria, status) VALUES ('Auxiliar de serviços gerais', 'Auxiliar de serviços gerais', 'Não se aplica', TIMESTAMP WITH TIME ZONE '2024-08-31T13:00:00Z', 'Gama', 120.00, 1);
INSERT INTO tb_vagas (titulo, descricao, requisitos, data_criacao_vaga, local, valor_diaria, status) VALUES ('Sergurança patrimonial', 'Sergurança patrimonial', 'Ter experiencia com segurança', TIMESTAMP WITH TIME ZONE '2024-08-31T13:00:00Z', 'Park Shopping', 250.00, 1);

INSERT INTO tb_candidaturas (user_id, vaga_id, data_candidatura, status) VALUES (2, 1, TIMESTAMP WITH TIME ZONE '2024-08-31T13:00:00Z', 0);
INSERT INTO tb_candidaturas (user_id, vaga_id, data_candidatura, status) VALUES (3, 2, TIMESTAMP WITH TIME ZONE '2024-08-31T13:00:00Z', 0);
