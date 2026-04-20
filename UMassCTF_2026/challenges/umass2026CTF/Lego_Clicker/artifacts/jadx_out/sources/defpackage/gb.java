package defpackage;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class gb extends hb {
    public final TextView o;
    public final cb p;
    public boolean q = true;

    public gb(TextView textView) {
        this.o = textView;
        this.p = new cb(textView);
    }

    @Override // defpackage.hb
    public final InputFilter[] C(InputFilter[] inputFilterArr) {
        if (!this.q) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i = 0; i < inputFilterArr.length; i++) {
                InputFilter inputFilter = inputFilterArr[i];
                if (inputFilter instanceof cb) {
                    sparseArray.put(i, inputFilter);
                }
            }
            if (sparseArray.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - sparseArray.size()];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (sparseArray.indexOfKey(i3) < 0) {
                    inputFilterArr2[i2] = inputFilterArr[i3];
                    i2++;
                }
            }
            return inputFilterArr2;
        }
        int length2 = inputFilterArr.length;
        int i4 = 0;
        while (true) {
            cb cbVar = this.p;
            if (i4 >= length2) {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = cbVar;
                return inputFilterArr3;
            }
            if (inputFilterArr[i4] == cbVar) {
                return inputFilterArr;
            }
            i4++;
        }
    }

    @Override // defpackage.hb
    public final void T(boolean z) {
        if (z) {
            f0();
        }
    }

    @Override // defpackage.hb
    public final void U(boolean z) {
        this.q = z;
        f0();
        TextView textView = this.o;
        textView.setFilters(C(textView.getFilters()));
    }

    public final void f0() {
        TextView textView = this.o;
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        if (this.q) {
            if (!(transformationMethod instanceof lb) && !(transformationMethod instanceof PasswordTransformationMethod)) {
                transformationMethod = new lb(transformationMethod);
            }
        } else if (transformationMethod instanceof lb) {
            transformationMethod = ((lb) transformationMethod).a;
        }
        textView.setTransformationMethod(transformationMethod);
    }
}
