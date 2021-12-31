package fr.tdi.drone.server.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class CommandsReader {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CommandsReader.class);
    private List<String> lines = new ArrayList<>();

    public CommandsReader() {
	//
    }

    public boolean readFile(String fileName) {
	String line = "";
	boolean res = false;

	lines.clear();

	try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	    while ((line = br.readLine()) != null) {
		lines.add(line);
	    }
	    res = true;
	} catch (FileNotFoundException f) {
	    LOGGER.warn("{} does not exist", fileName);
	} catch (IOException e) {
	    LOGGER.error(e.getMessage());
	}

	return res;
    }

}
