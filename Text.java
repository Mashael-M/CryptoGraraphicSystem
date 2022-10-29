
package text;
import java.io.File;
import java.io.FileInputStream;//writing data from file 
import java.io.FileOutputStream;//writing data into file 
import java.security.Key;
import java.util.*;
import javax.crypto.Cipher;//inctance form cipher library
import javax.crypto.SecretKey;//
import javax.crypto.SecretKeyFactory;//generate secret key 
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Text {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
            int choiceInput; //user input chosen method
            
             System.out.println(" A SYMMETRIC CRYPTO SYSTEM\n"
                + "======================================= \n"
                + "MAIN MENU \n"
                + "------------------------------------- \n"
                +"     WHAT DO YO WANT TO IMPLEMENT ? \n"
                + "1. Encrypt \n"
                + "2. Decrypt \n"
                + "3. Exit \n"
                + "-----------------------------------------\n");
    choiceInput = input.nextInt();
            

                System.out.print("Enter your choice: ");
                switch (choiceInput) {
                    case 1:
                        Encrypt_Option();
                         
                    case 2:
                        Decrypt_Option();
                       
                    case 3:
                        break; 
//                    default:
//                        System.out.print("try agin /n");
//                        break; 
                }
       
    }  public static void Encrypt_Option() {
        Scanner input = new Scanner(System.in); //عبارة عن اتاحه اخذ بيانات من المستخدم عشان يختار ملف اومجلد 
        int option = 0;
        String FileFolder = "";
        String algorithm;
        String key = "";

//        while (true) {
            System.out.println("(1) File (2) Folder ");
            System.out.print("Enter your choice: ");
            option = input.nextInt();
//            if (option == 1 || option == 2) {

                if (option == 1) {
                    System.out.print("Type your file name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter key : ");
                    key = input.next();
                    if (algorithm.equalsIgnoreCase("AES")) {
                        AES_Encrypt(key, FileFolder);}}
//                    break;
                    
                    
                 else if (option == 2) {
                    System.out.print("Type your Folder name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter key : ");
                    key = input.next();
                    File dir = new File(FileFolder);
                    for (File file : dir.listFiles()) {
                        if (file.getName().endsWith("txt")) {
                            if (algorithm.equalsIgnoreCase("AES")) {
                                AES_Encrypt(key, file.getAbsolutePath());

                        }
                    }
//                    break;
                }

            } else {
                System.out.println("Invalid choice ):  \t  Enter choice 1 to File  \t or 2 to Folder  try agin \n");
//                Encrypt_Option();
                System.out.println("");
//                break;
            }

        }
    
        
    // end Encrypt_Option

    public static void Decrypt_Option() {
        Scanner input = new Scanner(System.in); //عبارة عن اتاحه اخذ بيانات من المستخدم 
        int option = 0;
        String FileFolder = "";
        String algorithm;
        String key = "";
//        while (true) {

            System.out.println("(1) File (2) Folder  ");
            System.out.print("Enter your choice: ");
            option = input.nextInt();
            

                if (option == 1) {
                    System.out.print("Type your file name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter  key : ");
                    key = input.next();
                    if (algorithm.equalsIgnoreCase("AES")) {
                        AES_Decrypt(key, FileFolder);}
//                    break;
                    
                } else if (option == 2) {
                    System.out.print("Type your Folder name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter  key : ");
                    key = input.next();
                    File dir = new File(FileFolder);
                    for (File file : dir.listFiles()) {
                        if (file.getName().endsWith("encrypted")) {
                            if (algorithm.equalsIgnoreCase("AES")) {
                                AES_Decrypt(key, file.getAbsolutePath());//
                        }
                    }
//                    break;
                    }

                }
                
                else {
                    System.out.println("Invalid choice ):  \t  Enter choice 1 to File  \t or 2 to Folder  try agin \n");
//                    Decrypt_Option();
                    System.out.println("");
//                    break;
                }
                }
            
        
    // end Decrypt_Option

    public static void AES_Encrypt(String key, String input) {
        try {
            File inFile = new File(input);
            File outFile = new File(input.replace(".txt", ".encrypted"));
            Key secretKey = new SecretKeySpec(key.getBytes(), 0, 24, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outFile);
            outputStream.write(outputBytes);
            outputStream.flush();
            inputStream.close();
            outputStream.close();

            System.out.println("Done! File " + inFile.getName() + " is encrypted using AES - 192");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public static void AES_Decrypt(String key, String input) {
        try {
            File inFile = new File(input);
            File outFile = new File(inFile.getAbsolutePath().replace(".encrypted", ".decrypted"));
            Key secretKey = new SecretKeySpec(key.getBytes(), 0, 24, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outFile);
            outputStream.write(outputBytes);
            outputStream.flush();
            inputStream.close();
            outputStream.close();

            System.out.println("Done! File " + inFile.getName() + " is decrypted using AES - 192");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}