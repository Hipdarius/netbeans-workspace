/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x3_demo;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Darius
 */
public class X3_Demo {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Persons persons = new Persons();
        
        persons.add(new Person("Patrick", 60));
        persons.add(new Person("Marco", 30));
        persons.add(new Person("Malou", 45));
        
        File currentFile = new File(System.getProperty("user.dir"));
        String fileName = new File(currentFile, "friendsbook.txt").getPath();
        JFileChooser fc = new JFileChooser(currentFile);
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            currentFile = fc.getSelectedFile();
            fileName = currentFile.getPath();
        }
        
        persons.saveToFile(fileName);
        persons.clear();
        
        System.out.println("Reading data from file");
        persons.loadFromFile(fileName);
        persons.showContent();
    }
}
