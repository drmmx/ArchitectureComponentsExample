package devmax.drmmx.com.architecturecomponents.ui.addnote;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.Toast;

import devmax.drmmx.com.architecturecomponents.R;
import devmax.drmmx.com.architecturecomponents.data.Note;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "devmax.drmmx.com.architecturecomponents.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "devmax.drmmx.com.architecturecomponents.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "devmax.drmmx.com.architecturecomponents.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "devmax.drmmx.com.architecturecomponents.EXTRA_PRIORITY";

    private TextInputEditText editTextTitle;
    private TextInputEditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        numberPickerPriority = findViewById(R.id.numberPickerPriority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
