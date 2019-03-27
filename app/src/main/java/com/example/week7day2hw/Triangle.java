package com.example.week7day2hw;

import android.content.Context ;
import android.content.res.TypedArray ;
import android.graphics.Canvas ;
import android.graphics.Paint ;
import android.graphics.Path ;
import android.graphics.Point ;
import android.support.annotation.Nullable;
import android.util.AttributeSet ;
import android.view.View ;

public class Triangle extends View {

    private static final Direction INITIAL_DIRECTION = Direction.LEFT ;
    private static final int INITIAL_COLOR = 0xff757575 ;

    private Paint mPaint ;
    private Path mTrianglePath ;
    private Direction mDirection ;
    private int mColor ;

    public Triangle(Context context) {
        this(context, null) ;
    }

    public Triangle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0) ;
    }

    public Triangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs) ;
    }


    private void init(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Triangle) ;
            switch (typedArray.getInt(R.styleable.Triangle_triangle_position, 0)) {
                case 0:
                    mDirection = Direction.UP ;
                    break ;
                case 1:
                    mDirection = Direction.LEFT ;
                    break ;
                case 2:
                    mDirection = Direction.RIGHT ;
                    break ;
                case 3:
                    default:
                    mDirection = Direction.DOWN ;
            }
            mColor = typedArray.getColor(R.styleable.Triangle_triangle_color, INITIAL_COLOR) ;
            typedArray.recycle() ;
        } else {
            mDirection = INITIAL_DIRECTION ;
            mColor = INITIAL_COLOR ;
        }
        mPaint = new Paint() ;
        mPaint.setStyle(Paint.Style.FILL) ;
        mPaint.setColor(mColor) ;
        mPaint.setAntiAlias(true) ;
    }

    public void setColor(int inputColor) {
        if (mColor != inputColor) {
            mColor = inputColor;
            if (mPaint != null) {
                mPaint.setColor(inputColor);
            }
            mTrianglePath = null;
            invalidate();
        }
    }

    public void setDirection(Direction direction) {
        if (direction != mDirection) {
            mDirection = direction;
            mTrianglePath = null;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(getThePathToTriangle(), mPaint);
    }

    private Path getThePathToTriangle() {
        if (mTrianglePath == null) {
            mTrianglePath = new Path();
            int width = getWidth();
            int height = getHeight();
            Point pointOne, pointTwo, pointThree;
            switch (mDirection) {
                case UP:
                    pointOne = new Point(0, height);
                    pointTwo = new Point(width, height);
                    pointThree = new Point(width / 2, 0);
                    break;
                case LEFT:
                    pointOne = new Point(width, 0);
                    pointTwo = new Point(width, height);
                    pointThree = new Point(0, height / 2);
                    break;
                case RIGHT:
                    pointOne = new Point(0, 0);
                    pointTwo = new Point(0, height);
                    pointThree = new Point(width, height / 2);
                    break;
                case DOWN:
                default:
                    pointOne = new Point(0, 0);
                    pointTwo = new Point(width, 0);
                    pointThree = new Point(width / 2, height);
            }
            mTrianglePath.moveTo(pointOne.x, pointOne.y);
            mTrianglePath.lineTo(pointTwo.x, pointTwo.y);
            mTrianglePath.lineTo(pointThree.x, pointThree.y);
        }
        return mTrianglePath;
    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }
}