package com.example.severalactivities;

import java.util.Observable;

public class BoxContainer extends Observable {
    private static final int MAX_GRID=9;
    private int i;
    private int roundCount = 0;

    private Box[] boxes;

    private boolean win;
    private boolean draw;
    private boolean reset;

    private char winner;

    public BoxContainer(){

        boxes = new Box[9];
        for(i=0; i<MAX_GRID; i++){
            boxes[i] = new Box();
        }

        resetState();
    }


    public void setSign(int index){

        if(!boxes[index].isFilled()) {
            if (whoTurn()) {
                boxes[index].setSign('X');
            } else if (!whoTurn()) {
                boxes[index].setSign('O');
            }
            boxes[index].setFilled();
            roundOver();
        }
    }

    public void resetGame(){
        reset = true;
        setChanged();
        notifyObservers();

        resetState();
        setChanged();
        notifyObservers();

    }

    public void resetState(){
        for(i = 0; i<MAX_GRID; i++){
            boxes[i].resetBox();
        }
        roundCount = 0;
        win = false;
        draw = false;
        reset = false;
    }

    public void roundOver(){
        roundCount++;

        setChanged();
        notifyObservers();

        if(checkWin() || checkDraw()){
            resetGame();
        }
    }

    public boolean whoTurn(){
        if(roundCount % 2 == 0){
            return true;
        } else return false;
    }

    public Box getBox(int index){
        return boxes[index];
    }

    public boolean checkWin(){
        String winString;

        for(i=0; i<8; i++){

            winString ="";
            switch(i){
                case 0:
                    winString = Character.toString(boxes[0].getSign()) + Character.toString(boxes[1].getSign()) + Character.toString(boxes[2].getSign());
                    break;
                case 1:
                    winString = Character.toString(boxes[3].getSign()) + Character.toString(boxes[4].getSign()) + Character.toString(boxes[5].getSign());
                    break;
                case 2:
                    winString = Character.toString(boxes[6].getSign()) + Character.toString(boxes[7].getSign()) + Character.toString(boxes[8].getSign());
                    break;
                case 3:
                    winString = Character.toString(boxes[0].getSign()) + Character.toString(boxes[3].getSign()) + Character.toString(boxes[6].getSign());
                    break;
                case 4:
                    winString = Character.toString(boxes[1].getSign()) + Character.toString(boxes[4].getSign()) + Character.toString(boxes[7].getSign());
                    break;
                case 5:
                    winString = Character.toString(boxes[2].getSign()) + Character.toString(boxes[5].getSign()) + Character.toString(boxes[8].getSign());
                    break;
                case 6:
                    winString = Character.toString(boxes[0].getSign()) + Character.toString(boxes[4].getSign()) + Character.toString(boxes[8].getSign());
                    break;
                case 7:
                    winString = Character.toString(boxes[2].getSign()) + Character.toString(boxes[4].getSign()) + Character.toString(boxes[6].getSign());
                    break;
            }

            if(winString.equals("XXX")){
                win = true;
                winner = 'X';
                return true;
            } else if(winString.equals("OOO")){
                win = true;
                winner = 'O';
                return true;
            }
        }
        return false;
    }

    public boolean checkDraw(){
        if(roundCount >= 9){
            draw = true;
            return true;
        } else return false;
    }

    public boolean isDraw() {
        return draw;
    }

    public boolean isReset() {
        return reset;
    }

    public boolean isWin() {
        return win;
    }

    public char getWinner() {
        return winner;
    }
}
