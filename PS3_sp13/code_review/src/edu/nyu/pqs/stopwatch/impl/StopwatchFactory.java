package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {

  // Maps ids to stopwatches
  private ConcurrentMap<String, IStopwatch> stopwatchMap;
  
  public StopwatchFactory() {
    stopwatchMap = new ConcurrentHashMap<String, IStopwatch>();
  }
	/**
	 * Creates and returns an IStopwatch object
	 * @param id The identifier of the new object
	 * @return The new IStopwatch object or previously created IStopwatch if ID
	 * was already used.
	 * @throws IllegalArgumentException if <code>id</code> is empty or null
	 */
	public IStopwatch getStopwatch(String id) {
	  
		if (id == null || id.isEmpty()) {
		  throw new IllegalArgumentException("Error: id is null or empty.");
		}
		
		IStopwatch stopwatch = new Stopwatch(id);
		IStopwatch oldStopwatch = stopwatchMap.putIfAbsent(id, stopwatch);
		
		if (oldStopwatch == null) {
		  return stopwatch;
		} else {
		  return oldStopwatch;
		}
	}

	/**
	 * Returns a list of all created stopwatches
	 * @return a List of al creates IStopwatch objects.  Returns an empty
	 * list oi no IStopwatches have been created.
	 */
	public List<IStopwatch> getStopwatches() {
		return new ArrayList<IStopwatch>(stopwatchMap.values());
	}
}
