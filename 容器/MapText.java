import java.util.*;

public class MapText {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        Emp emp = new Emp("351", "张三");
        Emp emp2 = new Emp("512", "李四");
        Emp emp3 = new Emp("853", "王一");
        Emp emp4 = new Emp("125", "赵六");
        Emp emp5 = new Emp("341", "黄七");

        map.put(emp4.getE_id(),emp4.getName());
        map.put(emp5.getE_id(),emp5.getName());
        map.put(emp.getE_id(),emp.getName());
        map.put(emp2.getE_id(),emp2.getName());
        map.put(emp3.getE_id(),emp3.getName());

            Set<String>set = map.keySet();

        Iterator<String>it = set.iterator();
        System.out.println("HashMap实现的Map集合，无序");
        while(it.hasNext()){
            String str = (String)it.next();
            String name = (String)map.get(str);
            System.out.println(str + "  " + name);
        }

        TreeMap<String,String>treemap = new TreeMap<>();
        treemap.putAll(map);

        Iterator<String>iter = treemap.keySet().iterator();
        System.out.println("TreeMap实现的Map集合，键对象升序");
        while(iter.hasNext()){
            String str = (String)iter.next();
            String name = (String)treemap.get(str);
            System.out.println(str + "  " + name);
        }

    }
}
