# 数据库文档 

### tb_login_log  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | id | INT   |      |      |
| login_name  | login_name | VARCHAR   |      |      |
| ip  | ip | VARCHAR   |      |      |
| browser_name  | browser_name | VARCHAR   |      |      |
| location  | 地区 | VARCHAR   |      |      |
| login_time  | 登录时间 | DATETIME   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /loginLog/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": id,
	"loginName": login_name,
	"ip": ip,
	"browserName": browser_name,
	"location": 地区,
	"loginTime": 登录时间,

},
.......
]
```



#### 分页查询数据  

##### url

> /loginLog/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /loginLog/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": id,
	"loginName": login_name,
	"ip": ip,
	"browserName": browser_name,
	"location": 地区,
	"loginTime": 登录时间,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /loginLog/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /loginLog/findList.do
{
	"id": id,
	"loginName": login_name,
	"ip": ip,
	"browserName": browser_name,
	"location": 地区,
	"loginTime": 登录时间,

}
```

##### 返回格式

```
[{
	"id": id,
	"loginName": login_name,
	"ip": ip,
	"browserName": browser_name,
	"location": 地区,
	"loginTime": 登录时间,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /loginLog/findPage.do

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
POST /loginLog/findPage.do?page=1&size=10
{
	"id": id,
	"loginName": login_name,
	"ip": ip,
	"browserName": browser_name,
	"location": 地区,
	"loginTime": 登录时间,

}
```

##### 返回格式

```
{rows:[{
	"id": id,
	"loginName": login_name,
	"ip": ip,
	"browserName": browser_name,
	"location": 地区,
	"loginTime": 登录时间,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /loginLog/findById.do

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

> /loginLog/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| loginLog | true | loginLog | 实体对象 |

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

> /loginLog/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| loginLog | true | loginLog | 实体对象 |

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

> /loginLog/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /loginLog/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
