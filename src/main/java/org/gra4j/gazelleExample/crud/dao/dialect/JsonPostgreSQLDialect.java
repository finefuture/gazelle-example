package org.gra4j.gazelleExample.crud.dao.dialect;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

public class JsonPostgreSQLDialect extends PostgreSQL94Dialect {
    public JsonPostgreSQLDialect(){
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
        this.registerHibernateType(Types.OTHER, StandardBasicTypes.TEXT.getName());
    }
}
