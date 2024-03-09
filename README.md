# OneCardService

校园一卡通后端服务。

封装了一个 `DataBase` 类，创建的实体类只要实现了 `Serializable` 和 `Identifiable` 接口即可调用，注意配置数据库名称。

实现了加密和解密功能，使用者可以在 `security.Encryption` 中编写两个函数即可。

### 后端接受请求格式
* 运行端口和数据库名称可以在 `ServiceConfige` 类中配置，其中`DEFAULT_USER_DATABASE_PATH` 先让它为空。
* `recharge user_id amount`：表示向服务器发送一个充值请求，参数为 `user_id` 和充值金额 `amount`。
* `consume user_id amount`：表示向服务器发送一个消费请求，参数为 `user_id` 和消费金额 `amount`。
* 请求失败会返回空的 `Result` 对象，错误码 `500`;
