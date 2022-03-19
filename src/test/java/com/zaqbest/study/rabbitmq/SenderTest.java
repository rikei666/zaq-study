package com.zaqbest.study.rabbitmq;

import cn.hutool.core.util.StrUtil;
import com.zaqbest.study.BaseTest;
import com.zaqbest.study.middleware.mq.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SenderTest extends BaseTest {

    @Autowired
    private Sender sender;

    private static List<String> domainList = Arrays.asList("art" ,"auto" ,"biz" ,"cc" ,"center" ,"chat" ,"city" ,"club" ,"cn" ,"co" ,"com" ,"company" ,"cool" ,"design" ,"email" ,"fit" ,"fun" ,"fund" ,"gold" ,"group" ,"guru" ,"host" ,"icu" ,"info" ,"ink" ,"kim" ,"life" ,"link" ,"live" ,"love" ,"ltd" ,"luxe" ,"mobi" ,"net" ,"online" ,"pet" ,"plus" ,"press" ,"pro" ,"pub" ,"red" ,"ren" ,"run" ,"shop" ,"show" ,"site" ,"social" ,"space" ,"store" ,"team" ,"tech" ,"today" ,"top" ,"tv" ,"video" ,"vip" ,"wang" ,"website" ,"wiki" ,"work" ,"world" ,"xin" ,"xyz" ,"yoga" ,"zone");

    /**
     * Method: send()
     */
    @Test
    public void testSend() throws Exception {
       for (String domain: getInterestDomain()){
           sender.send(domain);
       }
    }

    private static List<String> getInterestDomain(){
        List<String> ans = new ArrayList<>();
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String digit = "0123456789";
        String str = alpha;

        List<String> result = new ArrayList<>();
//        result.addAll(getCombination(str.toCharArray(), 1));
        result.addAll(getCombination(str.toCharArray(), 2));
        result.addAll(getCombination(str.toCharArray(), 3));
        result.addAll(getCombination(str.toCharArray(), 4));

        //result = result.stream().filter(s -> s.equals(StrUtil.reverse(s))).collect(Collectors.toList());

        for (String prefix: result){
            ans.add(prefix+".com");
        }

        return ans;
    }

    private static List<String> getCombination(char[] str, int size){
        List<String> result = new ArrayList<>();
        char[] path = new char[size];
        process(str, size, 0, path, result);
        return result;
    }

    private static void  process(char[] str, int size, int cur, char[] path, List<String> result){
        if (cur == size){
            result.add(String.valueOf(path));
        } else {
            for (int i =0; i < str.length; i++){
                path[cur] = str[i];
                process(str, size, cur+1, path, result);
            }
        }
    }
}