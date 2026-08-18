import java.io.*;
import java.util.*;

class Packer
{
    // Packing method called by GUI
    public void PackFiles(
                             String FolderName,
                             String PackFileName

                        ) throws Exception
    {
        int iRet = 0;
        int Size = 0;
        int i = 0, j = 0;

        String header = "";

        FileOutputStream foobj = null;
        FileInputStream fiobj = null;

        byte Buffer[] = new byte[1024];
        byte bHeader[] = null;

        // Create folder object
        File fobjfolder = new File(FolderName);

        // Check folder
        if((fobjfolder.exists()) && (fobjfolder.isDirectory()))
        {
            System.out.println("Folder exists");

            // Create packed file
            File fobjpack = new File(PackFileName);

            fobjpack.createNewFile();

            // Open packed file for writing
            foobj = new FileOutputStream(fobjpack);


            // Get files from folder
            File fArr[] = fobjfolder.listFiles();

            System.out.println("Number of files in folder :" + fArr.length);


            // Allowed file extensions
            Set<String> allowedExtensions = Set.of(".txt", ".c", ".cpp", ".java");


            // Process every file
            for(i = 0; i < fArr.length; i++)
            {
                // Check whether it is a file
                if(fArr[i].isFile())
                {
                    String FileName = fArr[i].getName();

                    // Find extension
                    int iDot = FileName.lastIndexOf('.');

                    // Check whether extension exists
                    if(iDot != -1)
                    {
                        String extension = FileName.substring(iDot).toLowerCase();


                        // Check allowed extension
                        if(allowedExtensions.contains(extension))
                        {
                            System.out.println("Packing : " + FileName);


                            fiobj = new FileInputStream(fArr[i]);


                            // Create header
                            header = header + fArr[i].getName();

                            header = header + " ";

                            header = header + fArr[i].length();


                            // Make header size 100 bytes
                            Size = 100 - header.length();


                            for(j = 1; j <= Size; j++)
                            {
                                header = header + " ";
                            }


                            // Convert header to byte array
                            bHeader = header.getBytes();


                            // Write header
                            foobj.write(bHeader);


                            // Read file and write into pack file
                            while((iRet = fiobj.read(Buffer)) != -1)
                            {
                                foobj.write(Buffer, 0, iRet);
                            }


                            // Close current file
                            fiobj.close();
                        
                            // Reset header
                            header = "";
                        }
                        else
                        {
                            System.out.println("Skipped : " + FileName + " (Unsupported extension)");
                        }
                    }
                    else
                    {
                        System.out.println("Skipped : " + FileName + " (No extension)");
                    }
                }
            }


            // Close packed file
            foobj.close();


            System.out.println("Packing completed.");
        }
        else
        {
            System.out.println("There is no such folder");
        }
    }


    // Main method for CUI testing
    public static void main(String A[]) throws Exception
    {
        Packer pobj = new Packer();

        pobj.PackFiles("Data", "Backup.pak");
    }
}