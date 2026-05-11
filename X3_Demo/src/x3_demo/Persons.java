/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package x3_demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Darius
 */
public class Persons {
    private ArrayList<Person> persons = new ArrayList<>();

    public boolean add(Person person) {
        return persons.add(person);
    }

    public void clear() {
        persons.clear();
    }

    public int size() {
        return persons.size();
    }

    public Person get(int index) {
        return persons.get(index);
    }
    
    public void showContent() {
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i).toString());
        }
    }

    public void saveToFile(String fileName) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                out.println(person.getName() + ";" + person.getMark());
            }
        }
    }
    
    public void loadFromFile(String fileName) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] items = line.split(";");
                String name = items[0];
                int mark = Integer.parseInt(items[1]);
                persons.add(new Person(name, mark));
            }
        }
    }
}
