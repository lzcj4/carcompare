/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : car_compare_test

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-13 15:42:03
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
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B21', 'saishi', '腮饰');
INSERT INTO `biz_feature_group` VALUES ('C93DA75C-180A-4B6B-870C-70204A3F4B22', 'chechuangneikuang', '侧窗框');

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
INSERT INTO `biz_feature_item` VALUES ('0DEB18F5-8022-4870-B197-CC954E4F250C', 'mianbao', '面包', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', '2', '厢形');
INSERT INTO `biz_feature_item` VALUES ('AAAAAAA-BBBB-5555-C5U8-X5172FU4F601', 'wu', '无', 'C93DA75C-180A-4B6B-870C-70204A3F4B21', '1', '腮饰');
INSERT INTO `biz_feature_item` VALUES ('AAAAAAA-BBBB-5555-C5U8-X5172FU4F602', 'you', '有', 'C93DA75C-180A-4B6B-870C-70204A3F4B21', '2', '腮饰');
INSERT INTO `biz_feature_item` VALUES ('AAAAAAAA-F586-1111-B5D8-C5370FX4F901', 'bense', '本色', 'C93DA75C-180A-4B6B-870C-70204A3F4A05', '1', 'B柱颜色');
INSERT INTO `biz_feature_item` VALUES ('AAAAAAAA-F586-1111-B5D8-C5370FX4F902', 'heise', '黑色', 'C93DA75C-180A-4B6B-870C-70204A3F4A05', '2', 'B柱颜色');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370F04F910', 'sanxiang', '三厢', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', '3', '厢形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F910', 'tuoyuanxing', '椭圆形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '2', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F911', 'tixing', '梯形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '1', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F912', 'juxing', '矩形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '3', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F913', 'duobianxing', '多边形', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '4', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AC4D1C92-F586-4879-B5D8-C5370FX4F914', 'shaungchaung', '双窗', 'C93DA75C-180A-4B6B-870C-70204A3F4A06', '5', '后窗框形');
INSERT INTO `biz_feature_item` VALUES ('AE3CFE70-F638-4839-ACD2-BD4B408905A6', 'xianghuo', '厢货', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', '4', '厢形');
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
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F904', 'qingka', '轻卡', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '7', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F905', 'zhongka', '重卡', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '8', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F906', 'pingtouche', '平头车', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '4', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F907', 'tutouche', '凸头车', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '5', '车型');
INSERT INTO `biz_feature_item` VALUES ('CCCCCCCC-F586-1111-B5D8-C5370FX4F908', 'xietouche', '斜头车', 'C93DA75C-180A-4B6B-870C-70204A3F4A02', '6', '车型');
INSERT INTO `biz_feature_item` VALUES ('DC82D8F8-0904-4596-93EC-A5E368DD8129', 'liangxiang', '两厢', 'C93DA75C-180A-4B6B-870C-70204A3F4A95', '1', '厢形');
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F901', 'geshanshang', '格栅上', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '1', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F902', 'geshanyan', '格栅沿', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '2', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F903', 'geshannei', '格栅内', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '3', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('DDDDDDDD-F586-1111-B5D8-C5370FX4F904', 'geshanjian', '格栅间', 'C93DA75C-180A-4B6B-870C-70204A3F4A03', '4', '车标位置');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F901', 'zhenyuanxing', '正圆形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '1', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F902', 'tuoyuanxing', '椭圆形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '2', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F903', 'zhengfangxing', '正方形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '3', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F904', 'changfangxing', '长方形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '4', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F905', 'dunpaixing', '盾牌形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '5', '车标框形');
INSERT INTO `biz_feature_item` VALUES ('EEEEEEEE-F586-1111-B5D8-C5370FX4F906', 'fuzhaxing', '复杂形', 'C93DA75C-180A-4B6B-870C-70204A3F4A01', '6', '车标框形');
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
INSERT INTO `biz_feature_item` VALUES ('X5ODA726-280A-716C-U704-000000000001', 'buxiugang', '加不锈钢', 'C93DA75C-180A-4B6B-870C-70204A3F4B22', '1', '侧窗框');
INSERT INTO `biz_feature_item` VALUES ('X5ODA726-280A-716C-U704-000000000002', 'heise', '黑色', 'C93DA75C-180A-4B6B-870C-70204A3F4B22', '2', '侧窗框');
INSERT INTO `biz_feature_item` VALUES ('X5ODA726-280A-716C-U704-000000000003', 'bense', '本色', 'C93DA75C-180A-4B6B-870C-70204A3F4B22', '3', '侧窗框');
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
  `saishi` char(36) DEFAULT NULL COMMENT '腮饰',
  `chechuangneikuang` char(36) DEFAULT NULL COMMENT '侧窗框',
  `creator` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `hotindexSort` tinyint(1) NOT NULL DEFAULT '0',
  `brandInitials` char(1) NOT NULL DEFAULT '',
  `image` varchar(150) DEFAULT '',
  `yearCode` int(4) NOT NULL DEFAULT '0',
  `brandName` varchar(50) NOT NULL DEFAULT '',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0:未审核 1:已审核',
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
  KEY `index_beijitianxian` (`beijitianxian`),
  KEY `index_status` (`status`) USING BTREE,
  KEY `index_saishi` (`saishi`) USING BTREE,
  KEY `index_chechuangneikuang` (`chechuangneikuang`) USING BTREE
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
) ENGINE=InnoDB AUTO_INCREMENT=50070 DEFAULT CHARSET=utf8;

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
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '操作人',
  `time` datetime NOT NULL COMMENT '操作时间',
  `type` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `description` varchar(200) DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`),
  KEY `index_username` (`username`) USING BTREE,
  KEY `index_time` (`time`) USING BTREE,
  KEY `index_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=806 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(50) DEFAULT NULL,
  `component` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `requireAuth` bit(1) DEFAULT NULL,
  `order` int(3) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `roleName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, null, '所有', null, null, '', '1', '', '*:*');
INSERT INTO `menu` VALUES ('2', '/manage', 'components/Manage', '后台管理', 'el-icon-setting', '1', '', '11', '', '');
INSERT INTO `menu` VALUES ('7', '/role/index', 'components/manage/role/List', '角色管理', 'el-icon-star-off\r\n', '2', '', '110', '', 'role:*');
INSERT INTO `menu` VALUES ('8', '/user/index', 'components/manage/user/List', '用户管理', 'el-icon-icare-users', '2', '', '120', '', 'user:*');
INSERT INTO `menu` VALUES ('9', '/car/add', 'components/manage/car/Add', '车型录入', 'el-icon-icare-car', '2', '', '130', '', 'car:add');
INSERT INTO `menu` VALUES ('10', '/car/preaudit', 'components/manage/car/PreAuditList', '预审库', 'el-icon-icare-box', '2', '', '140', '', 'car:preaudit');
INSERT INTO `menu` VALUES ('11', '/log/list', 'components/manage/log/List', '日志管理', 'el-icon-icare-logs', '2', '', '160', '', 'log:*');
INSERT INTO `menu` VALUES ('12', '/import', 'components/manage/import', '品牌管理', 'el-icon-icare-brand', '2', '', '170', '', 'import:*');
INSERT INTO `menu` VALUES ('13', '/car/audited', 'components/manage/car/AuditedList', '车型库', 'el-icon-icare-cars', '2', '', '150', '', 'car:audited');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `isBackend` bit(1) DEFAULT NULL COMMENT '是否后端角色(1:是，0:否)',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `enabled` bit(1) NOT NULL COMMENT '是否启用',
  `isAdmin` bit(1) DEFAULT NULL,
  `defaultUrl` varchar(50) DEFAULT NULL COMMENT '默认页面',
  `defaultUrlName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '001', '超级管理员', '', '系统所有模块权限', '', '', '/car/audited', '车型库');
INSERT INTO `role` VALUES ('2', '002', '管理员', '', '添加前端用户权限', '', '', '/user/index', '用户管理');
INSERT INTO `role` VALUES ('3', '003', '前端用户', '', '提供前端数据检索查看', '', '', '', '');

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
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('2', '8');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐值',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `creationUserId` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '超级管理员', 'administrator', '966906c9b7012b88e85b800161a774c5', 'administrator', '1', null, '2018-02-16 21:51:49');
INSERT INTO `user` VALUES ('2', '管理员', 'manager', 'e10adc3949ba59abbe56e057f20f883e', 'manager', '1', '1', '2018-03-16 10:45:48');

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
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');

-- ----------------------------
-- View structure for `view_biz_vehicle`
-- ----------------------------
DROP VIEW IF EXISTS `view_biz_vehicle`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_biz_vehicle` AS select `v`.`id` AS `id`,`v`.`image` AS `image`,`b`.`id` AS `brandId`,`b`.`Name` AS `brand`,`b`.`initials` AS `initials`,`s`.`id` AS `seriesId`,`s`.`Name` AS `series`,`y`.`id` AS `yearId`,`y`.`Name` AS `modelyear`,`y`.`code` AS `yearCode`,`m`.`id` AS `modelId`,`m`.`Name` AS `model` from ((((`biz_vehicle` `v` left join `biz_brand` `b` on((`v`.`brand` = `b`.`id`))) left join `biz_series` `s` on((`s`.`id` = `v`.`series`))) left join `biz_year` `y` on((`v`.`modelyear` = `y`.`id`))) left join `biz_model` `m` on((`v`.`model` = `m`.`id`))) order by `v`.`brandInitials`,`v`.`brandName`,`v`.`hotindexSort` ;
