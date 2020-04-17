grammar JavaSE8;

compilationUnit:
	packageDeclaration? importDeclaration* typeDeclaration*
	;

packageDeclaration:
	packageModifier* 'package' identifier ('.' identifier)* ';' 
	;

packageModifier:
	annotation
	;

importDeclaration:
	singleTypeImportDeclaration
	| typeImportOnDemandDeclaration
	| singleStaticImportDeclaration
	| staticImportOnDemandDeclaration
	;

singleTypeImportDeclaration:
	'import' typeName ';' 
	;

typeImportOnDemandDeclaration:
	'import' packageOrTypeName '.' '*' 
	;

singleStaticImportDeclaration:
	'import' 'static' typeName '.' identifier ';'
	;

staticImportOnDemandDeclaration:
	'import' 'static' typeName '.' '*' ';'
	;

typeDeclaration:
	classDeclaration
	| interfaceDeclaration
	| ';'
	;

classDeclaration:
	normalClassDeclaration
	| enumDeclaration
	;

normalClassDeclaration:
	classModifier* 'class' identifier typeParameters? superclass? superinterfaces? classBody
	;

classModifier:
	annotation
	| 'public'
	| 'protected'
	| 'private'
	| 'abstract'
	| 'static'
	| 'final'
	| 'strictfp'
	;

typeParameters:
	'<' typeParameterList '>'
	;

typeParameterList:
	typeParameter (',' typeParameter)
	;

superclass:
	'extends' classType
	;

superinterfaces:
	'implements' interfaceTypeList
	;

interfaceTypeList:
	interfaceType (',' interfaceType)*
	;

classBody:
	'{' classBodyDeclaration* '}'
	;

classBodyDeclaration:
	classMemberDeclaration
	| instanceInitializer
	| staticInitializer
	| constructorDeclaration
	;

classMemberDeclaration:
	fieldDeclaration
	| methodDeclaration
	| classDeclaration
	| interfaceDeclaration
	| ';'
	;

fieldDeclaration:
	fieldModifier* unannType variableDeclaratorList ';'
	;

fieldModifier:
	annotation
	| 'public'
	| 'protected'
	| 'private'
	| 'abstract'
	| 'static'
	| 'final'
	| 'strictfp'
	;

variableDeclaratorList:
	variableDeclarator (',' variableDeclarator)*
	;

variableDeclarator:
	variableDeclaratorId ('=' variableInitializer)?
	;

variableDeclaratorId:
	identifier dims?
	;

variableInitializer:
	expression
	| arrayInitializer
	;

unannType:
	unannPrimitiveType
	| unannReferenceType
	;

unannPrimitiveType:
	numericType
	| 'boolean'
	;

unannReferenceType:
	unannClassOrInterfaceType
	| unannTypeVariable
	| unannArrayType
	;

unannClassOrInterfaceType:
	unannClassType
	| unannInterfaceType
	;

unannClassType:
	identifier typeArguments?
	unannClassOrInterfaceType '.' annotation* identifier typeArguments?
	;

unannInterfaceType:
	unannClassType
	;

unannTypeVariable:
	identifier
	;

unannArrayType:
	unannPrimitiveType dims
	| unannClassOrInterfaceType dims
	| unannTypeVariable dims
	;

methodDeclaration:
	methodModifier* methodHeader methodBody
	;

methodModifier:
	annotation
	| 'public'
	| 'protected'
	| 'private'
	| 'abstract'
	| 'static'
	| 'final'
	| 'strictfp'
	| 'synchronized'
	| 'native'
	;

methodHeader:
	result methodDeclarator throwsStatement?
	| typeParameters annotation* result methodDeclarator throwsStatement?
	;

result:
	unannType
	| 'void'
	;

methodDeclarator:
	identifier '(' formalParameterList? ')' dims?
	;

formalParameterList:
	receiverParameter
	| formalParameters ',' lastFormalParameter
	| lastFormalParameter
	;

formalParameters:
	formalParameter (',' formalParameter)*
	| receiverParameter (',' formalParameter)*
	;

formalParameter:
	variableModifier* unannType variableDeclaratorId
	;

variableModifier:
	annotation 
	| 'final'
	;
	
lastFormalParameter:
	variableModifier* unannType annotation* '...' variableDeclaratorId
	| formalParameter
	;

receiverParameter:
	annotation* unannType (identifier '.')? 'this'
	;

throwsStatement:
	'throws' exceptionTypeList
	;

exceptionTypeList:
	exceptionType (',' exceptionType)
	;

exceptionType:
	classType
	| typeVariable
	;

methodBody:
	| block
	| ';'
	;

instanceInitializer:
	block
	;

staticInitializer:
	'static' block
	;


constructorDeclaration:
	constructorModifier* constructorDeclarator throwsStatement? constructorBody
	;

constructorModifier:
	annotation 
	| 'public' 
	| 'protected' 
	| 'private'
	;

constructorDeclarator:
	typeParameters* simpleTypeName '(' formalParameterList? ')'
	;

simpleTypeName:
	identifier
	;

constructorBody:
	'{' explicitConstructorInvocation? bBlockStatements? '}'
	;

explicitConstructorInvocation:
	typeArguments? 'this' '(' argumentList? ')' ';'
	| typeArguments? 'super' '(' argumentList? ')' ';'
	| expressionName '.' typeArguments? 'super' '(' argumentList? ')' ';'
	| primary '.' typeArguments? 'super' '(' argumentList? ')' ';'
	;

enumDeclaration:
	classModifier* 'enum' identifier superinterfaces? enumBody
	;

enumBody:
	'{' enumConstantList? ','? enumBodyDeclarations? '}'
	;

enumConstantList:
	enumConstant (',' enumConstant)
	;

enumConstant:
	enumConstantModifier* identifier '(' argumentList? ')'? classBody?
	;

enumConstantModifier:
	annotation
	;

enumBodyDeclarations:
	';' classBodyDeclaration*
	;

interfaceDeclaration:
	normalInterfaceDeclaration
	| annotationTypeDeclaration
	;

normalInterfaceDeclaration:
	interfaceModifier* 'interface' identifier typeParameters? extendsInterfaces? interfaceBody
	;

interfaceModifier:
	annotation
	| 'public'
	| 'protected'
	| 'private'
	| 'abstract'
	| 'static'
	| 'final'
	| 'strictfp'
	;

extendsInterfaces:
	'extends' interfaceTypeList
	;

interfaceBody:
	'{' interfaceMemberDeclaration* '}'
	;

interfaceMemberDeclaration:
	constantDeclaration
	| interfaceMethodDeclaration
	| classDeclaration
	| interfaceDeclaration
	| ';'
	;

constantDeclaration:
	constantModifier* unannType variableDeclaratorList ';'
	;

constantModifier:
	annotation
	| 'public'
	| 'static'
	| 'final'
	;

interfaceMethodDeclaration:
	interfaceMethodModifier* methodHeader methodBody
	;

interfaceMethodModifier:
	annotation
	| 'public'
	| 'protected'
	| 'private'
	| 'abstract'
	| 'static'
	| 'final'
	| 'strictfp'
	;

annotationTypeDeclaration:
	interfaceModifier* '@interface' identifier annotationTypeBody
	;

annotationTypeBody:
	{ annotationTypeMemberDeclaration* }
	;

annotationTypeMemberDeclaration:
	annotationTypeElementDeclaration
	| constantDeclaration
	| classDeclaration
	| interfaceDeclaration
	| ';'
	;

annotationTypeElementDeclaration:
	annotationTypeElementModifier* unannType identifier '(' ')' dims? defaultValue? ';'
	;

annotationTypeElementModifier:
	annotation
	| 'public'
	| 'abstract'
	;

defaultValue:
	'default' elementValue
	;

annotation:
	normalAnnotation
	| markerAnnotation
	| singleElementAnnotation
	;

normalAnnotation:
	'@' typeName '(' elementValuePairList? ')'
	;

elementValuePairList:
	elementValuePair (',' elementValuePair)*
	;

elementValuePair:
	identifier '=' elementValue
	;

elementValue:
	conditionalExpression
	| elementValueArrayInitializer
	| annotation
	;

elementValueArrayInitializer:
	'{' elementValueList? ','? '}'
	;

elementValueList:
	elementValue (',' elementValue)*
	;

markerAnnotation:
	'@' typeName
	;

singleElementAnnotation:
	'@' typeName '(' elementValue ')'
	;


arrayInitializer:
	'{' variableInitializerList? ','? '}'
	;

variableInitializerList:
	variableInitializer (',' variableInitializer)*
	;

block:
	'{' bBlockStatements? '}'
	;

bBlockStatements:
	blockStatement+
	;

blockStatement:
	localVariableDeclarationStatement
	| classDeclaration
	| statement
	;

localVariableDeclarationStatement:
	localVariableDeclaration ';'
	;

localVariableDeclaration:
	variableModifier* unannType variableDeclaratorList
	;

statement:
	statementWithoutTrailingSubstatement
	| labeledStatement
	| ifThenStatement
	| ifThenElseStatement
	| whileStatement
	| forStatement
	;

statementNoShortIf:
	statementWithoutTrailingSubstatement
	| labeledStatementNoShortIf
	| ifThenElseStatementNoShortIf
	| whileStatementNoShortIf
	| forStatementNoShortIf
	;

statementWithoutTrailingSubstatement:
	block
	| emptyStatement
	| expressionStatement
	| assertStatement
	| switchStatement
	| doStatement
	| breakStatement
	| continueStatement
	| returnStatement
	| synchronizedStatement
	| throwStatement
	| tryStatement
	;

emptyStatement:
	';'
	;

labeledStatement:
	identifier ':' statement
	;

labeledStatementNoShortIf:
	identifier ':' statementNoShortIf
	;

expressionStatement:
	statementExpression ';'
	;

statementExpression:
	assignment
	| preIncrementExpression
	| preDecrementExpression
	| postIncrementExpression
	| postDecrementExpression
	| methodInvocation
	| classInstanceCreationExpression
	;

ifThenStatement:
	'if' '(' expression ')' statement
	;

ifThenElseStatement:
	'if' '(' expression ')' statementNoShortIf 'else' statement
	;

ifThenElseStatementNoShortIf:
	'if' '(' expression ')' statementNoShortIf 'else' statementNoShortIf
	;

assertStatement:
	'assert' expression ';'
	| 'assert' expression ':' expression ';'
	;

switchStatement:
	'switch' '(' expression ')' switchBlock
	;

switchBlock:
	'{' switchBlockStatementGroup* switchLabel* '}'
	;

switchBlockStatementGroup:
	switchLabels bBlockStatements
	;

switchLabels:
	switchLabel switchLabel*
	;

switchLabel:
	'case' constantExpression ':'
	| 'case' enumConstantName ':'
	| 'default' ':'
	;

enumConstantName:
	identifier
	;

whileStatement:
	'while' '(' expression ')' statement
	;

whileStatementNoShortIf:
	'while' '(' expression ')' statementNoShortIf
	;

doStatement:
	'do' statement 'while' '(' expression ')' ';'
	;

forStatement:
	basicForStatement
	| enhancedForStatement
	;

forStatementNoShortIf:
	basicForStatementNoShortIf
	| enhancedForStatementNoShortIf
	;

basicForStatement:
	'for' '(' forInit? ';' expression? ';' forUpdate ')' statement
	;

basicForStatementNoShortIf:
	'for' '(' forInit? ';' expression? ';' forUpdate ')' statementNoShortIf
	;

forInit:
	statementExpressionList
	| localVariableDeclaration
	;

forUpdate:	
	statementExpressionList
	;

statementExpressionList:
	statementExpression (',' statementExpression)*
	;

enhancedForStatement:
	'for' '(' variableModifier* unannType variableDeclaratorId ':' expression ')' statement
	;

enhancedForStatementNoShortIf:
	'for' '(' variableModifier* unannType variableDeclaratorId ':' expression ')' statementNoShortIf
	;

breakStatement:
	'break' identifier? ';'
	;

continueStatement:
	'continue' identifier? ';'
	;

returnStatement:
	'return' expression? ';'
	;

throwStatement:
	'throw' expression ';'
	;

synchronizedStatement:
	'synchronized' ( expression ) block
	;

tryStatement:
	'try' block catches
	| 'try' block catches? finallyStatement
	| tryWithResourcesStatement
	;

catches:
	catchClause+
	;

catchClause:
	'catch' '(' catchFormalParameter ')' block
	;

catchFormalParameter:
	variableModifier* catchType variableDeclaratorId
	;

catchType:
	unannClassType ('|' classType)
	;

finallyStatement:
	'finally' block
	;

tryWithResourcesStatement:
	'try' resourceSpecification block catches? finallyStatement?
	;

resourceSpecification:
	'(' resourceList ';'? ')'
	;

resourceList:
	resource (';' resource)*
	;

resource:
	variableModifier* unannType variableDeclaratorId '=' expression
	;


primary:
	primaryNoNewArray
	| arrayCreationExpression
	;

primaryNoNewArray:
	literal
	| classLiteral
	| 'this'
	| typeName '.' 'this'
	| '(' expression ')'
	| classInstanceCreationExpression
	| fieldAccess
	| arrayAccess
	| methodInvocation
	| methodReference
	;

classLiteral:
	typeName ('[' ']')* '.' 'class'
	| numericType ('[' ']')* '.' 'class'
	| 'boolean' ('[' ']')* '.' 'class'
	| 'void' '.' 'class'
	;

classInstanceCreationExpression:
	unqualifiedClassInstanceCreationExpression
	expressionName '.' unqualifiedClassInstanceCreationExpression
	primary '.' unqualifiedClassInstanceCreationExpression
	;

unqualifiedClassInstanceCreationExpression:
	'new' typeArguments? classOrInterfaceTypeToInstantiate '(' argumentList? ')' classBody?
	;

classOrInterfaceTypeToInstantiate:
	annotation* identifier ('.' annotation* identifier)* typeArgumentsOrDiamond?
	;

typeArgumentsOrDiamond:
	typeArguments
	| '<''>'
	;

fieldAccess:
	primary '.' identifier
	| 'super' '.' identifier
	| typeName '.' 'super' '.' identifier
	;

arrayAccess:
	expressionName '[' expression ']'
	| primaryNoNewArray '[' expression ']'
	;

methodInvocation:
	methodName '(' argumentList? ')'
	| typeName '.' typeArguments? identifier '(' argumentList? ')'
	| expressionName '.' typeArguments? identifier '(' argumentList? ')'
	| primary '.' typeArguments? identifier '(' argumentList? ')'
	| 'super' '.' typeArguments? identifier '(' argumentList? ')'
	| typeName '.' 'super' '.' typeArguments? identifier '(' argumentList? ')'
	;

argumentList:
	expression (',' expression)
	;

methodReference:
	expressionName '::' typeArguments? identifier
	| referenceType '::' typeArguments? identifier
	| primary '::' typeArguments? identifier
	| 'super' '::' typeArguments? identifier
	| typeName '.' 'super' '::' typeArguments? identifier
	| classType '::' typeArguments? 'new'
	| arrayType '::' 'new'
	;

arrayCreationExpression:
	'new' primitiveType dimExprs dims?
	| 'new' classOrInterfaceType dimExprs dims?
	| 'new' primitiveType dims arrayInitializer
	| 'new' classOrInterfaceType dims arrayInitializer
	;

dimExprs:
	dimExpr+
	;

dimExpr:
	annotation* '[' expression ']'
	;

expression:
	lambdaExpression
	| assignmentExpression
	;

lambdaExpression:
	lambdaParameters '->' lambdaBody
	;

lambdaParameters:
	identifier
	| '(' formalParameterList? ')'
	| '(' inferredFormalParameterList ')'
	;

inferredFormalParameterList:
	identifier (',' identifier)
	;

lambdaBody:
	expression
	| block
	;

assignmentExpression:
	conditionalExpression
	| assignment
	;

assignment:
	leftHandSide assignmentOperator expression
	;

leftHandSide:
	expressionName
	| fieldAccess
	| arrayAccess
	;

assignmentOperator:
	'='
	| '*='  
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


conditionalExpression:
	conditionalOrExpression
	| conditionalOrExpression '?' expression ':' conditionalExpression
	| conditionalOrExpression '?' expression ':' lambdaExpression
	;

conditionalOrExpression:
	conditionalAndExpression
	| conditionalOrExpression '||' conditionalAndExpression
	;

conditionalAndExpression:
	inclusiveOrExpression
	| conditionalAndExpression '&&' inclusiveOrExpression
	;

inclusiveOrExpression:
	exclusiveOrExpression
	| inclusiveOrExpression '|' exclusiveOrExpression
	;

exclusiveOrExpression:
	andExpression
	| exclusiveOrExpression '^' andExpression
	;

andExpression:
	equalityExpression
	| andExpression '&' equalityExpression
	;

equalityExpression:
	relationalExpression
	| equalityExpression '==' relationalExpression
	| equalityExpression '!=' relationalExpression
	;

relationalExpression:
	shiftExpression
	| relationalExpression '<' shiftExpression
	| relationalExpression '>' shiftExpression
	| relationalExpression '<=' shiftExpression
	| relationalExpression '>=' shiftExpression
	| relationalExpression 'instanceof' referenceType
	;
	
shiftExpression:
	additiveExpression
	| shiftExpression '<<' additiveExpression
	| shiftExpression '>>' additiveExpression
	| shiftExpression '>>>' additiveExpression
	;

additiveExpression:
	multiplicativeExpression
	| additiveExpression '+' multiplicativeExpression
	| additiveExpression '-' multiplicativeExpression
	;

multiplicativeExpression:
	unaryExpression
	| multiplicativeExpression '*' unaryExpression
	| multiplicativeExpression '/' unaryExpression
	| multiplicativeExpression '%' unaryExpression
	;

unaryExpression:
	preIncrementExpression
	| preDecrementExpression
	| '+' unaryExpression
	| '-' unaryExpression
	| unaryExpressionNotPlusMinus
	;

preIncrementExpression:
	'++' unaryExpression
	;

preDecrementExpression:
	'--' unaryExpression
	;

unaryExpressionNotPlusMinus:
	postfixExpression
	| '~' unaryExpression
	| '!' unaryExpression
	| castExpression
	;

postfixExpression:
	primary
	| expressionName
	| postIncrementExpression
	| postDecrementExpression
	;

postIncrementExpression:
	postfixExpression '++' 
	;

postDecrementExpression:
	postfixExpression '--'
	;

castExpression:
	'(' primitiveType ')' unaryExpression
	| '(' referenceType additionalBound* ')' unaryExpressionNotPlusMinus
	| '(' referenceType additionalBound* ')' lambdaExpression
	;

constantExpression:
	expression
	;

typeName:
	identifier
	| packageOrTypeName '.' identifier
	;

packageOrTypeName:
	identifier
	| packageOrTypeName '.' identifier
	;

expressionName:
	identifier
	| ambiguousName '.' identifier
	;

methodName:
	identifier
	;

packageName:
	identifier
	| packageName '.' identifier
	;

ambiguousName:
	identifier
	| ambiguousName '.' identifier
	;

type:
	primitiveType
	| referenceType
	;

primitiveType:
	annotation* numericType
	| annotation* 'boolean'
	;

numericType:
	integralType
	| floatingPointType
	;

integralType:
	'byte' 
	| 'short' 
	| 'int' 
	| 'long' 
	| 'char'
	;

floatingPointType:
	'float'
	| 'double'
	;

referenceType:
	classOrInterfaceType
	| typeVariable
	| arrayType
	;

classOrInterfaceType
	:	(	classType_lfno_classOrInterfaceType
		|	interfaceType_lfno_classOrInterfaceType
		)
		(	classType_lf_classOrInterfaceType
		|	interfaceType_lf_classOrInterfaceType
		)*
	;

classType
	:	annotation* Identifier typeArguments?
	|	classOrInterfaceType '.' annotation* Identifier typeArguments?
	;

classType_lf_classOrInterfaceType
	:	'.' annotation* Identifier typeArguments?
	;

classType_lfno_classOrInterfaceType
	:	annotation* Identifier typeArguments?
	;

interfaceType
	:	classType
	;

interfaceType_lf_classOrInterfaceType
	:	classType_lf_classOrInterfaceType
	;

interfaceType_lfno_classOrInterfaceType
	:	classType_lfno_classOrInterfaceType
	;

typeVariable:
	annotation* identifier
	;

arrayType:
	primitiveType dims
	| classOrInterfaceType dims
	| typeVariable dims
	;

dims:
	(annotation* '[' ']')+
	;

typeParameter:
	typeParameterModifier* identifier typeBound?
	;

typeParameterModifier:
	annotation
	;

typeBound:
	'extends' typeVariable
	| 'extends' classOrInterfaceType additionalBound*
	;

additionalBound:
	'&' interfaceType
	;

typeArguments:
	'<' typeArgumentList '>'
	;

typeArgumentList:
	typeArgument (',' typeArgument)
	;

typeArgument:
	referenceType
	| wildcard
	;

wildcard:
	annotation* '?' wildcardBounds?
	;

wildcardBounds:
	'extends' referenceType
	| 'super' referenceType
	;


literal:
	IntegerLiteral
	| FloatingPointLiteral
	| BooleanLiteral
	| CharacterLiteral
	| StringLiteral
	| NullLiteral
	;

identifier:
	IdentifierChars
	;

IdentifierChars:
	JavaLetter JavaLetterOrDigit*
	;

IntegerLiteral
	:	DecimalIntegerLiteral
	|	HexIntegerLiteral
	|	OctalIntegerLiteral
	|	BinaryIntegerLiteral
	;

fragment
DecimalIntegerLiteral
	:	DecimalNumeral IntegerTypeSuffix?
	;

fragment
HexIntegerLiteral
	:	HexNumeral IntegerTypeSuffix?
	;

fragment
OctalIntegerLiteral
	:	OctalNumeral IntegerTypeSuffix?
	;

fragment
BinaryIntegerLiteral
	:	BinaryNumeral IntegerTypeSuffix?
	;

fragment
IntegerTypeSuffix
	:	[lL]
	;

fragment
DecimalNumeral
	:	'0'
	|	NonZeroDigit (Digits? | Underscores Digits)
	;

fragment
Digits
	:	Digit (DigitsAndUnderscores? Digit)?
	;

fragment
Digit
	:	'0'
	|	NonZeroDigit
	;

fragment
NonZeroDigit
	:	[1-9]
	;

fragment
DigitsAndUnderscores
	:	DigitOrUnderscore+
	;

fragment
DigitOrUnderscore
	:	Digit
	|	'_'
	;

fragment
Underscores
	:	'_'+
	;

fragment
HexNumeral
	:	'0' [xX] HexDigits
	;

fragment
HexDigits
	:	HexDigit (HexDigitsAndUnderscores? HexDigit)?
	;

fragment
HexDigit
	:	[0-9a-fA-F]
	;

fragment
HexDigitsAndUnderscores
	:	HexDigitOrUnderscore+
	;

fragment
HexDigitOrUnderscore
	:	HexDigit
	|	'_'
	;

fragment
OctalNumeral
	:	'0' Underscores? OctalDigits
	;

fragment
OctalDigits
	:	OctalDigit (OctalDigitsAndUnderscores? OctalDigit)?
	;

fragment
OctalDigit
	:	[0-7]
	;

fragment
OctalDigitsAndUnderscores
	:	OctalDigitOrUnderscore+
	;

fragment
OctalDigitOrUnderscore
	:	OctalDigit
	|	'_'
	;

fragment
BinaryNumeral
	:	'0' [bB] BinaryDigits
	;

fragment
BinaryDigits
	:	BinaryDigit (BinaryDigitsAndUnderscores? BinaryDigit)?
	;

fragment
BinaryDigit
	:	[01]
	;

fragment
BinaryDigitsAndUnderscores
	:	BinaryDigitOrUnderscore+
	;

fragment
BinaryDigitOrUnderscore
	:	BinaryDigit
	|	'_'
	;

// §3.10.2 Floating-Point Literals

FloatingPointLiteral
	:	DecimalFloatingPointLiteral
	|	HexadecimalFloatingPointLiteral
	;

fragment
DecimalFloatingPointLiteral
	:	Digits '.' Digits? ExponentPart? FloatTypeSuffix?
	|	'.' Digits ExponentPart? FloatTypeSuffix?
	|	Digits ExponentPart FloatTypeSuffix?
	|	Digits FloatTypeSuffix
	;

fragment
ExponentPart
	:	ExponentIndicator SignedInteger
	;

fragment
ExponentIndicator
	:	[eE]
	;

fragment
SignedInteger
	:	Sign? Digits
	;

fragment
Sign
	:	[+-]
	;

fragment
FloatTypeSuffix
	:	[fFdD]
	;

fragment
HexadecimalFloatingPointLiteral
	:	HexSignificand BinaryExponent FloatTypeSuffix?
	;

fragment
HexSignificand
	:	HexNumeral '.'?
	|	'0' [xX] HexDigits? '.' HexDigits
	;

fragment
BinaryExponent
	:	BinaryExponentIndicator SignedInteger
	;

fragment
BinaryExponentIndicator
	:	[pP]
	;

// §3.10.3 Boolean Literals

BooleanLiteral
	:	'true'
	|	'false'
	;

// §3.10.4 Character Literals

CharacterLiteral
	:	'\'' SingleCharacter '\''
	|	'\'' EscapeSequence '\''
	;

fragment
SingleCharacter
	:	~['\\]
	;
// §3.10.5 String Literals
StringLiteral
	:	'"' StringCharacters? '"'
	;
fragment
StringCharacters
	:	StringCharacter+
	;
fragment
StringCharacter
	:	~["\\]
	|	EscapeSequence
	;
// §3.10.6 Escape Sequences for Character and String Literals
fragment
EscapeSequence
	:	'\\' [btnfr"'\\]
	|	OctalEscape
    |   UnicodeEscape // This is not in the spec but prevents having to preprocess the input
	;

fragment
OctalEscape
	:	'\\' OctalDigit
	|	'\\' OctalDigit OctalDigit
	|	'\\' ZeroToThree OctalDigit OctalDigit
	;

fragment
ZeroToThree
	:	[0-3]
	;

// This is not in the spec but prevents having to preprocess the input
fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

// §3.10.7 The Null Literal

NullLiteral
	:	'null'
	;

// §3.11 Separators

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
SEMI : ';';
COMMA : ',';
DOT : '.';

// §3.12 Operators

ASSIGN : '=';
GT : '>';
LT : '<';
BANG : '!';
TILDE : '~';
QUESTION : '?';
COLON : ':';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';
AND : '&&';
OR : '||';
INC : '++';
DEC : '--';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
BITAND : '&';
BITOR : '|';
CARET : '^';
MOD : '%';
ARROW : '->';
COLONCOLON : '::';

ADD_ASSIGN : '+=';
SUB_ASSIGN : '-=';
MUL_ASSIGN : '*=';
DIV_ASSIGN : '/=';
AND_ASSIGN : '&=';
OR_ASSIGN : '|=';
XOR_ASSIGN : '^=';
MOD_ASSIGN : '%=';
LSHIFT_ASSIGN : '<<=';
RSHIFT_ASSIGN : '>>=';
URSHIFT_ASSIGN : '>>>=';

// §3.8 Identifiers (must appear after all keywords in the grammar)

Identifier
	:	JavaLetter JavaLetterOrDigit*
	;

fragment
JavaLetter
	:	[a-zA-Z$_] // these are the "java letters" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierStart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

fragment
JavaLetterOrDigit
	:	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierPart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

//
// Additional symbols not defined in the lexical specification
//

AT : '@';
ELLIPSIS : '...';

//
// Whitespace and comments
//

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;