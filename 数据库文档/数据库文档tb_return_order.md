# 数据库文档 

### tb_return_order  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 服务单号 | BIGINT   |      |      |
| order_id  | 订单号 | BIGINT   |      |      |
| apply_time  | 申请时间 | DATETIME   |      |      |
| user_id  | 用户ID | BIGINT   |      |      |
| user_account  | 用户账号 | VARCHAR   |      |      |
| linkman  | 联系人 | VARCHAR   |      |      |
| linkman_mobile  | 联系人手机 | VARCHAR   |      |      |
| type  | 类型 | CHAR   |      |      |
| return_money  | 退款金额 | INT   |      |      |
| is_return_freight  | 是否退运费 | CHAR   |      |      |
| status  | 申请状态 | CHAR   |      |      |
| dispose_time  | 处理时间 | DATETIME   |      |      |
| return_cause  | 退货退款原因 | INT   |      |      |
| evidence  | 凭证图片 | VARCHAR   |      |      |
| description  | 问题描述 | VARCHAR   |      |      |
| remark  | 处理备注 | VARCHAR   |      |      |
| admin_id  | 管理员id | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /returnOrder/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 服务单号,
	"orderId": 订单号,
	"applyTime": 申请时间,
	"userId": 用户ID,
	"userAccount": 用户账号,
	"linkman": 联系人,
	"linkmanMobile": 联系人手机,
	"type": 类型,
	"returnMoney": 退款金额,
	"isReturnFreight": 是否退运费,
	"status": 申请状态,
	"disposeTime": 处理时间,
	"returnCause": 退货退款原因,
	"evidence": 凭证图片,
	"description": 问题描述,
	"remark": 处理备注,
	"adminId": 管理员id,

},
.......
]
```



#### 分页查询数据  

##### url

> /returnOrder/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /returnOrder/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 服务单号,
	"orderId": 订单号,
	"applyTime": 申请时间,
	"userId": 用户ID,
	"userAccount": 用户账号,
	"linkman": 联系人,
	"linkmanMobile": 联系人手机,
	"type": 类型,
	"returnMoney": 退款金额,
	"isReturnFreight": 是否退运费,
	"status": 申请状态,
	"disposeTime": 处理时间,
	"returnCause": 退货退款原因,
	"evidence": 凭证图片,
	"description": 问题描述,
	"remark": 处理备注,
	"adminId": 管理员id,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /returnOrder/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /returnOrder/findList.do
{
	"id": 服务单号,
	"orderId": 订单号,
	"applyTime": 申请时间,
	"userId": 用户ID,
	"userAccount": 用户账号,
	"linkman": 联系人,
	"linkmanMobile": 联系人手机,
	"type": 类型,
	"returnMoney": 退款金额,
	"isReturnFreight": 是否退运费,
	"status": 申请状态,
	"disposeTime": 处理时间,
	"returnCause": 退货退款原因,
	"evidence": 凭证图片,
	"description": 问题描述,
	"remark": 处理备注,
	"adminId": 管理员id,

}
```

##### 返回格式

```
[{
	"id": 服务单号,
	"orderId": 订单号,
	"applyTime": 申请时间,
	"userId": 用户ID,
	"userAccount": 用户账号,
	"linkman": 联系人,
	"linkmanMobile": 联系人手机,
	"type": 类型,
	"returnMoney": 退款金额,
	"isReturnFreight": 是否退运费,
	"status": 申请状态,
	"disposeTime": 处理时间,
	"returnCause": 退货退款原因,
	"evidence": 凭证图片,
	"description": 问题描述,
	"remark": 处理备注,
	"adminId": 管理员id,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /returnOrder/findPage.do

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
POST /returnOrder/findPage.do?page=1&size=10
{
	"id": 服务单号,
	"orderId": 订单号,
	"applyTime": 申请时间,
	"userId": 用户ID,
	"userAccount": 用户账号,
	"linkman": 联系人,
	"linkmanMobile": 联系人手机,
	"type": 类型,
	"returnMoney": 退款金额,
	"isReturnFreight": 是否退运费,
	"status": 申请状态,
	"disposeTime": 处理时间,
	"returnCause": 退货退款原因,
	"evidence": 凭证图片,
	"description": 问题描述,
	"remark": 处理备注,
	"adminId": 管理员id,

}
```

##### 返回格式

```
{rows:[{
	"id": 服务单号,
	"orderId": 订单号,
	"applyTime": 申请时间,
	"userId": 用户ID,
	"userAccount": 用户账号,
	"linkman": 联系人,
	"linkmanMobile": 联系人手机,
	"type": 类型,
	"returnMoney": 退款金额,
	"isReturnFreight": 是否退运费,
	"status": 申请状态,
	"disposeTime": 处理时间,
	"returnCause": 退货退款原因,
	"evidence": 凭证图片,
	"description": 问题描述,
	"remark": 处理备注,
	"adminId": 管理员id,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /returnOrder/findById.do

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

> /returnOrder/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| returnOrder | true | returnOrder | 实体对象 |

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

> /returnOrder/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| returnOrder | true | returnOrder | 实体对象 |

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

> /returnOrder/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /returnOrder/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
