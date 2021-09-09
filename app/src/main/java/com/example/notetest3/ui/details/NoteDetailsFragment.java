package com.example.notetest3.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import com.example.notetest3.R;
import com.example.notetest3.domain.Note;
import com.example.notetest3.ui.list.NotesListFragment;

public class NoteDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_NOTE, note);
        fragment.setArguments(arguments);
        return fragment;

    }

    public NoteDetailsFragment() {
        super(R.layout.fragment_note_details);
    }

    private TextView noteTitle;
    private TextView noteDescription;
    private TextView noteDate;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteTitle = view.findViewById(R.id.note_title);
        noteDescription = view.findViewById(R.id.note_dsc);
        noteDate = view.findViewById(R.id.note_date);

        getParentFragmentManager().setFragmentResultListener(NotesListFragment.KEY_SELECTED_NOTE, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Note note = result.getParcelable(NotesListFragment.ARG_NOTE);
                displayNote(note);
            }
        });
        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {

            Note note = getArguments().getParcelable(ARG_NOTE);
            if (note != null) {
                displayNote(note);
            }
        }
    }

    private void displayNote(Note note) {
        noteTitle.setText(note.getTitle());
        noteDescription.setText(note.getDescription());
        noteDate.setText(note.getDate());
    }
}
