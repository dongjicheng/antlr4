package com.antlr4.dongjicheng.hello;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.antlr4.dongjicheng.hello.HelloLexer;
import com.antlr4.dongjicheng.labeledexpr.EvalVisitor;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprLexer;
import com.antlr4.dongjicheng.labeledexpr.LabeledExprParser;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String inputFile = null;
		if(args.length > 0) inputFile = args[0];
		InputStream is = System.in;
		if(inputFile != null){
			is = new FileInputStream(inputFile);
		}
		ANTLRInputStream input = new ANTLRInputStream(is);
		HelloLexer lexer = new HelloLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LabeledExprParser parser = new LabeledExprParser(tokens);
		ParseTree tree = parser.prog();
		EvalVisitor eval = new EvalVisitor();
		eval.visit(tree);
		DecimalFormat doubledf = new DecimalFormat("#.####");
		System.out.println(doubledf.format(2.0000));
	}

}
