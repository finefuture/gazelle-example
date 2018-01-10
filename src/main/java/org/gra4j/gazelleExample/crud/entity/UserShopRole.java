package org.gra4j.gazelleExample.crud.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/4.
 */
@Entity(name = "UserShopRole")
@Table(name = "user_shop_role")
@DynamicUpdate
@DynamicInsert
@Data
public class UserShopRole extends BaseEntity {

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "shop_id")
    private String shopId;

    @Column(name = "user_id")
    private String userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Shop shop;
}
