package xxl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import xxl.exceptions.ImportFileException;
import xxl.exceptions.MissingFileAssociationException;
import xxl.exceptions.UnavailableFileException;
import xxl.exceptions.UnrecognizedEntryException;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = new Spreadsheet();

    /** The calculator. */
    private String _filename = "";
    
    
    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            _spreadsheet = (Spreadsheet) ois.readObject();
			_filename = filename;
		} catch (IOException e) {
            throw new UnavailableFileException(filename);
		} catch (ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
		}
	}
    
    /**
     * Saves the serialized application's state into the file associated to the current spreadsheet.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
     * @throws MissingFileAssociationException if the current spreadsheet does not have a file.
     * @throws IOException if there is some error while serializing the state of the spreadsheet to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        
        if (_filename == null || _filename.isBlank()) {
            throw new MissingFileAssociationException();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_spreadsheet);
            _spreadsheet.setChanged(false);
        }
    }
    
    /**
     * Saves the serialized application's state into the specified file. The current network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        _filename = filename;
		save();
    }
    
    
    /**
     * Read text input file and create domain entities..
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException {
        try {
            _spreadsheet.importFile(filename);
            // FIXME open import file and feed entries to new spreadsheet (in a cycle)
            //       each entry is inserted with:
	        _spreadsheet.insertContents("FIXME", "FIXME");
            // ....
        } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(filename, e);
        }
    }
    
    /**
     * @return filename
     */
    public String getFilename() {
        return _filename;
    }
    
    /**
     * @param filename
     */
    public void setFilename(String filename) {
        _filename = filename;
    }
    
   /**
   * @return spreadsheet
   */
    public Spreadsheet getSpreadsheet() {
        return _spreadsheet;
    }

   /**
   * @return changed?
   */
  public boolean changed() {
    return _spreadsheet.hasChanged();
  }

}
