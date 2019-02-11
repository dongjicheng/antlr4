grammar LabeledExpr;
 
/** The start rule; beginparsing here. */
prog:stat+;
 
stat: expr NEWLINE       # printExpr
| ID '=' expr NEWLINE    # assign
| NEWLINE                # blank
;
expr:expr op=('*'|'/') expr     # MulDiv
| expr op=('+'|'-') expr        # AddSub
| INT                           # int
| ID                            # id
| '(' expr ')'                  # parens
;
ID : [a-zA-Z]+ ; // matchidentifiers
INT : [0-9]+ ; // match integers
NEWLINE:'\r'? '\n' ;//return newlinesto parser(end-statement signal)
WS : [ \t]+ -> skip ;
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;