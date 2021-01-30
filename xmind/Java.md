# Java 

## JVM & GC

### JVM

- 内存模型

	- 程序计数器
（PC，Program Counter Register

		- 每个线程都有它自己的程序计数器
		- 当前方法

			- 任何时间一个线程都只有一个方法在执行
			- 存储当前线程正在执行的 Java 方法的 JVM 指令地址

				- 如果是在执行本地方法，则是未指定值（undefined）

	- Java 虚拟机栈（Java Virtual Machine Stack）

		- 栈帧（Stack Frame）

			- 内容

				- 局部变量表
				- 操作数（operand）栈
				- 动态链接
				- 方法正常退出或者异常退出的定义等

		- 当前栈帧

			- 只会有一个活动的栈帧
			- 操作

				- 压栈
				- 出栈

	- 堆（Heap）

		- Java 对象实例

			- new String("test")

		- 堆内存类型

			- 新生代

				- Eden 伊甸园

					- 第一代：刚 new 创建出来的对象

				- Suvivor(From)

					- 第二代：经历过上次 GC 后，被转过来存活的存活对象

				- Suvivor(To)

					- 第二代：本次 GC 时， 所有  Eden 和 From 中的存活对象都会被转至 To 区

			- 老年代

				- Tenured Gen

					- 经过几次 GC 后，仍然存活的对象，内存大小比较大，垃圾回收没有那么频繁

			- 世代的概念

				- 对象经历 GC 的次数

	- 方法区（Method Area）

		- 元（Meta）数据

			- 类结构信息
			- 运行时常量池、字段、方法代码等

		- 运行时常量池（Run-Time Constant Pool）

			- 版本号、字段、方法、超类、接口等

	- 本地方法栈（Native Method Stack）

		- 非 java 方法， C/C++

- OutOfMemory（OOM）问题

	- 堆内存不足

		- “java.lang.OutOfMemoryError:Java heap space”

			- 可能存在内存泄漏问题
			- 数据量大，但堆的大小不合理 或者 没有显式指定 JVM 堆大小
			- JVM 处理引用不及时，导致堆积起来，内存无法释放等

	- Java 虚拟机栈和本地方法栈

		- 递归调用，而且没有退出条件，导致不断地进行压栈

			- StackOverFlowError
			- OutOfMemoryError

	- JDK 8前

		- 永久代的大小是有限

			- 字符串常量池的字符串被存储在永久代中，因此可能导致一系列的性能问题和内存溢出错误
			- 调优过程非常困难，永久代的大小很难确，涉及到太多因素

				- 类的总数、常量池大小和方法数量等

		- java.lang.OutOfMemoryError: PermGen space”

	- JDK 8后

		- “java.lang.OutOfMemoryError: Metaspace”
		- 移除永久代

			- 把类的元数据直接保存在本地内存区域（堆外内存）

				- 可以避免永久代的内存溢出问题
				- 元空间的最大可分配空间就是系统可用内存空间
				- 不过需要监控内存的消耗情况，一旦发生内存泄漏，会占用大量的本地内存

	- 直接内存不足
	- 解决方案

		- 1. 打开 GC 日志

			- Heap Usage After GC ，每次 Full GC 后，内存都没有完全释放，导致有部分对象还存在内存中不能被  GC 清理。

				- 日积月累，会 OOM

					- 内存溢出

		- 增加 Heap Size

			- 不能盲目增加 JVM 的内存分配
			- 

		- 定时重启

			- 7*24小时的系统不好

		- 代码层面

			- 1. 压测4个时间点

				- 1. 开始前
				- 2. 进行中
				- 3. 结束前
				- 4. 结束后

			- 2. 工具分析
			- 3. 比较不同时间点的 Heap dump 截取出的对象

				- 看哪些一直没有释放

			- 4. 针对性地检查代码中不会释放对象的代码块

- JVM 调优

	- 关注

		- 内存占用（footprint）
		- 延时（latency）
		- 吞吐量（throughput）

	- 实际问题

		- 定位 Full GC 发生的原因

			- 1,首先通过 printGcDetail 查看 fullgc 频率以及时长
			- 2,通过dump 查看内存中哪些对象多，这些可能是引起fullgc的原因，看是否能优化
			- 3,如果堆大或者是生产环境，可以开起 jmc 飞行一段时间，查看这期间的相关数据来订位问题

		- 应用程序周期性卡顿

			- 垃圾回收时，Java 应用的其它线程都会挂起（除了垃圾收集帮助器）。全局暂停，即所有代码停止 ，这种机制叫 Stop-The-World(STW)
			- 解决方案

				- 1. 将转移到老年代的对象数量降到最少

					- GC 处理 mirnor GC 的时间比 full GC 效率高很多
					- 调整新生代空间大小

				- 2. 减少 Full GC 的执行时间

					- 不盲目把 Heap 或者 老年代调大，会使 full GC 的时间更长
					- 如果把老年代的空间值减少会导致 full GC 的时间缩短，但是会增加 full GC 的执行次数

				- 3. 减少内存消耗的对象

					- StringBuilder 替换  String
					- 少用 XML 或者 Json
					- 线程使用

	- 工具？？？？

		- 开发环境可以用 java visualvm查看线程执行情况
		- 用top命令查看Java线程的cpu利用率，
		- 用jstack来dump线程

- 常见参数

	- -Xms  初始Heap值 1024m
	- -Xmx 可申请的最大Heap值 1024m
	- -Xmn Java Heap Young区大小
	- -Xss

		- 每个线程的Stack大小

	- 辅助信息

		- -XX:+PrintGC
		- -XX:+PrintGCDetails

- JVM创建一个对象的步骤

	- (1) 给对象分配内存。
	- (2) 将对象的实例变量自动初始化为其类型的默认值。
	- (3) 初始化对象，给实例变量赋予正确的初始值。 

### GC

- 原理

	- 对象是通过引用使用的，如果不再有引用指向对象，就无从调用和处理（即不可达）

		- 哪些内存可以被释放

			- 堆上的 对象实例

				- Python 引用计数算法
				- Java 可达性分析

					- 对象及其引用关系看作一个图

			- 方法区中的元数据等信息

		- 垃圾回收，就是释放掉不可达对象所占据的内存

	- 只有对象的引用才有“作用域”

- GC分类

	- Serial GC

		- -XX:+UseSerialGC

	- ParNew GC
	- CMS（Concurrent Mark Sweep） GC
	- Parallel（并行）GC

		- server 模式 JVM 的默认 GC 选择
		- Java 8
		- 吞吐量优先
		- 新生代和老年代 GC 都是并行进行
		- -XX:+UseParallelGC

	- G1 GC（垃圾优先）

		- Java 9

			- 内存碎片的产生率大大降低
			- 设计初衷是为了尽量缩短处理超大堆（大于4GB）时产生的停顿

- 引用

	- 不同的引用类型，主要体现的是对象不同的可达性（reachable）状态和对垃圾收集的影响
	- 都是抽象类 java.lang.ref.Reference 的子类
	- 分类

		- 强引用（“Strong” Reference）

			- 对象“活着”，GC 不要碰我
			- Object obj = new Object();
			- 超过了引用的作用域
			- 显式地将相应（强）引用赋值为 null

		- 软引用（SoftReference）

			- 内存敏感的缓存

				- 保证了使用缓存的同时，不会耗尽内存

					- 图片缓存框架中，“内存缓存”中的图片是以这种引用来保存，使得JVM在发生OOM之前，可以回收这部分缓存

			- java.lang.ref.SoftReference
			- 只会等到内存不足的时候才会执行

		- 弱引用（WeakReference）

			- 非强制性的映射关系
			- 就算你不显式的把他置为null，垃圾回收也会立即执行
			- 检查弱引用指向对象是否被垃圾收集，也是诊断是否有特定内存泄漏的一个思路

				- 如果我们错误的保持了强引用（比如，赋值给了 static 变量），那么对象可能就没有机会变回类似弱引用的可达性状态了，就会产生内存泄漏

			- 应用场景

				- 在静态内部类中，经常会使用虚引用。例如，一个类发送网络请求，承担callback的静态内部类，则常以虚引用的方式来保存外部类(宿主类)的引用，当外部类需要被JVM回收时，不会因为网络请求没有及时回来，导致外部类不能被回收，引起内存泄漏

			- java.lang.ref.WeakReference

		- 幻象引用

			- Java 平台自身 Cleaner 机制
			- 利用幻象引用监控对象的创建和销毁?

				- get 永远返回 null
				- 相当于null

- 算法

	- 复制（Copying）算法

		- 为了克服句柄的开销和解决堆碎片的垃圾回收

	- 标记 - 清除（Mark-Sweep）算法

		- 不需要进行对象的移动，并且仅对不存活的对象进行处理，在存活对象比较多的情况下极为高效

	- 标记 - 整理（Mark-Compact）

		- 在标记-清除算法的基础上，又进行了对象的移动，因此成本更高，但是却解决了内存碎片的问题

- 回收阶段

	- YC
	- OC

		- 尽可能减少Full GC的次数

## 基础数据

### int和Integer的区别

- 应用场景

	- Integer

		- 容器里推荐用Integer。 对于PO实体类，如果db里int型字段允许null，则属性应定义为Integer。 当然，如果系统限定db里int字段不允许null值，则也可考虑将属性定义为int
		- Integer默认值是null，可以区分未赋值和值为0的情况。比如未参加考试的学生和考试成绩为0的学生

## Java 8

### 通过 :: 关键字访问类的构造方法，对象方法、静态方法

### GC/JVM

### 简化代码

- Stream 简化集合操作
- Optional 简化判空逻辑
- JDK 8 结合 Lambda 和 Stream 对各种类的增强

### 日期时间类

- 初始化日期时间
- “烦人”的时间问题

	- Date 类

- SimpleDataFomat 的问题

	- new SimpleDataFormat("YYYY-MM-DD")
	- 线程不安全

- 日期的计算

	- eg: 计算30天后的时间

- 遗留

	- Date
	- Calender

### lambda 表达式

- 好处

	- 简化匿名类和的语法

		- 分别使用匿名类 和 Lambda 表达式创建一个线程打印字符串

			- new Thread(new Runnable(){
public voie run(){
System.out.println("Hello World");
}
}).start();
			- new Thread(() -> System.out.println("Hello World!").start());

	- Lambda 表达式如何匹配 Java 的类型系统呢？

		- 函数式接口

			- @FunctionalInterface

				- 接口的 default 方法

			- 得到 Supplier 接口的实例

	- Debug 代码变得复杂？

- 坏处

	- 破坏封装性
	- 强x队友
	- 调试
	- 侵入

## IO

### 传统的 java.io 包

- 基于流模型实现
- 交互方式是同步、阻塞的方式

### NIO 2-AIO（Asynchronous IO）

- Java 7
- 组成部分

	- Buffer，高效的数据容器
	- Channel，类似在 Linux 之类操作系统上看到的文件描述符

		- 更加操作系统底层的一种抽象

	- File 或者 Socket，通常被认为是比较高层次的抽象
	- Selector，是 NIO 实现多路复用的基础

		- 实现了单线程对多 Channel 的高效管理
		- 检测到注册在 Selector 上的多个 Channel 中，是否有 Channel 处于就绪状态

	- Chartset，提供 Unicode 字符串定义

		- 提供了相应的编解码器

			- 字符串到 ByteBuffer 的转换

				- Charset.defaultCharset().encode("Hello world!"));

- 能解决什么问题

## 集合框架

### Map

- HashTable

	- 不支持 null 键和值

- HashMap

	- 性能

		- 表现非常依赖于哈希码的有效性

			- hashCode 和 equals 的一些基本约定

				- equals 相等，hashCode 一定要相等
				- 重写了 hashCode 也要重写 equals
				- hashCode 需要保持一致性，状态改变返回的哈希值仍然要一致
				- equals 的对称、反射、传递等特性

		- 常数级的 get()、put()

	- 非线程安全
	- 结构

		- 数组（Node[] table）和链表结合组成的复合结构

			- 哈希值相同的键值对，则以链表形式存储

		- 扩容

			- resize

				- 门限值 = （负载因子）* （容量）
				- 倍数进行调整
				- 性能开销

					- 将老的数组中的元素重新放置到新的数组

		- 哈希思想

			- 当我们将键值对传递给put()方法时，它调用键对象的hashCode()方法来计算hashcode，让后找到bucket位置来储存值对象
			- 当获取对象时，通过键对象的equals()方法找到正确的键值对，然后返回值对象
			- 使用链表来解决碰撞问题，当发生碰撞了，对象将会储存在链表的下一个节点中

	- HashMap 源码分析

		- HashMap 内部实现基本点分析
		- 容量（capacity）和负载系数（load factor）

			-  负载因子 * 容量 > 元素数量

				- 容量 大于“预估元素数量 / 负载因子”，同时它是 2 的幂数
				- 默认的情况下，HashMap的容量是16

		- 树化

			- 为什么 HashMap 要树化呢？

				- 在元素放置过程中，如果一个对象哈希冲突，都被放置到同一个桶里，则会形成一个链表，我们知道链表查询是线性的，会严重影响存取的性能
				- 本质上这是个性能-安全问题

			- 使用红黑树
			- 解决哈希冲突的常用方法

				- 开放定址法
				- 再哈希法
				- 链地址法
				- 建立公共溢出区

			- 如果链表大小超过阈值（TREEIFY_THRESHOLD, 8），链表就会被改造为树形结构

	- LinkedHashMap

		- 双向链表头结点header 
		- 标志位accessOrder

- TreeMap

	- 构建一个具有优先级的调度系统

		- 优先队列

			- 基于二叉堆实现的 PriorityQueue

	- 工程应用

		- Fisp中登要求报文有 head-body-tail 的要求

	- 红黑树顺序访问

		- （树中的每个节点的值，都会大于或等于它的左子树种的所有节点的值，并且小于或等于它的右子树中的所有节点的值
		- 实现了SortMap<K,V>接口，对key进行了排序
		- 具体顺序可以由指定的 Comparator 来决定

	- O(log N)

### 集合

- 类图

	- Vector

		- 线性安全的动态数组

			- 扩容时会提高 1 倍

		- 
void addIfNotExist(Vector v, 
    Object o){
  if(!v.contains(o)) {
    v.add(o);
  }
}

			- Vector实现线程安全是通过给主要的写方法加了synchronized，类似contains这样的读方法并没有synchronized，该题的问题就出在不是线程安全的contains方法，两个线程如果同时执行到if(!v.contains(o)) 是可以都通过的，这时就会执行两次add方法，重复添加

	- LinkedList

		- 双向链表

			- 扩容增加 50%

		- 既是 List，也是 Deque
		- 重写HashMap 的迭代器
		- LRU

	- ArrayList<E> 

		- 动态数组
		- extends AbstractList<E> implements List<E>, RandomAccess， Cloneable, java.io.Serializable

	- Collection<E>

		- List<E> extends Collection<E>
		- Set

			- TreeSet
			- HashSet
			- LinkedHashSet

		- Queue/Deque

			- Java 提供的标准队列结构的实现，除了集合的基本功能，它还支持类似先入先出（FIFO， First-in-First-Out）或者后入先出（LIFO，Last-In-First-Out）

- 排序算法

	- 稳定性
	- 分类
	- 最好、最差的情况
	- 理解 Java 提供的默认排序算法，具体是什么排序方式以及设计思路等;   eg: Arrays.sort()

		- 多大的数据集（太小的数据集，复杂排序是没必要的，Java 会直接进行二分插入排序）
		- 什么数据类型

			- 原始数据类型 eg:int

				- 双轴快速排序

			- 对象数据类型

				- TimSort

					- 归并和二分插入排序（binarySort）结合的优化排序算法
					- 查找数据集中已经排好序的分区（这里叫 run），然后合并这些分区来达到排序的目的

		- Java 8 并行排序算法（直接使用 parallelSort 方法）

			- 底层实现基于 fork-join 框架

- Java 8 允许接口实现默认方法

	- Lambda 和 Stream

### 如何保证容器是线程安全的？

- 并发包 java.util.concurrent

	- 并发容器

		- ConcurrentHashMap

			- 早期 ConcurrentHashMap

				- 利用分段设计，在进行并发操作的时候，只需要锁定相应段，这样就有效避免了类似 Hashtable 整体同步的问题，大大提高了性能
				- 将内部进行分段（Segment），里面则是 HashEntry 的数组
				- put

					- 通过二次哈希避免哈希冲突
					- 以 Unsafe 调用方式，直接获取相应的 Segment，然后进行线程安全的 put 操作

			- Java 8

				- 桶(bucket)数组 + 链表结构（bin）
				- Segment

					- 有定义，但不再使用 
					- 仅仅是为了保证序列化时的兼容性而已
					- 不再有任何结构上的用处
					- 初始化操作大大简化，修改为 lazy-load 形式，这样可以有效避免初始开销

				- 数据存储

					- Key 是 final 的

						- 在生命周期中，一个条目的 Key 发生变化是不可能的

					- val 利用 volatile 来保证可见性
					- put

				- CAS 等操作，在特定场景进行无锁并发操作（CAS无锁算法）

					- 乐观锁

						- 如果符合预期才给予执行，对并发操作提供良好的优化

				- 使用 Unsafe/LongAdder 之类的底层手段，进行极端情况的优化
				- 保证线程安全

					- 1. 使用volatile保证当Node中的值变化时对于其他线程是可见的
					- 2. 使用table数组的头结点作为synchronized的锁来保证写操作的安全

						- 锁的颗粒度，是加在链表头上的

					- 3. 当头结点为null时，使用CAS(乐观锁)操作来保证数据能正确的写入

			- 基于分离锁实现

		- CopyOnWriteArrayList

	- 线程安全队列

		- Queue/Deque

			- ArrayBlockingQueue
			- SynchronousQueue

	- 有序容器的线程安全版本

- 传统集合框架内部

	- HashTable 为什么低效？

		- 将 put/get/size 等各种方法加上 “synchronized”

## Object

### 方法

- getClass()
- clone()
- equals()
- int hashCode()
- notify()
- notifyAll()
- toString()
- wait()
- wait(long timeout)

## 异常处理

### 异常被当做对象来处理

- 分类(Object)

	- Error

		- 举例

			- OutOfMemory(OOM)  Error

				- JVM会选择终止程

		- 不需要关心

	- Exception

		- checked exception
检查型异常

			- java编译器强制程序员必须进行捕获处理

				- IOExeption
				- SQLException

			- 如果不进行捕获或者抛出声明处理，编译都不会通过

		- unchecked exception
非检查型异常

			- RuntimeException

				- 举例

					- NullPointerException
					- IndexOutOfBoundsException

			- java编译器不要求必须进行异常捕获处理或者抛出声明，由程序员自行决定

		- 都是在运行期间发生的

- 异常处理设计

*XMind - Trial Version*