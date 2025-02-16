/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hertzbeat.common.util;

import static org.apache.hertzbeat.common.util.JsonUtil.isJsonStr;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link JsonUtil}
 */
class JsonUtilTest {

    @Test
    void toJson() {
        Map<String, String> map = Map.of("test", "pro");
        
        assertEquals("{\"test\":\"pro\"}",
                JsonUtil.toJson(map));

        assertNull(JsonUtil.toJson(null));
    }

    @Test
    void testFromJson() {
        assertNull(JsonUtil.fromJson("", new TypeReference<>() {
        }));
        assertNull(JsonUtil.fromJson(null, new TypeReference<>() {
        }));
        assertNull(JsonUtil.fromJson(" ", new TypeReference<>() {
        }));
        assertNull(JsonUtil.fromJson(" ", String.class));
        assertNull(JsonUtil.fromJson(" "));
        assertNull(JsonUtil.fromJson(null));
        assertNull(JsonUtil.fromJson("invalid"));
    }

    @Test
    void testIsJsonStr() {
        String jsonString = "{\"name\":\"John\", \"age\":30";
        assertFalse(isJsonStr(jsonString));

        assertFalse(isJsonStr(""));

        assertFalse(isJsonStr(null));

        String whitespaceString = " ";
        assertFalse(isJsonStr(whitespaceString));

        jsonString = "This is just a plain string.";
        assertFalse(isJsonStr(jsonString));

        String jsonStringArrays = "[{\"name\":\"John\"}, {\"name\":\"Doe\"}]";
        assertTrue(isJsonStr(jsonStringArrays));

        assertFalse(isJsonStr("{invalid}"));
    }

}
