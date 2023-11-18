create table users (
    "id" number generated as identity primary key,
    "user_id" varchar2(50) NOT NULL UNIQUE,
    "password" varchar2(64) NOT NULL,
    "name" varchar2(15) NOT NULL,
    "phone" varchar2(14) NOT NULL,
    "address" varchar2(100) NOT NULL,
    "email" varchar2(100) NOT NULL,
    "git_address" varchar2(100),
    "gender" number(1),
    "birth" date,
    "createAt" DATE DEFAULT systimestamp
);
create table boards (
    "id" number generated as identity primary key,
    "user_id" number NOT NULL,
    "title" varchar2(100) NOT NULL,
    "content" long NOT NULL,
    "view" number default 0,
    "createAt" DATE DEFAULT systimestamp,
    "is_withdrew" number(1),
    constraint "user_to_board" foreign key("user_id") REFERENCES users("id")
);
create table likes_and_hates (
    "id" number generated as identity primary key,
    "user_id" number,
    "board_id" number,
    "likes_or_hates" number(1),
    "createAt" DATE DEFAULT systimestamp,
    constraint "user_to_likes_and_hates" foreign key("user_id") REFERENCES users("id"),
    constraint "board_to_likes_and_hates" foreign key("board_id") REFERENCES boards("id")
);