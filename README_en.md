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

Every day at 3 p.m., regularly crawl the hot search data of the whole network. include

- Weibo hot search
- Station B hot search
- CSDN Hot Search
- Zhihu Hot Search
- Today's headlines
- Baidu Hot Search

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
   4.db Configure the redis address in /config.
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
@Scheduled(cron = "0 0 15 * * ?") // Performed daily at 3 pm
public void crawl(){
        ...

        }
```

## Contribute

Contributions make the open source community a great place to learn, inspire, and create. Thank you very much for any
contribution.

1. Fork project

2.Create feature branches ('git checkout-b Feature/AamazingFeature')

3. Commit the changes ('git-commit-m' to add some AmazingFeatures')

4. Push to branch ('git Push origin feature/AamazingFeature')

5. Open a pull request

## License

DISTRIBUTION UNDER THE MITT LICENSE, PLEASE FOLLOW THE RELEVANT OPEN SOURCE LICENSE: [MIT] (LICENSE)

## Contact

- ShaoxiongDu email@shaoxiongdu.cn
- WeChat: 15603430511
- Personal blog: https://shaoxiongdu.cn