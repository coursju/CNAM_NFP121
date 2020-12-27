package config_editor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Insets;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/** Interface graphique d'aide à la génération du fichier de configuration.
 * Le texte de configuration est généré dans la fenêtre principale, il suffit
 * ensuite de la compléter avec les valeurs des attributs laissés vacants. Ces valeurs
 * peuvent être des noms de bean de ce même fichier ou 
 * des valeurs pour les types tableaux ou bien des types primitifs (int/Integer,...).
 * 
 * @author jean-michel Douin
 * @version Mars et Juin 2019
 * @see config_editor.ConfigGenerator
 */
public class ConfigGeneratorGUI extends JFrame{

    private JTextField  numberBeanField, prefixBeanField;
    private JTextField  classBeanField;
    private JTextArea   configTextArea;
    private JCheckBox   commentButton, propertiesButton, swingButton, htmlButton;

    private IConfigGenerator configGenerator;

    public void setConfigConfigurator(IConfigGenerator  configGenerator){
        if(configGenerator== null) 
            this.configGenerator = new ConfigGenerator(); // ou erreur ?
        else 
            this.configGenerator = configGenerator;

        if(configGenerator.getFormatter()==null) this.configGenerator.setFormatter(new PropertiesFormatter());
        // couplage fort ici, mais comment s'en affranchir ? à l'aide d'un fichier de config ?
        propertiesButton.setSelected(configGenerator.getFormatter() instanceof PropertiesFormatter);
        swingButton.setSelected(configGenerator.getFormatter() instanceof SwingPropertiesFormatter);
        htmlButton.setSelected(configGenerator.getFormatter() instanceof HtmlFormatter);
    }

    public void setNumberBean(int number){
        if(number<=0) number=1;
        this.numberBeanField.setText(Integer.toString(number));
    }

    public void setClassBean(String classBean){
        this.classBeanField.setText(classBean);
    }

    public ConfigGeneratorGUI(){
        super("Interface d'aide à l'édition du fichier de configuration, Juin 2019");

        setLocation(10,10);
        setLayout(new BorderLayout(20,20));
        setAlwaysOnTop(true);
        JPanel panel = new JPanel();
        panel.setFont(new Font("Serif", Font.PLAIN, 16));
        panel.setLayout(new FlowLayout());
        
        // ajout du numéro du bean et du prefix éventuiel de chaque bean
        JPanel panelIdAndPrefix = new JPanel();
        panelIdAndPrefix.setLayout(new GridLayout(2,2));
        
        JLabel label = new JLabel(" bean.id. ", JLabel.RIGHT);
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setForeground(Color.RED);
        label.addMouseListener(new DoubleClickActionClear());
        numberBeanField = new JTextField("1",2);
        numberBeanField.setEnabled(true);
        panelIdAndPrefix.add(label);panelIdAndPrefix.add(numberBeanField);
        
        label = new JLabel(" prefix: ", JLabel.RIGHT);
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setForeground(Color.RED);
        prefixBeanField = new JTextField("pre_",2);
        prefixBeanField.setEnabled(true);
        panelIdAndPrefix.add(label);panelIdAndPrefix.add(prefixBeanField);
        
        panel.add(panelIdAndPrefix);
        
        // ajout du champ pour le nom de la classe/bean ou du répertoire/paquetage
        JLabel classBeanFieldLabel = new JLabel(" beanClassName or directory: ", JLabel.RIGHT);
        classBeanFieldLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        classBeanFieldLabel.setForeground(Color.BLUE);
        classBeanField = new JTextField(20);
        classBeanField.setEnabled(true);
        classBeanField.setText("syntaxe_exemples/");
        panel.add(classBeanFieldLabel);
        panel.add(classBeanField);

        
        JPanel panelCheckBox = new JPanel();
        panelCheckBox.setLayout(new GridLayout(4,1));
        // ajout des différentes options
        // le fichier commenté ou non
        commentButton = new JCheckBox("Comment");
        commentButton.setMnemonic(KeyEvent.VK_C); 
        commentButton.setSelected(true);
        panelCheckBox.add(commentButton);

        // en Properties, par défaut
        ButtonGroup jCheckBoxGroup = new ButtonGroup();
        propertiesButton = new JCheckBox("Properties");
        propertiesButton.setMnemonic(KeyEvent.VK_P); 
        propertiesButton.setSelected(true);
        propertiesButton.addItemListener(new PropertiesButtonAction());
        jCheckBoxGroup.add(propertiesButton);
        panelCheckBox.add(propertiesButton);

        // avec une interface de saisie des valeurs des attributs en Swing
        swingButton = new JCheckBox("Swing");
        swingButton.setMnemonic(KeyEvent.VK_S); 
        swingButton.setSelected(false);
        swingButton.addItemListener(new SwingButtonAction());
        jCheckBoxGroup.add(swingButton);
        panelCheckBox.add(swingButton);

        // avec une génération de la configuration par un formulaire HTML,
        htmlButton = new JCheckBox("Html");
        htmlButton.setMnemonic(KeyEvent.VK_H); 
        htmlButton.setSelected(false);
        htmlButton.addItemListener(new HtmlButtonAction());
        jCheckBoxGroup.add(htmlButton);
        panelCheckBox.add(htmlButton);
        panel.add(panelCheckBox);

        JButton generateButton = new JButton("generate");
        ActionListener generateConfigAction = new GenerateConfigAction();
        generateButton.addActionListener(generateConfigAction); // un click
        classBeanField.addActionListener(generateConfigAction); // ou la touche Entrée
        panel.add(generateButton);

        this.configTextArea = new JTextArea(20,20);
        add(panel, BorderLayout.NORTH);
        panel.addMouseListener(new DoubleClickActionClear());

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel,BorderLayout.WEST);

        configTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        configTextArea.setMargin( new Insets(10,10,10,10) ); 
        configTextArea.addMouseListener(new DoubleClickActionClear());

        JScrollPane scrollPane = new JScrollPane(configTextArea);
        add(scrollPane,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configTextArea.setText(description);
        configTextArea.setCaretPosition(0);
        pack();show();
    }

    private static final String description =
        "  ConfigGeneratorGUI est une interface d'aide à la génération des fichiers de configuration.\n\n" +

        "  - En entrée, un nom de classe(bean) ou un paquetage(répertoire), en sortie un texte de configuration.\n" +
        "  - Chaque mutateur(setter) de chaque bean est détecté et l'outil engendre les items attendus pour le\n"+
        "fichier de configuration, un mutateur est une méthode qui respecte les conventions habituelles.\n" +
        "Un numéro de bean par fichier .class est attribué, les valeurs des attributs doivent ensuite être\n" +
        "renseignées afin de produire un fichier de configuration avec les valeurs des attributs attendues.\n" +
        "  - Sont générées, les valeurs par défaut pour les types primitifs et leur \"wrapper\",\n" +
        "et un identifiant de ce qui est attendu comme valeur d'attribut: a_ClassName_bean, an_array, etc. .\n"+
        "  - L'option \"Swing\" présente une interface additionnelle afin de saisir les valeurs attendues,\n"+
        "  - L'option \"Html\" engendre un formulaire en HTML, à destination d'un service web à créér.\n\n" +

        "  - En exemple, la classe syntaxe_exemples.ListeDeTables, Option Properties seule,\n"+
        "il suffit ensuite de copier ce texte et de remplacer le littéral (ou d'utiliser l'option swing)\n" +
        "par ce qui est attendu cf. <--\n\n" +
        "  - Ci-dessous, la configuration issue de la classe syntaxe_exemples.ListeDeTables : \n\n" +
        "\tbean.id.7=listeDeTables\n" +
        "\tlisteDeTables.class=syntaxe_exemples.ListeDeTables\n" +
        "\tlisteDeTables.property.1=operand\n" +
        "\tlisteDeTables.property.1.param.1=0    <--\n" +
        "\tlisteDeTables.property.2=table\n" +
        "\tlisteDeTables.property.2.param.1=a_Table_bean  <--\n" +
        "\tlisteDeTables.property.3=tables\n" +
        "\tlisteDeTables.property.3.param.1=an_array_a_Table_bean <--\n\n" +
        "  - Cette configuration est déduite de la Classe: syntaxe_exemples.ListeDeTables, ci-dessous \n" +
        "\tpublic class ListeDeTables{\n" +
        "\t  private List<Table> liste;\n" +
        "\t  public ListeDeTables(){\n" +
        "\t    this.liste = new ArrayList<Table>();\n" +
        "\t  }\n" +
        "\t  public void setTable(Table t){\n" +
        "\t    liste.add(t);\n" +
        "\t  }\n" +
        "\t  public void setTables(Table[] tables){\n" +
        "\t    for(Table t : tables)\n" +
        "\t      setTable(t);\n" +
        "\t    }\n" +
        "\t  }\n" +
        "\t  // ... \n" +
        "\t}\n\n" +
        "Notes: - Un double click dans cette fenêtre efface son contenu,\n" +
        "si le mode Comment(Alt-C) est validé, des commentaires sont ajoutés au texte généré,\n" +
        "si le mode Swing(Alt-S) est sélectionné, une interface supplémentaire permet la saisie des valeurs des attributs,\n" +
        "si le mode Html(Alt-H) est sélectionné, un formulaire HTML est engendré.\n\n" +
        " - Cette interface, son code restent perfectibles. N'hésitez pas à m'envoyez vos remarques, idées, modifications, etc... .\n\n" +
        "     Bonne utilisation de ce générateur de configurations pour femtoContainer ! douin@cnam.fr.\n";

    private class GenerateConfigAction implements ActionListener{
        public void actionPerformed(ActionEvent unused){
            try{
                int beanNumber = 1;
                try{
                    beanNumber = Integer.parseInt(numberBeanField.getText());
                    if(beanNumber<=0) beanNumber=1;
                }catch(Exception e){
                    numberBeanField.setText("1");
                };
                String prefixBeanName = null;
                try{
                    prefixBeanName = prefixBeanField.getText().trim();
                }catch(Exception e){
                   prefixBeanName = "";
                }
                configGenerator.setBeanNumber(beanNumber);
                configGenerator.setBeanPrefixName(prefixBeanName);
                configGenerator.setBeanClassFileName(classBeanField.getText());
                configGenerator.setComment(commentButton.isSelected());
                configGenerator.setFormatter(getFormatter());
                configGenerator.analyze();

                new Thread(new Runnable(){ // attente du résultat en dehors de l'uiswing
                        // https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html
                        public void run(){
                            String res = configGenerator.getFormatter().get();
                            configTextArea.setText(configTextArea.getText() + res);
                            configTextArea.setCaretPosition(configTextArea.getText().length());
                            configTextArea.getCaret().setVisible(true);
                            configGenerator.getFormatter().clear();
                        }}).start();
            }catch(Exception e){
                configTextArea.setText("Exception ! "+ e.getClass().getName() + " --> " + e.getMessage() +"\n");
            }
        }
    }

    // Classe interne permettant de changer de "formatteur", cf. le patron Strategie
    private class SwingButtonAction implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED)
                configGenerator.setFormatter(new SwingPropertiesFormatter());    
        }
    }

    private class HtmlButtonAction implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED)
                configGenerator.setFormatter(new HtmlFormatter());
        }
    }

    private class PropertiesButtonAction implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED)
                configGenerator.setFormatter(new PropertiesFormatter());
        }
    }
    
    // getFormatter, en suspend la fenêtre swing qui ne s'efface pas entre deux appels
    // la solution provisoire est ici de la créer à chaque génération... peu satisfaisant
    private Formatter getFormatter(){
        if(configGenerator.getFormatter() instanceof SwingPropertiesFormatter){
            Formatter formatter = new SwingPropertiesFormatter();
            configGenerator.setFormatter(formatter);
        }
        return configGenerator.getFormatter();
    }

    // Classe interne permettant d'effacer le contenu de la zone de texte,
    private class ClearAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try{
                ConfigGeneratorGUI.this.configTextArea.selectAll();
                ConfigGeneratorGUI.this.configTextArea.replaceSelection("");
                configGenerator.getFormatter().clear();
            }catch(Exception e){
                configTextArea.selectAll();
                configTextArea.replaceSelection("Exception ... ??? " + e.getMessage());
                configGenerator.getFormatter().clear();
            }
        }
    }

    private class DoubleClickActionClear extends MouseAdapter implements MouseListener{
        // cf. https://stackoverflow.com/questions/4051659/identifying-double-click-in-java
        private AtomicBoolean  isAlreadyOneClick; // atomic : accès concurrent
        private ActionListener actionListener;

        public DoubleClickActionClear(){
            this.isAlreadyOneClick = new AtomicBoolean(false);
            this.actionListener = new ClearAction(); // idem au patron proxy ...
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (isAlreadyOneClick.get()) {
                isAlreadyOneClick.set(false);
                actionListener.actionPerformed(null); // peu orthodoxe
            } else {
                isAlreadyOneClick.set(true);
                java.util.Timer t = new java.util.Timer("doubleclickTimer", false);
                t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            isAlreadyOneClick.set(false);
                        }
                    }, 500);
            }
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    IConfigGenerator configGenerator = new ConfigGenerator();
                    configGenerator.setBeanName("exemple");
                    //configGenerator.setBeanPrefixName("pre_");
                    configGenerator.setBeanPrefixName("");
                    ConfigGeneratorGUI gui = new ConfigGeneratorGUI();
                    //gui.setClassBean("config_editor.ConfigGeneratorGUI");
                    gui.setClassBean("martin_fowler");
                    gui.setNumberBean(1);
                    gui.setConfigConfigurator(configGenerator);
                }
            });

    }
}
