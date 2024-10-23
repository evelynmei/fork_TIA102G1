CREATE DATABASE tia102g1;
USE tia102g1;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS member;																									
DROP TABLE IF EXISTS memberLv;																									
DROP TABLE IF EXISTS memberCoupon;																									
DROP TABLE IF EXISTS memberCoin;																									
DROP TABLE IF EXISTS productType;																									
DROP TABLE IF EXISTS productInfo;																									
DROP TABLE IF EXISTS productStock;																									
DROP TABLE IF EXISTS productComment;																									
DROP TABLE IF EXISTS favProduct;																									
DROP TABLE IF EXISTS cart;																									
DROP TABLE IF EXISTS addOn;																									
DROP TABLE IF EXISTS orderList;																									
DROP TABLE IF EXISTS orderListInfo;																									
DROP TABLE IF EXISTS event;																									
DROP TABLE IF EXISTS coupon;																									
DROP TABLE IF EXISTS quType;																									
DROP TABLE IF EXISTS csForm;																									
DROP TABLE IF EXISTS commonAsk;																									
DROP TABLE IF EXISTS news;																									
DROP TABLE IF EXISTS store;																									
DROP TABLE IF EXISTS county;																									
DROP TABLE IF EXISTS district;																									
DROP TABLE IF EXISTS sysMsg;																									
DROP TABLE IF EXISTS staff;																									
DROP TABLE IF EXISTS socialMember;																									
DROP TABLE IF EXISTS role;																									
DROP TABLE IF EXISTS memberhasrole;																									
DROP TABLE IF EXISTS staffhasrole;																									
SET FOREIGN_KEY_CHECKS = 1;																									
																									
																									
CREATE TABLE  MEMBER (																									
MEMBERID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
MEMBERLVID	INT UNSIGNED DEFAULT 1 NOT NULL COMMENT '會員等級FK',																								
STAFFID	INT UNSIGNED COMMENT '執行停權員工FK',																								
ACCOUNT	VARCHAR(50) NOT NULL UNIQUE KEY  COMMENT '帳號',																								
PASSWORD	VARCHAR(255) NOT NULL COMMENT '密碼',																								
NAME	VARCHAR(50) NOT NULL COMMENT '姓名',																								
BIRTHDT	DATE NOT NULL COMMENT '生日',																								
PHONE	VARCHAR(20) NOT NULL COMMENT '電話',																								
EMAIL	VARCHAR(100) NOT NULL UNIQUE COMMENT '電子信箱',																								
CNTCODE	INT NOT NULL COMMENT '地址-縣市FK',																								
DISTCODE	INT NOT NULL COMMENT '地址-鄉鎮區FK',																								
ADDRESS	VARCHAR(90) NOT NULL COMMENT '地址',																								
ACCUMULATE	INT NOT NULL DEFAULT 0 COMMENT '目前累積消費金額',																								
COINBALANCE	INT NOT NULL DEFAULT 0 COMMENT '目前購物金餘額',																								
JOINDATE	DATE DEFAULT (CURRENT_DATE) NOT NULL COMMENT '加入日期',																								
NOSHOW	INT DEFAULT 0 NOT NULL COMMENT '未取貨次數',																								
CARDHOLDER	VARCHAR(30) COMMENT '持卡人姓名',																								
CARDNUMBER	VARCHAR(20) COMMENT '卡號',																								
CARDYY	INT COMMENT '卡片年',																								
CARDMM	INT COMMENT '卡片月份',																								
CARDVERIFYCODE	VARCHAR(3) COMMENT '卡片末三碼',																								
STATUS	INT DEFAULT 1 NOT NULL COMMENT '帳號狀態',																								
BLOCKEDTIME	DATETIME COMMENT '停權日期',																								
BLOCKEDREASON	VARCHAR(100) COMMENT '停權原因',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  MEMBER_MEMBERID_PK PRIMARY KEY (memberId)																									
);																									
																									
																									
CREATE TABLE  MEMBERLV (																									
MEMBERLVID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '會員等級PK',																								
LVNAME	VARCHAR(10) NOT NULL COMMENT '會員等級名稱',																								
LVBUFF	VARCHAR(50) NOT NULL COMMENT '會員優惠內容',																								
LVACCUMULATE	INT NOT NULL COMMENT '會員累積消費金額',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  MEMBERLV_MEMBERLVID_PK PRIMARY KEY (memberLvId)																									
);																									
																									
																									
CREATE TABLE  MEMBERCOUPON (																									
MEMCOUPONID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
MEMBERID	INT UNSIGNED NOT NULL COMMENT '會員編號FK',																								
COUPONID	INT UNSIGNED NOT NULL COMMENT '優惠券FK',																								
GETDT	DATE NOT NULL COMMENT '取得日期',																								
STATUS	INT NOT NULL COMMENT '使用狀態',																								
CONSTRAINT  MEMBERCOUPON_MEMCOUPONID_PK PRIMARY KEY (memCouponId)																									
);																									
																									
																									
CREATE TABLE  MEMBERCOIN (																									
MEMCOINID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
MEMBERID	INT UNSIGNED NOT NULL COMMENT '會員編號FK',																								
ORDERLISTID	INT UNSIGNED COMMENT '訂單編號 FK',																								
GETDT	DATE NOT NULL COMMENT '取得日期',																								
SUMMARY	VARCHAR(50) NOT NULL COMMENT '摘要',																								
TYPE	INT NOT NULL COMMENT '異動類型',																								
AMOUNT	INT NOT NULL COMMENT '金額',																								
EXPIRYDT	DATE NOT NULL COMMENT '到期日',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  MEMBERCOIN_MEMCOINID_PK PRIMARY KEY (memCoinId)																									
);																									
																									
																									
CREATE TABLE  PRODUCTTYPE (																									
PRODUCTTYPEID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商品類型編號PK',																								
TYPENAME	VARCHAR(20) NOT NULL COMMENT '商品類型名稱',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  PRODUCTTYPE_PRODUCTTYPEID_PK PRIMARY KEY (productTypeId)																									
);																									
																									
																									
set auto_increment_offset=1001;																									
set auto_increment_increment=1;																									
CREATE TABLE  PRODUCTINFO (																									
PRODUCTID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商品編號PK',																								
PRODUCTTYPEID	INT UNSIGNED NOT NULL COMMENT '商品類型編號FK',																								
PRONAME	VARCHAR(50) NOT NULL COMMENT '商品名稱',																								
PROPRICE	INT NOT NULL COMMENT '商品單價',																								
PROSAFETYSTOCK	INT NOT NULL COMMENT '商品安全存量',																								
TOTALCOUNT	INT NOT NULL COMMENT '商品結餘數量',																								
COMMENTUSERS	INT COMMENT '總評價人數',																								
COMMENTSTARS	INT COMMENT '總星星數',																								
PROPIC	LONGBLOB COMMENT '商品圖片',																								
PROSTATUS	INT NOT NULL COMMENT '商品狀態',																								
PRODESC	VARCHAR(300) NOT NULL COMMENT '商品描述',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  PRODUCTINFO_PRODUCTID_PK PRIMARY KEY (productId)																									
) AUTO_INCREMENT = 1001;																									
																									
																									
CREATE TABLE  PRODUCTSTOCK (																									
PROSTOCKID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
PRODUCTID	INT UNSIGNED NOT NULL COMMENT '商品編號FK',																								
COUNT	INT NOT NULL COMMENT '數量',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  PRODUCTSTOCK_PROSTOCKID_PK PRIMARY KEY (proStockId)																									
);																									
																									
																									
CREATE TABLE  PRODUCTCOMMENT (																									
PROCOMMENTID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
MEMBERID	INT UNSIGNED NOT NULL COMMENT '會員編號FK',																								
PRODUCTID	INT UNSIGNED NOT NULL COMMENT '商品編號FK',																								
ORDERLISTINFOID	INT UNSIGNED NOT NULL COMMENT '訂單商品明細FK',																								
STAFFID	INT UNSIGNED COMMENT '回覆評價員工FK',																								
STOREREPLY	VARCHAR(300) COMMENT '店家回覆',																								
COMMENTTEXT	VARCHAR(300) NOT NULL COMMENT '評論內容',																								
COMMENTPIC	LONGBLOB COMMENT '評論圖片',																								
COMMENTRATE	INT NOT NULL COMMENT '滿意度',																								
COMMENTDATE	DATE NOT NULL COMMENT '評論時間',																								
REPLYTIME	DATETIME COMMENT '回覆時間',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  PRODUCTCOMMENT_PROCOMMENTID_PK PRIMARY KEY (proCommentId)																									
);																									
																									
																									
CREATE TABLE  FAVPRODUCT (																									
FAVPRODUCTID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
MEMBERID	INT UNSIGNED NOT NULL COMMENT '會員編號FK',																								
PRODUCTID	INT UNSIGNED NOT NULL COMMENT '商品編號FK',																								
JOINDT	DATE DEFAULT (CURRENT_DATE) NOT NULL COMMENT '商品加入日期',																								
CONSTRAINT  FAVPRODUCT_FAVPRODUCTID_PK PRIMARY KEY (favProductId)																									
);																									
																									
																									
CREATE TABLE  CART (																									
CARTID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
MEMBERID	INT UNSIGNED NOT NULL COMMENT '會員編號FK',																								
PRODUCTID	INT UNSIGNED NOT NULL COMMENT '商品編號FK',																								
PROAMOUNT	INT NOT NULL COMMENT '商品數量',																								
JOINDT	DATE DEFAULT (CURRENT_DATE) NOT NULL COMMENT '加入日期',																								
CONSTRAINT  CART_CARTID_PK PRIMARY KEY (cartId)																									
);																									
																									
																									
CREATE TABLE  ADDON (																									
ADDONID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
MAINPROID	INT UNSIGNED NOT NULL COMMENT '主商品編號FK',																								
ADDONPROID	INT UNSIGNED NOT NULL COMMENT '加購商品編號FK',																								
ADDONPRICE	INT NOT NULL COMMENT '加購折扣價格',																								
STATUS	INT NOT NULL COMMENT '啟用狀態',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  ADDON_ADDONID_PK PRIMARY KEY (addOnId)																									
);																									
																									
																									
set auto_increment_offset=20001;																									
set auto_increment_increment=1;																									
CREATE TABLE  ORDERLIST (																									
ORDERLISTID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '訂單編號PK',																								
MEMBERID	INT UNSIGNED NOT NULL COMMENT '會員編號FK',																								
COUPONID	INT UNSIGNED COMMENT '優惠券編號FK',																								
EVENTID	INT UNSIGNED COMMENT '促銷活動FK',																								
ORDERDT	DATE NOT NULL COMMENT '訂單日期',																								
ORDERAMOUNT	INT NOT NULL COMMENT '總金額',																								
COUPONUSEDAMOUNT	INT NOT NULL COMMENT '優惠券折扣金額',																								
COINUSEDAMOUNT	INT NOT NULL COMMENT '購物金抵用金額',																								
PAYAMOUNT	INT NOT NULL COMMENT '總付款金額',																								
ORDERSTATUS	INT NOT NULL COMMENT '訂單狀態',																								
PAYMENTMETHOD	INT NOT NULL COMMENT '付款方式',																								
PAYMENTSTATUS	INT NOT NULL COMMENT '付款狀態',																								
PICKUPMETHOD	INT NOT NULL COMMENT '取貨方式',																								
USECOUPON	INT NOT NULL COMMENT '是否使用優惠券',																								
USECOIN	INT NOT NULL COMMENT '是否使用購物金',																								
CARDHOLDER	VARCHAR(30) COMMENT '持卡人姓名',																								
CARDNUMBER	VARCHAR(20) COMMENT '卡號',																								
CARDYY	INT COMMENT '卡片年',																								
CARDMM	VARCHAR(2) COMMENT '卡片月份',																								
CARDVERIFYCODE	VARCHAR(3) COMMENT '卡片末三碼',																								
INVOICEWAY	INT NOT NULL COMMENT '發票方式',																								
INVOICETAXNO	VARCHAR(8) COMMENT '統一編號',																								
INVOICEMOBILECODE	VARCHAR(20) COMMENT '手機載具碼',																								
RECIPIENTNAME	VARCHAR(50) NOT NULL COMMENT '收件人姓名',																								
RECIPIENTPHONE	VARCHAR(20) NOT NULL COMMENT '收件人電話',																								
RECIPIENTCNT	INT NOT NULL COMMENT '地址-縣市FK',																								
RECIPIENTDIST	INT NOT NULL COMMENT '地址-鄉鎮區FK',																								
RECIPIENTADDRESS	VARCHAR(90) NOT NULL COMMENT '收件人地址',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  ORDERLIST_ORDERLISTID_PK PRIMARY KEY (orderListId)																									
) AUTO_INCREMENT = 20001;																									
																									
																									
CREATE TABLE  ORDERLISTINFO (																									
ORDERLISTINFOID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
ORDERLISTID	INT UNSIGNED NOT NULL COMMENT '訂單編號FK',																								
PRODUCTID	INT UNSIGNED NOT NULL COMMENT '商品編號FK',																								
PURCHASEDPRICE	INT NOT NULL COMMENT '購買單價',																								
PROQUANTITY	INT NOT NULL COMMENT '商品數量',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  ORDERLISTINFO_ORDERLISTINFOID_PK PRIMARY KEY (orderListInfoId)																									
);																									
																									
																									
set auto_increment_offset=60001;																									
set auto_increment_increment=1;																									
CREATE TABLE  EVENT (																									
EVENTID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '活動編號PK',																								
EVENTNAME	VARCHAR(50) NOT NULL COMMENT '活動名稱',																								
STARTDT	DATE NOT NULL COMMENT '活動開始日期',																								
ENDDT	DATE NOT NULL COMMENT '活動結束日期',																								
EVENTDISCOUNT	DECIMAL(3,2) NOT NULL COMMENT '活動折數',																								
EVENTPIC	LONGBLOB COMMENT '活動圖片',																								
STATUS	INT NOT NULL COMMENT '活動狀態',																								
EVENTCONTENT	TEXT NOT NULL COMMENT '活動文案內容',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  EVENT_EVENTID_PK PRIMARY KEY (eventId)																									
) AUTO_INCREMENT = 60001;																									
																									
																									
set auto_increment_offset=10001;																									
set auto_increment_increment=1;																									
CREATE TABLE  COUPON (																									
COUPONID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '優惠券編號PK',																								
COUPONCODE	VARCHAR(20) NOT NULL UNIQUE KEY COMMENT '優惠券代碼',																								
COUPONNAME	VARCHAR(30) NOT NULL COMMENT '優惠券名稱',																								
COUPONSTATUS	INT NOT NULL COMMENT '優惠券狀態',																								
STARTDT	DATE NOT NULL COMMENT '使用起始日',																								
ENDDT	DATE NOT NULL COMMENT '使用結束日',																								
DISCTYPE	INT NOT NULL COMMENT '折扣類型',																								
DISCAMOUNT	INT COMMENT '折扣金額',																								
DISCPERCENTAGE	DECIMAL(3,2) COMMENT '折扣百分比',																								
CREATEDBY	VARCHAR(50) COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  COUPON_COUPONID_PK PRIMARY KEY (couponId)																									
) AUTO_INCREMENT = 10001;																									
																									
																									
CREATE TABLE  QUTYPE (																									
QUTYPEID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '類型編號PK',																								
QUTYPEDESC	VARCHAR(50) NOT NULL COMMENT '問題類型描述',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  QUTYPE_QUTYPEID_PK PRIMARY KEY (quTypeId)																									
);																									
																									
																									
																									
set auto_increment_offset=10001;																									
set auto_increment_increment=1;																									
CREATE TABLE  CSFORM (																									
CSFORMID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '表單編號PK',																								
MEMBERID	INT UNSIGNED NOT NULL COMMENT '會員編號FK',																								
ORDERID	INT UNSIGNED NOT NULL COMMENT '訂單編號FK',																								
STAFFID	INT UNSIGNED COMMENT '回覆客服人員FK',																								
QUTYPEID	INT UNSIGNED NOT NULL COMMENT '問題類型FK',																								
QUDATE	DATE NOT NULL COMMENT '客訴日期',																								
QUCONTENT	TEXT NOT NULL COMMENT '客訴內容',																								
QUPIC	LONGBLOB COMMENT '客訴圖片',																								
QUAVTIME	VARCHAR(50) COMMENT '可聯繫時間',																								
REPLYDT	DATE COMMENT '回覆日期',																								
REPLYCONTENT	TEXT COMMENT '回覆內容',																								
STATUS	INT NOT NULL COMMENT '處理狀態',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  CSFORM_CSFORMID_PK PRIMARY KEY (csFormId)																									
) AUTO_INCREMENT = 10001;																									
																									
																									
CREATE TABLE  COMMONASK (																									
COMMONASKID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
COMMONASK	VARCHAR(150) NOT NULL COMMENT '問題',																								
COMMONANSWER	VARCHAR(150) NOT NULL COMMENT '回答',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  COMMONASK_COMMONASKID_PK PRIMARY KEY (commonAskId)																									
);																									
																									
																									
CREATE TABLE  NEWS (																									
NEWSID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
NEWSTITLE	VARCHAR(20) NOT NULL COMMENT '公告標題',																								
NEWSDATE	DATE NOT NULL COMMENT '公告日期',																								
STARTDT	DATE NOT NULL COMMENT '公告起始日',																								
ENDDT	DATE NOT NULL COMMENT '公告結束日',																								
NEWSCONTENT	VARCHAR(300) NOT NULL COMMENT '公告內容',																								
NEWSPIC	LONGBLOB COMMENT '公告圖片',																								
STATUS	INT NOT NULL COMMENT '公告狀態',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  NEWS_NEWSID_PK PRIMARY KEY (newsId)																									
);																									
																									
																									
CREATE TABLE  STORE (																									
STOREID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '門市編號PK',																								
STORENAME	VARCHAR(50) NOT NULL COMMENT '門市名稱',																								
CNTCODE	INT NOT NULL COMMENT '地址-縣市FK',																								
DISTCODE	INT NOT NULL COMMENT '地址-鄉鎮區FK',																								
STOREADDRESS	VARCHAR(90) NOT NULL COMMENT '地址',																								
LONGITUDE	VARCHAR(10) COMMENT '地址經度',																								
LATITUDE	VARCHAR(10) COMMENT '地址緯度',																								
STOREPHONE	VARCHAR(20) NOT NULL COMMENT '電話',																								
OPENINGHOURS	VARCHAR(50) NOT NULL COMMENT '營業時間',																								
STOREMAIL	VARCHAR(100) NOT NULL COMMENT '電子信箱',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  STORE_STOREID_PK PRIMARY KEY (storeId)																									
);																									
																									
																									
CREATE TABLE  COUNTY (																									
CNTCODE	INT NOT NULL COMMENT '縣市代碼PK',																								
CNTNAME	VARCHAR(10) NOT NULL COMMENT '縣市名稱',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  COUNTY_CNTCODE_PK PRIMARY KEY (cntCode)																									
);																									
																									
																									
CREATE TABLE  DISTRICT (																									
DISTCODE	INT NOT NULL COMMENT '鄉鎮區代碼PK',																								
CNTCODE	INT NOT NULL COMMENT '縣市代碼FK',																								
DISTNAME	VARCHAR(10) NOT NULL COMMENT '鄉鎮區名稱',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  DISTRICT_DISTCODE_PK PRIMARY KEY (distCode)																									
);																									
																									
																									
CREATE TABLE  SYSMSG (																									
SYSMSGID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
TYPE	INT NOT NULL COMMENT '訊息類型',																								
MSGTITLE	VARCHAR(20) NOT NULL COMMENT '訊息標題',																								
MSGCONTENT	TEXT NOT NULL COMMENT '訊息內容',																								
STATUS	INT NOT NULL COMMENT '啟用狀態',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  SYSMSG_SYSMSGID_PK PRIMARY KEY (sysMsgId)																									
);																									
																									
																									
set auto_increment_offset=7001;																									
set auto_increment_increment=1;																									
CREATE TABLE  STAFF (																									
STAFFID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '員工編號PK',																								
PASSWORD	VARCHAR(255) NOT NULL COMMENT '員工密碼',																								
NAME	VARCHAR(50) NOT NULL COMMENT '員工姓名',																								
PHONE	VARCHAR(20) NOT NULL COMMENT '員工電話',																								
EMAIL	VARCHAR(100) NOT NULL COMMENT '員工電子信箱',																								
EMPLOYDT	DATE NOT NULL COMMENT '到職日期',																								
LEAVEDT	DATE COMMENT '離職日期',																								
STATUS	INT NOT NULL COMMENT '帳號狀態',																								
PERMISSION	INT NOT NULL COMMENT '權限狀態',																								
CREATEDBY	VARCHAR(50) NOT NULL COMMENT '建檔者',																								
DATECREATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '建檔時間',																								
LASTUPDATEDBY	VARCHAR(50) NOT NULL COMMENT '最後更新者',																								
LASTUPDATED	DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最後更新時間',																								
CONSTRAINT  STAFF_STAFFID_PK PRIMARY KEY (staffId)																									
) AUTO_INCREMENT = 7001;																									
																									
																									
																									
CREATE TABLE  SOCIALMEMBER (																									
OAUTH2MEMBERID	INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '流水號PK',																								
PROVIDER	VARCHAR(256) NOT NULL COMMENT '第三方網站',																								
PROVIDERID	VARCHAR(256) NOT NULL COMMENT '使用者在該網站中id值',																								
NAME	VARCHAR(256) COMMENT '使用者姓名',																								
EMAIL	VARCHAR(256) COMMENT '使用者信箱',																								
ACCESSTOKEN	TEXT COMMENT '訪問權杖',																								
EXPIRESAT	TIMESTAMP COMMENT 'access_token過期時間',																								
CONSTRAINT  OAUTH2MEMBERID_PK PRIMARY KEY (oauth2MemberId)																									
);																									
																									
																									
																									
ALTER TABLE MEMBER ADD CONSTRAINT  MEMBER_MEMBERLVID_FK FOREIGN KEY (memberLvId) REFERENCES memberLv (memberLvId);																									
ALTER TABLE MEMBER ADD CONSTRAINT  MEMBER_STAFFID_FK FOREIGN KEY (staffId) REFERENCES staff (staffId);																									
ALTER TABLE MEMBER ADD CONSTRAINT  MEMBER_CNTCODE_FK FOREIGN KEY (cntCode) REFERENCES county (cntCode);																									
ALTER TABLE MEMBER ADD CONSTRAINT  MEMBER_DISTCODE_FK FOREIGN KEY (distCode) REFERENCES district (distCode);																									
ALTER TABLE MEMBERCOUPON ADD CONSTRAINT  MEMBERCOUPON_MEMBERID_FK FOREIGN KEY (memberId) REFERENCES member (memberId);																									
ALTER TABLE MEMBERCOUPON ADD CONSTRAINT  MEMBERCOUPON_COUPONID_FK FOREIGN KEY (couponId) REFERENCES coupon (couponId);																									
ALTER TABLE MEMBERCOIN ADD CONSTRAINT  MEMBERCOIN_MEMBERID_FK FOREIGN KEY (memberId) REFERENCES member (memberId);																									
ALTER TABLE MEMBERCOIN ADD CONSTRAINT  MEMBERCOIN_ORDERLISTID_FK FOREIGN KEY (orderListId) REFERENCES orderList (orderListId);																									
ALTER TABLE PRODUCTINFO ADD CONSTRAINT  PRODUCTINFO_PRODUCTTYPEID_FK FOREIGN KEY (productTypeId) REFERENCES productType (productTypeId);																									
ALTER TABLE PRODUCTSTOCK ADD CONSTRAINT  PRODUCTSTOCK_PRODUCTID_FK FOREIGN KEY (productId) REFERENCES productInfo (productId);																									
ALTER TABLE PRODUCTCOMMENT ADD CONSTRAINT  PRODUCTCOMMENT_MEMBERID_FK FOREIGN KEY (memberId) REFERENCES member (memberId);																									
ALTER TABLE PRODUCTCOMMENT ADD CONSTRAINT  PRODUCTCOMMENT_PRODUCTID_FK FOREIGN KEY (productId) REFERENCES productInfo (productId);																									
ALTER TABLE PRODUCTCOMMENT ADD CONSTRAINT  PRODUCTCOMMENT_ORDERLISTINFOID_FK FOREIGN KEY (orderListInfoId) REFERENCES orderListInfo (orderListInfoId);																									
ALTER TABLE PRODUCTCOMMENT ADD CONSTRAINT  PRODUCTCOMMENT_STAFFID_FK FOREIGN KEY (staffId) REFERENCES staff (staffId);																									
ALTER TABLE FAVPRODUCT ADD CONSTRAINT  FAVPRODUCT_MEMBERID_FK FOREIGN KEY (memberId) REFERENCES member (memberId);																									
ALTER TABLE FAVPRODUCT ADD CONSTRAINT  FAVPRODUCT_PRODUCTID_FK FOREIGN KEY (productId) REFERENCES productInfo (productId);																									
ALTER TABLE CART ADD CONSTRAINT  CART_MEMBERID_FK FOREIGN KEY (memberId) REFERENCES member (memberId);																									
ALTER TABLE CART ADD CONSTRAINT  CART_PRODUCTID_FK FOREIGN KEY (productId) REFERENCES productInfo (productId);																									
ALTER TABLE ADDON ADD CONSTRAINT  ADDON_MAINPROID_FK FOREIGN KEY (mainProId) REFERENCES productInfo (productId);																									
ALTER TABLE ADDON ADD CONSTRAINT  ADDON_ADDONPROID_FK FOREIGN KEY (addOnProId) REFERENCES productInfo (productId);																									
ALTER TABLE ORDERLIST ADD CONSTRAINT  ORDERLIST_MEMBERID_FK FOREIGN KEY (memberId) REFERENCES member (memberId);																									
ALTER TABLE ORDERLIST ADD CONSTRAINT  ORDERLIST_COUPONID_FK FOREIGN KEY (couponId) REFERENCES coupon (couponId);																									
ALTER TABLE ORDERLIST ADD CONSTRAINT  ORDERLIST_EVENTID_FK FOREIGN KEY (eventId) REFERENCES event (eventId);																									
ALTER TABLE ORDERLIST ADD CONSTRAINT  ORDERLIST_RECIPIENTCNT_FK FOREIGN KEY (recipientCnt) REFERENCES county (cntCode);																									
ALTER TABLE ORDERLIST ADD CONSTRAINT  ORDERLIST_RECIPIENTDIST_FK FOREIGN KEY (recipientDist) REFERENCES district (distCode);																									
ALTER TABLE ORDERLISTINFO ADD CONSTRAINT  ORDERLISTINFO_ORDERLISTID_FK FOREIGN KEY (orderListId) REFERENCES orderList (orderListId);																									
ALTER TABLE ORDERLISTINFO ADD CONSTRAINT  ORDERLISTINFO_PRODUCTID_FK FOREIGN KEY (productId) REFERENCES productInfo (productId);																									
ALTER TABLE CSFORM ADD CONSTRAINT  CSFORM_MEMBERID_FK FOREIGN KEY (memberId) REFERENCES member (memberId);																									
ALTER TABLE CSFORM ADD CONSTRAINT  CSFORM_ORDERID_FK FOREIGN KEY (orderId) REFERENCES orderList (orderListId);																									
ALTER TABLE CSFORM ADD CONSTRAINT  CSFORM_STAFFID_FK FOREIGN KEY (staffId) REFERENCES staff (staffId);																									
ALTER TABLE CSFORM ADD CONSTRAINT  CSFORM_QUTYPEID_FK FOREIGN KEY (quTypeId) REFERENCES quType (quTypeId);																									
ALTER TABLE STORE ADD CONSTRAINT  STORE_CNTCODE_FK FOREIGN KEY (cntCode) REFERENCES county (cntCode);																									
ALTER TABLE STORE ADD CONSTRAINT  STORE_DISTCODE_FK FOREIGN KEY (distCode) REFERENCES district (distCode);																									
ALTER TABLE DISTRICT ADD CONSTRAINT  DISTRICT_CNTCODE_FK FOREIGN KEY (cntCode) REFERENCES county (cntCode);																									
																									
CREATE TABLE ROLE																									
																									
( ROLEID INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID', ROLENAME VARCHAR(256) NOT NULL COMMENT '角色名字' );																									
																									
CREATE TABLE MEMBERHASROLE																									
																									
( MEMBERROLEID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, MEMBERID INT UNSIGNED NOT NULL, ROLEID INT NOT NULL, FOREIGN KEY (MEMBERID) REFERENCES MEMBER (MEMBERID), FOREIGN KEY (ROLEID) REFERENCES ROLE (ROLEID) );																									
																									
CREATE TABLE STAFFHASROLE ( STAFFROLEID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, STAFFID INT UNSIGNED NOT NULL, ROLEID INT NOT NULL, FOREIGN KEY (STAFFID) REFERENCES STAFF (STAFFID), FOREIGN KEY (ROLEID) REFERENCES ROLE (ROLEID) );																									
																									
INSERT INTO role(ROLENAME) VALUES ('ROLE_ADMIN');																									
																									
INSERT INTO role(ROLENAME) VALUES ('ROLE_MEMBER');																									
																									
INSERT INTO role(ROLENAME) VALUES ('ROLE_STAFF');																									