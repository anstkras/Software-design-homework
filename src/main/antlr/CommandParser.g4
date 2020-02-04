grammar CommandParser;

line
    : command ('|' line)*
    | assignment
    ;

command
    : echoCommand
    | catCommand
    | wcCommand
    | pwdCommand
    | exitCommand
    | unknown
    ;

assignment
    : variable'='value
    ;

echoCommand
    : 'echo' STRING
    ;

catCommand
    : 'cat' STRING
    ;

wcCommand
    : 'wc' STRING?
    ;

pwdCommand
    : 'pwd'
    ;

exitCommand
    : 'exit'
    ;

variable
    : STRING
    ;

value
    : STRING
    ;

unknown
    : STRING
    | UNKNOWN
    ;

STRING
    : (~[ \t\n\r=])+
    | '"'~["]*'"'
    | '\''~[']'\''
    ;

UNKNOWN
    : [a-zA-Z0-9_]+? ~[|]*?
    ;

WS : [ \t\n] -> skip;