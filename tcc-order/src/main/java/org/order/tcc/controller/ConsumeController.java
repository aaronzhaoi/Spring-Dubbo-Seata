package org.order.tcc.controller;



import org.order.tcc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ConsumeController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/doCommit")
    public String doCommit() {
        return "consumer : " + userService.doTransactionCommit();
    }

    @GetMapping(value = "/doRollback")
    public String doRollback() {
        return "consumer : " + userService.doTransactionRollback();
    }

}