-- Pedidos
INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (1, 23.98, 'PENDENTE', true, '2023-09-01 10:00:00');
INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (2, 19.99, 'RECEBIDO', true, '2023-09-01 11:30:00');
INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (3, 14.99, 'EM_PREPARACAO', true, '2023-09-01 12:45:00');
INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (4, 10.99, 'PRONTO', true, '2023-09-01 14:15:00');
INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (5, 17.98, 'FINALIZADO', true, '2023-09-01 15:20:00');


-- Combos
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (1, 6, 11, 16, 1, 23.98, 23.98, 1);
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (2, 7, 12, 17, 1, 19.99, 19.99, 2);
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (3, 8, 13, 18, 1, 14.99, 14.99, 3);
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (4, 9, 14, 19, 1, 10.99, 10.99, 4);
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (5, 10, 15, 20, 1, 17.98, 17.98, 5);

-- Pedidos com status pago false para testar fakeCheckout
INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (1, 10.99 + 5.99 + 4.99 + 7.99, 'PENDENTE', false, '2023-09-01 08:30:00');
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (1, 6, 11, 16, 1, 10.99, 10.99 + 5.99 + 4.99 + 7.99, currval('pedido_id_seq'));

INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (2, 12.99 + 6.99 + 5.99 + 6.99, 'PENDENTE', false, '2023-09-01 09:15:00');
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (2, 7, 12, 17, 1, 12.99, 12.99 + 6.99 + 5.99 + 6.99, currval('pedido_id_seq'));

INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (3, 14.99 + 7.99 + 2.99 + 5.99, 'PENDENTE', false, '2023-09-01 10:45:00');
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (3, 8, 13, 18, 1, 14.99, 14.99 + 7.99 + 2.99 + 5.99, currval('pedido_id_seq'));

INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (4, 9.99 + 8.99 + 3.99 + 4.99, 'PENDENTE', false, '2023-09-01 11:30:00');
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (4, 9, 14, 19, 1, 9.99, 9.99 + 8.99 + 3.99 + 4.99, currval('pedido_id_seq'));

INSERT INTO Pedido (id_cliente, valor_total, status, pago, dt_cadastro) VALUES (5, 11.99 + 4.99 + 2.99 + 6.99, 'PENDENTE', false, '2023-09-01 12:15:00');
INSERT INTO Combo (lanche_id, acompanhamento_id, bebida_id, sobremesa_id, quantidade, valor_unitario, valor_total, pedido_id)
VALUES (5, 10, 15, 10, 1, 11.99, 11.99 + 4.99 + 2.99 + 6.99, currval('pedido_id_seq'));



