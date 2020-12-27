
#verbose=true

# martin_fowler
bean.id.1=martin_fowler
martin_fowler.class=container.FileSystemPropsApplicationContext
martin_fowler.property.1=fileName
martin_fowler.property.1.param.1=./martin_fowler/README.TXT
martin_fowler.property.2=name
martin_fowler.property.2.param.1=MARTIN_FOWLER

# exemples
bean.id.2=exemples
exemples.class=container.FileSystemPropsApplicationContext
exemples.property.1=fileName
exemples.property.1.param.1=./syntaxe_exemples/README.TXT
# le fichier de config se trouve à une url, cf. répertoire serveurWeb8086 
# avec console> java ServeurWeb886 au préalable
#exemples.property.1=url
#exemples.property.1.param.1=http://localhost:8086/config.props
exemples.property.2=name
exemples.property.2.param.1=EXEMPLES

# patron_commande
bean.id.3=patron_commande
patron_commande.class=container.FileSystemPropsApplicationContext
patron_commande.property.1=fileName
patron_commande.property.1.param.1=./command/README.TXT
patron_commande.property.2=name
patron_commande.property.2.param.1=PATRON_COMMANDE

# patron_decorateur
bean.id.4=patron_decorateur
patron_decorateur.class=container.FileSystemPropsApplicationContext
patron_decorateur.property.1=fileName
patron_decorateur.property.1.param.1=./decorator/README.TXT
patron_decorateur.property.2=name
patron_decorateur.property.2.param.1=PATRON_DECORATEUR

bean.id.5=serviceLocator
serviceLocator.class=service_locator.ServiceLocator
serviceLocator.property.1=container
serviceLocator.property.1.param.1=martin_fowler
serviceLocator.property.2=container
serviceLocator.property.2.param.1=exemples
serviceLocator.property.3=container
serviceLocator.property.3.param.1=patron_commande
serviceLocator.property.4=container
serviceLocator.property.4.param.1=patron_decorateur

# patron_strategy
bean.id.6=strategy1
strategy1.class=container.FileSystemPropsApplicationContext
strategy1.property.1=fileName
strategy1.property.1.param.1=./strategy/README.TXT
strategy1.property.2=name
strategy1.property.2.param.1=STRATEGY_1

# patron_strategy
bean.id.7=strategy2
strategy2.class=container.FileSystemPropsApplicationContext
strategy2.property.1=fileName
strategy2.property.1.param.1=./strategy/README.TXT
strategy2.property.2=name
strategy2.property.2.param.1=STRATEGY_2

# exemples
bean.id.8=exemples1
exemples1.class=container.FileSystemPropsApplicationContext
exemples1.property.1=fileName
exemples1.property.1.param.1=./syntaxe_exemples/README.TXT
exemples1.property.2=name
exemples1.property.2.param.1=EXEMPLES_1

# exemples
bean.id.9=exemples2
exemples2.class=container.FileSystemPropsApplicationContext
exemples2.property.1=fileName
exemples2.property.1.param.1=./syntaxe_exemples/README.TXT
exemples2.property.2=name
exemples2.property.2.param.1=EXEMPLES_2


# présence de doublons, necéssité d'utiliser lookup d'arité 2
bean.id.10=serviceLocator2
serviceLocator2.class=service_locator.ServiceLocator
serviceLocator2.property.1=container
serviceLocator2.property.1.param.1=strategy1
serviceLocator2.property.2=container
serviceLocator2.property.2.param.1=strategy2
serviceLocator2.property.3=container
serviceLocator2.property.3.param.1=exemples1
serviceLocator2.property.4=container
serviceLocator2.property.4.param.1=exemples2

