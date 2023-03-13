
import com.xunpou.aliyun.StoreBucket;
import com.xunpou.aliyun.StoreClient;
import com.xunpou.aliyun.domain.AliFileDTO;

import java.util.List;

/**
 * @email 22615732@qq.com
 */
public class example {
    public static void main(String[] args) throws Exception{
        /**
         * 实现列举阿里云盘的文件列表
         */

        //创建阿里云盘Bucket
        StoreBucket storeBucket = new StoreBucket("refreshToken","driveId","rootId");

        //创建操作端
        StoreClient storeClient = new StoreClient(storeBucket);

        //列举根目录下的文件
        List<AliFileDTO> list = storeClient.listAllObject(storeBucket, "rootId");


    }
}
