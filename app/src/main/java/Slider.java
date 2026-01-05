import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_semesterptoject.R;

public class Slider extends AppCompatActivity {

    private ImageView profileImage;
    private TextView nameTextView;
    private TextView quoteTextView;
    private ImageView backButton;
    private ImageView forwardButton;

    private int currentSlideIndex = 0;
    private String[] names = {"Mary G McDonnell", "John Smith", "Jane Doe"};
    private String[] quotes = {
            "\"It is nice to stay home and receive care instead of going to a doctor's office with other sick people. Warm and comfortable care at home, on my time!\" ",
            "\"I was so impressed with the care and attention I received. It was like having a doctor in my own home!\" ",
            "\"The doctor was so kind and compassionate. I felt like I was in good hands.\" "
    };

    private int[] profileImages = {R.drawable.doctor1, R.drawable.doctor2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImage = findViewById(R.id.profileImage);
        nameTextView = findViewById(R.id.nameTextView);
        quoteTextView = findViewById(R.id.quoteTextView);
        //backButton = findViewById(R.id.backButton);
        //forwardButton = findViewById(R.id.forwardButton);

    }
    public void back(View v)
    {
        currentSlideIndex--;
        if (currentSlideIndex < 0) {
            currentSlideIndex = names.length - 1;
        }
        updateSlide(currentSlideIndex);
    }
    public void forward(View v)
    {
        currentSlideIndex++;
        if (currentSlideIndex >= names.length) {
            currentSlideIndex = 0;
        }
        updateSlide(currentSlideIndex);
    }
    private void updateSlide(int index) {
        profileImage.setImageResource(profileImages[index]);
        nameTextView.setText(names[index]);
        quoteTextView.setText(quotes[index]);
    }
}