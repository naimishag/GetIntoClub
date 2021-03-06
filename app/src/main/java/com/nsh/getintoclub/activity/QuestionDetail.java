package com.nsh.getintoclub.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nsh.getintoclub.R;

public class QuestionDetail extends AppCompatActivity {

    public static String q1 = "", q2 = "", q3 = "", q4 = "";
    ScrollView scrollView;
    EditText ques1, ques2, ques3, ques4;
    TextView doneQuestion;
    View backView;
    int rollLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initUI();
    }

    public void initUI() {
        ques1 = findViewById(R.id.q1);
        ques2 = findViewById(R.id.q2);
        ques3 = findViewById(R.id.q3);
        ques4 = findViewById(R.id.q4);
        scrollView = findViewById(R.id.scrollView);
        doneQuestion = findViewById(R.id.donequestion);
        backView = findViewById(R.id.backView);
        if (q1.length() > 0)
            ques1.setText(q1);
        if (q2.length() > 0)
            ques2.setText(q2);
        if (q3.length() > 0)
            ques3.setText(q3);
        if (q4.length() > 0)
            ques4.setText(q4);
        setupdata();
    }

    public void setupdata() {

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneQuestion.setAlpha(0f);
        scrollView.setAlpha(0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(doneQuestion, "alpha", 0, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(scrollView, "alpha", 0, 1);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(scrollView, "translationY", 50, 0);
        objectAnimator.setDuration(500);
        objectAnimator2.setDuration(500);
        objectAnimator1.setDuration(500);
        objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
        objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        objectAnimator1.setInterpolator(new AnticipateOvershootInterpolator());
        AnimatorSet animator = new AnimatorSet();
        animator.setStartDelay(100);
        animator.playTogether(objectAnimator, objectAnimator2, objectAnimator1);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                scrollView.setAlpha(1f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void setupData() {
        q1 = String.valueOf(ques1.getText());
        q2 = String.valueOf(ques2.getText());
        q3 = String.valueOf(ques3.getText());
        q4 = String.valueOf(ques4.getText());
    }

    public void onClick(View view) {
        setupData();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(scrollView, "alpha", 1, 0);
        objectAnimator.setDuration(200);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(doneQuestion, "alpha", 1, 0);
        objectAnimator2.setDuration(200);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(scrollView, "translationY", 0, 50);
        objectAnimator1.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
        animatorSet.start();
    }
}
