package com.song.cloud.service.impl;

import com.song.cloud.apis.AccountFeignApi;
import com.song.cloud.apis.StorageFeignApi;
import com.song.cloud.entities.Order;
import com.song.cloud.mapper.OrderMapper;
import com.song.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author song
 * @version 0.0.1
 * @date 2024/4/28 18:46
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource //订单微服务通过openFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;
    @Resource //订单微服务通过0penFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "song-create-order", rollbackFor = Exception.class)
    public void create(Order order) {

        //xid全局事务id的检查，重要
        String xid = RootContext.getXID();
        // 1. 新建订单
        log.info("------------------------开始新建订单：" + "\t" + "xid: " + xid);
        order.setStatus(0);
        int i = orderMapper.insertSelective(order);

        Order orderFromDB = null;

        if (i > 0) {
            //重数据库查出来，存在则插入成功
            orderFromDB = orderMapper.selectOne(order);
            log.info("----> 新建订单成功, orderFromDB info: " + orderFromDB);
            // 2. 减库存
            log.info("----> 订单微服务开始调用库存微服务, 进行减库存");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("----> 订单微服结束调用库存微服务, 完成减库存");
            System.out.println();
            // 3. 扣账户余额
            log.info("----> 订单微服务开始调用Account账号, 进行扣账户余额");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("----> 订单微服结束调用Account账号, 完成扣账户余额");
            System.out.println();
            // 4. 修改订单状态
            //将订单状态从0修改到1， 表示已完成
            log.info("----> 修改订单状态");
            orderFromDB.setStatus(1);

            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);
            log.info("---------------------> 修改订单状态完成" + "\t" + updateResult);
            log.info("---------------------> orderFromDB info: " + orderFromDB);
        }
        System.out.println();
        log.info("------------------------------结束新建订单： " + "\t" + "xid: " + xid);
    }
}
