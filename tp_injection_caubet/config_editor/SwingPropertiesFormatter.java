package config_editor;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.event.*;


public class SwingPropertiesFormatter extends JFrame implements Formatter{

    
    private static class Entry{
        String key;
        Object value;
        public Entry(String key, Object value){
            this.key = key; this.value = value;
        }
    }

    private ArrayList<Entry> list; // liste des entrées au format des properties
    private SynchronousQueue<StringBuilder> queue; // file de communication synchrone 
                                                   // avec le client
    private JPanel jPanel;
    private String colorHex;
    private Color  currentTextColor;

    public SwingPropertiesFormatter(){
        super("Swing properties formatter");
        this.list = new ArrayList<>();

        this.jPanel = new JPanel();
        this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.jPanel.setLayout(new GridLayout(0,2,5,5));
        // this.jPanel.setLayout(new GridBagLayout()); // à voir si ce serait pas plus adapté
        // this.gridBagConstraints = new GridBagConstraints();
        
        JScrollPane scrollPane = new JScrollPane(jPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane,BorderLayout.CENTER);
        this.setAlwaysOnTop(true);
        setLocation(100,50);
        setColor("#000000"); // black
        
    }

    public void newTitle(String title){
        super.setTitle(title);
    }
    
    public void newLine(){
      list.add(new Entry("",System.lineSeparator()));
    }

    public void newComment(String comment){
        list.add(new Entry("# " + comment,""));
    }

    public void newLine(int numberLine){
    }

    public void newCommentLine(){
        list.add(new Entry("#",System.lineSeparator()));
    }

    public void newText(String text){
        //list.add(new Entry(text,""));
    }

    public void newBeanId(int beanNumber, String completeBeanName){
        String key = "bean.id."+beanNumber +"=";
        this.jPanel.setForeground(currentTextColor);
        this.jPanel.add(new JLabel(key,JLabel.RIGHT));
        this.jPanel.add(new JLabel(completeBeanName,JLabel.LEFT));
        list.add(new Entry(key,completeBeanName));
    }

    public void newBeanClass(String completeBeanName, String beanClassName){
        String key = completeBeanName+".class=";
        this.jPanel.add(new JLabel(key,JLabel.RIGHT));
        this.jPanel.add(new JLabel(beanClassName,JLabel.LEFT));
        list.add(new Entry(key,beanClassName));
    }

    public void newBeanPropertyKey(int number, String completeBeanName, String propertyName){
        String key = completeBeanName+".property."+number+"=";
        this.jPanel.add(new JLabel(key,JLabel.RIGHT));
        this.jPanel.add(new JLabel(propertyName,JLabel.LEFT));
        list.add(new Entry(key,propertyName));
    }


    
    public void newBeanPropertyValue(int number, String completeBeanName, String propertyName, String propertyValue){
        String key = completeBeanName+".property."+number+".param.1="; 
        this.jPanel.add(new JLabel(key,JLabel.RIGHT));
        JTextField value = new JTextField(propertyValue,JLabel.LEFT);
        value.setForeground(currentTextColor);
 
        this.jPanel.add(value);
        list.add(new Entry(key,value));

    }

    private class ValidateAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try{
                StringBuilder sb = new StringBuilder();
                for(Entry entry : list){ // collecte des données
                    sb.append(entry.key);
                    if(entry.value instanceof JTextField)
                        sb.append(((JTextField) entry.value).getText());
                    else
                        sb.append(entry.value.toString());
                }
                
                queue.put(sb);
            }catch(InterruptedException ie){
                StringBuilder exception = new StringBuilder();
                exception.append("Exception: " + ie.getMessage());
                try{
                    queue.offer(exception);
                }catch(Exception e){}
            }
        }
    }

    public String get(){
        JPanel panelBottom = new JPanel();
        panelBottom.add(new JLabel("Entrez les valeurs des attributs puis "));
        JButton validate = new JButton("validez");
        validate.addActionListener(new ValidateAction());
        panelBottom.add(validate);
        add(panelBottom,BorderLayout.SOUTH);
        
        queue = new SynchronousQueue<StringBuilder>();
        
        pack();show();
        
        String res = null;
        try{
            res = queue.take().toString();
            queue.clear();
            list.clear();
            jPanel.removeAll();
            getContentPane().removeAll();
            setVisible(false);
            dispose();

        }catch(Exception e){
          res = "Exception : " + e.getMessage();
        }

        return res;
    }
    
    public void clear(){
      list.clear();
      jPanel.removeAll();

    }
    
    
    public void setColor(String colorHex){
        this.colorHex = colorHex;
        this.currentTextColor = Color.decode(colorHex);
    }
    public String getColor(){return colorHex;}
}
