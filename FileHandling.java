
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileHandling {
    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter pw = null;
    BufferedReader br = null;
    public void writeFile(String txt, String ss) 
    {
      try
      {
       pw = new PrintWriter(new BufferedWriter(new FileWriter(ss, true)));
       pw.println(txt);
      }
      catch(IOException io)
      {          
      }
      finally
      {
         pw.close();
      }
    }
    public void writeFileS(String txt, String ss) 
    {
      try
      {
       pw = new PrintWriter(new BufferedWriter(new FileWriter(ss, false)));
       if(txt.equals(""))
       {
           pw.print(txt);
       }
       else
       {
       pw.println(txt);
       }
      }
      catch(IOException io)
      {          
      }
      finally
       {
         pw.close();
       }
    }

    public Vector<String> readFile(String ss) throws IOException 
    {
    try 
    {
     br = new BufferedReader(new FileReader(ss));
     String line;
     Vector<String> fileRead = new Vector<String>();

     while ((line = br.readLine()) != null) {
         fileRead.add(line);
     }
     return fileRead;
    }
    finally
       {
           br.close();
       }
    }
    public boolean readFireWall(String ss) throws IOException 
    {
    try 
    {
     br = new BufferedReader(new FileReader(ss));
     String line;
     while ((line = br.readLine()) != null) {
         if("true".equals(line))
         {
            return true;
         }
         else
         {
             return false;
         }
     }
    }
    finally
    {
           br.close();
    }
    return false;
    }
    public void read_history(String d) throws IOException, URISyntaxException 
    {
    try 
    {
     br = new BufferedReader(new FileReader("date.txt"));
     String line;
     boolean f = true;
     while ((line = br.readLine()) != null) {
         if(line.equals(d))
         {
             f = false;
         }
    }
     if(f)
     {
         writeFile(d, "date.txt");
     }
     int count = 0;
     br = new BufferedReader(new FileReader("date.txt"));
     Vector<String> a = new Vector<String>();
     while ((line = br.readLine()) != null) {
         a.add(line);
         if(line.equals(d))
         {
             f = false;
         }
         count++;
    }
     if(f)
     {
         writeFile(d, "date.txt");
     }
        
     if(count>3)
     {
         writeFileS(a.elementAt(1), "date.txt");
         writeFile(a.elementAt(2), "date.txt");
         writeFile(a.elementAt(3), "date.txt");         
         readWriteFileForHistory("history.txt", a.elementAt(0));
     }
    }
    finally
    {
           br.close();
    }
    }
    public boolean readFileForSearch(String ss, String t) throws IOException 
    {
    try 
    {
     br = new BufferedReader(new FileReader(ss));
     String line;
     String[] temp1 = t.split("-");
     temp1[0] = temp1[0].replaceAll(" ", "");
     while ((line = br.readLine()) != null) {
         String[] temp = line.split("-");
         temp[0] = temp[0].replaceAll(" ", "");
         if (temp[0].equals(temp1[0]))
         {
             return true;
         }
     }
     return false;
    }
    finally
       {
           br.close();
       }
    }
    public void readWriteFileForHistory(String ss, String t) throws IOException  
    {
    try 
    {
     br = new BufferedReader(new FileReader(ss));
     String line;
     int count = 0;
     Vector<String> a = new Vector<String>();
     while ((line = br.readLine()) != null) {
         a.add(line);
         count++;
    }
     writeFileS("", "history.txt");
        for (String string : a) {
            if(!"".equals(a))
            {
            String[] temp1 = string.split("-");
         temp1[1] = temp1[1].replaceAll(" ", "");
         String[] temp2 = temp1[1].split(",");
         if (!temp2[0].equals(t))
         {
             writeFile(string, ss);
         }
            }
        }
    }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
        }    finally
       {
           br.close();
       }
    }
   public void newDate(String s) throws IOException
   {
       try 
    {
     br = new BufferedReader(new FileReader("history.txt"));
     String line;
     Vector<String> fileRead = new Vector<String>();
     while ((line = br.readLine()) != null) {
         fileRead.add(line);
     }
     String []a= s.split("-");
     String []a1 = fileRead.lastElement().split("-");
     a[0]=a[0].replace(" ", "");
     a1[0]=a1[0].replace(" ", "");
     if(a[0].equals(a1[0]))
     {
     fileRead.setElementAt(s,(fileRead.size()-1));
      writeFileS("", "history.txt");
        for (String string : fileRead) {
            writeFile(string, "history.txt");
        }
     }
    }
    finally
       {
           br.close();
       }
   }
}