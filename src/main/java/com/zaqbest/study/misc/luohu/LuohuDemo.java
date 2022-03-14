package com.zaqbest.study.misc.luohu;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LuohuDemo {
    public static void main(String[] args) {

        List<String> urlList = new ArrayList<>();
        urlList.add("http://www.shchhukou.com/2022/01/18/202201shsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/12/24/202112sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/12/02/202111xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/11/17/202111sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/10/30/202110xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/10/16/202110sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/09/29/202109xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/09/17/202109sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/09/03/202108xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/08/19/202108sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/07/30/202107xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/07/30/202107xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/07/18/202107sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/06/30/202106xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/06/16/202106sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/06/02/202105xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/05/17/202105sshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/05/01/202104xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/04/15/202104shsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/04/01/202103xshsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/03/15/202103shsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/02/26/202102shsyjrcsbbsczhkgsmd-2/");
        urlList.add("http://www.shchhukou.com/2021/02/09/202102shsyjrcsbbsczhkgsmd/");
        urlList.add("http://www.shchhukou.com/2021/02/01/202101shsyjrcsbbsczhkgsmd/");

        List<ResultModel> resultList = new LinkedList<>();
        for (String url: urlList) {
            System.out.println(StrUtil.format("开始处理：{}", url));
            List<ResultModel> tmp = getList(url);
            if (tmp.size() == 0) {
                tmp = getListOld(url);
            }
            System.out.println(StrUtil.format("处理完成：{}, size={}", url, tmp.size()));

            resultList.addAll(tmp);
        }

        String file = "result_juzhuanhu.csv";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultList.size(); i++){
            ResultModel tmp = resultList.get(i);
            sb.append(StrUtil.format("{},{},{},{}\n", tmp.date, tmp.getRange(),
                    tmp.getCorpName(), tmp.getPersonName()));
        }
        FileUtil.writeString(sb.toString(), file, CharsetUtil.CHARSET_UTF_8);
        System.out.println(resultList.size());
    }

    public static List<ResultModel> getList(String url){
        String respStr = HttpUtil.get(url);
        String date = url.substring(25, 35).replaceAll("/","_");
        Document doc = Jsoup.parse(respStr);
        Elements titleList = doc.select("div.single-content p strong");

        Elements tables = doc.getElementsByClass("table");

        List<ResultModel> resultModels = new LinkedList<>();
        for (int i = 0; i < tables.size(); i++){
            String title  = ((TextNode) titleList.get(i).childNodes().get(0)).text();

            Element table = tables.get(i);
            Elements rows = table.select("table tr");
            for (int r=1; r < rows.size(); r++){
                Element row = rows.get(r);
                String corp = row.select("td").get(1).text();;
                String name = row.select("td").get(2).text();

                //System.out.println(StrUtil.format("{}, {},{},{}", date, title, corp, name));
                resultModels.add(new ResultModel(date, title, corp, name));
            }
        }

        return resultModels;
    }

    public static List<ResultModel> getListOld(String url){
        String respStr = HttpUtil.get(url);
        String date = url.substring(25, 35).replaceAll("/","_");
        Document doc = Jsoup.parse(respStr);
        Elements titleList = doc.select("div.single-content p strong");

        Elements tables = doc.select("table");

        List<ResultModel> resultModels = new LinkedList<>();
        for (int i = 0; i < tables.size(); i++){
            String title  = titleList.get(i).text();

            Element table = tables.get(i);
            Elements rows = table.select("table tr");
            for (int r=1; r < rows.size(); r++){
                Element row = rows.get(r);
                String corp = row.select("td").get(1).text();;
                String name = row.select("td").get(2).text();

                //System.out.println(StrUtil.format("{}, {},{},{}", date, title, corp, name));
                resultModels.add(new ResultModel(date, title, corp, name));
            }
        }

        return resultModels;
    }

    @Data
    @AllArgsConstructor
    static class ResultModel{
        String date;
        String range;
        String corpName;
        String personName;
    }
}
