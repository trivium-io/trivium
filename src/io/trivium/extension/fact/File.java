/*
 * Copyright 2016 Jens Walter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.trivium.extension.fact;

import io.trivium.NVList;
import io.trivium.extension.fact.TriviumObject;
import io.trivium.extension.Fact;
import io.trivium.glue.om.Element;

import java.time.Instant;

public class File implements Fact {
    /**
     * {"io.trivium.extension.fact.File": {
         "data": "cGFja2FnZSBjb20uaW5maW5pdXAudGVzdDsKCmltcG9ydCBjb20uZ29vZ2xlLmdzb24uSnNvbk9iamVjdDsKCnB1YmxpYyBjbGFzcyBOamFtc1Rlc3REYXRhIHsKCiAgICBwdWJsaWMgU3RyaW5nIGRvbWFpbjsKICAgIHB1YmxpYyBTdHJpbmcgZGVwbG95bWVudDsKICAgIHB1YmxpYyBTdHJpbmcgcHJvY2VzczsKICAgIHB1YmxpYyBpbnQgZHVyYXRpb247CiAgICBwdWJsaWMgU3RyaW5nIGpvYnN0YXJ0OwogICAgcHVibGljIFN0cmluZyBqb2JlbmQ7CiAgICBwdWJsaWMgU3RyaW5nIHN0YXR1czsKICAgIHB1YmxpYyBTdHJpbmcgbG9naWQ7CiAgICAKICAgIHB1YmxpYyBTdHJpbmcgdG9TdHJpbmcoKXsKICAgICAgICBKc29uT2JqZWN0IGpzID0gbmV3IEpzb25PYmplY3QoKTsKICAgICAgICBqcy5hZGRQcm9wZXJ0eSgiZG9tYWluIiwgdGhpcy5kb21haW4pOwogICAgICAgIGpzLmFkZFByb3BlcnR5KCJkZXBsb3ltZW50IiwgdGhpcy5kZXBsb3ltZW50KTsKICAgICAgICBqcy5hZGRQcm9wZXJ0eSgicHJvY2VzcyIsIHRoaXMucHJvY2Vzcyk7CiAgICAgICAganMuYWRkUHJvcGVydHkoImR1cmF0aW9uIiwgdGhpcy5kdXJhdGlvbik7CiAgICAgICAganMuYWRkUHJvcGVydHkoImpvYnN0YXJ0IiwgdGhpcy5qb2JzdGFydCk7CiAgICAgICAganMuYWRkUHJvcGVydHkoImpvYmVuZCIsIHRoaXMuam9iZW5kKTsKICAgICAgICBqcy5hZGRQcm9wZXJ0eSgic3RhdHVzIiwgdGhpcy5zdGF0dXMpOwogICAgICAgIGpzLmFkZFByb3BlcnR5KCJsb2dpZCIsIHRoaXMubG9naWQpOwogICAgICAgIHJldHVybiBqcy50b1N0cmluZygpOwogICAgfQp9Cg==",
         "name": "io\/trivium\/test\/NjamsTestData.java",
         "size": "814",
         "contentType": "text\/x-java-source",
         "lastModified": "2014-11-11T15:33:14Z"
         "metadata": []
            }
        }
     */
    public String name;
    public long size;
    public String contentType;
    public Instant lastModified;
    public String data;
    public NVList metadata;
}
