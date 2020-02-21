package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance(){
        Point p1 = new Point(1,1);
        Point p2 = new Point(3,3);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }

    @Test
    public void testDouble(){
        Point p1 = new Point(1,1);
        Point p2 = new Point(3,3);
        Assert.assertNotNull(p1.distance(p2));
    }

}
