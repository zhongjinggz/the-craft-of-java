# 演出账单问题

## 业务场景

- 有一个话剧团，会到客户（customer）现场演出
- 剧团根据观众（audience）人数以及剧目（play）类型向客户收费
- 目前有两种剧目类型：悲剧（tragedy）和喜剧（comedy）
- 结算时，还会根据观众数量给出积分（audience points）以便下一次为客户提供优惠。

题目给出了一个已经完成的程序，用于根据演出情况（performance summary）计算结算单（performance invoice）存入数据库，并将其返回给客户。

请完成以下任务：

## 任务1
- 为程序添加层次结构，建议按照DDD分层架构：domain, application, drivingadapter, drivenadapter

## 任务2
- 用策略模式重构结算金额和积分的逻辑	

## 任务3
- 识别其他坏味道并重构

## 任务4
- 在重构过程中添加必要的单元测试
