package config_editor;


/** Interface pour la génération du fichier de configuration selon plusieurs formats.
 * 
 */
public interface Formatter{
    
  public void newTitle(String title);
    
  public void newLine();
  public void newText(String text);
  
  public void newComment(String comment);
  public void newCommentLine();
  
  public void newBeanId(int beanNumber, String beanName);
  
  public void newBeanClass(String beanName, String beanClassName);
  
  public void newBeanPropertyKey(int number, String completeBeanName, String propertyName);
  
  public void newBeanPropertyValue(int number, String completeBeanName, String propertyName, String propertyValue);
  
  public void setColor(String colorHex);
  public String getColor();
  
  public String get();
  public void clear();
}
