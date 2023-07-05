<h3 align="center">SkyEyeSystem</h3>

  <p align="center">
     一个基于SpringBoot的全网热点爬虫项目
    <br />
    <a href="./README.md">中文</a>
    ·
    <a href="./README_en.md">English</a>
  </p>
<details open="open">
  <summary>目录</summary>
  <ol>
    <li>
      <a href="#关于项目">关于项目</a>
    </li>
    <li>
      <a href="#快速启动">快速启动</a>
      <ul>
        <li><a href="#先决条件">先决条件</a></li>
        <li><a href="#安装">安装</a></li>
      </ul>
    </li>
    <li><a href="#使用">使用</a></li>
    <li><a href="#贡献">贡献</a></li>
    <li><a href="#许可证">许可证</a></li>
    <li><a href="#联系">联系</a></li>
  </ol>
</details>

## 关于项目

![image-20230705153710250](https://images-1301128659.cos.ap-beijing.myqcloud.com/shaoxiongdu/202307051537338.png)

每天下午三点定时爬取全网热搜数据。包括

- 微博热搜
- B站热搜
- CSDN热搜
- 知乎热搜
- 今日头条
- 百度热搜
- 掘金
- 36氪
- 腾讯新闻
- 少数派

爬取数据之后

1. 会将原始数据存入MySQL。
2. 进行词频统计 存入Redis。

## 快速启动

此处说明了如何快速的使用本项目

### 先决条件

确保您的安装器是Maven

### 安装

1. maven sync
2. 初始化数据库 [SQL脚本](src/main/resources/db/ddl.sql)
3. application中配置您的数据库地址
4. [config](src/main/resources/config/redis.setting)中配置redis地址。
5. 启动即可。

## 使用

#### 1. 手动执行爬虫操作

发送get请求 /api/hotspot/crawler即可。

#### 2. 配置爬虫的执行时间

修改[爬虫任务](src/main/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/CrawlerTask.java)中的注解值即可。
接收标准的CRON参数。 可使用[Cron在线表达式生成器](http://cron.ciding.cc/) 在线生成

```java

public class CrawlerTask {

   @Scheduled(cron = "0 */10 9-23 * * *") // 每天的 9 点到 23 点之间，每隔十分钟执行一次任务。
   public void crawl() {
      // ...
   }

}
```

#### 3. 新增爬虫数据的平台实现

1. 首先在 平台表 hot_platform 中新增对应对平台记录。举例如下。

   ``` sql
   INSERT INTO sky_eye_system.hot_platform 
   VALUES (2, 
           '微博',
           'https://ts3.cn.mm.bing.net/th?id=ODLS.05d45f55-2151-4d66-83e5-d10018607094&w=32&h=32&qlt=90&pcl=fffffa&o=6&pid=1.2',
           '随时随地发现新鲜事！微博带你欣赏世界上每一个精彩瞬间，了解每一个幕后故事。分享你想表达的，让全世界都能听到你的心声！',
           'https://weibo.com', 
           '随时随地发现新鲜事！', 
           '王志东', 
           null, 
           null, 
           0);
   ```

2. 在 [src/main/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/impl]
   下新增对应的平台类，并实现接口 [HotDataCrawler](src/main/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/HotDataCrawler.java)

   ``` java
   
   public class XXXCrawler implements HotDataCrawler {
       
      // 平台表中的id 
       private static final Long PLATFORM_ID = ${platformId};
       
       private PlatformMapper platformMapper;
       
       @Override
       public List<HotSpot> crawlHotSpotData() {
           // 执行自定义爬虫逻辑 返回的HotSpot列表。
           return hotSpotList;
       }
       
       @Override
       public Platform getPlatform() {
           return platformMapper.selectById(PLATFORM_ID);
       }
   }
   
   ```

3. 实现crawlHotSpotData方法，执行自定义的数据爬取逻辑，将爬取的数据封装为HotSpot的List并返回。

4. 将常量PLATFORM_ID的值改为您的对应的平台表中的id。

5. 将实现类添加到Spring容器中。( @Component/@Service )

6. 完成。此时，定时任务会执行您的爬取逻辑并入库。同时首页会展示相对应的数据。

## 贡献

贡献使开源社区成为一个学习、激励和创造的绝佳场所。非常感谢您所做的任何贡献。

1.fork项目

2.创建功能分支

3.提交更改

4.推送至分支

5.打开拉取请求

## 许可证

基于MIT的许可证分发，传输请遵循相关开源协议: [MIT许可证](LICENSE )

## 联系

- 杜少雄 email@shaoxiongdu.cn
- 微信: 15603430511
- 个人博客: https://shaoxiongdu.cn
