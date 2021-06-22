# 数据库文档 

### tb_spec  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | INT   |      |      |
| name  | 名称 | VARCHAR   |      |      |
| options  | 规格选项 | VARCHAR   |      |      |
| seq  | 排序 | INT   |      |      |
| template_id  | 模板ID | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /spec/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"name": 名称,
	"options": 规格选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
]
```



#### 分页查询数据  

##### url

> /spec/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /spec/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"name": 名称,
	"options": 规格选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /spec/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /spec/findList.do
{
	"id": ID,
	"name": 名称,
	"options": 规格选项,
	"seq": 排序,
	"templateId": 模板ID,

}
```

##### 返回格式

```
[{
	"id": ID,
	"name": 名称,
	"options": 规格选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /spec/findPage.do

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
POST /spec/findPage.do?page=1&size=10
{
	"id": ID,
	"name": 名称,
	"options": 规格选项,
	"seq": 排序,
	"templateId": 模板ID,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"name": 名称,
	"options": 规格选项,
	"seq": 排序,
	"templateId": 模板ID,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /spec/findById.do

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

> /spec/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| spec | true | spec | 实体对象 |

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

> /spec/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| spec | true | spec | 实体对象 |

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

> /spec/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /spec/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
