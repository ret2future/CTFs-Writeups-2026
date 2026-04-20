package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.LegoClicker.R;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class yg extends BaseAdapter {
    public int a = -1;
    public final /* synthetic */ zg b;

    public yg(zg zgVar) {
        this.b = zgVar;
        a();
    }

    public final void a() {
        gi giVar = this.b.c;
        oi oiVar = giVar.v;
        if (oiVar != null) {
            giVar.i();
            ArrayList arrayList = giVar.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((oi) arrayList.get(i)) == oiVar) {
                    this.a = i;
                    return;
                }
            }
        }
        this.a = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final oi getItem(int i) {
        zg zgVar = this.b;
        gi giVar = zgVar.c;
        giVar.i();
        ArrayList arrayList = giVar.j;
        zgVar.getClass();
        int i2 = this.a;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (oi) arrayList.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        zg zgVar = this.b;
        gi giVar = zgVar.c;
        giVar.i();
        int size = giVar.j.size();
        zgVar.getClass();
        return this.a < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.b.b.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
        }
        ((dj) view).c(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
