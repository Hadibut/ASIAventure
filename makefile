all : compilJava tests

compilJava :
	javac -sourcepath ./src -d ./classes ./src/fr/insarouen/asi/prog/asiaventure/*.java;

tests : 
	javac -sourcepath ./srctest -cp ./classes:/usr/share/java/junit4-4.11.jar -d ./classestest ./srctest/fr/insarouen/asi/prog/asiaventure/*.java; java -cp ./classes:./classestest/:/usr/share/java/junit4-4.11.jar org.junit.runner.JUnitCore fr.insarouen.asi.prog.asiaventure.SuiteASIAventure