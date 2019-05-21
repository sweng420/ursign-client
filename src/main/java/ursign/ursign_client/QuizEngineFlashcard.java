package ursign.ursign_client;

public class QuizEngineFlashcard {

    private static final int BOXES = 5;

    private String string;
    private Integer integer;
    // BOX THREE IS EASIEST, DECREMENT BOX WHEN PLAYER GETS QUESTION RIGHT
    private Integer box = BOXES;

    public QuizEngineFlashcard(String string, Integer integer) {
        this.string = string;
        this.integer = integer;
    }

    public QuizEngineFlashcard(String string, Integer integer, Integer box) {
        this.string = string;
        this.integer = integer;
        if (box < 1){
            this.box = 1;
        } else if (box > BOXES) {
            this.box = BOXES;
        } else {
            this.box = box;
        }
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "string='" + string + '\'' +
                ", integer=" + integer +
                ", box=" + (BOXES - box) +
                '}';
    }

    public String getString() {
        return string;
    }

    public Integer getInteger() {
        return integer;
    }

    public Integer getBox() {
        return box;
    }

    public void incBox() {
        if (this.box > 1) { this.box--; };
    }

    public void resetBox(){
        this.box = BOXES;
    }
    
    public boolean checkStringAnswer(String answer) {
    	if (answer.equals(this.string)) {
            // put the answer in the next box
            this.incBox();
            return true;
        } else {
            // return the answer to the first box
            this.resetBox();
            return false;
        }
    }
}