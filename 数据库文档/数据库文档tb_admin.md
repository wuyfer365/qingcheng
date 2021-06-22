# 数据库文档 

### tb_admin  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | id | INT   |      |      |
| login_name  | 用户名 | VARCHAR   |      |      |
| password  | 密码 | VARCHAR   |      |      |
| status  | 状态 | CHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /admin/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": id,
	"loginName": 用户名,
	"password": 密码,
	"status": 状态,

},
.......
]
```



#### 分页查询数据  

##### url

> /admin/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /admin/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": id,
	"loginName": 用户名,
	"password": 密码,
	"status": 状态,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /admin/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /admin/findList.do
{
	"id": id,
	"loginName": 用户名,
	"password": 密码,
	"status": 状态,

}
```

##### 返回格式

```
[{
	"id": id,
	"loginName": 用户名,
	"password": 密码,
	"status": 状态,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /admin/findPage.do

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
POST /admin/findPage.do?page=1&size=10
{
	"id": id,
	"loginName": 用户名,
	"password": 密码,
	"status": 状态,

}
```

##### 返回格式

```
{rows:[{
	"id": id,
	"loginName": 用户名,
	"password": 密码,
	"status": 状态,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /admin/findById.do

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

> /admin/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| admin | true | admin | 实体对象 |

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

> /admin/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| admin | true | admin | 实体对象 |

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

> /admin/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /admin/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
