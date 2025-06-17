import java.awt.BorderLayout;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Window extends JFrame {
    JFrame fr = new JFrame();
    JButton bt;
    BtnHandler hnd;
    MouseListner mnd;
    JTextField tx;
    GUIManager refg;
    JLabel lb;
    DefaultListModel model;
    JList bb;
   
    Window(GUIManager gui)    {
        refg = gui;
   }
    public void search_window() throws IOException
    {
        fr.dispose();
        fr = new JFrame();
        hnd = new BtnHandler(refg);
        String s = "";
        int x = 0;
        s = JOptionPane.showInputDialog("Search");
        if(s!=null && !"".equals(s))
        {
            int temp2 = hnd.extractText(s);
            JOptionPane.showMessageDialog(null,String.valueOf(temp2));
        } else {
        }
    }
    public void history_window() 
    {
        fr.dispose(); 
        fr = new JFrame("History");
        hnd = new BtnHandler(refg);
        model = new DefaultListModel();
        bb = new JList(model);
        Vector<String> tp;
        try {
            tp = refg.fl.readFile("History.txt");
            for (String line: tp)
            {
            model.addElement(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fr.setLayout(new BorderLayout());
        bb.addMouseListener(hnd);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(bb);
        bb.setLayoutOrientation(JList.VERTICAL);
        fr.getContentPane().add(scrollPane);
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     
    }
    public void view_fav()
    {
        fr.dispose(); 
        fr = new JFrame("Favourite");
        hnd = new BtnHandler(refg);
        model = new DefaultListModel();
        bb = new JList(model);
        Vector<String> tp;
        try {
            tp = refg.fl.readFile("fav.txt");
            for (String line: tp)
            {
            model.addElement(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        fr.setLayout(new BorderLayout());
        bb.addMouseListener(hnd);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(bb);
        bb.setLayoutOrientation(JList.VERTICAL);
        fr.getContentPane().add(scrollPane);
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
    }
    public void add_home()
    {
        fr.dispose();
        fr = new JFrame();
        String s = JOptionPane.showInputDialog("Set Home Page");
        
            if(s!=null && !"".equals(s) && refg.loadPage(s,refg.hnd.fire))
            {
              refg.fl.writeFileS(s, "home.txt");
            }
    }
    public void add_fire()
    {
        fr.dispose();
        fr = new JFrame();
        boolean flagr = false;
        String s = JOptionPane.showInputDialog("Add Word To Firewall");
        Vector<String> tp;
        try {
            tp = refg.fl.readFile("firewall.txt");
            for (String line: tp)
            {
             if (s.toLowerCase().equals(line.toLowerCase()))
             {
                flagr = true;
             }
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(s!=null && !"".equals(s) && flagr == false && !s.contains("http://") 
                    && !s.contains("https://") && !s.contains("http") && !s.contains("https") 
                    && !s.contains("www") && !s.contains("WWW") && !s.contains("://."))
            {
              refg.fl.writeFile(s, "firewall.txt");
            }
    }
    public void del_fire()
    {
        fr.dispose(); 
        fr = new JFrame("Firewall");
        mnd = new MouseListner(refg);
        model = new DefaultListModel();
        bb = new JList(model);
        Vector<String> tp;
        try {
            tp = refg.fl.readFile("firewall.txt");
            for (String line: tp)
            {
            model.addElement(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        fr.setLayout(new BorderLayout());     
        JScrollPane scrollPane = new JScrollPane();
        bb.addMouseListener(mnd);
        scrollPane.setViewportView(bb);
        bb.setLayoutOrientation(JList.VERTICAL);
        fr.getContentPane().add(scrollPane);
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    }
    public boolean search_fire(String url)
    {
        fr.dispose(); 
        fr = new JFrame();
        hnd = new BtnHandler(refg);
        model = new DefaultListModel();
        bb = new JList(model);
        Vector<String> tp;
        try {
            tp = refg.fl.readFile("firewall.txt");
            for (String line: tp)
            {
                if(!"".equals(line))
                {
                    if(url.toLowerCase().contains(line.toLowerCase()))
                    {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void set_home()
    {
       try {
               Vector<String> vv = refg.fl.readFile("home.txt");
               if(!vv.isEmpty())
               {
               String url = vv.elementAt(0);
                     refg.preUrl = url;
                     refg.loadPage(url,refg.hnd.fire);
                     DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                     String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                     refg.fl.writeFile(a,"History.txt");          
               }
            } catch (IOException ex) {
                Logger.getLogger(BtnHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void add_fav()
    {
        fr.dispose();
        fr = new JFrame();
        String s = JOptionPane.showInputDialog("Add Favourite");
        
            if(s!=null && !"".equals(s) && !"".equals(refg.tfAddress.getText()))
            {
            String temp = refg.tfAddress.getText()  + " - " + s;
                try {
                    if (refg.fl.readFileForSearch("fav.txt", temp) == false)
                    {
                        refg.fl.writeFile(temp, "fav.txt");
                    }
                } catch (IOException ex) {
            }
        }
    }
}
