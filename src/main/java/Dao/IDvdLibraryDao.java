package Dao;

import Dto.DVD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IDvdLibraryDao {
    void addDVD(DVD dvd) throws IOException;

    boolean deleteDVD(String title) throws IOException;

    boolean updateDVD(DVD dvd) throws IOException;

    List<DVD> getAllDVDs() throws FileNotFoundException;

    DVD getDVD(String title);
}
