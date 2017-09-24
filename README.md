# RESTful风格鉴权设计

## REST简介

REST (Representational State Transfer) 是一种软件架构风格。它将服务端的信息和功能等所有事物统称为资源，客户端的请求实际就是对资源进行操作，它的主要特点有： 每一个资源都会对应一个独一无二的 url，客户端通过 HTTP 的 GET、POST、PUT、DELETE 请求方法对资源进行查询、创建、修改、删除操作。 客户端与服务端的交互必须是无状态的。

## Token身份鉴权

网站应用一般使用 Session 进行登录用户信息的存储及验证，而在移动端使用 Token 则更加普遍。它们之间并没有太大区别，Token 比较像是一个更加精简的自定义的 Session。Session 的主要功能是保持会话信息，而 Token 则只用于登录用户的身份鉴权。所以在移动端使用 Token 会比使用 Session 更加简易并且有更高的安全性，同时也更加符合 RESTful 中无状态的定义。
