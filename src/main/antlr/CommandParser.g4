grammar CommandParser;

line
    : command ('|' line)*
    ;

command
    : echo_command
    | cat_command
    | wcCommand
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

STRING
    : (~[ \t\n\r])+
    | '"'~["]*'"'
    | '\''~[']'\''
    ;


WS : [ \t\n] -> skip;