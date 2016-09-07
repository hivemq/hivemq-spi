/*
 * Copyright 2014 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.spi.topic;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests various cases where topics should match or not match.
 *
 * @author Dominik Obermaier
 * @see <a href="https://groups.google.com/forum/#!msg/mqtt/tQxZjssBDGw/m1CQYZVyVmoJ">Topic edge cases clarification</a>,
 * @see <a href="http://mqtt.org/wiki/doku.php/topic_format">Topic Clarifications</a>
 * @see <a href="http://mqtt.org/wiki/doku.php/overlapping_topics">Overlapping Topics</a>
 */
public class PermissionTopicMatcherTest {

    private String actual;
    private PermissionTopicMatcher topicMatcher;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Before
    public void setUp() throws Exception {

        actual = "my/test/topic/for/the/unit/test";

        topicMatcher = new PermissionTopicMatcher();

    }

    @Test
    public void testMatchesWithoutWildcards() throws Exception {

        assertTrue(topicMatcher.matches(actual, StringUtils.splitPreserveAllTokens(actual, "/"), true, false, false, actual, StringUtils.splitPreserveAllTokens(actual, "/")));

        assertFalse(topicMatcher.matches(actual + "/getCacheImpl", split(actual + "/getCacheImpl"), true, false, false, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit", split("my/test/topic/for/the/unit"), true, false, false, actual, split(actual)));

        assertFalse(topicMatcher.matches(actual.toUpperCase(), split(actual.toUpperCase()), true, false, false, actual, split(actual)));

    }

    @Test
    public void testMatchesWithLevelWildcards() throws Exception {

        assertTrue(topicMatcher.matches("my/test/topic/for/the/+/test", split("my/test/topic/for/the/+/test"), false, false, false, actual, split(actual)));
        assertTrue(topicMatcher.matches("my/+/topic/+/the/+/test", split("my/+/topic/+/the/+/test"), false, false, false, actual, split(actual)));
        assertTrue(topicMatcher.matches("+/+/+/+/+/+/test", split("+/+/+/+/+/+/test"), false, false, false, actual, split(actual)));
        assertTrue(topicMatcher.matches("my/test/topic/for/the/unit/+", split("my/test/topic/for/the/unit/+"), false, false, false, actual, split(actual)));

        assertFalse(topicMatcher.matches("my/test/topic/for/the/+/nottest", split("my/test/topic/for/the/+/nottest"), false, false, false, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/a/+/test", split("my/test/topic/for/a/+/test"), false, false, false, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/+/test/toolong", split("my/test/topic/for/the/+/test/toolong"), false, false, false, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/+/unit", split("my/test/topic/for/+/unit"), false, false, false, actual, split(actual)));

        assertFalse(topicMatcher.matches(actual + "/+", split(actual + "/+"), false, false, false, actual, split(actual)));

        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/t+", split("my/test/topic/for/the/unit/t+"), false, false, false, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/+t", split("my/test/topic/for/the/unit/+t"), false, false, false, actual, split(actual)));

    }

    @Test
    public void testMatchesWithWildcard() throws Exception {

        assertTrue(topicMatcher.matches("my/test/topic/for/the/#", split("my/test/topic/for/the/#"), false, true, true, actual, split(actual)));
        assertTrue(topicMatcher.matches("my/test/topic/#", split("my/test/topic/#"), false, true, true, actual, split(actual)));
        assertTrue(topicMatcher.matches("#", split("#"), false, false, true, actual, split(actual)));
        assertTrue(topicMatcher.matches("+/#", split("+/#"), false, true, true, actual, split(actual)));
        assertTrue(topicMatcher.matches(actual + "/#", split(actual + "/#"), false, true, true, actual, split(actual)));

        assertTrue(topicMatcher.matches("my/+/topic/for/the/#", split("my/+/topic/for/the/#"), false, true, true, actual, split(actual)));
        assertTrue(topicMatcher.matches("+/+/topic/for/the/#", split("+/+/topic/for/the/#"), false, true, true, actual, split(actual)));
        assertTrue(topicMatcher.matches("+/+/topic/+/+/#", split("+/+/topic/+/+/#"), false, true, true, actual, split(actual)));


        assertFalse(topicMatcher.matches("a/test/topic/#", split("a/test/topic/#"), false, true, true, actual, split(actual)));
        assertFalse(topicMatcher.matches("a/#", split("a/#"), false, true, true, actual, split(actual)));
        assertFalse(topicMatcher.matches("+/+/topic/+/a/#", split("+/+/topic/+/a/#"), false, true, true, actual, split(actual)));

        assertFalse(topicMatcher.matches("my/test/topic/for/the#", split("my/test/topic/for/the#"), false, false, true, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/#the", split("my/test/topic/for/#the"), false, false, true, actual, split(actual)));
        assertFalse(topicMatcher.matches(actual + "#", split(actual + "#"), false, false, true, actual, split(actual)));

        assertFalse(topicMatcher.matches("/" + actual, split("/"), true, false, false, actual, split(actual)));
        assertFalse(topicMatcher.matches("/#", split("/#"), false, true, true, actual, split(actual)));

        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/test/toolong/#", split("my/test/topic/for/the/unit/test/toolong/#"), false, true, true, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/toolong/#", split("my/test/topic/for/the/unit/toolong/#"), false, true, true, actual, split(actual)));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/t+/#", split("my/test/topic/for/the/unit/t+/#"), false, true, true, actual, split(actual)));

        assertTrue(topicMatcher.matches("my/test/topic/for/the/unit/+/#", split("my/test/topic/for/the/unit/+/#"), false, true, true, actual, split(actual)));
        assertTrue(topicMatcher.matches("my/test/topic/for/the/unit/test/#", split("my/test/topic/for/the/unit/test/#"), false, true, true, actual, split(actual)));

        assertFalse(topicMatcher.matches("my/#/test/topic", split("my/#/test/topic"), false, false, true, "my/test/topic", split("my/test/topic")));

    }

    @Test
    public void test_invalid_wildcard_handling() throws Exception {


        assertFalse(topicMatcher.matches("my/t#", split("my/t#"), false, false, true, "my/t", split("my/t")));

        assertFalse(topicMatcher.matches("my/#t", split("my/#t"), false, false, true, "my/t", split("my/t")));
        assertFalse(topicMatcher.matches("my/t#t", split("my/t#t"), false, false, true, "my/ttt", split("my/ttt")));


        assertFalse(topicMatcher.matches("my/t+", split("my/t+"), false, false, false, "my/t", split("my/t")));
        assertFalse(topicMatcher.matches("my/+t", split("my/+t"), false, false, false, "my/t", split("my/t")));
        assertFalse(topicMatcher.matches("my/t+t", split("my/t+t"), false, false, false, "my/ttt", split("my/ttt")));
    }

    private String[] split(final String str) {
        return StringUtils.splitPreserveAllTokens(str, "/");
    }
}
