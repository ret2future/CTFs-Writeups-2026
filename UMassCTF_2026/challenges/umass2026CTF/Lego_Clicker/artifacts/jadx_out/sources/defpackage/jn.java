package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class jn extends mn implements Iterator {
    public kn a;
    public kn b;
    public final /* synthetic */ int c;

    public jn(kn knVar, kn knVar2, int i) {
        this.c = i;
        this.a = knVar2;
        this.b = knVar;
    }

    @Override // defpackage.mn
    public final void a(kn knVar) {
        kn knVar2;
        kn knVarB = null;
        if (this.a == knVar && knVar == this.b) {
            this.b = null;
            this.a = null;
        }
        kn knVar3 = this.a;
        if (knVar3 == knVar) {
            switch (this.c) {
                case 0:
                    knVar2 = knVar3.d;
                    break;
                default:
                    knVar2 = knVar3.c;
                    break;
            }
            this.a = knVar2;
        }
        kn knVar4 = this.b;
        if (knVar4 == knVar) {
            kn knVar5 = this.a;
            if (knVar4 != knVar5 && knVar5 != null) {
                knVarB = b(knVar4);
            }
            this.b = knVarB;
        }
    }

    public final kn b(kn knVar) {
        switch (this.c) {
            case 0:
                return knVar.c;
            default:
                return knVar.d;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        kn knVar = this.b;
        kn knVar2 = this.a;
        this.b = (knVar == knVar2 || knVar2 == null) ? null : b(knVar);
        return knVar;
    }
}
