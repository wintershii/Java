package cn.pra_01.collection_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * This program uses a set to print all unique words in System in
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();    // HashSet implements Set
        long totalTime = 0;

        try (Scanner in = new Scanner(System.in)){
            while (in.hasNext()){
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }

        Iterator<String> iter = words.iterator();
        for (int i = 0; i < 20 && iter.hasNext(); i++){
            System.out.println(iter.next());
        }
        System.out.println("...");
        System.out.println(words.size() + " distinct words. " + totalTime + "milliseconds. ");
    }
}
