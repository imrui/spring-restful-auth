package com.xxicon.listener;

import com.xxicon.context.Context;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class ContextClosedHandler implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
//        Context.getContext().getXXXX().stop();
        // todo: 系统停止时需要处理的逻辑
    }

}
