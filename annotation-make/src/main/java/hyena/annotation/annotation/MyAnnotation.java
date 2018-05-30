package hyena.annotation.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_PARAMETER})
public @interface MyAnnotation {


    /**
     * TYPE_PARAMETER 注解使用在范型，
     * 而TYPE_USE 是任何使用类型的地方都可以使用TYPE_USE注解
     *
     *
     * @param <T>
     */
    public  class A< @MyAnnotation T> {
    }



}

