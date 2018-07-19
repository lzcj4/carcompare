/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : car_compare

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-16 14:34:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `biz_brand`
-- ----------------------------
DROP TABLE IF EXISTS `biz_brand`;
CREATE TABLE `biz_brand` (
  `id` char(36) NOT NULL COMMENT '品牌id',
  `initials` char(1) NOT NULL COMMENT '品牌首字母',
  `Name` varchar(50) DEFAULT NULL COMMENT '品牌名称',
  `order` int(3) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆品牌表';

-- ----------------------------
-- Records of biz_brand
-- ----------------------------
INSERT INTO `biz_brand` VALUES ('06860269-7424-4af4-9e8d-7f5f60ae1012', 'M', 'MINI', '0');
INSERT INTO `biz_brand` VALUES ('06d4015b-cd21-45bf-aaa0-d5f2c2c8a731', 'F', '福汽启腾', '0');
INSERT INTO `biz_brand` VALUES ('0710c509-bff7-4ffa-879d-b7f0120ef0cc', 'Z', '长江EV', '0');
INSERT INTO `biz_brand` VALUES ('07841c3e-de40-42b7-9f24-d310c9e0b7de', 'T', '泰克鲁斯 腾风', '0');
INSERT INTO `biz_brand` VALUES ('08ae41b1-95cb-48e8-b2f6-dfccac7b4d0c', 'Y', '永源', '0');
INSERT INTO `biz_brand` VALUES ('0ef4c047-72b4-4043-9e6d-8407fa90af66', 'H', '华骐', '0');
INSERT INTO `biz_brand` VALUES ('1240768a-4f0e-4ddc-8d64-d6d35f0a79bc', 'Y', '野马汽车', '0');
INSERT INTO `biz_brand` VALUES ('163cee3c-1384-483a-900f-bc579800c884', 'L', '路特斯', '0');
INSERT INTO `biz_brand` VALUES ('180d97f3-933c-4915-a077-88514e784b52', 'W', '威麟', '0');
INSERT INTO `biz_brand` VALUES ('182b9a29-a77d-4c3f-83f9-717133fbce0b', 'Z', '之诺', '0');
INSERT INTO `biz_brand` VALUES ('1d4d51e1-a796-46a9-9119-01bcc03f4f0b', 'J', '金旅', '0');
INSERT INTO `biz_brand` VALUES ('1ed1b50b-55c3-40ad-90c1-6c6b2ae46a83', 'W', '沃克斯豪尔', '0');
INSERT INTO `biz_brand` VALUES ('1f452f5c-2d5b-4d00-bc9b-9b4be183fa67', 'Y', '云度', '0');
INSERT INTO `biz_brand` VALUES ('1f90243a-2637-4b52-820c-865244e3008b', 'S', '思铭', '0');
INSERT INTO `biz_brand` VALUES ('2163cc2d-4de7-4655-8d32-25da05200136', 'K', 'KTM', '0');
INSERT INTO `biz_brand` VALUES ('224d6bd2-ec25-405c-be4e-f652e2a89c76', 'A', '安凯客车', '0');
INSERT INTO `biz_brand` VALUES ('236047a4-43f1-431a-86ac-6ebe5244176b', 'D', '东风', '0');
INSERT INTO `biz_brand` VALUES ('261409b7-28a0-4539-8df6-08b7cbbfd4b0', 'L', '雷诺', '0');
INSERT INTO `biz_brand` VALUES ('29742eb8-ca1b-490c-be2d-3a6755fec1d2', 'M', 'MG', '0');
INSERT INTO `biz_brand` VALUES ('2b4007ec-f18d-4e43-81aa-5c76679cc6b8', 'Z', '众泰', '0');
INSERT INTO `biz_brand` VALUES ('2be1abde-6ef0-404f-9bd5-8303b3d49034', 'S', '世爵', '0');
INSERT INTO `biz_brand` VALUES ('2c33afa1-572a-42e0-b740-b844252c0d6d', 'Z', '中兴', '0');
INSERT INTO `biz_brand` VALUES ('2e5e69f7-4761-4ed0-b9bc-23719189a9a9', 'H', '华泰', '0');
INSERT INTO `biz_brand` VALUES ('31f42a53-131c-4b48-900f-3321ffc2e463', 'F', '福特', '0');
INSERT INTO `biz_brand` VALUES ('343d4e50-b496-41fb-a2fd-05eda5056879', 'H', '华泰新能源', '0');
INSERT INTO `biz_brand` VALUES ('34a5ab22-eca7-4eb8-a9c4-74e6b3651083', 'J', '捷豹', '0');
INSERT INTO `biz_brand` VALUES ('35642be5-8592-4a2f-aa2b-2654025d20aa', 'H', '汉腾汽车', '0');
INSERT INTO `biz_brand` VALUES ('356ad706-ecb3-40f4-be89-000549c03a51', 'J', '江铃集团新能源', '0');
INSERT INTO `biz_brand` VALUES ('3b02ef4a-4e5e-4ab8-9ab5-33f5f9c5869e', 'F', '福田', '0');
INSERT INTO `biz_brand` VALUES ('3c603aaa-d5fc-47a4-b583-48da8037612d', 'L', '理念', '0');
INSERT INTO `biz_brand` VALUES ('4038ef07-05b6-487e-ae11-e7d291900f69', 'H', '海格', '0');
INSERT INTO `biz_brand` VALUES ('40db23c3-3b03-47e3-b4a2-1c9697d58223', 'S', 'SWM斯威汽车', '0');
INSERT INTO `biz_brand` VALUES ('41a0606c-233e-4fbb-b2be-270b630a7d1f', 'G', '观致', '0');
INSERT INTO `biz_brand` VALUES ('41bc96df-30cf-4a20-9de8-2283bf682ca6', 'H', '红旗', '0');
INSERT INTO `biz_brand` VALUES ('432f0a16-81ce-4269-b9e3-1ad9246a0cff', 'K', '科尼塞克', '0');
INSERT INTO `biz_brand` VALUES ('48300801-8ca4-40ee-a386-a477793b6063', 'H', '恒天', '0');
INSERT INTO `biz_brand` VALUES ('4ae3a36e-8d71-4320-88fc-d12f36b3e088', 'L', '铃木', '0');
INSERT INTO `biz_brand` VALUES ('4f2201a6-ec0e-4425-8208-580f041fc9f6', 'G', '广汽吉奥', '0');
INSERT INTO `biz_brand` VALUES ('504e95aa-b775-4f32-9ea6-33c50b9be767', 'H', '华凯', '0');
INSERT INTO `biz_brand` VALUES ('567ff173-8d66-4c96-a7a0-0dd85f824828', 'W', '潍柴英致', '0');
INSERT INTO `biz_brand` VALUES ('56e130ce-8746-47a1-98e1-b4f12621589d', 'A', 'AC Schnitzer', '0');
INSERT INTO `biz_brand` VALUES ('57579161-3798-49e8-a2bb-4e97f273471a', 'F', '福田乘用车', '0');
INSERT INTO `biz_brand` VALUES ('57ccaf2a-c68d-41ea-93c1-17b4f49d88a1', 'H', '华利', '0');
INSERT INTO `biz_brand` VALUES ('5af9b588-d3a2-4db7-a5f8-708181139842', 'K', '凯翼', '0');
INSERT INTO `biz_brand` VALUES ('5B41C477-FBD1-490F-8A29-8DB6C8752D51', 'A', '奥拓', null);
INSERT INTO `biz_brand` VALUES ('5B41C477-FBD1-490F-8A29-8DB6C8752D52', 'A', '阿斯顿·马丁', '0');
INSERT INTO `biz_brand` VALUES ('5B41C477-FBD1-490F-8A29-8DB6C8752D53', 'A', '阿尔法·罗密欧', '0');
INSERT INTO `biz_brand` VALUES ('5B41C477-FBD1-490F-8A29-8DB6C8752D58', 'A', '奥迪', '0');
INSERT INTO `biz_brand` VALUES ('60653a6a-08be-4dfe-a0eb-4b1305f8a705', 'F', '菲亚特', '0');
INSERT INTO `biz_brand` VALUES ('60fd2e8c-16ac-46e8-a802-01a3d77e6a62', 'D', '东风风神', '0');
INSERT INTO `biz_brand` VALUES ('61ec78cf-1114-47ba-8eb4-869f726eb4fc', 'M', '马自达', '0');
INSERT INTO `biz_brand` VALUES ('61fb223d-8ae3-430f-b252-ae2522214ae5', 'L', '兰博基尼', '0');
INSERT INTO `biz_brand` VALUES ('63b66fb9-a720-452c-ae3a-30ada6551352', 'S', '斯柯达', '0');
INSERT INTO `biz_brand` VALUES ('65e45628-e5bf-48b3-84df-458e848e055c', 'Y', '一汽', '0');
INSERT INTO `biz_brand` VALUES ('6ba45f27-68fb-463c-bc50-808bd28d4291', 'L', '力帆汽车', '0');
INSERT INTO `biz_brand` VALUES ('6d562d88-6122-4143-b539-7d478cbf34f7', 'X', '西雅特', '0');
INSERT INTO `biz_brand` VALUES ('6e32a395-d744-4a90-817d-0538b340521f', 'L', '莲花汽车', '0');
INSERT INTO `biz_brand` VALUES ('7225a2e1-f04d-465a-84f8-2506e4085ab6', 'K', '凯佰赫', '0');
INSERT INTO `biz_brand` VALUES ('7330a329-0e92-46d1-b05a-500a62f244de', 'J', '金龙', '0');
INSERT INTO `biz_brand` VALUES ('745eba21-4cc0-4e81-add5-c71e413112ed', 'H', '悍马', '0');
INSERT INTO `biz_brand` VALUES ('7549ae50-e5fb-4b74-9160-560d68c81a5e', 'S', '双环', '0');
INSERT INTO `biz_brand` VALUES ('75ab6b85-df95-414c-af0c-14b50f0b233d', 'K', '克莱斯勒', '0');
INSERT INTO `biz_brand` VALUES ('75f848e0-5ffa-4613-ad0d-09d05d5e8e71', 'K', '卡威', '0');
INSERT INTO `biz_brand` VALUES ('76e709f9-8f40-4bc8-a8cf-d69ac2a0ad39', 'S', '上汽大通', '0');
INSERT INTO `biz_brand` VALUES ('78a20104-0f54-4d99-b49b-d8eaa021b0b2', 'D', '东风风行', '0');
INSERT INTO `biz_brand` VALUES ('7a1af8c2-c28d-4144-b0af-85f81d1c5acf', 'J', '江铃集团轻汽', '0');
INSERT INTO `biz_brand` VALUES ('7fec7ff4-8c99-4983-8fad-4be54ce0c3f0', 'K', '卡尔森', '0');
INSERT INTO `biz_brand` VALUES ('8011287e-9b00-412e-bc7b-d5975968c494', 'D', '东风风光', '0');
INSERT INTO `biz_brand` VALUES ('81f5c64d-0de1-4ff2-b2e7-bdcba76d3b7a', 'A', 'ALPINA', '0');
INSERT INTO `biz_brand` VALUES ('89b61a2f-b804-42a7-91c3-a76c995d7d0f', 'B', '北京汽车', '0');
INSERT INTO `biz_brand` VALUES ('89f62689-4b65-4709-8881-7f501ae6b524', 'L', '朗世', '0');
INSERT INTO `biz_brand` VALUES ('8ba0dc6a-1d00-4b17-8e0e-e4b62aa76e05', 'H', '华颂', '0');
INSERT INTO `biz_brand` VALUES ('8fd6be0f-94b1-423f-b6a7-457314ace5f7', 'G', '光冈', '0');
INSERT INTO `biz_brand` VALUES ('938a43cc-4d9b-4c3f-b2fc-b57ad0dc1ce8', 'D', '大发', '0');
INSERT INTO `biz_brand` VALUES ('9410a51b-3295-4c3e-93d2-f53e49855b58', 'D', '东风风度', '0');
INSERT INTO `biz_brand` VALUES ('9b0ef204-6bb9-479b-a1c5-e38796c7312f', 'T', '腾势', '0');
INSERT INTO `biz_brand` VALUES ('9f8b43d9-f4d4-4c22-92cb-7ceaf68fb207', 'Z', '中华', '0');
INSERT INTO `biz_brand` VALUES ('a20fbf56-680a-4e1e-b1d4-170f9cd1eeae', 'S', '斯巴鲁', '0');
INSERT INTO `biz_brand` VALUES ('a3826bfc-1a96-4e5c-aafb-dc48d5b6ab44', 'W', '五菱汽车', '0');
INSERT INTO `biz_brand` VALUES ('aa69605e-d2e8-4add-b258-1cf58834c993', 'Y', '驭胜', '0');
INSERT INTO `biz_brand` VALUES ('aafedc0d-b22a-4355-9243-f56a30834a96', 'J', '江淮', '0');
INSERT INTO `biz_brand` VALUES ('adbce978-763a-47af-9bfb-dc5086218552', 'K', '开瑞', '0');
INSERT INTO `biz_brand` VALUES ('ae10e60f-bf7f-4c64-8035-49fa78c3a942', 'H', '华普', '0');
INSERT INTO `biz_brand` VALUES ('b1bb9fde-c3f9-48f8-818b-3aff1d1cda4c', 'W', '威兹曼', '0');
INSERT INTO `biz_brand` VALUES ('b6b0705f-7fcc-40cd-8ffb-7d1f19841170', 'L', '领志', '0');
INSERT INTO `biz_brand` VALUES ('bb3e204a-d53b-4fab-b0d4-a89ddc9088b2', 'L', '陆风', '0');
INSERT INTO `biz_brand` VALUES ('bb5aecc2-ceac-4bdc-91fa-82474fcb6bb2', 'L', '蓝旗亚', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B01', 'B', '宝骏', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B02', 'B', '标致', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B03', 'B', '保时捷', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B04', 'B', '保斐利', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B05', 'B', '北京', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B06', 'B', '北汽制造', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B07', 'B', '北汽威旺', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B08', 'B', '北汽幻速', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B09', 'B', '北汽新能源', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B10', 'B', '北汽绅宝', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B11', 'B', '北汽道达', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B12', 'B', '奔腾', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B13', 'B', '宝沃', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B14', 'B', '宾利', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B15', 'B', '宾尼法利纳', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B16', 'B', '巴博斯', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B17', 'B', '布加迪', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B18', 'B', '拜腾', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5B19', 'B', '比速汽车', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5C41', 'B', '宝马', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5C42', 'B', '本田', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5C43', 'B', '别克', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5C44', 'B', '比亚迪', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5C48', 'B', '奔驰', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5CC1', 'Z', '长安', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5CC2', 'Z', '长城', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5CC3', 'C', '昌河', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5D01', 'D', '大众', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5D02', 'D', '道奇', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5F01', 'F', '法拉利', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5F02', 'F', '丰田', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5G01', 'G', 'GMC', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5H01', 'H', '哈弗', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5I01', 'I', 'Icona', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5J01', 'J', 'Jeep', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5K01', 'K', '凯迪拉克', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5L01', 'L', '劳斯莱斯', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5M01', 'M', '玛莎拉蒂', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5N01', 'N', '纳智捷', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5O01', 'O', '欧宝', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5P01', 'P', '帕加尼', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5Q01', 'Q', '奇瑞', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5R01', 'R', '荣威', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5S01', 'S', '三菱', null);
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5T01', 'T', '特斯拉', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5W01', 'W', '沃尔沃', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5X01', 'X', '现代', '0');
INSERT INTO `biz_brand` VALUES ('BEE4C639-1294-4D4D-95A1-8367235B5Y02', 'Y', '英菲尼迪', '0');
INSERT INTO `biz_brand` VALUES ('c17d5546-ae4f-492e-a7d0-bb10b6e514b6', 'D', '东风小康', '0');
INSERT INTO `biz_brand` VALUES ('c1e83717-b03b-48f6-83ca-417d29b646eb', 'G', '广汽传祺', '0');
INSERT INTO `biz_brand` VALUES ('c98aaec4-8d29-4c37-8e39-c8ff7e345e9c', 'H', '霍顿', '0');
INSERT INTO `biz_brand` VALUES ('d3c9ce34-d8ef-4b43-a024-01275b8111fb', 'S', '双龙', '0');
INSERT INTO `biz_brand` VALUES ('d3f1a7c9-6726-4cc5-a7f7-7f6a073d544c', 'Y', '蔚来', '0');
INSERT INTO `biz_brand` VALUES ('d6e811cb-ba5f-4157-8d20-31bf1150e854', 'Z', '知豆', '0');
INSERT INTO `biz_brand` VALUES ('d70c0060-37a2-40bc-9b17-22730268c0f9', 'C', '成功汽车', '0');
INSERT INTO `biz_brand` VALUES ('da26d46c-3009-461e-874e-001ff4c0388e', 'L', '路虎', '0');
INSERT INTO `biz_brand` VALUES ('db0ae8ec-3860-4182-ae33-0a2eadf38c84', 'W', '五十铃', '0');
INSERT INTO `biz_brand` VALUES ('dbcffc5a-6fa9-49d1-85ce-e655353add35', 'L', '林肯', '0');
INSERT INTO `biz_brand` VALUES ('dca00fe3-1d0e-45c7-b375-10e0691a6c21', 'X', '雪铁龙', '0');
INSERT INTO `biz_brand` VALUES ('dcdc4b44-cd97-46a5-be21-7d49c1ba7120', 'S', 'smart', '0');
INSERT INTO `biz_brand` VALUES ('e2561cac-0011-4c27-869a-253490a5c603', 'J', '九龙', '0');
INSERT INTO `biz_brand` VALUES ('e2fb8e78-f9e9-4513-a3e4-f5509a44fd87', 'D', '东南', '0');
INSERT INTO `biz_brand` VALUES ('ec5c6f24-76ba-4388-98e0-d407b2d8c7ac', 'L', '猎豹汽车', '0');
INSERT INTO `biz_brand` VALUES ('edcb6a4a-6fd2-4584-9594-b4da36d25908', 'J', '吉利汽车', '0');
INSERT INTO `biz_brand` VALUES ('f1c2bf1b-eff6-47a7-963a-e19508e6ef02', 'D', 'DS', '0');
INSERT INTO `biz_brand` VALUES ('f2199368-a7ad-4b66-b442-7bb1921e6cfb', 'H', '海马', '0');
INSERT INTO `biz_brand` VALUES ('f7cc1cc0-d7a5-4150-9bf9-af310d7b42b6', 'J', '江铃', '0');
INSERT INTO `biz_brand` VALUES ('f8082bda-7509-4809-b729-2c397d8ff180', 'F', '福迪', '0');
INSERT INTO `biz_brand` VALUES ('fbf27906-ee6b-4567-8a87-1b67ed25e9f1', 'H', '哈飞', '0');
INSERT INTO `biz_brand` VALUES ('fc828c91-ee8b-400c-b60b-7125c073802f', 'H', '黄海', '0');
INSERT INTO `biz_brand` VALUES ('fcf4a429-ff0e-4b1a-aee4-6f7d9b504bea', 'J', '金杯', '0');
INSERT INTO `biz_brand` VALUES ('ffdd01a7-2d6f-4003-a72b-7858ab0e1cb7', 'L', '雷克萨斯', '0');

-- ----------------------------
-- Table structure for `biz_feature_group`
-- ----------------------------
DROP TABLE IF EXISTS `biz_feature_group`;
CREATE TABLE `biz_feature_group` (
  `id` char(36) NOT NULL COMMENT '车型特征分组id',
  `code` varchar(50) NOT NULL COMMENT '特征组编码',
  `name` varchar(50) NOT NULL COMMENT '特征组名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车型特征分组表';

-- ----------------------------
-- Records of biz_feature_group
-- ----------------------------
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A01', 'logoShape', '车标框形');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A02', 'carType', '车型');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A03', 'logoPlacement', '车标位置');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A04', 'geshanjiegou', '格栅结构');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A05', 'bzhuyanse', 'B柱颜色');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A06', 'houchuangkuangxing', '后窗框形');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A07', 'dingmifengtiao', '顶密封条');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A08', 'shanggeshanxing', '上格栅形');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A09', 'geshanqianzhao', '格栅前照');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A10', 'geshanqianwu', '格栅前雾');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A11', 'qianbaohuban', '前包护板');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A12', 'frontxinnengyuanbiao', '新能源标');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A13', 'houchuangxiaochuang', '后窗小窗');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A95', 'carriageType', '厢型');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4A96', '', '款型');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B00', 'houchuangxiayan', '后窗下沿');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B01', 'menbashou', '门把手');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B02', 'menbashouyanse', '门把手颜色');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B03', 'menxianwei', '门线围');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B04', 'lungulunfu', '轮毂轮辐');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B05', 'youxianggaiwei', '邮箱盖位');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B06', 'sidexinnengyuanbiao', '新能源标');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B07', 'weidengweizhi', '尾灯位置');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B08', 'weidengxingzhuang', '尾灯形状');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B09', 'haopaikuangmei', '号牌框眉');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B10', 'haopaikuangxing', '号牌框形');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B11', 'haopaiweizhi', '号牌位置');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B12', 'gaoweizhidongdeng', '高位制动灯');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B13', 'houfangwudeng', '后防烟雾');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B14', 'paiqikouwei', '排气口位');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B15', 'waiguabeitai', '外挂备胎');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B16', 'backxinnengyuanbiao', '新能源标');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B18', 'weiyiraoliu', '尾翼扰流');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B19', 'xinglizhijia', '行李支架');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B20', 'beijitianxian', '背鯺天线');

-- ----------------------------
-- Table structure for `biz_feature_item`
-- ----------------------------
DROP TABLE IF EXISTS `biz_feature_item`;
CREATE TABLE `biz_feature_item` (
  `id` char(36) NOT NULL,
  `code` varchar(50) DEFAULT NULL COMMENT '特征项编码',
  `name` varchar(50) NOT NULL COMMENT '特征项名称',
  `parentId` char(36) NOT NULL COMMENT '所属特征组ID',
  `order` int(11) DEFAULT NULL COMMENT '显示顺序',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车型特征字典表';

-- ----------------------------
-- Records of biz_feature_item
-- ----------------------------
INSERT INTO `biz_feature_item` VALUES ('0DEB18F5-8022-4870-B197-CC954E4F250C', 'mianbao', '面包', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', '0', null);
INSERT INTO `biz_feature_item` VALUES ('AAAAAAAA-F586-1111-B5D8-C5370FX4F901', 'bense', '本色', 'C93DA75C-180A-4B6B-870C-70204A3F4A05', '1', 'B柱颜色');
INSERT INTO `biz_feature_item` VALUES ('AAAAAAAA-F586-1111-B5D8-C5370FX4F902', 'heise', '黑色', 'C93DA75C-180A-4B6B-870C-70204A3F4A05', '2', 'B柱颜色');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370F04F910', 'sanxiang', '三厢', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', '0', null);
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F910', 'tuoyuanxing', '椭圆形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '2', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F911', 'tixing', '梯形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '1', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F912', 'juxing', '矩形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '3', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F913', 'duobianxing', '多边形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '4', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F914', 'shaungchaung', '双窗', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '5', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AE3CFE70-F638-4839-ACD2-BD4B408905A6', 'xianghuo', '厢货', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', '0', null);
INSERT INTO `biz_feature_item` VALUES ('BBBBBBBB-F586-1111-B5D8-C5370FX4F901', 'shangxiafenli', '上下分离', 'C93DA75C-180A-4B6B-870C-70204A3F4A04', '1', '格栅结构');
INSERT INTO `biz_feature_item` VALUES ('BBBBBBBB-F586-1111-B5D8-C5370FX4F902', 'shangxiayiti', '上下一体', 'C93DA75C-180A-4B6B-870C-70204A3F4A04', '2', '格栅结构');
INSERT INTO `biz_feature_item` VALUES ('BBBBBBBB-F586-1111-B5D8-C5370FX4F903', 'zuoyoufenli', '左右分离', 'C93DA75C-180A-4B6B-870C-70204A3F4A04', '3', '格栅结构');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000000', 'yiziban', '翼子板', 'C93DA75C-180A-4B6B-870C-70204A3F4B07', '1', '尾灯位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000001', 'ruhouxiang', '入后厢', 'C93DA75C-180A-4B6B-870C-70204A3F4B07', '2', '尾灯位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000002', 'shanghouchuang', '上后窗', 'C93DA75C-180A-4B6B-870C-70204A3F4B07', '3', '尾灯位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000003', 'bianping', '扁平类', 'C93DA75C-180A-4B6B-870C-70204A3F4B08', '1', '尾灯形状');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000004', 'baomanxing', '饱满类', 'C93DA75C-180A-4B6B-870C-70204A3F4B08', '2', '尾灯形状');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000005', 'shutiaoxing', '竖条类', 'C93DA75C-180A-4B6B-870C-70204A3F4B08', '3', '尾灯形状');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000006', 'guantongxing', '贯通形', 'C93DA75C-180A-4B6B-870C-70204A3F4B08', '4', '尾灯形状');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000007', 'duojiegou', '多结构', 'C93DA75C-180A-4B6B-870C-70204A3F4B08', '5', '尾灯形状');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000008', 'wu', '无/隐', 'C93DA75C-180A-4B6B-870C-70204A3F4B09', '1', '号牌框眉');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000009', 'duanmei', '短眉', 'C93DA75C-180A-4B6B-870C-70204A3F4B09', '2', '号牌框眉');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000010', 'changmei', '长眉', 'C93DA75C-180A-4B6B-870C-70204A3F4B09', '3', '号牌框眉');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000011', 'wu', '无框形', 'C93DA75C-180A-4B6B-870C-70204A3F4B10', '1', '号牌框形');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000012', 'zhengtixing', '正梯形', 'C93DA75C-180A-4B6B-870C-70204A3F4B10', '2', '号牌框形');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000013', 'daotixing', '倒梯形', 'C93DA75C-180A-4B6B-870C-70204A3F4B10', '3', '号牌框形');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000014', 'juxing', '矩形', 'C93DA75C-180A-4B6B-870C-70204A3F4B10', '4', '号牌框形');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000015', 'qitaxing', '其他形', 'C93DA75C-180A-4B6B-870C-70204A3F4B10', '5', '号牌框形');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000016', 'chaogao', '超高', 'C93DA75C-180A-4B6B-870C-70204A3F4B11', '1', '号牌位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000017', 'pingqi', '平齐', 'C93DA75C-180A-4B6B-870C-70204A3F4B11', '2', '号牌位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000018', 'xiacheng', '下沉', 'C93DA75C-180A-4B6B-870C-70204A3F4B11', '3', '号牌位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000019', 'dibu', '底部', 'C93DA75C-180A-4B6B-870C-70204A3F4B11', '4', '号牌位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000020', 'feijuzhong', '非居中', 'C93DA75C-180A-4B6B-870C-70204A3F4B11', '5', '号牌位置');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000021', 'dingweiyi', '顶尾翼', 'C93DA75C-180A-4B6B-870C-70204A3F4B12', '1', '高位制动灯');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000022', 'shangchuangyan', '上窗沿', 'C93DA75C-180A-4B6B-870C-70204A3F4B12', '2', '高位制动灯');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000023', 'xiachuangyan', '下窗沿', 'C93DA75C-180A-4B6B-870C-70204A3F4B12', '3', '高位制动灯');
INSERT INTO `biz_feature_item` VALUES ('C93DA75C-180A-4B6B-870C-000000000024', 'houxianggai', '后箱盖', 'C93DA75C-180A-4B6B-870C-70204A3F4B12', '4', '高位制动灯');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F901', 'suv', 'SUV', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '1', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F902', 'jiaoche', '轿车', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '2', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F903', 'pika', '皮卡', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '3', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F904', 'qingka', '轻卡', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '4', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F905', 'zhongka', '重卡', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '5', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F906', 'pingtouche', '平头车', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '6', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F907', 'tutouche', '凸头车', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '7', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F908', 'xietouche', '斜头车', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '8', '车型');
INSERT INTO `biz_feature_item` VALUES ('DC82D8F8-0904-4596-93EC-A5E368DD8129', 'liangxiang', '两厢', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', null, null);
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F901', 'geshanshang', '格栅上', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '1', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F902', 'geshanyan', '格栅沿', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '2', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F903', 'geshannei', '格栅内', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '3', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F904', 'geshanjian', '格栅间', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '4', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F901', null, '正圆形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '1', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F902', null, '椭圆形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '2', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F903', null, '正方形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '3', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F904', null, '长方形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '4', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F905', null, '盾牌形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '5', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F906', null, '复杂形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '6', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('FFFFFFFF-F586-1111-B5D8-C5370FX4F901', 'hengxiang', '横向', 'C93DA75C-180A-4B6B-870C-70204A3F4A08', '1', '上格栅形');
INSERT INTO `biz_feature_item` VALUES ('FFFFFFFF-F586-1111-B5D8-C5370FX4F902', 'zongxiang', '纵向', 'C93DA75C-180A-4B6B-870C-70204A3F4A08', '2', '上格栅形');
INSERT INTO `biz_feature_item` VALUES ('FFFFFFFF-F586-1111-B5D8-C5370FX4F903', 'fengwo', '蜂窝', 'C93DA75C-180A-4B6B-870C-70204A3F4A08', '3', '上格栅形');
INSERT INTO `biz_feature_item` VALUES ('FFFFFFFF-F586-1111-B5D8-C5370FX4F904', 'fengbi', '封闭形', 'C93DA75C-180A-4B6B-870C-70204A3F4A08', '4', '上格栅形');
INSERT INTO `biz_feature_item` VALUES ('FFFFFFFF-F586-1111-B5D8-C5370FX4F905', 'qita', '其它', 'C93DA75C-180A-4B6B-870C-70204A3F4A08', '5', '上格栅形');
INSERT INTO `biz_feature_item` VALUES ('GGGGGGGG-F586-1111-B5D8-C5370FX4F901', 'lianti', '联体', 'C93DA75C-180A-4B6B-870C-70204A3F4A09', '1', '格栅前照');
INSERT INTO `biz_feature_item` VALUES ('GGGGGGGG-F586-1111-B5D8-C5370FX4F902', 'fenli', '分离', 'C93DA75C-180A-4B6B-870C-70204A3F4A09', '2', '格栅前照');
INSERT INTO `biz_feature_item` VALUES ('HHHHHHHH-F586-1111-B5D8-C5370FX4F901', 'lianti', '联体', 'C93DA75C-180A-4B6B-870C-70204A3F4A10', '1', '格栅前雾');
INSERT INTO `biz_feature_item` VALUES ('HHHHHHHH-F586-1111-B5D8-C5370FX4F902', 'fenli', '分离', 'C93DA75C-180A-4B6B-870C-70204A3F4A10', '2', '格栅前雾');
INSERT INTO `biz_feature_item` VALUES ('IIIIIIII-F586-1111-B5D8-C5370FX4F901', '', '无/隐', 'C93DA75C-180A-4B6B-870C-70204A3F4A11', '1', '前包护板');
INSERT INTO `biz_feature_item` VALUES ('IIIIIIII-F586-1111-B5D8-C5370FX4F902', null, '白色', 'C93DA75C-180A-4B6B-870C-70204A3F4A11', '2', '前包护板');
INSERT INTO `biz_feature_item` VALUES ('IIIIIIII-F586-1111-B5D8-C5370FX4F903', null, '黑色', 'C93DA75C-180A-4B6B-870C-70204A3F4A11', '3', '前包护板');
INSERT INTO `biz_feature_item` VALUES ('JJJJJJJJ-F586-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4A12', '1', '新能源标');
INSERT INTO `biz_feature_item` VALUES ('JJJJJJJJ-F586-1111-B5D8-C5370FX4F902', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4A12', '2', '新能源标');
INSERT INTO `biz_feature_item` VALUES ('KKKKKKKK-F586-1111-B5D8-C5370FX4F901', 'wuxiaochuang', '无小窗', 'C93DA75C-180A-4B6B-870C-70204A3F4A13', '1', '后窗小窗');
INSERT INTO `biz_feature_item` VALUES ('KKKKKKKK-F586-1111-B5D8-C5370FX4F902', 'sanjiaochuang', '三角窗', 'C93DA75C-180A-4B6B-870C-70204A3F4A13', '2', '后窗小窗');
INSERT INTO `biz_feature_item` VALUES ('KKKKKKKK-F586-1111-B5D8-C5370FX4F903', 'dxingchuang', 'D形窗', 'C93DA75C-180A-4B6B-870C-70204A3F4A13', '3', '后窗小窗');
INSERT INTO `biz_feature_item` VALUES ('KKKKKKKK-F586-1111-B5D8-C5370FX4F904', 'sanjiaoban', '三角板', 'C93DA75C-180A-4B6B-870C-70204A3F4A13', '4', '后窗小窗');
INSERT INTO `biz_feature_item` VALUES ('KKKKKKKK-F586-1111-B5D8-C5370FX4F905', 'liangxiaochuang', '两小窗', 'C93DA75C-180A-4B6B-870C-70204A3F4A13', '5', '后窗小窗');
INSERT INTO `biz_feature_item` VALUES ('LLLLLLLL-F586-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B13', '1', '后防烟雾');
INSERT INTO `biz_feature_item` VALUES ('LLLLLLLL-F586-1111-B5D8-C5370FX4F902', 'juzhong', '居中', 'C93DA75C-180A-4B6B-870C-70204A3F4B13', '2', '后防烟雾');
INSERT INTO `biz_feature_item` VALUES ('LLLLLLLL-F586-1111-B5D8-C5370FX4F903', 'zuoce', '左侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B13', '3', '后防烟雾');
INSERT INTO `biz_feature_item` VALUES ('LLLLLLLL-F586-1111-B5D8-C5370FX4F904', 'youce', '右侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B13', '4', '后防烟雾');
INSERT INTO `biz_feature_item` VALUES ('LLLLLLLL-F586-1111-B5D8-C5370FX4F905', 'shuangce', '双侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B13', '5', '后防烟雾');
INSERT INTO `biz_feature_item` VALUES ('LLLLLLLL-F586-1111-B5D8-C5370FX4F906', 'zuozhongyou', '三个', 'C93DA75C-180A-4B6B-870C-70204A3F4B13', '6', '后防烟雾');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM1-F586-1111-B5D8-C5370FX4F901', 'quankaifang', '全开放', 'C93DA75C-180A-4B6B-870C-70204A3F4B01', '1', '门把手');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM1-F586-1111-B5D8-C5370FX4F902', 'bankaifang', '半开放', 'C93DA75C-180A-4B6B-870C-70204A3F4B01', '2', '门把手');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM1-F586-1111-B5D8-C5370FX4F903', 'qianhouyoubie', '前后有别', 'C93DA75C-180A-4B6B-870C-70204A3F4B01', '3', '门把手');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM2-F586-1111-B5D8-C5370FX4F901', null, '本色', 'C93DA75C-180A-4B6B-870C-70204A3F4B02', '1', '门把手颜色');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM2-F586-1111-B5D8-C5370FX4F902', null, '镀铬', 'C93DA75C-180A-4B6B-870C-70204A3F4B02', '2', '门把手颜色');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM2-F586-1111-B5D8-C5370FX4F903', null, '黑色', 'C93DA75C-180A-4B6B-870C-70204A3F4B02', '3', '门把手颜色');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM3-F586-1111-B5D8-C5370FX4F901', 'wu', '无/隐', 'C93DA75C-180A-4B6B-870C-70204A3F4B03', '1', '门线围');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM3-F586-1111-B5D8-C5370FX4F902', 'yaoxian', '腰线', 'C93DA75C-180A-4B6B-870C-70204A3F4B03', '2', '门线围');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM3-F586-1111-B5D8-C5370FX4F903', 'cewei', '侧围', 'C93DA75C-180A-4B6B-870C-70204A3F4B03', '3', '门线围');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM3-F586-1111-B5D8-C5370FX4F904', 'jiaoxian', '脚线', 'C93DA75C-180A-4B6B-870C-70204A3F4B03', '4', '门线围');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM4-F586-1111-B5D8-C5370FX4F901', 'sanfu', '三辐', 'C93DA75C-180A-4B6B-870C-70204A3F4B04', '1', '轮毂轮辐');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM4-F586-1111-B5D8-C5370FX4F902', 'sifu', '四辐', 'C93DA75C-180A-4B6B-870C-70204A3F4B04', '2', '轮毂轮辐');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM4-F586-1111-B5D8-C5370FX4F903', 'wufu', '五辐', 'C93DA75C-180A-4B6B-870C-70204A3F4B04', '3', '轮毂轮辐');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM4-F586-1111-B5D8-C5370FX4F904', 'liufu', '六辐', 'C93DA75C-180A-4B6B-870C-70204A3F4B04', '4', '轮毂轮辐');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM4-F586-1111-B5D8-C5370FX4F905', 'qifu', '七辐及以上', 'C93DA75C-180A-4B6B-870C-70204A3F4B04', '5', '轮毂轮辐');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM4-F586-1111-B5D8-C5370FX4F906', 'qita', '其他', 'C93DA75C-180A-4B6B-870C-70204A3F4B04', '6', '轮毂轮辐');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM5-F586-1111-B5D8-C5370FX4F901', null, '左侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B05', '1', '油箱盖位');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM5-F586-1111-B5D8-C5370FX4F902', null, '右侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B05', '2', '油箱盖位');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM6-F586-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B06', '1', '新能源标');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMM6-F586-1111-B5D8-C5370FX4F902', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4B06', '2', '新能源标');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMMM-F586-1111-B5D8-C5370FX4F901', 'pingzhi', '平直', 'C93DA75C-180A-4B6B-870C-70204A3F4B00', '1', '后窗下沿');
INSERT INTO `biz_feature_item` VALUES ('MMMMMMMM-F586-1111-B5D8-C5370FX4F902', 'shangqiao', '上翘', 'C93DA75C-180A-4B6B-870C-70204A3F4B00', '2', '后窗下沿');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-AAAA-1111-B5D8-C5370FX4F901', 'wu', '隐形', 'C93DA75C-180A-4B6B-870C-70204A3F4B14', '1', '排气口位');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-AAAA-1111-B5D8-C5370FX4F902', 'zuoce', '左侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B14', '2', '排气口位');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-AAAA-1111-B5D8-C5370FX4F903', 'youce', '右侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B14', '3', '排气口位');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-AAAA-1111-B5D8-C5370FX4F904', 'shaungce', '双侧', 'C93DA75C-180A-4B6B-870C-70204A3F4B14', '4', '排气口位');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-AAAA-1111-B5D8-C5370FX4F905', 'juzhong', '居中', 'C93DA75C-180A-4B6B-870C-70204A3F4B14', '5', '排气口位');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-BBBB-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B16', '1', '新能源标');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-BBBB-1111-B5D8-C5370FX4F902', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4B16', '2', '新能源标');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-CCCC-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B15', '1', '外挂备胎');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-CCCC-1111-B5D8-C5370FX4F902', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4B15', '2', '外挂备胎');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-DDDD-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B18', '1', '尾翼扰流');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-DDDD-1111-B5D8-C5370FX4F902', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4B18', '2', '尾翼扰流');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-EEEE-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B19', '1', '行李支架');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-EEEE-1111-B5D8-C5370FX4F902', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4B19', '2', '行李支架');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-FFFF-1111-B5D8-C5370FX4F901', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B20', '1', '背鯺天线');
INSERT INTO `biz_feature_item` VALUES ('TTTTTTTT-FFFF-1111-B5D8-C5370FX4F902', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4B20', '2', '背鯺天线');
INSERT INTO `biz_feature_item` VALUES ('XC4D1C92-F586-1111-B5D8-C5370FX4F901', 'bense', '本色', 'C93DA75C-180A-4B6B-870C-70204A3F4A07', '1', '顶密封条');
INSERT INTO `biz_feature_item` VALUES ('XC4D1C92-F586-1111-B5D8-C5370FX4F902', 'heise', '黑色', 'C93DA75C-180A-4B6B-870C-70204A3F4A07', '2', '顶密封条');

-- ----------------------------
-- Table structure for `biz_model`
-- ----------------------------
DROP TABLE IF EXISTS `biz_model`;
CREATE TABLE `biz_model` (
  `id` char(36) NOT NULL,
  `Name` varchar(50) DEFAULT NULL COMMENT '车辆款型名称',
  `Parentid` char(36) DEFAULT NULL COMMENT '款型父id(车辆年款id)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_model
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_series`
-- ----------------------------
DROP TABLE IF EXISTS `biz_series`;
CREATE TABLE `biz_series` (
  `id` char(36) NOT NULL,
  `Name` varchar(50) DEFAULT NULL COMMENT '车系名称',
  `parentid` char(36) DEFAULT NULL COMMENT '车系上级(品牌)id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_series
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_vehicle`
-- ----------------------------
DROP TABLE IF EXISTS `biz_vehicle`;
CREATE TABLE `biz_vehicle` (
  `id` char(36) NOT NULL,
  `hotindex` int(11) DEFAULT NULL COMMENT '热门指数',
  `brand` char(36) DEFAULT NULL COMMENT '品牌',
  `series` char(36) DEFAULT NULL COMMENT '车系',
  `modelyear` char(36) DEFAULT NULL COMMENT '年份',
  `model` char(36) DEFAULT NULL COMMENT '车辆款型',
  `type` char(36) DEFAULT NULL COMMENT '车型',
  `xiangxing` char(36) DEFAULT NULL,
  `chebiaokuangxing` char(36) DEFAULT NULL COMMENT '车标框形',
  `chebiaoweizhi` char(36) DEFAULT NULL COMMENT '车标位置',
  `geshanjiegou` char(36) DEFAULT NULL COMMENT '格栅结构',
  `shanggeshanxing` char(36) DEFAULT NULL COMMENT '上格栅形',
  `geshanqianwu` char(36) DEFAULT NULL COMMENT '格栅前雾',
  `geshanqianzhao` char(36) DEFAULT NULL COMMENT '格栅前照',
  `qianbaohuban` char(36) DEFAULT NULL COMMENT '前标护板',
  `frontxinnengyuanbiao` char(36) DEFAULT NULL COMMENT '正前-新能源标',
  `bzhuyanse` char(36) DEFAULT NULL COMMENT 'B柱颜色',
  `houchuangxiaochuang` char(36) DEFAULT NULL COMMENT '后窗小窗',
  `houchuangxiayan` char(36) DEFAULT NULL COMMENT '后窗小窗',
  `menbashou` char(36) DEFAULT NULL COMMENT '门把手',
  `menbashouyanse` char(36) DEFAULT NULL COMMENT '门把手颜色',
  `menxianwei` char(36) DEFAULT NULL COMMENT '门线位',
  `lungulunfu` char(36) DEFAULT NULL COMMENT '轮毂轮辐',
  `youxianggaiwei` char(36) DEFAULT NULL COMMENT '油箱盖位',
  `sidexinnengyuanbiao` char(36) DEFAULT NULL COMMENT '侧面-新能源标',
  `houchuangkuangxing` char(36) DEFAULT NULL COMMENT '后窗框形',
  `weidengweizhi` char(36) DEFAULT NULL COMMENT '尾灯位置',
  `weidengxingzhuang` char(36) DEFAULT NULL COMMENT '尾灯形状',
  `haopaikuangmei` char(36) DEFAULT NULL COMMENT '号牌框眉',
  `haopaikuangxing` char(36) DEFAULT NULL COMMENT '号牌框形',
  `haopaiweizhi` char(36) DEFAULT NULL COMMENT '号牌位置',
  `gaoweizhidongdeng` char(36) DEFAULT NULL COMMENT '高位制动灯',
  `houfangwudeng` char(36) DEFAULT NULL COMMENT '后防雾灯',
  `paiqikouwei` char(36) DEFAULT NULL COMMENT '排气口位',
  `backxinnengyuanbiao` char(36) DEFAULT NULL COMMENT '后面-新能源标',
  `waiguabeitai` char(36) DEFAULT NULL COMMENT '外挂备胎',
  `dingmifengtiao` char(36) DEFAULT NULL COMMENT '顶密封条',
  `weiyiraoliu` char(36) DEFAULT NULL COMMENT '尾翼扰流',
  `xinglizhijia` char(36) DEFAULT NULL COMMENT '行李支架',
  `beijitianxian` char(36) DEFAULT NULL COMMENT '背鳍天线',
  `creator` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `hotindexSort` tinyint(1) NOT NULL DEFAULT '0',
  `brandInitials` char(1) NOT NULL DEFAULT '',
  `image` varchar(150) DEFAULT '',
  `yearCode` int(4) NOT NULL DEFAULT '0',
  `brandName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `index_brandInitials_brandName_hotindexSort` (`brandInitials`,`brandName`,`hotindexSort`),
  KEY `index_id` (`id`),
  KEY `index_brand` (`brand`),
  KEY `index_series` (`series`),
  KEY `index_model` (`model`),
  KEY `index_modelyear` (`modelyear`),
  KEY `index_type` (`type`),
  KEY `index_hotindex` (`hotindex`),
  KEY `index_dingmifengtiao` (`dingmifengtiao`),
  KEY `index_chebiaokuangxing` (`chebiaokuangxing`),
  KEY `index_xiangxing` (`xiangxing`),
  KEY `index_chebiaoweizhi` (`chebiaoweizhi`),
  KEY `index_geshanjiegou` (`geshanjiegou`),
  KEY `index_shanggeshanxing` (`shanggeshanxing`),
  KEY `index_geshanqianwu` (`geshanqianwu`),
  KEY `index_geshanqianzhao` (`geshanqianzhao`),
  KEY `index_qianbaohuban` (`qianbaohuban`),
  KEY `index_frontxinnengyuanbiao` (`frontxinnengyuanbiao`),
  KEY `index_bzhuyanse` (`bzhuyanse`),
  KEY `index_houchuangxiaochuang` (`houchuangxiaochuang`),
  KEY `index_houchuangxiayan` (`houchuangxiayan`),
  KEY `index_menbashou` (`menbashou`),
  KEY `index_menbashouyanse` (`menbashouyanse`),
  KEY `index_menxianwei` (`menxianwei`),
  KEY `index_lungulunfu` (`lungulunfu`),
  KEY `index_youxianggaiwei` (`youxianggaiwei`),
  KEY `index_sidexinnengyuanbiao` (`sidexinnengyuanbiao`),
  KEY `index_houchuangkuangxing` (`houchuangkuangxing`),
  KEY `index_weidengweizhi` (`weidengweizhi`),
  KEY `index_weidengxingzhuang` (`weidengxingzhuang`),
  KEY `index_haopaikuangmei` (`haopaikuangmei`),
  KEY `index_haopaikuangxing` (`haopaikuangxing`),
  KEY `index_haopaiweizhi` (`haopaiweizhi`),
  KEY `index_gaoweizhidongdeng` (`gaoweizhidongdeng`),
  KEY `index_houfangwudeng` (`houfangwudeng`),
  KEY `index_paiqikouwei` (`paiqikouwei`),
  KEY `index_backxinnengyuanbiao` (`backxinnengyuanbiao`),
  KEY `index_waiguabeitai` (`waiguabeitai`),
  KEY `index_weiyiraoliu` (`weiyiraoliu`),
  KEY `index_xinglizhijia` (`xinglizhijia`),
  KEY `index_beijitianxian` (`beijitianxian`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_vehicle
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_vehicle_picture`
-- ----------------------------
DROP TABLE IF EXISTS `biz_vehicle_picture`;
CREATE TABLE `biz_vehicle_picture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentid` char(36) DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(256) DEFAULT NULL,
  `originname` varchar(200) DEFAULT NULL,
  `bigname` varchar(200) DEFAULT NULL,
  `mediumname` varchar(200) DEFAULT NULL,
  `smallname` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_id` (`id`),
  KEY `index_parentid` (`parentid`)
) ENGINE=InnoDB AUTO_INCREMENT=50033 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_vehicle_picture
-- ----------------------------

-- ----------------------------
-- Table structure for `biz_year`
-- ----------------------------
DROP TABLE IF EXISTS `biz_year`;
CREATE TABLE `biz_year` (
  `id` char(36) NOT NULL,
  `code` int(11) DEFAULT NULL COMMENT '年份编码',
  `Name` varchar(50) DEFAULT NULL COMMENT '年份名称',
  `Parentid` char(36) DEFAULT NULL COMMENT '年份上级(车系)id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_year
-- ----------------------------

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_username` varchar(45) NOT NULL,
  `log_operation_time` datetime NOT NULL,
  `log_operation` varchar(45) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6219 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `requireAuth` bit(1) DEFAULT NULL,
  `order` int(3) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, null, '所有', null, null, '', '1', '', '*:*');
INSERT INTO `menu` VALUES ('2', '/manage', 'components/Manage', '后台管理', '\r\nel-icon-setting', '1', '', '11', '', '');
INSERT INTO `menu` VALUES ('7', '/role/index', 'components/manage/role/List', '角色管理', 'el-icon-star-off\r\n', '2', '', '117', '', 'role:*');
INSERT INTO `menu` VALUES ('8', '/user/index', 'components/manage/user/List', '用户管理', 'el-icon-icare-users', '2', '', '114', '', 'user:*');
INSERT INTO `menu` VALUES ('9', '/car/add', 'components/manage/car/Add', '车型录入', 'el-icon-icare-car', '2', '', '111', '', 'car:add');
INSERT INTO `menu` VALUES ('10', '/car/list', 'components/manage/car/List', '车型库', '\r\nel-icon-icare-cars', '2', '', '112', '', 'car:list');
INSERT INTO `menu` VALUES ('11', '/log/list', 'components/manage/log/List', '日志管理', 'el-icon-icare-logs', '2', '', '115', '', 'log:*');
INSERT INTO `menu` VALUES ('12', '/import', 'components/manage/import', '品牌管理', 'el-icon-icare-brand', '2', '', '116', '', 'import:*');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `isBackend` bit(1) DEFAULT NULL COMMENT '是否后端角色(1:是，0:否)',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `enabled` bit(1) NOT NULL COMMENT '是否启用',
  `isAdmin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'administrator', '管理员', '', '管理员', '', '');
INSERT INTO `role` VALUES ('2', 'manager', '管理人员', '', '管理人员', '', '');
INSERT INTO `role` VALUES ('9', 'user', '普通用户', '', '普通用户', '', '');
INSERT INTO `role` VALUES ('12', 'editor', '录入人员', '', '录入人员', '', '');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `roleId` bigint(20) NOT NULL,
  `menuId` bigint(20) NOT NULL,
  KEY `menuId` (`menuId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`menuId`) REFERENCES `menu` (`id`),
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('12', '9');
INSERT INTO `role_menu` VALUES ('12', '10');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐值',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `creationUserId` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `isBackend` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否后台用户(1:是，0:否)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '1', null, '2018-02-16 21:51:49', '');
INSERT INTO `user` VALUES ('2', '管理人员', 'manage', 'e10adc3949ba59abbe56e057f20f883e', 'manage', '1', null, '2018-03-16 10:45:48', '');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('1', '1');

-- ----------------------------
-- View structure for `view_biz_vehicle`
-- ----------------------------
DROP VIEW IF EXISTS `view_biz_vehicle`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_biz_vehicle` AS select `v`.`id` AS `id`,`v`.`image` AS `image`,`b`.`id` AS `brandId`,`b`.`Name` AS `brand`,`b`.`initials` AS `initials`,`s`.`id` AS `seriesId`,`s`.`Name` AS `series`,`y`.`id` AS `yearId`,`y`.`Name` AS `modelyear`,`y`.`code` AS `yearCode`,`m`.`id` AS `modelId`,`m`.`Name` AS `model` from ((((`biz_vehicle` `v` left join `biz_brand` `b` on((`v`.`brand` = `b`.`id`))) left join `biz_series` `s` on((`s`.`id` = `v`.`series`))) left join `biz_year` `y` on((`v`.`modelyear` = `y`.`id`))) left join `biz_model` `m` on((`v`.`model` = `m`.`id`))) order by `v`.`brandInitials`,`v`.`brandName`,`v`.`hotindexSort` ;
