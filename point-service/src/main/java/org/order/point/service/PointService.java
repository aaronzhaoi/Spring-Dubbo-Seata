package org.order.point.service;


import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface PointService {
    @TwoPhaseBusinessAction(name = "PointService", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext, int a);


    boolean commit(BusinessActionContext actionContext);


    boolean rollback(BusinessActionContext actionContext);
}
