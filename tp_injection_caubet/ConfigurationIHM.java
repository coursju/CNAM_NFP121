
import config_editor.IConfigGenerator;
import config_editor.ConfigGenerator;
import config_editor.PropertiesFormatter;
import config_editor.SwingPropertiesFormatter;
import config_editor.ConfigGeneratorGUI;

public class ConfigurationIHM{
 
    public static void main(String[] args){
        ConfigGeneratorGUI gui = new ConfigGeneratorGUI();
        //gui.setClassBean("question1.Table");
        gui.setClassBean("question1/");
        gui.setNumberBean(1);
        
        IConfigGenerator configGenerator = new ConfigGenerator();
        configGenerator.setFormatter(new PropertiesFormatter());
        //configGenerator.setFormatter(new SwingPropertiesFormatter());
        gui.setConfigConfigurator(configGenerator);

    }
}
