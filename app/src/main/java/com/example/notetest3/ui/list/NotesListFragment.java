package com.example.notetest3.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.notetest3.R;
import com.example.notetest3.domain.DeviceNotesRepository;
import com.example.notetest3.domain.Note;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {
    private OnNoteClicked OnNoteClicked;

    public static final String KEY_SELECTED_NOTE = "KEY_SELECTED_NOTE";
    public static final String ARG_NOTE = "ARG_NOTE";

    public interface OnNoteClicked {
        void onNoteClicked(Note note);
    }

    private NotesListPresenter presenter;
    private LinearLayout container;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteClicked) {
            OnNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        OnNoteClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NotesListPresenter(this, new DeviceNotesRepository());
    }

    public NotesListFragment() {
        super(R.layout.fragment_notes_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        container = view.findViewById(R.id.root);
        presenter.requestNotes();
    }

    @Override
    public void showNotes(List<Note> notes) {
        for (Note note : notes) {
            View noteItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, container, false);

            noteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (OnNoteClicked != null) {
                        OnNoteClicked.onNoteClicked(note);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_NOTE, note);
                    getParentFragmentManager().setFragmentResult(KEY_SELECTED_NOTE, bundle);
                }
            });
            TextView noteTitle = noteItem.findViewById(R.id.note_title);


            noteTitle.setText(note.getTitle());

            container.addView(noteItem);
        }
    }
}
