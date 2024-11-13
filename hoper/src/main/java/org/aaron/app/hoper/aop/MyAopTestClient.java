package org.aaron.app.hoper.aop;

import org.springframework.stereotype.Component;

@Component
public class MyAopTestClient {

    @MyAopAspect(name = "赵", desc = "金")
    public String doAopTest(String para) throws Exception {
        if (para.equals("1")) {
            throw new Exception("异常啦");
        }
        System.out.print(para);
        return "刚";
    }
}
