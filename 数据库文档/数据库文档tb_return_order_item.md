# 数据库文档 

### tb_return_order_item  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | BIGINT   |      |      |
| category_id  | 分类ID | BIGINT   |      |      |
| spu_id  | SPU_ID | BIGINT   |      |      |
| sku_id  | SKU_ID | BIGINT   |      |      |
| order_id  | 订单ID | BIGINT   |      |      |
| order_item_id  | 订单明细ID | BIGINT   |      |      |
| return_order_id  | 退货订单ID | BIGINT   |      |      |
| title  | 标题 | VARCHAR   |      |      |
| price  | 单价 | INT   |      |      |
| num  | 数量 | INT   |      |      |
| money  | 总金额 | INT   |      |      |
| pay_money  | 支付金额 | INT   |      |      |
| image  | 图片地址 | VARCHAR   |      |      |
| weight  | 重量 | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /returnOrderItem/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"categoryId": 分类ID,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"orderItemId": 订单明细ID,
	"returnOrderId": 退货订单ID,
	"title": 标题,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 支付金额,
	"image": 图片地址,
	"weight": 重量,

},
.......
]
```



#### 分页查询数据  

##### url

> /returnOrderItem/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /returnOrderItem/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"categoryId": 分类ID,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"orderItemId": 订单明细ID,
	"returnOrderId": 退货订单ID,
	"title": 标题,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 支付金额,
	"image": 图片地址,
	"weight": 重量,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /returnOrderItem/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /returnOrderItem/findList.do
{
	"id": ID,
	"categoryId": 分类ID,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"orderItemId": 订单明细ID,
	"returnOrderId": 退货订单ID,
	"title": 标题,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 支付金额,
	"image": 图片地址,
	"weight": 重量,

}
```

##### 返回格式

```
[{
	"id": ID,
	"categoryId": 分类ID,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"orderItemId": 订单明细ID,
	"returnOrderId": 退货订单ID,
	"title": 标题,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 支付金额,
	"image": 图片地址,
	"weight": 重量,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /returnOrderItem/findPage.do

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
POST /returnOrderItem/findPage.do?page=1&size=10
{
	"id": ID,
	"categoryId": 分类ID,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"orderItemId": 订单明细ID,
	"returnOrderId": 退货订单ID,
	"title": 标题,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 支付金额,
	"image": 图片地址,
	"weight": 重量,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"categoryId": 分类ID,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"orderItemId": 订单明细ID,
	"returnOrderId": 退货订单ID,
	"title": 标题,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 支付金额,
	"image": 图片地址,
	"weight": 重量,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /returnOrderItem/findById.do

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

> /returnOrderItem/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| returnOrderItem | true | returnOrderItem | 实体对象 |

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

> /returnOrderItem/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| returnOrderItem | true | returnOrderItem | 实体对象 |

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

> /returnOrderItem/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /returnOrderItem/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
