#!/bin/bash
#
echo "Compiling the lexer...."
javac lex/Lex.java
echo "Compiled the lexer"
echo "Executing the app..."
java Main.java
