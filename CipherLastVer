/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cipherlastver;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Sal
 */
public class CipherLastVer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MenuChoices(); //invoke main output screen  
        Scanner input = new Scanner(System.in);
        int choiceInput; //user input chosen method

        while (true) {
            System.out.print("Enter your choice: ");
            choiceInput = input.nextInt();
            if (choiceInput == 1 || choiceInput == 2 || choiceInput == 3) {
                if (choiceInput != 3) {
                    if (choiceInput == 1) {
                        Encrypt_Option();
                    } else {
                        Decrypt_Option();
                    }
                    //اعادة ادخال البيانات 
                    MenuChoices();
                } //نوقف  
                else {
                    break;
                }
            }//big if 
            else {
                //اذا اختيار المستخدم كان خاطئ
                System.out.println(choiceInput + " Invalid choice ): .\t Enter choice 1 to Encrypt \t or 2 to Decrypt \t or 3 to  Exit ");
            }
        }
    }

    private static void MenuChoices() {
        System.out.println(" A SYMMETRIC CRYPTO SYSTEM\n"
                + "======================================= \n"
                + "MAIN MENU \n"
                + "------------------------------------- \n"
                + "1. Encrypt \n"
                + "2. Decrypt \n"
                + "3. Exit \n"
                + "-----------------------------------------\n");
    }

    public static void Encrypt_Option() {
        Scanner input = new Scanner(System.in); //عبارة عن اتاحه اخذ بيانات من المستخدم عشان يختار ملف اومجلد 
        int option = 0; //varibale for choice input 
        String FileFolder = "";
        String algorithmType;
        String key = "";

        while (true) {
            System.out.println("(1) For File Or (2) For Folder ");
            System.out.print("Enter your choice: ");
            option = input.nextInt();
            if (option == 1 || option == 2) {

                if (option == 1) {
                    System.out.print("Type your file path : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithmType = input.next();
                    System.out.print("Enter key : ");
                    key = input.next();
                    if (algorithmType.equalsIgnoreCase("AES")) {
                        AES_Encrypt(key, FileFolder);
                    } else if (algorithmType.equalsIgnoreCase("DES")) {
                        DES_Encrypt(key, FileFolder);
                    }
                    break;
                } else if (option == 2) {
                    System.out.print("Type your Folder path : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithmType = input.next();
                    System.out.print("Enter key : ");
                    key = input.next();

                    File dir = new File(FileFolder);
                    for (File file : dir.listFiles())// The function returns a File array, or null value if the file object is a file
                    {
                        if (file.getName().endsWith("txt")) {
                            if (algorithmType.equalsIgnoreCase("AES"))//accept lower or upper case 
                            {
                                AES_Encrypt(key, file.getAbsolutePath());// getAbsolutePath This function returns the absolute pathname of the given file
                            } else if (algorithmType.equalsIgnoreCase("DES")) {
                                DES_Encrypt(key, file.getAbsolutePath());
                            }

                        }
                    }
                    break;
                }

            } else if (option != 1 || option != 2) {
                System.out.println("Invalid choice ):  \t  Enter choice 1 to File  \t or 2 to Folder  try agin \n");
                Encrypt_Option();
                System.out.println("");
                break;
            }

        }
    }// end Encrypt_Option

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
                AES_Decrypt(key, FileFolder);
            }
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
                        AES_Encrypt(key, file.getAbsolutePath());//
                    }
                }
//                    break;
            }

        } else {
            System.out.println("Invalid choice ):  \t  Enter choice 1 to File  \t or 2 to Folder  try agin \n");
//                    Decrypt_Option();
            System.out.println("");
//                    break;
        }
    }

    // end Decrypt_Option
    ///////////////////////////////////////////////////////////////////
    
    
    private static String getNameWithoutExtension(String filePath) {
        int index = filePath.lastIndexOf("."); //get last index of "."
        return filePath.substring(0, index); //return substring from last "." to end
    }
    
    
    ////////////////////////////////////////////////////////////////////
    public static void AES_Encrypt(String fileName, String key) {
        byte[] keyBytes = (key).getBytes(); // convert key string to byte array

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES"); //constructs a SecretKey from a byte array

        try {
            Cipher cipher = Cipher.getInstance("AES"); //Get Cipher Instance for "AES" algorithm

            String fileNameWithoutExtension = getNameWithoutExtension(fileName); //get file name without extension
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec); //Cipher initialized with key for encryption mode
            Path inputPath = Paths.get(fileName); //create path object from string file path
            byte[] data = Files.readAllBytes(inputPath); //reads All Bytes from the file path
            byte[] encrypted = cipher.doFinal(data); //Encrypts data bytes

            Path outputPath = Paths.get(fileNameWithoutExtension + ".encrypted"); //create path object from string file path
            Files.write(outputPath, encrypted); //writes byte array to output file path
            System.out.println("Done! File " + fileName + " is encrypted using AES-192");
            System.out.println("Output file is " + fileNameWithoutExtension + ".encrypted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
             System.out.println("TRY AGAIN!");
        }
    }

    
     public static void AES_Decrypt(String key, String fileName) {
     		byte[] keyBytes = (key).getBytes(); // converts key string to byte array
		
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES"); //constructs a SecretKey from a byte array
		try{
			Cipher cipher = Cipher.getInstance("AES"); //Get Cipher Instance for "AES" algorithm
			String fileNameWithoutExtension = getNameWithoutExtension(fileName); //get file name without extension
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec); //Cipher initialized with key for decryption mode
			Path inputPath = Paths.get(fileName); //create path object from string file path
			byte[] data = Files.readAllBytes(inputPath); //reads All Bytes from the file path
		    byte[] decrypted = cipher.doFinal(data); //Decrypt data bytes
		    
		    Path outputPath = Paths.get(fileNameWithoutExtension + ".decrypted"); //create path object from string file path
		    Files.write(outputPath, decrypted); //writes byte array to output file path
		    
		    System.out.println("Done! File " + fileName + " is decrypted using AES-192");
		    System.out.println("Output file is " + fileNameWithoutExtension + ".decrypted");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
     }
     
     
     
     
     
     
     
     
     
     
     ///////////////////////////////////////////////////////////////////////////////////////
     
     
      public static void DES_Encrypt(String key, String input) {        try {
            File inFile = new File(input);
            File outFile = new File(input.replace(".txt", ".encrypted"));
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFac.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            FileOutputStream stream = new FileOutputStream(outFile);
            stream.write(outputBytes);
            stream.flush();
            inputStream.close();
            stream.close();

            System.out.println("Done! File " + inFile.getName() + " is Encrypted using DES");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
      }
      
      
       public static void DES_Decrypt(String key, String input) {
        try {
            File inFile = new File(input);
            File outFile = new File(inFile.getAbsolutePath().replace(".encrypted", ".decrypted"));
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFac.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, desKey);

            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream stream = new FileOutputStream(outFile);
            stream.write(outputBytes);
            stream.flush();
            inputStream.close();
            stream.close();

            System.out.println("Done! File " + inFile.getName() + " is Decrypted using DES");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
       }
    
}
