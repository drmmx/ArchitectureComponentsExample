package devmax.drmmx.com.architecturecomponents.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import devmax.drmmx.com.architecturecomponents.data.Note;
import devmax.drmmx.com.architecturecomponents.data.NoteRepository;


public class NoteViewModel extends AndroidViewModel {
    private NoteRepository mRepository;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }

    public void update(Note note) {
        mRepository.update(note);
    }

    public void delete(Note note) {
        mRepository.delete(note);
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }
}
