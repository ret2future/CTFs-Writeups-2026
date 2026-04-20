package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import com.example.LegoClicker.R;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class f2 {
    public final int A;
    public final boolean B;
    public final d2 C;
    public final Context a;
    public final h2 b;
    public final Window c;
    public CharSequence d;
    public String e;
    public AlertController$RecycleListView f;
    public Button g;
    public CharSequence h;
    public Message i;
    public Button j;
    public CharSequence k;
    public Message l;
    public Button m;
    public CharSequence n;
    public Message o;
    public NestedScrollView p;
    public Drawable q;
    public ImageView r;
    public TextView s;
    public TextView t;
    public View u;
    public ListAdapter v;
    public final int x;
    public final int y;
    public final int z;
    public int w = -1;
    public final q0 D = new q0(1, this);

    public f2(Context context, h2 h2Var, Window window) {
        this.a = context;
        this.b = h2Var;
        this.c = window;
        d2 d2Var = new d2();
        d2Var.a = new WeakReference(h2Var);
        this.C = d2Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, fl.e, R.attr.alertDialogStyle, 0);
        this.x = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.y = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.z = typedArrayObtainStyledAttributes.getResourceId(7, 0);
        this.A = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.B = typedArrayObtainStyledAttributes.getBoolean(6, true);
        typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        h2Var.b().g(1);
    }

    public static ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    public final void b(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        Message messageObtainMessage = onClickListener != null ? this.C.obtainMessage(i, onClickListener) : null;
        if (i == -3) {
            this.n = charSequence;
            this.o = messageObtainMessage;
        } else if (i == -2) {
            this.k = charSequence;
            this.l = messageObtainMessage;
        } else if (i != -1) {
            z6.f("Button does not exist");
        } else {
            this.h = charSequence;
            this.i = messageObtainMessage;
        }
    }
}
