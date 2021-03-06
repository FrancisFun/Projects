//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     ANTLR Version: 4.8
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// Generated from CMM.g4 by ANTLR 4.8

// Unreachable code detected
#pragma warning disable 0162
// The variable '...' is assigned but its value is never used
#pragma warning disable 0219
// Missing XML comment for publicly visible type or member '...'
#pragma warning disable 1591
// Ambiguous reference in cref attribute
#pragma warning disable 419

using System;
using System.IO;
using System.Text;
using Antlr4.Runtime;
using Antlr4.Runtime.Atn;
using Antlr4.Runtime.Misc;
using DFA = Antlr4.Runtime.Dfa.DFA;

[System.CodeDom.Compiler.GeneratedCode("ANTLR", "4.8")]
[System.CLSCompliant(false)]
public partial class CMMLexer : Lexer {
	protected static DFA[] decisionToDFA;
	protected static PredictionContextCache sharedContextCache = new PredictionContextCache();
	public const int
		If=1, Else=2, While=3, Do=4, For=5, Return=6, Break=7, Continue=8, Int=9, 
		Real=10, Bool=11, Void=12, Read=13, Write=14, True=15, False=16, Add=17, 
		Sub=18, Mul=19, Div=20, Assign=21, Equal=22, NotEqual=23, Less=24, LessEqual=25, 
		Greater=26, GreaterEqual=27, Not=28, And=29, Or=30, LeftParen=31, RightParen=32, 
		LeftBarce=33, RightBrace=34, LeftBracket=35, RightBracket=36, Semicolon=37, 
		Comma=38, Dot=39, Dash=40, Identifier=41, IntegerLiteral=42, RealLiteral=43, 
		Alpha=44, Digit=45, Whitespce=46, Comment=47;
	public static string[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static string[] modeNames = {
		"DEFAULT_MODE"
	};

	public static readonly string[] ruleNames = {
		"If", "Else", "While", "Do", "For", "Return", "Break", "Continue", "Int", 
		"Real", "Bool", "Void", "Read", "Write", "True", "False", "Add", "Sub", 
		"Mul", "Div", "Assign", "Equal", "NotEqual", "Less", "LessEqual", "Greater", 
		"GreaterEqual", "Not", "And", "Or", "LeftParen", "RightParen", "LeftBarce", 
		"RightBrace", "LeftBracket", "RightBracket", "Semicolon", "Comma", "Dot", 
		"Dash", "Identifier", "IntegerLiteral", "RealLiteral", "Alpha", "Digit", 
		"Whitespce", "Comment"
	};


	public CMMLexer(ICharStream input)
	: this(input, Console.Out, Console.Error) { }

	public CMMLexer(ICharStream input, TextWriter output, TextWriter errorOutput)
	: base(input, output, errorOutput)
	{
		Interpreter = new LexerATNSimulator(this, _ATN, decisionToDFA, sharedContextCache);
	}

	private static readonly string[] _LiteralNames = {
		null, "'if'", "'else'", "'while'", "'do'", "'for'", "'return'", "'break'", 
		"'continue'", "'int'", "'real'", "'bool'", "'void'", "'read'", "'write'", 
		"'true'", "'false'", "'+'", "'-'", "'*'", "'/'", "'='", "'=='", "'<>'", 
		"'<'", "'<='", "'>'", "'>='", "'!'", "'&&'", "'||'", "'('", "')'", "'{'", 
		"'}'", "'['", "']'", "';'", "','", "'.'", "'_'"
	};
	private static readonly string[] _SymbolicNames = {
		null, "If", "Else", "While", "Do", "For", "Return", "Break", "Continue", 
		"Int", "Real", "Bool", "Void", "Read", "Write", "True", "False", "Add", 
		"Sub", "Mul", "Div", "Assign", "Equal", "NotEqual", "Less", "LessEqual", 
		"Greater", "GreaterEqual", "Not", "And", "Or", "LeftParen", "RightParen", 
		"LeftBarce", "RightBrace", "LeftBracket", "RightBracket", "Semicolon", 
		"Comma", "Dot", "Dash", "Identifier", "IntegerLiteral", "RealLiteral", 
		"Alpha", "Digit", "Whitespce", "Comment"
	};
	public static readonly IVocabulary DefaultVocabulary = new Vocabulary(_LiteralNames, _SymbolicNames);

	[NotNull]
	public override IVocabulary Vocabulary
	{
		get
		{
			return DefaultVocabulary;
		}
	}

	public override string GrammarFileName { get { return "CMM.g4"; } }

	public override string[] RuleNames { get { return ruleNames; } }

	public override string[] ChannelNames { get { return channelNames; } }

	public override string[] ModeNames { get { return modeNames; } }

	public override string SerializedAtn { get { return new string(_serializedATN); } }

	static CMMLexer() {
		decisionToDFA = new DFA[_ATN.NumberOfDecisions];
		for (int i = 0; i < _ATN.NumberOfDecisions; i++) {
			decisionToDFA[i] = new DFA(_ATN.GetDecisionState(i), i);
		}
	}
	private static char[] _serializedATN = {
		'\x3', '\x608B', '\xA72A', '\x8133', '\xB9ED', '\x417C', '\x3BE7', '\x7786', 
		'\x5964', '\x2', '\x31', '\x11F', '\b', '\x1', '\x4', '\x2', '\t', '\x2', 
		'\x4', '\x3', '\t', '\x3', '\x4', '\x4', '\t', '\x4', '\x4', '\x5', '\t', 
		'\x5', '\x4', '\x6', '\t', '\x6', '\x4', '\a', '\t', '\a', '\x4', '\b', 
		'\t', '\b', '\x4', '\t', '\t', '\t', '\x4', '\n', '\t', '\n', '\x4', '\v', 
		'\t', '\v', '\x4', '\f', '\t', '\f', '\x4', '\r', '\t', '\r', '\x4', '\xE', 
		'\t', '\xE', '\x4', '\xF', '\t', '\xF', '\x4', '\x10', '\t', '\x10', '\x4', 
		'\x11', '\t', '\x11', '\x4', '\x12', '\t', '\x12', '\x4', '\x13', '\t', 
		'\x13', '\x4', '\x14', '\t', '\x14', '\x4', '\x15', '\t', '\x15', '\x4', 
		'\x16', '\t', '\x16', '\x4', '\x17', '\t', '\x17', '\x4', '\x18', '\t', 
		'\x18', '\x4', '\x19', '\t', '\x19', '\x4', '\x1A', '\t', '\x1A', '\x4', 
		'\x1B', '\t', '\x1B', '\x4', '\x1C', '\t', '\x1C', '\x4', '\x1D', '\t', 
		'\x1D', '\x4', '\x1E', '\t', '\x1E', '\x4', '\x1F', '\t', '\x1F', '\x4', 
		' ', '\t', ' ', '\x4', '!', '\t', '!', '\x4', '\"', '\t', '\"', '\x4', 
		'#', '\t', '#', '\x4', '$', '\t', '$', '\x4', '%', '\t', '%', '\x4', '&', 
		'\t', '&', '\x4', '\'', '\t', '\'', '\x4', '(', '\t', '(', '\x4', ')', 
		'\t', ')', '\x4', '*', '\t', '*', '\x4', '+', '\t', '+', '\x4', ',', '\t', 
		',', '\x4', '-', '\t', '-', '\x4', '.', '\t', '.', '\x4', '/', '\t', '/', 
		'\x4', '\x30', '\t', '\x30', '\x3', '\x2', '\x3', '\x2', '\x3', '\x2', 
		'\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', 
		'\x3', '\x4', '\x3', '\x4', '\x3', '\x4', '\x3', '\x4', '\x3', '\x4', 
		'\x3', '\x4', '\x3', '\x5', '\x3', '\x5', '\x3', '\x5', '\x3', '\x6', 
		'\x3', '\x6', '\x3', '\x6', '\x3', '\x6', '\x3', '\a', '\x3', '\a', '\x3', 
		'\a', '\x3', '\a', '\x3', '\a', '\x3', '\a', '\x3', '\a', '\x3', '\b', 
		'\x3', '\b', '\x3', '\b', '\x3', '\b', '\x3', '\b', '\x3', '\b', '\x3', 
		'\t', '\x3', '\t', '\x3', '\t', '\x3', '\t', '\x3', '\t', '\x3', '\t', 
		'\x3', '\t', '\x3', '\t', '\x3', '\t', '\x3', '\n', '\x3', '\n', '\x3', 
		'\n', '\x3', '\n', '\x3', '\v', '\x3', '\v', '\x3', '\v', '\x3', '\v', 
		'\x3', '\v', '\x3', '\f', '\x3', '\f', '\x3', '\f', '\x3', '\f', '\x3', 
		'\f', '\x3', '\r', '\x3', '\r', '\x3', '\r', '\x3', '\r', '\x3', '\r', 
		'\x3', '\xE', '\x3', '\xE', '\x3', '\xE', '\x3', '\xE', '\x3', '\xE', 
		'\x3', '\xF', '\x3', '\xF', '\x3', '\xF', '\x3', '\xF', '\x3', '\xF', 
		'\x3', '\xF', '\x3', '\x10', '\x3', '\x10', '\x3', '\x10', '\x3', '\x10', 
		'\x3', '\x10', '\x3', '\x11', '\x3', '\x11', '\x3', '\x11', '\x3', '\x11', 
		'\x3', '\x11', '\x3', '\x11', '\x3', '\x12', '\x3', '\x12', '\x3', '\x13', 
		'\x3', '\x13', '\x3', '\x14', '\x3', '\x14', '\x3', '\x15', '\x3', '\x15', 
		'\x3', '\x16', '\x3', '\x16', '\x3', '\x17', '\x3', '\x17', '\x3', '\x17', 
		'\x3', '\x18', '\x3', '\x18', '\x3', '\x18', '\x3', '\x19', '\x3', '\x19', 
		'\x3', '\x1A', '\x3', '\x1A', '\x3', '\x1A', '\x3', '\x1B', '\x3', '\x1B', 
		'\x3', '\x1C', '\x3', '\x1C', '\x3', '\x1C', '\x3', '\x1D', '\x3', '\x1D', 
		'\x3', '\x1E', '\x3', '\x1E', '\x3', '\x1E', '\x3', '\x1F', '\x3', '\x1F', 
		'\x3', '\x1F', '\x3', ' ', '\x3', ' ', '\x3', '!', '\x3', '!', '\x3', 
		'\"', '\x3', '\"', '\x3', '#', '\x3', '#', '\x3', '$', '\x3', '$', '\x3', 
		'%', '\x3', '%', '\x3', '&', '\x3', '&', '\x3', '\'', '\x3', '\'', '\x3', 
		'(', '\x3', '(', '\x3', ')', '\x3', ')', '\x3', '*', '\x3', '*', '\x3', 
		'*', '\x3', '*', '\a', '*', '\xF0', '\n', '*', '\f', '*', '\xE', '*', 
		'\xF3', '\v', '*', '\x3', '*', '\x3', '*', '\x5', '*', '\xF7', '\n', '*', 
		'\x5', '*', '\xF9', '\n', '*', '\x3', '+', '\x3', '+', '\a', '+', '\xFD', 
		'\n', '+', '\f', '+', '\xE', '+', '\x100', '\v', '+', '\x3', ',', '\x3', 
		',', '\x3', ',', '\x5', ',', '\x105', '\n', ',', '\x3', '-', '\x3', '-', 
		'\x3', '.', '\x3', '.', '\x3', '/', '\x6', '/', '\x10C', '\n', '/', '\r', 
		'/', '\xE', '/', '\x10D', '\x3', '/', '\x3', '/', '\x3', '\x30', '\x3', 
		'\x30', '\x3', '\x30', '\x3', '\x30', '\a', '\x30', '\x116', '\n', '\x30', 
		'\f', '\x30', '\xE', '\x30', '\x119', '\v', '\x30', '\x3', '\x30', '\x3', 
		'\x30', '\x3', '\x30', '\x3', '\x30', '\x3', '\x30', '\x3', '\x117', '\x2', 
		'\x31', '\x3', '\x3', '\x5', '\x4', '\a', '\x5', '\t', '\x6', '\v', '\a', 
		'\r', '\b', '\xF', '\t', '\x11', '\n', '\x13', '\v', '\x15', '\f', '\x17', 
		'\r', '\x19', '\xE', '\x1B', '\xF', '\x1D', '\x10', '\x1F', '\x11', '!', 
		'\x12', '#', '\x13', '%', '\x14', '\'', '\x15', ')', '\x16', '+', '\x17', 
		'-', '\x18', '/', '\x19', '\x31', '\x1A', '\x33', '\x1B', '\x35', '\x1C', 
		'\x37', '\x1D', '\x39', '\x1E', ';', '\x1F', '=', ' ', '?', '!', '\x41', 
		'\"', '\x43', '#', '\x45', '$', 'G', '%', 'I', '&', 'K', '\'', 'M', '(', 
		'O', ')', 'Q', '*', 'S', '+', 'U', ',', 'W', '-', 'Y', '.', '[', '/', 
		']', '\x30', '_', '\x31', '\x3', '\x2', '\x5', '\x4', '\x2', '\x43', '\\', 
		'\x63', '|', '\x3', '\x2', '\x32', ';', '\x5', '\x2', '\v', '\f', '\xF', 
		'\xF', '\"', '\"', '\x2', '\x127', '\x2', '\x3', '\x3', '\x2', '\x2', 
		'\x2', '\x2', '\x5', '\x3', '\x2', '\x2', '\x2', '\x2', '\a', '\x3', '\x2', 
		'\x2', '\x2', '\x2', '\t', '\x3', '\x2', '\x2', '\x2', '\x2', '\v', '\x3', 
		'\x2', '\x2', '\x2', '\x2', '\r', '\x3', '\x2', '\x2', '\x2', '\x2', '\xF', 
		'\x3', '\x2', '\x2', '\x2', '\x2', '\x11', '\x3', '\x2', '\x2', '\x2', 
		'\x2', '\x13', '\x3', '\x2', '\x2', '\x2', '\x2', '\x15', '\x3', '\x2', 
		'\x2', '\x2', '\x2', '\x17', '\x3', '\x2', '\x2', '\x2', '\x2', '\x19', 
		'\x3', '\x2', '\x2', '\x2', '\x2', '\x1B', '\x3', '\x2', '\x2', '\x2', 
		'\x2', '\x1D', '\x3', '\x2', '\x2', '\x2', '\x2', '\x1F', '\x3', '\x2', 
		'\x2', '\x2', '\x2', '!', '\x3', '\x2', '\x2', '\x2', '\x2', '#', '\x3', 
		'\x2', '\x2', '\x2', '\x2', '%', '\x3', '\x2', '\x2', '\x2', '\x2', '\'', 
		'\x3', '\x2', '\x2', '\x2', '\x2', ')', '\x3', '\x2', '\x2', '\x2', '\x2', 
		'+', '\x3', '\x2', '\x2', '\x2', '\x2', '-', '\x3', '\x2', '\x2', '\x2', 
		'\x2', '/', '\x3', '\x2', '\x2', '\x2', '\x2', '\x31', '\x3', '\x2', '\x2', 
		'\x2', '\x2', '\x33', '\x3', '\x2', '\x2', '\x2', '\x2', '\x35', '\x3', 
		'\x2', '\x2', '\x2', '\x2', '\x37', '\x3', '\x2', '\x2', '\x2', '\x2', 
		'\x39', '\x3', '\x2', '\x2', '\x2', '\x2', ';', '\x3', '\x2', '\x2', '\x2', 
		'\x2', '=', '\x3', '\x2', '\x2', '\x2', '\x2', '?', '\x3', '\x2', '\x2', 
		'\x2', '\x2', '\x41', '\x3', '\x2', '\x2', '\x2', '\x2', '\x43', '\x3', 
		'\x2', '\x2', '\x2', '\x2', '\x45', '\x3', '\x2', '\x2', '\x2', '\x2', 
		'G', '\x3', '\x2', '\x2', '\x2', '\x2', 'I', '\x3', '\x2', '\x2', '\x2', 
		'\x2', 'K', '\x3', '\x2', '\x2', '\x2', '\x2', 'M', '\x3', '\x2', '\x2', 
		'\x2', '\x2', 'O', '\x3', '\x2', '\x2', '\x2', '\x2', 'Q', '\x3', '\x2', 
		'\x2', '\x2', '\x2', 'S', '\x3', '\x2', '\x2', '\x2', '\x2', 'U', '\x3', 
		'\x2', '\x2', '\x2', '\x2', 'W', '\x3', '\x2', '\x2', '\x2', '\x2', 'Y', 
		'\x3', '\x2', '\x2', '\x2', '\x2', '[', '\x3', '\x2', '\x2', '\x2', '\x2', 
		']', '\x3', '\x2', '\x2', '\x2', '\x2', '_', '\x3', '\x2', '\x2', '\x2', 
		'\x3', '\x61', '\x3', '\x2', '\x2', '\x2', '\x5', '\x64', '\x3', '\x2', 
		'\x2', '\x2', '\a', 'i', '\x3', '\x2', '\x2', '\x2', '\t', 'o', '\x3', 
		'\x2', '\x2', '\x2', '\v', 'r', '\x3', '\x2', '\x2', '\x2', '\r', 'v', 
		'\x3', '\x2', '\x2', '\x2', '\xF', '}', '\x3', '\x2', '\x2', '\x2', '\x11', 
		'\x83', '\x3', '\x2', '\x2', '\x2', '\x13', '\x8C', '\x3', '\x2', '\x2', 
		'\x2', '\x15', '\x90', '\x3', '\x2', '\x2', '\x2', '\x17', '\x95', '\x3', 
		'\x2', '\x2', '\x2', '\x19', '\x9A', '\x3', '\x2', '\x2', '\x2', '\x1B', 
		'\x9F', '\x3', '\x2', '\x2', '\x2', '\x1D', '\xA4', '\x3', '\x2', '\x2', 
		'\x2', '\x1F', '\xAA', '\x3', '\x2', '\x2', '\x2', '!', '\xAF', '\x3', 
		'\x2', '\x2', '\x2', '#', '\xB5', '\x3', '\x2', '\x2', '\x2', '%', '\xB7', 
		'\x3', '\x2', '\x2', '\x2', '\'', '\xB9', '\x3', '\x2', '\x2', '\x2', 
		')', '\xBB', '\x3', '\x2', '\x2', '\x2', '+', '\xBD', '\x3', '\x2', '\x2', 
		'\x2', '-', '\xBF', '\x3', '\x2', '\x2', '\x2', '/', '\xC2', '\x3', '\x2', 
		'\x2', '\x2', '\x31', '\xC5', '\x3', '\x2', '\x2', '\x2', '\x33', '\xC7', 
		'\x3', '\x2', '\x2', '\x2', '\x35', '\xCA', '\x3', '\x2', '\x2', '\x2', 
		'\x37', '\xCC', '\x3', '\x2', '\x2', '\x2', '\x39', '\xCF', '\x3', '\x2', 
		'\x2', '\x2', ';', '\xD1', '\x3', '\x2', '\x2', '\x2', '=', '\xD4', '\x3', 
		'\x2', '\x2', '\x2', '?', '\xD7', '\x3', '\x2', '\x2', '\x2', '\x41', 
		'\xD9', '\x3', '\x2', '\x2', '\x2', '\x43', '\xDB', '\x3', '\x2', '\x2', 
		'\x2', '\x45', '\xDD', '\x3', '\x2', '\x2', '\x2', 'G', '\xDF', '\x3', 
		'\x2', '\x2', '\x2', 'I', '\xE1', '\x3', '\x2', '\x2', '\x2', 'K', '\xE3', 
		'\x3', '\x2', '\x2', '\x2', 'M', '\xE5', '\x3', '\x2', '\x2', '\x2', 'O', 
		'\xE7', '\x3', '\x2', '\x2', '\x2', 'Q', '\xE9', '\x3', '\x2', '\x2', 
		'\x2', 'S', '\xEB', '\x3', '\x2', '\x2', '\x2', 'U', '\xFA', '\x3', '\x2', 
		'\x2', '\x2', 'W', '\x101', '\x3', '\x2', '\x2', '\x2', 'Y', '\x106', 
		'\x3', '\x2', '\x2', '\x2', '[', '\x108', '\x3', '\x2', '\x2', '\x2', 
		']', '\x10B', '\x3', '\x2', '\x2', '\x2', '_', '\x111', '\x3', '\x2', 
		'\x2', '\x2', '\x61', '\x62', '\a', 'k', '\x2', '\x2', '\x62', '\x63', 
		'\a', 'h', '\x2', '\x2', '\x63', '\x4', '\x3', '\x2', '\x2', '\x2', '\x64', 
		'\x65', '\a', 'g', '\x2', '\x2', '\x65', '\x66', '\a', 'n', '\x2', '\x2', 
		'\x66', 'g', '\a', 'u', '\x2', '\x2', 'g', 'h', '\a', 'g', '\x2', '\x2', 
		'h', '\x6', '\x3', '\x2', '\x2', '\x2', 'i', 'j', '\a', 'y', '\x2', '\x2', 
		'j', 'k', '\a', 'j', '\x2', '\x2', 'k', 'l', '\a', 'k', '\x2', '\x2', 
		'l', 'm', '\a', 'n', '\x2', '\x2', 'm', 'n', '\a', 'g', '\x2', '\x2', 
		'n', '\b', '\x3', '\x2', '\x2', '\x2', 'o', 'p', '\a', '\x66', '\x2', 
		'\x2', 'p', 'q', '\a', 'q', '\x2', '\x2', 'q', '\n', '\x3', '\x2', '\x2', 
		'\x2', 'r', 's', '\a', 'h', '\x2', '\x2', 's', 't', '\a', 'q', '\x2', 
		'\x2', 't', 'u', '\a', 't', '\x2', '\x2', 'u', '\f', '\x3', '\x2', '\x2', 
		'\x2', 'v', 'w', '\a', 't', '\x2', '\x2', 'w', 'x', '\a', 'g', '\x2', 
		'\x2', 'x', 'y', '\a', 'v', '\x2', '\x2', 'y', 'z', '\a', 'w', '\x2', 
		'\x2', 'z', '{', '\a', 't', '\x2', '\x2', '{', '|', '\a', 'p', '\x2', 
		'\x2', '|', '\xE', '\x3', '\x2', '\x2', '\x2', '}', '~', '\a', '\x64', 
		'\x2', '\x2', '~', '\x7F', '\a', 't', '\x2', '\x2', '\x7F', '\x80', '\a', 
		'g', '\x2', '\x2', '\x80', '\x81', '\a', '\x63', '\x2', '\x2', '\x81', 
		'\x82', '\a', 'm', '\x2', '\x2', '\x82', '\x10', '\x3', '\x2', '\x2', 
		'\x2', '\x83', '\x84', '\a', '\x65', '\x2', '\x2', '\x84', '\x85', '\a', 
		'q', '\x2', '\x2', '\x85', '\x86', '\a', 'p', '\x2', '\x2', '\x86', '\x87', 
		'\a', 'v', '\x2', '\x2', '\x87', '\x88', '\a', 'k', '\x2', '\x2', '\x88', 
		'\x89', '\a', 'p', '\x2', '\x2', '\x89', '\x8A', '\a', 'w', '\x2', '\x2', 
		'\x8A', '\x8B', '\a', 'g', '\x2', '\x2', '\x8B', '\x12', '\x3', '\x2', 
		'\x2', '\x2', '\x8C', '\x8D', '\a', 'k', '\x2', '\x2', '\x8D', '\x8E', 
		'\a', 'p', '\x2', '\x2', '\x8E', '\x8F', '\a', 'v', '\x2', '\x2', '\x8F', 
		'\x14', '\x3', '\x2', '\x2', '\x2', '\x90', '\x91', '\a', 't', '\x2', 
		'\x2', '\x91', '\x92', '\a', 'g', '\x2', '\x2', '\x92', '\x93', '\a', 
		'\x63', '\x2', '\x2', '\x93', '\x94', '\a', 'n', '\x2', '\x2', '\x94', 
		'\x16', '\x3', '\x2', '\x2', '\x2', '\x95', '\x96', '\a', '\x64', '\x2', 
		'\x2', '\x96', '\x97', '\a', 'q', '\x2', '\x2', '\x97', '\x98', '\a', 
		'q', '\x2', '\x2', '\x98', '\x99', '\a', 'n', '\x2', '\x2', '\x99', '\x18', 
		'\x3', '\x2', '\x2', '\x2', '\x9A', '\x9B', '\a', 'x', '\x2', '\x2', '\x9B', 
		'\x9C', '\a', 'q', '\x2', '\x2', '\x9C', '\x9D', '\a', 'k', '\x2', '\x2', 
		'\x9D', '\x9E', '\a', '\x66', '\x2', '\x2', '\x9E', '\x1A', '\x3', '\x2', 
		'\x2', '\x2', '\x9F', '\xA0', '\a', 't', '\x2', '\x2', '\xA0', '\xA1', 
		'\a', 'g', '\x2', '\x2', '\xA1', '\xA2', '\a', '\x63', '\x2', '\x2', '\xA2', 
		'\xA3', '\a', '\x66', '\x2', '\x2', '\xA3', '\x1C', '\x3', '\x2', '\x2', 
		'\x2', '\xA4', '\xA5', '\a', 'y', '\x2', '\x2', '\xA5', '\xA6', '\a', 
		't', '\x2', '\x2', '\xA6', '\xA7', '\a', 'k', '\x2', '\x2', '\xA7', '\xA8', 
		'\a', 'v', '\x2', '\x2', '\xA8', '\xA9', '\a', 'g', '\x2', '\x2', '\xA9', 
		'\x1E', '\x3', '\x2', '\x2', '\x2', '\xAA', '\xAB', '\a', 'v', '\x2', 
		'\x2', '\xAB', '\xAC', '\a', 't', '\x2', '\x2', '\xAC', '\xAD', '\a', 
		'w', '\x2', '\x2', '\xAD', '\xAE', '\a', 'g', '\x2', '\x2', '\xAE', ' ', 
		'\x3', '\x2', '\x2', '\x2', '\xAF', '\xB0', '\a', 'h', '\x2', '\x2', '\xB0', 
		'\xB1', '\a', '\x63', '\x2', '\x2', '\xB1', '\xB2', '\a', 'n', '\x2', 
		'\x2', '\xB2', '\xB3', '\a', 'u', '\x2', '\x2', '\xB3', '\xB4', '\a', 
		'g', '\x2', '\x2', '\xB4', '\"', '\x3', '\x2', '\x2', '\x2', '\xB5', '\xB6', 
		'\a', '-', '\x2', '\x2', '\xB6', '$', '\x3', '\x2', '\x2', '\x2', '\xB7', 
		'\xB8', '\a', '/', '\x2', '\x2', '\xB8', '&', '\x3', '\x2', '\x2', '\x2', 
		'\xB9', '\xBA', '\a', ',', '\x2', '\x2', '\xBA', '(', '\x3', '\x2', '\x2', 
		'\x2', '\xBB', '\xBC', '\a', '\x31', '\x2', '\x2', '\xBC', '*', '\x3', 
		'\x2', '\x2', '\x2', '\xBD', '\xBE', '\a', '?', '\x2', '\x2', '\xBE', 
		',', '\x3', '\x2', '\x2', '\x2', '\xBF', '\xC0', '\a', '?', '\x2', '\x2', 
		'\xC0', '\xC1', '\a', '?', '\x2', '\x2', '\xC1', '.', '\x3', '\x2', '\x2', 
		'\x2', '\xC2', '\xC3', '\a', '>', '\x2', '\x2', '\xC3', '\xC4', '\a', 
		'@', '\x2', '\x2', '\xC4', '\x30', '\x3', '\x2', '\x2', '\x2', '\xC5', 
		'\xC6', '\a', '>', '\x2', '\x2', '\xC6', '\x32', '\x3', '\x2', '\x2', 
		'\x2', '\xC7', '\xC8', '\a', '>', '\x2', '\x2', '\xC8', '\xC9', '\a', 
		'?', '\x2', '\x2', '\xC9', '\x34', '\x3', '\x2', '\x2', '\x2', '\xCA', 
		'\xCB', '\a', '@', '\x2', '\x2', '\xCB', '\x36', '\x3', '\x2', '\x2', 
		'\x2', '\xCC', '\xCD', '\a', '@', '\x2', '\x2', '\xCD', '\xCE', '\a', 
		'?', '\x2', '\x2', '\xCE', '\x38', '\x3', '\x2', '\x2', '\x2', '\xCF', 
		'\xD0', '\a', '#', '\x2', '\x2', '\xD0', ':', '\x3', '\x2', '\x2', '\x2', 
		'\xD1', '\xD2', '\a', '(', '\x2', '\x2', '\xD2', '\xD3', '\a', '(', '\x2', 
		'\x2', '\xD3', '<', '\x3', '\x2', '\x2', '\x2', '\xD4', '\xD5', '\a', 
		'~', '\x2', '\x2', '\xD5', '\xD6', '\a', '~', '\x2', '\x2', '\xD6', '>', 
		'\x3', '\x2', '\x2', '\x2', '\xD7', '\xD8', '\a', '*', '\x2', '\x2', '\xD8', 
		'@', '\x3', '\x2', '\x2', '\x2', '\xD9', '\xDA', '\a', '+', '\x2', '\x2', 
		'\xDA', '\x42', '\x3', '\x2', '\x2', '\x2', '\xDB', '\xDC', '\a', '}', 
		'\x2', '\x2', '\xDC', '\x44', '\x3', '\x2', '\x2', '\x2', '\xDD', '\xDE', 
		'\a', '\x7F', '\x2', '\x2', '\xDE', '\x46', '\x3', '\x2', '\x2', '\x2', 
		'\xDF', '\xE0', '\a', ']', '\x2', '\x2', '\xE0', 'H', '\x3', '\x2', '\x2', 
		'\x2', '\xE1', '\xE2', '\a', '_', '\x2', '\x2', '\xE2', 'J', '\x3', '\x2', 
		'\x2', '\x2', '\xE3', '\xE4', '\a', '=', '\x2', '\x2', '\xE4', 'L', '\x3', 
		'\x2', '\x2', '\x2', '\xE5', '\xE6', '\a', '.', '\x2', '\x2', '\xE6', 
		'N', '\x3', '\x2', '\x2', '\x2', '\xE7', '\xE8', '\a', '\x30', '\x2', 
		'\x2', '\xE8', 'P', '\x3', '\x2', '\x2', '\x2', '\xE9', '\xEA', '\a', 
		'\x61', '\x2', '\x2', '\xEA', 'R', '\x3', '\x2', '\x2', '\x2', '\xEB', 
		'\xF8', '\x5', 'Y', '-', '\x2', '\xEC', '\xF0', '\x5', '[', '.', '\x2', 
		'\xED', '\xF0', '\x5', 'Y', '-', '\x2', '\xEE', '\xF0', '\a', '\x61', 
		'\x2', '\x2', '\xEF', '\xEC', '\x3', '\x2', '\x2', '\x2', '\xEF', '\xED', 
		'\x3', '\x2', '\x2', '\x2', '\xEF', '\xEE', '\x3', '\x2', '\x2', '\x2', 
		'\xF0', '\xF3', '\x3', '\x2', '\x2', '\x2', '\xF1', '\xEF', '\x3', '\x2', 
		'\x2', '\x2', '\xF1', '\xF2', '\x3', '\x2', '\x2', '\x2', '\xF2', '\xF6', 
		'\x3', '\x2', '\x2', '\x2', '\xF3', '\xF1', '\x3', '\x2', '\x2', '\x2', 
		'\xF4', '\xF7', '\x5', '[', '.', '\x2', '\xF5', '\xF7', '\x5', 'Y', '-', 
		'\x2', '\xF6', '\xF4', '\x3', '\x2', '\x2', '\x2', '\xF6', '\xF5', '\x3', 
		'\x2', '\x2', '\x2', '\xF7', '\xF9', '\x3', '\x2', '\x2', '\x2', '\xF8', 
		'\xF1', '\x3', '\x2', '\x2', '\x2', '\xF8', '\xF9', '\x3', '\x2', '\x2', 
		'\x2', '\xF9', 'T', '\x3', '\x2', '\x2', '\x2', '\xFA', '\xFE', '\x5', 
		'[', '.', '\x2', '\xFB', '\xFD', '\x5', '[', '.', '\x2', '\xFC', '\xFB', 
		'\x3', '\x2', '\x2', '\x2', '\xFD', '\x100', '\x3', '\x2', '\x2', '\x2', 
		'\xFE', '\xFC', '\x3', '\x2', '\x2', '\x2', '\xFE', '\xFF', '\x3', '\x2', 
		'\x2', '\x2', '\xFF', 'V', '\x3', '\x2', '\x2', '\x2', '\x100', '\xFE', 
		'\x3', '\x2', '\x2', '\x2', '\x101', '\x104', '\x5', 'U', '+', '\x2', 
		'\x102', '\x103', '\a', '\x30', '\x2', '\x2', '\x103', '\x105', '\x5', 
		'U', '+', '\x2', '\x104', '\x102', '\x3', '\x2', '\x2', '\x2', '\x104', 
		'\x105', '\x3', '\x2', '\x2', '\x2', '\x105', 'X', '\x3', '\x2', '\x2', 
		'\x2', '\x106', '\x107', '\t', '\x2', '\x2', '\x2', '\x107', 'Z', '\x3', 
		'\x2', '\x2', '\x2', '\x108', '\x109', '\t', '\x3', '\x2', '\x2', '\x109', 
		'\\', '\x3', '\x2', '\x2', '\x2', '\x10A', '\x10C', '\t', '\x4', '\x2', 
		'\x2', '\x10B', '\x10A', '\x3', '\x2', '\x2', '\x2', '\x10C', '\x10D', 
		'\x3', '\x2', '\x2', '\x2', '\x10D', '\x10B', '\x3', '\x2', '\x2', '\x2', 
		'\x10D', '\x10E', '\x3', '\x2', '\x2', '\x2', '\x10E', '\x10F', '\x3', 
		'\x2', '\x2', '\x2', '\x10F', '\x110', '\b', '/', '\x2', '\x2', '\x110', 
		'^', '\x3', '\x2', '\x2', '\x2', '\x111', '\x112', '\a', '\x31', '\x2', 
		'\x2', '\x112', '\x113', '\a', ',', '\x2', '\x2', '\x113', '\x117', '\x3', 
		'\x2', '\x2', '\x2', '\x114', '\x116', '\v', '\x2', '\x2', '\x2', '\x115', 
		'\x114', '\x3', '\x2', '\x2', '\x2', '\x116', '\x119', '\x3', '\x2', '\x2', 
		'\x2', '\x117', '\x118', '\x3', '\x2', '\x2', '\x2', '\x117', '\x115', 
		'\x3', '\x2', '\x2', '\x2', '\x118', '\x11A', '\x3', '\x2', '\x2', '\x2', 
		'\x119', '\x117', '\x3', '\x2', '\x2', '\x2', '\x11A', '\x11B', '\a', 
		',', '\x2', '\x2', '\x11B', '\x11C', '\a', '\x31', '\x2', '\x2', '\x11C', 
		'\x11D', '\x3', '\x2', '\x2', '\x2', '\x11D', '\x11E', '\b', '\x30', '\x2', 
		'\x2', '\x11E', '`', '\x3', '\x2', '\x2', '\x2', '\v', '\x2', '\xEF', 
		'\xF1', '\xF6', '\xF8', '\xFE', '\x104', '\x10D', '\x117', '\x3', '\b', 
		'\x2', '\x2',
	};

	public static readonly ATN _ATN =
		new ATNDeserializer().Deserialize(_serializedATN);


}
