package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ln extends mn implements Iterator {
    public kn a;
    public boolean b = true;
    public final /* synthetic */ nn c;

    public ln(nn nnVar) {
        this.c = nnVar;
    }

    @Override // defpackage.mn
    public final void a(kn knVar) {
        kn knVar2 = this.a;
        if (knVar == knVar2) {
            kn knVar3 = knVar2.d;
            this.a = knVar3;
            this.b = knVar3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b) {
            return this.c.a != null;
        }
        kn knVar = this.a;
        return (knVar == null || knVar.c == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b) {
            this.b = false;
            this.a = this.c.a;
        } else {
            kn knVar = this.a;
            this.a = knVar != null ? knVar.c : null;
        }
        return this.a;
    }
}
