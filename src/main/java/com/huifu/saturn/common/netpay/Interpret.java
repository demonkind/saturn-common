/**
 * 
 *上海汇付网络科技有限公司
 *
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.saturn.common.netpay;

/**
 * 计算手续费
 */
import java.util.Vector;

/* Data Type 
	"01"	-	( Number )
	"03"	-	( Vector )
	"04"	-	( Date ) 
*/

// the stack of operator
class OptrStack {

    private int    top;
    private char[] stack;

    public OptrStack() {
        top = -1;
        stack = new char[50];
    }

    public int push(char optr) {
        if (top > 49)
            return -1;

        top++;
        stack[top] = optr;

        return 0;
    }

    public char pop() {
        if (top < 0)
            return '$';
        top--;

        return (stack[top + 1]);
    }

    public char getTop() {
        if (top < 0)
            return '$';

        return (stack[top]);
    }
}

//the stack of operand
class OpndStack {
    private Vector stack;

    public OpndStack() {
        stack = new Vector();
    }

    public int push(String Type, Object Value) {
        DATA data = new DATA(Type, Value);
        stack.addElement(data);

        return 0;
    }

    public int push(DATA data) {

        stack.addElement(data);

        return 0;
    }

    public DATA pop() {
        DATA data;

        int k = stack.size();

        if (k == 0)
            return null;

        data = (DATA) stack.elementAt(k - 1);

        stack.removeElementAt(k - 1);

        return data;

    }

    public String getTopType() {
        DATA data;
        int k = stack.size();

        if (k == 0)
            return null;

        data = (DATA) stack.elementAt(k - 1);

        return (data.getType());

    }

}

public class Interpret {
    // String MerId , Month , CalcCond ;
    String MerId, StartDate, EndDate, CalcCond;
    String GateIdCond = " ";

    String TransInfo[][];

    /* D -- by database 	*/
    /* P -- by parameter  	*/

    public Interpret() {
    }

    // Month0 -- YYYYMM
    public Interpret(String MerId0, String StartDate0, String EndDate0, String GateId0,
                     String CalcCond0) {
        MerId = MerId0;
        StartDate = StartDate0;
        EndDate = EndDate0;
        CalcCond = CalcCond0.trim();

        if (CalcCond.length() != 0)
            CalcCond = " and " + CalcCond;

        GateId0 = GateId0.trim();

        if (GateId0.length() != 0)
            GateIdCond = " and gate_id='" + GateId0 + "' ";
        else
            GateIdCond = " ";
    }

    String TransAmt    = "0.00";
    double fTransAmt   = 0;

    /* the second amt */
    double secTransAmt = 0;

    public void setSecondAmt(String secondAmt0) {
        secTransAmt = (new Double(secondAmt0)).doubleValue();
    }

    public String getFeeAmt(String sExp, String TransAmt0) {
        TransAmt = TransAmt0;
        fTransAmt = (new Double(TransAmt)).doubleValue();
        //System.out.println( "fAmt[" + fTransAmt ) ; 
        DATA feeData;
        try {
            feeData = getExpValue(sExp, true);
            double dd;
            dd = ((Double) feeData.getValue()).doubleValue();

            dd = ((long) (dd * 100.00 + 0.50001)) / 100.0;
            return (double2amt(dd));
        } catch (Exception ee) {
            String ErrMsg = "Calc Err" + sExp + ee;
            System.out.println(ErrMsg);
            return null;
        }
    }

    private String double2amt(double f) {
        java.text.DecimalFormat dmf = new java.text.DecimalFormat("0.00");
        return (dmf.format(f));
    }

    // get the value of one express such as 
    //	MON + MAX( 100 , SUM(AMT*OFR)*0.85 )
    public DATA getExpValue(String sExp) throws Exception {
        return (getExpValue(sExp, true));

    }

    public DATA getExpValue(String sExp, boolean check) throws Exception {

        OptrStack optrstack = new OptrStack();
        OpndStack opndstack = new OpndStack();

        char optr, ch;
        DATA data1, data2, data;

        String type, sFun;

        Double L;

        double l;

        if (check) {
            sExp = sExp.toUpperCase();
            sExp = trimSpace(sExp);
            //sExp = replaceByMacroDef( sExp ) ;
        }

        //System.out.println( "exp[" + sExp + "]" ) ;

        String s;

        while (true) {
            if (sExp.length() == 0)
                break;
            s = getWord(sExp);
            //System.out.println( "sExp[" + sExp + 
            //	"]len[" + sExp.length( ) + "]s[" + s + "]" ) ;

            if (s.equals("AMT")) {
                //System.out.println( "fAmt[" + fTransAmt ) ; 
                opndstack.push("01", new Double(fTransAmt));
            } else if (s.equals("+") || s.equals("-")) {
                optr = optrstack.pop();
                ch = s.charAt(0);
                if (optr == '$') {
                    optrstack.push(ch);
                    sExp = sExp.substring(1);
                    continue;
                }

                data2 = opndstack.pop();
                data1 = opndstack.pop();

                if (data1 == null || data2 == null) {
                    throw new Exception("005");
                }

                data = Calc(data1, data2, optr);

                optrstack.push(ch);
                opndstack.push(data);
            } else if (s.equals("*")) {
                optrstack.push('*');

            } else if (s.equals("/")) {
                optrstack.push('/');

            } else if (s.equals("(")) {
                sFun = getFunStr(sExp);
                //System.out.println( "sFun[" + sFun + "]" ) ; 

                if (sFun == null) {
                    throw new Exception("KUO005");
                }

                if (sFun.equals("()")) {
                    throw new Exception("KUO010");
                }

                data = getExpValue(sFun.substring(1, sFun.length() - 1));
                sExp = sExp.substring(sFun.length());
                opndstack.push(data);
                s = "";
                //System.out.println( "sFun[" + sFun + "]" ) ; 
            } else if (s.equals("WANGYI")) {
                l = GetWangYiFee();
                opndstack.push("01", new Double(l));
            } else if (s.equals("INC")) {
                sExp = sExp.substring(s.length());
                sFun = getFunStr(sExp);

                if (sFun == null) {
                    throw new Exception("030");
                }

                if (sFun.length() == 0)
                    throw new Exception("031");

                sExp = sExp.substring(sFun.length());

                l = INC(sFun.substring(1, sFun.length() - 1));
                opndstack.push("01", new Double(l));

                s = "";
            } else if (s.charAt(0) == '[')
            //if it is constant Date data type 
            {
                if ((s.length() == 10) && (s.charAt(0) == '[' && s.charAt(9) == ']')
                    && (s.substring(1, 4).equals("199") || s.substring(1, 4).equals("200"))) {
                    ;
                } else {
                    throw new Exception("006");
                }

                if (!isNumber(s.substring(1, 9), '0')) {
                    throw new Exception("007");
                }

                type = opndstack.getTopType();
                optr = optrstack.getTop();

                if (type == null || optr == '$') {
                    opndstack.push("04", new String(s.substring(1, 9)));
                } else {
                    throw new Exception("008");
                }
            } else if ((s.charAt(0) <= '9' && s.charAt(0) >= '0')) {
                if (!isNumber(s, '.')) {
                    throw new Exception("010");
                }
                opndstack.push("01", (new Double(s)));
                //System.out.println( "number[" + s + "]" ) ;

            } else if (s.equals("SUM"))
            //if meet SUM( ) 
            {
                sExp = sExp.substring(s.length());
                sFun = getFunStr(sExp);

                if (sFun == null) {
                    throw new Exception("030");
                }

                if (sFun.length() == 0)
                    throw new Exception("031");

                sExp = sExp.substring(sFun.length());

                l = SUM(sFun.substring(1, sFun.length() - 1));

                opndstack.push("01", new Double(l));

                s = "";
            } else if (s.equals("MIN") || s.equals("MAX") || s.equals("MTM"))
            //if meet MIN( ) , MAX( ) , MTM( ) 
            {

                int flag = 0;
                if (s.equals("MIN"))
                    flag = 1;
                else if (s.equals("MAX"))
                    flag = 2;
                else if (s.equals("MTM"))
                    flag = 3;

                sExp = sExp.substring(s.length());
                sFun = getFunStr(sExp);

                if (sFun == null) {
                    throw new Exception("035");
                }

                sExp = sExp.substring(sFun.length());

                data = MMM(sFun.substring(1, sFun.length() - 1), flag);

                opndstack.push(data);

                s = "";
            } else if (s.equals("CNT")) {
                l = getTransCnt();
                opndstack.push("01", new Double(l));
            } else if (s.equals("SCD"))
            // get system current date
            {
                //opndstack.push( "04" , CPublic.getDate() ) ;
            } else if (s.equals("PGS"))
            // progression : such as PGS(100,0.01,1000,0.005,0.001)
            {
                sExp = sExp.substring(s.length());
                sFun = getFunStr(sExp);

                if (sFun == null) {
                    throw new Exception("PGS035");
                }

                if (sFun.equals("()")) {
                    throw new Exception("PGS036");
                }

                data = PGS(sFun.substring(1, sFun.length() - 1));
                sExp = sExp.substring(sFun.length());
                opndstack.push(data);
                s = "";
            } else if (s.equals("FLO"))
            // double : such as FLO(100,0.01,1000,0.005,0.001)
            {
                sExp = sExp.substring(s.length());
                sFun = getFunStr(sExp);
                //System.out.println( "sFun[" + sFun + "]" ) ; 

                if (sFun == null) {
                    throw new Exception("FLO035");
                }

                if (sFun.equals("()")) {
                    throw new Exception("FLO036");
                }

                data = FLO(sFun.substring(1, sFun.length() - 1));
                sExp = sExp.substring(sFun.length());
                opndstack.push(data);
                s = "";
                //System.out.println( "sFun[" + sFun + "]" ) ; 
            } else if (s.equals("SECAMT")) {
                opndstack.push("01", new Double(secTransAmt));
            } else {
                //System.out.println( "sExp[" + sExp + 
                //	"]s[" + s + "]" ) ;
                throw new Exception("040");
            }

            sExp = sExp.substring(s.length());
        }

        while (true) {
            optr = optrstack.pop();
            if (optr != '$') {
                data2 = opndstack.pop();
                data1 = opndstack.pop();

                if (data1 == null || data2 == null) {
                    throw new Exception("045");
                }

                data = Calc(data1, data2, optr);
                opndstack.push(data);
                //System.out.println("clac" + data.getType( ) +
                //"data" + data.getValue( ) ) ;

            } else {
                data = opndstack.pop();

                if (data == null) {
                    throw new Exception("050");
                }
                break;
            }
        }

        if (opndstack.pop() != null) {
            throw new Exception("060");
        }

        return data;
    }

    //calc the value by data1 , data2 and operator
    private DATA Calc(DATA data1, DATA data2, char op) throws Exception {

        String type, type1, type2;
        double l1 = 0, l2 = 0;

        type1 = data1.getType();
        type2 = data2.getType();

        if (type1.equals("01") && //Double+Double=Double
            type2.equals("01")) {
            l1 = ((Double) data1.getValue()).doubleValue();
            l2 = ((Double) data2.getValue()).doubleValue();

            type = "01";
            Double l;
            if (op == '+')
                l = new Double(l1 + l2);
            else if (op == '-')
                l = new Double(l1 - l2);
            else if (op == '*')
                l = new Double(l1 * l2);
            else if (op == '/')
                l = new Double(l1 / l2);
            else {
                throw new Exception("108");
            }

            return (new DATA(type, l));
        } else if (type1.equals("03") && //Vector*Double=Vector
                   op == '*' && type2.equals("01")) {
            Vector v1 = (Vector) data1.getValue();
            Vector v2 = new Vector();
            l2 = ((Double) data2.getValue()).doubleValue();

            int i, k;

            k = v1.size();
            for (i = 0; i < k; i++) {
                l1 = ((Double) (v1.elementAt(i))).doubleValue();
                l1 = l1 * l2;
                v2.addElement(new Double(l1));
            }
            type = "03";

            return (new DATA(type, v2));

        } else if (type1.equals("04") && //Date + Double = Date
                   op == '+' && type2.equals("01")) {
            String date1 = (String) data1.getValue();
            l1 = ((Double) (data2.getValue())).doubleValue();

            date1 = getAfterDate(date1, (int) l1);

            if (date1 == null) {
                throw new Exception("109");
            }

            return (new DATA("04", date1));

        } else {
            throw new Exception("110");
        }

    }

    private double INC(String s) throws Exception {
        int i, k;
        double f_sumAmt = getSumAmt();

        // seach how many couple ','
        for (k = 0, i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',')
                k++;
        }

        // INC should be the type :INC(2.5,20000)
        if (k != 1) {
            throw (new Exception("FLO005"));
        }

        double ff, one_fee;
        String ss;

        ss = getWord(s);
        if (ss.length() == 0)
            throw new Exception("FLO006");

        s = s.substring(ss.length());
        //System.out.println("ss" + ss ) ;

        if (!isNumber(ss, '.')) {
            throw new Exception("FLO010");
        }
        one_fee = (new Double(ss)).doubleValue();

        ss = getWord(s);
        s = s.substring(ss.length());
        if (ss.charAt(0) != ',') {
            throw new Exception("FLO015");
        }

        ss = getWord(s);
        if (ss.length() == 0)
            throw new Exception("FLO026");

        if (!isNumber(ss, '.')) {
            throw new Exception("FLO027");
        }
        ff = (new Double(ss)).doubleValue();

        double fee_amt = 0;

        int count = (int) (f_sumAmt / ff) + 1;

        fee_amt = one_fee * count;

        return (fee_amt);
    }

    // get the sum value by adding all fields of vector
    private double SUM(String s) throws Exception {

        DATA data = getExpValue(s, false);

        if (!((data.getType()).equals("01"))) {
            throw new Exception("200");
        }

        double sum = 0;

        sum = ((Double) (data.getValue())).doubleValue();

        return sum;

    }

    // This Function cooperate the function min( ) , max( ) , mtm( ) 
    //	min( double , double ) or min( double , vector ) 
    //	max( double , double ) or max( double , vector )
    //	mtm( double , double , double ) or mtm( double , double , vector ) 
    //	flag = 1 : MAX
    //	flag = 2 : MTM
    //	flag = 3 : MIN

    private DATA MMM(String s, int flag) throws Exception {

        String ss = getWord(s);
        // 1. get the first parameter
        s = s.substring(ss.length());

        double l1 = 0, l2 = 0;
        DATA data;

        if (!isNumber(ss, '.')) {
            throw new Exception("310");
        }

        l1 = (new Double(ss)).doubleValue();

        ss = getWord(s);
        s = s.substring(ss.length());

        if (ss.charAt(0) != ',') {
            throw new Exception("320");
        }

        if (flag == 3) {
            ss = getWord(s);
            // if fuction is mtm ,get the second parameter
            s = s.substring(ss.length());

            if (!isNumber(ss, '.')) {
                throw new Exception("340");
            }

            l2 = (new Double(ss)).doubleValue();

            if (l1 > l2)
                throw new Exception("350");

            ss = getWord(s);
            s = s.substring(ss.length());

            if (ss.charAt(0) != ',') {
                throw new Exception("355");
            }

        }

        int i;
        double l;

        data = getExpValue(s, false);
        // get the second or third parameter

        if (data.getType().equals("03"))
        // calc the value by the data type
        {

            Vector v = new Vector();
            v = (Vector) data.getValue();

            int k = v.size();

            Double Field;

            for (i = 0; i < k; i++) {
                Field = (Double) (v.elementAt(i));
                l = Field.doubleValue();

                if (flag == 1) //MIN
                {
                    if (l > l1)
                        l = l1;
                } else if (flag == 2) //MAX
                {
                    if (l < l1)
                        l = l1;
                } else if (flag == 3) //MIN TO MAX 
                {
                    if (l < l1)
                        l = l1;
                    if (l > l2)
                        l = l2;
                }

                Field = new Double(l);

                v.setElementAt(Field, i);

            }

            data = new DATA("03", v);
        } else if (data.getType().equals("01")) {
            Double L = (Double) (data.getValue());

            l = L.doubleValue();

            if (flag == 1) //MIN
            {
                if (l > l1)
                    l = l1;
            } else if (flag == 2) //MAX
            {
                if (l < l1)
                    l = l1;
            } else if (flag == 3) //MIN TO MAX 
            {
                if (l < l1)
                    l = l1;
                if (l > l2)
                    l = l2;
            }

            data = new DATA("01", new Double(l));

        } else
            throw new Exception("360");

        return data;

    }

    private double getTransCnt() {
        return 1;
    }

    /* This is special for wangyi */
    private double GetWangYiFee() {
        double fee_amt;

        if (fTransAmt < 10000.00001)
            fee_amt = 0.004 * fTransAmt;
        else if (fTransAmt > 10000.00001 && fTransAmt < 50000.00001)
            fee_amt = 30;
        else if (fTransAmt > 50000.00001)
            fee_amt = 130;
        else
            return 0;

        return (fee_amt);
    }

    private DATA FLO(String s) throws Exception {
        int i, k;
        int deep = 0;
        char ch;
        DATA data;

        // seach how many couple ','
        for (k = 0, i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                deep++;
            } else if (ch == ')') {
                deep--;
            } else if (ch == ',') {
                if (deep == 0) {
                    k++;
                }
            }
        }

        // FLO should be the type :FLO(10,0.01,100,0.02,0.03)
        if (k % 2 == 1) {
            throw (new Exception("FLO005"));
        }

        k = k / 2;
        k += 1;

        double amt[] = new double[k];
        double ratio[] = new double[k];
        double ff;

        String ss;
        i = 0;
        //System.out.println("steps1" + s  + "k" + k) ;

        for (i = 0; i < k - 1; i++) {
            ss = getWord(s);
            if (ss.length() == 0)
                throw new Exception("FLO006");

            s = s.substring(ss.length());
            //System.out.println("ss" + ss ) ;

            if (!isNumber(ss, '.')) {
                throw new Exception("FLO010");
            }
            ff = (new Double(ss)).doubleValue();
            amt[i] = ff;

            ss = getWord(s);
            s = s.substring(ss.length());
            if (ss.charAt(0) != ',') {
                throw new Exception("FLO015");
            }

            ss = getFieldStr(s);
            if (ss == null)
                throw new Exception("FLO016");

            //System.out.println("ss1[" + ss + "]" ) ;
            //System.out.println( "fAmt[" + fTransAmt ) ; 

            data = getExpValue(ss, false);
            if (!data.getType().equals("01")) {
                //System.out.println("err steps1"  ) ;
                throw new Exception("FLO017");
            }
            ff = ((Double) data.getValue()).doubleValue();

            ratio[i] = ff;
            System.out.println("ff" + ff);

            s = s.substring(ss.length());
            if (s.length() < 1) {
                throw new Exception("FLO021");
            }

            ss = getWord(s);
            s = s.substring(ss.length());
            if (ss.charAt(0) != ',') {
                throw new Exception("FLO025");
            }
        }

        ss = getFieldStr(s);
        if (ss == null)
            throw new Exception("FLO026");

        data = getExpValue(ss, false);
        if (data.getType().equals("01"))
            ;
        else
            throw new Exception("FLO026");
        ff = ((Double) data.getValue()).doubleValue();

        ratio[k - 1] = ff;
        amt[k - 1] = amt[k - 2];

        double fee_amt = 0;
        boolean end_flag = false;

        for (i = 0; i < k - 1; i++) {
            if (fTransAmt < amt[i])
                break;
        }

        fee_amt = ratio[i];

        return new DATA("01", new Double(fee_amt));
    }

    // progression
    private DATA PGS(String s) throws Exception {
        double f_sumAmt = getSumAmt();
        int i, k;

        // seach how many couple ','
        for (k = 0, i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',')
                k++;
        }

        // PGS should be the type :PGS(10,0.01,100,0.02,0.03)
        if (k % 2 == 1) {
            throw (new Exception("PGS005"));
        }

        k = k / 2;
        k += 1;

        double amt[] = new double[k];
        double ratio[] = new double[k];
        double ff;

        String ss;
        i = 0;
        //System.out.println("steps1" + s  + "k" + k) ;

        for (i = 0; i < k - 1; i++) {
            ss = getWord(s);
            if (ss.length() == 0)
                throw new Exception("PGS006");

            s = s.substring(ss.length());
            //System.out.println("ss" + ss ) ;

            if (!isNumber(ss, '.')) {
                throw new Exception("PGS010");
            }
            ff = (new Double(ss)).doubleValue();
            amt[i] = ff;

            ss = getWord(s);
            s = s.substring(ss.length());
            if (ss.charAt(0) != ',') {
                throw new Exception("PGS015");
            }

            ss = getWord(s);
            if (ss.length() == 0)
                throw new Exception("PGS016");

            s = s.substring(ss.length());
            //System.out.println("ss" + ss ) ;

            if (!isNumber(ss, '.')) {
                throw new Exception("PGS020");
            }
            ff = (new Double(ss)).doubleValue();
            ratio[i] = ff;

            ss = getWord(s);
            s = s.substring(ss.length());
            if (ss.charAt(0) != ',') {
                throw new Exception("PGS025");
            }

        }

        ss = getWord(s);
        if (ss.length() == 0)
            throw new Exception("PGS026");

        if (!isNumber(ss, '.')) {
            throw new Exception("PGS027");
        }
        ff = (new Double(ss)).doubleValue();
        ratio[k - 1] = ff;
        amt[k - 1] = amt[k - 2];

        double fee_amt = 0;
        boolean end_flag = false;

        ff = amt[0];
        if (f_sumAmt - ff < 0.009) {
            end_flag = true;
            ff = f_sumAmt;
        }

        fee_amt = ff * ratio[0];
        f_sumAmt -= ff;

        if (!end_flag) {
            for (i = 1; i < k - 1; i++) {

                ff = amt[i] - amt[i - 1];
                if (ff < 0.009)
                    throw new Exception("PGS030");

                if (f_sumAmt - ff < 0.009) {
                    end_flag = true;
                    ff = f_sumAmt;
                }

                fee_amt += ff * ratio[i];

                // end
                if (end_flag)
                    break;

                f_sumAmt -= ff;
            }
        }

        if (!end_flag)
            fee_amt += f_sumAmt * ratio[k - 1];

        return new DATA("01", new Double(fee_amt));
    }

    private double getSumAmt() throws Exception {
        return ((new Double(TransAmt)).doubleValue());
    }

    // 
    private Vector getTransAmt(String amtCond) {

        Vector v0 = new Vector();
        Double L;

        L = new Double(TransAmt);
        v0.addElement(L);
        return v0;
    }

    // get the date that after the day
    // must satisfy EndDate >= StartDate
    // if EndDate < StartDate , return -1
    public static int getDayBetween(String StartDate, String EndDate) {
        if ((StartDate.length() != 8) || (EndDate.length() != 8))
            return -1;

        int y1, y2, m1, m2, d1, d2, day;
        int i, j, k;

        y1 = Integer.parseInt(StartDate.substring(0, 4));
        y2 = Integer.parseInt(EndDate.substring(0, 4));

        m1 = Integer.parseInt(StartDate.substring(4, 6));
        m2 = Integer.parseInt(EndDate.substring(4, 6));

        d1 = Integer.parseInt(StartDate.substring(6, 8));
        d2 = Integer.parseInt(EndDate.substring(6, 8));

        if (y1 > y2)
            return -1;
        else if (y1 == y2) {
            if (m1 > m2)
                return -1;
            else if (m1 == m2) {
                if (d1 > d2)
                    return -1;
            }
        }

        if (y1 == y2 && m1 == m2)
            return (d2 - d1);

        day = haveDay(y1, m1) - d1;
        m1++;

        while (true) {
            if (m1 > 12) {
                m1 = 1;
                y1++;
            }

            if (y1 == y2 && m1 == m2) {
                return (day + d2);
            }

            day += haveDay(y1, m1);
            m1++;

        }

    }

    // get the date that after the day
    public static String getAfterDate(String date, int day) {
        if (date.length() != 8)
            return null;

        String sYear = date.substring(0, 4);
        int iYear = Integer.parseInt(sYear);

        String sMonth = date.substring(4, 6);
        int iMonth = Integer.parseInt(sMonth);

        String sDay = date.substring(6, 8);
        int iDay = Integer.parseInt(sDay);

        if (iMonth > 12 || iDay > 31) {
            return null;
        }

        iDay += day;

        int haveday;

        while (true) {
            haveday = haveDay(iYear, iMonth);

            if (haveday < 0)
                return null;

            if (iDay <= haveday)
                break;

            iMonth++;
            iDay -= haveday;

            if (iMonth > 12) {
                iMonth = 1;
                iYear++;
            }

        }

        sYear = "" + iYear;
        if (iMonth < 10)
            sMonth = "0" + iMonth;
        else
            sMonth = "" + iMonth;

        if (iDay < 10)
            sDay = "0" + iDay;
        else
            sDay = "" + iDay;

        return (sYear + sMonth + sDay);

    }

    // how many days one month have ?
    public static int haveDay(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
                    return 29;
                else
                    return 28;
        }

        return -1;

    }

    public static String trimSpace(String s) {
        int i = 0, k = 0;
        byte bData[] = s.getBytes();
        byte ch;
        for (i = 0; i < bData.length; i++) {
            ch = bData[i];
            if (ch != (byte) (' ')) {
                bData[k] = ch;
                k++;
            }
        }

        return (new String(bData, 0, k));

    }

    //in : (90,(100+10),AMT*0.4)+40
    //out: (90,(100+10),AMT*0.4)
    public static String getFunStr(String s) {
        int i, len;
        len = s.length();

        int deep = 0;
        String sFun = "";
        char ch;
        char ch0[] = new char[1];

        if (s.length() == 0)
            return null;

        if (s.charAt(0) != '(') {
            return null;
        }

        for (i = 0; i < len; i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                deep++;

            } else if (ch == ')') {
                deep--;
                if (deep == 0)
                    break;
            }
            ch0[0] = ch;
            sFun += new String(ch0);

        }

        if (deep != 0)
            return null;

        return (sFun + ")");

    }

    //in : MIN(MAX(10,2)*2,AMT*10+20),(AMT*0.4)+40
    //out: MIN(MAX(10,2)*2,AMT*10+20)
    public static String getFieldStr(String s) {
        char[] bData = new char[200];
        int i, len, len0;
        len = s.length();

        int deep = 0;
        char ch;

        if (len == 0)
            return null;

        len0 = 0;

        for (i = 0; i < len; i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                deep++;
            } else if (ch == ')') {
                deep--;
            } else if (ch == ',') {
                if (deep == 0)
                    break;
            }
            bData[len0] = ch;
            len0++;
        }

        if (deep != 0)
            return null;

        return (new String(bData, 0, len0));
    }

    public static boolean isNumber(String s, char flag)
    // flag -- '0' : '0' -- '9'
    // flag -- '.' : '0' -- '9' or '.'
    {
        int i, len;

        len = s.length();

        for (i = 0; i < len; i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                if (s.charAt(i) == '.') {
                    if (flag == '.')
                        continue;
                    else
                        return false;
                } else
                    return false;
            }
        }
        return true;

    }

    public static int getCharType(char ch) {
        int flag = -1;

        if ((ch <= '9' && ch >= '0') || ch == '.' || ch == '[' || ch == ']')
            flag = 1;
        else if (ch <= 'Z' && ch >= 'A')
            flag = 2;
        else if (ch == '_')
            flag = 2;
        else if (ch == '+' || ch == '*')
            flag = 3;
        else if (ch == '(')
            flag = 4;
        else if (ch == ')')
            flag = 5;
        else if (ch == ',')
            flag = 6;
        return flag;
    }

    public static String getWord(String s) {
        String word;
        int i, len;
        char ch;

        len = s.length();

        if (len == 0)
            return "";

        int flag1 = getCharType(s.charAt(0));
        int flag2 = 0;

        for (i = 1; i < len; i++) {
            ch = s.charAt(i);

            flag2 = getCharType(ch);

            if (flag2 != flag1) {
                // permit suck as word :tmp1
                if ((flag1 != 2) || (flag2 != 1))
                    return (s.substring(0, i));
            }

        }

        return s;

    }

}
