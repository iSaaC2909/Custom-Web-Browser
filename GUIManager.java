
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Anzar
 */
public class GUIManager {
    JFrame fMain;
    JEditorPane jepMain;
    JButton btnGo,btn[];
    JTextField tfAddress;
    JPanel content,semiContent,address,TaskBar;
    BtnHandler hnd;
    Stack stk;
    Stack stk1;
    String preUrl;
    Menu main_menu;
    Window win;
    FileHandling fl;
    JRadioButton rj;
    JLabel lb;
    boolean fire1;
    Icon icon;/** Creates a new instance of GUIManager */
    public GUIManager() throws IOException {
        stk = new Stack();//for handling backward pages
        preUrl = "";
        fire1 = false;
        stk1 = new Stack();//for handling forward pages
        initGui();
    }
    private void initGui() throws IOException{
        fMain = new JFrame("Browser");
        
        content = new JPanel();
        address = new JPanel();
        semiContent = new JPanel();
        
        hnd = new BtnHandler(this);
        main_menu = new Menu(this);
        win = new Window(this);
        fl = new FileHandling();
        btn  = new JButton[10];
        
        lb = new JLabel(new ImageIcon(((new ImageIcon("8.PNG")).getImage()).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH)));
        lb.setBackground(Color.GRAY);
        
        rj = new JRadioButton();
        rj.setBackground(Color.GRAY);
        rj.setActionCommand("firewall");
        rj.addActionListener(hnd);
        boolean f = fl.readFireWall("start.txt");
        if(f==true)
        {
            rj.setSelected(true);
            fire1 = true;
            hnd.fire = true;
        }
        else
        {
            rj.setSelected(false);
            fire1 = false;
            hnd.fire = false;
        }
                
        btn[0] = new JButton(new ImageIcon(((new ImageIcon("1.PNG")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        btn[0].setBackground(Color.GRAY);
        btn[0].setBorder(new LineBorder(Color.GRAY));
        btn[0].setActionCommand("back");
        btn[0].addMouseListener(hnd);
        btn[0].addActionListener(hnd);
        btn[0].setEnabled(false);
        
        btn[1] = new JButton(new ImageIcon(((new ImageIcon("2.PNG")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        btn[1].setBackground(Color.GRAY);
        btn[1].setBorder(new LineBorder(Color.GRAY));
        btn[1].setActionCommand("forward");
        btn[1].addActionListener(hnd);
        btn[1].addMouseListener(hnd);
        btn[1].setEnabled(false);
        
        btn[2] = new JButton(new ImageIcon(((new ImageIcon("3.PNG")).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        btn[2].setBackground(Color.GRAY);
        btn[2].setBorder(new LineBorder(Color.GRAY));
        btn[2].setActionCommand("refresh");
        btn[2].addMouseListener(hnd);
        btn[2].addActionListener(hnd);
        
        
        btn[3] = new JButton(new ImageIcon(((new ImageIcon("4.PNG")).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        btn[3].setBackground(Color.GRAY);
        btn[3].setBorder(new LineBorder(Color.GRAY));
        btn[3].setActionCommand("home");
        btn[3].addMouseListener(hnd);
        btn[3].addActionListener(hnd);
        
        btn[4] = new JButton(new ImageIcon(((new ImageIcon("5.PNG")).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        btn[4].setBackground(Color.GRAY);
        btn[4].setBorder(new LineBorder(Color.GRAY));
        btn[4].setActionCommand("history");
        btn[4].addMouseListener(hnd);
        btn[4].addActionListener(hnd);
        
        btn[5] = new JButton(new ImageIcon(((new ImageIcon("6.PNG")).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        btn[5].setBackground(Color.GRAY);
        btn[5].setBorder(new LineBorder(Color.GRAY));
        btn[5].setActionCommand("vf");
        btn[5].addMouseListener(hnd);
        btn[5].addActionListener(hnd);
        
        btn[6] = new JButton(new ImageIcon(((new ImageIcon("7.PNG")).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        btn[6].setBackground(Color.GRAY);
        btn[6].setBorder(new LineBorder(Color.GRAY));
        btn[6].setActionCommand("search");
        btn[6].addMouseListener(hnd);
        btn[6].addActionListener(hnd);
        
        jepMain = new JEditorPane();
        jepMain.setBackground(Color.DARK_GRAY);
        jepMain.setEditable(false);
        jepMain.addHyperlinkListener(hnd);
        DateFormat dtf;
        dtf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fl.read_history(dtf.format(new Date()));
        } catch (URISyntaxException ex) {
            Logger.getLogger(GUIManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btnGo = new JButton(new ImageIcon(((new ImageIcon("Go.PNG")).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        btnGo.addActionListener(hnd);
        btnGo.addMouseListener(hnd);
        btnGo.setBackground(Color.GRAY);
        btnGo.setBorder(new LineBorder(Color.GRAY));
        btnGo.setActionCommand("Go");
        
        tfAddress = new JTextField(79);
        tfAddress.setForeground(Color.LIGHT_GRAY);
        tfAddress.setBackground(Color.DARK_GRAY);
        tfAddress.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tfAddress.addFocusListener(hnd);
        tfAddress.addKeyListener(hnd);
        
        address.setLayout(new FlowLayout(FlowLayout.LEFT,18,0));
        address.add(btn[0]);
        address.add(btn[1]);
        address.add(btn[2]);
        address.add(btn[3]);
        address.add(tfAddress);
        address.add(btnGo);
        address.add(btn[4]);
        address.add(btn[5]);
        address.add(btn[6]);
        address.add(lb);
        address.add(rj);
        address.setBackground(Color.GRAY);
        
        semiContent.setLayout(new BorderLayout());
        semiContent.add(main_menu.bar, BorderLayout.NORTH);
        semiContent.add(address,BorderLayout.CENTER);
       
        
        content.setLayout(new BorderLayout());
        content.setBackground(Color.red);
        content.add(semiContent, BorderLayout.NORTH);
        content.add(jepMain, BorderLayout.CENTER);
        content.add(new JScrollPane(jepMain));
        win.set_home();
        preUrl = tfAddress.getText();
       
        fMain.setContentPane(content);
        makeFrameFullSize(fMain);
        fMain.setVisible(true);
        fMain.setResizable(false);
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public boolean loadPage(String url, boolean b){
        
        if(!url.equals(""))
        {
            url = url.replaceAll(" ", "");
            if(!url.contains("http://") && !url.contains("https://") )
            {
                url = "http://"+url;
            }
        }
        if(!isURL(url))
        {
            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try{
            if(b)
            {
                if(win.search_fire(url))
                {
                  tfAddress.setText(url);
                  jepMain.setBackground(Color.white);
                  jepMain.setText("You Don't Have Access To This Site.");
                  return true;    
                }
                else
                {
                  jepMain.setPage(url);
                  tfAddress.setText(url);
                  return true;
                }
            }
            else
            {
                jepMain.setPage(url);
                tfAddress.setText(url);
                return true; 
            }
        }
        catch(IOException ioexp){
            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private void makeFrameFullSize(JFrame aFrame) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    aFrame.setSize(screenSize.width, screenSize.height-40);
}
    public boolean isURL(String url) {
  try {
     (new java.net.URL(url)).openStream().close();
     return true;
  } catch (Exception ex) { }
  return false;
}
}
