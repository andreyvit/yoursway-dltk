// created by jay 1.1.0 (c) 2002-2006 ats@cs.rit.edu
// skeleton Java tables 1.1.0 (c) 2002-2006 ats@cs.rit.edu

					// line 1 "DefaultRubyParser.y"

/***** BEGIN LICENSE BLOCK *****
 * Version: CPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Common Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2001 Alan Moore <alan_moore@gmx.net>
 * Copyright (C) 2001-2002 Benoit Cerrina <b.cerrina@wanadoo.fr>
 * Copyright (C) 2001-2004 Stefan Matthias Aust <sma@3plus4.de>
 * Copyright (C) 2001-2004 Jan Arne Petersen <jpetersen@uni-bonn.de>
 * Copyright (C) 2002-2004 Anders Bengtsson <ndrsbngtssn@yahoo.se>
 * Copyright (C) 2004 Thomas E Enebo <enebo@acm.org>
 * Copyright (C) 2004 Charles O Nutter <headius@headius.com>
 * Copyright (C) 2006 Miguel Covarrubias <mlcovarrubias@gmail.com>
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the CPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the CPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/
package org.jruby.parser;

import java.io.IOException;
import java.math.BigInteger;

import org.jruby.ast.*;
import org.jruby.ast.types.*;
import org.jruby.ast.types.*;
import org.jruby.common.IRubyWarnings;
import org.jruby.lexer.yacc.*;
import org.jruby.runtime.Visibility;
import org.jruby.util.IdUtil;

public class DefaultRubyParser {
    private ParserSupport support;
    protected RubyYaccLexer lexer;
    protected IRubyWarnings warnings;

    public DefaultRubyParser() {
        support = new ParserSupport();
        lexer = new RubyYaccLexer();
        lexer.setParserSupport(support);
    }

    public void setWarnings(IRubyWarnings warnings) {
        this.warnings = warnings;

        support.setWarnings(warnings);
        lexer.setWarnings(warnings);
    }

/*
%union {
    Node *node;
    VALUE val;
    ID id;
    int num;
    struct RVarmap *vars;
}
*/
					// line 81 "-"
  // %token constants
  public static final int kCLASS = 257;
  public static final int kMODULE = 258;
  public static final int kDEF = 259;
  public static final int kUNDEF = 260;
  public static final int kBEGIN = 261;
  public static final int kRESCUE = 262;
  public static final int kENSURE = 263;
  public static final int kEND = 264;
  public static final int kIF = 265;
  public static final int kUNLESS = 266;
  public static final int kTHEN = 267;
  public static final int kELSIF = 268;
  public static final int kELSE = 269;
  public static final int kCASE = 270;
  public static final int kWHEN = 271;
  public static final int kWHILE = 272;
  public static final int kUNTIL = 273;
  public static final int kFOR = 274;
  public static final int kBREAK = 275;
  public static final int kNEXT = 276;
  public static final int kREDO = 277;
  public static final int kRETRY = 278;
  public static final int kIN = 279;
  public static final int kDO = 280;
  public static final int kDO_COND = 281;
  public static final int kDO_BLOCK = 282;
  public static final int kRETURN = 283;
  public static final int kYIELD = 284;
  public static final int kSUPER = 285;
  public static final int kSELF = 286;
  public static final int kNIL = 287;
  public static final int kTRUE = 288;
  public static final int kFALSE = 289;
  public static final int kAND = 290;
  public static final int kOR = 291;
  public static final int kNOT = 292;
  public static final int kIF_MOD = 293;
  public static final int kUNLESS_MOD = 294;
  public static final int kWHILE_MOD = 295;
  public static final int kUNTIL_MOD = 296;
  public static final int kRESCUE_MOD = 297;
  public static final int kALIAS = 298;
  public static final int kDEFINED = 299;
  public static final int klBEGIN = 300;
  public static final int klEND = 301;
  public static final int k__LINE__ = 302;
  public static final int k__FILE__ = 303;
  public static final int tIDENTIFIER = 304;
  public static final int tFID = 305;
  public static final int tGVAR = 306;
  public static final int tIVAR = 307;
  public static final int tCONSTANT = 308;
  public static final int tCVAR = 309;
  public static final int tSTRING_CONTENT = 310;
  public static final int tINTEGER = 311;
  public static final int tFLOAT = 312;
  public static final int tNTH_REF = 313;
  public static final int tBACK_REF = 314;
  public static final int tREGEXP_END = 315;
  public static final int tUPLUS = 316;
  public static final int tUMINUS = 317;
  public static final int tUMINUS_NUM = 318;
  public static final int tPOW = 319;
  public static final int tCMP = 320;
  public static final int tEQ = 321;
  public static final int tEQQ = 322;
  public static final int tNEQ = 323;
  public static final int tGEQ = 324;
  public static final int tLEQ = 325;
  public static final int tANDOP = 326;
  public static final int tOROP = 327;
  public static final int tMATCH = 328;
  public static final int tNMATCH = 329;
  public static final int tDOT = 330;
  public static final int tDOT2 = 331;
  public static final int tDOT3 = 332;
  public static final int tAREF = 333;
  public static final int tASET = 334;
  public static final int tLSHFT = 335;
  public static final int tRSHFT = 336;
  public static final int tCOLON2 = 337;
  public static final int tCOLON3 = 338;
  public static final int tOP_ASGN = 339;
  public static final int tASSOC = 340;
  public static final int tLPAREN = 341;
  public static final int tLPAREN2 = 342;
  public static final int tLPAREN_ARG = 343;
  public static final int tLBRACK = 344;
  public static final int tLBRACE = 345;
  public static final int tLBRACE_ARG = 346;
  public static final int tSTAR = 347;
  public static final int tSTAR2 = 348;
  public static final int tAMPER = 349;
  public static final int tAMPER2 = 350;
  public static final int tTILDE = 351;
  public static final int tPERCENT = 352;
  public static final int tDIVIDE = 353;
  public static final int tPLUS = 354;
  public static final int tMINUS = 355;
  public static final int tLT = 356;
  public static final int tGT = 357;
  public static final int tPIPE = 358;
  public static final int tBANG = 359;
  public static final int tCARET = 360;
  public static final int tLCURLY = 361;
  public static final int tBACK_REF2 = 362;
  public static final int tSYMBEG = 363;
  public static final int tSTRING_BEG = 364;
  public static final int tXSTRING_BEG = 365;
  public static final int tREGEXP_BEG = 366;
  public static final int tWORDS_BEG = 367;
  public static final int tQWORDS_BEG = 368;
  public static final int tSTRING_DBEG = 369;
  public static final int tSTRING_DVAR = 370;
  public static final int tSTRING_END = 371;
  public static final int tLOWEST = 372;
  public static final int tLAST_TOKEN = 373;
  public static final int yyErrorCode = 256;

  /** number of final state.
    */
  protected static final int yyFinal = 1;

  /** parser tables.
      Cannot be <tt>final</tt> because they are loaded from a resource.
      Order is mandated by <i>jay</i>.
    */
  protected static short[] yyLhs, yyLen, yyDefRed, yyDgoto,
    yySindex, yyRindex, yyGindex, yyTable, yyCheck;

//yyLhs 498
//yy    -1,   100,     0,    18,    17,    19,    19,    19,    19,   103,
//yy    20,    20,    20,    20,    20,    20,    20,    20,    20,    20,
//yy   104,    20,    20,    20,    20,    20,    20,    20,    20,    20,
//yy    20,    20,    20,    20,    20,    20,    21,    21,    21,    21,
//yy    21,    21,    29,    25,    25,    25,    25,    25,    48,    48,
//yy    48,   105,    63,    24,    24,    24,    24,    24,    24,    24,
//yy    24,    69,    69,    71,    71,    70,    70,    70,    70,    70,
//yy    70,    65,    65,    74,    74,    66,    66,    66,    66,    66,
//yy    66,    66,    66,    59,    59,    59,    59,    59,    59,    59,
//yy    59,    91,    91,    16,    16,    16,    92,    92,    92,    92,
//yy    92,    85,    85,    54,   107,    54,    93,    93,    93,    93,
//yy    93,    93,    93,    93,    93,    93,    93,    93,    93,    93,
//yy    93,    93,    93,    93,    93,    93,    93,    93,    93,    93,
//yy    93,    93,   106,   106,   106,   106,   106,   106,   106,   106,
//yy   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,
//yy   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,
//yy   106,   106,   106,   106,   106,   106,   106,   106,   106,   106,
//yy   106,   106,   106,    22,    22,    22,    22,    22,    22,    22,
//yy    22,    22,    22,    22,    22,    22,    22,    22,    22,    22,
//yy    22,    22,    22,    22,    22,    22,    22,    22,    22,    22,
//yy    22,    22,    22,    22,    22,    22,    22,    22,    22,    22,
//yy    22,    22,    22,    22,   109,    22,    22,    22,    67,    78,
//yy    78,    78,    78,    78,    78,    36,    36,    36,    36,    37,
//yy    37,    38,    38,    38,    38,    38,    38,    38,    38,    38,
//yy    39,    39,    39,    39,    39,    39,    39,    39,    39,    39,
//yy    39,    39,   111,    41,    40,   112,    40,   113,    40,    44,
//yy    43,    43,    72,    72,    64,    64,    64,    23,    23,    23,
//yy    23,    23,    23,    23,    23,    23,    23,   114,    23,    23,
//yy    23,    23,    23,    23,    23,    23,    23,    23,    23,   115,
//yy    23,    23,    23,    23,    23,    23,   117,   119,    23,   120,
//yy   121,    23,    23,    23,    23,   122,   123,    23,   124,    23,
//yy   126,   127,    23,   128,    23,   129,    23,   130,   131,    23,
//yy    23,    23,    23,    23,    30,   116,   116,   116,   116,   118,
//yy   118,   118,    33,    33,    31,    31,    57,    57,    58,    58,
//yy    58,    58,   132,    62,    47,    47,    47,    26,    26,    26,
//yy    26,    26,    26,   133,    61,   134,    61,    68,    73,    73,
//yy    73,    32,    32,    79,    79,    77,    77,    77,    34,    34,
//yy    35,    35,    13,    13,    13,     2,     3,     3,     4,     5,
//yy     6,    10,    10,    28,    28,    12,    12,    11,    11,    27,
//yy    27,     7,     7,     8,     8,     9,   135,     9,   136,     9,
//yy    55,    55,    55,    55,    87,    86,    86,    86,    86,    15,
//yy    14,    14,    14,    14,    80,    80,    80,    80,    80,    80,
//yy    80,    80,    80,    80,    80,    42,    81,    56,    56,    46,
//yy   137,    46,    46,    51,    51,    52,    52,    52,    52,    52,
//yy    52,    52,    52,    52,    94,    94,    94,    94,    96,    96,
//yy    53,    84,    84,    98,    98,    95,    95,    99,    99,    50,
//yy    49,    49,     1,   138,     1,    83,    83,    83,    75,    75,
//yy    75,    76,    88,    88,    88,    89,    89,    89,    89,    90,
//yy    90,    90,    97,    97,   101,   101,   108,   108,   110,   110,
//yy   110,   125,   125,   102,   102,    60,    82,    45,
//yyLen 498
//yy     2,     0,     2,     4,     2,     1,     1,     3,     2,     0,
//yy     4,     3,     3,     3,     2,     3,     3,     3,     3,     3,
//yy     0,     5,     4,     3,     3,     3,     6,     5,     5,     5,
//yy     3,     3,     3,     3,     1,     1,     1,     3,     3,     2,
//yy     2,     1,     1,     1,     1,     2,     2,     2,     1,     4,
//yy     4,     0,     5,     2,     3,     4,     5,     4,     5,     2,
//yy     2,     1,     3,     1,     3,     1,     2,     3,     2,     2,
//yy     1,     1,     3,     2,     3,     1,     4,     3,     3,     3,
//yy     3,     2,     1,     1,     4,     3,     3,     3,     3,     2,
//yy     1,     1,     1,     2,     1,     3,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     0,     4,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     3,     5,     3,     6,     5,     5,     5,
//yy     5,     4,     3,     3,     3,     3,     3,     3,     3,     3,
//yy     3,     4,     4,     2,     2,     3,     3,     3,     3,     3,
//yy     3,     3,     3,     3,     3,     3,     3,     3,     2,     2,
//yy     3,     3,     3,     3,     0,     4,     5,     1,     1,     1,
//yy     2,     2,     5,     2,     3,     3,     4,     4,     6,     1,
//yy     1,     1,     2,     5,     2,     5,     4,     7,     3,     1,
//yy     4,     3,     5,     7,     2,     5,     4,     6,     7,     9,
//yy     3,     1,     0,     2,     1,     0,     3,     0,     4,     2,
//yy     2,     1,     1,     3,     3,     4,     2,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     3,     0,     5,     3,
//yy     3,     2,     4,     3,     3,     1,     4,     3,     1,     0,
//yy     6,     2,     1,     2,     6,     6,     0,     0,     7,     0,
//yy     0,     7,     5,     4,     5,     0,     0,     9,     0,     6,
//yy     0,     0,     8,     0,     5,     0,     6,     0,     0,     9,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     2,     1,
//yy     1,     1,     1,     5,     1,     2,     1,     1,     1,     2,
//yy     1,     3,     0,     5,     2,     4,     4,     2,     4,     4,
//yy     3,     2,     1,     0,     5,     0,     5,     5,     1,     4,
//yy     2,     1,     1,     6,     0,     1,     1,     1,     2,     1,
//yy     2,     1,     1,     1,     1,     1,     1,     2,     3,     3,
//yy     3,     3,     3,     0,     3,     1,     2,     3,     3,     0,
//yy     3,     0,     2,     0,     2,     1,     0,     3,     0,     4,
//yy     1,     1,     1,     1,     2,     1,     1,     1,     1,     3,
//yy     1,     1,     2,     2,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     0,     4,     2,     4,     2,     6,     4,     4,     2,     4,
//yy     2,     2,     1,     0,     1,     1,     1,     1,     1,     3,
//yy     3,     1,     3,     1,     1,     2,     1,     1,     1,     2,
//yy     2,     0,     1,     0,     5,     1,     2,     2,     1,     3,
//yy     3,     3,     1,     1,     1,     1,     1,     1,     1,     1,
//yy     1,     1,     1,     1,     0,     1,     0,     1,     0,     1,
//yy     1,     1,     1,     1,     2,     0,     0,     0,
//yyDefRed 888
//yy     1,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   296,   299,     0,     0,     0,   322,   323,     0,
//yy     0,     0,   420,   419,   421,   422,     0,     0,     0,    20,
//yy     0,   424,   423,     0,     0,   416,   415,     0,   418,   410,
//yy   411,   427,   428,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   391,   393,   393,     0,     0,
//yy   268,     0,   376,   269,   270,   271,   272,   267,   372,   374,
//yy     2,     0,     0,     0,     0,     0,     0,    36,     0,     0,
//yy   273,     0,    44,     0,     0,     5,     0,    71,     0,    61,
//yy     0,     0,     0,   373,     0,    35,     0,   320,   321,   285,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   324,     0,   274,   425,     0,    94,   313,   141,   152,   142,
//yy   165,   138,   158,   148,   147,   163,   146,   145,   140,   166,
//yy   150,   139,   153,   157,   159,   151,   144,   160,   167,   162,
//yy     0,     0,     0,     0,   137,   156,   155,   168,   169,   170,
//yy   171,   172,   136,   143,   134,   135,     0,     0,     0,    98,
//yy     0,   127,   128,   125,   109,   110,   111,   114,   116,   112,
//yy   129,   130,   117,   118,   463,   122,   121,   108,   126,   124,
//yy   123,   119,   120,   115,   113,   106,   107,   131,     0,   462,
//yy   315,    99,   100,   161,   154,   164,   149,   132,   133,    96,
//yy    97,     0,     0,   103,   102,   101,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   492,   491,     0,
//yy     0,     0,   493,     0,     0,     0,     0,     0,     0,   336,
//yy   337,     0,     0,     0,     0,     0,   231,    46,   239,     0,
//yy     0,     0,   468,    47,    45,     0,    60,     0,     0,   351,
//yy    59,    39,     0,     9,   487,     0,     0,     0,   193,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   219,     0,     0,     0,     0,     0,   465,     0,     0,
//yy     0,     0,    69,     0,   209,   208,    40,   407,   406,   408,
//yy     0,   404,   405,     0,     0,     0,     0,     0,     0,     0,
//yy   377,     4,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   342,   344,   355,   353,
//yy   293,     0,     0,     0,     0,     0,     0,     0,    73,     0,
//yy     0,     0,     0,     0,   347,     0,   291,     0,   412,   413,
//yy     0,    91,     0,    93,     0,   430,   308,   429,     0,     0,
//yy     0,     0,     0,   482,   483,   317,     0,   104,     0,     0,
//yy   276,     0,   327,   326,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   494,     0,     0,
//yy     0,     0,     0,     0,   305,     0,   259,     0,     0,   232,
//yy   261,     0,   234,   287,     0,     0,   254,   253,     0,     0,
//yy     0,     0,     0,    11,    13,    12,     0,   289,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   279,     0,     0,
//yy     0,   220,     0,   489,   221,     0,   223,   283,     0,   467,
//yy   466,   284,     0,     0,     0,     0,   395,   398,   396,   409,
//yy   394,   378,   392,   379,   380,   381,   382,   385,     0,   387,
//yy     0,   388,     0,    15,    16,    17,    18,    19,    37,    38,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   477,     0,     0,   478,     0,     0,     0,     0,   350,     0,
//yy     0,   475,   476,     0,     0,     0,    30,     0,     0,    23,
//yy    31,   262,     0,    24,    33,     0,     0,    67,    74,     0,
//yy    25,    51,    54,     0,   432,     0,     0,     0,     0,     0,
//yy     0,    95,     0,     0,     0,     0,     0,   445,   444,   446,
//yy     0,   454,   453,   458,   457,   442,     0,     0,   451,     0,
//yy   448,     0,     0,     0,     0,     0,   367,   366,     0,     0,
//yy     0,     0,   334,     0,   328,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   303,   331,   330,
//yy   297,   329,   300,     0,     0,     0,     0,     0,     0,     0,
//yy   238,   471,     0,   260,     0,     0,   470,     0,     0,   469,
//yy   286,     0,     0,   257,   251,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   225,    10,     0,     0,     0,    22,     0,
//yy     0,     0,     0,     0,   224,     0,   263,     0,     0,     0,
//yy     0,     0,     0,     0,   384,   386,   390,     0,   340,     0,
//yy     0,   338,     0,     0,     0,     0,   230,   348,     0,   229,
//yy     0,     0,   349,     0,     0,   345,    49,   346,    50,   266,
//yy     0,     0,    72,     0,   311,     0,     0,   282,   314,     0,
//yy   318,     0,     0,     0,   434,     0,   440,     0,   441,     0,
//yy   438,   455,   459,   105,     0,     0,   369,   335,     0,     3,
//yy   371,     0,   332,     0,     0,     0,     0,     0,     0,   302,
//yy   304,   360,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   236,     0,     0,     0,     0,     0,   244,   256,   226,     0,
//yy     0,   227,     0,     0,     0,    21,   278,     0,     0,     0,
//yy   400,   401,   402,   397,   403,     0,   339,     0,     0,     0,
//yy     0,     0,    27,     0,    28,    56,     0,    29,     0,    58,
//yy     0,     0,     0,     0,     0,     0,   431,   309,   464,     0,
//yy   450,     0,   316,     0,   460,   452,     0,     0,   449,     0,
//yy     0,     0,     0,   368,     0,     0,   370,     0,   294,     0,
//yy   295,     0,     0,     0,     0,   306,   233,     0,   235,   250,
//yy   258,     0,   241,     0,     0,     0,     0,   290,   222,   399,
//yy   341,   343,   356,   354,     0,    26,   265,     0,     0,     0,
//yy   433,   439,     0,   436,   437,     0,     0,     0,     0,     0,
//yy     0,   359,   361,   357,   362,   298,   301,     0,     0,     0,
//yy     0,   240,     0,   246,     0,   228,    52,   312,     0,     0,
//yy     0,     0,     0,     0,     0,   363,     0,     0,   237,   242,
//yy     0,     0,     0,   245,   319,   435,     0,   333,   307,     0,
//yy     0,   247,     0,   243,     0,   248,     0,   249,
//yyDgoto 139
//yy     1,   188,    60,    61,    62,    63,    64,   293,   290,   460,
//yy    65,    66,   468,    67,    68,    69,   109,   206,   207,    71,
//yy    72,    73,    74,    75,    76,    77,    78,   299,   297,   210,
//yy   259,   712,   843,   713,   705,   709,   666,   667,   237,   623,
//yy   417,   246,    80,   409,   613,   410,   366,    81,    82,   696,
//yy   784,   566,   567,   568,   202,   753,   212,   228,   660,   213,
//yy    85,   356,   337,   542,   530,    86,    87,   239,   396,    88,
//yy    89,   267,   272,   596,    90,   273,   242,   579,   274,   379,
//yy   214,   215,   277,   278,   569,   203,   291,    93,   114,   547,
//yy   518,   115,   205,   513,   570,   571,   572,   375,   573,   574,
//yy     2,   220,   221,   426,   256,   683,   192,   575,   255,   428,
//yy   444,   247,   627,   733,   439,   635,   384,   223,   600,   724,
//yy   224,   725,   608,   847,   546,   385,   543,   775,   371,   376,
//yy   555,   779,   506,   508,   507,   653,   652,   545,   372,
//yySindex 888
//yy     0,     0, 13602, 13715,  3439, 17082, 17632, 17525, 13602, 15514,
//yy 15514, 10823,     0,     0, 16858, 14730, 14730,     0,     0, 14730,
//yy  -261,  -258,     0,     0,     0,     0, 15514, 17418,   136,     0,
//yy  -274,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0, 16522, 16522,  -124,  -211, 13828, 15514, 15626,
//yy 16522, 17194, 16522, 16634, 17738,     0,     0,     0,   126,   166,
//yy     0,  -149,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   145,  1050,   246,  2586,     0,   -48,     0,  -222,   157,
//yy     0,   254,     0,   -67,   213,     0,   234,     0,   221,     0,
//yy 16970,     0,   -18,     0,  -195,     0,  1050,     0,     0,     0,
//yy  -261,  -258,   136,     0,     0,   260, 15514,   -59, 13602,    11,
//yy     0,   161,     0,     0,  -195,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   268,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0, 17738,   291,     0,     0,     0,   119,   150,   109,   246,
//yy   118,   278,    51,   377,     0,   110,   118,     0,     0,   145,
//yy   -99,   401,     0, 15514, 15514,   182,   308,     0,   232,     0,
//yy     0,     0, 16522, 16522, 16522,  2586,     0,     0,     0,   179,
//yy   479,   504,     0,     0,     0, 13131,     0, 14842, 14730,     0,
//yy     0,     0,   468,     0,     0,   219,   206, 13602,     0,   375,
//yy   266,   274,   285,   262, 13828,   566,     0,   579,   246, 16522,
//yy   136,     0,    50,    82,   551,   127,    82,     0,   532,   351,
//yy   397,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy  -191,     0,     0,  -188,  -174,   549,   293,   193,   302,  -247,
//yy     0,     0, 13245, 15514, 15514, 15514, 15514, 13715, 15514, 15514,
//yy 16522, 16522, 16522, 16522, 16522, 16522, 16522, 16522, 16522, 16522,
//yy 16522, 16522, 16522, 16522, 16522, 16522, 16522, 16522, 16522, 16522,
//yy 16522, 16522, 16522, 16522, 16522, 16522,     0,     0,     0,     0,
//yy     0,  2066,  2540, 15626,  3031,  3031, 16634, 15738,     0, 15738,
//yy 13828, 17194,   630, 16634,     0,   329,     0,   219,     0,     0,
//yy   246,     0,     0,     0,   145,     0,     0,     0,  3031,  3504,
//yy 15626, 13602, 15514,     0,     0,     0,  1650,     0, 15850,   417,
//yy     0,   262,     0,     0, 13602,   420,  3836,  3898, 15626, 16522,
//yy 16522, 16522, 13602,   418, 13602, 15962,   426,     0,    55,    55,
//yy     0,  3962,  4309, 15626,     0,   650,     0, 16522, 14954,     0,
//yy     0, 13941,     0,     0,   656, 14618,     0,     0,   -48,   136,
//yy    42,   658,   663,     0,     0,     0, 17525,     0, 16522, 13602,
//yy   585,  3836,  3898, 16522, 16522, 16522,   680,     0,     0,   136,
//yy  1917,     0, 16074,     0,     0, 14506,     0,     0, 16522,     0,
//yy     0,     0,     0,  4371,  4435, 15626,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   -16,     0,
//yy   691,     0,  1050,     0,     0,     0,     0,     0,     0,     0,
//yy   266,  4889,  4889,  4889,  4889,  1562,  1562,  4479,  4006,  4889,
//yy  4889,  2117,  2117,   908,   908,   266,   932,   266,   266,  -253,
//yy  -253,  1562,  1562,   807,   807,  2000,  -226,  -226,  -226,   386,
//yy     0,   387,  -258,     0,   398,     0,   400,  -258,     0,     0,
//yy   647,     0,     0,  -258,  -258,  2586,     0, 16522,  2474,     0,
//yy     0,     0,   697,     0,     0,     0,   703,     0,     0,  2586,
//yy     0,     0,     0,   145,     0, 15514, 13602,  -258,     0,     0,
//yy  -258,     0,   659,   486,    39, 17844,   698,     0,     0,     0,
//yy   742,     0,     0,     0,     0,     0, 13602,   145,     0,   723,
//yy     0,   725,   726,   474,   484, 17525,     0,     0,     0,   433,
//yy 13602,   520,     0,   342,     0,   450,   464,   472,   400,   721,
//yy  2474,   417,   552,   555, 16522,   771,   118,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   745, 15514,   495,
//yy     0,     0, 16522,     0,   179,   802,     0, 16522,   179,     0,
//yy     0, 16522,  2586,     0,     0,    20,   806,   812,   815,  3031,
//yy  3031,   817, 15066,     0,     0, 15514,  2586,   748,     0,   266,
//yy   266,  2586,     0,   836,     0, 16522,     0,     0,     0,     0,
//yy     0,   787, 13602,   488,     0,     0,     0, 16522,     0,  2966,
//yy 13602,     0, 13602, 13602, 16634, 16634,     0,     0,   329,     0,
//yy 16634, 16522,     0,   329,   547,     0,     0,     0,     0,     0,
//yy 16522, 16186,     0,  -226,     0,   145,   631,     0,     0,   853,
//yy     0, 16522,   136,   635,     0,   728,     0,   276,     0,   742,
//yy     0,     0,     0,     0, 17306,   118,     0,     0, 13602,     0,
//yy     0, 15514,     0,   638, 16522, 16522, 16522,   558,   639,     0,
//yy     0,     0, 16298, 13602, 13602, 13602,     0,    55,   650, 14054,
//yy     0,   650,   650,   864, 15178, 14167,     0,     0,     0,  -258,
//yy  -258,     0,   -48,    42,   186,     0,     0,  1917,     0,   781,
//yy     0,     0,     0,     0,     0,  2586,     0,   553,   645,   646,
//yy   790,  2586,     0,  2586,     0,     0,  2586,     0,  2586,     0,
//yy 16634,  2586, 16522,     0, 13602, 13602,     0,     0,     0,  1650,
//yy     0,   879,     0,   698,     0,     0,   725,   877,     0,   725,
//yy   614,   419,     0,     0,     0, 13602,     0,   118,     0, 16522,
//yy     0, 16522,   253,   661,   664,     0,     0, 16522,     0,     0,
//yy     0, 16522,     0,   890,   892, 16522,   897,     0,     0,     0,
//yy     0,     0,     0,     0,  2586,     0,     0,   818,   678, 13602,
//yy     0,     0,   728,     0,     0,     0,  4783,  4845, 15626,   119,
//yy 13602,     0,     0,     0,     0,     0,     0, 13602,  2391,   650,
//yy 15290,     0, 14280,     0,   650,     0,     0,     0,   686,   725,
//yy     0,     0,     0,     0,   851,     0,   342,   687,     0,     0,
//yy 16522,   911, 16522,     0,     0,     0,     0,     0,     0,   650,
//yy 14393,     0,   650,     0, 16522,     0,   650,     0,
//yyRindex 888
//yy     0,     0,   151,  5660,     0,     0,     0,     0,    21,     0,
//yy     0,   284,     0,     0,     0,  7364,  7499,     0,     0,  7612,
//yy  4230,  3644,     0,     0,     0,     0,     0,     0, 16410,     0,
//yy     0,     0,     0,  1748,  2808,     0,     0,  1860,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   183,     0,   863,
//yy   833,   -21,     0,     0,   282,     0,     0,     0,   462,  -214,
//yy     0,  6654,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   350,  1624,  5920,  6384,  6789, 11044,     0,  6902,     0,
//yy     0, 11502,     0,  8671,     0,     0,     0,     0,     0,     0,
//yy    13,  8570,     0,     0, 15402,     0,  5609,     0,     0,     0,
//yy  7003,  5645,   618,  9897, 10010,     0,     0,     0,   183,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   842,  1058,  1240,  1249,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,  1332,  1425,  1519,     0,
//yy  1599,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,  6094,     0,     0,     0,   360,     0,     0, 13450,
//yy     0,     0,  6192,     0,  6078,     0,     0,     0,     0,   693,
//yy     0,   364,     0,     0,     0,     0,     0,   279,     0,     0,
//yy     0,   469,     0,     0,     0, 12276,     0,     0,     0, 12924,
//yy 13086, 13086,     0,     0,     0,     0,     0,     0,   924,     0,
//yy     0,     0,     0,     0,     0, 16746,     0,    97,     0,     0,
//yy  7713,  7150,  7251,  8785,   183,     0,   337,     0,   143,     0,
//yy   869,     0,   874,   874,     0,   844,   844,     0,     0,     0,
//yy     0,   896,     0,  1178,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,  1156,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   863,     0,     0,     0,     0,     0,     0,
//yy   183,    77,   104,     0,     0, 13400,     0,     0,     0,     0,
//yy   154,     0, 10360,     0,     0,     0,     0,     0,     0,     0,
//yy   863,    21,     0,     0,     0,     0,   180,     0,     3,   376,
//yy     0,  6440,     0,     0,   644, 10473,     0,     0,   863,     0,
//yy     0,     0,   210,     0,    62,     0,     0,     0,     0,     0,
//yy   763,     0,     0,   863,     0, 13086,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   938,     0,     0,   184,   939,
//yy   939,   281,     0,     0,     0,     0,     0,     0,     0,    97,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   185,   939,
//yy   869,     0,   888,     0,     0,    74,     0,     0,   858,     0,
//yy     0,     0,  1626,     0,     0,   863,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,  6018,     0,     0,     0,     0,     0,     0,     0,
//yy  7860, 11628, 11682, 11762, 11898, 11113, 11193, 11952, 12114, 12006,
//yy 12060, 12168, 12222,  9319,  9423,  7961,  9503,  8074,  8209,  9030,
//yy  9134, 11273, 11548,  9608,  9792,     0, 10592, 10592, 10705,  4591,
//yy     0,  4703,  3756,     0,  5065,  3170,  5177, 15402,     0,  3282,
//yy     0,     0,     0,  5538,  5538, 12330,     0,     0, 11236,     0,
//yy     0,     0,     0,     0,     0,  5598,     0,     0,     0, 12384,
//yy     0,     0,     0,     0,     0,     0,    21,  5978, 10129, 10242,
//yy     0,     0,     0,     0,   939,     0,   227,     0,     0,     0,
//yy   429,     0,     0,     0,     0,     0,    21,     0,     0,   218,
//yy     0,   218,   218,   688,     0,     0,     0,     0,   115,   487,
//yy   204,   720,     0,   720,     0,  2222,  2334,  2696,  4118,     0,
//yy 12978,   720,     0,     0,     0,   541,     0,     0,     0,     0,
//yy     0,     0,     0,  1422,  1460,  1603,   236,     0,     0,     0,
//yy     0,     0,     0,     0, 13032, 13086,     0,     0,     0,     0,
//yy     0,     0,    49,     0,     0,     0,   945,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0, 12438,     0,     0,  8322,
//yy  8423, 12492,   194,     0,     0,     0,     0,   667,  1092,  1369,
//yy   652,     0,    97,     0,     0,     0,     0,     0,     0,     0,
//yy    62,     0,    62,    97,     0,     0,     0,     0, 13514,     0,
//yy     0,     0,     0, 13560,  8919,     0,     0,     0,     0,     0,
//yy     0,     0,     0, 10705,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   939,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,    62,     0,
//yy     0,     0,     0,     0,     0,     0,     0,  6541,     0,     0,
//yy     0,     0,     0,   192,    62,    62,   769,     0, 13086,     0,
//yy     0, 13086,   945,     0,     0,     0,     0,     0,     0,    47,
//yy    47,     0,     0,   939,     0,     0,     0,   869,  1632,     0,
//yy     0,     0,     0,     0,     0, 12546,     0,     0,     0,     0,
//yy     0, 12600,     0, 12654,     0,     0, 12708,     0, 12762,     0,
//yy     0, 12816,     0,   949,    97,    21,     0,     0,     0,   180,
//yy     0,     0,     0,     0,     0,     0,   218,   218,     0,   218,
//yy     0,     0,   531,     0,   633,    21,     0,     0,     0,     0,
//yy     0,     0,   720,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   945,   945,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0, 12870,     0,     0,     0,     0,    21,
//yy     0,     0,     0,     0,     0,  1024,     0,     0,   863,   360,
//yy   644,     0,     0,     0,     0,     0,     0,    62, 13086,   945,
//yy     0,     0,     0,     0,   945,     0,     0,     0,     0,   218,
//yy   198,   537,   624,   202,     0,     0,   720,     0,     0,     0,
//yy     0,   945,     0,     0,     0,     0,  1115,     0,     0,   945,
//yy     0,     0,   945,     0,     0,     0,   945,     0,
//yyGindex 139
//yy     0,     0,     0,     0,   930,     0,     0,     0,   613,  -218,
//yy     0,     0,     0,     0,     0,     0,   987,   158,  -330,     0,
//yy    95,  1070,   -15,    72,   114,    -2,     0,     0,     0,   277,
//yy   101,  -365,     0,   129,     0,     0,   -12,   108,   642,     0,
//yy     0,    -3,   990,   789,    -4,     0,     0,  -238,     0,     4,
//yy  -337,   220,   441,  -640,     0,     0,   478,   343,  -462,   921,
//yy   657,   925,     0,  -227,  -138,   916,    -9,  1124,  -368,   -11,
//yy   -17,  -216,    17,     0,     0,    28,  -303,     0,  -320,   172,
//yy   837,  1062,   765,     0,   315,   -10,     0,    -5,   669,  -201,
//yy     0,   -88,     2,    -1,   318,  -626,     0,     0,     0,     0,
//yy     0,   -44,   950,     0,     0,     0,     0,     0,    87,     0,
//yy   258,     0,     0,     0,     0,     0,  -209,     0,  -375,     0,
//yy     0,     0,     0,     0,     0,    60,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yyTable 18207
//yy   235,   235,   204,   230,   235,   191,   191,   392,   190,   249,
//yy   420,   238,   238,   495,   581,   238,   654,   253,   250,   363,
//yy    70,   217,   204,   520,   602,   592,   191,   301,   258,   260,
//yy   266,   495,   240,   240,   235,   235,   240,   284,   285,   565,
//yy    70,   553,   282,   241,   241,   662,   663,   241,   436,   254,
//yy   552,   286,   254,   191,    65,   785,   292,   495,   338,    41,
//yy   443,   495,   495,   470,   734,   217,   310,   275,   589,   786,
//yy   218,   222,   495,   789,    65,   462,   110,   110,   276,   467,
//yy   495,   245,   354,   607,   248,   338,   110,   257,   495,   249,
//yy    41,   355,   443,   218,   442,   325,   389,   263,    96,   327,
//yy   328,   658,   354,    79,    79,   111,   111,   495,   619,    79,
//yy   211,   211,   211,   599,   218,   226,   211,   211,    68,   456,
//yy   211,   495,   456,   110,   471,   365,   445,   211,   217,   236,
//yy   236,   222,   659,   236,   536,   651,   456,   443,    68,   339,
//yy   512,   517,   619,   523,   524,    66,   254,   248,    79,   211,
//yy   211,   495,   280,   277,   211,   217,   495,   389,   296,   262,
//yy    70,   495,   110,   270,   310,    66,   339,   490,   550,   367,
//yy   394,   448,   395,   365,   365,   393,   383,   218,   457,   458,
//yy   459,   457,   458,   461,   277,   512,   517,   261,   262,   357,
//yy   443,   280,   785,   495,   231,   457,   458,   463,   298,   490,
//yy   191,   550,   495,   292,   218,   265,   859,   211,    85,    79,
//yy   495,   534,    88,   310,   495,    55,   686,   235,   235,   285,
//yy   495,   774,   495,   565,   495,   231,   718,   817,   461,    72,
//yy   235,   550,   235,   235,   336,    64,   693,   447,    72,   443,
//yy   577,   238,   495,   238,   238,   361,    62,   266,   343,   362,
//yy   655,   495,   370,   550,   440,    62,    85,    85,    70,   461,
//yy    88,    88,   240,   495,   240,   421,   265,   364,   447,   495,
//yy   495,   447,   346,   241,   347,   241,   241,   461,   348,   222,
//yy    80,   551,   349,   495,   495,   495,   447,   216,   219,   475,
//yy   495,   497,    65,   280,   456,   480,   481,   482,   483,   484,
//yy   485,   486,   487,   488,   489,   490,   491,   492,   493,   494,
//yy   495,   496,   497,   498,   499,   500,   501,   502,   503,   504,
//yy   505,   353,   497,    82,   211,   211,   495,   280,   235,   308,
//yy   309,   525,   528,   266,   235,   377,   598,    70,   539,    41,
//yy    41,   519,   537,   495,   526,   529,   211,   533,   211,   211,
//yy   484,   540,   805,   457,   458,   235,    68,   441,    79,   236,
//yy   407,   236,   418,   235,   532,    79,   532,   365,   519,   388,
//yy   274,    65,   629,   235,   525,   590,   539,   495,    63,   630,
//yy   235,   378,   365,    66,   495,   382,   519,   723,   235,   218,
//yy   389,   484,   235,   235,   743,   532,   235,   472,    61,   403,
//yy   622,   519,   477,    79,   211,   211,   211,   211,    79,   211,
//yy   211,   624,   595,   636,   380,   430,   634,   381,   639,   640,
//yy   641,   204,   265,   110,   544,   191,   619,   235,   739,   740,
//yy   235,   519,   619,   235,   844,    68,   615,   842,   390,   443,
//yy   235,   765,   565,   626,   211,   828,   769,   211,   211,   391,
//yy   211,    79,   280,   519,   211,   365,   495,   270,   601,   601,
//yy   397,   495,    66,   495,    62,    85,   388,   495,   495,    88,
//yy   443,   211,    79,   211,   495,   484,   308,   309,   475,   495,
//yy    83,    83,   112,   112,   270,    79,    83,   341,   455,   211,
//yy   400,   368,   227,    79,   342,    79,   795,   495,   369,   858,
//yy   398,   399,   270,   456,   211,   672,   628,   631,   265,   668,
//yy   838,   404,   235,    75,   673,    88,   211,   270,   864,   407,
//yy   676,   678,   580,   408,   395,    83,   643,   644,   475,   281,
//yy    79,   446,   280,   449,   450,   475,   308,   309,   672,   280,
//yy   475,    90,   583,    62,   476,   495,   495,    87,   411,   619,
//yy   591,   358,   593,   484,   191,   484,   211,   690,    90,   475,
//yy   425,   427,   457,   458,   466,   703,   280,   429,   281,   270,
//yy   204,   358,   359,   280,   191,   698,   700,   619,   476,   235,
//yy   473,   474,   475,   476,   344,   310,    83,   637,   840,    90,
//yy    90,   345,   393,   433,    88,    87,    87,   235,   373,   358,
//yy   358,   435,   235,   684,   434,   374,   235,   437,   386,   274,
//yy   711,   580,   484,   484,   484,   387,   274,   235,   484,   484,
//yy   438,   484,   274,   364,   364,   563,   564,   694,   476,   364,
//yy   747,   675,   677,   485,    86,   485,   550,    90,   401,   495,
//yy   495,   689,   755,    83,   447,   402,   211,    79,   230,   761,
//yy   763,   393,   393,   393,   495,   766,   768,   451,   243,   452,
//yy   615,   244,   762,   764,   465,   771,   235,    79,   767,   294,
//yy   295,    94,    94,   469,   538,   541,   235,    94,    94,    94,
//yy    94,    79,    86,    86,    94,    94,   580,   584,    94,   395,
//yy   597,    83,    83,    80,   609,    94,    80,   620,   456,   761,
//yy   763,   766,   632,   495,   633,   431,   271,   235,    77,   211,
//yy   638,    77,   432,    80,   235,   479,    94,    94,    94,   235,
//yy   235,   642,    94,   656,   425,   664,   665,   453,    77,   456,
//yy   812,   110,   456,   211,   454,    83,   211,   670,   707,   671,
//yy   674,   681,    83,   280,   682,   776,   742,   456,    83,   836,
//yy   688,   813,   687,    79,   495,   824,   837,   235,   475,   691,
//yy   226,    79,   814,    79,    79,   211,   211,   695,   825,   697,
//yy   699,   211,   383,   704,   423,    94,   110,    94,   701,   781,
//yy    83,   424,   425,   708,   824,    83,   235,   601,   702,   714,
//yy   831,   833,   848,   834,   750,   751,   235,   752,    90,   425,
//yy   235,    41,    42,   715,    87,   791,   425,    81,   358,    79,
//yy   749,   716,   211,    76,   717,   722,   719,   476,   758,   720,
//yy   759,   760,   685,   235,    79,    79,    79,    83,    83,   281,
//yy   816,   383,   383,   383,   818,   235,   519,   235,   726,    91,
//yy    91,   113,   113,   113,   233,    91,   729,   675,   677,    83,
//yy   735,   231,   161,   737,   281,   235,   738,   235,   741,   456,
//yy   282,   274,    83,   875,   464,   235,   796,   476,   274,   235,
//yy    83,   211,    83,   745,   476,    79,    79,   746,   871,   476,
//yy   748,   802,   803,   804,    91,   727,   770,   414,   283,   416,
//yy   419,    86,    94,    94,   778,   777,    79,   799,   476,   782,
//yy    83,   161,   798,   800,   479,   810,   819,    83,   495,   821,
//yy   822,   820,   495,   495,    94,   823,    94,    94,   457,   458,
//yy   830,   832,   835,    84,    84,   845,    94,   283,   846,    84,
//yy    79,    80,   827,    94,   850,   229,   852,    82,   855,   211,
//yy    82,    79,   857,   856,   876,    91,    77,   475,    79,   264,
//yy   874,   878,   270,   839,   479,   880,   495,    82,   496,   264,
//yy   486,   479,   486,   425,   484,   496,   475,   488,    84,   488,
//yy   425,    94,    94,    94,    94,    94,    94,    94,    94,   255,
//yy   486,   490,   280,   490,   495,   479,   497,   274,   797,   280,
//yy   264,   300,   116,   263,   476,   877,   189,   475,   866,   829,
//yy   271,   692,   757,   340,   475,   867,   352,   264,   264,   475,
//yy    80,   865,    94,   422,   787,    94,    94,   788,    94,    94,
//yy     0,   302,    94,     0,    83,    77,     0,   271,   475,    84,
//yy   412,     0,   783,     0,    89,   576,   582,     0,     0,    94,
//yy    94,    94,    89,     0,    83,   271,   556,     0,    84,   557,
//yy   558,   559,     0,    94,     0,     0,     0,    94,    83,     0,
//yy   271,    94,     0,    94,    92,    92,     0,     0,   154,     0,
//yy    92,     0,    94,     0,   264,   561,   562,   563,   564,   209,
//yy   209,   209,    89,    89,    94,     0,     0,     0,     0,   561,
//yy   562,   563,   564,   281,    91,     0,   251,     0,    94,   282,
//yy   281,    91,     0,     0,     0,     0,   282,     0,     0,    92,
//yy     0,     0,   271,     0,     0,   281,     0,   154,   268,     0,
//yy     0,    89,     0,     0,    94,    84,   310,    84,     0,     0,
//yy    83,   754,     0,    79,     0,     0,    79,   227,    83,    91,
//yy    83,    83,   323,   324,    91,     0,   161,     0,     0,   161,
//yy   161,   161,     0,    79,     0,   325,   485,   326,     0,   327,
//yy   328,   329,   330,   661,   661,   661,     0,     0,     0,   669,
//yy    92,     0,   420,    84,    84,    82,   360,     0,    84,   420,
//yy   669,   669,   792,   476,   161,    84,    83,    91,   283,   161,
//yy   161,   161,   161,     0,   610,     0,     0,   485,     0,     0,
//yy     0,    83,    83,    83,   669,     0,   282,     0,    91,     0,
//yy     0,   264,   264,   264,    94,    94,   264,   264,   264,    75,
//yy   264,    91,    75,    84,     0,     0,   274,   310,    84,    91,
//yy     0,    91,     0,   274,     0,    94,   706,     0,   710,    75,
//yy   582,     0,   264,   264,   264,   264,   264,     0,   582,    94,
//yy   164,   310,    83,    83,    82,     0,   325,     0,     0,   149,
//yy   327,   328,   329,   330,     0,     0,    91,   323,   324,   425,
//yy     0,    84,     0,    83,     0,     0,     0,    94,     0,     0,
//yy   325,   485,     0,     0,   327,   328,   329,   330,     0,   264,
//yy     0,    89,    84,   209,   209,     0,     0,     0,     0,   164,
//yy     0,    94,     0,     0,    94,    84,     0,    83,   149,     0,
//yy     0,     0,     0,    84,     0,    84,     0,     0,    83,    92,
//yy     0,    94,     0,     0,     0,    83,    92,     0,     0,    94,
//yy     0,    94,    94,    94,    94,     0,     0,     0,     0,    94,
//yy   661,     0,   132,   303,   304,   305,   306,   307,     0,     0,
//yy    84,     0,     0,     0,   281,     0,   405,   406,     0,     0,
//yy     0,   281,   154,     0,    92,   154,   154,   154,     0,    92,
//yy     0,    79,   476,   209,   209,   209,   209,    94,   478,   479,
//yy    94,     0,    84,    91,     0,     0,     0,     0,   419,     0,
//yy     0,   132,    94,    94,    94,   419,   669,   669,     0,     0,
//yy   154,     0,     0,    91,   730,   154,   154,   154,   154,     0,
//yy    78,     0,    92,    78,     0,   736,     0,    91,   485,   485,
//yy   485,     0,   476,     0,   485,   485,     0,   485,     0,   476,
//yy    78,     0,     0,    92,   476,   133,     0,     0,     0,    94,
//yy     0,     0,   554,    94,    94,   282,    92,     0,     0,     0,
//yy    79,     0,   282,   476,    92,     0,    92,    75,     0,   582,
//yy   479,     0,     0,     0,    94,     0,    77,    84,     0,     0,
//yy     0,   531,     0,   535,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   133,   268,     0,    84,     0,    91,
//yy     0,    92,     0,     0,     0,   271,   231,    91,    94,    91,
//yy    91,    84,   578,     0,    79,     0,     0,    94,   425,    94,
//yy     0,     0,     0,   475,     0,   425,    94,   806,     0,   531,
//yy   808,   809,     0,   582,     0,     0,     0,     0,     0,    96,
//yy     0,   611,   614,     0,     0,   618,    75,     0,     0,   625,
//yy     0,   794,     0,     0,   164,    91,     0,   164,   164,   164,
//yy     0,   476,     0,   149,     0,     0,   149,   149,   149,     0,
//yy    91,    91,    91,     0,     0,     0,   646,     0,     0,   618,
//yy   421,     0,   646,    84,     0,     0,     0,   421,    96,   422,
//yy   229,    84,   164,    84,    84,     0,   422,   164,   164,   164,
//yy   164,   149,     0,     0,     0,     0,   149,   149,   149,   149,
//yy     0,     0,   851,   853,     0,     0,     0,     0,    92,    97,
//yy     0,    91,    91,     0,     0,   209,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     6,   793,     0,     0,    92,    84,
//yy     0,     0,    91,     0,     6,     0,   132,   868,   869,   132,
//yy   132,   132,    92,   873,    84,    84,    84,    78,    78,   479,
//yy     0,   679,     0,     0,     0,     0,     0,     0,    97,     0,
//yy   881,     0,   424,     0,     0,     6,    91,    81,   883,   424,
//yy    81,   885,     0,    76,   132,   887,    76,    91,   209,   132,
//yy   132,   132,   132,     6,    91,     0,     0,    81,     0,     0,
//yy     0,     0,     0,    76,   479,    84,    84,     0,     0,   479,
//yy     0,    85,   475,     0,     0,   744,   479,     0,     0,     0,
//yy     0,   475,     0,     0,    92,     0,    84,   281,   721,     0,
//yy     0,     0,    92,   282,    92,    92,     0,    78,     0,   133,
//yy   479,     0,   133,   133,   133,     0,   728,     0,     0,    87,
//yy   476,   731,     0,     0,     0,   732,     0,     0,   414,     6,
//yy    84,     0,   475,     0,     0,   423,   614,     0,   414,   475,
//yy     0,    84,   423,     0,   475,     0,     0,   133,    84,     0,
//yy    92,     0,   133,   133,   133,   133,     0,     0,     0,     0,
//yy    85,   209,     0,   475,     0,    92,    92,    92,     0,   414,
//yy   476,     0,   414,     0,     0,     0,     0,   476,     0,     0,
//yy     0,     0,   476,     0,     0,   773,   414,   414,     0,   414,
//yy     0,   414,     0,     0,     0,   780,     0,     0,    87,     0,
//yy     0,   476,     0,    96,     0,     0,    96,    96,    96,     0,
//yy     0,     0,     0,     0,     0,     0,    92,    92,     0,   414,
//yy     0,   414,     0,     0,     0,     0,   646,     0,     0,   414,
//yy     0,     0,     0,   618,     0,     0,   414,    92,     0,   618,
//yy   417,    96,     0,     0,     0,     0,    96,    96,    96,    96,
//yy   417,     0,     0,   414,     0,     0,     0,     0,     0,     0,
//yy     0,   310,    86,   479,     0,     0,     6,     6,     6,     0,
//yy     0,    92,     6,     6,     0,     6,   826,   323,   324,     0,
//yy     0,   417,    92,    97,   417,    81,    97,    97,    97,    92,
//yy   325,    76,   326,     0,   327,   328,   329,   330,   417,   417,
//yy   333,   417,   334,   417,     0,   841,     0,   254,     0,   417,
//yy     0,     0,     0,   479,     0,   849,   417,     0,     0,   854,
//yy   479,    97,     0,     0,     0,   475,    97,    97,    97,    97,
//yy     0,   417,     0,   417,   556,     0,   281,   557,   558,   559,
//yy     0,    86,   282,   281,   479,     0,     0,     0,     0,   282,
//yy     0,     0,     0,     0,   614,     0,   618,     0,     0,     0,
//yy   335,     0,     0,     0,    81,   417,     0,     0,     0,     0,
//yy    76,     0,   560,     0,   879,     0,   882,   561,   562,   563,
//yy   564,     0,     0,     0,   618,   472,   472,   472,   886,   472,
//yy   414,   414,   414,   472,   472,   414,   414,   414,   472,   414,
//yy   472,   472,   472,   472,   472,   472,   472,   414,   472,   414,
//yy   414,   472,   472,   472,   472,   472,   472,   472,   414,   414,
//yy     0,   414,   414,   414,   414,   414,     0,   472,     0,     0,
//yy   472,   472,   472,   472,   472,   472,   472,   472,   657,   472,
//yy   472,   472,   472,   335,   472,   472,   472,   414,   414,   414,
//yy   414,   414,   414,   414,   414,   414,   414,   414,   414,   414,
//yy   414,     0,     0,   414,   414,   414,   472,   414,   414,   472,
//yy   472,   472,   472,   472,   414,   472,   414,   472,   414,   472,
//yy   414,   414,   414,   414,   414,   414,   414,   472,   414,   472,
//yy     0,   472,   472,   472,   472,   472,   472,   473,   473,   473,
//yy     0,   473,   417,   417,   417,   473,   473,   417,   417,   417,
//yy   473,   417,   473,   473,   473,   473,   473,   473,   473,   417,
//yy   473,   417,   417,   473,   473,   473,   473,   473,   473,   473,
//yy   417,   417,     0,   417,   417,   417,   417,   417,     0,   473,
//yy     0,     0,   473,   473,   473,   473,   473,   473,   473,   473,
//yy     0,   473,   473,   473,   473,     0,   473,   473,   473,   417,
//yy   417,   417,   417,   417,   417,   417,   417,   417,   417,   417,
//yy   417,   417,   417,     0,     0,   417,   417,   417,   473,   417,
//yy   417,   473,   473,   473,   473,   473,   417,   473,   417,   473,
//yy   417,   473,   417,   417,   417,   417,   417,   417,   417,   473,
//yy   417,   473,   475,   473,   473,   473,   473,   473,   473,     0,
//yy     0,     0,   475,     0,     0,     0,   310,   311,   312,   313,
//yy   314,   315,   316,   317,   318,   319,   320,     0,   321,   322,
//yy     0,     0,   323,   324,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   475,     0,   325,   475,   326,     0,   327,
//yy   328,   329,   330,   331,   332,   333,     0,   334,     0,     0,
//yy   475,   475,     0,    85,     0,   475,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   475,     0,   475,     0,     0,     0,   310,
//yy   311,   312,   313,   314,   315,   316,   317,   318,   319,   320,
//yy     0,   321,   322,     0,   476,   323,   324,     0,     0,     0,
//yy     0,     0,     0,     0,   476,     0,     0,   475,   325,     0,
//yy   326,     0,   327,   328,   329,   330,   331,   332,   333,     0,
//yy   334,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   509,   510,     0,     0,   511,   476,     0,     0,   476,     0,
//yy     0,     0,   161,   162,     0,   163,   164,   165,   166,     0,
//yy   167,   168,   476,   476,   169,    87,     0,   476,     0,   170,
//yy   171,   172,   173,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   175,   176,     0,   177,   178,   179,   180,
//yy   181,   182,   183,   184,   185,   476,   186,   476,   187,     0,
//yy     0,     0,     0,     0,     0,   609,   310,   311,   312,   313,
//yy   314,   315,   316,   317,   318,   319,   320,     0,     0,     0,
//yy     0,     0,   323,   324,   335,     0,     0,     0,     0,   476,
//yy     0,     0,     0,     0,     0,   325,     0,   326,     0,   327,
//yy   328,   329,   330,   331,   332,   333,     0,   334,     0,   475,
//yy   475,   475,     0,   475,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,   475,   475,   475,   475,   475,   475,   475,
//yy   475,     0,   475,   475,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,   475,     0,   475,   475,   475,   475,   475,
//yy     0,   475,     0,     0,   475,   475,   475,   475,   475,   475,
//yy   475,   475,     0,   475,   475,   475,   475,   335,   475,   475,
//yy   475,   475,   475,   475,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,   475,   475,     0,     0,   475,   475,   475,
//yy   475,     0,   475,   475,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,   475,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,   475,     0,   475,   475,   475,   475,   475,
//yy   475,   476,   476,   476,     0,   476,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,   476,   476,   476,   476,   476,
//yy   476,   476,   476,     0,   476,   476,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,   476,     0,   476,   476,   476,
//yy   476,   476,     0,   476,     0,     0,   476,   476,   476,   476,
//yy   476,   476,   476,   476,     0,   476,   476,   476,   476,   335,
//yy   476,   476,   476,   476,   476,   476,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,   476,   476,     0,     0,   476,
//yy   476,   476,   476,     0,   476,   476,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,   476,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,   476,   479,   476,   476,   476,
//yy   476,   476,   476,     0,     0,     0,   479,     0,     0,     0,
//yy   310,   311,   312,   313,   314,   315,   316,   317,   318,   319,
//yy   320,     0,   321,   322,     0,     0,   323,   324,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   479,     0,   325,
//yy   479,   326,     0,   327,   328,   329,   330,   331,   332,   333,
//yy     0,   334,     0,     0,   479,   479,     0,    86,     0,   479,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   680,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   479,     0,   479,
//yy     0,     0,     0,   310,   311,   312,   313,   314,   315,   316,
//yy   317,   318,   319,   320,     0,   321,   322,     0,   275,   323,
//yy   324,     0,     0,     0,     0,     0,     0,     0,   275,     0,
//yy     0,   479,   325,     0,   326,     0,   327,   328,   329,   330,
//yy   331,   332,   333,     0,   334,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   514,   515,     0,     0,   516,   275,
//yy     0,     0,   275,     0,     0,     0,   161,   162,     0,   163,
//yy   164,   165,   166,     0,   167,   168,   275,   275,   169,     0,
//yy     0,   275,     0,   170,   171,   172,   173,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   175,   176,     0,
//yy   177,   178,   179,   180,   181,   182,   183,   184,   185,   275,
//yy   186,   275,   187,     0,     0,   310,   311,   312,   313,   314,
//yy   315,   316,   317,   318,   319,   320,     0,   321,   322,     0,
//yy     0,   323,   324,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   275,   325,     0,   326,     0,   327,   328,
//yy   329,   330,   331,   332,   333,     0,   334,     0,     0,     0,
//yy     0,     0,     0,   475,   475,   475,     0,   475,   479,   479,
//yy   479,   475,   475,   479,   479,   479,   475,   479,   475,   475,
//yy   475,   475,   475,   475,   475,     0,   479,   479,   479,   475,
//yy   475,   475,   475,   475,   475,   475,   479,   479,     0,   479,
//yy   479,   479,   479,   479,     0,   475,     0,     0,   475,   475,
//yy   475,   475,   475,   475,   475,   475,     0,   475,   475,   475,
//yy   475,     0,   475,   475,   475,   479,   479,   479,   479,   479,
//yy   479,   479,   479,   479,   479,   479,   479,   479,   479,     0,
//yy     0,   479,   479,   479,   475,     0,   479,   475,   475,   475,
//yy   475,   475,   479,   475,   479,   475,   479,   475,   479,   479,
//yy   479,   479,   479,   479,   479,   475,   479,   479,     0,   475,
//yy   475,   475,   475,   475,   475,   474,   474,   474,     0,   474,
//yy   275,   275,   275,   474,   474,   275,   275,   275,   474,   275,
//yy   474,   474,   474,   474,   474,   474,   474,     0,   474,   275,
//yy   275,   474,   474,   474,   474,   474,   474,   474,   275,   275,
//yy     0,   275,   275,   275,   275,   275,     0,   474,     0,     0,
//yy   474,   474,   474,   474,   474,   474,   474,   474,     0,   474,
//yy   474,   474,   474,     0,   474,   474,   474,   275,   275,   275,
//yy   275,   275,   275,   275,   275,   275,   275,   275,   275,   275,
//yy   275,     0,     0,   275,   275,   275,   474,     0,   275,   474,
//yy   474,   474,   474,   474,   275,   474,   275,   474,   275,   474,
//yy   275,   275,   275,   275,   275,   275,   275,   474,   275,   474,
//yy   480,   474,   474,   474,   474,   474,   474,     0,     0,     0,
//yy   480,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   480,     0,     0,   480,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     4,     5,     6,     0,     8,   480,   480,
//yy     0,     9,    10,   480,     0,     0,    11,     0,    12,    13,
//yy    14,    97,    98,    17,    18,     0,     0,     0,     0,    99,
//yy   100,   101,    22,    23,    24,    25,     0,     0,     0,     0,
//yy     0,   480,     0,   480,     0,   102,     0,     0,    31,    32,
//yy    33,    34,    35,    36,    37,    38,     0,    39,    40,    41,
//yy    42,     0,   481,     0,   105,     0,     0,     0,     0,     0,
//yy     0,     0,   481,     0,     0,   480,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   225,     0,     0,    47,     0,    48,
//yy    49,    50,     0,    51,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   481,   756,     0,   481,     0,     0,    54,
//yy    55,    56,    57,    58,    59,   521,   510,     0,     0,   522,
//yy   481,   481,     0,     0,     0,   481,     0,   161,   162,     0,
//yy   163,   164,   165,   166,     0,   167,   168,     0,     0,   169,
//yy     0,     0,     0,     0,   170,   171,   172,   173,     0,     0,
//yy     0,     0,     0,   481,     0,   481,     0,     0,   175,   176,
//yy     0,   177,   178,   179,   180,   181,   182,   183,   184,   185,
//yy     0,   186,     0,   187,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   481,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   477,   477,   477,
//yy     0,   477,   480,   480,   480,   477,   477,   480,   480,   480,
//yy   477,   480,   477,   477,   477,   477,   477,   477,   477,     0,
//yy   480,   480,   480,   477,   477,   477,   477,   477,   477,   477,
//yy   480,   480,     0,   480,   480,   480,   480,   480,     0,   477,
//yy     0,     0,   477,   477,   477,   477,   477,   477,   477,   477,
//yy     0,   477,   477,   477,   477,     0,   477,   477,   477,   480,
//yy   480,   480,   480,   480,   480,   480,   480,   480,   480,   480,
//yy   480,   480,   480,     0,     0,   480,   480,   480,   477,     0,
//yy   480,   477,   477,   477,   477,   477,   480,   477,   480,   477,
//yy   480,   477,   480,   480,   480,   480,   480,   480,   480,   477,
//yy   480,   480,     0,   477,   477,   477,   477,   477,   477,   478,
//yy   478,   478,     0,   478,   481,   481,   481,   478,   478,   481,
//yy   481,   481,   478,   481,   478,   478,   478,   478,   478,   478,
//yy   478,     0,   481,   481,   481,   478,   478,   478,   478,   478,
//yy   478,   478,   481,   481,     0,   481,   481,   481,   481,   481,
//yy     0,   478,     0,     0,   478,   478,   478,   478,   478,   478,
//yy   478,   478,     0,   478,   478,   478,   478,     0,   478,   478,
//yy   478,   481,   481,   481,   481,   481,   481,   481,   481,   481,
//yy   481,   481,   481,   481,   481,     0,     0,   481,   481,   481,
//yy   478,     0,   481,   478,   478,   478,   478,   478,   481,   478,
//yy   481,   478,   481,   478,   481,   481,   481,   481,   481,   481,
//yy   481,   478,   481,   481,   352,   478,   478,   478,   478,   478,
//yy   478,     0,     0,     0,   352,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   352,     0,     0,   352,     0,
//yy     0,     0,     0,     0,     0,     0,     4,     5,     6,     0,
//yy     8,     0,   352,   352,     9,    10,     0,   352,     0,    11,
//yy     0,    12,    13,    14,    97,    98,    17,    18,     0,     0,
//yy     0,     0,    99,   100,   101,    22,    23,    24,    25,     0,
//yy     0,     0,     0,     0,     0,   352,     0,   352,   102,     0,
//yy     0,    31,    32,   103,    34,    35,    36,   104,    38,     0,
//yy    39,    40,    41,    42,     0,     0,   495,   105,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   495,     0,     0,   352,
//yy     0,     0,     0,     0,   106,     0,     0,   107,     0,     0,
//yy   108,     0,    48,    49,    50,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   495,     0,     0,
//yy   495,     0,    54,    55,    56,    57,    58,    59,   548,   515,
//yy     0,     0,   549,     0,   495,   495,     0,     0,     0,   495,
//yy   161,   162,     0,   163,   164,   165,   166,     0,   167,   168,
//yy     0,     0,   169,     0,     0,     0,     0,   170,   171,   172,
//yy   173,     0,     0,     0,     0,     0,     0,   495,     0,   495,
//yy     0,   175,   176,     0,   177,   178,   179,   180,   181,   182,
//yy   183,   184,   185,     0,   186,     0,   187,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   495,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   252,   252,   252,     0,   252,   352,   352,   352,   252,
//yy   252,   352,   352,   352,   252,   352,   252,   252,   252,   252,
//yy   252,   252,   252,     0,   352,   352,   352,   252,   252,   252,
//yy   252,   252,   252,   252,   352,   352,     0,   352,   352,   352,
//yy   352,   352,     0,   252,     0,     0,   252,   252,   252,   252,
//yy   252,   252,   252,   252,     0,   252,   252,   252,   252,     0,
//yy   252,   252,   252,   352,   352,   352,   352,   352,   352,   352,
//yy   352,   352,   352,   352,   352,   352,   352,     0,     0,   352,
//yy   352,   352,   252,     0,   352,   252,     0,   252,   252,   252,
//yy   352,   252,   352,   252,   352,   252,   352,   352,   352,   352,
//yy   352,   352,   352,   252,   352,   352,     0,   252,   252,   252,
//yy   252,   252,   252,   252,   252,   252,     0,   252,   495,   495,
//yy   495,   252,   252,   495,   495,   495,   252,   495,   252,   252,
//yy   252,   252,   252,   252,   252,     0,   495,   495,   495,   252,
//yy   252,   252,   252,   252,   252,   252,   495,   495,     0,   495,
//yy   495,   495,   495,   495,     0,   252,     0,     0,   252,   252,
//yy   252,   252,   252,   252,   252,   252,     0,   252,   252,   252,
//yy   252,     0,   252,   252,   252,   495,   495,   495,   495,   495,
//yy   495,   495,   495,   495,   495,   495,   495,   495,   495,     0,
//yy     0,   495,   495,   495,   252,     0,   495,   252,     0,   252,
//yy   252,   252,   495,   252,   495,   252,   495,   252,   495,   495,
//yy   495,   495,   495,   495,   495,   252,   495,   495,   280,   252,
//yy   252,   252,   252,   252,   252,     0,     0,     0,   280,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   585,   510,     0,     0,   586,     0,     0,     0,     0,     0,
//yy     0,     0,   161,   162,     0,   163,   164,   165,   166,   280,
//yy   167,   168,   280,     0,   169,     0,     0,     0,     0,   170,
//yy   171,   172,   173,     0,     0,     0,   280,   280,     0,    88,
//yy     0,   280,     0,   175,   176,     0,   177,   178,   179,   180,
//yy   181,   182,   183,   184,   185,     0,   186,     0,   187,     0,
//yy     0,     0,   587,   515,     0,     0,   588,     0,     0,   280,
//yy     0,   280,     0,     0,   161,   162,     0,   163,   164,   165,
//yy   166,     0,   167,   168,     0,     0,   169,     0,     0,     0,
//yy   288,   170,   171,   172,   173,     0,     0,     0,     0,     0,
//yy   288,     0,     0,   280,     0,   175,   176,     0,   177,   178,
//yy   179,   180,   181,   182,   183,   184,   185,     0,   186,     0,
//yy   187,     0,     0,     0,     0,     0,   603,   510,     0,     0,
//yy   604,   288,     0,     0,   288,     0,     0,     0,   161,   162,
//yy     0,   163,   164,   165,   166,     0,   167,   168,   288,   288,
//yy   169,     0,     0,   288,     0,   170,   171,   172,   173,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   175,
//yy   176,     0,   177,   178,   179,   180,   181,   182,   183,   184,
//yy   185,   288,   186,   288,   187,   310,   311,   312,   313,   314,
//yy   315,   316,   317,     0,   319,   320,     0,     0,     0,     0,
//yy     0,   323,   324,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   325,   288,   326,     0,   327,   328,
//yy   329,   330,   331,   332,   333,     0,   334,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   476,   476,   476,     0,   476,
//yy   280,   280,   280,   476,   476,   280,   280,   280,   476,   280,
//yy   476,   476,   476,   476,   476,   476,   476,     0,     0,   280,
//yy   280,   476,   476,   476,   476,   476,   476,   476,   280,   280,
//yy     0,   280,   280,   280,   280,   280,     0,   476,     0,     0,
//yy   476,   476,   476,   476,   476,   476,   476,   476,     0,   476,
//yy   476,   476,   476,     0,   476,   476,   476,   280,   280,   280,
//yy   280,   280,   280,   280,   280,   280,   280,   280,   280,   280,
//yy   280,     0,     0,   280,   280,   280,   476,     0,   280,   476,
//yy   476,   476,   476,   476,   280,   476,   280,   476,   280,   476,
//yy   280,   280,   280,   280,   280,   280,   280,   476,   280,     0,
//yy     0,   476,   476,   476,   476,   476,   476,   252,   252,   252,
//yy     0,   252,   288,   288,   288,   252,   252,   288,   288,   288,
//yy   252,   288,   252,   252,   252,   252,   252,   252,   252,     0,
//yy     0,   288,   288,   252,   252,   252,   252,   252,   252,   252,
//yy   288,   288,     0,   288,   288,   288,   288,   288,     0,   252,
//yy     0,     0,   252,   252,   252,   252,   252,   252,   252,   252,
//yy     0,   252,   252,   252,   252,     0,   252,   252,   252,   288,
//yy   288,   288,   288,   288,   288,   288,   288,   288,   288,   288,
//yy   288,   288,   288,     0,     0,   288,   288,   288,   252,     0,
//yy   288,   252,     0,   252,   252,   252,   288,   252,   288,   252,
//yy   288,   252,   288,   288,   288,   288,   288,   288,   288,   252,
//yy   288,   475,     0,   252,   252,   252,   252,   252,   252,     0,
//yy     0,   475,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   605,   515,     0,     0,   606,     0,     0,
//yy     0,     0,     0,     0,     0,   161,   162,     0,   163,   164,
//yy   165,   166,   475,   167,   168,    77,     0,   169,     0,     0,
//yy     0,     0,   170,   171,   172,   173,     0,     0,     0,     0,
//yy   475,     0,    85,     0,   475,     0,   175,   176,     0,   177,
//yy   178,   179,   180,   181,   182,   183,   184,   185,     0,   186,
//yy     0,   187,     0,     0,     0,   647,   510,     0,     0,   648,
//yy     0,     0,   475,     0,     0,     0,     0,   161,   162,     0,
//yy   163,   164,   165,   166,     0,   167,   168,     0,     0,   169,
//yy     0,     0,     0,   476,   170,   171,   172,   173,     0,     0,
//yy     0,     0,     0,   476,     0,     0,   475,     0,   175,   176,
//yy     0,   177,   178,   179,   180,   181,   182,   183,   184,   185,
//yy     0,   186,     0,   187,     0,     0,     0,     0,     0,   649,
//yy   515,     0,     0,   650,   476,     0,     0,    79,     0,     0,
//yy     0,   161,   162,     0,   163,   164,   165,   166,     0,   167,
//yy   168,     0,   476,   169,    87,     0,   476,     0,   170,   171,
//yy   172,   173,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   175,   176,     0,   177,   178,   179,   180,   181,
//yy   182,   183,   184,   185,   476,   186,     0,   187,   310,   311,
//yy   312,   313,   314,   315,   316,     0,     0,   319,   320,     0,
//yy     0,     0,     0,     0,   323,   324,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   325,   476,   326,
//yy     0,   327,   328,   329,   330,   331,   332,   333,     0,   334,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   475,   475,
//yy   475,     0,   475,   475,   475,   475,   475,   475,     0,   475,
//yy   475,   475,   475,   475,   475,   475,   475,   475,   475,   475,
//yy     0,   475,     0,     0,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,     0,   475,   475,   475,   475,   475,     0,
//yy   475,     0,     0,   475,   475,   475,   475,   475,   475,   475,
//yy   475,     0,   475,   475,   475,   475,     0,   475,   475,   475,
//yy   475,   475,   475,   475,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,   475,     0,     0,   475,   475,   475,   475,
//yy     0,     0,   475,   475,   475,   475,   475,     0,   475,   475,
//yy   475,   475,   475,   475,   475,   475,   475,   475,   475,   475,
//yy   475,   475,   475,     0,   475,   475,   475,   475,   475,   475,
//yy   476,   476,   476,     0,   476,   476,   476,   476,   476,   476,
//yy     0,   476,   476,   476,   476,   476,   476,   476,   476,   476,
//yy   476,   476,     0,   476,     0,     0,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,     0,   476,   476,   476,   476,
//yy   476,     0,   476,     0,     0,   476,   476,   476,   476,   476,
//yy   476,   476,   476,     0,   476,   476,   476,   476,     0,   476,
//yy   476,   476,   476,   476,   476,   476,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,   476,     0,     0,   476,   476,
//yy   476,   476,     0,     0,   476,   476,   476,   476,   476,     0,
//yy   476,   476,   476,   476,   476,   476,   476,   476,   476,   476,
//yy   476,   476,   476,   476,   476,   479,   476,   476,   476,   476,
//yy   476,   476,     0,     0,     0,   479,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   860,   510,     0,
//yy     0,   861,     0,     0,     0,     0,     0,     0,     0,   161,
//yy   162,     0,   163,   164,   165,   166,   479,   167,   168,    78,
//yy     0,   169,     0,     0,     0,     0,   170,   171,   172,   173,
//yy     0,     0,     0,     0,   479,     0,    86,     0,   479,     0,
//yy   175,   176,     0,   177,   178,   179,   180,   181,   182,   183,
//yy   184,   185,     0,   186,     0,   187,     0,     0,     0,   862,
//yy   515,     0,     0,   863,     0,     0,   479,     0,     0,     0,
//yy     0,   161,   162,     0,   163,   164,   165,   166,     0,   167,
//yy   168,     0,     0,   169,     0,     0,     0,   280,   170,   171,
//yy   172,   173,     0,     0,     0,     0,     0,   280,     0,     0,
//yy   479,     0,   175,   176,     0,   177,   178,   179,   180,   181,
//yy   182,   183,   184,   185,     0,   186,     0,   187,   310,     0,
//yy     0,     0,     0,   315,   316,     0,     0,     0,   280,     0,
//yy     0,    80,     0,     0,   323,   324,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   280,   325,    88,   326,
//yy   280,   327,   328,   329,   330,   331,   332,   333,     0,   334,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   280,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   280,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   475,   475,   475,     0,   475,   479,   479,   479,
//yy   475,   475,     0,   479,   479,   475,   479,   475,   475,   475,
//yy   475,   475,   475,   475,     0,   479,     0,     0,   475,   475,
//yy   475,   475,   475,   475,   475,   479,   479,     0,   479,   479,
//yy   479,   479,   479,     0,   475,     0,     0,   475,   475,   475,
//yy   475,   475,   475,   475,   475,     0,   475,   475,   475,   475,
//yy     0,   475,   475,   475,   479,   479,   479,   479,   479,   479,
//yy   479,   479,   479,   479,   479,   479,   479,   479,     0,     0,
//yy   479,   479,   479,   475,     0,     0,   475,   475,   475,   475,
//yy   475,     0,   475,   479,   475,   479,   475,   479,   479,   479,
//yy   479,   479,   479,   479,   475,   479,   479,     0,   475,   475,
//yy   475,   475,   475,   475,   476,   476,   476,     0,   476,   280,
//yy   280,   280,   476,   476,     0,   280,   280,   476,   280,   476,
//yy   476,   476,   476,   476,   476,   476,     0,     0,     0,     0,
//yy   476,   476,   476,   476,   476,   476,   476,   280,   280,     0,
//yy   280,   280,   280,   280,   280,     0,   476,     0,     0,   476,
//yy   476,   476,   476,   476,   476,   476,   476,     0,   476,   476,
//yy   476,   476,     0,   476,   476,   476,   280,   280,   280,   280,
//yy   280,   280,   280,   280,   280,   280,   280,   280,   280,   280,
//yy     0,     0,   280,   280,   280,   476,     0,     0,   476,   476,
//yy   476,   476,   476,     0,   476,   280,   476,   280,   476,   280,
//yy   280,   280,   280,   280,   280,   280,   476,   280,   495,     0,
//yy   476,   476,   476,   476,   476,   476,     0,     0,   495,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   495,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   495,   495,    32,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,    32,     8,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     8,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    32,
//yy     0,     0,   262,     0,     0,   352,     0,     0,     0,     0,
//yy     8,     0,     0,     0,     0,   352,     0,    32,     0,     0,
//yy    35,     0,     0,   495,     0,     0,     0,     0,     8,     0,
//yy    35,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   352,     0,     0,   352,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    35,     0,   352,   352,     0,     0,     0,   352,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    35,
//yy     0,     0,     0,    32,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     8,     0,   352,     0,   352,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   352,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    35,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   252,   252,   252,     0,   252,
//yy   495,   495,   495,   252,   252,   495,   495,   495,   252,   495,
//yy   252,   252,   252,   252,   252,   252,   252,     0,     0,   495,
//yy     0,   252,   252,   252,   252,   252,   252,   252,   495,   495,
//yy     0,   495,   495,   495,   495,   495,     0,   252,     0,     0,
//yy   252,   252,   252,   252,   252,   252,   252,   252,     0,   252,
//yy   252,   252,   252,     0,   252,   252,   252,     0,     0,     0,
//yy    32,    32,    32,     0,     0,     0,    32,    32,   495,    32,
//yy     0,     8,     8,     8,     0,   495,   252,     8,     8,   252,
//yy     8,   252,   252,   252,     0,   252,     0,   252,     0,   252,
//yy     0,    32,    32,    32,    32,    32,     0,   252,     0,     0,
//yy     0,   252,   252,   252,   252,   252,   252,   352,   352,   352,
//yy     0,     0,   352,   352,   352,     0,   352,     0,     0,     0,
//yy    34,     0,    35,    35,    35,   352,   352,   352,    35,    35,
//yy    34,    35,     0,     0,     0,   352,   352,     0,   352,   352,
//yy   352,   352,   352,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    35,    35,    35,    35,    35,     0,     0,
//yy     0,    34,     0,     0,   352,   352,   352,   352,   352,   352,
//yy   352,   352,   352,   352,   352,   352,   352,   352,   495,    34,
//yy   352,   352,   352,     0,     0,   352,     0,     0,   495,     0,
//yy     0,   352,     0,   352,     0,   352,     0,   352,   352,   352,
//yy   352,   352,   352,   352,     0,   352,   352,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     7,   495,
//yy     0,     0,   495,     0,     0,     0,     0,     0,     7,     0,
//yy     0,     0,     0,     0,     0,     0,   495,   495,     0,     0,
//yy     0,   495,     0,     0,     0,    34,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     7,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   495,
//yy     0,   495,     0,     0,     0,     0,     0,     7,   425,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   425,     0,
//yy     0,     0,     0,     0,    14,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   495,    14,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   425,
//yy     0,     0,   425,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    14,   425,   425,     0,    83,
//yy     0,   425,     0,     7,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    14,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   425,
//yy     0,   425,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,    34,    34,    34,     0,     0,     0,    34,    34,
//yy     0,    34,   274,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   274,   425,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    34,    34,    34,    34,    34,     0,    14,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   274,     0,     0,   274,     0,     0,     0,
//yy   495,   495,   495,     0,     0,   495,   495,   495,     0,   495,
//yy   274,   274,     0,    90,     0,   274,     0,     0,   495,   495,
//yy   495,     0,     0,     0,     0,     0,     0,     0,   495,   495,
//yy     0,   495,   495,   495,   495,   495,     0,     0,     0,     0,
//yy     7,     7,     7,   274,     0,   274,     7,     7,     0,     7,
//yy     0,     0,     0,     0,     0,     0,     0,   495,   495,   495,
//yy   495,   495,   495,   495,   495,   495,   495,   495,   495,   495,
//yy   495,     0,     0,   495,   495,   495,     0,   274,   495,     0,
//yy     0,     0,     0,     0,   495,     0,   495,     0,   495,     0,
//yy   495,   495,   495,   495,   495,   495,   495,     0,   495,   495,
//yy   425,   425,   425,     0,     0,   425,   425,   425,     0,   425,
//yy     0,     0,     0,     0,     0,     0,    14,    14,    14,   425,
//yy   425,     0,    14,    14,     0,    14,     0,     0,   425,   425,
//yy     0,   425,   425,   425,   425,   425,     0,     0,     0,     0,
//yy     0,     0,     0,     0,    41,     0,     0,    14,    14,    14,
//yy    14,    14,     0,     0,    41,     0,     0,   425,   425,   425,
//yy   425,   425,   425,   425,   425,   425,   425,   425,   425,   425,
//yy   425,     0,     0,   425,   425,   425,     0,   426,   425,     0,
//yy     0,     0,     0,     0,   425,    41,   425,     0,   425,     0,
//yy   425,   425,   425,   425,   425,   425,   425,     0,   425,     0,
//yy   281,     0,    41,    41,     0,     0,     0,     0,     0,     0,
//yy   281,     0,     0,     0,   274,   274,   274,     0,     0,   274,
//yy   274,   274,     0,   274,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   274,   274,     0,     0,     0,     0,     0,
//yy     0,   281,   274,   274,   281,   274,   274,   274,   274,   274,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   281,   281,
//yy     0,    89,     0,   281,     0,     0,     0,     0,     0,    41,
//yy     0,   274,   274,   274,   274,   274,   274,   274,   274,   274,
//yy   274,   274,   274,   274,   274,     0,     0,   274,   274,   274,
//yy     0,   281,   274,   281,     0,     0,     0,     0,   274,     0,
//yy   274,   282,   274,     0,   274,   274,   274,   274,   274,   274,
//yy   274,   282,   274,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   281,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   282,     0,     0,   282,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   282,
//yy   282,     0,    84,     0,   282,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   282,     0,   282,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,    41,    41,    41,     0,
//yy     0,    41,    41,    41,   375,    41,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   375,    41,   282,     0,     0,     0,
//yy     0,     0,     0,     0,    41,    41,     0,    41,    41,    41,
//yy    41,    41,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   375,     0,     0,   375,     0,
//yy     0,     0,   281,   281,   281,     0,     0,   281,   281,   281,
//yy     0,   281,   375,   375,     0,     0,     0,   375,     0,     0,
//yy     0,   281,   281,     0,     0,     0,     0,     0,     0,     0,
//yy   281,   281,     0,   281,   281,   281,   281,   281,     0,     0,
//yy     0,     0,     0,     0,     0,   375,     0,   375,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   281,
//yy   281,   281,   281,   281,   281,   281,   281,   281,   281,   281,
//yy   281,   281,   281,     0,     0,   281,   281,   281,     0,   375,
//yy   281,     0,     0,     0,     0,     0,   281,     0,   281,   217,
//yy   281,     0,   281,   281,   281,   281,   281,   281,   281,   217,
//yy   281,     0,     0,   282,   282,   282,     0,     0,   282,   282,
//yy   282,     0,   282,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   282,   282,     0,     0,     0,     0,     0,     0,
//yy   217,   282,   282,   217,   282,   282,   282,   282,   282,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   217,   217,     0,
//yy     0,     0,   217,     0,     0,     0,     0,     0,     0,     0,
//yy   282,   282,   282,   282,   282,   282,   282,   282,   282,   282,
//yy   282,   282,   282,   282,     0,     0,   282,   282,   282,     0,
//yy   324,   282,   217,     0,     0,     0,     0,   282,     0,   282,
//yy     0,   282,     0,   282,   282,   282,   282,   282,   282,   282,
//yy     0,   282,   292,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   292,     0,   217,     0,   375,   375,   375,     0,
//yy     0,   375,   375,   375,     0,   375,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   375,   375,     0,     0,     0,
//yy     0,     0,     0,   292,   375,   375,   292,   375,   375,   375,
//yy   375,   375,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   292,   292,     0,     0,     0,   292,     0,     0,     0,     0,
//yy     0,     0,     0,   375,   375,   375,   375,   375,   375,   375,
//yy   375,   375,   375,   375,   375,   375,   375,     0,     0,   375,
//yy   375,   375,     0,   292,   375,   292,     0,     0,     0,     0,
//yy   375,     0,   375,   288,   375,     0,   375,   375,   375,   375,
//yy   375,   375,   375,   288,   375,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   292,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   288,     0,     0,   288,     0,     0,
//yy     0,   217,   217,   217,     0,     0,   217,   217,   217,     0,
//yy   217,   288,   288,     0,     0,     0,   288,     0,     0,     0,
//yy   217,   217,     0,     0,     0,     0,     0,     0,     0,   217,
//yy   217,     0,   217,   217,   217,   217,   217,     0,     0,     0,
//yy     0,     0,     0,     0,   288,     0,   288,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   217,   217,
//yy   217,   217,   217,   217,   217,   217,   217,   217,   217,   324,
//yy   217,   217,     0,     0,   217,   217,   324,     0,   288,   217,
//yy     0,     0,     0,     0,     0,   217,     0,   217,     0,   217,
//yy     0,   217,   217,   217,   217,   217,   217,   217,     0,   217,
//yy   412,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   412,     0,     0,     0,   292,   292,   292,     0,     0,   292,
//yy   292,   292,     0,   292,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   292,   292,     0,     0,     0,     0,     0,
//yy     0,   412,   292,   292,   412,   292,   292,   292,   292,   292,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   412,   412,
//yy     0,     0,     0,   412,     0,     0,     0,     0,     0,     0,
//yy     0,   292,   292,   292,   292,   292,   292,   292,   292,   292,
//yy   292,   292,   292,   292,   292,     0,     0,   292,   292,   292,
//yy     0,   412,   292,   412,     0,     0,     0,     0,   292,     0,
//yy   292,   413,   292,     0,   292,   292,   292,   292,   292,   292,
//yy   292,   413,   292,     0,     0,   288,   288,   288,     0,     0,
//yy   288,   288,   288,     0,   288,   412,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   288,   288,     0,     0,     0,     0,
//yy     0,     0,   413,   288,   288,   413,   288,   288,   288,   288,
//yy   288,     0,     0,     0,     0,     0,     0,     0,     0,   413,
//yy   413,     0,     0,     0,   413,     0,     0,     0,     0,     0,
//yy     0,     0,   288,   288,   288,   288,   288,   288,   288,   288,
//yy   288,   288,   288,   288,   288,   288,     0,     0,   288,   288,
//yy   288,     0,   413,   288,   413,     0,     0,     0,     0,   288,
//yy     0,   288,     0,   288,     0,   288,   288,   288,   288,   288,
//yy   288,   288,     0,   288,   320,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   320,     0,   413,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   320,     0,     0,   320,     0,
//yy     0,     0,   412,   412,   412,     0,     0,   412,   412,   412,
//yy     0,   412,   320,   320,     0,     0,     0,   320,     0,     0,
//yy     0,   412,   412,     0,     0,     0,     0,     0,     0,     0,
//yy   412,   412,     0,   412,   412,   412,   412,   412,     0,     0,
//yy     0,     0,     0,     0,     0,   320,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   412,   412,   412,   412,   412,   412,   412,   412,   412,   412,
//yy   412,   412,   412,     0,     0,   412,   412,   412,     0,   320,
//yy   412,     0,     0,     0,     0,     0,   412,     0,   412,   321,
//yy   412,     0,   412,   412,   412,   412,   412,   412,   412,   321,
//yy   412,     0,     0,   413,   413,   413,     0,     0,   413,   413,
//yy   413,     0,   413,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   413,   413,     0,     0,     0,     0,     0,     0,
//yy   321,   413,   413,   321,   413,   413,   413,   413,   413,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   321,   321,     0,
//yy     0,     0,   321,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   413,   413,   413,   413,   413,   413,   413,   413,   413,
//yy   413,   413,   413,   413,     0,     0,   413,   413,   413,     0,
//yy   321,   413,     0,     0,     0,     0,     0,   413,     0,   413,
//yy     0,   413,     0,   413,   413,   413,   413,   413,   413,   413,
//yy     0,   413,   285,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   285,     0,   321,     0,   320,   320,   320,     0,
//yy     0,   320,   320,   320,     0,   320,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   320,     0,     0,     0,     0,
//yy     0,     0,     0,   285,   320,   320,   285,   320,   320,   320,
//yy   320,   320,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   285,   285,     0,     0,     0,   285,     0,     0,     0,     0,
//yy     0,     0,     0,   320,   320,   320,   320,   320,   320,   320,
//yy   320,   320,   320,   320,   320,   320,   320,     0,     0,   320,
//yy   320,   320,     0,   285,   320,     0,     0,     0,     0,     0,
//yy     0,     0,   320,   194,   320,     0,   320,   320,   320,   320,
//yy   320,   320,   320,   194,   320,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   285,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   194,     0,     0,   194,     0,     0,
//yy     0,   321,   321,   321,     0,     0,   321,   321,   321,     0,
//yy   321,   194,   194,     0,     0,     0,   194,     0,     0,     0,
//yy   321,     0,     0,     0,     0,     0,     0,     0,     0,   321,
//yy   321,     0,   321,   321,   321,   321,   321,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   194,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   321,   321,
//yy   321,   321,   321,   321,   321,   321,   321,   321,   321,   321,
//yy   321,   321,     0,     0,   321,   321,   321,     0,   194,   321,
//yy     0,     0,     0,     0,     0,     0,     0,   321,     0,   321,
//yy     0,   321,   321,   321,   321,   321,   321,   321,     0,   321,
//yy   190,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   190,     0,     0,     0,   285,   285,   285,     0,     0,   285,
//yy   285,   285,     0,   285,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   285,     0,     0,     0,     0,     0,     0,
//yy     0,   190,   285,   285,   190,   285,   285,   285,   285,   285,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   190,   190,
//yy     0,     0,     0,   190,     0,     0,     0,     0,     0,     0,
//yy     0,   285,   285,   285,   285,   285,   285,   285,   285,   285,
//yy   285,   285,   285,   285,   285,     0,     0,   285,   285,   285,
//yy     0,     0,   285,   190,     0,     0,     0,     0,     0,     0,
//yy   285,   187,   285,     0,   285,   285,   285,   285,   285,   285,
//yy   285,   187,   285,     0,     0,   194,   194,   194,     0,     0,
//yy   194,   194,   194,     0,   194,   190,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   194,   194,     0,     0,     0,     0,
//yy     0,     0,   187,   194,   194,   187,   194,   194,   194,   194,
//yy   194,     0,     0,     0,     0,     0,     0,     0,     0,   187,
//yy   187,     0,     0,     0,   187,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   194,   194,   194,   194,   194,   194,   194,
//yy   194,   194,   194,     0,   194,   194,     0,     0,   194,   194,
//yy     0,     0,     0,   194,   187,     0,     0,     0,     0,   194,
//yy     0,   194,     0,   194,     0,   194,   194,   194,   194,   194,
//yy   194,   194,     0,   194,   189,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   189,     0,   187,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   189,     0,     0,   189,     0,
//yy     0,     0,   190,   190,   190,     0,     0,   190,   190,   190,
//yy     0,   190,   189,   189,     0,     0,     0,   189,     0,     0,
//yy     0,   190,   190,     0,     0,     0,     0,     0,     0,     0,
//yy   190,   190,     0,   190,   190,   190,   190,   190,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   189,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   190,   190,   190,   190,   190,   190,   190,   190,   190,   190,
//yy     0,   190,   190,     0,     0,   190,   190,     0,     0,   189,
//yy   190,     0,     0,     0,     0,     0,   190,     0,   190,   188,
//yy   190,     0,   190,   190,   190,   190,   190,   190,   190,   188,
//yy   190,     0,     0,   187,   187,   187,     0,     0,   187,   187,
//yy   187,     0,   187,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   187,   187,     0,     0,     0,     0,     0,     0,
//yy   188,   187,   187,   188,   187,   187,   187,   187,   187,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   188,   188,     0,
//yy     0,     0,   188,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   187,   187,   187,   187,   187,   187,   187,   187,   187,
//yy   187,     0,   187,   187,     0,     0,   187,   187,     0,     0,
//yy     0,   187,   188,     0,     0,     0,     0,   187,     0,   187,
//yy     0,   187,     0,   187,   187,   187,   187,   187,   187,   187,
//yy     0,   187,   191,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   191,     0,   188,     0,   189,   189,   189,     0,
//yy     0,   189,   189,   189,     0,   189,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   189,   189,     0,     0,     0,
//yy     0,     0,     0,   191,   189,   189,   191,   189,   189,   189,
//yy   189,   189,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   191,   191,     0,     0,     0,   191,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   189,   189,   189,   189,   189,   189,
//yy   189,   189,   189,   189,     0,   189,   189,     0,     0,   189,
//yy   189,     0,     0,     0,   189,   191,     0,     0,     0,     0,
//yy   189,     0,   189,   192,   189,     0,   189,   189,   189,   189,
//yy   189,   189,   189,   192,   189,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   191,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   192,     0,     0,   192,     0,     0,
//yy     0,   188,   188,   188,     0,     0,   188,   188,   188,     0,
//yy   188,   192,   192,     0,     0,     0,   192,     0,     0,     0,
//yy   188,   188,     0,     0,     0,     0,     0,     0,     0,   188,
//yy   188,     0,   188,   188,   188,   188,   188,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   192,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   188,
//yy   188,   188,   188,   188,   188,   188,   188,   188,   188,     0,
//yy   188,   188,     0,     0,   188,   188,     0,     0,   192,   188,
//yy     0,     0,     0,     0,     0,   188,     0,   188,     0,   188,
//yy     0,   188,   188,   188,   188,   188,   188,   188,     0,   188,
//yy   425,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   425,     0,     0,     0,   191,   191,   191,     0,     0,   191,
//yy   191,   191,     0,   191,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   191,   191,     0,     0,     0,     0,     0,
//yy     0,   425,   191,   191,    75,   191,   191,   191,   191,   191,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   425,
//yy     0,    83,     0,   425,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   191,   191,   191,   191,   191,   191,   191,   191,
//yy   191,   191,     0,   191,   191,     0,     0,   191,   191,     0,
//yy     0,   425,   191,     0,     0,     0,     0,     0,   191,     0,
//yy   191,   274,   191,     0,   191,   191,   191,   191,   191,   191,
//yy   191,   274,   191,     0,     0,   192,   192,   192,     0,     0,
//yy   192,   192,   192,     0,   192,   425,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   192,   192,     0,     0,     0,     0,
//yy     0,     0,   274,   192,   192,    82,   192,   192,   192,   192,
//yy   192,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   274,     0,    90,     0,   274,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   192,   192,   192,   192,   192,   192,   192,
//yy   192,   192,   192,     0,   192,   192,     0,     0,   192,   192,
//yy     0,     0,   274,   192,     0,     0,     0,     0,     0,   192,
//yy     0,   192,     0,   192,     0,   192,   192,   192,   192,   192,
//yy   192,   192,     0,   192,     0,   281,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   281,   274,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   281,     0,     0,    81,
//yy     0,     0,   425,   425,   425,     0,     0,     0,   425,   425,
//yy     0,   425,     0,     0,   281,     0,    89,     0,   281,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   425,   425,     0,   425,   425,   425,   425,   425,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   281,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   425,
//yy   425,   425,   425,   425,   425,   425,   425,   425,   425,   425,
//yy   425,   425,   425,     0,     0,   425,   425,   425,     0,   426,
//yy   281,     0,     0,     0,     0,     0,     0,     0,   425,   282,
//yy   425,     0,   425,   425,   425,   425,   425,   425,   425,   282,
//yy   425,     0,     0,   274,   274,   274,     0,     0,     0,   274,
//yy   274,     0,   274,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   282,   274,   274,    76,   274,   274,   274,   274,   274,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   282,     0,
//yy    84,     0,   282,     0,     0,     0,     0,     0,     0,     0,
//yy   274,   274,   274,   274,   274,   274,   274,   274,   274,   274,
//yy   274,   274,   274,   274,     0,     0,   274,   274,   274,     0,
//yy   282,     0,     0,     0,     0,     0,     0,     0,     0,   274,
//yy     0,   274,     0,   274,   274,   274,   274,   274,   274,   274,
//yy   185,   274,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   185,     0,     0,     0,   282,     0,     0,   281,   281,   281,
//yy     0,     0,     0,   281,   281,     0,   281,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   185,     0,     0,   185,   281,   281,     0,   281,   281,
//yy   281,   281,   281,     0,     0,     0,     0,     0,   185,   185,
//yy     0,     0,     0,   185,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   281,   281,   281,   281,   281,   281,
//yy   281,   281,   281,   281,   281,   281,   281,   281,     0,     0,
//yy   281,   281,   281,   185,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   281,   186,   281,     0,   281,   281,   281,
//yy   281,   281,   281,   281,   186,   281,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   185,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   186,     0,     0,   186,     0,
//yy     0,   282,   282,   282,     0,     0,     0,   282,   282,     0,
//yy   282,     0,   186,   186,     0,     0,     0,   186,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   282,
//yy   282,     0,   282,   282,   282,   282,   282,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   186,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   282,   282,
//yy   282,   282,   282,   282,   282,   282,   282,   282,   282,   282,
//yy   282,   282,     0,     0,   282,   282,   282,     0,     0,   186,
//yy     0,     0,     0,     0,     0,     0,     0,   282,     0,   282,
//yy     0,   282,   282,   282,   282,   282,   282,   282,     0,   282,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   185,   185,   185,     0,     0,   185,   185,   185,
//yy     0,   185,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   185,   185,     0,     0,     0,     0,     0,     0,   210,
//yy   185,   185,     0,   185,   185,   185,   185,   185,     0,   210,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   185,   185,   185,   185,   185,   185,   185,   185,   185,   185,
//yy   210,   185,   185,   210,     0,   185,   185,     0,     0,     0,
//yy   185,     0,     0,     0,     0,     0,   185,   210,   210,     0,
//yy   185,     0,   210,     0,   185,   185,   185,   185,   185,     0,
//yy   185,     0,     0,     0,     0,     0,   186,   186,   186,     0,
//yy     0,   186,   186,   186,     0,   186,     0,     0,     0,     0,
//yy     0,     0,   210,     0,     0,   186,   186,     0,     0,     0,
//yy     0,     0,     0,   211,   186,   186,     0,   186,   186,   186,
//yy   186,   186,     0,   211,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   210,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   186,   186,   186,   186,   186,   186,
//yy   186,   186,   186,   186,   211,   186,   186,   211,     0,   186,
//yy   186,     0,     0,     0,   186,     0,     0,     0,     0,     0,
//yy   186,   211,   211,     0,   186,     0,   211,     0,   186,   186,
//yy   186,   186,   186,     0,   186,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   197,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   197,     0,     0,   211,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   197,     0,     0,   197,   211,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   197,   197,     0,     0,     0,   197,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   210,   210,   210,     0,     0,   210,   210,   210,     0,
//yy   210,     0,     0,     0,     0,     0,   197,     0,     0,     0,
//yy   210,   210,     0,     0,     0,     0,     0,     0,   195,   210,
//yy   210,     0,   210,   210,   210,   210,   210,     0,   195,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   197,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   210,
//yy   210,   210,   210,   210,   210,   210,   210,   210,   210,   195,
//yy   210,   210,   195,     0,   210,   210,     0,     0,     0,   210,
//yy     0,     0,     0,     0,     0,   210,   195,   195,     0,   210,
//yy     0,   195,     0,     0,     0,   210,   210,   210,     0,   210,
//yy     0,     0,     0,     0,     0,   211,   211,   211,     0,     0,
//yy   211,   211,   211,     0,   211,     0,     0,     0,     0,     0,
//yy     0,   195,     0,     0,   211,   211,     0,     0,     0,     0,
//yy     0,     0,     0,   211,   211,     0,   211,   211,   211,   211,
//yy   211,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   195,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   211,   211,   211,   211,   211,   211,   211,
//yy   211,   211,   211,     0,   211,   211,     0,     0,   211,   211,
//yy     0,     0,     0,   211,     0,   197,   197,   197,     0,   211,
//yy   197,   197,   197,   211,   197,     0,     0,     0,     0,   211,
//yy   211,   211,     0,   211,   197,   197,     0,     0,     0,     0,
//yy     0,     0,   196,   197,   197,     0,   197,   197,   197,   197,
//yy   197,     0,   196,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   197,   197,   197,   197,   197,   197,   197,
//yy   197,   197,   197,   196,   197,   197,   196,     0,     0,     0,
//yy     0,     0,     0,   197,     0,     0,     0,     0,     0,   197,
//yy   196,   196,     0,   197,     0,   196,     0,     0,     0,   197,
//yy   197,   197,     0,   197,     0,     0,     0,     0,     0,     0,
//yy   195,   195,   195,     0,     0,   195,   195,   195,     0,   195,
//yy     0,     0,     0,     0,     0,   196,     0,     0,     0,   195,
//yy   195,     0,     0,     0,     0,     0,     0,     0,   195,   195,
//yy     0,   195,   195,   195,   195,   195,     0,    91,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   196,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   195,   195,
//yy   195,   195,   195,   195,   195,   195,   195,   195,     0,   195,
//yy   195,     0,     0,     0,     0,     0,     0,     0,   195,     0,
//yy     0,     0,     0,     0,   195,     0,    91,     0,     0,     0,
//yy     0,     0,     0,     0,   195,   195,   195,     0,   195,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   414,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy    92,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   196,   196,   196,     0,     0,   196,
//yy   196,   196,     0,   196,     0,     0,     0,     0,     0,    92,
//yy     0,     0,     0,   196,   196,     0,     0,     0,     0,     0,
//yy     0,     0,   196,   196,     0,   196,   196,   196,   196,   196,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   417,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   196,   196,   196,   196,   196,   196,   196,   196,
//yy   196,   196,     0,   196,   196,     0,     0,     0,     0,     0,
//yy     0,     0,   196,     0,     0,     0,     0,     0,   196,    91,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   196,   196,
//yy   196,     0,   196,    91,    91,    91,    91,    91,    91,    91,
//yy    91,    91,    91,    91,     0,     0,    91,    91,     0,    91,
//yy    91,    91,    91,    91,    91,    91,     0,   472,     0,     0,
//yy    91,    91,    91,    91,    91,    91,    91,     0,    91,    91,
//yy     0,     0,     0,     0,     0,    91,    91,    91,    91,    91,
//yy    91,    91,    91,    91,    91,    91,    91,     0,    91,    91,
//yy    91,    91,     0,    91,    91,    91,     0,     0,     0,     0,
//yy   479,     0,     0,     0,     0,     0,     0,   414,     0,     0,
//yy     0,     0,     0,     0,   414,    91,     0,     0,    91,   472,
//yy    91,    91,    91,     0,    91,     0,     0,     0,    91,     0,
//yy     0,     0,    92,    91,     0,     0,    91,     0,   472,     0,
//yy    91,    91,    91,    91,    91,    91,    92,    92,    92,    92,
//yy    92,    92,    92,    92,    92,    92,    92,     0,     0,    92,
//yy    92,     0,    92,    92,    92,    92,    92,    92,    92,     0,
//yy   473,     0,     0,    92,    92,    92,    92,    92,    92,    92,
//yy     0,    92,    92,     0,     0,     0,     0,     0,    92,    92,
//yy    92,    92,    92,    92,    92,    92,    92,    92,    92,    92,
//yy     0,    92,    92,    92,    92,     0,    92,    92,    92,     0,
//yy     0,     0,     0,   280,     0,     0,     0,     0,     0,     0,
//yy   417,     0,     0,     0,     0,     0,     0,   417,    92,     0,
//yy     0,    92,   473,    92,    92,    92,     0,    92,     0,     0,
//yy     0,    92,     0,     0,     0,     0,    92,     0,     0,    92,
//yy    92,   473,     0,    92,    92,    92,    92,    92,    92,     0,
//yy     0,     0,     0,     0,     0,    91,    91,    91,    91,    91,
//yy    91,    91,    91,    91,    91,    91,     0,     0,    91,    91,
//yy     0,    91,    91,    91,    91,    91,    91,    91,     0,   479,
//yy     0,     0,    91,    91,    91,    91,    91,    91,    91,    92,
//yy     0,    91,     0,     0,     0,     0,     0,    91,    91,    91,
//yy    91,    91,    91,    91,    91,    91,    91,    91,    91,     0,
//yy    91,    91,    91,    91,     0,    91,    91,    91,     0,     0,
//yy     0,   281,     0,     0,     0,     0,     0,     0,     0,   479,
//yy     0,     0,     0,     0,     0,     0,   479,    91,     0,     0,
//yy    91,   475,    91,    91,    91,     0,    91,     0,     0,     0,
//yy    91,     0,     0,   325,     0,    91,     0,     0,    91,     0,
//yy   479,     0,    91,    91,    91,    91,    91,    91,    92,    92,
//yy    92,    92,    92,    92,    92,    92,    92,    92,    92,     0,
//yy     0,    92,    92,     0,    92,    92,    92,    92,    92,    92,
//yy    92,     0,     0,     0,     0,    92,    92,    92,    92,    92,
//yy    92,    92,   325,     0,    92,     0,     0,     0,     0,     0,
//yy    92,    92,    92,    92,    92,    92,    92,    92,    92,    92,
//yy    92,    92,     0,    92,    92,    92,    92,     0,    92,    92,
//yy    92,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   280,     0,     0,     0,     0,     0,     0,   280,
//yy    92,     0,     0,    92,   476,    92,    92,    92,     0,    92,
//yy     0,     0,     0,    92,     0,     0,     0,     0,    92,     0,
//yy     0,    92,   495,     0,     0,    92,    92,    92,    92,    92,
//yy    92,     0,     0,     0,     0,     0,    92,    92,    92,    92,
//yy    92,    92,    92,    92,    92,    92,    92,     0,     0,    92,
//yy    92,     0,    92,    92,    92,    92,    92,    92,    92,     0,
//yy     0,     0,     0,    92,    92,    92,    92,    92,    92,    92,
//yy     0,   495,    92,     0,     0,     0,     0,     0,    92,    92,
//yy    92,    92,    92,    92,    92,    92,    92,    92,    92,    92,
//yy     0,    92,    92,    92,    92,     0,    92,    92,    92,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   281,     0,     0,     0,     0,     0,     0,   281,    92,     0,
//yy     0,    92,     0,    92,    92,    92,     0,    92,     0,     0,
//yy     0,    92,     0,     0,     0,   495,    92,     0,     0,    92,
//yy     0,     0,     0,    92,    92,    92,    92,    92,    92,   325,
//yy   325,   325,   325,   325,   325,   325,   325,   325,   325,   325,
//yy     0,   325,   325,   325,   325,   325,   325,   325,   325,   325,
//yy   325,   325,     0,     0,     0,     0,   325,   325,   325,   325,
//yy   325,   325,   325,     0,   495,   325,     0,     0,     0,     0,
//yy     0,   325,   325,   325,   325,   325,   325,   325,   325,   325,
//yy   325,   325,   325,     0,   325,   325,   325,   325,     0,   325,
//yy   325,   325,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   325,     0,     0,   325,     0,   325,   325,   325,     0,
//yy   325,     0,     0,     0,   325,     0,     0,     0,     0,     0,
//yy   495,     0,   325,   217,     0,     0,   325,   325,   325,   325,
//yy   325,   325,     0,     0,     0,     0,     0,     0,   495,   495,
//yy   495,   495,   495,   495,     0,     0,   495,   495,   495,     0,
//yy     0,     0,   495,     0,   495,   495,   495,   495,   495,   495,
//yy   495,     0,     0,     0,     0,   495,   495,   495,   495,   495,
//yy   495,   495,   218,     0,   495,     0,     0,     0,     0,     0,
//yy   495,   495,   495,   495,   495,   495,   495,   495,   495,   495,
//yy   495,   495,     0,   495,   495,   495,   495,     0,   495,   495,
//yy   495,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   495,     0,     0,   495,     0,   495,   495,   495,     0,   495,
//yy     0,     0,     0,   495,     0,     0,     0,     0,     0,     0,
//yy     0,   495,     0,     0,     0,   495,   495,   495,   495,   495,
//yy   495,   495,   495,   495,   495,   495,   495,     0,     0,     0,
//yy   495,   495,     0,     0,     0,   495,     0,   495,   495,   495,
//yy   495,   495,   495,   495,     0,     0,     0,     0,   495,   495,
//yy   495,   495,   495,   495,   495,     0,     0,   495,     0,     0,
//yy     0,     0,     0,   495,   495,   495,   495,   495,   495,   495,
//yy   495,   495,   495,   495,   495,     0,   495,   495,   495,   495,
//yy     0,   495,   495,   495,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   495,    43,     0,   495,     0,   495,   495,
//yy   495,     0,   495,     0,    43,     0,   495,     0,     0,     0,
//yy     0,     0,     0,     0,   495,     0,     0,     0,   495,   495,
//yy   495,   495,   495,   495,     0,     0,     0,     0,     0,     0,
//yy     4,     5,     6,     0,     8,    43,     0,     0,     9,    10,
//yy     0,     0,     0,    11,     0,    12,    13,    14,    15,    16,
//yy    17,    18,    43,    43,     0,     0,    19,    20,    21,    22,
//yy    23,    24,    25,   200,     0,    26,     0,     0,     0,     0,
//yy     0,     0,    28,   200,     0,    31,    32,    33,    34,    35,
//yy    36,    37,    38,     0,    39,    40,    41,    42,     0,    43,
//yy    44,    45,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   200,     0,     0,   200,     0,     0,
//yy     0,   208,     0,     0,   108,     0,    48,    49,    50,    43,
//yy     0,   200,   200,     0,    52,     0,   200,     0,     0,     0,
//yy     0,     0,    53,     0,     0,     0,    54,    55,    56,    57,
//yy    58,    59,     0,   202,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   202,     0,     0,   200,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   202,     0,   173,   202,   200,     0,
//yy     0,     0,     0,     0,     0,     0,   173,     0,     0,     0,
//yy     0,   202,   202,     0,     0,     0,   202,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   201,     0,     0,     0,   173,     0,     0,
//yy   218,     0,     0,   201,     0,     0,   202,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   173,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,    43,    43,    43,     0,
//yy     0,    43,    43,    43,   201,    43,     0,   201,   202,     0,
//yy     0,     0,     0,     0,     0,    43,     0,     0,     0,     0,
//yy     0,   201,   201,     0,    43,    43,   201,    43,    43,    43,
//yy    43,    43,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   173,     0,     0,     0,     0,   201,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   200,   200,   200,     0,     0,
//yy   200,   200,   200,     0,   200,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   200,   200,     0,     0,   201,     0,
//yy     0,     0,     0,   200,   200,     0,   200,   200,   200,   200,
//yy   200,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   200,   200,   200,   200,   200,   200,   200,
//yy   200,   200,   200,     0,   200,   200,     0,     0,     0,     0,
//yy     0,     0,     0,   200,     0,   202,   202,   202,     0,   200,
//yy   202,   202,   202,     0,   202,     0,     0,     0,     0,   200,
//yy   200,     0,     0,     0,   202,   202,     0,     0,     0,     0,
//yy     0,     0,     0,   202,   202,     0,   202,   202,   202,   202,
//yy   202,     0,     0,     0,     0,     0,     0,     0,   173,   173,
//yy   173,     0,    48,     0,   173,   173,     0,   173,     0,     0,
//yy     0,     0,    48,   202,   202,   202,   202,   202,   202,   202,
//yy   202,   202,   202,     0,   202,   202,   173,   173,     0,   173,
//yy   173,   173,   173,   202,     0,   201,   201,   201,     0,   202,
//yy   201,   201,   201,    48,   201,     0,     0,     0,   199,   202,
//yy   202,     0,     0,     0,   201,   201,     0,     0,   199,     0,
//yy    48,    48,     0,   201,   201,     0,   201,   201,   201,   201,
//yy   201,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   199,
//yy     0,     0,   199,   201,   201,   201,   201,   201,   201,   201,
//yy   201,   201,   201,     0,   201,   201,   199,   199,     0,     0,
//yy     0,   199,     0,   201,     0,     0,     0,     0,     0,   201,
//yy     0,     0,     0,     0,     0,     0,     0,    48,   198,   201,
//yy   201,     0,     0,     0,     0,     0,     0,     0,   198,     0,
//yy     0,   199,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   198,
//yy     0,     0,   198,   199,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   203,     0,     0,     0,   198,   198,     0,     0,
//yy     0,   198,   203,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   198,     0,   203,     0,     0,   203,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   203,   203,     0,     0,     0,   203,     0,     0,     0,     0,
//yy     0,     0,     0,   198,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   204,     0,    48,    48,    48,     0,     0,    48,
//yy    48,    48,   204,    48,     0,   203,     0,     0,     0,     0,
//yy     0,     0,     0,    48,     0,     0,     0,     0,     0,     0,
//yy     0,     0,    48,    48,     0,    48,    48,    48,    48,    48,
//yy     0,     0,     0,   204,     0,     0,   204,   203,     0,     0,
//yy   199,   199,   199,     0,     0,   199,   199,   199,     0,   199,
//yy   204,   204,     0,     0,     0,   204,     0,     0,     0,   199,
//yy   199,     0,     0,     0,     0,     0,     0,     0,   199,   199,
//yy     0,   199,   199,   199,   199,   199,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   204,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   199,   199,
//yy   199,   199,   199,   199,   199,   199,   199,   199,     0,   199,
//yy   199,     0,     0,     0,     0,     0,     0,   204,   199,     0,
//yy   198,   198,   198,     0,   199,   198,   198,   198,   205,   198,
//yy     0,     0,     0,     0,   199,   199,     0,     0,   205,   198,
//yy   198,     0,     0,     0,     0,     0,     0,     0,   198,   198,
//yy     0,   198,   198,   198,   198,   198,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   205,
//yy     0,     0,   205,     0,   203,   203,   203,     0,     0,   203,
//yy   203,   203,   212,   203,   198,   198,   205,   205,     0,   198,
//yy   198,   205,   212,   203,   203,     0,     0,     0,   198,     0,
//yy     0,     0,   203,   203,   198,   203,   203,   203,   203,   203,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   205,     0,   212,     0,     0,   212,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   206,     0,   203,   203,
//yy   212,   212,     0,   203,   203,   212,   206,     0,     0,     0,
//yy     0,     0,   203,   205,   204,   204,   204,     0,   203,   204,
//yy   204,   204,     0,   204,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   204,   204,   212,     0,   206,     0,     0,
//yy   206,     0,   204,   204,     0,   204,   204,   204,   204,   204,
//yy   207,     0,     0,     0,   206,   206,     0,     0,     0,   206,
//yy   207,     0,     0,     0,     0,     0,     0,   212,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   204,   204,
//yy     0,     0,     0,   204,   204,     0,     0,     0,     0,   206,
//yy     0,   207,   204,     0,   207,     0,     0,     0,   204,     0,
//yy     0,     0,     0,     0,   213,     0,     0,     0,   207,   207,
//yy     0,     0,     0,   207,   213,     0,     0,     0,     0,     0,
//yy     0,   206,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   207,     0,   213,     0,     0,   213,     0,
//yy   205,   205,   205,     0,     0,   205,   205,   205,   183,   205,
//yy     0,     0,   213,   213,     0,     0,     0,   213,   183,   205,
//yy   205,     0,     0,     0,     0,   207,     0,     0,   205,   205,
//yy     0,   205,   205,   205,   205,   205,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   213,     0,   183,
//yy     0,     0,   183,     0,   212,   212,   212,     0,     0,   212,
//yy   212,   212,   184,   212,   205,   205,   183,   183,     0,   205,
//yy   205,   183,   184,   212,   212,     0,     0,     0,   205,   213,
//yy     0,     0,   212,   212,   205,   212,   212,   212,   212,   212,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   183,     0,   184,     0,     0,   184,     0,   206,   206,
//yy   206,     0,     0,   206,   206,   206,   218,   206,   212,   212,
//yy   184,   184,     0,   212,   212,   184,   218,   206,   206,     0,
//yy     0,     0,   212,   183,     0,     0,   206,   206,   212,   206,
//yy   206,   206,   206,   206,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   184,     0,   218,     0,     0,
//yy   218,     0,   207,   207,   207,     0,     0,   207,   207,   207,
//yy   182,   207,   206,   206,   218,   218,     0,   206,   206,     0,
//yy   182,   207,   207,     0,     0,     0,   206,   184,     0,     0,
//yy   207,   207,   206,   207,   207,   207,   207,   207,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   218,
//yy     0,   182,     0,     0,   182,     0,   213,   213,   213,     0,
//yy     0,   213,   213,   213,   175,   213,   207,   207,   182,   182,
//yy     0,   207,   207,     0,   175,   213,   213,     0,     0,     0,
//yy   207,   218,     0,     0,   213,   213,   207,   213,   213,   213,
//yy   213,   213,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   182,     0,   175,     0,     0,   175,     0,
//yy   183,   183,   183,     0,     0,   183,   183,   183,   215,   183,
//yy     0,   213,   175,   175,     0,   213,   213,     0,   215,   183,
//yy   183,     0,     0,     0,   213,   182,     0,     0,   183,   183,
//yy   213,   183,   183,   183,   183,   183,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   175,     0,   215,
//yy     0,     0,   215,     0,   184,   184,   184,     0,     0,   184,
//yy   184,   184,   181,   184,     0,     0,   215,   215,     0,     0,
//yy     0,     0,   181,   184,   184,     0,     0,     0,   183,   175,
//yy     0,     0,   184,   184,   183,   184,   184,   184,   184,   184,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   215,     0,   181,     0,     0,   181,     0,   218,   218,
//yy   218,     0,     0,   218,   218,   218,   216,   218,     0,     0,
//yy   181,   181,     0,     0,     0,     0,   216,   218,   218,     0,
//yy     0,     0,   184,   215,     0,     0,   218,   218,   184,   218,
//yy   218,   218,   218,   218,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   181,     0,   216,     0,     0,
//yy   216,     0,   182,   182,   182,     0,     0,   182,   182,   182,
//yy   177,   182,     0,     0,   216,   216,     0,     0,     0,     0,
//yy   177,   182,   182,     0,     0,     0,   218,   181,     0,     0,
//yy   182,   182,   218,   182,   182,   182,   182,   182,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   216,
//yy     0,   177,     0,     0,   177,     0,   175,   175,   175,     0,
//yy     0,   175,   175,   175,   178,   175,     0,     0,   177,   177,
//yy     0,     0,     0,     0,   178,   175,   175,     0,     0,     0,
//yy   182,   216,     0,     0,   175,   175,   182,   175,   175,   175,
//yy   175,   175,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   177,     0,   178,     0,     0,   178,     0,
//yy   215,   215,   215,     0,     0,   215,   215,   215,   179,   215,
//yy     0,     0,   178,   178,     0,     0,     0,     0,   179,   215,
//yy   215,     0,     0,     0,   175,   177,     0,     0,   215,   215,
//yy   175,   215,   215,   215,   215,   215,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   178,     0,   179,
//yy     0,     0,   179,     0,   181,   181,   181,     0,     0,   181,
//yy   181,   181,   180,   181,     0,     0,   179,   179,     0,     0,
//yy     0,     0,   180,   181,   181,     0,     0,     0,   215,   178,
//yy     0,     0,   181,   181,   215,   181,   181,   181,   181,   181,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   179,     0,   180,     0,     0,   180,     0,   216,   216,
//yy   216,     0,     0,   216,   216,   216,   174,   216,     0,     0,
//yy   180,   180,     0,     0,     0,     0,   174,   216,   216,     0,
//yy     0,     0,   181,   179,     0,     0,   216,   216,   181,   216,
//yy   216,   216,   216,   216,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   180,     0,   174,     0,     0,
//yy   174,     0,   177,   177,   177,     0,     0,   177,   177,   177,
//yy   176,   177,     0,     0,   174,   174,     0,     0,     0,     0,
//yy   176,   177,   177,     0,     0,     0,   216,   180,     0,     0,
//yy   177,   177,   216,   177,   177,   177,   177,   177,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   174,
//yy     0,   176,     0,     0,   176,     0,   178,   178,   178,     0,
//yy     0,   178,   178,   178,   262,   178,     0,     0,   176,   176,
//yy     0,     0,     0,     0,   262,   178,   178,     0,     0,     0,
//yy   177,   174,     0,     0,   178,   178,   177,   178,   178,   178,
//yy   178,   178,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   176,     0,   262,     0,     0,   262,     0,
//yy   179,   179,   179,     0,     0,   179,   179,   179,   173,   179,
//yy     0,     0,   262,   262,     0,     0,     0,     0,   173,   179,
//yy   179,     0,     0,     0,   178,   176,     0,     0,   179,   179,
//yy   178,   179,   179,   179,   179,   179,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   262,     0,   173,
//yy     0,     0,   173,     0,   180,   180,   180,     0,     0,   180,
//yy   180,   180,   263,   180,     0,     0,   173,   173,     0,     0,
//yy     0,     0,   263,   180,   180,     0,     0,     0,   179,   262,
//yy     0,     0,   180,   180,   179,   180,   180,   180,   180,   180,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   173,     0,   263,     0,     0,   263,     0,   174,   174,
//yy   174,     0,     0,   174,   174,   174,   497,   174,     0,     0,
//yy   263,   263,     0,     0,     0,     0,   497,   174,   174,     0,
//yy     0,     0,   180,   173,     0,     0,   174,   174,   180,   174,
//yy   174,   174,   174,   174,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   263,     0,   497,     0,     0,
//yy     0,     0,   176,   176,   176,     0,     0,   176,   176,   176,
//yy     0,   176,     0,     0,   497,   497,     0,     0,     0,     0,
//yy     0,   176,   176,     0,     0,     0,   174,   263,     0,     0,
//yy   176,   176,   174,   176,   176,   176,   176,   176,     0,     0,
//yy     0,     0,   413,     0,     0,     0,     0,     0,     0,   497,
//yy     0,     0,     0,     0,     0,     0,   262,   262,   262,     0,
//yy     0,   262,   262,   262,     0,   262,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   262,   262,     0,     0,     0,
//yy   176,   497,     0,     0,   262,   262,   176,   262,   262,   262,
//yy   262,   262,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   173,   173,   173,     0,     0,   173,   173,   173,     0,   173,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   173,
//yy   173,     0,     0,     0,     0,     0,     0,     0,   173,   173,
//yy   262,   173,   173,   173,   173,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   263,   263,   263,     0,     0,   263,
//yy   263,   263,     0,   263,   397,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   263,   263,     0,     0,     0,   173,     0,
//yy     0,     0,   263,   263,   173,   263,   263,   263,   263,   263,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   497,   497,
//yy   497,     0,     0,   497,   497,   497,     0,   497,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   497,   497,     0,
//yy     0,     0,     0,     0,     0,     0,   497,   497,   263,   497,
//yy   497,   497,   497,   497,     0,     0,     0,     0,     4,     5,
//yy     6,     0,     8,     0,     0,     0,     9,    10,     0,     0,
//yy    53,    11,     0,    12,    13,    14,    97,    98,    17,    18,
//yy    53,     0,     0,     0,    99,    20,    21,    22,    23,    24,
//yy    25,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy    28,     0,   497,    31,    32,    33,    34,    35,    36,    37,
//yy    38,    53,    39,    40,    41,    42,     0,    43,    44,    45,
//yy    42,     0,     0,     0,     0,     0,     0,     0,    53,    53,
//yy    42,     0,     0,     0,     0,     0,     0,     0,     0,   208,
//yy     0,     0,   108,     0,    48,    49,    50,     0,   232,     0,
//yy   233,     0,    52,     0,     0,     0,     0,     0,     0,     0,
//yy   234,    42,     0,    53,    54,    55,    56,    57,    58,    59,
//yy     0,    95,     4,     5,     6,     7,     8,     0,    42,    42,
//yy     9,    10,     0,     0,    55,    11,     0,    12,    13,    14,
//yy    15,    16,    17,    18,    55,    53,     0,     0,    19,    20,
//yy    21,    22,    23,    24,    25,     0,     0,    26,     0,     0,
//yy     0,     0,     0,    27,    28,    29,    30,    31,    32,    33,
//yy    34,    35,    36,    37,    38,    55,    39,    40,    41,    42,
//yy    57,    43,    44,    45,     0,     0,     0,     0,     0,     0,
//yy    57,     0,    55,    55,     0,    42,     0,     0,     0,     0,
//yy     0,     0,     0,    46,     0,     0,    47,     0,    48,    49,
//yy    50,     0,    51,     0,     0,     0,    52,     0,     0,     0,
//yy     0,    57,     0,     0,    53,     0,     0,    55,    54,    55,
//yy    56,    57,    58,    59,     0,     0,     0,     0,    57,    57,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    55,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    57,     0,     0,     0,     0,     0,     0,
//yy     0,     0,    53,    53,    53,     0,     0,    53,    53,    53,
//yy     0,    53,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    53,    53,     0,     0,    57,     0,     0,     0,     0,
//yy    53,    53,     0,    53,    53,    53,    53,    53,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,    42,    42,    42,     0,     0,    42,    42,    42,
//yy     0,    42,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    42,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    42,    42,    42,    42,    42,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,    55,    55,    55,     0,
//yy     0,    55,    55,    55,     0,    55,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    55,    55,     0,     0,     0,
//yy     0,     0,     0,     0,    55,    55,     0,    55,    55,    55,
//yy    55,    55,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,    57,    57,    57,     0,     0,    57,    57,    57,
//yy     0,    57,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    57,    57,     0,     0,     0,     0,     0,     0,     0,
//yy    57,    57,     0,    57,    57,    57,    57,    57,     3,     4,
//yy     5,     6,     7,     8,     0,     0,     0,     9,    10,     0,
//yy     0,     0,    11,     0,    12,    13,    14,    15,    16,    17,
//yy    18,     0,     0,     0,     0,    19,    20,    21,    22,    23,
//yy    24,    25,     0,     0,    26,     0,     0,     0,     0,     0,
//yy    27,    28,    29,    30,    31,    32,    33,    34,    35,    36,
//yy    37,    38,     0,    39,    40,    41,    42,     0,    43,    44,
//yy    45,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy    46,     0,     0,    47,     0,    48,    49,    50,     0,    51,
//yy     0,     0,     0,    52,     0,     0,     0,     0,     0,     0,
//yy     0,    53,     0,     0,     0,    54,    55,    56,    57,    58,
//yy    59,    95,     4,     5,     6,     7,     8,     0,     0,     0,
//yy     9,    10,     0,     0,     0,    11,     0,    12,    13,    14,
//yy    15,    16,    17,    18,     0,     0,     0,     0,    19,    20,
//yy    21,    22,    23,    24,    25,     0,     0,    26,     0,     0,
//yy     0,     0,     0,    27,    28,    29,    30,    31,    32,    33,
//yy    34,    35,    36,    37,    38,     0,    39,    40,    41,    42,
//yy     0,    43,    44,    45,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    46,     0,     0,    47,     0,    48,    49,
//yy    50,     0,    51,     0,     0,     0,    52,     0,     0,     0,
//yy     0,     0,     0,     0,    53,     0,     0,     0,    54,    55,
//yy    56,    57,    58,    59,     3,     4,     5,     6,     7,     8,
//yy     0,     0,     0,     9,    10,     0,     0,     0,    11,     0,
//yy    12,    13,    14,    15,    16,    17,    18,     0,     0,     0,
//yy     0,    19,    20,    21,    22,    23,    24,    25,     0,     0,
//yy    26,     0,     0,     0,     0,     0,    27,    28,    29,    30,
//yy    31,    32,    33,    34,    35,    36,    37,    38,     0,    39,
//yy    40,    41,    42,     0,    43,    44,    45,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,    46,     0,     0,   264,
//yy     0,    48,    49,    50,     0,    51,     0,     0,     0,    52,
//yy     0,     0,     0,     0,     0,     0,     0,    53,     0,     0,
//yy     0,    54,    55,    56,    57,    58,    59,   616,     4,     5,
//yy     6,     0,     8,     0,     0,     0,     9,    10,     0,     0,
//yy     0,    11,     0,    12,    13,    14,    97,    98,    17,    18,
//yy     0,     0,     0,     0,    99,   100,   101,    22,    23,    24,
//yy    25,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy    28,     0,     0,    31,    32,    33,    34,    35,    36,    37,
//yy    38,     0,    39,    40,    41,    42,     0,    43,    44,    45,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   208,
//yy     0,     0,   108,     0,    48,    49,    50,     0,   617,     0,
//yy   233,     0,    52,     0,     0,     0,     0,     0,     0,     0,
//yy   234,     0,     0,     0,    54,    55,    56,    57,    58,    59,
//yy   616,     4,     5,     6,     0,     8,     0,     0,     0,     9,
//yy    10,     0,     0,     0,    11,     0,    12,    13,    14,    97,
//yy    98,    17,    18,     0,     0,     0,     0,    99,   100,   101,
//yy    22,    23,    24,    25,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    28,     0,     0,    31,    32,    33,    34,
//yy    35,    36,    37,    38,     0,    39,    40,    41,    42,     0,
//yy    43,    44,    45,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   208,     0,     0,   108,     0,    48,    49,    50,
//yy     0,   807,     0,   233,     0,    52,     0,     0,     0,     0,
//yy     0,     0,     0,   234,     0,     0,     0,    54,    55,    56,
//yy    57,    58,    59,   616,     4,     5,     6,     0,     8,     0,
//yy     0,     0,     9,    10,     0,     0,     0,    11,     0,    12,
//yy    13,    14,    97,    98,    17,    18,     0,     0,     0,     0,
//yy    99,   100,   101,    22,    23,    24,    25,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,    28,     0,     0,    31,
//yy    32,    33,    34,    35,    36,    37,    38,     0,    39,    40,
//yy    41,    42,     0,    43,    44,    45,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   208,     0,     0,   108,     0,
//yy    48,    49,    50,     0,   815,     0,   233,     0,    52,     0,
//yy     0,     0,     0,     0,     0,     0,   234,     0,     0,     0,
//yy    54,    55,    56,    57,    58,    59,   616,     4,     5,     6,
//yy     0,     8,     0,     0,     0,     9,    10,     0,     0,     0,
//yy    11,     0,    12,    13,    14,    97,    98,    17,    18,     0,
//yy     0,     0,     0,    99,   100,   101,    22,    23,    24,    25,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    28,
//yy     0,     0,    31,    32,    33,    34,    35,    36,    37,    38,
//yy     0,    39,    40,    41,    42,     0,    43,    44,    45,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   208,     0,
//yy     0,   108,     0,    48,    49,    50,     0,   872,     0,   233,
//yy     0,    52,     0,     0,     0,     0,     0,     0,     0,   234,
//yy     0,     0,     0,    54,    55,    56,    57,    58,    59,   616,
//yy     4,     5,     6,     0,     8,     0,     0,     0,     9,    10,
//yy     0,     0,     0,    11,     0,    12,    13,    14,    97,    98,
//yy    17,    18,     0,     0,     0,     0,    99,   100,   101,    22,
//yy    23,    24,    25,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,    28,     0,     0,    31,    32,    33,    34,    35,
//yy    36,    37,    38,     0,    39,    40,    41,    42,     0,    43,
//yy    44,    45,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   208,     0,     0,   108,     0,    48,    49,    50,     0,
//yy   884,     0,   233,     0,    52,     0,     0,     0,     0,     0,
//yy     0,     0,   234,     0,     0,     0,    54,    55,    56,    57,
//yy    58,    59,   616,     4,     5,     6,     0,     8,     0,     0,
//yy     0,     9,    10,     0,     0,     0,    11,     0,    12,    13,
//yy    14,    97,    98,    17,    18,     0,     0,     0,     0,    99,
//yy   100,   101,    22,    23,    24,    25,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    28,     0,     0,    31,    32,
//yy    33,    34,    35,    36,    37,    38,     0,    39,    40,    41,
//yy    42,     0,    43,    44,    45,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   208,     0,     0,   108,     0,    48,
//yy    49,    50,     0,     0,     0,     0,     0,    52,     0,     0,
//yy     0,     0,     0,     0,     0,   234,     0,     0,     0,    54,
//yy    55,    56,    57,    58,    59,     4,     5,     6,     0,     8,
//yy     0,     0,     0,     9,    10,     0,     0,     0,    11,     0,
//yy    12,    13,    14,    15,    16,    17,    18,     0,     0,     0,
//yy     0,    19,    20,    21,    22,    23,    24,    25,     0,     0,
//yy    26,     0,     0,     0,     0,     0,     0,    28,     0,     0,
//yy    31,    32,    33,    34,    35,    36,    37,    38,     0,    39,
//yy    40,    41,    42,     0,    43,    44,    45,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   208,     0,     0,   108,
//yy     0,    48,    49,    50,     0,   621,     0,   233,     0,    52,
//yy     0,     0,     0,     0,     0,     0,     0,    53,     0,     0,
//yy     0,    54,    55,    56,    57,    58,    59,     4,     5,     6,
//yy     0,     8,     0,     0,     0,     9,    10,     0,     0,     0,
//yy    11,     0,    12,    13,    14,    97,    98,    17,    18,     0,
//yy     0,     0,     0,    99,    20,    21,    22,    23,    24,    25,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    28,
//yy     0,     0,    31,    32,    33,    34,    35,    36,    37,    38,
//yy     0,    39,    40,    41,    42,     0,    43,    44,    45,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   208,     0,
//yy     0,   108,     0,    48,    49,    50,     0,   232,     0,   233,
//yy     0,    52,     0,     0,     0,     0,     0,     0,     0,   234,
//yy     0,     0,     0,    54,    55,    56,    57,    58,    59,     4,
//yy     5,     6,     0,     8,     0,     0,     0,     9,    10,     0,
//yy     0,     0,    11,     0,    12,    13,    14,    97,    98,    17,
//yy    18,     0,     0,     0,     0,    99,    20,    21,    22,    23,
//yy    24,    25,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    28,     0,     0,    31,    32,    33,    34,    35,    36,
//yy    37,    38,     0,    39,    40,    41,    42,     0,    43,    44,
//yy    45,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   208,     0,     0,   108,     0,   415,    49,    50,     0,   232,
//yy     0,   233,     0,    52,     0,     0,     0,     0,     0,     0,
//yy     0,   234,     0,     0,     0,    54,    55,    56,    57,    58,
//yy    59,     4,     5,     6,     0,     8,     0,     0,     0,     9,
//yy    10,     0,     0,     0,    11,     0,    12,    13,    14,    97,
//yy    98,    17,    18,     0,     0,     0,     0,    99,   100,   101,
//yy    22,    23,    24,    25,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    28,     0,     0,    31,    32,    33,    34,
//yy    35,    36,    37,    38,     0,    39,    40,    41,    42,     0,
//yy    43,    44,    45,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   208,     0,     0,   108,     0,    48,    49,    50,
//yy     0,   612,     0,   233,     0,    52,     0,     0,     0,     0,
//yy     0,     0,     0,   234,     0,     0,     0,    54,    55,    56,
//yy    57,    58,    59,     4,     5,     6,     0,     8,     0,     0,
//yy     0,     9,    10,     0,     0,     0,    11,     0,    12,    13,
//yy    14,    97,    98,    17,    18,     0,     0,     0,     0,    99,
//yy    20,    21,    22,    23,    24,    25,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    28,     0,     0,    31,    32,
//yy    33,    34,    35,    36,    37,    38,     0,    39,    40,    41,
//yy    42,     0,    43,    44,    45,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   208,     0,     0,   108,     0,    48,
//yy    49,    50,     0,   612,     0,   233,     0,    52,     0,     0,
//yy     0,     0,     0,     0,     0,   234,     0,     0,     0,    54,
//yy    55,    56,    57,    58,    59,     4,     5,     6,     0,     8,
//yy     0,     0,     0,     9,    10,     0,     0,     0,    11,     0,
//yy    12,    13,    14,    97,    98,    17,    18,     0,     0,     0,
//yy     0,    99,   100,   101,    22,    23,    24,    25,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,    28,     0,     0,
//yy    31,    32,    33,    34,    35,    36,    37,    38,     0,    39,
//yy    40,    41,    42,     0,    43,    44,    45,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   208,     0,     0,   108,
//yy     0,    48,    49,    50,     0,   811,     0,   233,     0,    52,
//yy     0,     0,     0,     0,     0,     0,     0,   234,     0,     0,
//yy     0,    54,    55,    56,    57,    58,    59,     4,     5,     6,
//yy     0,     8,     0,     0,     0,     9,    10,     0,     0,     0,
//yy    11,     0,    12,    13,    14,    97,    98,    17,    18,     0,
//yy     0,     0,     0,    99,   100,   101,    22,    23,    24,    25,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    28,
//yy     0,     0,    31,    32,    33,    34,    35,    36,    37,    38,
//yy     0,    39,    40,    41,    42,     0,    43,    44,    45,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   208,     0,
//yy     0,   108,     0,    48,    49,    50,     0,   870,     0,   233,
//yy     0,    52,     0,     0,     0,     0,     0,     0,     0,   234,
//yy     0,     0,     0,    54,    55,    56,    57,    58,    59,   252,
//yy   252,   252,     0,   252,     0,     0,     0,   252,   252,     0,
//yy     0,     0,   252,     0,   252,   252,   252,   252,   252,   252,
//yy   252,     0,     0,     0,     0,   252,   252,   252,   252,   252,
//yy   252,   252,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   252,     0,     0,   252,   252,   252,   252,   252,   252,
//yy   252,   252,     0,   252,   252,   252,   252,     0,   252,   252,
//yy   252,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   252,     0,     0,   252,     0,   252,   252,   252,     0,   252,
//yy     0,   252,     0,   252,     0,     0,     0,     0,     0,     0,
//yy     0,   252,     0,     0,     0,   252,   252,   252,   252,   252,
//yy   252,     4,     5,     6,     0,     8,     0,     0,     0,     9,
//yy    10,     0,     0,     0,    11,     0,    12,    13,    14,    15,
//yy    16,    17,    18,     0,     0,     0,     0,    19,    20,    21,
//yy    22,    23,    24,    25,     0,     0,    26,     0,     0,     0,
//yy     0,     0,     0,    28,     0,     0,    31,    32,    33,    34,
//yy    35,    36,    37,    38,     0,    39,    40,    41,    42,     0,
//yy    43,    44,    45,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   208,     0,     0,   108,     0,    48,    49,    50,
//yy     0,     0,     0,     0,     0,    52,     0,     0,     0,     0,
//yy     0,     0,     0,    53,     0,     0,     0,    54,    55,    56,
//yy    57,    58,    59,     4,     5,     6,     0,     8,     0,     0,
//yy     0,     9,    10,     0,     0,     0,    11,     0,    12,    13,
//yy    14,    97,    98,    17,    18,     0,     0,     0,     0,    99,
//yy    20,    21,    22,    23,    24,    25,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    28,     0,     0,    31,    32,
//yy    33,    34,    35,    36,    37,    38,     0,    39,    40,    41,
//yy    42,     0,    43,    44,    45,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   208,     0,     0,   108,     0,    48,
//yy    49,    50,     0,   269,     0,     0,     0,    52,     0,     0,
//yy     0,     0,     0,     0,     0,   234,     0,     0,     0,    54,
//yy    55,    56,    57,    58,    59,     4,     5,     6,     0,     8,
//yy     0,     0,     0,     9,    10,     0,     0,     0,    11,     0,
//yy    12,    13,    14,    15,    16,    17,    18,     0,     0,     0,
//yy     0,    19,    20,    21,    22,    23,    24,    25,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,    28,     0,     0,
//yy    31,    32,    33,    34,    35,    36,    37,    38,     0,    39,
//yy    40,    41,    42,     0,    43,    44,    45,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   208,     0,     0,   108,
//yy     0,    48,    49,    50,     0,   527,     0,     0,     0,    52,
//yy     0,     0,     0,     0,     0,     0,     0,   234,     0,     0,
//yy     0,    54,    55,    56,    57,    58,    59,     4,     5,     6,
//yy     0,     8,     0,     0,     0,     9,    10,     0,     0,     0,
//yy    11,     0,    12,    13,    14,    97,    98,    17,    18,     0,
//yy     0,     0,     0,    99,   100,   101,    22,    23,    24,    25,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    28,
//yy     0,     0,    31,    32,    33,    34,    35,    36,    37,    38,
//yy     0,    39,    40,    41,    42,     0,    43,    44,    45,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   208,     0,
//yy     0,   108,     0,    48,    49,    50,     0,   527,     0,     0,
//yy     0,    52,     0,     0,     0,     0,     0,     0,     0,   234,
//yy     0,     0,     0,    54,    55,    56,    57,    58,    59,     4,
//yy     5,     6,     0,     8,     0,     0,     0,     9,    10,     0,
//yy     0,     0,    11,     0,    12,    13,    14,    97,    98,    17,
//yy    18,     0,     0,     0,     0,    99,   100,   101,    22,    23,
//yy    24,    25,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    28,     0,     0,    31,    32,    33,    34,    35,    36,
//yy    37,    38,     0,    39,    40,    41,    42,     0,    43,    44,
//yy    45,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   208,     0,     0,   108,     0,    48,    49,    50,     0,   594,
//yy     0,     0,     0,    52,     0,     0,     0,     0,     0,     0,
//yy     0,   234,     0,     0,     0,    54,    55,    56,    57,    58,
//yy    59,     4,     5,     6,     0,     8,     0,     0,     0,     9,
//yy    10,     0,     0,     0,    11,     0,    12,    13,    14,    97,
//yy    98,    17,    18,     0,     0,     0,     0,    99,   100,   101,
//yy    22,    23,    24,    25,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    28,     0,     0,    31,    32,    33,    34,
//yy    35,    36,    37,    38,     0,    39,    40,    41,    42,     0,
//yy    43,    44,    45,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   208,     0,     0,   108,     0,    48,    49,    50,
//yy     0,   645,     0,     0,     0,    52,     0,     0,     0,     0,
//yy     0,     0,     0,   234,     0,     0,     0,    54,    55,    56,
//yy    57,    58,    59,     4,     5,     6,     0,     8,     0,     0,
//yy     0,     9,    10,     0,     0,     0,    11,     0,    12,    13,
//yy    14,    97,    98,    17,    18,     0,     0,     0,     0,    99,
//yy   100,   101,    22,    23,    24,    25,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    28,     0,     0,    31,    32,
//yy    33,    34,    35,    36,    37,    38,     0,    39,    40,    41,
//yy    42,     0,    43,    44,    45,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   208,     0,     0,   108,     0,    48,
//yy    49,    50,     0,   772,     0,     0,     0,    52,     0,     0,
//yy     0,     0,     0,     0,     0,   234,     0,     0,     0,    54,
//yy    55,    56,    57,    58,    59,     4,     5,     6,     0,     8,
//yy     0,     0,     0,     9,    10,     0,     0,     0,    11,     0,
//yy    12,    13,    14,    97,    98,    17,    18,     0,     0,     0,
//yy     0,    99,   100,   101,    22,    23,    24,    25,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,    28,     0,     0,
//yy    31,    32,    33,    34,    35,    36,    37,    38,     0,    39,
//yy    40,    41,    42,     0,    43,    44,    45,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   208,     0,     0,   108,
//yy     0,    48,    49,    50,     0,   801,     0,     0,     0,    52,
//yy     0,     0,     0,     0,     0,     0,     0,   234,     0,     0,
//yy     0,    54,    55,    56,    57,    58,    59,   486,   486,   486,
//yy     0,   486,     0,     0,     0,   486,   486,     0,     0,     0,
//yy   486,     0,   486,   486,   486,   486,   486,   486,   486,     0,
//yy     0,     0,     0,   486,   486,   486,   486,   486,   486,   486,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   486,
//yy     0,     0,   486,   486,   486,   486,   486,   486,   486,   486,
//yy     0,   486,   486,   486,   486,     0,   486,   486,   486,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   486,     0,
//yy     0,   486,   486,   486,   486,   486,     0,     0,     0,     0,
//yy     0,   486,     0,     0,     0,     0,     0,     0,     0,   486,
//yy     0,     0,     0,   486,   486,   486,   486,   486,   486,     4,
//yy     5,     6,     0,     8,     0,     0,     0,     9,    10,     0,
//yy     0,     0,    11,     0,    12,    13,    14,    97,    98,    17,
//yy    18,     0,     0,     0,     0,    99,   100,   101,    22,    23,
//yy    24,    25,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    28,     0,     0,    31,    32,    33,    34,    35,    36,
//yy    37,    38,     0,    39,    40,    41,    42,     0,    43,    44,
//yy    45,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   208,     0,     0,   108,     0,    48,    49,    50,     0,     0,
//yy     0,     0,     0,    52,     0,     0,     0,     0,     0,     0,
//yy     0,   234,     0,     0,     0,    54,    55,    56,    57,    58,
//yy    59,     4,     5,     6,     0,     8,     0,     0,     0,     9,
//yy    10,     0,     0,     0,    11,     0,    12,    13,    14,    15,
//yy    16,    17,    18,     0,     0,     0,     0,    19,    20,    21,
//yy    22,    23,    24,    25,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    28,     0,     0,    31,    32,    33,    34,
//yy    35,    36,    37,    38,     0,    39,    40,    41,    42,     0,
//yy    43,    44,    45,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   208,     0,     0,   108,     0,    48,    49,    50,
//yy     0,     0,     0,     0,     0,    52,     0,     0,     0,     0,
//yy     0,     0,     0,   234,     0,     0,     0,    54,    55,    56,
//yy    57,    58,    59,   214,   214,   214,     0,   214,     0,     0,
//yy     0,   214,   214,     0,     0,     0,   214,     0,   214,   214,
//yy   214,   214,   214,   214,   214,     0,     0,     0,     0,   214,
//yy   214,   214,   214,   214,   214,   214,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   214,     0,     0,   214,   214,
//yy   214,   214,   214,   214,   214,   214,     0,   214,   214,   214,
//yy   214,     0,   214,   214,   214,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   214,     0,     0,   214,     0,   214,
//yy   214,   214,     0,     0,     0,     0,     0,   214,     0,     0,
//yy     0,     0,     0,     0,     0,   214,     0,     0,     0,   214,
//yy   214,   214,   214,   214,   214,     4,     5,     6,     0,     8,
//yy     0,     0,     0,     9,    10,     0,     0,     0,    11,     0,
//yy    12,    13,    14,    97,    98,    17,    18,     0,     0,     0,
//yy     0,    99,   100,   101,    22,    23,    24,    25,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,   102,     0,     0,
//yy    31,    32,    33,    34,    35,    36,    37,    38,     0,    39,
//yy    40,    41,    42,     0,     0,     0,   105,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,   225,     0,     0,    47,
//yy     0,    48,    49,    50,     0,    51,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,    54,    55,    56,    57,    58,    59,     4,     5,     6,
//yy     0,     8,     0,     0,     0,     9,    10,     0,     0,     0,
//yy    11,     0,    12,    13,    14,    97,    98,    17,    18,     0,
//yy     0,     0,     0,    99,   100,   101,    22,    23,    24,    25,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,   102,
//yy     0,     0,    31,    32,    33,    34,    35,    36,    37,    38,
//yy     0,    39,    40,    41,    42,     0,     0,     0,   105,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,   279,     0,
//yy     0,   350,     0,    48,    49,    50,     0,   351,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,    54,    55,    56,    57,    58,    59,     4,
//yy     5,     6,     0,     8,     0,     0,     0,     9,    10,     0,
//yy     0,     0,    11,     0,    12,    13,    14,    97,    98,    17,
//yy    18,     0,     0,     0,     0,    99,   100,   101,    22,    23,
//yy    24,    25,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   102,     0,     0,    31,    32,   103,    34,    35,    36,
//yy   104,    38,     0,    39,    40,    41,    42,     0,     0,     0,
//yy   105,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy   107,     0,     0,   108,     0,    48,    49,    50,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,    54,    55,    56,    57,    58,
//yy    59,     4,     5,     6,     0,     8,     0,     0,     0,     9,
//yy    10,     0,     0,     0,    11,     0,    12,    13,    14,    97,
//yy    98,    17,    18,     0,     0,     0,     0,    99,   100,   101,
//yy    22,    23,    24,    25,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,   102,     0,     0,    31,    32,    33,    34,
//yy    35,    36,    37,    38,     0,    39,    40,    41,    42,     0,
//yy     0,     0,   105,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   279,     0,     0,   108,     0,    48,    49,    50,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,    54,    55,    56,
//yy    57,    58,    59,     4,     5,     6,     0,     8,     0,     0,
//yy     0,     9,    10,     0,     0,     0,    11,     0,    12,    13,
//yy    14,    97,    98,    17,    18,     0,     0,     0,     0,    99,
//yy   100,   101,    22,    23,    24,    25,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   102,     0,     0,    31,    32,
//yy    33,    34,    35,    36,    37,    38,     0,    39,    40,    41,
//yy    42,     0,     0,     0,   105,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,   790,     0,     0,   108,     0,    48,
//yy    49,    50,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,     0,     0,     0,     0,    54,
//yy    55,    56,    57,    58,    59,   117,   118,   119,   120,   121,
//yy   122,   123,   124,     0,     0,   125,   126,   127,   128,   129,
//yy     0,     0,   130,   131,   132,   133,   134,   135,   136,     0,
//yy     0,   137,   138,   139,   193,   194,   195,   196,   144,   145,
//yy   146,   147,   148,   149,   150,   151,   152,   153,   154,   155,
//yy   197,   198,   199,   159,   252,     0,   200,     0,     0,     0,
//yy     0,     0,     0,     0,   161,   162,     0,   163,   164,   165,
//yy   166,     0,   167,   168,     0,     0,   169,     0,     0,     0,
//yy     0,   170,   171,   172,   173,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   175,   176,     0,   177,   178,
//yy   179,   180,   181,   182,   183,   184,   185,     0,   186,     0,
//yy   187,   201,   117,   118,   119,   120,   121,   122,   123,   124,
//yy     0,     0,   125,   126,   127,   128,   129,     0,     0,   130,
//yy   131,   132,   133,   134,   135,   136,     0,     0,   137,   138,
//yy   139,   193,   194,   195,   196,   144,   145,   146,   147,   148,
//yy   149,   150,   151,   152,   153,   154,   155,   197,   198,   199,
//yy   159,     0,     0,   200,     0,     0,     0,     0,     0,     0,
//yy     0,   161,   162,     0,   163,   164,   165,   166,     0,   167,
//yy   168,     0,     0,   169,     0,     0,     0,     0,   170,   171,
//yy   172,   173,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,     0,   175,   176,     0,   177,   178,   179,   180,   181,
//yy   182,   183,   184,   185,     0,   186,     0,   187,   201,   117,
//yy   118,   119,   120,   121,   122,   123,   124,     0,     0,   125,
//yy   126,   127,   128,   129,     0,     0,   130,   131,   132,   133,
//yy   134,   135,   136,     0,     0,   137,   138,   139,   140,   141,
//yy   142,   143,   144,   145,   146,   147,   148,   149,   150,   151,
//yy   152,   153,   154,   155,   156,   157,   158,   159,    35,    36,
//yy   160,    38,     0,     0,     0,     0,     0,     0,   161,   162,
//yy     0,   163,   164,   165,   166,     0,   167,   168,     0,     0,
//yy   169,     0,     0,     0,     0,   170,   171,   172,   173,     0,
//yy     0,     0,     0,     0,   174,     0,     0,     0,     0,   175,
//yy   176,     0,   177,   178,   179,   180,   181,   182,   183,   184,
//yy   185,     0,   186,     0,   187,   117,   118,   119,   120,   121,
//yy   122,   123,   124,     0,     0,   125,   126,   127,   128,   129,
//yy     0,     0,   130,   131,   132,   133,   134,   135,   136,     0,
//yy     0,   137,   138,   139,   193,   194,   195,   196,   144,   145,
//yy   146,   147,   148,   149,   150,   151,   152,   153,   154,   155,
//yy   197,   198,   199,   159,   287,   288,   200,   289,     0,     0,
//yy     0,     0,     0,     0,   161,   162,     0,   163,   164,   165,
//yy   166,     0,   167,   168,     0,     0,   169,     0,     0,     0,
//yy     0,   170,   171,   172,   173,     0,     0,     0,     0,     0,
//yy     0,     0,     0,     0,     0,   175,   176,     0,   177,   178,
//yy   179,   180,   181,   182,   183,   184,   185,     0,   186,     0,
//yy   187,   117,   118,   119,   120,   121,   122,   123,   124,     0,
//yy     0,   125,   126,   127,   128,   129,     0,     0,   130,   131,
//yy   132,   133,   134,   135,   136,     0,     0,   137,   138,   139,
//yy   193,   194,   195,   196,   144,   145,   146,   147,   148,   149,
//yy   150,   151,   152,   153,   154,   155,   197,   198,   199,   159,
//yy     0,     0,   200,     0,     0,     0,     0,     0,     0,     0,
//yy   161,   162,     0,   163,   164,   165,   166,     0,   167,   168,
//yy     0,     0,   169,     0,     0,     0,     0,   170,   171,   172,
//yy   173,     0,     0,     0,     0,     0,     0,     0,     0,     0,
//yy     0,   175,   176,     0,   177,   178,   179,   180,   181,   182,
//yy   183,   184,   185,     0,   186,     0,   187,
//yyCheck 18207
//yy    15,    16,     7,    14,    19,     6,     7,   216,     6,    21,
//yy   248,    15,    16,    10,   379,    19,    32,    27,    21,   107,
//yy    41,    10,    27,   343,   399,   393,    27,    71,    43,    44,
//yy    47,    10,    15,    16,    49,    50,    19,    52,    53,   376,
//yy    61,   371,    51,    15,    16,   507,   508,    19,   264,    10,
//yy   370,    53,    10,    54,    41,   695,    54,    10,   280,    10,
//yy    10,    58,    59,   310,    44,    10,   319,    50,   388,   695,
//yy    59,    11,    10,   699,    61,   293,     4,     5,    50,   297,
//yy    59,   342,    94,   403,   342,   280,    14,   361,    41,   101,
//yy    41,    94,    10,    44,    44,   348,   310,   308,     3,   352,
//yy   353,   327,   114,     2,     3,     4,     5,    10,   411,     8,
//yy     9,    10,    11,    58,    59,    14,    15,    16,    41,   310,
//yy    19,    59,   310,    51,   371,    10,    44,    26,    10,    15,
//yy    16,    71,   358,    19,   350,   455,   310,    10,    61,   361,
//yy   341,   342,   445,   344,   345,    41,    10,   342,    47,    48,
//yy    49,     0,    51,    10,    53,    10,    59,   371,    32,    44,
//yy     2,    10,    90,    49,    10,    61,   361,    93,   369,   109,
//yy   269,    44,   271,    58,    59,   219,    58,    59,   369,   370,
//yy   371,   369,   370,   371,    41,   386,   387,   311,   312,   102,
//yy    10,    90,   832,    10,    10,   369,   370,   371,    32,   125,
//yy   201,   402,    10,   201,    59,    47,   832,   106,    10,   108,
//yy    59,   349,    10,    59,    10,   364,   546,   232,   233,   234,
//yy    10,   683,   125,   560,    41,    41,   591,    41,    10,    44,
//yy   245,   432,   247,   248,   282,    41,   566,    10,    44,    59,
//yy   378,   245,    59,   247,   248,   304,    61,   264,    91,   308,
//yy   468,    59,    91,   454,   269,    61,    58,    59,   279,    41,
//yy    58,    59,   245,    59,   247,   248,   108,   256,    41,    59,
//yy   267,    44,   339,   245,    61,   247,   248,    59,    44,   219,
//yy    44,   369,    61,   262,   263,   264,    59,    10,    11,    91,
//yy   269,    10,   279,    91,   310,   310,   311,   312,   313,   314,
//yy   315,   316,   317,   318,   319,   320,   321,   322,   323,   324,
//yy   325,   326,   327,   328,   329,   330,   331,   332,   333,   334,
//yy   335,   339,    41,    44,   223,   224,   264,    91,   343,   290,
//yy   291,   346,   347,   350,   349,    44,   281,   358,   353,   290,
//yy   291,   342,   351,   340,   346,   347,   245,   349,   247,   248,
//yy     0,   353,   727,   369,   370,   370,   279,   270,   257,   245,
//yy   340,   247,   248,   378,   347,   264,   349,   356,   369,    91,
//yy    91,   358,   330,   388,   389,   390,   391,   330,    41,   337,
//yy   395,   262,   267,   279,   337,   267,   387,   596,   403,   340,
//yy   339,    41,   407,   408,   632,   378,   411,   302,    61,    91,
//yy   415,   402,   307,   302,   303,   304,   305,   306,   307,   308,
//yy   309,   415,   395,   428,   264,   257,   426,   308,   433,   434,
//yy   435,   426,   264,   351,   364,   426,   729,   442,   629,   630,
//yy   445,   432,   735,   448,   802,   358,   408,   802,    61,    10,
//yy   455,   668,   779,   415,   343,   775,   673,   346,   347,   339,
//yy   349,   350,   351,   454,   353,   340,   264,   343,   398,   399,
//yy    59,   269,   358,   271,   279,   267,    91,   263,   264,   267,
//yy    41,   370,   371,   372,   264,   125,   290,   291,   280,   269,
//yy     2,     3,     4,     5,   370,   384,     8,   330,    91,   388,
//yy   308,   330,    14,   392,   337,   394,   705,    10,   337,   829,
//yy   223,   224,   388,   310,   403,   517,   419,   420,   350,   512,
//yy    91,   279,   527,    44,   517,   279,   415,   403,   838,   340,
//yy   523,   524,   269,    44,   271,    47,   439,   440,   330,    51,
//yy   429,   273,   330,   275,   276,   337,   290,   291,   550,   337,
//yy   342,    10,   384,   358,   342,    58,    59,    10,    44,   852,
//yy   392,    10,   394,   269,   555,   271,   455,   555,   279,   361,
//yy    91,   342,   369,   370,   371,   575,   330,   361,    90,   455,
//yy   575,   311,   312,   337,   575,   571,   572,   880,   342,   594,
//yy   303,   304,   305,   306,   330,   319,   108,   429,   797,    58,
//yy    59,   337,   310,   319,   358,    58,    59,   612,   330,    58,
//yy    59,   339,   617,   543,   319,   337,   621,    41,   330,   330,
//yy   268,   269,   262,   263,   264,   337,   337,   632,   268,   269,
//yy    41,   271,    91,   263,   264,   349,   350,   567,    91,   269,
//yy   645,   523,   524,   269,    10,   271,   837,   358,   330,   263,
//yy   264,   554,   657,    10,    93,   337,   545,   546,   659,   664,
//yy   665,   369,   370,   371,    10,   670,   671,   125,    16,   308,
//yy   632,    19,   664,   665,   371,   680,   681,   566,   670,    56,
//yy    57,     2,     3,   371,    44,   346,   691,     8,     9,    10,
//yy    11,   580,    58,    59,    15,    16,   269,   267,    19,   271,
//yy   264,    58,    59,    41,    44,    26,    44,    41,    10,   714,
//yy   715,   716,    44,    59,    41,   330,    49,   722,    41,   608,
//yy   125,    44,   337,    61,   729,    91,    47,    48,    49,   734,
//yy   735,    41,    53,    32,    91,   339,   339,   330,    61,    41,
//yy   734,   659,    44,   632,   337,   257,   635,   339,   580,   339,
//yy    93,    44,   264,    91,    41,   685,   632,    59,   279,   330,
//yy   264,   734,    93,   652,   267,   770,   337,   772,    91,    61,
//yy   659,   660,   734,   662,   663,   664,   665,    44,   770,    44,
//yy    44,   670,   310,   340,   306,   106,   704,   108,   304,   692,
//yy   302,   313,   314,   263,   799,   307,   801,   727,   304,   339,
//yy   786,   787,   807,   789,   306,   307,   811,   309,   267,   330,
//yy   815,   313,   314,   339,   267,   704,   337,    44,   267,   708,
//yy   652,   339,   711,    44,    93,    44,   264,   280,   660,   264,
//yy   662,   663,   545,   838,   723,   724,   725,   358,   350,   351,
//yy   743,   369,   370,   371,   747,   850,   837,   852,    93,     2,
//yy     3,     4,     5,     6,   349,     8,    44,   739,   740,   371,
//yy    44,    14,    10,    41,    91,   870,    41,   872,    41,   310,
//yy    91,   330,   384,   859,   315,   880,   708,   330,   337,   884,
//yy   392,   770,   394,   125,   337,   774,   775,    41,   850,   342,
//yy    93,   723,   724,   725,    47,   608,   339,   245,    51,   247,
//yy   248,   267,   223,   224,    41,   264,   795,   339,   361,   264,
//yy   267,    59,   264,   264,   280,    41,   125,   429,   264,   264,
//yy   264,   358,   268,   269,   245,   125,   247,   248,   369,   370,
//yy    41,    44,   308,     2,     3,   264,   257,    90,   264,     8,
//yy   829,   279,   774,   264,    44,    14,    44,    41,    41,   838,
//yy    44,   840,   264,   125,    93,   108,   279,   280,   847,     0,
//yy   264,   264,   838,   795,   330,    44,    93,    61,   125,    10,
//yy   342,   337,    93,   330,   271,    41,   342,    93,    47,   125,
//yy   337,   302,   303,   304,   305,   306,   307,   308,   309,    41,
//yy    41,    93,   330,   125,   264,   361,    41,    91,   711,   337,
//yy    41,    61,     5,    44,   342,   866,     6,   330,   840,   779,
//yy   343,   560,   659,    78,   337,   847,    90,    58,    59,   342,
//yy   358,   839,   343,   248,   699,   346,   347,   699,   349,   350,
//yy    -1,    71,   353,    -1,   546,   358,    -1,   370,   361,   108,
//yy   241,    -1,   304,    -1,    10,   378,   379,    -1,    -1,   370,
//yy   371,   372,   279,    -1,   566,   388,   304,    -1,   279,   307,
//yy   308,   309,    -1,   384,    -1,    -1,    -1,   388,   580,    -1,
//yy   403,   392,    -1,   394,     2,     3,    -1,    -1,    10,    -1,
//yy     8,    -1,   403,    -1,   125,   347,   348,   349,   350,     9,
//yy    10,    11,    58,    59,   415,    -1,    -1,    -1,    -1,   347,
//yy   348,   349,   350,   330,   257,    -1,    26,    -1,   429,   330,
//yy   337,   264,    -1,    -1,    -1,    -1,   337,    -1,    -1,    47,
//yy    -1,    -1,   455,    -1,    -1,    91,    -1,    59,    48,    -1,
//yy    -1,   358,    -1,    -1,   455,    10,   319,   358,    -1,    -1,
//yy   652,   653,    -1,    41,    -1,    -1,    44,   659,   660,   302,
//yy   662,   663,   335,   336,   307,    -1,   304,    -1,    -1,   307,
//yy   308,   309,    -1,    61,    -1,   348,     0,   350,    -1,   352,
//yy   353,   354,   355,   506,   507,   508,    -1,    -1,    -1,   512,
//yy   108,    -1,   330,    58,    59,   279,   106,    -1,   257,   337,
//yy   523,   524,   704,    91,   342,   264,   708,   350,   351,   347,
//yy   348,   349,   350,    -1,   405,    -1,    -1,    41,    -1,    -1,
//yy    -1,   723,   724,   725,   547,    -1,    91,    -1,   371,    -1,
//yy    -1,   262,   263,   264,   545,   546,   267,   268,   269,    41,
//yy   271,   384,    44,   302,    -1,    -1,   330,   319,   307,   392,
//yy    -1,   394,    -1,   337,    -1,   566,   579,    -1,   581,    61,
//yy   583,    -1,   293,   294,   295,   296,   297,    -1,   591,   580,
//yy    10,   319,   774,   775,   358,    -1,   348,    -1,    -1,    10,
//yy   352,   353,   354,   355,    -1,    -1,   429,   335,   336,    91,
//yy    -1,   350,    -1,   795,    -1,    -1,    -1,   608,    -1,    -1,
//yy   348,   125,    -1,    -1,   352,   353,   354,   355,    -1,   340,
//yy    -1,   267,   371,   223,   224,    -1,    -1,    -1,    -1,    59,
//yy    -1,   632,    -1,    -1,   635,   384,    -1,   829,    59,    -1,
//yy    -1,    -1,    -1,   392,    -1,   394,    -1,    -1,   840,   257,
//yy    -1,   652,    -1,    -1,    -1,   847,   264,    -1,    -1,   660,
//yy    -1,   662,   663,   664,   665,    -1,    -1,    -1,    -1,   670,
//yy   683,    -1,    10,   293,   294,   295,   296,   297,    -1,    -1,
//yy   429,    -1,    -1,    -1,   330,    -1,   232,   233,    -1,    -1,
//yy    -1,   337,   304,    -1,   302,   307,   308,   309,    -1,   307,
//yy    -1,   279,   280,   303,   304,   305,   306,   708,   308,   309,
//yy   711,    -1,   267,   546,    -1,    -1,    -1,    -1,   330,    -1,
//yy    -1,    59,   723,   724,   725,   337,   739,   740,    -1,    -1,
//yy   342,    -1,    -1,   566,   615,   347,   348,   349,   350,    -1,
//yy    41,    -1,   350,    44,    -1,   626,    -1,   580,   262,   263,
//yy   264,    -1,   330,    -1,   268,   269,    -1,   271,    -1,   337,
//yy    61,    -1,    -1,   371,   342,    10,    -1,    -1,    -1,   770,
//yy    -1,    -1,   372,   774,   775,   330,   384,    -1,    -1,    -1,
//yy   358,    -1,   337,   361,   392,    -1,   394,   279,    -1,   802,
//yy    91,    -1,    -1,    -1,   795,    -1,    44,   546,    -1,    -1,
//yy    -1,   347,    -1,   349,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    59,   415,    -1,   566,    -1,   652,
//yy    -1,   429,    -1,    -1,    -1,   838,   659,   660,   829,   662,
//yy   663,   580,   378,    -1,    44,    -1,    -1,   838,   330,   840,
//yy    -1,    -1,    -1,    91,    -1,   337,   847,   728,    -1,   395,
//yy   731,   732,    -1,   866,    -1,    -1,    -1,    -1,    -1,    10,
//yy    -1,   407,   408,    -1,    -1,   411,   358,    -1,    -1,   415,
//yy    -1,   704,    -1,    -1,   304,   708,    -1,   307,   308,   309,
//yy    -1,    91,    -1,   304,    -1,    -1,   307,   308,   309,    -1,
//yy   723,   724,   725,    -1,    -1,    -1,   442,    -1,    -1,   445,
//yy   330,    -1,   448,   652,    -1,    -1,    -1,   337,    59,   330,
//yy   659,   660,   342,   662,   663,    -1,   337,   347,   348,   349,
//yy   350,   342,    -1,    -1,    -1,    -1,   347,   348,   349,   350,
//yy    -1,    -1,   813,   814,    -1,    -1,    -1,    -1,   546,    10,
//yy    -1,   774,   775,    -1,    -1,   545,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,     0,   704,    -1,    -1,   566,   708,
//yy    -1,    -1,   795,    -1,    10,    -1,   304,   848,   849,   307,
//yy   308,   309,   580,   854,   723,   724,   725,    44,   279,   280,
//yy    -1,   527,    -1,    -1,    -1,    -1,    -1,    -1,    59,    -1,
//yy   871,    -1,   330,    -1,    -1,    41,   829,    41,   879,   337,
//yy    44,   882,    -1,    41,   342,   886,    44,   840,   608,   347,
//yy   348,   349,   350,    59,   847,    -1,    -1,    61,    -1,    -1,
//yy    -1,    -1,    -1,    61,    91,   774,   775,    -1,    -1,   330,
//yy    -1,   279,   280,    -1,    -1,   635,   337,    -1,    -1,    -1,
//yy    -1,   342,    -1,    -1,   652,    -1,   795,    91,   594,    -1,
//yy    -1,    -1,   660,    91,   662,   663,    -1,   358,    -1,   304,
//yy   361,    -1,   307,   308,   309,    -1,   612,    -1,    -1,   279,
//yy   280,   617,    -1,    -1,    -1,   621,    -1,    -1,     0,   125,
//yy   829,    -1,   330,    -1,    -1,   330,   632,    -1,    10,   337,
//yy    -1,   840,   337,    -1,   342,    -1,    -1,   342,   847,    -1,
//yy   708,    -1,   347,   348,   349,   350,    -1,    -1,    -1,    -1,
//yy   358,   711,    -1,   361,    -1,   723,   724,   725,    -1,    41,
//yy   330,    -1,    44,    -1,    -1,    -1,    -1,   337,    -1,    -1,
//yy    -1,    -1,   342,    -1,    -1,   681,    58,    59,    -1,    61,
//yy    -1,    63,    -1,    -1,    -1,   691,    -1,    -1,   358,    -1,
//yy    -1,   361,    -1,   304,    -1,    -1,   307,   308,   309,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   774,   775,    -1,    91,
//yy    -1,    93,    -1,    -1,    -1,    -1,   722,    -1,    -1,   330,
//yy    -1,    -1,    -1,   729,    -1,    -1,   337,   795,    -1,   735,
//yy     0,   342,    -1,    -1,    -1,    -1,   347,   348,   349,   350,
//yy    10,    -1,    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   319,   279,   280,    -1,    -1,   262,   263,   264,    -1,
//yy    -1,   829,   268,   269,    -1,   271,   772,   335,   336,    -1,
//yy    -1,    41,   840,   304,    44,   279,   307,   308,   309,   847,
//yy   348,   279,   350,    -1,   352,   353,   354,   355,    58,    59,
//yy   358,    61,   360,    63,    -1,   801,    -1,    10,    -1,   330,
//yy    -1,    -1,    -1,   330,    -1,   811,   337,    -1,    -1,   815,
//yy   337,   342,    -1,    -1,    -1,   342,   347,   348,   349,   350,
//yy    -1,    91,    -1,    93,   304,    -1,   330,   307,   308,   309,
//yy    -1,   358,   330,   337,   361,    -1,    -1,    -1,    -1,   337,
//yy    -1,    -1,    -1,    -1,   850,    -1,   852,    -1,    -1,    -1,
//yy    63,    -1,    -1,    -1,   358,   125,    -1,    -1,    -1,    -1,
//yy   358,    -1,   342,    -1,   870,    -1,   872,   347,   348,   349,
//yy   350,    -1,    -1,    -1,   880,   257,   258,   259,   884,   261,
//yy   262,   263,   264,   265,   266,   267,   268,   269,   270,   271,
//yy   272,   273,   274,   275,   276,   277,   278,   279,   280,   281,
//yy   282,   283,   284,   285,   286,   287,   288,   289,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    58,   311,
//yy   312,   313,   314,    63,   316,   317,   318,   319,   320,   321,
//yy   322,   323,   324,   325,   326,   327,   328,   329,   330,   331,
//yy   332,    -1,    -1,   335,   336,   337,   338,   339,   340,   341,
//yy   342,   343,   344,   345,   346,   347,   348,   349,   350,   351,
//yy   352,   353,   354,   355,   356,   357,   358,   359,   360,   361,
//yy    -1,   363,   364,   365,   366,   367,   368,   257,   258,   259,
//yy    -1,   261,   262,   263,   264,   265,   266,   267,   268,   269,
//yy   270,   271,   272,   273,   274,   275,   276,   277,   278,   279,
//yy   280,   281,   282,   283,   284,   285,   286,   287,   288,   289,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,   319,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy   330,   331,   332,    -1,    -1,   335,   336,   337,   338,   339,
//yy   340,   341,   342,   343,   344,   345,   346,   347,   348,   349,
//yy   350,   351,   352,   353,   354,   355,   356,   357,   358,   359,
//yy   360,   361,     0,   363,   364,   365,   366,   367,   368,    -1,
//yy    -1,    -1,    10,    -1,    -1,    -1,   319,   320,   321,   322,
//yy   323,   324,   325,   326,   327,   328,   329,    -1,   331,   332,
//yy    -1,    -1,   335,   336,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    41,    -1,   348,    44,   350,    -1,   352,
//yy   353,   354,   355,   356,   357,   358,    -1,   360,    -1,    -1,
//yy    58,    59,    -1,    61,    -1,    63,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    91,    -1,    93,    -1,    -1,    -1,   319,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy    -1,   331,   332,    -1,     0,   335,   336,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    10,    -1,    -1,   125,   348,    -1,
//yy   350,    -1,   352,   353,   354,   355,   356,   357,   358,    -1,
//yy   360,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   304,   305,    -1,    -1,   308,    41,    -1,    -1,    44,    -1,
//yy    -1,    -1,   316,   317,    -1,   319,   320,   321,   322,    -1,
//yy   324,   325,    58,    59,   328,    61,    -1,    63,    -1,   333,
//yy   334,   335,   336,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   347,   348,    -1,   350,   351,   352,   353,
//yy   354,   355,   356,   357,   358,    91,   360,    93,   362,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    44,   319,   320,   321,   322,
//yy   323,   324,   325,   326,   327,   328,   329,    -1,    -1,    -1,
//yy    -1,    -1,   335,   336,    63,    -1,    -1,    -1,    -1,   125,
//yy    -1,    -1,    -1,    -1,    -1,   348,    -1,   350,    -1,   352,
//yy   353,   354,   355,   356,   357,   358,    -1,   360,    -1,   257,
//yy   258,   259,    -1,   261,   262,   263,   264,   265,   266,   267,
//yy   268,   269,   270,   271,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,   280,   281,   282,   283,   284,   285,   286,   287,
//yy   288,   289,   290,   291,    -1,   293,   294,   295,   296,   297,
//yy    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    63,   316,   317,
//yy   318,   319,   320,   321,   322,   323,   324,   325,   326,   327,
//yy   328,   329,   330,   331,   332,    -1,    -1,   335,   336,   337,
//yy   338,    -1,   340,   341,   342,   343,   344,   345,   346,   347,
//yy   348,   349,   350,   351,   352,   353,   354,   355,   356,   357,
//yy   358,   359,   360,   361,    -1,   363,   364,   365,   366,   367,
//yy   368,   257,   258,   259,    -1,   261,   262,   263,   264,   265,
//yy   266,   267,   268,   269,   270,   271,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,   280,   281,   282,   283,   284,   285,
//yy   286,   287,   288,   289,   290,   291,    -1,   293,   294,   295,
//yy   296,   297,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    63,
//yy   316,   317,   318,   319,   320,   321,   322,   323,   324,   325,
//yy   326,   327,   328,   329,   330,   331,   332,    -1,    -1,   335,
//yy   336,   337,   338,    -1,   340,   341,   342,   343,   344,   345,
//yy   346,   347,   348,   349,   350,   351,   352,   353,   354,   355,
//yy   356,   357,   358,   359,   360,   361,     0,   363,   364,   365,
//yy   366,   367,   368,    -1,    -1,    -1,    10,    -1,    -1,    -1,
//yy   319,   320,   321,   322,   323,   324,   325,   326,   327,   328,
//yy   329,    -1,   331,   332,    -1,    -1,   335,   336,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,    -1,   348,
//yy    44,   350,    -1,   352,   353,   354,   355,   356,   357,   358,
//yy    -1,   360,    -1,    -1,    58,    59,    -1,    61,    -1,    63,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    91,    -1,    93,
//yy    -1,    -1,    -1,   319,   320,   321,   322,   323,   324,   325,
//yy   326,   327,   328,   329,    -1,   331,   332,    -1,     0,   335,
//yy   336,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    10,    -1,
//yy    -1,   125,   348,    -1,   350,    -1,   352,   353,   354,   355,
//yy   356,   357,   358,    -1,   360,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   304,   305,    -1,    -1,   308,    41,
//yy    -1,    -1,    44,    -1,    -1,    -1,   316,   317,    -1,   319,
//yy   320,   321,   322,    -1,   324,   325,    58,    59,   328,    -1,
//yy    -1,    63,    -1,   333,   334,   335,   336,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   347,   348,    -1,
//yy   350,   351,   352,   353,   354,   355,   356,   357,   358,    91,
//yy   360,    93,   362,    -1,    -1,   319,   320,   321,   322,   323,
//yy   324,   325,   326,   327,   328,   329,    -1,   331,   332,    -1,
//yy    -1,   335,   336,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   125,   348,    -1,   350,    -1,   352,   353,
//yy   354,   355,   356,   357,   358,    -1,   360,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   257,   258,   259,    -1,   261,   262,   263,
//yy   264,   265,   266,   267,   268,   269,   270,   271,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,   280,   281,   282,   283,
//yy   284,   285,   286,   287,   288,   289,   290,   291,    -1,   293,
//yy   294,   295,   296,   297,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,   316,   317,   318,   319,   320,   321,   322,   323,
//yy   324,   325,   326,   327,   328,   329,   330,   331,   332,    -1,
//yy    -1,   335,   336,   337,   338,    -1,   340,   341,   342,   343,
//yy   344,   345,   346,   347,   348,   349,   350,   351,   352,   353,
//yy   354,   355,   356,   357,   358,   359,   360,   361,    -1,   363,
//yy   364,   365,   366,   367,   368,   257,   258,   259,    -1,   261,
//yy   262,   263,   264,   265,   266,   267,   268,   269,   270,   271,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,   280,   281,
//yy   282,   283,   284,   285,   286,   287,   288,   289,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,   319,   320,   321,
//yy   322,   323,   324,   325,   326,   327,   328,   329,   330,   331,
//yy   332,    -1,    -1,   335,   336,   337,   338,    -1,   340,   341,
//yy   342,   343,   344,   345,   346,   347,   348,   349,   350,   351,
//yy   352,   353,   354,   355,   356,   357,   358,   359,   360,   361,
//yy     0,   363,   364,   365,   366,   367,   368,    -1,    -1,    -1,
//yy    10,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    41,    -1,    -1,    44,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   257,   258,   259,    -1,   261,    58,    59,
//yy    -1,   265,   266,    63,    -1,    -1,   270,    -1,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,
//yy   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,    -1,
//yy    -1,    91,    -1,    93,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,     0,    -1,   318,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    10,    -1,    -1,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,
//yy   344,   345,    -1,   347,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    41,   358,    -1,    44,    -1,    -1,   363,
//yy   364,   365,   366,   367,   368,   304,   305,    -1,    -1,   308,
//yy    58,    59,    -1,    -1,    -1,    63,    -1,   316,   317,    -1,
//yy   319,   320,   321,   322,    -1,   324,   325,    -1,    -1,   328,
//yy    -1,    -1,    -1,    -1,   333,   334,   335,   336,    -1,    -1,
//yy    -1,    -1,    -1,    91,    -1,    93,    -1,    -1,   347,   348,
//yy    -1,   350,   351,   352,   353,   354,   355,   356,   357,   358,
//yy    -1,   360,    -1,   362,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   257,   258,   259,
//yy    -1,   261,   262,   263,   264,   265,   266,   267,   268,   269,
//yy   270,   271,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy   280,   281,   282,   283,   284,   285,   286,   287,   288,   289,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,   319,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy   330,   331,   332,    -1,    -1,   335,   336,   337,   338,    -1,
//yy   340,   341,   342,   343,   344,   345,   346,   347,   348,   349,
//yy   350,   351,   352,   353,   354,   355,   356,   357,   358,   359,
//yy   360,   361,    -1,   363,   364,   365,   366,   367,   368,   257,
//yy   258,   259,    -1,   261,   262,   263,   264,   265,   266,   267,
//yy   268,   269,   270,   271,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,   280,   281,   282,   283,   284,   285,   286,   287,
//yy   288,   289,   290,   291,    -1,   293,   294,   295,   296,   297,
//yy    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,   319,   320,   321,   322,   323,   324,   325,   326,   327,
//yy   328,   329,   330,   331,   332,    -1,    -1,   335,   336,   337,
//yy   338,    -1,   340,   341,   342,   343,   344,   345,   346,   347,
//yy   348,   349,   350,   351,   352,   353,   354,   355,   356,   357,
//yy   358,   359,   360,   361,     0,   363,   364,   365,   366,   367,
//yy   368,    -1,    -1,    -1,    10,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   257,   258,   259,    -1,
//yy   261,    -1,    58,    59,   265,   266,    -1,    63,    -1,   270,
//yy    -1,   272,   273,   274,   275,   276,   277,   278,    -1,    -1,
//yy    -1,    -1,   283,   284,   285,   286,   287,   288,   289,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    91,    -1,    93,   299,    -1,
//yy    -1,   302,   303,   304,   305,   306,   307,   308,   309,    -1,
//yy   311,   312,   313,   314,    -1,    -1,     0,   318,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    10,    -1,    -1,   125,
//yy    -1,    -1,    -1,    -1,   335,    -1,    -1,   338,    -1,    -1,
//yy   341,    -1,   343,   344,   345,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,
//yy    44,    -1,   363,   364,   365,   366,   367,   368,   304,   305,
//yy    -1,    -1,   308,    -1,    58,    59,    -1,    -1,    -1,    63,
//yy   316,   317,    -1,   319,   320,   321,   322,    -1,   324,   325,
//yy    -1,    -1,   328,    -1,    -1,    -1,    -1,   333,   334,   335,
//yy   336,    -1,    -1,    -1,    -1,    -1,    -1,    91,    -1,    93,
//yy    -1,   347,   348,    -1,   350,   351,   352,   353,   354,   355,
//yy   356,   357,   358,    -1,   360,    -1,   362,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   257,   258,   259,    -1,   261,   262,   263,   264,   265,
//yy   266,   267,   268,   269,   270,   271,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,   280,   281,   282,   283,   284,   285,
//yy   286,   287,   288,   289,   290,   291,    -1,   293,   294,   295,
//yy   296,   297,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    -1,
//yy   316,   317,   318,   319,   320,   321,   322,   323,   324,   325,
//yy   326,   327,   328,   329,   330,   331,   332,    -1,    -1,   335,
//yy   336,   337,   338,    -1,   340,   341,    -1,   343,   344,   345,
//yy   346,   347,   348,   349,   350,   351,   352,   353,   354,   355,
//yy   356,   357,   358,   359,   360,   361,    -1,   363,   364,   365,
//yy   366,   367,   368,   257,   258,   259,    -1,   261,   262,   263,
//yy   264,   265,   266,   267,   268,   269,   270,   271,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,   280,   281,   282,   283,
//yy   284,   285,   286,   287,   288,   289,   290,   291,    -1,   293,
//yy   294,   295,   296,   297,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,   316,   317,   318,   319,   320,   321,   322,   323,
//yy   324,   325,   326,   327,   328,   329,   330,   331,   332,    -1,
//yy    -1,   335,   336,   337,   338,    -1,   340,   341,    -1,   343,
//yy   344,   345,   346,   347,   348,   349,   350,   351,   352,   353,
//yy   354,   355,   356,   357,   358,   359,   360,   361,     0,   363,
//yy   364,   365,   366,   367,   368,    -1,    -1,    -1,    10,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   304,   305,    -1,    -1,   308,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   316,   317,    -1,   319,   320,   321,   322,    41,
//yy   324,   325,    44,    -1,   328,    -1,    -1,    -1,    -1,   333,
//yy   334,   335,   336,    -1,    -1,    -1,    58,    59,    -1,    61,
//yy    -1,    63,    -1,   347,   348,    -1,   350,   351,   352,   353,
//yy   354,   355,   356,   357,   358,    -1,   360,    -1,   362,    -1,
//yy    -1,    -1,   304,   305,    -1,    -1,   308,    -1,    -1,    91,
//yy    -1,    93,    -1,    -1,   316,   317,    -1,   319,   320,   321,
//yy   322,    -1,   324,   325,    -1,    -1,   328,    -1,    -1,    -1,
//yy     0,   333,   334,   335,   336,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    -1,   125,    -1,   347,   348,    -1,   350,   351,
//yy   352,   353,   354,   355,   356,   357,   358,    -1,   360,    -1,
//yy   362,    -1,    -1,    -1,    -1,    -1,   304,   305,    -1,    -1,
//yy   308,    41,    -1,    -1,    44,    -1,    -1,    -1,   316,   317,
//yy    -1,   319,   320,   321,   322,    -1,   324,   325,    58,    59,
//yy   328,    -1,    -1,    63,    -1,   333,   334,   335,   336,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   347,
//yy   348,    -1,   350,   351,   352,   353,   354,   355,   356,   357,
//yy   358,    91,   360,    93,   362,   319,   320,   321,   322,   323,
//yy   324,   325,   326,    -1,   328,   329,    -1,    -1,    -1,    -1,
//yy    -1,   335,   336,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   348,   125,   350,    -1,   352,   353,
//yy   354,   355,   356,   357,   358,    -1,   360,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   257,   258,   259,    -1,   261,
//yy   262,   263,   264,   265,   266,   267,   268,   269,   270,   271,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,   281,
//yy   282,   283,   284,   285,   286,   287,   288,   289,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,   319,   320,   321,
//yy   322,   323,   324,   325,   326,   327,   328,   329,   330,   331,
//yy   332,    -1,    -1,   335,   336,   337,   338,    -1,   340,   341,
//yy   342,   343,   344,   345,   346,   347,   348,   349,   350,   351,
//yy   352,   353,   354,   355,   356,   357,   358,   359,   360,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   257,   258,   259,
//yy    -1,   261,   262,   263,   264,   265,   266,   267,   268,   269,
//yy   270,   271,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,   281,   282,   283,   284,   285,   286,   287,   288,   289,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,   319,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy   330,   331,   332,    -1,    -1,   335,   336,   337,   338,    -1,
//yy   340,   341,    -1,   343,   344,   345,   346,   347,   348,   349,
//yy   350,   351,   352,   353,   354,   355,   356,   357,   358,   359,
//yy   360,     0,    -1,   363,   364,   365,   366,   367,   368,    -1,
//yy    -1,    10,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   304,   305,    -1,    -1,   308,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   316,   317,    -1,   319,   320,
//yy   321,   322,    41,   324,   325,    44,    -1,   328,    -1,    -1,
//yy    -1,    -1,   333,   334,   335,   336,    -1,    -1,    -1,    -1,
//yy    59,    -1,    61,    -1,    63,    -1,   347,   348,    -1,   350,
//yy   351,   352,   353,   354,   355,   356,   357,   358,    -1,   360,
//yy    -1,   362,    -1,    -1,    -1,   304,   305,    -1,    -1,   308,
//yy    -1,    -1,    91,    -1,    -1,    -1,    -1,   316,   317,    -1,
//yy   319,   320,   321,   322,    -1,   324,   325,    -1,    -1,   328,
//yy    -1,    -1,    -1,     0,   333,   334,   335,   336,    -1,    -1,
//yy    -1,    -1,    -1,    10,    -1,    -1,   125,    -1,   347,   348,
//yy    -1,   350,   351,   352,   353,   354,   355,   356,   357,   358,
//yy    -1,   360,    -1,   362,    -1,    -1,    -1,    -1,    -1,   304,
//yy   305,    -1,    -1,   308,    41,    -1,    -1,    44,    -1,    -1,
//yy    -1,   316,   317,    -1,   319,   320,   321,   322,    -1,   324,
//yy   325,    -1,    59,   328,    61,    -1,    63,    -1,   333,   334,
//yy   335,   336,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   347,   348,    -1,   350,   351,   352,   353,   354,
//yy   355,   356,   357,   358,    91,   360,    -1,   362,   319,   320,
//yy   321,   322,   323,   324,   325,    -1,    -1,   328,   329,    -1,
//yy    -1,    -1,    -1,    -1,   335,   336,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   348,   125,   350,
//yy    -1,   352,   353,   354,   355,   356,   357,   358,    -1,   360,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   257,   258,
//yy   259,    -1,   261,   262,   263,   264,   265,   266,    -1,   268,
//yy   269,   270,   271,   272,   273,   274,   275,   276,   277,   278,
//yy    -1,   280,    -1,    -1,   283,   284,   285,   286,   287,   288,
//yy   289,   290,   291,    -1,   293,   294,   295,   296,   297,    -1,
//yy   299,    -1,    -1,   302,   303,   304,   305,   306,   307,   308,
//yy   309,    -1,   311,   312,   313,   314,    -1,   316,   317,   318,
//yy   319,   320,   321,   322,   323,   324,   325,   326,   327,   328,
//yy   329,   330,   331,   332,    -1,    -1,   335,   336,   337,   338,
//yy    -1,    -1,   341,   342,   343,   344,   345,    -1,   347,   348,
//yy   349,   350,   351,   352,   353,   354,   355,   356,   357,   358,
//yy   359,   360,   361,    -1,   363,   364,   365,   366,   367,   368,
//yy   257,   258,   259,    -1,   261,   262,   263,   264,   265,   266,
//yy    -1,   268,   269,   270,   271,   272,   273,   274,   275,   276,
//yy   277,   278,    -1,   280,    -1,    -1,   283,   284,   285,   286,
//yy   287,   288,   289,   290,   291,    -1,   293,   294,   295,   296,
//yy   297,    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,
//yy   307,   308,   309,    -1,   311,   312,   313,   314,    -1,   316,
//yy   317,   318,   319,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,   330,   331,   332,    -1,    -1,   335,   336,
//yy   337,   338,    -1,    -1,   341,   342,   343,   344,   345,    -1,
//yy   347,   348,   349,   350,   351,   352,   353,   354,   355,   356,
//yy   357,   358,   359,   360,   361,     0,   363,   364,   365,   366,
//yy   367,   368,    -1,    -1,    -1,    10,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   304,   305,    -1,
//yy    -1,   308,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   316,
//yy   317,    -1,   319,   320,   321,   322,    41,   324,   325,    44,
//yy    -1,   328,    -1,    -1,    -1,    -1,   333,   334,   335,   336,
//yy    -1,    -1,    -1,    -1,    59,    -1,    61,    -1,    63,    -1,
//yy   347,   348,    -1,   350,   351,   352,   353,   354,   355,   356,
//yy   357,   358,    -1,   360,    -1,   362,    -1,    -1,    -1,   304,
//yy   305,    -1,    -1,   308,    -1,    -1,    91,    -1,    -1,    -1,
//yy    -1,   316,   317,    -1,   319,   320,   321,   322,    -1,   324,
//yy   325,    -1,    -1,   328,    -1,    -1,    -1,     0,   333,   334,
//yy   335,   336,    -1,    -1,    -1,    -1,    -1,    10,    -1,    -1,
//yy   125,    -1,   347,   348,    -1,   350,   351,   352,   353,   354,
//yy   355,   356,   357,   358,    -1,   360,    -1,   362,   319,    -1,
//yy    -1,    -1,    -1,   324,   325,    -1,    -1,    -1,    41,    -1,
//yy    -1,    44,    -1,    -1,   335,   336,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    59,   348,    61,   350,
//yy    63,   352,   353,   354,   355,   356,   357,   358,    -1,   360,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    91,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   257,   258,   259,    -1,   261,   262,   263,   264,
//yy   265,   266,    -1,   268,   269,   270,   271,   272,   273,   274,
//yy   275,   276,   277,   278,    -1,   280,    -1,    -1,   283,   284,
//yy   285,   286,   287,   288,   289,   290,   291,    -1,   293,   294,
//yy   295,   296,   297,    -1,   299,    -1,    -1,   302,   303,   304,
//yy   305,   306,   307,   308,   309,    -1,   311,   312,   313,   314,
//yy    -1,   316,   317,   318,   319,   320,   321,   322,   323,   324,
//yy   325,   326,   327,   328,   329,   330,   331,   332,    -1,    -1,
//yy   335,   336,   337,   338,    -1,    -1,   341,   342,   343,   344,
//yy   345,    -1,   347,   348,   349,   350,   351,   352,   353,   354,
//yy   355,   356,   357,   358,   359,   360,   361,    -1,   363,   364,
//yy   365,   366,   367,   368,   257,   258,   259,    -1,   261,   262,
//yy   263,   264,   265,   266,    -1,   268,   269,   270,   271,   272,
//yy   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,
//yy   283,   284,   285,   286,   287,   288,   289,   290,   291,    -1,
//yy   293,   294,   295,   296,   297,    -1,   299,    -1,    -1,   302,
//yy   303,   304,   305,   306,   307,   308,   309,    -1,   311,   312,
//yy   313,   314,    -1,   316,   317,   318,   319,   320,   321,   322,
//yy   323,   324,   325,   326,   327,   328,   329,   330,   331,   332,
//yy    -1,    -1,   335,   336,   337,   338,    -1,    -1,   341,   342,
//yy   343,   344,   345,    -1,   347,   348,   349,   350,   351,   352,
//yy   353,   354,   355,   356,   357,   358,   359,   360,     0,    -1,
//yy   363,   364,   365,   366,   367,   368,    -1,    -1,    10,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,     0,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    10,     0,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    10,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,
//yy    -1,    -1,    44,    -1,    -1,     0,    -1,    -1,    -1,    -1,
//yy    41,    -1,    -1,    -1,    -1,    10,    -1,    59,    -1,    -1,
//yy     0,    -1,    -1,   125,    -1,    -1,    -1,    -1,    59,    -1,
//yy    10,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    41,    -1,    58,    59,    -1,    -1,    -1,    63,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
//yy    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   125,    -1,    91,    -1,    93,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   125,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   257,   258,   259,    -1,   261,
//yy   262,   263,   264,   265,   266,   267,   268,   269,   270,   271,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,   281,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,
//yy   262,   263,   264,    -1,    -1,    -1,   268,   269,   330,   271,
//yy    -1,   262,   263,   264,    -1,   337,   338,   268,   269,   341,
//yy   271,   343,   344,   345,    -1,   347,    -1,   349,    -1,   351,
//yy    -1,   293,   294,   295,   296,   297,    -1,   359,    -1,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   262,   263,   264,
//yy    -1,    -1,   267,   268,   269,    -1,   271,    -1,    -1,    -1,
//yy     0,    -1,   262,   263,   264,   280,   281,   282,   268,   269,
//yy    10,   271,    -1,    -1,    -1,   290,   291,    -1,   293,   294,
//yy   295,   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    41,    -1,    -1,   319,   320,   321,   322,   323,   324,
//yy   325,   326,   327,   328,   329,   330,   331,   332,     0,    59,
//yy   335,   336,   337,    -1,    -1,   340,    -1,    -1,    10,    -1,
//yy    -1,   346,    -1,   348,    -1,   350,    -1,   352,   353,   354,
//yy   355,   356,   357,   358,    -1,   360,   361,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,     0,    41,
//yy    -1,    -1,    44,    -1,    -1,    -1,    -1,    -1,    10,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
//yy    -1,    63,    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    91,
//yy    -1,    93,    -1,    -1,    -1,    -1,    -1,    59,     0,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    10,    -1,
//yy    -1,    -1,    -1,    -1,     0,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   125,    10,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,
//yy    -1,    -1,    44,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    41,    58,    59,    -1,    61,
//yy    -1,    63,    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    59,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    91,
//yy    -1,    93,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,    -1,   268,   269,
//yy    -1,   271,     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    10,   125,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   293,   294,   295,   296,   297,    -1,   125,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,    -1,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,   271,
//yy    58,    59,    -1,    61,    -1,    63,    -1,    -1,   280,   281,
//yy   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy   262,   263,   264,    91,    -1,    93,   268,   269,    -1,   271,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   319,   320,   321,
//yy   322,   323,   324,   325,   326,   327,   328,   329,   330,   331,
//yy   332,    -1,    -1,   335,   336,   337,    -1,   125,   340,    -1,
//yy    -1,    -1,    -1,    -1,   346,    -1,   348,    -1,   350,    -1,
//yy   352,   353,   354,   355,   356,   357,   358,    -1,   360,   361,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,   271,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   262,   263,   264,   281,
//yy   282,    -1,   268,   269,    -1,   271,    -1,    -1,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,     0,    -1,    -1,   293,   294,   295,
//yy   296,   297,    -1,    -1,    10,    -1,    -1,   319,   320,   321,
//yy   322,   323,   324,   325,   326,   327,   328,   329,   330,   331,
//yy   332,    -1,    -1,   335,   336,   337,    -1,   339,   340,    -1,
//yy    -1,    -1,    -1,    -1,   346,    41,   348,    -1,   350,    -1,
//yy   352,   353,   354,   355,   356,   357,   358,    -1,   360,    -1,
//yy     0,    -1,    58,    59,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    41,   290,   291,    44,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
//yy    -1,    61,    -1,    63,    -1,    -1,    -1,    -1,    -1,   125,
//yy    -1,   319,   320,   321,   322,   323,   324,   325,   326,   327,
//yy   328,   329,   330,   331,   332,    -1,    -1,   335,   336,   337,
//yy    -1,    91,   340,    93,    -1,    -1,    -1,    -1,   346,    -1,
//yy   348,     0,   350,    -1,   352,   353,   354,   355,   356,   357,
//yy   358,    10,   360,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    41,    -1,    -1,    44,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
//yy    59,    -1,    61,    -1,    63,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    91,    -1,    93,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,     0,   271,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    10,   281,   125,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   290,   291,    -1,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,
//yy    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    91,    -1,    93,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   319,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy   330,   331,   332,    -1,    -1,   335,   336,   337,    -1,   125,
//yy   340,    -1,    -1,    -1,    -1,    -1,   346,    -1,   348,     0,
//yy   350,    -1,   352,   353,   354,   355,   356,   357,   358,    10,
//yy   360,    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,
//yy   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    41,   290,   291,    44,   293,   294,   295,   296,   297,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
//yy    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   319,   320,   321,   322,   323,   324,   325,   326,   327,   328,
//yy   329,   330,   331,   332,    -1,    -1,   335,   336,   337,    -1,
//yy    91,   340,    93,    -1,    -1,    -1,    -1,   346,    -1,   348,
//yy    -1,   350,    -1,   352,   353,   354,   355,   356,   357,   358,
//yy    -1,   360,     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    10,    -1,   125,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    41,   290,   291,    44,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   319,   320,   321,   322,   323,   324,   325,
//yy   326,   327,   328,   329,   330,   331,   332,    -1,    -1,   335,
//yy   336,   337,    -1,    91,   340,    93,    -1,    -1,    -1,    -1,
//yy   346,    -1,   348,     0,   350,    -1,   352,   353,   354,   355,
//yy   356,   357,   358,    10,   360,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,    -1,
//yy    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,
//yy   271,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,
//yy   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,
//yy   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    91,    -1,    93,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   319,   320,
//yy   321,   322,   323,   324,   325,   326,   327,   328,   329,   330,
//yy   331,   332,    -1,    -1,   335,   336,   337,    -1,   125,   340,
//yy    -1,    -1,    -1,    -1,    -1,   346,    -1,   348,    -1,   350,
//yy    -1,   352,   353,   354,   355,   356,   357,   358,    -1,   360,
//yy     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    41,   290,   291,    44,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
//yy    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   319,   320,   321,   322,   323,   324,   325,   326,   327,
//yy   328,   329,   330,   331,   332,    -1,    -1,   335,   336,   337,
//yy    -1,    91,   340,    93,    -1,    -1,    -1,    -1,   346,    -1,
//yy   348,     0,   350,    -1,   352,   353,   354,   355,   356,   357,
//yy   358,    10,   360,    -1,    -1,   262,   263,   264,    -1,    -1,
//yy   267,   268,   269,    -1,   271,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    41,   290,   291,    44,   293,   294,   295,   296,
//yy   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
//yy    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   319,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,   330,   331,   332,    -1,    -1,   335,   336,
//yy   337,    -1,    91,   340,    93,    -1,    -1,    -1,    -1,   346,
//yy    -1,   348,    -1,   350,    -1,   352,   353,   354,   355,   356,
//yy   357,   358,    -1,   360,     0,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    10,    -1,   125,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,
//yy    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    91,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy   330,   331,   332,    -1,    -1,   335,   336,   337,    -1,   125,
//yy   340,    -1,    -1,    -1,    -1,    -1,   346,    -1,   348,     0,
//yy   350,    -1,   352,   353,   354,   355,   356,   357,   358,    10,
//yy   360,    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,
//yy   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    41,   290,   291,    44,   293,   294,   295,   296,   297,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
//yy    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   320,   321,   322,   323,   324,   325,   326,   327,   328,
//yy   329,   330,   331,   332,    -1,    -1,   335,   336,   337,    -1,
//yy    91,   340,    -1,    -1,    -1,    -1,    -1,   346,    -1,   348,
//yy    -1,   350,    -1,   352,   353,   354,   355,   356,   357,   358,
//yy    -1,   360,     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    10,    -1,   125,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   281,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    41,   290,   291,    44,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   319,   320,   321,   322,   323,   324,   325,
//yy   326,   327,   328,   329,   330,   331,   332,    -1,    -1,   335,
//yy   336,   337,    -1,    91,   340,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   348,     0,   350,    -1,   352,   353,   354,   355,
//yy   356,   357,   358,    10,   360,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,    -1,
//yy    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,
//yy   271,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,
//yy   281,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,
//yy   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   319,   320,
//yy   321,   322,   323,   324,   325,   326,   327,   328,   329,   330,
//yy   331,   332,    -1,    -1,   335,   336,   337,    -1,   125,   340,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   348,    -1,   350,
//yy    -1,   352,   353,   354,   355,   356,   357,   358,    -1,   360,
//yy     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   281,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    41,   290,   291,    44,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
//yy    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   319,   320,   321,   322,   323,   324,   325,   326,   327,
//yy   328,   329,   330,   331,   332,    -1,    -1,   335,   336,   337,
//yy    -1,    -1,   340,    93,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   348,     0,   350,    -1,   352,   353,   354,   355,   356,   357,
//yy   358,    10,   360,    -1,    -1,   262,   263,   264,    -1,    -1,
//yy   267,   268,   269,    -1,   271,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    41,   290,   291,    44,   293,   294,   295,   296,
//yy   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
//yy    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,    -1,   331,   332,    -1,    -1,   335,   336,
//yy    -1,    -1,    -1,   340,    93,    -1,    -1,    -1,    -1,   346,
//yy    -1,   348,    -1,   350,    -1,   352,   353,   354,   355,   356,
//yy   357,   358,    -1,   360,     0,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    10,    -1,   125,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,
//yy    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy    -1,   331,   332,    -1,    -1,   335,   336,    -1,    -1,   125,
//yy   340,    -1,    -1,    -1,    -1,    -1,   346,    -1,   348,     0,
//yy   350,    -1,   352,   353,   354,   355,   356,   357,   358,    10,
//yy   360,    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,
//yy   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    41,   290,   291,    44,   293,   294,   295,   296,   297,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
//yy    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   320,   321,   322,   323,   324,   325,   326,   327,   328,
//yy   329,    -1,   331,   332,    -1,    -1,   335,   336,    -1,    -1,
//yy    -1,   340,    93,    -1,    -1,    -1,    -1,   346,    -1,   348,
//yy    -1,   350,    -1,   352,   353,   354,   355,   356,   357,   358,
//yy    -1,   360,     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    10,    -1,   125,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    41,   290,   291,    44,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   320,   321,   322,   323,   324,   325,
//yy   326,   327,   328,   329,    -1,   331,   332,    -1,    -1,   335,
//yy   336,    -1,    -1,    -1,   340,    93,    -1,    -1,    -1,    -1,
//yy   346,    -1,   348,     0,   350,    -1,   352,   353,   354,   355,
//yy   356,   357,   358,    10,   360,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,    -1,
//yy    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,
//yy   271,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,
//yy   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,
//yy   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   320,
//yy   321,   322,   323,   324,   325,   326,   327,   328,   329,    -1,
//yy   331,   332,    -1,    -1,   335,   336,    -1,    -1,   125,   340,
//yy    -1,    -1,    -1,    -1,    -1,   346,    -1,   348,    -1,   350,
//yy    -1,   352,   353,   354,   355,   356,   357,   358,    -1,   360,
//yy     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    41,   290,   291,    44,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
//yy    -1,    61,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   320,   321,   322,   323,   324,   325,   326,   327,
//yy   328,   329,    -1,   331,   332,    -1,    -1,   335,   336,    -1,
//yy    -1,    91,   340,    -1,    -1,    -1,    -1,    -1,   346,    -1,
//yy   348,     0,   350,    -1,   352,   353,   354,   355,   356,   357,
//yy   358,    10,   360,    -1,    -1,   262,   263,   264,    -1,    -1,
//yy   267,   268,   269,    -1,   271,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    41,   290,   291,    44,   293,   294,   295,   296,
//yy   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    59,    -1,    61,    -1,    63,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,    -1,   331,   332,    -1,    -1,   335,   336,
//yy    -1,    -1,    91,   340,    -1,    -1,    -1,    -1,    -1,   346,
//yy    -1,   348,    -1,   350,    -1,   352,   353,   354,   355,   356,
//yy   357,   358,    -1,   360,    -1,     0,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    10,   125,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,    -1,   268,   269,
//yy    -1,   271,    -1,    -1,    59,    -1,    61,    -1,    63,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    91,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   319,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy   330,   331,   332,    -1,    -1,   335,   336,   337,    -1,   339,
//yy   125,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   348,     0,
//yy   350,    -1,   352,   353,   354,   355,   356,   357,   358,    10,
//yy   360,    -1,    -1,   262,   263,   264,    -1,    -1,    -1,   268,
//yy   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    41,   290,   291,    44,   293,   294,   295,   296,   297,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    -1,
//yy    61,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   319,   320,   321,   322,   323,   324,   325,   326,   327,   328,
//yy   329,   330,   331,   332,    -1,    -1,   335,   336,   337,    -1,
//yy    91,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   348,
//yy    -1,   350,    -1,   352,   353,   354,   355,   356,   357,   358,
//yy     0,   360,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    -1,    -1,   125,    -1,    -1,   262,   263,   264,
//yy    -1,    -1,    -1,   268,   269,    -1,   271,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    41,    -1,    -1,    44,   290,   291,    -1,   293,   294,
//yy   295,   296,   297,    -1,    -1,    -1,    -1,    -1,    58,    59,
//yy    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   319,   320,   321,   322,   323,   324,
//yy   325,   326,   327,   328,   329,   330,   331,   332,    -1,    -1,
//yy   335,   336,   337,    93,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   348,     0,   350,    -1,   352,   353,   354,
//yy   355,   356,   357,   358,    10,   360,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,
//yy    -1,   262,   263,   264,    -1,    -1,    -1,   268,   269,    -1,
//yy   271,    -1,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,
//yy   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   319,   320,
//yy   321,   322,   323,   324,   325,   326,   327,   328,   329,   330,
//yy   331,   332,    -1,    -1,   335,   336,   337,    -1,    -1,   125,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   348,    -1,   350,
//yy    -1,   352,   353,   354,   355,   356,   357,   358,    -1,   360,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,     0,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,    10,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   320,   321,   322,   323,   324,   325,   326,   327,   328,   329,
//yy    41,   331,   332,    44,    -1,   335,   336,    -1,    -1,    -1,
//yy   340,    -1,    -1,    -1,    -1,    -1,   346,    58,    59,    -1,
//yy   350,    -1,    63,    -1,   354,   355,   356,   357,   358,    -1,
//yy   360,    -1,    -1,    -1,    -1,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    93,    -1,    -1,   281,   282,    -1,    -1,    -1,
//yy    -1,    -1,    -1,     0,   290,   291,    -1,   293,   294,   295,
//yy   296,   297,    -1,    10,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   320,   321,   322,   323,   324,   325,
//yy   326,   327,   328,   329,    41,   331,   332,    44,    -1,   335,
//yy   336,    -1,    -1,    -1,   340,    -1,    -1,    -1,    -1,    -1,
//yy   346,    58,    59,    -1,   350,    -1,    63,    -1,   354,   355,
//yy   356,   357,   358,    -1,   360,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,     0,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    10,    -1,    -1,    93,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,   125,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,
//yy   271,    -1,    -1,    -1,    -1,    -1,    93,    -1,    -1,    -1,
//yy   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,     0,   290,
//yy   291,    -1,   293,   294,   295,   296,   297,    -1,    10,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   320,
//yy   321,   322,   323,   324,   325,   326,   327,   328,   329,    41,
//yy   331,   332,    44,    -1,   335,   336,    -1,    -1,    -1,   340,
//yy    -1,    -1,    -1,    -1,    -1,   346,    58,    59,    -1,   350,
//yy    -1,    63,    -1,    -1,    -1,   356,   357,   358,    -1,   360,
//yy    -1,    -1,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,
//yy   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    93,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   290,   291,    -1,   293,   294,   295,   296,
//yy   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,    -1,   331,   332,    -1,    -1,   335,   336,
//yy    -1,    -1,    -1,   340,    -1,   262,   263,   264,    -1,   346,
//yy   267,   268,   269,   350,   271,    -1,    -1,    -1,    -1,   356,
//yy   357,   358,    -1,   360,   281,   282,    -1,    -1,    -1,    -1,
//yy    -1,    -1,     0,   290,   291,    -1,   293,   294,   295,   296,
//yy   297,    -1,    10,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,    41,   331,   332,    44,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   340,    -1,    -1,    -1,    -1,    -1,   346,
//yy    58,    59,    -1,   350,    -1,    63,    -1,    -1,    -1,   356,
//yy   357,   358,    -1,   360,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,   271,
//yy    -1,    -1,    -1,    -1,    -1,    93,    -1,    -1,    -1,   281,
//yy   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,    10,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   320,   321,
//yy   322,   323,   324,   325,   326,   327,   328,   329,    -1,   331,
//yy   332,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   340,    -1,
//yy    -1,    -1,    -1,    -1,   346,    -1,    59,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   356,   357,   358,    -1,   360,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    91,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    59,
//yy    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   290,   291,    -1,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    91,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   320,   321,   322,   323,   324,   325,   326,   327,
//yy   328,   329,    -1,   331,   332,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   340,    -1,    -1,    -1,    -1,    -1,   346,    10,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   356,   357,
//yy   358,    -1,   360,   256,   257,   258,   259,   260,   261,   262,
//yy   263,   264,   265,   266,    -1,    -1,   269,   270,    -1,   272,
//yy   273,   274,   275,   276,   277,   278,    -1,   280,    -1,    -1,
//yy   283,   284,   285,   286,   287,   288,   289,    -1,    59,   292,
//yy    -1,    -1,    -1,    -1,    -1,   298,   299,   300,   301,   302,
//yy   303,   304,   305,   306,   307,   308,   309,    -1,   311,   312,
//yy   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,    -1,
//yy    91,    -1,    -1,    -1,    -1,    -1,    -1,   330,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   337,   338,    -1,    -1,   341,   342,
//yy   343,   344,   345,    -1,   347,    -1,    -1,    -1,   351,    -1,
//yy    -1,    -1,    10,   356,    -1,    -1,   359,    -1,   361,    -1,
//yy   363,   364,   365,   366,   367,   368,   256,   257,   258,   259,
//yy   260,   261,   262,   263,   264,   265,   266,    -1,    -1,   269,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy   280,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    59,   292,    -1,    -1,    -1,    -1,    -1,   298,   299,
//yy   300,   301,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,    -1,
//yy    -1,    -1,    -1,    91,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   330,    -1,    -1,    -1,    -1,    -1,    -1,   337,   338,    -1,
//yy    -1,   341,   342,   343,   344,   345,    -1,   347,    -1,    -1,
//yy    -1,   351,    -1,    -1,    -1,    -1,   356,    -1,    -1,   359,
//yy    10,   361,    -1,   363,   364,   365,   366,   367,   368,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   256,   257,   258,   259,   260,
//yy   261,   262,   263,   264,   265,   266,    -1,    -1,   269,   270,
//yy    -1,   272,   273,   274,   275,   276,   277,   278,    -1,   280,
//yy    -1,    -1,   283,   284,   285,   286,   287,   288,   289,    59,
//yy    -1,   292,    -1,    -1,    -1,    -1,    -1,   298,   299,   300,
//yy   301,   302,   303,   304,   305,   306,   307,   308,   309,    -1,
//yy   311,   312,   313,   314,    -1,   316,   317,   318,    -1,    -1,
//yy    -1,    91,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   330,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   337,   338,    -1,    -1,
//yy   341,   342,   343,   344,   345,    -1,   347,    -1,    -1,    -1,
//yy   351,    -1,    -1,    10,    -1,   356,    -1,    -1,   359,    -1,
//yy   361,    -1,   363,   364,   365,   366,   367,   368,   256,   257,
//yy   258,   259,   260,   261,   262,   263,   264,   265,   266,    -1,
//yy    -1,   269,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    59,    -1,   292,    -1,    -1,    -1,    -1,    -1,
//yy   298,   299,   300,   301,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   330,    -1,    -1,    -1,    -1,    -1,    -1,   337,
//yy   338,    -1,    -1,   341,   342,   343,   344,   345,    -1,   347,
//yy    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,   356,    -1,
//yy    -1,   359,    10,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,    -1,    -1,    -1,    -1,    -1,   256,   257,   258,   259,
//yy   260,   261,   262,   263,   264,   265,   266,    -1,    -1,   269,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    59,   292,    -1,    -1,    -1,    -1,    -1,   298,   299,
//yy   300,   301,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   330,    -1,    -1,    -1,    -1,    -1,    -1,   337,   338,    -1,
//yy    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,    -1,
//yy    -1,   351,    -1,    -1,    -1,    10,   356,    -1,    -1,   359,
//yy    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,   256,
//yy   257,   258,   259,   260,   261,   262,   263,   264,   265,   266,
//yy    -1,   268,   269,   270,   271,   272,   273,   274,   275,   276,
//yy   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,
//yy   287,   288,   289,    -1,    59,   292,    -1,    -1,    -1,    -1,
//yy    -1,   298,   299,   300,   301,   302,   303,   304,   305,   306,
//yy   307,   308,   309,    -1,   311,   312,   313,   314,    -1,   316,
//yy   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,
//yy   347,    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,    -1,
//yy   125,    -1,   359,    10,    -1,    -1,   363,   364,   365,   366,
//yy   367,   368,    -1,    -1,    -1,    -1,    -1,    -1,   256,   257,
//yy   258,   259,   260,   261,    -1,    -1,   264,   265,   266,    -1,
//yy    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    59,    -1,   292,    -1,    -1,    -1,    -1,    -1,
//yy   298,   299,   300,   301,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,   347,
//yy    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,   256,   257,   258,   259,   260,   261,    -1,    -1,    -1,
//yy   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,
//yy   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,
//yy   285,   286,   287,   288,   289,    -1,    -1,   292,    -1,    -1,
//yy    -1,    -1,    -1,   298,   299,   300,   301,   302,   303,   304,
//yy   305,   306,   307,   308,   309,    -1,   311,   312,   313,   314,
//yy    -1,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   338,     0,    -1,   341,    -1,   343,   344,
//yy   345,    -1,   347,    -1,    10,    -1,   351,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,   364,
//yy   365,   366,   367,   368,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   257,   258,   259,    -1,   261,    41,    -1,    -1,   265,   266,
//yy    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,
//yy   277,   278,    58,    59,    -1,    -1,   283,   284,   285,   286,
//yy   287,   288,   289,     0,    -1,   292,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   299,    10,    -1,   302,   303,   304,   305,   306,
//yy   307,   308,   309,    -1,   311,   312,   313,   314,    -1,   316,
//yy   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    41,    -1,    -1,    44,    -1,    -1,
//yy    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,   125,
//yy    -1,    58,    59,    -1,   351,    -1,    63,    -1,    -1,    -1,
//yy    -1,    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,
//yy   367,   368,    -1,     0,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    10,    -1,    -1,    93,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    41,    -1,     0,    44,   125,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    10,    -1,    -1,    -1,
//yy    -1,    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,     0,    -1,    -1,    -1,    41,    -1,    -1,
//yy    44,    -1,    -1,    10,    -1,    -1,    93,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    59,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,    41,   271,    -1,    44,   125,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   281,    -1,    -1,    -1,    -1,
//yy    -1,    58,    59,    -1,   290,   291,    63,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   125,    -1,    -1,    -1,    -1,    93,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,
//yy   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,   125,    -1,
//yy    -1,    -1,    -1,   290,   291,    -1,   293,   294,   295,   296,
//yy   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,    -1,   331,   332,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   340,    -1,   262,   263,   264,    -1,   346,
//yy   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,   356,
//yy   357,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   290,   291,    -1,   293,   294,   295,   296,
//yy   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   262,   263,
//yy   264,    -1,     0,    -1,   268,   269,    -1,   271,    -1,    -1,
//yy    -1,    -1,    10,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,    -1,   331,   332,   290,   291,    -1,   293,
//yy   294,   295,   296,   340,    -1,   262,   263,   264,    -1,   346,
//yy   267,   268,   269,    41,   271,    -1,    -1,    -1,     0,   356,
//yy   357,    -1,    -1,    -1,   281,   282,    -1,    -1,    10,    -1,
//yy    58,    59,    -1,   290,   291,    -1,   293,   294,   295,   296,
//yy   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,
//yy    -1,    -1,    44,   320,   321,   322,   323,   324,   325,   326,
//yy   327,   328,   329,    -1,   331,   332,    58,    59,    -1,    -1,
//yy    -1,    63,    -1,   340,    -1,    -1,    -1,    -1,    -1,   346,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,     0,   356,
//yy   357,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    10,    -1,
//yy    -1,    93,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,
//yy    -1,    -1,    44,   125,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,     0,    -1,    -1,    -1,    58,    59,    -1,    -1,
//yy    -1,    63,    10,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    93,    -1,    41,    -1,    -1,    44,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,     0,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,    10,   271,    -1,    93,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   281,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   290,   291,    -1,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    41,    -1,    -1,    44,   125,    -1,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,   271,
//yy    58,    59,    -1,    -1,    -1,    63,    -1,    -1,    -1,   281,
//yy   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    93,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   320,   321,
//yy   322,   323,   324,   325,   326,   327,   328,   329,    -1,   331,
//yy   332,    -1,    -1,    -1,    -1,    -1,    -1,   125,   340,    -1,
//yy   262,   263,   264,    -1,   346,   267,   268,   269,     0,   271,
//yy    -1,    -1,    -1,    -1,   356,   357,    -1,    -1,    10,   281,
//yy   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,
//yy    -1,    -1,    44,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,     0,   271,   326,   327,    58,    59,    -1,   331,
//yy   332,    63,    10,   281,   282,    -1,    -1,    -1,   340,    -1,
//yy    -1,    -1,   290,   291,   346,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    93,    -1,    41,    -1,    -1,    44,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,     0,    -1,   326,   327,
//yy    58,    59,    -1,   331,   332,    63,    10,    -1,    -1,    -1,
//yy    -1,    -1,   340,   125,   262,   263,   264,    -1,   346,   267,
//yy   268,   269,    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   281,   282,    93,    -1,    41,    -1,    -1,
//yy    44,    -1,   290,   291,    -1,   293,   294,   295,   296,   297,
//yy     0,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
//yy    10,    -1,    -1,    -1,    -1,    -1,    -1,   125,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   326,   327,
//yy    -1,    -1,    -1,   331,   332,    -1,    -1,    -1,    -1,    93,
//yy    -1,    41,   340,    -1,    44,    -1,    -1,    -1,   346,    -1,
//yy    -1,    -1,    -1,    -1,     0,    -1,    -1,    -1,    58,    59,
//yy    -1,    -1,    -1,    63,    10,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   125,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,    44,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,     0,   271,
//yy    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    10,   281,
//yy   282,    -1,    -1,    -1,    -1,   125,    -1,    -1,   290,   291,
//yy    -1,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,
//yy    -1,    -1,    44,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,     0,   271,   326,   327,    58,    59,    -1,   331,
//yy   332,    63,    10,   281,   282,    -1,    -1,    -1,   340,   125,
//yy    -1,    -1,   290,   291,   346,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    93,    -1,    41,    -1,    -1,    44,    -1,   262,   263,
//yy   264,    -1,    -1,   267,   268,   269,     0,   271,   326,   327,
//yy    58,    59,    -1,   331,   332,    63,    10,   281,   282,    -1,
//yy    -1,    -1,   340,   125,    -1,    -1,   290,   291,   346,   293,
//yy   294,   295,   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,
//yy    44,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy     0,   271,   326,   327,    58,    59,    -1,   331,   332,    -1,
//yy    10,   281,   282,    -1,    -1,    -1,   340,   125,    -1,    -1,
//yy   290,   291,   346,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,
//yy    -1,    41,    -1,    -1,    44,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,     0,   271,   326,   327,    58,    59,
//yy    -1,   331,   332,    -1,    10,   281,   282,    -1,    -1,    -1,
//yy   340,   125,    -1,    -1,   290,   291,   346,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,    44,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,     0,   271,
//yy    -1,   327,    58,    59,    -1,   331,   332,    -1,    10,   281,
//yy   282,    -1,    -1,    -1,   340,   125,    -1,    -1,   290,   291,
//yy   346,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,
//yy    -1,    -1,    44,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,     0,   271,    -1,    -1,    58,    59,    -1,    -1,
//yy    -1,    -1,    10,   281,   282,    -1,    -1,    -1,   340,   125,
//yy    -1,    -1,   290,   291,   346,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    93,    -1,    41,    -1,    -1,    44,    -1,   262,   263,
//yy   264,    -1,    -1,   267,   268,   269,     0,   271,    -1,    -1,
//yy    58,    59,    -1,    -1,    -1,    -1,    10,   281,   282,    -1,
//yy    -1,    -1,   340,   125,    -1,    -1,   290,   291,   346,   293,
//yy   294,   295,   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,
//yy    44,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy     0,   271,    -1,    -1,    58,    59,    -1,    -1,    -1,    -1,
//yy    10,   281,   282,    -1,    -1,    -1,   340,   125,    -1,    -1,
//yy   290,   291,   346,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,
//yy    -1,    41,    -1,    -1,    44,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,     0,   271,    -1,    -1,    58,    59,
//yy    -1,    -1,    -1,    -1,    10,   281,   282,    -1,    -1,    -1,
//yy   340,   125,    -1,    -1,   290,   291,   346,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,    44,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,     0,   271,
//yy    -1,    -1,    58,    59,    -1,    -1,    -1,    -1,    10,   281,
//yy   282,    -1,    -1,    -1,   340,   125,    -1,    -1,   290,   291,
//yy   346,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,
//yy    -1,    -1,    44,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,     0,   271,    -1,    -1,    58,    59,    -1,    -1,
//yy    -1,    -1,    10,   281,   282,    -1,    -1,    -1,   340,   125,
//yy    -1,    -1,   290,   291,   346,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    93,    -1,    41,    -1,    -1,    44,    -1,   262,   263,
//yy   264,    -1,    -1,   267,   268,   269,     0,   271,    -1,    -1,
//yy    58,    59,    -1,    -1,    -1,    -1,    10,   281,   282,    -1,
//yy    -1,    -1,   340,   125,    -1,    -1,   290,   291,   346,   293,
//yy   294,   295,   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,
//yy    44,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy     0,   271,    -1,    -1,    58,    59,    -1,    -1,    -1,    -1,
//yy    10,   281,   282,    -1,    -1,    -1,   340,   125,    -1,    -1,
//yy   290,   291,   346,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,
//yy    -1,    41,    -1,    -1,    44,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,     0,   271,    -1,    -1,    58,    59,
//yy    -1,    -1,    -1,    -1,    10,   281,   282,    -1,    -1,    -1,
//yy   340,   125,    -1,    -1,   290,   291,   346,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,    44,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,     0,   271,
//yy    -1,    -1,    58,    59,    -1,    -1,    -1,    -1,    10,   281,
//yy   282,    -1,    -1,    -1,   340,   125,    -1,    -1,   290,   291,
//yy   346,   293,   294,   295,   296,   297,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,
//yy    -1,    -1,    44,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,     0,   271,    -1,    -1,    58,    59,    -1,    -1,
//yy    -1,    -1,    10,   281,   282,    -1,    -1,    -1,   340,   125,
//yy    -1,    -1,   290,   291,   346,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    93,    -1,    41,    -1,    -1,    44,    -1,   262,   263,
//yy   264,    -1,    -1,   267,   268,   269,     0,   271,    -1,    -1,
//yy    58,    59,    -1,    -1,    -1,    -1,    10,   281,   282,    -1,
//yy    -1,    -1,   340,   125,    -1,    -1,   290,   291,   346,   293,
//yy   294,   295,   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    93,    -1,    41,    -1,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    -1,    -1,    58,    59,    -1,    -1,    -1,    -1,
//yy    -1,   281,   282,    -1,    -1,    -1,   340,   125,    -1,    -1,
//yy   290,   291,   346,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    41,    -1,    -1,    -1,    -1,    -1,    -1,    93,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,
//yy   340,   125,    -1,    -1,   290,   291,   346,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,    -1,   271,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   281,
//yy   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   290,   291,
//yy   346,   293,   294,   295,   296,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,    -1,   271,    59,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,   340,    -1,
//yy    -1,    -1,   290,   291,   346,   293,   294,   295,   296,   297,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   262,   263,
//yy   264,    -1,    -1,   267,   268,   269,    -1,   271,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   281,   282,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   290,   291,   346,   293,
//yy   294,   295,   296,   297,    -1,    -1,    -1,    -1,   257,   258,
//yy   259,    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,
//yy     0,   270,    -1,   272,   273,   274,   275,   276,   277,   278,
//yy    10,    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,
//yy   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   299,    -1,   346,   302,   303,   304,   305,   306,   307,   308,
//yy   309,    41,   311,   312,   313,   314,    -1,   316,   317,   318,
//yy     0,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
//yy    10,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,
//yy    -1,    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,
//yy   349,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   359,    41,    -1,    93,   363,   364,   365,   366,   367,   368,
//yy    -1,   256,   257,   258,   259,   260,   261,    -1,    58,    59,
//yy   265,   266,    -1,    -1,     0,   270,    -1,   272,   273,   274,
//yy   275,   276,   277,   278,    10,   125,    -1,    -1,   283,   284,
//yy   285,   286,   287,   288,   289,    -1,    -1,   292,    -1,    -1,
//yy    -1,    -1,    -1,   298,   299,   300,   301,   302,   303,   304,
//yy   305,   306,   307,   308,   309,    41,   311,   312,   313,   314,
//yy     0,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    10,    -1,    58,    59,    -1,   125,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,
//yy   345,    -1,   347,    -1,    -1,    -1,   351,    -1,    -1,    -1,
//yy    -1,    41,    -1,    -1,   359,    -1,    -1,    93,   363,   364,
//yy   365,   366,   367,   368,    -1,    -1,    -1,    -1,    58,    59,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    93,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   281,   282,    -1,    -1,   125,    -1,    -1,    -1,    -1,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   281,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   293,   294,   295,   296,   297,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,    -1,   271,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   281,   282,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   290,   291,    -1,   293,   294,   295,
//yy   296,   297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   262,   263,   264,    -1,    -1,   267,   268,   269,
//yy    -1,   271,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   281,   282,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   290,   291,    -1,   293,   294,   295,   296,   297,   256,   257,
//yy   258,   259,   260,   261,    -1,    -1,    -1,   265,   266,    -1,
//yy    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    -1,    -1,   292,    -1,    -1,    -1,    -1,    -1,
//yy   298,   299,   300,   301,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,   347,
//yy    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,   256,   257,   258,   259,   260,   261,    -1,    -1,    -1,
//yy   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,
//yy   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,
//yy   285,   286,   287,   288,   289,    -1,    -1,   292,    -1,    -1,
//yy    -1,    -1,    -1,   298,   299,   300,   301,   302,   303,   304,
//yy   305,   306,   307,   308,   309,    -1,   311,   312,   313,   314,
//yy    -1,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,
//yy   345,    -1,   347,    -1,    -1,    -1,   351,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,   364,
//yy   365,   366,   367,   368,   256,   257,   258,   259,   260,   261,
//yy    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,   270,    -1,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,    -1,    -1,
//yy   292,    -1,    -1,    -1,    -1,    -1,   298,   299,   300,   301,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,
//yy    -1,   343,   344,   345,    -1,   347,    -1,    -1,    -1,   351,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   256,   257,   258,
//yy   259,    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,
//yy    -1,   270,    -1,   272,   273,   274,   275,   276,   277,   278,
//yy    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,
//yy   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   299,    -1,    -1,   302,   303,   304,   305,   306,   307,   308,
//yy   309,    -1,   311,   312,   313,   314,    -1,   316,   317,   318,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,
//yy    -1,    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,
//yy   349,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   359,    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,
//yy   256,   257,   258,   259,    -1,   261,    -1,    -1,    -1,   265,
//yy   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,
//yy   286,   287,   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    -1,
//yy   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,
//yy    -1,   347,    -1,   349,    -1,   351,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,   364,   365,
//yy   366,   367,   368,   256,   257,   258,   259,    -1,   261,    -1,
//yy    -1,    -1,   265,   266,    -1,    -1,    -1,   270,    -1,   272,
//yy   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,
//yy   283,   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,   302,
//yy   303,   304,   305,   306,   307,   308,   309,    -1,   311,   312,
//yy   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,
//yy   343,   344,   345,    -1,   347,    -1,   349,    -1,   351,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,
//yy   363,   364,   365,   366,   367,   368,   256,   257,   258,   259,
//yy    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,
//yy    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,   349,
//yy    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,
//yy    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,   256,
//yy   257,   258,   259,    -1,   261,    -1,    -1,    -1,   265,   266,
//yy    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,
//yy   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,
//yy   287,   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,
//yy   307,   308,   309,    -1,   311,   312,   313,   314,    -1,   316,
//yy   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,
//yy   347,    -1,   349,    -1,   351,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,
//yy   367,   368,   256,   257,   258,   259,    -1,   261,    -1,    -1,
//yy    -1,   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,
//yy   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,
//yy   344,   345,    -1,    -1,    -1,    -1,    -1,   351,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,
//yy   364,   365,   366,   367,   368,   257,   258,   259,    -1,   261,
//yy    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,   270,    -1,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,    -1,    -1,
//yy   292,    -1,    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,
//yy    -1,   343,   344,   345,    -1,   347,    -1,   349,    -1,   351,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   257,   258,   259,
//yy    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,
//yy    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,   349,
//yy    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,
//yy    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,   257,
//yy   258,   259,    -1,   261,    -1,    -1,    -1,   265,   266,    -1,
//yy    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,   347,
//yy    -1,   349,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,   257,   258,   259,    -1,   261,    -1,    -1,    -1,   265,
//yy   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,
//yy   286,   287,   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    -1,
//yy   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,
//yy    -1,   347,    -1,   349,    -1,   351,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,   364,   365,
//yy   366,   367,   368,   257,   258,   259,    -1,   261,    -1,    -1,
//yy    -1,   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,
//yy   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,
//yy   344,   345,    -1,   347,    -1,   349,    -1,   351,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,
//yy   364,   365,   366,   367,   368,   257,   258,   259,    -1,   261,
//yy    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,   270,    -1,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,
//yy    -1,   343,   344,   345,    -1,   347,    -1,   349,    -1,   351,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   257,   258,   259,
//yy    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,
//yy    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,   349,
//yy    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,
//yy    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,   257,
//yy   258,   259,    -1,   261,    -1,    -1,    -1,   265,   266,    -1,
//yy    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,   347,
//yy    -1,   349,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,   257,   258,   259,    -1,   261,    -1,    -1,    -1,   265,
//yy   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,
//yy   286,   287,   288,   289,    -1,    -1,   292,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    -1,
//yy   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,
//yy    -1,    -1,    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,   364,   365,
//yy   366,   367,   368,   257,   258,   259,    -1,   261,    -1,    -1,
//yy    -1,   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,
//yy   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,
//yy   344,   345,    -1,   347,    -1,    -1,    -1,   351,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,
//yy   364,   365,   366,   367,   368,   257,   258,   259,    -1,   261,
//yy    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,   270,    -1,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,
//yy    -1,   343,   344,   345,    -1,   347,    -1,    -1,    -1,   351,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   257,   258,   259,
//yy    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,
//yy    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,    -1,
//yy    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,
//yy    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,   257,
//yy   258,   259,    -1,   261,    -1,    -1,    -1,   265,   266,    -1,
//yy    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,   347,
//yy    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,   257,   258,   259,    -1,   261,    -1,    -1,    -1,   265,
//yy   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,
//yy   286,   287,   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    -1,
//yy   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,
//yy    -1,   347,    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,   364,   365,
//yy   366,   367,   368,   257,   258,   259,    -1,   261,    -1,    -1,
//yy    -1,   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,
//yy   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,
//yy   344,   345,    -1,   347,    -1,    -1,    -1,   351,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,
//yy   364,   365,   366,   367,   368,   257,   258,   259,    -1,   261,
//yy    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,   270,    -1,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,   316,   317,   318,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,
//yy    -1,   343,   344,   345,    -1,   347,    -1,    -1,    -1,   351,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   257,   258,   259,
//yy    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,   316,   317,   318,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,
//yy    -1,   341,   342,   343,   344,   345,    -1,    -1,    -1,    -1,
//yy    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,
//yy    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,   257,
//yy   258,   259,    -1,   261,    -1,    -1,    -1,   265,   266,    -1,
//yy    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,   316,   317,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,    -1,
//yy    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   359,    -1,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,   257,   258,   259,    -1,   261,    -1,    -1,    -1,   265,
//yy   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,
//yy   286,   287,   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    -1,
//yy   316,   317,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,
//yy    -1,    -1,    -1,    -1,    -1,   351,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,   364,   365,
//yy   366,   367,   368,   257,   258,   259,    -1,   261,    -1,    -1,
//yy    -1,   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,
//yy   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,   316,   317,   318,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,
//yy   344,   345,    -1,    -1,    -1,    -1,    -1,   351,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   359,    -1,    -1,    -1,   363,
//yy   364,   365,   366,   367,   368,   257,   258,   259,    -1,   261,
//yy    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,   270,    -1,
//yy   272,   273,   274,   275,   276,   277,   278,    -1,    -1,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,   311,
//yy   312,   313,   314,    -1,    -1,    -1,   318,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,
//yy    -1,   343,   344,   345,    -1,   347,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   363,   364,   365,   366,   367,   368,   257,   258,   259,
//yy    -1,   261,    -1,    -1,    -1,   265,   266,    -1,    -1,    -1,
//yy   270,    -1,   272,   273,   274,   275,   276,   277,   278,    -1,
//yy    -1,    -1,    -1,   283,   284,   285,   286,   287,   288,   289,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   299,
//yy    -1,    -1,   302,   303,   304,   305,   306,   307,   308,   309,
//yy    -1,   311,   312,   313,   314,    -1,    -1,    -1,   318,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   338,    -1,
//yy    -1,   341,    -1,   343,   344,   345,    -1,   347,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   363,   364,   365,   366,   367,   368,   257,
//yy   258,   259,    -1,   261,    -1,    -1,    -1,   265,   266,    -1,
//yy    -1,    -1,   270,    -1,   272,   273,   274,   275,   276,   277,
//yy   278,    -1,    -1,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   299,    -1,    -1,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,   311,   312,   313,   314,    -1,    -1,    -1,
//yy   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   338,    -1,    -1,   341,    -1,   343,   344,   345,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   363,   364,   365,   366,   367,
//yy   368,   257,   258,   259,    -1,   261,    -1,    -1,    -1,   265,
//yy   266,    -1,    -1,    -1,   270,    -1,   272,   273,   274,   275,
//yy   276,   277,   278,    -1,    -1,    -1,    -1,   283,   284,   285,
//yy   286,   287,   288,   289,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,   299,    -1,    -1,   302,   303,   304,   305,
//yy   306,   307,   308,   309,    -1,   311,   312,   313,   314,    -1,
//yy    -1,    -1,   318,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   338,    -1,    -1,   341,    -1,   343,   344,   345,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,   363,   364,   365,
//yy   366,   367,   368,   257,   258,   259,    -1,   261,    -1,    -1,
//yy    -1,   265,   266,    -1,    -1,    -1,   270,    -1,   272,   273,
//yy   274,   275,   276,   277,   278,    -1,    -1,    -1,    -1,   283,
//yy   284,   285,   286,   287,   288,   289,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   299,    -1,    -1,   302,   303,
//yy   304,   305,   306,   307,   308,   309,    -1,   311,   312,   313,
//yy   314,    -1,    -1,    -1,   318,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   338,    -1,    -1,   341,    -1,   343,
//yy   344,   345,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   363,
//yy   364,   365,   366,   367,   368,   257,   258,   259,   260,   261,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,   270,   271,
//yy    -1,    -1,   274,   275,   276,   277,   278,   279,   280,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,   290,   291,
//yy   292,   293,   294,   295,   296,   297,   298,   299,   300,   301,
//yy   302,   303,   304,   305,   306,    -1,   308,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   316,   317,    -1,   319,   320,   321,
//yy   322,    -1,   324,   325,    -1,    -1,   328,    -1,    -1,    -1,
//yy    -1,   333,   334,   335,   336,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   347,   348,    -1,   350,   351,
//yy   352,   353,   354,   355,   356,   357,   358,    -1,   360,    -1,
//yy   362,   363,   257,   258,   259,   260,   261,   262,   263,   264,
//yy    -1,    -1,   267,   268,   269,   270,   271,    -1,    -1,   274,
//yy   275,   276,   277,   278,   279,   280,    -1,    -1,   283,   284,
//yy   285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
//yy   295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
//yy   305,    -1,    -1,   308,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   316,   317,    -1,   319,   320,   321,   322,    -1,   324,
//yy   325,    -1,    -1,   328,    -1,    -1,    -1,    -1,   333,   334,
//yy   335,   336,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,   347,   348,    -1,   350,   351,   352,   353,   354,
//yy   355,   356,   357,   358,    -1,   360,    -1,   362,   363,   257,
//yy   258,   259,   260,   261,   262,   263,   264,    -1,    -1,   267,
//yy   268,   269,   270,   271,    -1,    -1,   274,   275,   276,   277,
//yy   278,   279,   280,    -1,    -1,   283,   284,   285,   286,   287,
//yy   288,   289,   290,   291,   292,   293,   294,   295,   296,   297,
//yy   298,   299,   300,   301,   302,   303,   304,   305,   306,   307,
//yy   308,   309,    -1,    -1,    -1,    -1,    -1,    -1,   316,   317,
//yy    -1,   319,   320,   321,   322,    -1,   324,   325,    -1,    -1,
//yy   328,    -1,    -1,    -1,    -1,   333,   334,   335,   336,    -1,
//yy    -1,    -1,    -1,    -1,   342,    -1,    -1,    -1,    -1,   347,
//yy   348,    -1,   350,   351,   352,   353,   354,   355,   356,   357,
//yy   358,    -1,   360,    -1,   362,   257,   258,   259,   260,   261,
//yy   262,   263,   264,    -1,    -1,   267,   268,   269,   270,   271,
//yy    -1,    -1,   274,   275,   276,   277,   278,   279,   280,    -1,
//yy    -1,   283,   284,   285,   286,   287,   288,   289,   290,   291,
//yy   292,   293,   294,   295,   296,   297,   298,   299,   300,   301,
//yy   302,   303,   304,   305,   306,   307,   308,   309,    -1,    -1,
//yy    -1,    -1,    -1,    -1,   316,   317,    -1,   319,   320,   321,
//yy   322,    -1,   324,   325,    -1,    -1,   328,    -1,    -1,    -1,
//yy    -1,   333,   334,   335,   336,    -1,    -1,    -1,    -1,    -1,
//yy    -1,    -1,    -1,    -1,    -1,   347,   348,    -1,   350,   351,
//yy   352,   353,   354,   355,   356,   357,   358,    -1,   360,    -1,
//yy   362,   257,   258,   259,   260,   261,   262,   263,   264,    -1,
//yy    -1,   267,   268,   269,   270,   271,    -1,    -1,   274,   275,
//yy   276,   277,   278,   279,   280,    -1,    -1,   283,   284,   285,
//yy   286,   287,   288,   289,   290,   291,   292,   293,   294,   295,
//yy   296,   297,   298,   299,   300,   301,   302,   303,   304,   305,
//yy    -1,    -1,   308,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy   316,   317,    -1,   319,   320,   321,   322,    -1,   324,   325,
//yy    -1,    -1,   328,    -1,    -1,    -1,    -1,   333,   334,   335,
//yy   336,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
//yy    -1,   347,   348,    -1,   350,   351,   352,   353,   354,   355,
//yy   356,   357,   358,    -1,   360,    -1,   362,

  /** maps symbol value to printable name.
      @see #yyExpecting
    */
  protected static String[] yyNames;
//yyNames 374 129
//yy 0 end-of-file
//yy 257 kCLASS
//yy 258 kMODULE
//yy 259 kDEF
//yy 260 kUNDEF
//yy 261 kBEGIN
//yy 262 kRESCUE
//yy 263 kENSURE
//yy 264 kEND
//yy 265 kIF
//yy 266 kUNLESS
//yy 267 kTHEN
//yy 268 kELSIF
//yy 269 kELSE
//yy 270 kCASE
//yy 271 kWHEN
//yy 272 kWHILE
//yy 273 kUNTIL
//yy 274 kFOR
//yy 275 kBREAK
//yy 276 kNEXT
//yy 277 kREDO
//yy 278 kRETRY
//yy 279 kIN
//yy 280 kDO
//yy 281 kDO_COND
//yy 282 kDO_BLOCK
//yy 283 kRETURN
//yy 284 kYIELD
//yy 285 kSUPER
//yy 286 kSELF
//yy 287 kNIL
//yy 288 kTRUE
//yy 289 kFALSE
//yy 290 kAND
//yy 291 kOR
//yy 292 kNOT
//yy 293 kIF_MOD
//yy 294 kUNLESS_MOD
//yy 295 kWHILE_MOD
//yy 296 kUNTIL_MOD
//yy 297 kRESCUE_MOD
//yy 298 kALIAS
//yy 299 kDEFINED
//yy 300 klBEGIN
//yy 301 klEND
//yy 302 k__LINE__
//yy 303 k__FILE__
//yy 304 tIDENTIFIER
//yy 305 tFID
//yy 306 tGVAR
//yy 307 tIVAR
//yy 308 tCONSTANT
//yy 309 tCVAR
//yy 310 tSTRING_CONTENT
//yy 311 tINTEGER
//yy 312 tFLOAT
//yy 313 tNTH_REF
//yy 314 tBACK_REF
//yy 315 tREGEXP_END
//yy 316 tUPLUS
//yy 317 tUMINUS
//yy 318 tUMINUS_NUM
//yy 319 tPOW
//yy 320 tCMP
//yy 321 tEQ
//yy 322 tEQQ
//yy 323 tNEQ
//yy 324 tGEQ
//yy 325 tLEQ
//yy 326 tANDOP
//yy 327 tOROP
//yy 328 tMATCH
//yy 329 tNMATCH
//yy 330 tDOT
//yy 331 tDOT2
//yy 332 tDOT3
//yy 333 tAREF
//yy 334 tASET
//yy 335 tLSHFT
//yy 336 tRSHFT
//yy 337 tCOLON2
//yy 338 tCOLON3
//yy 339 tOP_ASGN
//yy 340 tASSOC
//yy 341 tLPAREN
//yy 342 tLPAREN2
//yy 343 tLPAREN_ARG
//yy 344 tLBRACK
//yy 345 tLBRACE
//yy 346 tLBRACE_ARG
//yy 347 tSTAR
//yy 348 tSTAR2
//yy 349 tAMPER
//yy 350 tAMPER2
//yy 351 tTILDE
//yy 352 tPERCENT
//yy 353 tDIVIDE
//yy 354 tPLUS
//yy 355 tMINUS
//yy 356 tLT
//yy 357 tGT
//yy 358 tPIPE
//yy 359 tBANG
//yy 360 tCARET
//yy 361 tLCURLY
//yy 362 tBACK_REF2
//yy 363 tSYMBEG
//yy 364 tSTRING_BEG
//yy 365 tXSTRING_BEG
//yy 366 tREGEXP_BEG
//yy 367 tWORDS_BEG
//yy 368 tQWORDS_BEG
//yy 369 tSTRING_DBEG
//yy 370 tSTRING_DVAR
//yy 371 tSTRING_END
//yy 372 tLOWEST
//yy 61 '='
//yy 63 '?'
//yy 58 ':'
//yy 373 tLAST_TOKEN
//yy 125 '}'
//yy 91 '['
//yy 93 ']'
//yy 41 ')'
//yy 44 ','
//yy 32 ' '
//yy 10 '\n'
//yy 59 ';'

//t  /** printable rules for debugging.
//t    */
//t  protected static String[] yyRule;
//t
//yyRule 498
//yy $accept: program
//yy $$1:
//yy program: $$1 compstmt
//yy bodystmt: compstmt opt_rescue opt_else opt_ensure
//yy compstmt: stmts opt_terms
//yy stmts: none
//yy stmts: stmt
//yy stmts: stmts terms stmt
//yy stmts: error stmt
//yy $$2:
//yy stmt: kALIAS fitem $$2 fitem
//yy stmt: kALIAS tGVAR tGVAR
//yy stmt: kALIAS tGVAR tBACK_REF
//yy stmt: kALIAS tGVAR tNTH_REF
//yy stmt: kUNDEF undef_list
//yy stmt: stmt kIF_MOD expr_value
//yy stmt: stmt kUNLESS_MOD expr_value
//yy stmt: stmt kWHILE_MOD expr_value
//yy stmt: stmt kUNTIL_MOD expr_value
//yy stmt: stmt kRESCUE_MOD stmt
//yy $$3:
//yy stmt: klBEGIN $$3 tLCURLY compstmt '}'
//yy stmt: klEND tLCURLY compstmt '}'
//yy stmt: lhs '=' command_call
//yy stmt: mlhs '=' command_call
//yy stmt: var_lhs tOP_ASGN command_call
//yy stmt: primary_value '[' aref_args ']' tOP_ASGN command_call
//yy stmt: primary_value tDOT tIDENTIFIER tOP_ASGN command_call
//yy stmt: primary_value tDOT tCONSTANT tOP_ASGN command_call
//yy stmt: primary_value tCOLON2 tIDENTIFIER tOP_ASGN command_call
//yy stmt: backref tOP_ASGN command_call
//yy stmt: lhs '=' mrhs
//yy stmt: mlhs '=' arg_value
//yy stmt: mlhs '=' mrhs
//yy stmt: expr
//yy stmt: error
//yy expr: command_call
//yy expr: expr kAND expr
//yy expr: expr kOR expr
//yy expr: kNOT expr
//yy expr: tBANG command_call
//yy expr: arg
//yy expr_value: expr
//yy command_call: command
//yy command_call: block_command
//yy command_call: kRETURN call_args
//yy command_call: kBREAK call_args
//yy command_call: kNEXT call_args
//yy block_command: block_call
//yy block_command: block_call tDOT operation2 command_args
//yy block_command: block_call tCOLON2 operation2 command_args
//yy $$4:
//yy cmd_brace_block: tLBRACE_ARG $$4 opt_block_var compstmt '}'
//yy command: operation command_args
//yy command: operation command_args cmd_brace_block
//yy command: primary_value tDOT operation2 command_args
//yy command: primary_value tDOT operation2 command_args cmd_brace_block
//yy command: primary_value tCOLON2 operation2 command_args
//yy command: primary_value tCOLON2 operation2 command_args cmd_brace_block
//yy command: kSUPER command_args
//yy command: kYIELD command_args
//yy mlhs: mlhs_basic
//yy mlhs: tLPAREN mlhs_entry ')'
//yy mlhs_entry: mlhs_basic
//yy mlhs_entry: tLPAREN mlhs_entry ')'
//yy mlhs_basic: mlhs_head
//yy mlhs_basic: mlhs_head mlhs_item
//yy mlhs_basic: mlhs_head tSTAR mlhs_node
//yy mlhs_basic: mlhs_head tSTAR
//yy mlhs_basic: tSTAR mlhs_node
//yy mlhs_basic: tSTAR
//yy mlhs_item: mlhs_node
//yy mlhs_item: tLPAREN mlhs_entry ')'
//yy mlhs_head: mlhs_item ','
//yy mlhs_head: mlhs_head mlhs_item ','
//yy mlhs_node: variable
//yy mlhs_node: primary_value '[' aref_args ']'
//yy mlhs_node: primary_value tDOT tIDENTIFIER
//yy mlhs_node: primary_value tCOLON2 tIDENTIFIER
//yy mlhs_node: primary_value tDOT tCONSTANT
//yy mlhs_node: primary_value tCOLON2 tCONSTANT
//yy mlhs_node: tCOLON3 tCONSTANT
//yy mlhs_node: backref
//yy lhs: variable
//yy lhs: primary_value '[' aref_args ']'
//yy lhs: primary_value tDOT tIDENTIFIER
//yy lhs: primary_value tCOLON2 tIDENTIFIER
//yy lhs: primary_value tDOT tCONSTANT
//yy lhs: primary_value tCOLON2 tCONSTANT
//yy lhs: tCOLON3 tCONSTANT
//yy lhs: backref
//yy cname: tIDENTIFIER
//yy cname: tCONSTANT
//yy cpath: tCOLON3 cname
//yy cpath: cname
//yy cpath: primary_value tCOLON2 cname
//yy fname: tIDENTIFIER
//yy fname: tCONSTANT
//yy fname: tFID
//yy fname: op
//yy fname: reswords
//yy fitem: fname
//yy fitem: symbol
//yy undef_list: fitem
//yy $$5:
//yy undef_list: undef_list ',' $$5 fitem
//yy op: tPIPE
//yy op: tCARET
//yy op: tAMPER2
//yy op: tCMP
//yy op: tEQ
//yy op: tEQQ
//yy op: tMATCH
//yy op: tGT
//yy op: tGEQ
//yy op: tLT
//yy op: tLEQ
//yy op: tLSHFT
//yy op: tRSHFT
//yy op: tPLUS
//yy op: tMINUS
//yy op: tSTAR2
//yy op: tSTAR
//yy op: tDIVIDE
//yy op: tPERCENT
//yy op: tPOW
//yy op: tTILDE
//yy op: tUPLUS
//yy op: tUMINUS
//yy op: tAREF
//yy op: tASET
//yy op: tBACK_REF2
//yy reswords: k__LINE__
//yy reswords: k__FILE__
//yy reswords: klBEGIN
//yy reswords: klEND
//yy reswords: kALIAS
//yy reswords: kAND
//yy reswords: kBEGIN
//yy reswords: kBREAK
//yy reswords: kCASE
//yy reswords: kCLASS
//yy reswords: kDEF
//yy reswords: kDEFINED
//yy reswords: kDO
//yy reswords: kELSE
//yy reswords: kELSIF
//yy reswords: kEND
//yy reswords: kENSURE
//yy reswords: kFALSE
//yy reswords: kFOR
//yy reswords: kIN
//yy reswords: kMODULE
//yy reswords: kNEXT
//yy reswords: kNIL
//yy reswords: kNOT
//yy reswords: kOR
//yy reswords: kREDO
//yy reswords: kRESCUE
//yy reswords: kRETRY
//yy reswords: kRETURN
//yy reswords: kSELF
//yy reswords: kSUPER
//yy reswords: kTHEN
//yy reswords: kTRUE
//yy reswords: kUNDEF
//yy reswords: kWHEN
//yy reswords: kYIELD
//yy reswords: kIF_MOD
//yy reswords: kUNLESS_MOD
//yy reswords: kWHILE_MOD
//yy reswords: kUNTIL_MOD
//yy reswords: kRESCUE_MOD
//yy arg: lhs '=' arg
//yy arg: lhs '=' arg kRESCUE_MOD arg
//yy arg: var_lhs tOP_ASGN arg
//yy arg: primary_value '[' aref_args ']' tOP_ASGN arg
//yy arg: primary_value tDOT tIDENTIFIER tOP_ASGN arg
//yy arg: primary_value tDOT tCONSTANT tOP_ASGN arg
//yy arg: primary_value tCOLON2 tIDENTIFIER tOP_ASGN arg
//yy arg: primary_value tCOLON2 tCONSTANT tOP_ASGN arg
//yy arg: tCOLON3 tCONSTANT tOP_ASGN arg
//yy arg: backref tOP_ASGN arg
//yy arg: arg tDOT2 arg
//yy arg: arg tDOT3 arg
//yy arg: arg tPLUS arg
//yy arg: arg tMINUS arg
//yy arg: arg tSTAR2 arg
//yy arg: arg tDIVIDE arg
//yy arg: arg tPERCENT arg
//yy arg: arg tPOW arg
//yy arg: tUMINUS_NUM tINTEGER tPOW arg
//yy arg: tUMINUS_NUM tFLOAT tPOW arg
//yy arg: tUPLUS arg
//yy arg: tUMINUS arg
//yy arg: arg tPIPE arg
//yy arg: arg tCARET arg
//yy arg: arg tAMPER2 arg
//yy arg: arg tCMP arg
//yy arg: arg tGT arg
//yy arg: arg tGEQ arg
//yy arg: arg tLT arg
//yy arg: arg tLEQ arg
//yy arg: arg tEQ arg
//yy arg: arg tEQQ arg
//yy arg: arg tNEQ arg
//yy arg: arg tMATCH arg
//yy arg: arg tNMATCH arg
//yy arg: tBANG arg
//yy arg: tTILDE arg
//yy arg: arg tLSHFT arg
//yy arg: arg tRSHFT arg
//yy arg: arg tANDOP arg
//yy arg: arg tOROP arg
//yy $$6:
//yy arg: kDEFINED opt_nl $$6 arg
//yy arg: arg '?' arg ':' arg
//yy arg: primary
//yy arg_value: arg
//yy aref_args: none
//yy aref_args: command opt_nl
//yy aref_args: args trailer
//yy aref_args: args ',' tSTAR arg opt_nl
//yy aref_args: assocs trailer
//yy aref_args: tSTAR arg opt_nl
//yy paren_args: tLPAREN2 none_list ')'
//yy paren_args: tLPAREN2 call_args opt_nl ')'
//yy paren_args: tLPAREN2 block_call opt_nl ')'
//yy paren_args: tLPAREN2 args ',' block_call opt_nl ')'
//yy opt_paren_args: none
//yy opt_paren_args: paren_args
//yy call_args: command
//yy call_args: args opt_block_arg
//yy call_args: args ',' tSTAR arg_value opt_block_arg
//yy call_args: assocs opt_block_arg
//yy call_args: assocs ',' tSTAR arg_value opt_block_arg
//yy call_args: args ',' assocs opt_block_arg
//yy call_args: args ',' assocs ',' tSTAR arg opt_block_arg
//yy call_args: tSTAR arg_value opt_block_arg
//yy call_args: block_arg
//yy call_args2: arg_value ',' args opt_block_arg
//yy call_args2: arg_value ',' block_arg
//yy call_args2: arg_value ',' tSTAR arg_value opt_block_arg
//yy call_args2: arg_value ',' args ',' tSTAR arg_value opt_block_arg
//yy call_args2: assocs opt_block_arg
//yy call_args2: assocs ',' tSTAR arg_value opt_block_arg
//yy call_args2: arg_value ',' assocs opt_block_arg
//yy call_args2: arg_value ',' args ',' assocs opt_block_arg
//yy call_args2: arg_value ',' assocs ',' tSTAR arg_value opt_block_arg
//yy call_args2: arg_value ',' args ',' assocs ',' tSTAR arg_value opt_block_arg
//yy call_args2: tSTAR arg_value opt_block_arg
//yy call_args2: block_arg
//yy $$7:
//yy command_args: $$7 open_args
//yy open_args: call_args
//yy $$8:
//yy open_args: tLPAREN_ARG $$8 ')'
//yy $$9:
//yy open_args: tLPAREN_ARG call_args2 $$9 ')'
//yy block_arg: tAMPER arg_value
//yy opt_block_arg: ',' block_arg
//yy opt_block_arg: none_block_pass
//yy args: arg_value
//yy args: args ',' arg_value
//yy mrhs: args ',' arg_value
//yy mrhs: args ',' tSTAR arg_value
//yy mrhs: tSTAR arg_value
//yy primary: literal
//yy primary: strings
//yy primary: xstring
//yy primary: regexp
//yy primary: words
//yy primary: qwords
//yy primary: var_ref
//yy primary: backref
//yy primary: tFID
//yy primary: kBEGIN bodystmt kEND
//yy $$10:
//yy primary: tLPAREN_ARG expr $$10 opt_nl ')'
//yy primary: tLPAREN compstmt ')'
//yy primary: primary_value tCOLON2 tCONSTANT
//yy primary: tCOLON3 tCONSTANT
//yy primary: primary_value '[' aref_args ']'
//yy primary: tLBRACK aref_args ']'
//yy primary: tLBRACE assoc_list '}'
//yy primary: kRETURN
//yy primary: kYIELD tLPAREN2 call_args ')'
//yy primary: kYIELD tLPAREN2 ')'
//yy primary: kYIELD
//yy $$11:
//yy primary: kDEFINED opt_nl tLPAREN2 $$11 expr ')'
//yy primary: operation brace_block
//yy primary: method_call
//yy primary: method_call brace_block
//yy primary: kIF expr_value then compstmt if_tail kEND
//yy primary: kUNLESS expr_value then compstmt opt_else kEND
//yy $$12:
//yy $$13:
//yy primary: kWHILE $$12 expr_value do $$13 compstmt kEND
//yy $$14:
//yy $$15:
//yy primary: kUNTIL $$14 expr_value do $$15 compstmt kEND
//yy primary: kCASE expr_value opt_terms case_body kEND
//yy primary: kCASE opt_terms case_body kEND
//yy primary: kCASE opt_terms kELSE compstmt kEND
//yy $$16:
//yy $$17:
//yy primary: kFOR block_var kIN $$16 expr_value do $$17 compstmt kEND
//yy $$18:
//yy primary: kCLASS cpath superclass $$18 bodystmt kEND
//yy $$19:
//yy $$20:
//yy primary: kCLASS tLSHFT expr $$19 term $$20 bodystmt kEND
//yy $$21:
//yy primary: kMODULE cpath $$21 bodystmt kEND
//yy $$22:
//yy primary: kDEF fname $$22 f_arglist bodystmt kEND
//yy $$23:
//yy $$24:
//yy primary: kDEF singleton dot_or_colon $$23 fname $$24 f_arglist bodystmt kEND
//yy primary: kBREAK
//yy primary: kNEXT
//yy primary: kREDO
//yy primary: kRETRY
//yy primary_value: primary
//yy then: term
//yy then: ':'
//yy then: kTHEN
//yy then: term kTHEN
//yy do: term
//yy do: ':'
//yy do: kDO_COND
//yy if_tail: opt_else
//yy if_tail: kELSIF expr_value then compstmt if_tail
//yy opt_else: none
//yy opt_else: kELSE compstmt
//yy block_var: lhs
//yy block_var: mlhs
//yy opt_block_var: none
//yy opt_block_var: tPIPE tPIPE
//yy opt_block_var: tOROP
//yy opt_block_var: tPIPE block_var tPIPE
//yy $$25:
//yy do_block: kDO_BLOCK $$25 opt_block_var compstmt kEND
//yy block_call: command do_block
//yy block_call: block_call tDOT operation2 opt_paren_args
//yy block_call: block_call tCOLON2 operation2 opt_paren_args
//yy method_call: operation paren_args
//yy method_call: primary_value tDOT operation2 opt_paren_args
//yy method_call: primary_value tCOLON2 operation2 paren_args
//yy method_call: primary_value tCOLON2 operation3
//yy method_call: kSUPER paren_args
//yy method_call: kSUPER
//yy $$26:
//yy brace_block: tLCURLY $$26 opt_block_var compstmt '}'
//yy $$27:
//yy brace_block: kDO $$27 opt_block_var compstmt kEND
//yy case_body: kWHEN when_args then compstmt cases
//yy when_args: args
//yy when_args: args ',' tSTAR arg_value
//yy when_args: tSTAR arg_value
//yy cases: opt_else
//yy cases: case_body
//yy opt_rescue: kRESCUE exc_list exc_var then compstmt opt_rescue
//yy opt_rescue:
//yy exc_list: arg_value
//yy exc_list: mrhs
//yy exc_list: none
//yy exc_var: tASSOC lhs
//yy exc_var: none
//yy opt_ensure: kENSURE compstmt
//yy opt_ensure: none
//yy literal: numeric
//yy literal: symbol
//yy literal: dsym
//yy strings: string
//yy string: string1
//yy string: string string1
//yy string1: tSTRING_BEG string_contents tSTRING_END
//yy xstring: tXSTRING_BEG xstring_contents tSTRING_END
//yy regexp: tREGEXP_BEG xstring_contents tREGEXP_END
//yy words: tWORDS_BEG ' ' tSTRING_END
//yy words: tWORDS_BEG word_list tSTRING_END
//yy word_list:
//yy word_list: word_list word ' '
//yy word: string_content
//yy word: word string_content
//yy qwords: tQWORDS_BEG ' ' tSTRING_END
//yy qwords: tQWORDS_BEG qword_list tSTRING_END
//yy qword_list:
//yy qword_list: qword_list tSTRING_CONTENT ' '
//yy string_contents:
//yy string_contents: string_contents string_content
//yy xstring_contents:
//yy xstring_contents: xstring_contents string_content
//yy string_content: tSTRING_CONTENT
//yy $$28:
//yy string_content: tSTRING_DVAR $$28 string_dvar
//yy $$29:
//yy string_content: tSTRING_DBEG $$29 compstmt '}'
//yy string_dvar: tGVAR
//yy string_dvar: tIVAR
//yy string_dvar: tCVAR
//yy string_dvar: backref
//yy symbol: tSYMBEG sym
//yy sym: fname
//yy sym: tIVAR
//yy sym: tGVAR
//yy sym: tCVAR
//yy dsym: tSYMBEG xstring_contents tSTRING_END
//yy numeric: tINTEGER
//yy numeric: tFLOAT
//yy numeric: tUMINUS_NUM tINTEGER
//yy numeric: tUMINUS_NUM tFLOAT
//yy variable: tIDENTIFIER
//yy variable: tIVAR
//yy variable: tGVAR
//yy variable: tCONSTANT
//yy variable: tCVAR
//yy variable: kNIL
//yy variable: kSELF
//yy variable: kTRUE
//yy variable: kFALSE
//yy variable: k__FILE__
//yy variable: k__LINE__
//yy var_ref: variable
//yy var_lhs: variable
//yy backref: tNTH_REF
//yy backref: tBACK_REF
//yy superclass: term
//yy $$30:
//yy superclass: tLT $$30 expr_value term
//yy superclass: error term
//yy f_arglist: tLPAREN2 f_args opt_nl ')'
//yy f_arglist: f_args term
//yy f_args: f_arg ',' f_optarg ',' f_rest_arg opt_f_block_arg
//yy f_args: f_arg ',' f_optarg opt_f_block_arg
//yy f_args: f_arg ',' f_rest_arg opt_f_block_arg
//yy f_args: f_arg opt_f_block_arg
//yy f_args: f_optarg ',' f_rest_arg opt_f_block_arg
//yy f_args: f_optarg opt_f_block_arg
//yy f_args: f_rest_arg opt_f_block_arg
//yy f_args: f_block_arg
//yy f_args:
//yy f_norm_arg: tCONSTANT
//yy f_norm_arg: tIVAR
//yy f_norm_arg: tCVAR
//yy f_norm_arg: tIDENTIFIER
//yy f_arg: f_norm_arg
//yy f_arg: f_arg ',' f_norm_arg
//yy f_opt: tIDENTIFIER '=' arg_value
//yy f_optarg: f_opt
//yy f_optarg: f_optarg ',' f_opt
//yy restarg_mark: tSTAR2
//yy restarg_mark: tSTAR
//yy f_rest_arg: restarg_mark tIDENTIFIER
//yy f_rest_arg: restarg_mark
//yy blkarg_mark: tAMPER2
//yy blkarg_mark: tAMPER
//yy f_block_arg: blkarg_mark tIDENTIFIER
//yy opt_f_block_arg: ',' f_block_arg
//yy opt_f_block_arg:
//yy singleton: var_ref
//yy $$31:
//yy singleton: tLPAREN2 $$31 expr opt_nl ')'
//yy assoc_list: none_list
//yy assoc_list: assocs trailer
//yy assoc_list: args trailer
//yy assocs: assoc
//yy assocs: assocs ',' assoc
//yy assocs: assocs ',' error
//yy assoc: arg_value tASSOC arg_value
//yy operation: tIDENTIFIER
//yy operation: tCONSTANT
//yy operation: tFID
//yy operation2: tIDENTIFIER
//yy operation2: tCONSTANT
//yy operation2: tFID
//yy operation2: op
//yy operation3: tIDENTIFIER
//yy operation3: tFID
//yy operation3: op
//yy dot_or_colon: tDOT
//yy dot_or_colon: tCOLON2
//yy opt_terms:
//yy opt_terms: terms
//yy opt_nl:
//yy opt_nl: '\n'
//yy trailer:
//yy trailer: '\n'
//yy trailer: ','
//yy term: ';'
//yy term: '\n'
//yy terms: term
//yy terms: terms ';'
//yy none:
//yy none_list:
//yy none_block_pass:

  /** loads parser table.
      Format: name length newline ten-numbers newline ...
    */
  protected static short[] loadShort (java.io.BufferedReader in) throws Exception {
    java.util.StringTokenizer st = new java.util.StringTokenizer(in.readLine());
    st.nextToken();
    short[] result = new short[Integer.parseInt(st.nextToken())];
    for (int n = 0; n < result.length; n += 10) {
      st = new java.util.StringTokenizer(in.readLine(), ", \t\r\n");
      for (int m = 0; st.hasMoreTokens(); ++ m)
        result[n+m] = new Short(st.nextToken()).shortValue();
    }
    return result;
  }

  // once-only code to initialize parser tables
  // non-static to name resource after parser class
  // any error is fatal
  {yyLhs = null; if (yyLhs == null)
      try {
        Class myClass = getClass();
        String resource = myClass.getName().replace('.', '/')+".tables";
        java.io.InputStream i;
        try { // full path in jar file
          i = myClass.getResourceAsStream("/"+resource);
        } catch (Exception e) { // relative path
          i = myClass.getResourceAsStream(resource);
        }
        java.io.BufferedReader in =
          new java.io.BufferedReader(new java.io.InputStreamReader(i));
  
        yyLhs = loadShort(in);
        yyLen = loadShort(in);
        yyDefRed = loadShort(in);
        yyDgoto = loadShort(in);
        yySindex = loadShort(in);
        yyRindex = loadShort(in);
        yyGindex = loadShort(in);
        yyTable = loadShort(in);
        yyCheck = loadShort(in);
  
        // load name table
        // Format: yyNames length lines newline index space name newline ...for lines
        { java.util.StringTokenizer st = new java.util.StringTokenizer(in.readLine());
          st.nextToken();
          yyNames = new String[Integer.parseInt(st.nextToken())];
          int max = Integer.parseInt(st.nextToken());
          for (int n = 0; n < max; ++ n) {
            st = new java.util.StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            yyNames[m] = st.nextToken("\n").substring(1);
          }
        }
  
//t        // load rules table for debugging
//t        // Format: yyRule length newline rule newline ...for length
//t        { java.util.StringTokenizer st = new java.util.StringTokenizer(in.readLine());
//t          st.nextToken();
//t          yyRule = new String[Integer.parseInt(st.nextToken())];
//t          for (int n = 0; n < yyRule.length; ++ n)
//t            yyRule[n] = in.readLine();
//t        }
  
        in.close();
      } catch (Exception e) {
        throw new Error("cannot load parser tables ["+e+"]");
      }
  }

//t  /** debugging support, requires the package <tt>jay.yydebug</tt>.
//t      Set to <tt>null</tt> to suppress debugging messages.
//t    */
//t  protected jay.yydebug.yyDebug yydebug;
//t
//t  /** index-checked interface to {@link #yyNames}.
//t      @param token single character or <tt>%token</tt> value.
//t      @return token name or <tt>[illegal]</tt> or <tt>[unknown]</tt>.
//t    */
//t  public static final String yyName (int token) {
//t    if (token < 0 || token > yyNames.length) return "[illegal]";
//t    String name;
//t    if ((name = yyNames[token]) != null) return name;
//t    return "[unknown]";
//t  }
//t
  /** thrown for irrecoverable syntax errors and stack overflow.
      Nested for convenience, does not depend on parser class.
    */
  public static class yyException extends java.lang.Exception {
    public yyException (String message) {
      super(message);
    }
  }

  /** must be implemented by a scanner object to supply input to the parser.
      Nested for convenience, does not depend on parser class.
    */
  public interface yyInput {

    /** move on to next token.
        @return <tt>false</tt> if positioned beyond tokens.
        @throws IOException on input error.
      */
    boolean advance () throws java.io.IOException;

    /** classifies current token.
        Should not be called if {@link #advance()} returned <tt>false</tt>.
        @return current <tt>%token</tt> or single character.
      */
    int token ();

    /** associated with current token.
        Should not be called if {@link #advance()} returned <tt>false</tt>.
        @return value for {@link #token()}.
      */
    Object value ();
  }

  /** simplified error message.
      @see #yyerror(java.lang.String, java.lang.String[])
    */
  public void yyerror (String message) {
    yyerror(message, null, null);
  }

  /** (syntax) error message.
      Can be overwritten to control message format.
      @param message text to be displayed.
      @param expected list of acceptable tokens, if available.
    */
  public void yyerror (String message, String[] expected, String found) {
    if (expected != null && expected.length > 0) {
      System.err.print(message+", expecting");
      for (int n = 0; n < expected.length; ++ n)
        System.err.print(" "+expected[n]);
      System.err.println();
    } else
      System.err.println(message);
  }

  /** computes list of expected tokens on error by tracing the tables.
      @param state for which to compute the list.
      @return list of token names.
    */
  protected String[] yyExpecting (int state) {
    int token, n, len = 0;
    boolean[] ok = new boolean[yyNames.length];

    if ((n = yySindex[state]) != 0)
      for (token = n < 0 ? -n : 0;
           token < yyNames.length && n+token < yyTable.length; ++ token)
        if (yyCheck[n+token] == token && !ok[token] && yyNames[token] != null) {
          ++ len;
          ok[token] = true;
        }
    if ((n = yyRindex[state]) != 0)
      for (token = n < 0 ? -n : 0;
           token < yyNames.length && n+token < yyTable.length; ++ token)
        if (yyCheck[n+token] == token && !ok[token] && yyNames[token] != null) {
          ++ len;
          ok[token] = true;
        }

    String result[] = new String[len];
    for (n = token = 0; n < len;  ++ token)
      if (ok[token]) result[n++] = yyNames[token];
    return result;
  }

  /** the generated parser, with debugging messages.
      Maintains a dynamic state and value stack.
      @param yyLex scanner.
      @param yydebug debug message writer implementing <tt>yyDebug</tt>, or <tt>null</tt>.
      @return result of the last reduction, if any.
      @throws yyException on irrecoverable parse error.
    */
  public Object yyparse (RubyYaccLexer yyLex, Object yydebug)
				throws java.io.IOException, yyException {
//t    this.yydebug = (jay.yydebug.yyDebug)yydebug;
    return yyparse(yyLex);
  }

  /** initial size and increment of the state/value stack [default 256].
      This is not final so that it can be overwritten outside of invocations
      of {@link #yyparse}.
    */
  protected int yyMax;

  /** executed at the beginning of a reduce action.
      Used as <tt>$$ = yyDefault($1)</tt>, prior to the user-specified action, if any.
      Can be overwritten to provide deep copy, etc.
      @param first value for <tt>$1</tt>, or <tt>null</tt>.
      @return first.
    */
  protected Object yyDefault (Object first) {
    return first;
  }

  /** the generated parser.
      Maintains a dynamic state and value stack.
      @param yyLex scanner.
      @return result of the last reduction, if any.
      @throws yyException on irrecoverable parse error.
    */
  public Object yyparse (RubyYaccLexer yyLex) throws java.io.IOException, yyException {
    if (yyMax <= 0) yyMax = 256;			// initial size
    int yyState = 0, yyStates[] = new int[yyMax];	// state stack
    Object yyVal = null, yyVals[] = new Object[yyMax];	// value stack
    int yyToken = -1;					// current input
    int yyErrorFlag = 0;				// #tokens to shift

    yyLoop: for (int yyTop = 0;; ++ yyTop) {
      if (yyTop >= yyStates.length) {			// dynamically increase
        int[] i = new int[yyStates.length+yyMax];
        System.arraycopy(yyStates, 0, i, 0, yyStates.length);
        yyStates = i;
        Object[] o = new Object[yyVals.length+yyMax];
        System.arraycopy(yyVals, 0, o, 0, yyVals.length);
        yyVals = o;
      }
      yyStates[yyTop] = yyState;
      yyVals[yyTop] = yyVal;
//t      if (yydebug != null) yydebug.push(yyState, yyVal);

      yyDiscarded: for (;;) {	// discarding a token does not change stack
        int yyN;
        if ((yyN = yyDefRed[yyState]) == 0) {	// else [default] reduce (yyN)
          if (yyToken < 0) {
            yyToken = yyLex.advance() ? yyLex.token() : 0;
//t            if (yydebug != null)
//t              yydebug.lex(yyState, yyToken, yyName(yyToken), yyLex.value());
          }
          if ((yyN = yySindex[yyState]) != 0 && (yyN += yyToken) >= 0
              && yyN < yyTable.length && yyCheck[yyN] == yyToken) {
//t            if (yydebug != null)
//t              yydebug.shift(yyState, yyTable[yyN], yyErrorFlag > 0 ? yyErrorFlag-1 : 0);
            yyState = yyTable[yyN];		// shift to yyN
            yyVal = yyLex.value();
            yyToken = -1;
            if (yyErrorFlag > 0) -- yyErrorFlag;
            continue yyLoop;
          }
          if ((yyN = yyRindex[yyState]) != 0 && (yyN += yyToken) >= 0
              && yyN < yyTable.length && yyCheck[yyN] == yyToken)
            yyN = yyTable[yyN];			// reduce (yyN)
          else
            switch (yyErrorFlag) {
  
            case 0:
              yyerror("syntax error", yyExpecting(yyState), yyNames[yyToken]);
//t              if (yydebug != null) yydebug.error("syntax error");
  
            case 1: case 2:
              yyErrorFlag = 3;
              do {
                if ((yyN = yySindex[yyStates[yyTop]]) != 0
                    && (yyN += yyErrorCode) >= 0 && yyN < yyTable.length
                    && yyCheck[yyN] == yyErrorCode) {
//t                  if (yydebug != null)
//t                    yydebug.shift(yyStates[yyTop], yyTable[yyN], 3);
                  yyState = yyTable[yyN];
                  yyVal = yyLex.value();
                  continue yyLoop;
                }
//t                if (yydebug != null) yydebug.pop(yyStates[yyTop]);
              } while (-- yyTop >= 0);
//t              if (yydebug != null) yydebug.reject();
              throw new yyException("irrecoverable syntax error");
  
            case 3:
              if (yyToken == 0) {
//t                if (yydebug != null) yydebug.reject();
                throw new yyException("irrecoverable syntax error at end-of-file");
              }
//t              if (yydebug != null)
//t                yydebug.discard(yyState, yyToken, yyName(yyToken),
//t  							yyLex.value());
              yyToken = -1;
              continue yyDiscarded;		// leave stack alone
            }
        }
        int yyV = yyTop + 1-yyLen[yyN];
//t        if (yydebug != null)
//t          yydebug.reduce(yyState, yyStates[yyV-1], yyN, yyRule[yyN], yyLen[yyN]);
        yyVal = yyDefault(yyV > yyTop ? null : yyVals[yyV]);
        switch (yyN) {
case 1:
					// line 239 "DefaultRubyParser.y"
  {
                  lexer.setState(LexState.EXPR_BEG);
                  support.initTopLocalVariables();

              }
  break;
case 2:
					// line 243 "DefaultRubyParser.y"
  {
                  if (((Node)yyVals[0+yyTop]) != null) {
                      /* last expression should not be void */
                      if (((Node)yyVals[0+yyTop]) instanceof BlockNode) {
                          support.checkUselessStatement(((BlockNode)yyVals[0+yyTop]).getLast());
                      } else {
                          support.checkUselessStatement(((Node)yyVals[0+yyTop]));
                      }
                  }
                  support.getResult().setAST(support.appendToBlock(support.getResult().getAST(), ((Node)yyVals[0+yyTop])));
                  support.updateTopLocalVariables();
              }
  break;
case 3:
					// line 259 "DefaultRubyParser.y"
  {
                 Node node = ((Node)yyVals[-3+yyTop]);

		 if (((RescueBodyNode)yyVals[-2+yyTop]) != null) {
		   node = new RescueNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop]), true), ((Node)yyVals[-3+yyTop]), ((RescueBodyNode)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]));
		 } else if (((Node)yyVals[-1+yyTop]) != null) {
		       warnings.warn(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), "else without rescue is useless");
                       node = support.appendToBlock(((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]));
		 }
		 if (((Node)yyVals[0+yyTop]) != null) {
		    node = new EnsureNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), node, ((Node)yyVals[0+yyTop]));
		 }

		 yyVal = node;
             }
  break;
case 4:
					// line 275 "DefaultRubyParser.y"
  {
                  if (((Node)yyVals[-1+yyTop]) instanceof BlockNode) {
                     support.checkUselessStatements(((BlockNode)yyVals[-1+yyTop]));
		  }
                  yyVal = ((Node)yyVals[-1+yyTop]);
              }
  break;
case 6:
					// line 283 "DefaultRubyParser.y"
  {
                    yyVal = support.newline_node(((Node)yyVals[0+yyTop]), getPosition(((ISourcePositionHolder)yyVals[0+yyTop]), true));
                }
  break;
case 7:
					// line 286 "DefaultRubyParser.y"
  {
		    yyVal = support.appendToBlock(((Node)yyVals[-2+yyTop]), support.newline_node(((Node)yyVals[0+yyTop]), getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]), true)));
                }
  break;
case 8:
					// line 289 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[0+yyTop]);
                }
  break;
case 9:
					// line 293 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_FNAME);
                }
  break;
case 10:
					// line 295 "DefaultRubyParser.y"
  {
                    yyVal = new AliasNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 11:
					// line 298 "DefaultRubyParser.y"
  {
                    yyVal = new VAliasNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), (String) ((Token)yyVals[-1+yyTop]).getValue(), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 12:
					// line 301 "DefaultRubyParser.y"
  {
                    yyVal = new VAliasNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), (String) ((Token)yyVals[-1+yyTop]).getValue(), "$" + ((BackRefNode)yyVals[0+yyTop]).getType()); /* XXX*/
                }
  break;
case 13:
					// line 304 "DefaultRubyParser.y"
  {
                    yyerror("can't make alias for the number variables");
                    yyVal = null; /*XXX 0*/
                }
  break;
case 14:
					// line 308 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[0+yyTop]);
                }
  break;
case 15:
					// line 311 "DefaultRubyParser.y"
  {
                    yyVal = new IfNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), null);
                }
  break;
case 16:
					// line 314 "DefaultRubyParser.y"
  {
                    yyVal = new IfNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), null, ((Node)yyVals[-2+yyTop]));
                }
  break;
case 17:
					// line 317 "DefaultRubyParser.y"
  {
                    if (((Node)yyVals[-2+yyTop]) != null && ((Node)yyVals[-2+yyTop]) instanceof BeginNode) {
                        yyVal = new WhileNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((BeginNode)yyVals[-2+yyTop]).getBodyNode(), false);
                    } else {
                        yyVal = new WhileNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), true);
                    }
                }
  break;
case 18:
					// line 324 "DefaultRubyParser.y"
  {
                    if (((Node)yyVals[-2+yyTop]) != null && ((Node)yyVals[-2+yyTop]) instanceof BeginNode) {
                        yyVal = new UntilNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((BeginNode)yyVals[-2+yyTop]).getBodyNode());
                    } else {
                        yyVal = new UntilNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]));
                    }
                }
  break;
case 19:
					// line 332 "DefaultRubyParser.y"
  {
		  yyVal = new RescueNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[-2+yyTop]), new RescueBodyNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), null,((Node)yyVals[0+yyTop]), null), null);
                }
  break;
case 20:
					// line 336 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) {
                        yyerror("BEGIN in method");
                    }
                    support.getLocalNames().push(new LocalNamesElement());
                }
  break;
case 21:
					// line 341 "DefaultRubyParser.y"
  {
                    support.getResult().addBeginNode(new ScopeNode(getPosition(((Token)yyVals[-4+yyTop]), true), ((LocalNamesElement) support.getLocalNames().peek()).getNamesArray(), ((Node)yyVals[-1+yyTop])));
                    support.getLocalNames().pop();
                    yyVal = null; /*XXX 0;*/
                }
  break;
case 22:
					// line 346 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) {
                        yyerror("END in method; use at_exit");
                    }
                    support.getResult().addEndNode(new IterNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), null, new PostExeNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop]))), ((Node)yyVals[-1+yyTop])));
                    yyVal = null;
                }
  break;
case 23:
					// line 353 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = support.node_assign(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 24:
					// line 357 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
		    if (((MultipleAsgnNode)yyVals[-2+yyTop]).getHeadNode() != null) {
		        ((MultipleAsgnNode)yyVals[-2+yyTop]).setValueNode(new ToAryNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[0+yyTop])));
		    } else {
		        ((MultipleAsgnNode)yyVals[-2+yyTop]).setValueNode(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(((Node)yyVals[0+yyTop])));
		    }
		    yyVal = ((MultipleAsgnNode)yyVals[-2+yyTop]);
                }
  break;
case 25:
					// line 366 "DefaultRubyParser.y"
  {
 		    support.checkExpression(((Node)yyVals[0+yyTop]));
		    if (yyVals[-2+yyTop] != null) {
		        String name = ((INameNode)yyVals[-2+yyTop]).getName();
			String asgnOp = (String) ((Token)yyVals[-1+yyTop]).getValue();
		        if (asgnOp.equals("||")) {
	                    ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
	                    yyVal = new OpAsgnOrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.gettable(name, ((ISourcePositionHolder)yyVals[-2+yyTop]).getPosition()), ((Node)yyVals[-2+yyTop]));
			    /* XXX
			    if (is_asgn_or_id(vid)) {
				$$->nd_aid = vid;
			    }
			    */
			} else if (asgnOp.equals("&&")) {
	                    ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                            yyVal = new OpAsgnAndNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.gettable(name, ((ISourcePositionHolder)yyVals[-2+yyTop]).getPosition()), ((Node)yyVals[-2+yyTop]));
			} else {
			    yyVal = yyVals[-2+yyTop];
                            if (yyVal != null) {
                                ((AssignableNode)yyVal).setValueNode(support.getOperatorCallNode(support.gettable(name, ((ISourcePositionHolder)yyVals[-2+yyTop]).getPosition()), asgnOp, ((Node)yyVals[0+yyTop])));
                            }
			}
		    } else {
 		        yyVal = null;
		    }
		}
  break;
case 26:
					// line 392 "DefaultRubyParser.y"
  {
                    /* Much smaller than ruby block */
                    yyVal = new OpElementAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), ((Node)yyVals[-5+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));

                }
  break;
case 27:
					// line 397 "DefaultRubyParser.y"
  {
                    yyVal = new OpAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
                }
  break;
case 28:
					// line 400 "DefaultRubyParser.y"
  {
                    yyVal = new OpAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
                }
  break;
case 29:
					// line 403 "DefaultRubyParser.y"
  {
  yyVal = new OpAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
                }
  break;
case 30:
					// line 406 "DefaultRubyParser.y"
  {
                    support.backrefAssignError(((Node)yyVals[-2+yyTop]));
                    yyVal = null;
                }
  break;
case 31:
					// line 410 "DefaultRubyParser.y"
  {
                    yyVal = support.node_assign(((Node)yyVals[-2+yyTop]), new SValueNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[0+yyTop])));
                }
  break;
case 32:
					// line 413 "DefaultRubyParser.y"
  {
                    if (((MultipleAsgnNode)yyVals[-2+yyTop]).getHeadNode() != null) {
		        ((MultipleAsgnNode)yyVals[-2+yyTop]).setValueNode(new ToAryNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[0+yyTop])));
		    } else {
		        ((MultipleAsgnNode)yyVals[-2+yyTop]).setValueNode(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(((Node)yyVals[0+yyTop])));
		    }
		    yyVal = ((MultipleAsgnNode)yyVals[-2+yyTop]);
		}
  break;
case 33:
					// line 421 "DefaultRubyParser.y"
  {
                    ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
		    yyVal = ((MultipleAsgnNode)yyVals[-2+yyTop]);
		}
  break;
case 35:
					// line 426 "DefaultRubyParser.y"
  {
              	System.err.println("stmt error " + yyVals[0+yyTop].getClass().getName());
              	yyVal = new ErrorNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
              }
  break;
case 37:
					// line 432 "DefaultRubyParser.y"
  {
                    yyVal = support.newAndNode(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 38:
					// line 435 "DefaultRubyParser.y"
  {
                    yyVal = support.newOrNode(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 39:
					// line 438 "DefaultRubyParser.y"
  {
                    yyVal = new NotNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])));
                }
  break;
case 40:
					// line 441 "DefaultRubyParser.y"
  {
                    yyVal = new NotNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])));
                }
  break;
case 42:
					// line 446 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
		    yyVal = ((Node)yyVals[0+yyTop]); /*Do we really need this set? $1 is $$?*/
		}
  break;
case 45:
					// line 453 "DefaultRubyParser.y"
  {
                    yyVal = new ReturnNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), support.ret_args(((Node)yyVals[0+yyTop]), getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))));
                }
  break;
case 46:
					// line 456 "DefaultRubyParser.y"
  {
                    yyVal = new BreakNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), support.ret_args(((Node)yyVals[0+yyTop]), getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))));
                }
  break;
case 47:
					// line 459 "DefaultRubyParser.y"
  {
                    yyVal = new NextNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), support.ret_args(((Node)yyVals[0+yyTop]), getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))));
                }
  break;
case 49:
					// line 464 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop]));
                }
  break;
case 50:
					// line 467 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop]));
                }
  break;
case 51:
					// line 471 "DefaultRubyParser.y"
  {
                      support.getBlockNames().push(new BlockNamesElement());
		  }
  break;
case 52:
					// line 473 "DefaultRubyParser.y"
  {
                      yyVal = new IterNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), null);
                      support.getBlockNames().pop();
		  }
  break;
case 53:
					// line 478 "DefaultRubyParser.y"
  {
                    yyVal = support.new_fcall((String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop])); /* .setPosFrom($2);*/
                }
  break;
case 54:
					// line 481 "DefaultRubyParser.y"
  {
                    yyVal = support.new_fcall((String) ((Token)yyVals[-2+yyTop]).getValue(), ((Node)yyVals[-1+yyTop]), ((Token)yyVals[-2+yyTop])); 
	            if (((IterNode)yyVals[0+yyTop]) != null) {
                        if (yyVal instanceof BlockPassNode) {
                            throw new SyntaxException(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), "Both block arg and actual block given.");
                        }
                        ((IterNode)yyVals[0+yyTop]).setIterNode(((Node)yyVal));
                        yyVal = ((Node)yyVals[-1+yyTop]);
		   }
                }
  break;
case 55:
					// line 491 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop])); /*.setPosFrom($1);*/
                }
  break;
case 56:
					// line 494 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), ((Node)yyVals[-1+yyTop])); 
		    if (((IterNode)yyVals[0+yyTop]) != null) {
		        if (yyVal instanceof BlockPassNode) {
                            throw new SyntaxException(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), "Both block arg and actual block given.");
                        }
                        ((IterNode)yyVals[0+yyTop]).setIterNode(((Node)yyVal));
			yyVal = ((IterNode)yyVals[0+yyTop]);
		    }
		 }
  break;
case 57:
					// line 504 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop]));
                }
  break;
case 58:
					// line 507 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), ((Node)yyVals[-1+yyTop])); 
		    if (((IterNode)yyVals[0+yyTop]) != null) {
		        if (yyVal instanceof BlockPassNode) {
                            throw new SyntaxException(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), "Both block arg and actual block given.");
                        }
                        ((IterNode)yyVals[0+yyTop]).setIterNode(((Node)yyVal));
			yyVal = ((IterNode)yyVals[0+yyTop]);
		    }
	        }
  break;
case 59:
					// line 517 "DefaultRubyParser.y"
  {
		    yyVal = support.new_super(((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop])); /* .setPosFrom($2);*/
		}
  break;
case 60:
					// line 520 "DefaultRubyParser.y"
  {
                    yyVal = support.new_yield(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[0+yyTop]));
		}
  break;
case 62:
					// line 525 "DefaultRubyParser.y"
  {
                    yyVal = ((MultipleAsgnNode)yyVals[-1+yyTop]);
		}
  break;
case 64:
					// line 530 "DefaultRubyParser.y"
  {
	            yyVal = new MultipleAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(((MultipleAsgnNode)yyVals[-1+yyTop])), null);
                }
  break;
case 65:
					// line 534 "DefaultRubyParser.y"
  {
                    yyVal = new MultipleAsgnNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), ((ListNode)yyVals[0+yyTop]), null);
                }
  break;
case 66:
					// line 537 "DefaultRubyParser.y"
  {
                    yyVal = new MultipleAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((ListNode)yyVals[-1+yyTop]).add(((Node)yyVals[0+yyTop])), null);
                }
  break;
case 67:
					// line 540 "DefaultRubyParser.y"
  {
                    yyVal = new MultipleAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((ListNode)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 68:
					// line 543 "DefaultRubyParser.y"
  {
                    yyVal = new MultipleAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((ListNode)yyVals[-1+yyTop]), new StarNode(getPosition(null)));
                }
  break;
case 69:
					// line 546 "DefaultRubyParser.y"
  {
                    yyVal = new MultipleAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), null, ((Node)yyVals[0+yyTop]));
                }
  break;
case 70:
					// line 549 "DefaultRubyParser.y"
  {
                    yyVal = new MultipleAsgnNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), null, new StarNode(getPosition(null)));
                }
  break;
case 72:
					// line 554 "DefaultRubyParser.y"
  {
                    yyVal = ((MultipleAsgnNode)yyVals[-1+yyTop]);
                }
  break;
case 73:
					// line 558 "DefaultRubyParser.y"
  {
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))).add(((Node)yyVals[-1+yyTop]));
                }
  break;
case 74:
					// line 561 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[-1+yyTop]));
                }
  break;
case 75:
					// line 565 "DefaultRubyParser.y"
  {
                    yyVal = support.assignable(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), yyVals[0+yyTop], null);
                }
  break;
case 76:
					// line 568 "DefaultRubyParser.y"
  {
                    yyVal = support.getElementAssignmentNode(((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]));
                }
  break;
case 77:
					// line 571 "DefaultRubyParser.y"
  {
                    yyVal = support.getAttributeAssignmentNode(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 78:
					// line 574 "DefaultRubyParser.y"
  {
                    yyVal = support.getAttributeAssignmentNode(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 79:
					// line 577 "DefaultRubyParser.y"
  {
                    yyVal = support.getAttributeAssignmentNode(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 80:
					// line 580 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) {
			    yyerror("dynamic constant assignment");
		    }
			
		    yyVal = new ConstDeclNode(support.union(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue(), null);
		}
  break;
case 81:
					// line 587 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) {
			    yyerror("dynamic constant assignment");
		    }

		    /* ERROR:  VEry likely a big error. */
                    yyVal = new Colon3Node(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
		    /* ruby $$ = NEW_CDECL(0, 0, NEW_COLON3($2)); */
		    }
  break;
case 82:
					// line 597 "DefaultRubyParser.y"
  {
	            support.backrefAssignError(((Node)yyVals[0+yyTop]));
                    yyVal = null;
                }
  break;
case 83:
					// line 602 "DefaultRubyParser.y"
  {
                    yyVal = support.assignable(getPosition(((ISourcePositionHolder)yyVals[0+yyTop]), true), yyVals[0+yyTop], null);
                }
  break;
case 84:
					// line 605 "DefaultRubyParser.y"
  {
                    yyVal = support.getElementAssignmentNode(((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]));
                }
  break;
case 85:
					// line 608 "DefaultRubyParser.y"
  {
                    yyVal = support.getAttributeAssignmentNode(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 86:
					// line 611 "DefaultRubyParser.y"
  {
                    yyVal = support.getAttributeAssignmentNode(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
 	        }
  break;
case 87:
					// line 614 "DefaultRubyParser.y"
  {
                    yyVal = support.getAttributeAssignmentNode(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 88:
					// line 617 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) {
			    yyerror("dynamic constant assignment");
		    }
			
                    yyVal = new ConstDeclNode(support.union(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue(), null);
	        }
  break;
case 89:
					// line 624 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) {
			    yyerror("dynamic constant assignment");
		    }

		    /* ERROR:  VEry likely a big error. */
                    yyVal = new Colon3Node(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
		    /* ruby $$ = NEW_CDECL(0, 0, NEW_COLON3($2)); */
	        }
  break;
case 90:
					// line 633 "DefaultRubyParser.y"
  {
                    support.backrefAssignError(((Node)yyVals[0+yyTop]));
                    yyVal = null;
		}
  break;
case 91:
					// line 638 "DefaultRubyParser.y"
  {
                    yyerror("class/module name must be CONSTANT");
                }
  break;
case 93:
					// line 643 "DefaultRubyParser.y"
  {
                    yyVal = new Colon3Node(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
		}
  break;
case 94:
					// line 646 "DefaultRubyParser.y"
  {
                    /* $1 was $$ in ruby?*/
                    yyVal = new Colon2Node(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), null, (String) ((Token)yyVals[0+yyTop]).getValue());
 	        }
  break;
case 95:
					// line 650 "DefaultRubyParser.y"
  {
                    yyVal = new Colon2Node(support.union(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
		}
  break;
case 99:
					// line 657 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_END);
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 100:
					// line 661 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_END);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 103:
					// line 669 "DefaultRubyParser.y"
  {
                    yyVal = new UndefNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 104:
					// line 672 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_FNAME);
	        }
  break;
case 105:
					// line 674 "DefaultRubyParser.y"
  {
                    yyVal = support.appendToBlock(((Node)yyVals[-3+yyTop]), new UndefNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue()));
                }
  break;
case 106:
					// line 678 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("|"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 107:
					// line 679 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("^"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 108:
					// line 680 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("&"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 109:
					// line 681 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("<=>"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 110:
					// line 682 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("=="); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 111:
					// line 683 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("==="); yyVal = ((Token)yyVals[0+yyTop]);}
  break;
case 112:
					// line 684 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("=~"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 113:
					// line 685 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue(">"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 114:
					// line 686 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue(">="); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 115:
					// line 687 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("<"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 116:
					// line 688 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("<="); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 117:
					// line 689 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("<<"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 118:
					// line 690 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue(">>"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 119:
					// line 691 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("+"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 120:
					// line 692 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("-"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 121:
					// line 693 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("*"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 122:
					// line 694 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("*"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 123:
					// line 695 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("/"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 124:
					// line 696 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("%"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 125:
					// line 697 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("**"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 126:
					// line 698 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("~"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 127:
					// line 699 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("+@"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 128:
					// line 700 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("-@"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 129:
					// line 701 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("[]"); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 130:
					// line 702 "DefaultRubyParser.y"
  { ((Token)yyVals[0+yyTop]).setValue("[]="); yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 131:
					// line 703 "DefaultRubyParser.y"
  {  yyVal = ((Token)yyVals[0+yyTop]); }
  break;
case 173:
					// line 714 "DefaultRubyParser.y"
  {
                    yyVal = support.node_assign(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
		    /* FIXME: Consider fixing node_assign itself rather than single case*/
		    ((Node)yyVal).setPosition(support.union(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])));
                }
  break;
case 174:
					// line 719 "DefaultRubyParser.y"
  {
                    yyVal = support.node_assign(((Node)yyVals[-4+yyTop]), new RescueNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), new RescueBodyNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), null,((Node)yyVals[0+yyTop]), null), null));
		}
  break;
case 175:
					// line 722 "DefaultRubyParser.y"
  {
		    support.checkExpression(((Node)yyVals[0+yyTop]));
		    if (yyVals[-2+yyTop] != null) {
		        String name = ((INameNode)yyVals[-2+yyTop]).getName();
			String asgnOp = (String) ((Token)yyVals[-1+yyTop]).getValue();

		        if (asgnOp.equals("||")) {
	                    ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
	                    yyVal = new OpAsgnOrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.gettable(name, ((ISourcePositionHolder)yyVals[-2+yyTop]).getPosition()), ((Node)yyVals[-2+yyTop]));
			    /* FIXME
			    if (is_asgn_or_id(vid)) {
				$$->nd_aid = vid;
			    }
			    */
			} else if (asgnOp.equals("&&")) {
	                    ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                            yyVal = new OpAsgnAndNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.gettable(name, ((ISourcePositionHolder)yyVals[-2+yyTop]).getPosition()), ((Node)yyVals[-2+yyTop]));
			} else {
			    yyVal = yyVals[-2+yyTop];
                            if (yyVal != null) {
			      ((AssignableNode)yyVal).setValueNode(support.getOperatorCallNode(support.gettable(name, ((ISourcePositionHolder)yyVals[-2+yyTop]).getPosition()), asgnOp, ((Node)yyVals[0+yyTop])));
                            }
			}
		    } else {
 		        yyVal = null; /* XXX 0; */
		    }
                }
  break;
case 176:
					// line 749 "DefaultRubyParser.y"
  {
                    yyVal = new OpElementAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), ((Node)yyVals[-5+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 177:
					// line 752 "DefaultRubyParser.y"
  {
                    yyVal = new OpAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
                }
  break;
case 178:
					// line 755 "DefaultRubyParser.y"
  {
                    yyVal = new OpAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
                }
  break;
case 179:
					// line 758 "DefaultRubyParser.y"
  {
                    yyVal = new OpAsgnNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
                }
  break;
case 180:
					// line 761 "DefaultRubyParser.y"
  {
		    yyerror("constant re-assignment");
		    yyVal = null;
	        }
  break;
case 181:
					// line 765 "DefaultRubyParser.y"
  {
		    yyerror("constant re-assignment");
		    yyVal = null;
	        }
  break;
case 182:
					// line 769 "DefaultRubyParser.y"
  {
                    support.backrefAssignError(((Node)yyVals[-2+yyTop]));
                    yyVal = null;
                }
  break;
case 183:
					// line 773 "DefaultRubyParser.y"
  {
		    support.checkExpression(((Node)yyVals[-2+yyTop]));
		    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = new DotNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]), false);
                }
  break;
case 184:
					// line 778 "DefaultRubyParser.y"
  {
		    support.checkExpression(((Node)yyVals[-2+yyTop]));
		    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = new DotNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]), true);
                }
  break;
case 185:
					// line 783 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "+", ((Node)yyVals[0+yyTop]));
                }
  break;
case 186:
					// line 786 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "-", ((Node)yyVals[0+yyTop]));
                }
  break;
case 187:
					// line 789 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "*", ((Node)yyVals[0+yyTop]));
                }
  break;
case 188:
					// line 792 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "/", ((Node)yyVals[0+yyTop]));
                }
  break;
case 189:
					// line 795 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "%", ((Node)yyVals[0+yyTop]));
                }
  break;
case 190:
					// line 798 "DefaultRubyParser.y"
  {
		      yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "**", ((Node)yyVals[0+yyTop]));
                    /* Covert '- number ** number' to '- (number ** number)' 
                    boolean needNegate = false;
                    if (($1 instanceof FixnumNode && $<FixnumNode>1.getValue() < 0) ||
                        ($1 instanceof BignumNode && $<BignumNode>1.getValue().compareTo(BigInteger.ZERO) < 0) ||
                        ($1 instanceof FloatNode && $<FloatNode>1.getValue() < 0.0)) {

                        $<>1 = support.getOperatorCallNode($1, "-@");
                        needNegate = true;
                    }

                    $$ = support.getOperatorCallNode($1, "**", $3);

                    if (needNegate) {
                        $$ = support.getOperatorCallNode($<Node>$, "-@");
                    }
		    */
                }
  break;
case 191:
					// line 817 "DefaultRubyParser.y"
  {
                    Object number = ((Token)yyVals[-2+yyTop]).getValue();

                    yyVal = support.getOperatorCallNode(support.getOperatorCallNode((number instanceof Long ? (Node) new FixnumNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((Long) number).longValue()) : (Node)new BignumNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((BigInteger) number))), "**", ((Node)yyVals[0+yyTop])), "-@");
                }
  break;
case 192:
					// line 822 "DefaultRubyParser.y"
  {
  /* ENEBO: Seems like this should be $2*/
                    yyVal = support.getOperatorCallNode(support.getOperatorCallNode(new FloatNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((Double) ((Token)yyVals[-3+yyTop]).getValue()).doubleValue()), "**", ((Node)yyVals[0+yyTop])), "-@");
                }
  break;
case 193:
					// line 826 "DefaultRubyParser.y"
  {
 	            if (((Node)yyVals[0+yyTop]) != null && ((Node)yyVals[0+yyTop]) instanceof ILiteralNode) {
		        yyVal = ((Node)yyVals[0+yyTop]);
		    } else {
                        yyVal = support.getOperatorCallNode(((Node)yyVals[0+yyTop]), "+@");
		    }
                }
  break;
case 194:
					// line 833 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[0+yyTop]), "-@");
		}
  break;
case 195:
					// line 836 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "|", ((Node)yyVals[0+yyTop]));
                }
  break;
case 196:
					// line 839 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "^", ((Node)yyVals[0+yyTop]));
                }
  break;
case 197:
					// line 842 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "&", ((Node)yyVals[0+yyTop]));
                }
  break;
case 198:
					// line 845 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<=>", ((Node)yyVals[0+yyTop]));
                }
  break;
case 199:
					// line 848 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), ">", ((Node)yyVals[0+yyTop]));
                }
  break;
case 200:
					// line 851 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), ">=", ((Node)yyVals[0+yyTop]));
                }
  break;
case 201:
					// line 854 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<", ((Node)yyVals[0+yyTop]));
                }
  break;
case 202:
					// line 857 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<=", ((Node)yyVals[0+yyTop]));
                }
  break;
case 203:
					// line 860 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "==", ((Node)yyVals[0+yyTop]));
                }
  break;
case 204:
					// line 863 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "===", ((Node)yyVals[0+yyTop]));
                }
  break;
case 205:
					// line 866 "DefaultRubyParser.y"
  {
                    yyVal = new NotNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "==", ((Node)yyVals[0+yyTop])));
                }
  break;
case 206:
					// line 869 "DefaultRubyParser.y"
  {
                    yyVal = support.getMatchNode(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 207:
					// line 872 "DefaultRubyParser.y"
  {
                    yyVal = new NotNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), support.getMatchNode(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])));
                }
  break;
case 208:
					// line 875 "DefaultRubyParser.y"
  {
                    yyVal = new NotNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])));
                }
  break;
case 209:
					// line 878 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[0+yyTop]), "~");
                }
  break;
case 210:
					// line 881 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<<", ((Node)yyVals[0+yyTop]));
                }
  break;
case 211:
					// line 884 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), ">>", ((Node)yyVals[0+yyTop]));
                }
  break;
case 212:
					// line 887 "DefaultRubyParser.y"
  {
                    yyVal = support.newAndNode(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 213:
					// line 890 "DefaultRubyParser.y"
  {
                    yyVal = support.newOrNode(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 214:
					// line 893 "DefaultRubyParser.y"
  {
	            support.setInDefined(true);
		}
  break;
case 215:
					// line 895 "DefaultRubyParser.y"
  {
                    support.setInDefined(false);
                    yyVal = new DefinedNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((Node)yyVals[0+yyTop]));
                }
  break;
case 216:
					// line 899 "DefaultRubyParser.y"
  {
                    yyVal = new IfNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 217:
					// line 902 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[0+yyTop]);
                }
  break;
case 218:
					// line 906 "DefaultRubyParser.y"
  {
		    support.checkExpression(((Node)yyVals[0+yyTop]));
	            yyVal = ((Node)yyVals[0+yyTop]);   
		}
  break;
case 220:
					// line 912 "DefaultRubyParser.y"
  {
                    warnings.warn(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), "parenthesize argument(s) for future version");
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))).add(((Node)yyVals[-1+yyTop]));
                }
  break;
case 221:
					// line 916 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
                }
  break;
case 222:
					// line 919 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[-1+yyTop]));
                    yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((ListNode)yyVals[-4+yyTop]), ((Node)yyVals[-1+yyTop]));
                }
  break;
case 223:
					// line 923 "DefaultRubyParser.y"
  {
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))).add(new HashNode(getPosition(null), ((ListNode)yyVals[-1+yyTop])));
                }
  break;
case 224:
					// line 926 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[-1+yyTop]));
		    yyVal = new NewlineNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), new SplatNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[-1+yyTop])));
                }
  break;
case 225:
					// line 931 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
                }
  break;
case 226:
					// line 934 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[-2+yyTop]);
                }
  break;
case 227:
					// line 937 "DefaultRubyParser.y"
  {
                    warnings.warn(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), "parenthesize argument(s) for future version");
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop]))).add(((Node)yyVals[-2+yyTop]));
                }
  break;
case 228:
					// line 941 "DefaultRubyParser.y"
  {
                    warnings.warn(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), "parenthesize argument(s) for future version");
                    yyVal = ((ListNode)yyVals[-4+yyTop]).add(((Node)yyVals[-2+yyTop]));
                }
  break;
case 231:
					// line 949 "DefaultRubyParser.y"
  {
                    warnings.warn(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), "parenthesize argument(s) for future version");
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop]))).add(((Node)yyVals[0+yyTop]));
                }
  break;
case 232:
					// line 953 "DefaultRubyParser.y"
  {
                    yyVal = support.arg_blk_pass(((ListNode)yyVals[-1+yyTop]), ((BlockPassNode)yyVals[0+yyTop]));
                }
  break;
case 233:
					// line 956 "DefaultRubyParser.y"
  {
                    yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((ListNode)yyVals[-4+yyTop]), ((Node)yyVals[-1+yyTop]));
                    yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
                }
  break;
case 234:
					// line 960 "DefaultRubyParser.y"
  {
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))).add(new HashNode(getPosition(null), ((ListNode)yyVals[-1+yyTop])));
                    yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
                }
  break;
case 235:
					// line 964 "DefaultRubyParser.y"
  {
                    yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop]))).add(new HashNode(getPosition(null), ((ListNode)yyVals[-4+yyTop]))), ((Node)yyVals[-1+yyTop]));
                    yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
                }
  break;
case 236:
					// line 968 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-3+yyTop]).add(new HashNode(getPosition(null), ((ListNode)yyVals[-1+yyTop])));
                    yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
                }
  break;
case 237:
					// line 972 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[-1+yyTop]));
		    yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-6+yyTop])), ((ListNode)yyVals[-6+yyTop]).add(new HashNode(getPosition(null), ((ListNode)yyVals[-4+yyTop]))), ((Node)yyVals[-1+yyTop]));
                    yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
                }
  break;
case 238:
					// line 977 "DefaultRubyParser.y"
  {
                    yyVal = support.arg_blk_pass(new SplatNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[-1+yyTop])), ((BlockPassNode)yyVals[0+yyTop]));
                }
  break;
case 239:
					// line 980 "DefaultRubyParser.y"
  {
	        }
  break;
case 240:
					// line 983 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_blk_pass(support.list_concat(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop]))).add(((Node)yyVals[-3+yyTop])), ((ListNode)yyVals[-1+yyTop])), ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 241:
					// line 986 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_blk_pass(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(((Node)yyVals[-2+yyTop])), ((BlockPassNode)yyVals[0+yyTop]));
                  }
  break;
case 242:
					// line 989 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop]))).add(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-1+yyTop]));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 243:
					// line 993 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-6+yyTop])), support.list_concat(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-6+yyTop]))).add(((Node)yyVals[-6+yyTop])), new HashNode(getPosition(null), ((ListNode)yyVals[-4+yyTop]))), ((Node)yyVals[-1+yyTop]));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 244:
					// line 997 "DefaultRubyParser.y"
  {
                      yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))).add(new HashNode(getPosition(null), ((ListNode)yyVals[-1+yyTop])));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 245:
					// line 1001 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop]))).add(new HashNode(getPosition(null), ((ListNode)yyVals[-4+yyTop]))), ((Node)yyVals[-1+yyTop]));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 246:
					// line 1005 "DefaultRubyParser.y"
  {
                      yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop]))).add(((Node)yyVals[-3+yyTop])).add(new HashNode(getPosition(null), ((ListNode)yyVals[-1+yyTop])));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 247:
					// line 1009 "DefaultRubyParser.y"
  {
                      yyVal = support.list_concat(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop]))).add(((Node)yyVals[-5+yyTop])), ((ListNode)yyVals[-3+yyTop])).add(new HashNode(getPosition(null), ((ListNode)yyVals[-1+yyTop])));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 248:
					// line 1013 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-6+yyTop])), new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-6+yyTop]))).add(((Node)yyVals[-6+yyTop])).add(new HashNode(getPosition(null), ((ListNode)yyVals[-4+yyTop]))), ((Node)yyVals[-1+yyTop]));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 249:
					// line 1017 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-8+yyTop])), support.list_concat(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-8+yyTop]))).add(((Node)yyVals[-8+yyTop])), ((ListNode)yyVals[-6+yyTop])).add(new HashNode(getPosition(null), ((ListNode)yyVals[-4+yyTop]))), ((Node)yyVals[-1+yyTop]));
                      yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 250:
					// line 1021 "DefaultRubyParser.y"
  {
                      yyVal = support.arg_blk_pass(new SplatNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[-1+yyTop])), ((BlockPassNode)yyVals[0+yyTop]));
		  }
  break;
case 251:
					// line 1024 "DefaultRubyParser.y"
  {}
  break;
case 252:
					// line 1026 "DefaultRubyParser.y"
  { 
		    yyVal = new Long(lexer.getCmdArgumentState().begin());
		}
  break;
case 253:
					// line 1028 "DefaultRubyParser.y"
  {
                    lexer.getCmdArgumentState().reset(((Long)yyVals[-1+yyTop]).longValue());
                    yyVal = ((Node)yyVals[0+yyTop]);
                }
  break;
case 255:
					// line 1034 "DefaultRubyParser.y"
  {                    
		    lexer.setState(LexState.EXPR_ENDARG);
		  }
  break;
case 256:
					// line 1036 "DefaultRubyParser.y"
  {
                    warnings.warn(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), "don't put space before argument parentheses");
		    yyVal = null;
		  }
  break;
case 257:
					// line 1040 "DefaultRubyParser.y"
  {
		    lexer.setState(LexState.EXPR_ENDARG);
		  }
  break;
case 258:
					// line 1042 "DefaultRubyParser.y"
  {
                    warnings.warn(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), "don't put space before argument parentheses");
		    yyVal = ((Node)yyVals[-2+yyTop]);
		  }
  break;
case 259:
					// line 1047 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = new BlockPassNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[0+yyTop]));
                }
  break;
case 260:
					// line 1052 "DefaultRubyParser.y"
  {
                    yyVal = ((BlockPassNode)yyVals[0+yyTop]);
                }
  break;
case 262:
					// line 1057 "DefaultRubyParser.y"
  {
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop]))).add(((Node)yyVals[0+yyTop]));
                }
  break;
case 263:
					// line 1060 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
                }
  break;
case 264:
					// line 1064 "DefaultRubyParser.y"
  {
		    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
                }
  break;
case 265:
					// line 1067 "DefaultRubyParser.y"
  {
                    yyVal = support.arg_concat(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((ListNode)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));
		}
  break;
case 266:
					// line 1070 "DefaultRubyParser.y"
  {  
                    yyVal = new SplatNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[0+yyTop]));
		}
  break;
case 275:
					// line 1082 "DefaultRubyParser.y"
  {
                    yyVal = new VCallNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
		}
  break;
case 276:
					// line 1086 "DefaultRubyParser.y"
  {
                    yyVal = new BeginNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-1+yyTop]));
		}
  break;
case 277:
					// line 1089 "DefaultRubyParser.y"
  { lexer.setState(LexState.EXPR_ENDARG); }
  break;
case 278:
					// line 1089 "DefaultRubyParser.y"
  {
		    warnings.warning(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), "(...) interpreted as grouped expression");
                    yyVal = ((Node)yyVals[-3+yyTop]);
		}
  break;
case 279:
					// line 1093 "DefaultRubyParser.y"
  {
	            yyVal = ((Node)yyVals[-1+yyTop]);
                }
  break;
case 280:
					// line 1096 "DefaultRubyParser.y"
  {
                    yyVal = new Colon2Node(support.union(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 281:
					// line 1099 "DefaultRubyParser.y"
  {
                    yyVal = new Colon3Node(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 282:
					// line 1102 "DefaultRubyParser.y"
  {
                    yyVal = new CallNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((Node)yyVals[-3+yyTop]), "[]", ((Node)yyVals[-1+yyTop]));
                }
  break;
case 283:
					// line 1105 "DefaultRubyParser.y"
  {
                    if (((Node)yyVals[-1+yyTop]) == null) {
                        yyVal = new ZArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))); /* zero length array*/
                    } else {
                        yyVal = ((Node)yyVals[-1+yyTop]);
                    }
                }
  break;
case 284:
					// line 1112 "DefaultRubyParser.y"
  {
                    yyVal = new HashNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((ListNode)yyVals[-1+yyTop]));
                }
  break;
case 285:
					// line 1115 "DefaultRubyParser.y"
  {
		    yyVal = new ReturnNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), null);
                }
  break;
case 286:
					// line 1118 "DefaultRubyParser.y"
  {
                    yyVal = support.new_yield(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((Node)yyVals[-1+yyTop]));
                }
  break;
case 287:
					// line 1121 "DefaultRubyParser.y"
  {
                    yyVal = new YieldNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), null, false);
                }
  break;
case 288:
					// line 1124 "DefaultRubyParser.y"
  {
                    yyVal = new YieldNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), null, false);
                }
  break;
case 289:
					// line 1127 "DefaultRubyParser.y"
  {
	            support.setInDefined(true);
		}
  break;
case 290:
					// line 1129 "DefaultRubyParser.y"
  {
                    support.setInDefined(false);
                    yyVal = new DefinedNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), ((Node)yyVals[-1+yyTop]));
                }
  break;
case 291:
					// line 1133 "DefaultRubyParser.y"
  {
                    ((IterNode)yyVals[0+yyTop]).setIterNode(new FCallNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]), true), (String) ((Token)yyVals[-1+yyTop]).getValue(), null));
                    yyVal = ((IterNode)yyVals[0+yyTop]);
                }
  break;
case 293:
					// line 1138 "DefaultRubyParser.y"
  {
		    if (((Node)yyVals[-1+yyTop]) != null && ((Node)yyVals[-1+yyTop]) instanceof BlockPassNode) {
                        throw new SyntaxException(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), "Both block arg and actual block given.");
		    }
                    ((IterNode)yyVals[0+yyTop]).setIterNode(((Node)yyVals[-1+yyTop]));
                    yyVal = ((IterNode)yyVals[0+yyTop]);
                }
  break;
case 294:
					// line 1145 "DefaultRubyParser.y"
  {
                    yyVal = new IfNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]));
		    /* missing from ruby
			if (cond_negative(&$$->nd_cond)) {
		            NODE *tmp = $$->nd_body;
		            $$->nd_body = $$->nd_else;
		            $$->nd_else = tmp;
			    } */
                }
  break;
case 295:
					// line 1154 "DefaultRubyParser.y"
  {
                    yyVal = new IfNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[-2+yyTop]));
		    /* missing from ruby
			if (cond_negative(&$$->nd_cond)) {
		            NODE *tmp = $$->nd_body;
		            $$->nd_body = $$->nd_else;
		            $$->nd_else = tmp;
			    } */
                }
  break;
case 296:
					// line 1163 "DefaultRubyParser.y"
  { 
	            lexer.getConditionState().begin();
		}
  break;
case 297:
					// line 1165 "DefaultRubyParser.y"
  {
		    lexer.getConditionState().end();
		}
  break;
case 298:
					// line 1167 "DefaultRubyParser.y"
  {
                    yyVal = new WhileNode(getPosition(((ISourcePositionHolder)yyVals[-6+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-1+yyTop]));
		    /* missing from ruby
			if (cond_negative(&$$->nd_cond)) {
			    nd_set_type($$, NODE_UNTIL);
			    } */
                }
  break;
case 299:
					// line 1174 "DefaultRubyParser.y"
  {
                    lexer.getConditionState().begin();
                }
  break;
case 300:
					// line 1176 "DefaultRubyParser.y"
  {
                    lexer.getConditionState().end();
                }
  break;
case 301:
					// line 1178 "DefaultRubyParser.y"
  {
                    yyVal = new UntilNode(getPosition(((ISourcePositionHolder)yyVals[-6+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-1+yyTop]));
		    /* missing from ruby
			if (cond_negative(&$$->nd_cond)) {
			    nd_set_type($$, NODE_WHILE);
			    } */
                }
  break;
case 302:
					// line 1187 "DefaultRubyParser.y"
  {
		    yyVal = new CaseNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop])); /* XXX*/
                }
  break;
case 303:
					// line 1190 "DefaultRubyParser.y"
  {
                    yyVal = new CaseNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), null, ((Node)yyVals[-1+yyTop]));
                }
  break;
case 304:
					// line 1193 "DefaultRubyParser.y"
  {
		    yyVal = ((Node)yyVals[-1+yyTop]);
                }
  break;
case 305:
					// line 1196 "DefaultRubyParser.y"
  {
                    lexer.getConditionState().begin();
                }
  break;
case 306:
					// line 1198 "DefaultRubyParser.y"
  {
                    lexer.getConditionState().end();
                }
  break;
case 307:
					// line 1201 "DefaultRubyParser.y"
  {
                    yyVal = new ForNode(getPosition(((ISourcePositionHolder)yyVals[-8+yyTop])), ((Node)yyVals[-7+yyTop]), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[-4+yyTop]));
                }
  break;
case 308:
					// line 1204 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) {
                        yyerror("class definition in method body");
                    }
                    support.getLocalNames().push(new LocalNamesElement());
                    /* $$ = new Integer(ruby.getSourceLine());*/
                }
  break;
case 309:
					// line 1211 "DefaultRubyParser.y"
  {
                    yyVal = new ClassNode(support.union(((Token)yyVals[-5+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-4+yyTop]), new ScopeNode(getRealPosition(((Node)yyVals[-1+yyTop])), ((LocalNamesElement) support.getLocalNames().peek()).getNamesArray(), ((Node)yyVals[-1+yyTop])), ((Node)yyVals[-3+yyTop]));
                    /* $<Node>$.setLine($<Integer>4.intValue());*/
                    support.getLocalNames().pop();
                }
  break;
case 310:
					// line 1216 "DefaultRubyParser.y"
  {
                    yyVal = new Boolean(support.isInDef());
                    support.setInDef(false);
                }
  break;
case 311:
					// line 1219 "DefaultRubyParser.y"
  {
                    yyVal = new Integer(support.getInSingle());
                    support.setInSingle(0);
                    support.getLocalNames().push(new LocalNamesElement());
                }
  break;
case 312:
					// line 1224 "DefaultRubyParser.y"
  {
                    yyVal = new SClassNode(support.union(((Token)yyVals[-7+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-5+yyTop]), new ScopeNode(getRealPosition(((Node)yyVals[-1+yyTop])), ((LocalNamesElement) support.getLocalNames().peek()).getNamesArray(), ((Node)yyVals[-1+yyTop])));
                    support.getLocalNames().pop();
                    support.setInDef(((Boolean)yyVals[-4+yyTop]).booleanValue());
                    support.setInSingle(((Integer)yyVals[-2+yyTop]).intValue());
                }
  break;
case 313:
					// line 1230 "DefaultRubyParser.y"
  {
                    if (support.isInDef() || support.isInSingle()) { 
                        yyerror("module definition in method body");
                    }
                    support.getLocalNames().push(new LocalNamesElement());
                    /* $$ = new Integer(ruby.getSourceLine());*/
                }
  break;
case 314:
					// line 1237 "DefaultRubyParser.y"
  {
                    yyVal = new ModuleNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-3+yyTop]), new ScopeNode(getRealPosition(((Node)yyVals[-1+yyTop])), ((LocalNamesElement) support.getLocalNames().peek()).getNamesArray(), ((Node)yyVals[-1+yyTop])));
                    /* $<Node>$.setLine($<Integer>3.intValue());*/
                    support.getLocalNames().pop();
                }
  break;
case 315:
					// line 1242 "DefaultRubyParser.y"
  {
		      /* missing
			$<id>$ = cur_mid;
			cur_mid = $2; */
                    support.setInDef(true);
                    support.getLocalNames().push(new LocalNamesElement());
                }
  break;
case 316:
					// line 1250 "DefaultRubyParser.y"
  {
		      /* was in old jruby grammar support.getClassNest() !=0 || IdUtil.isAttrSet($2) ? Visibility.PUBLIC : Visibility.PRIVATE); */
                    /* NOEX_PRIVATE for toplevel */
                    yyVal = new DefnNode(support.union(((Token)yyVals[-5+yyTop]), ((Token)yyVals[0+yyTop])), new ArgumentNode(((ISourcePositionHolder)yyVals[-4+yyTop]).getPosition(), (String) ((Token)yyVals[-4+yyTop]).getValue()), ((Node)yyVals[-2+yyTop]),
		                      new ScopeNode(getRealPosition(((Node)yyVals[-1+yyTop])), ((LocalNamesElement) support.getLocalNames().peek()).getNamesArray(), ((Node)yyVals[-1+yyTop])), Visibility.PRIVATE);
                    /* $<Node>$.setPosFrom($4);*/
                    support.getLocalNames().pop();
                    support.setInDef(false);
		    /* missing cur_mid = $<id>3; */
                }
  break;
case 317:
					// line 1260 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_FNAME);
                }
  break;
case 318:
					// line 1262 "DefaultRubyParser.y"
  {
                    support.setInSingle(support.getInSingle() + 1);
                    support.getLocalNames().push(new LocalNamesElement());
                    lexer.setState(LexState.EXPR_END); /* force for args */
                }
  break;
case 319:
					// line 1268 "DefaultRubyParser.y"
  {
                    yyVal = new DefsNode(support.union(((Token)yyVals[-8+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-7+yyTop]), (String) ((Token)yyVals[-4+yyTop]).getValue(), ((Node)yyVals[-2+yyTop]), new ScopeNode(getPosition(null), ((LocalNamesElement) support.getLocalNames().peek()).getNamesArray(), ((Node)yyVals[-1+yyTop])));
                    /* $<Node>$.setPosFrom($2);*/
                    support.getLocalNames().pop();
                    support.setInSingle(support.getInSingle() - 1);
                }
  break;
case 320:
					// line 1274 "DefaultRubyParser.y"
  {
                    yyVal = new BreakNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])));
                }
  break;
case 321:
					// line 1277 "DefaultRubyParser.y"
  {
                    yyVal = new NextNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])));
                }
  break;
case 322:
					// line 1280 "DefaultRubyParser.y"
  {
                    yyVal = new RedoNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])));
                }
  break;
case 323:
					// line 1283 "DefaultRubyParser.y"
  {
                    yyVal = new RetryNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])));
                }
  break;
case 324:
					// line 1287 "DefaultRubyParser.y"
  {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
		    yyVal = ((Node)yyVals[0+yyTop]);
		}
  break;
case 333:
					// line 1304 "DefaultRubyParser.y"
  {
                    yyVal = new IfNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), support.getConditionNode(((Node)yyVals[-3+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 335:
					// line 1309 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[0+yyTop]);
                }
  break;
case 337:
					// line 1314 "DefaultRubyParser.y"
  {}
  break;
case 339:
					// line 1317 "DefaultRubyParser.y"
  {
                    yyVal = new ZeroArgNode(getPosition(null));
                }
  break;
case 340:
					// line 1320 "DefaultRubyParser.y"
  {
                    yyVal = new ZeroArgNode(getPosition(null));
		}
  break;
case 341:
					// line 1323 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[-1+yyTop]);
                }
  break;
case 342:
					// line 1327 "DefaultRubyParser.y"
  {
                    support.getBlockNames().push(new BlockNamesElement());
		}
  break;
case 343:
					// line 1330 "DefaultRubyParser.y"
  {
                    yyVal = new IterNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), null);
                    support.getBlockNames().pop();
                }
  break;
case 344:
					// line 1335 "DefaultRubyParser.y"
  {
                    if (((Node)yyVals[-1+yyTop]) instanceof BlockPassNode) {
                        throw new SyntaxException(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), "Both block arg and actual block given.");
                    }
                    ((IterNode)yyVals[0+yyTop]).setIterNode(((Node)yyVals[-1+yyTop]));
                    yyVal = ((IterNode)yyVals[0+yyTop]);
                }
  break;
case 345:
					// line 1342 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop]));
                }
  break;
case 346:
					// line 1345 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop]));
                }
  break;
case 347:
					// line 1349 "DefaultRubyParser.y"
  {
                    yyVal = support.new_fcall((String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop])); /* .setPosFrom($2);*/
                }
  break;
case 348:
					// line 1352 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop])); /*.setPosFrom($1);*/
                }
  break;
case 349:
					// line 1355 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[0+yyTop])); /*.setPosFrom($1);*/
                }
  break;
case 350:
					// line 1358 "DefaultRubyParser.y"
  {
                    yyVal = support.new_call(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue(), null);
                }
  break;
case 351:
					// line 1361 "DefaultRubyParser.y"
  {
                    yyVal = support.new_super(((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop]));
                }
  break;
case 352:
					// line 1364 "DefaultRubyParser.y"
  {
                    yyVal = new ZSuperNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])));
                }
  break;
case 353:
					// line 1368 "DefaultRubyParser.y"
  {
                    support.getBlockNames().push(new BlockNamesElement());
		}
  break;
case 354:
					// line 1370 "DefaultRubyParser.y"
  {
                    yyVal = new IterNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), null);
                    support.getBlockNames().pop();
                }
  break;
case 355:
					// line 1374 "DefaultRubyParser.y"
  {
                    support.getBlockNames().push(new BlockNamesElement());
		}
  break;
case 356:
					// line 1376 "DefaultRubyParser.y"
  {
                    yyVal = new IterNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), null);
                    support.getBlockNames().pop();
                }
  break;
case 357:
					// line 1383 "DefaultRubyParser.y"
  {
		    yyVal = new WhenNode(getPosition(((ISourcePositionHolder)yyVals[-4+yyTop])), ((ListNode)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 359:
					// line 1388 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-3+yyTop]).add(new WhenNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((Node)yyVals[0+yyTop]), null, null));
                }
  break;
case 360:
					// line 1391 "DefaultRubyParser.y"
  {
                    yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))).add(new WhenNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[0+yyTop]), null, null));
                }
  break;
case 363:
					// line 1401 "DefaultRubyParser.y"
  {
                    Node node;
		    if (((Node)yyVals[-3+yyTop]) != null) {
                       node = support.appendToBlock(support.node_assign(((Node)yyVals[-3+yyTop]), new GlobalVarNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), "$!")), ((Node)yyVals[-1+yyTop]));
		    } else {
		       node = ((Node)yyVals[-1+yyTop]);
                    }
                    yyVal = new RescueBodyNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop]), true), ((Node)yyVals[-4+yyTop]), node, ((RescueBodyNode)yyVals[0+yyTop]));
		}
  break;
case 364:
					// line 1410 "DefaultRubyParser.y"
  {yyVal = null;}
  break;
case 365:
					// line 1412 "DefaultRubyParser.y"
  {
                    yyVal = new ArrayNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition()).add(((Node)yyVals[0+yyTop]));
		}
  break;
case 368:
					// line 1418 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[0+yyTop]);
                }
  break;
case 370:
					// line 1423 "DefaultRubyParser.y"
  {
                    if (((Node)yyVals[0+yyTop]) != null) {
                        yyVal = ((Node)yyVals[0+yyTop]);
                    } else {
                        yyVal = new NilNode(getPosition(null));
                    }
                }
  break;
case 373:
					// line 1433 "DefaultRubyParser.y"
  {
                    yyVal = new SymbolNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
                }
  break;
case 375:
					// line 1438 "DefaultRubyParser.y"
  {
		    if (((Node)yyVals[0+yyTop]) == null) {
		        yyVal = new StrNode(getPosition(null), "");
		    } else {
		        if (((Node)yyVals[0+yyTop]) instanceof EvStrNode) {
			    yyVal = new DStrNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop]))).add(((Node)yyVals[0+yyTop]));
			} else {
		            yyVal = ((Node)yyVals[0+yyTop]);
			}
		    }
		}
  break;
case 376:
					// line 1450 "DefaultRubyParser.y"
  {
                    yyVal = support.literal_concat(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), null, ((Node)yyVals[0+yyTop]));
		}
  break;
case 377:
					// line 1453 "DefaultRubyParser.y"
  {
                    yyVal = support.literal_concat(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
		}
  break;
case 378:
					// line 1457 "DefaultRubyParser.y"
  {
		     yyVal = ((Node)yyVals[-1+yyTop]);
		}
  break;
case 379:
					// line 1461 "DefaultRubyParser.y"
  {
		    if (((Node)yyVals[-1+yyTop]) == null) {
			  yyVal = new XStrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), null);
		    } else {
		      if (((Node)yyVals[-1+yyTop]) instanceof StrNode) {
			  yyVal = new XStrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((StrNode)yyVals[-1+yyTop]).getValue());
		      } else if (((Node)yyVals[-1+yyTop]) instanceof DStrNode) {
			  yyVal = new DXStrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(((Node)yyVals[-1+yyTop]));
		      } else {
			yyVal = new DXStrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(((Node)yyVals[-1+yyTop])));
		      }
		    }
                }
  break;
case 380:
					// line 1475 "DefaultRubyParser.y"
  {
		    int options = ((RegexpNode)yyVals[0+yyTop]).getOptions();
		    Node node = ((Node)yyVals[-1+yyTop]);

		    if (node == null) {
		        yyVal = new RegexpNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), "", options & ~ReOptions.RE_OPTION_ONCE);
		    } else if (node instanceof StrNode) {
		      yyVal = new RegexpNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((StrNode) node).getValue(), options & ~ReOptions.RE_OPTION_ONCE);
		    } else {
		        if (node instanceof DStrNode == false) {
			    node = new DStrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(node));
		        } 

			yyVal = new DRegexpNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), options, (options & ReOptions.RE_OPTION_ONCE) != 0).add(node);
		    }
		 }
  break;
case 381:
					// line 1492 "DefaultRubyParser.y"
  {
		     yyVal = new ZArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])));
		 }
  break;
case 382:
					// line 1495 "DefaultRubyParser.y"
  {
		     yyVal = ((ListNode)yyVals[-1+yyTop]);
		 }
  break;
case 383:
					// line 1499 "DefaultRubyParser.y"
  {
		     yyVal = null;
		 }
  break;
case 384:
					// line 1502 "DefaultRubyParser.y"
  {
                     Node node = ((Node)yyVals[-1+yyTop]);

                     if (node instanceof EvStrNode) {
		       node = new DStrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(node);
		     }

		     if (((ListNode)yyVals[-2+yyTop]) == null) {
		       yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(node);
		     } else {
		       yyVal = ((ListNode)yyVals[-2+yyTop]).add(node);
		     }
		 }
  break;
case 386:
					// line 1517 "DefaultRubyParser.y"
  {
                     yyVal = support.literal_concat(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
	         }
  break;
case 387:
					// line 1521 "DefaultRubyParser.y"
  {
		     yyVal = new ZArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])));
		 }
  break;
case 388:
					// line 1524 "DefaultRubyParser.y"
  {
		     yyVal = ((ListNode)yyVals[-1+yyTop]);
		 }
  break;
case 389:
					// line 1528 "DefaultRubyParser.y"
  {
		     yyVal = null;
		 }
  break;
case 390:
					// line 1531 "DefaultRubyParser.y"
  {
                     if (((ListNode)yyVals[-2+yyTop]) == null) {
		         yyVal = new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop]))).add(new StrNode(((ISourcePositionHolder)yyVals[-1+yyTop]).getPosition(), (String) ((Token)yyVals[-1+yyTop]).getValue()));
		     } else {
		         yyVal = ((ListNode)yyVals[-2+yyTop]).add(new StrNode(((ISourcePositionHolder)yyVals[-1+yyTop]).getPosition(), (String) ((Token)yyVals[-1+yyTop]).getValue()));
		     }
		 }
  break;
case 391:
					// line 1539 "DefaultRubyParser.y"
  {
		     yyVal = null;
		 }
  break;
case 392:
					// line 1542 "DefaultRubyParser.y"
  {
                     yyVal = support.literal_concat(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
		 }
  break;
case 393:
					// line 1546 "DefaultRubyParser.y"
  {
		     yyVal = null;
		 }
  break;
case 394:
					// line 1549 "DefaultRubyParser.y"
  {
                     yyVal = support.literal_concat(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
		 }
  break;
case 395:
					// line 1554 "DefaultRubyParser.y"
  {
                      yyVal = new StrNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
                  }
  break;
case 396:
					// line 1557 "DefaultRubyParser.y"
  {
                      yyVal = lexer.getStrTerm();
		      lexer.setStrTerm(null);
		      lexer.setState(LexState.EXPR_BEG);
		  }
  break;
case 397:
					// line 1561 "DefaultRubyParser.y"
  {
		      lexer.setStrTerm(((StrTerm)yyVals[-1+yyTop]));
		      yyVal = new EvStrNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), ((Node)yyVals[0+yyTop]));
		  }
  break;
case 398:
					// line 1565 "DefaultRubyParser.y"
  {
		      yyVal = lexer.getStrTerm();
		      lexer.setStrTerm(null);
		      lexer.setState(LexState.EXPR_BEG);
		  }
  break;
case 399:
					// line 1569 "DefaultRubyParser.y"
  {
		      lexer.setStrTerm(((StrTerm)yyVals[-2+yyTop]));
		      Node node = ((Node)yyVals[-1+yyTop]);

		      if (node instanceof NewlineNode) {
		        node = ((NewlineNode)node).getNextNode();
		      }

		      yyVal = support.newEvStrNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), node);
		  }
  break;
case 400:
					// line 1580 "DefaultRubyParser.y"
  {
                      yyVal = new GlobalVarNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
                 }
  break;
case 401:
					// line 1583 "DefaultRubyParser.y"
  {
                      yyVal = new InstVarNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
                 }
  break;
case 402:
					// line 1586 "DefaultRubyParser.y"
  {
                      yyVal = new ClassVarNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
                 }
  break;
case 404:
					// line 1592 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_END);
                    yyVal = ((Token)yyVals[0+yyTop]);
		    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])));
                }
  break;
case 409:
					// line 1603 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_END);

		    /* DStrNode: :"some text #{some expression}"*/
                    /* StrNode: :"some text"*/
		    /* EvStrNode :"#{some expression}"*/
		    DStrNode node;

		    if (((Node)yyVals[-1+yyTop]) instanceof DStrNode) {
		        node = (DStrNode) ((Node)yyVals[-1+yyTop]);
		    } else {
		      node = new DStrNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])));
		      node.add(new ArrayNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop]))).add(((Node)yyVals[-1+yyTop])));
                    }
		    yyVal = new DSymbolNode(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), node);
		}
  break;
case 410:
					// line 1620 "DefaultRubyParser.y"
  {
                    Object number = ((Token)yyVals[0+yyTop]).getValue();

                    if (number instanceof Long) {
		        yyVal = new FixnumNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), ((Long) number).longValue());
                    } else {
		        yyVal = new BignumNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), (BigInteger) number);
                    }
                }
  break;
case 411:
					// line 1629 "DefaultRubyParser.y"
  {
                    yyVal = new FloatNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), ((Double) ((Token)yyVals[0+yyTop]).getValue()).doubleValue());
	        }
  break;
case 412:
					// line 1632 "DefaultRubyParser.y"
  {
                    Object number = ((Token)yyVals[0+yyTop]).getValue();

                    yyVal = support.getOperatorCallNode((number instanceof Long ? (Node) new FixnumNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Long) number).longValue()) : (Node) new BignumNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), (BigInteger) number)), "-@");
		}
  break;
case 413:
					// line 1637 "DefaultRubyParser.y"
  {
                    yyVal = support.getOperatorCallNode(new FloatNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((Double) ((Token)yyVals[0+yyTop]).getValue()).doubleValue()), "-@");
		}
  break;
case 414:
					// line 1646 "DefaultRubyParser.y"
  {
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 415:
					// line 1649 "DefaultRubyParser.y"
  {
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 416:
					// line 1652 "DefaultRubyParser.y"
  {
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 417:
					// line 1655 "DefaultRubyParser.y"
  {
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 418:
					// line 1658 "DefaultRubyParser.y"
  {
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 419:
					// line 1661 "DefaultRubyParser.y"
  { 
                    yyVal = new NilNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
                }
  break;
case 420:
					// line 1664 "DefaultRubyParser.y"
  {
                    yyVal = new SelfNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
                }
  break;
case 421:
					// line 1667 "DefaultRubyParser.y"
  { 
		    yyVal = new TrueNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
                }
  break;
case 422:
					// line 1670 "DefaultRubyParser.y"
  {
		    yyVal = new FalseNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
                }
  break;
case 423:
					// line 1673 "DefaultRubyParser.y"
  {
                    yyVal = new StrNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), getPosition(((ISourcePositionHolder)yyVals[0+yyTop])).getFile());
                }
  break;
case 424:
					// line 1676 "DefaultRubyParser.y"
  {
                    yyVal = new FixnumNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), getPosition(((ISourcePositionHolder)yyVals[0+yyTop])).getEndLine() + 1);
                }
  break;
case 425:
					// line 1680 "DefaultRubyParser.y"
  {
                    /* Work around __LINE__ and __FILE__ */
                    if (yyVals[0+yyTop] instanceof INameNode) {
		        String name = ((INameNode)yyVals[0+yyTop]).getName();
                        yyVal = support.gettable(name, ((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
		    } else if (yyVals[0+yyTop] instanceof Token) {
		      yyVal = support.gettable((String) ((Token)yyVals[0+yyTop]).getValue(), ((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
		    } else {
		        yyVal = yyVals[0+yyTop];
		    }
                }
  break;
case 426:
					// line 1693 "DefaultRubyParser.y"
  {
                    yyVal = support.assignable(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), yyVals[0+yyTop], null);
                }
  break;
case 429:
					// line 1700 "DefaultRubyParser.y"
  {
                    yyVal = null;
                }
  break;
case 430:
					// line 1703 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_BEG);
                }
  break;
case 431:
					// line 1705 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[-1+yyTop]);
                }
  break;
case 432:
					// line 1708 "DefaultRubyParser.y"
  {
                    yyerrok();
                    yyVal = null;
                }
  break;
case 433:
					// line 1713 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[-2+yyTop]);
                    lexer.setState(LexState.EXPR_BEG);
                }
  break;
case 434:
					// line 1717 "DefaultRubyParser.y"
  {
                    yyVal = ((Node)yyVals[-1+yyTop]);
                }
  break;
case 435:
					// line 1721 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(getPosition(((ISourcePositionHolder)yyVals[-5+yyTop])), ((ListNode)yyVals[-5+yyTop]), ((ListNode)yyVals[-3+yyTop]), ((Integer) ((Token)yyVals[-1+yyTop]).getValue()).intValue(), ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 436:
					// line 1724 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((ListNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), -1, ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 437:
					// line 1727 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), ((ListNode)yyVals[-3+yyTop]), null, ((Integer) ((Token)yyVals[-1+yyTop]).getValue()).intValue(), ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 438:
					// line 1730 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(((ISourcePositionHolder)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop]), null, -1, ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 439:
					// line 1733 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(getPosition(((ISourcePositionHolder)yyVals[-3+yyTop])), null, ((ListNode)yyVals[-3+yyTop]), ((Integer) ((Token)yyVals[-1+yyTop]).getValue()).intValue(), ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 440:
					// line 1736 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), null, ((ListNode)yyVals[-1+yyTop]), -1, ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 441:
					// line 1739 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), null, null, ((Integer) ((Token)yyVals[-1+yyTop]).getValue()).intValue(), ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 442:
					// line 1742 "DefaultRubyParser.y"
  {
                    yyVal = new ArgsNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop])), null, null, -1, ((BlockArgNode)yyVals[0+yyTop]));
                }
  break;
case 443:
					// line 1745 "DefaultRubyParser.y"
  {
                   yyVal = new ArgsNode(getPosition(null), null, null, -1, null);
                }
  break;
case 444:
					// line 1749 "DefaultRubyParser.y"
  {
                    yyerror("formal argument cannot be a constant");
                }
  break;
case 445:
					// line 1752 "DefaultRubyParser.y"
  {
                    yyerror("formal argument cannot be an instance variable");
                }
  break;
case 446:
					// line 1755 "DefaultRubyParser.y"
  {
                    yyerror("formal argument cannot be a class variable");
                }
  break;
case 447:
					// line 1758 "DefaultRubyParser.y"
  {
                   String identifier = (String) ((Token)yyVals[0+yyTop]).getValue();
                   if (!IdUtil.isLocal(identifier)) {
                        yyerror("formal argument must be local variable");
                    } else if (((LocalNamesElement) support.getLocalNames().peek()).isLocalRegistered(identifier)) {
                        yyerror("duplicate argument name");
                    }
		    /* Register new local var or die trying (side-effect)*/
                    ((LocalNamesElement) support.getLocalNames().peek()).getLocalIndex(identifier);
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 448:
					// line 1770 "DefaultRubyParser.y"
  {
                    yyVal = new ListNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
                    ((ListNode) yyVal).add(new ArgumentNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue()));
                }
  break;
case 449:
					// line 1774 "DefaultRubyParser.y"
  {
                    ((ListNode)yyVals[-2+yyTop]).add(new ArgumentNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue()));
                    ((ListNode)yyVals[-2+yyTop]).setPosition(support.union(((ListNode)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
		    yyVal = ((ListNode)yyVals[-2+yyTop]);
                }
  break;
case 450:
					// line 1780 "DefaultRubyParser.y"
  {
                    String identifier = (String) ((Token)yyVals[-2+yyTop]).getValue();

                    if (!IdUtil.isLocal(identifier)) {
                        yyerror("formal argument must be local variable");
                    } else if (((LocalNamesElement) support.getLocalNames().peek()).isLocalRegistered(identifier)) {
                        yyerror("duplicate optional argument name");
                    }
		    ((LocalNamesElement) support.getLocalNames().peek()).getLocalIndex(identifier);
                    yyVal = support.assignable(getPosition(((ISourcePositionHolder)yyVals[-2+yyTop])), identifier, ((Node)yyVals[0+yyTop]));
                }
  break;
case 451:
					// line 1792 "DefaultRubyParser.y"
  {
                    yyVal = new BlockNode(getPosition(((ISourcePositionHolder)yyVals[0+yyTop]))).add(((Node)yyVals[0+yyTop]));
                }
  break;
case 452:
					// line 1795 "DefaultRubyParser.y"
  {
                    yyVal = support.appendToBlock(((ListNode)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                }
  break;
case 455:
					// line 1802 "DefaultRubyParser.y"
  {
                    String identifier = (String) ((Token)yyVals[0+yyTop]).getValue();

                    if (!IdUtil.isLocal(identifier)) {
                        yyerror("rest argument must be local variable");
                    } else if (((LocalNamesElement) support.getLocalNames().peek()).isLocalRegistered(identifier)) {
                        yyerror("duplicate rest argument name");
                    }
		    ((Token)yyVals[-1+yyTop]).setValue(new Integer(((LocalNamesElement) support.getLocalNames().peek()).getLocalIndex(identifier)));
                    yyVal = ((Token)yyVals[-1+yyTop]);
                }
  break;
case 456:
					// line 1813 "DefaultRubyParser.y"
  {
                    ((Token)yyVals[0+yyTop]).setValue(new Integer(-2));
                    yyVal = ((Token)yyVals[0+yyTop]);
                }
  break;
case 459:
					// line 1821 "DefaultRubyParser.y"
  {
                    String identifier = (String) ((Token)yyVals[0+yyTop]).getValue();

                    if (!IdUtil.isLocal(identifier)) {
                        yyerror("block argument must be local variable");
                    } else if (((LocalNamesElement) support.getLocalNames().peek()).isLocalRegistered(identifier)) {
                        yyerror("duplicate block argument name");
                    }
                    yyVal = new BlockArgNode(getPosition(((ISourcePositionHolder)yyVals[-1+yyTop])), ((LocalNamesElement) support.getLocalNames().peek()).getLocalIndex(identifier));
                }
  break;
case 460:
					// line 1832 "DefaultRubyParser.y"
  {
                    yyVal = ((BlockArgNode)yyVals[0+yyTop]);
                }
  break;
case 461:
					// line 1835 "DefaultRubyParser.y"
  {
	            yyVal = null;
	        }
  break;
case 462:
					// line 1839 "DefaultRubyParser.y"
  {
                    if (((Node)yyVals[0+yyTop]) instanceof SelfNode) {
		        yyVal = new SelfNode(((ISourcePositionHolder)yyVals[0+yyTop]).getPosition());
                    } else {
			support.checkExpression(((Node)yyVals[0+yyTop]));
			yyVal = ((Node)yyVals[0+yyTop]);
		    }
                }
  break;
case 463:
					// line 1847 "DefaultRubyParser.y"
  {
                    lexer.setState(LexState.EXPR_BEG);
                }
  break;
case 464:
					// line 1849 "DefaultRubyParser.y"
  {
                    if (((Node)yyVals[-2+yyTop]) instanceof ILiteralNode) {
                        /*case Constants.NODE_STR:
                        case Constants.NODE_DSTR:
                        case Constants.NODE_XSTR:
                        case Constants.NODE_DXSTR:
                        case Constants.NODE_DREGX:
                        case Constants.NODE_LIT:
                        case Constants.NODE_ARRAY:
                        case Constants.NODE_ZARRAY:*/
                        yyerror("Can't define single method for literals.");
                    }
		    support.checkExpression(((Node)yyVals[-2+yyTop]));
                    yyVal = ((Node)yyVals[-2+yyTop]);
                }
  break;
case 466:
					// line 1866 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
                }
  break;
case 467:
					// line 1869 "DefaultRubyParser.y"
  {
                    if (((ListNode)yyVals[-1+yyTop]).size() % 2 != 0) {
                        yyerror("Odd number list for Hash.");
                    }
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
                }
  break;
case 469:
					// line 1877 "DefaultRubyParser.y"
  {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).addAll(((ListNode)yyVals[0+yyTop]));
              	System.err.println("assoc ok");
                }
  break;
case 470:
					// line 1881 "DefaultRubyParser.y"
  {
              	System.err.println("assocs ',' error");
              }
  break;
case 471:
					// line 1885 "DefaultRubyParser.y"
  {
                    yyVal = new ArrayNode(support.union(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]))).add(((Node)yyVals[-2+yyTop])).add(((Node)yyVals[0+yyTop]));
                }
  break;
case 491:
					// line 1915 "DefaultRubyParser.y"
  {
                    yyerrok();
                }
  break;
case 494:
					// line 1921 "DefaultRubyParser.y"
  {
                    yyerrok();
                }
  break;
case 495:
					// line 1925 "DefaultRubyParser.y"
  {
                    yyVal = null;
                }
  break;
case 496:
					// line 1929 "DefaultRubyParser.y"
  {  yyVal = null;
		  }
  break;
case 497:
					// line 1932 "DefaultRubyParser.y"
  {  yyVal = null;
		  }
  break;
					// line 7792 "-"
        }
        yyTop -= yyLen[yyN];
        yyState = yyStates[yyTop];
        int yyM = yyLhs[yyN];
        if (yyState == 0 && yyM == 0) {
//t          if (yydebug != null) yydebug.shift(0, yyFinal);
          yyState = yyFinal;
          if (yyToken < 0) {
            yyToken = yyLex.advance() ? yyLex.token() : 0;
//t            if (yydebug != null)
//t               yydebug.lex(yyState, yyToken,yyName(yyToken), yyLex.value());
          }
          if (yyToken == 0) {
//t            if (yydebug != null) yydebug.accept(yyVal);
            return yyVal;
          }
          continue yyLoop;
        }
        if ((yyN = yyGindex[yyM]) != 0 && (yyN += yyState) >= 0
            && yyN < yyTable.length && yyCheck[yyN] == yyState)
          yyState = yyTable[yyN];
        else
          yyState = yyDgoto[yyM];
//t        if (yydebug != null) yydebug.shift(yyStates[yyTop], yyState);
        continue yyLoop;
      }
    }
  }

					// line 1935 "DefaultRubyParser.y"

      
    /** The parse method use an lexer stream and parse it to an AST node 
     * structure
     * @throws yyException 
     */
    public RubyParserResult parse(LexerSource source) throws yyException {
        support.reset();
        support.setResult(new RubyParserResult());
        
        lexer.reset();
        lexer.setSource(source);
        support.setPositionFactory(lexer.getPositionFactory());
        try {
	    //yyparse(lexer, new jay.yydebug.yyAnim("JRuby", 9));
	    //yyparse(lexer, new jay.yydebug.yyDebugAdapter());
	    yyparse(lexer, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return support.getResult();
    }

    public void init(RubyParserConfiguration configuration) {
        support.setConfiguration(configuration);
    }

    // +++
    // Helper Methods
    
    void yyerrok() {}

    private ISourcePosition getRealPosition(Node node) {
      if (node == null) {
	return getPosition(null);
      }

      if (node instanceof BlockNode) {
	return node.getPosition();
      }

      if (node instanceof NewlineNode) {
	while (node instanceof NewlineNode) {
	  node = ((NewlineNode) node).getNextNode();
	}
	return node.getPosition();
      }

      return getPosition((ISourcePositionHolder)node);
    }

    private ISourcePosition getPosition(ISourcePositionHolder start) {
        return getPosition(start, false);
    }

    private ISourcePosition getPosition(ISourcePositionHolder start, boolean inclusive) {
        if (start != null) {
	    return lexer.getPosition(start.getPosition(), inclusive);
	} 
	
	return lexer.getPosition(null, inclusive);
    }
}
					// line 7888 "-"
