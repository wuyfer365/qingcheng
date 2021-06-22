# 数据库文档 

### tb_address  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | id | INT   |      |      |
| username  | 用户名 | VARCHAR   |      |      |
| provinceid  | 省 | VARCHAR   |      |      |
| cityid  | 市 | VARCHAR   |      |      |
| areaid  | 县/区 | VARCHAR   |      |      |
| phone  | 电话 | VARCHAR   |      |      |
| address  | 详细地址 | VARCHAR   |      |      |
| contact  | 联系人 | VARCHAR   |      |      |
| is_default  | 是否是默认 1默认 0否 | VARCHAR   |      |      |
| alias  | 别名 | VARCHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /address/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": id,
	"username": 用户名,
	"provinceid": 省,
	"cityid": 市,
	"areaid": 县/区,
	"phone": 电话,
	"address": 详细地址,
	"contact": 联系人,
	"isDefault": 是否是默认 1默认 0否,
	"alias": 别名,

},
.......
]
```



#### 分页查询数据  

##### url

> /address/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /address/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": id,
	"username": 用户名,
	"provinceid": 省,
	"cityid": 市,
	"areaid": 县/区,
	"phone": 电话,
	"address": 详细地址,
	"contact": 联系人,
	"isDefault": 是否是默认 1默认 0否,
	"alias": 别名,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /address/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /address/findList.do
{
	"id": id,
	"username": 用户名,
	"provinceid": 省,
	"cityid": 市,
	"areaid": 县/区,
	"phone": 电话,
	"address": 详细地址,
	"contact": 联系人,
	"isDefault": 是否是默认 1默认 0否,
	"alias": 别名,

}
```

##### 返回格式

```
[{
	"id": id,
	"username": 用户名,
	"provinceid": 省,
	"cityid": 市,
	"areaid": 县/区,
	"phone": 电话,
	"address": 详细地址,
	"contact": 联系人,
	"isDefault": 是否是默认 1默认 0否,
	"alias": 别名,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /address/findPage.do

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
POST /address/findPage.do?page=1&size=10
{
	"id": id,
	"username": 用户名,
	"provinceid": 省,
	"cityid": 市,
	"areaid": 县/区,
	"phone": 电话,
	"address": 详细地址,
	"contact": 联系人,
	"isDefault": 是否是默认 1默认 0否,
	"alias": 别名,

}
```

##### 返回格式

```
{rows:[{
	"id": id,
	"username": 用户名,
	"provinceid": 省,
	"cityid": 市,
	"areaid": 县/区,
	"phone": 电话,
	"address": 详细地址,
	"contact": 联系人,
	"isDefault": 是否是默认 1默认 0否,
	"alias": 别名,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /address/findById.do

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

> /address/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| address | true | address | 实体对象 |

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

> /address/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| address | true | address | 实体对象 |

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

> /address/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /address/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
