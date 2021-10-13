package Dao;

import Dto.DVD;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DvdLibraryDao implements IDvdLibraryDao{

    private static final String FILE_NAME = "dvdLibrary.txt";
    private static final String SEPARATOR = "::";
    private List<DVD> dvds = new ArrayList<DVD>();

    @Override
    public void addDVD(DVD dvd) throws IOException {
        dvds.add(dvd);
        writeToFile();
    }

    @Override
    public boolean deleteDVD(String title) throws IOException {
        boolean deleted = dvds.remove(getDVD(title));

        if(!deleted){
            return  false;
        }

        writeToFile();
        return true;
    }

    @Override
    public boolean updateDVD(DVD dvd) throws IOException {
        boolean found = dvds.remove(getDVD(dvd.getTitle()));

        if(!found){
            return  false;
        }

        dvds.add(dvd);
        writeToFile();
        return true;
    }

    @Override
    public List<DVD> getAllDVDs() throws FileNotFoundException {
        readAll();
        return dvds;
    }

    @Override
    public DVD getDVD(String title) {
        return dvds.stream().filter(p -> p.getTitle().equals(title)).findFirst().orElse(null);
    }

    private void writeToFile() throws IOException {
        PrintWriter out;

        out = new PrintWriter(new FileWriter(FILE_NAME));

        for (DVD currentDVD : dvds) {
            String serializedObject = marshall(currentDVD);
            out.println(serializedObject);
            out.flush();
        }

        out.close();
    }

    private void readAll() throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));
        dvds.clear();
        while (scanner.hasNextLine()) {
            String serializedDvd = scanner.nextLine();
            DVD dvd = unmarshall(serializedDvd);
            dvds.add(dvd);
        }

        scanner.close();
    }

    private String marshall(DVD dvd){
        String serializedObject = dvd.getTitle() + SEPARATOR;
        serializedObject += dvd.getUserRating() + SEPARATOR;
        serializedObject += dvd.getRating() + SEPARATOR;
        serializedObject += dvd.getStudio() + SEPARATOR;
        serializedObject += dvd.getDirectorName() + SEPARATOR;
        serializedObject += dvd.getReleaseDate();

        return serializedObject;
    }

    private DVD unmarshall(String serializedDvd){
        String[] splitedDvd = serializedDvd.split(SEPARATOR);
        String title = splitedDvd[0];
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setUserRating(splitedDvd[1]);
        dvdFromFile.setRating(splitedDvd[2]);
        dvdFromFile.setStudio(splitedDvd[3]);
        dvdFromFile.setDirectorName(splitedDvd[4]);
        dvdFromFile.setReleaseDate(LocalDate.parse(splitedDvd[5]));

        return dvdFromFile;
    }
}
