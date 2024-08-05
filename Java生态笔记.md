### Spring

#### Spring介绍

##### 什么是SpringFromwork？

* Spring是一个轻量级Java开发框架，解决企业级应用开发的复杂性。
* 为企业级开发提供了丰富的功能，这些功能底层依赖于它的两个核心特性：**依赖注入（DI）**和**面向切面编程（AOP）**。
* Spring是一个**IOC（控制反转）**和**AOP**的容器框架。

##### IOC（控制反转）

* 把创建和查找依赖的控制权交给IOC容器，由IOC容器进行注入、组合对象之间的关系（对象由spring来创建、管理、装配）。减少对象的创建和内存消耗，使系统更灵活、易扩展、可维护。
* IOC两大基本原则：**接口分离原则**和**依赖倒置原则**。
* IOC是设计思想，DI是具体实现。

#### Spring底层原理

##### ApplicationContext加载过程

* 1.super(parent)：初始化父类，获得xml路径资源解析器

* 2.setConfigLocations(configLocations)：通过环境变量解析xml路径

* refresh()：这个方法是spring生命周期
  * prepareRefresh(); //这个方法准备上下文进行刷新，设置启动日期和活动标志，以及执行任何属性源的初始化。
  * ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();   //这个方法告诉子类刷新内部Bean工厂
  * prepareBeanFactory(beanFactory); //对bean工厂进行填充属性
  * postProcessBeanFactory(beanFactory); //留给子类去实现该接口
  * **invokeBeanFactoryPostProcessors(beanFactory); 调用 bean工厂的后置处理器，主要用于修改或增强Bean定义**
  * **registerBeanPostProcessors(beanFactory); //调用bean的后置处理器，主要用于修改或增强Bean实例。**
  * initMessageSource(); //初始化国际资源处理器
  * initApplicationEventMulticaster(); //创建事件多播器
  * onRefresh(); //留给子类实现，springboot从这个方法启动tomcat
  * registerListeners(); //把事件监听器注册在多播器上
  * **finishBeanFactoryInitialization(beanFactory); //实例化所有bean**
  * finishRefresh(); //容器刷新 发布刷新事件（springCloude也是从这里启动）

* 3.创建Bean工厂

* 4.加载Bean定义到BeanDefinitionMap中

* 5.调用bean工厂的后置处理器

* 6.判断是否符合生产标准，如：是不是抽象、懒加载、单例等

* 7.调用doGetBean生产Bean，去单例池中获取是否创建，如果创建直接返回，如果没有则需要重新创建（为了解决循环依赖，将Bean加入到正在创建的标识中singletonsCurrentlylnCreation）

* 8.使用Bean后置处理器直接返回自定义的Bean实例

* 9.调用doCreateBean开始真正创建Bean
  * 调用createBeanInstance实例化Bean（通过无参构造函数、工厂方法、Supplier、Bean后置处理器等）

  * 注入属性
  * 初始化Bean

* 10.最终加入到单例池中

##### 九个后置处理器

1. **BeanFactoryPostProcessor**:
   - 用于在Bean定义加载后、实例化之前对Bean工厂进行修改。
2. **BeanPostProcessor**:
   - 用于在Bean实例化后、初始化前后对Bean进行处理。
3. **InstantiationAwareBeanPostProcessor**:
   - 扩展自BeanPostProcessor，提供在Bean实例化前后的额外处理能力。
4. **DestructionAwareBeanPostProcessor**:
   - 用于在Bean销毁前进行处理。
5. **SmartInstantiationAwareBeanPostProcessor**:
   - 扩展自InstantiationAwareBeanPostProcessor，提供更智能的实例化前后的处理能力。
6. **MergedBeanDefinitionPostProcessor**:
   - 用于在Bean定义合并后进行处理。
7. **ApplicationContextAwareProcessor**:
   - 用于处理实现了ApplicationContextAware接口的Bean，使其能够获取ApplicationContext。
8. **ApplicationListenerDetector**:
   - 用于检测实现了ApplicationListener接口的Bean，并将其注册为事件监听器。
9. **LoadTimeWeaverAwareProcessor**:
   - 用于处理实现了LoadTimeWeaverAware接口的Bean，使其能够获取LoadTimeWeaver。

#### SpringIOC容器 --- 基于XML配置

* ##### 容器概述

  * ApplicationContext负责实例化、配置和组装Bean，容器通过读取配置元数据获取有关实例化、配置和组装对象。它允许你处理应用程序的对象与其他对象之间 的相互依赖关系。在容器实例化的时候就会加载所有Bean

* ##### 容器的使用

  ```java
  // 创建spring上下文  加载所有Bean
  AppliationContext applicationContext = new ClassPathXmlApplication("XML相对文件路径"); //基于XML方式
  AppliationContext applicationContext = new AnnotationConfigApplication("javaconfig类相对路径"); //基于注解
  //获取bean
  //1. 通过类获取Bean   getBean(a.Class);
  //2. 通过id或者bean的名字获取Bean 
  //3. 通过名字+类获取Bean
  applicationContext.getBean().var;
  ```

* ##### 基于XML声明Bean

  ```xml
  <!-- 声明Bean -->
  <bean class="需要被装配的类" id = "唯一标识">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  
  <!-- 控制bean加载顺序，当Bean想让另一个bean在它之前加载 -->
  <bean class="需要被装配的类" id = "唯一标识" deprnds-on="id2">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  
  <!-- 懒加载bean 在使用时才实例化bean -->
  <bean class="需要被装配的类" id = "唯一标识" lazy-init="true">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  
  <!-- 设置Bean的别名 -->
  <alias name = "需要其别名的Bean的id" alias = "别名"></alias>
  
  <!-- 导入其他spring的XML配置文件 -->
  <import resource="XML文件"></import>
  ```

* ##### 基于XML依赖注入

  ```xml
  <!-- 基于setter方法依赖注入 -->
  <bean class="需要被装配的类" id = "唯一标识">
  	<description>用来描述一个Bean是干什么的</description>
  	<property name = "属性名" value="值"></property>  //依赖注入标签
  </bean>
  <!-- 基于构造函数依赖注入 -->
  <bean class="需要被装配的类" id = "唯一标识">
  	<description>用来描述一个Bean是干什么的</description>
  	<constructor-arg name = "属性名" value="值"></constructor-arg>  //依赖注入标签
  </bean>
  ```


##### Bean的作用域

* 作用域

  * singleton（单例） 默认值 同一个id总是只被创建一次。可能存在线程安全问题
  * prototype（多例）每次使用都会创建一个Bean

* scope的使用

  ```xml
  <!-- 声明Bean，并控制作用域 -->
  <bean class="需要被装配的类" id = "唯一标识" scope="">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  ```

##### 实例化Bean

* 构造器实例化Bean 默认

* 静态工厂实例化Bean

  ```xml
  <!-- 静态工厂方式实例化Bean -->
  <bean class="需要被装配的类" id = "唯一标识" factory-method="工厂类">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  ```

* 实例工厂实例化Bean

  ```xml
  <!-- 实例化工厂实例化Bean -->
  <bean class="需要被装配的类" id = "唯一标识" factory-bean="实例化的工厂Bean" factory-method="工厂类">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  ```

##### 自动注入

* 自动注入使用

  ```xml
  <!-- byType 根据类型自动匹配 -->
  <bean class="需要被装配的类" id = "唯一标识" autowire="byType">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  <!-- byName 根据set方法的名字自动匹配 -->
  <bean class="需要被装配的类" id = "唯一标识" autowire="byType">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  <!-- constructor 根据构造器自动匹配
   优先根据参数名字去匹配，加入参数名字没有匹配到，会根据参数类型去匹配 -->
  <bean class="需要被装配的类" id = "唯一标识" autowire="constructor">
  	<description>用来描述一个Bean是干什么的</description>
  </bean>
  ```

##### 生命周期回调

* 基于接口的方式实现
  * 初始化回调 实现InitializingBean 重写afterPropertiesSet
  * 销毁回调 实现DisposableBean 重写destory

* 基于配置实现

  ```
  <bean class="需要被装配的类" id = "唯一标识" init-method="" destroy-method="">
  	
  </bean>
  ```

##### 配置第三方Bean

* 配置第三方Bean与配置bean一样，只是需要你了解它的类有哪些，参数有哪些

* 引用数据文件

  ```xml
  <!-- 获取外部属性资源文件 -->
  <context:property-placeholder location="property文件"></context:property-placeholder>
  <!-- 使用应用数据 -->
  <bean class = " " id = " ">
  	<property name = " " value = "${property文件的key}"></property>
  </bean>
  ```

##### 扫描包

* ```xml
  <context:component-scan base-package="包" use-default-filters="true">
  	<context:exclude-filter type="annotation" expression="需要排除的Bean注解的完整限定名"/> //排除扫描
  	<context:include-filter type="annotation" expression="需要包含的Bean注解的完整限定名"/> //包含扫描
  </context:component-scan>
  <!-- 
  	type 
  	1. annotaion 默认 根据注解的全限定名设置排除/包含
      2. assignable    根据类完整限定名设置排除/扫描 
  -->
  <!-- 
  	use-default-filters 
  		1. true 默认 
  		2. false 
  		是否会扫描包含@Conteoller  @Service @Repository @Component注解的包 
  -->
  ```
  
  

#### SpringIOC容器 --- 基于注解配置

##### 注册Bean

* @Conteoller  标记在控制层的类 注册为Bean组件
* @Service 标记在业务逻辑层 注册为Bean组件
* @Repository 标记在数据访问层 注册为Bean组件
* @Component 标记非三层的普通类 注册为Bean组件

##### 依赖注入

* 设置依赖注入的属性 @Value("${key}")；${获取外部属性资源文件的值}  ；#{SpEL表达式}
* @Autowired 实现自动注入 默认优先根据类型匹配 其次根据属性的名字匹配 如果名字也没有匹配到则会报错
* @Qualifier("属性名") 设置属性的名字
* @Primary  设置其中一个Bean为自动注入的主要Bean
* @Resource 实现自动注入 默认根据名字匹配 其次根据类型匹配
* **@Autowired与@Resource 的区别?**
  * @Autowired依赖于Spring，而@Resource依赖于JDK
  * @Autowired优先根据类型匹配，而@Resource优先根据名字匹配

##### 配置类

* @Configuration；  标记为配置类
* @ComponentScan ；扫描包
* @Bean ；

  * @Bean是一个方法级别的注解，开发者可以在@Configuration类或者@Component类中使用@Bean注解
  * 可以将一个**类的实例**注册为Bean，会自动将返回值作为Bean的类型，将方法名作为Bean的id
  * 可以干预实例化过程

* @PropertySource("classpath: 资源文件")；
* @import(其他配置类.class)；
  * 导入其他的配置类@import(其他配置类.class)
  * 导入类注册为Bean @import(类.class)
  * 导入ImportSelector实现类，可以注册多个Bean
  * 导入ImportBeanDefinitionRegistrar实现类，可以注册多个BeanDefinition

##### 其他注解

* 控制加载顺序：@DependsOn(" ")
* 控制懒加载：@Lazy
* 作用域：@Scope("singletion")
* 初始化生命周期回调：@PostConstruct
* 销毁生命周期回调：@PreDestroy

#### AOP编程

##### springAOP介绍

* 面向切面编程：基于OOP基础之上新的编程思想，OOP面向的主要对象是类，而AOP面向的主要对象是切面，在处理**日志**、**安全管理**、**事务管理**等等方面有非常重要的作用。AOP是spring中重要的核心点。**在不修改原有代码的情况下增强跟主要业务没有关系的公共功能代码给之前写好的方法中的指定位置**。可以动态选择动态代理的方式。
* 术语
  * 切面（aspect）：对横切关注点的抽象
  * 横切关注点：对哪些方法进行拦截，拦截后怎么处理
  * 连接点（joinpoint）：被拦截到的点。也就是被拦截到的方法，还可以是字段或者构造器
  * 切入点（pointcut）：对连接点进行拦截的定义。
  * 通知（advice）：拦截到连接点之后要执行的代码。
  * 目标对象：代理的目标对象
  * 织入（weave）：将切面应用到目标对象并导致代理对象创建的过程
  * 引入（introduction）：在不修改代码的前提下，引入可以在运行期为类动态的添加一些方法

##### SpringAOP底层原理

* 在spring容器中，如果有接口，那么会使用JDK自带的动态代理，如果没有接口，那么会使用cglib的动态代理。

* SpringAop中断动态代理主要有两种方式，JDK动态代理和gclib动态代理。JDK动态代理通过反射来来接收被代理的类，并且要求被代理的类必须实现一个接口。如果目标类没有实现接口，那么SpringAOP会选择使用gclib来动态代理目标类，cglib是一个代码生成的类库，可以在允许时动态的生成某个类的子类。因为cglib是通过继承的方式生成动态代理，所有经过final修饰的类无法被gclib代理。

* 静态代理

  * 需要为每个被代理的类创建代理类。

* 动态代理（AOP的底层实现）

  * JDK动态代理：必须保证被代理的类实现接口

  
  * ```java
    有点没太懂....不写了....！！！
    public static Object ProxyClass(Object needProxy){
    	ClassLoader classLoader = needProxy.getClass().getClassLoader();
    	Class<?>[] interface = needProxy.getClass().getInterfaces();
    	//传入被代理的对象
    	InvocationHandler handler = new InvocationHandler(needProxy){
    		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
    			
    		}
    	}
    	//动态创建代理类
    	Object object Proxy.newProxyInstance(classLoader, interface, handler);
    	return object;
    }
    ```
  
  * cglib动态代理：不需要接口

##### AOP的使用

* @Aspect ； 声明为切面，需要注入到IOC容器

* @Before("execution(SqEL表达式) ")； 声明为前置通知

* @After("execution(SqEL表达式)")；声明为后置通知

* @AfterThrowing("execution(SqEL表达式)")；声明为后置异常通知

* @AfterReturning("execution(SqEL表达式)")；声明为返回通知

* @Around("execution(SqEL表达式)")；声明为环绕通知

* **execution(访问修饰符 返回值 方法全称)**

* @EnableAspectJAutoProxy；开启AspectJ支持

##### AOP面试题

* 什么是AOP？
* SpringAOP和AspectJAOP有什么区别？AOP有哪些实现方式？
* JDK动态代理和cglib动态代理的区别？
* 如何理解spring中的代理？
* 解释一下springAOP里面的几个名词？
* Spring通知有哪些类型？
* 解释基于注解的切面实现？

#### Spring声明式事务

##### 事务控制介绍

* 编程式事务：在代码中直接加入事务处理的逻辑，可能需要在代码中显示调用beginTransaction()、commit()、rollback()等事务管理相关方法。
* 声明式事务：在方法外部添加注解或直接在配置文件中定义，将事务管理从业务方法中分离出来 ，以声明的方式解决事务管理。springAOP恰好可以完成此功能：**事务管理代码的固定模式作为一种横切关注点，通过 AOP方法模块化，进而实现声明式事务。**
* Spring核心事务管理抽象是PlatformTransactionManager。它为事务管理封装了一组独立于技术的方法。无论使用spring的哪种事务管理策略，事务管理器都是必须的。同时事务管理器是可以以普通Bean的形式声明在IOC容器中。
* 支持声明式事务、统一的事务管理接口、灵活的事务传播行为、细粒度的事务控制、异常处理和事务回滚、支持嵌套事务、与其他框架集成。
* 声明式事务优点：不需要在业务逻辑代码中编写相关代码，只需要在配置文件配置使用注解，这种方式没有入侵性。

##### 事务实现方式

* ```xml
  <!-- 1. 配置好数据源信息 -->
  <!-- 配置事务管理器 -->
  <bean class="org.springframework.jdbc.datashourc.DataSourceTransactionManager" id="自己起给你惯着的">
  	<property name="dataSource" ref="dataSource"></peoperty>
  </bean>
  <!-- 基于注解方式的事务，开启事务的注解驱动 -->
  <tx:annotation-driven transaction-manager="自己起给你惯着的"></tx:annotation-driven>
  ```

##### 事务配置注解及其属性

* @Transaction；可以标记在类上也可以标记在方法上，运用事务。建议写在业务逻辑层

* 事务配置属性

  * isolation：设置事务的隔离级别

  * propagation：事务的传播行为

  * noRollbackFor：哪些异常事务可以不回滚

  * noRollbackForClassName：填写的参数是全类名

  * rollbackFor：哪些异常事务需要回滚

  * rollbackForClassName：填写的参数是全类名

  * readOnly：设置事务是否为只读事务

  * timeout：事务超出指定时长后自动终止并回滚，单位秒

* 设置事务隔离级别（isolation）
  * 通过设置事务级别解决并发过程中产生的问题（什么问题自己去复习数据库！！！）
    * 脏读：一个事务，读取了另一个事务中没有提交的数据，产生数据不一致的问题。
    * 不可重复读：一个事务中，多次读取相同的数据，读取的结果不一样，产生数据不一致的问题。
    * 幻读：一个事务中，多次对数据进行整表数据读取，但是结果不一致，产生的数据不一致的问题。
  * 读已提交：READ_COMMITTED  ；只能读取到已提交的数据。
  * 可重复读：REPEATBLE_READ ；事务执行期间禁止其他事务对这个字段进行更新。只需行锁。
  * 串行化：SERIALIZABLE ；事务执行期间禁止其他事务对这个**表**进行增、删、改操作。可以避免任何并发问题，但是性能低下。只需要表锁。

* 事务的传播性（propagation）

  * 事务的传播性是指：当一个事务方法被另一个事务方法调用。两个Service层调用并且都有事务。

  * spring的事务传播行为共有7中：

    ```
    1. REQUIRED（默认） 外部不存在事务，开启新事务；外部存在事务，融合到外部事务；适用于增删改查
    2. SUPPORTS        外部不存在事务，不开启新事务；外部存在事务，融合到外部事务；适用于查询
    3. REQUIRES_NEW    外部不存在事务，开启新事务；外部存在事务，挂起外部事务，创建新事务；适用于日志等情况
    4. NOT_SUPPORTED   外部不存在事务，不开启新事务；外部存在事务，挂起外部事务；不常用
    5. NEVER           外部不存在事务，不开启新事务；外部存在事务，抛出异常；不常用
    6. MANDATORY       外部不存在事务，抛出异常；外部存在事务，融合到外部事务；不常用
    7. ...？？？ 不是七个吗？？？ 怎么少了一个！！！...
    ```

* 超时属性（timeout）
  * 当前事务访问数据时，可能访问的数据被别的数据进行加锁处理，那么此时事务就必须等待。
* 设置事务只读（readOnly）

##### 问题

* Spring事务实现方式和实现原理？
* 说一下Spring的事务传播行为？
* 说一下spring的事务隔离？
* Spring框架的事务管理有哪些优点?

------

### SpringMVC

#### 介绍

* SpringMVC是Spring框架的一部分，是基于Java实现的轻量级框架。
* SpringMVC框架核心是DispatcherServlet的设计。掌握好DispatcherServlet是掌握SpringMCV的核心关键。
* 高效，基于请求响应的MVC框架（解耦、可重用、维护性高），约定优于配置，功能强大（灵活的 URL映射、数据验证、RESTful本地化等）

#### SpringMVC底层原理

##### SpringMVC实现原理

  * Spring的web框架是围绕DispatcherServlet设计。DispatcherServlet的作用是将请求分发到不同的处理器。
  * SpringMVC框架以请求为驱动，围绕一个中心Servlet分派请求以及提供其他功能，DispatcherServlet是一个实际的Servlet（它继承自HttpServlet基类）。

##### SpringMVC具体执行流程

  * DispatcherServlet表示前端控制器，是整个SpringMVC的控制中心。用户发送请求，DispatchaerServlet接收请求并拦截请求。
  * HandlerMapping为处理器映射。DispatcherServlet调用HandlerMapping，HandlerMapping根据请求url查找Handler。
  * 返回处理器执行链，根据url查找控制器，并且将解析后的信息传递给DispatcherServlet
  * HandlerAdapter表示处理器适配器，其按照特定的规则区执行Handler。
  * 执行handler找到具体的处理器
  * Controller将具体的执行信息返回给HandlerAdapter，如ModlerAndView
  * HandlerAdapter将视图逻辑名或者模型传递给DispactherServlet。
  * DispatcherServlet调用视图解析器解析HandlerAdapter传递的逻辑视图名。
  * 视图解析器会将解析的逻辑视图名传给DispatcherServlet
  * DispatcherServlet根据视图解析器的视图结果调用具体的视图，进行视图渲染。
  * 将响应数据返回给客户端。

##### SpringMvc运行流程

* 客户端发送请求
* 由tomcat接收对应的请求
* SpringMVC的前端控制器DispatcherServlet接收到所有的请求
* 查看请求地址和@RequestMapping注解的哪个匹配，找到具体的类的处理方法
* 前端控制器找到目标后会有一个返回值，SpringMVC会将这个返回值用视图解析器解析拼接成完整的页面地址
* DispatcherServlet拿到页面地址后，转发到具体的页面。

#### 基于XML搭建MVC

##### 编写Servlet需要的web.xml配置文件

* ```xml
  <!-- 配置DispatcherServlet -->
  <servlet>
  	<servlet-name>name</servlet-name>
  	<servlet-class>DispatcherServlet</servlet-class>
  	<!-- 关联SpringMVC配置文件 -->
  	<init-param>
  		<param-name>springMvcConfigContext</param-name>
  		<param-value>classpath:applicationContext.xml</param-value>
  	</init-param>
  	<load-on-startup>1</load-on-startup>
  </servlet>
  <!-- 匹配servlet的请求,/标识匹配所有请求 -->
  <servlet-mapping>
  	<servlet-name>name</servlet-name>
  	<!-- /*和/都是拦截所有请求，/会拦截的请求不包*.jsp，而/*的范围更大，会拦截*.jsp这些请求 -->
  	<url-pattern>/</url-pattern>
  </servlet-mapping>
  ```

##### 编写springmvc需要的spring配置文件，applicationContext.xml

* ```xml
  <!-- 只需要配置扫描包的注解扫描注解即可 -->
  <context:commponent-scan base-package=" "><context:commponent-scan>
  ```

#### SpringMVC基于注解的使用

##### 请求处理

###### 处理请求参数

* 在SpringMVC中只需要在处理的方法中声明对应的参数就可以自动接收请求的参数并且可以自动类型转换
* 如果参数问题会出现400错误
* 请求参数必须和处理方法参数名一致，如果处理方法的参数没有传入的情况下会自动传入null
* 参数不要传入基本数据类型，只能使用包装类型，因为基本数据类型不能接收null
* @RequestParam("name", defaultValue = " defaultValue ") ；参数重命名，默认必须传入参数；defaultValue 设置参数默认值
* @RequestHeader：获取请求头信息
* @CookieValue：获取cookie中的值

###### 请求映射处理

* @RequestMapping(value="/URL", method  = {RequestMethod.GET, RequestMethod.POST}, params={"value"}, headers = {" "}, consumes={"application/json"}, produces={"application/json"})
  * 用来处理URL映射，将请求映射到处理类或者方法中
  * value：请求路径。支持通配符，？匹配单个字符，*匹配任意一个字符，**匹配任意层次字符
  * RequestMethod：设置请求方式
  * params：必须带有某个请求参数才能请求
  * headers ：必须带有指定请求头才能映射
  * consumes：当前请求的内容内类型必须为指定值,类型不匹配报415错误
  * produces：设置当前响应内容类型
* @PathVariable：用在参数上，获取URL目录级别的参数。如果是JavaBean可以省略。

###### RESTful

* 客户端映射到服务器资源的架构设计风格。
* 面向资源的设计风格

* GET--查询，POST--新增，PUT--修改，DELETE--删除

###### 静态资源处理

* ```xml
  <!-- 在springMVC配置文件中配置 -->
  <!-- 配置访问静态资源 -->
  <mvc:annotation-driven/>
  <!-- 将映射的地址直接指向静态资源文件夹 -->
  <mvc:resource mapping="/imges/**" location="/images/"/>
  <!-- 当springmvc没有映射到某一个请求的时候，就会调用默认servlet处理 -->
  <mvc:default-servlet-handler/>
  ```

##### 响应处理

###### 视图解析器(viewResplver)

* 配置视图解析器

  ```xml
  <!-- 默认视图解析器 配上前缀和后缀 简化逻辑视图名称 -->
  <bean calss="InternalResourceViewResolver" name="viewResolver">
  	<property name="prefix" value="/WEB-INF/views/"></property>
  	<property name="auffix" value=".jsp"></property>
  </bean>
  ```

###### 视图控制器(view-controller)

* 如果某些请求只想跳转页面，不需要后台处理逻辑 ，无法在Action中写一个空方法跳转，可以直接配置视图控制器（不经过Action，直接跳转页面）

  ```xml
  <!-- path需要访问的路径，view-name需要访问的页面 -->
  <mvc:view-conreoller path="/" view-name="index"></mvc:view-controller>
  ```

###### Model

* 在参数方法上传入Model、ModelMap、Map类型将数据传送回页面。实际上者三个类底层都是最终到实现类BindingAwareModelMap

  ```java
  model.addAttribute("type", "model");
  ```

###### 使用session传输数据到页面

* @SessionAttributes：使用在类上，负责写入session。这个值是从model中获取指定的属性写入session。
* @SessionAttribute：使用在方法上，负责读取session。

###### 使用@ModelAttribute获取请求中的数据

* @ModelAttribute注解用于将方法的参数或者方法的返回值绑定到指定模型属性上，并返回给web视图。
* 声明在方法上，会让所有的处理方法调用之前先调用@ModelAttribute的方法。
* 声明在参数里，会从model中获取指定的属性和参数进行合并，因为model和sessionAttribute具有共通的特性。

###### 线程安全问题

* 参数绑定获取servlet-api，不存在线程安全。
* @AutoWireed自动注入获取servlet-api，不存在线程安全，虽然是共享变量，但是底层是通过ThreadLocal存储servlet-api的。
* @ModelAtrribute的方式获取servlet-api，不是线程安全的，因为它是共享变量。

###### 转发和重定向

* 转发：由服务器的页面进行跳转，不需要客户端重新发送请求
  * 地址栏的请求不会发生变化
  * 请求的次数有且仅有一次
  * 请求域中的数据不会丢失
  * 根目录包含项目的访问地址
  * 实现：返回逻辑视图的时候默认就是转发，如：return "forward:/index"
  * 显式使用forward不会参与视图解析器，需要完整的逻辑名和路径。
* 重定向 ：在浏览器端进行页面跳转，需要发送两次请求
  * 地址栏的地址发生变化，显示最新发送请求的地址
  * 请求域中的数据会丢失，因为是不同的请求
  * 根目录不包含项目名称
  * 实现：return "redirect:index"
  * 使用redirect，不会参与视图解析器

##### 类型转换&数据格式化 &数据校验

###### 数据类型转换器

* 实现

  ```
  //实现Converter接口，Converter<原类型, 目标类型>
  //重写convert方法
  ```

###### 数据格式化

* Spring提供了两个可以用于格式化数字、日期和时间的注解@NumberFormat和@DateTimeFormat，这两个标签可以用于JavaBean的属性或者方法参数上。
* @NumberFormat可以用来格式化任何的数字的基本类型或者java.lang.Number的实例。
* @DateTimeFormat可以用来格式化java.util.Date、java.util.Calendar和java.util.Long类型。

###### 数据校验

* JSR303是Java为Bean数据合法性校验提供的标准框架，它已经包含在 Java EE6.0中。JSR303通过在Bean属性上标注类似于@NotNull等标准的注解指定校验规则，并通过标准的校验接口对Bean进行验证。

##### JSON

###### SpringMVC返回JSON数据

* @ResponseBody ：将该方法返回值作为文本进行返回，并不是返回逻辑视图
* @RestController：将该方法标记在类上，作用等同于@ResponseBody + @Controller；所有方法将以json的数据进行响应。
* @JsonIgnore：当返回JavaBean时将会忽略该属性。
* @JsonFormat(patter="")：用户转换JSON时格式化数据

###### SpringMVC获取JSON数据

* @RequestBody：作用于参数，接收参数将JSON转换为JavaBean

##### 文件上传下载

###### 下载

* ```java
  public String download(HttpServletRequest request, HttpServletResponse response){
  	//获取当前项目路径下的下载文件
  	String realPath = request.getServletContext().getRealPAth("/file/01.png");
  	//获取文件路径并封装为File对象
  	File tmpFile = new File(realPath);
  	//根据File对象获取文件名
  	String fileName = tmpFile.getName();
  	//设置响应头以及打开方式和字符编码
  	response.getHeader("context-disposition", "attachment=" + URLEncoder.encode(fileName, "UTF-8"))
  	//根据文件路径封装成文件输入流
  	InputStream in = new FileInputStream(realPath);
  	int len = 0;
  	//声明大小为1kB的字节缓冲区
  	byte[] buffer = new byte[1024];
  	//获取输出流
  	OutputStream out = response.getOutputStream();
  	//循环读取文件，每次读取1kB避免内存溢出
  	while((len = in.read(buffer)) > 0){
  		out.write(buffer, 0, len);
  	}
  	in.close();
  	return null;
  }
  ```

###### 上传

* SpringMVC为文件上传提供了直接的支持，这种支持是通过即插即用的MultipartResolver实现的。Spring用Jakarta Commons FileUpload技术实现了一个MultipartResolver实现类：CommonsMultipartResovler。
* SpringMVC上下文中默认没有装配MultipartResovler，因此默认情况下不能处理文件上传工作，如果想使用 Spring的文件上传功能需要在上下文中配置MultipartResolver。

##### 拦截器

* SpringMVC提供了拦截器机制，允许允许目标方法之前进行一些拦截工作或者目标方法之后进行其他相关的处理。自定义拦截器必须实现HandlerInterceptor接口。
* 拦截器采用AOP设计思想，与过滤器类似，用来拦截处理方法之前和之后执行一些跟主业务没有关系的 一些公共功能，如：权限控制、日志、异常记录、记录执行方法时间等。
* preHandle：在处理方法之前执行，return false时，会中断当前线程执行。
* postHandle：请求之后，视图渲染之前执行。
* afterCompletion：视图渲染之后执行。

##### 国际化

* 将网站提供给不同语言地区使用，此时需要国际化。国际化（i18n）各国家语言缩写。

* 基于浏览器设置的语言切换国际化

  * 新建jsp对应的国际化属性资源文件

  * 配置 springmvc.xml，将国际化支持和资源文件注入springmvc中

    ```xml
    <bean class="ResourceBundleMessageSource" id="messageSource">
    	<property name="basename"?
    		<array>
    			<value>i18n/login</value>
    		</array>
    	</property>
    </bean>
    ```

  * 在jsp页面调用对应的属性资源文件内容

##### 异常处理

###### 内置异常处理器

* 在SpringMVC中拥有一套强大的异常处理机制，SpringMVC通过实现HandlerExceptionResolver处理程序的异常，包括 请求映射、数据绑定以及目标方法执行时发生的异常。
* @ExceptionHandler：用于处理指定异常的异常类
* @ResponseStatus

###### 统一异常处理

* @ControllerAdvice是Spring3.2提供的新注解 ，它是对Controller的增强，对被@RequestMapping注解的方法加一些逻辑处理：
  * 全局异常处理
  * 全局数据绑定
  * 全局数据预处理
* @ExceptionHandler
  * 加在 Controller中，只处理当前控制器的异常，优先级比全局高
  * 加在ControllerAdvice中，处理全局异常。
* 案例：https://blog.csdn.net/m0_61933976/article/details/131067701

------

### SpringBoot

#### 介绍

* SpringBoot基于 Spring开发，继承了Spring框架的优秀特性。其设计目的是用于简化Spring应用的初始搭建以及开发过程。
* SpringBoot是基于Spring4.0的。
* **约定大于配置**，它继承了大量常用的第三方库配置并集成了常用第三方框架技术，如：Redis、MongoDB、Dubbo、kafka等。
* 能够快速构建独立的Spring应用程序、嵌入Tomcat、jetty或者Undertow，无需部署war文件、简化Maven配置，减少版本 冲突、无需配置XML，代码生成、继承了大量框架并做好了许多默认配置。

#### SpringBoot底层原理

##### SpringBoot自动配置原理

* 从启动类注解入手

* 注解详解

  ```java
  @Target ：设置当前注解可以标记的位置
  @Retention ：当注解标记的类编译后以什么方式保留
  @Documented ：当javaDoc时会生成注解信息
  @Inherited ：标记的是否能够被继承
  @SpringBootConfiguration ：标注在某个类上，表示这是一个SpringBoot的配置类
  @EnableAutoConfiguration ：开启自动配置功能，帮助我们自动加载和配置自动配置类
  @ComponentScan ：扫描包，相当于在spring.xml配置中的扫描包
  @AutoConfigurationPackage ：将当前配置类所在包保存在BasePackages的Bean中，供Spring内部使用
  ```

* SpringBoot自动配置类遵循SPI机制

##### SpringMVC自动配置原理

* SpringBoot为SpringMVC提供了自动配置，可与大多数应用程序完美配合。
* 自动配置在Spring的默认值之上添加了以下功能：
  * 包含ContentNegotiatingViewResolver和BeanNameViewResolver。
  * 支持提供静态资源，包括WebJars的支持
  * 自动注册Converter，GenericConverter和FormatterBean类。
  * 支持HttpMessageConverters
  * 自动注册MessageCodesResolver
  * 静态index.html支持
  * 自动使用ConfigurableWebBindingInitializer bean
* 可以查看http://t.csdnimg.cn/tCGPu以及https://www.cnblogs.com/antonzhao/p/13191738.html两篇文献做了解

##### WebMvcConfigurer原理

* **实现webMvcConfigurer接口可以扩展MVC实现，又保留了SpringBoot的自动配置。**
* 在WebMvcAutoConfiguration自动配置类中也实现类WebMvcConfigurer的配置类。
* 其中WebMvcAutoConfigurationAdapter也是利用这种方式进行扩展，其中帮我自动配置了一些我们不常用的方法，因此我们重点实现的是拦截器、视图控制器以及CORS这些功能。
* @Import导入了一个EnableWebMvcConfiguration.class。
* 这个类的父类中setConfigurer使用@Autowired注解将容器中实现了WebMvcConfigurer接口的Bean都注入进来，添加到configurers变量中。
* 之后调用delegates委派器中，底层调用WebMvcConfigurer对应的方法时，去拿到之前注入到delegates的WebMvcConfigurer。
* 官方建议不建议添加@EnableWebMvc注解，当添加了这个注解就不会使用SpringMVC自动配置类默认的配置了。
  * 在这个注解中Import了一个DelegatingWebMvcConfiguration类
  * 这个类又继承了WebMvcConfigurationSupport类，WebMvcAutoConfiguration这个类中又声明当WebMvcConfigurationSupport存在时，这个Bean会失效。
  * 正是因为通过@EnableWebMvc注解导入了WebMvcConfigurationSupport才会导致失效。

##### SpringBoot异常处理原理

* 

##### 嵌入式Servlet容器自动配置原理

* 解决问题
  * 为什么可以根据配置的依赖自动使用对应的servlet容器？
  * 怎么根据配置文件中server.xxx以及WebServerFactoryCustomizer去设置好servlet容器？
  * 嵌入式Servlet容器是怎么启动的？

##### 外部Servlet容器启动SpringBoot应用原理

* 
* 参考：http://t.csdnimg.cn/XoJRZ

##### MyBatis自动配置原理

* 
* 参考：https://blog.csdn.net/qq_38318330/article/details/108088494

##### SpringBoot启动原理

* 
* 参考文献：
  * https://www.cnblogs.com/theRhyme/p/11057233.html
  * https://blog.csdn.net/admans/article/details/139215616
  * https://blog.csdn.net/u014252478/article/details/88789852
  * https://cloud.tencent.com/developer/article/1874814
  * https://www.jianshu.com/p/bbb2cbe2c49a
  * https://cloud.tencent.com/developer/article/1483402

#### 基础入门

##### 自定义SpringApplication

* 将SpringApplication进行实例化
* 参考：https://docs.spring.io/spring-boot/reference/features/spring-application.html

##### 配置文件

###### 文件格式

* 约定SpringBoot配置文件名为application.properties或者application.yml，约定的情况下必须为application。
* properties文件格式采用扁平的k/v格式。
* yml文件格式采用树形结构。

###### 优先级

* properties与yml两种文件格式同时存在会以优先读取的为主，官方解释以properties为主。
* 在不同目录级下优先级加载顺序，优先级是由低到高，高优先级的配置会覆盖低优先级的配置；互补配置；
* 优先级由低到高依次为：classpath根目录-->classpath根config-->项目根目录-->项目跟目录/config-->直接子目录/config

###### 属性绑定

* @ConfigurationProperties(prefix="rootKey")：用于bean属性和yml文件绑定，prefix可以指定配置文件的某个节点，该节点的子节点将自动和属性进行绑定。
* 支持属性占位符，不支持SqEL表达式，支持随机生成值，以及其他属性的引用，支持JSR303数据校验。

###### 引入其他properties文件

* @PropertySource("classpath:propertiesName.properties")
* 只支持properties文件，不支持yml文件

#### 日志

##### 介绍

* SpringBoot底层也是使用slf4j+logback的方式进行日志记录，默认添加了logback-classic的桥接器
* SpringBoot也通过其他适配器将其他日志替换为slf4j。

##### SpringBoot日志使用

* 使用日志之气先声明日志记录器，不建议使用Lombok依赖声明@Slf4j。

  ```java
  //声明日志记录器并且参数指定哪个类需要进行日志记录
  Logger logger = LoggerFactory.getLogger(Application.class); 
  ```

* 使用application.yml配置文件声明日志

  ```yml
  logging.level //设置日志级别
  logging.pattern.console //自定义日志格式
  logging.file.name  //以文件形式输出日志信息，写入指定日志文件
  logging.file.path  //以文件形式输出日志信息，写入spring.log，默认使用spring这个名字
  logging.logback.rollingpolicy //日志迭代
  ```

##### 日志级别

* 可以设置TRACE、DEBUG、INFO、WARN、ERROR、FATAL或者OFF之一。

  ```yml
  //在指定类中使用
  logger.debug("测试");
  //application.yml文件使用
  logging.level = debug
  ```

##### 日志格式

* 用于自定义修改日志的格式，

  ```
  2024-06-20T10:08:15.428Z  INFO 111881 --- [myapp] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
  
  日期和时间：毫秒级精度，易于排序。
  日志级别：、、、或 。ERRORWARNINFODEBUGTRACE
  进程 ID。
  用于区分实际日志消息开头的分隔符。---
  应用程序名称：用方括号括起来（默认情况下，仅当设置了）Application name： En括在方括号中（默认情况下，仅当设置了此项时才记录）spring.application.name
  线程名称：用方括号括起来（对于控制台输出，可以截断）。
  相关 ID：如果启用了跟踪（上面的示例中未显示）
  记录器名称：这通常是源类名称（通常缩写）。
  日志消息。
  ```

##### 文件输出

* 默认情况下，SpringBoot仅将日志信息记录在控制台，不写日志文件。如果想以文件形式记录日志信息，则需要配置logging.file.name或者logging.file.path属性，前者指定文件后者指定目录。

##### 日志迭代

* 使用logback可以使用application.yml文件微调日志迭代设置。对于其他日志记录系统，则需要自己配置xml文件迭代，如：使用log4j2，则可以添加log4j.xml文件

  ```yml
  logging.logback.rollingpolicy.file-name-pattern  //归档文件名
  logging.logback.rollingpolicy.clean-history-on-start //应用程序启动时进行日志归档清理
  logging.logback.rollingpolicy.max-file-size  //归档前日志文件的最大大小
  logging.logback.rollingpolicy.total-size-cap //删除日志档案之前可以使用的最大大小
  logging.logback.rollingpolicy.max-history    //保存日志存档的天数（默认为7）
  ```

##### 自定义日志配置文件

* 如果自定义使用日志配置文件，会使全局配置文件的logging相关配置失效。
* 配置自定义日志文件需要将日志配置的文件名以logback-spring.xml命名，因为logback.xml会在SpringBoot容器加载前先被logback给加载到。

##### 切换日志框架

* 想要logback日志框架切换为log4j2，需要在pom文件中开启log4j2场景启动器并排除logback场景启动器，同时还需要log4j2配置文件。
* 想要logback日志框架切换为log4j，需要在pom文件中开启log4j2场景启动器，并排除logback场景启动器，同时还需要log4j配置文件。

#### SpringBoot与Web开发

##### RestTemplate

* RestTemplate是Spring提供的用于访问Rest服务的，RestTemplate提供了多种简便访问远程Http服务的方法。
* 参考https://docs.spring.io/spring-boot/reference/io/rest-client.html#io.rest-client.resttemplate

##### MockMVC

* MokeMvc是由spring-test包提供，实现了对Http请求的模拟，能够直接使用网络的形式，切换到Controller的调用，使得测试速度块、不依赖于网络环境。同时提供了一套验证的工具。

##### swagger

* 用于测试接口以及生成测试文档界面，目前SpringBoot3.x版本使用的是swagger2.x版本
* 需要添加两个依赖：springfox-swagger2和springfox-swagger-ui（亲测不咋好用，版本依赖问题很麻烦）
* 配置swagger2配置类，在类上添加@EnableSwagger2注解

#### 定制SpringMVC自动配置

* 一个非常不错的WebMvcConfigurer扩展案例地址：https://blog.csdn.net/m0_61933976/article/details/131067701

##### 通过自动配置类

* SpringMVC的自动配置类为WebMvcAutoConfiguration，在大多数情况下，SpringBoot在自动配置中标记了 很多@ConditionlOnMissingBean(Xxxxx.class)；（这个注解意思是如果容器中没有当前Bean，当前Bean才会生效）。只需要在自己的配置类中配置对于的一个@Bean就可以覆盖自动配置。

##### 通过WebMvcConfigurer进行扩展

* 实现WebMvcConfigurer接口
* 添加@Configuration注解在类上

###### ViewController（视图控制器）

* 重写addViewControllers方法实现自己需要的逻辑。

###### ViewResolver（视图解析器）

* 重写configureViewResolvers方法

###### 拦截器Interceptor

* 继承HandlerInterceptor接口，实现拦截器功能
* 重写addInterceptors方法
* 链式调用拦截器方法
  * addInterceptor //添加拦截器
  * addPathPatterns //哪些路径需要拦截
  * excludePathPatterns //排除哪些路径需要拦截

###### 跨域映射CORS

* 在需要跨域的Controller方法或Controller类上添加@CrossOrigin注解
* 重写addCorsMappings方法，调用链式方法进行设置
  * addMapping //映射服务器中哪些Http接口允许跨域访问
  * allowedMethods //配置允许跨域的请求方法
  * allowedOrigin //配置哪些来源有权限跨域请求
* @CrossOrigin使用在类上就是全局配置，使用在某个Http接口上就是指定接口允许跨域访问

###### JSON的处理

* SpringBoot提供了三个JSON映射库的集成（Gson、Jackson、JSON-B），其中Jackson是 默认的JSON库。

* jackson的使用

  ```
  @JsonIgnore //进行排除序列化
  @JsonFormat(pattern = "yyy-MM-dd hh:mm:ss", locale="zh") //进行日期格式化
  @JsonInclute(JsonInclude.Include.NON_NULL) //当属性值为Null时不进行json序列化
  @JsonProperty("name") //给属性设置别名
  ```

* SpringBoot还提供了@JsonComponent来根据自己的需求的序列化和反序列化，被标记的类需要继承JsonSerializer<SomeObject>类和JsonDeserializer<SomeObject>这两个基类。

* 使用JSON序列化和反序列化参考官网：https://docs.spring.io/spring-boot/reference/features/json.html#features.json.jackson.custom-serializers-and-deserializers

###### 国际化

* 侧重点放在后端的国际化，前端页面国际化由前端程序员负责。
* 实现步骤
  * 添加国际化资源文件
  * 配置messageResource设置国际化资源文件
  * 需要去解析请求头中的accept-language
  * 将本地语言进行缓存
  * 通过messageResource获取国际化信息。

###### 统一异常处理

* SpringBoot有统一异常处理自动配置类
* 我们需要使用自定义的页面响应错误只需要在对应的路径上创建对应错误代码页面就行，但是如果想记录日志就需要自己定制。
  * templates/error/4xx
  * static/error/4xx.html
* 自定义异常配置类，只需要继承AbstractErrorController类并实现其中方法。
* 参考统一异常处理文献
  * https://blog.csdn.net/wufaqidong1/article/details/128738361
  * https://blog.csdn.net/qq_41107231/article/details/115874974

#### Servlet容器

##### 自定义嵌入式Servlet容器

###### 声明式自定义

* 可以通过servlet.xxx进行web服务配置，带了具体服务器名称则是单独对该服务器进行配置。
* Tomcat支持SSL协议和HTTP压缩
* **自学web服务器调优**

###### 编程式自定义

* 如果需要以编程式方式配置servlet容器，则可以注册一个实现WebServerFactoryCustomizer接口的Bean。WebServerFactoryCustomizer提供对ConfigurableServletWebServerFactory的访问，其中包括许多自定义设置方法。
* https://docs.spring.io/spring-boot/reference/web/servlet.html#web.servlet.embedded-container

##### 注册Servlet三大组件

* 三大组件：Servlet、Listerent、Filter

* servlet3.0规范提供的注解方式注册
  * 添加@WebServlet注解，继承HttpServlet，添加到容器。
  * 添加@WebListener注解
  * 添加@WebFilter注解
* SpringBoot提供的注册
  * ServletRegistrationBean 
  * FilterRegistrationBean 
  * ServletListenerRegistrationBean
  * 参考：https://blog.csdn.net/qq_44993558/article/details/107141664

##### 切换其他内嵌Servlet容器

* tomcat是默认的嵌入式服务器
* 排除tomcat场景启动器
* 开启所需服务器的场景启动器

##### 使用外部Servlet容器

* 下载安装服务器
* 设置Maven打包方式
* 配置内嵌tomcat服务器不参与打包
* 参考：https://blog.csdn.net/qq_28480349/article/details/115890674

#### SpringBoot集成MyBatis

##### 整合Durid连接池

* 添加durid依赖或者添加druid场景启动器

  ```xml
  <dependency>
  	<groupId>com.alibaba</groupId>
  	<artifactId>druid-spring-boot-3-starter</artifactId>
  	<version>1.2.20</version>
  </dependency>
  ```

* 配置数据源

  ```yml
  spring:
    datasource:
      type: com.alibaba.druid.pool.DruidDataSource
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://192.168.10.106:3306/xj_doc?characterEncoding=utf8&serverTimezone=Asia/Shanghai
      username: root
      password: 123456
      initialization-mode: always //表示始终启动时初始化数据库
      schema: classpath:sql/xxx.sql //sql脚本路径
      # druid 连接池管理
      druid:
        # 初始化时建立物理连接的个数
        initial-size: 5
        # 连接池的最小空闲数量
        min-idle: 5
        # 连接池最大连接数量
        max-active: 20
        # 获取连接时最大等待时间，单位毫秒
        max-wait: 60000
        # 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        test-while-idle: true
        # 既作为检测的间隔时间又作为testWhileIdel执行的依据
        time-between-eviction-runs-millis: 60000
        # 销毁线程时检测当前连接的最后活动时间和当前时间差大于该值时，关闭当前连接(配置连接在池中的最小生存时间)
        min-evictable-idle-time-millis: 30000
        # 用来检测数据库连接是否有效的sql 必须是一个查询语句(oracle中为 select 1 from dual)
        validation-query: select 'x'
        # 申请连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
        test-on-borrow: false
        # 归还连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
        test-on-return: false
        # 是否缓存preparedStatement, 也就是PSCache,PSCache对支持游标的数据库性能提升巨大，比如说oracle,在mysql下建议关闭。
        pool-prepared-statements: false
        # 置监控统计拦截的filters，去掉后监控界面sql无法统计，stat: 监控统计、Slf4j:日志记录、waLL: 防御sqL注入
        filters: stat,wall,slf4j
        # 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
        max-pool-prepared-statement-per-connection-size: -1
        # 合并多个DruidDataSource的监控数据
        use-global-data-source-stat: true
        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        connect-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
  
        web-stat-filter:
          # 是否启用StatFilter默认值true
          enabled: true
          # 添加过滤规则
          url-pattern: /*
          # 忽略过滤的格式
          exclusions: /druid/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico
  
        stat-view-servlet:
          # 是否启用StatViewServlet默认值true
          enabled: true
          # 访问路径为/druid时，跳转到StatViewServlet
          url-pattern: /druid/*
          # 是否能够重置数据
          reset-enable: false
          # 需要账号密码才能访问控制台，默认为root
          login-username: root
          login-password: 123456
          # IP白名单
          allow: 127.0.0.1
          # IP黑名单（共同存在时，deny优先于allow）
          deny:
  ```

* 创建durid配置类（如果导入的是durid场景启动器则不需要这个配置类）

  ```java
  @Configuration
  @ConditionalOnProperty("spring.dadasource.type")
  public class DruidConfig {
  
      @Bean
      //@ConfigurationProperties(prefix = "spring.datasource") 方式1
      public DataSource  druidDataSource(DataSourceProperties properties) {
      	return properties.initializeDataSourceBuilder().build
          //return new DruidDataSource(); 方式1
      } 
      
  }
  ```

##### 整合MyBatis

* 导入MyBatis-Generator插件，自动生成代码

* 导入MyBatis场景启动器依赖

* 编写配置文件

  ```yml
  # mybatis配置
  mybatis:
    check-config-location: true
    #  mybatis框架配置文件，对mybatis的生命周期起作用
    config-location: "classpath:mybatis/mybatis-config.xml"
    #  配置xml路径
    mapper-locations: "classpath:mybatis/mapper/*Mapper.xml"
    #  配置model包路径
    type-aliases-package: "com.example.awesomespring.dao.entity.*"
  
  ```

* 在启动类上添加@MapperScan("mapper包")

#### SpringBoot自定义starters场景启动器

##### 介绍

* starter 是 SpringBoot 中一种非常重要的机制，它可以繁杂的配置统一集成到 starter 中，我们只需要通过 maven 将 starter 依赖引入到项目中，SpringBoot 就能自动扫描并加载相应的默认配置。starter 的出现让开发人员从繁琐的框架配置中解放出来，将更多的精力专注于业务逻辑的开发，极大的提高了开发效率。在一些特殊情况下，我们也可以将一些通用功能封装成自定义的 starter 进行使用。


##### 命名规范

* SpringBoot 提供的 starter 以 spring-boot-starter-xxx 的形式命名。为了与 SpringBoot 生态提供的 starter 进行区分，官方建议第三方开发者或技术（例如 Druid、Mybatis 等等）厂商自定义的 starter 使用 xxx-spring-boot-starter 的形式命名，例如 mybatis-spring-boot-starter、druid-spring-boot-starter 等等。

##### 模块规范

* Spring Boot 官方建议我们在自定义 starter 时，创建两个 Module ：autoConfigure Module 和 starter Module，其中 starter Module 依赖于 autoConfigure Module。当然，这只是 Spring Boot 官方的建议，并不是硬性规定，若不需要自动配置代码和依赖项目分离，我们也可以将它们组合到同一个 Module 里

##### 自定义starter场景启动器

* 步骤：

  * 定义自动配置类

    ```java
    //在autoConfigure Module中进行配置
    @Configuration
    @EnableConfigurationProperties(PersonProperties.class) //启用实体类，并默认将它添加到容器中
    public class PersonAutoConfiguration{
    		@Bean
            @ConditionalOnMissingBean(PersonService.class) //当容器中没有 PersonService 时生效
            public PersonService personService() {
                PersonService personService = new PersonService();
                return personService ;
            }
    }
    ```

  * 创建 spring.factories文件

    ```factories
    /*由于 Spring Boot 的自动配置是基于 Spring Factories 机制实现的，因此我们自定义 starter 时，同样需要在项目类路径下创建一个 spring.factories 文件。在 autoConfigure Module 的类路径下（resources ）中创建一个 META-INF 文件夹，并在 META-INF 文件夹中创建一个 spring.factories 文件。将 Spring Boot 的 EnableAutoConfiguration 接口与自定义 starter 的自动配置类 PersonAutoConfiguration组成一组键值对添加到 spring.factories 文件中，以方便 Spring Boot 在启动时，获取到自定义 starter 的自动配置，代码如下
    */
    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
    com.example.tmlspringbootstarterautoconfiguration.autoConfiguration.PersonAutoConfiguration
    ```

  * 构建 starter

    ```xml
    //添加 POM 依赖，在starter Module的 pom.xml 中添加以下代码，将 autoConfigure Module 作为其依赖项
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>tml-spring-boot-starter-autoconfiguration</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    //分别将两个项目构建即可。
    ```

* 参考：https://blog.csdn.net/sinat_33087001/article/details/120649859

#### GraaIVM

* 参考官方文档：https://docs.spring.io/spring-boot/how-to/native-image/developing-your-first-application.html
* 参考：https://blog.csdn.net/weixin_43933728/article/details/131479472

------

### SpringCloudAlibaba

#### 介绍

##### 基本介绍

* SpringCloudAlibaba提供了微服务开发的一站式解决方案。
* 对SpringCloud的标准实现。
* 以微服务为核心的整体解决方案。
* 开源与平台服务分开维护。

##### 组件

* Nacos（分布式配置）
* Eureka（服务注册/发现）
* Sentinel（服务熔断）
* OpenFeign RestTemplate（服务调用）
* Ribbon（负载均衡）
* Seata（分布式事务）
* Spring Cloud Alibaba Sidecar（Sidecar）
* ![image-20240712203259167](C:\Users\wg_z_\AppData\Roaming\Typora\typora-user-images\image-20240712203259167.png)

#### 环境搭建

##### 环境配置

* 64 bit JDK1.8+；
* Maven 3.2.x+；
* Spring Boot 3.2.x；
* Spring Cloud Alibaba 2023.x；
* Spring Cloud 2023.x；
* 测试工具：JMeter

##### 基础分布式架构

* 基于SpringBoot的父Maven项目

* 创建n个服务

* 通过RestTemplate进行远程调用（没有通过微服务远程调用）

  ```java
  //配置RestTemplate
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
      RestTemplate restTemplate = builder.build();
      return restTemplate;
  }
  //远程调用
  String msg = restTemplate.getForObject("http://localhost:8082/stock/stock", String.class);
  ```


##### 微服务构建

* Spring Cloud and Spring Cloud Alibaba 版本对应官网：https://github.com/alibaba/spring-cloud-alibaba
* Spring Cloud Alibaba官网：https://spring-cloud-alibaba-group.github.io/github-pages/2021/en-us/index.html
* Spring Cloud Alibaba 脚手架：https://start.aliyun.com/

#### Nacos Discovery

##### 核心功能

* 服务注册：Nacos Client会通过发送REST请求方式想Nacos Server注册自己的服务，提供元数据，如IP地址、端口信息、健康状态等信息。Nacos Server接收到注册请求后，会把这些元数据信息存储在一个双层的内存Map中。
* 服务心跳：在服务注册后，Nacos Client会维护一个定时心跳来持续通知NacosServer，说明服务一直处于可用状态，防止被剔除，默认5秒发送一次心跳。
* 服务同步：NacosServer集群之间会相互同步服务实例，用于保证服务信息的一致性。
* 服务发现：Nacos Client在调用服务提供者服务时，会发送一个REST请求给Nacos Server，获取上面注册的服务清单，并且缓存Nacos Client本地，同时会像Nacos Client本地开启一个定时任务定时拉取服务器最新的注册表信息更新到本地缓存。
* 服务健康检查：Nacos Server会开启一个定时任务来检查注册服务实例的健康情况，对于超过15秒没有收到客户端心跳的实例将会将它的healthy属性设置为false，如果某个实例超过30秒没有收到心跳，直接剔除该实例。（如果被剔除的实例回复心跳，则会重新注册）

##### 搭建Nacos Server

* 下载地址：https://github.com/alibaba/nacos/releases
* 解压并进入bin目录并单机启动命令：startup.cmd -m standalone
* 启动问题：https://blog.csdn.net/qq_62982856/article/details/127549913

##### 搭建Nacos Client

* 导入nacos-discovery启动器

  ```xml
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-loadbalancer</artifactId>
  </dependency>
  ```

* Nacos基础配置

  ```yml
  spring:
    application:
      name: orders-nacos-service #服务名称
    cloud:
      nacos:
        server-addr: 127.0.0.1:8848 #服务IP和端口
        discovery:
          username: nacos #nacos用户名
          password: nacos #nacos密码
          namespace: public #命名空间
  ```

* 远程调用（采用微服务）

  ```java
  //配置RestTemplate
  @Bean
  @LoadBalanced //注入负载均衡器
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
  }
  //远程调用
  String msg = restTemplate.getForObject("http://stock-nacos-service/stock/stock", String.class);
  ```

##### Nacos管理界面

* 命名空间一般由项目名命名，用于更细粒度的分割管理。
* 分组与命名空间类似，都是用于分类管理。
* 保护阈值与雪崩保护相关，值为0表示未开启，值范围在0~1之间。
* 临时实例宕机是会进行剔除，设置为false表示永久实例，宕机不会被回收。

##### Nacos集群

###### 环境准备

* 64bitOSLinux/Unix/Mac，推荐使用Linux系统
* 64bit JDK1.8+；
* Maven 3.2+；
* 3个或3个以上Nacos节点才能构成集群。

###### 配置文件

* 修改application.properties配置文件，使用外置数据源

* 使用外置MySQL数据源

  ```properties
  spring.datasoource.platform=mysql
  db.num=1
  db.url.0=jdbc:mysql://127.0.0.1:3306/nacos
  db.user.0=root
  db.password.0=123456
  ```

* 将cluster.conf.example改为cluster.conf，添加节点配置

  ```
  #ip:port
  192.168.159.1:8849
  192.168.159.1:8850
  192.168.159.1:8851
  ```

* 创建MySQL数据库，sql文件位置：conf\nacos-mysql.sql

* 如果出现内存不足：修改启动脚本startup的jvm参数

  ```sh
  JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256 -xx:MetaspaceSize=64m"
  ```

* 分别启动nacos8849、nacos8850、nacos8851

* 安装nginx

* 官方推荐使用nginx反向代理

  ```nginx
  upstream nacoscluster {
  	server 192.168.159.1:8849;
  	server 192.168.159.1:8850;
  	server 192.168.159.1:8851;
  }
  server {
      listen 8847;
      server_name localhost;
      location/nacos/{
          proxy_pass http://nacoscluster/nacos/;
      }
  }
  ```

#### Nacos config（配置中心）

##### 引用配置中心配置文件

* 添加依赖

  ```xml
  <dependency>
  	<groupId>com.alibaba.cloud</groupId>
  	<artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
  </dependency>
  ```

* 必须使用bootstrap.properties配置文件配置NacosServer地址

  ```properties
  spring.application.name=nacos-config
  spring.cloud.nacos.config.server-addr=127.0.0.1:8848
  ```

* 在启动类中读取配置信息

  ```java
  @SpringBootApplication
  @EnableDiscoveryClient  //开启服务发现
  public class OrdersApplication {
      public static void main(String[] args) {
          SpringApplication springApplication = new SpringApplication(OrdersApplication.class);
          springApplication.run(args);
          springApplication.getEnvironment().getProperty("user.name");
      }
  }
  ```

##### 其他扩展配置

* 最佳实践：

  ```txt
  Namespace:代表不同环境，如开发、测试、生产。
  Group：代表项目。
  Dataid：每个项目下诺干个子工程（微服务），每个配置集是一个工程（微服务）的主要配置文件。
  ```

* 默认是properties文件格式，修改为其他格式需要进行设置

  ```properties
  #在bootstrap.properties配置
  spring.cloud.nacos.config.file-extension=yaml
  ```

* 禁用动态刷新

  ```properties
  #在bootstrap.properties配置
  spring.cloud.nacos.config.refresh.enabled=false
  ```

* profile细粒度配置

  ```properties
  #仅支持默认的配置文件，与服务名相同的dataid配置文件
  #在配置中心dataid命名格式：服务名-dev.ymal
  #在application.yaml配置
  spring.profiles.active = dev
  ```

* namespace配置

  ```properties
  #在bootstrap.properties配置
  spring.cloud.nacos.config.namespace = dev
  ```

* Grpup配置

  ```properties
  #在bootstrap.properties配置
  spring.cloud.nacos.config.group = DEVELOP_GROUP
  ```

* DataID配置

  ```properties
  #在bootstrap.properties配置
  #优先级：prodile>配置文件>extension>shared
  #configs[0]值越大优先级越高
  spring.cloud.nacos.config.shared-configs[0].data-id = common.yaml
  #开启动态感知
  spring.cloud.nacos.config.shared-configs[0].refresh = true 
  #设置分组
  spring.cloud.nacos.config.shared-configs[0].group =  DEVELOP_GROUP
  ```

##### @RefreshScope

* @Value注解可以获取配置中心的值，但是无法动态感知修改后的值。

  ```java
  @RestController
  @RefreshScope
  public class configController {
      @Value("${user.name}")
      public String name;
      
      @RequestMapping("/name")
      public String name(){
          return name;
      }
  }
  ```

#### Ribbon-微服务负载均衡器

##### 介绍

###### 基本介绍

* 集中式负载均衡，在消费者和服务提供方中间使用独立的代理方式进行负载。
* 客户端根据自己的请求情况做负载均衡，Ribbon就属于客户端自己做负载均衡。

###### 客户端负载均衡

* 客户端会有一个服务器地址列表，在发送请求前通过负载均衡算法选择一个服务器，然后进行访问。即客户端进行负载均衡算法分配。

###### 服务端负载均衡

* 例如通过Nginx，通过Nginx进行负载均衡，先发送请求，然后通过负载均衡算法，在多个服务器之间选择一个进行访问；即在服务器端再进行负载均衡算法分配。

###### 常见负载均衡算法

* 随机，通过随机选择服务器执行。
* 轮询，负载均衡默认实现方式，请求来之后排队处理。
* 加权轮询，通过对服务器性能的分析，给高配置，低负载的服务器分配更高的权重，均衡各个服务器的压力。
* 地址Hash，通过客户端请求的地址Hash值取模映射进行服务器调度。
* 最小连接数，即使请求均衡，压力不一定均衡，最小连接数法就是根据服务器情况，比如请求积压数等参数，将请求分配到当前压力最小的服务器上，最小活跃数。

##### Nacos使用Ribbon

* nacos-discovery依赖了ribbon，不用再引入ribbon依赖

  ```xml
  <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
  </dependency>
  ```

* 添加**@LoadBalanced**注解

  ```java
  //配置RestTemplate
  @Bean
  @LoadBalanced //负载均衡器
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
  }
  ```

* 修改controller

  ```java
  @Autowired
  private RestTemplate restTemplate;
  @RequestMapping("/create")
  public R create() {
      String url = "http://stock-nacos-service/stock/stock";
      R result = restTemplate.getForObject(url, R.class);
      return result;
  }
  ```

##### 负载均衡策略

###### 策略

* IRule：所有负载均衡策略的父接口，核心方法choose方法，用于选择一个服务实例。

* AbstractLoadBalancerRule：上下文负载均衡器，主要定义了一个LoadBalancer，它主要用于辅助负责负载均衡策略选取合适的服务端实例。

* 策略

  ```
  RandomRule：随机选择一个服务实例。
  RoundRobinRule：轮询负载均衡策略。
  RetryRule：在轮询的基础上进行重试。
  WeightedResponseTimeRule：如果一个服务的平均响应时间越短权重越大，那么该服务实例被选中执行任务的概率也就越大。
  BestAvailableRule：过滤掉失效的服务实例的功能，然后顺便找出并发请求最小的服务实例使用。
  ZoneAvoidanceRule：默认规则，复合判断server所在区域的性能和server的可用性选择服务器。
  ```

###### 修改默认负载均衡策略

* 全局配置

  ```java
  //配置类
  @Configuration
  public class RibbonConfig {
      @Bean
      public IRule iRule() {
          return new RandomRule();
      }
  }
  //该方法配置时，需要排除配置类所在的包，保证ApplicationContext扫描不到这个配置类的包
  //在启动类指定微服务以及负载均衡策略
  @RibbonClients(value={
      @RibbonClient(name = "mall-order", configuration = RibbonConfig.class), //name服务提供方
      @RibbonClient(name = "mall-account", configuration = RibbonConfig.class)
  })
  ```

* 局部配置

  ```yml
  stock-service: #服务提供方
  	ribbon:
  	  NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule
  ```

###### 自定义负载均衡策略

* 通过实现IRule接口或者继承AbstractLoadBalancerRule抽象类，可以自定义负载均衡策略，主要选择逻辑在choose方法中。

  ```java
  public class CustomRule extend AbstractLoadBalancerRule {
      @Override
      public Server choose (Object key) {
          ILoadBalancer loadBalancer = this.getLoadBalancer();
          //获取当前请求的服务实例
          List<Server> reachableServer = loadBalancer.getReachableServers();
          int random = ThreadLocalRandom.current().nextInt(reachableServers.size());
          Server server = reachableServers.get(random);
          return server;
      }
  }
  ```

###### 饥饿加载

* ```yml
  ribbon:
  	eager-load:
  		enabled: true
  		clients: stock-service #指定服务提供方ribbon立即加载，多个提供方使用逗号分离
  ```

##### LoadBalancer

* Spring Cloud LoadBalancer是Spring官方提供的负载均衡器，用于替代Ribbon。

* Spring官方提供了两种负载均衡的客户端，RestTemplate、WebClient。

###### RestTemplate整合LoadBalancer

* 引入依赖

  ```xml
  <!-- Spring Cloud -->
  <dependency>
  	<groupId>org.springframework.cloud</groupId>
  	<artifactId>spring-cloud-dependencies</artifactId>
  	<version>${spring-cloud.version}</version>
  	<type>pom</type>
  	<scope>import</scope>
  </dependency>
  <!-- loadbalancer -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-loadbalancer</artifactId>
  </dependency>
  ```

* 移除ribbon支持

  ```xml
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
      <exclusions>
      	<exclusion>
  			<groupId>org.springframework.cloud</groupId>
  			<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  ```

* 添加**@LoadBalanced**注解

  ```java
  //配置RestTemplate
  @Bean
  @LoadBalanced //负载均衡器
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
  }
  ```

* 修改controller

  ```java
  @Autowired
  private RestTemplate restTemplate;
  @RequestMapping("/create")
  public R create() {
      String url = "http://stock-nacos-service/stock/stock";
      R result = restTemplate.getForObject(url, R.class);
      return result;
  }
  ```

#### OpenFeign(远程服务调用)

##### 介绍

* Feign是Netfix开发的声明式、模板化的HTTP客户端，可以简化便捷的调用HTTP API。
* Spring Cloud Openfeign对Feign进行了增强，使其支持SpringMVC注解，另外还整合了Ribbon和Nacos，从而使用更加方便。
* 使用HTTP请求远程服务时就像调用本地方法一样的体验。

##### 整合OpenFeign

* 引入依赖

  ```xml
  <dependency>
  	<groupId>org.springframework.cloud</groupId>
  	<artifactId>spring-cloud-starter-openfeign</artifactId>
  </dependency>
  ```

* 编写调用接口+@FeignClient注解

  ```java
  /* 
  * value 指定调用rest接口所对应的服务名
  * path  指定调用rest接口所在的controller指定的@RequestMapping
  */
  @FeignCliebt(value="stock-service", path="/stocks")
  public interface StockServiceFeign {
  	//声明需要调用的rest接口对应的方法
  	@RequestMapping("/stock")
  	publuc String stock();
  }
  ```

* 在调用端启动类上添加@EnableFelgnClients注解

* 文献：https://cloud.tencent.com/developer/article/2371890

##### Feign的自定义配置及使用

###### 日志配置

* 日志级别

  ```
  NONE[性能最佳，适用于生产]：不记录任何日志（默认）
  BASIC[适用于生产环境追踪问题]：仅记录请求方法、URL、响应状态代码以及执行时间
  HEADER：在BASIC级别基础上，记录请求和响应的header
  FULL[适用于开发及测试环境定位问题]：记录请求和响应的header、body和元数据
  ```

* 全局配置

  ```java
  @Configuration //添加了配置类注解会进行全局配置
  public class FeignConfig{
  	@Bean
      public Logger.Level feignLoggerLevel(){
          return Logger.Level.FULL;
      }
  }
  ```

* 局部配置

  配置文件配置

  ```yml
  feign:
    client:
  	config:
  	  stock-service: #服务提供者 服务名
  		loggerLevel: BASIC
  ```

  接口中配置

  ```java
  @FeignCliebt(value="stock-service", path="/stocks", configuration = FeignConfig.class)
  public interface StockServiceFeign {
  	//声明需要调用的rest接口对应的方法
  	@RequestMapping("/stock")
  	publuc String stock();
  }
  ```

###### 契约配置

* 配置文件配置

  ```yml
  feign:
    client:
  	config:
  	  stock-service: #服务提供者 服务名
  		contract: feign.Contract.Default #还原成原生注解
  ```

###### 超时时间配置

* 配置连接超时时间和读取超时时间，通过Options进行配置 

* 全局配置

  ```java
  @Configuration //添加了配置类注解会进行全局配置
  public class FeignConfig{
  	@Bean
      public Request.Options options(){
          return new Request.Options(5000, 10000); //第一个参数是连接请求超时，第二个参数是请求处理超时时间
      }
  }
  ```

* 配置文件配置（局部配置）

  ```yml
  feign:
    client:
  	config:
  	  stock-service: #服务提供者 服务名
  		connectTimeout: 5000 #连接超时时间
  		readTimeout: 10000 #请求处理超时时间
  ```

###### 自定义拦截器

* 实现RequestInterceptor接口

  ```java
  //拦截器
  public class FeignAuthRequestInterceptor implements RequestInterceptor {
      @Override
      public void apply(RequesrTemplate template) {
          //业务逻辑
      }
  }
  //全局配置
  @Configuration
  public class FeignConfig{
      @Bean
      public FeignAuthRequestInterceptor feignAuthRequestInterceptor() {
          return new FeignAuthRequestInterceptor();
      }
  }
  
  ```


#### Sentinel（哨兵组件）

##### 介绍

###### 分布式系统遇到的问题

* OOM问题、第三方服务宕机、激增流量、缓存击穿、单点故障、异常未处理、DB超时、负载不均、服务雪崩。

###### 解决方案

* 超时机制：在不做任何处理的情况下，服务提供者不可用导致消费者请求线程强制等待，而造成系统资源耗尽，加入超时机制，一旦超时，就释放资源速度较快，一定程度上可以抑制资源耗尽问题。
* 服务限流：设置流量限制
* 隔离：用户的请求将不再直接访问服务，而是通过线程池中的空闲线程来访问服务，如果线程池满，则会进行降级处理，用户的请求不会被阻塞，至少可以看到一个执行结果，而不是无休止的等待或看到系统崩溃。
* 服务熔断：远程服务不稳定或者网络抖动时暂时关闭。
* 服务降级：有服务熔断，必须有服务降级。

###### 基本介绍

* 面向分布式服务架构的高可用防护组件。

* 主要以流量为切入点。

* **官网**：

  https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D

  https://sentinelguard.io/zh-cn/docs/dashboard.html

##### @SentinelResource

* 用于资源定义以及规则处理

* 添加依赖

  ```xml
  <!-- sentinel依赖 -->
  <dependency>
      <groupId>com.alibaba.csp</groupId>
      <artifactId>sentinel-core</artifactId>
      <version>1.8.6</version>
  </dependency>
  <!-- @SentinelResource依赖 -->
  <dependency>
      <groupId>com.alibaba.csp</groupId>
      <artifactId>sentinel-annotation-aspectj</artifactId>
      <version>1.8.6</version>
  </dependency>
  ```

* 配置Bean

  ```java
  @Configuration
  public class SentinelAspectConfiguration {
  
      @Bean
      public SentinelResourceAspect sentinelResourceAspect() {
          return new SentinelResourceAspect();
      }
  }
  ```

* 使用

  ```
  @SentinelResource 用于定义资源，并提供可选的异常处理和 fallback 配置项。 @SentinelResource 注解包含以下属性：
  
  value：资源名称，必需项（不能为空）
  
  entryType：entry 类型，可选项（默认为 EntryType.OUT）
  
  blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型 BlockException。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
  
  fallback / fallbackClass：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
  返回值类型必须与原函数返回值类型一致；方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
  
  defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：返回值类型必须与原函数返回值类型一致；方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
  
  exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。
  ```

##### 降级规则（一般设置在服务消费端）

* 慢调用比例：
* 异常比例：
* 异常数：请求有两个异常数就会触发熔断
* 最小请求数：默认是5，触发熔断最小请求数
* 统计时常（statIntervalMs）：默认1秒，界面没有，使用代码设置。在1秒内出现两次异常，触发熔断。
* 熔断持续时间：单位秒，在该时间内调用降级处理接口，时间过后恢复请求，如果再次出现异常直接降级。

##### 启用Sentinel控制台

* 下载控制台jar包并在本地启动

  ```cmd
  java -jar sentinel-dashboard-1.8.6.jar
  ```

* 快捷方式

  ```cmd
  java -Dserver.port=8858 -Dsentinel.dashboard.auth.username=username -Dsentinel.dashboard.auth.password=123456 -jar sentinel-dashboard-1.8.6.jar
  #将该命令放在.bat文件中
  #-Dserver.port 设置端口
  #-Dsentinel.dashboard.auth.username 设置登录时的用户名
  #-Dsentinel.dashboard.auth.password 设置登陆时密码
  ```

* 客户端整合控制台

  ```xml
  <dependency>
      <groupId>com.alibaba.csp</groupId>
      <artifactId>sentinel-transport-simple-http</artifactId>
      <version>1.8.6</version>
  </dependency>
  ```

* 配置JVM参数

  ```cmd
  启动时加入 JVM 参数 -Dcsp.sentinel.dashboard.server=consoleIp:port 指定控制台地址和端口。若启动多个应用，则需要通过 -Dcsp.sentinel.api.port=xxxx 指定客户端监控 API 的端口（默认是 8719）。
  ```

##### Cloud Alibaba整合Sentinel

* 导入依赖

  ```xml
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
  </dependency>
  ```

* 设置控制台

  ```yaml
  spring:
    cloud:
      sentinel:
        transport:
          port: 8719
          dashboard: localhost:8858
  ```

##### 流控规则

* 原理：监控应用流量的QPS或并发线程数等指标，当达到指定的阈值时对流量进行控制，以免被瞬时的流量高峰冲垮，从而保证高可用。
* 场景：洪峰流量（秒杀、大促、下单、订单回流），消息型场景（削峰填谷、冷热启动），付费系统（根据使用流量付费），API Gateway（精准控制API流量），探测应用中运行的慢程序块进行限制。

###### QPS

* 每秒请求数，服务器在一秒内处理多少个请求数，一般在**服务端**进行配置。

* QPS出现异常处理

  ```java
  @RequestMapping("/flow")
  @SentinelResource(value="flow", blockHandler = "flowBlockHandler")
  public String flow(){
      return "正常访问";
  }
  
  public String flowBlockHandler(BlockException e) {
      return "流控了";
  }
  ```

###### 并发线程数

* Sentinel并发控制并不负责创建和管理线程池，而是简单统计当前请求上下文的线程数目，如果超出阈值，新的请求会被立即拒绝，并发控制通常在**调用端**进行配置。

###### 统一异常处理

* 异常统一处理

  ```java
  
  @Component
  public class MyBlockExceptionHandler implements BlockExceptionHandler {
  
      @Override
      public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
  
          log.info(e.getRule);
              
          ResponseResult rs=null;
  
          if(e instanceof FlowException)
              rs= Response.createFailResp(-1,"接口限流了");
          else if(e instanceof DegradeException)
              rs=Response.createFailResp(-2,"服务降级了");
          else if(e instanceof ParamFlowException)
              rs=Response.createFailResp(-3,"参数限流了");
          else if(e instanceof AuthorityException)
              rs=Response.createFailResp(-4,"权限规则不通过");
          else if(e instanceof SystemBlockException)
              rs=Response.createFailResp(-5,"系统保护");
  
          httpServletResponse.setContentType("application/json;charset=utf-8");
          httpServletResponse.getWriter().write(JSON.toJSONString(rs));
  
      }
  }
  
  ```

###### 流控模式

* 直接：统计当前资源的请求，触发阈值时直接对当前资源限流，默认模式。
* 关联：统计与当前资源相关的另一个资源，触发阈值时，对当前资源限流。
* 链路：统计从指定链路访问到当前资源的请求，触发阈值时对指定链路限流。

###### 流控效果

* 快速失败：就是被限流时，返回失败信息到页面。
* Warm UP：默认coldFactor冷加载因子为3，即请求QPS从（请求总数/3）开始，经过我们设置的时间才逐渐升至设定的QPS阈值。系统初始化的阈值为10/3等于3.即阈值刚开始为3.如果刚开始每秒请求数大于3则默认失败。过了5秒后阈值才慢慢升高恢复到10，即5秒后能承受大于3但是仍要小于10的请求，才不会被限流。
* 排队等待：匀速排队，让请求以均匀的速度通过，必须时QPS才能生效。

##### 熔断降级规则

* 需要对不稳定的弱依赖服务调用进行熔断降级，暂时切断不稳定调用，避免局部不稳定因素导致整体雪崩。熔断降级作为保护自身的手段，一般在调用端进行配置

###### 慢调用比例

* *选择以慢调用比例作为阈值，需要设置允许的慢调用RT（最大响应时间），请求的响应大于该阈值则统计为慢调用。当单位统计时长内请求数目大于设置的最小请求数目，并且慢调用比例大于阈值，则会进行熔断。*经过熔断时长后熔断器会进入深测恢复状态，若接下来的请求响应时间小于设置的慢调用RT，则结束熔断，若大于设置的慢调用RT则会再次被熔断。

###### 异常比例

* 当单位统计时长内请求数目大于这只的最小请求数目，并且异常的比例大于阈值，则会被熔断降级。经过熔断时长后熔断器会进入深测恢复状态，若接下来的请求没有错误则结束熔断，否则再次被熔断降级。 

###### 异常数

* 当单位统计时长内的异常数目超过阈值后会自动进行熔断。经过熔断时长后熔断器进入深测恢复状态，若接下来的请求没有错误则结束熔断，否则再次熔断降级。

##### 整合OpenFeign进行降级

* 导入依赖

  ```xml
  <!-- Nacos依赖 -->
  <!-- openfeign依赖 -->
  <!-- sentinel依赖 -->
  ```

* 创建降级类并必须实现提供服务者接口

  ```java
  @Component
  public class StockFeignServiceFallback implements StockFeignService {
  	@Overrid
      public String stock() {
          return "降级方法";
      }
  }
  ```

* 在配置文件中启动sentinel整合openfeign

  ```yaml
  feign:
    sentinel:
  	enabled: true
  ```

* 在提供者接口上设置降级方法

  ```java
  @FeignClient(value="stock-service", path = "/stocks", fallback = StockFeignServiceFallback.class)
  publuc interface StockFeignService {
  	@RequestMApping("/stock")
  	public String stock();
  }
  ```

##### 热点规则

* 热点：经常访问的数据，统计某个热点数据中访问频次最高的数据，并进行流控。
* *资源名必须时@SentinelResource(value="资源名")中配置的资源名，热点规则依赖于注解。*

###### 单机阈值

* 假设参数大部分都是热点参数，那么单机阈值就主要争对热点参数进行流控，后续额外针对普通参数进行流控
* 假设参数大部分都是普通参数，那么单机阈值就主要争对普通参数进行流控，后续额外针对热点参数进行流控

##### 系统保护规则

* Load：仅对Linux/Unix-like系统生效。
* CPU usage：当系统CPU使用率超过阈值即触发系统保护。
* 平均RT：当单台机器上所有入口流量的平均RT达到阈值时触发系统保护，单位是毫秒
* 并发线程数：当单台机器上所有入口的流量并发线程数达到阈值即触发系统保护。
* 入口QPS：当单台机器上所有入口流量的QPS达到阈值时触发系统保护。

##### 规则持久化

###### 推模式

* 通过远程配置中心进行持久化，基于Nacos

* 引入依赖

  ```xml
  <dependency>
      <groupId>com.alibaba.csp</groupId>
      <artifactId>sentinel-datasource-nacos</artifactId>
  </dependency>
  ```

* 在Nacos配置中心编写规则

  ```json
  [{
  	"clusterMode": false,
  	"controlBehavior": 0,
  	"count": 5.0,
  	"grade": 1,
  	"limitApp": "default",
  	"resource": "/sentinel/flow",
  	"strategy": 0
  }]
  
  ```

* 在yml中引入配置中心规则

  ```yaml
  spring:
  	cloud:
  		sentinel:
  			datasource:
  			  # 自定义命名
  			  flow-rule:
  			    # 支持多种持久化数据源：file、nacos、zk、apollo、redis、consul
  			    nacos:
  			      # naco服务地址
  			      server-addr: localhost:8848
  			      # 命名空间，根据环境配置
  			      namespace: public
  			      # 这里我做了一下细分，不同规则设置不同groupId
  			      group-id: SENTINEL_FLOW_GROUP
  			      # 仅支持JSON和XML类型
  			      data-id: ${spring.application.name}-flow-rules.json
  			      # 规则类型：flow、degrade、param-flow、system、authority
  			      rule-type: flow
  			      # nacos开启了认证需要配置username、password
  			      # username: nacos
  			      # password: nacos
  ```

* 参考：https://blog.csdn.net/weixin_42270645/article/details/123399569

#### Seata（事务组件）

##### 介绍

###### 基本介绍

* Seata是分布式事务解决方案，致力于提供高性能和简单易用的分布式事务服务。Seata将为用户提供了AT、TCC、SAGA和XA事务模式。
* 官网：https://seata.apache.org/zh-cn/

###### Seata三大角色

* TC：事务协调者，维护全局和分支事务的状态，驱动全局事务提交或回滚。
* TM：事务管理器，定义全局事务的范围，开启全局事务、提交或回滚全局事务。
* RM：资源管理器，管理分支事务处理的资源，与TC以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。
* TC为单独部署的Server服务端，TM和RM为嵌入到应用中的Client客户端。

###### 分布式事务理论

* 解决分布式事务也有相应的规范和协议。分布式事务相关的协议有2PC和3PC，由于三阶段提交协议3PC非常难实现，目前市面主流的分布式事务解决方案都是2PC协议。

###### 2PC两阶段协议

* 分为两个阶段：Prepare（预处理）和Commit（提交）
* 第一阶段：预处理阶段，协调者会向所有参与者发送事务请求，询问是否可执行，等待各个参与者响应。各个参与者接收协调者事务请求后，执行事务操作，并将Undo和Redo信息记录到事务日志中。如果参与者成功执行了事务并写入Undo和Redo信息，会向协调者发送YES响应或者不响应或者响应NO。
* 第二阶段：提交阶段，所有参与者返回响应YES，协调者会通知参与者统一执行Commit。

###### 2PC问题

* 同步阻塞
* 协调者宕机
* 数据不一致

##### 4种模式

###### AT模式

* 第一阶段：Seata会拦截业务SQL，首先解析SQL语句，找到业务SQL要更新的业务数据，在业务数据被更新前，将其保存成before image，然后执行业务SQL更新业务数据，在业务数据更新后，将其保存成after image，然后生成行锁。以上操作全部在一个数据库事务内完成，保证了操作的原子性。
* 第二阶段：如果是提交，因为业务SQL在第一阶段已经提交至数据库，所以Seata只需要将第一阶段保存的快照数据和行锁删除，完成数据清理即可。
* 如果是回滚，Seata需要回滚第一阶段已经执行的业务SQL，还原业务数据。回滚方式使用before image还原业务数据；但是在还原前需要校验脏写，对比数据库当前业务数据与after image数据，如果两份数据完全一致就说明没有脏写，可以还原业务数据，如果数据不一致，出现脏写则需要人工干预。

###### TCC模式

* TCC模式需要用户根据自己的业务场景实现Try、Confirm、Cancel三个操作；事务发起方在一阶段执行Try方法，在第二阶段执行Confirm方法，二阶段回滚执行Cancel方法。
* 第三方TCC框架：BeyeTCC、TCC-transaction、Himly。

##### TC环境搭建

###### Service端存储模式

* file：单机模式，全局事务会话信息内存中读写并持久化本地文件root.data，性能高（默认）
* db：高可用模式，全局事务会话信息通过db共享，响应性能较差
* redis：Seata Server1.3以及以上版本支持，性能高，存在事务信息丢失风险，配合redis持久化使用。

###### 资源文件

* client：存放client端sql脚本，参数配置
* config-center：各个配置中心参数导入脚本，config.txt为通用参数文件
* server：server端数据库脚本以及各个容器配置

###### db存储模式+Nacos部署

* 下载安装包

  地址：https://github.com/apache/incubator-seata/releases

* 修改配置文件（file.config）

  ```conf
  mode = "db"
  
  url = "jdbc:mysql://127.0.0.1:3306/seata_server"
  user = "root"
  password = "123456"
  ```

* 新建数据库seata_server

* 创建表官网提供：https://github.com/apache/incubator-seata/tree/master/script/server/db

* 配置Nacos注册中心和配置中心

  ```conf
  #registry.conf
  #nacos
  type = "nacos"
  username = "nacos"
  password = "123456"
  #config
  type = "nacos"
  username = "nacos"
  password = "123456"
  #config.txt
  store.mode=db
  service.vgrouupMapping.tx-service-group=default
  ```

* 将config.txt注册到nacos

  ```shell
  运行nacos-config.sh
  sh $nacos-config.sh -h localhost -p 8848 -g SEATA_GROUP -t 
  ```

* 启动Seata Server

##### 微服务整合seata

* 添加依赖

  ```xml
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
  </dependency>
  ```

* 各个微服务对应数据库中添加undo_log表

* 客户端配置分组

  ```properties
  #application配置文件
  spring.cloud.alibaba.seata.tx-service-group = default
  ```

* 配置seata注册中心

  ```yaml
  #注册中心
  seata:
    registry:
  	type: nacos
  	  nacos:
  		server-addr:127.0.0.1:8848 #seata server 所在nacos服务地址
  		application: seata-service #seata server 服务名
  		username: nacos
  		password: 123456
  		group: SEATE_GROUP #seata server所在组
    config:
      type: nacos
      nacos:
        server-add: 127.0.0.1:8848
        username: nacos
        password: 123456
        group: SEATA_GROUP
  ```

* 在需要添加事务的接口添加@GlobalTransactional

#### Gateway（网关组件）

##### 介绍

* 每个业务都需要鉴权、限流、权限校验、跨域等问题，完全可以抽离出来放在一个统一的地方。
* 稳定与安全问题：全局性流控、日志统计、防止SQL注入、防止Web攻击、屏蔽工具扫描、黑白IP名单、证书/加解密处理
* 服务：服务级别流控、服务降级与熔断、路由与负载均衡、灰度策略、服务过滤聚合与发现、权限验证与用户等级策略、业务规则与参数校验、多级缓存策略

###### 核心概念

* 路由：路由信息包括ID、目的URL、一组断言工厂、一组Filter。如果断言为真，则说明请求的URL和配置的路由匹配。
* 断言：断言函数运行开发者去定义匹配的Http request中的任何信息，比如请求头和参数等
* 过滤器：filter分为GatewayFilter和Global Filter。Filter可以对请求和响应进行处理。

##### 环境搭建

* 引入依赖

  ```xml
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-gateway</artifactId>
  </dependency>
  <!-- 会和spring-webmvc的依赖冲突，需要排除spring-webmvc -->
  ```

* 编写yml配置文件

  ```yaml
  spring:
    cloud:
      gateway:
        routes:  # 路由规则数组
          - id: product_route # 当前路由的标识，要求唯一
            url: http://localhost:8081  # 请求要转发的地址
            order: 1 # 路由优先级，数字越小级别越高
            predicates: # 断言（路由转发需要满足的条件）
              - Path = /product-serv/** # 当请求路径满足Path指定的规则时，才进行路由转发
            filters: # 过滤器，请求在传递过程中可以通过过滤器对其进行一定的修改
              -StripPrefix = 1
  ```

##### 整合Nacos

* 编写yml配置文件--推荐

  ```yaml
  spring:
    cloud:
      nacos:
        discovery:
          server-addr: 127.0.0.1:8848
          username: nacos
          password: 123456
      gateway:
        routes:  # 路由规则数组
          - id: product_route # 当前路由的标识，要求唯一
            url: lb://order-service  # lb是从nacos中按照名称获取微服务，并遵循负载均衡策略
            order: 1 # 路由优先级，数字越小级别越高
            predicates: # 断言（路由转发需要满足的条件）
              - Path = /product-serv/** # 当请求路径满足Path指定的规则时，才进行路由转发
            filters: # 过滤器，请求在传递过程中可以通过过滤器对其进行一定的修改
              -StripPrefix = 1
  ```

* 简写yml配置文件-不推荐

  ```yaml
  spring:
    cloud:
      nacos:
        discovery:
          server-addr: 127.0.0.1:8848
          username: nacos
          password: 123456
      gateway:
        discovery:
          locator:
            enabled: true #是否启动自动识别Nacos服务
  ```

##### 路由断言工厂配置

###### 内置：

* 官网：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/

###### 自定义断言工厂

* 自定义断言工厂需要继承AbstractRoutePredicateFactory类，重写apply方法逻辑。在apply方法中可以通过exchange.getRequest()拿到ServerHttpRequest对象，从而获取请求参数、请求方式、请求头等信息。
* 必须是Spring组件bean
* 类命名必须以RoutePredicateFactory结尾

* 必须声明静态内部类，声明属性来接收配置文件中的信息
* 需要结合shortFildOrder进行绑定
* 通过apply进行逻辑判断，true匹配成功，false匹配失败
* 文献：https://blog.csdn.net/chaojunma/article/details/107331172

##### 过滤器

###### 过滤器工厂：

* 官网：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gatewayfilter-factories
* 文献：https://blog.csdn.net/abu935009066/article/details/112252692

###### 全局过滤器：

* 实现GlobalFilter接口
* 重写filter方法
* 文献：https://blog.csdn.net/duan_yuer/article/details/120117242

##### CORS跨域处理

* 通过yml配置

  ```yaml
  spring:
    cloud:
      gateway:
        cors-configurations:
          '[/**]': # 允许跨域访问的资源
           allowedOrgins: "*" #跨域运行来源
           allowedMethods: #运行的跨域请求方式
             -GET
             -POST
             -DELETE
             -PUT
             -OPTION
  ```

* 通过java文件配置

  ```java
  @Configuration
  public class CoreConfig {
  	@Bean
      public CorsWebFilter corsFilter() {
          CorsConfiguration config = new CorsConfiguration();
          config.addAllowedMethod("*");
          config.addAllowedOrigin("*"); //运行的来源
          config.addAllowedHeader("*"); //运行的请求头参数
          //运行访问的资源
          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(
              new PathPatternParser
          );
          source.registerCorsConfiguration("/**", config);
          return new CorsWebFilter(source);
      }
  }
  ```

##### 整合sentinel流控降级

* 网关层限流可以简单的针对不同路由进行限流，也可以针对业务的接口进行限流，或者根据接口的特征分组限流。

* 导入依赖

  ```
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-alibaba-sentinel-gateway</artifactId>
  </dependency>
  
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
  </dependency>
  ```

* 添加配置

  ```yaml
  sentinel:
    transport:
      dashboard: 127.0.0.1:8080 #添加sentinel的控制台地址
  ```

##### 网关配置类

* ```java
  @Configuration
  public class GetewayConfig {
  	@PostContruct
  	public void init() {
  		BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
  			@Override
  			public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable) {
  				HashMap<String, String> map = new HashMap<>();
  				map.put("code", HttpStatus.TOO_WANY_REQUESTS.toString());
                  //自定义异常处理
  				return ServerResponse.status(HttpStatus.OK)
  						.contentType(MediaType.APPLICATION_JSON)
  						.body(BodyInserters.fromValue(map));
  			}
  		};
          GetewayCallbackManager.setBlockHandler(blockRequestHandler);
  	}
  }
  ```

#### SkyWalking(链路追踪组件)

##### 介绍

* SkyWalking是分布式系统的应用程序性能监视工具，专为微服务、云原生架构和基于容器架构设计，是一款优秀的APM工具，包括分布式追踪、性能指标分析、应用和服务依赖分析。
* 官网：https://skywalking.apache.org/

##### SkyWalking环境搭建

* 下载SkyWalking
* 注意修改默认端口

###### 搭建微服务客户端

* 在运行的程序配置JVM参数

  ```sh
  -javaagent:D:\server\apache-skywalking-apm-bin-se9.2\agent\skywalking-agent.jar # 指定agent。jar所在位置
  -DSW_AGENT_NAME=服务名 # 指定服务名
  -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800 # 指定aollector连接地址
  ```

##### SkyWalking持久化跟踪数据

* 修改config目录下的application.yml，使用MySQL作为持久化存储仓库

  ```yaml
  storage:
    selecter: ${SW_STORAGE:mysql}
  ```

* 同文件下修改MySQL连接配置

* 在oap-libs下放入MySQL驱动jar包

* 创建数据库，表信息由SkyWalking自动生成

* 启动SkyWalking

##### 自定义链路追踪

* 添加SkyWalking工具类

  ```xml
  <!-- 与我们的服务版本一致 -->
  <dependency>
      <groupId>org.apache.skywalking</groupId>
      <artifactId>apm-tooljit-trace</artifactId>
      <version>9.2.0</version>
  </dependency>
  ```

* 在需要被追踪的方法上添加@Trace注解

* 为追踪链路增加其他额外的信息，如返回值和参数，在方法上添加@Tag或者@Tags注解

  ```java
  //key为方法名，value为returnedObj返回值，arg[0]为参数
  @Trace
  @Tag(key = "list", value = "returnedObj")
  public List<User> list() {
  	return userMapper.list();
  }
  ```

##### 集成日志框架

* 官网：https://skywalking.apache.org/docs/skywalking-java/next/en/setup/service-agent/java-agent/application-toolkit-logback-1.x/

* 引入依赖

  ```xml
  <dependency>
      <groupId>org.apache.skywalking</groupId>
      <artifactId>apm-toolkit-logback-1.x</artifactId>
      <version>9.2.0</version>
  </dependency>
  ```

* 添加logback-spring.xml文件，并配置%tid占位符

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration>
  	<!-- 引入 Spring Boot 默认的 logback XML配置文件 -->
      <include resource="org/springframework/boot/logging/logback/defaults.xml" />
      <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
      	<!-- 日志格式化 -->
          <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
          	<layout class="
                           org.apache.skywalking.apm.toolkit.log.logback.v1.x.TraceIdPatternLogbackLayout">
              	<PAttern>${CONSOLE_LOG_PATTERN}</PAttern>
              </layout>
          </encoder>
      </appender>
      <!-- gRPC -->
      <appender name="grpc-log" class="org.apache.skywalking.apm.toolkit.log.logback.v1.x.log.GRPCLogClientAppender">
          <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
              <layout class="org.apache.skywalking.apm.toolkit.log.logback.v1.x.mdc.TraceIdMDCPatternLogbackLayout">
                  <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%X{tid}] [%thread] %-5level %logger{36} -%msg%n</Pattern>
              </layout>
          </encoder>
      </appender>
      <!-- 设置Appender -->
      <root level="INFO">
      	<appender-ref ref="console"/>
          <appender-ref ref="grpc-log"/>
      </root>
  </configuration>
  ```

* 如果skywalking没有部署在本地，需要在agent\config\agent.config中做配置

  ```xml-dtd
  plugin.toolkit.log.grpc.reporter.server_host=${SW_GRPC_LOG_SERVER_HOST:127.0.0.1}
  plugin.toolkit.log.grpc.reporter.server_port=${SW_GRPC_LOG_SERVER_PORT:11800}
  plugin.toolkit.log.grpc.reporter.max_message_size=${SW_GRPC_MAX_MESSAGE_SIZE:10485760}
  plugin.toolkit.log.grpc.reporter.upstream_timeout=${SW_GRPC_LOG_GRPC_UPSTREAM_TIMEOUT:30}
  ```



------

### Redis

#### Redis介绍

* Redis是基于内存的key-value键值对的内存数据库。
* Redis数据操作主要在内存，而MySQL是关系数据库，在特定场景需要配合使用。
* Redis性能高、数据类型丰富、支持持久化、支持备份

#### Redis持久化

* 将内存中的数据持久化保存在磁盘，可以通过RDB（快照）或AOF（复制命令）或者RDB+AOF混合持久化。

##### RDB（快照）

###### 介绍：

* RDB持久性以指定的时间间隔执行数据集的时间点快照。
* 配置文件所在模块：SNAPSHOTTING模块

* 快照文件（dump.rdb），RDB保存的的是dump.rdb文件。
* 适合大规模数据恢复、按照业务定时备份、对数据完整性和一致性要求不高、RDB加载速度要远远大于AOF。
* RDB需要fork以便使用子进程在磁盘上持久化，数据量过大可能会导致IO性能下降，内存膨胀。如果redis意外宕机，容易造成最近数据丢失，文件破损。

###### 自动触发-配置说明

* 自动触发：save 时间 频次 （修改config配置文件）
* 修改dump.rdb保存路径：dir 路径
* 修改dump.rdb文件名：dbfilename dump6379.rdb
* 恢复数据：将备份文件移动到redis安装目录并启动服务器。物理恢复，分机隔离。

###### 手动触发-命令

* Redis提供了两个命令手动生成RDB文件，分别是save和bgsave（默认）。手动触发会覆盖自动触发
* save：在主程序中执行save会阻塞当前redis服务器，直到持久化完成。在执行save命令期间，Redis不能处理其他命令。
* bgsave：在后台进行异步快照，不会阻塞。在执行bgsave命令期间，Redis还能处理其他命令。
* lastsave命令可以查看上一次保存文件的时间。

###### 检查并修复RDB文件

* 命令： **redis-check-rdb** /rdb文件

###### 触发RDB快照

* 配置文件默认快照配置
* 手动调用save/bgsave命令
* 执行flushall/flushdb命令也会产生rdb文件，但是内容为空
* 执行shutdown命令且没有开启AOP持久化
* 主从复制时，主节点自动触发

###### 禁用快照

* 命令方式动态停止RDB保存规则（只在本次有效）：redis-cli config set save " "
* 配置文件方式：save " "

##### AOF（复制命令）

###### 介绍

* 以日志的形式记录每个写操作，不记录读操作，只能追加文件不能改写文件
* 配置文件所在模块：APPEND ONLY MODE
* 默认情况下，AOP没有开启，需要在配置文件中开启
* 保存文件为appendonly.aop
* Redis7新特性，aof文件被分为 三个文件：基本文件（base.rdb）、增量文件（incr.aof）、清单文件（manifest）
* 更好 的保存数据不丢失、性能高、可做紧急恢复。
* 相同数据集aof文件大于rdb，加载会恢复比rdb慢。

###### AOF持久化流程

* redis接收命令来源
* 将命令放入AOF缓存区，避免频繁IO操作
* 缓存区根据同步文件的**写回策略**将命令写入AOF文件
* 随着AOF内容增加为了避免文件膨胀会进行**AOF重写**，压缩AOF文件
* 重启服务器时，从AOF文件载入数据

###### 写回策略

* always：同步写回，每个写命令执行完立刻同步的及那个日志写回磁盘
* everysec：（默认）每秒写回，每秒执行一次同步
* no：操作系统控制的写回，由操作系统决定将缓存区的内容写回磁盘

###### 开启AOP持久化配置

* 打开AOP持久化：appendonly yes
* 配置写回策略：appendfsync everysec
* 配置AOP持久化保存路径：appenddirname "appenddirname"   最终文件路径：RDB的dir + appenddirname
* 配置AOP持久化保存文件名称：appendfilename "appendfilename.aof"

###### 文件异常恢复

* 命令：**redis-check-aof --fx** aof文件

###### AOF重写机制

* 当AOF文件大小超过设定的峰值，Redis自动启动AOF文件内容压缩，**只保留可恢复数据的最小指令集**。

* 重写原理：

  * 重写前床架子进程，读取现有AOF文件，将指令进行分析压缩并写入临时文件
  * 主进程接收命令到内存缓存区，一边写入到原有的AOF文件
  * 子进程完成重写任务，父进程会将内存缓存的指令写入到新AOF文件
  * 写入结束后，redis会用新文件代替旧文件
  * 有新的写操作时，以命令的方式重写一个新文件

* 自动触发--配置文件

  ```
  auto-aof-rewrite-percentage 100 判断当前aof大小比上次重写aof大小是不是增长了1倍
  auto-aof-rewrite-min-size 64mb  重写时满足文件大小
  需要两者同时满足，才会触发
  ```

* 手动触发--命令：bgrewriteaof

##### RDB+AOF混合持久化

###### 介绍

* 在同时开启 rdb和aof持久化时，重启时只会加载aof文件，不会加载rdb文件。aof文件优先级高于rdb。

* RDB持久化方式能够在指定时间间隔对数据进行快照存储，AOF持久化方式记录每次服务器的写操作。通常情况下aof文件保存的数据更完整，而且同时开启优先加载AOF，RDB更适合用于备份数据库。
* 混合持久化产生的文件一部分时RDB格式，一部分时AOF格式。在加载时会从RDB和AOF两部分进行数据恢复。

###### 开启混合配置

* aof-use-rdb-preamble yes

##### 纯缓存模式

* 同时关闭RDB和AOF混合持久化，命令依然可以使用

* 禁用RDB和AOF配置

  ```
  //禁用RDB
  rdb：save " "
  //禁用AOF
  aof：appendonly no
  ```

#### Redis事务

##### 介绍

* 一次执行一组命令的集合。一个事务中的所有命令都会序列化，按照顺序串行化执行而不被其他命令插入。
* 具有单独的隔离操作，没有隔离级别，不保证原子性，具有排他性。
* 事务的原理采用的是队列
* 编译时错误命令会终止事务。运行时错误，会执行正确命令，放弃错误命令。
* 乐观锁：每次拿数据时不会认为别人修改，所以不会上锁，但是在更新时会进行判断有没有其他操作更新 这个数据。
* watch命令时一种乐观锁的实现，Redis在修改时会检测数据被否被更改，如果被更改，则整个事务执行失败。

##### 使用

* 正常执行

  ```
  MULTI //开启
  sql1  //入队
  ...
  EXEC  //结束
  ```

* 放弃事务

  ```
  MULTI
  sql1
  ...
  DISCARD
  ```

* 开启监控/放弃监控

  ```
  //开启监控
  watch key
  //放弃监控
  unwatch key
  ```



#### Redis管道

##### 介绍

* 通过一次响应将结果一次性返回，减少客户端与redis的通信次数降低流通信的往返时间，解决RTT问题。
* 底层原理：由队列组成 ，利用先进先出保证数据的顺序性.
* 管道与原生批量命令（如mset）的区别：原生批量命令是原子性，pipeline是非原子性。支持不同操作的命令。
* 管道与事务的区别：事务具有原子性，管道是一次性执行，事务是一条一条执行。

##### 使用

* 将执行的命令写在txt文件中：cat cmd.txt
* 通过管道执行txt文件：cat cmd.txt | redis-cli -a 123456 --pipe

#### Redis主从复制

##### 介绍

* master以写为主，slave以读为主，当master数据变化时，自动将新的数据异步到其他slave数据库
* 读写分离、容灾备份、数据备份、水平扩容支持高并发
* 系统繁忙时复制可能有延时。主机宕机时，不会自动选择主机，系统处于半瘫痪。

##### 原理和工作流程

* 从机连接到主机会发送sync命令，首次连接从机会完全复制主机数据，原有的数据将会被主机数据覆盖
* 主机接收sync命令后，开始在后台保存快照，将rdb快照文件和缓存中的数据发送给从机。从机接收并加载到内存完成复制初始化
* 通过心跳保持通信
* master继续将新的所有收集到的修改命令依次传给slave，完成同步
* 若从机宕机后重启，主机会将这期间丢失的数据复制给从机

##### 使用

* 配从库不配主库
* master如果配置类requirepass参数，slave需要配置masterauth来设置校验密码
* 需要主机和从机网络相互ping通且注意防火墙配置
* 基本命令：
  * 查看复制节点关系：info replication
  * 临时切换主机：slaveof 主库IP 主库端口
  * 由从机转主机：slaveof no one
* 配置文件
  * 配置主从关系：replicaof 主库IP 主库端口
  * 配置心跳：repl-ping-replica-period 10

##### 面试题

* 主机宕机后，从机会上位吗？
  * 从机数据正常使用，等待主机重启。
* 主机宕机重启后主从关系还在吗？从机能否顺利复制？
  * 主机启动恢复主从关系，依然能够进行复制
* 从机宕机重启还能跟上主机数据吗/
  * 数据依然能够被复制

#### Redis哨兵

##### 介绍

* 哨兵用于监控后台主机是否故障，如果故障需要根据投票自动将从机转换为新的主机，继续对外服务。
* 监控主机和从机状态，从从机自动选择主机上线。
* 自动监控和维护集群，不存放数据，只是哨兵作用
* 原主机重启称为从机
* 主要功能：
  * 主从监控：监控主从库是否正常运行
  * 消息通知：将故障转移的结果发送给客户端
  * 故障转移：主机异常，进行主从切换，选择一个从机为新主机
  * 配置中心：客户端通过 连接哨兵获得 当前redis服务的主节点地址

##### 哨兵运行流程和选举原理

* 运行流程
  * 主从机正常运行
  * 主观下线：单个哨兵自己主观上检测主机状态，哨兵发送ping心跳，一定时间没有收到合法恢复，将会达到主观下线的条件。
  * 客观下线：一定数量的哨兵达成一致意见，认为该主机出现异常，将会达到客观下线的条件。
  * 采用Raft算法选择主哨兵，并由该哨兵从从机选择主机。

* 选举原理：
  * 某个从机被选择为新主机，在从机健康的情况下，比较权限、复制偏移量、运行ID
  * 成为主机后会向其他从机发送命令，让其他从机成为新节点的从机。
  * 主机重启，将会以从机的身份，成为新主机的从机。

##### 常用命令

* redis-sentinel  /path/to/sentinel.conf     启动哨兵服务器
* info replication                                            查看状态信息

##### 哨兵通用配置文件

```
bind 0.0.0.0
daemonize yes
protected-mode no
port 26379
logfile "myredis/sentinel_26379.log"
pidfile /var/run/redis-sentinel_26379.pid
dir /myredis
sentinel monitor mymaster 主机IP地址 redis端口号 2
sentinel auth-pass mymaster 123456
```

##### 配置文件

  ```
  //设置要监控的master服务器，quorum表示法定票数
  sentinel monitor <master-name> <ip> <redis-port> <quorum>
  
  //master设置了密码，连接master服务需要密码
  sentinel auth-pass <master-name> <password>
  
  //指定多少毫秒之后，主节点没有应答哨兵，此时哨兵主观上认为主节点下线
  sentinel down-after-milliseconds <master-name> <milliseconds>
  
  //表示允许并行同步的从机个数，当主机宕机后，哨兵会选择新的主机，剩余的从机会向新主机发送同步数据请求
  sentinel parallel-syncs <master-name><nums>
  
  //故障转移的超时时间，进行故障转移时，如果超过设置的毫秒，表示故障转移失败
  sentinel failover-timeout <master-name> <milliseconds>
  
  //配置当某一事件发生时所需要执行的脚本
  sentinel notifiction-script <master-name> <script-path>
  
  //客户端重新配置主节点参数脚本
  sentinel client-reconfig-script <master-name> <script-path>
  ```

#### Redis集群

##### 介绍

* 由于数据量过大，单个主机复制集难以承担，因此需要多个复制集进行集群，形成水平扩展每个复制集只负责存储整个数据集的一部分，**提供在多个redis节点间共享数据的程序集。**

* 集群支持多个主机，每个主机又可以有多个从机
* 集群自带哨兵的故障转移机制，不需要再去使用哨兵功能
* 客户端自带redis的节点连接，不需要连接集群中的所有节点，只需要连接一个节点即可
* 槽位slot负责分配到各个服务节点，由对应的集群负责维护节点、插槽和数据之间的关系。

##### 集群算法-分片-槽位solt

* 槽位：redis集群没有使用一致性hash，而是引入哈希槽的概念；redis集群有16384个哈希槽，每个key通过CRC16校验后对16384取模来决定放在哪个槽位，集群的每个节点负责一部分hash槽。
* 分片：将存储的数据分散到多台redis机器上，集群中的每个redis实例都被认为时整个数据的一个分片。为了找到给定的key的分片，对key进行CRC16算法处理并通过对增分片数量取模，然后，使用确定性哈希函数读取特定key的位置。
* 优势：方便扩容和缩容，易添加和删除节点 。

* slot槽位映射算法
  * 哈希取余分区：客户端每次读写都是根据哈希取余计算哈希值，决定数据映射在哪个节点。但是不易扩容和缩容。
  * 一致性哈希算法分区：将整个哈希值空间看成一个圆，所有可能哈希值构成一个全量集。将集群中各个IP节点映射到哈希环上。存储key时，计算key的哈希值，将key使用相同的函数hash计算得到在这个环上的位置。该算法具有容错性、易扩容的特点。容易分布不均匀造成数据倾斜。
  * 哈希槽分区：实质上就是一个数组，长度就是哈希槽的长度16384.根据节点数据大致均等的将哈希槽映射到不同的节点。

##### 批处理操作问题

* 在不同 slot槽位下的键值无法使用mast、mget等多建操作，可以通过{}来定义通一个组的概念，使用key中{}内相同内容的键值对放到同一个slot槽位。

##### 集群配置

* 三主三从，各自新建 mkdir -p/myredis/cluster
* 在对应的主从redis中新建和编写rediscluster_port.conf
* 启动redis服务器
* 构建主从关系
  * redis-cli -a 123456 --cluster create --cluster-replicas 1 需要集群IP+port ...
  * --cluster-replicas 1 表示为每个主机创建一个从机

##### 集群常用命令

* info replication
* cluster nodes  //查看节点关系
* cluster info
* redis-cli -a 123456 --cluster add-node 需要加入的IP+port  //需要添加新的主机和从机到集群
* redis-cli -a 123456 --cluster reshard IP地址:端口号              //重新分配槽号
* redis-cli -a 123456 --cluster add-node IP:新从机端口 ip:新主机端口 --cluster -slave --cluster-master-id 新主机节点ID    //为主节点分配从节点
* redis-cli -a 123456 --cluster del-node IP:从机端口 从机节点ID

#### config配置文件

```java
//单机配置到主从配置文件
1. 开启daemonize yes     //后台运行
2. 注释掉bind 127.0.01   //去掉本地IP
3. protected-mode no    //关闭保护模式
4. prot 6379            //指定端口
5. dir /myredis         //指定当前工作目录
6. pidfile /var/run/redis_6379.pid //pid文件名字
7. logfile /myredis/mylog.log //日志文件
8. requirepass 123456   //设置密码
9. dblifename dump6379.rdb //设置rdb文件名
10. appendonly yes      //开启aof
11. appendfilename "appendonly.aof" //aof文件名
12. replicaof 主库IP 主库端口 //配置主从复制（只配从机，不配主机）

//集群配置配置文件
bind 0.0.0.0
daemonize yes
protected-mode no
port 6380
logfile "/myredis/cluster/cluster_6380.log"
pidfile /myredis/cluster6380.pid
dir /myredis/cluster
dbfilename dump6380.rdb
appendonly yes
appendfilename "appendonly6380.aof"
requirepass 123456
masterauth 123456
cluster-enabled yes
cluster-config-file rediscluster_6380.conf
cluster-node-timeout 5000
```

------

### KafKa

#### 介绍

* 消息中间件作为数据的缓冲区，降低系统之间的耦合性，增强扩展性。
* JMS（Java Message Service）Java对中间件的规范，类似于JDBC
* 支持PS模型（订阅-发布）、P2P模型（消息队列中的消息只能被消费一次）

#### 主题

* 创建主题：kafka-topics.bat --bootstrap-server localhost:9092 --topic topicName --create
* 查看主题列表：kafka-topics.bat --bootstrap-server localhost:9092 --list
* 详细查看主题信息：kafka-topics.bat --bootstrap-server localhost:9092 --topic topicName --describe
* 修改主题：kafka-topics.bat --bootstrap-server localhost:9092 --topic topicName --alter --partition 2
* 删除主题：kafka-topics.bat --bootstrap-server localhost:9092 --topic topicName --delete

#### 生产者消费者

* 命令操作：

  * 创建消费者：kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic topicName

  * 创建生产者：kafka-console-producer.bat --bootstrap-server localhost:9092 --topic topicName

* 生产者开发流程

  ```
  //导入kafka依赖
  //创建配置对象
  Map<String, Object> map = new Map<>();
  map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
  //对生产数据K，V进行序列化
  map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
  map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
  //创建生产者对象
  KafkaProducer<Object, Object> producer = new KafkaProducer<Object, Object>();
  //创建数据
  ProducerRecord<Sreing, String> recurd = new ProducerRecord<Sreing, String>("topicName", "key","value");
  //通过生产者对象将数据发送到kafka
  producer.send(recurd);
  //关闭生产者对象
  producer.close();
  ```

* 消费者开发流程

  ```
  //创建配置对象
  Map<String, Object> map = new Map<>();
  map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
  //对接收数据K，V进行反序列化
  map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
  map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
  map.put(ConsumerConfig.GROUP_ID_CONFIG, " ");
  //创建消费者对象
  KafkaConsumer<Object, Object> consumer = new KafkaConsumer<Object, Object>();
  //订阅主题
  consumer.subscribe(Collections.singletionList("topicName"));
  //从Kafka主题中获取数据
  final ConsumerRecords<Sreing, String> poll = consumer.poll(100);
  for(ConsumerRecords<Sreing, String> data : datas){
  	System.out.println(data);
  }
  //关闭生产者对象
  consumer.close();
  ```

------

### RocketMQ介绍

#### 介绍

RocketMQ是一款由阿里巴巴开源的分布式消息队列，它具有低延迟、高吞吐、高可用和高可靠性等特点，适用于构建具有海量消息堆积和异步解耦功能的应用系统。

##### 基本概念

* 生产者（Producer）：消息发布者，用于构建并传输消息到服务端的运行实体。
* 主题（Topic）：消息传输和存储的顶层容器，用于标识同一类业务逻辑的消息。Topic是一个逻辑概念，并不是实际的消息容器。
* 消息队列（MessageQueue）：消息存储和传输的实际容器，也是消息的最小存储单元。
* 消费者（Consumer）：消息订阅者，用于接收和处理消息的运行实体。
* 消费者组（ConsumerGroup）：承载多个消费行为一致的消费者负载均衡分组，和消费者不同。
* NameServer：注册中心，负责更新和发现Broker服务。在NameServer的集群中，NameServer与NameServer之间是没有任何通信的，是无状态的。
* Broker：消息中转角色，负责消息的存储和转发，接收生产者产生的消息并持久化；当用户发送的消息被发送到Broker时，Broker会将消息转发到与之关联的Topic中，以便让更多的接收者进行处理。

##### 消息模型

* 掌握

##### 部署模型

* 掌握



































