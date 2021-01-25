# Spring

## IoC

### IoC 是什么？

- 一种编程思想 VS 传统

	- 解耦、屏蔽细节
	- 构造和初始化（属性）交给容器

- IoC 容器

	- 生命周期

- 好莱坞原则

	- 导演
	- 演员

### 实现策略

- 依赖注入 DI

	- 由自主拉取，变为（容器）被动获取
	- 依赖注入示例

		- 依赖来源

- 依赖查找

	- 上下文
	- Java Beans

- 依赖查找 和 依赖注入的区别

	- 依赖查找是主动或手动的依赖查找方式，通常需要依赖容器或标准 API 实现。而依赖注入则是手动或自动依赖绑定的方式，无需依赖特定的容器和 API

### BeanFactory VS ApplicationContext VS ObjectFactory

### 依赖来源

- 自定义 Bean
- 依赖注入（內建依赖）
- 容器內建 Bean

### 问题

- 为什么要将@Service和@Repository放到实现类上面而不是接口类上面

	- 通过注解注入bean，就是实例化依赖类的方式
	- 接口只是一个规范，需要各种实现类去实现这个接口，我们要用的就是这些实用类的方法

- Feign 单元测试注入失败

## 数据存储

### JDBC

- 连接池

	- HiKariCP

		- 字节码级的优化

			- 由 Assist 生成

		- 小改进

			- FastStatement 代替 ArrarList
			- 子主题 2
			- 子主题 3

		- 子主题 3

	- Tomcat 的多线程

- 如何配置数据源

### 事务抽象

- 核心接口

	- TransactionDefinition

- 事务的传播特性

	- 声明式事务

- 事务隔离特性

	- 默认为-1(取决于数据库)
	- 1-读未提交（Read uncommitted）
	- 2-允许从已经提交的并发事务读取
	- 3-
	- 4-

- 关系

	- 事务和jdbc连接池中的连接是什么关系？

		- 事务是在连接上的
		- 一个事务开启后的多条SQL都是执行在这个连接上的，不会更换连接

### DAO 支持

### O/R 映射 Mapping

### NoSql

- 缓存抽象

	- 什么时候用缓存

		- 长期不变的放 JVM
		- 集中式缓冲的开销
		- 不该用缓存

			- 读10次写1次

	- eg: Redis 缓存
	- 缓存存储在什么地方

		- 集中式缓存的问题

	- 存储类型

		- 序列化

	- 接口

		- org.springframework.core.cache.Cache
		- org.springframework.core.cache.CacheManager

	- 注解

		- @EnableCache
		- @CacheConfig(cacheNames="userName")
		- @EnableCaching(proxyTargetClass = true)

			- 用了 AOP 的思想

		- @Cacheable
		- @CacheEvict

- Redis

	- 哨兵与集群模式

- MongoDB

	- 文档型数据库
	- mybatis 不支持mongoDB

## Spring - JDK API

### Java 6 API

- JDBC 4.0

	- JdbcTemplate

- Common Annoations

	- CommonAnnotationBeanPostProcessor

- 可插拔注解处理API

  注解急剧膨胀。
  之前注解来自两类（都是运行时）：
  1. ASM
  2. 标准的 Java 反射
  
  编译时 @Indexed ，减少 Scan 
  

	- @Indexed

		- 编译时 VS 运行时 @ComponentScan（Base）

- Java Compiler  API

	- TestCompliler（单元测试）

	  运行时单独建立编译器，对 ATP 进行编译 
	  

## Spring Bean

### 生命周期

Servlet 也有

- 创建 Bean

	- 0. 什么时候创建？
	- 1. 实例化 Bean
	- 2. 设置 Bean 属性
	- 3. Aware 接口声明了依赖关系，则会注入 Bean 对容器基础设施层面的依赖
	- 4. 调用 BeanPostProcessor 的前置初始化方法 postProcessBeforeInitialization

- 销毁

### 作用域

- 1. Singleton(单例)

	- 在整个应用中，application context中（无状态）

		- 每个单例的无状态对象都是线程安全的
		- 无状态对象包括我们经常使用的DO、DTO、VO这些只作为数据的实体模型的贫血对象，还有Service、DAO和Controller

			- 每个DAO提供的函数都只是对数据库的CRUD，而且每个数据库Connection都作为函数的局部变量（局部变量是在用户栈中的，而且用户栈本身就是线程私有的内存区域，所以不存在线程安全问题），用完即关（或交还给连接池）

		- 不要在bean中声明任何有状态的实例变量或类变量，如果必须如此，那么就使用ThreadLocal把变量变为线程私有的

- 2. Propotype(原型)

	- 每次注入或者通过Spring应用上下文获取的时候,都会创建一个新的bean实例（有状态的 Bean）

- 3. Session(会话) 

	- Web应用中,为每个会话创建一个bean实例

- 4. Request(请求)

	- Web应用中,为每个请求创建一个bean实例

### 元信息

### 由Spring容器创建并托管的实例

## Spring Cloud

### 微服务

- Why

	- 优点

		- 独立部署
		- 拓展性强
		- 迭代速度快

	- 缺点

		- 架构复杂

			- 服务依赖

		- 部署复杂
		- 监控、排错困难
		- 事务一致性问题
		- 测试复杂

- what

	- 12-Factors
	- 不为了微服务而拆

- 服务治理

	- 关注点

		- 服务保护

			- 容错
			- 降级
			- 限流

		- 路由管理

			- 路由查询
			- 路由策略
			- 灰度策略

		- 数据管理

			- 元数据管理
			- 数据上报及更新
			- 服务注册和发现

		- 服务监控

			- 心跳检查
			- 健康检查

				- 自定义 Actuator, 消息小站的网络判断

			- 监控预警

				- grafana

		- 核心组件管理
		- 故障排除

			- 日志查询
			- 链路跟踪

		- 流量管控

	- Pafa-Cloud 治理功能矩阵

		- 集群

			- 启停
			- 服务列表
			- 注册中心

		- 服务

			- 实例列表
			- 日志级别调整

				- 全局
				- 包日志
				- 类日志

			- 灰度路由管理

				- 基于客户端IP
				- 基于权重 
				- 基于请求头
				- 基于一致性Hash
				- 组策略
				- 默认策略

			- 容错

				- 雪崩效应

					- 实现

						- 调整 Hystrix 埋点方法

					- 降级

						- 请求超时、资源不足

							- 不调用真实服务逻辑，快速失败（fallback）方式返回一个兜底数据，保证链路完整

					- 熔断

						- 异常请求比例（超时、网络故障、服务异常），启动熔断器，fallback

			- 日志检索

				- 服务名称
				- 时间区间
				- 级别
				- IP
				- 日志内容关键字
				- 线程名称
				- 类名称
				- 链路 Trace ID?
				- Span ID?

		- 实例

			- 实例注册中心上下线
			- 基本元数据查询？
			- 运行时信息查询

				- Base

					- ip地址、端口、版本、依赖包版本、元数据

				- System

					- 健康状态、进程信息、线程状态、JVM Heap/GC

				- Enviroment 

					- 属性列表？

				- Configration
				- Beans
				- Mappings
				- Threads

			- 配置信息查询
			- 健康检查

		- 接入层网关

			- 页面灰度
			- 反向代理服务
			- TSL 证书管理
			- Kong 插件管理

				- 子主题 1

			- 静态资源管理

				- 版本切换

		- 微服务网关

			- 服务路由查询
			- 服务、接口屏蔽
			- 服务限流
			- 可视化网关配置管理

	- 架构

		- Portal-Server

			- 服务治理平台组件，管理治理模块和用户服务集群之间的通信，负责数据通道请求和命令通道请求

		- Portal-Agent

			- 多环境治理

### Spring Cloud Native

- 配置中心

	- Git + SpringCloudConfig + SpringCloudBus
	- 配置加解密（无）

- 注册中心

	- SpringCloudConsul/Eureka/SpringCloud-Zookeeper

- 治理中心
- 监控可视化

	- 拓扑关系
	- SpringBootActuator + Eureka + SpringBootAdmin

- 服务交互

	- 服务 RPC

		- SpringCloudOpenFeign

	- 资源控制器

		- Rest支持 + SringMVC + SpringWebFlux

	- 消息总线

		- SpringCloudStream

	- 服务Task

		- SpringCloudTask

- 服务网关

	- Spring Cloud Gateway
	- Zuul
	- Spring Cloud Open Service Broker
	- Spring Cloud Ribbon

- 服务运维

	- Spring Cloud Sleuth

- 服务追踪
- 服务部署

	- Spring Cloud PepeLines

- 服务虚拟化

	- 服务网格化(无)

		- ServiceMesh ，业界对标SOFA Mesh/WeiboMesh 支持跨语言异构系统、轻量级网络代理、无代码侵入的服务治理、负载均衡

	- 云部署方案

		- Spring Cloud AWS

	- 服务移植 + 动态伸缩

- 业务服务工具

	- 分布式事务（两阶段提交，消息事务 + 最终一致书性 ， TCC 编程模式）
	- 分布式锁
	- 分布式计数器
	- 分布式会话
	- 数据源支持

		- SpringData JDBC

	- 分库分表
	- API  文档生成

		- Spring Cloud Swager

### k8s 组件 VS Rancher

- 容器自定义创建
- UI
- 网络
- 服务发现

	- 容器中运行 dns 服务器

- 容器伸缩

	- 一键扩容

- 项目管理

	- 以 stack 隔离

- 环境管理
- 资源管理
- 对象存储
- 监控
- 日志、ssh 工具

	- web ssh 工具

### 负载均衡

- 硬件

	- F5、Array等

- 软件

	- LVS、Nginx等

- 原理

	- 1. 维护一个服务端清单，利用心跳检测等手段进行清单维护，保证清单中都是可以正常访问的服务节点
	- 2. 当用户发送请求时，会先到达负载均衡器（也相当于一个服务），负载均衡器根据负载均衡算法（轮训、随机、加权轮训）从可用的服务端列表中取出一台服务端的地址，接着进行转发，降低系统的压力

- 客户端负载均衡（Ribbon）

	- 存储一份服务端清单，通过从注册中心（Eureka服务注册中心）进行抓取得到

## 框架总览

### 特性总览

### 版本特性

### 模块化设计

- 按需自取

### 技术整合

- Java 语言特性运用

	- Java 8 Lambda
	- Java 版本

- JDK API 实践

	- 动态代理 

		- 反射

- Java EE API 整合

### 编程模型

- OOP

	- 多态

		- 接口编程

			- Aware

				- ApplicationAware

			- BeanPostProcessor

	- 设计模式

		- 观察者模式

			- ApplicationEvent

		- 组合模式

			- CompositeCacheManager

		- 模板模式

			- JdbcTemplate
			- RestTemplate

	- 对象继承

		- Abstract* 类

			- AbstractApplicationContext

				- Ctrl + Alt + B 快捷键

			- AbstractFactoryBean

- AOP 面向切面编程

	- 动态代理
	- 字节码提升

- 面向元编程

	- 配置元数据
	- 注解
	- 属性配置

- 面向模块编程

	- Maven Artifacts
	- Java 9 Automatic Modules
	- Spring @Enable* 编程

		- @EnableAutoConfigration
		- @EnableCache
		- @Enable MVC

- 面向函数编程

	- Lambda

		- 没有太大优势？

	- Reactive

## AOP

### @Pointcut - Where

### @Aspect 类

- @Advice 方法 - - When
- @Around 方法

### 不能进行编译期的静态编织或者类加载期编织

- 可以使用 AspectJ

### 实际应用

- 方法调用前、后消耗时间日志
- Spring 声明式事务的实现机制

	- around

		- 方法执行前，先关闭数据库的自动提交功能，然后设定一个标志符

			- 数据更新成功赋true

	- after-throwing

		- 对出错的信息进行记录。然后再将错误抛出至上层

### 什么是 AOP?

-  OOP 编程思想的补充
- 实现机制

	- 动态代理(在运行时动态生成一个Java类)

		- 类加载机制

			- 加载

				- 将字节码数据从不同的数据源读取到 JVM 中，并映射为 JVM 认可的数据结构（Class 对象）

					- jar 文件
					- class 文件
					- 甚至是网络数据源
					- 否则报：ClassFormatError

				- 双亲委派模型

					- 避免重复加载 Java 类型

						- 当类加载器（Class-Loader）试图加载某个类型的时候，除非父加载器找不到相应类型，否则尽量将这个任务代理给当前加载器的父加载器去做
						- 实际案例：Maven 包管理时的 Jar hell jar包冲突

				- 加载器

					- 内建的类加载器

						- 1. 启动类加载器（Bootstrap Class-Loader）

							- 加载 jre/lib 下面的 jar 文件，如 rt.jar

						- 2. 扩展类加载器（Extension or Ext Class-Loader）

							- 加载  jre/lib/ext/ 目录下面的 jar 包

						- 3. 应用类加载器（Application or App Class-Loader）

							- classpath

					- 可自定义类加载

				- 加载机制有三个基本特征

					- 亲委派模型

						- 不是所有类加载都遵守这个模型

							- ServiceProvider 机制

								- 上下文加载器

									- JNDI、JDBC、文件系统、Cipher

								- 用户可以在标准 API 框架上，提供自己的实现

					- 可见性（父对子可见）

						- 子类加载器可以访问父加载器加载的类型，但是反过来是不允许的

					- 单一性

						- 父加载器中加载过的类型，就不会在子加载器中重复加载。

			- 链接

				- 验证（Verification

					- 保障虚拟机安全
					- 虚拟机规范

				- 准备（Preparation）

					- 创建类或接口中的静态变量，并初始化静态变量的初始值

				- 解析（Resolution）

					- 类、接口、方法和字段
					- 常量池中的符号引用（symbolic reference）替换为直接引用

			- 初始化（initialization

				- 真正去执行类初始化的代码逻辑

					- 静态字段赋值
					- 执行类定义中的静态初始化块内
					- 父类型的初始化逻辑优先于当前类型

		- 动态代码生成阶段

			- newProxyInstance 生成代理类实例

		- 反射的性能问题

			- 现代JDK有使用ASM码操作，不存在数量级的差距

		- VS 静态代理

			- 事先写好代理类，不灵活

- 实现方式

	- JDK Proxy

		- Class.forName
		- java.lang.reflect

			- Method
			- Field
			- Constructor

	- 字节码操技术

		- 应用

			- Mock 框架
			- 动态代理
			- IOC 容器
			- Profiler 工具

				- 运行时诊断工具等 Arthas

			- 生成形式化代码的工具

		- cglib

			- 创建目标类的子类？

	- 代码实现

		- 一个基础的接口，作为被调用类型

			- com.mycorp.HelloImpl

		- 代理类之间的统一入口

			- com.mycorp.Hello

		- 实现InvocationHandler
		- 通过 Proxy 类，调用其 newProxyInstance 方法，生成一个实现了相应基础接口的代理类实例

			- public static Object newProxyInstance(ClassLoader loader,    InvocationHandler h)Class<?>[] interfaces,

## Spring Boot

### 启动过程

- // SpringApplication.run( CodeSheepApplication.class args ); // 这是传统SpringBoot应用的启动，一行代码搞定，内部默认做了很多事
		SpringApplication app = new SpringApplication( CodeSheepApplication.class );
		app.setXXX( ... ); // 用户自定的扩展在此 ！！！
		app.run( args );

### Spring Boot 不是什么，是什么？

### 自动配置及原理

- @EnableAutoConfigration

	- eg: 自动配置 H2 内存数据库

- @Configuration

	- 工程应用

		- ResourceServerConfiguration extends ResourceServerConfigureAdapter

- 在 start.Spring.io 官网界面化选择更能体现

### 起步依赖及原理

- 多个依赖的兼容问题

	- Idea  的 Maven Helper 插件

- 多个 Module

	- dependceManager

- 面向功能
- starter

	- 官方
	- 自定义

		- 自动做 platform-starter 框架

### 配置加载机制

- 外化配置加载顺序

	- 1. 开启 DevTools,   ~/.spring-boot-devtools.properties
	- 2. 测试类上的 @TestPropertySource
	- 3. @SpringBootTest#properties 属性
	- 4. 命令行参数 --server.port = 8001
	- Spring_application_json 中的属性
	- System.getProperties()
	- 操作系统环境变量
	- jar 包外的 application-{profile}.properties

- application.properties 位置

	- ./config
	- ./
	- CLASSPATH 中的 ./config
	- CLASSPATH 中的 ./

### web 容器定制

- 关闭容器

	- setWebApplicationType #NONE
	- 常用工具类

		- 不同 Runnner
		- 返回码

			- ExitCodeGenerator

- web

### http2

- server.ssl.

	- eg:postman 选项设置

- 服务端

	- 生产证书

		- key-store-tool

- 客户端

### 可执行 Jar

- Maven 打包内容

	- Jar 包的描述
	- Spring Boot Loader,  org/springframework/boot/loader
	- 项目内容

		- BOOT-INF/class

	- 项目依赖

		- 不包含

			- JDK/JRE

- 程序的入口

	- Jar 的启动类

		- @Application

## 元信息

## web 技术

### Web Servlet

### Web Reactive

## Spring 常见源码汇总

### IoC

### 为什么想到 @Bean-MongoCustomConversions

- 如果想定制自己的组件特性，不妨打开Spring配置，看有没有留口子（MongoTemplate-丁雪丰）

## 线程池管理

### 上下文

### ThreadPoolTaskExecutor  VS ThreadPoolExecutor 区别

### @Async 配置

## Spring MVC

### 初始化和流程

### 基于Servlet的技术

### 组件

- 核心类DispatcherServlet

### 流程

- 1.用户发送请求至前端控制器DispatcherServlet
- 2.DispatcherServlet收到请求调用处理器映射器HandlerMapping。
- 3.处理器映射器根据请求url找到具体的处理器，生成处理器执行链HandlerExecutionChain(包括处理器对象和处理器拦截器)一并返回给DispatcherServlet。
- 4.DispatcherServlet根据处理器Handler获取处理器适配器HandlerAdapter执行HandlerAdapter处理一系列的操作，如：参数封装，数据格式转换，数据验证等操作
- 5.执行处理器Handler(Controller，也叫页面控制器)。
- 6.Handler执行完成返回ModelAndView
- 7.HandlerAdapter将Handler执行结果ModelAndView返回到DispatcherServlet
- 8.DispatcherServlet将ModelAndView传给ViewReslover视图解析器
- 9.ViewReslover解析后返回具体View
- 10.DispatcherServlet对View进行渲染视图（即将模型数据model填充至视图中）。
- 11.DispatcherServlet响应用户

### Spring MVC的初始化

- 1⃣️初始化Spring IOC

	- 通过配置ContextLoderListener完成

- 2⃣️初始化映射请求上下文

	- 通过配置DispatcherServlet完成

*XMind - Trial Version*