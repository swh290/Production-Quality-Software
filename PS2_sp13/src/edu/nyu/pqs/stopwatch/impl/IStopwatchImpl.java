package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.api.StopwatchState;

/**
 * 
 * @author shihweihuang
 * 
 */
public class IStopwatchImpl implements IStopwatch {
	private final String id;
	private long time = 0;
	private StopwatchState stopwatchState;
	private List<Long> lapTimes = new ArrayList<Long>();
	private Object lock = new Object();

	public IStopwatchImpl(String id) {
		this.id = id;
		stopwatchState = StopwatchState.RESET;
	}

	/**
	 * @return current system time in milliseconds
	 */
	private long getTime() {
		return System.currentTimeMillis();
	}

	/**
	 * Accumulate all lap times for next lap or stop action
	 * 
	 * @return accumulated time from lapTimes
	 */
	private long accumulateTime() {
		if (lapTimes.size() == 0) {
			return 0l;
		} else {
			long accu = 0;
			for (long l : lapTimes) {
				accu += l;
			}
			return accu;
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void start() throws IllegalStateException {
		synchronized (lock) {
			// Lock whole block of action prevent the state, time, or lapTimes from
			// being modified by other threads
			// Could go into wrong state, or remove objects from an empty list
			if (stopwatchState == StopwatchState.RUNNING) {
				throw new IllegalStateException("Stopwatch is alread RUNNING");
			} else if (stopwatchState == StopwatchState.RESET) {
				time = getTime();
			} else { // if stopwatchState == StopwatchState.STOP
				int lapTimesSize = lapTimes.size();
				lapTimes.remove(lapTimesSize - 1);
			}
			stopwatchState = StopwatchState.RUNNING;
		}
	}

	@Override
	public void lap() throws IllegalStateException {
		synchronized (lock) {
			// Lock whole block of action prevent the lapTimes from
			// being modified by other threads
			if (stopwatchState != StopwatchState.RUNNING) {
				throw new IllegalStateException("Stopwatch isn't RUNNING");
			}
			int lapTimesSize = lapTimes.size();
			if (lapTimesSize == 0) {
				lapTimes.add(getTime() - time);
			} else {
				lapTimes.add(getTime() - accumulateTime() - time);
			}
		}
	}

	@Override
	public void stop() throws IllegalStateException {
		// Lock whole block of action prevent the state, or lapTimes from
		// being modified by other threads
		synchronized (lock) {
			lap();
			stopwatchState = StopwatchState.STOP;
		}
	}

	@Override
	public void reset() {
		synchronized (lock) {
			// Lock whole block of action prevent the state, or lapTimes from
			// being modified by other threads
			if (stopwatchState == StopwatchState.RUNNING) {
				stop();
			}
			lapTimes.clear();
			stopwatchState = StopwatchState.RESET;
		}
	}

	@Override
	public List<Long> getLapTimes() {
		// Lock whole block of action to promise that get lap times not between
		// other actions
		synchronized (lock) {
			return lapTimes;
		}
	}

	@Override
	public String toString() {
		return "ID:" + id + " State:" + stopwatchState + " Current laps:"
				+ lapTimes.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof IStopwatch)) {
			return false;
		}
		IStopwatch watch = (IStopwatchImpl) o;
		// equals considers only id
		return watch.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + id.hashCode();
		return result;
	}
}
