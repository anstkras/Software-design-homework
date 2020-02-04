grammar CommandParser;

line
    : command ('|' line)*
    ;

command
    : echo_command
    | cat_command
    ;

echo_command
    : 'echo' STRING
    ;

cat_command
    : 'cat' STRING
    ;

STRING
    : (~[ \t\n\r])+
    | '"'~["]*'"'
    | '\''~[']'\''
    ;


WS : [ \t\n] -> skip;