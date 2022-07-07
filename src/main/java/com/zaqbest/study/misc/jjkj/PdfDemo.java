package com.zaqbest.study.misc.jjkj;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PdfDemo {
    public static void main(String[] args) throws IOException, DocumentException {
        try {
            // pdf模板所在路径，就是网站制作好后下载的pdf模板路径
            String fileName = "C:\\Users\\Admin\\Downloads\\授权委托函-模板.pdf";
            PdfReader reader = new PdfReader(fileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);

            // 使用中文字体
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
            fontList.add(bf);


            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            fillData(fields, data());

            //必须要调用这个，否则文档不会生成的
            ps.setFormFlattening(true);
            ps.close();

            //生成pdf路径存放的路径
            OutputStream fos = new FileOutputStream("C:\\Users\\Admin\\Downloads\\aaa.pdf");
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 填充模板中的数据
     */
    public static void fillData(AcroFields fields, Map<String, String> data) {
        try {
            for (String key : data.keySet()) {
                String value = data.get(key);
                // 为字段赋值,注意字段名称是区分大小写的
                fields.setField(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 填充数据源
     * 其中data存放的key值与pdf模板中的文本域值相对应
     */
    public static Map<String, String> data() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("legalName", "法人姓名");
        data.put("legalIdNo", "11111");
        data.put("agentName", "代理人姓名");
        data.put("agentIdNo", "222222");
        data.put("orgName", "公司名称");
        data.put("orgCode", "3333");

        return data;
    }

}
