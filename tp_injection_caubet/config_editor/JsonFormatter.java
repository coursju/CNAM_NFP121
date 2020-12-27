package config_editor;

/**
 * A terminer
 */

public class JsonFormatter implements Formatter{
    private StringBuffer sb;

    public JsonFormatter(){
        this.sb = new StringBuffer("{");

    }

    public void newTitle(String title){

    }

    public void newLine(){

    }

    public void newComment(String comment){
    }

    public void newLine(int numberLine){
    }

    public void newCommentLine(){
    }

    public void newText(String text){
    }

    public void newBeanId(int beanNumber, String completeBeanName){
    }

    public void newBeanClass(String completeBeanName, String beanClassName){
    }

    public void newBeanPropertyKey(int number, String completeBeanName, String propertyName){
    }

    public void newBeanPropertyValue(int number, String completeBeanName, String propertyName, String propertyValue){
    }

    public String get(){
        return sb.append("}").toString();
    }

    public void clear(){
        sb.setLength(0);
    }

    public void setColor(String colorHex){}

    public String getColor(){return "#000000";}
}

