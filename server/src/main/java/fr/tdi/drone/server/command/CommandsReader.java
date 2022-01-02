package fr.tdi.drone.server.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class CommandsReader {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CommandsReader.class);
    private static final String[] ORIENTATIONS = { "E", "N", "S", "W" };
    private static final Character[] MOVES = { 'A', 'D', 'G' };
    private List<String> lines = new ArrayList<>();

    public CommandsReader() {
	//
    }

    /**
     * @return the lines
     */
    public List<String> getLines() {
	return lines;
    }

    public boolean readFile(String fileName) {
	String line = "";
	boolean res = false;

	lines.clear();

	try (BufferedReader buff = new BufferedReader(new FileReader(fileName))) {
	    while ((line = buff.readLine()) != null) {
		lines.add(line);
	    }
	    res = true;
	} catch (FileNotFoundException e) {
	    LOGGER.warn("{} does not exist", fileName);
	} catch (IOException e) {
	    LOGGER.error(e.getMessage());
	}

	return res;
    }

    public boolean isValid() {
	boolean res = true;

	if (lines.size() < 3) {
	    return false;
	}

	if (!isZoneValid(lines.get(0))) {
	    return false;
	}

	for (int i = 1; i < lines.size() - 1; i += 2) {
	    if (!isPositionValid(lines.get(i)) && !isMoveValid(lines.get(i + 1))) {
		return false;
	    }

	}

	return res;
    }

    private boolean isZoneValid(String s) {
	List<String> strings = Arrays.asList(s.split("\\ "));
	try {
	    int param1 = Integer.parseInt(strings.get(1));
	    int param2 = Integer.parseInt(strings.get(2));
	    return ">".equals(strings.get(0)) && param1 > 0 && param2 > 0;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    private boolean isPositionValid(String s) {
	List<String> strings = Arrays.asList(s.split("\\ "));

	try {
	    int param1 = Integer.parseInt(strings.get(0));
	    int param2 = Integer.parseInt(strings.get(1));
	    return Arrays.binarySearch(ORIENTATIONS, strings.get(2)) > 0 && param1 >= 0 && param2 >= 0;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    private boolean isMoveValid(String s) {
	boolean res = true;

	List<Character> chars = s.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

	for (Character c : chars) {
	    if (Arrays.binarySearch(MOVES, c) < 0) {
		res = false;
		break;
	    }
	}

	return res;
    }

}
