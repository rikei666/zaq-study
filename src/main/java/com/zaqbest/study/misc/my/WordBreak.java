package com.zaqbest.study.misc.my;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        List<String> lines = FileUtil.readUtf8Lines("classpath:files/Friends-S01.txt");
        String fout = "Friends-S01-result.txt";

        Set<String> seen = new HashSet<>();
        List<String> words = new ArrayList<>();
        for (String line: lines){
            if (line.endsWith("Pages")){
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int cur  = 0; cur < line.length(); cur++){
                //如果是字母
                if ((
                        line.charAt(cur) == '\''
                                && (cur > 0 && CharUtil.isLetter(line.charAt(cur-1)))
                                && (cur < line.length() - 1 && CharUtil.isLetter(line.charAt(cur+1)))
                        )
                        || CharUtil.isLetter(line.charAt(cur))){
                    sb.append(line.charAt(cur));

                } else {
                    String newWord = sb.toString().toLowerCase(Locale.ROOT);
                    //非字母
                    if (newWord.length() >0 ){
                        if (!seen.contains(newWord)){
                            seen.add(newWord);
                            words.add(newWord);
                        }
                        sb = new StringBuilder();
                    }
                }
            }
        }
        System.out.println("行数:"+lines.size());
        System.out.println("单词数:"+words.size());

        FileUtil.writeUtf8Lines(words, fout);
    }

}
