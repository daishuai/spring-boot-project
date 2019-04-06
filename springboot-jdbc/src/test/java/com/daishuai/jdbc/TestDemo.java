package com.daishuai.jdbc;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/15 14:01
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class TestDemo {

    public static void main(String[] args) throws IllegalAccessException {
        String[] strs = new String[]{"aa","a"};
        System.out.println(commonPrefix(strs));
        TestDemo demo = new TestDemo();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);
        ListNode l2 = new ListNode(0);
        addTwoNumbers(l1,l2);

    }

    /**
     * 查找最长公共前缀
     * @param strs
     * @return
     */
    public static String commonPrefix(String[] strs) {
        StringBuffer result = new StringBuffer("");
        if (strs.length == 0) {
            return result.toString();
        }
        String first = strs[0];
        for (int i=0; i<first.length(); i++){
            char pre = first.charAt(i);
            for (String str : strs) {
                //超出其中一个字符串长度时或者相同位置的字符不相等时结束
                if (i > str.length()-1) {
                    return result.toString();
                }
                if (str.charAt(i) != pre) {
                    return result.toString();
                }
            }
            result.append(pre);
        }
        return result.toString();
    }



    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2, temp = new ListNode(0), result = new ListNode(0);
        temp = result;
        int carry = 0;
        while(p != null || q != null || carry > 0){
            int a = p == null ? 0 : p.val;
            int b = q == null ? 0 : q.val;
            int sum = carry + a + b;
            carry = sum/10;
            temp.next = new ListNode(sum%10);
            temp = temp.next;
            p = p == null ? null : p.next;
            q = q == null ? null : q.next;
        }
        return result.next;
    }

    public static void change(Person person) {
        person.setName("list");
    }
}
class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) {
        val = x;
    }
}
class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}