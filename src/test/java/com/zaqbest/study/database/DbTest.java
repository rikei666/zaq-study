package com.zaqbest.study.database;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.zaqbest.study.BaseTest;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class DbTest extends BaseTest {
    @Test
    public void dbTest() throws SQLException {
        List<Entity> res =  Db.use().findAll(Entity.create("tbl_shorturl"));
        System.out.println("records:" + res.size());
    }
}
