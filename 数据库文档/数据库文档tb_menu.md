# 数据库文档 

### tb_menu  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 菜单ID | VARCHAR   |      |      |
| name  | 菜单名称 | VARCHAR   |      |      |
| icon  | 图标 | VARCHAR   |      |      |
| url  | URL | VARCHAR   |      |      |
| parent_id  | 上级菜单ID | VARCHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /menu/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 菜单ID,
	"name": 菜单名称,
	"icon": 图标,
	"url": URL,
	"parentId": 上级菜单ID,

},
.......
]
```



#### 分页查询数据  

##### url

> /menu/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /menu/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 菜单ID,
	"name": 菜单名称,
	"icon": 图标,
	"url": URL,
	"parentId": 上级菜单ID,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /menu/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /menu/findList.do
{
	"id": 菜单ID,
	"name": 菜单名称,
	"icon": 图标,
	"url": URL,
	"parentId": 上级菜单ID,

}
```

##### 返回格式

```
[{
	"id": 菜单ID,
	"name": 菜单名称,
	"icon": 图标,
	"url": URL,
	"parentId": 上级菜单ID,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /menu/findPage.do

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
POST /menu/findPage.do?page=1&size=10
{
	"id": 菜单ID,
	"name": 菜单名称,
	"icon": 图标,
	"url": URL,
	"parentId": 上级菜单ID,

}
```

##### 返回格式

```
{rows:[{
	"id": 菜单ID,
	"name": 菜单名称,
	"icon": 图标,
	"url": URL,
	"parentId": 上级菜单ID,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /menu/findById.do

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

> /menu/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| menu | true | menu | 实体对象 |

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

> /menu/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| menu | true | menu | 实体对象 |

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

> /menu/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /menu/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
