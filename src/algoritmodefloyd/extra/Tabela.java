/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmodefloyd.extra;

/**
 *
 * @author Lucas
 */
public class Tabela {
    
    String c1,c2,c3,c4,c5,c6,c7,c8,c9,c0,id;

    public Tabela(String id) {
        this.id = id;
    }

    

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public String getC5() {
        return c5;
    }

    public void setC5(String c5) {
        this.c5 = c5;
    }

    public String getC6() {
        return c6;
    }

    public void setC6(String c6) {
        this.c6 = c6;
    }

    public String getC7() {
        return c7;
    }

    public void setC7(String c7) {
        this.c7 = c7;
    }

    public String getC8() {
        return c8;
    }

    public void setC8(String c8) {
        this.c8 = c8;
    }

    public String getC9() {
        return c9;
    }

    public void setC9(String c9) {
        this.c9 = c9;
    }

    public String getC0() {
        return c0;
    }

    public void setC0(String c0) {
        this.c0 = c0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void insere(int pos, String texto)
    {
        if(pos==0)
        {
            c0 = texto+"";
        }else if(pos ==1)
        {
            c1 = texto+"";
        }else if(pos ==2)
        {
            c2 = texto+"";
        }else if(pos ==3)
        {
            c3 = texto+"";
        }else if(pos ==4)
        {
            c4 = texto+"";
        }else if(pos ==5)
        {
            c5 = texto+"";
        }else if(pos ==6)
        {
            c6 = texto+"";
        }else if(pos ==7)
        {
            c7 = texto+"";
        }else if(pos ==8)
        {
            c8 = texto+"";
        }else if(pos ==9)
        {
            c9 = texto+"";
        }

    }
}
