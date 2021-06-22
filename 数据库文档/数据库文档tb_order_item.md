# 数据库文档 

### tb_order_item  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | VARCHAR   |      |      |
| category_id1  | 1级分类 | INT   |      |      |
| category_id2  | 2级分类 | INT   |      |      |
| category_id3  | 3级分类 | INT   |      |      |
| spu_id  | SPU_ID | VARCHAR   |      |      |
| sku_id  | SKU_ID | VARCHAR   |      |      |
| order_id  | 订单ID | VARCHAR   |      |      |
| name  | 商品名称 | VARCHAR   |      |      |
| price  | 单价 | INT   |      |      |
| num  | 数量 | INT   |      |      |
| money  | 总金额 | INT   |      |      |
| pay_money  | 实付金额 | INT   |      |      |
| image  | 图片地址 | VARCHAR   |      |      |
| weight  | 重量 | INT   |      |      |
| post_fee  | 运费 | INT   |      |      |
| is_return  | 是否退货 | CHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /orderItem/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"categoryId1": 1级分类,
	"categoryId2": 2级分类,
	"categoryId3": 3级分类,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"name": 商品名称,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 实付金额,
	"image": 图片地址,
	"weight": 重量,
	"postFee": 运费,
	"isReturn": 是否退货,

},
.......
]
```



#### 分页查询数据  

##### url

> /orderItem/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /orderItem/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"categoryId1": 1级分类,
	"categoryId2": 2级分类,
	"categoryId3": 3级分类,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"name": 商品名称,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 实付金额,
	"image": 图片地址,
	"weight": 重量,
	"postFee": 运费,
	"isReturn": 是否退货,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /orderItem/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /orderItem/findList.do
{
	"id": ID,
	"categoryId1": 1级分类,
	"categoryId2": 2级分类,
	"categoryId3": 3级分类,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"name": 商品名称,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 实付金额,
	"image": 图片地址,
	"weight": 重量,
	"postFee": 运费,
	"isReturn": 是否退货,

}
```

##### 返回格式

```
[{
	"id": ID,
	"categoryId1": 1级分类,
	"categoryId2": 2级分类,
	"categoryId3": 3级分类,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"name": 商品名称,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 实付金额,
	"image": 图片地址,
	"weight": 重量,
	"postFee": 运费,
	"isReturn": 是否退货,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /orderItem/findPage.do

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
POST /orderItem/findPage.do?page=1&size=10
{
	"id": ID,
	"categoryId1": 1级分类,
	"categoryId2": 2级分类,
	"categoryId3": 3级分类,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"name": 商品名称,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 实付金额,
	"image": 图片地址,
	"weight": 重量,
	"postFee": 运费,
	"isReturn": 是否退货,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"categoryId1": 1级分类,
	"categoryId2": 2级分类,
	"categoryId3": 3级分类,
	"spuId": SPU_ID,
	"skuId": SKU_ID,
	"orderId": 订单ID,
	"name": 商品名称,
	"price": 单价,
	"num": 数量,
	"money": 总金额,
	"payMoney": 实付金额,
	"image": 图片地址,
	"weight": 重量,
	"postFee": 运费,
	"isReturn": 是否退货,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /orderItem/findById.do

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

> /orderItem/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| orderItem | true | orderItem | 实体对象 |

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

> /orderItem/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| orderItem | true | orderItem | 实体对象 |

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

> /orderItem/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /orderItem/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
