package com.example.study_webapp.model.api;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class GithubApi implements Comparator<Map<String,Object>> {
    @Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        return 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public GithubApi() {
        super();
    }

    @Override
    public Comparator<Map<String, Object>> thenComparingDouble(ToDoubleFunction<? super Map<String, Object>> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }

    @Override
    public Comparator<Map<String, Object>> thenComparingLong(ToLongFunction<? super Map<String, Object>> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<Map<String, Object>> thenComparingInt(ToIntFunction<? super Map<String, Object>> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Map<String, Object>> thenComparing(Function<? super Map<String, Object>, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public <U> Comparator<Map<String, Object>> thenComparing(Function<? super Map<String, Object>, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public Comparator<Map<String, Object>> thenComparing(Comparator<? super Map<String, Object>> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public Comparator<Map<String, Object>> reversed() {
        return Comparator.super.reversed();
    }
}
