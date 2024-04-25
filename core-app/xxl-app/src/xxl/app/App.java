package xxl.app;

import pt.tecnico.uilib.Dialog;
import pt.tecnico.uilib.menus.Menu;
import xxl.exceptions.ImportFileException;
import xxl.Calculator;

import java.io.IOException;

/**
 * Class that represents the spreadsheet's textual interface.
 */
public class App {

    public static void main(String[] args) {
        try (var ui = Dialog.UI) {

            Calculator calculator = new Calculator();
            String datafile = System.getProperty("import");

            if (datafile != null) {
                try {
                    calculator.importFile(datafile);
                } catch (ImportFileException e) {
                    // Should not happen!
                    e.printStackTrace();
                }
            }

            (new xxl.app.main.Menu(calculator)).open();
        }
    }

}
