/*
 * Copyright 2013 dc-square GmbH
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

package com.dcsquare.hivemq.spi.topic;

import com.dcsquare.hivemq.spi.topic.exception.InvalidTopicException;
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
public class TokenizedTopicMatcherTest {

    private String actual;
    private TokenizedTopicMatcher topicMatcher;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Before
    public void setUp() throws Exception {

        actual = "my/test/topic/for/the/unit/test";

        topicMatcher = new TokenizedTopicMatcher();

    }

    @Test
    public void testMatchesWithoutWildcards() throws Exception {

        assertTrue(topicMatcher.matches(actual, actual));
        assertTrue(topicMatcher.matches(actual + "/", actual));
        assertTrue(topicMatcher.matches(actual, actual + "/"));
        assertTrue(topicMatcher.matches(actual + "/", actual + "/"));

        assertFalse(topicMatcher.matches(actual + "/getCacheImpl", actual));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit", actual));

        assertFalse(topicMatcher.matches(actual.toUpperCase(), actual));

    }

    @Test
    public void testMatchesWithLevelWildcards() throws Exception {

        assertTrue(topicMatcher.matches("my/test/topic/for/the/+/test", actual));
        assertTrue(topicMatcher.matches("my/+/topic/+/the/+/test", actual));
        assertTrue(topicMatcher.matches("+/+/+/+/+/+/test", actual));
        assertTrue(topicMatcher.matches("my/test/topic/for/the/unit/+", actual));
        assertTrue(topicMatcher.matches("my/test/topic/for/the/unit/+/", actual));

        assertFalse(topicMatcher.matches("my/test/topic/for/the/+/nottest", actual));
        assertFalse(topicMatcher.matches("my/test/topic/for/a/+/test", actual));

        assertFalse(topicMatcher.matches("my/test/topic/for/the/+/test/toolong", actual));
        assertFalse(topicMatcher.matches("my/test/topic/for/+/unit", actual));

        assertFalse(topicMatcher.matches(actual + "/+", actual));

        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/t+", actual));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/+t", actual));

    }

    @Test
    public void testMatchesWithWildcard() throws Exception {

        assertTrue(topicMatcher.matches("my/test/topic/for/the/#", actual));
        assertTrue(topicMatcher.matches("my/test/topic/#", actual));
        assertTrue(topicMatcher.matches("#", actual));
        assertTrue(topicMatcher.matches("#/", actual));
        assertTrue(topicMatcher.matches("+/#", actual));
        assertTrue(topicMatcher.matches(actual + "/#", actual));

        assertTrue(topicMatcher.matches("my/+/topic/for/the/#", actual));
        assertTrue(topicMatcher.matches("+/+/topic/for/the/#", actual));
        assertTrue(topicMatcher.matches("+/+/topic/+/+/#", actual));


        assertFalse(topicMatcher.matches("a/test/topic/#", actual));
        assertFalse(topicMatcher.matches("a/#", actual));
        assertFalse(topicMatcher.matches("+/+/topic/+/a/#", actual));

        assertFalse(topicMatcher.matches("my/test/topic/for/the#", actual));
        assertFalse(topicMatcher.matches("my/test/topic/for/#the", actual));
        assertFalse(topicMatcher.matches(actual + "#", actual));

        assertFalse(topicMatcher.matches("/" + actual, actual));
        assertFalse(topicMatcher.matches("/#", actual));

        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/test/toolong/#", actual));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/toolong/#", actual));
        assertFalse(topicMatcher.matches("my/test/topic/for/the/unit/t+/#", actual));

        assertTrue(topicMatcher.matches("my/test/topic/for/the/unit/+/#", actual));
        assertTrue(topicMatcher.matches("my/test/topic/for/the/unit/test/#", actual));

        assertFalse(topicMatcher.matches("my/#/test/topic", "my/test/topic"));

    }

    @Test
    public void testMatchesSysTopic() throws Exception {

        final String actualTopic = "$SYS/broker/messages/sent";

        assertTrue(topicMatcher.matches("$SYS/#", actualTopic));
        assertTrue(topicMatcher.matches("$SYS/#", actualTopic));
        assertTrue(topicMatcher.matches("$SYS/+/messages/sent", actualTopic));

        assertFalse(topicMatcher.matches("#", actualTopic));
        assertFalse(topicMatcher.matches("+/#", actualTopic));
        assertFalse(topicMatcher.matches("SYS/#", actualTopic));
        assertFalse(topicMatcher.matches("/$SYS/#", actualTopic));

        assertFalse(topicMatcher.matches("$SYS/#", "test/topic"));

    }

    @Test
    public void test_invalid_wildcard_handling() throws Exception {


        assertFalse(topicMatcher.matches("my/t#", "my/t"));

        assertFalse(topicMatcher.matches("my/#t", "my/t"));
        assertFalse(topicMatcher.matches("my/t#t", "my/ttt"));


        assertFalse(topicMatcher.matches("my/t+", "my/t"));
        assertFalse(topicMatcher.matches("my/+t", "my/t"));
        assertFalse(topicMatcher.matches("my/t+t", "my/ttt"));
    }


    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_hash_sign_1() throws Exception {
        topicMatcher.matches("my/#", "my/t#");
    }

    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_hash_sign_2() throws Exception {
        topicMatcher.matches("#", "my/t#");
    }

    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_hash_sign_3() throws Exception {
        topicMatcher.matches("#", "my/t#");
    }

    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_hash_sign_4() throws Exception {
        topicMatcher.matches("+/#", "my/t#");
    }

    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_hash_sign_5() throws Exception {
        assertFalse(topicMatcher.matches("my/#/test/topic", "my/#/test/topic"));
    }

    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_plus_sign_1() throws Exception {
        assertFalse(topicMatcher.matches("my/t+", "my/t+"));
    }

    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_plus_sign_2() throws Exception {
        assertFalse(topicMatcher.matches("my/#", "my/t+"));
    }

    @Test(expected = InvalidTopicException.class)
    public void test_invalid_actual_topic_with_plus_sign_3() throws Exception {
        assertFalse(topicMatcher.matches("+/#", "my/t+"));
    }


}
