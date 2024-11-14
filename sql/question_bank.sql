use shuati;
-- 题库表
create table if not exists question_bank
(
    id          bigint auto_increment comment 'id' primary key,
    title       varchar(256)                       null comment '标题',
    description text                               null comment '描述',
    picture     varchar(2048)                      null comment '图片',
    userId      bigint                             not null comment '创建用户 id',
    editTime    datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index idx_title (title)
) comment '题库' collate = utf8mb4_unicode_ci;

INSERT INTO question_bank (title, description, picture, userId, isDelete)
VALUES ('数据库基础', '本题考查数据库的基本概念和操作。', 'http://example.com/db_base.png', 1, 0),
       ('HTTP协议详解', '探讨HTTP协议的工作原理和请求/响应流程。', 'http://example.com/http_protocol.png', 2, 0),
       ('Java集合框架', '介绍Java中的集合框架，包括List、Set、Map等。', 'http://example.com/java_collections.png', 3, 0),
       ('算法复杂度', '分析不同算法的时间复杂度和空间复杂度。', 'http://example.com/algorithm_complexity.png', 4, 0),
       ('微服务架构设计', '讨论微服务架构的设计原则和实现方式。', 'http://example.com/microservices_design.png', 5, 0),
       ('网络安全基础', '解释网络安全的基本概念和防护措施。', 'http://example.com/network_security.png', 6, 0),
       ('前端框架比较', '对比当前流行的前端框架，如React、Vue和Angular。', 'http://example.com/front_end_frameworks.png',
        7, 0),
       ('云计算服务', '介绍云计算的基本概念和主要服务提供商。', 'http://example.com/cloud_computing.png', 8, 0),
       ('人工智能入门', '概述人工智能的基础知识和应用场景。', 'http://example.com/ai_introduction.png', 9, 0);