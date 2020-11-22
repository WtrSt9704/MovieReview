- [제작환경](#----)
- [사용법](#---)
- [클래스](#---)
  * [Main](#main)
  * [FunctionPage](#functionpage)
      - [`isAdmin(Connection, User) : boolean`](#-isadmin-connection--user----boolean-)
      - [`doFunctions(Connection, Statement, User) : boolean`](#-dofunctions-connection--statement--user----boolean-)
  * [SearchPage](#searchpage)
      - [`makeMap(Map<String, String>, String[]) : void`](#-makemap-map-string--string---string------void-)
      - [`retrieveAll(Connection, User, boolean) : void`](#-retrieveall-connection--user--boolean----void-)
      - [`retrieveTitle(Connection, String, User, boolean) : void`](#-retrievetitle-connection--string--user--boolean----void-)
      - [`retrieveFilter(Connection, User, boolean) : void`](#-retrievefilter-connection--user--boolean----void-)
  * [RatingPage](#ratingpage)
      - [`totalRating(Connection, String) : void`](#-totalrating-connection--string----void-)
      - [`AdminRating(Connection) : void`](#-adminrating-connection----void-)
      - [`showMovieDetail(Connection, int) : void`](#-showmoviedetail-connection--int----void-)
      - [`evaluateMovie(Connection, int, String) : boolean`](#-evaluatemovie-connection--int--string----boolean-)
      - [`rerateMovie(Connection, int, String) : void`](#-reratemovie-connection--int--string----void-)
  * [MyPage](#mypage)
      - [`display(Connection, Statement, User) : boolean`](#-display-connection--statement--user----boolean-)
      - [`update(Connection, Statement, String id, String pw) : void`](#-update-connection--statement--string-id--string-pw----void-)
      - [`changepwd(Connection, Statement, String id, String pw) : void`](#-changepwd-connection--statement--string-id--string-pw----void-)
      - [`drop(Connection, Statement, String id, String pw)`](#-drop-connection--statement--string-id--string-pw--)
  * [LandingPage](#landingpage)
      - [`display(Connection, Statement, User) : boolean`](#-display-connection--statement--user----boolean--1)
      - [`signup(Connection, Statement) : void`](#-signup-connection--statement----void-)
      - [`login(Connection, Statement ,User) : boolean`](#-login-connection--statement--user----boolean-)
  * [AdminFunc](#adminfunc)
      - [`movieUpdate(Connection, int) : void`](#-movieupdate-connection--int----void-)
      - [`movieEnroll(Connection, Statement, String) : void`](#-movieenroll-connection--statement--string----void-)
  * [MovieEnrollFunc](#movieenrollfunc)
      - [`episode(Connection, Statement, int) : void`](#-episode-connection--statement--int----void-)
      - [`version(Connection, Statement, int, String) : void`](#-version-connection--statement--int--string----void-)
      - [`genreof(Connection, Statement, int) : void`](#-genreof-connection--statement--int--string----void-)
  * [Util](#util)
      - [`clearScr(): void`](#-clearscr----void-)
      - [`afterList(Connection, User, boolean) : void`](#-afterlist-connection--user--boolean----void-)
      - [`printMovie(Connection, String, User) : int`](#-printmovie-connection--string--user----int-)
- [변경사항](#----)
  * [Rating 테이블](#rating----)
  * [Account 테이블](#account----)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>



# 제작환경

- OS: Window 10 education
- Tool:
  - ojdbc7.jar
  - JavaSE-1.8
  - Eclipse IDE 2020-09 (4.17.0)
  - Oracle 11g in the docker container

# 사용법

- **기본적으로 원하는 기능에 부여된 번호를 입력하면 해당 기능을 실행한다.**
- 일반 사용자
  - 일반 사용자가 영상을 조회하면, 상세보기를 한 후 평가를 할 수 있다.
- 관리자
  - 관리자가 영상을 조회하면, 상세보기를 한 후 영상 정보를 수정할 수 있다.

# 클래스

## Main

- LandingPage를 보여주며,  사용자로 부터 종료 요청이 들어오면 프로그램을 끝낸다.

## FunctionPage

#### `isAdmin(Connection, User) : boolean`

- Function: 관리자 계정 여부를 검사한다.

- Return: 로그인한 사용자가 Admin이면 `true`, 아니면 `false`를 반환한다.

#### `doFunctions(Connection, Statement, User) : boolean`

- Function: 일반사용자와 관리자에 따른 필요한 기능을 나열하고 이에 대한 요청을 처리한다.
- Return: 사용자가 프로그램 종료를 선택하면 `true`, 로그아웃을 선택하면 `false`를 반환한다.

## SearchPage

#### `makeMap(Map<String, String>, String[]) : void`

- Function: 한 속성(Attribute)에서 가질 수 있는 값을 `key-value`형식으로 만든다.
- Parameter: 
  - `String[]`: Attribute가 가질 수 있는 값

> 아래 메소드는 내부에서 Util.printMovie 와 Util.afterList를 호출한다.

#### `retrieveAll(Connection, User, boolean) : void`

- Function: 사용자가 평가했던 영화를 보여준다.

#### `retrieveTitle(Connection, String, User, boolean) : void`

- Function: 입력한 제목을 가진 영화를 보여준다.
- Parameter:
  - `String`: 검색할 영화 제목

#### `retrieveFilter(Connection, User, boolean) : void`

- Function:  사용자가 입력한 조건에 맞는 영화를 보여준다.

## RatingPage

#### `totalRating(Connection, String) : void`

- Function: 한 계정 내의 모든 평가 점수를 보여준다.
- Parameter:
  - `String`: 유저 아이디

#### `AdminRating(Connection) : void`

- Function: 관리가 계정으로 접속했을시 모든 평가를 보여준다.

#### `showMovieDetail(Connection, int) : void`

- Function: 특정 영화의 정보들을 보여준다.
- Parameter: 영화 ID

#### `evaluateMovie(Connection, int, String) : boolean`

- Function:
  - 한 영화의 평균 평점을 계산한다.
  - Rating테이블에서 해당 계정이 영화를 평가한 기록이 있으면 update로 수정을, 없으면 Rating의max(Id)에 +1 하여 Insert문으로 추가한다.

- Parameter:
  - `String`: 사용자 ID
  - `int`: 영화 ID
- Return: 평가를 했으면 true, 하지 않았으면 false를 반환한다.

#### `rerateMovie(Connection, int, String) : void`

- Function:
  - 한 영화의 평균 평점을 다시 계산 한다.
  - movie테이블에 적힌 rating과 Rating테이블에서 계산된 점수가 다르면 movie 테이블의 Rating을 계산된 점수로 바꾼다.
- Parameter:
  - `String`: 사용자 ID
  - `int`: 영화 ID

## MyPage

#### `display(Connection, Statement, User) : boolean`

- Function: 마이페이지에서 사용할 수 있는 기능을 나열하고 요청을 처리한다.
- Return: 되돌아가기를 선택하면 `true`, 로그아웃을 하면 `false`를 반환한다.

> update는 내부에서 changepwd, changejob, changeaddress를 호출한다.

#### `update(Connection, Statement, String id, String pw) : void`

- Function: 사용자가 원하는 속성의 값을 변경한다.

#### `changepwd(Connection, Statement, String id, String pw) : void`

- Function: 사용자의 비밀번호를 변경한다.

#### `drop(Connection, Statement, String id, String pw)`

- Function: 계정을 삭제한다.

## LandingPage

#### `display(Connection, Statement, User) : boolean`

- Function: 최초 접속했을 때 가입, 로그인 기능을 할 수 있게 처리한다.
- Return: 로그인이 성공적이면 `true`, 그렇지 않으면 `false`를 반환한다.

#### `signup(Connection, Statement) : void`

- Function: 회원 가입을 처리한다.

#### `login(Connection, Statement ,User) : boolean`

- Function: 로그인을 처리한다.
- Return: 로그인이 성공적이면 `true`, 그렇지 않으면 `false`를 반환한다.

## AdminFunc

#### `movieUpdate(Connection, int) : void`

- Function:  영화의 정보를 변경한다.
- Parameter:
  - `int`: 영화 iD

#### `movieEnroll(Connection, Statement, String) : void`

- Function: 새로운 영화를 등록한다.
- Parameter:
  - `String`: 영화를 등록하는 관리자 ID
#### `movieUpdate(Connection, int) : void`

## MovieEnrollFUnc

#### `episode(Connection, Statement, int) : void`

- Function: 추가된 tvSeries의 에피소드를 등록한다.
- Parameter:
 `int`: 영화의 ID
 
 #### `version(Connection, Statement, int, String) : void`

- Function: 추가된 영상물의 version을 등록한다.
- Parameter:
 `int`: 영화의 ID
 `String': 영화의 title
 
  #### `genreof(Connection, Statement, int) : void`

- Function: 추가된 영상물의 genre를 등록한다.
- Parameter:
 `int`: 영화의 ID

## Util

#### `clearScr(): void`

- Function: 콘솔창을 비운다.

#### `afterList(Connection, User, boolean) : void`

- Function:  영화 목록을 출력하고 일반 사용자와 관리자를 구분하여 후속 처리를 한다.
- Parameter:
  - `boolean`: 관리자이면 `true`, 그렇지 않으면 `false`를 반환한다.

#### `printMovie(Connection, String, User) : int`

- Function: 사용자가 평가한 영화를 제외하고 출력한다.
- Return: 출력한 영화의 개수를 반환한다.

# 변경사항

## Rating 테이블

- Rating 테이블의 FK인 Account_id를 삭제시 Delete set NULL로 설정이 되어있었고, Account_id에는 NOT NULL 제약이 있어 계정을 삭제했을 때 오류가 발생했다. 이러한 이유로 Account_id의 NOT NULL제약을 삭제했다.

## Account 테이블

- Account 테이블의 Membership_grade 속성에 NOT NULL 제약을 추가했다.

