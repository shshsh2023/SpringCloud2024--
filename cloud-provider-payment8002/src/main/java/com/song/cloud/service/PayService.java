package com.song.cloud.service;

import com.song.cloud.entities.Pay;

import java.util.List;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/12 18:59
 */

public interface PayService {
    public int add(Pay pay);

    public int delete(Integer id);

    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();
}
