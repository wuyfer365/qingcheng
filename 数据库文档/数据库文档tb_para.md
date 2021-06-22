# 数据库文档 

### tb_para  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | id | INT   |      |      |
| name  | 名称 | VARCHAR   |      |      |
| options  | 选项 | VARCHAR   |      |      |
| seq  | 排序 | INT   |      |      |
| template_id  | 模板ID | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /para/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": id,
	"name": 名称,
	"options": 选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
]
```



#### 分页查询数据  

##### url

> /para/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /para/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": id,
	"name": 名称,
	"options": 选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /para/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /para/findList.do
{
	"id": id,
	"name": 名称,
	"options": 选项,
	"seq": 排序,
	"templateId": 模板ID,

}
```

##### 返回格式

```
[{
	"id": id,
	"name": 名称,
	"options": 选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /para/findPage.do

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
POST /para/findPage.do?page=1&size=10
{
	"id": id,
	"name": 名称,
	"options": 选项,
	"seq": 排序,
	"templateId": 模板ID,

}
```

##### 返回格式

```
{rows:[{
	"id": id,
	"name": 名称,
	"options": 选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /para/findById.do

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

> /para/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| para | true | para | 实体对象 |

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

> /para/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| para | true | para | 实体对象 |

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

> /para/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /para/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
