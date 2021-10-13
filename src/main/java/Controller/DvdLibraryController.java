package Controller;

import Dao.IDvdLibraryDao;
import Dto.DVD;
import UI.IUserInterface;

import java.io.IOException;
import java.util.Locale;

public class DvdLibraryController {

    private IUserInterface userInterface;
    private IDvdLibraryDao dvdLibraryDao;

    public DvdLibraryController(IUserInterface userInterface, IDvdLibraryDao dvdLibraryDao) {
        this.userInterface = userInterface;
        this.dvdLibraryDao = dvdLibraryDao;
    }

    public void run(){
        boolean exit = false;
            while(!exit) {
                try {
                    String selectedMenu = userInterface.showMainMenu();
                    DVD dvd;
                    String title = "";

                    switch (selectedMenu.toUpperCase()) {
                        case "A":
                            dvd = userInterface.getDVDInfo();
                            dvdLibraryDao.addDVD(dvd);
                            break;
                        case "E":
                            dvd = userInterface.getDVDInfo();
                            dvdLibraryDao.updateDVD(dvd);
                            break;
                        case "D":
                            title = userInterface.searchDvd();
                            dvdLibraryDao.deleteDVD(title);
                            break;
                        case "L":
                         userInterface.showAllDvdList( dvdLibraryDao.getAllDVDs());
                            break;
                        case "S":
                            title = userInterface.searchDvd();
                            userInterface.showDvd(dvdLibraryDao.getDVD(title));
                            break;
                        case "Q":
                            exit = true;
                            break;
                        default:
                            userInterface.showMessage("Invalid Selection!");
                    }
                } catch (Exception e) {
                    userInterface.showMessage(e.getMessage());
                }
            }
        }
}
