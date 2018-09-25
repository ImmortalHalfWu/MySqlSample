package com.immortal.half.wu.works.impls;

import com.immortal.half.wu.utils.LogUtil;
import com.immortal.half.wu.works.abstracts.AbsWorker;
import com.immortal.half.wu.works.interfaces.WorkInterface;

import java.util.concurrent.TimeUnit;

class WorkRefreshPayToken extends AbsWorker {


    private WorkRefreshPayToken(long firstRunDelay, long runDelay, TimeUnit timeUnit) {
        super(firstRunDelay, runDelay, timeUnit);
    }

    public static WorkInterface newInstance(long firstRunDelay, long runDelay, TimeUnit timeUnit) {
        return new WorkRefreshPayToken(firstRunDelay, runDelay, timeUnit);
    }

    @Override
    protected void doWork() {
        // todo 尝试刷新paytoken
        LogUtil.i("WorkRefreshPayToken do work");
    }
}
