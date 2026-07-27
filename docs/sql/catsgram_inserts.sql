INSERT INTO public.users (username,email,"password",registration_date) VALUES
	 ('alice','alice@example.com','password123',NOW()),
	 ('bob','bob@example.com','password123',NOW()),
	 ('charlie','charlie@example.com','password123',NOW()),
	 ('Воин Рохана','mail@mail.ru','passwd',NOW()) ON CONFLICT DO NOTHING;
	 
INSERT INTO public.posts (author_id,description,post_date) VALUES
	 (1,'пост номер один',NOW()),
	 (1,'пост номер два',NOW()),
	 (1,'пост номер три',NOW()),
	 (2,'единственный пост этого автора',NOW()) ON CONFLICT DO NOTHING;