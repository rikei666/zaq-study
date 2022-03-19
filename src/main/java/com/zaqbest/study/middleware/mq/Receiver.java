package com.zaqbest.study.middleware.mq;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.domain.v20180808.DomainClient;
import com.tencentcloudapi.domain.v20180808.models.CheckDomainRequest;
import com.tencentcloudapi.domain.v20180808.models.CheckDomainResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RabbitListener(queues = "domain", concurrency = "2")
@Slf4j
public class Receiver {

    @RabbitHandler
    public void process(String s) throws SQLException {
        log.info("查询域名 {} 开始", s);
//        if (!isOk(s)){
//            return;
//        }
        List<Entity>  records = Db.use().find(Entity.create("tbl_domain").set("domain_name", s));
        if (records.size() == 0){
            CheckDomainResponse response = checkDomain(s);
            if (response == null){
                throw new RuntimeException("查询失败，domain="+s);
            }

            Db.use().insert(Entity.create("tbl_domain")
                    .set("domain_name", s)
                    .set("available", response.getAvailable() ? 1 : 0)
                    .set("reason", response.getReason())
            );

            log.info("查询域名 {} 完成", s);
        }
    }

    private boolean isOk(String s) {
        boolean res;

        if (s.length() == 1){
            return true;
        } else {
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) != s.charAt(i-1)){
                return false;
            }
        }

        return true;
        }
    }

    private static CheckDomainResponse checkDomain(String domain) {
        // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
        //final Credential cred = new Credential(PropUtil.getValue("tencentSecretId"), PropUtil.getValue("tencentSecretKey"));
        Credential cred = new Credential("AKIDzpJWifHxOZpoHnjWhGsEqCHH1VpvbTu5", "ECuWnohJeYN2dqngIgofFgwxpRn50fTu");
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("domain.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            DomainClient client = new DomainClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            CheckDomainRequest req = new CheckDomainRequest();

            req.setDomainName(domain);
            // 返回的resp是一个CheckDomainResponse的实例，与请求对象对应
            CheckDomainResponse resp = client.CheckDomain(req);

            return resp;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return null;
    }
}
