1.service层将会直接于数据库交互直接返回所需数据，而controller是作为前端控制器返回rspData，所以需要分离方法（改造service，不是接口层,而是接口及其后端实现层）;DTO，final格式数据可序列化的数据类
2.添加问卷发布前可更改功能,配合3一起
3.增加redis缓存，追踪问卷填写状态（应填写人，已填写人，未填写人），问卷需要添加填写时间的限制，配置到cache上
4.添加每2h的提醒功能（quartz或者redis key超时功能） https://cloud.tencent.com/developer/article/1664682
5.设计一个合理的时限缓存系统(合理的生命周期，何时加入消失更新）
6。上述3-5添加到实验报告中，并且需要redis的安装过程
7.分布式session调研报告

完成进度：12