package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class j8 {
    public static final int[] d = {0, 4, 8};
    public static final SparseIntArray e;
    public static final SparseIntArray f;
    public final HashMap a = new HashMap();
    public final boolean b = true;
    public final HashMap c = new HashMap();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        e = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        f = sparseIntArray2;
        sparseIntArray.append(82, 25);
        sparseIntArray.append(83, 26);
        sparseIntArray.append(85, 29);
        sparseIntArray.append(86, 30);
        sparseIntArray.append(92, 36);
        sparseIntArray.append(91, 35);
        sparseIntArray.append(63, 4);
        sparseIntArray.append(62, 3);
        sparseIntArray.append(58, 1);
        sparseIntArray.append(60, 91);
        sparseIntArray.append(59, 92);
        sparseIntArray.append(101, 6);
        sparseIntArray.append(102, 7);
        sparseIntArray.append(70, 17);
        sparseIntArray.append(71, 18);
        sparseIntArray.append(72, 19);
        sparseIntArray.append(54, 99);
        sparseIntArray.append(0, 27);
        sparseIntArray.append(87, 32);
        sparseIntArray.append(88, 33);
        sparseIntArray.append(69, 10);
        sparseIntArray.append(68, 9);
        sparseIntArray.append(106, 13);
        sparseIntArray.append(109, 16);
        sparseIntArray.append(107, 14);
        sparseIntArray.append(104, 11);
        sparseIntArray.append(108, 15);
        sparseIntArray.append(105, 12);
        sparseIntArray.append(95, 40);
        sparseIntArray.append(80, 39);
        sparseIntArray.append(79, 41);
        sparseIntArray.append(94, 42);
        sparseIntArray.append(78, 20);
        sparseIntArray.append(93, 37);
        sparseIntArray.append(67, 5);
        sparseIntArray.append(81, 87);
        sparseIntArray.append(90, 87);
        sparseIntArray.append(84, 87);
        sparseIntArray.append(61, 87);
        sparseIntArray.append(57, 87);
        sparseIntArray.append(5, 24);
        sparseIntArray.append(7, 28);
        sparseIntArray.append(23, 31);
        sparseIntArray.append(24, 8);
        sparseIntArray.append(6, 34);
        sparseIntArray.append(8, 2);
        sparseIntArray.append(3, 23);
        sparseIntArray.append(4, 21);
        sparseIntArray.append(96, 95);
        sparseIntArray.append(73, 96);
        sparseIntArray.append(2, 22);
        sparseIntArray.append(13, 43);
        sparseIntArray.append(26, 44);
        sparseIntArray.append(21, 45);
        sparseIntArray.append(22, 46);
        sparseIntArray.append(20, 60);
        sparseIntArray.append(18, 47);
        sparseIntArray.append(19, 48);
        sparseIntArray.append(14, 49);
        sparseIntArray.append(15, 50);
        sparseIntArray.append(16, 51);
        sparseIntArray.append(17, 52);
        sparseIntArray.append(25, 53);
        sparseIntArray.append(97, 54);
        sparseIntArray.append(74, 55);
        sparseIntArray.append(98, 56);
        sparseIntArray.append(75, 57);
        sparseIntArray.append(99, 58);
        sparseIntArray.append(76, 59);
        sparseIntArray.append(64, 61);
        sparseIntArray.append(66, 62);
        sparseIntArray.append(65, 63);
        sparseIntArray.append(28, 64);
        sparseIntArray.append(121, 65);
        sparseIntArray.append(35, 66);
        sparseIntArray.append(122, 67);
        sparseIntArray.append(113, 79);
        sparseIntArray.append(1, 38);
        sparseIntArray.append(112, 68);
        sparseIntArray.append(100, 69);
        sparseIntArray.append(77, 70);
        sparseIntArray.append(111, 97);
        sparseIntArray.append(32, 71);
        sparseIntArray.append(30, 72);
        sparseIntArray.append(31, 73);
        sparseIntArray.append(33, 74);
        sparseIntArray.append(29, 75);
        sparseIntArray.append(114, 76);
        sparseIntArray.append(89, 77);
        sparseIntArray.append(123, 78);
        sparseIntArray.append(56, 80);
        sparseIntArray.append(55, 81);
        sparseIntArray.append(116, 82);
        sparseIntArray.append(120, 83);
        sparseIntArray.append(119, 84);
        sparseIntArray.append(118, 85);
        sparseIntArray.append(117, 86);
        sparseIntArray2.append(85, 6);
        sparseIntArray2.append(85, 7);
        sparseIntArray2.append(0, 27);
        sparseIntArray2.append(89, 13);
        sparseIntArray2.append(92, 16);
        sparseIntArray2.append(90, 14);
        sparseIntArray2.append(87, 11);
        sparseIntArray2.append(91, 15);
        sparseIntArray2.append(88, 12);
        sparseIntArray2.append(78, 40);
        sparseIntArray2.append(71, 39);
        sparseIntArray2.append(70, 41);
        sparseIntArray2.append(77, 42);
        sparseIntArray2.append(69, 20);
        sparseIntArray2.append(76, 37);
        sparseIntArray2.append(60, 5);
        sparseIntArray2.append(72, 87);
        sparseIntArray2.append(75, 87);
        sparseIntArray2.append(73, 87);
        sparseIntArray2.append(57, 87);
        sparseIntArray2.append(56, 87);
        sparseIntArray2.append(5, 24);
        sparseIntArray2.append(7, 28);
        sparseIntArray2.append(23, 31);
        sparseIntArray2.append(24, 8);
        sparseIntArray2.append(6, 34);
        sparseIntArray2.append(8, 2);
        sparseIntArray2.append(3, 23);
        sparseIntArray2.append(4, 21);
        sparseIntArray2.append(79, 95);
        sparseIntArray2.append(64, 96);
        sparseIntArray2.append(2, 22);
        sparseIntArray2.append(13, 43);
        sparseIntArray2.append(26, 44);
        sparseIntArray2.append(21, 45);
        sparseIntArray2.append(22, 46);
        sparseIntArray2.append(20, 60);
        sparseIntArray2.append(18, 47);
        sparseIntArray2.append(19, 48);
        sparseIntArray2.append(14, 49);
        sparseIntArray2.append(15, 50);
        sparseIntArray2.append(16, 51);
        sparseIntArray2.append(17, 52);
        sparseIntArray2.append(25, 53);
        sparseIntArray2.append(80, 54);
        sparseIntArray2.append(65, 55);
        sparseIntArray2.append(81, 56);
        sparseIntArray2.append(66, 57);
        sparseIntArray2.append(82, 58);
        sparseIntArray2.append(67, 59);
        sparseIntArray2.append(59, 62);
        sparseIntArray2.append(58, 63);
        sparseIntArray2.append(28, 64);
        sparseIntArray2.append(105, 65);
        sparseIntArray2.append(34, 66);
        sparseIntArray2.append(106, 67);
        sparseIntArray2.append(96, 79);
        sparseIntArray2.append(1, 38);
        sparseIntArray2.append(97, 98);
        sparseIntArray2.append(95, 68);
        sparseIntArray2.append(83, 69);
        sparseIntArray2.append(68, 70);
        sparseIntArray2.append(32, 71);
        sparseIntArray2.append(30, 72);
        sparseIntArray2.append(31, 73);
        sparseIntArray2.append(33, 74);
        sparseIntArray2.append(29, 75);
        sparseIntArray2.append(98, 76);
        sparseIntArray2.append(74, 77);
        sparseIntArray2.append(107, 78);
        sparseIntArray2.append(55, 80);
        sparseIntArray2.append(54, 81);
        sparseIntArray2.append(100, 82);
        sparseIntArray2.append(104, 83);
        sparseIntArray2.append(103, 84);
        sparseIntArray2.append(102, 85);
        sparseIntArray2.append(101, 86);
        sparseIntArray2.append(94, 97);
    }

    public static int[] b(g6 g6Var, String str) {
        int iIntValue;
        String[] strArrSplit = str.split(",");
        Context context = g6Var.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            Object obj = null;
            try {
                iIntValue = al.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && g6Var.isInEditMode() && (g6Var.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) g6Var.getParent();
                if (strTrim != null) {
                    HashMap map = constraintLayout.m;
                    if (map != null && map.containsKey(strTrim)) {
                        obj = constraintLayout.m.get(strTrim);
                    }
                } else {
                    constraintLayout.getClass();
                }
                if (obj != null && (obj instanceof Integer)) {
                    iIntValue = ((Integer) obj).intValue();
                }
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    public static e8 c(Context context, AttributeSet attributeSet, boolean z) {
        int i;
        int i2;
        e8 e8Var = new e8();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? bl.c : bl.a);
        String[] strArr = d.k;
        h8 h8Var = e8Var.b;
        i8 i8Var = e8Var.e;
        g8 g8Var = e8Var.c;
        f8 f8Var = e8Var.d;
        int[] iArr = d;
        SparseIntArray sparseIntArray = e;
        if (z) {
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            d8 d8Var = new d8();
            d8Var.a = new int[10];
            d8Var.b = new int[10];
            d8Var.c = 0;
            d8Var.d = new int[10];
            d8Var.e = new float[10];
            d8Var.f = 0;
            d8Var.g = new int[5];
            d8Var.h = new String[5];
            d8Var.i = 0;
            d8Var.j = new int[4];
            d8Var.k = new boolean[4];
            d8Var.l = 0;
            g8Var.getClass();
            f8Var.getClass();
            i8Var.getClass();
            int i3 = 0;
            while (i3 < indexCount) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                int i4 = indexCount;
                switch (f.get(index)) {
                    case 2:
                        i2 = i3;
                        d8Var.b(2, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.I));
                        continue;
                        i3 = i2 + 1;
                        indexCount = i4;
                        break;
                    case 3:
                    case 4:
                    case 9:
                    case 10:
                    case 25:
                    case 26:
                    case 29:
                    case 30:
                    case 32:
                    case 33:
                    case 35:
                    case 36:
                    case 61:
                    case 88:
                    case 89:
                    case 90:
                    case 91:
                    case 92:
                    default:
                        StringBuilder sb = new StringBuilder("Unknown attribute 0x");
                        i2 = i3;
                        sb.append(Integer.toHexString(index));
                        sb.append("   ");
                        sb.append(sparseIntArray.get(index));
                        Log.w("ConstraintSet", sb.toString());
                        break;
                    case 5:
                        i2 = i3;
                        d8Var.c(5, typedArrayObtainStyledAttributes.getString(index));
                        continue;
                        i3 = i2 + 1;
                        indexCount = i4;
                        break;
                    case 6:
                        i2 = i3;
                        d8Var.b(6, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, f8Var.C));
                        break;
                    case 7:
                        i2 = i3;
                        d8Var.b(7, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, f8Var.D));
                        break;
                    case 8:
                        i2 = i3;
                        d8Var.b(8, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.J));
                        break;
                    case 11:
                        i2 = i3;
                        d8Var.b(11, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.P));
                        break;
                    case 12:
                        i2 = i3;
                        d8Var.b(12, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.Q));
                        break;
                    case 13:
                        i2 = i3;
                        d8Var.b(13, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.M));
                        break;
                    case 14:
                        i2 = i3;
                        d8Var.b(14, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.O));
                        break;
                    case 15:
                        i2 = i3;
                        d8Var.b(15, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.R));
                        break;
                    case 16:
                        i2 = i3;
                        d8Var.b(16, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.N));
                        break;
                    case 17:
                        i2 = i3;
                        d8Var.b(17, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, f8Var.d));
                        break;
                    case 18:
                        i2 = i3;
                        d8Var.b(18, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, f8Var.e));
                        break;
                    case 19:
                        i2 = i3;
                        d8Var.a(19, typedArrayObtainStyledAttributes.getFloat(index, f8Var.f));
                        break;
                    case 20:
                        i2 = i3;
                        d8Var.a(20, typedArrayObtainStyledAttributes.getFloat(index, f8Var.w));
                        break;
                    case 21:
                        i2 = i3;
                        d8Var.b(21, typedArrayObtainStyledAttributes.getLayoutDimension(index, f8Var.c));
                        break;
                    case 22:
                        i2 = i3;
                        d8Var.b(22, iArr[typedArrayObtainStyledAttributes.getInt(index, h8Var.a)]);
                        break;
                    case 23:
                        i2 = i3;
                        d8Var.b(23, typedArrayObtainStyledAttributes.getLayoutDimension(index, f8Var.b));
                        break;
                    case 24:
                        i2 = i3;
                        d8Var.b(24, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.F));
                        break;
                    case 27:
                        i2 = i3;
                        d8Var.b(27, typedArrayObtainStyledAttributes.getInt(index, f8Var.E));
                        break;
                    case 28:
                        i2 = i3;
                        d8Var.b(28, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.G));
                        break;
                    case 31:
                        i2 = i3;
                        d8Var.b(31, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.K));
                        break;
                    case 34:
                        i2 = i3;
                        d8Var.b(34, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.H));
                        break;
                    case 37:
                        i2 = i3;
                        d8Var.a(37, typedArrayObtainStyledAttributes.getFloat(index, f8Var.x));
                        break;
                    case 38:
                        i2 = i3;
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, e8Var.a);
                        e8Var.a = resourceId;
                        d8Var.b(38, resourceId);
                        break;
                    case 39:
                        i2 = i3;
                        d8Var.a(39, typedArrayObtainStyledAttributes.getFloat(index, f8Var.U));
                        break;
                    case 40:
                        i2 = i3;
                        d8Var.a(40, typedArrayObtainStyledAttributes.getFloat(index, f8Var.T));
                        break;
                    case 41:
                        i2 = i3;
                        d8Var.b(41, typedArrayObtainStyledAttributes.getInt(index, f8Var.V));
                        break;
                    case 42:
                        i2 = i3;
                        d8Var.b(42, typedArrayObtainStyledAttributes.getInt(index, f8Var.W));
                        break;
                    case 43:
                        i2 = i3;
                        d8Var.a(43, typedArrayObtainStyledAttributes.getFloat(index, h8Var.c));
                        break;
                    case 44:
                        i2 = i3;
                        d8Var.d(44, true);
                        d8Var.a(44, typedArrayObtainStyledAttributes.getDimension(index, i8Var.m));
                        break;
                    case 45:
                        i2 = i3;
                        d8Var.a(45, typedArrayObtainStyledAttributes.getFloat(index, i8Var.b));
                        break;
                    case 46:
                        i2 = i3;
                        d8Var.a(46, typedArrayObtainStyledAttributes.getFloat(index, i8Var.c));
                        break;
                    case 47:
                        i2 = i3;
                        d8Var.a(47, typedArrayObtainStyledAttributes.getFloat(index, i8Var.d));
                        break;
                    case 48:
                        i2 = i3;
                        d8Var.a(48, typedArrayObtainStyledAttributes.getFloat(index, i8Var.e));
                        break;
                    case 49:
                        i2 = i3;
                        d8Var.a(49, typedArrayObtainStyledAttributes.getDimension(index, i8Var.f));
                        break;
                    case 50:
                        i2 = i3;
                        d8Var.a(50, typedArrayObtainStyledAttributes.getDimension(index, i8Var.g));
                        break;
                    case 51:
                        i2 = i3;
                        d8Var.a(51, typedArrayObtainStyledAttributes.getDimension(index, i8Var.i));
                        break;
                    case 52:
                        i2 = i3;
                        d8Var.a(52, typedArrayObtainStyledAttributes.getDimension(index, i8Var.j));
                        break;
                    case 53:
                        i2 = i3;
                        d8Var.a(53, typedArrayObtainStyledAttributes.getDimension(index, i8Var.k));
                        break;
                    case 54:
                        i2 = i3;
                        d8Var.b(54, typedArrayObtainStyledAttributes.getInt(index, f8Var.X));
                        break;
                    case 55:
                        i2 = i3;
                        d8Var.b(55, typedArrayObtainStyledAttributes.getInt(index, f8Var.Y));
                        break;
                    case 56:
                        i2 = i3;
                        d8Var.b(56, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.Z));
                        break;
                    case 57:
                        i2 = i3;
                        d8Var.b(57, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.a0));
                        break;
                    case 58:
                        i2 = i3;
                        d8Var.b(58, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.b0));
                        break;
                    case 59:
                        i2 = i3;
                        d8Var.b(59, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.c0));
                        break;
                    case 60:
                        i2 = i3;
                        d8Var.a(60, typedArrayObtainStyledAttributes.getFloat(index, i8Var.a));
                        break;
                    case 62:
                        i2 = i3;
                        d8Var.b(62, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.A));
                        break;
                    case 63:
                        i2 = i3;
                        d8Var.a(63, typedArrayObtainStyledAttributes.getFloat(index, f8Var.B));
                        break;
                    case 64:
                        i2 = i3;
                        d8Var.b(64, e(typedArrayObtainStyledAttributes, index, g8Var.a));
                        break;
                    case 65:
                        i2 = i3;
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            d8Var.c(65, typedArrayObtainStyledAttributes.getString(index));
                        } else {
                            d8Var.c(65, strArr[typedArrayObtainStyledAttributes.getInteger(index, 0)]);
                        }
                        break;
                    case 66:
                        i2 = i3;
                        d8Var.b(66, typedArrayObtainStyledAttributes.getInt(index, 0));
                        break;
                    case 67:
                        i2 = i3;
                        d8Var.a(67, typedArrayObtainStyledAttributes.getFloat(index, g8Var.e));
                        break;
                    case 68:
                        i2 = i3;
                        d8Var.a(68, typedArrayObtainStyledAttributes.getFloat(index, h8Var.d));
                        break;
                    case 69:
                        i2 = i3;
                        d8Var.a(69, typedArrayObtainStyledAttributes.getFloat(index, 1.0f));
                        break;
                    case 70:
                        i2 = i3;
                        d8Var.a(70, typedArrayObtainStyledAttributes.getFloat(index, 1.0f));
                        break;
                    case 71:
                        i2 = i3;
                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        break;
                    case 72:
                        i2 = i3;
                        d8Var.b(72, typedArrayObtainStyledAttributes.getInt(index, f8Var.f0));
                        break;
                    case 73:
                        i2 = i3;
                        d8Var.b(73, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.g0));
                        break;
                    case 74:
                        i2 = i3;
                        d8Var.c(74, typedArrayObtainStyledAttributes.getString(index));
                        break;
                    case 75:
                        i2 = i3;
                        d8Var.d(75, typedArrayObtainStyledAttributes.getBoolean(index, f8Var.n0));
                        break;
                    case 76:
                        i2 = i3;
                        d8Var.b(76, typedArrayObtainStyledAttributes.getInt(index, g8Var.c));
                        break;
                    case 77:
                        i2 = i3;
                        d8Var.c(77, typedArrayObtainStyledAttributes.getString(index));
                        break;
                    case 78:
                        i2 = i3;
                        d8Var.b(78, typedArrayObtainStyledAttributes.getInt(index, h8Var.b));
                        break;
                    case 79:
                        i2 = i3;
                        d8Var.a(79, typedArrayObtainStyledAttributes.getFloat(index, g8Var.d));
                        break;
                    case 80:
                        i2 = i3;
                        d8Var.d(80, typedArrayObtainStyledAttributes.getBoolean(index, f8Var.l0));
                        break;
                    case 81:
                        i2 = i3;
                        d8Var.d(81, typedArrayObtainStyledAttributes.getBoolean(index, f8Var.m0));
                        break;
                    case 82:
                        i2 = i3;
                        d8Var.b(82, typedArrayObtainStyledAttributes.getInteger(index, g8Var.b));
                        break;
                    case 83:
                        i2 = i3;
                        d8Var.b(83, e(typedArrayObtainStyledAttributes, index, i8Var.h));
                        break;
                    case 84:
                        i2 = i3;
                        d8Var.b(84, typedArrayObtainStyledAttributes.getInteger(index, g8Var.g));
                        break;
                    case 85:
                        i2 = i3;
                        d8Var.a(85, typedArrayObtainStyledAttributes.getFloat(index, g8Var.f));
                        break;
                    case 86:
                        i2 = i3;
                        int i5 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i5 == 1) {
                            int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            g8Var.i = resourceId2;
                            d8Var.b(89, resourceId2);
                            if (g8Var.i != -1) {
                                d8Var.b(88, -2);
                            }
                        } else if (i5 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            g8Var.h = string;
                            d8Var.c(90, string);
                            if (g8Var.h.indexOf("/") > 0) {
                                int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                g8Var.i = resourceId3;
                                d8Var.b(89, resourceId3);
                                d8Var.b(88, -2);
                            } else {
                                d8Var.b(88, -1);
                            }
                        } else {
                            d8Var.b(88, typedArrayObtainStyledAttributes.getInteger(index, g8Var.i));
                        }
                        break;
                    case 87:
                        i2 = i3;
                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                        break;
                    case 93:
                        i2 = i3;
                        d8Var.b(93, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.L));
                        break;
                    case 94:
                        i2 = i3;
                        d8Var.b(94, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, f8Var.S));
                        break;
                    case 95:
                        i2 = i3;
                        f(d8Var, typedArrayObtainStyledAttributes, index, 0);
                        break;
                    case 96:
                        i2 = i3;
                        f(d8Var, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    case 97:
                        i2 = i3;
                        d8Var.b(97, typedArrayObtainStyledAttributes.getInt(index, f8Var.o0));
                        break;
                    case 98:
                        i2 = i3;
                        int i6 = jj.q;
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            e8Var.a = typedArrayObtainStyledAttributes.getResourceId(index, e8Var.a);
                        }
                        break;
                    case 99:
                        i2 = i3;
                        d8Var.d(99, typedArrayObtainStyledAttributes.getBoolean(index, f8Var.g));
                        break;
                }
                i3 = i2 + 1;
                indexCount = i4;
            }
        } else {
            int i7 = 0;
            for (int indexCount2 = typedArrayObtainStyledAttributes.getIndexCount(); i7 < indexCount2; indexCount2 = i) {
                int index2 = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index2 != 1 && 23 != index2) {
                    if (24 != index2) {
                        g8Var.getClass();
                        f8Var.getClass();
                        i8Var.getClass();
                    }
                }
                switch (sparseIntArray.get(index2)) {
                    case 1:
                        i = indexCount2;
                        f8Var.p = e(typedArrayObtainStyledAttributes, index2, f8Var.p);
                        continue;
                        i7++;
                        break;
                    case 2:
                        i = indexCount2;
                        f8Var.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.I);
                        continue;
                        i7++;
                        break;
                    case 3:
                        i = indexCount2;
                        f8Var.o = e(typedArrayObtainStyledAttributes, index2, f8Var.o);
                        continue;
                        i7++;
                        break;
                    case 4:
                        i = indexCount2;
                        f8Var.n = e(typedArrayObtainStyledAttributes, index2, f8Var.n);
                        continue;
                        i7++;
                        break;
                    case 5:
                        i = indexCount2;
                        f8Var.y = typedArrayObtainStyledAttributes.getString(index2);
                        continue;
                        i7++;
                        break;
                    case 6:
                        i = indexCount2;
                        f8Var.C = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, f8Var.C);
                        continue;
                        i7++;
                        break;
                    case 7:
                        i = indexCount2;
                        f8Var.D = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, f8Var.D);
                        continue;
                        i7++;
                        break;
                    case 8:
                        i = indexCount2;
                        f8Var.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.J);
                        continue;
                        i7++;
                        break;
                    case 9:
                        i = indexCount2;
                        f8Var.v = e(typedArrayObtainStyledAttributes, index2, f8Var.v);
                        continue;
                        i7++;
                        break;
                    case 10:
                        i = indexCount2;
                        f8Var.u = e(typedArrayObtainStyledAttributes, index2, f8Var.u);
                        continue;
                        i7++;
                        break;
                    case 11:
                        i = indexCount2;
                        f8Var.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.P);
                        continue;
                        i7++;
                        break;
                    case 12:
                        i = indexCount2;
                        f8Var.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.Q);
                        continue;
                        i7++;
                        break;
                    case 13:
                        i = indexCount2;
                        f8Var.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.M);
                        continue;
                        i7++;
                        break;
                    case 14:
                        i = indexCount2;
                        f8Var.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.O);
                        continue;
                        i7++;
                        break;
                    case 15:
                        i = indexCount2;
                        f8Var.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.R);
                        continue;
                        i7++;
                        break;
                    case 16:
                        i = indexCount2;
                        f8Var.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.N);
                        continue;
                        i7++;
                        break;
                    case 17:
                        i = indexCount2;
                        f8Var.d = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, f8Var.d);
                        continue;
                        i7++;
                        break;
                    case 18:
                        i = indexCount2;
                        f8Var.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, f8Var.e);
                        continue;
                        i7++;
                        break;
                    case 19:
                        i = indexCount2;
                        f8Var.f = typedArrayObtainStyledAttributes.getFloat(index2, f8Var.f);
                        continue;
                        i7++;
                        break;
                    case 20:
                        i = indexCount2;
                        f8Var.w = typedArrayObtainStyledAttributes.getFloat(index2, f8Var.w);
                        continue;
                        i7++;
                        break;
                    case 21:
                        i = indexCount2;
                        f8Var.c = typedArrayObtainStyledAttributes.getLayoutDimension(index2, f8Var.c);
                        continue;
                        i7++;
                        break;
                    case 22:
                        i = indexCount2;
                        int i8 = typedArrayObtainStyledAttributes.getInt(index2, h8Var.a);
                        h8Var.a = i8;
                        h8Var.a = iArr[i8];
                        continue;
                        i7++;
                        break;
                    case 23:
                        i = indexCount2;
                        f8Var.b = typedArrayObtainStyledAttributes.getLayoutDimension(index2, f8Var.b);
                        continue;
                        i7++;
                        break;
                    case 24:
                        i = indexCount2;
                        f8Var.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.F);
                        continue;
                        i7++;
                        break;
                    case 25:
                        i = indexCount2;
                        f8Var.h = e(typedArrayObtainStyledAttributes, index2, f8Var.h);
                        continue;
                        i7++;
                        break;
                    case 26:
                        i = indexCount2;
                        f8Var.i = e(typedArrayObtainStyledAttributes, index2, f8Var.i);
                        continue;
                        i7++;
                        break;
                    case 27:
                        i = indexCount2;
                        f8Var.E = typedArrayObtainStyledAttributes.getInt(index2, f8Var.E);
                        continue;
                        i7++;
                        break;
                    case 28:
                        i = indexCount2;
                        f8Var.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.G);
                        continue;
                        i7++;
                        break;
                    case 29:
                        i = indexCount2;
                        f8Var.j = e(typedArrayObtainStyledAttributes, index2, f8Var.j);
                        continue;
                        i7++;
                        break;
                    case 30:
                        i = indexCount2;
                        f8Var.k = e(typedArrayObtainStyledAttributes, index2, f8Var.k);
                        continue;
                        i7++;
                        break;
                    case 31:
                        i = indexCount2;
                        f8Var.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.K);
                        continue;
                        i7++;
                        break;
                    case 32:
                        i = indexCount2;
                        f8Var.s = e(typedArrayObtainStyledAttributes, index2, f8Var.s);
                        continue;
                        i7++;
                        break;
                    case 33:
                        i = indexCount2;
                        f8Var.t = e(typedArrayObtainStyledAttributes, index2, f8Var.t);
                        continue;
                        i7++;
                        break;
                    case 34:
                        i = indexCount2;
                        f8Var.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.H);
                        continue;
                        i7++;
                        break;
                    case 35:
                        i = indexCount2;
                        f8Var.m = e(typedArrayObtainStyledAttributes, index2, f8Var.m);
                        continue;
                        i7++;
                        break;
                    case 36:
                        i = indexCount2;
                        f8Var.l = e(typedArrayObtainStyledAttributes, index2, f8Var.l);
                        continue;
                        i7++;
                        break;
                    case 37:
                        i = indexCount2;
                        f8Var.x = typedArrayObtainStyledAttributes.getFloat(index2, f8Var.x);
                        continue;
                        i7++;
                        break;
                    case 38:
                        i = indexCount2;
                        e8Var.a = typedArrayObtainStyledAttributes.getResourceId(index2, e8Var.a);
                        continue;
                        i7++;
                        break;
                    case 39:
                        i = indexCount2;
                        f8Var.U = typedArrayObtainStyledAttributes.getFloat(index2, f8Var.U);
                        continue;
                        i7++;
                        break;
                    case 40:
                        i = indexCount2;
                        f8Var.T = typedArrayObtainStyledAttributes.getFloat(index2, f8Var.T);
                        continue;
                        i7++;
                        break;
                    case 41:
                        i = indexCount2;
                        f8Var.V = typedArrayObtainStyledAttributes.getInt(index2, f8Var.V);
                        continue;
                        i7++;
                        break;
                    case 42:
                        i = indexCount2;
                        f8Var.W = typedArrayObtainStyledAttributes.getInt(index2, f8Var.W);
                        continue;
                        i7++;
                        break;
                    case 43:
                        i = indexCount2;
                        h8Var.c = typedArrayObtainStyledAttributes.getFloat(index2, h8Var.c);
                        continue;
                        i7++;
                        break;
                    case 44:
                        i = indexCount2;
                        i8Var.l = true;
                        i8Var.m = typedArrayObtainStyledAttributes.getDimension(index2, i8Var.m);
                        continue;
                        i7++;
                        break;
                    case 45:
                        i = indexCount2;
                        i8Var.b = typedArrayObtainStyledAttributes.getFloat(index2, i8Var.b);
                        continue;
                        i7++;
                        break;
                    case 46:
                        i = indexCount2;
                        i8Var.c = typedArrayObtainStyledAttributes.getFloat(index2, i8Var.c);
                        continue;
                        i7++;
                        break;
                    case 47:
                        i = indexCount2;
                        i8Var.d = typedArrayObtainStyledAttributes.getFloat(index2, i8Var.d);
                        continue;
                        i7++;
                        break;
                    case 48:
                        i = indexCount2;
                        i8Var.e = typedArrayObtainStyledAttributes.getFloat(index2, i8Var.e);
                        continue;
                        i7++;
                        break;
                    case 49:
                        i = indexCount2;
                        i8Var.f = typedArrayObtainStyledAttributes.getDimension(index2, i8Var.f);
                        continue;
                        i7++;
                        break;
                    case 50:
                        i = indexCount2;
                        i8Var.g = typedArrayObtainStyledAttributes.getDimension(index2, i8Var.g);
                        continue;
                        i7++;
                        break;
                    case 51:
                        i = indexCount2;
                        i8Var.i = typedArrayObtainStyledAttributes.getDimension(index2, i8Var.i);
                        continue;
                        i7++;
                        break;
                    case 52:
                        i = indexCount2;
                        i8Var.j = typedArrayObtainStyledAttributes.getDimension(index2, i8Var.j);
                        continue;
                        i7++;
                        break;
                    case 53:
                        i = indexCount2;
                        i8Var.k = typedArrayObtainStyledAttributes.getDimension(index2, i8Var.k);
                        continue;
                        i7++;
                        break;
                    case 54:
                        i = indexCount2;
                        f8Var.X = typedArrayObtainStyledAttributes.getInt(index2, f8Var.X);
                        continue;
                        i7++;
                        break;
                    case 55:
                        i = indexCount2;
                        f8Var.Y = typedArrayObtainStyledAttributes.getInt(index2, f8Var.Y);
                        continue;
                        i7++;
                        break;
                    case 56:
                        i = indexCount2;
                        f8Var.Z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.Z);
                        continue;
                        i7++;
                        break;
                    case 57:
                        i = indexCount2;
                        f8Var.a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.a0);
                        continue;
                        i7++;
                        break;
                    case 58:
                        i = indexCount2;
                        f8Var.b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.b0);
                        continue;
                        i7++;
                        break;
                    case 59:
                        i = indexCount2;
                        f8Var.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.c0);
                        continue;
                        i7++;
                        break;
                    case 60:
                        i = indexCount2;
                        i8Var.a = typedArrayObtainStyledAttributes.getFloat(index2, i8Var.a);
                        continue;
                        i7++;
                        break;
                    case 61:
                        i = indexCount2;
                        f8Var.z = e(typedArrayObtainStyledAttributes, index2, f8Var.z);
                        continue;
                        i7++;
                        break;
                    case 62:
                        i = indexCount2;
                        f8Var.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.A);
                        continue;
                        i7++;
                        break;
                    case 63:
                        i = indexCount2;
                        f8Var.B = typedArrayObtainStyledAttributes.getFloat(index2, f8Var.B);
                        continue;
                        i7++;
                        break;
                    case 64:
                        i = indexCount2;
                        g8Var.a = e(typedArrayObtainStyledAttributes, index2, g8Var.a);
                        continue;
                        i7++;
                        break;
                    case 65:
                        i = indexCount2;
                        if (typedArrayObtainStyledAttributes.peekValue(index2).type == 3) {
                            typedArrayObtainStyledAttributes.getString(index2);
                            g8Var.getClass();
                        } else {
                            String str = strArr[typedArrayObtainStyledAttributes.getInteger(index2, 0)];
                            g8Var.getClass();
                            i7++;
                        }
                        break;
                    case 66:
                        i = indexCount2;
                        typedArrayObtainStyledAttributes.getInt(index2, 0);
                        g8Var.getClass();
                        continue;
                        i7++;
                        break;
                    case 67:
                        i = indexCount2;
                        g8Var.e = typedArrayObtainStyledAttributes.getFloat(index2, g8Var.e);
                        break;
                    case 68:
                        i = indexCount2;
                        h8Var.d = typedArrayObtainStyledAttributes.getFloat(index2, h8Var.d);
                        break;
                    case 69:
                        i = indexCount2;
                        f8Var.d0 = typedArrayObtainStyledAttributes.getFloat(index2, 1.0f);
                        break;
                    case 70:
                        i = indexCount2;
                        f8Var.e0 = typedArrayObtainStyledAttributes.getFloat(index2, 1.0f);
                        break;
                    case 71:
                        i = indexCount2;
                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        break;
                    case 72:
                        i = indexCount2;
                        f8Var.f0 = typedArrayObtainStyledAttributes.getInt(index2, f8Var.f0);
                        break;
                    case 73:
                        i = indexCount2;
                        f8Var.g0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.g0);
                        break;
                    case 74:
                        i = indexCount2;
                        f8Var.j0 = typedArrayObtainStyledAttributes.getString(index2);
                        break;
                    case 75:
                        i = indexCount2;
                        f8Var.n0 = typedArrayObtainStyledAttributes.getBoolean(index2, f8Var.n0);
                        break;
                    case 76:
                        i = indexCount2;
                        g8Var.c = typedArrayObtainStyledAttributes.getInt(index2, g8Var.c);
                        break;
                    case 77:
                        i = indexCount2;
                        f8Var.k0 = typedArrayObtainStyledAttributes.getString(index2);
                        break;
                    case 78:
                        i = indexCount2;
                        h8Var.b = typedArrayObtainStyledAttributes.getInt(index2, h8Var.b);
                        break;
                    case 79:
                        i = indexCount2;
                        g8Var.d = typedArrayObtainStyledAttributes.getFloat(index2, g8Var.d);
                        break;
                    case 80:
                        i = indexCount2;
                        f8Var.l0 = typedArrayObtainStyledAttributes.getBoolean(index2, f8Var.l0);
                        break;
                    case 81:
                        i = indexCount2;
                        f8Var.m0 = typedArrayObtainStyledAttributes.getBoolean(index2, f8Var.m0);
                        break;
                    case 82:
                        i = indexCount2;
                        g8Var.b = typedArrayObtainStyledAttributes.getInteger(index2, g8Var.b);
                        break;
                    case 83:
                        i = indexCount2;
                        i8Var.h = e(typedArrayObtainStyledAttributes, index2, i8Var.h);
                        break;
                    case 84:
                        i = indexCount2;
                        g8Var.g = typedArrayObtainStyledAttributes.getInteger(index2, g8Var.g);
                        break;
                    case 85:
                        i = indexCount2;
                        g8Var.f = typedArrayObtainStyledAttributes.getFloat(index2, g8Var.f);
                        break;
                    case 86:
                        i = indexCount2;
                        int i9 = typedArrayObtainStyledAttributes.peekValue(index2).type;
                        if (i9 == 1) {
                            g8Var.i = typedArrayObtainStyledAttributes.getResourceId(index2, -1);
                        } else if (i9 == 3) {
                            String string2 = typedArrayObtainStyledAttributes.getString(index2);
                            g8Var.h = string2;
                            if (string2.indexOf("/") > 0) {
                                g8Var.i = typedArrayObtainStyledAttributes.getResourceId(index2, -1);
                            }
                        } else {
                            typedArrayObtainStyledAttributes.getInteger(index2, g8Var.i);
                        }
                        break;
                    case 87:
                        i = indexCount2;
                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index2) + "   " + sparseIntArray.get(index2));
                        break;
                    case 88:
                    case 89:
                    case 90:
                    default:
                        StringBuilder sb2 = new StringBuilder("Unknown attribute 0x");
                        i = indexCount2;
                        sb2.append(Integer.toHexString(index2));
                        sb2.append("   ");
                        sb2.append(sparseIntArray.get(index2));
                        Log.w("ConstraintSet", sb2.toString());
                        break;
                    case 91:
                        i = indexCount2;
                        f8Var.q = e(typedArrayObtainStyledAttributes, index2, f8Var.q);
                        break;
                    case 92:
                        i = indexCount2;
                        f8Var.r = e(typedArrayObtainStyledAttributes, index2, f8Var.r);
                        break;
                    case 93:
                        i = indexCount2;
                        f8Var.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.L);
                        break;
                    case 94:
                        i = indexCount2;
                        f8Var.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, f8Var.S);
                        break;
                    case 95:
                        i = indexCount2;
                        f(f8Var, typedArrayObtainStyledAttributes, index2, 0);
                        continue;
                        i7++;
                        break;
                    case 96:
                        i = indexCount2;
                        f(f8Var, typedArrayObtainStyledAttributes, index2, 1);
                        break;
                    case 97:
                        i = indexCount2;
                        f8Var.o0 = typedArrayObtainStyledAttributes.getInt(index2, f8Var.o0);
                        break;
                }
                i7++;
            }
            if (f8Var.j0 != null) {
                f8Var.i0 = null;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return e8Var;
    }

    public static int e(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void f(java.lang.Object r7, android.content.res.TypedArray r8, int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j8.f(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void g(z7 z7Var, String str) {
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            int i = -1;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                i = strSubstring.equalsIgnoreCase("W") ? 0 : strSubstring.equalsIgnoreCase("H") ? 1 : -1;
                i = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i);
                    if (strSubstring2.length() > 0) {
                        Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f2 = Float.parseFloat(strSubstring3);
                        float f3 = Float.parseFloat(strSubstring4);
                        if (f2 > 0.0f && f3 > 0.0f) {
                            if (i == 1) {
                                Math.abs(f3 / f2);
                            } else {
                                Math.abs(f2 / f3);
                            }
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        z7Var.G = str;
    }

    public final void a(Context context, int i) {
        ConstraintLayout constraintLayout;
        int i2;
        HashMap map;
        int i3;
        int i4;
        j8 j8Var = this;
        ConstraintLayout constraintLayout2 = (ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        int childCount = constraintLayout2.getChildCount();
        HashMap map2 = j8Var.c;
        map2.clear();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = constraintLayout2.getChildAt(i5);
            z7 z7Var = (z7) childAt.getLayoutParams();
            int id = childAt.getId();
            if (j8Var.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!map2.containsKey(Integer.valueOf(id))) {
                map2.put(Integer.valueOf(id), new e8());
            }
            e8 e8Var = (e8) map2.get(Integer.valueOf(id));
            if (e8Var == null) {
                constraintLayout = constraintLayout2;
                i2 = childCount;
                map = map2;
                i3 = i5;
            } else {
                h8 h8Var = e8Var.b;
                f8 f8Var = e8Var.d;
                i8 i8Var = e8Var.e;
                constraintLayout = constraintLayout2;
                HashMap map3 = new HashMap();
                i2 = childCount;
                Class<?> cls = childAt.getClass();
                map = map2;
                HashMap map4 = j8Var.a;
                for (String str : map4.keySet()) {
                    w7 w7Var = (w7) map4.get(str);
                    HashMap map5 = map4;
                    try {
                        if (str.equals("BackgroundColor")) {
                            i4 = i5;
                            try {
                                map3.put(str, new w7(w7Var, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                            } catch (IllegalAccessException e2) {
                                e = e2;
                                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName(), e);
                            } catch (NoSuchMethodException e3) {
                                e = e3;
                                Log.e("TransitionLayout", cls.getName() + " must have a method " + str, e);
                            } catch (InvocationTargetException e4) {
                                e = e4;
                                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName(), e);
                            }
                        } else {
                            i4 = i5;
                            map3.put(str, new w7(w7Var, cls.getMethod("getMap" + str, null).invoke(childAt, null)));
                        }
                    } catch (IllegalAccessException e5) {
                        e = e5;
                        i4 = i5;
                    } catch (NoSuchMethodException e6) {
                        e = e6;
                        i4 = i5;
                    } catch (InvocationTargetException e7) {
                        e = e7;
                        i4 = i5;
                    }
                    map4 = map5;
                    i5 = i4;
                }
                i3 = i5;
                e8Var.f = map3;
                e8Var.a = id;
                f8Var.h = z7Var.e;
                f8Var.i = z7Var.f;
                f8Var.j = z7Var.g;
                f8Var.k = z7Var.h;
                f8Var.l = z7Var.i;
                f8Var.m = z7Var.j;
                f8Var.n = z7Var.k;
                f8Var.o = z7Var.l;
                f8Var.p = z7Var.m;
                f8Var.q = z7Var.n;
                f8Var.r = z7Var.o;
                f8Var.s = z7Var.s;
                f8Var.t = z7Var.t;
                f8Var.u = z7Var.u;
                f8Var.v = z7Var.v;
                f8Var.w = z7Var.E;
                f8Var.x = z7Var.F;
                f8Var.y = z7Var.G;
                f8Var.z = z7Var.p;
                f8Var.A = z7Var.q;
                f8Var.B = z7Var.r;
                f8Var.C = z7Var.T;
                f8Var.D = z7Var.U;
                f8Var.E = z7Var.V;
                f8Var.f = z7Var.c;
                f8Var.d = z7Var.a;
                f8Var.e = z7Var.b;
                f8Var.b = ((ViewGroup.MarginLayoutParams) z7Var).width;
                f8Var.c = ((ViewGroup.MarginLayoutParams) z7Var).height;
                f8Var.F = ((ViewGroup.MarginLayoutParams) z7Var).leftMargin;
                f8Var.G = ((ViewGroup.MarginLayoutParams) z7Var).rightMargin;
                f8Var.H = ((ViewGroup.MarginLayoutParams) z7Var).topMargin;
                f8Var.I = ((ViewGroup.MarginLayoutParams) z7Var).bottomMargin;
                f8Var.L = z7Var.D;
                f8Var.T = z7Var.I;
                f8Var.U = z7Var.H;
                f8Var.W = z7Var.K;
                f8Var.V = z7Var.J;
                f8Var.l0 = z7Var.W;
                f8Var.m0 = z7Var.X;
                f8Var.X = z7Var.L;
                f8Var.Y = z7Var.M;
                f8Var.Z = z7Var.P;
                f8Var.a0 = z7Var.Q;
                f8Var.b0 = z7Var.N;
                f8Var.c0 = z7Var.O;
                f8Var.d0 = z7Var.R;
                f8Var.e0 = z7Var.S;
                f8Var.k0 = z7Var.Y;
                f8Var.N = z7Var.x;
                f8Var.P = z7Var.z;
                f8Var.M = z7Var.w;
                f8Var.O = z7Var.y;
                f8Var.R = z7Var.A;
                f8Var.Q = z7Var.B;
                f8Var.S = z7Var.C;
                f8Var.o0 = z7Var.Z;
                f8Var.J = z7Var.getMarginEnd();
                f8Var.K = z7Var.getMarginStart();
                h8Var.a = childAt.getVisibility();
                h8Var.c = childAt.getAlpha();
                i8Var.a = childAt.getRotation();
                i8Var.b = childAt.getRotationX();
                i8Var.c = childAt.getRotationY();
                i8Var.d = childAt.getScaleX();
                i8Var.e = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    i8Var.f = pivotX;
                    i8Var.g = pivotY;
                }
                i8Var.i = childAt.getTranslationX();
                i8Var.j = childAt.getTranslationY();
                i8Var.k = childAt.getTranslationZ();
                if (i8Var.l) {
                    i8Var.m = childAt.getElevation();
                }
                if (childAt instanceof g6) {
                    g6 g6Var = (g6) childAt;
                    f8Var.n0 = g6Var.getAllowsGoneWidget();
                    f8Var.i0 = g6Var.getReferencedIds();
                    f8Var.f0 = g6Var.getType();
                    f8Var.g0 = g6Var.getMargin();
                }
            }
            i5 = i3 + 1;
            j8Var = this;
            constraintLayout2 = constraintLayout;
            childCount = i2;
            map2 = map;
        }
    }

    public final void d(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    e8 e8VarC = c(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        e8VarC.d.a = true;
                    }
                    this.c.put(Integer.valueOf(e8VarC.a), e8VarC);
                }
            }
        } catch (IOException e2) {
            Log.e("ConstraintSet", "Error parsing resource: " + i, e2);
        } catch (XmlPullParserException e3) {
            Log.e("ConstraintSet", "Error parsing resource: " + i, e3);
        }
    }
}
