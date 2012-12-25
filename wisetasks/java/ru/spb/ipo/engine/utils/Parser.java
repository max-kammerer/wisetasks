/*
 * This file is part of Wisetasks
 *
 * Copyright (C) 2006-2008, 2012  Michael Bogdanov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.spb.ipo.engine.utils;

import java.util.Stack;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;

import ru.spb.ipo.engine.exception.UserAnswerParseException;


public class Parser {

    private Stack stack;
    private String exps;
    private ArrayList pol;

    private static String termO = "(";
    private static String termC = ")";
    private static String termM = "*";
    private static String termD = "/";
    private static String termA = "+";
    private static String termS = "-";
    private static String termP = "^";
    private static String termF = "!";

    private static TreeSet terms = new TreeSet ( Arrays.asList(new Object[] { termO, termC, termM, termD, termA, termS, termP, termF }));
    private int k;

    public Parser() {        
        stack = new Stack();        
        pol = new ArrayList();
    }

    /**
     * Вставляет нули перед унарным минусом.
     * @param sb
     */
    private static StringBuffer preParse(StringBuffer sb){
        int i = 0;
        while (i < sb.length()) {
            if ((sb.charAt(i) == termS.charAt(0)) && ((i == 0)||(sb.charAt(i-1) == termO.charAt(0)))) {
                sb = sb.insert(i, '0');
                i++;
            }
            i++;
        }
        return sb;
    }

    private void rebuildExps() {
        k = 0;
        StringBuffer sbi = new StringBuffer(exps);
        sbi = preParse(sbi);
        //System.out.println(sbi);
        while (k < sbi.length()) {
            String term = getTerm(sbi);

            if (termC.equals(term)) {
                String str = null;
                do {
                    str = (String)stack.pop();
                    if (!termO.equals(str))  pol.add(str);
                }  while (!termO.equals(str));
            }

            if (isNumber(term)) {
                pol.add(term);
            }

            if (isFunction(term)) {
                BigInteger [] abi = getParameters(sbi);
                if ("C".equals(term.toUpperCase())) {
                    pol.add(MathOperations.combination(abi[0].longValue(), abi[1].longValue()).toString());
                }

                if ("A".equals(term.toUpperCase())) {
                    pol.add(MathOperations.layout(abi[0].longValue(), abi[1].longValue()).toString());
                }
            }

            if(termO.equals(term)) {
                stack.push(termO);
            }

            if ((k + 1 <= sbi.length()) && isOperation(sbi.substring(k,k+1))) {
                if (stack.size() == 0) stack.push(sbi.substring(k,k+1));
                else {
                    String c =  sbi.substring(k, k + 1);
                    String stemp = (String)stack.pop();
                    if (getPrior(c)>getPrior(stemp)) {
                        stack.push(stemp);
                        stack.push(c);
                    } else {
                        while ((stemp!= null)&&(getPrior(c) <= getPrior(stemp))) {
                            pol.add(stemp);
                            if (stack.size() != 0)
                                stemp = (String)stack.pop();
                            else stemp = null;
                        }
                        if (stemp != null) stack.push(stemp);
                        stack.push(c);
                    }
                }
                k++;
            }

            if (isOperation(term)) {
                if (stack.size() == 0) stack.push(term);
                else {
                    String c =  term;
                    String stemp = (String)stack.pop();
                    if (getPrior(c)>getPrior(stemp)) {
                        stack.push(stemp);
                        stack.push(c);
                    } else {
                        while ((stemp!= null)&&(getPrior(c) <= getPrior(stemp))) {
                            pol.add(stemp);
                            if (stack.size() != 0)
                                stemp = (String)stack.pop();
                            else stemp = null;
                        }
                        if (stemp != null) stack.push(stemp);
                        stack.push(c);
                    }
                }
            }

        }

        while (stack.size() != 0) {
            pol.add(stack.pop());
        }
        //System.out.println(pol);
    }
    
    public RationalNumber[] parseUserAnswer(String ustr) throws UserAnswerParseException {
    	try {
	    	ustr = ustr.trim();
	    	if ("empty".equalsIgnoreCase(ustr)) {
	    		return new RationalNumber[0];
	    	}
	    	String answers [] = ustr.split(";");
	    	ArrayList al = new ArrayList();
	    	for (int i = 0; i < answers.length; i++) {
				String str = answers[i];
				if (!"".equals(str.trim())) {
					al.add(parse(str));
				}
			}	    	
	    	return (RationalNumber[]) al.toArray(new RationalNumber[al.size()]);
    	} catch (Exception e) {
    		throw new UserAnswerParseException("Ошибка в записи выражения. Проверьте его правильность!" ,e);
		}
    }

    public RationalNumber parse(String ustr) {
    	ustr = ustr.replaceAll("\\s+", "");
    	stack.clear();
    	pol.clear();
    	exps = ustr;
    	k = 0;
        rebuildExps();
        int index = 0;
        while ((pol.size() >= 1) && (index < pol.size())) {        	
             if (isNumber((String)pol.get(index))) {
            	 //System.err.println(pol.get(index));
                 String str = (String)pol.remove(index);
                 pol.add(index, new RationalNumber( new BigInteger(str)));
                 index++;
             } else if (isOperation((String)pol.get(index))) {
               String op = (String)pol.get(index);
               RationalNumber res = null;
               if (termA.equals(op) || termS.equals(op) || termD.equals(op) || termM.equals(op) || termP.equals(op)) {
            	   RationalNumber bi1, bi2;
                   if (index > 0) {
                       bi2 = (RationalNumber)pol.remove(index - 1);
                       index--;
                   } else bi2 = new RationalNumber( BigInteger.valueOf(0));

                   if (index > 0) {
                       bi1 = (RationalNumber)pol.remove(index - 1);
                       index--;
                   } else bi1 = new RationalNumber(BigInteger.valueOf(0));

                   if (termA.equals(op)) res = bi1.add(bi2);
                   if (termS.equals(op)) res = bi1.substract(bi2);
                   if (termM.equals(op)) res = bi1.multiply(bi2);
                   if (termD.equals(op)) res = bi1.divide(bi2);
                   if (termP.equals(op)) res = bi1.pow(bi2);
               }
               if (termF.equals(op)) {
            	   RationalNumber bi1;
                   if (index > 0) {
                       bi1 = (RationalNumber)pol.remove(index - 1);
                       index--;
                   } else bi1 = new RationalNumber(BigInteger.valueOf(0));
                   res = new RationalNumber(MathOperations.factorial(bi1.getBigInteger().intValue()));
               }
               pol.remove(index);
               pol.add(index, res);
               index++;
            } else {
            	throw new RuntimeException();
            }
        }
        //System.out.println(pol);        
        return (RationalNumber) pol.get(0);
    }

    public String getTerm(StringBuffer sb){
        String ch = sb.substring(k, k + 1);

        if (terms.contains(ch)) {
            k++;
            return ch;
        } else {
            int temp = k;

            if (isDigit(sb, k)) {
                while (isDigit(sb,temp)) temp++;
                String str = sb.substring(k, temp);
                k = temp;
                return str;
            }

            if (isLetter(sb, k)) {
                while (isLetter(sb,temp)) temp++;
                String str = sb.substring(k, temp);
                k = temp;
                return str;
            }

        }
        return "";
    }

    public static boolean isDigit(StringBuffer sb, int k) {
        if (k >= sb.length()) return false;
        char c = sb.charAt(k);
        if ((c >= '0') && (c <='9')) return true;
        else return false;
    }

    public static boolean isLetter(StringBuffer sb, int k) {
        if (k >= sb.length()) return false;
        char c = sb.charAt(k);
        if (((c >= 'a') && (c <='z')) || ((c >= 'A') && (c <='Z'))) return true;
        else return false;
    }

    public static boolean isFunction(String str) {
        char c = str.charAt(0);
        if (((c >= 'a') && (c <='z')) || ((c >= 'A') && (c <='Z'))) return true;
        else return false;
    }

    public static boolean isNumber(String s) {
        char c = s.charAt(0);
        if ((c >= '0') && (c <='9')) return true;
        else return false;
    }


    public static boolean isOperation(String s) {
        if (termA.equals(s) || termS.equals(s) || termD.equals(s) || termM.equals(s) || termP.equals(s) || termF.equals(s)) return true;
        return false;
    }

    public static int getPrior(String c) {
        if  (termF.equals(c)) return 5;
        if  (termP.equals(c)) return 4;
        if  (termM.equals(c) || termD.equals(c)) return 3;
        if  (termA.equals(c) || termS.equals(c)) return 2;
        if  (termO.equals(c)) return 1;
        return 0;
    }

    public static void main(String [] str) {
        if (str != null) {
        	System.out.println(new Parser().parse(str[0]));
        }
    }

    private BigInteger [] getParameters(StringBuffer sb) {
        String pstr = null;
        if (k >= sb.length()) return null;
        if (!termO.equals(sb.substring(k, k+1))) return null;
        int count = 1;
        int oldk = k + 1;
        while (count != 0) {
            k++;
            if (k >= sb.length()) return null;
            if (termO.equals(sb.substring(k, k+1))) count++;
            if (termC.equals(sb.substring(k, k+1))) count--;
        }
        pstr = sb.substring(oldk, k);
        //System.out.println(pstr);
        k++;
        String [] strs = split(pstr);
        BigInteger [] abi = new BigInteger[strs.length];
        Parser p = new Parser();
        for (int i = 0; i < abi.length; i++) {            
            abi[i] = p.parse(strs[i].trim()).getBigInteger();
        }
        //System.out.println(strs);
        //System.out.println(abi);
        return abi;
    }

    private String [] split(String str) {
        ArrayList al = new ArrayList();
        str = str.trim();
        int count = 0;
        int p = 0;
        int oldp = p;
        while (p != str.length() - 1) {
            if ((str.charAt(p) == ',') && (count == 0)) {
                al.add(str.substring(oldp, p));
                oldp = p + 1;
            }
            if (termO.equals(str.substring(p, p+1))) count++;
            if (termC.equals(str.substring(p, p+1))) count--;
            p++;
        }
        al.add(str.substring(oldp, p + 1));
        return  (String []) al.toArray(new String[0]);
    }

}


