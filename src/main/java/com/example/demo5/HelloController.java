package com.example.demo5;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.Math;
public class HelloController {

    //elements
    @FXML
    private Label Guesses;
    @FXML
    private String Guessed;
    @FXML
    private TextField GuessBox;
    @FXML
    private TextField warning;
    @FXML
    private Label Word;

    @FXML
    private ImageView man;
    //variables

   // @FXML
    //private static String word = "JAVAFX";

    @FXML
    private static String wordlist [] = new String[]{"DURING", "NUMBER", "SYSTEM", "STATES", "BEFORE","LITTLE","PEOPLE","SHOULD",
    "SOCIAL", "WITHIN","RHYTHM","ZEPHYR","AVENUE","GUITAR","SNAPPY","SULFUR","DENGUE","QUORUM","PYTHON","ABATED","EDITOR","HYENAS",
    "JASMIN","ENMITY"};
    @FXML
    private static String word = wordlist[(int)(Math.random()*(wordlist.length))];

    @FXML
    private static String reveal=word;
//    @FXML
//    private int wordlen = word.length();
//    @FXML
//    private int correct = 0;
    @FXML
    private static int lives_lost = 0;
    @FXML
    private static int letters_left = 6;
    @FXML
    private static String attempts = " ";
    @FXML
    private final String[] img = new String[]{"1.png", "2.png", "3.png", "4.png", "5.png", "6.png"};

    //to reset variables
    @FXML
    public void reset(){
        reveal = word;
        word = wordlist[(int)(Math.random()*(wordlist.length))];
//        wordlen = word.length();
//        correct = 0;
        letters_left = 6;
        lives_lost = 0;
        attempts = " ";
        Guesses.setText(" ");
    }

    public static String show_ans(){
        return reveal;
    }
    @FXML
    public void update_warning(){
        warning.setText("Letters left = " + String.valueOf(letters_left) +"  Lives left = " + String.valueOf(5-lives_lost));
    }

    @FXML
    public void onSubmitButtonClick() throws IOException {
        Guessed = Guesses.getText();
        // saving their guess
        String Attempt = GuessBox.getText().toUpperCase();
        // validations
        int len = Attempt.length();
        if(len!=1){
            warning.setText("Enter 1 letter");
        }
        else if(attempts.contains(Attempt)){
            warning.setText("Enter new letter");
        }
            else if(! ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(Attempt))){
                    warning.setText("Enter a letter");
                }
                else{
                    GuessBox.setText("");
                    attempts = attempts + Attempt;
                    int pos = word.indexOf(Attempt);

                    // if incorrect guess
                    if(pos<0){
                        // showing in guessed box
                        Guessed = Guessed + " " + Attempt;
                        Guesses.setText(Guessed);


                        //change in lives
                        lives_lost+=1;
                        update_warning();
                        System.out.println("Lives lost = " + lives_lost);
                        String man_stage = img[lives_lost];
                        Path imageFile = Paths.get("C:\\Users\\ansh sancheti\\IdeaProjects\\demo5\\src\\main\\resources\\com\\example\\demo5\\"+man_stage);
                        man.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));

                        // if all lives lost
                        if(lives_lost == 5){
                            System.out.println("You loose");
                            // displaying correct answer
                            warning.setText("The word was: " + word);
                            reset();

//                            try {
//                                //resetting
//                                warning.setText("The word was: " + word);
//                                reset();
//                                Thread.sleep(10 * 1000);
//                                System.out.println("123");
//                            } catch (InterruptedException ie) {
//                                Thread.currentThread().interrupt();
//                            }



                            // routing to loser page
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loser.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                            Stage stageL = new Stage();
                            stageL.setResizable(false);
                            stageL.setTitle("YOU LOSE!!");
                            stageL.setScene(scene);
                            stageL.show();
                        }


                    }
                    // if attempt is there in the word
                    else{
                        while(pos>=0) {
                            letters_left -=1;
                            update_warning();
                            //displaying it
                            String temp = Word.getText();
                            char letter = Attempt.charAt(0);
                            temp = temp.substring(0, pos) + letter
                                    + temp.substring(pos + 1);
                            Word.setText(temp);

                            //checking for more occurences
                            pos = word.indexOf(Attempt, pos + 1);

                             //if all letters guessed
                            if(letters_left==0){
                                System.out.println("You Win");
                                reset();
                                // routing to winner page
                                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("winner.fxml"));
                                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                                Stage stage = new Stage();
                                stage.setResizable(false);
                                stage.setTitle("Winner!!");
                                stage.setScene(scene);
                                stage.show();
                            }
                        }
                    }
                }
    }
}