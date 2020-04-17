// Generated from ECMAScript.g4 by ANTLR 4.5
package langparser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ECMAScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, RegularExpressionLiteral=47, LineTerminator=48, OpenBracket=49, 
		CloseBracket=50, OpenParen=51, CloseParen=52, OpenBrace=53, CloseBrace=54, 
		SemiColon=55, Comma=56, Assign=57, QuestionMark=58, Colon=59, Dot=60, 
		PlusPlus=61, MinusMinus=62, Plus=63, Minus=64, BitNot=65, Not=66, Multiply=67, 
		Divide=68, Modulus=69, RightShiftArithmetic=70, LeftShiftArithmetic=71, 
		RightShiftLogical=72, LessThan=73, MoreThan=74, LessThanEquals=75, GreaterThanEquals=76, 
		Equals=77, NotEquals=78, IdentityEquals=79, IdentityNotEquals=80, BitAnd=81, 
		BitXOr=82, BitOr=83, And=84, Or=85, MultiplyAssign=86, DivideAssign=87, 
		ModulusAssign=88, PlusAssign=89, MinusAssign=90, LeftShiftArithmeticAssign=91, 
		RightShiftArithmeticAssign=92, RightShiftLogicalAssign=93, BitAndAssign=94, 
		BitXorAssign=95, BitOrAssign=96, BooleanLiteral=97, DecimalLiteral=98, 
		HexIntegerLiteral=99, OctalIntegerLiteral=100, BinaryLiteral=101, Break=102, 
		Do=103, Instanceof=104, Typeof=105, Case=106, Else=107, New=108, Var=109, 
		Catch=110, Finally=111, Return=112, Void=113, Continue=114, For=115, Switch=116, 
		While=117, Debugger=118, Function=119, This=120, With=121, Default=122, 
		If=123, Throw=124, Delete=125, In=126, Try=127, Of=128, Get=129, Set=130, 
		Each=131, Class=132, Enum=133, Extends=134, Super=135, Const=136, Export=137, 
		Import=138, Implements=139, Let=140, Private=141, Public=142, Interface=143, 
		Package=144, Protected=145, Static=146, Yield=147, Identifier=148, StringLiteral=149, 
		WhiteSpaces=150, MultiLineComment=151, SingleLineComment=152, UnexpectedCharacter=153;
	public static final int
		RULE_program = 0, RULE_statementListItem = 1, RULE_functionDeclaration = 2, 
		RULE_classDeclaration = 3, RULE_classTail = 4, RULE_classHeritage = 5, 
		RULE_classBody = 6, RULE_classElement = 7, RULE_statement = 8, RULE_yieldExpression = 9, 
		RULE_block = 10, RULE_statementList = 11, RULE_variableStatement = 12, 
		RULE_variableDeclarationList = 13, RULE_variableDeclaration = 14, RULE_identifierBinding = 15, 
		RULE_identifierReference = 16, RULE_identifierPattern = 17, RULE_arrayBindingPattern = 18, 
		RULE_restElement = 19, RULE_bindingElementList = 20, RULE_objectBindingPattern = 21, 
		RULE_argumentBindingPattern = 22, RULE_bindingPropertyList = 23, RULE_bindingProperty = 24, 
		RULE_initialiser = 25, RULE_emptyStatement = 26, RULE_expressionStatement = 27, 
		RULE_ifStatement = 28, RULE_elseStatement = 29, RULE_iterationStatement = 30, 
		RULE_continueStatement = 31, RULE_breakStatement = 32, RULE_returnStatement = 33, 
		RULE_withStatement = 34, RULE_switchStatement = 35, RULE_caseBlock = 36, 
		RULE_caseClauses = 37, RULE_caseClause = 38, RULE_defaultClause = 39, 
		RULE_labelledStatement = 40, RULE_throwStatement = 41, RULE_tryStatement = 42, 
		RULE_catchProduction = 43, RULE_finallyProduction = 44, RULE_debuggerStatement = 45, 
		RULE_formalParameterList = 46, RULE_formalParameters = 47, RULE_formalParameter = 48, 
		RULE_arrayLiteral = 49, RULE_elementList = 50, RULE_elision = 51, RULE_objectLiteral = 52, 
		RULE_propertyNameAndValueList = 53, RULE_propertyAssignment = 54, RULE_methodDefinition = 55, 
		RULE_propertyName = 56, RULE_arguments = 57, RULE_argumentList = 58, RULE_arrowFunction = 59, 
		RULE_arrowParameters = 60, RULE_conciseBody = 61, RULE_expression = 62, 
		RULE_assignmentExpression = 63, RULE_conditionalExpression = 64, RULE_leftHandSideExpression = 65, 
		RULE_callExpression = 66, RULE_newExpression = 67, RULE_memberExpression = 68, 
		RULE_functionExpression = 69, RULE_primaryExpression = 70, RULE_assignmentOperator = 71, 
		RULE_literal = 72, RULE_stringLiteral = 73, RULE_numericLiteral = 74, 
		RULE_identifierName = 75, RULE_reservedWord = 76, RULE_keyword = 77, RULE_futureReservedWord = 78, 
		RULE_eos = 79, RULE_eof = 80;
	public static final String[] ruleNames = {
		"program", "statementListItem", "functionDeclaration", "classDeclaration", 
		"classTail", "classHeritage", "classBody", "classElement", "statement", 
		"yieldExpression", "block", "statementList", "variableStatement", "variableDeclarationList", 
		"variableDeclaration", "identifierBinding", "identifierReference", "identifierPattern", 
		"arrayBindingPattern", "restElement", "bindingElementList", "objectBindingPattern", 
		"argumentBindingPattern", "bindingPropertyList", "bindingProperty", "initialiser", 
		"emptyStatement", "expressionStatement", "ifStatement", "elseStatement", 
		"iterationStatement", "continueStatement", "breakStatement", "returnStatement", 
		"withStatement", "switchStatement", "caseBlock", "caseClauses", "caseClause", 
		"defaultClause", "labelledStatement", "throwStatement", "tryStatement", 
		"catchProduction", "finallyProduction", "debuggerStatement", "formalParameterList", 
		"formalParameters", "formalParameter", "arrayLiteral", "elementList", 
		"elision", "objectLiteral", "propertyNameAndValueList", "propertyAssignment", 
		"methodDefinition", "propertyName", "arguments", "argumentList", "arrowFunction", 
		"arrowParameters", "conciseBody", "expression", "assignmentExpression", 
		"conditionalExpression", "leftHandSideExpression", "callExpression", "newExpression", 
		"memberExpression", "functionExpression", "primaryExpression", "assignmentOperator", 
		"literal", "stringLiteral", "numericLiteral", "identifierName", "reservedWord", 
		"keyword", "futureReservedWord", "eos", "eof"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'function'", "'function*'", "'static'", "'yield'", "'yield*'", 
		"'var'", "'let'", "'...'", "'if'", "'else'", "'do'", "'while'", "'for'", 
		"'each'", "'in'", "'of'", "'continue'", "'break'", "'return'", "'with'", 
		"'switch'", "'case'", "'default'", "'throw'", "'try'", "'catch'", "'finally'", 
		"'debugger'", "'get'", "'set'", "'=>'", "'instanceof'", "'delete'", "'void'", 
		"'typeof'", "'new'", "'this'", "'null'", "'true'", "'false'", "'implements'", 
		"'private'", "'public'", "'interface'", "'package'", "'protected'", null, 
		null, "'['", "']'", "'('", "')'", "'{'", "'}'", "';'", "','", "'='", "'?'", 
		"':'", "'.'", "'++'", "'--'", "'+'", "'-'", "'~'", "'!'", "'*'", "'/'", 
		"'%'", "'>>'", "'<<'", "'>>>'", "'<'", "'>'", "'<='", "'>='", "'=='", 
		"'!='", "'==='", "'!=='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'*='", 
		"'/='", "'%='", "'+='", "'-='", "'<<='", "'>>='", "'>>>='", "'&='", "'^='", 
		"'|='", null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"'class'", "'enum'", "'extends'", "'super'", "'const'", "'export'", "'import'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "RegularExpressionLiteral", 
		"LineTerminator", "OpenBracket", "CloseBracket", "OpenParen", "CloseParen", 
		"OpenBrace", "CloseBrace", "SemiColon", "Comma", "Assign", "QuestionMark", 
		"Colon", "Dot", "PlusPlus", "MinusMinus", "Plus", "Minus", "BitNot", "Not", 
		"Multiply", "Divide", "Modulus", "RightShiftArithmetic", "LeftShiftArithmetic", 
		"RightShiftLogical", "LessThan", "MoreThan", "LessThanEquals", "GreaterThanEquals", 
		"Equals", "NotEquals", "IdentityEquals", "IdentityNotEquals", "BitAnd", 
		"BitXOr", "BitOr", "And", "Or", "MultiplyAssign", "DivideAssign", "ModulusAssign", 
		"PlusAssign", "MinusAssign", "LeftShiftArithmeticAssign", "RightShiftArithmeticAssign", 
		"RightShiftLogicalAssign", "BitAndAssign", "BitXorAssign", "BitOrAssign", 
		"BooleanLiteral", "DecimalLiteral", "HexIntegerLiteral", "OctalIntegerLiteral", 
		"BinaryLiteral", "Break", "Do", "Instanceof", "Typeof", "Case", "Else", 
		"New", "Var", "Catch", "Finally", "Return", "Void", "Continue", "For", 
		"Switch", "While", "Debugger", "Function", "This", "With", "Default", 
		"If", "Throw", "Delete", "In", "Try", "Of", "Get", "Set", "Each", "Class", 
		"Enum", "Extends", "Super", "Const", "Export", "Import", "Implements", 
		"Let", "Private", "Public", "Interface", "Package", "Protected", "Static", 
		"Yield", "Identifier", "StringLiteral", "WhiteSpaces", "MultiLineComment", 
		"SingleLineComment", "UnexpectedCharacter"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ECMAScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	  
	    private boolean here(final int type) {

	        int possibleIndexEosToken = this.getCurrentToken().getTokenIndex() - 1;
	        Token ahead = _input.get(possibleIndexEosToken);

	        return (ahead.getChannel() == Lexer.HIDDEN) && (ahead.getType() == type);
	    }

	    private boolean lineTerminatorAhead() {

	        int possibleIndexEosToken = this.getCurrentToken().getTokenIndex() - 1;
	        Token ahead = _input.get(possibleIndexEosToken);

	        if (ahead.getChannel() != Lexer.HIDDEN) {
	            return false;
	        }

	        String text = ahead.getText();
	        int type = ahead.getType();

	        return (type == MultiLineComment && (text.contains("\r") || text.contains("\n"))) ||
	                (type == LineTerminator);
	    }                                
	    private boolean functionAhead() {

	        int possibleIndexEosToken = this.getCurrentToken().getTokenIndex() - 1;
	        Token ahead = _input.get(possibleIndexEosToken);

	        if (ahead.getChannel() != Lexer.HIDDEN) {
	            return false;
	        }

	        String text = ahead.getText();
	        int type = ahead.getType();

	        return (type == Function);
	    }                                


	public ECMAScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ECMAScriptParser.EOF, 0); }
		public List<StatementListItemContext> statementListItem() {
			return getRuleContexts(StatementListItemContext.class);
		}
		public StatementListItemContext statementListItem(int i) {
			return getRuleContext(StatementListItemContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(162); 
					statementListItem();
					}
					} 
				}
				setState(167);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(168); 
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementListItemContext extends ParserRuleContext {
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementListItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementListItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterStatementListItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitStatementListItem(this);
		}
	}

	public final StatementListItemContext statementListItem() throws RecognitionException {
		StatementListItemContext _localctx = new StatementListItemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statementListItem);
		try {
			setState(173);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170); 
				functionDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171); 
				classDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(172); 
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public List<StatementListItemContext> statementListItem() {
			return getRuleContexts(StatementListItemContext.class);
		}
		public StatementListItemContext statementListItem(int i) {
			return getRuleContext(StatementListItemContext.class,i);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitFunctionDeclaration(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(177);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)))) != 0)) {
				{
				setState(176); 
				identifierName();
				}
			}

			setState(179); 
			match(OpenParen);
			setState(181);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(180); 
				formalParameterList();
				}
				break;
			}
			setState(183); 
			match(CloseParen);
			setState(193);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(184); 
				match(OpenBrace);
				setState(188);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(185); 
						statementListItem();
						}
						} 
					}
					setState(190);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				setState(191); 
				match(CloseBrace);
				}
				break;
			case 2:
				{
				setState(192); 
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public ClassTailContext classTail() {
			return getRuleContext(ClassTailContext.class,0);
		}
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); 
			match(Class);
			setState(197);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(196); 
				identifierReference();
				}
				break;
			}
			setState(199); 
			classTail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassTailContext extends ParserRuleContext {
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public ClassHeritageContext classHeritage() {
			return getRuleContext(ClassHeritageContext.class,0);
		}
		public ClassTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterClassTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitClassTail(this);
		}
	}

	public final ClassTailContext classTail() throws RecognitionException {
		ClassTailContext _localctx = new ClassTailContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if (_la==Extends) {
				{
				setState(201); 
				classHeritage();
				}
			}

			setState(204); 
			match(OpenBrace);
			setState(205); 
			classBody();
			setState(206); 
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassHeritageContext extends ParserRuleContext {
		public TerminalNode Extends() { return getToken(ECMAScriptParser.Extends, 0); }
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ClassHeritageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classHeritage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterClassHeritage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitClassHeritage(this);
		}
	}

	public final ClassHeritageContext classHeritage() throws RecognitionException {
		ClassHeritageContext _localctx = new ClassHeritageContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classHeritage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); 
			match(Extends);
			setState(209); 
			conditionalExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public List<ClassElementContext> classElement() {
			return getRuleContexts(ClassElementContext.class);
		}
		public ClassElementContext classElement(int i) {
			return getRuleContext(ClassElementContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitClassBody(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_classBody);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(211); 
					classElement();
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassElementContext extends ParserRuleContext {
		public MethodDefinitionContext methodDefinition() {
			return getRuleContext(MethodDefinitionContext.class,0);
		}
		public ClassElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterClassElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitClassElement(this);
		}
	}

	public final ClassElementContext classElement() throws RecognitionException {
		ClassElementContext _localctx = new ClassElementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_classElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(217); 
				match(T__2);
				}
				break;
			}
			setState(220); 
			methodDefinition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public VariableStatementContext variableStatement() {
			return getRuleContext(VariableStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public WithStatementContext withStatement() {
			return getRuleContext(WithStatementContext.class,0);
		}
		public LabelledStatementContext labelledStatement() {
			return getRuleContext(LabelledStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public TryStatementContext tryStatement() {
			return getRuleContext(TryStatementContext.class,0);
		}
		public DebuggerStatementContext debuggerStatement() {
			return getRuleContext(DebuggerStatementContext.class,0);
		}
		public YieldExpressionContext yieldExpression() {
			return getRuleContext(YieldExpressionContext.class,0);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(242);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222); 
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(223); 
				variableStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(224); 
				emptyStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(225); 
				ifStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(226); 
				expressionStatement();
				setState(228);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(227); 
					eos();
					}
					break;
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(230); 
				iterationStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(231); 
				continueStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(232); 
				breakStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(233); 
				returnStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(234); 
				withStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(235); 
				labelledStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(236); 
				switchStatement();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(237); 
				throwStatement();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(238); 
				tryStatement();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(239); 
				debuggerStatement();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(240); 
				yieldExpression();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(241); 
				elseStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YieldExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public YieldExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yieldExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterYieldExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitYieldExpression(this);
		}
	}

	public final YieldExpressionContext yieldExpression() throws RecognitionException {
		YieldExpressionContext _localctx = new YieldExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_yieldExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(246);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(245); 
				assignmentExpression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248); 
			match(OpenBrace);
			setState(250);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(249); 
				statementList(0);
				}
				break;
			}
			setState(252); 
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementListContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public StatementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitStatementList(this);
		}
	}

	public final StatementListContext statementList() throws RecognitionException {
		return statementList(0);
	}

	private StatementListContext statementList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementListContext _localctx = new StatementListContext(_ctx, _parentState);
		StatementListContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_statementList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(255); 
			statement();
			}
			_ctx.stop = _input.LT(-1);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statementList);
					setState(257);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(258); 
					statement();
					}
					} 
				}
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VariableStatementContext extends ParserRuleContext {
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public VariableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterVariableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitVariableStatement(this);
		}
	}

	public final VariableStatementContext variableStatement() throws RecognitionException {
		VariableStatementContext _localctx = new VariableStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6 || _la==Const) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(265); 
			variableDeclarationList(0);
			setState(267);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(266); 
				eos();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationListContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public VariableDeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterVariableDeclarationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitVariableDeclarationList(this);
		}
	}

	public final VariableDeclarationListContext variableDeclarationList() throws RecognitionException {
		return variableDeclarationList(0);
	}

	private VariableDeclarationListContext variableDeclarationList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VariableDeclarationListContext _localctx = new VariableDeclarationListContext(_ctx, _parentState);
		VariableDeclarationListContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_variableDeclarationList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(270); 
			variableDeclaration();
			}
			_ctx.stop = _input.LT(-1);
			setState(277);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new VariableDeclarationListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_variableDeclarationList);
					setState(272);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(273); 
					match(Comma);
					setState(274); 
					variableDeclaration();
					}
					} 
				}
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public IdentifierBindingContext identifierBinding() {
			return getRuleContext(IdentifierBindingContext.class,0);
		}
		public InitialiserContext initialiser() {
			return getRuleContext(InitialiserContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); 
			identifierBinding();
			setState(282);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(281); 
				initialiser();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierBindingContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public IdentifierPatternContext identifierPattern() {
			return getRuleContext(IdentifierPatternContext.class,0);
		}
		public IdentifierBindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierBinding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterIdentifierBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitIdentifierBinding(this);
		}
	}

	public final IdentifierBindingContext identifierBinding() throws RecognitionException {
		IdentifierBindingContext _localctx = new IdentifierBindingContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_identifierBinding);
		try {
			setState(286);
			switch (_input.LA(1)) {
			case T__0:
			case T__2:
			case T__3:
			case T__5:
			case T__6:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(284); 
				identifierReference();
				}
				break;
			case OpenBracket:
			case OpenParen:
			case OpenBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(285); 
				identifierPattern();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierReferenceContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public IdentifierReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterIdentifierReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitIdentifierReference(this);
		}
	}

	public final IdentifierReferenceContext identifierReference() throws RecognitionException {
		IdentifierReferenceContext _localctx = new IdentifierReferenceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_identifierReference);
		try {
			setState(290);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288); 
				identifierName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(289); 
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierPatternContext extends ParserRuleContext {
		public ArrayBindingPatternContext arrayBindingPattern() {
			return getRuleContext(ArrayBindingPatternContext.class,0);
		}
		public ObjectBindingPatternContext objectBindingPattern() {
			return getRuleContext(ObjectBindingPatternContext.class,0);
		}
		public ArgumentBindingPatternContext argumentBindingPattern() {
			return getRuleContext(ArgumentBindingPatternContext.class,0);
		}
		public IdentifierPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterIdentifierPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitIdentifierPattern(this);
		}
	}

	public final IdentifierPatternContext identifierPattern() throws RecognitionException {
		IdentifierPatternContext _localctx = new IdentifierPatternContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_identifierPattern);
		try {
			setState(295);
			switch (_input.LA(1)) {
			case OpenBracket:
				enterOuterAlt(_localctx, 1);
				{
				setState(292); 
				arrayBindingPattern();
				}
				break;
			case OpenBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(293); 
				objectBindingPattern();
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 3);
				{
				setState(294); 
				argumentBindingPattern();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayBindingPatternContext extends ParserRuleContext {
		public ElisionContext elision() {
			return getRuleContext(ElisionContext.class,0);
		}
		public RestElementContext restElement() {
			return getRuleContext(RestElementContext.class,0);
		}
		public BindingElementListContext bindingElementList() {
			return getRuleContext(BindingElementListContext.class,0);
		}
		public ArrayBindingPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayBindingPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterArrayBindingPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitArrayBindingPattern(this);
		}
	}

	public final ArrayBindingPatternContext arrayBindingPattern() throws RecognitionException {
		ArrayBindingPatternContext _localctx = new ArrayBindingPatternContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arrayBindingPattern);
		int _la;
		try {
			setState(320);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(297); 
				match(OpenBracket);
				setState(299);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(298); 
					elision();
					}
				}

				setState(302);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(301); 
					restElement();
					}
				}

				setState(304); 
				match(CloseBracket);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(305); 
				match(OpenBracket);
				setState(306); 
				bindingElementList();
				setState(307); 
				match(CloseBracket);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(309); 
				match(OpenBracket);
				setState(310); 
				bindingElementList();
				setState(311); 
				match(Comma);
				setState(313);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(312); 
					elision();
					}
				}

				setState(316);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(315); 
					restElement();
					}
				}

				setState(318); 
				match(CloseBracket);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RestElementContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public RestElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterRestElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitRestElement(this);
		}
	}

	public final RestElementContext restElement() throws RecognitionException {
		RestElementContext _localctx = new RestElementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_restElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322); 
			match(T__7);
			setState(323); 
			identifierReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BindingElementListContext extends ParserRuleContext {
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<ElisionContext> elision() {
			return getRuleContexts(ElisionContext.class);
		}
		public ElisionContext elision(int i) {
			return getRuleContext(ElisionContext.class,i);
		}
		public BindingElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bindingElementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterBindingElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitBindingElementList(this);
		}
	}

	public final BindingElementListContext bindingElementList() throws RecognitionException {
		BindingElementListContext _localctx = new BindingElementListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_bindingElementList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(326);
					_la = _input.LA(1);
					if (_la==Comma) {
						{
						setState(325); 
						elision();
						}
					}

					setState(328); 
					variableDeclaration();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(331); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectBindingPatternContext extends ParserRuleContext {
		public BindingPropertyListContext bindingPropertyList() {
			return getRuleContext(BindingPropertyListContext.class,0);
		}
		public ObjectBindingPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectBindingPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterObjectBindingPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitObjectBindingPattern(this);
		}
	}

	public final ObjectBindingPatternContext objectBindingPattern() throws RecognitionException {
		ObjectBindingPatternContext _localctx = new ObjectBindingPatternContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_objectBindingPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333); 
			match(OpenBrace);
			setState(335);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace))) != 0) || ((((_la - 98)) & ~0x3f) == 0 && ((1L << (_la - 98)) & ((1L << (DecimalLiteral - 98)) | (1L << (HexIntegerLiteral - 98)) | (1L << (OctalIntegerLiteral - 98)) | (1L << (BinaryLiteral - 98)) | (1L << (Class - 98)) | (1L << (Enum - 98)) | (1L << (Extends - 98)) | (1L << (Super - 98)) | (1L << (Const - 98)) | (1L << (Export - 98)) | (1L << (Import - 98)) | (1L << (Identifier - 98)) | (1L << (StringLiteral - 98)))) != 0)) {
				{
				setState(334); 
				bindingPropertyList(0);
				}
			}

			setState(337); 
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentBindingPatternContext extends ParserRuleContext {
		public BindingPropertyListContext bindingPropertyList() {
			return getRuleContext(BindingPropertyListContext.class,0);
		}
		public ArgumentBindingPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentBindingPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterArgumentBindingPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitArgumentBindingPattern(this);
		}
	}

	public final ArgumentBindingPatternContext argumentBindingPattern() throws RecognitionException {
		ArgumentBindingPatternContext _localctx = new ArgumentBindingPatternContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argumentBindingPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339); 
			match(OpenParen);
			setState(341);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace))) != 0) || ((((_la - 98)) & ~0x3f) == 0 && ((1L << (_la - 98)) & ((1L << (DecimalLiteral - 98)) | (1L << (HexIntegerLiteral - 98)) | (1L << (OctalIntegerLiteral - 98)) | (1L << (BinaryLiteral - 98)) | (1L << (Class - 98)) | (1L << (Enum - 98)) | (1L << (Extends - 98)) | (1L << (Super - 98)) | (1L << (Const - 98)) | (1L << (Export - 98)) | (1L << (Import - 98)) | (1L << (Identifier - 98)) | (1L << (StringLiteral - 98)))) != 0)) {
				{
				setState(340); 
				bindingPropertyList(0);
				}
			}

			setState(343); 
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BindingPropertyListContext extends ParserRuleContext {
		public BindingPropertyContext bindingProperty() {
			return getRuleContext(BindingPropertyContext.class,0);
		}
		public BindingPropertyListContext bindingPropertyList() {
			return getRuleContext(BindingPropertyListContext.class,0);
		}
		public BindingPropertyListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bindingPropertyList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterBindingPropertyList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitBindingPropertyList(this);
		}
	}

	public final BindingPropertyListContext bindingPropertyList() throws RecognitionException {
		return bindingPropertyList(0);
	}

	private BindingPropertyListContext bindingPropertyList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BindingPropertyListContext _localctx = new BindingPropertyListContext(_ctx, _parentState);
		BindingPropertyListContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_bindingPropertyList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(346); 
			bindingProperty();
			}
			_ctx.stop = _input.LT(-1);
			setState(353);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BindingPropertyListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_bindingPropertyList);
					setState(348);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(349); 
					match(Comma);
					setState(350); 
					bindingProperty();
					}
					} 
				}
				setState(355);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BindingPropertyContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public BindingPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bindingProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterBindingProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitBindingProperty(this);
		}
	}

	public final BindingPropertyContext bindingProperty() throws RecognitionException {
		BindingPropertyContext _localctx = new BindingPropertyContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_bindingProperty);
		try {
			setState(361);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(356); 
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(357); 
				propertyName();
				setState(358); 
				match(Colon);
				setState(359); 
				variableDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitialiserContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public InitialiserContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initialiser; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterInitialiser(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitInitialiser(this);
		}
	}

	public final InitialiserContext initialiser() throws RecognitionException {
		InitialiserContext _localctx = new InitialiserContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_initialiser);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363); 
			match(Assign);
			setState(364); 
			assignmentExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(ECMAScriptParser.SemiColon, 0); }
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitEmptyStatement(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366); 
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitExpressionStatement(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_expressionStatement);
		try {
			setState(372);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(368);
				if (!(!here(OpenBrace))) throw new FailedPredicateException(this, "!here(OpenBrace)");
				setState(369); 
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(370);
				if (!(!here(Function))) throw new FailedPredicateException(this, "!here(Function)");
				setState(371); 
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374); 
			match(T__8);
			setState(375); 
			match(OpenParen);
			setState(376); 
			expression(0);
			setState(377); 
			match(CloseParen);
			setState(378); 
			statement();
			setState(380);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(379); 
				elseStatement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitElseStatement(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382); 
			match(T__9);
			setState(383); 
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public IdentifierBindingContext identifierBinding() {
			return getRuleContext(IdentifierBindingContext.class,0);
		}
		public LeftHandSideExpressionContext leftHandSideExpression() {
			return getRuleContext(LeftHandSideExpressionContext.class,0);
		}
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterIterationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitIterationStatement(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_iterationStatement);
		int _la;
		try {
			setState(444);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(385); 
				match(T__10);
				setState(386); 
				statement();
				setState(387); 
				match(T__11);
				setState(388); 
				match(OpenParen);
				setState(389); 
				expression(0);
				setState(390); 
				match(CloseParen);
				setState(391); 
				eos();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(393); 
				match(T__11);
				setState(394); 
				match(OpenParen);
				setState(395); 
				expression(0);
				setState(396); 
				match(CloseParen);
				setState(397); 
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(399); 
				match(T__12);
				setState(400); 
				match(OpenParen);
				setState(402);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Minus - 64)) | (1L << (BitNot - 64)) | (1L << (Not - 64)) | (1L << (DecimalLiteral - 64)) | (1L << (HexIntegerLiteral - 64)) | (1L << (OctalIntegerLiteral - 64)) | (1L << (BinaryLiteral - 64)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)) | (1L << (StringLiteral - 132)))) != 0)) {
					{
					setState(401); 
					expression(0);
					}
				}

				setState(404); 
				match(SemiColon);
				setState(406);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Minus - 64)) | (1L << (BitNot - 64)) | (1L << (Not - 64)) | (1L << (DecimalLiteral - 64)) | (1L << (HexIntegerLiteral - 64)) | (1L << (OctalIntegerLiteral - 64)) | (1L << (BinaryLiteral - 64)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)) | (1L << (StringLiteral - 132)))) != 0)) {
					{
					setState(405); 
					expression(0);
					}
				}

				setState(408); 
				match(SemiColon);
				setState(410);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Minus - 64)) | (1L << (BitNot - 64)) | (1L << (Not - 64)) | (1L << (DecimalLiteral - 64)) | (1L << (HexIntegerLiteral - 64)) | (1L << (OctalIntegerLiteral - 64)) | (1L << (BinaryLiteral - 64)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)) | (1L << (StringLiteral - 132)))) != 0)) {
					{
					setState(409); 
					expression(0);
					}
				}

				setState(412); 
				match(CloseParen);
				setState(413); 
				statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(414); 
				match(T__12);
				setState(415); 
				match(OpenParen);
				setState(416);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6 || _la==Const) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(417); 
				variableDeclarationList(0);
				setState(418); 
				match(SemiColon);
				setState(420);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Minus - 64)) | (1L << (BitNot - 64)) | (1L << (Not - 64)) | (1L << (DecimalLiteral - 64)) | (1L << (HexIntegerLiteral - 64)) | (1L << (OctalIntegerLiteral - 64)) | (1L << (BinaryLiteral - 64)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)) | (1L << (StringLiteral - 132)))) != 0)) {
					{
					setState(419); 
					expression(0);
					}
				}

				setState(422); 
				match(SemiColon);
				setState(424);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Minus - 64)) | (1L << (BitNot - 64)) | (1L << (Not - 64)) | (1L << (DecimalLiteral - 64)) | (1L << (HexIntegerLiteral - 64)) | (1L << (OctalIntegerLiteral - 64)) | (1L << (BinaryLiteral - 64)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)) | (1L << (StringLiteral - 132)))) != 0)) {
					{
					setState(423); 
					expression(0);
					}
				}

				setState(426); 
				match(CloseParen);
				setState(427); 
				statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(429); 
				match(T__12);
				setState(431);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(430); 
					match(T__13);
					}
				}

				setState(433); 
				match(OpenParen);
				setState(437);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(434);
					_la = _input.LA(1);
					if ( !(_la==T__5 || _la==T__6 || _la==Const) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(435); 
					identifierBinding();
					}
					break;
				case 2:
					{
					setState(436); 
					leftHandSideExpression();
					}
					break;
				}
				setState(439);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(440); 
				expression(0);
				setState(441); 
				match(CloseParen);
				setState(442); 
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitContinueStatement(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446); 
			match(T__16);
			setState(447);
			if (!(!here(LineTerminator))) throw new FailedPredicateException(this, "!here(LineTerminator)");
			setState(449);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(448); 
				identifierName();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451); 
			match(T__17);
			setState(452);
			if (!(!here(LineTerminator))) throw new FailedPredicateException(this, "!here(LineTerminator)");
			setState(454);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(453); 
				identifierName();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456); 
			match(T__18);
			setState(457);
			if (!(!here(LineTerminator))) throw new FailedPredicateException(this, "!here(LineTerminator)");
			setState(459);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(458); 
				expression(0);
				}
				break;
			}
			setState(462);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(461); 
				eos();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WithStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WithStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterWithStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitWithStatement(this);
		}
	}

	public final WithStatementContext withStatement() throws RecognitionException {
		WithStatementContext _localctx = new WithStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_withStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464); 
			match(T__19);
			setState(465); 
			match(OpenParen);
			setState(466); 
			expression(0);
			setState(467); 
			match(CloseParen);
			setState(468); 
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CaseBlockContext caseBlock() {
			return getRuleContext(CaseBlockContext.class,0);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitSwitchStatement(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_switchStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470); 
			match(T__20);
			setState(471); 
			match(OpenParen);
			setState(472); 
			expression(0);
			setState(473); 
			match(CloseParen);
			setState(474); 
			caseBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseBlockContext extends ParserRuleContext {
		public List<CaseClausesContext> caseClauses() {
			return getRuleContexts(CaseClausesContext.class);
		}
		public CaseClausesContext caseClauses(int i) {
			return getRuleContext(CaseClausesContext.class,i);
		}
		public DefaultClauseContext defaultClause() {
			return getRuleContext(DefaultClauseContext.class,0);
		}
		public CaseBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterCaseBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitCaseBlock(this);
		}
	}

	public final CaseBlockContext caseBlock() throws RecognitionException {
		CaseBlockContext _localctx = new CaseBlockContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_caseBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476); 
			match(OpenBrace);
			setState(478);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(477); 
				caseClauses();
				}
			}

			setState(484);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(480); 
				defaultClause();
				setState(482);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(481); 
					caseClauses();
					}
				}

				}
			}

			setState(486); 
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseClausesContext extends ParserRuleContext {
		public List<CaseClauseContext> caseClause() {
			return getRuleContexts(CaseClauseContext.class);
		}
		public CaseClauseContext caseClause(int i) {
			return getRuleContext(CaseClauseContext.class,i);
		}
		public CaseClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterCaseClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitCaseClauses(this);
		}
	}

	public final CaseClausesContext caseClauses() throws RecognitionException {
		CaseClausesContext _localctx = new CaseClausesContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_caseClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(488); 
				caseClause();
				}
				}
				setState(491); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__21 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseClauseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public CaseClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitCaseClause(this);
		}
	}

	public final CaseClauseContext caseClause() throws RecognitionException {
		CaseClauseContext _localctx = new CaseClauseContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_caseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493); 
			match(T__21);
			setState(494); 
			expression(0);
			setState(495); 
			match(Colon);
			setState(497);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(496); 
				statementList(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultClauseContext extends ParserRuleContext {
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public DefaultClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterDefaultClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitDefaultClause(this);
		}
	}

	public final DefaultClauseContext defaultClause() throws RecognitionException {
		DefaultClauseContext _localctx = new DefaultClauseContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_defaultClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499); 
			match(T__22);
			setState(500); 
			match(Colon);
			setState(502);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(501); 
				statementList(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelledStatementContext extends ParserRuleContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LabelledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelledStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterLabelledStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitLabelledStatement(this);
		}
	}

	public final LabelledStatementContext labelledStatement() throws RecognitionException {
		LabelledStatementContext _localctx = new LabelledStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_labelledStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504); 
			propertyName();
			setState(505); 
			match(Colon);
			setState(508);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(506); 
				statement();
				}
				break;
			case 2:
				{
				setState(507); 
				expression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitThrowStatement(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510); 
			match(T__23);
			setState(511);
			if (!(!here(LineTerminator))) throw new FailedPredicateException(this, "!here(LineTerminator)");
			setState(512); 
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TryStatementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<CatchProductionContext> catchProduction() {
			return getRuleContexts(CatchProductionContext.class);
		}
		public CatchProductionContext catchProduction(int i) {
			return getRuleContext(CatchProductionContext.class,i);
		}
		public FinallyProductionContext finallyProduction() {
			return getRuleContext(FinallyProductionContext.class,0);
		}
		public TryStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitTryStatement(this);
		}
	}

	public final TryStatementContext tryStatement() throws RecognitionException {
		TryStatementContext _localctx = new TryStatementContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_tryStatement);
		int _la;
		try {
			int _alt;
			setState(536);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(514); 
				match(T__24);
				setState(515); 
				block();
				setState(519);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(516); 
						catchProduction();
						}
						} 
					}
					setState(521);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(522); 
				match(T__24);
				setState(523); 
				block();
				setState(524); 
				finallyProduction();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(526); 
				match(T__24);
				setState(527); 
				block();
				setState(531);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__25) {
					{
					{
					setState(528); 
					catchProduction();
					}
					}
					setState(533);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(534); 
				finallyProduction();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchProductionContext extends ParserRuleContext {
		public IdentifierBindingContext identifierBinding() {
			return getRuleContext(IdentifierBindingContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CatchProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterCatchProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitCatchProduction(this);
		}
	}

	public final CatchProductionContext catchProduction() throws RecognitionException {
		CatchProductionContext _localctx = new CatchProductionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_catchProduction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538); 
			match(T__25);
			setState(539); 
			match(OpenParen);
			setState(540); 
			identifierBinding();
			setState(543);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(541); 
				match(T__8);
				setState(542); 
				expression(0);
				}
			}

			setState(545); 
			match(CloseParen);
			setState(546); 
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinallyProductionContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterFinallyProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitFinallyProduction(this);
		}
	}

	public final FinallyProductionContext finallyProduction() throws RecognitionException {
		FinallyProductionContext _localctx = new FinallyProductionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_finallyProduction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548); 
			match(T__26);
			setState(549); 
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DebuggerStatementContext extends ParserRuleContext {
		public DebuggerStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_debuggerStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterDebuggerStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitDebuggerStatement(this);
		}
	}

	public final DebuggerStatementContext debuggerStatement() throws RecognitionException {
		DebuggerStatementContext _localctx = new DebuggerStatementContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_debuggerStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(551); 
			match(T__27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterListContext extends ParserRuleContext {
		public RestElementContext restElement() {
			return getRuleContext(RestElementContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitFormalParameterList(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_formalParameterList);
		try {
			setState(559);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(553); 
				restElement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(554); 
				formalParameters();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(555); 
				formalParameters();
				setState(556); 
				match(Comma);
				setState(557); 
				restElement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParametersContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitFormalParameters(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_formalParameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(561); 
			formalParameter();
			setState(566);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(562); 
					match(Comma);
					setState(563); 
					formalParameter();
					}
					} 
				}
				setState(568);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public PropertyNameAndValueListContext propertyNameAndValueList() {
			return getRuleContext(PropertyNameAndValueListContext.class,0);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitFormalParameter(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_formalParameter);
		try {
			setState(573);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(569); 
				identifierName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(570); 
				arrayLiteral();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(571); 
				objectLiteral();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(572); 
				propertyNameAndValueList(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiteralContext extends ParserRuleContext {
		public ElisionContext elision() {
			return getRuleContext(ElisionContext.class,0);
		}
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitArrayLiteral(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_arrayLiteral);
		int _la;
		try {
			setState(592);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(575); 
				match(OpenBracket);
				setState(577);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(576); 
					elision();
					}
				}

				setState(579); 
				match(CloseBracket);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(580); 
				match(OpenBracket);
				setState(581); 
				elementList(0);
				setState(582); 
				match(CloseBracket);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(584); 
				match(OpenBracket);
				setState(585); 
				elementList(0);
				setState(586); 
				match(Comma);
				setState(588);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(587); 
					elision();
					}
				}

				setState(590); 
				match(CloseBracket);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementListContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ElisionContext elision() {
			return getRuleContext(ElisionContext.class,0);
		}
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public ElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitElementList(this);
		}
	}

	public final ElementListContext elementList() throws RecognitionException {
		return elementList(0);
	}

	private ElementListContext elementList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ElementListContext _localctx = new ElementListContext(_ctx, _parentState);
		ElementListContext _prevctx = _localctx;
		int _startState = 100;
		enterRecursionRule(_localctx, 100, RULE_elementList, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(596);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(595); 
				elision();
				}
			}

			setState(599);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(598); 
				match(T__7);
				}
			}

			setState(601); 
			assignmentExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(614);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ElementListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_elementList);
					setState(603);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(604); 
					match(Comma);
					setState(606);
					_la = _input.LA(1);
					if (_la==Comma) {
						{
						setState(605); 
						elision();
						}
					}

					setState(609);
					_la = _input.LA(1);
					if (_la==T__7) {
						{
						setState(608); 
						match(T__7);
						}
					}

					setState(611); 
					assignmentExpression(0);
					}
					} 
				}
				setState(616);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ElisionContext extends ParserRuleContext {
		public ElisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elision; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterElision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitElision(this);
		}
	}

	public final ElisionContext elision() throws RecognitionException {
		ElisionContext _localctx = new ElisionContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_elision);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(618); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(617); 
				match(Comma);
				}
				}
				setState(620); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Comma );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectLiteralContext extends ParserRuleContext {
		public PropertyNameAndValueListContext propertyNameAndValueList() {
			return getRuleContext(PropertyNameAndValueListContext.class,0);
		}
		public ObjectLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterObjectLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitObjectLiteral(this);
		}
	}

	public final ObjectLiteralContext objectLiteral() throws RecognitionException {
		ObjectLiteralContext _localctx = new ObjectLiteralContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_objectLiteral);
		try {
			setState(633);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(622); 
				match(OpenBrace);
				setState(623); 
				match(CloseBrace);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(624); 
				match(OpenBrace);
				setState(625); 
				propertyNameAndValueList(0);
				setState(626); 
				match(CloseBrace);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(628); 
				match(OpenBrace);
				setState(629); 
				propertyNameAndValueList(0);
				setState(630); 
				match(Comma);
				setState(631); 
				match(CloseBrace);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyNameAndValueListContext extends ParserRuleContext {
		public PropertyAssignmentContext propertyAssignment() {
			return getRuleContext(PropertyAssignmentContext.class,0);
		}
		public PropertyNameAndValueListContext propertyNameAndValueList() {
			return getRuleContext(PropertyNameAndValueListContext.class,0);
		}
		public PropertyNameAndValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyNameAndValueList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterPropertyNameAndValueList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitPropertyNameAndValueList(this);
		}
	}

	public final PropertyNameAndValueListContext propertyNameAndValueList() throws RecognitionException {
		return propertyNameAndValueList(0);
	}

	private PropertyNameAndValueListContext propertyNameAndValueList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PropertyNameAndValueListContext _localctx = new PropertyNameAndValueListContext(_ctx, _parentState);
		PropertyNameAndValueListContext _prevctx = _localctx;
		int _startState = 106;
		enterRecursionRule(_localctx, 106, RULE_propertyNameAndValueList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(636); 
			propertyAssignment();
			}
			_ctx.stop = _input.LT(-1);
			setState(643);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PropertyNameAndValueListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_propertyNameAndValueList);
					setState(638);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(639); 
					match(Comma);
					setState(640); 
					propertyAssignment();
					}
					} 
				}
				setState(645);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PropertyAssignmentContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public InitialiserContext initialiser() {
			return getRuleContext(InitialiserContext.class,0);
		}
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public MethodDefinitionContext methodDefinition() {
			return getRuleContext(MethodDefinitionContext.class,0);
		}
		public PropertyAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterPropertyAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitPropertyAssignment(this);
		}
	}

	public final PropertyAssignmentContext propertyAssignment() throws RecognitionException {
		PropertyAssignmentContext _localctx = new PropertyAssignmentContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_propertyAssignment);
		try {
			setState(655);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(646); 
				identifierReference();
				setState(648);
				switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					{
					setState(647); 
					initialiser();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(650); 
				propertyName();
				setState(651); 
				match(Colon);
				setState(652); 
				assignmentExpression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(654); 
				methodDefinition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDefinitionContext extends ParserRuleContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public List<StatementListItemContext> statementListItem() {
			return getRuleContexts(StatementListItemContext.class);
		}
		public StatementListItemContext statementListItem(int i) {
			return getRuleContext(StatementListItemContext.class,i);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public MethodDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterMethodDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitMethodDefinition(this);
		}
	}

	public final MethodDefinitionContext methodDefinition() throws RecognitionException {
		MethodDefinitionContext _localctx = new MethodDefinitionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_methodDefinition);
		try {
			int _alt;
			setState(700);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(657); 
				match(T__28);
				setState(658); 
				propertyName();
				setState(659); 
				match(OpenParen);
				setState(660); 
				match(CloseParen);
				setState(661); 
				match(OpenBrace);
				setState(665);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(662); 
						statementListItem();
						}
						} 
					}
					setState(667);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				}
				setState(668); 
				match(CloseBrace);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(670); 
				match(T__29);
				setState(671); 
				propertyName();
				setState(672); 
				match(OpenParen);
				setState(673); 
				variableDeclaration();
				setState(674); 
				match(CloseParen);
				setState(675); 
				match(OpenBrace);
				setState(679);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(676); 
						statementListItem();
						}
						} 
					}
					setState(681);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
				}
				setState(682); 
				match(CloseBrace);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(684);
				if (!(!here(Function))) throw new FailedPredicateException(this, "!here(Function)");
				setState(685); 
				propertyName();
				setState(686); 
				match(OpenParen);
				setState(688);
				switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					{
					setState(687); 
					formalParameterList();
					}
					break;
				}
				setState(690); 
				match(CloseParen);
				setState(691); 
				match(OpenBrace);
				setState(695);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(692); 
						statementListItem();
						}
						} 
					}
					setState(697);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				}
				setState(698); 
				match(CloseBrace);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyNameContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public PropertyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterPropertyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitPropertyName(this);
		}
	}

	public final PropertyNameContext propertyName() throws RecognitionException {
		PropertyNameContext _localctx = new PropertyNameContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_propertyName);
		try {
			setState(709);
			switch (_input.LA(1)) {
			case T__0:
			case T__2:
			case T__3:
			case T__5:
			case T__6:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(702); 
				identifierName();
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(703); 
				stringLiteral();
				}
				break;
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case BinaryLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(704); 
				numericLiteral();
				}
				break;
			case OpenBracket:
				enterOuterAlt(_localctx, 4);
				{
				setState(705); 
				match(OpenBracket);
				setState(706); 
				assignmentExpression(0);
				setState(707); 
				match(CloseBracket);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(711); 
			match(OpenParen);
			setState(713);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Minus - 64)) | (1L << (BitNot - 64)) | (1L << (Not - 64)) | (1L << (DecimalLiteral - 64)) | (1L << (HexIntegerLiteral - 64)) | (1L << (OctalIntegerLiteral - 64)) | (1L << (BinaryLiteral - 64)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)) | (1L << (StringLiteral - 132)))) != 0)) {
				{
				setState(712); 
				argumentList(0);
				}
			}

			setState(715); 
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentListContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ElisionContext elision() {
			return getRuleContext(ElisionContext.class,0);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitArgumentList(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		return argumentList(0);
	}

	private ArgumentListContext argumentList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, _parentState);
		ArgumentListContext _prevctx = _localctx;
		int _startState = 116;
		enterRecursionRule(_localctx, 116, RULE_argumentList, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(719);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(718); 
				match(T__7);
				}
			}

			setState(721); 
			assignmentExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(732);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArgumentListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_argumentList);
					setState(723);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(724); 
					elision();
					setState(726);
					_la = _input.LA(1);
					if (_la==T__7) {
						{
						setState(725); 
						match(T__7);
						}
					}

					setState(728); 
					assignmentExpression(0);
					}
					} 
				}
				setState(734);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArrowFunctionContext extends ParserRuleContext {
		public ArrowParametersContext arrowParameters() {
			return getRuleContext(ArrowParametersContext.class,0);
		}
		public ConciseBodyContext conciseBody() {
			return getRuleContext(ConciseBodyContext.class,0);
		}
		public ArrowFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterArrowFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitArrowFunction(this);
		}
	}

	public final ArrowFunctionContext arrowFunction() throws RecognitionException {
		ArrowFunctionContext _localctx = new ArrowFunctionContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_arrowFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(735); 
			arrowParameters();
			setState(736);
			if (!(!here(LineTerminator))) throw new FailedPredicateException(this, "!here(LineTerminator)");
			setState(737); 
			match(T__30);
			setState(738); 
			conciseBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowParametersContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RestElementContext restElement() {
			return getRuleContext(RestElementContext.class,0);
		}
		public ArrowParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterArrowParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitArrowParameters(this);
		}
	}

	public final ArrowParametersContext arrowParameters() throws RecognitionException {
		ArrowParametersContext _localctx = new ArrowParametersContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_arrowParameters);
		int _la;
		try {
			setState(749);
			switch (_input.LA(1)) {
			case T__0:
			case T__2:
			case T__3:
			case T__5:
			case T__6:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(740); 
				identifierReference();
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 2);
				{
				setState(741); 
				match(OpenParen);
				setState(743);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Minus - 64)) | (1L << (BitNot - 64)) | (1L << (Not - 64)) | (1L << (DecimalLiteral - 64)) | (1L << (HexIntegerLiteral - 64)) | (1L << (OctalIntegerLiteral - 64)) | (1L << (BinaryLiteral - 64)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)) | (1L << (StringLiteral - 132)))) != 0)) {
					{
					setState(742); 
					expression(0);
					}
				}

				setState(746);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(745); 
					restElement();
					}
				}

				setState(748); 
				match(CloseParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConciseBodyContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public List<StatementListItemContext> statementListItem() {
			return getRuleContexts(StatementListItemContext.class);
		}
		public StatementListItemContext statementListItem(int i) {
			return getRuleContext(StatementListItemContext.class,i);
		}
		public ConciseBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conciseBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterConciseBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitConciseBody(this);
		}
	}

	public final ConciseBodyContext conciseBody() throws RecognitionException {
		ConciseBodyContext _localctx = new ConciseBodyContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_conciseBody);
		try {
			int _alt;
			setState(761);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(751);
				if (!(!here(OpenBrace))) throw new FailedPredicateException(this, "!here(OpenBrace)");
				setState(752); 
				assignmentExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(753); 
				match(OpenBrace);
				setState(757);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(754); 
						statementListItem();
						}
						} 
					}
					setState(759);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
				}
				setState(760); 
				match(CloseBrace);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 124;
		enterRecursionRule(_localctx, 124, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(764); 
			assignmentExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(771);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(766);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(767); 
					match(Comma);
					setState(768); 
					assignmentExpression(0);
					}
					} 
				}
				setState(773);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public YieldExpressionContext yieldExpression() {
			return getRuleContext(YieldExpressionContext.class,0);
		}
		public ArrowFunctionContext arrowFunction() {
			return getRuleContext(ArrowFunctionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierBindingContext identifierBinding() {
			return getRuleContext(IdentifierBindingContext.class,0);
		}
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitAssignmentExpression(this);
		}
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		return assignmentExpression(0);
	}

	private AssignmentExpressionContext assignmentExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, _parentState);
		AssignmentExpressionContext _prevctx = _localctx;
		int _startState = 126;
		enterRecursionRule(_localctx, 126, RULE_assignmentExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(786);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(775); 
				conditionalExpression(0);
				setState(776); 
				match(Assign);
				setState(777); 
				assignmentExpression(5);
				}
				break;
			case 2:
				{
				setState(779); 
				conditionalExpression(0);
				setState(780); 
				assignmentOperator();
				setState(781); 
				assignmentExpression(4);
				}
				break;
			case 3:
				{
				setState(783); 
				conditionalExpression(0);
				}
				break;
			case 4:
				{
				setState(784); 
				yieldExpression();
				}
				break;
			case 5:
				{
				setState(785); 
				arrowFunction();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(811);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(809);
					switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
					case 1:
						{
						_localctx = new AssignmentExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_assignmentExpression);
						setState(788);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(789); 
						match(T__12);
						setState(791);
						_la = _input.LA(1);
						if (_la==T__13) {
							{
							setState(790); 
							match(T__13);
							}
						}

						setState(793); 
						match(OpenParen);
						setState(797);
						switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
						case 1:
							{
							setState(794);
							_la = _input.LA(1);
							if ( !(_la==T__5 || _la==T__6 || _la==Const) ) {
							_errHandler.recoverInline(this);
							}
							consume();
							setState(795); 
							identifierBinding();
							}
							break;
						case 2:
							{
							setState(796); 
							conditionalExpression(0);
							}
							break;
						}
						setState(799);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==T__15) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(800); 
						expression(0);
						setState(801); 
						match(CloseParen);
						}
						break;
					case 2:
						{
						_localctx = new AssignmentExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_assignmentExpression);
						setState(803);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(804); 
						match(T__8);
						setState(805); 
						match(OpenParen);
						setState(806); 
						expression(0);
						setState(807); 
						match(CloseParen);
						}
						break;
					}
					} 
				}
				setState(813);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConditionalExpressionContext extends ParserRuleContext {
		public List<ConditionalExpressionContext> conditionalExpression() {
			return getRuleContexts(ConditionalExpressionContext.class);
		}
		public ConditionalExpressionContext conditionalExpression(int i) {
			return getRuleContext(ConditionalExpressionContext.class,i);
		}
		public LeftHandSideExpressionContext leftHandSideExpression() {
			return getRuleContext(LeftHandSideExpressionContext.class,0);
		}
		public List<AssignmentExpressionContext> assignmentExpression() {
			return getRuleContexts(AssignmentExpressionContext.class);
		}
		public AssignmentExpressionContext assignmentExpression(int i) {
			return getRuleContext(AssignmentExpressionContext.class,i);
		}
		public ConditionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitConditionalExpression(this);
		}
	}

	public final ConditionalExpressionContext conditionalExpression() throws RecognitionException {
		return conditionalExpression(0);
	}

	private ConditionalExpressionContext conditionalExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionalExpressionContext _localctx = new ConditionalExpressionContext(_ctx, _parentState);
		ConditionalExpressionContext _prevctx = _localctx;
		int _startState = 128;
		enterRecursionRule(_localctx, 128, RULE_conditionalExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(834);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				{
				setState(815); 
				match(T__32);
				setState(816); 
				conditionalExpression(12);
				}
				break;
			case 2:
				{
				setState(817); 
				match(T__33);
				setState(818); 
				conditionalExpression(11);
				}
				break;
			case 3:
				{
				setState(819); 
				match(T__34);
				setState(820); 
				conditionalExpression(10);
				}
				break;
			case 4:
				{
				setState(821); 
				match(PlusPlus);
				setState(822); 
				conditionalExpression(9);
				}
				break;
			case 5:
				{
				setState(823); 
				match(MinusMinus);
				setState(824); 
				conditionalExpression(8);
				}
				break;
			case 6:
				{
				setState(825); 
				match(Plus);
				setState(826); 
				conditionalExpression(7);
				}
				break;
			case 7:
				{
				setState(827); 
				match(Minus);
				setState(828); 
				conditionalExpression(6);
				}
				break;
			case 8:
				{
				setState(829); 
				match(BitNot);
				setState(830); 
				conditionalExpression(5);
				}
				break;
			case 9:
				{
				setState(831); 
				match(Not);
				setState(832); 
				conditionalExpression(4);
				}
				break;
			case 10:
				{
				setState(833); 
				leftHandSideExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(919);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(917);
					switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(836);
						if (!(precpred(_ctx, 35))) throw new FailedPredicateException(this, "precpred(_ctx, 35)");
						setState(837); 
						match(Or);
						setState(838); 
						conditionalExpression(36);
						}
						break;
					case 2:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(839);
						if (!(precpred(_ctx, 34))) throw new FailedPredicateException(this, "precpred(_ctx, 34)");
						setState(840); 
						match(And);
						setState(841); 
						conditionalExpression(35);
						}
						break;
					case 3:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(842);
						if (!(precpred(_ctx, 33))) throw new FailedPredicateException(this, "precpred(_ctx, 33)");
						setState(843); 
						match(BitOr);
						setState(844); 
						conditionalExpression(34);
						}
						break;
					case 4:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(845);
						if (!(precpred(_ctx, 32))) throw new FailedPredicateException(this, "precpred(_ctx, 32)");
						setState(846); 
						match(BitXOr);
						setState(847); 
						conditionalExpression(33);
						}
						break;
					case 5:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(848);
						if (!(precpred(_ctx, 31))) throw new FailedPredicateException(this, "precpred(_ctx, 31)");
						setState(849); 
						match(BitAnd);
						setState(850); 
						conditionalExpression(32);
						}
						break;
					case 6:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(851);
						if (!(precpred(_ctx, 30))) throw new FailedPredicateException(this, "precpred(_ctx, 30)");
						setState(852); 
						match(Equals);
						setState(853); 
						conditionalExpression(31);
						}
						break;
					case 7:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(854);
						if (!(precpred(_ctx, 29))) throw new FailedPredicateException(this, "precpred(_ctx, 29)");
						setState(855); 
						match(NotEquals);
						setState(856); 
						conditionalExpression(30);
						}
						break;
					case 8:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(857);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(858); 
						match(IdentityEquals);
						setState(859); 
						conditionalExpression(29);
						}
						break;
					case 9:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(860);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(861); 
						match(IdentityNotEquals);
						setState(862); 
						conditionalExpression(28);
						}
						break;
					case 10:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(863);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(864); 
						match(LessThan);
						setState(865); 
						conditionalExpression(27);
						}
						break;
					case 11:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(866);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(867); 
						match(MoreThan);
						setState(868); 
						conditionalExpression(26);
						}
						break;
					case 12:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(869);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(870); 
						match(LessThanEquals);
						setState(871); 
						conditionalExpression(25);
						}
						break;
					case 13:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(872);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(873); 
						match(GreaterThanEquals);
						setState(874); 
						conditionalExpression(24);
						}
						break;
					case 14:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(875);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(876); 
						match(T__31);
						setState(877); 
						conditionalExpression(23);
						}
						break;
					case 15:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(878);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(879); 
						match(T__14);
						setState(880); 
						conditionalExpression(22);
						}
						break;
					case 16:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(881);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(882); 
						match(LeftShiftArithmetic);
						setState(883); 
						conditionalExpression(21);
						}
						break;
					case 17:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(884);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(885); 
						match(RightShiftArithmetic);
						setState(886); 
						conditionalExpression(20);
						}
						break;
					case 18:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(887);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(888); 
						match(RightShiftLogical);
						setState(889); 
						conditionalExpression(19);
						}
						break;
					case 19:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(890);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(891); 
						match(Plus);
						setState(892); 
						conditionalExpression(18);
						}
						break;
					case 20:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(893);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(894); 
						match(Minus);
						setState(895); 
						conditionalExpression(17);
						}
						break;
					case 21:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(896);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(897); 
						match(Multiply);
						setState(898); 
						conditionalExpression(16);
						}
						break;
					case 22:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(899);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(900); 
						match(Divide);
						setState(901); 
						conditionalExpression(15);
						}
						break;
					case 23:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(902);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(903); 
						match(Modulus);
						setState(904); 
						conditionalExpression(14);
						}
						break;
					case 24:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(905);
						if (!(precpred(_ctx, 36))) throw new FailedPredicateException(this, "precpred(_ctx, 36)");
						setState(906); 
						match(QuestionMark);
						setState(907); 
						assignmentExpression(0);
						setState(908); 
						match(Colon);
						setState(909); 
						assignmentExpression(0);
						}
						break;
					case 25:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(911);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(912);
						if (!(!here(LineTerminator))) throw new FailedPredicateException(this, "!here(LineTerminator)");
						setState(913); 
						match(PlusPlus);
						}
						break;
					case 26:
						{
						_localctx = new ConditionalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_conditionalExpression);
						setState(914);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(915);
						if (!(!here(LineTerminator))) throw new FailedPredicateException(this, "!here(LineTerminator)");
						setState(916); 
						match(MinusMinus);
						}
						break;
					}
					} 
				}
				setState(921);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LeftHandSideExpressionContext extends ParserRuleContext {
		public CallExpressionContext callExpression() {
			return getRuleContext(CallExpressionContext.class,0);
		}
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public LeftHandSideExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftHandSideExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterLeftHandSideExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitLeftHandSideExpression(this);
		}
	}

	public final LeftHandSideExpressionContext leftHandSideExpression() throws RecognitionException {
		LeftHandSideExpressionContext _localctx = new LeftHandSideExpressionContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_leftHandSideExpression);
		try {
			setState(924);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(922); 
				callExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(923); 
				newExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallExpressionContext extends ParserRuleContext {
		public MemberExpressionContext memberExpression() {
			return getRuleContext(MemberExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public CallExpressionContext callExpression() {
			return getRuleContext(CallExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public CallExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitCallExpression(this);
		}
	}

	public final CallExpressionContext callExpression() throws RecognitionException {
		return callExpression(0);
	}

	private CallExpressionContext callExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CallExpressionContext _localctx = new CallExpressionContext(_ctx, _parentState);
		CallExpressionContext _prevctx = _localctx;
		int _startState = 132;
		enterRecursionRule(_localctx, 132, RULE_callExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(927); 
			memberExpression(0);
			setState(928); 
			arguments();
			}
			_ctx.stop = _input.LT(-1);
			setState(942);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(940);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
					case 1:
						{
						_localctx = new CallExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_callExpression);
						setState(930);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(931); 
						arguments();
						}
						break;
					case 2:
						{
						_localctx = new CallExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_callExpression);
						setState(932);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(933); 
						match(OpenBracket);
						setState(934); 
						expression(0);
						setState(935); 
						match(CloseBracket);
						}
						break;
					case 3:
						{
						_localctx = new CallExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_callExpression);
						setState(937);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(938); 
						match(Dot);
						setState(939); 
						identifierName();
						}
						break;
					}
					} 
				}
				setState(944);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NewExpressionContext extends ParserRuleContext {
		public MemberExpressionContext memberExpression() {
			return getRuleContext(MemberExpressionContext.class,0);
		}
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public NewExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitNewExpression(this);
		}
	}

	public final NewExpressionContext newExpression() throws RecognitionException {
		NewExpressionContext _localctx = new NewExpressionContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_newExpression);
		try {
			setState(948);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(945); 
				memberExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(946); 
				match(T__35);
				setState(947); 
				newExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public MemberExpressionContext memberExpression() {
			return getRuleContext(MemberExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public MemberExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterMemberExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitMemberExpression(this);
		}
	}

	public final MemberExpressionContext memberExpression() throws RecognitionException {
		return memberExpression(0);
	}

	private MemberExpressionContext memberExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MemberExpressionContext _localctx = new MemberExpressionContext(_ctx, _parentState);
		MemberExpressionContext _prevctx = _localctx;
		int _startState = 136;
		enterRecursionRule(_localctx, 136, RULE_memberExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(956);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				{
				setState(951); 
				primaryExpression();
				}
				break;
			case 2:
				{
				setState(952); 
				match(T__35);
				setState(953); 
				memberExpression(0);
				setState(954); 
				arguments();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(968);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(966);
					switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
					case 1:
						{
						_localctx = new MemberExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_memberExpression);
						setState(958);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(959); 
						match(OpenBracket);
						setState(960); 
						expression(0);
						setState(961); 
						match(CloseBracket);
						}
						break;
					case 2:
						{
						_localctx = new MemberExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_memberExpression);
						setState(963);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(964); 
						match(Dot);
						setState(965); 
						identifierName();
						}
						break;
					}
					} 
				}
				setState(970);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FunctionExpressionContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public List<StatementListItemContext> statementListItem() {
			return getRuleContexts(StatementListItemContext.class);
		}
		public StatementListItemContext statementListItem(int i) {
			return getRuleContext(StatementListItemContext.class,i);
		}
		public FunctionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitFunctionExpression(this);
		}
	}

	public final FunctionExpressionContext functionExpression() throws RecognitionException {
		FunctionExpressionContext _localctx = new FunctionExpressionContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_functionExpression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(971);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(973);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)) | (1L << (Identifier - 132)))) != 0)) {
				{
				setState(972); 
				identifierName();
				}
			}

			setState(975); 
			match(OpenParen);
			setState(977);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				{
				setState(976); 
				formalParameterList();
				}
				break;
			}
			setState(979); 
			match(CloseParen);
			setState(989);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				{
				setState(980); 
				match(OpenBrace);
				setState(984);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(981); 
						statementListItem();
						}
						} 
					}
					setState(986);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
				}
				setState(987); 
				match(CloseBrace);
				}
				break;
			case 2:
				{
				setState(988); 
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public FunctionExpressionContext functionExpression() {
			return getRuleContext(FunctionExpressionContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitPrimaryExpression(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_primaryExpression);
		try {
			setState(1002);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(991); 
				match(T__36);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(992); 
				identifierName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(993); 
				functionExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(994); 
				classDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(995); 
				literal();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(996); 
				objectLiteral();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(997); 
				match(OpenParen);
				setState(998); 
				expression(0);
				setState(999); 
				match(CloseParen);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1001); 
				arrayLiteral();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentOperatorContext extends ParserRuleContext {
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitAssignmentOperator(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1004);
			_la = _input.LA(1);
			if ( !(((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & ((1L << (MultiplyAssign - 86)) | (1L << (DivideAssign - 86)) | (1L << (ModulusAssign - 86)) | (1L << (PlusAssign - 86)) | (1L << (MinusAssign - 86)) | (1L << (LeftShiftArithmeticAssign - 86)) | (1L << (RightShiftArithmeticAssign - 86)) | (1L << (RightShiftLogicalAssign - 86)) | (1L << (BitAndAssign - 86)) | (1L << (BitXorAssign - 86)) | (1L << (BitOrAssign - 86)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public TerminalNode RegularExpressionLiteral() { return getToken(ECMAScriptParser.RegularExpressionLiteral, 0); }
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_literal);
		try {
			setState(1012);
			switch (_input.LA(1)) {
			case T__37:
				enterOuterAlt(_localctx, 1);
				{
				setState(1006); 
				match(T__37);
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 2);
				{
				setState(1007); 
				match(T__38);
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 3);
				{
				setState(1008); 
				match(T__39);
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 4);
				{
				setState(1009); 
				stringLiteral();
				}
				break;
			case RegularExpressionLiteral:
				enterOuterAlt(_localctx, 5);
				{
				setState(1010); 
				match(RegularExpressionLiteral);
				}
				break;
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case BinaryLiteral:
				enterOuterAlt(_localctx, 6);
				{
				setState(1011); 
				numericLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(ECMAScriptParser.StringLiteral, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitStringLiteral(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1014); 
			match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralContext extends ParserRuleContext {
		public TerminalNode DecimalLiteral() { return getToken(ECMAScriptParser.DecimalLiteral, 0); }
		public TerminalNode HexIntegerLiteral() { return getToken(ECMAScriptParser.HexIntegerLiteral, 0); }
		public TerminalNode OctalIntegerLiteral() { return getToken(ECMAScriptParser.OctalIntegerLiteral, 0); }
		public TerminalNode BinaryLiteral() { return getToken(ECMAScriptParser.BinaryLiteral, 0); }
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitNumericLiteral(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1016);
			_la = _input.LA(1);
			if ( !(((((_la - 98)) & ~0x3f) == 0 && ((1L << (_la - 98)) & ((1L << (DecimalLiteral - 98)) | (1L << (HexIntegerLiteral - 98)) | (1L << (OctalIntegerLiteral - 98)) | (1L << (BinaryLiteral - 98)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierNameContext extends ParserRuleContext {
		public ReservedWordContext reservedWord() {
			return getRuleContext(ReservedWordContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(ECMAScriptParser.Identifier, 0); }
		public IdentifierNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterIdentifierName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitIdentifierName(this);
		}
	}

	public final IdentifierNameContext identifierName() throws RecognitionException {
		IdentifierNameContext _localctx = new IdentifierNameContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_identifierName);
		try {
			setState(1020);
			switch (_input.LA(1)) {
			case T__0:
			case T__2:
			case T__3:
			case T__5:
			case T__6:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
				enterOuterAlt(_localctx, 1);
				{
				setState(1018); 
				reservedWord();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1019); 
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReservedWordContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public FutureReservedWordContext futureReservedWord() {
			return getRuleContext(FutureReservedWordContext.class,0);
		}
		public ReservedWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reservedWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterReservedWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitReservedWord(this);
		}
	}

	public final ReservedWordContext reservedWord() throws RecognitionException {
		ReservedWordContext _localctx = new ReservedWordContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_reservedWord);
		try {
			setState(1024);
			switch (_input.LA(1)) {
			case T__0:
			case T__5:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
				enterOuterAlt(_localctx, 1);
				{
				setState(1022); 
				keyword();
				}
				break;
			case T__2:
			case T__3:
			case T__6:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
				enterOuterAlt(_localctx, 2);
				{
				setState(1023); 
				futureReservedWord();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1026);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FutureReservedWordContext extends ParserRuleContext {
		public FutureReservedWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_futureReservedWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterFutureReservedWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitFutureReservedWord(this);
		}
	}

	public final FutureReservedWordContext futureReservedWord() throws RecognitionException {
		FutureReservedWordContext _localctx = new FutureReservedWordContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_futureReservedWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1028);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__6) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (Class - 132)) | (1L << (Enum - 132)) | (1L << (Extends - 132)) | (1L << (Super - 132)) | (1L << (Const - 132)) | (1L << (Export - 132)) | (1L << (Import - 132)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EosContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(ECMAScriptParser.SemiColon, 0); }
		public TerminalNode EOF() { return getToken(ECMAScriptParser.EOF, 0); }
		public EosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterEos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitEos(this);
		}
	}

	public final EosContext eos() throws RecognitionException {
		EosContext _localctx = new EosContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_eos);
		try {
			setState(1034);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1030); 
				match(SemiColon);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1031); 
				match(EOF);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1032);
				if (!(lineTerminatorAhead())) throw new FailedPredicateException(this, "lineTerminatorAhead()");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1033);
				if (!(_input.LT(1).getType() == CloseBrace)) throw new FailedPredicateException(this, "_input.LT(1).getType() == CloseBrace");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EofContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ECMAScriptParser.EOF, 0); }
		public EofContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eof; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).enterEof(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ECMAScriptListener ) ((ECMAScriptListener)listener).exitEof(this);
		}
	}

	public final EofContext eof() throws RecognitionException {
		EofContext _localctx = new EofContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_eof);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1036); 
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11: 
			return statementList_sempred((StatementListContext)_localctx, predIndex);
		case 13: 
			return variableDeclarationList_sempred((VariableDeclarationListContext)_localctx, predIndex);
		case 23: 
			return bindingPropertyList_sempred((BindingPropertyListContext)_localctx, predIndex);
		case 27: 
			return expressionStatement_sempred((ExpressionStatementContext)_localctx, predIndex);
		case 31: 
			return continueStatement_sempred((ContinueStatementContext)_localctx, predIndex);
		case 32: 
			return breakStatement_sempred((BreakStatementContext)_localctx, predIndex);
		case 33: 
			return returnStatement_sempred((ReturnStatementContext)_localctx, predIndex);
		case 41: 
			return throwStatement_sempred((ThrowStatementContext)_localctx, predIndex);
		case 50: 
			return elementList_sempred((ElementListContext)_localctx, predIndex);
		case 53: 
			return propertyNameAndValueList_sempred((PropertyNameAndValueListContext)_localctx, predIndex);
		case 55: 
			return methodDefinition_sempred((MethodDefinitionContext)_localctx, predIndex);
		case 58: 
			return argumentList_sempred((ArgumentListContext)_localctx, predIndex);
		case 59: 
			return arrowFunction_sempred((ArrowFunctionContext)_localctx, predIndex);
		case 61: 
			return conciseBody_sempred((ConciseBodyContext)_localctx, predIndex);
		case 62: 
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 63: 
			return assignmentExpression_sempred((AssignmentExpressionContext)_localctx, predIndex);
		case 64: 
			return conditionalExpression_sempred((ConditionalExpressionContext)_localctx, predIndex);
		case 66: 
			return callExpression_sempred((CallExpressionContext)_localctx, predIndex);
		case 68: 
			return memberExpression_sempred((MemberExpressionContext)_localctx, predIndex);
		case 79: 
			return eos_sempred((EosContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean statementList_sempred(StatementListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean variableDeclarationList_sempred(VariableDeclarationListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean bindingPropertyList_sempred(BindingPropertyListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expressionStatement_sempred(ExpressionStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: 
			return !here(OpenBrace);
		case 4: 
			return !here(Function);
		}
		return true;
	}
	private boolean continueStatement_sempred(ContinueStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: 
			return !here(LineTerminator);
		}
		return true;
	}
	private boolean breakStatement_sempred(BreakStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6: 
			return !here(LineTerminator);
		}
		return true;
	}
	private boolean returnStatement_sempred(ReturnStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7: 
			return !here(LineTerminator);
		}
		return true;
	}
	private boolean throwStatement_sempred(ThrowStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8: 
			return !here(LineTerminator);
		}
		return true;
	}
	private boolean elementList_sempred(ElementListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean propertyNameAndValueList_sempred(PropertyNameAndValueListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean methodDefinition_sempred(MethodDefinitionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11: 
			return !here(Function);
		}
		return true;
	}
	private boolean argumentList_sempred(ArgumentListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 12: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean arrowFunction_sempred(ArrowFunctionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 13: 
			return !here(LineTerminator);
		}
		return true;
	}
	private boolean conciseBody_sempred(ConciseBodyContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14: 
			return !here(OpenBrace);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 15: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean assignmentExpression_sempred(AssignmentExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 16: 
			return precpred(_ctx, 2);
		case 17: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean conditionalExpression_sempred(ConditionalExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 18: 
			return precpred(_ctx, 35);
		case 19: 
			return precpred(_ctx, 34);
		case 20: 
			return precpred(_ctx, 33);
		case 21: 
			return precpred(_ctx, 32);
		case 22: 
			return precpred(_ctx, 31);
		case 23: 
			return precpred(_ctx, 30);
		case 24: 
			return precpred(_ctx, 29);
		case 25: 
			return precpred(_ctx, 28);
		case 26: 
			return precpred(_ctx, 27);
		case 27: 
			return precpred(_ctx, 26);
		case 28: 
			return precpred(_ctx, 25);
		case 29: 
			return precpred(_ctx, 24);
		case 30: 
			return precpred(_ctx, 23);
		case 31: 
			return precpred(_ctx, 22);
		case 32: 
			return precpred(_ctx, 21);
		case 33: 
			return precpred(_ctx, 20);
		case 34: 
			return precpred(_ctx, 19);
		case 35: 
			return precpred(_ctx, 18);
		case 36: 
			return precpred(_ctx, 17);
		case 37: 
			return precpred(_ctx, 16);
		case 38: 
			return precpred(_ctx, 15);
		case 39: 
			return precpred(_ctx, 14);
		case 40: 
			return precpred(_ctx, 13);
		case 41: 
			return precpred(_ctx, 36);
		case 42: 
			return precpred(_ctx, 3);
		case 43: 
			return !here(LineTerminator);
		case 44: 
			return precpred(_ctx, 2);
		case 45: 
			return !here(LineTerminator);
		}
		return true;
	}
	private boolean callExpression_sempred(CallExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 46: 
			return precpred(_ctx, 3);
		case 47: 
			return precpred(_ctx, 2);
		case 48: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean memberExpression_sempred(MemberExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 49: 
			return precpred(_ctx, 3);
		case 50: 
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean eos_sempred(EosContext _localctx, int predIndex) {
		switch (predIndex) {
		case 51: 
			return lineTerminatorAhead();
		case 52: 
			return _input.LT(1).getType() == CloseBrace;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\u009b\u0411\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\7\2\u00a6"+
		"\n\2\f\2\16\2\u00a9\13\2\3\2\3\2\3\3\3\3\3\3\5\3\u00b0\n\3\3\4\3\4\5\4"+
		"\u00b4\n\4\3\4\3\4\5\4\u00b8\n\4\3\4\3\4\3\4\7\4\u00bd\n\4\f\4\16\4\u00c0"+
		"\13\4\3\4\3\4\5\4\u00c4\n\4\3\5\3\5\5\5\u00c8\n\5\3\5\3\5\3\6\5\6\u00cd"+
		"\n\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\7\b\u00d7\n\b\f\b\16\b\u00da\13\b"+
		"\3\t\5\t\u00dd\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00e7\n\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00f5\n\n\3\13\3\13\5\13"+
		"\u00f9\n\13\3\f\3\f\5\f\u00fd\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\7\r\u0106"+
		"\n\r\f\r\16\r\u0109\13\r\3\16\3\16\3\16\5\16\u010e\n\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\7\17\u0116\n\17\f\17\16\17\u0119\13\17\3\20\3\20\5\20"+
		"\u011d\n\20\3\21\3\21\5\21\u0121\n\21\3\22\3\22\5\22\u0125\n\22\3\23\3"+
		"\23\3\23\5\23\u012a\n\23\3\24\3\24\5\24\u012e\n\24\3\24\5\24\u0131\n\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u013c\n\24\3\24\5\24"+
		"\u013f\n\24\3\24\3\24\5\24\u0143\n\24\3\25\3\25\3\25\3\26\5\26\u0149\n"+
		"\26\3\26\6\26\u014c\n\26\r\26\16\26\u014d\3\27\3\27\5\27\u0152\n\27\3"+
		"\27\3\27\3\30\3\30\5\30\u0158\n\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\7\31\u0162\n\31\f\31\16\31\u0165\13\31\3\32\3\32\3\32\3\32\3\32"+
		"\5\32\u016c\n\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\5\35\u0177"+
		"\n\35\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u017f\n\36\3\37\3\37\3\37\3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u0195\n \3 \3 \5 "+
		"\u0199\n \3 \3 \5 \u019d\n \3 \3 \3 \3 \3 \3 \3 \3 \5 \u01a7\n \3 \3 "+
		"\5 \u01ab\n \3 \3 \3 \3 \3 \5 \u01b2\n \3 \3 \3 \3 \5 \u01b8\n \3 \3 "+
		"\3 \3 \3 \5 \u01bf\n \3!\3!\3!\5!\u01c4\n!\3\"\3\"\3\"\5\"\u01c9\n\"\3"+
		"#\3#\3#\5#\u01ce\n#\3#\5#\u01d1\n#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3"+
		"%\3&\3&\5&\u01e1\n&\3&\3&\5&\u01e5\n&\5&\u01e7\n&\3&\3&\3\'\6\'\u01ec"+
		"\n\'\r\'\16\'\u01ed\3(\3(\3(\3(\5(\u01f4\n(\3)\3)\3)\5)\u01f9\n)\3*\3"+
		"*\3*\3*\5*\u01ff\n*\3+\3+\3+\3+\3,\3,\3,\7,\u0208\n,\f,\16,\u020b\13,"+
		"\3,\3,\3,\3,\3,\3,\3,\7,\u0214\n,\f,\16,\u0217\13,\3,\3,\5,\u021b\n,\3"+
		"-\3-\3-\3-\3-\5-\u0222\n-\3-\3-\3-\3.\3.\3.\3/\3/\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\5\60\u0232\n\60\3\61\3\61\3\61\7\61\u0237\n\61\f\61\16\61\u023a"+
		"\13\61\3\62\3\62\3\62\3\62\5\62\u0240\n\62\3\63\3\63\5\63\u0244\n\63\3"+
		"\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\5\63\u024f\n\63\3\63\3\63"+
		"\5\63\u0253\n\63\3\64\3\64\5\64\u0257\n\64\3\64\5\64\u025a\n\64\3\64\3"+
		"\64\3\64\3\64\3\64\5\64\u0261\n\64\3\64\5\64\u0264\n\64\3\64\7\64\u0267"+
		"\n\64\f\64\16\64\u026a\13\64\3\65\6\65\u026d\n\65\r\65\16\65\u026e\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u027c\n\66\3\67"+
		"\3\67\3\67\3\67\3\67\3\67\7\67\u0284\n\67\f\67\16\67\u0287\13\67\38\3"+
		"8\58\u028b\n8\38\38\38\38\38\58\u0292\n8\39\39\39\39\39\39\79\u029a\n"+
		"9\f9\169\u029d\139\39\39\39\39\39\39\39\39\39\79\u02a8\n9\f9\169\u02ab"+
		"\139\39\39\39\39\39\39\59\u02b3\n9\39\39\39\79\u02b8\n9\f9\169\u02bb\13"+
		"9\39\39\59\u02bf\n9\3:\3:\3:\3:\3:\3:\3:\5:\u02c8\n:\3;\3;\5;\u02cc\n"+
		";\3;\3;\3<\3<\5<\u02d2\n<\3<\3<\3<\3<\3<\5<\u02d9\n<\3<\3<\7<\u02dd\n"+
		"<\f<\16<\u02e0\13<\3=\3=\3=\3=\3=\3>\3>\3>\5>\u02ea\n>\3>\5>\u02ed\n>"+
		"\3>\5>\u02f0\n>\3?\3?\3?\3?\7?\u02f6\n?\f?\16?\u02f9\13?\3?\5?\u02fc\n"+
		"?\3@\3@\3@\3@\3@\3@\7@\u0304\n@\f@\16@\u0307\13@\3A\3A\3A\3A\3A\3A\3A"+
		"\3A\3A\3A\3A\3A\5A\u0315\nA\3A\3A\3A\5A\u031a\nA\3A\3A\3A\3A\5A\u0320"+
		"\nA\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\7A\u032c\nA\fA\16A\u032f\13A\3B\3B\3"+
		"B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\5B\u0345\nB\3B\3"+
		"B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3"+
		"B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3"+
		"B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3"+
		"B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\7B\u0398\nB\fB\16B\u039b\13B\3C\3C\5C"+
		"\u039f\nC\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\7D\u03af\nD\fD\16"+
		"D\u03b2\13D\3E\3E\3E\5E\u03b7\nE\3F\3F\3F\3F\3F\3F\5F\u03bf\nF\3F\3F\3"+
		"F\3F\3F\3F\3F\3F\7F\u03c9\nF\fF\16F\u03cc\13F\3G\3G\5G\u03d0\nG\3G\3G"+
		"\5G\u03d4\nG\3G\3G\3G\7G\u03d9\nG\fG\16G\u03dc\13G\3G\3G\5G\u03e0\nG\3"+
		"H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\5H\u03ed\nH\3I\3I\3J\3J\3J\3J\3J\3J\5"+
		"J\u03f7\nJ\3K\3K\3L\3L\3M\3M\5M\u03ff\nM\3N\3N\5N\u0403\nN\3O\3O\3P\3"+
		"P\3Q\3Q\3Q\3Q\5Q\u040d\nQ\3R\3R\3R\2\r\30\34\60flv~\u0080\u0082\u0086"+
		"\u008aS\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:"+
		"<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a"+
		"\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2"+
		"\2\n\3\2\3\4\3\2\6\7\4\2\b\t\u008a\u008a\3\2\21\22\3\2Xb\3\2dg\6\2\3\3"+
		"\b\b\13 \"\'\6\2\5\6\t\t+\60\u0086\u008c\u047f\2\u00a7\3\2\2\2\4\u00af"+
		"\3\2\2\2\6\u00b1\3\2\2\2\b\u00c5\3\2\2\2\n\u00cc\3\2\2\2\f\u00d2\3\2\2"+
		"\2\16\u00d8\3\2\2\2\20\u00dc\3\2\2\2\22\u00f4\3\2\2\2\24\u00f6\3\2\2\2"+
		"\26\u00fa\3\2\2\2\30\u0100\3\2\2\2\32\u010a\3\2\2\2\34\u010f\3\2\2\2\36"+
		"\u011a\3\2\2\2 \u0120\3\2\2\2\"\u0124\3\2\2\2$\u0129\3\2\2\2&\u0142\3"+
		"\2\2\2(\u0144\3\2\2\2*\u014b\3\2\2\2,\u014f\3\2\2\2.\u0155\3\2\2\2\60"+
		"\u015b\3\2\2\2\62\u016b\3\2\2\2\64\u016d\3\2\2\2\66\u0170\3\2\2\28\u0176"+
		"\3\2\2\2:\u0178\3\2\2\2<\u0180\3\2\2\2>\u01be\3\2\2\2@\u01c0\3\2\2\2B"+
		"\u01c5\3\2\2\2D\u01ca\3\2\2\2F\u01d2\3\2\2\2H\u01d8\3\2\2\2J\u01de\3\2"+
		"\2\2L\u01eb\3\2\2\2N\u01ef\3\2\2\2P\u01f5\3\2\2\2R\u01fa\3\2\2\2T\u0200"+
		"\3\2\2\2V\u021a\3\2\2\2X\u021c\3\2\2\2Z\u0226\3\2\2\2\\\u0229\3\2\2\2"+
		"^\u0231\3\2\2\2`\u0233\3\2\2\2b\u023f\3\2\2\2d\u0252\3\2\2\2f\u0254\3"+
		"\2\2\2h\u026c\3\2\2\2j\u027b\3\2\2\2l\u027d\3\2\2\2n\u0291\3\2\2\2p\u02be"+
		"\3\2\2\2r\u02c7\3\2\2\2t\u02c9\3\2\2\2v\u02cf\3\2\2\2x\u02e1\3\2\2\2z"+
		"\u02ef\3\2\2\2|\u02fb\3\2\2\2~\u02fd\3\2\2\2\u0080\u0314\3\2\2\2\u0082"+
		"\u0344\3\2\2\2\u0084\u039e\3\2\2\2\u0086\u03a0\3\2\2\2\u0088\u03b6\3\2"+
		"\2\2\u008a\u03be\3\2\2\2\u008c\u03cd\3\2\2\2\u008e\u03ec\3\2\2\2\u0090"+
		"\u03ee\3\2\2\2\u0092\u03f6\3\2\2\2\u0094\u03f8\3\2\2\2\u0096\u03fa\3\2"+
		"\2\2\u0098\u03fe\3\2\2\2\u009a\u0402\3\2\2\2\u009c\u0404\3\2\2\2\u009e"+
		"\u0406\3\2\2\2\u00a0\u040c\3\2\2\2\u00a2\u040e\3\2\2\2\u00a4\u00a6\5\4"+
		"\3\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ab\7\2"+
		"\2\3\u00ab\3\3\2\2\2\u00ac\u00b0\5\6\4\2\u00ad\u00b0\5\b\5\2\u00ae\u00b0"+
		"\5\22\n\2\u00af\u00ac\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2"+
		"\u00b0\5\3\2\2\2\u00b1\u00b3\t\2\2\2\u00b2\u00b4\5\u0098M\2\u00b3\u00b2"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\7\65\2\2"+
		"\u00b6\u00b8\5^\60\2\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9"+
		"\3\2\2\2\u00b9\u00c3\7\66\2\2\u00ba\u00be\7\67\2\2\u00bb\u00bd\5\4\3\2"+
		"\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c4\78\2\2\u00c2"+
		"\u00c4\5\22\n\2\u00c3\u00ba\3\2\2\2\u00c3\u00c2\3\2\2\2\u00c4\7\3\2\2"+
		"\2\u00c5\u00c7\7\u0086\2\2\u00c6\u00c8\5\"\22\2\u00c7\u00c6\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\5\n\6\2\u00ca\t\3\2\2\2"+
		"\u00cb\u00cd\5\f\7\2\u00cc\u00cb\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce"+
		"\3\2\2\2\u00ce\u00cf\7\67\2\2\u00cf\u00d0\5\16\b\2\u00d0\u00d1\78\2\2"+
		"\u00d1\13\3\2\2\2\u00d2\u00d3\7\u0088\2\2\u00d3\u00d4\5\u0082B\2\u00d4"+
		"\r\3\2\2\2\u00d5\u00d7\5\20\t\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2\2"+
		"\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\17\3\2\2\2\u00da\u00d8"+
		"\3\2\2\2\u00db\u00dd\7\5\2\2\u00dc\u00db\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00df\5p9\2\u00df\21\3\2\2\2\u00e0\u00f5\5\26\f\2"+
		"\u00e1\u00f5\5\32\16\2\u00e2\u00f5\5\66\34\2\u00e3\u00f5\5:\36\2\u00e4"+
		"\u00e6\58\35\2\u00e5\u00e7\5\u00a0Q\2\u00e6\u00e5\3\2\2\2\u00e6\u00e7"+
		"\3\2\2\2\u00e7\u00f5\3\2\2\2\u00e8\u00f5\5> \2\u00e9\u00f5\5@!\2\u00ea"+
		"\u00f5\5B\"\2\u00eb\u00f5\5D#\2\u00ec\u00f5\5F$\2\u00ed\u00f5\5R*\2\u00ee"+
		"\u00f5\5H%\2\u00ef\u00f5\5T+\2\u00f0\u00f5\5V,\2\u00f1\u00f5\5\\/\2\u00f2"+
		"\u00f5\5\24\13\2\u00f3\u00f5\5<\37\2\u00f4\u00e0\3\2\2\2\u00f4\u00e1\3"+
		"\2\2\2\u00f4\u00e2\3\2\2\2\u00f4\u00e3\3\2\2\2\u00f4\u00e4\3\2\2\2\u00f4"+
		"\u00e8\3\2\2\2\u00f4\u00e9\3\2\2\2\u00f4\u00ea\3\2\2\2\u00f4\u00eb\3\2"+
		"\2\2\u00f4\u00ec\3\2\2\2\u00f4\u00ed\3\2\2\2\u00f4\u00ee\3\2\2\2\u00f4"+
		"\u00ef\3\2\2\2\u00f4\u00f0\3\2\2\2\u00f4\u00f1\3\2\2\2\u00f4\u00f2\3\2"+
		"\2\2\u00f4\u00f3\3\2\2\2\u00f5\23\3\2\2\2\u00f6\u00f8\t\3\2\2\u00f7\u00f9"+
		"\5\u0080A\2\u00f8\u00f7\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\25\3\2\2\2\u00fa"+
		"\u00fc\7\67\2\2\u00fb\u00fd\5\30\r\2\u00fc\u00fb\3\2\2\2\u00fc\u00fd\3"+
		"\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\78\2\2\u00ff\27\3\2\2\2\u0100\u0101"+
		"\b\r\1\2\u0101\u0102\5\22\n\2\u0102\u0107\3\2\2\2\u0103\u0104\f\3\2\2"+
		"\u0104\u0106\5\22\n\2\u0105\u0103\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105"+
		"\3\2\2\2\u0107\u0108\3\2\2\2\u0108\31\3\2\2\2\u0109\u0107\3\2\2\2\u010a"+
		"\u010b\t\4\2\2\u010b\u010d\5\34\17\2\u010c\u010e\5\u00a0Q\2\u010d\u010c"+
		"\3\2\2\2\u010d\u010e\3\2\2\2\u010e\33\3\2\2\2\u010f\u0110\b\17\1\2\u0110"+
		"\u0111\5\36\20\2\u0111\u0117\3\2\2\2\u0112\u0113\f\3\2\2\u0113\u0114\7"+
		":\2\2\u0114\u0116\5\36\20\2\u0115\u0112\3\2\2\2\u0116\u0119\3\2\2\2\u0117"+
		"\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\35\3\2\2\2\u0119\u0117\3\2\2"+
		"\2\u011a\u011c\5 \21\2\u011b\u011d\5\64\33\2\u011c\u011b\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011d\37\3\2\2\2\u011e\u0121\5\"\22\2\u011f\u0121\5$\23"+
		"\2\u0120\u011e\3\2\2\2\u0120\u011f\3\2\2\2\u0121!\3\2\2\2\u0122\u0125"+
		"\5\u0098M\2\u0123\u0125\7\6\2\2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2"+
		"\2\u0125#\3\2\2\2\u0126\u012a\5&\24\2\u0127\u012a\5,\27\2\u0128\u012a"+
		"\5.\30\2\u0129\u0126\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u0128\3\2\2\2\u012a"+
		"%\3\2\2\2\u012b\u012d\7\63\2\2\u012c\u012e\5h\65\2\u012d\u012c\3\2\2\2"+
		"\u012d\u012e\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u0131\5(\25\2\u0130\u012f"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0143\7\64\2\2"+
		"\u0133\u0134\7\63\2\2\u0134\u0135\5*\26\2\u0135\u0136\7\64\2\2\u0136\u0143"+
		"\3\2\2\2\u0137\u0138\7\63\2\2\u0138\u0139\5*\26\2\u0139\u013b\7:\2\2\u013a"+
		"\u013c\5h\65\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2"+
		"\2\2\u013d\u013f\5(\25\2\u013e\u013d\3\2\2\2\u013e\u013f\3\2\2\2\u013f"+
		"\u0140\3\2\2\2\u0140\u0141\7\64\2\2\u0141\u0143\3\2\2\2\u0142\u012b\3"+
		"\2\2\2\u0142\u0133\3\2\2\2\u0142\u0137\3\2\2\2\u0143\'\3\2\2\2\u0144\u0145"+
		"\7\n\2\2\u0145\u0146\5\"\22\2\u0146)\3\2\2\2\u0147\u0149\5h\65\2\u0148"+
		"\u0147\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014c\5\36"+
		"\20\2\u014b\u0148\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014b\3\2\2\2\u014d"+
		"\u014e\3\2\2\2\u014e+\3\2\2\2\u014f\u0151\7\67\2\2\u0150\u0152\5\60\31"+
		"\2\u0151\u0150\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154"+
		"\78\2\2\u0154-\3\2\2\2\u0155\u0157\7\65\2\2\u0156\u0158\5\60\31\2\u0157"+
		"\u0156\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a\7\66"+
		"\2\2\u015a/\3\2\2\2\u015b\u015c\b\31\1\2\u015c\u015d\5\62\32\2\u015d\u0163"+
		"\3\2\2\2\u015e\u015f\f\3\2\2\u015f\u0160\7:\2\2\u0160\u0162\5\62\32\2"+
		"\u0161\u015e\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\61\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u016c\5\36\20\2\u0167"+
		"\u0168\5r:\2\u0168\u0169\7=\2\2\u0169\u016a\5\36\20\2\u016a\u016c\3\2"+
		"\2\2\u016b\u0166\3\2\2\2\u016b\u0167\3\2\2\2\u016c\63\3\2\2\2\u016d\u016e"+
		"\7;\2\2\u016e\u016f\5\u0080A\2\u016f\65\3\2\2\2\u0170\u0171\79\2\2\u0171"+
		"\67\3\2\2\2\u0172\u0173\6\35\5\2\u0173\u0177\5~@\2\u0174\u0175\6\35\6"+
		"\2\u0175\u0177\5~@\2\u0176\u0172\3\2\2\2\u0176\u0174\3\2\2\2\u01779\3"+
		"\2\2\2\u0178\u0179\7\13\2\2\u0179\u017a\7\65\2\2\u017a\u017b\5~@\2\u017b"+
		"\u017c\7\66\2\2\u017c\u017e\5\22\n\2\u017d\u017f\5<\37\2\u017e\u017d\3"+
		"\2\2\2\u017e\u017f\3\2\2\2\u017f;\3\2\2\2\u0180\u0181\7\f\2\2\u0181\u0182"+
		"\5\22\n\2\u0182=\3\2\2\2\u0183\u0184\7\r\2\2\u0184\u0185\5\22\n\2\u0185"+
		"\u0186\7\16\2\2\u0186\u0187\7\65\2\2\u0187\u0188\5~@\2\u0188\u0189\7\66"+
		"\2\2\u0189\u018a\5\u00a0Q\2\u018a\u01bf\3\2\2\2\u018b\u018c\7\16\2\2\u018c"+
		"\u018d\7\65\2\2\u018d\u018e\5~@\2\u018e\u018f\7\66\2\2\u018f\u0190\5\22"+
		"\n\2\u0190\u01bf\3\2\2\2\u0191\u0192\7\17\2\2\u0192\u0194\7\65\2\2\u0193"+
		"\u0195\5~@\2\u0194\u0193\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\3\2\2"+
		"\2\u0196\u0198\79\2\2\u0197\u0199\5~@\2\u0198\u0197\3\2\2\2\u0198\u0199"+
		"\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019c\79\2\2\u019b\u019d\5~@\2\u019c"+
		"\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\7\66"+
		"\2\2\u019f\u01bf\5\22\n\2\u01a0\u01a1\7\17\2\2\u01a1\u01a2\7\65\2\2\u01a2"+
		"\u01a3\t\4\2\2\u01a3\u01a4\5\34\17\2\u01a4\u01a6\79\2\2\u01a5\u01a7\5"+
		"~@\2\u01a6\u01a5\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8"+
		"\u01aa\79\2\2\u01a9\u01ab\5~@\2\u01aa\u01a9\3\2\2\2\u01aa\u01ab\3\2\2"+
		"\2\u01ab\u01ac\3\2\2\2\u01ac\u01ad\7\66\2\2\u01ad\u01ae\5\22\n\2\u01ae"+
		"\u01bf\3\2\2\2\u01af\u01b1\7\17\2\2\u01b0\u01b2\7\20\2\2\u01b1\u01b0\3"+
		"\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b7\7\65\2\2\u01b4"+
		"\u01b5\t\4\2\2\u01b5\u01b8\5 \21\2\u01b6\u01b8\5\u0084C\2\u01b7\u01b4"+
		"\3\2\2\2\u01b7\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\t\5\2\2\u01ba"+
		"\u01bb\5~@\2\u01bb\u01bc\7\66\2\2\u01bc\u01bd\5\22\n\2\u01bd\u01bf\3\2"+
		"\2\2\u01be\u0183\3\2\2\2\u01be\u018b\3\2\2\2\u01be\u0191\3\2\2\2\u01be"+
		"\u01a0\3\2\2\2\u01be\u01af\3\2\2\2\u01bf?\3\2\2\2\u01c0\u01c1\7\23\2\2"+
		"\u01c1\u01c3\6!\7\2\u01c2\u01c4\5\u0098M\2\u01c3\u01c2\3\2\2\2\u01c3\u01c4"+
		"\3\2\2\2\u01c4A\3\2\2\2\u01c5\u01c6\7\24\2\2\u01c6\u01c8\6\"\b\2\u01c7"+
		"\u01c9\5\u0098M\2\u01c8\u01c7\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9C\3\2\2"+
		"\2\u01ca\u01cb\7\25\2\2\u01cb\u01cd\6#\t\2\u01cc\u01ce\5~@\2\u01cd\u01cc"+
		"\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01d0\3\2\2\2\u01cf\u01d1\5\u00a0Q"+
		"\2\u01d0\u01cf\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1E\3\2\2\2\u01d2\u01d3"+
		"\7\26\2\2\u01d3\u01d4\7\65\2\2\u01d4\u01d5\5~@\2\u01d5\u01d6\7\66\2\2"+
		"\u01d6\u01d7\5\22\n\2\u01d7G\3\2\2\2\u01d8\u01d9\7\27\2\2\u01d9\u01da"+
		"\7\65\2\2\u01da\u01db\5~@\2\u01db\u01dc\7\66\2\2\u01dc\u01dd\5J&\2\u01dd"+
		"I\3\2\2\2\u01de\u01e0\7\67\2\2\u01df\u01e1\5L\'\2\u01e0\u01df\3\2\2\2"+
		"\u01e0\u01e1\3\2\2\2\u01e1\u01e6\3\2\2\2\u01e2\u01e4\5P)\2\u01e3\u01e5"+
		"\5L\'\2\u01e4\u01e3\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e7\3\2\2\2\u01e6"+
		"\u01e2\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01e9\78"+
		"\2\2\u01e9K\3\2\2\2\u01ea\u01ec\5N(\2\u01eb\u01ea\3\2\2\2\u01ec\u01ed"+
		"\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01eeM\3\2\2\2\u01ef"+
		"\u01f0\7\30\2\2\u01f0\u01f1\5~@\2\u01f1\u01f3\7=\2\2\u01f2\u01f4\5\30"+
		"\r\2\u01f3\u01f2\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4O\3\2\2\2\u01f5\u01f6"+
		"\7\31\2\2\u01f6\u01f8\7=\2\2\u01f7\u01f9\5\30\r\2\u01f8\u01f7\3\2\2\2"+
		"\u01f8\u01f9\3\2\2\2\u01f9Q\3\2\2\2\u01fa\u01fb\5r:\2\u01fb\u01fe\7=\2"+
		"\2\u01fc\u01ff\5\22\n\2\u01fd\u01ff\5~@\2\u01fe\u01fc\3\2\2\2\u01fe\u01fd"+
		"\3\2\2\2\u01ffS\3\2\2\2\u0200\u0201\7\32\2\2\u0201\u0202\6+\n\2\u0202"+
		"\u0203\5~@\2\u0203U\3\2\2\2\u0204\u0205\7\33\2\2\u0205\u0209\5\26\f\2"+
		"\u0206\u0208\5X-\2\u0207\u0206\3\2\2\2\u0208\u020b\3\2\2\2\u0209\u0207"+
		"\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u021b\3\2\2\2\u020b\u0209\3\2\2\2\u020c"+
		"\u020d\7\33\2\2\u020d\u020e\5\26\f\2\u020e\u020f\5Z.\2\u020f\u021b\3\2"+
		"\2\2\u0210\u0211\7\33\2\2\u0211\u0215\5\26\f\2\u0212\u0214\5X-\2\u0213"+
		"\u0212\3\2\2\2\u0214\u0217\3\2\2\2\u0215\u0213\3\2\2\2\u0215\u0216\3\2"+
		"\2\2\u0216\u0218\3\2\2\2\u0217\u0215\3\2\2\2\u0218\u0219\5Z.\2\u0219\u021b"+
		"\3\2\2\2\u021a\u0204\3\2\2\2\u021a\u020c\3\2\2\2\u021a\u0210\3\2\2\2\u021b"+
		"W\3\2\2\2\u021c\u021d\7\34\2\2\u021d\u021e\7\65\2\2\u021e\u0221\5 \21"+
		"\2\u021f\u0220\7\13\2\2\u0220\u0222\5~@\2\u0221\u021f\3\2\2\2\u0221\u0222"+
		"\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0224\7\66\2\2\u0224\u0225\5\26\f\2"+
		"\u0225Y\3\2\2\2\u0226\u0227\7\35\2\2\u0227\u0228\5\26\f\2\u0228[\3\2\2"+
		"\2\u0229\u022a\7\36\2\2\u022a]\3\2\2\2\u022b\u0232\5(\25\2\u022c\u0232"+
		"\5`\61\2\u022d\u022e\5`\61\2\u022e\u022f\7:\2\2\u022f\u0230\5(\25\2\u0230"+
		"\u0232\3\2\2\2\u0231\u022b\3\2\2\2\u0231\u022c\3\2\2\2\u0231\u022d\3\2"+
		"\2\2\u0232_\3\2\2\2\u0233\u0238\5b\62\2\u0234\u0235\7:\2\2\u0235\u0237"+
		"\5b\62\2\u0236\u0234\3\2\2\2\u0237\u023a\3\2\2\2\u0238\u0236\3\2\2\2\u0238"+
		"\u0239\3\2\2\2\u0239a\3\2\2\2\u023a\u0238\3\2\2\2\u023b\u0240\5\u0098"+
		"M\2\u023c\u0240\5d\63\2\u023d\u0240\5j\66\2\u023e\u0240\5l\67\2\u023f"+
		"\u023b\3\2\2\2\u023f\u023c\3\2\2\2\u023f\u023d\3\2\2\2\u023f\u023e\3\2"+
		"\2\2\u0240c\3\2\2\2\u0241\u0243\7\63\2\2\u0242\u0244\5h\65\2\u0243\u0242"+
		"\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0253\7\64\2\2"+
		"\u0246\u0247\7\63\2\2\u0247\u0248\5f\64\2\u0248\u0249\7\64\2\2\u0249\u0253"+
		"\3\2\2\2\u024a\u024b\7\63\2\2\u024b\u024c\5f\64\2\u024c\u024e\7:\2\2\u024d"+
		"\u024f\5h\65\2\u024e\u024d\3\2\2\2\u024e\u024f\3\2\2\2\u024f\u0250\3\2"+
		"\2\2\u0250\u0251\7\64\2\2\u0251\u0253\3\2\2\2\u0252\u0241\3\2\2\2\u0252"+
		"\u0246\3\2\2\2\u0252\u024a\3\2\2\2\u0253e\3\2\2\2\u0254\u0256\b\64\1\2"+
		"\u0255\u0257\5h\65\2\u0256\u0255\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0259"+
		"\3\2\2\2\u0258\u025a\7\n\2\2\u0259\u0258\3\2\2\2\u0259\u025a\3\2\2\2\u025a"+
		"\u025b\3\2\2\2\u025b\u025c\5\u0080A\2\u025c\u0268\3\2\2\2\u025d\u025e"+
		"\f\3\2\2\u025e\u0260\7:\2\2\u025f\u0261\5h\65\2\u0260\u025f\3\2\2\2\u0260"+
		"\u0261\3\2\2\2\u0261\u0263\3\2\2\2\u0262\u0264\7\n\2\2\u0263\u0262\3\2"+
		"\2\2\u0263\u0264\3\2\2\2\u0264\u0265\3\2\2\2\u0265\u0267\5\u0080A\2\u0266"+
		"\u025d\3\2\2\2\u0267\u026a\3\2\2\2\u0268\u0266\3\2\2\2\u0268\u0269\3\2"+
		"\2\2\u0269g\3\2\2\2\u026a\u0268\3\2\2\2\u026b\u026d\7:\2\2\u026c\u026b"+
		"\3\2\2\2\u026d\u026e\3\2\2\2\u026e\u026c\3\2\2\2\u026e\u026f\3\2\2\2\u026f"+
		"i\3\2\2\2\u0270\u0271\7\67\2\2\u0271\u027c\78\2\2\u0272\u0273\7\67\2\2"+
		"\u0273\u0274\5l\67\2\u0274\u0275\78\2\2\u0275\u027c\3\2\2\2\u0276\u0277"+
		"\7\67\2\2\u0277\u0278\5l\67\2\u0278\u0279\7:\2\2\u0279\u027a\78\2\2\u027a"+
		"\u027c\3\2\2\2\u027b\u0270\3\2\2\2\u027b\u0272\3\2\2\2\u027b\u0276\3\2"+
		"\2\2\u027ck\3\2\2\2\u027d\u027e\b\67\1\2\u027e\u027f\5n8\2\u027f\u0285"+
		"\3\2\2\2\u0280\u0281\f\3\2\2\u0281\u0282\7:\2\2\u0282\u0284\5n8\2\u0283"+
		"\u0280\3\2\2\2\u0284\u0287\3\2\2\2\u0285\u0283\3\2\2\2\u0285\u0286\3\2"+
		"\2\2\u0286m\3\2\2\2\u0287\u0285\3\2\2\2\u0288\u028a\5\"\22\2\u0289\u028b"+
		"\5\64\33\2\u028a\u0289\3\2\2\2\u028a\u028b\3\2\2\2\u028b\u0292\3\2\2\2"+
		"\u028c\u028d\5r:\2\u028d\u028e\7=\2\2\u028e\u028f\5\u0080A\2\u028f\u0292"+
		"\3\2\2\2\u0290\u0292\5p9\2\u0291\u0288\3\2\2\2\u0291\u028c\3\2\2\2\u0291"+
		"\u0290\3\2\2\2\u0292o\3\2\2\2\u0293\u0294\7\37\2\2\u0294\u0295\5r:\2\u0295"+
		"\u0296\7\65\2\2\u0296\u0297\7\66\2\2\u0297\u029b\7\67\2\2\u0298\u029a"+
		"\5\4\3\2\u0299\u0298\3\2\2\2\u029a\u029d\3\2\2\2\u029b\u0299\3\2\2\2\u029b"+
		"\u029c\3\2\2\2\u029c\u029e\3\2\2\2\u029d\u029b\3\2\2\2\u029e\u029f\78"+
		"\2\2\u029f\u02bf\3\2\2\2\u02a0\u02a1\7 \2\2\u02a1\u02a2\5r:\2\u02a2\u02a3"+
		"\7\65\2\2\u02a3\u02a4\5\36\20\2\u02a4\u02a5\7\66\2\2\u02a5\u02a9\7\67"+
		"\2\2\u02a6\u02a8\5\4\3\2\u02a7\u02a6\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9"+
		"\u02a7\3\2\2\2\u02a9\u02aa\3\2\2\2\u02aa\u02ac\3\2\2\2\u02ab\u02a9\3\2"+
		"\2\2\u02ac\u02ad\78\2\2\u02ad\u02bf\3\2\2\2\u02ae\u02af\69\r\2\u02af\u02b0"+
		"\5r:\2\u02b0\u02b2\7\65\2\2\u02b1\u02b3\5^\60\2\u02b2\u02b1\3\2\2\2\u02b2"+
		"\u02b3\3\2\2\2\u02b3\u02b4\3\2\2\2\u02b4\u02b5\7\66\2\2\u02b5\u02b9\7"+
		"\67\2\2\u02b6\u02b8\5\4\3\2\u02b7\u02b6\3\2\2\2\u02b8\u02bb\3\2\2\2\u02b9"+
		"\u02b7\3\2\2\2\u02b9\u02ba\3\2\2\2\u02ba\u02bc\3\2\2\2\u02bb\u02b9\3\2"+
		"\2\2\u02bc\u02bd\78\2\2\u02bd\u02bf\3\2\2\2\u02be\u0293\3\2\2\2\u02be"+
		"\u02a0\3\2\2\2\u02be\u02ae\3\2\2\2\u02bfq\3\2\2\2\u02c0\u02c8\5\u0098"+
		"M\2\u02c1\u02c8\5\u0094K\2\u02c2\u02c8\5\u0096L\2\u02c3\u02c4\7\63\2\2"+
		"\u02c4\u02c5\5\u0080A\2\u02c5\u02c6\7\64\2\2\u02c6\u02c8\3\2\2\2\u02c7"+
		"\u02c0\3\2\2\2\u02c7\u02c1\3\2\2\2\u02c7\u02c2\3\2\2\2\u02c7\u02c3\3\2"+
		"\2\2\u02c8s\3\2\2\2\u02c9\u02cb\7\65\2\2\u02ca\u02cc\5v<\2\u02cb\u02ca"+
		"\3\2\2\2\u02cb\u02cc\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cd\u02ce\7\66\2\2"+
		"\u02ceu\3\2\2\2\u02cf\u02d1\b<\1\2\u02d0\u02d2\7\n\2\2\u02d1\u02d0\3\2"+
		"\2\2\u02d1\u02d2\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02d4\5\u0080A\2\u02d4"+
		"\u02de\3\2\2\2\u02d5\u02d6\f\3\2\2\u02d6\u02d8\5h\65\2\u02d7\u02d9\7\n"+
		"\2\2\u02d8\u02d7\3\2\2\2\u02d8\u02d9\3\2\2\2\u02d9\u02da\3\2\2\2\u02da"+
		"\u02db\5\u0080A\2\u02db\u02dd\3\2\2\2\u02dc\u02d5\3\2\2\2\u02dd\u02e0"+
		"\3\2\2\2\u02de\u02dc\3\2\2\2\u02de\u02df\3\2\2\2\u02dfw\3\2\2\2\u02e0"+
		"\u02de\3\2\2\2\u02e1\u02e2\5z>\2\u02e2\u02e3\6=\17\2\u02e3\u02e4\7!\2"+
		"\2\u02e4\u02e5\5|?\2\u02e5y\3\2\2\2\u02e6\u02f0\5\"\22\2\u02e7\u02e9\7"+
		"\65\2\2\u02e8\u02ea\5~@\2\u02e9\u02e8\3\2\2\2\u02e9\u02ea\3\2\2\2\u02ea"+
		"\u02ec\3\2\2\2\u02eb\u02ed\5(\25\2\u02ec\u02eb\3\2\2\2\u02ec\u02ed\3\2"+
		"\2\2\u02ed\u02ee\3\2\2\2\u02ee\u02f0\7\66\2\2\u02ef\u02e6\3\2\2\2\u02ef"+
		"\u02e7\3\2\2\2\u02f0{\3\2\2\2\u02f1\u02f2\6?\20\2\u02f2\u02fc\5\u0080"+
		"A\2\u02f3\u02f7\7\67\2\2\u02f4\u02f6\5\4\3\2\u02f5\u02f4\3\2\2\2\u02f6"+
		"\u02f9\3\2\2\2\u02f7\u02f5\3\2\2\2\u02f7\u02f8\3\2\2\2\u02f8\u02fa\3\2"+
		"\2\2\u02f9\u02f7\3\2\2\2\u02fa\u02fc\78\2\2\u02fb\u02f1\3\2\2\2\u02fb"+
		"\u02f3\3\2\2\2\u02fc}\3\2\2\2\u02fd\u02fe\b@\1\2\u02fe\u02ff\5\u0080A"+
		"\2\u02ff\u0305\3\2\2\2\u0300\u0301\f\3\2\2\u0301\u0302\7:\2\2\u0302\u0304"+
		"\5\u0080A\2\u0303\u0300\3\2\2\2\u0304\u0307\3\2\2\2\u0305\u0303\3\2\2"+
		"\2\u0305\u0306\3\2\2\2\u0306\177\3\2\2\2\u0307\u0305\3\2\2\2\u0308\u0309"+
		"\bA\1\2\u0309\u030a\5\u0082B\2\u030a\u030b\7;\2\2\u030b\u030c\5\u0080"+
		"A\7\u030c\u0315\3\2\2\2\u030d\u030e\5\u0082B\2\u030e\u030f\5\u0090I\2"+
		"\u030f\u0310\5\u0080A\6\u0310\u0315\3\2\2\2\u0311\u0315\5\u0082B\2\u0312"+
		"\u0315\5\24\13\2\u0313\u0315\5x=\2\u0314\u0308\3\2\2\2\u0314\u030d\3\2"+
		"\2\2\u0314\u0311\3\2\2\2\u0314\u0312\3\2\2\2\u0314\u0313\3\2\2\2\u0315"+
		"\u032d\3\2\2\2\u0316\u0317\f\4\2\2\u0317\u0319\7\17\2\2\u0318\u031a\7"+
		"\20\2\2\u0319\u0318\3\2\2\2\u0319\u031a\3\2\2\2\u031a\u031b\3\2\2\2\u031b"+
		"\u031f\7\65\2\2\u031c\u031d\t\4\2\2\u031d\u0320\5 \21\2\u031e\u0320\5"+
		"\u0082B\2\u031f\u031c\3\2\2\2\u031f\u031e\3\2\2\2\u0320\u0321\3\2\2\2"+
		"\u0321\u0322\t\5\2\2\u0322\u0323\5~@\2\u0323\u0324\7\66\2\2\u0324\u032c"+
		"\3\2\2\2\u0325\u0326\f\3\2\2\u0326\u0327\7\13\2\2\u0327\u0328\7\65\2\2"+
		"\u0328\u0329\5~@\2\u0329\u032a\7\66\2\2\u032a\u032c\3\2\2\2\u032b\u0316"+
		"\3\2\2\2\u032b\u0325\3\2\2\2\u032c\u032f\3\2\2\2\u032d\u032b\3\2\2\2\u032d"+
		"\u032e\3\2\2\2\u032e\u0081\3\2\2\2\u032f\u032d\3\2\2\2\u0330\u0331\bB"+
		"\1\2\u0331\u0332\7#\2\2\u0332\u0345\5\u0082B\16\u0333\u0334\7$\2\2\u0334"+
		"\u0345\5\u0082B\r\u0335\u0336\7%\2\2\u0336\u0345\5\u0082B\f\u0337\u0338"+
		"\7?\2\2\u0338\u0345\5\u0082B\13\u0339\u033a\7@\2\2\u033a\u0345\5\u0082"+
		"B\n\u033b\u033c\7A\2\2\u033c\u0345\5\u0082B\t\u033d\u033e\7B\2\2\u033e"+
		"\u0345\5\u0082B\b\u033f\u0340\7C\2\2\u0340\u0345\5\u0082B\7\u0341\u0342"+
		"\7D\2\2\u0342\u0345\5\u0082B\6\u0343\u0345\5\u0084C\2\u0344\u0330\3\2"+
		"\2\2\u0344\u0333\3\2\2\2\u0344\u0335\3\2\2\2\u0344\u0337\3\2\2\2\u0344"+
		"\u0339\3\2\2\2\u0344\u033b\3\2\2\2\u0344\u033d\3\2\2\2\u0344\u033f\3\2"+
		"\2\2\u0344\u0341\3\2\2\2\u0344\u0343\3\2\2\2\u0345\u0399\3\2\2\2\u0346"+
		"\u0347\f%\2\2\u0347\u0348\7W\2\2\u0348\u0398\5\u0082B&\u0349\u034a\f$"+
		"\2\2\u034a\u034b\7V\2\2\u034b\u0398\5\u0082B%\u034c\u034d\f#\2\2\u034d"+
		"\u034e\7U\2\2\u034e\u0398\5\u0082B$\u034f\u0350\f\"\2\2\u0350\u0351\7"+
		"T\2\2\u0351\u0398\5\u0082B#\u0352\u0353\f!\2\2\u0353\u0354\7S\2\2\u0354"+
		"\u0398\5\u0082B\"\u0355\u0356\f \2\2\u0356\u0357\7O\2\2\u0357\u0398\5"+
		"\u0082B!\u0358\u0359\f\37\2\2\u0359\u035a\7P\2\2\u035a\u0398\5\u0082B"+
		" \u035b\u035c\f\36\2\2\u035c\u035d\7Q\2\2\u035d\u0398\5\u0082B\37\u035e"+
		"\u035f\f\35\2\2\u035f\u0360\7R\2\2\u0360\u0398\5\u0082B\36\u0361\u0362"+
		"\f\34\2\2\u0362\u0363\7K\2\2\u0363\u0398\5\u0082B\35\u0364\u0365\f\33"+
		"\2\2\u0365\u0366\7L\2\2\u0366\u0398\5\u0082B\34\u0367\u0368\f\32\2\2\u0368"+
		"\u0369\7M\2\2\u0369\u0398\5\u0082B\33\u036a\u036b\f\31\2\2\u036b\u036c"+
		"\7N\2\2\u036c\u0398\5\u0082B\32\u036d\u036e\f\30\2\2\u036e\u036f\7\"\2"+
		"\2\u036f\u0398\5\u0082B\31\u0370\u0371\f\27\2\2\u0371\u0372\7\21\2\2\u0372"+
		"\u0398\5\u0082B\30\u0373\u0374\f\26\2\2\u0374\u0375\7I\2\2\u0375\u0398"+
		"\5\u0082B\27\u0376\u0377\f\25\2\2\u0377\u0378\7H\2\2\u0378\u0398\5\u0082"+
		"B\26\u0379\u037a\f\24\2\2\u037a\u037b\7J\2\2\u037b\u0398\5\u0082B\25\u037c"+
		"\u037d\f\23\2\2\u037d\u037e\7A\2\2\u037e\u0398\5\u0082B\24\u037f\u0380"+
		"\f\22\2\2\u0380\u0381\7B\2\2\u0381\u0398\5\u0082B\23\u0382\u0383\f\21"+
		"\2\2\u0383\u0384\7E\2\2\u0384\u0398\5\u0082B\22\u0385\u0386\f\20\2\2\u0386"+
		"\u0387\7F\2\2\u0387\u0398\5\u0082B\21\u0388\u0389\f\17\2\2\u0389\u038a"+
		"\7G\2\2\u038a\u0398\5\u0082B\20\u038b\u038c\f&\2\2\u038c\u038d\7<\2\2"+
		"\u038d\u038e\5\u0080A\2\u038e\u038f\7=\2\2\u038f\u0390\5\u0080A\2\u0390"+
		"\u0398\3\2\2\2\u0391\u0392\f\5\2\2\u0392\u0393\6B-\2\u0393\u0398\7?\2"+
		"\2\u0394\u0395\f\4\2\2\u0395\u0396\6B/\2\u0396\u0398\7@\2\2\u0397\u0346"+
		"\3\2\2\2\u0397\u0349\3\2\2\2\u0397\u034c\3\2\2\2\u0397\u034f\3\2\2\2\u0397"+
		"\u0352\3\2\2\2\u0397\u0355\3\2\2\2\u0397\u0358\3\2\2\2\u0397\u035b\3\2"+
		"\2\2\u0397\u035e\3\2\2\2\u0397\u0361\3\2\2\2\u0397\u0364\3\2\2\2\u0397"+
		"\u0367\3\2\2\2\u0397\u036a\3\2\2\2\u0397\u036d\3\2\2\2\u0397\u0370\3\2"+
		"\2\2\u0397\u0373\3\2\2\2\u0397\u0376\3\2\2\2\u0397\u0379\3\2\2\2\u0397"+
		"\u037c\3\2\2\2\u0397\u037f\3\2\2\2\u0397\u0382\3\2\2\2\u0397\u0385\3\2"+
		"\2\2\u0397\u0388\3\2\2\2\u0397\u038b\3\2\2\2\u0397\u0391\3\2\2\2\u0397"+
		"\u0394\3\2\2\2\u0398\u039b\3\2\2\2\u0399\u0397\3\2\2\2\u0399\u039a\3\2"+
		"\2\2\u039a\u0083\3\2\2\2\u039b\u0399\3\2\2\2\u039c\u039f\5\u0086D\2\u039d"+
		"\u039f\5\u0088E\2\u039e\u039c\3\2\2\2\u039e\u039d\3\2\2\2\u039f\u0085"+
		"\3\2\2\2\u03a0\u03a1\bD\1\2\u03a1\u03a2\5\u008aF\2\u03a2\u03a3\5t;\2\u03a3"+
		"\u03b0\3\2\2\2\u03a4\u03a5\f\5\2\2\u03a5\u03af\5t;\2\u03a6\u03a7\f\4\2"+
		"\2\u03a7\u03a8\7\63\2\2\u03a8\u03a9\5~@\2\u03a9\u03aa\7\64\2\2\u03aa\u03af"+
		"\3\2\2\2\u03ab\u03ac\f\3\2\2\u03ac\u03ad\7>\2\2\u03ad\u03af\5\u0098M\2"+
		"\u03ae\u03a4\3\2\2\2\u03ae\u03a6\3\2\2\2\u03ae\u03ab\3\2\2\2\u03af\u03b2"+
		"\3\2\2\2\u03b0\u03ae\3\2\2\2\u03b0\u03b1\3\2\2\2\u03b1\u0087\3\2\2\2\u03b2"+
		"\u03b0\3\2\2\2\u03b3\u03b7\5\u008aF\2\u03b4\u03b5\7&\2\2\u03b5\u03b7\5"+
		"\u0088E\2\u03b6\u03b3\3\2\2\2\u03b6\u03b4\3\2\2\2\u03b7\u0089\3\2\2\2"+
		"\u03b8\u03b9\bF\1\2\u03b9\u03bf\5\u008eH\2\u03ba\u03bb\7&\2\2\u03bb\u03bc"+
		"\5\u008aF\2\u03bc\u03bd\5t;\2\u03bd\u03bf\3\2\2\2\u03be\u03b8\3\2\2\2"+
		"\u03be\u03ba\3\2\2\2\u03bf\u03ca\3\2\2\2\u03c0\u03c1\f\5\2\2\u03c1\u03c2"+
		"\7\63\2\2\u03c2\u03c3\5~@\2\u03c3\u03c4\7\64\2\2\u03c4\u03c9\3\2\2\2\u03c5"+
		"\u03c6\f\4\2\2\u03c6\u03c7\7>\2\2\u03c7\u03c9\5\u0098M\2\u03c8\u03c0\3"+
		"\2\2\2\u03c8\u03c5\3\2\2\2\u03c9\u03cc\3\2\2\2\u03ca\u03c8\3\2\2\2\u03ca"+
		"\u03cb\3\2\2\2\u03cb\u008b\3\2\2\2\u03cc\u03ca\3\2\2\2\u03cd\u03cf\t\2"+
		"\2\2\u03ce\u03d0\5\u0098M\2\u03cf\u03ce\3\2\2\2\u03cf\u03d0\3\2\2\2\u03d0"+
		"\u03d1\3\2\2\2\u03d1\u03d3\7\65\2\2\u03d2\u03d4\5^\60\2\u03d3\u03d2\3"+
		"\2\2\2\u03d3\u03d4\3\2\2\2\u03d4\u03d5\3\2\2\2\u03d5\u03df\7\66\2\2\u03d6"+
		"\u03da\7\67\2\2\u03d7\u03d9\5\4\3\2\u03d8\u03d7\3\2\2\2\u03d9\u03dc\3"+
		"\2\2\2\u03da\u03d8\3\2\2\2\u03da\u03db\3\2\2\2\u03db\u03dd\3\2\2\2\u03dc"+
		"\u03da\3\2\2\2\u03dd\u03e0\78\2\2\u03de\u03e0\5\22\n\2\u03df\u03d6\3\2"+
		"\2\2\u03df\u03de\3\2\2\2\u03e0\u008d\3\2\2\2\u03e1\u03ed\7\'\2\2\u03e2"+
		"\u03ed\5\u0098M\2\u03e3\u03ed\5\u008cG\2\u03e4\u03ed\5\b\5\2\u03e5\u03ed"+
		"\5\u0092J\2\u03e6\u03ed\5j\66\2\u03e7\u03e8\7\65\2\2\u03e8\u03e9\5~@\2"+
		"\u03e9\u03ea\7\66\2\2\u03ea\u03ed\3\2\2\2\u03eb\u03ed\5d\63\2\u03ec\u03e1"+
		"\3\2\2\2\u03ec\u03e2\3\2\2\2\u03ec\u03e3\3\2\2\2\u03ec\u03e4\3\2\2\2\u03ec"+
		"\u03e5\3\2\2\2\u03ec\u03e6\3\2\2\2\u03ec\u03e7\3\2\2\2\u03ec\u03eb\3\2"+
		"\2\2\u03ed\u008f\3\2\2\2\u03ee\u03ef\t\6\2\2\u03ef\u0091\3\2\2\2\u03f0"+
		"\u03f7\7(\2\2\u03f1\u03f7\7)\2\2\u03f2\u03f7\7*\2\2\u03f3\u03f7\5\u0094"+
		"K\2\u03f4\u03f7\7\61\2\2\u03f5\u03f7\5\u0096L\2\u03f6\u03f0\3\2\2\2\u03f6"+
		"\u03f1\3\2\2\2\u03f6\u03f2\3\2\2\2\u03f6\u03f3\3\2\2\2\u03f6\u03f4\3\2"+
		"\2\2\u03f6\u03f5\3\2\2\2\u03f7\u0093\3\2\2\2\u03f8\u03f9\7\u0097\2\2\u03f9"+
		"\u0095\3\2\2\2\u03fa\u03fb\t\7\2\2\u03fb\u0097\3\2\2\2\u03fc\u03ff\5\u009a"+
		"N\2\u03fd\u03ff\7\u0096\2\2\u03fe\u03fc\3\2\2\2\u03fe\u03fd\3\2\2\2\u03ff"+
		"\u0099\3\2\2\2\u0400\u0403\5\u009cO\2\u0401\u0403\5\u009eP\2\u0402\u0400"+
		"\3\2\2\2\u0402\u0401\3\2\2\2\u0403\u009b\3\2\2\2\u0404\u0405\t\b\2\2\u0405"+
		"\u009d\3\2\2\2\u0406\u0407\t\t\2\2\u0407\u009f\3\2\2\2\u0408\u040d\79"+
		"\2\2\u0409\u040d\7\2\2\3\u040a\u040d\6Q\65\2\u040b\u040d\6Q\66\2\u040c"+
		"\u0408\3\2\2\2\u040c\u0409\3\2\2\2\u040c\u040a\3\2\2\2\u040c\u040b\3\2"+
		"\2\2\u040d\u00a1\3\2\2\2\u040e\u040f\7\2\2\3\u040f\u00a3\3\2\2\2s\u00a7"+
		"\u00af\u00b3\u00b7\u00be\u00c3\u00c7\u00cc\u00d8\u00dc\u00e6\u00f4\u00f8"+
		"\u00fc\u0107\u010d\u0117\u011c\u0120\u0124\u0129\u012d\u0130\u013b\u013e"+
		"\u0142\u0148\u014d\u0151\u0157\u0163\u016b\u0176\u017e\u0194\u0198\u019c"+
		"\u01a6\u01aa\u01b1\u01b7\u01be\u01c3\u01c8\u01cd\u01d0\u01e0\u01e4\u01e6"+
		"\u01ed\u01f3\u01f8\u01fe\u0209\u0215\u021a\u0221\u0231\u0238\u023f\u0243"+
		"\u024e\u0252\u0256\u0259\u0260\u0263\u0268\u026e\u027b\u0285\u028a\u0291"+
		"\u029b\u02a9\u02b2\u02b9\u02be\u02c7\u02cb\u02d1\u02d8\u02de\u02e9\u02ec"+
		"\u02ef\u02f7\u02fb\u0305\u0314\u0319\u031f\u032b\u032d\u0344\u0397\u0399"+
		"\u039e\u03ae\u03b0\u03b6\u03be\u03c8\u03ca\u03cf\u03d3\u03da\u03df\u03ec"+
		"\u03f6\u03fe\u0402\u040c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}