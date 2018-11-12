package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long, TimeEntry> mapOfTimeEntries = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id = findTheNextId();
        TimeEntry timeEntryToCreate = createTimeEntry(timeEntry, id);
        mapOfTimeEntries.put(timeEntryToCreate.getId(), timeEntryToCreate);
        return timeEntryToCreate;
    }

    private TimeEntry createTimeEntry(TimeEntry timeEntry, long id) {
        return new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
    }

    private long findTheNextId() {
        return mapOfTimeEntries.size() ==0 ? 1l : mapOfTimeEntries.size() +1l;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return mapOfTimeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<>();
        for(Long id: mapOfTimeEntries.keySet()){
            timeEntries.add(mapOfTimeEntries.get(id));
        }
        return timeEntries;
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry timeEntryToUpdate = createTimeEntry(timeEntry, timeEntryId);
        mapOfTimeEntries.put(timeEntryId, timeEntryToUpdate);
        return timeEntryToUpdate;
    }

    @Override
    public void delete(long timeEntryId) {
       mapOfTimeEntries.remove(timeEntryId);
    }
}
