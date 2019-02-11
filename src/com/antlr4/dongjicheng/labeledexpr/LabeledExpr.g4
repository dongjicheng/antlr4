grammar LabeledExpr;
import CommonLexerRules;
prog: stat+;
stat: expr NEWLINE				#printExpr
	| ID '=' expr NEWLINE		#assign
	| 'print(' expr ')' NEWLINE   # print
	| NEWLINE   # blank
	;
expr: <assoc=right> expr '**' expr         #power
	| sn=(ADD|SUB) expr 				#sign
	| expr op=(MUL|DIV) expr		#MulDiv
	| expr op=(ADD|SUB) expr		#AddSub
	| NUMBER						#number
	| ID						#id
	| '(' expr ')'				#parens
	;