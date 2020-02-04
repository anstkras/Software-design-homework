grammar CommandParser;

line
    : command ('|' line)*
    ;

command
    : echo_command
    | cat_command
    | wcCommand
    | pwdCommand
    | exitCommand
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

STRING
    : (~[ \t\n\r])+
    | '"'~["]*'"'
    | '\''~[']'\''
    ;


WS : [ \t\n] -> skip;