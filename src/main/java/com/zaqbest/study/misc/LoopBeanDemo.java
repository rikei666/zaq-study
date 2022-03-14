package com.zaqbest.study.misc;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author lipan
 * @version 1.0.0
 * @description 功能描述
 * @date 2022/1/12 12:33
 **/
public class LoopBeanDemo {
    public static void main(String[] args) throws IllegalAccessException {
        ReqVo reqVo = new ReqVo();
        reqVo.setValue("hello");
        reqVo.setClearValue("hello11111");
        reqVo.setALong(100L);
        reqVo.setAByte(new Byte((byte) 0x11));
        reqVo.setSenList(Arrays.asList("111","2222"));
        reqVo.setStrSet(new HashSet(Arrays.asList("111","2222")));
        reqVo.setClazz(new Clazz("aaaa"));
        reqVo.setClazzList(Arrays.asList(new Clazz("cccc"),new Clazz("bbbb")));
        Map<String, String> aMap = new HashMap<>();
        aMap.put("aaa", "1111");
        aMap.put("bbbb", "2222");
        reqVo.setAMap(aMap);

        System.out.println("第1次遍历=================================");
        loop(reqVo, node -> {
            System.out.println(getSpace(node.getLevel()) + node.path + " " + node.getObject());
        });

//        loop(System.getProperties(), node -> {
//            System.out.println(getSpace(node.getLevel()) + node.path + " " + node.getObject());
//        });
    }

    public static String getSpace(int level){
        String ans = "";
        for (int i = 0; i < level; i++){
            ans += "\t";
        }

        return ans;
    }

    public static void loop(Object object, Consumer<Node> consumer) throws IllegalAccessException {
        Queue<Node> queue = new LinkedList<>() ;
        queue.add(new Node("", object, -1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (isBasicClass(node.getObject().getClass())){
                consumer.accept(node);
                continue;
            }

            if (isCollection(node.getObject().getClass())){
                Iterable iterable = (Iterable) node.getObject();
                Iterator iterator = iterable.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    Object value = iterator.next();
                    queue.add(new Node(node.path+"["+(i++)+"]" ,value, node.level+1));
                }
                continue;
            }

            if (Map.class.isAssignableFrom(node.getObject().getClass())){
                Map map = (Map) node.getObject();
                map.keySet().stream().forEach(k->{queue.add(new Node(node.path+"['"+k+"']" ,map.get(k) , node.level+1));});
                continue;
            }

            Field[] fields = node.getObject().getClass().getDeclaredFields();

            for (Field f: fields) {
                f.setAccessible(true);
                if (f.get(node.getObject()) != null) {
                    queue.add(new Node((StrUtil.isEmpty(node.path) ? "" : node.path + ".") + f.getName(), f.get(node.getObject()), node.getLevel() + 1));
                }
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Node {
        public String path;
        public Object object;
        public int level;
    }

    private static boolean isBasicClass(Class clazz){
        List<Class> basicClazzList = Arrays.asList(
                Number.class, CharSequence.class, Boolean.class, Date.class,Calendar.class,
                int.class, long.class, char.class, byte.class, boolean.class, short.class
        );

        return basicClazzList.stream().anyMatch(e->e.isAssignableFrom(clazz));
    }

    private static boolean isIgnore(String name){
        List<String> ignoreList = Arrays.asList(
          "hash", "serialVersionUID"
        );

        return ignoreList.contains(name);
    }

    private static boolean isCollection(Class clazz){
        List<Class> basicClazzList = Arrays.asList(
                Collection.class
        );

        return basicClazzList.stream().anyMatch(e->e.isAssignableFrom(clazz));
    }
}



@Data
class ReqVo {

    private String value;

    private String clearValue;

    private List<String> senList;

    private Long aLong;

    private Byte aByte;

    private Set<String> strSet;

    private Clazz clazz;

    private List<Clazz> clazzList;

    private Map<String, String> aMap;
}

@Data
@AllArgsConstructor
class Clazz {
    private String myvalue;
}