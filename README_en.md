<h3 align="center">SkyEyeSystem</h3>

  <p align="center">
     A web-wide hotspot crawler project based on Spring Boot
    <br />
    <a href="./README.md">中文</a>
    ·
    <a href="./README_en.md">English</a>
  </p>
<details open="open">
  <summary>directory</summary>
  <ol>
    <li>
      <a href="#AboutTheProject">AboutTheProject</a>
    </li>
    <li>
      <a href="#QuickStart">QuickStart</a>
      <ul>
        <li><a href="#Prerequisite">Prerequisite</a></li>
        <li><a href="#Installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#Use">Use</a></li>
    <li><a href="#Contribute">Contribute</a></li>
    <li><a href="#License">License</a></li>
    <li><a href="#Contact">Contact</a></li>
  </ol>
</details>

## AboutTheProject

![image-20230705153710250](https://images-1301128659.cos.ap-beijing.myqcloud.com/shaoxiongdu/202307051537338.png)

Every day at 3 p.m., regularly crawl the hot search data of the whole network. include

- Weibo hot search
- Station B hot search
- CSDN Hot Search
- Zhihu Hot Search
- Today's headlines
- Baidu Hot Search
- jueJing
- 36k
- QQNew
- ShaoShuPai

After crawling the data

1. The raw data will be stored in MySQL.
2. Conduct word frequency statistics and deposit them in Redis.

## QuickStart

Here's how to quickly use the project

### Prerequisites

Make sure your installer is Maven

### Installation

1. maven sync
2. Execute SQL script [SQL script] (src/main/resources/db/ddl.sql)
3. Configure your database address in application
4. db Configure the redis address in  [config](src/main/resources/config/redis.setting)

5. Just start.

## Use

#### 1. Perform crawler operations manually

execute [HotSpotCrawlerTest.java](src/test/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/BiliBiliCrawlerTest.java)

#### 2. Configure the execution time of the crawler

Modify the annotation value
in [crawl task](src/main/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/CrawlerTask.java)
Receives standard CRON parameters. It can be generated online
using [Cron Online Expression Builder](http://cron.ciding.cc/).

```java

public class CrawlerTask {
    
    @Scheduled(cron = "0 */10 9-23 * * *")
    public void crawl() {
        // ...
    }

}
```

#### 3. added platform implementation of crawler data

1. First, add a record of the response platform in the Platform Table hot_platform. Examples are as follows.

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


2. Add the corresponding platform class under [src/main/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/impl] and
   implement the
   interface [HotDataCrawler](src/main/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/HotDataCrawler.java)
   ``` java
   
   public class XXXCrawler implements HotDataCrawler {
       
      // the id in the platform table
       private static final Long PLATFORM_ID = ${platformId};
       
       private PlatformMapper platformMapper;
       
       @Override
       public List<HotSpot> crawlHotSpotData() {
           // Execute custom crawler logic The returned HotSpot list.
           return hotSpotList;
       }
       
       @Override
       public Platform getPlatform() {
           return platformMapper.selectById(PLATFORM_ID);
       }
   }
   
   ```
3. Implement the crawlHotSpotData method, execute custom data crawling logic, and encapsulate the crawled data as a
   HotSpot List and return.

4. Change the value of the constant PLATFORM_ID to the id in your corresponding platform table.

5. Add the implementation class to the Spring container. ( @Component/@Service )

6. Done. At this point, the scheduled task executes your crawling logic and puts it into storage. At the same time, the
   corresponding data will be displayed on the home page.

## Contribute

Contributions make the open source community a great place to learn, inspire, and create. Thank you very much for any
contribution.

1. Fork project

2. Create feature branches

3. Commit the changes

4. Push to branch

5. Open a pull request

## License

DISTRIBUTION UNDER THE MITT LICENSE, PLEASE FOLLOW THE RELEVANT OPEN SOURCE LICENSE: [MIT] (LICENSE)

## Contact

- ShaoxiongDu email@shaoxiongdu.cn
- WeChat: 15603430511
- Personal blog: https://shaoxiongdu.cn
