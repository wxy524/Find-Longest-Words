/*
Author: Xueyi Wang
Date: July 30 2014

Algorithm Description:
Use dynamic programming to implement this algorithm. 
1. Given a docuement includes tons of words, we build dictionary by reading words from document. 
   We chosse HashSet bing our data structure.
2. For all the words in the dictionary, we check if the word can be constructed by other words
   based on the rule (Suppose the length of word is n): 
   a) The part of word from (0, j) can be constrcuted by other words in the dictionary. We use 
      an array in the type of boolean to record if the part of word before position j can be 
      constrcut by other words in the dictionary.
   b) The part of word from (j, i) is a word in the dictionary. The record word from (0, i) can
      be constructed by other words in the dictionary.
   c) repeat a) and b) until reaching i = n.
3. Record words which can be constructed by other words by using ArrayList.
4. Find two longest words.

Improvement of time complexity:
There are two things I used here to reduce time complexity:
1. Sort dictionary by length of words in the descending order. 
2. Use dynamic programming instead of recursion in the step 2. If we choose recursion, the time 
   complexity will be exponential large. 

Future possible improvment:
I guess, instead of using HashSet being data structure of our dictionary, we can use some other 
data strcutrue, such as prefix tree. I guess it can reduce seraching time for the word from dictionary.  
*/

import java.io.*;
import java.util.*;

public class FindWords {
    public List<String> findWords(Set<String> dict, String[] words) {
        List<String> canConstructed = new ArrayList<String>();

        /*To check all the words in the dictionary*/
        for(String word: words) {
            dict.remove(word);                          //make sure the word can't constructed by itself
            if(isCanConstructed(dict, word)) {
                canConstructed.add(word);               //add the word constructed by other words into result
            }
            dict.add(word);                             //add the removed word back to the dictionary
        }
        return canConstructed;
    }

    private boolean isCanConstructed(Set<String> dict, String word) {
        boolean[] constructed = new boolean[word.length()+1];
        constructed[0] = true;
        for(int i = 1; i < word.length()+1; i++) {
            for(int j = 0; j < i; j++) {
                String temp = word.substring(j,i);
                /* word(0,j) && word(j,i) */
                if(constructed[j] && dict.contains(temp)) {                 //word(0,j) is valid and word(j,i) is a word in the dictionary
                    constructed[i] = true;                                  //record word(0,i) can be constructed.
                    break;
                }
            }
        }
        return constructed[word.length()];
    }

    public List<String> readFile(String fileName){
        List<String> list = new ArrayList<String>();
        BufferedReader br = null;        
        byte[] data = new byte[(int) new File(fileName).length()];
        
        try {FileInputStream file = new FileInputStream(fileName);
            file.read(data);
            file.close();

            StringTokenizer tokens = new StringTokenizer(new String(data));     

            while(tokens.hasMoreTokens())
            {
                list.add(tokens.nextToken());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please enter a correct filename!");         
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}