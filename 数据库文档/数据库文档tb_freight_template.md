# 数据库文档 

### tb_freight_template  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | INT   |      |      |
| name  | 模板名称 | VARCHAR   |      |      |
| type  | 计费方式 | CHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /freightTemplate/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"name": 模板名称,
	"type": 计费方式,

},
.......
]
```



#### 分页查询数据  

##### url

> /freightTemplate/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /freightTemplate/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"name": 模板名称,
	"type": 计费方式,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /freightTemplate/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /freightTemplate/findList.do
{
	"id": ID,
	"name": 模板名称,
	"type": 计费方式,

}
```

##### 返回格式

```
[{
	"id": ID,
	"name": 模板名称,
	"type": 计费方式,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /freightTemplate/findPage.do

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
POST /freightTemplate/findPage.do?page=1&size=10
{
	"id": ID,
	"name": 模板名称,
	"type": 计费方式,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"name": 模板名称,
	"type": 计费方式,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /freightTemplate/findById.do

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

> /freightTemplate/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| freightTemplate | true | freightTemplate | 实体对象 |

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

> /freightTemplate/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| freightTemplate | true | freightTemplate | 实体对象 |

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

> /freightTemplate/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /freightTemplate/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
