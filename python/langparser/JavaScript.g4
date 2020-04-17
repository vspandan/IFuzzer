program : statementListItem |

statementListItem : functionDeclaration | classDeclaration | statement

functionDeclaration : function identifierName ( formalParameterList ) { statementListItem } | function identifierName ( ) { statementListItem } | function identifierName ( formalParameterList ) {  } | function identifierName ( ) {  } 

classDeclaration : class identifierReference classTail | class classTail

classTail : classHeritage { classBody } | { classBody } |  classHeritage { } | {  }

classHeritage : extends conditionalExpression

classBody : classElement | classElement classBody

classElement : static methodDefinition | methodDefinition

statement : block | variableStatement | expressionStatement | ifStatement | iterationStatement | continueStatement | breakStatement | returnStatement | withStatement | labelledStatement | switchStatement | throwStatement | tryStatement | debuggerStatement | yieldExpression | elseStatement | emptyStatement

yieldExpression : yield assignmentExpression | yield

block : { statementList } | { }

statementList : statement | statementList statement

variableStatement : var variableDeclarationList ; | let variableDeclarationList ; | const variableDeclarationList ;

variableDeclarationList : variableDeclaration  | variableDeclarationList , variableDeclaration

variableDeclaration : identifierBinding initialiser | identifierBinding

identifierBinding : identifierReference | identifierPattern

identifierReference : identifierName | yield

identifierPattern : arrayBindingPattern | objectBindingPattern | argumentBindingPattern

arrayBindingPattern : [ ] | [ , ] | [ restElement ] | [ , restElement ] | [ bindingElementList ] | [ bindingElementList , , restElement ] | [ bindingElementList , restElement ]

restElement : ... identifierReference

bindingElementList : variableDeclaration | variableDeclaration bindingElementList | , variableDeclaration | , variableDeclaration bindingElementList

objectBindingPattern : { bindingPropertyList } | { }

argumentBindingPattern : ( bindingPropertyList ) | ( )

bindingPropertyList : bindingProperty | bindingPropertyList , bindingProperty

bindingProperty  : variableDeclaration | propertyName : variableDeclaration    

initialiser : = assignmentExpression

expressionStatement : expression 

ifStatement : if ( expression ) statement elseStatement | if ( expression ) statement

elseStatement: else statement

iterationStatement : do statement while ( expression ) ;  | while ( expression ) statement  | for (  ;  ;  ) statement | for (  ;  ; expression ) statement | for (  ; expression ;  ) statement | for (  ; expression ; expression ) statement | for ( expression ; ; ) statement | for ( expression ;  ; expression ) statement | for ( expression ; expression ;  ) statement | for ( expression ; expression ; expression ) statement | for ( (var|let|const) variableDeclarationList ; expression ; expression ) statement | for ( var identifierBinding of expression ) statement | for ( let identifierBinding of expression ) statement | for ( const identifierBinding of expression ) statement | for ( var leftHandSideExpression of expression ) statement | for ( let leftHandSideExpression of expression ) statement | for ( const leftHandSideExpression of expression ) statement | for ( var identifierBinding  in expression ) statement | for ( let identifierBinding  in expression ) statement | for ( const identifierBinding  in expression ) statement | for ( var leftHandSideExpression in expression ) statement | for ( let leftHandSideExpression in expression ) statement | for ( const leftHandSideExpression in expression ) statement  | for each ( var identifierBinding of expression ) statement | for each ( let identifierBinding of expression ) statement | for each ( const identifierBinding of expression ) statement | for each ( var leftHandSideExpression of expression ) statement | for each ( let leftHandSideExpression of expression ) statement | for each ( const leftHandSideExpression of expression ) statement  | for each ( var identifierBinding  in expression ) statement | for each ( let identifierBinding  in expression ) statement | for each ( const identifierBinding  in expression ) statement | for each ( var leftHandSideExpression in expression ) statement | for each ( let leftHandSideExpression in expression ) statement | for each ( const leftHandSideExpression in expression ) statement 

continueStatement : continue | continue identifierName  

breakStatement : break identifierName | break

returnStatement : return ;| return expression ;

withStatement : with ( expression ) statement

switchStatement : switch ( expression ) caseBlock

caseBlock : { } | { caseClauses defaultClause } | { defaultClause } | { caseClauses defaultClause caseClauses } | { defaultClause caseClauses } | { caseClauses defaultClause caseClauses } 

caseClauses : caseClause | caseClause caseClauses

caseClause : case expression : statementList | case expression : 

defaultClause : default : | default : statementList

labelledStatement : propertyName : expression | propertyName : statement

throwStatement : throw expression 

tryStatement : try block | try block catchProduction | try block finallyProduction | try block finallyProduction | try block catchProduction finallyProduction

catchProduction : catch ( identifierBinding ) block | catch ( identifierBinding if expression ) block

finallyProduction : finally block

debuggerStatement : debugger

formalParameterList : restElement | formalParameters | formalParameters , restElement
 
formalParameters : formalParameter | formalParameter , formalParameters

formalParameter : identifierName | arrayLiteral | objectLiteral

arrayLiteral : [ ] | [ elementList ] | [ elementList , ] | [ , ] | [ elementList , , ]

elementList : assignmentExpression  | elementList , assignmentExpression  | , ... assignmentExpression  | ... assignmentExpression  | elementList , ... assignmentExpression | elementList , assignmentExpression | , assignmentExpression  | elementList , , assignmentExpression  | , ... assignmentExpression  | elementList , , ... assignmentExpression 

objectLiteral : {  } | {  propertyNameAndValueList } | {  propertyNameAndValueList , }

propertyNameAndValueList : propertyAssignment | propertyNameAndValueList  , propertyAssignment 
    
propertyAssignment :  identifierReference | identifierReference initialiser | propertyName : assignmentExpression | methodDefinition  

methodDefinition :  propertyName ( ) { functionBody } | propertyName ( formalParameterList ) { functionBody } | Get propertyName ( ) { functionBody } | Set propertyName ( variableDeclaration ) { functionBody } 

propertyName : identifierName | StringLiteral | numericLiteral | [ assignmentExpression ]
    
arguments : ( ) | ( argumentList )
    
argumentList : assignmentExpression | argumentList  , assignmentExpression | ... assignmentExpression | argumentList  , ... assignmentExpression 
    
arrowfunction : arrowParameters => conciseBody

arrowParameters : identifierReference | ( ) |  ( restElement ) | ( expression ) | ( expression restElement ) 

conciseBody : assignmentExpression | { functionBody }

expression : assignmentExpression | expression , assignmentExpression
 
assignmentExpression : conditionalExpression | yieldExpression  | leftHandSideExpression = assignmentExpression  | leftHandSideExpression assignmentOperator assignmentExpression  | arrowfunction  | assignmentExpression for ( var identifierBinding of expression ) | assignmentExpression for ( var identifierBinding of expression ) | assignmentExpression for ( var identifierBinding of expression ) | assignmentExpression for ( var identifierBinding in expression ) | assignmentExpression for ( var identifierBinding in expression ) | assignmentExpression for ( var identifierBinding in expression ) | assignmentExpression for ( let identifierBinding of expression ) | assignmentExpression for ( let identifierBinding of expression ) | assignmentExpression for ( let identifierBinding of expression ) | assignmentExpression for ( let identifierBinding in expression ) | assignmentExpression for ( let identifierBinding in expression ) | assignmentExpression for ( let identifierBinding in expression ) | assignmentExpression for ( const identifierBinding of expression ) | assignmentExpression for ( const identifierBinding of expression ) | assignmentExpression for ( const identifierBinding of expression ) | assignmentExpression for ( const identifierBinding in expression ) | assignmentExpression for ( const identifierBinding in expression ) | assignmentExpression for ( const identifierBinding in expression ) | assignmentExpression for ( var assignmentExpression of expression ) | assignmentExpression for ( var assignmentExpression of expression ) | assignmentExpression for ( var assignmentExpression of expression ) | assignmentExpression for ( var assignmentExpression in expression ) | assignmentExpression for ( var assignmentExpression in expression ) | assignmentExpression for ( var assignmentExpression in expression ) | assignmentExpression for ( let assignmentExpression of expression ) | assignmentExpression for ( let assignmentExpression of expression ) | assignmentExpression for ( let assignmentExpression of expression ) | assignmentExpression for ( let assignmentExpression in expression ) | assignmentExpression for ( let assignmentExpression in expression ) | assignmentExpression for ( let assignmentExpression in expression ) | assignmentExpression for ( const assignmentExpression of expression ) | assignmentExpression for ( const assignmentExpression of expression ) | assignmentExpression for ( const assignmentExpression of expression ) | assignmentExpression for ( const assignmentExpression in expression ) | assignmentExpression for ( const assignmentExpression in expression ) | assignmentExpression for ( const assignmentExpression in expression ) | assignmentExpression for each ( var identifierBinding of expression ) | assignmentExpression for each ( var identifierBinding in expression ) | assignmentExpression for each ( var identifierBinding in expression ) | assignmentExpression for each ( var identifierBinding of expression ) | assignmentExpression for each ( var identifierBinding in expression ) | assignmentExpression for each ( var identifierBinding of expression ) | assignmentExpression for each ( let identifierBinding of expression ) | assignmentExpression for each ( let identifierBinding of expression ) | assignmentExpression for each ( let identifierBinding in expression ) | assignmentExpression for each ( let identifierBinding in expression ) | assignmentExpression for each ( let identifierBinding of expression ) | assignmentExpression for each ( let identifierBinding in expression ) | assignmentExpression for each ( const identifierBinding of expression ) | assignmentExpression for each ( const identifierBinding of expression ) | assignmentExpression for each ( const identifierBinding of expression ) | assignmentExpression for each ( const identifierBinding in expression ) | assignmentExpression for each ( const identifierBinding in expression ) | assignmentExpression for each ( const identifierBinding in expression ) | assignmentExpression for each ( var assignmentExpression of expression ) | assignmentExpression for each ( var assignmentExpression in expression ) | assignmentExpression for each ( var assignmentExpression in expression ) | assignmentExpression for each ( var assignmentExpression of expression ) | assignmentExpression for each ( var assignmentExpression in expression ) | assignmentExpression for each ( var assignmentExpression of expression ) | assignmentExpression for each ( let assignmentExpression of expression ) | assignmentExpression for each ( let assignmentExpression of expression ) | assignmentExpression for each ( let assignmentExpression in expression ) | assignmentExpression for each ( let assignmentExpression in expression ) | assignmentExpression for each ( let assignmentExpression of expression ) | assignmentExpression for each ( let assignmentExpression in expression ) | assignmentExpression for each ( const assignmentExpression of expression ) | assignmentExpression for each ( const assignmentExpression of expression ) | assignmentExpression for each ( const assignmentExpression of expression ) | assignmentExpression for each ( const assignmentExpression in expression ) | assignmentExpression for each ( const assignmentExpression in expression ) | assignmentExpression for each ( const assignmentExpression in expression ) | assignmentExpression if ( expression ) | 

conditionalExpression :     conditionalExpression ? assignmentExpression : assignmentExpression  |   conditionalExpression && conditionalExpression |     conditionalExpression ^ conditionalExpression |     conditionalExpression & conditionalExpression |     conditionalExpression == conditionalExpression |     conditionalExpression != conditionalExpression |     conditionalExpression === conditionalExpression |     conditionalExpression !== conditionalExpression |     conditionalExpression < conditionalExpression |     conditionalExpression > conditionalExpression |     conditionalExpression <= conditionalExpression |     conditionalExpression >= conditionalExpression |     conditionalExpression instanceof conditionalExpression  |     conditionalExpression in conditionalExpression |     conditionalExpression << conditionalExpression |     conditionalExpression >> conditionalExpression |     conditionalExpression >>> conditionalExpression |     conditionalExpression + conditionalExpression |     conditionalExpression - conditionalExpression |     conditionalExpression * conditionalExpression |     conditionalExpression / conditionalExpression |     conditionalExpression % conditionalExpression |     delete conditionalExpression |     void conditionalExpression |     typeof conditionalExpression |     ++ conditionalExpression |     -- conditionalExpression |     + conditionalExpression |     - conditionalExpression |     ~ conditionalExpression |     ! conditionalExpression |     conditionalExpression ++ |     conditionalExpression -- |     leftHandSideExpression

leftHandSideExpression   :     callExpression |     newExpression


memberExpression   :     primaryExpression |     memberExpression [ expression ] |     memberExpression . identifierName  |     new memberExpression arguments

primaryExpression   :     this |     identifierName |     functionExpression |     classDeclaration |     literal |     objectLiteral |     ( expression ) |     arrayLiteral 

callExpression   : memberExpression arguments | callExpression arguments | callExpression [ expression ] | callExpression . identifierName

newExpression   : memberExpression | new newExpression

functionExpression   : function ( ) statement | function  identifierName ( ) statement | function ( formalParameterList ) statement | function  identifierName ( formalParameterList ) statement |  function ( ) { functionBody } | function  identifierName ( ) { functionBody } | function ( formalParameterList ) { functionBody } | function  identifierName ( formalParameterList ) { functionBody } | function ( ) {  } | function  identifierName ( ) {  } | function ( formalParameterList ) {  } | function  identifierName ( formalParameterList ) {  }
 
assignmentOperator : *=  | /=  | %=  | +=  | -=  | <<=  | >>=  | >>>=  | &=  | ^=  | #=

emptyStatement : ;