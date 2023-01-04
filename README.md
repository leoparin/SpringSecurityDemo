# SpringSecurityDemo
a simple demo of spring security inspired by sangeng
# default configuration
校验流程
![[Screenshot 2022-12-25 at 21.25.02.png]]
![[Screenshot 2022-12-26 at 08.33.37.png]]
**UsernamePasswordAuthenticationFilter**: 负责处理我们在登陆页面填写了用户名密码后的登陆请求。入门案例的认证工作主要有它负责。

**ExceptionTranslationFilter**：处理过滤器链中抛出的任何 AccessDeniedException 和 AuthenticationException 

**FilterSecurityInterceptor**：负责权限校验的过滤器。
![[Screenshot 2022-12-26 at 08.36.51.png]]
![[Screenshot 2022-12-26 at 08.38.35.png]]
![[Screenshot 2022-12-26 at 09.00.36.png]]
登陆：
1.自定义登陆借口
	调用providerManager的方法进行认证 认证通过生成jwt
	把用户信息存入redis
2自定义UserDetailsService
	在这个实现class中茶数据库

校验：
1.定义jwt过滤器
	获取token
	解析token获得其中的userid
	从reids中获取用户信息
	存入SecurityContextHolder
![[Screenshot 2022-12-26 at 09.42.09.png]]
![[Screenshot 2022-12-27 at 09.49.18.png]]

UserDetailServices:
responsible for retriving the user by username

UseDetailManager:
responsible for adding functionality

# filterchain
## what？

## why？

## how？


UsernamePasswordAuthenticationToken:
what
提供一个authentication对象

三个参数的方法可以设定对象状态为已认证

HttpSecurityConfig
![[Screenshot 2022-12-30 at 09.35.32.png]]http
返回httpSecurity对象来继续配置
.and()方法无论如何返回httpsecurity对象 

authorizeRequest
antMatchers（）对指定路径或者请求方式进行配置
authorized：必须认证后才能访问

rbac：权限模型 ![[Screenshot 2022-12-30 at 10.28.02.png]]
 
 ![[Screenshot 2022-12-31 at 07.20.20.png]]

![[Screenshot 2022-12-31 at 07.23.59.png]]

就像机场层层安检，manager就是安检人员，authentication provider就是安检仪器。

