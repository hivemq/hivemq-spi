package com.hivemq.spi.metrics;

import com.codahale.metrics.*;
import com.hivemq.spi.callback.events.*;
import com.hivemq.spi.callback.lowlevel.*;
import com.hivemq.spi.callback.security.*;
import com.hivemq.spi.services.PluginExecutorService;

/**
 * This class holds a constant {@link HiveMQMetric} for every metric which is provided by HiveMQ
 *
 * @author Christoph Schäbel
 */
public class HiveMQMetrics {

    public static final String PLUGIN_EXECUTOR_PREFIX = "com.hivemq.plugin.executor";
    public static final String CALLBACK_EXECUTOR_PREFIX = "com.hivemq.callback.executor";
    public static final String EXCEPTION_PREFIX = "com.hivemq.exceptions";
    public static final String WEBINTERFACE_EXECUTOR_PREFIX = "com.hivemq.web-ui.executor";

    @Deprecated
    public static final String SINGLE_WRITER_PREFIX = "com.hivemq.persistence.executor";

    /**
     * represents a {@link Counter}, which counts every incoming MQTT message
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_MESSAGE_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.total.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT message
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_MESSAGE_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.total.count", Counter.class);

    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_MESSAGE_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.total.rate", Meter.class);


    /**
     * represents a {@link Meter},which measures the current rate of outgoing MQTT messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_MESSAGE_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.total.rate", Meter.class);


    /**
     * represents a {@link Histogram}, which measures the distribution of incoming MQTT message size (payload without fixed header)
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Histogram> INCOMING_MESSAGE_SIZE_MEAN =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.total.bytes", Histogram.class);

    /**
     * represents a {@link Histogram}, which measures the distribution of incoming MQTT message size (payload without fixed header)
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Histogram> OUTGOING_MESSAGE_SIZE_MEAN =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.total.bytes", Histogram.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT CONNECT messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_CONNECT_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.connect.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT CONNECT messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_CONNECT_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.connect.count", Counter.class);


    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT CONNACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_CONNACK_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.connack.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT CONNACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_CONNACK_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.connack.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT PUBLISH messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_PUBLISH_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.publish.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT PUBLISH messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_PUBLISH_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.publish.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT PUBLISH messages with the retained flag set
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Counter> INCOMING_PUBLISH_RETAINED_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.publish.retained.count", Counter.class);

    /**
     * represents a {@link Histogram}, which measures the distribution of incoming MQTT message size (payload without fixed header)
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Histogram> INCOMING_PUBLISH_SIZE_MEAN =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.publish.bytes", Histogram.class);

    /**
     * represents a {@link Histogram}, which measures the distribution of incoming MQTT message size (payload without fixed header)
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Histogram> OUTGOING_PUBLISH_SIZE_MEAN =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.publish.bytes", Histogram.class);

    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT PUBLISH messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_PUBLISH_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.publish.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT PUBLISH messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_PUBLISH_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.publish.count", Counter.class);


    /**
     * represents a {@link Counter}, which counts every outgoing MQTT PUBLISH messages with the retained flag set
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Counter> OUTGOING_PUBLISH_RETAINED_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.publish.retained.count", Counter.class);

    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT DISCONNECT messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_DISCONNECT_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.disconnect.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT DISCONNECT messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_DISCONNECT_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.disconnect.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT PINGREQ messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_PINGREQ_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pingreq.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT PINGREQ messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_PINGREQ_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pingreq.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of outgoing MQTT PINGRESP messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_PINGRESP_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pingresp.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT PINGRESP messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_PINGRESP_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pingresp.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT PUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_PUBACK_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.puback.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT PUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_PUBACK_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.puback.count", Counter.class);

    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT PUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_PUBACK_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.puback.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT PUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_PUBACK_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.puback.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT PUBCOMP messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_PUBCOMP_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pubcomp.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT PUBCOMP messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_PUBCOMP_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pubcomp.count", Counter.class);

    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT PUBCOMP messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_PUBCOMP_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pubcomp.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT PUBCOMP messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_PUBCOMP_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pubcomp.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT PUBREC messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_PUBREC_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pubrec.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT PUBREC messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_PUBREC_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pubrec.count", Counter.class);

    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT PUBREC messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_PUBREC_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pubrec.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT PUBREC messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_PUBREC_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pubrec.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT PUBREL messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_PUBREL_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pubrel.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT PUBREL messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_PUBREL_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.pubrel.count", Counter.class);

    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT PUBREL messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_PUBREL_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pubrel.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT PUBREL messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_PUBREL_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.pubrel.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT SUBSCRIBE messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_SUBSCRIBE_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.subscribe.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT SUBSCRIBE messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_SUBSCRIBE_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.subscribe.count", Counter.class);

    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT SUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_SUBACK_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.suback.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every outgoing MQTT SUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_SUBACK_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.suback.count", Counter.class);


    /**
     * represents a {@link Meter},which measures the current rate of incoming MQTT UNSUBSCRIBE messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> INCOMING_UNSUBSCRIBE_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.unsubscribe.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every incoming MQTT UNSUBSCRIBE messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> INCOMING_UNSUBSCRIBE_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.incoming.unsubscribe.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts the amount of clients with a message queue, that is at least half-full.
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> HALF_FULL_QUEUE_COUNT =
            HiveMQMetric.valueOf("com.hivemq.clients.half-full-queue.count", Counter.class);

    /**
     * represents a {@link Meter},which measures the current rate of dropped PUBLISH messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> DROPPED_MESSAGE_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.rate", Meter.class);

    /**
     * represents a {@link Counter}, which counts every dropped PUBLISH messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.count", Counter.class);


    /**
     * represents a {@link Counter}, which counts the PUBLISH messages that have been dropped, because a clean session client disconnected before the message could be sent
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_NOT_CONNECTED_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.not-connected.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts PUBLISH messages that have been dropped, because the message queue for a disconnected persistent session client was full
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_QUEUE_FULL_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.queue-full.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts PUBLISH messages that have been dropped, because the in-flight message queue for the client was full
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_IN_FLIGHT_WINDOW_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.in-flight-window.count", Counter.class);


    /**
     * represents a {@link Counter}, which counts PUBLISH messages that have been dropped, because the socket for the client was not writable (only QoS 0)
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_NOT_WRITABLE_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.not-writable.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts PUBLISH messages that have been dropped, because there QoS was 0 and the client session queue was not yet empty
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_QUEUE_NOT_EMPTY =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.qos-0-queue-not-empty.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts PUBLISH messages that have been dropped, because of an internal error
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_INTERNAL_ERROR =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.internal-error.count", Counter.class);

    /**
     * represents a {@link Counter}, which counts PUBLISH messages that have been dropped, because an exception was thrown in a {@link BeforePublishSendCallback}
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> DROPPED_MESSAGE_BEFORE_PUBLISH_SEND_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.dropped.before-publish-send.count", Counter.class);

    /**
     * represents a {@link Meter}, which measures the current rate of outgoing MQTT UNSUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> OUTGOING_UNSUBACK_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.unsuback.rate", Meter.class);

    /**
     * represents a {@link Gauge}, which holds the current amount of retained messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Gauge<Number>> RETAINED_MESSAGES_CURRENT =
            HiveMQMetric.gaugeValue("com.hivemq.messages.retained.current");

    /**
     * represents a {@link Histogram}, which holds metrics about the mean payload-size of retained messages in bytes
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Histogram> RETAINED_MESSAGES_MEAN =
            HiveMQMetric.valueOf("com.hivemq.messages.retained.mean", Histogram.class);

    /**
     * represents a {@link Histogram}, which holds the current rate of retained messages
     *
     * @since 3.1
     */
    public static final HiveMQMetric<Meter> RETAINED_MESSAGES_RATE =
            HiveMQMetric.valueOf("com.hivemq.messages.retained.rate", Meter.class);


    /**
     * represents a {@link Counter}, which counts every outgoing MQTT UNSUBACK messages
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> OUTGOING_UNSUBACK_COUNT =
            HiveMQMetric.valueOf("com.hivemq.messages.outgoing.unsuback.count", Counter.class);


    /**
     * represents a {@link Gauge}, which holds the current (last 5 seconds) amount of read bytes
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Gauge<Number>> BYTES_READ_CURRENT =
            HiveMQMetric.gaugeValue("com.hivemq.networking.bytes.read.current");


    /**
     * represents a {@link Gauge}, which holds the current (last 5 seconds) amount of written bytes
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Gauge<Number>> BYTES_WRITE_CURRENT =
            HiveMQMetric.gaugeValue("com.hivemq.networking.bytes.write.current");


    /**
     * represents a {@link Gauge}, which holds the total amount of read bytes
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Gauge<Number>> BYTES_READ_TOTAL =
            HiveMQMetric.gaugeValue("com.hivemq.networking.bytes.read.total");


    /**
     * represents a {@link Gauge}, which holds total of written bytes
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Gauge<Number>> BYTES_WRITE_TOTAL =
            HiveMQMetric.gaugeValue("com.hivemq.networking.bytes.write.total");


    /**
     * represents a {@link Gauge}, which holds the current total number of connections
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Gauge<Number>> CONNECTIONS_OVERALL_CURRENT =
            HiveMQMetric.gaugeValue("com.hivemq.networking.connections.current");

    /**
     * represents a {@link Histogram}, which holds the mean total number of connections
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Histogram> CONNECTIONS_OVERALL_MEAN =
            HiveMQMetric.valueOf("com.hivemq.networking.connections.mean", Histogram.class);


    /**
     * represents a {@link Counter}, which is increased every time a network connection is closed gracefully.
     * Graceful means the client sent a DISCONNECT message before closing the TCP connection
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Counter> CONNECTIONS_CLOSED_GRACEFUL_COUNT =
            HiveMQMetric.valueOf("com.hivemq.networking.connections-closed.graceful.count", Counter.class);

    /**
     * represents a {@link Counter}, which is increased every time a network connection is closed ungraceful.
     * Ungraceful means the client did NOT send a DISCONNECT message before closing the TCP connection
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Counter> CONNECTIONS_CLOSED_UNGRACEFUL_COUNT =
            HiveMQMetric.valueOf("com.hivemq.networking.connections-closed.ungraceful.count", Counter.class);


    /**
     * represents a {@link Counter}, which is increased every time a network connection is closed
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Counter> CONNECTIONS_CLOSED_COUNT =
            HiveMQMetric.valueOf("com.hivemq.networking.connections-closed.total.count", Counter.class);


    /**
     * represents a {@link Counter}, which measures the current count of subscriptions
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> SUBSCRIPTIONS_CURRENT =
            HiveMQMetric.valueOf("com.hivemq.subscriptions.overall.current", Counter.class);

    /**
     * represents a {@link Counter}, which measures the current count of active persistent sessions
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> PERSISTENT_SESSIONS_ACTIVE =
            HiveMQMetric.valueOf("com.hivemq.sessions.persistent.active", Counter.class);


    /**
     * represents a {@link Gauge}, which measures the current count of stored sessions
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Gauge<Number>> CLIENT_SESSIONS_CURRENT =
            HiveMQMetric.gaugeValue("com.hivemq.sessions.overall.current");


    /**
     * represents a {@link Meter}, which measures the current rate of submitted jobs to the
     * {@link PluginExecutorService}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> PLUGIN_EXECUTOR_SERVICE_SUMBITTED =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".submitted", Meter.class);

    /**
     * represents a {@link Meter}, which measures the current count of running jobs to the
     * {@link PluginExecutorService}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> PLUGIN_EXECUTOR_SERVICE_RUNNING =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".running", Counter.class);

    /**
     * represents a {@link Meter}, which measures the current count of completed jobs to the
     * {@link PluginExecutorService}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> PLUGIN_EXECUTOR_SERVICE_COMPLETED =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".completed", Meter.class);

    /**
     * represents a {@link Timer}, which measures the duration of time spent on submitted jobs to the
     * {@link PluginExecutorService}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_EXECUTOR_SERVICE_DURATION =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".duration", Timer.class);

    /**
     * represents a {@link Meter}, which measures the current rate of scheduled jobs in the
     * {@link PluginExecutorService} that run once
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> PLUGIN_EXECUTOR_SERVICE_SCHEDULED_ONCE =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".scheduled.once", Meter.class);

    /**
     * represents a {@link Meter}, which measures the current rate of scheduled jobs in the
     * {@link PluginExecutorService} that run repetitively
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> PLUGIN_EXECUTOR_SERVICE_SCHEDULED_REPETITIVELY =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".scheduled.repetitively", Meter.class);

    /**
     * represents a {@link Meter}, which measures the current count of jobs to the
     * {@link PluginExecutorService}  that are overrun which means that the job lasted so long that it interfered with the next scheduled job
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> PLUGIN_EXECUTOR_SERVICE_SCHEDULED_OVERRUN =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".scheduled.overrun", Counter.class);

    /**
     * represents a {@link Meter}, which Measures how much percent of the scheduled period the
     * {@link PluginExecutorService} jobs lasted
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Histogram> PLUGIN_EXECUTOR_SERVICE_SCHEDULED_PERCENT_OF_PERIOD =
            HiveMQMetric.valueOf(PLUGIN_EXECUTOR_PREFIX + ".scheduled.percent-of-period", Histogram.class);


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link AfterLoginCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_AFTER_LOGIN_SUCCESS =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.after-login.success.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link AfterLoginCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_AFTER_LOGIN_FAILED =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.after-login.failed.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnAuthenticationCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_AUTHENTICATION =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.authentication.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link RestrictionsAfterLoginCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_RESTRICTIONS =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.restrictions.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnAuthorizationCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_AUTHORIZATION =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.authorization.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnInsufficientPermissionDisconnect}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PERMISSIONS_DISCONNECT_PUBLISH =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.permissions-disconnect.publish.time", Timer.class);


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnInsufficientPermissionDisconnect}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PERMISSIONS_DISCONNECT_SUBSCRIBE =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.permissions-disconnect.subscribe.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnConnectCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_CONNECT =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.connect.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnDisconnectCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_DISCONNECT =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.disconnect.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPublishReceivedCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBLISH_RECEIVED =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.publish-received.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPublishSend}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBLISH_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.publish-send.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnSubscribeCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_SUBSCRIBE =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.subscribe.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubackSend} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBACK_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.puback-send.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubackReceived} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBACK_RECEIVED =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.puback-received.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnSubackSend} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_SUBACK_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.suback-send.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnUnsubackSend} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_UNSUBACK_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.unsuback-send.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubcompSend} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBCOMP_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.pubcomp-send.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubcompReceived} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBCOMP_RECEIVED =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.pubcomp-received.time", Timer.class);


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubrecSend} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBREC_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.pubrec-send.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubrecReceived} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBREC_RECEIVED =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.pubrec-received.time", Timer.class);


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubrelSend} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBREL_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.pubrel-send.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPubrelReceived} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PUBREL_RECEIVED =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.pubrel-received.time", Timer.class);


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnTopicSubscriptionCallback} callback
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_TOPIC_SUBSCRIPTION =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.topic-subscription.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnConnackSend} callback
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_CONNACK_SEND =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.connack-send.time", Timer.class);


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnUnsubscribeCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_UNSUBSCRIBE =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.unsubscribe.time", Timer.class);


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnPingCallback}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_PING =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.ping.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of the {@link OnSessionReadyCallback}
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Timer> PLUGIN_TIMER_SESSION_READY =
            HiveMQMetric.valueOf("com.hivemq.plugin.callbacks.session-ready.time", Timer.class);

    /**
     * represents a {@link Meter}, which measures the rate of unhandled Exceptions
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> TOTAL_EXCEPTION_RATE =
            HiveMQMetric.valueOf(EXCEPTION_PREFIX + ".total", Meter.class);


    /**
     * represents a {@link Counter}, which measures the current count of messages in the publish queue
     * {@link PluginExecutorService}
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Counter> PUBLISH_QUEUE_SIZE =
            HiveMQMetric.valueOf("com.hivemq.queues.publish.size", Counter.class);


    /**
     * represents a {@link Meter}, which measures the rate of messages put into the publish queue
     *
     * @since 3.0
     */
    public static final HiveMQMetric<Meter> PUBLISH_QUEUE_RATE =
            HiveMQMetric.valueOf("com.hivemq.queues.publish.rate", Meter.class);


    /**
     * represents a {@link Gauge}, which holds the current amount of disk I/O tasks that are enqueued by the retained message persistence.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_RETAINED_MESSAGE_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.retained-messages.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of disk I/O tasks that are enqueued by the client session persistence.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_CLIENT_SESSION_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.client-session.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of disk I/O tasks that are enqueued by the subscription persistence.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_SUBSCRIPTION_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.subscription.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of disk I/O tasks that are enqueued by the queued messages persistence.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_QUEUED_MESSAGES_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.queued-messages.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of disk I/O tasks that are enqueued by the outgoing message flow persistence.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_OUTGOING_MESSAGE_FLOW_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.outgoing-message-flow.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of disk I/O tasks that are enqueued by the attribute persistence.
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_ATTRIBUTE_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.attribute.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of tasks that are enqueued by the request event bus.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_REQUEST_EVENT_BUS_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.request-event-bus.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of tasks that are enqueued by the client group persistence.
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_CLIENT_GROUP_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.client-group.tasks");


    /**
     * represents a {@link Gauge}, which holds the current amount of disk I/O tasks that are enqueued by all persistences.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_TOTAL_TASKS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.total.tasks");

    /**
     * represents a {@link Gauge}, which holds the current amount of single writer task queues that are not empty.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_NONEMPTY_QUEUES =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.nonempty-queues");

    /**
     * represents a {@link Gauge}, which holds the current amount of threads that are executing disk I/O tasks.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> SINGLE_WRITER_RUNNING_THREADS =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.executor.running.threads");


    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of retained message disk I/O tasks
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_RETAINED_MESSAGE_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.retained-messages.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of client session disk I/O tasks
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_CLIENT_SESSION_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.client-session.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of subscription disk I/O tasks
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_SUBSCRIPTION_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.subscription.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of queued messages disk I/O tasks
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_QUEUED_MESSAGES_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.queued-messages.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of outgoing message flow disk I/O tasks
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_OUTGOING_MESSAGE_FLOW_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.outgoing-message-flow.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of attribute disk I/O tasks
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_ATTRIBUTE_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.attribute.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of request event bus tasks
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_REQUEST_EVENT_BUS_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.request-event-bus.time", Timer.class);

    /**
     * represents a {@link Timer}, which measures the mean execution time (in nanoseconds)
     * of client group tasks
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Timer> SINGLE_WRITER_CLIENT_GROUP_TIMER =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.client-group.time", Timer.class);

    /**
     * represents a {@link Counter}, which measures the current count of loops that all single writer threads have done without executing a task
     * {@link PluginExecutorService}
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Counter> SINGLE_WRITER_QUEUE_MISSES =
            HiveMQMetric.valueOf("com.hivemq.persistence.executor.queue-misses", Counter.class);

    /**
     * represents a {@link Gauge}, which holds the current amount of payloads stored in the payload persistence.
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Gauge<Number>> PAYLOAD_PERSISTENCE_ENTRIES =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.payload-entries.count");


    /**
     * represents a {@link Gauge}, which holds the current amount of payloads stored in the payload persistence, that can be removed by the cleanup.
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Gauge<Number>> PAYLOAD_PERSISTENCE_REMOVABLE_ENTRIES =
            HiveMQMetric.gaugeValue("com.hivemq.persistence.removable-entries.count");

    /**
     * represents a {@link Counter}, which measures the total count of clients that have been disconnected, because they did not send a message within their keep alive interval
     * {@link PluginExecutorService}
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Counter> KEEP_ALIVE_DISCONNECT_COUNT =
            HiveMQMetric.valueOf("com.hivemq.keep-alive.disconnect.count", Counter.class);


    /**
     * represents a {@link Gauge}, which holds the currently used direct memory in bytes
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Gauge<Number>> DIRECT_MEMORY_USED =
            HiveMQMetric.gaugeValue("com.hivemq.direct-memory.used");

    /**
     * represents a {@link Meter}, which measures the rate of PUBLISH messages that are resent, because the broker did not receive a PUBACK message in time.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Meter> PUBLISH_RESENT =
            HiveMQMetric.valueOf("com.hivemq.messages.publish-resent", Meter.class);

    /**
     * represents a {@link Meter}, which measures the rate of PUBREL messages that are resent, because the broker did not receive a PUBCOMP message in time.
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Meter> PUBREL_RESENT =
            HiveMQMetric.valueOf("com.hivemq.messages.pubrel-resent", Meter.class);

    /**
     * represents a {@link Timer}, which measures the time spent waiting for cluster topology changes
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> CLUSTER_TOPOLOGY_CHANGE_TIMER =
            HiveMQMetric.valueOf("com.hivemq.cluster.topology-change.time", Timer.class);

    /**
     * represents a {@link Counter}, which measures the current count of retry that have been processed to request the name of other cluster nodes.
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Counter> CLUSTER_NAME_REQUEST_RETRY_COUNT =
            HiveMQMetric.valueOf("com.hivemq.cluster.name-request.retry.count", Counter.class);

    /**
     * represents a {@link Timer}, which measures the execution time of the tombstone cleanup jobs
     *
     * @since 3.2
     */
    public static final HiveMQMetric<Timer> TOMBSTONE_CLEAN_UP_JOB_TIMER =
            HiveMQMetric.valueOf("com.hivemq.tombstone-cleanup.time", Timer.class);

    /**
     * represents a {@link Gauge}, which holds the current number of queued messages in persistent sessions
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Gauge<Number>> SESSIONS_PERSISTENT_QUEUE_MESSAGE_COUNT =
            HiveMQMetric.gaugeValue("com.hivemq.messages.queued.count");

    /**
     * represents a {@link Gauge}, which holds the current amount of nodes in cluster
     *
     * @since 3.3
     */
    public static final HiveMQMetric<Gauge<Number>> CLUSTER_NODE_COUNT =
            HiveMQMetric.gaugeValue("com.hivemq.cluster.nodes.count");

    /**
     * represents a {@link Counter}, which count the amount of times that the plugin service rate limit was exceeded
     * {@link PluginExecutorService}
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Counter> PLUGIN_RATE_LIMIT_EXCEEDED_COUNT =
            HiveMQMetric.valueOf("com.hivemq.plugin.services.rate-limit-exceeded.count", Counter.class);

    /**
     * represents a {@link Gauge}, which holds the current level of supervision overload protection
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Gauge<Number>> SUPERVISION_OVERLOAD_PROTECTION_LEVEL =
            HiveMQMetric.gaugeValue("com.hivemq.supervision.overload.protection.level");

    /**
     * represents a {@link Gauge}, which holds the current amount of credits a client receives per tick.
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Gauge<Number>> OVERLOAD_PROTECTION_CREDITS_PER_TICK =
            HiveMQMetric.gaugeValue("com.hivemq.overload-protection.credits.per-tick");

    /**
     * represents a {@link Gauge}, which holds the average amount of available credits between all clients.
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Gauge<Number>> OVERLOAD_PROTECTION_CLIENTS_AVERAGE_CREDITS =
            HiveMQMetric.gaugeValue("com.hivemq.overload-protection.clients.average-credits");

    /**
     * represents a {@link Gauge}, which holds the current amount of clients having less than the full amount of credits.
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Gauge<Number>> OVERLOAD_PROTECTION_CLIENTS_USING_CREDITS =
            HiveMQMetric.gaugeValue("com.hivemq.overload-protection.clients.using-credits");

    /**
     * represents a {@link Gauge}, which holds the current amount of clients for which backpressure is applied by overload protection.
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Gauge<Number>> OVERLOAD_PROTECTION_CLIENTS_BACKPRESSURE_ACTIVE =
            HiveMQMetric.gaugeValue("com.hivemq.overload-protection.clients.backpressure-active");

    /**
     * represents a {@link Counter}, which counts the current amount of clients subscriptions that may be removable by the topic tree cleanup.
     *
     * @since 3.4
     */
    public static final HiveMQMetric<Counter> POSSIBLY_STALE_SUBSCRIPTIONS =
            HiveMQMetric.valueOf("com.hivemq.internal.topic-tree.possibly-stale-subscriptions", Counter.class);


}

