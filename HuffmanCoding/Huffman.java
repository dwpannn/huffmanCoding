
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Huffman {
    private static int[] intArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static ArrayList<Integer> freqList = new ArrayList<Integer>();
    private static String[] solution = new String[10];
    private static void printCode(treeNode root, String s) {
        boolean contains = false;

        for (int k = 0; k < 10; k += 1) {
            if (intArray[k] == root.c) {
                contains = true;
            }
        }
        if (root.left
                == null
                && root.right
                == null
                && contains) {

            solution[root.c] = " " + s;
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {
        ArrayList<Integer> inputString = new ArrayList<Integer>();
        ArrayList<Integer> freq = new ArrayList<Integer>();
        try {
            File file = new File(args[0]);
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(filereader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                for (int i = 0; i < data.length(); i++) {
                    inputString.add(Character.getNumericValue(data.charAt(i)));
                }
            }
            filereader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int n = inputString.size();
        for (int i = 0; i < 10; i += 1) {
            freq.add(i, 0);
        }
        freqList = freq;

        for (int k = 0; k < inputString.size(); k += 1) {
            freq.set(inputString.get(k), freq.get(inputString.get(k)) + 1);
        }


        PriorityQueue<treeNode> q
                = new PriorityQueue<treeNode>(n, new newComparator());

        for (int i = 0; i < 10; i++) {
            treeNode hn = new treeNode();
            hn.c = intArray[i];
            hn.data = freq.get(i);
            hn.left = null;
            hn.right = null;
                q.add(hn);
        }

        treeNode root = null;

        while (q.size() > 1) {
            treeNode x = q.peek();
            q.poll();
            treeNode y = q.peek();
            q.poll();
            treeNode f = new treeNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        printCode(root, "");
        for (int j=0; j<10; j++){
            if(freq.get(j)==0){
                solution[j] = " " + null;
            }
        }
        for (int k=0; k<10;k++){
            System.out.println(k + solution[k]);
        }
    }
}
