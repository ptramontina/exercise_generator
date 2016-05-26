java -jar antlr-3.2.jar Translator.g

javac -cp antlr-3.2.jar *java

set /p entrada="Digite o arquivo de entrada: "

java -cp .;antlr-3.2.jar TranslatorParser  < %entrada% > Saida.txt

pause

