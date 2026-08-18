import java.io.*;

public class Unpacker
{
    public void UnpackFile(String PackFileName) throws Exception
    {
        File fpackobj = null;

        FileInputStream fiobj = null;
        FileOutputStream foobj = null;

        byte Header[] = new byte[100];
        
        String strHeader = null;
        String Tokens[] = null;
        File NewFile = null;
        byte Buffer[] = null;
        int iRet = 0;

        fpackobj = new File(PackFileName);

        if(fpackobj.exists())
        {
            fiobj = new FileInputStream(fpackobj);

            // Read header
            while((iRet = fiobj.read(Header,0,100)) != -1)
            {
                strHeader = new String(Header);

                System.out.println("Header is :"+strHeader);

                strHeader = strHeader.trim();
                strHeader = strHeader.replaceAll("\\s+", " ");

                Tokens = strHeader.split(" ");

                System.out.println("File name : "+Tokens[0]);
                System.out.println("File size : "+Tokens[1]);

                NewFile = new File(Tokens[0]);
                NewFile.createNewFile();

                foobj = new FileOutputStream(NewFile);

                Buffer = new byte[Integer.parseInt(Tokens[1])];

                // read data
                fiobj.read(Buffer,0,Integer.parseInt(Tokens[1]));

                // write the data
                foobj.write(Buffer,0,Integer.parseInt(Tokens[1]));

                foobj.close();

            }//end of main

            fiobj.close();
        }
        else
        {
            System.out.println("There is no such pack file");
        }
    }
}