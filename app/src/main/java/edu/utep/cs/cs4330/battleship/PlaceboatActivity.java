package edu.utep.cs.cs4330.battleship;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.LinkedList;

/**
 * Created by oscarricaud on 3/12/17.
 * This activity will allow the user to place boats. @see activity_place_boat
 */

public class PlaceboatActivity  extends Activity {
    private TextView title;
    private Button next;
    private Button quit;
    private ViewGroup rootLayout;
    protected LinkedList<Integer> xPlace = new LinkedList<>();
    protected LinkedList<Integer> yPlace = new LinkedList<>();
    private int boatSize = 5;
    private int _xDelta;
    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_boat);
        setEverything(); // The creation of this activity
    }

    /**
     * This method creates buttons and drag & drop feauture the user uses to place boats on grid.
     */
    private void setEverything() {
        title = (TextView) findViewById(R.id.placeboats); // PLACE BOATS
        setBoard();
        setButtons();           // Sets the appropriate buttons
        setBoatImagesOnView(); // Displays all 5 boats on UI
    }

    private void setBoard() {
        // Set the board view so boats can be placed on the grid
        Board board = new Board(10);
        BoardView boardView = (BoardView) findViewById(R.id.boardView);
        boardView.setBoard(board);
    }

    /**
     *  The user needs to be able to traverse to next or quit, hence the maker creates buttons.
     */
    private void setButtons() {
        next = (Button) findViewById(R.id.next);
        quit =(Button) findViewById(R.id.quitB);
        // Go to the next activity
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceboatActivity.this, GameActivity.class);
                PlaceboatActivity.this.startActivity(intent);
                /** Fading Transition Effect */
                PlaceboatActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceboatActivity.this, HomeActivity.class);
                PlaceboatActivity.this.startActivity(intent);
                /** Fading Transition Effect */
                PlaceboatActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void setBoatImagesOnView() {
        rootLayout = (ViewGroup) findViewById(R.id.defaultBoatsView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150); // Size of ships
        //Aircraft
        ImageView battleshipImage = (ImageView) findViewById(R.id.aircraft);
        battleshipImage.setLayoutParams(layoutParams);
        battleshipImage.setOnTouchListener(new ChoiceToucheListener());

        // Battleship
        ImageView aircraftImage = (ImageView) findViewById(R.id.battleship);
        aircraftImage.setLayoutParams(layoutParams);
        aircraftImage.setOnTouchListener(new ChoiceToucheListener());

        // Submarine
        ImageView submarineImage = (ImageView) findViewById(R.id.submarine);
        submarineImage.setLayoutParams(layoutParams);
        submarineImage.setOnTouchListener(new ChoiceToucheListener());

        // Minesweeper
        ImageView minesweeperImage = (ImageView) findViewById(R.id.minesweeper);
        minesweeperImage.setLayoutParams(layoutParams);
        minesweeperImage.setOnTouchListener(new ChoiceToucheListener());

        // Frigate
        ImageView frigateImage = (ImageView) findViewById(R.id.frigate);
        frigateImage.setLayoutParams(layoutParams);
        frigateImage.setOnTouchListener(new ChoiceToucheListener());

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/eightbit.TTF");
        title.setTypeface(typeface);
        next.setTypeface(typeface);
        quit.setTypeface(typeface);
    }

    /**
     * The drag and drop feature
     */
    private final class ChoiceToucheListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            // TODO Auto-generated method stub
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    _xDelta = X;
                    _yDelta = Y;
                break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X;
                    addplacedShip(X,'x',boatSize);
                    layoutParams.topMargin = Y;
                    addplacedShip(Y,'y', boatSize);
                    view.setLayoutParams(layoutParams);
                    break;
            }
            rootLayout.invalidate();
            return true;
        }
    }

    private void addplacedShip(int location, char flag, int boatSize){
        if(flag == 'x')
            for(int i = boatSize; i > 1; i--)
                xPlace.add(location + i);
        if(flag == 'y')
            yPlace.add(location);
    }
}
