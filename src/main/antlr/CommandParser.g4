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
    | grepCommand
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

grepCommand
    : GREP
    ;

unknown
    : STRING
    | UNKNOWN
    ;

GREP
    : 'grep'~[|]*
    ;

CAT
    : 'cat '
    ;

ECHO
    : 'echo '
    ;

WC
    : 'wc '
    | ' wc'
    ;

PWD
    : 'pwd'
    ;

EXIT
    : 'exit'
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