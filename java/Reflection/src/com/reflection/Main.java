package com.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
        Class clazz = Reflection.class;
        Reflection obj = null;

        System.out.println("========= class name ========");
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());

        System.out.println("========= class modifier ========");
        int modifier = clazz.getModifiers();
        System.out.println(modifier);
        System.out.println("is interface: " + Modifier.isInterface(modifier));
        System.out.println("is final " + Modifier.isFinal(modifier));
        System.out.println("is abstract: "+ Modifier.isAbstract(modifier));
        System.out.println("is public: " + Modifier.isPublic(modifier));

        System.out.println("========= constructor =========");
        try {
            Constructor constructor = clazz.getConstructor(new Class[]{int.class, String.class});
            Class[] classes = constructor.getParameterTypes();
            System.out.println("Constructor name: " + constructor.getName());
            System.out.print("Parameters:");
            for(Class parameter: classes){
                System.out.print(parameter.getName() + ",");
            }
            System.out.println();
            obj = (Reflection)constructor.newInstance(13, "hello");
        } catch (Exception e) {
        }

        System.out.println("======== fields ============");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        try {
            System.out.println("name is: " + obj.name);
            Field nameField = clazz.getField("name");
            nameField.set(obj, "hello cat!");
            System.out.println("new name is: " + obj.name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("======== Declared class fields ========");
        fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        try {
            System.out.println("id value is: " + obj.getId());
            Field idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(obj, 1);
            System.out.println("new id value is: " + obj.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("========== class methods ==========");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        try {
            Method method = clazz.getMethod("calculateRandomName");
            System.out.println(method.getReturnType());
            Object result = method.invoke(obj);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("======== Declared class methods ========");
        methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("========== package ==========");
        Package packageInfo = clazz.getPackage();
        System.out.println(packageInfo.getName());

        System.out.println("======== super class & interface ========");
        System.out.println(clazz.getSuperclass());
        Class[] interfaces = clazz.getInterfaces();
        for (Class interfaceInfo : interfaces) {
            System.out.println(interfaceInfo);
        }
        System.out.println("========== Generic interface ==========");
        Type[] types = clazz.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                for (Type parameterType: parameterizedType.getActualTypeArguments()){
                    System.out.println(parameterType);
                }
            }
        }

        System.out.println("======== Annotation ============");
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation: annotations){
            if(annotation instanceof CustomAnnotation){
                System.out.println(annotation.annotationType());
                System.out.println(((CustomAnnotation) annotation).name());
                System.out.println(((CustomAnnotation) annotation).value());
            }
        }

        System.out.println("======== Array ==========");
        int[] array = (int[]) Array.newInstance(int.class, 2);
        Array.set(array, 0 ,1);
        Array.set(array, 1, 2);
        System.out.println("Array: " + array[0] + ", " + array[1]);
        System.out.println(array.getClass().getComponentType());
    }
}
