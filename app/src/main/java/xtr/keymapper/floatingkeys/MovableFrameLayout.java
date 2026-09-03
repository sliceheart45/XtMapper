package xtr.keymapper.floatingkeys;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MovableFrameLayout extends FrameLayout implements View.OnTouchListener {
    private float dX, dY;

    public MovableFrameLayout(Context context) {
        super(context);
        init();
    }

    public MovableFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovableFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();

        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                dX = view.getX() - motionEvent.getRawX();
                dY = view.getY() - motionEvent.getRawY();
                return true; // Consumed
            }
            case MotionEvent.ACTION_MOVE: {
                int viewWidth = view.getWidth();
                int viewHeight = view.getHeight();

                View viewParent = (View)view.getParent();
                int parentWidth = viewParent.getWidth();
                int parentHeight = viewParent.getHeight();

                float newX = motionEvent.getRawX() + dX;
                float newY = motionEvent.getRawY() + dY;

                view.animate()
                        .x(newX)
                        .y(newY)
                        .setDuration(0)
                        .start();
                return true; // Consumed
            }
            default:
                return view.onTouchEvent(motionEvent);
        }
    }
}