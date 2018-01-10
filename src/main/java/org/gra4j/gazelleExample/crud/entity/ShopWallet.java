package org.gra4j.gazelleExample.crud.entity;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/25.
 */
@Data
public class ShopWallet {

    /**冻结金额*/
    private Integer frozenMoney;

    /**可用余额*/
    private Integer money;
}
