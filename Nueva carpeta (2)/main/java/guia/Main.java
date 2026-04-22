package guia;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        new Utils();

        Main m = new Main();

        try {
            int op;

            do {
                Utils.getOut().println("1-15 | 0 salir");
                op = Integer.parseInt(Utils.getLector().readLine());

                switch (op) {
                    case 1: m.Guia1_Ej1(); break;
                    case 2: m.Guia1_Ej2(); break;
                    case 3: m.Guia1_Ej3(); break;
                    case 4: m.Guia1_Ej4(); break;
                    case 5: m.Guia1_Ej5(); break;
                    case 6: m.Guia1_Ej6(); break;
                    case 7: m.Guia1_Ej7(); break;
                    case 8: m.Guia1_Ej8(); break;
                    case 9: m.Guia1_Ej9(); break;
                    case 10: m.Guia1_Ej10(); break;
                    case 11: m.Guia1_Ej11(); break;
                    case 12: m.Guia1_Ej12(); break;
                    case 13: m.Guia1_Ej13(); break;
                    case 14: m.Guia1_Ej14(); break;
                    case 15: m.Guia1_Ej15(); break;
                }

            } while (op != 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Guia1_Ej1(){}
    public void Guia1_Ej2(){}
    public void Guia1_Ej3(){}
    public void Guia1_Ej4(){}
    public void Guia1_Ej5(){}
    public void Guia1_Ej6(){}
    public void Guia1_Ej7(){}
    public void Guia1_Ej8(){}
    public void Guia1_Ej9(){}
    public void Guia1_Ej10(){}
    public void Guia1_Ej11(){}
    public void Guia1_Ej12(){}
    public void Guia1_Ej13(){}
    public void Guia1_Ej14(){}
    public void Guia1_Ej15(){}
}