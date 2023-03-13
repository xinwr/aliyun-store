package com.xunpou.aliyun.domain;

import lombok.Data;

/**
 * 文件上传对象
 */
@Data
public class UploadPartInfo {
    private String internalUploadUrl;
    private String uploadUrl;
    private int partNumber;
}
