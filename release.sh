mvn clean
mvn release:clean
git commit -am "Automatic commit before mvn release:prepare"
ssh-add ~/.ssh/id_rsa
mvn release:prepare
mvn release:perform