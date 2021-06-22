# 数据库文档 

### tb_order  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 订单id | VARCHAR   |      |      |
| total_num  | 数量合计 | INT   |      |      |
| total_money  | 金额合计 | INT   |      |      |
| pre_money  | 优惠金额 | INT   |      |      |
| post_fee  | 邮费 | INT   |      |      |
| pay_money  | 实付金额 | INT   |      |      |
| pay_type  | 支付类型，1、在线支付、0 货到付款 | VARCHAR   |      |      |
| create_time  | 订单创建时间 | DATETIME   |      |      |
| update_time  | 订单更新时间 | DATETIME   |      |      |
| pay_time  | 付款时间 | DATETIME   |      |      |
| consign_time  | 发货时间 | DATETIME   |      |      |
| end_time  | 交易完成时间 | DATETIME   |      |      |
| close_time  | 交易关闭时间 | DATETIME   |      |      |
| shipping_name  | 物流名称 | VARCHAR   |      |      |
| shipping_code  | 物流单号 | VARCHAR   |      |      |
| username  | 用户名称 | VARCHAR   |      |      |
| buyer_message  | 买家留言 | VARCHAR   |      |      |
| buyer_rate  | 是否评价 | CHAR   |      |      |
| receiver_contact  | 收货人 | VARCHAR   |      |      |
| receiver_mobile  | 收货人手机 | VARCHAR   |      |      |
| receiver_address  | 收货人地址 | VARCHAR   |      |      |
| source_type  | 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面 | CHAR   |      |      |
| transaction_id  | 交易流水号 | VARCHAR   |      |      |
| order_status  | 订单状态 | CHAR   |      |      |
| pay_status  | 支付状态 | CHAR   |      |      |
| consign_status  | 发货状态 | CHAR   |      |      |
| is_delete  | 是否删除 | CHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /order/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 订单id,
	"totalNum": 数量合计,
	"totalMoney": 金额合计,
	"preMoney": 优惠金额,
	"postFee": 邮费,
	"payMoney": 实付金额,
	"payType": 支付类型，1、在线支付、0 货到付款,
	"createTime": 订单创建时间,
	"updateTime": 订单更新时间,
	"payTime": 付款时间,
	"consignTime": 发货时间,
	"endTime": 交易完成时间,
	"closeTime": 交易关闭时间,
	"shippingName": 物流名称,
	"shippingCode": 物流单号,
	"username": 用户名称,
	"buyerMessage": 买家留言,
	"buyerRate": 是否评价,
	"receiverContact": 收货人,
	"receiverMobile": 收货人手机,
	"receiverAddress": 收货人地址,
	"sourceType": 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面,
	"transactionId": 交易流水号,
	"orderStatus": 订单状态,
	"payStatus": 支付状态,
	"consignStatus": 发货状态,
	"isDelete": 是否删除,

},
.......
]
```



#### 分页查询数据  

##### url

> /order/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /order/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 订单id,
	"totalNum": 数量合计,
	"totalMoney": 金额合计,
	"preMoney": 优惠金额,
	"postFee": 邮费,
	"payMoney": 实付金额,
	"payType": 支付类型，1、在线支付、0 货到付款,
	"createTime": 订单创建时间,
	"updateTime": 订单更新时间,
	"payTime": 付款时间,
	"consignTime": 发货时间,
	"endTime": 交易完成时间,
	"closeTime": 交易关闭时间,
	"shippingName": 物流名称,
	"shippingCode": 物流单号,
	"username": 用户名称,
	"buyerMessage": 买家留言,
	"buyerRate": 是否评价,
	"receiverContact": 收货人,
	"receiverMobile": 收货人手机,
	"receiverAddress": 收货人地址,
	"sourceType": 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面,
	"transactionId": 交易流水号,
	"orderStatus": 订单状态,
	"payStatus": 支付状态,
	"consignStatus": 发货状态,
	"isDelete": 是否删除,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /order/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /order/findList.do
{
	"id": 订单id,
	"totalNum": 数量合计,
	"totalMoney": 金额合计,
	"preMoney": 优惠金额,
	"postFee": 邮费,
	"payMoney": 实付金额,
	"payType": 支付类型，1、在线支付、0 货到付款,
	"createTime": 订单创建时间,
	"updateTime": 订单更新时间,
	"payTime": 付款时间,
	"consignTime": 发货时间,
	"endTime": 交易完成时间,
	"closeTime": 交易关闭时间,
	"shippingName": 物流名称,
	"shippingCode": 物流单号,
	"username": 用户名称,
	"buyerMessage": 买家留言,
	"buyerRate": 是否评价,
	"receiverContact": 收货人,
	"receiverMobile": 收货人手机,
	"receiverAddress": 收货人地址,
	"sourceType": 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面,
	"transactionId": 交易流水号,
	"orderStatus": 订单状态,
	"payStatus": 支付状态,
	"consignStatus": 发货状态,
	"isDelete": 是否删除,

}
```

##### 返回格式

```
[{
	"id": 订单id,
	"totalNum": 数量合计,
	"totalMoney": 金额合计,
	"preMoney": 优惠金额,
	"postFee": 邮费,
	"payMoney": 实付金额,
	"payType": 支付类型，1、在线支付、0 货到付款,
	"createTime": 订单创建时间,
	"updateTime": 订单更新时间,
	"payTime": 付款时间,
	"consignTime": 发货时间,
	"endTime": 交易完成时间,
	"closeTime": 交易关闭时间,
	"shippingName": 物流名称,
	"shippingCode": 物流单号,
	"username": 用户名称,
	"buyerMessage": 买家留言,
	"buyerRate": 是否评价,
	"receiverContact": 收货人,
	"receiverMobile": 收货人手机,
	"receiverAddress": 收货人地址,
	"sourceType": 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面,
	"transactionId": 交易流水号,
	"orderStatus": 订单状态,
	"payStatus": 支付状态,
	"consignStatus": 发货状态,
	"isDelete": 是否删除,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /order/findPage.do

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
POST /order/findPage.do?page=1&size=10
{
	"id": 订单id,
	"totalNum": 数量合计,
	"totalMoney": 金额合计,
	"preMoney": 优惠金额,
	"postFee": 邮费,
	"payMoney": 实付金额,
	"payType": 支付类型，1、在线支付、0 货到付款,
	"createTime": 订单创建时间,
	"updateTime": 订单更新时间,
	"payTime": 付款时间,
	"consignTime": 发货时间,
	"endTime": 交易完成时间,
	"closeTime": 交易关闭时间,
	"shippingName": 物流名称,
	"shippingCode": 物流单号,
	"username": 用户名称,
	"buyerMessage": 买家留言,
	"buyerRate": 是否评价,
	"receiverContact": 收货人,
	"receiverMobile": 收货人手机,
	"receiverAddress": 收货人地址,
	"sourceType": 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面,
	"transactionId": 交易流水号,
	"orderStatus": 订单状态,
	"payStatus": 支付状态,
	"consignStatus": 发货状态,
	"isDelete": 是否删除,

}
```

##### 返回格式

```
{rows:[{
	"id": 订单id,
	"totalNum": 数量合计,
	"totalMoney": 金额合计,
	"preMoney": 优惠金额,
	"postFee": 邮费,
	"payMoney": 实付金额,
	"payType": 支付类型，1、在线支付、0 货到付款,
	"createTime": 订单创建时间,
	"updateTime": 订单更新时间,
	"payTime": 付款时间,
	"consignTime": 发货时间,
	"endTime": 交易完成时间,
	"closeTime": 交易关闭时间,
	"shippingName": 物流名称,
	"shippingCode": 物流单号,
	"username": 用户名称,
	"buyerMessage": 买家留言,
	"buyerRate": 是否评价,
	"receiverContact": 收货人,
	"receiverMobile": 收货人手机,
	"receiverAddress": 收货人地址,
	"sourceType": 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面,
	"transactionId": 交易流水号,
	"orderStatus": 订单状态,
	"payStatus": 支付状态,
	"consignStatus": 发货状态,
	"isDelete": 是否删除,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /order/findById.do

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

> /order/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| order | true | order | 实体对象 |

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

> /order/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| order | true | order | 实体对象 |

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

> /order/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /order/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
