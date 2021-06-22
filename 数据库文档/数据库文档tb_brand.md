# 数据库文档 

### tb_brand  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 品牌id | INT   |      |      |
| name  | 品牌名称 | VARCHAR   |      |      |
| image  | 品牌图片地址 | VARCHAR   |      |      |
| letter  | 品牌的首字母 | CHAR   |      |      |
| seq  | 排序 | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /brand/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 品牌id,
	"name": 品牌名称,
	"image": 品牌图片地址,
	"letter": 品牌的首字母,
	"seq": 排序,

},
.......
]
```



#### 分页查询数据  

##### url

> /brand/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /brand/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 品牌id,
	"name": 品牌名称,
	"image": 品牌图片地址,
	"letter": 品牌的首字母,
	"seq": 排序,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /brand/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /brand/findList.do
{
	"id": 品牌id,
	"name": 品牌名称,
	"image": 品牌图片地址,
	"letter": 品牌的首字母,
	"seq": 排序,

}
```

##### 返回格式

```
[{
	"id": 品牌id,
	"name": 品牌名称,
	"image": 品牌图片地址,
	"letter": 品牌的首字母,
	"seq": 排序,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /brand/findPage.do

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
POST /brand/findPage.do?page=1&size=10
{
	"id": 品牌id,
	"name": 品牌名称,
	"image": 品牌图片地址,
	"letter": 品牌的首字母,
	"seq": 排序,

}
```

##### 返回格式

```
{rows:[{
	"id": 品牌id,
	"name": 品牌名称,
	"image": 品牌图片地址,
	"letter": 品牌的首字母,
	"seq": 排序,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /brand/findById.do

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

> /brand/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| brand | true | brand | 实体对象 |

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

> /brand/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| brand | true | brand | 实体对象 |

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

> /brand/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /brand/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
