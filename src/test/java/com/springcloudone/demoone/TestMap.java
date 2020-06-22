package com.springcloudone.demoone;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xw
 * @date 2020/6/19 16:21
 */
public class TestMap {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("1","11");
        map.put("2","22");
        map.put("3","33");
        map.put("4","44");

        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        //并查找出在线用户的WebSocketSession（会话），将其移除（不再对其发消息了。。）
        for (Map.Entry<String, Object> entry : entrySet) {
            if (entry.getKey().equals("2")) {
                //清除在线会话
                map.remove(entry.getKey());
                //记录日志：
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
        System.out.println(map);
        Map<String, Map<String, Object>> ws = new HashMap<>();
        ws.put("1",map);
        Map<String, Object> value = ws.get("1");
        if (value != null){
            value.put("5","55");
        }else {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("6", "66");
            ws.put("1",map1);
        }
        System.out.println(ws);
    }
}
