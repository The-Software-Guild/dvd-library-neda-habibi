package UI;

import Dto.DVD;

import java.util.List;

public interface IUserInterface {

    String showMainMenu();

    void showMessage(String message);

    DVD getDVDInfo();

    void showAllDvdList(List<DVD> DVDList);

    public String searchDvd();

    void showDvd(DVD dvd);
}
