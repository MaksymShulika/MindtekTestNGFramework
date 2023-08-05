package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    //documentation comments
    /**
     * This class reads Configuration.properties file.
     * The method hetProperty(String key) fetches the values from Configuration.properties file
     * using the kay provided as a parameter.
     */

    private static FileInputStream input;
    //----------------------------------
    private static Properties properties;


    //static block
    static {
        //* Path to the Configuration.properties file
        String path = "C:\\Users\\User\\IdeaProjects\\MindtekTestNGFramework\\src\\test\\resources\\configurations\\Configuration.properties.properties";
        try {
            //* FileInputStream gets the file from provided path
            input = new FileInputStream(path);
            //* Properties(object) loads data from the input file
            properties = new Properties();
            properties.load(input);
            //1. it checks Configuration (Absolute xpath)
        } catch (FileNotFoundException e) {
            System.out.println("###(Path for properties file is invalid)###");

            //2. it checks URL from KEY in Configuration(Latter)
        } catch (IOException e) {
            System.out.println("###(Failed to load the properties file)###");

        } finally {

            //3. We need close input after we finish using it
            try {

                //IF
                //* assert input it not = to 0
                //* make sure it work we (changed) it
                //* make sure it open
                assert input !=null;
                //* close
                input.close();

                //4.IF (false)
            } catch (IOException e) {
                //исключение происходит
                System.out.println("###(Exception occurred when truing to close input object)###");
            }
        }
    }


    //5. This method gets the value from Configuration.properties file using the provided (key)
    public static String getProperty(String key){
        //* it makes URL our cod reusable
        return properties.getProperty(key);
    }
}
