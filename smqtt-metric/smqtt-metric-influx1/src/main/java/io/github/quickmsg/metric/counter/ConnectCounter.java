package io.github.quickmsg.metric.counter;


import io.github.quickmsg.common.metric.MetricCounter;
import io.github.quickmsg.metric.Influx1Metric;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接计数器
 *
 * @author easy
 * @date 2021/11/12
 */
public class ConnectCounter implements MetricCounter {

    public static final ConnectCounter CONNECT_SIZE_COUNTER_INSTANCE = new ConnectCounter();

    private static final AtomicInteger ATOMICINTEGER = new AtomicInteger(0);

    public static final String NAME = "smqtt.connect.count";

    static {
        Influx1Metric.INFLUX_METER_REGISTRY_INSTANCE.gauge(NAME, ATOMICINTEGER);
    }

    public void increment() {
        Influx1Metric.INFLUX_METER_REGISTRY_INSTANCE.gauge(NAME, ATOMICINTEGER.incrementAndGet());
    }

    public void decrement() {
        Influx1Metric.INFLUX_METER_REGISTRY_INSTANCE.gauge(NAME, ATOMICINTEGER.decrementAndGet());
    }

}