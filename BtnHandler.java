
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class BtnHandler implements ActionListener, HyperlinkListener, KeyListener, MouseListener, FocusListener{
    GUIManager refg;
    boolean flag,flag1,flag2 = false;
    boolean fire = false;
    
    public BtnHandler(GUIManager gui)
    {
        refg = gui;
    }
    public void actionPerformed(ActionEvent e) {
        String btnText = e.getActionCommand();
       if("Go".equals(btnText) )
       {
            if ("".equals(refg.preUrl))
            {
                refg.preUrl = refg.tfAddress.getText();
                refg.loadPage(refg.tfAddress.getText(),fire);
                DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                refg.fl.writeFile(a,"History.txt");                
            }
            else{
                boolean fl=false;
                fl = refg.loadPage(refg.tfAddress.getText(),fire);
                if(!equals(refg.preUrl,refg.tfAddress.getText()) && fl)
                {
                    refg.stk.push(refg.preUrl);
                    refg.preUrl = refg.tfAddress.getText();
                    refg.stk1.clear();
                    refg.btn[1].setEnabled(false);
                    flag2 = false;
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    refg.fl.writeFile(a,"History.txt");
                    refg.btn[0].setEnabled(true);
                    flag1 = true;
                }
                else if(equals(refg.preUrl,refg.tfAddress.getText()) && fl)
                {
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    try {
                        refg.fl.newDate(a);
                    } catch (IOException ex) {
                        Logger.getLogger(BtnHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }   
        }
       else if ("back".equals(btnText))
       {
           if(!refg.stk.isEmpty())
           {
                String temp = refg.stk.pop().toString();
                refg.stk1.push(refg.tfAddress.getText());
                refg.loadPage(temp,fire);
                refg.preUrl = temp;
                refg.btn[1].setEnabled(true);
                flag2 = true;
           }
           if(refg.stk.isEmpty())
           {
               refg.btn[0].setEnabled(false);
               flag1 = false;
           }
       }
       else if ("forward".equals(btnText))
       {
           if (!refg.stk1.isEmpty())
           {
                String temp = refg.stk1.pop().toString();
                refg.stk.push(refg.tfAddress.getText());
                refg.loadPage(temp,fire);
                refg.preUrl = temp;
                refg.btn[0].setEnabled(true);
                flag1 = true;
           }
           if (refg.stk1.isEmpty())
           {
             refg.btn[1].setEnabled(false);
             flag2 = false;
           }
       }
       else if ("search".equals(btnText))
       {  try {
                refg.win.search_window();
            } catch (IOException ex) {}   
       }
       else if ("history".equals(btnText))
       {
           refg.win.history_window();
       }
       else if ("home".equals(btnText))
       {
            try {
               Vector<String> vv = refg.fl.readFile("home.txt");
               if(!vv.isEmpty())
               {
               String url = vv.elementAt(0);
               
               if ("".equals(refg.preUrl))
               {
                refg.preUrl = url;
                refg.loadPage(url,fire); 
                DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                refg.fl.writeFile(a,"History.txt");
                }
            else{
                boolean fl=false;
                fl = refg.loadPage(url,fire);
                if(!equals(refg.preUrl,url) && fl)
                {
                    refg.stk.push(refg.preUrl);
                    refg.preUrl = url;
                    refg.stk1.clear();
                    refg.btn[1].setEnabled(false);
                    flag2 = false;
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    refg.fl.writeFile(a,"History.txt");
                    refg.btn[0].setEnabled(true);
                    flag1 = true;
                }
                else if(equals(refg.preUrl,url) && fl)
                {
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    try {
                        refg.fl.newDate(a);
                    } catch (IOException ex) {
                        Logger.getLogger(BtnHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
               }
            } catch (IOException ex) {
                Logger.getLogger(BtnHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else if ("set_home".equals(btnText))
       {
            refg.win.add_home();
       }
       else if ("vf".equals(btnText))
       {
            refg.win.view_fav();
       }
       else if ("af".equals(btnText))
       {
            refg.win.add_fav();
       }
       else if ("firewall".equals(btnText))
       {
           if(refg.rj.isSelected())
           {
               refg.fl.writeFileS("true", "start.txt");
               fire = true;
               refg.fire1 = true;
               
           }
           else
           {
               refg.fl.writeFileS("false", "start.txt");
               fire = false;
               refg.fire1 = false;
           }
          
       }
       else if ("refresh".equals(btnText))
       {
           if (!"".equals(refg.preUrl))
            {
                refg.loadPage(refg.preUrl,fire);
            }
       }
       else if ("add_fire".equals(btnText))
       {
           refg.win.add_fire();
       }
       else if ("view_fire".equals(btnText))
       {
           refg.win.del_fire();
       }
    }

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
        {
            boolean fl = refg.loadPage(e.getURL().toString(),fire);
            if(!equals(refg.preUrl,refg.tfAddress.getText()) && fl)
            {
                refg.stk.push(refg.preUrl);
                refg.preUrl = e.getURL().toString();
                refg.stk1.clear();
                refg.btn[1].setEnabled(false);
                flag2 = false;
                DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                refg.fl.writeFile(a,"History.txt");
                refg.btn[0].setEnabled(true);
                flag1 = true;
            }
            else if(equals(refg.preUrl,refg.tfAddress.getText()) && fl)
                {
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    try {
                        refg.fl.newDate(a);
                    } catch (IOException ex) {
                        Logger.getLogger(BtnHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    }

    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER && flag == true)
        {
           if ("".equals(refg.preUrl))
            {
                refg.preUrl = refg.tfAddress.getText();
                refg.loadPage(refg.tfAddress.getText(),fire);
                DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                refg.fl.writeFile(a,"History.txt");  
            }
            else{
                boolean fl=false;
                fl = refg.loadPage(refg.tfAddress.getText(),fire);
                if(!equals(refg.preUrl,refg.tfAddress.getText()) && fl)
                {
                    refg.stk.push(refg.preUrl);
                    refg.preUrl = refg.tfAddress.getText();
                    refg.stk1.clear();
                    refg.btn[1].setEnabled(false);
                    flag2 = false;
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    refg.fl.writeFile(a,"History.txt");
                    refg.btn[0].setEnabled(true);
                    flag1 = true;
                }
                else if(equals(refg.preUrl,refg.tfAddress.getText()) && fl)
                {
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    try {
                        refg.fl.newDate(a);
                    } catch (IOException ex) {
                        Logger.getLogger(BtnHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              } 
            }
        }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource()!= refg.btn[0] && e.getSource()!= refg.btn[1] && e.getSource()!= refg.btn[2] &&
                e.getSource()!= refg.btn[3] &&e.getSource()!= refg.btn[4] && e.getSource()!= refg.btn[5]
                && e.getSource()!= refg.btn[6] && e.getSource()!= refg.btnGo)
        {
            String selectedItem = (String) refg.win.bb.getSelectedValue();
        String[] items = null;
        if(selectedItem!=null)
        {
            items = selectedItem.split("-");
        }
        if(items!=null)
        {
        if ("".equals(refg.preUrl))
            {
                refg.preUrl = items[0];
                refg.loadPage(items[0],refg.fire1);
                DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                refg.fl.writeFile(a,"History.txt");     
            }
            else{
                boolean fl=false;
                fl = refg.loadPage(items[0],refg.fire1);
                if(!equals(refg.preUrl,items[0]) && fl)
                {
                    refg.stk.push(refg.preUrl);
                    refg.preUrl = items[0];
                    refg.stk1.clear();
                    refg.btn[1].setEnabled(false);
                    flag2 = false;
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    refg.fl.writeFile(a,"History.txt");
                    refg.btn[0].setEnabled(true);
                    flag1 = true;
                }
                else if(equals(refg.preUrl,items[0]) && fl)
                {
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss aa"); 
                    String a = refg.tfAddress.getText() +" - " + dtf.format(new Date()).toString();
                    try {
                        refg.fl.newDate(a);
                    } catch (IOException ex) {
                        Logger.getLogger(BtnHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
      }
       
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == refg.btn[0] && flag1 == true)
        {
            refg.btn[0].setBackground(Color.lightGray);
        }
        else if (e.getSource() == refg.btn[1] && flag2 == true)
        {
        refg.btn[1].setBackground(Color.lightGray);
        }
        else if (e.getSource() == refg.btn[2])
        {
        refg.btn[2].setBackground(Color.lightGray);
        }
        else if (e.getSource() == refg.btn[3])
        {
        refg.btn[3].setBackground(Color.lightGray);
        }
        else if (e.getSource() == refg.btn[4])
        {
        refg.btn[4].setBackground(Color.lightGray);
        }
        else if (e.getSource() == refg.btn[5])
        {
        refg.btn[5].setBackground(Color.lightGray);
        }
        else if (e.getSource() == refg.btn[6])
        {
        refg.btn[6].setBackground(Color.lightGray);
        }
        else if (e.getSource() == refg.btnGo)
        {
        refg.btnGo.setBackground(Color.lightGray);
        }
        
    }

    public void mouseExited(MouseEvent e) {
        if(e.getSource() == refg.btn[0])
        {
            refg.btn[0].setBackground(Color.GRAY);
        }
        else if (e.getSource() == refg.btn[1])
        {
        refg.btn[1].setBackground(Color.GRAY);
        }
        else if (e.getSource() == refg.btn[2])
        {
        refg.btn[2].setBackground(Color.GRAY);
        }
        else if (e.getSource() == refg.btn[3])
        {
        refg.btn[3].setBackground(Color.GRAY);
        }
        else if (e.getSource() == refg.btn[4])
        {
        refg.btn[4].setBackground(Color.GRAY);
        }
        else if (e.getSource() == refg.btn[5])
        {
        refg.btn[5].setBackground(Color.GRAY);
        }
        else if (e.getSource() == refg.btn[6])
        {
        refg.btn[6].setBackground(Color.GRAY);
        }
        else if (e.getSource() == refg.btnGo)
        {
        refg.btnGo.setBackground(Color.GRAY);
        }
    }

    public void focusGained(FocusEvent e) {
        flag = true;
    }

    public void focusLost(FocusEvent e) {
        flag = false;
    }
    public int extractText(String ss) throws IOException
    {
        ss = ">"+ss;
      String result = refg.jepMain.getText();
      boolean f = true;
      int count=0,i = 0;
      String a[] = result.split("[\\r\\n]+");
        for (String string : a) {
            
        i =0;
      while(i<string.length())
      {
          if(string.charAt(i)=='<')
          {
              f = false;
          }
          else if(string.charAt(i)=='>')
          {
              f = true;
          }
          if(f)
          {
              int x = string.indexOf(ss, i);
              if(x!=-1 && x<string.length())
              {
                  i = x + ss.length();
                  count++;
              }
              else
              {
                  i++;
              }
          }
          else
          {
          i++;
          }
      }
        }
        return count;
    }
    
    public boolean equals(String s, String s2)
    {
        if(s.equals(s2))
        {
            return true;
        }
        else
        {
            s = s.toLowerCase();
            s2 = s2.toLowerCase();
            s = s.replace("www.", "");
            s2 = s2.replace("www.", "");
            s = s.replace(" ", "");
            s2 = s2.replace(" ", "");
            s = s.replace("http://", "");
            s2 = s2.replace("http://", "");
            s = s.replace("https://", "");
            s2 = s2.replace("https://", "");
            if(s.equals(s2))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

 
}