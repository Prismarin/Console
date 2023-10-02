package dev.duuduu.engine;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This Annotation shows that makes no use to modify values like this later, because they are only used at the start
 */
@Retention(RetentionPolicy.SOURCE)
public @interface EngineStart {
}
