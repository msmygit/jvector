// Generated by jextract

package io.github.jbellis.jvector.vector.cnative;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

public class NativeSimdOps {

    NativeSimdOps() {
        // Should not be called directly
    }

    static final Arena LIBRARY_ARENA = Arena.ofAuto();
    static final boolean TRACE_DOWNCALLS = Boolean.getBoolean("jextract.trace.downcalls");

    static void traceDowncall(String name, Object... args) {
         String traceArgs = Arrays.stream(args)
                       .map(Object::toString)
                       .collect(Collectors.joining(", "));
         System.out.printf("%s(%s)\n", name, traceArgs);
    }

    static MemorySegment findOrThrow(String symbol) {
        return SYMBOL_LOOKUP.find(symbol)
            .orElseThrow(() -> new UnsatisfiedLinkError("unresolved symbol: " + symbol));
    }

    static MethodHandle upcallHandle(Class<?> fi, String name, FunctionDescriptor fdesc) {
        try {
            return MethodHandles.lookup().findVirtual(fi, name, fdesc.toMethodType());
        } catch (ReflectiveOperationException ex) {
            throw new AssertionError(ex);
        }
    }

    static MemoryLayout align(MemoryLayout layout, long align) {
        return switch (layout) {
            case PaddingLayout p -> p;
            case ValueLayout v -> v.withByteAlignment(align);
            case GroupLayout g -> {
                MemoryLayout[] alignedMembers = g.memberLayouts().stream()
                        .map(m -> align(m, align)).toArray(MemoryLayout[]::new);
                yield g instanceof StructLayout ?
                        MemoryLayout.structLayout(alignedMembers) : MemoryLayout.unionLayout(alignedMembers);
            }
            case SequenceLayout s -> MemoryLayout.sequenceLayout(s.elementCount(), align(s.elementLayout(), align));
        };
    }

    static final SymbolLookup SYMBOL_LOOKUP = SymbolLookup.loaderLookup()
            .or(Linker.nativeLinker().defaultLookup());

    public static final ValueLayout.OfBoolean C_BOOL = ValueLayout.JAVA_BOOLEAN;
    public static final ValueLayout.OfByte C_CHAR = ValueLayout.JAVA_BYTE;
    public static final ValueLayout.OfShort C_SHORT = ValueLayout.JAVA_SHORT;
    public static final ValueLayout.OfInt C_INT = ValueLayout.JAVA_INT;
    public static final ValueLayout.OfLong C_LONG_LONG = ValueLayout.JAVA_LONG;
    public static final ValueLayout.OfFloat C_FLOAT = ValueLayout.JAVA_FLOAT;
    public static final ValueLayout.OfDouble C_DOUBLE = ValueLayout.JAVA_DOUBLE;
    public static final AddressLayout C_POINTER = ValueLayout.ADDRESS
            .withTargetLayout(MemoryLayout.sequenceLayout(java.lang.Long.MAX_VALUE, JAVA_BYTE));
    public static final ValueLayout.OfLong C_LONG = ValueLayout.JAVA_LONG;
    private static final int true_ = (int)1L;
    /**
     * {@snippet lang=c :
     * #define true 1
     * }
     */
    public static int true_() {
        return true_;
    }
    private static final int false_ = (int)0L;
    /**
     * {@snippet lang=c :
     * #define false 0
     * }
     */
    public static int false_() {
        return false_;
    }
    private static final int __bool_true_false_are_defined = (int)1L;
    /**
     * {@snippet lang=c :
     * #define __bool_true_false_are_defined 1
     * }
     */
    public static int __bool_true_false_are_defined() {
        return __bool_true_false_are_defined;
    }

    private static class check_compatibility {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            NativeSimdOps.C_BOOL    );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("check_compatibility"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * _Bool check_compatibility()
     * }
     */
    public static FunctionDescriptor check_compatibility$descriptor() {
        return check_compatibility.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * _Bool check_compatibility()
     * }
     */
    public static MethodHandle check_compatibility$handle() {
        return check_compatibility.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * _Bool check_compatibility()
     * }
     */
    public static boolean check_compatibility() {
        var mh$ = check_compatibility.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("check_compatibility");
            }
            return (boolean)mh$.invokeExact();
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class dot_product_f32 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("dot_product_f32"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * float dot_product_f32(int preferred_size, const float *a, int aoffset, const float *b, int boffset, int length)
     * }
     */
    public static FunctionDescriptor dot_product_f32$descriptor() {
        return dot_product_f32.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * float dot_product_f32(int preferred_size, const float *a, int aoffset, const float *b, int boffset, int length)
     * }
     */
    public static MethodHandle dot_product_f32$handle() {
        return dot_product_f32.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * float dot_product_f32(int preferred_size, const float *a, int aoffset, const float *b, int boffset, int length)
     * }
     */
    public static float dot_product_f32(int preferred_size, MemorySegment a, int aoffset, MemorySegment b, int boffset, int length) {
        var mh$ = dot_product_f32.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("dot_product_f32", preferred_size, a, aoffset, b, boffset, length);
            }
            return (float)mh$.invokeExact(preferred_size, a, aoffset, b, boffset, length);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class euclidean_f32 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("euclidean_f32"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * float euclidean_f32(int preferred_size, const float *a, int aoffset, const float *b, int boffset, int length)
     * }
     */
    public static FunctionDescriptor euclidean_f32$descriptor() {
        return euclidean_f32.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * float euclidean_f32(int preferred_size, const float *a, int aoffset, const float *b, int boffset, int length)
     * }
     */
    public static MethodHandle euclidean_f32$handle() {
        return euclidean_f32.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * float euclidean_f32(int preferred_size, const float *a, int aoffset, const float *b, int boffset, int length)
     * }
     */
    public static float euclidean_f32(int preferred_size, MemorySegment a, int aoffset, MemorySegment b, int boffset, int length) {
        var mh$ = euclidean_f32.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("euclidean_f32", preferred_size, a, aoffset, b, boffset, length);
            }
            return (float)mh$.invokeExact(preferred_size, a, aoffset, b, boffset, length);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class bulk_quantized_shuffle_dot_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_POINTER
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("bulk_quantized_shuffle_dot_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_dot_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartials, float delta, float minDistance, float *results)
     * }
     */
    public static FunctionDescriptor bulk_quantized_shuffle_dot_f32_512$descriptor() {
        return bulk_quantized_shuffle_dot_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_dot_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartials, float delta, float minDistance, float *results)
     * }
     */
    public static MethodHandle bulk_quantized_shuffle_dot_f32_512$handle() {
        return bulk_quantized_shuffle_dot_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_dot_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartials, float delta, float minDistance, float *results)
     * }
     */
    public static void bulk_quantized_shuffle_dot_f32_512(MemorySegment shuffles, int codebookCount, MemorySegment quantizedPartials, float delta, float minDistance, MemorySegment results) {
        var mh$ = bulk_quantized_shuffle_dot_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("bulk_quantized_shuffle_dot_f32_512", shuffles, codebookCount, quantizedPartials, delta, minDistance, results);
            }
            mh$.invokeExact(shuffles, codebookCount, quantizedPartials, delta, minDistance, results);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class bulk_quantized_shuffle_euclidean_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_POINTER
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("bulk_quantized_shuffle_euclidean_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_euclidean_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartials, float delta, float minDistance, float *results)
     * }
     */
    public static FunctionDescriptor bulk_quantized_shuffle_euclidean_f32_512$descriptor() {
        return bulk_quantized_shuffle_euclidean_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_euclidean_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartials, float delta, float minDistance, float *results)
     * }
     */
    public static MethodHandle bulk_quantized_shuffle_euclidean_f32_512$handle() {
        return bulk_quantized_shuffle_euclidean_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_euclidean_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartials, float delta, float minDistance, float *results)
     * }
     */
    public static void bulk_quantized_shuffle_euclidean_f32_512(MemorySegment shuffles, int codebookCount, MemorySegment quantizedPartials, float delta, float minDistance, MemorySegment results) {
        var mh$ = bulk_quantized_shuffle_euclidean_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("bulk_quantized_shuffle_euclidean_f32_512", shuffles, codebookCount, quantizedPartials, delta, minDistance, results);
            }
            mh$.invokeExact(shuffles, codebookCount, quantizedPartials, delta, minDistance, results);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class bulk_quantized_shuffle_cosine_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_POINTER
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("bulk_quantized_shuffle_cosine_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_cosine_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartialSums, float sumDelta, float minDistance, const char *quantizedPartialMagnitudes, float magnitudeDelta, float minMagnitude, float queryMagnitudeSquared, float *results)
     * }
     */
    public static FunctionDescriptor bulk_quantized_shuffle_cosine_f32_512$descriptor() {
        return bulk_quantized_shuffle_cosine_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_cosine_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartialSums, float sumDelta, float minDistance, const char *quantizedPartialMagnitudes, float magnitudeDelta, float minMagnitude, float queryMagnitudeSquared, float *results)
     * }
     */
    public static MethodHandle bulk_quantized_shuffle_cosine_f32_512$handle() {
        return bulk_quantized_shuffle_cosine_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * void bulk_quantized_shuffle_cosine_f32_512(const unsigned char *shuffles, int codebookCount, const char *quantizedPartialSums, float sumDelta, float minDistance, const char *quantizedPartialMagnitudes, float magnitudeDelta, float minMagnitude, float queryMagnitudeSquared, float *results)
     * }
     */
    public static void bulk_quantized_shuffle_cosine_f32_512(MemorySegment shuffles, int codebookCount, MemorySegment quantizedPartialSums, float sumDelta, float minDistance, MemorySegment quantizedPartialMagnitudes, float magnitudeDelta, float minMagnitude, float queryMagnitudeSquared, MemorySegment results) {
        var mh$ = bulk_quantized_shuffle_cosine_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("bulk_quantized_shuffle_cosine_f32_512", shuffles, codebookCount, quantizedPartialSums, sumDelta, minDistance, quantizedPartialMagnitudes, magnitudeDelta, minMagnitude, queryMagnitudeSquared, results);
            }
            mh$.invokeExact(shuffles, codebookCount, quantizedPartialSums, sumDelta, minDistance, quantizedPartialMagnitudes, magnitudeDelta, minMagnitude, queryMagnitudeSquared, results);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class assemble_and_sum_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("assemble_and_sum_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * float assemble_and_sum_f32_512(const float *data, int dataBase, const unsigned char *baseOffsets, int baseOffsetsOffset, int baseOffsetsLength)
     * }
     */
    public static FunctionDescriptor assemble_and_sum_f32_512$descriptor() {
        return assemble_and_sum_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * float assemble_and_sum_f32_512(const float *data, int dataBase, const unsigned char *baseOffsets, int baseOffsetsOffset, int baseOffsetsLength)
     * }
     */
    public static MethodHandle assemble_and_sum_f32_512$handle() {
        return assemble_and_sum_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * float assemble_and_sum_f32_512(const float *data, int dataBase, const unsigned char *baseOffsets, int baseOffsetsOffset, int baseOffsetsLength)
     * }
     */
    public static float assemble_and_sum_f32_512(MemorySegment data, int dataBase, MemorySegment baseOffsets, int baseOffsetsOffset, int baseOffsetsLength) {
        var mh$ = assemble_and_sum_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("assemble_and_sum_f32_512", data, dataBase, baseOffsets, baseOffsetsOffset, baseOffsetsLength);
            }
            return (float)mh$.invokeExact(data, dataBase, baseOffsets, baseOffsetsOffset, baseOffsetsLength);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class pq_decoded_cosine_similarity_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            NativeSimdOps.C_FLOAT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_FLOAT
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("pq_decoded_cosine_similarity_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * float pq_decoded_cosine_similarity_f32_512(const unsigned char *baseOffsets, int baseOffsetsOffset, int baseOffsetsLength, int clusterCount, const float *partialSums, const float *aMagnitude, float bMagnitude)
     * }
     */
    public static FunctionDescriptor pq_decoded_cosine_similarity_f32_512$descriptor() {
        return pq_decoded_cosine_similarity_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * float pq_decoded_cosine_similarity_f32_512(const unsigned char *baseOffsets, int baseOffsetsOffset, int baseOffsetsLength, int clusterCount, const float *partialSums, const float *aMagnitude, float bMagnitude)
     * }
     */
    public static MethodHandle pq_decoded_cosine_similarity_f32_512$handle() {
        return pq_decoded_cosine_similarity_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * float pq_decoded_cosine_similarity_f32_512(const unsigned char *baseOffsets, int baseOffsetsOffset, int baseOffsetsLength, int clusterCount, const float *partialSums, const float *aMagnitude, float bMagnitude)
     * }
     */
    public static float pq_decoded_cosine_similarity_f32_512(MemorySegment baseOffsets, int baseOffsetsOffset, int baseOffsetsLength, int clusterCount, MemorySegment partialSums, MemorySegment aMagnitude, float bMagnitude) {
        var mh$ = pq_decoded_cosine_similarity_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("pq_decoded_cosine_similarity_f32_512", baseOffsets, baseOffsetsOffset, baseOffsetsLength, clusterCount, partialSums, aMagnitude, bMagnitude);
            }
            return (float)mh$.invokeExact(baseOffsets, baseOffsetsOffset, baseOffsetsLength, clusterCount, partialSums, aMagnitude, bMagnitude);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class calculate_partial_sums_dot_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("calculate_partial_sums_dot_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void calculate_partial_sums_dot_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums)
     * }
     */
    public static FunctionDescriptor calculate_partial_sums_dot_f32_512$descriptor() {
        return calculate_partial_sums_dot_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void calculate_partial_sums_dot_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums)
     * }
     */
    public static MethodHandle calculate_partial_sums_dot_f32_512$handle() {
        return calculate_partial_sums_dot_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * void calculate_partial_sums_dot_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums)
     * }
     */
    public static void calculate_partial_sums_dot_f32_512(MemorySegment codebook, int codebookBase, int size, int clusterCount, MemorySegment query, int queryOffset, MemorySegment partialSums) {
        var mh$ = calculate_partial_sums_dot_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("calculate_partial_sums_dot_f32_512", codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums);
            }
            mh$.invokeExact(codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class calculate_partial_sums_euclidean_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("calculate_partial_sums_euclidean_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void calculate_partial_sums_euclidean_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums)
     * }
     */
    public static FunctionDescriptor calculate_partial_sums_euclidean_f32_512$descriptor() {
        return calculate_partial_sums_euclidean_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void calculate_partial_sums_euclidean_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums)
     * }
     */
    public static MethodHandle calculate_partial_sums_euclidean_f32_512$handle() {
        return calculate_partial_sums_euclidean_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * void calculate_partial_sums_euclidean_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums)
     * }
     */
    public static void calculate_partial_sums_euclidean_f32_512(MemorySegment codebook, int codebookBase, int size, int clusterCount, MemorySegment query, int queryOffset, MemorySegment partialSums) {
        var mh$ = calculate_partial_sums_euclidean_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("calculate_partial_sums_euclidean_f32_512", codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums);
            }
            mh$.invokeExact(codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class calculate_partial_sums_best_dot_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_POINTER
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("calculate_partial_sums_best_dot_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void calculate_partial_sums_best_dot_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums, float *partialBestDistances)
     * }
     */
    public static FunctionDescriptor calculate_partial_sums_best_dot_f32_512$descriptor() {
        return calculate_partial_sums_best_dot_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void calculate_partial_sums_best_dot_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums, float *partialBestDistances)
     * }
     */
    public static MethodHandle calculate_partial_sums_best_dot_f32_512$handle() {
        return calculate_partial_sums_best_dot_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * void calculate_partial_sums_best_dot_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums, float *partialBestDistances)
     * }
     */
    public static void calculate_partial_sums_best_dot_f32_512(MemorySegment codebook, int codebookBase, int size, int clusterCount, MemorySegment query, int queryOffset, MemorySegment partialSums, MemorySegment partialBestDistances) {
        var mh$ = calculate_partial_sums_best_dot_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("calculate_partial_sums_best_dot_f32_512", codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums, partialBestDistances);
            }
            mh$.invokeExact(codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums, partialBestDistances);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class calculate_partial_sums_best_euclidean_f32_512 {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_INT,
            NativeSimdOps.C_POINTER,
            NativeSimdOps.C_POINTER
        );

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(
                    NativeSimdOps.findOrThrow("calculate_partial_sums_best_euclidean_f32_512"),
                    DESC, Linker.Option.critical(true));
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void calculate_partial_sums_best_euclidean_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums, float *partialBestDistances)
     * }
     */
    public static FunctionDescriptor calculate_partial_sums_best_euclidean_f32_512$descriptor() {
        return calculate_partial_sums_best_euclidean_f32_512.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void calculate_partial_sums_best_euclidean_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums, float *partialBestDistances)
     * }
     */
    public static MethodHandle calculate_partial_sums_best_euclidean_f32_512$handle() {
        return calculate_partial_sums_best_euclidean_f32_512.HANDLE;
    }
    /**
     * {@snippet lang=c :
     * void calculate_partial_sums_best_euclidean_f32_512(const float *codebook, int codebookBase, int size, int clusterCount, const float *query, int queryOffset, float *partialSums, float *partialBestDistances)
     * }
     */
    public static void calculate_partial_sums_best_euclidean_f32_512(MemorySegment codebook, int codebookBase, int size, int clusterCount, MemorySegment query, int queryOffset, MemorySegment partialSums, MemorySegment partialBestDistances) {
        var mh$ = calculate_partial_sums_best_euclidean_f32_512.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("calculate_partial_sums_best_euclidean_f32_512", codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums, partialBestDistances);
            }
            mh$.invokeExact(codebook, codebookBase, size, clusterCount, query, queryOffset, partialSums, partialBestDistances);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }
}

