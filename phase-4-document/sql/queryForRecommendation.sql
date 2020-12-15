-- avg rating
create view avg_rating as 
select movie_id, avg(cast(stars as DECIMAL(10,2))) as ravg
from rating
group by movie_id
order by ravg DESC;

-- top 5
select distinct m.id, m.title, m.mtype, t.ravg
from avg_rating t, movie m, genre_of g
where t.movie_id = m.id and g.movie_id = t.movie_id
and not exists ( select r.rating_id  from rating r
where r.movie_id = m.id and r.account_id ='test2020')
order by ravg desc;


-- top 5 Recommendation 
drop view favor;
create view favor as
  select mtype, genre, runtime 
  from account_favorite
  where id = 'test2021';

select *
from favor;

select distinct m.id, m.title, m.mtype, t.ravg
from avg_rating t, genre_of g, movie m, favor f
where t.movie_id = g.movie_id and t.movie_id = m.id and 
  -- use favors of an account
  g.GENRE_NAME = f.genre and m.mtype=f.mtype and
  -- allowed arror +20, -20
  m.runtime between f.runtime - 20 and f.runtime + 20
  and not exists ( select r.rating_id  from rating r
      where r.movie_id = m.id and r.account_id ='test2020')
order by t.ravg desc;
