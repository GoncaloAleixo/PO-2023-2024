package xxl;

// FIXME import classes
import xxl.exceptions.UnrecognizedEntryException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

	/** Spreadsheet object has been changed. */
	private boolean _changed = false;

    // FIXME define attributes
    // FIXME define contructor(s)
    // FIXME define methods

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        //FIXME implement method
    }

    /**
	 * Read text input file and create corresponding domain entities.
	 * 
	 * @param filename						name of the text input file
     * @throws UnrecognizedEntryException	if some entry is not correct
	 * @throws IOException					if there is an IO error while processing the text file
	 * @throws Cores_RepeatedFriendException
	 */
	void importFile(String filename) throws UnrecognizedEntryException, IOException  {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				readLine(line);
			}
		}
	}

    /**
	 * Read a line from the text input file and create corresponding domain entities.
	 * 
	 * @param line								line from the text input file
	 * @throws UnrecognizedEntryException		if some entry is not correct
	 * @throws Cores_RepeatedFriendException	if the same friend is added twice
	*/
	public void readLine(String line) throws UnrecognizedEntryException {
		String[] fields = line.split("\\|");
		switch(fields[0]) {
            /**
	 * 
	 *  FIXME
	 *  FIXME
	 *  FIXME
     *  FIXME
	 *  FIXME
	*/
		}
	}


  /**
   * Set changed.
   */
 	public void changed() {
  	  setChanged(true);
  	}

  /**
   * @return changed
   */
   public boolean hasChanged() {
	  return _changed;
   }

  /**
   * @param changed
   */
   public void setChanged(boolean changed) {
	_changed = changed;
   }
}
