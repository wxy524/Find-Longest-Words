import java.util.*;

public class TestForFindWords {
    public static void main(String[] args) {

        /*No file name as input*/
        if(args.length == 0) {
            System.out.println("Please enter the file path");
            System.exit(-1);
        }
        FindWords test = new FindWords();
        List<String> words = new ArrayList<String>();
       
        /*Read words from the given file*/
         words = test.readFile(args[0]);        

        /*Sort words by length in the descending order*/
        String[] sortedWords = new String[words.size()];
        for(int i = 0; i < words.size(); i++) {
            sortedWords[i] = words.get(i);
        }
        Comparator<String> cmp = new Comparator<String>() {
            public int compare(String a, String b) {
                if(a.length() < b.length()) {
                    return 1;
                } else if (a.length() == b.length()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        };
        Arrays.sort(sortedWords, cmp);

        /*Build dictionary*/
        Set<String> hs = new HashSet<String>();
        for(String str: sortedWords) {
            hs.add(str);
        }

        List<String> res = new ArrayList<String>();
        
        long startTime = System.nanoTime();

        /*Find the words can be constructed by other words*/
        res = test.findWords(hs, sortedWords);
        System.out.println("Total number of words in the document is " + words.size());
        System.out.println("The number of words can be constructed by other words is " + res.size());
        System.out.println("The longest word is " + res.get(0));
        System.out.println("The second longest word is " + res.get(1));
        long endTime = System.nanoTime();
        System.out.println("The time is " + (endTime-startTime) + " ns");
    }
}