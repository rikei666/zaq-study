package com.zaqbest.study.misc;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CmbQuery {
    public static void main(String[] args) throws SQLException {
        List<List<String>> mertCodeList = new LinkedList<>();
//        mertCodeList.add(Arrays.asList("836291756510260","上海可檀服装服饰有限公司"));
//        mertCodeList.add(Arrays.asList("823000553117802","新世界..."));
//        mertCodeList.add(Arrays.asList("823000154112517","上海联华..."));
//        mertCodeList.add(Arrays.asList("8213930554100VE","厦门中福石油..."));
//        mertCodeList.add(Arrays.asList("836291770110189","上海苑丰酒店..."));
//        mertCodeList.add(Arrays.asList("836290679110118","上海至尊娱乐..."));
//        mertCodeList.add(Arrays.asList("836290472980648","上海永高美容..."));
        mertCodeList.add(Arrays.asList("113310041110007","上海地铁"));
        mertCodeList.add(Arrays.asList("802310059620516","上海华程..."));

        for(List<String> item: mertCodeList){
            Result result = queryCmbPoint(item.get(0));
            System.out.println(item.get(1) + ":"+ JSONUtil.toJsonPrettyStr(result));
        }
    }

    public static Result  queryCmbPoint(String mertCode) throws SQLException {

        String mcc = mertCode.substring(7, 11);

        //白名单商户
        List<Entity> mertWhiteList =  Db.use().findAll(Entity.create("table_cmb_merchant_whitelist").set("merchant_code", mertCode));

        //黑名单商户
        List<Entity> mertBlackList =  Db.use().findAll(Entity.create("table_cmb_merchant_blacklist").set("merchant_code", mertCode));

        //白名单MCC
        List<Entity> mccWhiteList =  Db.use().findAll(Entity.create("table_cmb_mcc_whitelist").set("mcc", mcc));

        //黑名单MCC
        List<Entity> mccBlackList =  Db.use().findAll(Entity.create("table_cmb_mcc_blacklist").set("mcc", mcc));

        //MCC信息
        List<Entity> mccInfo =  Db.use().find(Entity.create("table_bank_mcc").set("mcc", mcc));

        Result result = new Result();
        result.setMertCode(mertCode);
        result.setMertBlackList(mertBlackList.size() > 0);
        result.setMertWhiteList(mertWhiteList.size() > 0);
        result.setMccHasPoint(mccWhiteList.size() > 0);
        result.setMccNoPoint(mccBlackList.size() > 0);
        if (mccInfo.size() > 0){
            result.setMccDesc((String) mccInfo.get(0).get("mcc_desc"));
        }
        return result;
    }

}

@Data
class Result{
    Boolean mertWhiteList;

    Boolean mertBlackList;

    Boolean mccHasPoint;

    Boolean mccNoPoint;

    String mccDesc;

    String mertCode;
}