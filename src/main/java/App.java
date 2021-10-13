import Controller.DvdLibraryController;
import Dao.DvdLibraryDao;
import UI.UserConsoleInterface;

public class App {

    public static void main(String[] args) {
        DvdLibraryController controller = new DvdLibraryController(new UserConsoleInterface(), new DvdLibraryDao());
        controller.run();
    }
}
