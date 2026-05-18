/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x4_staticlistvsarraylist;

import java.util.ArrayList;

/**
 *
 * @author Darius
 */
public class X4_StaticListVsArrayList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] friendsStatic = new String[4];
        ArrayList<String> alFriends = new ArrayList<>();
        
        // Fill with values
        friendsStatic[0] = "Anna";
        friendsStatic[1] = "Tom";
        friendsStatic[2] = "Darius";
        friendsStatic[3] = "Noah";
        
        alFriends.add("Anna");
        alFriends.add("Tom");
        alFriends.add("Darius");
        alFriends.add("Noah");
        
        // add a fifth friend
        // Static array: not possible to change size after declaration
        // Dynamic array: yes it is possible:
        alFriends.add("John");
        
        System.out.println(friendsStatic[1]);
        System.out.println(alFriends.get(1));
        
        System.out.println(friendsStatic.length);
        System.out.println(alFriends.size());
        
        friendsStatic[0] = "Carl";
        alFriends.set(0, "Carl");
        
        alFriends.remove("Carl");
        // Static list: Cannot remove 
        
        int size = 100000;
        int[] intArray = new int[size];
        ArrayList<Integer> alIntegers = new ArrayList<>();
        
        long start = System.nanoTime();
        for(int i = 0; i < size; i++) {
            int rn = (int)(Math.random() * size);
            intArray[i] = rn;           
        }
        
        for (int i = 0; i < 1000; i++) {
            for(int j = 0; j < intArray.length; j++) {
                Integer tmp = intArray[j];
                intArray[j] = intArray[size - 1 - j];
                intArray[size - 1 - j] = tmp;
            }
        }
        
        long stop = System.nanoTime();
    
        System.out.println("Time for static array: " + (stop - start) / 1000000.0 + "ms");
        
        start = System.nanoTime();
        for(int i = 0; i < size; i++) {
            int rn = (int)(Math.random() * size);
            alIntegers.add(rn);           
        }
        
        for (int i = 0; i < 1000; i++) {
            for(int j = 0; j < alIntegers.size(); j++) {
                Integer tmp = alIntegers.get(j);
                alIntegers.set(j, alIntegers.get(size - 1 - j));
                alIntegers.set(size - 1 - j, tmp);
            }
        }
        
        stop = System.nanoTime();
    
        System.out.println("Time for dynamic array: " + (stop - start) / 1000000.0 + "ms");
    }
}
