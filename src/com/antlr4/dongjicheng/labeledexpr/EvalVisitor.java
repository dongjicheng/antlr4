package com.antlr4.dongjicheng.labeledexpr;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.AddSubContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.AssignContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.IdContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.MulDivContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.NumberContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.ParensContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.PowerContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.PrintContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.PrintExprContext;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser.SignContext;

public class EvalVisitor extends LabeledExprBaseVisitor<Double>{
	//存放变量名跟变量值对应关系
	Map<String, Double> memory = new HashMap<String, Double>();
	boolean isIntegerConvertoDouble = false;
	DecimalFormat intdf = new DecimalFormat("#");
	DecimalFormat doubledf = new DecimalFormat("#.####");
	//ID '=' expr NEWLINE
	@Override
	public Double visitAssign(AssignContext ctx) {
		String id = ctx.ID().getText(); //id在'='左侧
		Double value = visit(ctx.expr());//计算右侧表达式的值
		memory.put(id, value);//保存变量跟值得映射关系
		return value;
	}
	//expr NEWLINE
	@Override
	public Double visitPrintExpr(PrintExprContext ctx) {
		// TODO Auto-generated method stub
		Double value = visit(ctx.expr());//计算expr子节点的值
        System.out.println(doubledf.format(value));
		return 0.0;//上面已经打印出结果，因此返回一个假值即可
	}
	@Override
	public Double visitPrint(PrintContext ctx) {
		// TODO Auto-generated method stub
		Double value = visit(ctx.expr());
        System.out.println(doubledf.format(value));
        return value;
	}
	//Number
	@Override
	public Double visitNumber(NumberContext ctx) {
		if(!isIntegerConvertoDouble
				&&ctx.NUMBER().getText().contains(".")) isIntegerConvertoDouble = true;
		return Double.parseDouble(ctx.NUMBER().getText());
	}
	//ID
	@Override
	public Double visitId(IdContext ctx) {
		// TODO Auto-generated method stub
		String id = ctx.ID().getText();
		if(memory.containsKey(id))return memory.get(id);
		return 0d;
	}
	//expr '**' expr
	@Override
	public Double visitPower(PowerContext ctx) {
		// TODO Auto-generated method stub
		Double left = visit(ctx.expr(0));//计算左侧子表达式的值
		Double right = visit(ctx.expr(1));//计算右侧子表达式的值
		return (Double) Math.pow(left, right);
	}
	//expr op=('*'|'/') expr
	@Override
	public Double visitMulDiv(MulDivContext ctx) {
		// TODO Auto-generated method stub
		Double left = visit(ctx.expr(0));//计算左侧子表达式的值
		Double right = visit(ctx.expr(1));//计算右侧子表达式的值
		if(ctx.op.getType() == LabeledExprParser.MUL) return left * right;
		return left / right;
	}
	//expr op=('+'|'-') expr
	@Override
	public Double visitAddSub(AddSubContext ctx) {
		Double left = visit(ctx.expr(0));//计算左侧子表达式的值
		Double right = visit(ctx.expr(1));//计算右侧子表达式的值
		if(ctx.op.getType() == LabeledExprParser.ADD) return left + right;
		return left - right;
	}
	//'(' expr ')'
	@Override
	public Double visitParens(ParensContext ctx) {
		// TODO Auto-generated method stub
		return visit(ctx.expr());
	}
	//sn=('+'|'-') expr
	@Override
	public Double visitSign(SignContext ctx) {
		// TODO Auto-generated method stub
		if(ctx.sn.getType() == LabeledExprParser.ADD) return visit(ctx.expr());
		//非+即-
		return -visit(ctx.expr());
	}
}
