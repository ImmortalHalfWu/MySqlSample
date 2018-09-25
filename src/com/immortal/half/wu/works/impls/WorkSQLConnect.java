package com.immortal.half.wu.works.impls;

import com.immortal.half.wu.utils.LogUtil;
import com.immortal.half.wu.works.abstracts.AbsWorker;

import java.util.concurrent.TimeUnit;

class WorkSQLConnect extends AbsWorker {

    private WorkSQLConnect(long firstRunDelay, long runDelay, TimeUnit timeUnit) {
        super(firstRunDelay, runDelay, timeUnit);
    }

    static WorkSQLConnect newInstance(long firstRunDelay, long runDelay, TimeUnit timeUnit) {
        return new WorkSQLConnect(firstRunDelay, runDelay, timeUnit);
    }

    @Override
    protected void doWork() {
        // todo 尝试从服务器拉取数据
        LogUtil.i("WorkSQLConnect do work");
    }
}
