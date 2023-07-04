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

每天下午三点定时爬取全网热搜数据。包括

- 微博热搜
- B站热搜
- CSDN热搜
- 知乎热搜
- 今日头条
- 百度热搜

爬取数据之后

1. 会将原始数据存入MySQL。
2. 进行词频统计 存入Redis。

## 快速启动

此处说明了如何快速的使用本项目

### 先决条件

确保您的安装器是Maven

### 安装

1. maven sync
2. 执行SQL脚本 [SQL脚本](src/main/resources/db/ddl.sql)
3. application中配置您的数据库地址
4. db/config中配置redis地址。
5. 启动即可。

## 使用

#### 1. 手动执行爬虫操作

执行[HotSpotCrawlerTest.java](src/test/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/BiliBiliCrawlerTest.java)

#### 2. 配置爬虫的执行时间

修改[爬虫任务](src/main/java/cn/shoxiongdu/SkyEyeSystem/task/hotspot/crawl/CrawlerTask.java)中的注解值即可。
接收标准的CRON参数。 可使用[Cron在线表达式生成器](http://cron.ciding.cc/) 在线生成

```java
@Scheduled(cron = "0 0 15 * * ?") // 每天下午3点执行
public void crawl(){
        ...
        }
```

## 贡献

贡献使开源社区成为一个学习、激励和创造的绝佳场所。非常感谢您所做的任何贡献。

1.fork项目

2.创建功能分支（`git checkout-b Feature/master`）

3.提交更改（`git-Commit-m'添加一些AmazingFeature`）

4.推送至分支（`git Push origin feature/master`）

5.打开拉取请求

## 许可证

基于MIT的许可证分发，传输请遵循相关开源协议: [MIT许可证](LICENSE )

## 联系

- 杜少雄 email@shaoxiongdu.cn
- 微信: 15603430511
- 个人博客: https://shaoxiongdu.cn
