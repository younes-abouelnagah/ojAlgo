/*
 * Copyright 1997-2015 Optimatika (www.optimatika.se)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.ojalgo.matrix.store;

import org.ojalgo.function.UnaryFunction;
import org.ojalgo.scalar.Scalar;

final class UnaryOperatorStore<N extends Number> extends LogicalStore<N> {

    private final UnaryFunction<N> myFunction;

    private UnaryOperatorStore(final int rows, final int columns, final MatrixStore<N> base) {

        super(base, rows, columns);

        myFunction = null;
    }

    UnaryOperatorStore(final MatrixStore<N> base, final UnaryFunction<N> function) {

        super(base, (int) base.countRows(), (int) base.countColumns());

        myFunction = function;
    }

    public double doubleValue(final long row, final long col) {
        return myFunction.invoke(this.getBase().doubleValue(row, col));
    }

    public N get(final long row, final long col) {
        return myFunction.invoke(this.getBase().get(row, col));
    }

    public Scalar<N> toScalar(final long row, final long column) {
        return this.factory().scalar().convert(this.get(row, column));
    }

}
