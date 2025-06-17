
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Menu {
    JMenu menu[];
    JMenuBar bar;
    JMenuItem items[];
    BtnHandler hnd;
    JButton btn[];
    GUIManager refg;
    public Menu(GUIManager gui)
    {
        refg = gui;
        init();
    }
    private void init()
    {
        hnd = new BtnHandler(refg);
        bar = new JMenuBar();
        menu = new JMenu[5];
        
        menu[0] = new JMenu("Search");
        menu[1] = new JMenu("Favourite");
        menu[2] = new JMenu("History");
        menu[3] = new JMenu("Home");
        menu[4] = new JMenu("Firewall");
        
        bar.setBackground(Color.LIGHT_GRAY);
        
        items = new JMenuItem[5];
        btn = new JButton[5];
        
        for (int i = 0; i<5;i++)
        {
            items[i] = new JMenuItem();
            btn[i] = new JButton();
        }
        
        items[0].setText("Add Favourite");
        items[0].setActionCommand("af");
        items[0].addActionListener(hnd);
        items[0].setBackground(Color.LIGHT_GRAY);
        menu[1].add(items[0]);
        
        items[1].setText("View Favourite");
        items[1].setActionCommand("vf");
        items[1].addActionListener(hnd);
        items[1].setBackground(Color.LIGHT_GRAY);
        menu[1].add(items[1]);
       
        items[2].setText("Set Home");
        items[2].setActionCommand("set_home");
        items[2].addActionListener(hnd);
        items[2].setBackground(Color.LIGHT_GRAY);
        menu[3].add(items[2]);
        
        btn[0].setText("Search");
        btn[0].setActionCommand("search");
        btn[0].addActionListener(hnd);
        btn[0].setBackground(Color.LIGHT_GRAY);
        menu[0].add(btn[0]);
        
        btn[1].setText("History");
        btn[1].setActionCommand("history");
        btn[1].addActionListener(hnd);
        btn[1].setBackground(Color.LIGHT_GRAY);
        menu[2].add(btn[1]);
        
        items[3].setText("Add To Firewall");
        items[3].setActionCommand("add_fire");
        items[3].addActionListener(hnd);
        items[3].setBackground(Color.LIGHT_GRAY);
        menu[4].add(items[3]);
        
        items[4].setText("View Firewall");
        items[4].setActionCommand("view_fire");
        items[4].addActionListener(hnd);
        items[4].setBackground(Color.LIGHT_GRAY);
        menu[4].add(items[4]);
        
        bar.add(menu[3]);
        bar.add(menu[0]);
        bar.add(menu[2]);
        bar.add(menu[1]);
        bar.add(menu[4]);
        bar.setBackground(Color.white);
    }
    
}
