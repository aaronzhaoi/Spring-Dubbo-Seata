package org.order.balance.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.order.balance.persistent.dao.BalanceMapper;
import org.order.dubbo.api.domain.CustomBalance;
import org.order.dubbo.api.domain.UserDTO;
import org.order.dubbo.api.facade.DubboBalanceFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;


@DubboService
public class DeductBalanceServiceImpl implements DubboBalanceFacade {


    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    public UserDTO deductBalance(UserDTO userDTO) {
        CustomBalance customBalance = new CustomBalance();

        Random random = new Random();

        customBalance.setUserId(userDTO.getUserId());
        customBalance.setBalanceId(String.valueOf(Math.abs(new Random().nextInt())));
        customBalance.setBalance(random.nextInt());
        userDTO.setCustomBalance(customBalance);

        balanceMapper.insertBalanceHis(customBalance);

        System.out.print("余额扣减完成：" + userDTO.toString());
        return userDTO;
    }
}
