package UI;

import Dto.DVD;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserConsoleInterface implements IUserInterface{

    private Scanner console = new Scanner(System.in);

    @Override
    public void showAllDvdList(List<DVD> DVDList){
        for (DVD currentDVD : DVDList) {
            String dvdInfo = String.format("Title: %s Studio: %s Rating: %s Release Date: %s Director: %s User Rating: %s",
                    currentDVD.getTitle(),
                    currentDVD.getStudio(),
                    currentDVD.getRating(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getDirectorName(),
                    currentDVD.getUserRating());
            System.out.println(dvdInfo);
        }
        System.out.println("");
    }

    @Override
    public String searchDvd(String title){
        return readString("Enter the Title:");
    }

    @Override
    public void showDvd(DVD dvd) {
        if (dvd != null) {
            System.out.println(dvd.getTitle());
            System.out.println(dvd.getStudio());
            System.out.println(dvd.getRating());
            System.out.println(dvd.getReleaseDate().toString());
            System.out.println(dvd.getDirectorName());
            System.out.println(dvd.getUserRating());
        } else {
            System.out.println("DVD not found!");
        }

        System.out.println("");
    }

    @Override
    public DVD getDVDInfo() {
        String title = readString("Enter DVD Title:");
        String studio = readString("Enter Studio Name:");
        String rating = readString("Enter MPAA Rating:");
        String directorName = readString("Enter the Director Name:");
        String userRating = readString("Enter your rating:");
        LocalDate date = readDate("Enter Release Date (the format should be DD/MM/YYYY):");

        DVD currentDVD = new DVD(title);
        currentDVD.setRating(rating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        currentDVD.setReleaseDate(date);
        return currentDVD;
    }

    @Override
    public String showMainMenu(){
        showMessage("Main Menu:");
        showMessage("(A)dd DVD");
        showMessage("(D)elete DVD");
        showMessage("(E)dit DVD");
        showMessage("(L)ist DVDs");
        showMessage("(S)earch DVD");
        showMessage("(Q)uit");

        return readString("Select With the first Character.");
    }

    @Override
    public void showMessage(String message){
        System.out.println(message);
    }

    private String readString(String message) {
        System.out.println(message);
        return console.nextLine();
    }

    private LocalDate readDate(String msgPrompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean invalidInput = true;
        LocalDate releaseDate = null;
        do{
            try {
                String date = readString(msgPrompt);
                releaseDate = LocalDate.parse(date, formatter);
                invalidInput = false;

            } catch (DateTimeException e) {
                showMessage("Input error. Please try again.");
            }

        } while(invalidInput);

        return releaseDate;
    }
}
