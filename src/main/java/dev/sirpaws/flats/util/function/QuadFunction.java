package dev.sirpaws.flats.util.function;

@FunctionalInterface
public interface QuadFunction<Arg1, Arg2, Arg3, Arg4, Output> {
    Output apply(Arg1 arg1, Arg2 arg2, Arg3 arg3, Arg4 arg4);
}
