package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.ParcelImpl;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class p1 implements Parcelable.Creator {
    public final /* synthetic */ int a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                return new q1(parcel);
            case 1:
                n4 n4Var = new n4(parcel);
                n4Var.a = parcel.readByte() != 0;
                return n4Var;
            case 2:
                return new e6(parcel);
            case 3:
                return new f6(parcel);
            case 4:
                ed edVar = new ed();
                edVar.a = parcel.readString();
                edVar.b = parcel.readInt();
                return edVar;
            case 5:
                hd hdVar = new hd();
                hdVar.e = null;
                hdVar.f = new ArrayList();
                hdVar.g = new ArrayList();
                hdVar.a = parcel.createStringArrayList();
                hdVar.b = parcel.createStringArrayList();
                hdVar.c = (e6[]) parcel.createTypedArray(e6.CREATOR);
                hdVar.d = parcel.readInt();
                hdVar.e = parcel.readString();
                hdVar.f = parcel.createStringArrayList();
                hdVar.g = parcel.createTypedArrayList(f6.CREATOR);
                hdVar.h = parcel.createTypedArrayList(ed.CREATOR);
                return hdVar;
            case 6:
                return new jd(parcel);
            case 7:
                sg sgVar = new sg();
                sgVar.a = parcel.readInt();
                sgVar.b = parcel.readInt();
                sgVar.c = parcel.readInt() == 1;
                return sgVar;
            case 8:
                pj pjVar = new pj(parcel);
                pjVar.a = parcel.readInt();
                return pjVar;
            case 9:
                return new ParcelImpl(parcel);
            case 10:
                wo woVar = new wo();
                woVar.a = parcel.readInt();
                woVar.b = parcel.readInt();
                woVar.d = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    woVar.c = iArr;
                    parcel.readIntArray(iArr);
                }
                return woVar;
            default:
                xo xoVar = new xo();
                xoVar.a = parcel.readInt();
                xoVar.b = parcel.readInt();
                int i2 = parcel.readInt();
                xoVar.c = i2;
                if (i2 > 0) {
                    int[] iArr2 = new int[i2];
                    xoVar.d = iArr2;
                    parcel.readIntArray(iArr2);
                }
                int i3 = parcel.readInt();
                xoVar.e = i3;
                if (i3 > 0) {
                    int[] iArr3 = new int[i3];
                    xoVar.f = iArr3;
                    parcel.readIntArray(iArr3);
                }
                xoVar.h = parcel.readInt() == 1;
                xoVar.i = parcel.readInt() == 1;
                xoVar.j = parcel.readInt() == 1;
                xoVar.g = parcel.readArrayList(wo.class.getClassLoader());
                return xoVar;
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new q1[i];
            case 1:
                return new n4[i];
            case 2:
                return new e6[i];
            case 3:
                return new f6[i];
            case 4:
                return new ed[i];
            case 5:
                return new hd[i];
            case 6:
                return new jd[i];
            case 7:
                return new sg[i];
            case 8:
                return new pj[i];
            case 9:
                return new ParcelImpl[i];
            case 10:
                return new wo[i];
            default:
                return new xo[i];
        }
    }
}
