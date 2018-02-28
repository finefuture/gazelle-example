package org.gra4j.gazelleExample.service;

import org.gra4j.gazelle.JPAQuery.GazelleQuery;
import org.gra4j.gazelleExample.crud.dao.jpa.ShopRepository;
import org.gra4j.gazelleExample.crud.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */
@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<Shop> get (String name) {
        return shopRepository.find(0,name,0);
    }

    public int update () {
        return GazelleQuery.update(Shop.class)
                .setter().set("shopName", "XXX").build()
                .where().eq("id","5BD0E7D5-CE2A-4A8A-9261-363BFD928FBD").build()
                .execute();
    }

    @Transactional(rollbackFor = Exception.class)
    public void testTX () {
        shopRepository.delete("014A7A16-2297-474D-B3C4-D8F9B9E976A3");
        shopRepository.delete("1111111111111");
        GazelleQuery.update(Shop.class)
                    .setter().set("shopName", "XXX").build()
                    .where().eq("id", "35847C13-40DE-4885-8FBE-2C1DD39F7860").build()
                    .execute();
    }

    public void delete (String id) {
        shopRepository.delete(id);
    }

    public List<Shop> find (Integer del) {
        return shopRepository.find(del);
    }

    public int rupdate (String name) {
        return shopRepository.update("TomLong", name);
    }

    public List<Shop> find () {

//        Shop one = (Shop) GazelleQuery.basic(Shop.class).findOne("5BD0E7D5-CE2A-4A8A-9261-363BFD928FBD");
//        System.out.println(one);
        return GazelleQuery.query().nativeQuery("select * from Shop limit 10", Shop.class);
//        return GazelleQuery.basic(Shop.class).findAll();
//        Shop save = (Shop) GazelleQuery.basic(Shop.class).save(new Shop());
//        return GazelleQuery.select(Shop.class).list();
    }


}
