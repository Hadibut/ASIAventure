all : compilJava tests

compilJava :
	javac -sourcepath ./src -d ./classes ./src/fr/insarouen/asi/prog/asiaventure/*.java;

tests : 
	javac -sourcepath ./srctest -cp ./classes:./junit4-4.11.jar:./hamcrest-core.jar -d ./classestest ./srctest/fr/insarouen/asi/prog/asiaventure/*.java;
	java -cp ./classes:./classestest/:./junit4-4.11.jar:./hamcrest-core.jar org.junit.runner.JUnitCore fr.insarouen.asi.prog.asiaventure.SuiteASIAventure;
