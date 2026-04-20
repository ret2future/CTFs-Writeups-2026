package defpackage;

import android.database.Observable;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class kl extends Observable {
    public final void a() {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            RecyclerView recyclerView = ((yl) ((Observable) this).mObservers.get(size)).a;
            recyclerView.h(null);
            recyclerView.d0.e = true;
            recyclerView.R(true);
            if (!recyclerView.e.f()) {
                recyclerView.requestLayout();
            }
        }
    }
}
