package com.example.demoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class RenameController {

    @FXML
    private TextField currentName;

    @FXML
    private TextField newName;

    @FXML
    private TextField password;

    @FXML
    private TextField rebootDelay;

    @FXML
    private Button renameButton;

    @FXML
    private Button pingButton;

    private void initialize() {

        //Add action listeners to the buttons
        renameButton.setOnAction(event -> renameComputer());
        pingButton.setOnAction(event -> pingComputer());

    }

    private void renameComputer() {

        // Get the input values from the text fields
        String oldComputerName = currentName.getText();
        String newComputerName = newName.getText();
        String domainPassword = password.getText();
        int delay = Integer.parseInt(rebootDelay.getText());

        // Get the current username
        String domainUsername = System.getProperty("user.name");

        // PowerShell command
        String[] command = {
                "powershell.exe",
                "-Command",
                "$credential = New-Object -TypeName System.Management.Automation.PSCredential - ArgumentList @('" + domainUsername + "', (ConvertTo-SecureString -String '" + domainPassword + "' - AsPlainText -Force));",
                "Rename-Computer -ComputerName '" + oldComputerName + "' -NewName '" + newComputerName + "' -DomainCredential $credential -Force -Restart"
        };

        try {
            // Execute the PowerShell command
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            process.waitFor();

            // Wait for reboot delay
            if (delay > 60){
                delay = 60;
            }
            Thread.sleep(delay * 10);

            //Optional extra code

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void pingComputer() {
        // Get the input value from the text field
        String computerName = currentName.getText();

        try {
            // Build the ping command
            String[] command = {"cmd", "/c", "start", "ping", "-t", currentName.getText()};

            // Execute the command
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.start();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions here
        }
    }






}
