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
    : ECHO strings+=STRING+
    ;

catCommand
    : CAT STRING
    ;

wcCommand
    : WC STRING?
    ;

pwdCommand
    : PWD
    ;

exitCommand
    : EXIT
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

CAT
    : 'cat '
    ;

ECHO
    : 'echo '
    ;

WC
    : 'wc '
    ;

PWD
    : 'pwd '
    ;

EXIT
    : 'exit'
    ;

UNKNOWN
    : [a-zA-Z0-9_]+? ~[|]*?
    ;

WS : [ \t\n] -> skip;