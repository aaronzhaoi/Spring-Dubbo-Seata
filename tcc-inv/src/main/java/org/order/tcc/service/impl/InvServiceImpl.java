package org.order.tcc.service.impl;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.apache.dubbo.config.annotation.DubboService;

import org.order.common.util.ResultHolder;
import org.order.dubbo.api.domain.OrderInv;
import org.order.dubbo.api.tcc.InvService;
import org.order.tcc.persistent.dao.InvMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@DubboService
@LocalTCC
public class InvServiceImpl implements InvService {

    @Autowired
    private InvMapper invMapper;

    @Override
    @TwoPhaseBusinessAction(name = "InvServicePrePare", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean prepare(BusinessActionContext actionContext, int a) {
        String xid = String.valueOf(Math.abs(new Random().nextInt()));
        // 生成订单
        OrderInv orderInv = new OrderInv();
        orderInv.setItemId(xid);
        orderInv.setItemName("商品01");
        //  int exp=1/0;   // 设置异常点测试是否提交
        invMapper.insertInv(orderInv);

        System.out.println("InvService prepare, xid:" + RootContext.getXID() + ", a:" + a);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        System.out.println("InvService commit, xid:" + xid + ", a:" + actionContext.getActionContext("a"));
        ResultHolder.setInvResults(xid, "T");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        System.out.println("InvService rollback, xid:" + xid + ", a:" + actionContext.getActionContext("a"));
        ResultHolder.setInvResults(xid, "R");
        return true;
    }
}
