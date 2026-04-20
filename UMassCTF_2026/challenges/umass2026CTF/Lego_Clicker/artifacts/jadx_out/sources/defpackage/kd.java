package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class kd implements i1 {
    public final Object a;
    public final Object b;
    public final Object c;
    public Object d;

    public kd(Typeface typeface, hj hjVar) {
        int i;
        int i2;
        int i3;
        int i4;
        this.d = typeface;
        this.a = hjVar;
        this.c = new ij(1024);
        int iA = hjVar.a(6);
        if (iA != 0) {
            int i5 = iA + hjVar.a;
            i = ((ByteBuffer) hjVar.d).getInt(((ByteBuffer) hjVar.d).getInt(i5) + i5);
        } else {
            i = 0;
        }
        this.b = new char[i * 2];
        int iA2 = hjVar.a(6);
        if (iA2 != 0) {
            int i6 = iA2 + hjVar.a;
            i2 = ((ByteBuffer) hjVar.d).getInt(((ByteBuffer) hjVar.d).getInt(i6) + i6);
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            dr drVar = new dr(this, i7);
            gj gjVarB = drVar.b();
            int iA3 = gjVarB.a(4);
            Character.toChars(iA3 != 0 ? ((ByteBuffer) gjVarB.d).getInt(iA3 + gjVarB.a) : 0, (char[]) this.b, i7 * 2);
            gj gjVarB2 = drVar.b();
            int iA4 = gjVarB2.a(16);
            if (iA4 != 0) {
                int i8 = iA4 + gjVarB2.a;
                i3 = ((ByteBuffer) gjVarB2.d).getInt(((ByteBuffer) gjVarB2.d).getInt(i8) + i8);
            } else {
                i3 = 0;
            }
            if (!(i3 > 0)) {
                z6.f("invalid metadata codepoint length");
                throw null;
            }
            ij ijVar = (ij) this.c;
            gj gjVarB3 = drVar.b();
            int iA5 = gjVarB3.a(16);
            if (iA5 != 0) {
                int i9 = iA5 + gjVarB3.a;
                i4 = ((ByteBuffer) gjVarB3.d).getInt(((ByteBuffer) gjVarB3.d).getInt(i9) + i9);
            } else {
                i4 = 0;
            }
            ijVar.a(drVar, 0, i4 - 1);
        }
    }

    public void a() {
        Iterator it = ((HashMap) this.b).values().iterator();
        while (it.hasNext()) {
            lo.a(it.next());
        }
    }

    @Override // defpackage.i1
    public boolean b(j1 j1Var, Menu menu) {
        ActionMode.Callback callback = (ActionMode.Callback) this.b;
        cp cpVarD = d(j1Var);
        ko koVar = (ko) this.d;
        Menu fjVar = (Menu) koVar.getOrDefault(menu, null);
        if (fjVar == null) {
            fjVar = new fj((Context) this.c, (gi) menu);
            koVar.put(menu, fjVar);
        }
        return callback.onCreateActionMode(cpVarD, fjVar);
    }

    @Override // defpackage.i1
    public boolean c(j1 j1Var, MenuItem menuItem) {
        return ((ActionMode.Callback) this.b).onActionItemClicked(d(j1Var), new si((Context) this.c, (gp) menuItem));
    }

    public cp d(j1 j1Var) {
        ArrayList arrayList = (ArrayList) this.a;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            cp cpVar = (cp) arrayList.get(i);
            if (cpVar != null && cpVar.b == j1Var) {
                return cpVar;
            }
        }
        cp cpVar2 = new cp((Context) this.c, j1Var);
        arrayList.add(cpVar2);
        return cpVar2;
    }

    @Override // defpackage.i1
    public boolean e(j1 j1Var, Menu menu) {
        ActionMode.Callback callback = (ActionMode.Callback) this.b;
        cp cpVarD = d(j1Var);
        ko koVar = (ko) this.d;
        Menu fjVar = (Menu) koVar.getOrDefault(menu, null);
        if (fjVar == null) {
            fjVar = new fj((Context) this.c, (gi) menu);
            koVar.put(menu, fjVar);
        }
        return callback.onPrepareActionMode(cpVarD, fjVar);
    }

    @Override // defpackage.i1
    public void f(j1 j1Var) {
        ((ActionMode.Callback) this.b).onDestroyActionMode(d(j1Var));
    }

    public ArrayList g() {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((HashMap) this.b).values().iterator();
        while (it.hasNext()) {
            lo.a(it.next());
        }
        return arrayList;
    }

    public List h() {
        ArrayList arrayList;
        if (((ArrayList) this.a).isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        synchronized (((ArrayList) this.a)) {
            arrayList = new ArrayList((ArrayList) this.a);
        }
        return arrayList;
    }

    public kd() {
        this.a = new ArrayList();
        this.b = new HashMap();
        this.c = new HashMap();
    }

    public kd(Context context, ActionMode.Callback callback) {
        this.c = context;
        this.b = callback;
        this.a = new ArrayList();
        this.d = new ko();
    }
}
