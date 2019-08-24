import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class TestClass {
    private static Object obj;
    static boolean after = false;
    static boolean before = false;

    public static void start(Class clazz) {

        Method afterMetod = null;
        Method beforeMetod = null;
        ArrayList<Method> allMethods = new ArrayList<>();
        Method[] clazzMethods = clazz.getDeclaredMethods();

        if (!afterTrue(clazz) & !beforeTrue(clazz)) {
            throw new RuntimeException();
        }

        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Method method : clazzMethods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeMetod = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                afterMetod = method;
            } else if (method.getAnnotation(Test.class) != null) {
                allMethods.add(method);
            }
        }

        allMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        if (beforeMetod != null) {
            allMethods.add(0, beforeMetod);
        }

        if (afterMetod != null) {
            allMethods.add(afterMetod);
        }

        try {
            for (Method testMethod : allMethods) {
                if (Modifier.isPrivate(testMethod.getModifiers())) {
                    testMethod.setAccessible(true);
                }
                testMethod.invoke(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

}

    private static boolean beforeTrue(Class clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                before = true;
            }
        }
        return before;
    }

    private static boolean afterTrue(Class clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getAnnotation(AfterSuite.class) != null) {
                after = true;
            }
        }
        return after;
    }
}
