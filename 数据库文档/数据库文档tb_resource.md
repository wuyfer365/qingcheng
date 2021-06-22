# 数据库文档 

### tb_resource  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | id | INT   |      |      |
| res_key  | res_key | VARCHAR   |      |      |
| res_name  | res_name | VARCHAR   |      |      |
| parent_id  | parent_id | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /resource/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": id,
	"resKey": res_key,
	"resName": res_name,
	"parentId": parent_id,

},
.......
]
```



#### 分页查询数据  

##### url

> /resource/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /resource/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": id,
	"resKey": res_key,
	"resName": res_name,
	"parentId": parent_id,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /resource/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /resource/findList.do
{
	"id": id,
	"resKey": res_key,
	"resName": res_name,
	"parentId": parent_id,

}
```

##### 返回格式

```
[{
	"id": id,
	"resKey": res_key,
	"resName": res_name,
	"parentId": parent_id,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /resource/findPage.do

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
POST /resource/findPage.do?page=1&size=10
{
	"id": id,
	"resKey": res_key,
	"resName": res_name,
	"parentId": parent_id,

}
```

##### 返回格式

```
{rows:[{
	"id": id,
	"resKey": res_key,
	"resName": res_name,
	"parentId": parent_id,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /resource/findById.do

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

> /resource/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| resource | true | resource | 实体对象 |

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

> /resource/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| resource | true | resource | 实体对象 |

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

> /resource/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /resource/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
