CREATE TABLE RATING (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY,
	book_id BIGINT not null,
	rating INTEGER not null
);

insert into RATING (book_id, rating)
values (1,4);

insert into RATING (book_id, rating)
values (2,3);

insert into RATING (book_id, rating)
values (3,4);

insert into RATING (book_id, rating)
values (5,5);
