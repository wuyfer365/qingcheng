# 数据库文档 

### tb_pref  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | INT   |      |      |
| cate_id  | 分类ID | INT   |      |      |
| buy_money  | 消费金额 | INT   |      |      |
| pre_money  | 优惠金额 | INT   |      |      |
| start_time  | 活动开始日期 | DATE   |      |      |
| end_time  | 活动截至日期 | DATE   |      |      |
| type  | 类型 | CHAR   |      |      |
| state  | 状态 | CHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /pref/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"cateId": 分类ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"type": 类型,
	"state": 状态,

},
.......
]
```



#### 分页查询数据  

##### url

> /pref/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /pref/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"cateId": 分类ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"type": 类型,
	"state": 状态,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /pref/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /pref/findList.do
{
	"id": ID,
	"cateId": 分类ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"type": 类型,
	"state": 状态,

}
```

##### 返回格式

```
[{
	"id": ID,
	"cateId": 分类ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"type": 类型,
	"state": 状态,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /pref/findPage.do

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
POST /pref/findPage.do?page=1&size=10
{
	"id": ID,
	"cateId": 分类ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"type": 类型,
	"state": 状态,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"cateId": 分类ID,
	"buyMoney": 消费金额,
	"preMoney": 优惠金额,
	"startTime": 活动开始日期,
	"endTime": 活动截至日期,
	"type": 类型,
	"state": 状态,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /pref/findById.do

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

> /pref/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| pref | true | pref | 实体对象 |

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

> /pref/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| pref | true | pref | 实体对象 |

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

> /pref/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /pref/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
