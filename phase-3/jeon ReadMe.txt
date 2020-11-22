total Rating
-> 한 계정 내의 모든 평가 점수를 보여준다.
-> 유저의 아이디를 parameter로 받아 계산하여 보여준다.

AdminRating
-> 관리가 계정으로 접속했을시 모든 평가를 보여준다.

ShowMovieDetail
-> 특정 영화의 정보들을 보여준다.
-> movie테이블의 id를 parameter로 받아 계산하여 보여준다.

EvaluateMovie
-> 한 영화의 평점을 매기는 기능이다.
-> Account 테이블의 id를 UserID로 Movie테이블의 id를 id로 받아 계산한다.
-> Rating테이블에서 해당 계정이 영화를 평가한 기록이 있으면 update로 수정을, 없으면 Rating의 Id에 +1 하여 Insert문으로 추가한다.


rerateMovie
-> 한 영화의 평점을 재 계산하는 기능이다.
->Account 테이블의 id를 UserID로 Movie테이블의 id를 id로 받아 계산한다.
-> movie테이블에 적힌 rating과 Rating테이블에서 계산된 점수가 다르면 movie 테이블의 Rating을 계산된 점수로 바꾼다.