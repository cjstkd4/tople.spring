
-- 사용자 테이블
create table moim_user (
    id varchar2(50) primary key,    -- 아이디
    moim varchar2(2000),            -- 참여 모임
    fav varchar2(2000)              -- 즐겨찾기 모임
    
    -- 테스트용 속성
    ,
    name varchar2(100),     -- 이름
    gender varchar2(50),    -- 성별
    birth varchar2(100),    -- 생년월일
    loca varchar2(100),     -- 거주지역
    prof varchar2(1000),    -- 자기소개
    thumb varchar2(500)     -- 프로필사진
);
-- 입력 쿼리
insert into moim_user values(
'asdf', 
'', 
'', 
'홍길동', 
'남', 
'1990-08-08', 
'경기도', 
'도사', 
'hong.jpg');

select * from moim_user;


-- 모임 멤버
create table moimmem (
    id varchar2(50) not null,       -- 사용자 아이디
    moimcode number not null,       -- 모임 코드
    permit number                   -- 모임에서의 권한레벨
);
-- 입력 쿼리
insert into moimmem values(
'asdf', 
1, 
3);
select * from moimmem;


-- 게시판 테이블
create table boardlist(
    listnum number primary key,     -- 게시글 번호
    id varchar2(50) not null,       -- 작성자 아이디
    moimcode number not null,       -- 글이 작성된 모임 코드
    subject varchar2(500),          -- 제목
    content varchar2(4000),         -- 내용
    filename varchar2(4000),        -- 저장된 사진 이름
    thumb varchar2(4000),           -- 사진의 썸네일 이름
    editdate date not null,         -- 작성/수정된 날짜
    lev number not null             -- 공지 등의 분류 번호(레벨)
);
-- 입력 쿼리
insert into boardlist values(
board_seq.nextval,
'asdf',
1,
'글 제목',
'글 내용',
'board_pic.jpg',
'board_thumb.jpg',
sysdate,
2
);
select * from boardlist;


-- 덧글 테이블
create table redat(
    renum number primary key,       -- 답글 번호
    listnum number not null,        -- 원글 번호
    id varchar2(50),                -- 작성자 아이디
    moimcode number not null,       -- 작성된 모임 코드
    reple varchar2(4000),           -- 내용
    redate date not null            -- 답글 생성 날짜
);
-- 입력 쿼리
insert into redat values(
re_seq.nextval,
1,
'asdf',
1,
'덧 글',
sysdate
);
select * from redat;


-- 일정 테이블
create table schedule (
    schnum number primary key,      -- 일정코드
    moimcode number not null,       -- 일정이 등록된 모임 코드
    day varchar2(100) not null,     -- 년-월-일0
    time varchar2(100) not null,    -- 시:분:초
    title varchar2(500),            -- 일정명칭(제목)
    sub varchar2(4000),             -- 일정 내용
    join varchar2(4000),            -- 참가자 명단
    lat number,                     -- 위도
    lot number                      -- 경도
);
-- 입력 쿼리
insert into schedule values(
sche_seq.nextval,
1,
'2019-08-12',
'18:50:01',
'8월 정모',
'모임은 이젠 곱창 입니다.',
'asdf,qwer,zxcv',
36.1234,
125.512
);
select * from schedule;


-- 할일 테이블
create table todo (
    schnum number not null,         -- 일정 코드
    id varchar2(50) not null,       -- 참여자(사용자) 아이디
    ex varchar2(500) not null,      -- 할 일
    flag varchar2(100)              -- 수행 여부 ( true 또는 false )
);
-- 입력 쿼리
insert into todo values(
1,
'asdf',
'삼겹살 챙기기',
'false'
);
select * from todo;


-- 회비 테이블
create table money (
    schnum number not null,         -- 일정 코드
    id varchar2(50) not null,       -- 참여자(사용자) 아이디
    amount varchar2(500) not null,  -- 회비
    flag varchar2(100)              -- 납입 여부 ( true 또는 false )
);
-- 입력 쿼리
insert into money values(
1,
'asdf',
'30000',
'false'
);
select * from money;

-- 테스트용 모임 테이블
create table zz100 (
    moimcode number primary key,    -- 모임 코드
    loca varchar2(100),             -- 위치
    moimname varchar2(1000),        -- 모임명
    prod varchar2(4000),            -- 모임소개
    pic varchar2(500),              -- 사진
    color varchar2(100)             -- 색상
);
-- 입력 쿼리
insert into zz100 values(
moim_seq.nextval,
'강원도',
'정동진 해돋이',
'정동진 해돋이 보면서 고기 먹자',
'banner.jpg',
'#333'
);
select * from zz100;


-- 마지막으로 생성된 모임 이름 얻기
select max(tname) as moimtable from tab order by tname desc;

-- 테이블 삭제
drop table a10000 purge;

-- 시퀀스 생성
-- 모임 코드
create sequence moim_seq nocache nocycle;

-- 글 번호
create sequence board_seq nocache nocycle;

-- 덧글 번호
create sequence re_seq nocache nocycle;

-- 일정 코드
create sequence sche_seq nocache nocycle;














