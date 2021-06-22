# 数据库文档 

### tb_provinces  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| provinceid  | 省份ID | VARCHAR   |      |      |
| province  | 省份名称 | VARCHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /provinces/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"provinceid": 省份ID,
	"province": 省份名称,

},
.......
]
```



#### 分页查询数据  

##### url

> /provinces/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /provinces/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"provinceid": 省份ID,
	"province": 省份名称,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /provinces/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /provinces/findList.do
{
	"provinceid": 省份ID,
	"province": 省份名称,

}
```

##### 返回格式

```
[{
	"provinceid": 省份ID,
	"province": 省份名称,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /provinces/findPage.do

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
POST /provinces/findPage.do?page=1&size=10
{
	"provinceid": 省份ID,
	"province": 省份名称,

}
```

##### 返回格式

```
{rows:[{
	"provinceid": 省份ID,
	"province": 省份名称,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /provinces/findById.do

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

> /provinces/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| provinces | true | provinces | 实体对象 |

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

> /provinces/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| provinces | true | provinces | 实体对象 |

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

> /provinces/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /provinces/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
