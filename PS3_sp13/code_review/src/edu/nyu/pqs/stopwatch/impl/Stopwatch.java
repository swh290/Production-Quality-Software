/**
 * 
 */
package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * A thread-safe Stopwatch class.  After starting it can be used to record
 * laps and stopped.  If it is started after being stopped, it will resume
 * recording where it left off allowing for pause functionality.  Each 
 * stopwatch is associated with an ID which can be returned to identify
 * the stopwatch.  Implements the IStopwatch interface.
 * 
 * @see IStopwatch
 *
 */
class Stopwatch implements IStopwatch {

  private final String id;
  private long startTime;
  private final List<Long> lapList;
  private Boolean running = false;

  Stopwatch(String id) {
    lapList = Collections.synchronizedList(new ArrayList<Long>());
    
    if (id == null) {
      throw new IllegalArgumentException("Error:  id is null");
    } else {
      this.id = id;      
    }
  }

  /* Private helper method to make changing time return calls
   * easier.  Mainly used to switch between nanoTime and currentTimeMillis.
   */
  private long sysTime() {
    return System.currentTimeMillis();
  }

  /* Private helper method to add a lap to the lapList and update startTime.
   * Only called within synchronized methods so isn't synchronized itself.
   */
  private void addLap(Long startTime, List<Long> lapList) {
    long lapTime = sysTime() - startTime;
    lapList.add(lapTime);
    this.startTime = sysTime();
  }

  /* (non-Javadoc)
   * @see edu.nyu.pqs.stopwatch.api.IStopwatch#getId()
   */
  @Override
  public String getId() {
    return id;
  }

  /* (non-Javadoc)
   * @see edu.nyu.pqs.stopwatch.api.IStopwatch#start()
   */
  @Override
  public void start() {
    synchronized(running) {
      if (!running) {
        /* If the stopwatch has already been used, remove the final value
         * and subtract it from the current time to create "stop-start"
         * pause functionality.
         */
        if (!lapList.isEmpty()) {
          startTime = sysTime() - lapList.remove(lapList.size() - 1);
        } else {
          startTime = sysTime();
        }
        running = true;
      } else {
        throw new IllegalStateException("Error:  Stopwatch " + id + 
            " is already running.");
      }
    }
  }

  /* (non-Javadoc)
   * @see edu.nyu.pqs.stopwatch.api.IStopwatch#lap()
   */
  @Override
  public void lap() {
    synchronized(running) {
      if (running) {
        addLap(startTime, lapList);
      } else {
        throw new IllegalStateException("Error: Stopwatch " + id +
            " is not running.");
      }
    }

  }

  /* (non-Javadoc)
   * @see edu.nyu.pqs.stopwatch.api.IStopwatch#stop()
   */
  @Override
  public void stop() {
    synchronized(running) {
      if (running) {
        addLap(startTime, lapList);
        running = false;
      } else {
        throw new IllegalStateException("Error:  Stopwatch " + id +
            " is not running.");
      }
    }
  }

  /* (non-Javadoc)
   * @see edu.nyu.pqs.stopwatch.api.IStopwatch#reset()
   */
  @Override
  public void reset() {
    synchronized(running) {
      lapList.clear();
      running = false;
    }
  }

  /* (non-Javadoc)
   * @see edu.nyu.pqs.stopwatch.api.IStopwatch#getLapTimes()
   */
  @Override
  public List<Long> getLapTimes() {
    return new ArrayList<Long>(lapList);
  }
  
  /**
   * Returns a boolean indicating whether the stopwatch is running
   * or not.
   * @return a boolean indicating the stopwatch state
   */
  public boolean isRunning() {
    return running;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    
    if (!(o instanceof Stopwatch)) {
      return false;
    }
    Stopwatch stopwatch = (Stopwatch) o;
    
    if (!id.equals(stopwatch.getId())) {
      return false;
    } else if (!lapList.equals(stopwatch.getLapTimes())) {
      return false;
    } else if (!running.equals(stopwatch.isRunning())){
      return false;
    } else {
      return true;
    }
  }
  
  @Override
  public int hashCode() {
    int result = 17;
    int multiplier = 31;
    result = multiplier * result + id.hashCode();
    result = multiplier * result + getLapTimes().hashCode();
    result = multiplier * result + (running ? 1 : 0);
    return result;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int lapNum = 1;
    sb.append("Stopwatch " + id);
    sb.append(" is " + (running ? "running" : "not running") + "\n");
    for (long lap : lapList) {
      sb.append("Lap " + lapNum + ": " + lap + "\n");
      lapNum += 1;
    }
    return sb.toString();
  }

}
