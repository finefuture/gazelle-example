package org.gra4j.gazelleExample.magic;

import org.gra4j.gazelle.core.Criterion;
import org.gra4j.gazelle.core.Jpa;
import org.gra4j.gazelle.core.Special;
import org.gra4j.gazelle.core.Where;
import org.gra4j.gazelle.modify.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/18.
 */
@Service
public class MagicService {

    @Autowired
    public Criterion criterion;

    @Autowired
    public Where where;

    @Autowired
    public Special special;

    @Autowired
    public Jpa jpa;

    @Autowired
    public Setter setter;

}
