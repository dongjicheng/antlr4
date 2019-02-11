lexer grammar CommonLexerRules;

ID : [a-zA-Z]+;
NUMBER: [0-9]+('.'([0-9]+)?)?
		|'.'[0-9]+;
NEWLINE:'\r'?'\n';
WS:[ \t]+ -> skip;
MUL : '*';	//Ϊ�����﷨��ʹ�õ�'*'����
DIV : '/';
ADD : '+';
SUB : '-';