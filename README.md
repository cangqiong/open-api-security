# open-api-security
open API 安全设计

参考网上的接口安全设计，设计开发自己的接口安全机制

## API接口保障安全性原则
1. 有调用者身份
2. 请求的唯一性
3. 请求的参数不能被篡改
4. 请求的有效时间

## 安全接口设计原理
通过验证调用方/第三方的Token与唯一签名与时间戳标识用户的有效性，从而保证用户调用接口的安全性

## Demo
http://localhost:8081/hello/sayHello?name=world&token=72e07590f879c8e5e291d2aaca8d68b2&timestamp=1520672558698&appkey=934d099851cab886fcd7db9ca1c442fe
上面的/hello/sayHello为访问路径
name=world为请求参数
token=72e07590f879c8e5e291d2aaca8d68b2为请求Token
timestamp=1520672558698为请求发出的时间
appkey=934d099851cab886fcd7db9ca1c442fe为加密签名








