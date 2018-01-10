package org.gra4j.gazelleExample.service;

import org.gra4j.gazelleExample.crud.entity.Shop;
import org.gra4j.gazelleExample.magic.MagicService;
import org.gra4j.gazelle.core.Operation;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/18.
 */
@Service
public class ShopService extends MagicService{

    public List<Map<String, Object>> get (boolean auditStatus, String shopName, int first, int max) {
        jpa.register(Shop.class, Operation.tupleQuery)
                .aggregate();
        special.init()
                .sum("del", "li")
                .all();
        where.init()
                .checkNullValue()
//                .addChecker("methodName", Checker.class)
                .eq("auditStatus", auditStatus)
                .eq("shopName", shopName)
                .asc("createTime", "updateTime")
                .desc("del","logo")
                .first(first)
                .max(max);
        return criterion.findSpecToList(special, where);
    }

    public List<Tuple> find (boolean auditStatus, int first, int max) {
        jpa.register(Shop.class, Operation.tupleQuery)
            .aggregate();
        special.init()
                .sum("del", "li")
                .all();
        where.init()
                .and()
                .like("shopName", "%food%")
                .lt("updateTime", new Date())
                .or()
                .eq("createTime", new Date())
                .like("id", "%BC85")
                .isNull("id")
                .or()
                .eq("del",0)
                .like("logo", "https%")
                .and()
                .like("description", "12")
                .having(
                    where.le_(where.sum("soldNum"), 2))
                .eq("auditStatus", auditStatus)
                .asc("createTime", "updateTime")
                .desc("del", "logo")
                .first(first)
                .max(max);
        List<Tuple> all = criterion.findSpec(special, where);
        return all;
    }

    public int update () {
        jpa.register(Shop.class, Operation.update);
        setter.set("createTime", new Date());
        where.init()
             .eq("id", "5BD0E7D5-CE2A-4A8A-9261-363BFD928FBD");
        return criterion.update(where);
    }
}
