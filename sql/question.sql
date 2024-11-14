use shuati;
-- 题目表
create table if not exists question
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(256)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    answer     text                               null comment '推荐答案',
    userId     bigint                             not null comment '创建用户 id',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_title (title),
    index idx_userId (userId)
) comment '题目' collate = utf8mb4_unicode_ci;


INSERT INTO question (title, content, tags, answer, userId, isDelete)
VALUES ('如何创建一个数据库表？', '详细描述如何创建一个数据库表，包括表结构和字段类型。', '["数据库", "SQL", "表创建"]',
        '要创建一个数据库表，首先需要使用CREATE TABLE语句，然后定义表名和字段，例如：CREATE TABLE example (id INT, name VARCHAR(255));',
        1, 0),
       ('什么是JSON？', '解释JSON是什么，以及它在Web开发中的应用。', '["JSON", "Web开发", "数据交换"]',
        'JSON（JavaScript Object Notation）是一种轻量级的数据交换格式，易于人阅读和编写，同时也易于机器解析和生成。它基于JavaScript的一个子集，但独立于语言，可以被多种编程语言读取。',
        2, 0),
       ('如何优化SQL查询性能？', '探讨SQL查询性能优化的技巧和最佳实践。', '["SQL", "性能优化", "数据库"]',
        '优化SQL查询性能可以通过多种方式实现，包括使用索引、避免全表扫描、优化查询逻辑、使用EXPLAIN分析查询计划等。', 3, 0),
       ('什么是微服务架构？', '解释微服务架构的概念以及它的优势和挑战。', '["微服务", "架构", "分布式系统"]',
        '微服务架构是一种将应用程序作为一组小型服务开发的方法，每个服务运行在其独立的进程中，并通常围绕特定的业务能力进行构建。这种架构使得应用程序易于扩展和维护。',
        4, 0),
       ('如何使用Git进行版本控制？', '介绍Git的基本命令和工作流程，以及如何使用它进行版本控制。',
        '["Git", "版本控制", "代码管理"]',
        'Git是一个分布式版本控制系统，允许多个开发者协作开发项目。基本命令包括git init, git add, git commit, git push等。',
        5, 0);