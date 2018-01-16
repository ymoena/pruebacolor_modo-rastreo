package com.example.cesar.pruebacolor;

import java.util.Date;

/**
 * Created by Diego on 16-03-2017.
 */

public class Coordenadas {
    int Robot1X;
    int Robot1Y;
    int Robot2X;
    int Robot2Y;
    int Robot3X;
    int Robot3Y;
    Date Fecha;

    Coordenadas() {

        Date fechaActual = new java.util.Date();
        /*Robot1X = 200;
        Robot3X = 600;
        Robot2X = 400;
        Robot1Y = 480;
        Robot3Y = 460;
        Robot2Y = 430;*/
        Fecha = fechaActual;

    }

    void UpdateR1(int Robo1X,int Robo1Y){
        Robot1X = Robo1X;
        Robot1Y = Robo1Y;
    }
    void UpdateR2(int Robo2X,int Robo2Y){
        Robot2X = Robo2X;
        Robot2Y = Robo2Y;
    }
    void UpdateR3(int Robo3X,int Robo3Y){
        Robot3X = Robo3X;
        Robot3Y = Robo3Y;
    }
}