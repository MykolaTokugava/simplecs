package ua.metlife.claims.simplecs.controllers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

public class ClaimСontrollerTest {

    @Test
    public void getList() {

        ArrayList<Integer> testValuesNull = new ArrayList();
        testValuesNull.add(0,null);
        testValuesNull.add(1,1);
        testValuesNull.add(2,2);
        testValuesNull.add(3,70);
        testValuesNull.add(4,50);

        Optional<Integer> maxValueNotNull =  testValuesNull.stream().filter((p) -> p != null).max(Integer::compareTo);
        System.out.println("maxValueNotNull="+maxValueNotNull);

    }
}