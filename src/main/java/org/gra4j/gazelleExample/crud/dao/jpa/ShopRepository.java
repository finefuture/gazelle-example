package org.gra4j.gazelleExample.crud.dao.jpa;

import org.gra4j.gazelle.repository.Enum.CheckOps;
import org.gra4j.gazelle.repository.Enum.ExpressionOps;
import org.gra4j.gazelle.repository.Enum.PageType;
import org.gra4j.gazelle.repository.GazelleRepository;
import org.gra4j.gazelle.repository.annotation.comprise.And;
import org.gra4j.gazelle.repository.annotation.comprise.Order;
import org.gra4j.gazelle.repository.annotation.core.Query;
import org.gra4j.gazelle.repository.annotation.core.SqlQuery;
import org.gra4j.gazelle.repository.annotation.core.Update;
import org.gra4j.gazelle.repository.annotation.core.Where;
import org.gra4j.gazelle.repository.annotation.expression.ExpParam;
import org.gra4j.gazelle.repository.annotation.expression.Expression;
import org.gra4j.gazelle.repository.annotation.expression.ModifyParam;
import org.gra4j.gazelle.repository.annotation.expression.PageParam;
import org.gra4j.gazelleExample.crud.entity.Shop;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */
public interface ShopRepository extends GazelleRepository<Shop, String> {

    @Query(@Where(and   = @And({@Expression(ops = ExpressionOps.eq,key = "del"),
                                @Expression(ops = ExpressionOps.eq,key = "shopName")}),
//                  or    = @Or({@Expression(ops = ExpressionOps.like,key = "id",value = "%BC85"),
//                               @Expression(ops = ExpressionOps.in)}),
                  order = @Order(asc = "createTime"),
                  check = {CheckOps.checkNullValue, CheckOps.checkEmptyValue}))
    List<Shop> find (@ExpParam Object del, @ExpParam Object shopName,
                     @PageParam(PageType.first) int first);

    @SqlQuery(value = "select * from shop where del=:del order by create_time limit 10", isNative = true, result = Shop.class)
    List<Shop> find (@ExpParam("del") Integer del);

    @Update(set = {"shopName"},where = @Where(and = @And(@Expression(ops = ExpressionOps.eq, key="shopName"))))
    int update(@ExpParam Object shopName, @ModifyParam String name);

}
