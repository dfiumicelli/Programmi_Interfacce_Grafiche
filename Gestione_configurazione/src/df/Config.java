/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package df;

/**
 *
 * @author jfunc
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Config {
    //Static fields
    
    private static Config config = null;
    
    //Static Methods
    public static Config getInstance(){
        if(config == null)
            config=new Config();
        return config;
    }
    
    //Instance field
    
    private Properties properties;
    
    private Config(){
        initProperties();
    }
    
    private void initProperties(){
        BufferedReader buffRead=null;
        try {
            buffRead = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("src\\config.txt"),"ISO-8859-1"));
            
            this.properties = new Properties();
            this.properties.load(buffRead);
        }
        catch(FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,
                        "Configuration file not found, the program will be closed.",
                        "Serious ERROR",
                        JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        catch(IOException ioe) {
            JOptionPane.showMessageDialog(null,
                        "Unable to read the configuration file, the program will be closed.",
                        "Serious ERROR",
                        JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        finally {
            try {
                if (buffRead != null)
                    buffRead.close();
            }
            catch(IOException ioe) {
                ioe.printStackTrace();
            }
        } // end finally
    }
    
    public String getBackgroundColor(){
        return this.properties.getProperty("backgroundColor");
    } 
}
