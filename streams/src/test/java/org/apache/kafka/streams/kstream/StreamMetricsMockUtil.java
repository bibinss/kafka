/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.streams.kstream;

import org.apache.kafka.common.metrics.Sensor;
import org.apache.kafka.streams.processor.internals.metrics.StreamsMetricsImpl;
import org.apache.kafka.streams.state.internals.metrics.RocksDBMetricsRecordingTrigger;
import org.easymock.EasyMock;
import org.powermock.api.easymock.PowerMock;

import java.util.HashMap;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;

public class StreamMetricsMockUtil {

    public static StreamsMetricsImpl mockStreamsMetrics() {
        final StreamsMetricsImpl streamsMetrics = PowerMock.createNiceMock(StreamsMetricsImpl.class);
        expect(streamsMetrics.version()).andReturn(StreamsMetricsImpl.Version.LATEST).anyTimes();
        expect(streamsMetrics.rocksDBMetricsRecordingTrigger()).andReturn(EasyMock.niceMock(RocksDBMetricsRecordingTrigger.class));
        expect(streamsMetrics.clientLevelTagMap()).andReturn(new HashMap<>()).anyTimes();
        expect(streamsMetrics.threadLevelTagMap(anyString())).andReturn(new HashMap<>()).anyTimes();
        expect(streamsMetrics.taskLevelTagMap(anyString(), anyString())).andReturn(new HashMap<>()).anyTimes();
        expect(streamsMetrics.nodeLevelTagMap(anyString(), anyString(), anyString())).andReturn(new HashMap<>()).anyTimes();
        expect(streamsMetrics.storeLevelTagMap(anyString(), anyString(), anyString(), anyString())).andReturn(new HashMap<>()).anyTimes();
        expect(streamsMetrics.bufferLevelTagMap(anyString(), anyString(), anyString())).andReturn(new HashMap<>()).anyTimes();
        final Sensor sensor = PowerMock.createNiceMock(Sensor.class);
        expect(streamsMetrics.taskLevelSensor(anyString(), anyString(), anyString(), anyObject(Sensor.RecordingLevel.class), anyObject(Sensor[].class))).
            andReturn(sensor);
        expect(streamsMetrics.nodeLevelSensor(anyString(), anyString(), anyString(), anyString(), anyObject(Sensor.RecordingLevel.class),
            anyObject(Sensor[].class))).andReturn(sensor);
        expect(streamsMetrics.cacheLevelSensor(anyString(), anyString(), anyString(), anyString(), anyObject(Sensor.RecordingLevel.class),
            anyObject(Sensor[].class))).andReturn(sensor);
        expect(streamsMetrics.cacheLevelTagMap(anyString(), anyString(), anyString())).andReturn(new HashMap<>()).anyTimes();
        expect(streamsMetrics.storeLevelSensor(anyString(), anyString(), anyString(), anyString(), anyObject(Sensor.RecordingLevel.class),
            anyObject(Sensor[].class))).andReturn(sensor);
        expect(streamsMetrics.addSensor(anyString(), anyObject(Sensor.RecordingLevel.class))).andReturn(sensor).anyTimes();
        expect(streamsMetrics.addSensor(anyString(), anyObject(Sensor.RecordingLevel.class), anyObject(Sensor[].class))).andReturn(sensor).anyTimes();
        expect(streamsMetrics.metrics()).andReturn(new HashMap<>()).anyTimes();
        expect(streamsMetrics.addLatencyAndThroughputSensor(anyString(), anyString(), anyString(), anyObject(Sensor.RecordingLevel.class),
            anyObject(String[].class))).andReturn(sensor);
        expect(streamsMetrics.addThroughputSensor(anyString(), anyString(), anyString(), anyObject(Sensor.RecordingLevel.class),
            anyObject(String[].class))).andReturn(sensor);
        PowerMock.mockStaticNice(StreamsMetricsImpl.class);
        PowerMock.replay(streamsMetrics, StreamsMetricsImpl.class, sensor);
        return streamsMetrics;
    }
}
