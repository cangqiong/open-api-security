# open-api-security
open API 安全设计

参考网上的接口安全设计，设计开发自己的接口安全机制

## API接口保障安全性原则
1. 有调用者身份
2. 请求的唯一性
3. 请求的参数不能被篡改
4. 请求的有效时间

## 安全接口设计原理
通过验证调用方或第三方的Token与唯一签名与时间戳标识用户的有效性
，从而保证用户调用接口的安全性

## Demo
https://***.com/search/users/?safe=strict&q=测试&cad=h
上面的/serarch/users为访问路径
safe=strict&q=测试&cad=h为请求参数





