package org.gra4j.gazelleExample.controller;

import org.gra4j.gazelleExample.service.ShopService;
import org.gra4j.gazelleExample.util.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/5.
 */
@RestController
@RequestMapping("jpa")
public class GazelleTest {

    @Autowired
    private ShopService shopService;

    @RequestMapping("test")
    public Response test (String name) {
        return new Response().success(shopService.get(name));
    }

    @RequestMapping("test1")
    public Response test1 () {
        return new Response().success(shopService.find());
    }

    @RequestMapping("test2")
    public Response test2 () {
        return new Response().success(shopService.find(0));
    }

    @RequestMapping("test3")
    public Response test3 (String id) {
        shopService.delete(id);
        return new Response().success();
    }

    @RequestMapping("test4")
    public Response test4 () {
        return new Response().success(shopService.update());
    }

    @RequestMapping("test5")
    public Response test5 () {
        shopService.testTX();
        return new Response().success();
    }

    @RequestMapping("test6")
    public Response test6 (String name) {
        return new Response().success(shopService.rupdate(name));
    }

}
