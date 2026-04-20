package defpackage;

import android.view.View;
import com.example.LegoClicker.R;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class hs {
    public static void a(View view, ms msVar) {
        ko koVar = (ko) view.getTag(R.id.tag_unhandled_key_listeners);
        if (koVar == null) {
            koVar = new ko();
            view.setTag(R.id.tag_unhandled_key_listeners, koVar);
        }
        Objects.requireNonNull(msVar);
        View.OnUnhandledKeyEventListener gsVar = new gs();
        koVar.put(msVar, gsVar);
        view.addOnUnhandledKeyEventListener(gsVar);
    }

    public static CharSequence b(View view) {
        return view.getAccessibilityPaneTitle();
    }

    public static boolean c(View view) {
        return view.isAccessibilityHeading();
    }

    public static boolean d(View view) {
        return view.isScreenReaderFocusable();
    }

    public static void e(View view, ms msVar) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        ko koVar = (ko) view.getTag(R.id.tag_unhandled_key_listeners);
        if (koVar == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) koVar.getOrDefault(msVar, null)) == null) {
            return;
        }
        view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
    }

    public static <T> T f(View view, int i) {
        return (T) view.requireViewById(i);
    }

    public static void g(View view, boolean z) {
        view.setAccessibilityHeading(z);
    }

    public static void h(View view, CharSequence charSequence) {
        view.setAccessibilityPaneTitle(charSequence);
    }

    public static void i(View view, c6 c6Var) {
        view.setAutofillId(null);
    }

    public static void j(View view, boolean z) {
        view.setScreenReaderFocusable(z);
    }
}
