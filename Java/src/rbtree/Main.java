package rbtree;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("resource/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            long startTime = System.nanoTime();

            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word)) {
                    rbt.set(word, rbt.get(word) + 1);
                } else {
                    rbt.add(word, 1);
                }
            }

            for (String word : words) {
                rbt.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("RBT perf: " + time);
            System.out.println("Total different words: " + rbt.getSize());
            System.out.println("Frequency of PRIDE: " + rbt.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + rbt.get("prejudice"));
        }

        System.out.println();
    }
}
