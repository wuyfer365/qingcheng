# 数据库文档 

### tb_user  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| username  | 用户名 | VARCHAR   |      |      |
| password  | 密码，加密存储 | VARCHAR   |      |      |
| phone  | 注册手机号 | VARCHAR   |      |      |
| email  | 注册邮箱 | VARCHAR   |      |      |
| created  | 创建时间 | DATETIME   |      |      |
| updated  | 修改时间 | DATETIME   |      |      |
| source_type  | 会员来源：1:PC，2：H5，3：Android，4：IOS | VARCHAR   |      |      |
| nick_name  | 昵称 | VARCHAR   |      |      |
| name  | 真实姓名 | VARCHAR   |      |      |
| status  | 使用状态（1正常 0非正常） | VARCHAR   |      |      |
| head_pic  | 头像地址 | VARCHAR   |      |      |
| qq  | QQ号码 | VARCHAR   |      |      |
| is_mobile_check  | 手机是否验证 （0否  1是） | VARCHAR   |      |      |
| is_email_check  | 邮箱是否检测（0否  1是） | VARCHAR   |      |      |
| sex  | 性别，1男，0女 | VARCHAR   |      |      |
| user_level  | 会员等级 | INT   |      |      |
| points  | 积分 | INT   |      |      |
| experience_value  | 经验值 | INT   |      |      |
| birthday  | 出生年月日 | DATETIME   |      |      |
| last_login_time  | 最后登录时间 | DATETIME   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /user/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"username": 用户名,
	"password": 密码，加密存储,
	"phone": 注册手机号,
	"email": 注册邮箱,
	"created": 创建时间,
	"updated": 修改时间,
	"sourceType": 会员来源：1:PC，2：H5，3：Android，4：IOS,
	"nickName": 昵称,
	"name": 真实姓名,
	"status": 使用状态（1正常 0非正常）,
	"headPic": 头像地址,
	"qq": QQ号码,
	"isMobileCheck": 手机是否验证 （0否  1是）,
	"isEmailCheck": 邮箱是否检测（0否  1是）,
	"sex": 性别，1男，0女,
	"userLevel": 会员等级,
	"points": 积分,
	"experienceValue": 经验值,
	"birthday": 出生年月日,
	"lastLoginTime": 最后登录时间,

},
.......
]
```



#### 分页查询数据  

##### url

> /user/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /user/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"username": 用户名,
	"password": 密码，加密存储,
	"phone": 注册手机号,
	"email": 注册邮箱,
	"created": 创建时间,
	"updated": 修改时间,
	"sourceType": 会员来源：1:PC，2：H5，3：Android，4：IOS,
	"nickName": 昵称,
	"name": 真实姓名,
	"status": 使用状态（1正常 0非正常）,
	"headPic": 头像地址,
	"qq": QQ号码,
	"isMobileCheck": 手机是否验证 （0否  1是）,
	"isEmailCheck": 邮箱是否检测（0否  1是）,
	"sex": 性别，1男，0女,
	"userLevel": 会员等级,
	"points": 积分,
	"experienceValue": 经验值,
	"birthday": 出生年月日,
	"lastLoginTime": 最后登录时间,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /user/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /user/findList.do
{
	"username": 用户名,
	"password": 密码，加密存储,
	"phone": 注册手机号,
	"email": 注册邮箱,
	"created": 创建时间,
	"updated": 修改时间,
	"sourceType": 会员来源：1:PC，2：H5，3：Android，4：IOS,
	"nickName": 昵称,
	"name": 真实姓名,
	"status": 使用状态（1正常 0非正常）,
	"headPic": 头像地址,
	"qq": QQ号码,
	"isMobileCheck": 手机是否验证 （0否  1是）,
	"isEmailCheck": 邮箱是否检测（0否  1是）,
	"sex": 性别，1男，0女,
	"userLevel": 会员等级,
	"points": 积分,
	"experienceValue": 经验值,
	"birthday": 出生年月日,
	"lastLoginTime": 最后登录时间,

}
```

##### 返回格式

```
[{
	"username": 用户名,
	"password": 密码，加密存储,
	"phone": 注册手机号,
	"email": 注册邮箱,
	"created": 创建时间,
	"updated": 修改时间,
	"sourceType": 会员来源：1:PC，2：H5，3：Android，4：IOS,
	"nickName": 昵称,
	"name": 真实姓名,
	"status": 使用状态（1正常 0非正常）,
	"headPic": 头像地址,
	"qq": QQ号码,
	"isMobileCheck": 手机是否验证 （0否  1是）,
	"isEmailCheck": 邮箱是否检测（0否  1是）,
	"sex": 性别，1男，0女,
	"userLevel": 会员等级,
	"points": 积分,
	"experienceValue": 经验值,
	"birthday": 出生年月日,
	"lastLoginTime": 最后登录时间,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /user/findPage.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |
| page      | true | int  | 页码（GET）      |
| size      | true | int  | 每页记录数（GET）   |

例子：

```
POST /user/findPage.do?page=1&size=10
{
	"username": 用户名,
	"password": 密码，加密存储,
	"phone": 注册手机号,
	"email": 注册邮箱,
	"created": 创建时间,
	"updated": 修改时间,
	"sourceType": 会员来源：1:PC，2：H5，3：Android，4：IOS,
	"nickName": 昵称,
	"name": 真实姓名,
	"status": 使用状态（1正常 0非正常）,
	"headPic": 头像地址,
	"qq": QQ号码,
	"isMobileCheck": 手机是否验证 （0否  1是）,
	"isEmailCheck": 邮箱是否检测（0否  1是）,
	"sex": 性别，1男，0女,
	"userLevel": 会员等级,
	"points": 积分,
	"experienceValue": 经验值,
	"birthday": 出生年月日,
	"lastLoginTime": 最后登录时间,

}
```

##### 返回格式

```
{rows:[{
	"username": 用户名,
	"password": 密码，加密存储,
	"phone": 注册手机号,
	"email": 注册邮箱,
	"created": 创建时间,
	"updated": 修改时间,
	"sourceType": 会员来源：1:PC，2：H5，3：Android，4：IOS,
	"nickName": 昵称,
	"name": 真实姓名,
	"status": 使用状态（1正常 0非正常）,
	"headPic": 头像地址,
	"qq": QQ号码,
	"isMobileCheck": 手机是否验证 （0否  1是）,
	"isEmailCheck": 邮箱是否检测（0否  1是）,
	"sex": 性别，1男，0女,
	"userLevel": 会员等级,
	"points": 积分,
	"experienceValue": 经验值,
	"birthday": 出生年月日,
	"lastLoginTime": 最后登录时间,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /user/findById.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键  |

例子：

```

```



#### 增加数据  

##### url

> /user/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| user | true | user | 实体对象 |

##### 返回格式

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败



#### 修改数据  

##### url

> /user/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| user | true | user | 实体对象 |

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败



#### 删除数据  

##### url

> /user/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /user/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
