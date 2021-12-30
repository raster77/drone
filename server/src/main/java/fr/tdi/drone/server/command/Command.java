package fr.tdi.drone.server.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Command
 *
 */
public class Command {

    private String verb = "";
    private List<String> parameters = new ArrayList<>();
    private int expectedParams = 0;

    public Command(String verb) {
	this.verb = verb;
	expectedParams = 0;
    }

    public Command(String verb, int expectedParams) {
	this.verb = verb;
	this.expectedParams = expectedParams;
    }

    public Command(String verb, int expectedParams, String... params) {
	this.verb = verb;
	parameters.addAll(0, Arrays.asList(params));
	this.expectedParams = expectedParams;
    }

    public Command(String verb, int expectedParams, List<String> params) {
	this.verb = verb;
	parameters.addAll(0, params);
	this.expectedParams = expectedParams;
    }

    public String getVerb() {
	return verb;
    }

    public String getParameter(int index) {
	return parameters.get(index);
    }

    public void addParameter(String arg) {
	parameters.add(arg);
    }

    public String generate() throws CommandException {
	if (parameters.size() != expectedParams) {
	    throw new CommandException(String.format("Wrong numbers of parameters. Expected %2d got %2d",
		    expectedParams, parameters.size()));
	}

	StringBuilder sb = new StringBuilder(verb);
	sb.append(" ");
	parameters.forEach(a -> {
	    sb.append(a);
	    sb.append(" ");
	});
	return sb.toString().trim();
    }

    public void clearParameters() {
	parameters.clear();
    }

}
