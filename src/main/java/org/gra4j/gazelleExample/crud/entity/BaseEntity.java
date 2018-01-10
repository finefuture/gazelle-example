package org.gra4j.gazelleExample.crud.entity;

import lombok.Data;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    /**
     * serialVersionUID:TODO(主键id).
     * 
     * @since jdk1.8
     */
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    /** 创建时间 */
    @Column(nullable = false, columnDefinition = "timestamp default now()",name = "create_time")
    protected Date createTime;

    /** 修改时间 */
    @Column(nullable = false, columnDefinition = "timestamp default now()",name = "update_time")
    protected Date updateTime;

    @Column(nullable = false, columnDefinition = "integer default 0",name = "del")
    protected Integer del;

    @PrePersist
    public void prePersist(){
        if (ObjectUtils.isEmpty(getId()))
            setId(UUID.randomUUID().toString());
        if (ObjectUtils.isEmpty(getUpdateTime()))
            setUpdateTime(new Date());
        if (ObjectUtils.isEmpty(getCreateTime()))
            setCreateTime(new Date());
        if (ObjectUtils.isEmpty(getDel()))
            setDel(0);
    }

    @PreUpdate
    public void preUpdate() {
        if (ObjectUtils.isEmpty(getUpdateTime()))
            setUpdateTime(new Date());
    }

}
