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

package io.trivium.test.anystore;

import io.trivium.anystore.AnyServer;
import io.trivium.anystore.TypeRef;
import io.trivium.anystore.query.Query;
import io.trivium.anystore.query.SortOrder;
import io.trivium.extension.fact.TriviumObject;
import io.trivium.glue.om.Element;
import io.trivium.test.Assert;
import io.trivium.test.TestCase;

import java.util.ArrayList;

public class _e30a1ef40c8d445c880ed149a14610de implements TestCase{
    @Override
    public String getTestName() {
        return "simple store query";
    }

    @Override
    public void run() throws Exception {
        TriviumObject tvm = new TriviumObject();
        Element el = new Element("node","hallo world");

        tvm.setData(el);
        tvm.replaceMeta("id", tvm.getId().toString());
        tvm.replaceMeta("contentType", "text/plain");
        TypeRef typeRef = TypeRef.getInstance("dummy._e30a1ef40c8d445c880ed149a14610de");
        tvm.setTypeRef(typeRef);

        AnyServer.INSTANCE.storeObject(tvm);

        Query<TriviumObject> q = new Query<TriviumObject>(){
            {
                targetType = TriviumObject.class;
                condition = (obj) -> obj.getId()==tvm.getId();
                partitionOver = (obj) -> obj.getTypeRef().toString();
                partitionOrderBy = (obj) -> obj.findMetaValue("created");
                partitionSortOrder = SortOrder.DESCENDING;
            }
        };
        ArrayList<TriviumObject> list = AnyServer.INSTANCE.loadObjects(q).getAllAsTypedList();
        String str1 = tvm.getMetadataJson();
        String str2 = list.get(0).getMetadataJson();
        Assert.equalsString(str1,str2);
    }
}

