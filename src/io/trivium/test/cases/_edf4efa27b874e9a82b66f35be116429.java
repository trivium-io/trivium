package io.trivium.test.cases;

import io.trivium.NVPair;
import io.trivium.anystore.ObjectType;
import io.trivium.test.Assert;
import io.trivium.test.TestCase;

public class _edf4efa27b874e9a82b66f35be116429 implements TestCase{
    @Override
    public String getClassName() {
        return "io.trivium.NVPair";
    }

    @Override
    public String getMethodName() {
        return "isArray";
    }
    
    @Override
    public String getName() {
        return "multiple values for one nvpair";
    }

    @Override
    public void run() throws Exception {
        String key = "key";
        String value = "value1";
        String value2 = "value2";
        NVPair pair = new NVPair(key);
        pair.setValue(value);
        pair.addValue(value2);
        Assert.isTrue(pair.isArray());
    }

    @Override
    public ObjectType getTypeId() {
        return ObjectType.getInstance("edf4efa2-7b87-4e9a-82b6-6f35be116429","v1");
    }
}
