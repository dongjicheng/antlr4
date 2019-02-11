package com.antlr4.dongjicheng.expr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.antlr4.dongjicheng.expr.LibExprLexer;
import com.antlr4.dongjicheng.expr.LibExprParser;

public class ExprJoyRide {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String inputFile = null;
		if(args.length > 0) inputFile = args[0];
		InputStream is = System.in;
		if(inputFile != null){
			is = new FileInputStream(inputFile);
		}
		ANTLRInputStream input = new ANTLRInputStream(is);
		LibExprLexer lexer = new LibExprLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LibExprParser parser = new LibExprParser(tokens);
		ParseTree tree = parser.prog();
		System.out.println(tree.toStringTree(parser));
		Trees.inspect(tree, parser);
	}

}
