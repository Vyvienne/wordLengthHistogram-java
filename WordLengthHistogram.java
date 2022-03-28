
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vyvienne
 */

public class WordLengthHistogram {

    public static void main(String []args) throws FileNotFoundException, IOException{

        try{        // Use try and catch when opening the file

            //read the file
            File file = new File("/Users/userA/Desktop/readText.txt");
            Scanner input = new Scanner(file);

            String word="";
            int wordcount=0;        //initial count of word length
            int maxCount=0;             //initial maxCount of the word length
            int[]array = new int[8];

            while(input.hasNextLine()){     //there is words inside the input(file)

                word = input.nextLine();            //read the word
                wordcount = word.length();              //count the word length


                if (wordcount > maxCount){         // iF the count of word length is higher than the maxCount
                    maxCount = wordcount;               //maxCount would become the number of count, this is to ensure the output printed
                    // does not exceed the count of words in the file have.
                }
                while(wordcount >= array.length){        //whenever the wordcount exceed array length, double the array.

                    //Object and class, refer to the DoubleArr method inside DoubleArrayLength class
                    DoubleArrayLength add = new DoubleArrayLength();
                    array = add.DoubleArr(array);     //use the DoubleArr method in DoubleArrayLength class

                }

                //Object and class, refer to the Count method inside WordCount class
                WordCount countWords = new WordCount();
                array = countWords.Count(word,array); //pass the word to the class and return array.
            }

            //Object and class, refer to print method inside PrintOutput class
            PrintOutput print = new PrintOutput();
            print.print(array, maxCount);//print the output

            //Object and class, refer to the saveFile method inside SaveFile class
            SaveFile save = new SaveFile();
            save.saveFile(array, maxCount);     //save output in file

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());     //to catch the error
        }

    }

    static class WordCount{

        public int[] Count(String word,int[]array){

            int count = word.length();      //count the length of word

            array[count] += 1;         //put the count into the array, and increase the array

            return array;           //return the updated array.

        }
    }

    static class PrintOutput{
        public void print(int[]array,int highest){      // value of int[]array and int highest would be appointed
                                                        // through class object in main method

            for(int i=0;i<=highest;i++){                //start the output from 0 (i=0) and
                // print the output to the point it reach highest number of letter in the file(word of length)

                System.out.println("Word of length "+(i)+"    "+array[i]);
            }
        }
    }

    static class SaveFile{
             // Save the printed output in a file.
            // Use try and catch when writing into a file.

        public void saveFile(int[]array1,int highest){
            try{        //use try and catch to write to new file

                FileWriter myWriter = new FileWriter("/Users/userA/Desktop/output.txt");
                for(int i=0;i<=highest;i++)                              //start the output from 0 (i=0) and
                                                     // print the output to the point it reach highest number of letter in the file(word of length)

                    myWriter.write("word of length " + (i) + "   " + array1[i] + "\n");

                myWriter.close();

            }catch(Exception e){
                System.out.print(e.getMessage());
            }
        }
    }

    static class DoubleArrayLength {


        public int[] DoubleArr(int[] word) {

            int[] array = new int[word.length * 2];           // array would be the word.length * 2

            int[] d = new int[array.length];              // array d would be the length of array

            for (int i = 0; i < word.length; i++) {

                d[i] = array[i];         //copy the array

            }

            return array;     //return the array that has already *2
        }

    }

}
