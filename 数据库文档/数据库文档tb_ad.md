# 数据库文档 

### tb_ad  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | ID | INT   |      |      |
| name  | 广告名称 | VARCHAR   |      |      |
| position  | 广告位置 | VARCHAR   |      |      |
| start_time  | 开始时间 | DATETIME   |      |      |
| end_time  | 到期时间 | DATETIME   |      |      |
| status  | 状态 | CHAR   |      |      |
| image  | 图片地址 | VARCHAR   |      |      |
| url  | URL | VARCHAR   |      |      |
| remarks  | 备注 | VARCHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /ad/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": ID,
	"name": 广告名称,
	"position": 广告位置,
	"startTime": 开始时间,
	"endTime": 到期时间,
	"status": 状态,
	"image": 图片地址,
	"url": URL,
	"remarks": 备注,

},
.......
]
```



#### 分页查询数据  

##### url

> /ad/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /ad/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"name": 广告名称,
	"position": 广告位置,
	"startTime": 开始时间,
	"endTime": 到期时间,
	"status": 状态,
	"image": 图片地址,
	"url": URL,
	"remarks": 备注,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /ad/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /ad/findList.do
{
	"id": ID,
	"name": 广告名称,
	"position": 广告位置,
	"startTime": 开始时间,
	"endTime": 到期时间,
	"status": 状态,
	"image": 图片地址,
	"url": URL,
	"remarks": 备注,

}
```

##### 返回格式

```
[{
	"id": ID,
	"name": 广告名称,
	"position": 广告位置,
	"startTime": 开始时间,
	"endTime": 到期时间,
	"status": 状态,
	"image": 图片地址,
	"url": URL,
	"remarks": 备注,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /ad/findPage.do

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
POST /ad/findPage.do?page=1&size=10
{
	"id": ID,
	"name": 广告名称,
	"position": 广告位置,
	"startTime": 开始时间,
	"endTime": 到期时间,
	"status": 状态,
	"image": 图片地址,
	"url": URL,
	"remarks": 备注,

}
```

##### 返回格式

```
{rows:[{
	"id": ID,
	"name": 广告名称,
	"position": 广告位置,
	"startTime": 开始时间,
	"endTime": 到期时间,
	"status": 状态,
	"image": 图片地址,
	"url": URL,
	"remarks": 备注,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /ad/findById.do

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

> /ad/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| ad | true | ad | 实体对象 |

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

> /ad/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| ad | true | ad | 实体对象 |

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

> /ad/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /ad/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
