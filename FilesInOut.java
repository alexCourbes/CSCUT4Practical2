import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * CSCU9T4 Java strings and files exercise.
 */

public class FilesInOut {

    public static void main(String[] args) {

        String inputFileName = "";
        String upperCaseFlag = "";
        String outputfileName = "";


        for (int i = 0; i < 1; i++) {

            if (args[0].toUpperCase().equals("-U")) {
                upperCaseFlag = args[0];
                inputFileName = args[1];
                outputfileName = args[2];

            } else {
                inputFileName = args[0];
                outputfileName = args[1];
            }
        }


        try {


            File inputFile = new File(inputFileName);
            Scanner inFile = new Scanner(inputFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line = reader.readLine();


            Date d = null;
            while (line != null) {
                //Taking the dates from the names

                SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy"); //format what we have
                SimpleDateFormat resformatter = new SimpleDateFormat("dd/MM/yyyy"); //format that we want
                String value = line.replaceAll("[^0-9]", ""); //takes the date (erases any int)
                d = formatter.parse(value); //parse the date from the old format to the new one


                String name = line.replaceAll("[\\d.]", ""); //takes the full name (erases any non-int)
                if (upperCaseFlag.toUpperCase().equals("-U")) { //if -U flag, then turn name to uppercase :)
                    name = name.toUpperCase();
                }else {

                    name= name.substring(0, 1).toUpperCase() + name.substring(1);

                }

                appendData(name,outputfileName,resformatter,d);
                //APENDING THE DATA INTO THE NEW FILE



                line = reader.readLine(); //go to the next line of the file
            }
        } catch (IOException | ParseException e) {
            System.err.println("IOException: " + e.getMessage() + "not found");
        }
    }

    public static void appendData(String name, String outputfileName, SimpleDateFormat resformatter, Date d){
        try {
            FileWriter fw = new FileWriter(outputfileName, true); //the true will append the new data
            fw.write(name + resformatter.format(d));//appends the string to the file
            fw.write("\n"); // new line
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }


    }
