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

import io.trivium.extension.Fact;
import io.trivium.extension.Typed;

public class Error implements Fact {

    public String message;
    public ErrorCategory category;
    public Class<? extends Typed> sourceModule;

    public enum ErrorCategory {
        TRANSIENT,
        PERSISTENT
    }
}
