# 数据库文档 

### tb_order_log  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | VARCHAR   |      |      |
| operater  | 操作员 | VARCHAR   |      |      |
| operate_time  | 操作时间 | DATETIME   |      |      |
| order_id  | 订单ID | VARCHAR   |      |      |
| order_status  | 订单状态 | CHAR   |      |      |
| pay_status  | 付款状态 | CHAR   |      |      |
| consign_status  | 发货状态 | CHAR   |      |      |
| remarks  | 备注 | VARCHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /orderLog/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"operater": 操作员,
	"operateTime": 操作时间,
	"orderId": 订单ID,
	"orderStatus": 订单状态,
	"payStatus": 付款状态,
	"consignStatus": 发货状态,
	"remarks": 备注,

},
.......
]
```



#### 分页查询数据  

##### url

> /orderLog/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /orderLog/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"operater": 操作员,
	"operateTime": 操作时间,
	"orderId": 订单ID,
	"orderStatus": 订单状态,
	"payStatus": 付款状态,
	"consignStatus": 发货状态,
	"remarks": 备注,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /orderLog/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /orderLog/findList.do
{
	"id": ID,
	"operater": 操作员,
	"operateTime": 操作时间,
	"orderId": 订单ID,
	"orderStatus": 订单状态,
	"payStatus": 付款状态,
	"consignStatus": 发货状态,
	"remarks": 备注,

}
```

##### 返回格式

```
[{
	"id": ID,
	"operater": 操作员,
	"operateTime": 操作时间,
	"orderId": 订单ID,
	"orderStatus": 订单状态,
	"payStatus": 付款状态,
	"consignStatus": 发货状态,
	"remarks": 备注,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /orderLog/findPage.do

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
POST /orderLog/findPage.do?page=1&size=10
{
	"id": ID,
	"operater": 操作员,
	"operateTime": 操作时间,
	"orderId": 订单ID,
	"orderStatus": 订单状态,
	"payStatus": 付款状态,
	"consignStatus": 发货状态,
	"remarks": 备注,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"operater": 操作员,
	"operateTime": 操作时间,
	"orderId": 订单ID,
	"orderStatus": 订单状态,
	"payStatus": 付款状态,
	"consignStatus": 发货状态,
	"remarks": 备注,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /orderLog/findById.do

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

> /orderLog/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| orderLog | true | orderLog | 实体对象 |

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

> /orderLog/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| orderLog | true | orderLog | 实体对象 |

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

> /orderLog/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /orderLog/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
