-- Q1
SELECT COUNT(Id) FROM Account;

-- Q2
SELECT Name, Address from Account where id = 'admin';

-- Q3
SELECT COUNT(Account_id)
FROM (
  SELECT Account_id
  FROM RATING
  GROUP BY Account_id
  HAVING COUNT(Account_id) >= 5
);

-- Q4
SELECT COUNT(M.Title)
FROM MOVIE M, GENRE_OF G
WHERE M.ID = G.Movie_id
  AND G.Genre_name = 'Romance'
  AND M.Start_year BETWEEN TO_DATE('2020-01-01', 'yyyy-mm-dd') AND TO_DATE('2020-12-31', 'yyyy-mm-dd');
  
-- Q5
SELECT count(M.title)
FROM MOVIE M
WHERE  M.Start_year BETWEEN TO_DATE('2018-10-07', 'yyyy-mm-dd') AND TO_DATE('2020-10-07', 'yyyy-mm-dd')
  AND M.End_year BETWEEN TO_DATE('2018-10-07', 'yyyy-mm-dd') AND TO_DATE('2020-10-07', 'yyyy-mm-dd');

-- Q6
SELECT avg(rating)
FROM MOVIE M
WHERE  M.Start_year BETWEEN TO_DATE('2015-10-07', 'yyyy-mm-dd') AND TO_DATE('2020-10-07', 'yyyy-mm-dd');

-- Q7
SELECT count(title)
FROM MOVIE M
WHERE Runtime >= 100;

-- Q8
SELECT TItle
FROM GENRE_OF G, MOVIE M
WHERE M.Id = G.Movie_id
  AND (G.Genre_name = 'Action' OR G.Genre_name = 'Comedy') AND
  Rating IN (SELECT max(rating)
    FROM GENRE_OF G, MOVIE M
    WHERE M.Id = G.Movie_id
    AND (G.Genre_name = 'Action' OR G.Genre_name = 'Comedy'));
    
-- Q9
SELECT Count(M.id)
FROM Movie M
WHERE M.mType='tvSeries' AND M.id IN (
  SELECT E.Movie_id
  FROM EPISODE E
  GROUP BY E.Movie_id
  Having Count(E.Movie_id) >= 10
);

-- Q10
SELECT A.name, A.age
FROM ACCOUNT A
WHERE  A.age = (
  SELECT max(A.Age)
  FROM ACCOUNT A
  WHERE NOT EXISTS (SELECT * FROM RATING R WHERE A.id = R.Account_id)
) AND NOT EXISTS (SELECT * FROM RATING R WHERE A.id = R.Account_id);

-- Q11
SELECT A.name, A.age, A.job
FROM ACCOUNT A
WHERE  A.id IN (
  SELECT A.Id
  FROM Rating R
  WHERE A.Id = R.Account_id
  GROUP BY R.Account_id
  Having Count(R.Account_id) = 1
) AND A.job IS NOT NULL;

-- Q12
SELECT A.Aname, A.id
FROM ACTOR A
WHERE 
  NOT EXISTS (
    (SELECT P.Movie_id
    FROM PLAY P
    WHERE P.Actor_id = A.Id)
    minus
    (SELECT M.id
    FROM MOVIE M
    WHERE M.rating >= 8)
  );

-- Q13
SELECT M.TITLE, A.Aname
FROM Movie M, ACTOR A, PLAY P
WHERE NOT EXISTS (SELECT G.Genre_name FROM GENRE_OF G WHERE M.id = G.Movie_id AND G.Genre_name = 'Action') AND
  M.rating < 6 AND
  M.Start_year >= TO_DATE('2010-01-01', 'yyyy-mm-dd') AND
  P.Actor_id = A.id AND P.Movie_id = M.id;

--Q14
 select count(M.id)
 from movie M
 where extract(YEAR from M.start_year)= ( select extract(YEAR from A.BIRTHDAY)
 from account A
 where  A.age = (select min(A.age)
      from account A
      where A.MEMBERSHIP_GRADE =2
      group by A.MEMBERSHIP_GRADE));
      
--Q15
Select M.title
from movie M
where M.MTYPE != 'tvSeries' and M.rating<8 and M.id IN (select RR.movie_id
from rating RR
where RR.account_id=(select A.id
from account A
where A.membership_grade=1 and A.id =(select R.ACCOUNT_ID
from rating R 
group by R.account_Id
having count(R.account_id)=10)));

-- Q16
SELECT A.Phone_number, A.address
FROM Account A
WHERE A.Id IN (
  SELECT A.Id
  FROM Movie M, Rating R
  WHERE R.Account_id = A.id AND R.Movie_id = M.id AND M.rating = (SELECT MAX(M.rating) FROM Movie)
) AND LENGTH(A.address) = (
  SELECT MAX(LENGTH(A.address)) FROM Account A WHERE A.Id IN (
    SELECT A.Id
    FROM Movie M, Rating R
    WHERE R.Account_id = A.id AND R.Movie_id = M.id AND M.rating = (SELECT MAX(M.rating) FROM Movie)
));

-- Q17
SELECT M.id, M.rating
FROM Movie M
WHERE  M.rating = (
  SELECT rating
  FROM
  (
    SELECT M.start_year , M.id, M.rating, DENSE_RANK() OVER (ORDER BY start_year ASC) as rak
    FROM Movie M
    WHERE M.id IN (
      SELECT id
      FROM
      (
          SELECT A.Id,
          DENSE_RANK() OVER (ORDER BY Count(P.Movie_id) DESC) AS RANKING
          FROM PLAY P, Actor A
          WHERE P.Actor_id = A.Id
          GROUP BY A.Id
      )
      WHERE RANKING = 3
    )
  ) WHERE rak = 1
) - 1 OR M.rating = (
  SELECT rating
  FROM
  (
    SELECT M.start_year , M.id, M.rating, DENSE_RANK() OVER (ORDER BY start_year ASC) as rak
    FROM Movie M
    WHERE M.id IN (
      SELECT id
      FROM
      (
          SELECT A.Id,
          DENSE_RANK() OVER (ORDER BY Count(P.Movie_id) DESC) AS RANKING
          FROM PLAY P, Actor A
          WHERE P.Actor_id = A.Id
          GROUP BY A.Id
      )
      WHERE RANKING = 3
    )
  ) WHERE rak = 1
) + 1;

-- Q18
SELECT Avg(M.rating)
FROM Movie M
WHERE (
  SELECT count(V.Movie_id)
  FROM VERSION V
  WHERE M.id = V.Movie_id
  GROUP BY V.Movie_id) BETWEEN 5 AND 10
  AND
  (
    SELECT COUNT(P.Actor_id)
    FROM PLAY P
    WHERE P.movie_id = M.id
    GROUP BY P.movie_id
  ) <= 5 AND (M.mType='movie' OR M.mType='tvSeries');


-- Q19
select mmmm.TITLE
from movie mmmm
where mmmm.runtime=(select mmm.RUNTIME
from movie mmm
where mmm.rating = (select max(mm.RATING)
from movie mm
where mm.rating not in(select max(rating)from movie) and mm.id in (select DISTINCT PP.MOVIE_ID
from play PP
where PP.actor_id in (select distinct P.Actor_id
from play P
where p.movie_id in (select M.id
from movie M
where M.rating =(
select max(MM.rating)
from movie MM
))))) and mmm.id in (select DISTINCT PP.MOVIE_ID
from play PP
where PP.actor_id in (select distinct P.Actor_id
from play P
where p.movie_id in (select M.id
from movie M
where M.rating =(
select max(MM.rating)
from movie MM
)))));

-- Q20
CREATE OR REPLACE VIEW inclue_genre
AS
  select m.id
  from movie m 
  where not exists
  (
    (select g.genre_name
    from genre_of g
    where g.movie_id = (
      select movie_id
      from
      (
        select r.movie_id,
        row_number() OVER (ORDER BY count(r.movie_id) DESC) AS rk
        from rating r,
        (
          select r.account_id as man_id
          from account a, rating r
          where r.movie_id = (
              select movie_id
              from
              (
                select r.movie_id, 
                row_number() OVER (ORDER BY count(r.movie_id) DESC) AS ranking
                from rating r, account a
                where r.account_id = a.id
                and a.gender = 'F'
                group by r.movie_id
              )
              where ranking = 1
            ) and a.id = r.account_id and a.gender='M'
        )
        where r.account_id = man_id
        group by r.movie_id
      )
      where rk = 1
    )) minus
    (select g.genre_name
    from genre_of g
    where g.movie_id = m.id)
  );
  
  
  select a.name
  from account a
  where a.birthday = 
  (
    select start_year
    from movie
    where id = 
      (
        select movie_id
        from
        (
          select r.movie_id, 
          row_number() OVER (ORDER BY count(r.movie_id) ASC) AS ranking
          from inclue_genre ig, rating r
          where r.movie_id = ig.id
          group by r.movie_id
        )
        where ranking = 1
      )
  );



