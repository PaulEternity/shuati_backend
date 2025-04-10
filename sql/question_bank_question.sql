
-- 创建库
create database if not exists juanshua;
use juanshua;
-- 题库题目表（硬删除）
create table if not exists question_bank_question
(
    id             bigint auto_increment comment 'id' primary key,
    questionBankId bigint                             not null comment '题库 id',
    questionId     bigint                             not null comment '题目 id',
    userId         bigint                             not null comment '创建用户 id',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    UNIQUE (questionBankId, questionId)
) comment '题库题目' collate = utf8mb4_unicode_ci;


INSERT INTO question_bank_question (questionBankId, questionId, userId, createTime)
VALUES (1, 1, 1, CURRENT_TIMESTAMP),
       (1, 2, 1, CURRENT_TIMESTAMP),
       (2, 3, 2, CURRENT_TIMESTAMP),
       (2, 4, 2, CURRENT_TIMESTAMP),
       (3, 5, 3, CURRENT_TIMESTAMP),
       (4, 6, 4, CURRENT_TIMESTAMP),
       (5, 7, 5, CURRENT_TIMESTAMP),
       (6, 8, 6, CURRENT_TIMESTAMP),
       (7, 9, 7, CURRENT_TIMESTAMP);