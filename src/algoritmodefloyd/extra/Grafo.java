/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmodefloyd.extra;

import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Grafo {
    private int [][] md,ms;
    private int qtdeV;
    private static String erro;
    private static int max = (Integer.MAX_VALUE - 1) / 2;

    public Grafo(int qtdeV) {
        this.qtdeV = qtdeV;
        md = new int[qtdeV][qtdeV];
        ms = new int[qtdeV][qtdeV];
        for (int i = 0; i < qtdeV; i++) 
            for (int j = 0; j < qtdeV; j++) {
                ms[i][j] = 0;
            }
    }
    public static Grafo leGrafo(String caminho)
    {
        Grafo g,b;
        try {            
            RandomAccessFile raf = new RandomAccessFile(caminho, "r");
            String str = raf.readLine();
            String[] verts;
            int q = str.split(" ").length; 
            g = new Grafo(q);
            
            for (int i = 0; i < q; i++) { 
                verts = str.split(" ");
                for(int j = 0; j < q; j++)
                    if (verts[j].equals("-"))
                        g.add(i, j, max);
                    else
                        g.add(i, j, Integer.parseInt(verts[j]));
                
                str = raf.readLine();
            }
  
        } catch (Exception e) {
            erro = e.toString();
            g=null;
        }
        return g;
    }
    public void exibeMd()
    {
        System.out.println("Matriz distancia");
        for (int i = 0; i < md.length; i++)
        {
            for (int j = 0; j < md.length; j++)
            {
                System.out.print(md[i][j] + ", ");
            }
            System.out.println("");
        }
    }
    public void exibeMs()
    {
        System.out.println("Matriz solução");
        for (int i = 0; i < ms.length; i++)
        {
            for (int j = 0; j < ms.length; j++)
            {
                System.out.print(ms[i][j] + ", ");
            }
            System.out.println("");
        }
    }
    public void floyd()
    {
        for (int k = 0; k < qtdeV; k++) 
        {
            for (int i = 0; i < qtdeV; ++i)
            {

                    for (int j = 0, v, x; j < qtdeV; ++j)
                    {
                        if (i!=j && i != k && j != k) 
                        {
                            
                            v = md[i][k] + md[k][j];
                            x = md[i][j];
                            if (v < x)
                            {
                                md[i][j] = v;
                                ms[i][j] = k+1;
                            }
                        }
                    }

            }
            /*System.out.println("------------------------------------------------------");
            exibeMd();*/
        }
    }

    public static String getErro() {
        return erro;
    }
    private void add(int i, int j, int v)
    {
        md[i][j] = v;
    }

    public int getQtdeV() {
        return qtdeV;
    }
    public int[][] getMatrizDistancia()
    {
        return md.clone();
    }

    public int[][] getMatrizSolucao()
    {
        return ms.clone();
    }
    
    public ArrayList<Integer> getCaminho(int v1, int v2)
    {
        ArrayList<Integer> caminho = new ArrayList<>();
        if (md[v1][v2] < max)
        {
            //System.out.println("Caminho "+(v1+1)+" -> "+(1+v2));
            gerarCaminho(caminho, v1, v2);
        }
        return caminho;
    }
    
    private void gerarCaminho(ArrayList<Integer> caminho, int v1, int v2)
    {
        //System.out.print(v1+1+" -> ");
        caminho.add(v1+1);
        if (ms[v1][v2] != 0)
        {
            gerarCaminho(caminho, ms[v1][v2]-1, v2);
        } else
        {
            //System.out.println(v2+1+"");
            caminho.add(v2+1);
        }
    }
}
