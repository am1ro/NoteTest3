package com.example.notetest3.domain;

import com.example.notetest3.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NoteRepository {

    @Override
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add (new Note(R.string.note_1, R.string.note_1_dsc, R.string.note_1_date));
        notes.add (new Note(R.string.note_2, R.string.note_2_dsc, R.string.note_2_date));
        notes.add (new Note(R.string.note_3, R.string.note_3_dsc, R.string.note_3_date));

        return notes;
    }
}
