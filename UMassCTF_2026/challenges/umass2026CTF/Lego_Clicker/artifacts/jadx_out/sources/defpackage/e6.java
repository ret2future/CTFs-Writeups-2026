package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class e6 implements Parcelable {
    public static final Parcelable.Creator<e6> CREATOR = new p1(2);
    public final int[] a;
    public final ArrayList b;
    public final int[] c;
    public final int[] d;
    public final int e;
    public final String f;
    public final int g;
    public final int h;
    public final CharSequence i;
    public final int j;
    public final CharSequence k;
    public final ArrayList l;
    public final ArrayList m;
    public final boolean n;

    public e6(d6 d6Var) {
        int size = d6Var.a.size();
        this.a = new int[size * 6];
        if (!d6Var.g) {
            z6.o("Not on back stack");
            throw null;
        }
        this.b = new ArrayList(size);
        this.c = new int[size];
        this.d = new int[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ld ldVar = (ld) d6Var.a.get(i2);
            this.a[i] = ldVar.a;
            this.b.add(null);
            int[] iArr = this.a;
            iArr[i + 1] = ldVar.b ? 1 : 0;
            iArr[i + 2] = ldVar.c;
            iArr[i + 3] = ldVar.d;
            int i3 = i + 5;
            iArr[i + 4] = ldVar.e;
            i += 6;
            iArr[i3] = ldVar.f;
            this.c[i2] = ldVar.g.ordinal();
            this.d[i2] = ldVar.h.ordinal();
        }
        this.e = d6Var.f;
        this.f = d6Var.h;
        this.g = d6Var.q;
        this.h = d6Var.i;
        this.i = d6Var.j;
        this.j = d6Var.k;
        this.k = d6Var.l;
        this.l = d6Var.m;
        this.m = d6Var.n;
        this.n = d6Var.o;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.a);
        parcel.writeStringList(this.b);
        parcel.writeIntArray(this.c);
        parcel.writeIntArray(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeInt(this.j);
        TextUtils.writeToParcel(this.k, parcel, 0);
        parcel.writeStringList(this.l);
        parcel.writeStringList(this.m);
        parcel.writeInt(this.n ? 1 : 0);
    }

    public e6(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.createStringArrayList();
        this.c = parcel.createIntArray();
        this.d = parcel.createIntArray();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.i = (CharSequence) creator.createFromParcel(parcel);
        this.j = parcel.readInt();
        this.k = (CharSequence) creator.createFromParcel(parcel);
        this.l = parcel.createStringArrayList();
        this.m = parcel.createStringArrayList();
        this.n = parcel.readInt() != 0;
    }
}
