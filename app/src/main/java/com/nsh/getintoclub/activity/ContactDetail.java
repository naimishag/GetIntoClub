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
import android.widget.Toast;

import com.nsh.getintoclub.R;

public class ContactDetail extends AppCompatActivity {

    public static String name = "", roll = "", branch = "", mobile = "", email = "",rollNo="";
    public static int RollLength;
    EditText Name, RollNumber, Branch, Mobile, Email;
    TextView doneContact;
    View backView;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initUI();
    }

    public void initUI() {
        Name = findViewById(R.id.name);
        RollNumber = findViewById(R.id.rollnumber);
        Branch = findViewById(R.id.branch);
        Mobile = findViewById(R.id.mobile);
        Mobile.setText(OtpGenerator.phone);
        Email = findViewById(R.id.email);
        backView = findViewById(R.id.backView);
        scrollView = findViewById(R.id.scrollView);
        doneContact = findViewById(R.id.donecontact);
        if (name.length() > 0)
            Name.setText(name);
        if (roll.length() > 0)
            RollNumber.setText(roll);
        if (branch.length() > 0)
            Branch.setText(branch);
        if (mobile.length() > 0)
            Mobile.setText(mobile);
        if (email.length() > 0)
            Email.setText(email);
        setupdata();
    }

    public void setupdata() {

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneContact.setAlpha(0f);
        scrollView.setAlpha(0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(doneContact, "alpha", 0, 1);
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
        rollNo = "";
        name = String.valueOf(Name.getText());
        roll = String.valueOf(RollNumber.getText());
        branch = String.valueOf(Branch.getText());
        mobile = String.valueOf(Mobile.getText());
        email = String.valueOf(Email.getText());
        for (int i = 0;i<roll.length();i++)
        {
            int y =(int) roll.charAt(i);
            if ((y>=48 && y<=57) || (y>=97 && y<=122) || (y>=65 && y<=90)) {
                if (y>=65 && y<=90)
                    y = y+32;
                char ad = (char) y;
                rollNo += (ad);
            }
        }
    roll = rollNo;
    }

    public void onClick(View view) {
        setupData();
        RollLength = roll.length();
        if (RollLength == 0) {
            Toast.makeText(this, "Seems to be incomplete", Toast.LENGTH_SHORT).show();
        } else {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(scrollView, "alpha", 1, 0);
        objectAnimator.setDuration(200);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(doneContact, "alpha", 1, 0);
        objectAnimator2.setDuration(200);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(scrollView, "translationY", 0, 50);
        objectAnimator1.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
        animatorSet.start();
    }


}
