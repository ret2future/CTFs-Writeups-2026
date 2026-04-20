package defpackage;

import android.os.Build;
import android.view.View;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ip {
    public int a;
    public int b;
    public int c;
    public Object d;

    public ip() {
        if (rn.a == null) {
            rn.a = new rn();
        }
    }

    public int a(int i) {
        if (i < this.c) {
            return ((ByteBuffer) this.d).getShort(this.b + i);
        }
        return 0;
    }

    public abstract Object b(View view);

    public abstract void c(View view, Object obj);

    public void d(View view, Object obj) {
        Object tag;
        if (Build.VERSION.SDK_INT >= this.b) {
            c(view, obj);
            return;
        }
        if (Build.VERSION.SDK_INT >= this.b) {
            tag = b(view);
        } else {
            tag = view.getTag(this.a);
            if (!((Class) this.d).isInstance(tag)) {
                tag = null;
            }
        }
        if (e(tag, obj)) {
            View.AccessibilityDelegate accessibilityDelegateC = os.c(view);
            r rVar = accessibilityDelegateC != null ? accessibilityDelegateC instanceof q ? ((q) accessibilityDelegateC).a : new r(accessibilityDelegateC) : null;
            if (rVar == null) {
                rVar = new r();
            }
            os.h(view, rVar);
            view.setTag(this.a, obj);
            os.e(view, this.c);
        }
    }

    public abstract boolean e(Object obj, Object obj2);
}
