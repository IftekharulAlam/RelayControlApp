/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relaycontrolapp;

import gnu.io.CommPortIdentifier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IftekharulAlam
 */
public class HomePageController implements Initializable {

    String myPort = "";
    ArduinoSerial sensorData;

    //= new ArduinoSerial(myPort);
    @FXML
    private ComboBox serialPortListCombo;
    @FXML
    private Button connectButton;
    @FXML
    private Button disconnectButton;
    @FXML
    private AnchorPane mywindow;
    Thread t;
    @FXML
    private Button forwardButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Enumeration portList = null;

        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {

            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();

            serialPortListCombo.getItems().add(portId.getName());

        }

    }


    @FXML
    private void connectButtonclick(ActionEvent event) {

        myPort = serialPortListCombo.getValue().toString();

        if (myPort.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Not valid");
        } else {
            sensorData = new ArduinoSerial(myPort);
            sensorData.initialize();
            t = new Thread() {
                @Override
                public void run() {

                    while (true) {

                        try {
                            Thread.sleep(1000);

                            String for1 = "S";
                            sensorData.write(for1);
                            //-------
                        } catch (Exception e) {
                            //System.out.println(e);
                        }

                    }

                }

            };

            t.start();
        }
    }

    @FXML
    private void disconnectButtonclick(ActionEvent event) {
        sensorData.close();
        t.stop();

    }

    @FXML
    private void ForwardOnButtonClick(ActionEvent event) {
        try {
            String for1 = "F";
            sensorData.write(for1);
        } catch (Exception e) {
        }


    }
    
  
    

    
    

    @FXML
    private void BackwardOnButtonClick(ActionEvent event) {
        try {
            String for1 = "B";
            sensorData.write(for1);
        } catch (Exception e) {
        }

    }

    @FXML
    private void LeftOnButtonClick(ActionEvent event) {
        try {
            String for1 = "L";
            sensorData.write(for1);
        } catch (Exception e) {
        }
    }

    @FXML
    private void RightOnButtonClick(ActionEvent event) {
        try {
            String for1 = "R";
            sensorData.write(for1);
        } catch (Exception e) {
        }
    }

    @FXML
    private void ScanButtonClicked(ActionEvent event) {
    }

}
