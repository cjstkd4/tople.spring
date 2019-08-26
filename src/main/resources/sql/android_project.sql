
-- ����� ���̺�
create table moim_user (
    id varchar2(50) primary key,    -- ���̵�
    moim varchar2(2000),            -- ���� ����
    fav varchar2(2000)              -- ���ã�� ����
    
    -- �׽�Ʈ�� �Ӽ�
    ,
    name varchar2(100),     -- �̸�
    gender varchar2(50),    -- ����
    birth varchar2(100),    -- �������
    loca varchar2(100),     -- ��������
    prof varchar2(1000),    -- �ڱ�Ұ�
    thumb varchar2(500)     -- �����ʻ���
);
-- �Է� ����
insert into moim_user values(
'asdf', 
'', 
'', 
'ȫ�浿', 
'��', 
'1990-08-08', 
'��⵵', 
'����', 
'hong.jpg');

select * from moim_user;


-- ���� ���
create table moimmem (
    id varchar2(50) not null,       -- ����� ���̵�
    moimcode number not null,       -- ���� �ڵ�
    permit number                   -- ���ӿ����� ���ѷ���
);
-- �Է� ����
insert into moimmem values(
'asdf', 
1, 
3);
select * from moimmem;


-- �Խ��� ���̺�
create table boardlist(
    listnum number primary key,     -- �Խñ� ��ȣ
    id varchar2(50) not null,       -- �ۼ��� ���̵�
    moimcode number not null,       -- ���� �ۼ��� ���� �ڵ�
    subject varchar2(500),          -- ����
    content varchar2(4000),         -- ����
    filename varchar2(4000),        -- ����� ���� �̸�
    thumb varchar2(4000),           -- ������ ����� �̸�
    editdate date not null,         -- �ۼ�/������ ��¥
    lev number not null             -- ���� ���� �з� ��ȣ(����)
);
-- �Է� ����
insert into boardlist values(
board_seq.nextval,
'asdf',
1,
'�� ����',
'�� ����',
'board_pic.jpg',
'board_thumb.jpg',
sysdate,
2
);
select * from boardlist;


-- ���� ���̺�
create table redat(
    renum number primary key,       -- ��� ��ȣ
    listnum number not null,        -- ���� ��ȣ
    id varchar2(50),                -- �ۼ��� ���̵�
    moimcode number not null,       -- �ۼ��� ���� �ڵ�
    reple varchar2(4000),           -- ����
    redate date not null            -- ��� ���� ��¥
);
-- �Է� ����
insert into redat values(
re_seq.nextval,
1,
'asdf',
1,
'�� ��',
sysdate
);
select * from redat;


-- ���� ���̺�
create table schedule (
    schnum number primary key,      -- �����ڵ�
    moimcode number not null,       -- ������ ��ϵ� ���� �ڵ�
    day varchar2(100) not null,     -- ��-��-��0
    time varchar2(100) not null,    -- ��:��:��
    title varchar2(500),            -- ������Ī(����)
    sub varchar2(4000),             -- ���� ����
    join varchar2(4000),            -- ������ ���
    lat number,                     -- ����
    lot number                      -- �浵
);
-- �Է� ����
insert into schedule values(
sche_seq.nextval,
1,
'2019-08-12',
'18:50:01',
'8�� ����',
'������ ���� ��â �Դϴ�.',
'asdf,qwer,zxcv',
36.1234,
125.512
);
select * from schedule;


-- ���� ���̺�
create table todo (
    schnum number not null,         -- ���� �ڵ�
    id varchar2(50) not null,       -- ������(�����) ���̵�
    ex varchar2(500) not null,      -- �� ��
    flag varchar2(100)              -- ���� ���� ( true �Ǵ� false )
);
-- �Է� ����
insert into todo values(
1,
'asdf',
'���� ì���',
'false'
);
select * from todo;


-- ȸ�� ���̺�
create table money (
    schnum number not null,         -- ���� �ڵ�
    id varchar2(50) not null,       -- ������(�����) ���̵�
    amount varchar2(500) not null,  -- ȸ��
    flag varchar2(100)              -- ���� ���� ( true �Ǵ� false )
);
-- �Է� ����
insert into money values(
1,
'asdf',
'30000',
'false'
);
select * from money;

-- �׽�Ʈ�� ���� ���̺�
create table zz100 (
    moimcode number primary key,    -- ���� �ڵ�
    loca varchar2(100),             -- ��ġ
    moimname varchar2(1000),        -- ���Ӹ�
    prod varchar2(4000),            -- ���ӼҰ�
    pic varchar2(500),              -- ����
    color varchar2(100)             -- ����
);
-- �Է� ����
insert into zz100 values(
moim_seq.nextval,
'������',
'������ �ص���',
'������ �ص��� ���鼭 ��� ����',
'banner.jpg',
'#333'
);
select * from zz100;


-- ���������� ������ ���� �̸� ���
select max(tname) as moimtable from tab order by tname desc;

-- ���̺� ����
drop table a10000 purge;

-- ������ ����
-- ���� �ڵ�
create sequence moim_seq nocache nocycle;

-- �� ��ȣ
create sequence board_seq nocache nocycle;

-- ���� ��ȣ
create sequence re_seq nocache nocycle;

-- ���� �ڵ�
create sequence sche_seq nocache nocycle;














