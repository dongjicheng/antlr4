lexer grammar CommonLexerRules;

ID : [a-zA-Z]+;
NUMBER: [0-9]+('.'([0-9]+)?)?
		|'.'[0-9]+;
NEWLINE:'\r'?'\n';
WS:[ \t]+ -> skip;
MUL : '*';	//为上述语法中使用的'*'命名
DIV : '/';
ADD : '+';
SUB : '-';