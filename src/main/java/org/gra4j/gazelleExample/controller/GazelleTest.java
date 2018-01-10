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
    public Response test () {
        return new Response().success(shopService.get(true, null, 10, 100));
    }

    @RequestMapping("test1")
    public Response test1 () {
        return new Response().success(shopService.find(true, 10, 10));
    }

    @RequestMapping("test2")
    public Response test2 () {
        return new Response().success(shopService.update());
    }

}
