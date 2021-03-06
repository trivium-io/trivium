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

package io.trivium.anystore.query;

import io.trivium.extension.Fact;

public class Query<T extends Fact>{
    /**
     * target type of the query
     */
    public Class<T> targetType;

    /**
     * evaluates the condition for a query
     */
    public BooleanClosure<T> condition = (item) -> true;

    /**
     * provides the context string
     */
    public StringClosure<T> context;

    /**
     * field the result set gets partitioned by
     */
    public StringClosure<T> partitionOver;

    /**
     * define order within the partition
     */
    public SortOrder partitionSortOrder =  SortOrder.DESCENDING;

    /**
     * field the order in the partition is determined by
     */
    public StringClosure<T> partitionOrderBy;

    /**
     * amount of result in one partition
     */
    public long partitionReduceTo = 1;

    public ConnectClosure<T,? extends Fact> connect;

    /**
     * dummy return method to ensure compile safety
     * @return always null
     */
    public T getObject() {
        return null;
    }

    /**
     * dummy return method to ensure compile safety
     * @return always null
     */
    public T[] getObjects() {
        return null;
    }

}
