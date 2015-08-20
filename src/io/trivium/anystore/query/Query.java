/*
 * Copyright 2015 Jens Walter
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

package io.trivium.anystore.query;

import io.trivium.anystore.ObjectRef;
import javolution.util.FastList;

public class Query {
    public ObjectRef id = ObjectRef.getInstance();
    public FastList<Criteria> criteria = new FastList<Criteria>();

    public String getValueForName(String name) {
        for (Criteria c : criteria) {
            if (c instanceof Value) {
                Value v = (Value) c;
                if (v.getName().equals(name)) {
                    return v.getValue();
                }
            }
            if(c instanceof Range) {
                Range r = (Range) c;
                if(r.getName().equals(name)) {
                    return r.getValue();
                }
            }
        }
        return null;
    }
}
