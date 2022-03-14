package com.zaqbest.study.misc.domain;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.domain.v20180808.DomainClient;
import com.tencentcloudapi.domain.v20180808.models.CheckDomainRequest;
import com.tencentcloudapi.domain.v20180808.models.CheckDomainResponse;
import com.zaqbest.comm.utils.PropUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TencentDomain {

    private static List<String> domainList = Arrays.asList("art" ,"auto" ,"biz" ,"cc" ,"center" ,"chat" ,"city" ,"club" ,"cn" ,"co" ,"com" ,"company" ,"cool" ,"design" ,"email" ,"fit" ,"fun" ,"fund" ,"gold" ,"group" ,"guru" ,"host" ,"icu" ,"info" ,"ink" ,"kim" ,"life" ,"link" ,"live" ,"love" ,"ltd" ,"luxe" ,"mobi" ,"net" ,"online" ,"pet" ,"plus" ,"press" ,"pro" ,"pub" ,"red" ,"ren" ,"run" ,"shop" ,"show" ,"site" ,"social" ,"space" ,"store" ,"team" ,"tech" ,"today" ,"top" ,"tv" ,"video" ,"vip" ,"wang" ,"website" ,"wiki" ,"work" ,"world" ,"xin" ,"xyz" ,"yoga" ,"zone"
    );
    private static List<String> getAllStr(int size){
        String repo = "abcdefghigklmnopgrstuvwxyz1234567890";

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < repo.length(); i++){
            for (int j = 0; j < repo.length(); j++){
                String tmp = String.valueOf(repo.charAt(i))+ repo.charAt(j);
                for (String top: domainList) {
                    ans.add(tmp + "." + top);
                }
            }
        }

        return ans;
    }

    public static void main(String [] args) {
//       List<String> ans  = getAllStr(2);
//       System.out.println(ans.size());
        checkDomain("qq.com");

    }

    private static void checkDomain(String domain) {
        // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
        final Credential cred = new Credential(PropUtil.getValue("tencentSecretId"), PropUtil.getValue("tencentSecretKey"));
        try{
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
            // 输出json格式的字符串回包
            System.out.println(CheckDomainResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
