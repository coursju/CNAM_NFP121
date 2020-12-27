package config_editor;

import java.util.Properties;
import java.io.StringReader;


public class PropertiesFormatter implements Formatter{
    private StringBuilder sb;
    private String name;

    public PropertiesFormatter(){
        this.sb = new StringBuilder();
    }

    public void newTitle(String title){
        this.sb.insert(0,title);
    }

    public void setName(String name){
        this.name = name;
    }

    public void newLine(){
        sb.append(System.lineSeparator());
    }

    public void newLine(int numberLine){
        for(int i = 0; i<numberLine;i++) newLine();
    }

    public void newComment(String comment){
        sb.append("# " + comment);
    }

    public void newCommentLine(){
        sb.append("#" + System.lineSeparator());
    }

    public void newText(String text){
        sb.append(text);
    }

    public void newBeanId(int beanNumber, String beanName){
        sb.append("bean.id."+beanNumber+"="+beanName);
    }

    public void newBeanClass(String beanName, String beanClassName){
        sb.append(beanName+".class="+beanClassName);
    }

    public void newBeanPropertyKey(int number, String completeBeanName, String propertyName){
        sb.append(completeBeanName+".property."+number+"="+propertyName);
    }

    public void newBeanPropertyValue(int number, String completeBeanName, String propertyName, String propertyValue){
        sb.append(completeBeanName+".property."+number+".param.1=" + propertyValue);   
    }

    public String get(){
        return sb.toString();
    }

    public Properties getProperties() throws Exception{
        Properties props = new Properties();
        props.load(new StringReader(this.get()));
        return props;
    }
    
    public void clear(){
        sb.setLength(0);
    }
    
    public void setColor(String colorHex){}
    public String getColor(){return "#000000";}
        
}
