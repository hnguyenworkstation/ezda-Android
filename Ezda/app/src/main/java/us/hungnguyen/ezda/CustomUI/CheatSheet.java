package us.hungnguyen.ezda.CustomUI;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/*
 * created by Aradh Pillai on 1.10.15
 *
 * Helper class for showing cheat sheets (tooltips) for icon-only UI elements on long-press. This is
 * already default platform behavior for icon-only {@link android.app.ActionBar} items and tabs.
 * This class provides this behavior for any other such UI element.
 */
public class CheatSheet {
    /**
     * The estimated height of a toast, in dips (density-independent pixels). This is used to
     * determine whether or not the toast should appear above or below the UI element.
     */
    private static final int ESTIMATED_TOAST_HEIGHT_DIPS = 48;

    /**
     * Sets up a cheat sheet (tooltip) for the given view by setting its {@link
     * View.OnLongClickListener}. When the view is long-pressed, a {@link Toast} with
     * the view's {@link View#getContentDescription() content description} will be
     * shown either above (default) or below the view (if there isn't room above it).
     *
     * @param view The view to add a cheat sheet for.
     */
    public static void setup(View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return showCheatSheet(view, view.getContentDescription());
            }
        });
    }

    /**
     * Sets up a cheat sheet (tooltip) for the given view by setting its {@link
     * View.OnLongClickListener}. When the view is long-pressed, a {@link Toast} with
     * the given text will be shown either above (default) or below the view (if there isn't room
     * above it).
     *
     * @param view      The view to add a cheat sheet for.
     * @param textResId The string resource containing the text to show on long-press.
     */
    public static void setup(View view, final int textResId) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return showCheatSheet(view, view.getContext().getString(textResId));
            }
        });
    }

    /**
     * Sets up a cheat sheet (tooltip) for the given view by setting its {@link
     * View.OnLongClickListener}. When the view is long-pressed, a {@link Toast} with
     * the given text will be shown either above (default) or below the view (if there isn't room
     * above it).
     *
     * @param view The view to add a cheat sheet for.
     * @param text The text to show on long-press.
     */
    public static void setup(View view, final CharSequence text) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return showCheatSheet(view, text);
            }
        });
    }

    /**
     * Removes the cheat sheet for the given view by removing the view's {@link
     * View.OnLongClickListener}.
     *
     * @param view The view whose cheat sheet should be removed.
     */
    public static void remove(final View view) {
        view.setOnLongClickListener(null);
    }

    /**
     * Internal helper method to show the cheat sheet toast.
     */
    private static boolean showCheatSheet(View view, CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }

        final int[] screenPos = new int[2]; // origin is device display
        final Rect displayFrame = new Rect(); // includes decorations (e.g. status bar)
        view.getLocationOnScreen(screenPos);
        view.getWindowVisibleDisplayFrame(displayFrame);

        final Context context = view.getContext();
        final int viewWidth = view.getWidth();
        final int viewHeight = view.getHeight();
        final int viewCenterX = screenPos[0] + viewWidth / 2;
        final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        final int estimatedToastHeight = (int) (ESTIMATED_TOAST_HEIGHT_DIPS
                * context.getResources().getDisplayMetrics().density);

        Toast cheatSheet = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        boolean showBelow = screenPos[1] < estimatedToastHeight;
        if (showBelow) {
            // Show below
            // Offsets are after decorations (e.g. status bar) are factored in
            cheatSheet.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,
                    viewCenterX - screenWidth / 2,
                    screenPos[1] - displayFrame.top + viewHeight);
        } else {
            // Show above
            // Offsets are after decorations (e.g. status bar) are factored in
            // NOTE: We can't use Gravity.BOTTOM because when the keyboard is up
            // its height isn't factored in.
            cheatSheet.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,
                    viewCenterX - screenWidth / 2,
                    screenPos[1] - displayFrame.top - estimatedToastHeight);
        }

        cheatSheet.show();
        return true;
    }
}