package ecoach.e_test_mobile_application;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by banktech on 10/9/2014.
 */
public class TopicTest extends ActionBarActivity {


    Button testmix, btnone, btntwo, btnthree, btnfour, btnfive, btnsix, instructions;
    TextView head;
    String getone,gettwo,getthree,getfour,getfive,getsix,display,displaytwo,displaythree,displayfour,displayfive,displaysix;
    Boolean setPressed = false;
    Random rnd = new Random();
    boolean clicked = false;
    boolean selected = false;
    int numClicks = 0;
    int testmixes = 0;
    int qIndex;
    private int status;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topictest);

        //forceShowActionBarOverflowMenu();

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout_tags);

        Intent mIntent = getIntent();
        String topicsArray = mIntent.getStringExtra("topics");
        final String topicstest = mIntent.getStringExtra("questions");
        try {
            JSONArray arrayoftopic = new JSONArray(topicstest);
            for(int i=0; i<arrayoftopic.length();i++){
                JSONObject object = arrayoftopic.getJSONObject(0);
                getone = object.getString("questions");

                object = arrayoftopic.getJSONObject(1);
                gettwo = object.getString("questions");

                object = arrayoftopic.getJSONObject(2);
                getthree = object.getString("questions");

                object = arrayoftopic.getJSONObject(3);
                getfour = object.getString("questions");

                object = arrayoftopic.getJSONObject(4);
                getfive = object.getString("questions");

                object = arrayoftopic.getJSONObject(5);
                getsix = object.getString("questions");


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String header = mIntent.getStringExtra("header");

        head = (TextView)findViewById(R.id.tvhelp);
        head.setText(header);

        int[] androidColors = getResources().getIntArray(R.array.buttonone);
        int randomcolorone = androidColors[new Random().nextInt(androidColors.length)];

        instructions = (Button) findViewById(R.id.btn_instructions);
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TopicTest.this);

                //setting custom layout to dialog
                dialog.setContentView(R.layout.instructions);
                dialog.setTitle("INSTRUCTIONS");

                //adding button click event
                Button dismissButton = (Button) dialog.findViewById(R.id.button);
                dismissButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        });

        //JSONObject aQues = TopicTest.getQuesList().getJSONObject(qIndex);

        // String quesValue = aQues.getString("text");
        //JSONArray ansList = aQues.getJSONArray("Topic");
        //String aAns = ansList.getJSONObject(0).getString("text");


        try {
            final JSONArray array = new JSONArray(topicsArray);
            //this for loop is for a list / grid list view
            for (int i = 0; i < array.length(); i++) {
                String s = array.getString(i);
                System.out.println("this a s "+s);
            }
            System.out.println("this is an array of topics "+ array);

        btnone = (Button) findViewById(R.id.btnyrone);


        btnone.setBackgroundColor(randomcolorone);
            btnone.setText(array.getString(0));
            display = array.getString(0);
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] androidColors = getResources().getIntArray(R.array.buttonone);
                int randomcolorone = androidColors[new Random().nextInt(androidColors.length)];


                Intent intent = new Intent(TopicTest.this, TestActivity.class);
                intent.putExtra("subject",header);
                intent.putExtra("topic",display);
                intent.putExtra("questions",getone);
                startActivity(intent);
                // close this activity
                finish();
                v.setTag(1);

            }

        });

        int[] colortwo = getResources().getIntArray(R.array.buttontwo);
        int randomcolortwo = colortwo[new Random().nextInt(colortwo.length)];

        btntwo = (Button) findViewById(R.id.btnyrtwo);
        //aAns = ansList.getJSONObject(1).getString("text");
        btntwo.setText(array.getString(1));
            displaytwo = array.getString(1);
        btntwo.setTag(1);
        btntwo.setBackgroundColor(randomcolortwo);
        btntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int[] androidColors = getResources().getIntArray(R.array.buttonone);
                int randomcolorone = androidColors[new Random().nextInt(androidColors.length)];


                clicked = false;
                Intent intent = new Intent(TopicTest.this, TestActivity.class);
                intent.putExtra("subject",header);
                intent.putExtra("topic",displaytwo);
                intent.putExtra("questions",gettwo);
                startActivity(intent);
                // close this activity
                finish();


            }
        });

        int[] colorthree = getResources().getIntArray(R.array.buttonthree);
        int randomcolorthree = colorthree[new Random().nextInt(colorthree.length)];

        btnthree = (Button) findViewById(R.id.btnyrthree);
        //aAns = ansList.getJSONObject(2).getString("text");
        btnthree.setText(array.getString(2));
            displaythree = array.getString(2);
        btnthree.setTag(1);
        btnthree.setBackgroundColor(randomcolorthree);
        btnthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] androidColors = getResources().getIntArray(R.array.buttonthree);
                int randomcolorthree = androidColors[new Random().nextInt(androidColors.length)];


                btnthree.setBackgroundColor(randomcolorthree);
                Intent intent = new Intent(TopicTest.this, TestActivity.class);
                intent.putExtra("subject",header);
                intent.putExtra("topic",displaythree);
                intent.putExtra("questions",getthree);
                startActivity(intent);
                // close this activity
                finish();

            }
        });

        int[] colorfour = getResources().getIntArray(R.array.buttonfour);
        int randomcolorfour = colorfour[new Random().nextInt(colorfour.length)];

        btnfour = (Button) findViewById(R.id.btnyrfour);
        //aAns = ansList.getJSONObject(3).getString("text");
        btnfour.setText(array.getString(3));
            displayfour = array.getString(3);
        btnfour.setTag(1);
        btnfour.setBackgroundColor(randomcolorfour);
        btnfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] androidColors = getResources().getIntArray(R.array.buttonfour);
                int randomcolorfour = androidColors[new Random().nextInt(androidColors.length)];


                btnfour.setBackgroundColor(randomcolorfour);
                Intent intent = new Intent(TopicTest.this, TestActivity.class);
                intent.putExtra("subject",header);
                intent.putExtra("questions",getfour);
                startActivity(intent);
                // close this activity
                finish();


            }
        });

        int[] colorfive = getResources().getIntArray(R.array.buttonfive);
        int randomcolorfive = colorfive[new Random().nextInt(colorfive.length)];

        btnfive = (Button) findViewById(R.id.btnyrfive);
        //aAns = ansList.getJSONObject(4).getString("text");
        btnfive.setText(array.getString(4));
            displayfive = array.getString(4);
        btnfive.setTag(1);
        btnfive.setBackgroundColor(randomcolorfive);
        btnfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] androidColors = getResources().getIntArray(R.array.buttonfive);
                int randomcolorfive = androidColors[new Random().nextInt(androidColors.length)];


                btnfive.setBackgroundColor(randomcolorfive);
                Intent intent = new Intent(TopicTest.this, TestActivity.class);
                intent.putExtra("subject",header);
                intent.putExtra("questions",getfive);
                startActivity(intent);
                // close this activity
                finish();


            }
        });

        int[] colorsix = getResources().getIntArray(R.array.buttonsix);
        int randomcolorsix = colorsix[new Random().nextInt(colorsix.length)];

        btnsix = (Button) findViewById(R.id.btnyrsix);
        //aAns = ansList.getJSONObject(5).getString("text");
        btnsix.setText(array.getString(5));
            displaysix = array.getString(5);
        btnsix.setTag(1);
        btnsix.setBackgroundColor(randomcolorsix);
        btnsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] androidColors = getResources().getIntArray(R.array.buttonsix);
                int randomcolorsix = androidColors[new Random().nextInt(androidColors.length)];

                btnsix.setBackgroundColor(randomcolorsix);
                Intent intent = new Intent(TopicTest.this, TestActivity.class);
                intent.putExtra("subject",header);
                intent.putExtra("questions",getsix);
                startActivity(intent);
                // close this activity
                finish();


            }
        });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        testmix = (Button) findViewById(R.id.btntestmix);
        testmix.setTag(1);
        testmix.setText("ENABLE TEST MIXING");
        testmix.setBackgroundColor(Color.parseColor("#FFFF8800"));
        testmix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = (Integer) v.getTag();
                if (status == 1) {

                    testmix.setText("DISABLE TEST MIXING");
                    testmix.setBackgroundColor(Color.parseColor("#FFCC0000"));
                    btnone.setTag(1);
                    btnone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonone);
                            int randomcolorone = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnone.setBackgroundColor(randomcolorone);
                                btnone.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });

                                v.setTag(0);
                            } else {

                                btnone.setBackgroundColor(randomcolorone);
                                testmix();
                                v.setTag(1);
                            }
                        }
                    });
                    btntwo.setTag(1);
                    btntwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttontwo);
                            int randomcolortwo = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btntwo.setBackgroundColor(randomcolortwo);
                                btntwo.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btntwo.setBackgroundColor(randomcolortwo);

                                v.setTag(1);
                            }
                        }
                    });
                    btnthree.setTag(1);
                    btnthree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonthree);
                            int randomcolorthree = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnthree.setBackgroundColor(randomcolorthree);
                                btnthree.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnthree.setBackgroundColor(randomcolorthree);

                                v.setTag(1);
                            }
                        }
                    });
                    btnfour.setTag(1);
                    btnfour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonfour);
                            int randomcolorfour = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnfour.setBackgroundColor(randomcolorfour);
                                btnfour.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnfour.setBackgroundColor(randomcolorfour);

                                v.setTag(1);
                            }
                        }
                    });
                    btnfive.setTag(1);
                    btnfive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonfive);
                            int randomcolorfive = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnfive.setBackgroundColor(randomcolorfive);
                                btnfive.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnfive.setBackgroundColor(randomcolorfive);

                                v.setTag(1);
                            }
                        }
                    });
                    btnsix.setTag(1);
                    btnsix.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonsix);
                            int randomcolorsix = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnsix.setBackgroundColor(randomcolorsix);
                                btnsix.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnsix.setBackgroundColor(randomcolorsix);


                                v.setTag(1);
                            }
                        }
                    });

                    v.setTag(0);
                } else {

                    testmix.setText("ENABLE TEST MIXING");
                    testmix.setBackgroundColor(Color.parseColor("#FFFF8800"));

                    btnone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btntwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnthree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnfour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnfive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnsix.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    v.setTag(1);

                }
            }
        });


    }

    public void testmix() {
        testmix = (Button) findViewById(R.id.btntestmix);
        testmix.setTag(1);
        testmix.setText("ENABLE TEST MIXING");
        testmix.setBackgroundColor(Color.parseColor("#FFFF8800"));
        testmix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = (Integer) v.getTag();
                if (status == 1) {

                    testmix.setText("DISABLE TEST MIXING");
                    testmix.setBackgroundColor(Color.parseColor("#FFCC0000"));
                    btnone.setTag(1);
                    btnone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonone);
                            int randomcolorone = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnone.setBackgroundColor(randomcolorone);
                                btnone.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });

                                v.setTag(0);
                            } else {

                                btnone.setBackgroundColor(randomcolorone);
                                v.setTag(1);
                            }
                        }
                    });
                    btntwo.setTag(1);
                    btntwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttontwo);
                            int randomcolortwo = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btntwo.setBackgroundColor(randomcolortwo);
                                btntwo.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btntwo.setBackgroundColor(randomcolortwo);

                                v.setTag(1);
                            }
                        }
                    });
                    btnthree.setTag(1);
                    btnthree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonthree);
                            int randomcolorthree = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnthree.setBackgroundColor(randomcolorthree);
                                btnthree.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnthree.setBackgroundColor(randomcolorthree);

                                v.setTag(1);
                            }
                        }
                    });
                    btnfour.setTag(1);
                    btnfour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonfour);
                            int randomcolorfour = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnfour.setBackgroundColor(randomcolorfour);
                                btnfour.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnfour.setBackgroundColor(randomcolorfour);

                                v.setTag(1);
                            }
                        }
                    });
                    btnfive.setTag(1);
                    btnfive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonfive);
                            int randomcolorfive = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnfive.setBackgroundColor(randomcolorfive);
                                btnfive.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnfive.setBackgroundColor(randomcolorfive);

                                v.setTag(1);
                            }
                        }
                    });
                    btnsix.setTag(1);
                    btnsix.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int[] androidColors = getResources().getIntArray(R.array.buttonsix);
                            int randomcolorsix = androidColors[new Random().nextInt(androidColors.length)];

                            final int click = (Integer) v.getTag();
                            if (click == 1) {

                                btnsix.setBackgroundColor(randomcolorsix);
                                btnsix.setBackgroundResource(R.drawable.activeselected);
                                testmix.setText("START TEST");
                                testmix.setBackgroundColor(Color.parseColor("#00CC33"));
                                testmix.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TopicTest.this, TestActivity.class);
                                        startActivity(intent);
                                        // close this activity
                                        finish();
                                    }
                                });


                                v.setTag(0);
                            } else {

                                btnsix.setBackgroundColor(randomcolorsix);


                                v.setTag(1);
                            }
                        }
                    });

                    v.setTag(0);
                } else {

                    testmix.setText("ENABLE TEST MIXING");
                    testmix.setBackgroundColor(Color.parseColor("#FFFF8800"));

                    btnone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btntwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnthree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnfour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnfive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    btnsix.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TopicTest.this, TestActivity.class);
                            startActivity(intent);
                            // close this activity
                            finish();
                        }
                    });
                    v.setTag(1);

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.profile:
                Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profile);
                // close this activity
                finish();
                return true;
            case R.id.about:
                Intent about = new Intent(getApplicationContext(), About.class);
                startActivity(about);
                // close this activity
                finish();
                return true;
            case R.id.help:
                Intent help = new Intent(getApplicationContext(), Help.class);
                startActivity(help);
                // close this activity
                finish();
                return true;
            case R.id.logout:
                Intent logout = new Intent(getApplicationContext(), Logout.class);
                startActivity(logout);
                // close this activity
                finish();
                return true;
            //case R.id.action_check_updates:
            // check for updates action
            // return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.splash, menu);

        return super.onCreateOptionsMenu(menu);
    }


    private void forceShowActionBarOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
