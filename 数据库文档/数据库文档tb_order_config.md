# 数据库文档 

### tb_order_config  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | INT   |      |      |
| order_timeout  | 正常订单超时时间（分） | INT   |      |      |
| seckill_timeout  | 秒杀订单超时时间（分） | INT   |      |      |
| take_timeout  | 自动收货（天） | INT   |      |      |
| service_timeout  | 售后期限 | INT   |      |      |
| comment_timeout  | 自动五星好评 | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /orderConfig/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"orderTimeout": 正常订单超时时间（分）,
	"seckillTimeout": 秒杀订单超时时间（分）,
	"takeTimeout": 自动收货（天）,
	"serviceTimeout": 售后期限,
	"commentTimeout": 自动五星好评,

},
.......
]
```



#### 分页查询数据  

##### url

> /orderConfig/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /orderConfig/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"orderTimeout": 正常订单超时时间（分）,
	"seckillTimeout": 秒杀订单超时时间（分）,
	"takeTimeout": 自动收货（天）,
	"serviceTimeout": 售后期限,
	"commentTimeout": 自动五星好评,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /orderConfig/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /orderConfig/findList.do
{
	"id": ID,
	"orderTimeout": 正常订单超时时间（分）,
	"seckillTimeout": 秒杀订单超时时间（分）,
	"takeTimeout": 自动收货（天）,
	"serviceTimeout": 售后期限,
	"commentTimeout": 自动五星好评,

}
```

##### 返回格式

```
[{
	"id": ID,
	"orderTimeout": 正常订单超时时间（分）,
	"seckillTimeout": 秒杀订单超时时间（分）,
	"takeTimeout": 自动收货（天）,
	"serviceTimeout": 售后期限,
	"commentTimeout": 自动五星好评,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /orderConfig/findPage.do

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
POST /orderConfig/findPage.do?page=1&size=10
{
	"id": ID,
	"orderTimeout": 正常订单超时时间（分）,
	"seckillTimeout": 秒杀订单超时时间（分）,
	"takeTimeout": 自动收货（天）,
	"serviceTimeout": 售后期限,
	"commentTimeout": 自动五星好评,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"orderTimeout": 正常订单超时时间（分）,
	"seckillTimeout": 秒杀订单超时时间（分）,
	"takeTimeout": 自动收货（天）,
	"serviceTimeout": 售后期限,
	"commentTimeout": 自动五星好评,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /orderConfig/findById.do

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

> /orderConfig/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| orderConfig | true | orderConfig | 实体对象 |

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

> /orderConfig/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| orderConfig | true | orderConfig | 实体对象 |

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

> /orderConfig/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /orderConfig/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
