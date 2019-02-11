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
	//��ű�����������ֵ��Ӧ��ϵ
	Map<String, Double> memory = new HashMap<String, Double>();
	boolean isIntegerConvertoDouble = false;
	DecimalFormat intdf = new DecimalFormat("#");
	DecimalFormat doubledf = new DecimalFormat("#.####");
	//ID '=' expr NEWLINE
	@Override
	public Double visitAssign(AssignContext ctx) {
		String id = ctx.ID().getText(); //id��'='���
		Double value = visit(ctx.expr());//�����Ҳ���ʽ��ֵ
		memory.put(id, value);//���������ֵ��ӳ���ϵ
		return value;
	}
	//expr NEWLINE
	@Override
	public Double visitPrintExpr(PrintExprContext ctx) {
		// TODO Auto-generated method stub
		Double value = visit(ctx.expr());//����expr�ӽڵ��ֵ
        System.out.println(doubledf.format(value));
		return 0.0;//�����Ѿ���ӡ���������˷���һ����ֵ����
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
		Double left = visit(ctx.expr(0));//��������ӱ��ʽ��ֵ
		Double right = visit(ctx.expr(1));//�����Ҳ��ӱ��ʽ��ֵ
		return (Double) Math.pow(left, right);
	}
	//expr op=('*'|'/') expr
	@Override
	public Double visitMulDiv(MulDivContext ctx) {
		// TODO Auto-generated method stub
		Double left = visit(ctx.expr(0));//��������ӱ��ʽ��ֵ
		Double right = visit(ctx.expr(1));//�����Ҳ��ӱ��ʽ��ֵ
		if(ctx.op.getType() == LabeledExprParser.MUL) return left * right;
		return left / right;
	}
	//expr op=('+'|'-') expr
	@Override
	public Double visitAddSub(AddSubContext ctx) {
		Double left = visit(ctx.expr(0));//��������ӱ��ʽ��ֵ
		Double right = visit(ctx.expr(1));//�����Ҳ��ӱ��ʽ��ֵ
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
		//��+��-
		return -visit(ctx.expr());
	}
}
