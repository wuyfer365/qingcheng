# 数据库文档 

### tb_preferential  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | INT   |      |      |
| buy_money  | 消费金额 | INT   |      |      |
| pre_money  | 优惠金额 | INT   |      |      |
| category_id  | 品类ID | INT   |      |      |
| start_time  | 活动开始日期 | DATE   |      |      |
| end_time  | 活动截至日期 | DATE   |      |      |
| state  | 状态 | VARCHAR   |      |      |
| type  | 类型1不翻倍 2翻倍 | VARCHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /preferential/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"categoryId": 品类ID,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"state": 状态,
	"type": 类型1不翻倍 2翻倍,

},
.......
]
```



#### 分页查询数据  

##### url

> /preferential/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /preferential/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"categoryId": 品类ID,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"state": 状态,
	"type": 类型1不翻倍 2翻倍,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /preferential/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /preferential/findList.do
{
	"id": ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"categoryId": 品类ID,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"state": 状态,
	"type": 类型1不翻倍 2翻倍,

}
```

##### 返回格式

```
[{
	"id": ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"categoryId": 品类ID,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"state": 状态,
	"type": 类型1不翻倍 2翻倍,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /preferential/findPage.do

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
POST /preferential/findPage.do?page=1&size=10
{
	"id": ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"categoryId": 品类ID,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"state": 状态,
	"type": 类型1不翻倍 2翻倍,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"categoryId": 品类ID,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"state": 状态,
	"type": 类型1不翻倍 2翻倍,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /preferential/findById.do

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

> /preferential/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| preferential | true | preferential | 实体对象 |

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

> /preferential/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| preferential | true | preferential | 实体对象 |

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

> /preferential/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /preferential/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
