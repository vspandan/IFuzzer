program : sourceElement

sourceElement : statement | functionDeclaration

functionDeclaration : function identifier ( formalParameterList ) { functionBody } | function identifier ( ) { functionBody }

statement : block | variableStatement | emptyStatement | expressionStatement | ifStatement | iterationStatement | continueStatement | breakStatement | returnStatement | withStatement | labelledStatement | switchStatement | throwStatement | tryStatement | debuggerStatement | yieldExpression | elseStatement

yieldExpression : Yield expression | Yield

block : { statementList } |  { } 

statementList : statement | statement statementList

variableStatement : var variableDeclarationList | let variableDeclarationList | const variableDeclarationList ;

variableDeclarationList : variableDeclarationList  , variableDeclaration | variableDeclaration

variableDeclaration : identifier initialiser | arrayLiteral initialiser | objectLiteral initialiser | identifier | arrayLiteral | objectLiteral

initialiser : = assignmentExpression

emptyStatement :  ;

expressionStatement : expression

ifStatement : if ( expression ) statement elseStatement | if ( expression ) statement 

elseStatement : else statement

iterationStatement : do statement while ( expression ) ; | while ( expression ) statement | for ( ; ; ) statement | for ( ; ; expression ) statement | for ( ; expression ; ) statement | for ( ; expression ; expression ) statement | for ( expression ; ; ) statement | for ( expression ; ; expression ) statement | for ( expression ; expression ; ) statement | for ( expression ; expression ; expression ) statement | for ( var variableDeclarationList ; ; ) statement | for ( var variableDeclarationList ; ; expression ) statement | for ( var variableDeclarationList ; expression ; ) statement | for ( var variableDeclarationList ; expression ; expression ) statement |  for ( let variableDeclarationList ; ; ) statement | for ( let variableDeclarationList ; ; expression ) statement | for ( let variableDeclarationList ; expression ; ) statement | let ( var variableDeclarationList ; expression ; expression ) statement |  for ( const variableDeclarationList ; ; ) statement | for ( const variableDeclarationList ; ; expression ) statement | for ( const variableDeclarationList ; expression ; ) statement | for ( const variableDeclarationList ; expression ; expression ) statement | for each ( var leftHandSideExpression in expression ) statement  | for ( var leftHandSideExpression in expression ) statement  | for each ( let leftHandSideExpression in expression ) statement  | for ( let leftHandSideExpression in expression ) statement  | for each ( const leftHandSideExpression in expression ) statement  | for ( const leftHandSideExpression in expression ) statement | for ( const variableDeclarationList ; expression ; ) statement | for ( const variableDeclarationList ; expression ; expression ) statement | for each ( var leftHandSideExpression of expression ) statement  | for ( var leftHandSideExpression of expression ) statement  | for each ( let leftHandSideExpression of expression ) statement  | for ( let leftHandSideExpression of expression ) statement  | for each ( const leftHandSideExpression of expression ) statement  | for ( const leftHandSideExpression of expression ) statement 

continueStatement : continue identifier ; | continue ;

breakStatement : break identifier ; | break ;

returnStatement : return expression ; | return ;

withStatement : with ( expression ) statement

switchStatement : switch ( expression ) caseBlock

caseBlock : { caseClauses defaultClause caseClauses } | { caseClauses defaultClause } | { caseClauses } | { defaultClause caseClauses } |{ defaultClause } | { } 

caseClauses : caseClause | caseClause caseClauses

defaultClause : default : statementList | default : 

labelledStatement : identifier : statement

throwStatement : throw expression ;

tryStatement : try block catchProduction | try block finallyProduction | try block catchProduction finallyProduction

catchProduction : catchProduction catch ( identifier ) block | 

finallyProduction : finally block

debuggerStatement : debugger ;

formalParameterList : formalParameterList ',' formalParameter | formalParameter

formalParameter : identifier | arrayLiteral | objectLiteral

functionBody : sourceElements |
    
arrayLiteral : [ elementList , elision ] | [ elementList , ] |[ elementList ] | [ , elision ] | [ , ] |[ elision ] | [] | [elementList elision]

elementList : elision expression elementList | elision expression | expression |elision elementList

elision : , | , elision

objectLiteral : { } | { propertyNameAndValueList } |{ propertyNameAndValueList ,  }

propertyNameAndValueList : propertyAssignment , propertyNameAndValueList | propertyAssignment
    
propertyAssignment : propertyName | propertyName : assignmentExpression | get identifierName ( ) { functionBody } | set identifierName ( propertySetParameterList ) { functionBody }
    
propertyName : identifierName | StringLiteral | numericLiteral
    
propertySetParameterList : identifier

arguments : ( argumentList ) |  ( )
    
argumentList : expression , argumentList | expression 
    
expression : assignmentExpression expression | assignmentExpression | assignmentExpression for each ( identifier of expression ) statement | assignmentExpression for  ( identifier of expression ) statement | assignmentExpression for each ( identifier in expression ) statement | assignmentExpression for  ( identifier in expression ) statement | expression if ( expression ) | ( formalParameterList ) =>  expression | ( ) => expression | ( formalParameterList ) => statement | ( ) => statement | identifier => { statement }  | identifier => expression | let ( expression ) statement

assignmentExpression : yieldExpression | conditionalExpression | leftHandSideExpression  =  assignmentExpression | leftHandSideExpression assignmentOperator assignmentExpression

conditionalExpression : logicalORExpression | logicalORExpression  ?  assignmentExpression  :  assignmentExpression

logicalORExpression : logicalANDExpression | logicalORExpression  ##  logicalANDExpression

logicalANDExpression : bitwiseORExpression | logicalANDExpression  &&  bitwiseORExpression

bitwiseORExpression : bitwiseXORExpression | bitwiseORExpression  #  bitwiseXORExpression

bitwiseXORExpression : bitwiseANDExpression | bitwiseXORExpression  ^  bitwiseANDExpression

bitwiseANDExpression : equalityExpression | bitwiseANDExpression & equalityExpression

equalityExpression  : relationalExpression | equalityExpression  ==  relationalExpression | equalityExpression  !=  relationalExpression | equalityExpression  ===  relationalExpression | equalityExpression  !==  relationalExpression

relationalExpression  : shiftExpression | relationalExpression  <  shiftExpression | relationalExpression  >  shiftExpression | relationalExpression  <=  shiftExpression | relationalExpression  >=  shiftExpression | relationalExpression instanceof shiftExpression  | relationalExpression in shiftExpression

shiftExpression : additiveExpression | shiftExpression  <<  additiveExpression | shiftExpression  >>  additiveExpression | shiftExpression  >>>  additiveExpression
 
additiveExpression :  multiplicativeExpression | additiveExpression  +  multiplicativeExpression | additiveExpression  -  multiplicativeExpression

multiplicativeExpression :  unaryExpression | multiplicativeExpression  *  unaryExpression | multiplicativeExpression  /  unaryExpression | multiplicativeExpression  %  unaryExpression

unaryExpression :  postfixExpression | delete unaryExpression | void unaryExpression | typeof unaryExpression |  ++  unaryExpression |  --  unaryExpression | + unaryExpression |  -  unaryExpression |  ~  unaryExpression |  !  unaryExpression

postfixExpression :  leftHandSideExpression | leftHandSideExpression ++ | leftHandSideExpression --

leftHandSideExpression :  newExpression | callExpression ;

callExpression :  memberExpression arguments | callExpression arguments | callExpression [  ] | callExpression . identifierName

newExpression :  memberExpression | new newExpression

memberExpression :  primaryExpression | functionExpression | memberExpression [  ] | memberExpression . identifierName | new memberExpression arguments

functionExpression :  function identifier ( formalParameterList ) { functionBody } | function identifier ( ) { functionBody } | function ( formalParameterList ) { functionBody } | function ( ) { functionBody }

primaryExpression :  this | identifier | literal | arrayLiteral | objectLiteral | arguments

assignmentOperator : *= | /= | %= | += | -= | <<= | >>= | >>>= | &= | ^= | #=

literal : NullLiteral | BooleanLiteral | StringLiteral | RegularExpressionLiteral | numericLiteral

identifierName : identifier | reservedWord

reservedWord : keyword | futureReservedWord | NullLiteral | BooleanLiteral

keyword : break | do | instanceof | typeof | case | else | new | var | catch | finally | return | void | continue | for | switch | while | debugger | function | this | with | default | if | throw | delete | in | try | get | set 

futureReservedWord : class | enum | extends | super | const | export | import | implements | let | private | public | interface | package | protected | static | yield

NullLiteral : null

BooleanLiteral : true | false

numericLiteral : DecimalLiteral | HexIntegerLiteral | OctalIntegerLiteral | BinaryLiteral

StringLiteral :

RegularExpressionLiteral: 


program
 : sourceElement* EOF
 ;

sourceElement
 : statement
 | declaration
 ;

identifierReference
: identifier
| ~Yield
;

bindingIdentifier 
: identifier
| ~Yield
;

labelIdentifier
: identifier
| ~Yield
;

identifier
: IdentifierName
;

primaryExpression  
 :     This
 |     identifierReference
 |     literal
 |     objectLiteral
 |     arrayLiteral
 | 	   functionExpression
 | 	   classExpression
 | 	   functionExpression
 | 	   classExpression
 | 	   generatorExpression
 | 	   RegularExpressionLiteral
 | 	   templateLiteral
 | 	   coverParenthesizedExpressionAndArrowParameterList
 ;

coverParenthesizedExpressionAndArrowParameterList
 : '(' expression? ')'
 | '(' '...' bindingIdentifier ')'
 | '(' expression ',' '...' bindingIdentifier ')'
 ;
literal
 : ( NullLiteral 
   | BooleanLiteral
   | StringLiteral
   )
 | numericLiteral
 ;

arrayLiteral
 : '[' elision? ']'
 | '[' elementList ']'
 | '[' elementList ',' elision? ']'
 ;

elision
 : ','+
 ;

elementList
 : elision? assignmentExpression 
 | elementList ',' elision? assignmentExpression 
 ;

spreadElement
 : '...' assignmentExpression
 ;

objectLiteral
 : '{'  propertyNameAndValueList? '}'
 | '{'  propertyNameAndValueList ',' '}'
 ;

propertyNameAndValueList
 : propertyAssignment
 | propertyNameAndValueList  ',' propertyAssignment 
 ;

propertyAssignment
 : identifierReference                                                 # PropertyExpressionAssignment1
 | coverInitializedName                                                 # PropertyExpressionAssignment2
 | propertyName ':' assignmentExpression                             # PropertyExpressionAssignment
 | methodDefinition                          # PropertyGetter
 ;   

propertyName
 : literalPropertyName
 | computedPropertyName
 ;

literalPropertyName
 : IdentifierName
 | StringLiteral
 | numericLiteral
 ;
computedPropertyName
 : '[' assignmentExpression ']'
 ;

coverInitializedName
 : identifierReference initializer
 ;

initializer
 :  '=' assignmentExpression
 ;

templateLiteral 
 : NoSubstitutionTemplate
 | TemplateHead expression templateSpans
 ;

templateSpans
 : TemplateTail
 | templateMiddleList TemplateTail
 ;

templateMiddleList
 : TemplateMiddle expression
 | templateMiddleList TemplateMiddle expression
 ;

memberExpression  
 :     primaryExpression
 |     functionExpression
 |     memberExpression '[' expression ']'
 |     memberExpression '.' IdentifierName
 |     New memberExpression arguments
 | memberExpression templateLiteral
 | superProperty
 | metaProperty
 ;

superProperty
 : Super '[' expression  ']'
 | Super '.' IdentifierName
 ;

metaProperty
 : newTarget
 ;

newTarget
 : New '.' 'target'
 ;

newExpression  
 :     memberExpression
 |     New newExpression
 ;

callExpression  
 :     memberExpression arguments
 |     superCall
 |     callExpression arguments
 |     callExpression '[' expression ']'
 |     callExpression '.' IdentifierName
 |     callExpression '.' templateLiteral
 ;

superCall
 : Super arguments
 ;

arguments
 : '(' ')'
 | '(' argumentList ')'
 ;
    
argumentList
 : '...'? assignmentExpression
 | argumentList  ',' '...'? assignmentExpression
 ;

leftHandSideExpression  
 :     callExpression
 |     newExpression
 ;

postfixExpression  
 :     leftHandSideExpression
 |     leftHandSideExpression {!here(LineTerminator)}? '++'
 |     leftHandSideExpression {!here(LineTerminator)}? '--'
 ;
    
unaryExpression  
 :     postfixExpression
 |     Delete unaryExpression
 |     Void unaryExpression
 |     Typeof unaryExpression
 |     '++' unaryExpression
 |     '--' unaryExpression
 |     '+' unaryExpression
 |     '-' unaryExpression
 |     '~' unaryExpression
 |     '!' unaryExpression
 ;

multiplicativeExpression  
 :     unaryExpression
 |     multiplicativeExpression '*' unaryExpression
 |     multiplicativeExpression '/' unaryExpression
 |     multiplicativeExpression '%' unaryExpression
 ;

additiveExpression  
 :     multiplicativeExpression
 |     additiveExpression '+' multiplicativeExpression
 |     additiveExpression '-' multiplicativeExpression
 ;

shiftExpression
 :     additiveExpression
 |     shiftExpression '<<' additiveExpression
 |     shiftExpression '>>' additiveExpression
 |     shiftExpression '>>>' additiveExpression
 ; 

relationalExpression 
 :     shiftExpression
 |     relationalExpression '<' shiftExpression
 |     relationalExpression '>' shiftExpression
 |     relationalExpression '<=' shiftExpression
 |     relationalExpression '>=' shiftExpression
 |     relationalExpression Instanceof shiftExpression 
 |     relationalExpression In shiftExpression
 ;

equalityExpression 
 :     relationalExpression
 |     equalityExpression '==' relationalExpression
 |     equalityExpression '!=' relationalExpression
 |     equalityExpression '===' relationalExpression
 |     equalityExpression '!==' relationalExpression
 ;

bitwiseANDExpression
 :     equalityExpression
 |     bitwiseANDExpression '&' equalityExpression
 ;

bitwiseXORExpression
 :     bitwiseANDExpression
 |     bitwiseXORExpression '^' bitwiseANDExpression
 ;

 bitwiseORExpression
 :     bitwiseXORExpression
 |     bitwiseORExpression '|' bitwiseXORExpression
 ;

logicalANDExpression
 :     bitwiseORExpression
 |     logicalANDExpression '&&' bitwiseORExpression
 ;

logicalORExpression
 :     logicalANDExpression
 |     logicalORExpression '||' logicalANDExpression
 ;

conditionalExpression
 :     logicalORExpression
 |     logicalORExpression '?' assignmentExpression ':' assignmentExpression 
 ;

assignmentExpression
 :     yieldExpression 
 |     conditionalExpression 
 |     leftHandSideExpression '=' assignmentExpression  
 |     leftHandSideExpression assignmentOperator assignmentExpression 
 | 	   arrowFunction 
 ;

assignmentOperator
 : '*=' 
 | '/=' 
 | '%=' 
 | '+=' 
 | '-=' 
 | '<<=' 
 | '>>=' 
 | '>>>=' 
 | '&=' 
 | '^=' 
 | '|='
 ;

expression
 :     assignmentExpression
 |     expression ',' assignmentExpression
 ;

statement
 : block
 | variableStatement
 | emptyStatement
 | expressionStatement
 | ifStatement
 | breakableStatement
 | continueStatement
 | breakStatement
 | returnStatement
 | withStatement
 | labelledStatement
 | throwStatement
 | tryStatement
 | debuggerStatement
 ;

declaration
 : hoistableStatement
 | classDeclaration
 | lexicalDeclaration
 ;

hoistableStatement
 : functionDeclaration
 | generatorDeclaration
 ; 

breakableStatement
 : iterationStatement
 | switchStatement
 ;

block
 : '{' statementList? '}'
 ;

statementList
 : statementListItem
 | statementList statementListItem
 ;

statementListItem 
 : statement
 | declaration
 ;

lexicalDeclaration
 : letOrConst bindingList ';'
 ;

letOrConst
 : Let
 | Const
 ;

bindingList
 : lexicalBinding 
 | bindingList ',' lexicalBinding
 ;

lexicalBinding
 : bindingIdentifier initializer
 | bindingPattern initializer
 ;

variableStatement
 : Var variableDeclarationList 
 ;

variableDeclarationList
 : variableDeclaration 
 | variableDeclarationList ',' variableDeclaration
 ;

variableDeclaration
 : bindingIdentifier initializer
 | bindingPattern initializer
 ;

bindingPattern
 : objectBindingPattern
 | arrayBindingPattern
 ;

objectBindingPattern
 : '{' bindingPropertyList? '}'
 | '{' bindingPropertyList ',' '}'
 ;

arrayBindingPattern
 : '[' elision? bindingRestElement ']'
 | '[' bindingElementList ']'
 | '[' bindingElementList ',' elision? bindingRestElement ']'
 ;

bindingPropertyList
 : bindingProperty
 | bindingPropertyList ',' bindingProperty
 ;

bindingElementList
 : bindingElisionElement
 | bindingElementList ',' bindingElisionElement
 ;

bindingElisionElement
 : elision? bindingElement
 ;

bindingProperty
 : singleNameBinding
 | propertyName ':' bindingElement
 ;

bindingElement
 : singleNameBinding
 | bindingPattern initializer
 ; 

singleNameBinding
 : bindingIdentifier initializer
 ;

bindingRestElement
 : '...' bindingIdentifier 
 ;

emptyStatement
 : ';'
 ;

expressionStatement
 : {!here(OpenBrace)}? expression 
 | {!here(Function)}? expression 
 ;

ifStatement
 : If '(' expression ')' statement Else statement
 | If '(' expression ')' statement 
 ;

iterationStatement
 : Do statement While '(' expression ')' ';'                                               
 | While '(' expression ')' statement                                                      
 | For '(' {!here(Let)}? expression? ';' expression? ';' expression? ')' statement        
 | For '(' {!here(CloseBrace)}? expression? ';' expression? ';' expression? ')' statement        
 | For '(' Var variableDeclarationList ';' expression? ';' expression? ')' statement 
 | For '(' lexicalDeclaration ';' expression? ';' expression? ')' statement 
 | For '(' {!here(Let)}? leftHandSideExpression In expression ')' statement  
 | For '(' {!here(CloseBrace)}? leftHandSideExpression In expression ')' statement
 | For '(' Var forBinding In expression ')' statement  
 | For '(' forDeclaration In expression ')' statement
 | For '(' {!here(Let)}? leftHandSideExpression Of assignmentExpression ')' statement  
 | For '(' {!here(CloseBrace)}? leftHandSideExpression Of assignmentExpression ')' statement
 | For '(' Var forBinding Of assignmentExpression ')' statement  
 | For '(' forDeclaration Of assignmentExpression ')' statement
 ;

forDeclaration
 : letOrConst forBinding
 ;

forBinding
 : bindingIdentifier
 | bindingPattern
 ;

continueStatement
 : Continue {!here(LineTerminator)}? identifier? ';'
 ;

breakStatement
 : Break {!here(LineTerminator)}? identifier? ';'
 ;

returnStatement
 : Return {!here(LineTerminator)}? expression?  ';'
 ;

withStatement
 : With '(' expression ')' statement
 ;

switchStatement
 : Switch '(' expression ')' caseBlock
 ;

caseBlock
 : '{' caseClauses? ( defaultClause caseClauses? )? '}'
 ;

caseClauses
 : caseClause+
 ;

caseClause
 : Case expression ':' statementList?
 ;

defaultClause
 : Default ':' statementList?
 ;

labelledStatement
 : labelIdentifier ':' labelledItem
 ;

labelledItem
 : statement 
 | functionDeclaration
 ;

throwStatement
 : Throw {!here(LineTerminator)}? expression ';'
 ;

tryStatement
 : Try block catchProduction*
 | Try block finallyProduction
 | Try block catchProduction* finallyProduction
 ;

catchProduction
 : Catch '(' catchParameter ')' block
 ;

catchParameter
 : bindingIdentifier
 | bindingPattern
 ;

finallyProduction
 : Finally block
 ;

debuggerStatement
 : Debugger 
 ;

functionDeclaration
 : Function bindingIdentifier? '(' formalParameters? ')' '{' functionBody '}' 
 ;

functionExpression
 : Function bindingIdentifier '(' formalParameters? ')' '{' functionBody '}' 
 ;

strictFormalParameters
 : formalParameters
 ;

formalParameters
 : formalParameterList
 ;

formalParameterList
 : functionRestParameter
 | formalsList
 | formalsList ',' functionRestParameter
 ;

formalsList
 : formalParameter
 | formalsList ',' formalParameter
 ;

functionRestParameter
 : bindingRestElement
 ;

formalParameter
 : bindingElement
 ;

functionBody
 : functionStatementList
 ;

functionStatementList
 : statementList
 ;

arrowFunction
 : arrowParameters {!here(LineTerminator)}? '=>' conciseBody
 ;

arrowParameters
 : bindingIdentifier
 | coverParenthesizedExpressionAndArrowParameterList
 ;

conciseBody
 : {!here(OpenBrace)}? assignmentExpression
 | '{' functionBody '}'
 ;

arrowFormalParameters
 : '(' strictFormalParameters ')'
 ;

methodDefinition
 : propertyName '(' strictFormalParameters ')' '{' functionBody '}'
 | generatorMethod
 | 'get' propertyName '(' ')' '{' functionBody '}'                          
 | 'set' propertyName '(' propertySetParameterList ')' '{' functionBody '}' 
 ;

propertySetParameterList
 : formalParameter
 ;

generatorMethod
 : propertyName '(' strictFormalParameters ')' '{' generatorBody '}'
 ;

generatorDeclaration
 : Function '*' bindingIdentifier '(' formalParameters? ')' '{' generatorBody '}' 
 ;

generatorExpression
 : Function * bindingIdentifier '(' formalParameters? ')' '{' generatorBody '}' 
 ;

generatorBody
 : '{' functionBody '}'
 ;

yieldExpression
 : Yield
 | Yield {!here(LineTerminator)}? '*'? assignmentExpression
 ;

classDeclaration
 : Class bindingIdentifier classTail
 | Class classTail
 ;

classExpression
 : Class bindingIdentifier classTail
 ;

classTail
 : classHeritage '{' classBody '}'
 ;

classHeritage
 : Extends leftHandSideExpression
 ; 

classBody
 : classElementList
 ;

classElementList
 : classElement
 | classElementList classElement
 ;

classElement
 : methodDefinition
 | Static methodDefinition
 ;


reservedWord
 : keyword
 | futureReservedWord
 | ( NullLiteral
   | BooleanLiteral
   )
 ;

keyword
 : Break
 | Do
 | Instanceof
 | Typeof
 | Case
 | Else
 | New
 | Var
 | Catch
 | Finally
 | Return
 | Void
 | Continue
 | For
 | Switch
 | While
 | Debugger
 | Function
 | This
 | With
 | Default
 | If
 | Throw
 | Delete
 | In
 | Try
 | Of
 | Export
 | Class
 | Const
 | Extends
 | Super
 | Import
 | Yield
 | Static
 ;

futureReservedWord
 : Enum
 | Await
 | Implements
 | Private
 | Public
 | Interface
 | Package
 | Protected
 ;

Break      : 'break';
Do         : 'do';
Instanceof : 'instanceof';
Typeof     : 'typeof';
Case       : 'case';
Else       : 'else';
New        : 'new';
Var        : 'var';
Catch      : 'catch';
Finally    : 'finally';
Return     : 'return';
Void       : 'void';
Continue   : 'continue';
For        : 'for';
Switch     : 'switch';
While      : 'while';
Debugger   : 'debugger';
Function   : 'function' ;
This       : 'this';
With       : 'with';
Default    : 'default';
If         : 'if';
Throw      : 'throw';
Delete     : 'delete';
In         : 'in';
Try        : 'try';
Of         : 'of';  
Class   : 'class';
Enum    : 'enum';
Extends : 'extends';
Super   : 'super';
Const   : 'const';
Export  : 'export';
Import  : 'import';
Await   : 'await';
Implements : {strictMode}? 'implements';
Let        : {strictMode}? 'let';
Private    : {strictMode}? 'private';
Public     : {strictMode}? 'public';
Interface  : {strictMode}? 'interface';
Package    : {strictMode}? 'package';
Protected  : {strictMode}? 'protected';
Static     : {strictMode}? 'static';
Yield      : {strictMode}? 'yield' ;

Punctuator
: OpenBracket               
| CloseBracket              
| OpenParen                 
| CloseParen                
| OpenBrace                 
| CloseBrace                
| SemiColon                 
| Comma                     
| Dot                       
| LessThan                  
| MoreThan                  
| LessThanEquals            
| GreaterThanEquals         
| Equals                    
| NotEquals                 
| IdentityEquals            
| IdentityNotEquals         
| Plus                      
| Minus                     
| Multiply                  
| Modulus                   
| PlusPlus                  
| MinusMinus                
| RightShiftArithmetic      
| LeftShiftArithmetic       
| RightShiftLogical         
| BitAnd                    
| BitOr                     
| BitXOr                    
| Assign                    
| PlusAssign                
| MinusAssign               
| MultiplyAssign            
| ModulusAssign             
| LeftShiftArithmeticAssign 
| RightShiftArithmeticAssign
| RightShiftLogicalAssign   
| BitAndAssign              
| DivideAssign              
| BitXorAssign              
| BitOrAssign               
| ArrowAssign               
| QuestionMark              
| Colon                     
| Not                       
| BitNot                    
| Divide                    
| And                       
| Or                       
;

OpenBracket                : '[';
CloseBracket               : ']';
OpenParen                  : '(';
CloseParen                 : ')';
OpenBrace                  : '{';
CloseBrace                 : '}';
SemiColon                  : ';';
Comma                      : ',';
Dot                        : '.';
LessThan                   : '<';
MoreThan                   : '>';
LessThanEquals             : '<=';
GreaterThanEquals          : '>=';
Equals                     : '==';
NotEquals                  : '!=';
IdentityEquals             : '===';
IdentityNotEquals          : '!==';
Plus                       : '+';
Minus                      : '-';
Multiply                   : '*';
Modulus                    : '%';
PlusPlus                   : '++';
MinusMinus                 : '--';
RightShiftArithmetic       : '>>';
LeftShiftArithmetic        : '<<';
RightShiftLogical          : '>>>';
BitAnd                     : '&';
BitOr                      : '|';
BitXOr                     : '^';
Assign                     : '=';
PlusAssign                 : '+='; 
MinusAssign                : '-='; 
MultiplyAssign             : '*=';
ModulusAssign              : '%='; 
LeftShiftArithmeticAssign  : '<<='; 
RightShiftArithmeticAssign : '>>='; 
RightShiftLogicalAssign    : '>>>='; 
BitAndAssign               : '&='; 
DivideAssign               : '/='; 
BitXorAssign               : '^='; 
BitOrAssign                : '|=';
ArrowAssign                : '=>';
QuestionMark               : '?';
Colon                      : ':';
Not                        : '!';
BitNot                     : '~';
Divide                     : '/';
And                        : '&&';
Or                         : '||';

fragment DivPunctuator
 : '/'
 | '/='
 ;


fragment RightBracePunctuator
 : '}'
 ;

NullLiteral
 : 'null'
 ;

BooleanLiteral
 : 'true'
 | 'false'
 ;

numericLiteral
 : DecimalLiteral
 | HexIntegerLiteral
 | OctalIntegerLiteral
 | BinaryIntegerLiteral
 ;

DecimalLiteral
 : DecimalIntegerLiteral '.' DecimalDigit* ExponentPart?
 | '.' DecimalDigit+ ExponentPart?
 | DecimalIntegerLiteral ExponentPart?
 ;

fragment DecimalIntegerLiteral
 : '0'
 | [0-9] DecimalDigit*
 ;

fragment DecimalDigit
 : [0-9]
 ;

fragment ExponentPart
 : [eE] [+-]? DecimalDigit+
 ;

BinaryIntegerLiteral
 : '0' [bB] BinDigit+
 ;

fragment BinDigit
 : [0-1]
 ;

OctalIntegerLiteral
 : {!strictMode}? '0' OctalDigit+
 ;

fragment OctalDigit
 : [0-7]
 ;

HexIntegerLiteral
 : '0' [xX] HexDigit+
 ;

fragment HexDigit
 : [0-9a-fA-F]
 ;

StringLiteral
 : '"' DoubleStringCharacter* '"'
 | '\'' SingleStringCharacter* '\''
 | '`' SingleStringCharacter* '`'
 ;

fragment DoubleStringCharacter
 : ~["\\\r\n]
 | '\\' EscapeSequence
 | LineContinuation
 ;

fragment SingleStringCharacter
 : ~['\\\r\n]
 | '\\' EscapeSequence
 | LineContinuation
 ;

fragment LineContinuation
 : '\\' LineTerminatorSequence 
 ;

fragment EscapeSequence
 : CharacterEscapeSequence
 | '0' ~[1-9]
 | HexEscapeSequence
 | UnicodeEscapeSequence
 ;

fragment CharacterEscapeSequence
 : SingleEscapeCharacter
 | NonEscapeCharacter
 ;

 //make -j 2 disassembler=on snapshot=on gdbjit=on debuggersupport=on werror=yes

fragment SingleEscapeCharacter
 : ['"\\bfnrtv]
 ;

fragment NonEscapeCharacter
 : ~['"\\bfnrtv0-9xu\r\n]
 ;

fragment EscapeCharacter
 : SingleEscapeCharacter
 | DecimalDigit
 | [xu]
 ;

fragment HexEscapeSequence
 : 'x' HexDigit HexDigit
 ;

fragment UnicodeEscapeSequence
 : 'u' HexDigit HexDigit HexDigit HexDigit
 ;

RegularExpressionLiteral
 : {isRegexPossible()}? '/' RegularExpressionBody '/' RegularExpressionFlags
 ;

fragment RegularExpressionBody
 : RegularExpressionFirstChar RegularExpressionChar*
 ;

fragment RegularExpressionFirstChar
 : ~[\r\n\u2028\u2029*\\/\[]
 | RegularExpressionBackslashSequence
 | RegularExpressionClass
 ;

fragment RegularExpressionChar
 : ~[\r\n\u2028\u2029\\/\[]
 | RegularExpressionBackslashSequence
 | RegularExpressionClass
 ;

fragment RegularExpressionBackslashSequence
 : '\\' RegularExpressionNonTerminator
 ;

fragment RegularExpressionNonTerminator
 : ~[\r\n\u2028\u2029]
 ;

fragment RegularExpressionClass
  : '[' RegularExpressionClassChar* ']'
  ;
 
fragment RegularExpressionClassChar
 : ~[\r\n\u2028\u2029\]\\]
 | RegularExpressionBackslashSequence
 ;

fragment RegularExpressionFlags
 : IdentifierPart*
 ;

Template 
 : NoSubstitutionTemplate
 | TemplateHead
 ;

NoSubstitutionTemplate
 : '`' TemplateCharacter* '`'
 ;

TemplateHead
 : '`' TemplateCharacter* '${'
 ;

TemplateSubstitutionTail
 : TemplateMiddle
 | TemplateTail
 ;

TemplateMiddle
 : '}' TemplateCharacter* '${'
 ;

TemplateTail
 : '}' TemplateCharacter* '`'
 ;

fragment TemplateCharacter
: '\\' EscapeSequence
 | LineContinuation
 | LineTerminatorSequence
 | ~[`\\$\r\n\u2028\u2029]  
 ;

WhiteSpaces
 : [\t\u000B\u000C\u0020\u00A0]+ -> channel(HIDDEN)
 ;

LineTerminator
 : [\r\n\u2028\u2029] -> channel(HIDDEN)
 ;

fragment LineTerminatorSequence
 : '\r\n'
 | LineTerminator
 ;


MultiLineComment
 : '/*' .*? '*/' -> channel(HIDDEN)
 ;

SingleLineComment
 : '//' ~[\r\n\u2028\u2029]* -> channel(HIDDEN)
 ;


commonToken
 : IdentifierName
 | Punctuator
 | numericLiteral
 | StringLiteral
 | Template
 ;

IdentifierName
 : ~[reservedWord]
 | IdentifierStart IdentifierPart*
 ;

