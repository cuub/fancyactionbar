package com.cuubonandroid.fancyactionbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * 
 * @author <a href="http://www.hugofernandes.pt">Hugo Fernandes</a>
 * 
 */
public class ListeningScrollView extends ScrollView {

  private Drawable actionBarBackgroundDrawable;
  private int actionBarHeight;
  private View headerView;

  public ListeningScrollView(Context context) {
    super(context);
  }

  public ListeningScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ListeningScrollView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    super.onScrollChanged(l, t, oldl, oldt);
    if (actionBarBackgroundDrawable != null) {
      final int headerHeight = headerView.getHeight() - actionBarHeight;
      final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
      final int newAlpha = (int) (ratio * 255);
      actionBarBackgroundDrawable.setAlpha(newAlpha);
    }
  }

  @Override
  protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY,
      int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
    return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, 0, 0, isTouchEvent);
  }

  /**
   * Set the actionBar background drawable
   * 
   * @param actionBarBackgroundDrawable ActionBar background when "solid"
   * @param drawableCallback Use <code>null</code> if
   *          <code>Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1</code>
   */
  public void setActionBarBackgroundDrawable(Drawable actionBarBackgroundDrawable, Callback drawableCallback) {
    this.actionBarBackgroundDrawable = actionBarBackgroundDrawable;
    if (drawableCallback != null)
      actionBarBackgroundDrawable.setCallback(drawableCallback);
  }

  /**
   * Set the top view where the actionbar should be transparent
   * 
   * @param transparentHeaderView
   */
  public void setTransparentHeaderView(View transparentHeaderView) {
    this.headerView = transparentHeaderView;
  }

  /**
   * Set the actionBar height
   * 
   * @param actionBarHeight
   */
  public void setActionBarHeight(int actionBarHeight) {
    this.actionBarHeight = actionBarHeight;
  }
}
