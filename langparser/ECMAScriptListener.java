// Generated from ECMAScript.g4 by ANTLR 4.5
package langparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ECMAScriptParser}.
 */
public interface ECMAScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull ECMAScriptParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull ECMAScriptParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#statementListItem}.
	 * @param ctx the parse tree
	 */
	void enterStatementListItem(@NotNull ECMAScriptParser.StatementListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#statementListItem}.
	 * @param ctx the parse tree
	 */
	void exitStatementListItem(@NotNull ECMAScriptParser.StatementListItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(@NotNull ECMAScriptParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(@NotNull ECMAScriptParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(@NotNull ECMAScriptParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(@NotNull ECMAScriptParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#classTail}.
	 * @param ctx the parse tree
	 */
	void enterClassTail(@NotNull ECMAScriptParser.ClassTailContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#classTail}.
	 * @param ctx the parse tree
	 */
	void exitClassTail(@NotNull ECMAScriptParser.ClassTailContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#classHeritage}.
	 * @param ctx the parse tree
	 */
	void enterClassHeritage(@NotNull ECMAScriptParser.ClassHeritageContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#classHeritage}.
	 * @param ctx the parse tree
	 */
	void exitClassHeritage(@NotNull ECMAScriptParser.ClassHeritageContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(@NotNull ECMAScriptParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(@NotNull ECMAScriptParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#classElement}.
	 * @param ctx the parse tree
	 */
	void enterClassElement(@NotNull ECMAScriptParser.ClassElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#classElement}.
	 * @param ctx the parse tree
	 */
	void exitClassElement(@NotNull ECMAScriptParser.ClassElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull ECMAScriptParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull ECMAScriptParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#yieldExpression}.
	 * @param ctx the parse tree
	 */
	void enterYieldExpression(@NotNull ECMAScriptParser.YieldExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#yieldExpression}.
	 * @param ctx the parse tree
	 */
	void exitYieldExpression(@NotNull ECMAScriptParser.YieldExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull ECMAScriptParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull ECMAScriptParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(@NotNull ECMAScriptParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(@NotNull ECMAScriptParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#variableStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableStatement(@NotNull ECMAScriptParser.VariableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#variableStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableStatement(@NotNull ECMAScriptParser.VariableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#variableDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationList(@NotNull ECMAScriptParser.VariableDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#variableDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationList(@NotNull ECMAScriptParser.VariableDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(@NotNull ECMAScriptParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(@NotNull ECMAScriptParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#identifierBinding}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierBinding(@NotNull ECMAScriptParser.IdentifierBindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#identifierBinding}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierBinding(@NotNull ECMAScriptParser.IdentifierBindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#identifierReference}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierReference(@NotNull ECMAScriptParser.IdentifierReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#identifierReference}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierReference(@NotNull ECMAScriptParser.IdentifierReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#identifierPattern}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierPattern(@NotNull ECMAScriptParser.IdentifierPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#identifierPattern}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierPattern(@NotNull ECMAScriptParser.IdentifierPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#arrayBindingPattern}.
	 * @param ctx the parse tree
	 */
	void enterArrayBindingPattern(@NotNull ECMAScriptParser.ArrayBindingPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#arrayBindingPattern}.
	 * @param ctx the parse tree
	 */
	void exitArrayBindingPattern(@NotNull ECMAScriptParser.ArrayBindingPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#restElement}.
	 * @param ctx the parse tree
	 */
	void enterRestElement(@NotNull ECMAScriptParser.RestElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#restElement}.
	 * @param ctx the parse tree
	 */
	void exitRestElement(@NotNull ECMAScriptParser.RestElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#bindingElementList}.
	 * @param ctx the parse tree
	 */
	void enterBindingElementList(@NotNull ECMAScriptParser.BindingElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#bindingElementList}.
	 * @param ctx the parse tree
	 */
	void exitBindingElementList(@NotNull ECMAScriptParser.BindingElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#objectBindingPattern}.
	 * @param ctx the parse tree
	 */
	void enterObjectBindingPattern(@NotNull ECMAScriptParser.ObjectBindingPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#objectBindingPattern}.
	 * @param ctx the parse tree
	 */
	void exitObjectBindingPattern(@NotNull ECMAScriptParser.ObjectBindingPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#argumentBindingPattern}.
	 * @param ctx the parse tree
	 */
	void enterArgumentBindingPattern(@NotNull ECMAScriptParser.ArgumentBindingPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#argumentBindingPattern}.
	 * @param ctx the parse tree
	 */
	void exitArgumentBindingPattern(@NotNull ECMAScriptParser.ArgumentBindingPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#bindingPropertyList}.
	 * @param ctx the parse tree
	 */
	void enterBindingPropertyList(@NotNull ECMAScriptParser.BindingPropertyListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#bindingPropertyList}.
	 * @param ctx the parse tree
	 */
	void exitBindingPropertyList(@NotNull ECMAScriptParser.BindingPropertyListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#bindingProperty}.
	 * @param ctx the parse tree
	 */
	void enterBindingProperty(@NotNull ECMAScriptParser.BindingPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#bindingProperty}.
	 * @param ctx the parse tree
	 */
	void exitBindingProperty(@NotNull ECMAScriptParser.BindingPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#initialiser}.
	 * @param ctx the parse tree
	 */
	void enterInitialiser(@NotNull ECMAScriptParser.InitialiserContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#initialiser}.
	 * @param ctx the parse tree
	 */
	void exitInitialiser(@NotNull ECMAScriptParser.InitialiserContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(@NotNull ECMAScriptParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(@NotNull ECMAScriptParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(@NotNull ECMAScriptParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(@NotNull ECMAScriptParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(@NotNull ECMAScriptParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(@NotNull ECMAScriptParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(@NotNull ECMAScriptParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(@NotNull ECMAScriptParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(@NotNull ECMAScriptParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(@NotNull ECMAScriptParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(@NotNull ECMAScriptParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(@NotNull ECMAScriptParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(@NotNull ECMAScriptParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(@NotNull ECMAScriptParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(@NotNull ECMAScriptParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(@NotNull ECMAScriptParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#withStatement}.
	 * @param ctx the parse tree
	 */
	void enterWithStatement(@NotNull ECMAScriptParser.WithStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#withStatement}.
	 * @param ctx the parse tree
	 */
	void exitWithStatement(@NotNull ECMAScriptParser.WithStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(@NotNull ECMAScriptParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(@NotNull ECMAScriptParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#caseBlock}.
	 * @param ctx the parse tree
	 */
	void enterCaseBlock(@NotNull ECMAScriptParser.CaseBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#caseBlock}.
	 * @param ctx the parse tree
	 */
	void exitCaseBlock(@NotNull ECMAScriptParser.CaseBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#caseClauses}.
	 * @param ctx the parse tree
	 */
	void enterCaseClauses(@NotNull ECMAScriptParser.CaseClausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#caseClauses}.
	 * @param ctx the parse tree
	 */
	void exitCaseClauses(@NotNull ECMAScriptParser.CaseClausesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#caseClause}.
	 * @param ctx the parse tree
	 */
	void enterCaseClause(@NotNull ECMAScriptParser.CaseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#caseClause}.
	 * @param ctx the parse tree
	 */
	void exitCaseClause(@NotNull ECMAScriptParser.CaseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#defaultClause}.
	 * @param ctx the parse tree
	 */
	void enterDefaultClause(@NotNull ECMAScriptParser.DefaultClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#defaultClause}.
	 * @param ctx the parse tree
	 */
	void exitDefaultClause(@NotNull ECMAScriptParser.DefaultClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#labelledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabelledStatement(@NotNull ECMAScriptParser.LabelledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#labelledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabelledStatement(@NotNull ECMAScriptParser.LabelledStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrowStatement(@NotNull ECMAScriptParser.ThrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrowStatement(@NotNull ECMAScriptParser.ThrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void enterTryStatement(@NotNull ECMAScriptParser.TryStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void exitTryStatement(@NotNull ECMAScriptParser.TryStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#catchProduction}.
	 * @param ctx the parse tree
	 */
	void enterCatchProduction(@NotNull ECMAScriptParser.CatchProductionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#catchProduction}.
	 * @param ctx the parse tree
	 */
	void exitCatchProduction(@NotNull ECMAScriptParser.CatchProductionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#finallyProduction}.
	 * @param ctx the parse tree
	 */
	void enterFinallyProduction(@NotNull ECMAScriptParser.FinallyProductionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#finallyProduction}.
	 * @param ctx the parse tree
	 */
	void exitFinallyProduction(@NotNull ECMAScriptParser.FinallyProductionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#debuggerStatement}.
	 * @param ctx the parse tree
	 */
	void enterDebuggerStatement(@NotNull ECMAScriptParser.DebuggerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#debuggerStatement}.
	 * @param ctx the parse tree
	 */
	void exitDebuggerStatement(@NotNull ECMAScriptParser.DebuggerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(@NotNull ECMAScriptParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(@NotNull ECMAScriptParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(@NotNull ECMAScriptParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(@NotNull ECMAScriptParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(@NotNull ECMAScriptParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(@NotNull ECMAScriptParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(@NotNull ECMAScriptParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(@NotNull ECMAScriptParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#elementList}.
	 * @param ctx the parse tree
	 */
	void enterElementList(@NotNull ECMAScriptParser.ElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#elementList}.
	 * @param ctx the parse tree
	 */
	void exitElementList(@NotNull ECMAScriptParser.ElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#elision}.
	 * @param ctx the parse tree
	 */
	void enterElision(@NotNull ECMAScriptParser.ElisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#elision}.
	 * @param ctx the parse tree
	 */
	void exitElision(@NotNull ECMAScriptParser.ElisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#objectLiteral}.
	 * @param ctx the parse tree
	 */
	void enterObjectLiteral(@NotNull ECMAScriptParser.ObjectLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#objectLiteral}.
	 * @param ctx the parse tree
	 */
	void exitObjectLiteral(@NotNull ECMAScriptParser.ObjectLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#propertyNameAndValueList}.
	 * @param ctx the parse tree
	 */
	void enterPropertyNameAndValueList(@NotNull ECMAScriptParser.PropertyNameAndValueListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#propertyNameAndValueList}.
	 * @param ctx the parse tree
	 */
	void exitPropertyNameAndValueList(@NotNull ECMAScriptParser.PropertyNameAndValueListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void enterPropertyAssignment(@NotNull ECMAScriptParser.PropertyAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void exitPropertyAssignment(@NotNull ECMAScriptParser.PropertyAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#methodDefinition}.
	 * @param ctx the parse tree
	 */
	void enterMethodDefinition(@NotNull ECMAScriptParser.MethodDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#methodDefinition}.
	 * @param ctx the parse tree
	 */
	void exitMethodDefinition(@NotNull ECMAScriptParser.MethodDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyName(@NotNull ECMAScriptParser.PropertyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyName(@NotNull ECMAScriptParser.PropertyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull ECMAScriptParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull ECMAScriptParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(@NotNull ECMAScriptParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(@NotNull ECMAScriptParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#arrowFunction}.
	 * @param ctx the parse tree
	 */
	void enterArrowFunction(@NotNull ECMAScriptParser.ArrowFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#arrowFunction}.
	 * @param ctx the parse tree
	 */
	void exitArrowFunction(@NotNull ECMAScriptParser.ArrowFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#arrowParameters}.
	 * @param ctx the parse tree
	 */
	void enterArrowParameters(@NotNull ECMAScriptParser.ArrowParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#arrowParameters}.
	 * @param ctx the parse tree
	 */
	void exitArrowParameters(@NotNull ECMAScriptParser.ArrowParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#conciseBody}.
	 * @param ctx the parse tree
	 */
	void enterConciseBody(@NotNull ECMAScriptParser.ConciseBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#conciseBody}.
	 * @param ctx the parse tree
	 */
	void exitConciseBody(@NotNull ECMAScriptParser.ConciseBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull ECMAScriptParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull ECMAScriptParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(@NotNull ECMAScriptParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(@NotNull ECMAScriptParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(@NotNull ECMAScriptParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(@NotNull ECMAScriptParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#leftHandSideExpression}.
	 * @param ctx the parse tree
	 */
	void enterLeftHandSideExpression(@NotNull ECMAScriptParser.LeftHandSideExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#leftHandSideExpression}.
	 * @param ctx the parse tree
	 */
	void exitLeftHandSideExpression(@NotNull ECMAScriptParser.LeftHandSideExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallExpression(@NotNull ECMAScriptParser.CallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallExpression(@NotNull ECMAScriptParser.CallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(@NotNull ECMAScriptParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(@NotNull ECMAScriptParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#memberExpression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpression(@NotNull ECMAScriptParser.MemberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#memberExpression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpression(@NotNull ECMAScriptParser.MemberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#functionExpression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpression(@NotNull ECMAScriptParser.FunctionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#functionExpression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpression(@NotNull ECMAScriptParser.FunctionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(@NotNull ECMAScriptParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(@NotNull ECMAScriptParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(@NotNull ECMAScriptParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(@NotNull ECMAScriptParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull ECMAScriptParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull ECMAScriptParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(@NotNull ECMAScriptParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(@NotNull ECMAScriptParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(@NotNull ECMAScriptParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(@NotNull ECMAScriptParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#identifierName}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierName(@NotNull ECMAScriptParser.IdentifierNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#identifierName}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierName(@NotNull ECMAScriptParser.IdentifierNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void enterReservedWord(@NotNull ECMAScriptParser.ReservedWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void exitReservedWord(@NotNull ECMAScriptParser.ReservedWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(@NotNull ECMAScriptParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(@NotNull ECMAScriptParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#futureReservedWord}.
	 * @param ctx the parse tree
	 */
	void enterFutureReservedWord(@NotNull ECMAScriptParser.FutureReservedWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#futureReservedWord}.
	 * @param ctx the parse tree
	 */
	void exitFutureReservedWord(@NotNull ECMAScriptParser.FutureReservedWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#eos}.
	 * @param ctx the parse tree
	 */
	void enterEos(@NotNull ECMAScriptParser.EosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#eos}.
	 * @param ctx the parse tree
	 */
	void exitEos(@NotNull ECMAScriptParser.EosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ECMAScriptParser#eof}.
	 * @param ctx the parse tree
	 */
	void enterEof(@NotNull ECMAScriptParser.EofContext ctx);
	/**
	 * Exit a parse tree produced by {@link ECMAScriptParser#eof}.
	 * @param ctx the parse tree
	 */
	void exitEof(@NotNull ECMAScriptParser.EofContext ctx);
}