package org.aaron.app.hoper.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
public class MyAopAspectPoint {


    @Pointcut("@annotation(org.aaron.app.hoper.aop.MyAopAspect)")
    public void aopTest() {
                                    System.out.print("\n我是切点\n");
    }

    @Before("aopTest()")
    public void doBefore(JoinPoint joinPoint) {

        System.out.print("前置通知......\n");
        String methodName = joinPoint.getSignature().getName();
        // 获取方法参数
        Object[] args = joinPoint.getArgs();

        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method mtd = methodSignature.getMethod();

        MyAopAspect myAopAspect = mtd.getAnnotation(MyAopAspect.class);

        System.out.print(myAopAspect.desc() + "\n" + myAopAspect.name());


        // 获取方法返回值类型
        Class<?> returnType = methodSignature.getReturnType();

        System.out.println("Method：" + methodName + " called with args: " + Arrays.toString(args));
        System.out.println("Return type: " + returnType.getName());
    }

    @After("aopTest()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.print("后置通知......");
    }

    @AfterReturning(value = "aopTest()",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint,Object result) {
        System.out.print("这个是返回值："+result.toString()+"\n");
        System.out.print("返回通知......");
    }

    @AfterThrowing("aopTest()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        System.out.print("异常通知.....");
    }

    @Around("aopTest()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.print("未执行....");
        Object obj = pjp.proceed();
        System.out.print("已执行....");
        System.out.print(obj.toString());
        return obj;
    }


}
