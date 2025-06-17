
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class MouseListner implements MouseListener {
    GUIManager refg;
    public MouseListner(GUIManager gui)
    {
        refg = gui;
    }

    public void mouseClicked(MouseEvent e) {
        String selectedItem = (String)refg.win.bb.getSelectedValue();
        
                try {
                    Vector<String> items = refg.fl.readFile("firewall.txt");
                    if(!items.isEmpty())
                    {
                        if(1==items.size())
                        {
                           if(items.lastElement().equals(selectedItem))
                            {
                                refg.fl.writeFileS("", "firewall.txt");
                            } 
                        }
                        else
                        {
                            refg.fl.writeFileS("", "firewall.txt");
                            for (String line: items)
                            {
                                if(!line.equals(selectedItem))
                                {
                                refg.fl.writeFile(line, "firewall.txt");
                                }
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
}
