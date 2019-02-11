package com.antlr4.dongjicheng.labeledexpr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;



public class Calc2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String inputFile = null;
		if(args.length > 0) inputFile = args[0];
		InputStream is = System.in;
		if(inputFile != null){
			is = new FileInputStream(inputFile);
		}
		ANTLRInputStream input = new ANTLRInputStream(is);
		LabeledExprLexer lexer = new LabeledExprLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LabeledExprParser parser = new LabeledExprParser(tokens);
		ParseTree tree = parser.prog();
		System.out.println(tree.toStringTree(parser));
		EvalVisitor eval = new EvalVisitor();
		eval.visit(tree);
		Trees.inspect(tree, parser);
	}

}
