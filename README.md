# aliyun-store
阿里云盘爬虫工具，实现向阿里云oss那样操作阿里云盘。实现文件上传，查询，删除等功能

### 1.最简单的例子：

        //创建阿里云盘Bucket
        StoreBucket storeBucket = new StoreBucket("refreshToken","driveId","rootId");

        //创建操作端
        StoreClient storeClient = new StoreClient(storeBucket);

        //列举根目录下的文件
        List<AliFileDTO> list = storeClient.listAllObject(storeBucket, "rootId");

更多详情方法请看com.xunpou.aliyun.StoreClient类

### 2.获取refreshToken和driveId的方法：
成功登录网页版阿里云盘后，打开控制台输入
window.localStorage.getItem("token"); <br>
然后回车即可返回一串json数据，然后找到相应字段即可（refreshToken和driveId长期有效，只需要手动获取一次即可）