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

import io.trivium.TriviumLoader;
import io.trivium.extension._f70b024ca63f4b6b80427238bfff101f.TriviumObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashMap;

public class Result {
    public ScriptEngine scriptEngine = new ScriptEngineManager(new TriviumLoader(ClassLoader.getSystemClassLoader(),true)).getEngineByName("nashorn");
    public HashMap<String, ArrayList<TriviumObject>> partition = new HashMap<>();
}
