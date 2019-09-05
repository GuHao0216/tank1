package org.csu.tank.base;

import com.alibaba.fastjson.JSON;

public abstract class BaseObject {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
