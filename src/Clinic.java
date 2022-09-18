import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;


public class Clinic {

    private File patientFile;
    private int day;

    public Clinic(File file) {
        patientFile = file;
        day = 1;
    }

    public Clinic(String fileName) {
        this(new File(fileName));
    }

    //Method for appointments with patient for next day
    // format name,species,stat,day,timein,timeout,inithealth,initpainlevel

    public String nextDay(File f) throws FileNotFoundException {
        day++;
        String output = "";
        Scanner fileScan = new Scanner(f);
        Scanner input = new Scanner(System.in);
        String line = null;

        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            String[] patientsInfo = line.split(",");
            String name = patientsInfo[0];
            String species = patientsInfo[1];
            String stat = patientsInfo[2];
            String timeIn = patientsInfo[3];

            if (!(species.equals("Dog") || species.equals("Cat"))) {
                throw new InvalidPetException();
            }
            System.out.printf("Consultation for %s the %s at %s.\n", name, species, timeIn);
            double health = 0;
            int painLevel = 0;
            boolean validHealth = false;
            boolean validPain = false;
            while (!validHealth) {
                System.out.printf("What is the health of %s?\n", name);
                if (input.hasNextDouble()) {
                    health = input.nextDouble();
                    validHealth = true;
                } else {
                    input.hasNextLine();
                    System.out.println("Please enter a number");
                }
            }

            while (!validPain) {
                System.out.printf("On a scale of 1 to 10, how much pain is %s " +
                        "in right now?\n", name);
                if (input.hasNextInt()) {
                    painLevel = input.nextInt();
                    validPain = true;
                } else {
                    input.nextLine();
                    System.out.println("Please enter a number");
                }
            }

            //Create new pet object with new info
            Pet petPatient;
            switch (species) {
                case "Dog":
                    petPatient = new Dog(name, health, painLevel, Double.parseDouble(stat));
                    break;
                case "Cat":
                    petPatient = new Cat(name, health, painLevel, Integer.parseInt(stat));
                    break;
                default:
                    throw new InvalidPetException();
            }

            //Fix in case bad value

            health = petPatient.getHealth();
            painLevel = petPatient.getPainLevel();
            petPatient.speak();
            int treatmentTime = petPatient.treat();
            String timeOut = addTime(timeIn, treatmentTime);
            output += String.format("%s,%s,%s,Day %d,%s,%s,%s,%d\n", name, species, stat, day,
                    timeIn, timeOut, String.valueOf(health), painLevel);
        }
        fileScan.close();
        input.close();
        return output.trim();
    }

    //Method that takes filename string, casts to file and runs nextDay
    public String nextDay(String fileName) throws FileNotFoundException{
        return nextDay(new File(fileName));
    }

    public boolean addToFile(String patientInfo){
        //variables that handle files

        Scanner fileScan = null;
        PrintWriter filePrint = null;
        String stringOutput = "";

        try {
            //Variables that the files handle
            fileScan = new Scanner(patientFile);

            //patient info
            boolean newPatient = true;
            int firstDelimiter = patientInfo.indexOf(",");
            String name = patientInfo.substring(0,firstDelimiter);

            while (fileScan.hasNextLine()){
                String line = fileScan.nextLine();
                if(line.startsWith(name)){
                    newPatient = false;
                    int currentDelimiter = firstDelimiter;
                    for(int i=2; i<=3; i++){
                        int nextDelimiter = patientInfo.indexOf(",", currentDelimiter + 1);
                        currentDelimiter = nextDelimiter;
                    }

                    line += patientInfo.substring(currentDelimiter);
                }
                stringOutput += (line +"\n");
            }
            //if new line needed for new patient
            if(newPatient){
                stringOutput += patientInfo;
            }
            //cant read/write simultaneously close scanner to open PrintWriter
            fileScan.close();
            filePrint = new PrintWriter(patientFile);
            filePrint.print(stringOutput);
            return true;
        } catch (Exception e) {
            return false;
        }finally {
            if(fileScan != null){
                fileScan.close();
            }
            if(filePrint != null){
                filePrint.close();
            }
        }
    }

    private static String addTime(String timeIn, int treatmentTime){
        int hours = Integer.parseInt(timeIn.substring(0,2));
        int minutes = Integer.parseInt(timeIn.substring(2));
        int hoursOut = hours +(int)((minutes+treatmentTime)/60);
        int minOut = (minutes + treatmentTime) % 60;
        String output = "";
        output+=(hoursOut < 10) ? ("0" + hoursOut) : hoursOut;
        output+= (minOut <10) ? ("0" + minOut): minOut;
        return output;
    }


}