DELETE FROM user_role;
DELETE FROM role_menu;
DELETE FROM role;
DELETE FROM user;
DELETE FROM log;


INSERT INTO `role` VALUES('1', '001', '超级管理员', 1, '系统所有模块权限', 1, 1, '/car/audited', '车型库');
INSERT INTO `role` VALUES('2', '002', '管理员', 1, '添加前端用户权限', 1, 1, '/user/index', '用户管理');
INSERT INTO `role` VALUES('3', '003', '前端用户', 0, '提供前端数据检索查看', 1, 0, '', '');


INSERT INTO `user` VALUES ('1', '超级管理员', 'administrator', '966906c9b7012b88e85b800161a774c5', 'administrator', '1', null, '2018-02-16 21:51:49');
INSERT INTO `user` VALUES ('2', '管理员', 'manager', 'e10adc3949ba59abbe56e057f20f883e', 'manager', '1', 1, '2018-03-16 10:45:48');


INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');


INSERT INTO `role_menu` VALUES('1', '7');
INSERT INTO `role_menu` VALUES('1', '8');
INSERT INTO `role_menu` VALUES('1', '9');
INSERT INTO `role_menu` VALUES('1', '10');
INSERT INTO `role_menu` VALUES('1', '11');
INSERT INTO `role_menu` VALUES('1', '12');
INSERT INTO `role_menu` VALUES('1', '13');
INSERT INTO `role_menu` VALUES('2', '8');


UPDATE `biz_vehicle` SET `creator` = 1;
