## 项目概况

### 优惠券管理系统

完成一期、二期开发，代码重构后未测试

### 优惠券分发系统

完成一期、二期开发，未测试

### 优惠券推荐系统

完成提供后台人员设定

未完成：定时根据 coupon 表存储的优惠券信息，动态分析出与消费者的职业、爱好的关联度，
并将这个关系存储在 interest 表中。

### 用户模拟系统

完成分发系统接口测试方法的编写，未测试

二期需求未完成

## 数据表

### coupon

该表用于存储优惠券的清单信息，**不代表实际发放在用户手中的每一张优惠券**

- (PK) business 优惠券所属业务
- (PK) name 优惠券名称
- type 优惠券种类，用于表示该券是折扣券还是抵用券
- value 优惠券的折扣值，如果是折扣券则表示折扣力度（例如 0.8 表示八折），如果是抵用券则表示可抵用力度
- limit_value 使用优惠券所需要达到的消费金额
- remain 优惠券的剩余数量
- total 优惠券的所有数量
- start 优惠券开始生效的日期
- end 优惠券不再生效的日期
- usable_cate 用每一个比特位表示优惠券可用于的商品门类

#### CouponDto 

去除了 remain 和 total 两个属性的 coupon

### staff

该表用于存储后台管理员工的信息，**不是消费者用户信息**

- (PK) name 员工姓名
- business 员工所属业务部门
- auth 员工权限等级
- pwd 登录密码

#### StaffDto

去除了 pwd 的 staff

### xxx_request 

该表用于记录对对应数据表做出改动且需要批准的申请（如 coupon_request 对应表为 coupon）

- (PK) id 申请 ID
- category 请求的操作类别，如创建、增量、删除等
- initiate 请求发起的日期
- (FK@staff.name) initiator 请求发起人
- rejected 记录该请求是否被拒绝过
- approval 需要等待的审批队列，每一个字节表示需要等待下一个审批者的权限等级
- 剩下的属性为执行修改操作所需要的清单信息，详细见对应表的属性

#### StaffRequestDto

去除了 pwd 属性的 StaffRequest

### interest

该表用于记录优惠券与职业和爱好之间的关联程度

- (PK) id
- hobby 爱好
- job 职业
- (FK@coupon.business) business 优惠券所属业务
- (FK@coupon.name) name 优惠券名称
- relation 关联程度，该值越高表示具有该职业与爱好的用户更有可能喜欢对应的优惠券
