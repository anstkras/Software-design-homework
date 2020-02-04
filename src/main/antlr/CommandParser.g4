grammar CommandParser;

line
    : command ('|' line)*
    | assignment
    ;

command
    : echo_command
    | cat_command
    | wcCommand
    | pwdCommand
    | exitCommand
    | unknown
    ;

assignment
    : variable'='value
    ;

echo_command
    : 'echo' STRING
    ;

cat_command
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