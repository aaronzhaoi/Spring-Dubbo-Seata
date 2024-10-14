package org.order.tcc.service.impl;


import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.order.common.util.ResultHolder;
import org.order.dubbo.api.domain.CustomBalance;
import org.order.tcc.persistent.dao.BalanceMapper;
import org.order.tcc.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Random;

@Service
@LocalTCC
public class BalanceServiceImpl implements BalanceService {


    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    @TwoPhaseBusinessAction(name = "BalanceServicePrepare", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean prepare(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "a") int a) {
        String xid = String.valueOf(Math.abs(new Random().nextInt()));
        CustomBalance customBalance = new CustomBalance();

        Random random = new Random();

        customBalance.setUserId(String.valueOf(567));
        customBalance.setBalanceId(xid);
        customBalance.setBalance(random.nextInt());

        balanceMapper.insertBalanceHis(customBalance);
        System.out.println("BalanceService prepare, xid:" + RootContext.getXID() + ", a:" + a);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Assert.isTrue(actionContext.getActionContext("a") != null);
        System.out.println("BalanceService commit, xid:" + xid + ", a:" + actionContext.getActionContext("a"));
        ResultHolder.setBalanceResults(xid, "T");
        return true;
    }

    /*Cancel 接口设计时需要允许空回滚。在 Try 接口因为丢包时没有收到，事务管理器会触发回滚，
    这时会触发 Cancel 接口，这时 Cancel 执行时发现没有对应的事务 xid 或主键时，需要返回回滚成功。
    让事务服务管理器认为已回滚，否则会不断重试，而 Cancel 又没有对应的业务数据可以进行回滚。*/
    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Assert.isTrue(actionContext.getActionContext("a") != null);
        System.out.println("BalanceService rollback, xid:" + xid + ", a:" + actionContext.getActionContext("a"));
        ResultHolder.setBalanceResults(xid, "R");
        return true;
    }
}
