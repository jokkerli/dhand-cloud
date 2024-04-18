package com.jokeer.dhand.admin.listener.rabbitmq;

import com.youlai.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Canal + RabbitMQ 监听数据库数据变化
 *
 * @author haoxr
 * @since 2021/11/4 23:14
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CanalListener {

    private final SysMenuService menuService;

    //@RabbitListener(queues = "canal.queue")
    public void handleDataChange() {

    }
}
