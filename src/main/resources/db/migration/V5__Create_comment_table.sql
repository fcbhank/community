CREATE TABLE comment
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id    BIGINT        NOT NULL comment '父类id',
    content      VARCHAR(1024) NULL comment '评论内容',
    type         INT           NOT NULL comment '父类id',
    commentator  INT           NOT NULL comment '评论人id',
    gmt_create   BIGINT        NOT NULL comment '创建时间',
    gmt_modified BIGINT        NOT NULL comment '修改时间',
    like_count   BIGINT DEFAULT 0 comment '点赞数'
);
