package com.song.cloud.service.impl;

import com.song.cloud.mapper.StorageMapper;
import com.song.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 19:50
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("--------------------> storage service 开始扣减库存");
        storageMapper.decrease(productId, count);
        log.info("--------------------> storage service 结束扣减库存");
    }
}
