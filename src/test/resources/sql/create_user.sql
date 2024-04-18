DELETE FROM public.users;
DELETE FROM public.account_cash;

INSERT INTO public.users
(id, cpf_cnpj, email, "name", "password")
VALUES(1, '00012345678', 'test@test.test', 'test', 'test123');

INSERT INTO public.users
(id, cpf_cnpj, email, "name", "password")
VALUES(2, '00123456000178', 'merchant@test.test', 'merchant', 'merchant123');