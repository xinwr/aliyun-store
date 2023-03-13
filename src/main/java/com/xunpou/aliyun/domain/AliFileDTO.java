package com.xunpou.aliyun.domain;

import lombok.Data;



/**
 * 阿里云盘文件对象
 */
@Data
public class AliFileDTO {
    private String name;
    private String fileId;
    private String url;
}
