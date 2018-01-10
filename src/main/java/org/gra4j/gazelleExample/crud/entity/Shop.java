package org.gra4j.gazelleExample.crud.entity;

import lombok.Data;
import org.gra4j.gazelleExample.crud.dao.dialect.JsonbUserType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/8/25.
 */
@Entity(name = "Shop")
@Table(name = "shop")
@DynamicUpdate
@DynamicInsert
@Data
@TypeDef(name = "JsonDataUserType", typeClass = JsonbUserType.class)
public class Shop extends BaseEntity {

    private boolean abroad;

    private boolean auditStatus;

    private String description;

    private String logo;

    private String shopName;

    @Type(type = "JsonDataUserType")
    private ShopWallet shopWallet;

    private Integer soldNum;

    private Integer status;

}
